package com.oversea.ab_firstarea.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.oversea.ab_firstarea.dpresenter.PresenterResetPassword;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_ResetPasswordPresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_ResetPasswordView;
import com.oversea.ab_firstarea.net.model.PlatformLoginAP;
import com.oversea.ab_firstarea.net.model.UserBindInfoBean;
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
public class Lxhw_ResetPasswordDialog extends Lxhw_BaseDialogFragment implements Lxhw_ResetPasswordView, View.OnClickListener {
    private static Lxhw_ChangePsdDiaDialog defaultInstance;
    private Bundle bundle;
    private Activity mContext;
    private PresenterResetPassword presenter;
    private Button tw_change_psd_btn;
    private EditText tw_input_newpsd_et;
    private EditText tw_input_newpsd_et2;
    private ImageView tw_sdk_back_iv;
    private TextView uname_tv;
    private String TAG = "Lhwl_ResetPasswordDialog";
    private boolean isChanging = false;
    private String account = "";
    private String password = "";

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "tw_set_password";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.presenter = new Lxhw_ResetPasswordPresenterImpl(this.mContext, this);
        this.bundle = getArguments();
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        DealTouchDelegate.expandViewTouchDelegateCom(imageView, 15);
        this.tw_sdk_back_iv.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_ResetPasswordDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Lxhw_DialogManage.getInstance().showLogin(Lxhw_ResetPasswordDialog.this.mContext);
                Lxhw_ResetPasswordDialog.this.dismiss();
            }
        });
        TextView textView = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "uname_tv"));
        this.uname_tv = textView;
        Bundle bundle = this.bundle;
        if (bundle != null) {
            textView.setText(bundle.getString("uname"));
        }
        this.tw_input_newpsd_et = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_input_newpsd_et"));
        this.tw_input_newpsd_et2 = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_input_newpsd_et2"));
        this.tw_input_newpsd_et.setLayerType(2, null);
        this.tw_input_newpsd_et2.setLayerType(2, null);
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_change_psd_btn"));
        this.tw_change_psd_btn = button;
        button.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_change_psd_btn) {
            if (TextUtils.isEmpty(this.tw_input_newpsd_et.getText())) {
                Toast.makeText(getActivity(), getActivity().getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_input_password")), 0).show();
                return;
            }
            if (TextUtils.isEmpty(this.tw_input_newpsd_et2.getText())) {
                Toast.makeText(getActivity(), getActivity().getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_input_password")), 0).show();
                return;
            }
            if (this.tw_input_newpsd_et.getText().toString().trim().length() < 6) {
                Toast.makeText(getActivity(), getActivity().getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_new_pwd_length_tip")), 0).show();
                return;
            }
            if (this.tw_input_newpsd_et2.getText().toString().trim().length() < 6) {
                Toast.makeText(getActivity(), getActivity().getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_new_pwd_length_tip")), 0).show();
                return;
            }
            String trim = this.tw_input_newpsd_et.getText().toString().trim();
            String trim2 = this.tw_input_newpsd_et2.getText().toString().trim();
            if (!trim.equals(trim2)) {
                Toast.makeText(this.mContext, getActivity().getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_two_pwd_dif")), 0).show();
                return;
            }
            if (IsFastClickUtils.isFastClick(Lxhw_Platform.getInstance().fastClickTime)) {
                Log.e(this.TAG, "多次点击，返回...................");
                return;
            }
            int i = this.bundle.getInt("chooseCheckBoxType");
            String string = this.bundle.getString("code");
            UserBindInfoBean bindInfoBean = Lxhw_AreaPlatform.getInstance().getBindInfoBean();
            this.account = bindInfoBean.getData().getPlatform_account();
            this.password = trim;
            if (i == 1) {
                this.presenter.doChangePasswordByPhone(bindInfoBean.getData().getPlatform_uid(), bindInfoBean.getData().getPlatform_account(), bindInfoBean.getData().getPhone_prefix(), bindInfoBean.getData().getPhone(), string, trim, trim2);
            } else {
                this.presenter.doChangePasswordByEmail(bindInfoBean.getData().getPlatform_uid(), bindInfoBean.getData().getPlatform_account(), bindInfoBean.getData().getEmail(), string, trim, trim2);
            }
            Lxhw_DialogManage.getInstance().showDialog();
        }
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, Object obj) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setP(this.password);
        loginInfo.setU(this.account);
        LoginInfoManage.getInstance().updataLoginInfos(this.mContext, loginInfo);
        String stringKeyForValue = ImageUtils.getStringKeyForValue(this.mContext, Constants.SDK_ACCOUNT);
        if (this.account.equals(stringKeyForValue)) {
            ImageUtils.setSharePreferences(this.mContext, Constants.SDK_PASSWORD, this.password);
            PlatformLoginAP.getInstance().setAccountAndPw(stringKeyForValue, this.password);
        }
        Lxhw_DialogManage.getInstance().showLogin(this.mContext);
        dismiss();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
    }
}
