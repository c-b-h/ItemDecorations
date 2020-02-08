package com.sheldonchen.itemdecorations.painter;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.sheldonchen.itemdecorations.painter.base.IDividerPainter;

/**
 * Created by cxd on 2018/4/17
 */

public class ColorIntPainter implements IDividerPainter {
    private final Paint mPaint;

    public ColorIntPainter(@ColorInt Integer colorInt) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(colorInt);
    }

    @Override
    public void drawDivider(@NonNull Canvas canvas, int left, int top, int right, int bottom) {
        canvas.drawRect(left, top, right, bottom, mPaint);
    }

}
