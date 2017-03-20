package com.example.guo.rxreview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guo.rxreview.R;


/**
 * Created by guo_hx on 2017/3/20 10:41.
 */

public class UseRxAdapter extends RecyclerView.Adapter<UseRxAdapter.Holder> {

    String[] mArray = {"qqq", "www", "eee", "rrr",
            "qqq", "www", "eee", "rrr",
            "qqq", "www", "eee", "rrr",
            "qqq", "www", "eee", "rrr",
            "qqq", "www", "eee", "rrr",
            "qqq", "www", "eee", "rrr"};

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_use, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.mTvTitle.setText(mArray[position]);
        holder.mLlItemRvRoot.setOnClickListener(v -> {
            Toast.makeText(holder.mLlItemRvRoot.getContext(), "item__" + position, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return mArray.length;
    }

    static class Holder extends RecyclerView.ViewHolder {

        private final TextView mTvTitle;
        private final View mLlItemRvRoot;

        public Holder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mLlItemRvRoot = itemView.findViewById(R.id.ll_item_rv_root);
        }

    }

}
