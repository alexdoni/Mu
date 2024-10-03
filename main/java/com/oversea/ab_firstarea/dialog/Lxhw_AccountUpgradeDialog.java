package com.oversea.ab_firstarea.dialog;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.oversea.ab_firstarea.dpresenter.PresenterAccountUpgrade;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_AccountUpgradePresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_AccountUpgradeView;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.net.model.PlatformLoginAP;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.model.LoginInfo;
import com.oversea.ab_firstplatform.model.LoginInfoManage;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.IsFastClickUtils;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_AccountUpgradeDialog extends Lxhw_BaseDialogFragment implements Lxhw_AccountUpgradeView, View.OnClickListener {
    private TextView hideformterm_tv;
    private CheckBox isAngreement;
    private EditText mRegisterAccount;
    private Button mRegisterButton;
    private EditText mRegisterPassword;
    private EditText mRegisterPassword2;
    private TextView platformterm_tv;
    private PresenterAccountUpgrade presenter;
    ImageView tw_sdk_back_iv;
    private String TAG = "Lhwl_AccountUpgradeDialog";
    private CharSequence mAccountText = "";
    protected String mAccount = "";
    protected String mPassword = "";

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xaccount_upgrade_dialog";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.presenter = new Lxhw_AccountUpgradePresenterImpl(this.mContext, this);
        this.mRegisterAccount = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_register_account"));
        this.mRegisterPassword = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_register_password"));
        this.mRegisterPassword2 = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_register_password2"));
        this.mRegisterAccount.setLayerType(2, null);
        this.mRegisterPassword.setLayerType(2, null);
        this.mRegisterPassword2.setLayerType(2, null);
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_confirm_btn"));
        this.mRegisterButton = button;
        button.setOnClickListener(this);
        this.mRegisterAccount.setText(this.mAccountText);
        CheckBox checkBox = (CheckBox) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_register_is_agree"));
        this.isAngreement = checkBox;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_AccountUpgradeDialog.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            }
        });
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        DealTouchDelegate.expandViewTouchDelegateCom(imageView, 15);
        this.tw_sdk_back_iv.setOnClickListener(this);
        this.hideformterm_tv = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "hideformterm_tv"));
        this.platformterm_tv = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "platformterm_tv"));
        this.hideformterm_tv.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_AccountUpgradeDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (InitBean.getInstance().getTerm_info() != null) {
                    if (TextUtils.isEmpty(InitBean.getInstance().getTerm_info().getTerm_privacy_url())) {
                        Lxhw_DialogManage.getInstance().showBigTextCommon(Lxhw_AccountUpgradeDialog.this.mContext, "2");
                        return;
                    } else {
                        Lxhw_DialogManage.getInstance().showWebCommon(Lxhw_AccountUpgradeDialog.this.mContext, InitBean.getInstance().getTerm_info().getTerm_privacy_url(), "");
                        return;
                    }
                }
                Lxhw_DialogManage.getInstance().showBigTextCommon(Lxhw_AccountUpgradeDialog.this.mContext, "2");
            }
        });
        this.platformterm_tv.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_AccountUpgradeDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (InitBean.getInstance().getTerm_info() != null) {
                    if (TextUtils.isEmpty(InitBean.getInstance().getTerm_info().getTerm_service_url())) {
                        Lxhw_DialogManage.getInstance().showBigTextCommon(Lxhw_AccountUpgradeDialog.this.mContext, "1");
                        return;
                    } else {
                        Lxhw_DialogManage.getInstance().showWebCommon(Lxhw_AccountUpgradeDialog.this.mContext, InitBean.getInstance().getTerm_info().getTerm_service_url(), "");
                        return;
                    }
                }
                Lxhw_DialogManage.getInstance().showBigTextCommon(Lxhw_AccountUpgradeDialog.this.mContext, "1");
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mRegisterButton == view) {
            if (IsFastClickUtils.isFastClick(Lxhw_Platform.getInstance().fastClickTime)) {
                Log.e(this.TAG, "多次点击，返回...................");
                return;
            }
            if (!this.isAngreement.isChecked()) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_aggre_auth"));
                return;
            }
            if (TextUtils.isEmpty(this.mRegisterAccount.getText())) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_register_placehoder"));
                return;
            }
            if (TextUtils.isEmpty(this.mRegisterPassword.getText())) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_register_password"));
                return;
            }
            if (TextUtils.isEmpty(this.mRegisterPassword2.getText())) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_register_password"));
                return;
            }
            if (this.mRegisterAccount.getText().toString().trim().length() < 2) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_account_length_tip"));
                return;
            }
            if (this.mRegisterPassword.getText().toString().trim().length() < 6) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_pwd_length_tip"));
                return;
            }
            if (this.mRegisterPassword2.getText().toString().trim().length() < 6) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_pwd_length_tip"));
                return;
            }
            if (!this.mRegisterPassword2.getText().toString().trim().equals(this.mRegisterPassword.getText().toString().trim())) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_register_pwd_dif"));
                return;
            } else {
                if (this.mRegisterPassword.getText().toString().trim().length() < 6 || this.mRegisterPassword2.getText().toString().trim().length() < 6) {
                    ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_pwd_length_tip"));
                    return;
                }
                this.mAccount = this.mRegisterAccount.getText().toString().trim();
                String trim = this.mRegisterPassword.getText().toString().trim();
                this.mPassword = trim;
                this.presenter.doUpgradeAccount(this.mAccount, trim);
                Lxhw_DialogManage.getInstance().showDialog();
                return;
            }
        }
        if (view == this.tw_sdk_back_iv) {
            dismiss();
        }
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, BaseBean baseBean) {
        ToastUtils.toastShow(Lxhw_XSDK.getInstance().getContext(), baseBean.getMessage());
        ImageUtils.setSharePreferences(this.mContext, Constants.SDK_ACCOUNT, this.mAccount);
        ImageUtils.setSharePreferences(this.mContext, Constants.SDK_PASSWORD, this.mPassword);
        PlatformLoginAP.getInstance().setAccountAndPw(this.mAccount, this.mPassword);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setP(this.mPassword);
        loginInfo.setU(this.mAccount);
        LoginInfoManage.getInstance().updataLoginInfosAccountAndPwd(this.mContext, ManageBean.getInstance().getAccount(), loginInfo);
        Lxhw_AreaPlatform.getInstance().callbackSwitchAccount();
        dismissAllowingStateLoss();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        ToastUtils.toastShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
    }
}
