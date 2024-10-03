package com.oversea.ab_firstarea.dialog.kefu;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_KFChooseDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    private ImageView img_kefu;
    private ImageView img_mail;
    private TextView kfshij;
    private TextView text_tip;
    private RelativeLayout tw_email_layout;
    private RelativeLayout tw_kefu_layout;
    ImageView tw_sdk_back_iv;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xkfchoose_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.img_mail = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "img_mail"));
        this.img_kefu = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "img_kefu"));
        this.kfshij = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "kfshij"));
        this.text_tip = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "text_tip"));
        this.tw_email_layout = (RelativeLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_email_layout"));
        this.tw_kefu_layout = (RelativeLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_kefu_layout"));
        if (InitBean.getInstance().getCustomer_service_config().getEmail_customer_service_switch() == 0) {
            this.tw_email_layout.setVisibility(8);
        }
        if (InitBean.getInstance().getCustomer_service_config().getOnline_customer_service_switch() == 0) {
            this.tw_kefu_layout.setVisibility(8);
        }
        if (this.kfshij != null && !TextUtils.isEmpty(ManageBean.getInstance().getKfBaseInfoBean().getData().getWork_time_text())) {
            this.kfshij.setText(ManageBean.getInstance().getKfBaseInfoBean().getData().getWork_time_text());
        }
        this.text_tip.setOnClickListener(this);
        this.img_mail.setOnClickListener(this);
        this.img_kefu.setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_sdk_back_iv, 15);
        if (TextUtils.isEmpty(ManageBean.getInstance().getKfBaseInfoBean().getData().getEmail())) {
            Lxhw_AreaPlatform.getInstance().getManageSomeRequestModel().kfBaseInfo();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_sdk_back_iv) {
            dismiss();
            return;
        }
        if (view == this.img_mail) {
            String email = ManageBean.getInstance().getKfBaseInfoBean().getData().getEmail();
            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            Lxhw_DialogManage.getInstance().enterKFEmailDialog(this.mContext, bundle);
            return;
        }
        if (view == this.img_kefu) {
            Lxhw_DialogManage.getInstance().enterKFOnlineCenter(this.mContext);
        }
    }
}
