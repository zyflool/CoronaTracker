package com.zyflool.coronatracker.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zyflool.coronatracker.R;

public class ListNoItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textView;

    public ListNoItemViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.iv_no_item);
        textView = itemView.findViewById(R.id.tv_no_item);
    }
}
