package com.oversea.ab_firstplatform.custom;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.internal.AnalyticsEvents;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes2.dex */
public class CustomDialog extends BaseCustomDialog {
    private LinearLayout ll_dialogcustom;
    private Context mContext;
    private float mHeight;
    private RelativeLayout.LayoutParams mLayoutParams;
    private float mWith;
    public FragmentTransaction transaction;

    /* renamed from: v */
    private View f787v;
    private Window window;

    public CustomDialog(Context context, float f) {
        super(context, Util.getIdByName(context, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "tw_CustomDialog"));
        this.mHeight = 0.0f;
        this.mContext = context;
        this.mLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        this.mContext = context;
        this.mWith = f;
    }

    public CustomDialog(Context context, float f, float f2) {
        super(context, Util.getIdByName(context, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "tw_CustomDialog"));
        this.mHeight = 0.0f;
        this.mContext = context;
        this.mLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        this.mContext = context;
        this.mWith = f;
        this.mHeight = f2;
    }

    public CustomDialog(Context context, int i, float f) {
        super(context, i);
        this.mHeight = 0.0f;
        this.mLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        this.mContext = context;
        this.mWith = f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstplatform.custom.BaseCustomDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.transaction = ((Activity) this.mContext).getFragmentManager().beginTransaction();
        setContentView(Util.getIdByName(this.mContext, "layout", "drhw_xdialog_custom"));
        windowDeploy();
        initView();
        setCanceledOnTouchOutside(false);
    }

    public void windowDeploy() {
        Window window = getWindow();
        this.window = window;
        window.setWindowAnimations(Util.getIdByName(this.mContext, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "tw_centre_dialog_ani"));
        WindowManager.LayoutParams attributes = this.window.getAttributes();
        WindowManager windowManager = this.window.getWindowManager();
        windowManager.getDefaultDisplay().getMetrics(new DisplayMetrics());
        attributes.gravity = 17;
        int i = this.mContext.getResources().getConfiguration().orientation;
        WindowManager windowManager2 = ((Activity) this.mContext).getWindowManager();
        int width = windowManager2.getDefaultDisplay().getWidth();
        int height = windowManager2.getDefaultDisplay().getHeight();
        if (width > height) {
            this.window.setLayout(height, -2);
        } else {
            this.window.setLayout(-1, -2);
        }
    }

    public FragmentTransaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(FragmentTransaction fragmentTransaction) {
        this.transaction = fragmentTransaction;
    }

    private void initView() {
        this.ll_dialogcustom = (LinearLayout) findViewById(Util.getIdByName(this.mContext, "id", "ll_dialogcustom"));
    }

    public LinearLayout getLl_dialogcustom() {
        return this.ll_dialogcustom;
    }

    public void setLl_dialogcustom(LinearLayout linearLayout) {
        this.ll_dialogcustom = linearLayout;
    }

    public void showDialog() {
        show();
    }

    public View getV() {
        return this.f787v;
    }

    public void setV(View view) {
        this.f787v = view;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 82 && keyEvent.getAction() == 0) {
            dismiss();
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void addView(Context context, int i) {
        View inflate = LayoutInflater.from(context).inflate(i, (ViewGroup) null);
        this.f787v = inflate;
        this.ll_dialogcustom.addView(inflate);
    }

    public void addView(int i) {
        View inflate = LayoutInflater.from(this.mContext).inflate(i, (ViewGroup) null);
        this.f787v = inflate;
        this.ll_dialogcustom.addView(inflate);
    }
}
