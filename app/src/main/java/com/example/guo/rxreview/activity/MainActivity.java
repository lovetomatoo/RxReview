package com.example.guo.rxreview.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guo.rxreview.R;
import com.example.guo.rxreview.utils.BitmapUtil;
import com.example.guo.rxreview.weiget.ImageCollectorView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * creat by guo_hx
 *
 * 这个Sample主要用来帮助自己理解rxjava
 *
 * 练习rxjava的使用
 * 熟悉rxjava的使用场景
 *
 */

public class MainActivity extends AppCompatActivity {

    private ImageCollectorView mIcvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Integer[] pics = {R.mipmap.img_player__like_blue,
                R.mipmap.img_player__like_orange,
                R.mipmap.img_player__like_purple,
                R.mipmap.img_player__like_red,
                R.mipmap.img_player__like_yellow};
        mIcvMain = (ImageCollectorView) findViewById(R.id.icv_main);

//        showPicsComm(pics);
        showPicsRxJava(pics);

    }

    private void showPicsComm(final Integer[] pics) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < pics.length; i++) {
                    final Bitmap bitmap = BitmapUtil.getBitmapFromResources(MainActivity.this, pics[i]);
                    runOnUiThread(() -> mIcvMain.addImage(bitmap));
                }
            }
        }.start();
    }

    private void showPicsRxJava(final Integer[] pics) {
        Observable.from(pics)
                .map(integer -> BitmapUtil.getBitmapFromResources(MainActivity.this, integer))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> {mIcvMain.addImage(bitmap);});
    }
}
