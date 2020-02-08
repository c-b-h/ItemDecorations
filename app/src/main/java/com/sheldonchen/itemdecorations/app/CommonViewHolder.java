package com.sheldonchen.itemdecorations.app;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by cxd on 2017/05/17
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> mViews = new SparseArray<>();

    public CommonViewHolder(View itemView) {
        super(itemView);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViews.put(id, view);
        }
        return (T) view;
    }

    public Context getContext() {
        return itemView.getContext();
    }
}