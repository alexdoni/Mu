package com.oversea.ab_firstarea.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_SimplePwdTipDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    Bundle bundle;
    private TextView content_tv;
    private Context mContext;
    private Button ok_btn;
    private String popup_tips = "";

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_simple_pwd_tip";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        Bundle arguments = getArguments();
        this.bundle = arguments;
        if (arguments != null && arguments.containsKey("popup_tips")) {
            this.popup_tips = this.bundle.getString("popup_tips");
        }
        if (TextUtils.isEmpty(this.popup_tips)) {
            dismiss();
            return;
        }
        TextView textView = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "content_tv"));
        this.content_tv = textView;
        textView.setText(this.popup_tips);
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "ok_btn"));
        this.ok_btn = button;
        button.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.ok_btn) {
            dismiss();
        }
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (displayMetrics.widthPixels >= displayMetrics.heightPixels) {
            int i = (int) (displayMetrics.heightPixels * 0.95d);
            int i2 = displayMetrics.heightPixels;
            getDialog().getWindow().setLayout(i, -2);
        } else {
            int i3 = (int) (displayMetrics.widthPixels * 0.95d);
            int i4 = displayMetrics.widthPixels;
            getDialog().getWindow().setLayout(i3, -2);
        }
    }
}
