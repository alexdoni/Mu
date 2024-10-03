package com.xsdk.ab_firstbase.statisics.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/* loaded from: classes3.dex */
public class DensityUtil {
    public static int dip2px(Context context, float f) {
        if (context == null) {
            context = ContextHolder.getContext();
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getWidth(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public static int getHeiht(Context context) {
        Point point = new Point();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealSize(point);
        return point.y;
    }

    public static float getScreenWidth(Activity activity) {
        try {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
            return r0.widthPixels;
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    public static float getScreenHeight(Context context) {
        try {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            return r0.heightPixels;
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    public static int getStateHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    public static int getAPPHeight(Activity activity) {
        int height = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay().getHeight();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return height - rect.top;
    }
}
