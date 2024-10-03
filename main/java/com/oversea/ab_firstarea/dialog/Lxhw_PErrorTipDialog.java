package com.oversea.ab_firstarea.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_PErrorTipDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    Bundle bundle;
    private TextView content_tv;
    private ImageView iv_close;
    private Context mContext;
    private Button ok_btn;
    private String popup_tips = "";
    private String popup_url = "";

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_perror_tip";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (displayMetrics.widthPixels >= displayMetrics.heightPixels) {
            getDialog().getWindow().setLayout((int) (displayMetrics.heightPixels * 0.8d), (int) (displayMetrics.heightPixels * 0.6d));
        } else {
            getDialog().getWindow().setLayout((int) (displayMetrics.widthPixels * 0.8d), (int) (displayMetrics.widthPixels * 0.6d));
        }
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        Bundle arguments = getArguments();
        this.bundle = arguments;
        if (arguments != null) {
            if (arguments.containsKey("popup_tips")) {
                this.popup_tips = this.bundle.getString("popup_tips");
            }
            if (this.bundle.containsKey("popup_url")) {
                this.popup_url = this.bundle.getString("popup_url");
            }
        }
        if (TextUtils.isEmpty(this.popup_tips) || TextUtils.isEmpty(this.popup_url)) {
            dismiss();
            return;
        }
        TextView textView = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "content_tv"));
        this.content_tv = textView;
        textView.setText(this.popup_tips);
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "ok_btn"));
        this.ok_btn = button;
        button.setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "iv_close"));
        this.iv_close = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.iv_close, 15);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.ok_btn) {
            if (!TextUtils.isEmpty(this.popup_url)) {
                Lxhw_DialogManage.getInstance().checkUpdata(getActivity(), this.popup_url);
            }
            dismiss();
        } else if (view == this.iv_close) {
            dismiss();
        }
    }
}
