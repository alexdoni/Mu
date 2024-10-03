package com.xsdk.ab_firstbase.loading;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.internal.AnalyticsEvents;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes3.dex */
public class LoadingDialog {
    private static WeakDialog mLoadingDialog;
    private WeakDialog mCustomLoadingDialog;

    public static Dialog showDialogForLoading(Activity activity, String str, boolean z) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        View inflate = LayoutInflater.from(activity).inflate(Util.getIdByName(activity, "layout", "idialog_loading"), (ViewGroup) null);
        ((TextView) inflate.findViewById(Util.getIdByName(activity, "id", "tv_i_loading_dialog_hint"))).setText(str);
        WeakDialog weakDialog = mLoadingDialog;
        if (weakDialog != null && weakDialog.isShowing()) {
            cancelDialogForLoading();
        }
        WeakDialog weakDialog2 = new WeakDialog(activity, Util.getIdByName(activity, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "tw_CustomDialog"));
        mLoadingDialog = weakDialog2;
        weakDialog2.setCancelable(z);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(inflate, new LinearLayout.LayoutParams(-1, -1));
        Window window = mLoadingDialog.getWindow();
        if (window != null) {
            window.setWindowAnimations(Util.getIdByName(activity, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "tw_sec_dialog_animations"));
        }
        mLoadingDialog.show();
        return mLoadingDialog;
    }

    public static Dialog showDialogForLoading(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        View inflate = LayoutInflater.from(activity).inflate(Util.getIdByName(activity, "layout", "idialog_loading"), (ViewGroup) null);
        ((TextView) inflate.findViewById(Util.getIdByName(activity, "id", "tv_i_loading_dialog_hint"))).setVisibility(8);
        WeakDialog weakDialog = new WeakDialog(activity, Util.getIdByName(activity, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "tw_CustomDialog"));
        mLoadingDialog = weakDialog;
        weakDialog.setCancelable(true);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(inflate, new LinearLayout.LayoutParams(-1, -1));
        mLoadingDialog.show();
        return mLoadingDialog;
    }

    public static void cancelDialogForLoading() {
        try {
            WeakDialog weakDialog = mLoadingDialog;
            if (weakDialog == null || !weakDialog.isShowing()) {
                return;
            }
            mLoadingDialog.cancel();
            mLoadingDialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Dialog showCustomDialogForLoading(Activity activity) {
        initCustomDiaLoading(activity, "加载中...");
        return mLoadingDialog;
    }

    public Dialog showCustomDialogForLoading(Activity activity, String str) {
        initCustomDiaLoading(activity, str);
        return mLoadingDialog;
    }

    private void initCustomDiaLoading(Activity activity, String str) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        View inflate = LayoutInflater.from(activity).inflate(Util.getIdByName(activity, "layout", "tanwan_dialog_loading"), (ViewGroup) null);
        ((TextView) inflate.findViewById(Util.getIdByName(activity, "id", "tv_loadview_msg"))).setText(str);
        WeakDialog weakDialog = new WeakDialog(activity, Util.getIdByName(activity, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "tw_CustomDialog"));
        this.mCustomLoadingDialog = weakDialog;
        weakDialog.setCancelable(true);
        this.mCustomLoadingDialog.setCanceledOnTouchOutside(false);
        this.mCustomLoadingDialog.setContentView(inflate, new LinearLayout.LayoutParams(-1, -1));
        this.mCustomLoadingDialog.show();
    }

    public void cancelCustomDialogForLoading() {
        WeakDialog weakDialog = this.mCustomLoadingDialog;
        if (weakDialog != null) {
            weakDialog.cancel();
        }
    }
}
