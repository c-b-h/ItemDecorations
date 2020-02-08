package com.sheldonchen.itemdecorations.decorations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sheldonchen.itemdecorations.CheckUtil;
import com.sheldonchen.itemdecorations.annotation.DecorationOrientType;
import com.sheldonchen.itemdecorations.painter.ColorIntPainter;
import com.sheldonchen.itemdecorations.painter.DrawablePainter;
import com.sheldonchen.itemdecorations.painter.base.IDividerPainter;

import java.util.HashSet;
import java.util.Set;

/**
 * 适用于RecyclerView线性布局下的Divider(ItemDecoration)
 * <p>
 * Created by cxd on 2017/05/24
 */

public class LinearLayoutDivider extends RecyclerView.ItemDecoration {

    public static final class Builder {

        /**
         * RecyclerView布局方向.
         */
        @DecorationOrientType
        int mOrientation = LinearLayoutManager.VERTICAL;

        /**
         * 分割线厚度.
         */
        int mDividerThickness = 0;

        /**
         * 竖向列表：左边距   横向列表：上边距.
         */
        int mStartPadding = 0;

        /**
         * 竖向列表：右边距   横向列表：下边距.
         */
        int mEndPadding = 0;

        /**
         * 是否画第一个item之前的分割线.
         */
        boolean mDrawFirstDivider = false;

        /**
         * 是否画最后一个item之后的分割线.
         */
        boolean mDrawLastDivider = false;

        /**
         * Painter: 支持Drawable和ColorInt或者自定义IDividerPainter.
         */
        Function<Context, IDividerPainter> mLazyPainter = null;

        /**
         * 指定不画分割线的位置的集合(set).
         */
        final Set<Integer> mNonDrawPositions = new HashSet<>();

        public Builder setOrientation(@DecorationOrientType int orientation) {
            this.mOrientation = orientation;
            return this;
        }

        public Builder setStartPadding(int startPadding) {
            this.mStartPadding = CheckUtil.ensureNatural(startPadding);
            return this;
        }

        public Builder setEndPadding(int endPadding) {
            this.mEndPadding = CheckUtil.ensureNatural(endPadding);
            return this;
        }

        public Builder setLazyPainter(@NonNull Function<Context, IDividerPainter> lazyPainter) {
            this.mLazyPainter = lazyPainter;
            return this;
        }

        public Builder setPainter(@NonNull IDividerPainter painter) {
            setLazyPainter((c) -> painter);
            return this;
        }

        public Builder setLazyDividerColor(@NonNull Function<Context, Integer> lazyDividerColor) {
            setLazyPainter((c) -> new ColorIntPainter(lazyDividerColor.apply(c)));
            return this;
        }

        public Builder setDividerColor(@ColorInt int color) {
            setLazyDividerColor((c) -> color);
            return this;
        }

        public Builder setLazyDividerDrawable(@NonNull Function<Context, Drawable> lazyDividerDrawable) {
            setLazyPainter((c) -> new DrawablePainter(lazyDividerDrawable.apply(c)));
            return this;
        }

        public Builder setDividerDrawable(@NonNull Drawable drawable) {
            setLazyDividerDrawable((c) -> drawable);
            return this;
        }

        public Builder drawFirstDivider(boolean isDraw) {
            this.mDrawFirstDivider = isDraw;
            return this;
        }

        public Builder drawLastDivider(boolean isDraw) {
            this.mDrawLastDivider = isDraw;
            return this;
        }

        public Builder setDividerThickness(int dividerThickness) {
            this.mDividerThickness = CheckUtil.ensureNatural(dividerThickness);
            return this;
        }

        /**
         * 指定位置的divider不画, 可指定多个位置.
         */
        public Builder notDrawSpecificDivider(int... positions) {
            if (positions == null) return this;
            for (int pos : positions) {
                mNonDrawPositions.add(pos);
            }
            return this;
        }

        public LinearLayoutDivider build() {
            return new LinearLayoutDivider(this);
        }

        public void apply(RecyclerView recyclerView) {
            if (recyclerView == null) return;

            recyclerView.addItemDecoration(build());
        }

        public void apply(RecyclerView... recyclerViews) {
            if (recyclerViews == null || recyclerViews.length == 0) return;

            LinearLayoutDivider divider = build();
            for (RecyclerView recyclerView : recyclerViews) {
                recyclerView.addItemDecoration(divider);
            }
        }

    }

    private final Builder mBuilder;

    @Nullable
    private IDividerPainter mDividerPainter;

    private LinearLayoutDivider(@NonNull Builder builder) {
        Preconditions.checkNotNull(builder, "LinearLayoutDivider: mBuilder can't be null.");
        this.mBuilder = builder;
    }

    @Override
    public void onDraw(
            @NonNull Canvas canvas,
            @NonNull RecyclerView parent,
            @NonNull RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        @NonNull final IDividerPainter dividerPainter = dividerPainter(parent);

        if (mBuilder.mOrientation == LinearLayoutManager.VERTICAL) {
            drawOrientVerticalDivider(canvas, parent, dividerPainter);
        } else {
            drawOrientHorizontalDivider(canvas, parent, dividerPainter);
        }
    }

    private void drawOrientVerticalDivider(
            @NonNull Canvas canvas,
            @NonNull RecyclerView parent,
            @NonNull IDividerPainter dividerPainter) {
        final int left = parent.getPaddingLeft() + mBuilder.mStartPadding;
        final int right = parent.getWidth() - parent.getPaddingRight() - mBuilder.mEndPadding;

        for (int i = 0, childCount = parent.getChildCount(); i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) childView.getLayoutParams();

            final int layoutPos = parent.getChildLayoutPosition(childView);
            int top = childView.getBottom() + params.bottomMargin;
            int bottom = top + dividerPainter.calcVerticalThickness(mBuilder.mDividerThickness);

            if (i < childCount - 1 || mBuilder.mDrawLastDivider) {
                if (!mBuilder.mNonDrawPositions.contains(layoutPos)) {
                    dividerPainter.drawDivider(canvas, left, top, right, bottom);
                }
            }
            if (i == 0 && mBuilder.mDrawFirstDivider) {
                bottom = childView.getTop() - params.topMargin;
                top = bottom - dividerPainter.calcVerticalThickness(mBuilder.mDividerThickness);
                dividerPainter.drawDivider(canvas, left, top, right, bottom);
            }
        }
    }

    private void drawOrientHorizontalDivider(
            @NonNull Canvas canvas,
            @NonNull RecyclerView parent,
            @NonNull IDividerPainter dividerPainter) {
        final int top = parent.getPaddingTop() + mBuilder.mStartPadding;
        final int bottom = parent.getHeight() - parent.getPaddingBottom() - mBuilder.mEndPadding;

        for (int i = 0, childCount = parent.getChildCount(); i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) childView.getLayoutParams();

            final int layoutPos = parent.getChildLayoutPosition(childView);
            int left = childView.getRight() + params.rightMargin;
            int right = left + dividerPainter.calcHorizontalThickness(mBuilder.mDividerThickness);
            if (i < childCount - 1 || mBuilder.mDrawLastDivider) {
                if (!mBuilder.mNonDrawPositions.contains(layoutPos)) {
                    dividerPainter.drawDivider(canvas, left, top, right, bottom);
                }
            }
            if (i == 0 && mBuilder.mDrawFirstDivider) {
                right = childView.getLeft() - params.leftMargin;
                left = right - dividerPainter.calcHorizontalThickness(mBuilder.mDividerThickness);
                dividerPainter.drawDivider(canvas, left, top, right, bottom);
            }
        }
    }

    @Override
    public void getItemOffsets(
            @NonNull Rect outRect,
            @NonNull View view,
            @NonNull RecyclerView parent,
            @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        @NonNull final IDividerPainter dividerPainter = dividerPainter(parent);
        final int currentPos = parent.getChildLayoutPosition(view);
        final int lastPos = state.getItemCount() - 1;

        if (mBuilder.mOrientation == LinearLayoutManager.VERTICAL) {
            if (currentPos == 0 && mBuilder.mDrawFirstDivider) {
                outRect.top = dividerPainter.calcVerticalThickness(mBuilder.mDividerThickness);
            }
            if ((currentPos == lastPos && !mBuilder.mDrawLastDivider)
                    || mBuilder.mNonDrawPositions.contains(currentPos)) {
                outRect.bottom = 0;
            } else {
                outRect.bottom = dividerPainter.calcVerticalThickness(mBuilder.mDividerThickness);
            }
        } else {// horizontal.
            if (currentPos == 0 && mBuilder.mDrawFirstDivider) {
                outRect.left = dividerPainter.calcHorizontalThickness(mBuilder.mDividerThickness);
            }
            if ((currentPos == lastPos && !mBuilder.mDrawLastDivider)
                    || mBuilder.mNonDrawPositions.contains(currentPos)) {
                outRect.right = 0;
            } else {
                outRect.right = dividerPainter.calcHorizontalThickness(mBuilder.mDividerThickness);
            }
        }
    }

    @NonNull
    private IDividerPainter dividerPainter(@NonNull RecyclerView recyclerView) {
        if (mDividerPainter == null) {
            mDividerPainter = mBuilder.mLazyPainter.apply(recyclerView.getContext());
        }

        return mDividerPainter;
    }
}
