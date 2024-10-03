package com.xsdk.ab_firstbase.statisics.util.image;

import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Field;

/* loaded from: classes3.dex */
public class ImageSizeUtil {

    /* loaded from: classes3.dex */
    public static class ImageSize {
        int height;
        int width;
    }

    public static int caculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        if (i3 > i || i4 > i2) {
            return Math.max(Math.round((i3 * 1.0f) / i), Math.round((i4 * 1.0f) / i2));
        }
        return 1;
    }

    public static ImageSize getImageViewSize(ImageView imageView) {
        ImageSize imageSize = new ImageSize();
        DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        int width = imageView.getWidth();
        if (width <= 0) {
            width = layoutParams.width;
        }
        if (width <= 0) {
            width = getImageViewFieldValue(imageView, "mMaxWidth");
        }
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }
        int height = imageView.getHeight();
        if (height <= 0) {
            height = layoutParams.height;
        }
        if (height <= 0) {
            height = getImageViewFieldValue(imageView, "mMaxHeight");
        }
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }
        imageSize.width = width;
        imageSize.height = height;
        return imageSize;
    }

    private static int getImageViewFieldValue(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int i = declaredField.getInt(obj);
            if (i <= 0 || i >= Integer.MAX_VALUE) {
                return 0;
            }
            return i;
        } catch (Exception unused) {
            return 0;
        }
    }
}
