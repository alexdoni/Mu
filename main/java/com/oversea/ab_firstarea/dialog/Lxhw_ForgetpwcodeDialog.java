package com.oversea.ab_firstarea.dialog;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.oversea.ab_firstarea.dpresenter.PresenterForgetpwcode;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_ForgetpwcodePresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_ForgetpwcodeView;
import com.oversea.ab_firstarea.net.model.UserBindInfoBean;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.IsFastClickUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_ForgetpwcodeDialog extends Lxhw_BaseDialogFragment implements Lxhw_ForgetpwcodeView, View.OnClickListener {
    private Bundle bundle;
    private CountDownTimer countDownTimer;
    private Button mSutmitBtn;
    private PresenterForgetpwcode presenter;
    private TextView textViewTip;
    private TextView textviewtitle;
    private EditText tw_forgetpw_code_Edit;
    private TextView tw_forgetpwd_code_btn;
    ImageView tw_sdk_back_iv;
    private String TAG = "Lhwl_ForgetpwcodeDialog";
    private String emial = "";
    private String mobile = "";
    private String phone_prefix = "";
    private String uname = "";
    private int chooseCheckBoxType = 0;
    private int viewType = 0;
    private String code = "";

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xforgetpwcode_layout";
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, Object obj) {
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.presenter = new Lxhw_ForgetpwcodePresenterImpl(this.mContext, this);
        Bundle arguments = getArguments();
        this.bundle = arguments;
        if (arguments != null) {
            if (arguments.containsKey("uname")) {
                this.uname = this.bundle.getString("uname");
            }
            if (this.bundle.containsKey("email")) {
                this.emial = this.bundle.getString("email");
            }
            if (this.bundle.containsKey("mobile")) {
                this.mobile = this.bundle.getString("mobile");
            }
            if (this.bundle.containsKey("phone_prefix")) {
                this.phone_prefix = this.bundle.getString("phone_prefix");
            }
            if (this.bundle.containsKey("chooseCheckBoxType")) {
                this.chooseCheckBoxType = this.bundle.getInt("chooseCheckBoxType");
            }
            if (this.bundle.containsKey("viewType")) {
                this.viewType = this.bundle.getInt("viewType");
            }
        }
        EditText editText = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_forgetpw_code_edit"));
        this.tw_forgetpw_code_Edit = editText;
        editText.setLayerType(2, null);
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_forgetpwd_submit_btn"));
        this.mSutmitBtn = button;
        button.setOnClickListener(this);
        TextView textView = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_forgetpwd_code_btn"));
        this.tw_forgetpwd_code_btn = textView;
        textView.setOnClickListener(this);
        this.textViewTip = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "textviewtip"));
        this.textviewtitle = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "textviewtitle"));
        if (this.viewType == 0) {
            this.textviewtitle.setText(this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_find_pwd")));
        } else if (this.chooseCheckBoxType == 2) {
            this.textviewtitle.setText(this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_change_email")));
        } else {
            this.textviewtitle.setText(this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_change_mobile")));
        }
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_sdk_back_iv, 15);
        setTextViewTip();
        this.countDownTimer = new CountDownTimer(60000L, 1000L) { // from class: com.oversea.ab_firstarea.dialog.Lxhw_ForgetpwcodeDialog.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                Log.d(ViewHierarchyConstants.TAG_KEY, "***********onTick***countDownTimer");
                if (Lxhw_ForgetpwcodeDialog.this.mContext != null) {
                    String string = Lxhw_ForgetpwcodeDialog.this.mContext.getString(Util.getIdByName(Lxhw_ForgetpwcodeDialog.this.mContext, TypedValues.Custom.S_STRING, "tw_send_second"));
                    Lxhw_ForgetpwcodeDialog.this.tw_forgetpwd_code_btn.setClickable(false);
                    Lxhw_ForgetpwcodeDialog.this.tw_forgetpwd_code_btn.setText(string + (j / 1000) + ExifInterface.LATITUDE_SOUTH);
                }
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (Lxhw_ForgetpwcodeDialog.this.mContext != null) {
                    try {
                        Lxhw_ForgetpwcodeDialog.this.tw_forgetpwd_code_btn.setText(Lxhw_ForgetpwcodeDialog.this.mContext.getString(Util.getIdByName(Lxhw_ForgetpwcodeDialog.this.mContext, TypedValues.Custom.S_STRING, "tw_send_second")));
                        Lxhw_ForgetpwcodeDialog.this.tw_forgetpwd_code_btn.setClickable(true);
                        Lxhw_ForgetpwcodeDialog.this.tw_forgetpwd_code_btn.setTextColor(Lxhw_ForgetpwcodeDialog.this.mContext.getResources().getColor(Util.getIdByName(Lxhw_ForgetpwcodeDialog.this.mContext, TypedValues.Custom.S_COLOR, "tw_sdk_background_font_color")));
                    } catch (Throwable unused) {
                    }
                }
            }
        };
        startCountDown();
    }

    public void setTextViewTip() {
        if (this.textViewTip != null) {
            int i = this.chooseCheckBoxType;
            if (i == 1) {
                this.textViewTip.setText(Html.fromHtml("<font color='#444444'>" + this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_change_pwd_tip1_1")) + "</font>,<font color='#FF0000'>" + this.mobile + "</font>,<font color='#444444'>" + this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_change_pwd_tip3")) + "</font>,<font color='#FF0000'>30</font><font color='#444444'>" + this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_change_pwd_tip4")) + "</font>,"));
                return;
            }
            if (i == 2) {
                this.textViewTip.setText(Html.fromHtml("<font color='#444444'>" + this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_change_pwd_tip1")) + "</font>,<font color='#FF0000'>" + this.emial + "</font>,<font color='#444444'>" + this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_change_pwd_tip2")) + "</font>,<font color='#FF0000'>30</font><font color='#444444'>" + this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_change_pwd_tip4")) + "</font>,"));
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_sdk_back_iv) {
            dismissDialog();
            return;
        }
        if (view == this.mSutmitBtn) {
            if (IsFastClickUtils.isFastClick(Lxhw_Platform.getInstance().fastClickTime)) {
                return;
            }
            if (TextUtils.isEmpty(this.tw_forgetpw_code_Edit.getText())) {
                Toast.makeText(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_user_name_nil")), 0).show();
                return;
            } else {
                submitOk(this.tw_forgetpw_code_Edit.getText().toString());
                Lxhw_DialogManage.getInstance().showDialog();
                return;
            }
        }
        if (view == this.tw_forgetpwd_code_btn) {
            getCode();
        }
    }

    public void submitOk(String str) {
        this.code = str;
        int i = this.viewType;
        if (i != 0) {
            if (i == 1) {
                int i2 = this.chooseCheckBoxType;
                if (i2 == 1) {
                    this.presenter.doUnbindPhone(this.phone_prefix, this.mobile, str);
                    return;
                } else {
                    if (i2 == 2) {
                        this.presenter.doUnbindEmail(this.emial, str);
                        return;
                    }
                    return;
                }
            }
            return;
        }
        UserBindInfoBean bindInfoBean = Lxhw_AreaPlatform.getInstance().getBindInfoBean();
        if (bindInfoBean == null || bindInfoBean.getData() == null) {
            LLog.v_noControl("getcode bindinfobean null");
            return;
        }
        int i3 = this.chooseCheckBoxType;
        if (i3 == 1) {
            this.presenter.doChangePasswordByPhoneCheckCode(bindInfoBean.getData().getPlatform_uid(), bindInfoBean.getData().getPlatform_account(), bindInfoBean.getData().getPhone_prefix(), bindInfoBean.getData().getPhone(), str);
        } else if (i3 == 2) {
            this.presenter.doChangePasswordByEmailCheckCode(bindInfoBean.getData().getPlatform_uid(), bindInfoBean.getData().getPlatform_account(), bindInfoBean.getData().getEmail(), str);
        }
    }

    public void getCode() {
        int i = this.viewType;
        if (i == 0) {
            UserBindInfoBean bindInfoBean = Lxhw_AreaPlatform.getInstance().getBindInfoBean();
            int i2 = this.chooseCheckBoxType;
            if (i2 == 1) {
                this.presenter.changePasswordByPhoneSendCode(bindInfoBean.getData().getPlatform_uid(), bindInfoBean.getData().getPlatform_account(), bindInfoBean.getData().getPhone_prefix(), bindInfoBean.getData().getPhone());
                return;
            } else {
                if (i2 == 2) {
                    this.presenter.changePasswordByEmailSendCode(bindInfoBean.getData().getPlatform_uid(), bindInfoBean.getData().getPlatform_account(), bindInfoBean.getData().getEmail());
                    return;
                }
                return;
            }
        }
        if (i == 1) {
            int i3 = this.chooseCheckBoxType;
            if (i3 == 1) {
                this.presenter.doUnbindPhoneSendCode(this.phone_prefix, this.mobile);
            } else if (i3 == 2) {
                this.presenter.doUnbindEmailSendCode(this.emial);
            }
        }
    }

    public void startCountDown() {
        this.countDownTimer.start();
        this.tw_forgetpwd_code_btn.setTextColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_hint_text_black")));
    }

    public void dismissDialog() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
        dismissAllowingStateLoss();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_ForgetpwcodeView
    public void onReqCodeSuccess() {
        startCountDown();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_ForgetpwcodeView
    public void jumpResetPassword() {
        this.bundle.putString("code", this.code);
        Lxhw_DialogManage.getInstance().removeFragment(this.mContext, "forgetpwaccountDialog");
        Lxhw_DialogManage.getInstance().removeFragment(this.mContext, "forgetpwchooseDialog");
        Lxhw_DialogManage.getInstance().removeFragment(this.mContext, "LoginDialog");
        Lxhw_DialogManage.getInstance().enterResetPasswordCenter(this.mContext, getArguments());
        dismissDialog();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_ForgetpwcodeView
    public void onReqUnbindPhoneSuccess() {
        Lxhw_DialogManage.getInstance().enterBindMobileCenter(this.mContext);
        Lxhw_DialogManage.getInstance().removeFragment(this.mContext, "UserCenterDialog");
        dismissDialog();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_ForgetpwcodeView
    public void onReqUnbindEmailSuccess() {
        Lxhw_DialogManage.getInstance().enterBindEmailCenter(this.mContext);
        Lxhw_DialogManage.getInstance().removeFragment(this.mContext, "UserCenterDialog");
        dismissDialog();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
    }
}
