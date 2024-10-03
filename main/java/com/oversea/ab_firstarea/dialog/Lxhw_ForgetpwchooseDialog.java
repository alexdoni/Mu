package com.oversea.ab_firstarea.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.oversea.ab_firstarea.dpresenter.PresenterForgetpwchoose;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_ForgetpwchoosePresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_ForgetpwchooseView;
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
public class Lxhw_ForgetpwchooseDialog extends Lxhw_BaseDialogFragment implements Lxhw_ForgetpwchooseView, View.OnClickListener {
    Bundle bundle;
    private Button mSutmitBtn;
    private PresenterForgetpwchoose presenter;
    private ImageView select_email;
    private ImageView select_phone;
    private RelativeLayout tw_email_layout;
    private RelativeLayout tw_phone_layout;
    ImageView tw_sdk_back_iv;
    private String TAG = "ForgetpwchooseDialog";
    private boolean isChanging = false;
    private String emial = "";
    private String mobile = "";
    private String uname = "";
    private int chooseCheckBoxType = 0;
    private String sessionid = "";
    int select_phone_id = 0;
    int select_email_id = 0;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xforgetpwchoose_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.presenter = new Lxhw_ForgetpwchoosePresenterImpl(this.mContext, this);
        Bundle arguments = getArguments();
        this.bundle = arguments;
        if (arguments != null) {
            if (arguments.containsKey("email")) {
                this.emial = this.bundle.getString("email");
            }
            if (this.bundle.containsKey("mobile")) {
                this.mobile = this.bundle.getString("mobile");
            }
            if (this.bundle.containsKey("uname")) {
                this.uname = this.bundle.getString("uname");
            }
        }
        this.tw_email_layout = (RelativeLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_email_layout"));
        if (TextUtils.isEmpty(this.emial)) {
            this.tw_email_layout.setVisibility(8);
        }
        this.tw_phone_layout = (RelativeLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_phone_layout"));
        if (TextUtils.isEmpty(this.mobile)) {
            this.tw_phone_layout.setVisibility(8);
        }
        this.select_phone = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "select_phone"));
        this.select_email = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "select_email"));
        this.select_phone.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_ForgetpwchooseDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Lxhw_ForgetpwchooseDialog.this.chooseCheckBoxType = 1;
                Lxhw_ForgetpwchooseDialog.this.select_phone.setImageResource(Util.getIdByName(Lxhw_ForgetpwchooseDialog.this.mContext, "drawable", "drhw_registerbox_on"));
                Lxhw_ForgetpwchooseDialog.this.select_email.setImageResource(Util.getIdByName(Lxhw_ForgetpwchooseDialog.this.mContext, "drawable", "drhw_registerbox_off"));
            }
        });
        this.select_email.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_ForgetpwchooseDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Lxhw_ForgetpwchooseDialog.this.chooseCheckBoxType = 2;
                Lxhw_ForgetpwchooseDialog.this.select_email.setImageResource(Util.getIdByName(Lxhw_ForgetpwchooseDialog.this.mContext, "drawable", "drhw_registerbox_on"));
                Lxhw_ForgetpwchooseDialog.this.select_phone.setImageResource(Util.getIdByName(Lxhw_ForgetpwchooseDialog.this.mContext, "drawable", "drhw_registerbox_off"));
            }
        });
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_forgetpwd_submit_btn"));
        this.mSutmitBtn = button;
        button.setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_sdk_back_iv, 15);
        setCancelable(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_sdk_back_iv) {
            dismiss();
            return;
        }
        if (view == this.mSutmitBtn) {
            if (this.chooseCheckBoxType == 0) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_choosepwd_type"));
            } else {
                if (IsFastClickUtils.isFastClick(Lxhw_Platform.getInstance().fastClickTime)) {
                    Log.e(this.TAG, "多次点击，返回...................");
                    return;
                }
                Bundle bundle = this.bundle;
                if (bundle != null) {
                    bundle.putInt("chooseCheckBoxType", this.chooseCheckBoxType);
                    this.bundle.putInt("viewType", 0);
                }
                getCode();
            }
        }
    }

    public void getCode() {
        UserBindInfoBean bindInfoBean = Lxhw_AreaPlatform.getInstance().getBindInfoBean();
        if (bindInfoBean == null || bindInfoBean.getData() == null) {
            LLog.v_noControl("getcode bindinfobean null");
            return;
        }
        int i = this.chooseCheckBoxType;
        if (i == 1) {
            this.presenter.changePasswordByPhoneSendCode(bindInfoBean.getData().getPlatform_uid(), bindInfoBean.getData().getPlatform_account(), bindInfoBean.getData().getPhone_prefix(), bindInfoBean.getData().getPhone());
        } else if (i == 2) {
            this.presenter.changePasswordByEmailSendCode(bindInfoBean.getData().getPlatform_uid(), bindInfoBean.getData().getPlatform_account(), bindInfoBean.getData().getEmail());
        }
        Lxhw_DialogManage.getInstance().showDialog();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, Object obj) {
        Lxhw_DialogManage.getInstance().enterForgetPwcodeCenter(this.mContext, this.bundle);
        dismiss();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
    }
}
