package com.oversea.ab_firstarea.dialog;

import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.oversea.ab_firstarea.channel.PTypeUrl;
import com.oversea.ab_firstarea.dpresenter.PresenterRollbackAccount;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_RollbackAccountPresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_RollbackAccountView;
import com.oversea.ab_firstarea.haiwai.FirebaseControl;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_RollbackAccountDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener, Lxhw_RollbackAccountView {
    private Button btnCancel;
    private Button btnConfirm;
    private PresenterRollbackAccount presenter;
    private String tip = "";
    private TextView tvContent;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xrollback_account_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        if (getArguments() != null) {
            this.tip = getArguments().getString("tip");
        }
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.CUSTOM_SDK_ROLLBACK_SHOW);
        TextView textView = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tv_del_tip"));
        this.tvContent = textView;
        try {
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        } catch (Exception unused) {
        }
        this.tvContent.setText(this.tip.replace("\\n", "\n"));
        this.presenter = new Lxhw_RollbackAccountPresenterImpl(this.mContext, this);
        this.btnCancel = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "btn_cancel"));
        this.btnConfirm = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "btn_confirm"));
        this.btnCancel.setOnClickListener(this);
        this.btnConfirm.setOnClickListener(this);
        setCancelable(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.btnCancel) {
            Lxhw_AreaPlatform.getInstance().callbackSwitchAccount();
        } else if (view == this.btnConfirm) {
            this.presenter.rollback();
            Lxhw_DialogManage.getInstance().showDialog();
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, Object obj) {
        Lxhw_DialogManage.getInstance().cancelDialog();
        Lxhw_AreaPlatform.getInstance().getManageSomeRequestModel().fireBaseUpdateToken(FirebaseControl.getInstance().getMsgToken());
        Lxhw_AreaPlatform.getInstance().getManageSomeRequestModel().kfBaseInfo();
        TextUtils.isEmpty(PTypeUrl.TYPENAME);
        dismissAllowingStateLoss();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_DialogManage.getInstance().cancelDialog();
        ToastUtils.toastShow(this.mContext, ComUtil.getBaseBeanTip(baseBean));
    }
}
