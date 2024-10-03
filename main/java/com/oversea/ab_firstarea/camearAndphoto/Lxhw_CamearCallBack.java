package com.oversea.ab_firstarea.camearAndphoto;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;

/* loaded from: classes.dex */
public class Lxhw_CamearCallBack {
    private static Lxhw_CamearCallBack mInstance;
    private Activity mActivity;
    public CamearCallBackListener mCallBack;

    /* loaded from: classes.dex */
    public interface CamearCallBackListener {
        void onCCBResult(Uri uri, Bitmap bitmap);
    }

    private Lxhw_CamearCallBack() {
    }

    public static Lxhw_CamearCallBack getInstance() {
        if (mInstance == null) {
            mInstance = new Lxhw_CamearCallBack();
        }
        return mInstance;
    }

    public void setCallback(Activity activity, CamearCallBackListener camearCallBackListener) {
        this.mActivity = activity;
        if (camearCallBackListener != null) {
            this.mCallBack = camearCallBackListener;
        }
    }
}
