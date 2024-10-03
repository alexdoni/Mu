package com.oversea.ab_firstarea.dialog;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oversea.ab_firstarea.dpresenter.PresenterRegister;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_RegisterPresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_RegisterView;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.loading.LoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_RegisterDialog extends Lxhw_BaseDialogFragment implements Lxhw_RegisterView, View.OnClickListener {
    private String TAG = "RegisterDialog";
    private TextView hideformterm_tv;
    private CheckBox isAngreement;
    private EditText mRegisterAccount;
    private Button mRegisterButton;
    private EditText mRegisterPassword;
    private EditText mRegisterPassword2;
    private TextView platformterm_tv;
    private PresenterRegister presenter;
    private LinearLayout tw_linear_checkbox;
    ImageView tw_sdk_back_iv;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xregister_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.presenter = new Lxhw_RegisterPresenterImpl(getActivity(), this);
        this.mRegisterAccount = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_register_account"));
        this.mRegisterPassword = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_register_password"));
        this.mRegisterPassword2 = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_register_password2"));
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_register_login_button"));
        this.mRegisterButton = button;
        button.setOnClickListener(this);
        this.isAngreement = (CheckBox) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_register_is_agree"));
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(this);
        this.hideformterm_tv = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "hideformterm_tv"));
        this.platformterm_tv = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "platformterm_tv"));
        this.hideformterm_tv.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_RegisterDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (InitBean.getInstance().getTerm_info() == null) {
                    Lxhw_RegisterDialog.this.presenter.jumpPrivacyAgreement();
                } else if (TextUtils.isEmpty(InitBean.getInstance().getTerm_info().getTerm_privacy_url())) {
                    Lxhw_RegisterDialog.this.presenter.jumpPrivacyAgreement();
                } else {
                    Lxhw_DialogManage.getInstance().showWebCommon(Lxhw_RegisterDialog.this.mContext, InitBean.getInstance().getTerm_info().getTerm_privacy_url(), "");
                }
            }
        });
        this.platformterm_tv.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_RegisterDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (InitBean.getInstance().getTerm_info() == null) {
                    Lxhw_RegisterDialog.this.presenter.jumpServerAgreement();
                } else if (TextUtils.isEmpty(InitBean.getInstance().getTerm_info().getTerm_service_url())) {
                    Lxhw_RegisterDialog.this.presenter.jumpServerAgreement();
                } else {
                    Lxhw_DialogManage.getInstance().showWebCommon(Lxhw_RegisterDialog.this.mContext, InitBean.getInstance().getTerm_info().getTerm_service_url(), "");
                }
            }
        });
        if (Lxhw_AreaPlatform.getInstance().isUseNewSdkLogin) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_linear_checkbox"));
            this.tw_linear_checkbox = linearLayout;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mRegisterButton == view) {
            String trim = this.mRegisterAccount.getText().toString().trim();
            String trim2 = this.mRegisterPassword.getText().toString().trim();
            String trim3 = this.mRegisterPassword2.getText().toString().trim();
            try {
                Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(this.mContext, ComConfig.PC_FF_REGISTER_CLICK, "custom_sdk_register_click:" + trim + "," + trim2 + "," + trim3 + "," + this.isAngreement.isChecked());
            } catch (Exception unused) {
            }
            if (TextUtils.isEmpty(trim)) {
                ToastUtils.toastShow(this.mContext, Util.getString(this.mContext, "tw_register_placehoder"));
                return;
            }
            if (trim.length() < 2) {
                ToastUtils.toastShow(this.mContext, Util.getString(this.mContext, "tw_account_len1gth_tip"));
                return;
            }
            if (TextUtils.isEmpty(trim2)) {
                ToastUtils.toastShow(this.mContext, Util.getString(this.mContext, "tw_register_password"));
                return;
            }
            if (TextUtils.isEmpty(trim3)) {
                ToastUtils.toastShow(this.mContext, Util.getString(this.mContext, "tw_register_password"));
                return;
            }
            if (!trim2.equals(trim3)) {
                ToastUtils.toastShow(this.mContext, Util.getString(this.mContext, "tw_register_pwd_dif"));
                return;
            }
            if (trim2.length() < 6 || trim3.length() < 6) {
                ToastUtils.toastShow(this.mContext, Util.getString(this.mContext, "tw_pwd_length_tip"));
                return;
            } else if (!this.isAngreement.isChecked()) {
                ToastUtils.toastShow(this.mContext, Util.getString(this.mContext, "tw_aggre_auth"));
                return;
            } else {
                LoadingDialog.showDialogForLoading(this.mContext);
                this.presenter.doRegister(trim, trim2);
                return;
            }
        }
        if (view == this.tw_sdk_back_iv) {
            dismiss();
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, Object obj) {
        LoadingDialog.cancelDialogForLoading();
        Lxhw_DialogManage.getInstance().removeFragment(this.mContext, "LoginDialog");
        if (Lxhw_AreaPlatform.getInstance().isUseNewSdkLogin) {
            Lxhw_DialogManage.getInstance().removeFragment(Lxhw_XSDK.getInstance().getContext(), "loginSelectDialog");
        }
        dismiss();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        LoadingDialog.cancelDialogForLoading();
        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
        try {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(this.mContext, ComConfig.CUSTOM_SDK_REGISTER_ERROR, "custom_sdk_register_error:" + baseBean.getCode() + ",msg:" + ComUtil.getBaseBeanTip(baseBean));
        } catch (Exception unused) {
        }
        if ("autoLogin".equals(str) && baseBean.getCode() == 1013) {
            Lxhw_DialogManage.getInstance().removeAllFragment(Lxhw_XSDK.getInstance().getContext());
            new Handler().postDelayed(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_RegisterDialog.3
                @Override // java.lang.Runnable
                public void run() {
                    Lxhw_DialogManage.getInstance().showLogin(Lxhw_XSDK.getInstance().getContext());
                }
            }, 200L);
        }
    }
}
