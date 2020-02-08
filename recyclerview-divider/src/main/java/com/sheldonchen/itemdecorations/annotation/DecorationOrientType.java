package com.sheldonchen.itemdecorations.annotation;

import androidx.annotation.IntDef;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by cxd on 2018/3/7
 */

@IntDef({LinearLayoutManager.VERTICAL, LinearLayoutManager.HORIZONTAL})
@Retention(RetentionPolicy.SOURCE)
public @interface DecorationOrientType {
}
