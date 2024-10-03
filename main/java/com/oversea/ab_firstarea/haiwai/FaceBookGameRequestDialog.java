package com.oversea.ab_firstarea.haiwai;

import android.app.Activity;
import com.facebook.CallbackManager;
import com.facebook.share.widget.GameRequestDialog;

/* loaded from: classes.dex */
public class FaceBookGameRequestDialog {
    private static FaceBookGameRequestDialog mInstance;
    private String TAG = "Lhwl_FaceBookGameRequestDialog";
    public CallbackManager callbackManager = null;
    private Activity mActivity;
    GameRequestDialog requestDialog;

    public void initFacebookGR() {
    }

    public void onClickRequestButton() {
    }

    private FaceBookGameRequestDialog() {
        initFacebookGR();
    }

    public static FaceBookGameRequestDialog getInstance() {
        if (mInstance == null) {
            synchronized (FaceBookGameRequestDialog.class) {
                if (mInstance == null) {
                    mInstance = new FaceBookGameRequestDialog();
                }
            }
        }
        return mInstance;
    }
}
