package com.oversea.ab_firstarea.permission;

import android.app.Activity;

/* loaded from: classes.dex */
public class PermissionCallBack {
    private static PermissionCallBack mInstance;
    private Activity mActivity;
    public PermissionCallBackListener mCallBack;

    /* loaded from: classes.dex */
    public interface PermissionCallBackListener {
        void onPCBResult(boolean z);
    }

    private PermissionCallBack() {
    }

    public static PermissionCallBack getInstance() {
        if (mInstance == null) {
            mInstance = new PermissionCallBack();
        }
        return mInstance;
    }

    public void setOversCallback(Activity activity, PermissionCallBackListener permissionCallBackListener) {
        this.mActivity = activity;
        if (permissionCallBackListener != null) {
            this.mCallBack = permissionCallBackListener;
        }
    }

    public void callBackOnPCBResult(Boolean bool) {
        PermissionCallBackListener permissionCallBackListener = this.mCallBack;
        if (permissionCallBackListener != null) {
            permissionCallBackListener.onPCBResult(bool.booleanValue());
        }
    }
}
