package com.example.guo.rxreview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guo.rxreview.R;
import com.example.guo.rxreview.adapter.UseRxAdapter;

/**
 * Created by guo_hx on 2017/3/20 10:20.
 */
public class UseRxActivity extends AppCompatActivity {

    private static final String TAG = UseRxActivity.class.getSimpleName();
    private RecyclerView mRvUse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userxjava);

        mRvUse = (RecyclerView) findViewById(R.id.rv_use);
        mRvUse.setLayoutManager(new LinearLayoutManager(this));
        mRvUse.setAdapter(new UseRxAdapter());
    }
}
