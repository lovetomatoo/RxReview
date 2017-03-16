package com.example.guo.rxreview.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.guo.rxreview.R;
import com.example.guo.rxreview.utils.BitmapUtil;
import com.example.guo.rxreview.weiget.ImageCollectorView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * creat by guo_hx
 * <p>
 * 这个Sample主要用来帮助自己理解rxjava
 * <p>
 * 练习rxjava的使用
 * 熟悉rxjava的使用场景
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageCollectorView mIcvMain;
    private ImageView mIvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Integer[] pics = {R.mipmap.img_player__like_blue,
                R.mipmap.img_player__like_orange,
                R.mipmap.img_player__like_purple,
                R.mipmap.img_player__like_red,
                R.mipmap.img_player__like_yellow};

        String[] str_names = {"西毒欧阳锋", "东邪黄药师", "南帝段智兴", "北丐洪七公", "中神通王重阳"};

        int drawableId = R.mipmap.ic_launcher;


        mIcvMain = (ImageCollectorView) findViewById(R.id.icv_main);
        mIvMain = (ImageView) findViewById(R.id.iv_main);

        //1.展示图片
//        showPicsComm(pics);
        showPicsRxJava(pics);

        //2.打印数组字符串
        printStr(str_names);

        //3.根据资源id展示图片
        showPicFromResId(drawableId);

        //4.Schedulers--打印1234
        printNums();
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
                .subscribe(bitmap -> {
                    mIcvMain.addImage(bitmap);
                });
    }

    private void printStr(String[] str_names) {
        Observable.from(str_names)
                .subscribe(s -> {
                    Log.d(TAG, s);
                });
    }

    private void showPicFromResId(int drawableId) {
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Log.w(TAG, "showPicFromResId____subscribe____运行的线程为：" + Thread.currentThread().getName());
                Drawable drawable = getDrawable(drawableId);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Drawable>() {
                    @Override
                    public void onNext(Drawable drawable) {
                        Log.w(TAG, "showPicFromResId____onNext____运行的线程为：" + Thread.currentThread().getName());
                        mIvMain.setImageDrawable(drawable);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void printNums() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())//subscribe在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//Subscriber的回调发生在主线程
                .subscribe(integer -> {
                    Log.d(TAG, "number: " + integer);
                });
    }
}
