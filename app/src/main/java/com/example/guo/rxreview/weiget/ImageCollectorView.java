package com.example.guo.rxreview.weiget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by guo_hx on 2017/3/14 17:23.
 */

public class ImageCollectorView extends LinearLayout {

    private Context mContext;

    public ImageCollectorView(Context context) {
        super(context);
        init(context);
    }

    public ImageCollectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageCollectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        mContext = context;
    }

    public void addImage(Bitmap bitmap) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageBitmap(bitmap);
        this.addView(imageView);
    }

}
