package com.xsdk.ab_firstbase.statisics.util;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

/* loaded from: classes3.dex */
public class DealTouchDelegate {
    public static void expandViewTouchDelegate(final View view, final int i, final int i2, final int i3, final int i4) {
        ((View) view.getParent()).post(new Runnable() { // from class: com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate.1
            @Override // java.lang.Runnable
            public void run() {
                Rect rect = new Rect();
                view.setEnabled(true);
                view.getHitRect(rect);
                rect.top -= i;
                rect.bottom += i2;
                rect.left -= i3;
                rect.right += i4;
                TouchDelegate touchDelegate = new TouchDelegate(rect, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }

    public static void expandViewTouchDelegateCom(final View view, final int i) {
        if (view == null || i <= 0) {
            return;
        }
        ((View) view.getParent()).post(new Runnable() { // from class: com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate.2
            @Override // java.lang.Runnable
            public void run() {
                Rect rect = new Rect();
                view.setEnabled(true);
                view.getHitRect(rect);
                rect.top -= i;
                rect.bottom += i;
                rect.left -= i;
                rect.right += i;
                TouchDelegate touchDelegate = new TouchDelegate(rect, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }

    public static void restoreViewTouchDelegate(final View view) {
        ((View) view.getParent()).post(new Runnable() { // from class: com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate.3
            @Override // java.lang.Runnable
            public void run() {
                Rect rect = new Rect();
                rect.setEmpty();
                TouchDelegate touchDelegate = new TouchDelegate(rect, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }
}
