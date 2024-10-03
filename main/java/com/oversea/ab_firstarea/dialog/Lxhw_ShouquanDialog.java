package com.oversea.ab_firstarea.dialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.share.internal.ShareConstants;
import com.oversea.ab_firstarea.camearAndphoto.Lxhw_CamearPhotoControl;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_ShouquanDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    private static Lxhw_ShouquanDialog defaultInstance;
    private String TAG = "ShouquanDialog";
    private RelativeLayout tw_btn_rl;
    private Button tw_ok;
    private Button tw_setting;
    private TextView tw_shouquan_content;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xshouquan_tip";
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (displayMetrics.widthPixels >= displayMetrics.heightPixels) {
            getDialog().getWindow().setLayout((int) (displayMetrics.heightPixels * 0.95d), (int) (displayMetrics.heightPixels * 0.7d));
        } else {
            getDialog().getWindow().setLayout((int) (displayMetrics.widthPixels * 0.95d), (int) (displayMetrics.widthPixels * 0.7d));
        }
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString(ShareConstants.MEDIA_TYPE) : "1";
        defaultInstance = this;
        this.tw_ok = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_ok"));
        this.tw_setting = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_setting"));
        this.tw_btn_rl = (RelativeLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_btn_rl"));
        this.tw_ok.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_ShouquanDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Lxhw_CamearPhotoControl.getInstance().requestPermissionsReadAndWriteExt();
                Lxhw_ShouquanDialog.this.dismiss();
            }
        });
        this.tw_setting.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_ShouquanDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", Lxhw_ShouquanDialog.this.mContext.getPackageName(), null));
                Lxhw_ShouquanDialog.this.mContext.startActivity(intent);
                Lxhw_ShouquanDialog.this.dismiss();
            }
        });
        this.tw_shouquan_content = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_shouquan_content"));
        if ("1".equals(string)) {
            this.tw_shouquan_content.setText(getActivity().getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_shouquan_storage")));
            this.tw_setting.setVisibility(8);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.width = (int) ((this.mContext.getResources().getDisplayMetrics().density * 105.0f) + 0.5f);
            layoutParams.addRule(13);
            this.tw_ok.setLayoutParams(layoutParams);
            return;
        }
        this.tw_shouquan_content.setText(getActivity().getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_permission_content2")));
    }
}
