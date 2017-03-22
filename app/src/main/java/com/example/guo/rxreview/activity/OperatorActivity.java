package com.example.guo.rxreview.activity;

import com.example.guo.rxreview.R;
import com.example.guo.rxreview.adapter.OperatorAdapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by guo_hx on 2017/3/22 11:32 11:55.
 */
public class OperatorActivity extends AppCompatActivity {

    private static final String TAG = OperatorActivity.class.getSimpleName();

    private RecyclerView mRvOperator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);

        mRvOperator = (RecyclerView) findViewById(R.id.rv_operator);

        mRvOperator.setLayoutManager(new LinearLayoutManager(this));
        mRvOperator.setAdapter(new OperatorAdapter());
    }

}
