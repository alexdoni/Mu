package com.oversea.ab_firstarea.dialog;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.internal.security.CertificateUtil;
import com.xsdk.ab_firstbase.statisics.util.DensityUtil;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_TipDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    private ImageView iv_close;
    private String tip = "";
    private TextView tvTip;
    private Button tw_app_update_btn;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xtip_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            if (getDialog() != null && getDialog().getWindow() != null) {
                int dip2px = DensityUtil.dip2px(this.mContext, 280.0f);
                if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                    getDialog().getWindow().setLayout((int) (displayMetrics.heightPixels * 0.85d), dip2px);
                } else {
                    getDialog().getWindow().setLayout((int) (displayMetrics.widthPixels * 0.85d), dip2px);
                }
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.tip = arguments.getString("tip");
        }
        this.tw_app_update_btn = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_app_update_btn"));
        this.tvTip = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tv_tip"));
        this.tw_app_update_btn.setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "iv_close"));
        this.iv_close = imageView;
        imageView.setOnClickListener(this);
        this.tvTip.setText(this.tip);
    }

    private String getTraceId(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return str.split(CertificateUtil.DELIMITER)[1];
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_app_update_btn) {
            try {
                ((ClipboardManager) this.mContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("kkk", getTraceId(this.tip)));
                ToastUtils.toastShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_copy_success")));
            } catch (Exception unused) {
            }
            dismissAllowingStateLoss();
            return;
        }
        if (view == this.iv_close) {
            dismissAllowingStateLoss();
        }
    }
}
