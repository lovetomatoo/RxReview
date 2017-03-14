package com.example.guo.rxreview.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by guo_hx on 2017/3/14 17:47.
 */

public class BitmapUtil {

    public static Bitmap getBitmapFromResources(Context context, int id) {

        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
    
}
