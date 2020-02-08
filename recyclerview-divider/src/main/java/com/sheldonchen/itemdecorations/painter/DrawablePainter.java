package com.sheldonchen.itemdecorations.painter;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.sheldonchen.itemdecorations.painter.base.IDividerPainter;

/**
 * Created by cxd on 2018/4/17
 */
public class DrawablePainter implements IDividerPainter {
    @NonNull
    private final Drawable mDividerDrawable;

    public DrawablePainter(@NonNull Drawable drawable) {
        mDividerDrawable = drawable;
    }

    @Override
    public void drawDivider(@NonNull Canvas canvas, int left, int top, int right, int bottom) {
        mDividerDrawable.setBounds(left, top, right, bottom);
        mDividerDrawable.draw(canvas);
    }

    @Override
    public int calcHorizontalThickness(int provided) {
        return Math.max(provided, mDividerDrawable.getIntrinsicWidth());
    }

    @Override
    public int calcVerticalThickness(int provided) {
        return Math.max(provided, mDividerDrawable.getIntrinsicHeight());
    }
}
