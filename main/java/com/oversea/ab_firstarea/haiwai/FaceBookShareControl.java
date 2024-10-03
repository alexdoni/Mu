package com.oversea.ab_firstarea.haiwai;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class FaceBookShareControl {
    private static FaceBookShareControl mInstance;
    public CallbackManager callbackManager;
    private Activity mActivity;
    ShareDialog shareDialog;

    public void sharePhoto(String str, String str2) {
    }

    public void sharePhoto2(String str, String str2) {
    }

    public void sharePhoto3() {
    }

    private FaceBookShareControl() {
    }

    public static FaceBookShareControl getInstance() {
        if (mInstance == null) {
            synchronized (FaceBookShareControl.class) {
                if (mInstance == null) {
                    mInstance = new FaceBookShareControl();
                }
            }
        }
        return mInstance;
    }

    public void initFacebookShareControl(Activity activity) {
        if (activity != null) {
            this.mActivity = activity;
            if (this.shareDialog == null) {
                this.shareDialog = new ShareDialog(this.mActivity);
            }
        }
    }

    private void setCallbackManager() {
        this.callbackManager = CallbackManager.Factory.create();
    }

    public void sharelink(final Activity activity, String str, String str2, String str3) {
        initFacebookShareControl(activity);
        if (this.shareDialog == null || TextUtils.isEmpty(str)) {
            Log.e("share", "sharelink dialog or url null");
            return;
        }
        ComConstants.CTRL_TYPE = 31;
        setCallbackManager();
        ShareLinkContent build = new ShareLinkContent.Builder().setContentUrl(Uri.parse(str)).setQuote(str3).build();
        this.shareDialog.registerCallback(this.callbackManager, new FacebookCallback<Sharer.Result>() { // from class: com.oversea.ab_firstarea.haiwai.FaceBookShareControl.1
            @Override // com.facebook.FacebookCallback
            public void onSuccess(Sharer.Result result) {
                Activity activity2 = activity;
                ToastUtils.toastLongShow(activity, activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "tw_share_suc")));
                AreaSdk.getInstance().onShareResult(true);
            }

            @Override // com.facebook.FacebookCallback
            public void onCancel() {
                Activity activity2 = activity;
                ToastUtils.toastLongShow(activity, activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "tw_share_cancel")));
                AreaSdk.getInstance().onShareResult(false);
                Log.v("share", "share onCancel");
            }

            @Override // com.facebook.FacebookCallback
            public void onError(FacebookException facebookException) {
                Activity activity2 = activity;
                ToastUtils.toastLongShow(activity, activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "tw_share_fail")));
                Log.v("share", "share error=" + facebookException.getMessage());
                AreaSdk.getInstance().onShareResult(false);
            }
        });
        this.shareDialog.show(build, ShareDialog.Mode.AUTOMATIC);
    }
}
