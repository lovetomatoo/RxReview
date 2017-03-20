package com.example.guo.rxreview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guo.rxreview.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by guo_hx on 2017/3/20 10:41.
 */

public class UseRxAdapter extends RecyclerView.Adapter<UseRxAdapter.Holder> {

    private static final String TAG = UseRxAdapter.class.getSimpleName();

    String[] mArray = {"数据变换", "延迟处理事件", "eee", "rrr",
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
            Toast.makeText(holder.mLlItemRvRoot.getContext(), mArray[position], Toast.LENGTH_SHORT).show();
            switch (position) {
                case 0://data change
                    Observable.just("1", "2", "3", "2", "4", "5", "5", "6", "7")
                            .map(Integer::parseInt)
                            .filter(integer -> integer > 3)
                            .distinct()
                            .take(3)
                            .reduce((integer, integer2) -> integer + integer2)
                            .subscribe(integer -> {
                                Log.i(TAG, "integer == " + integer);
                            });
                    break;

                case 1://delayed handle event
                    Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                            .subscribe(aLong -> {
                                holder.mTvTitle.setText("延迟处理事件__完成");
                            });
                    break;
            }
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
