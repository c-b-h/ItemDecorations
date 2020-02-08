package com.sheldonchen.itemdecorations.painter.base;

import android.graphics.Canvas;

import androidx.annotation.NonNull;

/**
 * Created by cxd on 2018/4/17
 */

public interface IDividerPainter {

    void drawDivider(@NonNull Canvas canvas, int left, int top, int right, int bottom);

    default int calcHorizontalThickness(int assumed) {
        return assumed;
    }

    default int calcVerticalThickness(int assumed) {
        return assumed;
    }
}
