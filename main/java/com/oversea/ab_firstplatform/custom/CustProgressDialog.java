package com.oversea.ab_firstplatform.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes2.dex */
public class CustProgressDialog extends Dialog {
    private static final String TAG = "com.oversea.ab_firstplatform.custom.CustProgressDialog";
    private String message;
    public View view;

    public CustProgressDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public CustProgressDialog(Context context, int i) {
        super(context, i);
        Window window = getWindow();
        window.requestFeature(1);
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setFlags(0, 2);
        attributes.alpha = 0.95f;
    }

    public CustProgressDialog(Context context) {
        super(context);
        Window window = getWindow();
        window.requestFeature(1);
        window.getAttributes().alpha = 0.95f;
        window.setFlags(0, 2);
    }

    public CustProgressDialog(Context context, int i, String str) {
        super(context, i);
        Window window = getWindow();
        window.requestFeature(1);
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setFlags(0, 2);
        attributes.alpha = 0.95f;
        this.message = str;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ProgressDialogView progressDialogView = new ProgressDialogView(getContext(), Util.getIdByName(getContext(), "drawable", "tw_progressdialog_bg"), Util.getIdByName(getContext(), "drawable", "drhw_progressdialog_icon"), this.message);
        this.view = progressDialogView;
        setContentView(progressDialogView);
        setFullScreen();
        setCanceledOnTouchOutside(false);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        if (z) {
            return;
        }
        if (isShowing()) {
            dismiss();
        }
        super.onWindowFocusChanged(z);
    }

    private void setFullScreen() {
        getWindow().getDecorView().setSystemUiVisibility(2);
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.oversea.ab_firstplatform.custom.CustProgressDialog.1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                CustProgressDialog.this.getWindow().getDecorView().setSystemUiVisibility(5894);
            }
        });
    }
}
