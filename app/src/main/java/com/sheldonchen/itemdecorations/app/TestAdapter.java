package com.sheldonchen.itemdecorations.app;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

/**
 * Created by cxd on 2018/4/17
 */

public class TestAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommonViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        TextView textView = holder.findView(R.id.tv);
        textView.setText(String.format(Locale.getDefault(), "%d", position));
    }

    @Override
    public int getItemCount() {
        return 25;
    }

}
