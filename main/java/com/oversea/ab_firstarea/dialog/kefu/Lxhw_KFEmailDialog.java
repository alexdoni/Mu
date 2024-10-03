package com.oversea.ab_firstarea.dialog.kefu;

import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_KFEmailDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    Bundle bundle;
    private String email = "";
    private Button email_copy;
    private TextView tv_kf_email;
    private LinearLayout tw_email_layout;
    ImageView tw_sdk_back_iv;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xkfemail_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        Bundle arguments = getArguments();
        this.bundle = arguments;
        if (arguments != null) {
            this.email = arguments.getString("email");
        }
        this.tw_email_layout = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_email_layout"));
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "email_copy"));
        this.email_copy = button;
        button.setOnClickListener(this);
        TextView textView = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tv_kf_email"));
        this.tv_kf_email = textView;
        textView.setText(this.email);
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_sdk_back_iv, 15);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_sdk_back_iv) {
            dismiss();
            return;
        }
        if (view == this.email_copy) {
            try {
                ((ClipboardManager) this.mContext.getSystemService("clipboard")).setText(this.email);
                ToastUtils.toastLongShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_copy_success")));
            } catch (Throwable th) {
                Log.e("CLIPBOARD_SERVICE", th.toString());
            }
        }
    }
}
