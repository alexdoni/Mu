package com.oversea.ab_firstarea.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.oversea.ab_firstarea.channel.ProjectType;
import com.oversea.ab_firstarea.dpresenter.PresenterUserCenter;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_UserCenterPresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_UserCenterView;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.net.model.UserInfoBean;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.plugin.ad.AdmobLibHelper;
import com.oversea.ab_firstarea.plugin.ad.max.MaxLibHelper;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstarea.utils.SDKVersion;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_UserCenterDialog extends Lxhw_BaseDialogFragment implements Lxhw_UserCenterView, View.OnClickListener {
    private int clickCount;
    private PresenterUserCenter presenter;
    private TextView sdk_version;
    private TextView tvTitle;
    private TextView tw_account_name_tv;
    private TextView tw_call_center;
    private TextView tw_change_psd_layout;
    private ImageView tw_close_dai_iv;
    private TextView tw_confirm_del_layout;
    private TextView tw_confirm_email_layout;
    private TextView tw_confirm_phone_layout;
    private TextView tw_confirm_zhsj_layout;
    private TextView tw_gdpr_layout;
    private TextView tw_gift_center_tv;
    private TextView tw_switch_account;
    private TextView usercenter_bdxx_tv;
    private TextView usercenter_sjqr_tv;
    private String emial = "";
    private String mobile = "";
    private String phone_prefix = "";
    private String TAG = "UserCenterDialog";

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xuser_center_dialog";
    }

    public void setAccountView() {
    }

    static /* synthetic */ int access$008(Lxhw_UserCenterDialog lxhw_UserCenterDialog) {
        int i = lxhw_UserCenterDialog.clickCount;
        lxhw_UserCenterDialog.clickCount = i + 1;
        return i;
    }

    private int getId(String str) {
        return Util.getIdByName(this.mContext, "id", str);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_confirm_email_layout) {
            if (TextUtils.isEmpty(this.emial)) {
                if (Lxhw_AreaPlatform.getInstance().getUserInfoBean() == null || Lxhw_AreaPlatform.getInstance().getUserInfoBean().getData().getCan_bind_email() == 0) {
                    ToastUtils.toastLongShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "upgrade_platform_account_tips")));
                    return;
                } else {
                    Lxhw_DialogManage.getInstance().enterBindEmailCenter(this.mContext);
                    return;
                }
            }
            if (Lxhw_AreaPlatform.getInstance().getUserInfoBean() == null || Lxhw_AreaPlatform.getInstance().getUserInfoBean().getData().getCan_unbind_email() == 0) {
                ToastUtils.toastLongShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "upgrade_platform_account_tips")));
                return;
            } else {
                this.presenter.doUnbindEmailSendCode(this.emial);
                return;
            }
        }
        if (view == this.tw_confirm_phone_layout) {
            if (TextUtils.isEmpty(this.mobile)) {
                if (Lxhw_AreaPlatform.getInstance().getUserInfoBean() == null || Lxhw_AreaPlatform.getInstance().getUserInfoBean().getData().getCan_bind_phone() == 0) {
                    ToastUtils.toastLongShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "upgrade_platform_account_tips")));
                    return;
                } else {
                    Lxhw_DialogManage.getInstance().enterBindMobileCenter(this.mContext);
                    return;
                }
            }
            if (Lxhw_AreaPlatform.getInstance().getUserInfoBean() == null || Lxhw_AreaPlatform.getInstance().getUserInfoBean().getData().getCan_unbind_phone() == 0) {
                ToastUtils.toastLongShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "upgrade_platform_account_tips")));
                return;
            } else {
                this.presenter.doUnbindPhoneSendCode(this.phone_prefix, this.mobile);
                return;
            }
        }
        if (view == this.tw_change_psd_layout) {
            if (Lxhw_AreaPlatform.getInstance().getUserInfoBean() == null || Lxhw_AreaPlatform.getInstance().getUserInfoBean().getData().getCan_change_password() == 0) {
                ToastUtils.toastLongShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "upgrade_platform_account_tips")));
                return;
            } else {
                Lxhw_DialogManage.getInstance().enterChangePsdCenter(this.mContext);
                return;
            }
        }
        if (view == this.tw_confirm_zhsj_layout) {
            Lxhw_DialogManage.getInstance().enterAccountUpgrade(this.mContext);
            return;
        }
        if (this.tw_close_dai_iv == view) {
            dismiss();
        } else if (view == this.tw_confirm_del_layout) {
            Lxhw_DialogManage.getInstance().showDelAccountDialog(this.mContext, null);
        } else if (view == this.tw_gdpr_layout) {
            Lxhw_DialogManage.getInstance().showGdprDialog(this.mContext, null);
        }
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.presenter = new Lxhw_UserCenterPresenterImpl(this.mContext, this);
        TextView textView = (TextView) view.findViewById(getId("tw_confirm_email_layout"));
        this.tw_confirm_email_layout = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) view.findViewById(getId("tw_confirm_phone_layout"));
        this.tw_confirm_phone_layout = textView2;
        textView2.setOnClickListener(this);
        if (ProjectType.FIVEFIVE.equals(ProjectType.pType)) {
            this.tw_confirm_phone_layout.setVisibility(8);
        }
        TextView textView3 = (TextView) view.findViewById(getId("tw_change_psd_layout"));
        this.tw_change_psd_layout = textView3;
        textView3.setOnClickListener(this);
        TextView textView4 = (TextView) view.findViewById(getId("tw_confirm_zhsj_layout"));
        this.tw_confirm_zhsj_layout = textView4;
        textView4.setOnClickListener(this);
        if (InitBean.getInstance().getIs_in_european_union() == 1) {
            TextView textView5 = (TextView) view.findViewById(getId("tw_gdpr_layout"));
            this.tw_gdpr_layout = textView5;
            textView5.setVisibility(0);
            this.tw_gdpr_layout.setOnClickListener(this);
        }
        TextView textView6 = (TextView) view.findViewById(getId("tw_confirm_del_layout"));
        this.tw_confirm_del_layout = textView6;
        textView6.setOnClickListener(this);
        this.tw_account_name_tv = (TextView) view.findViewById(getId("tw_account_name_tv"));
        this.usercenter_bdxx_tv = (TextView) view.findViewById(getId("usercenter_bdxx_tv"));
        this.usercenter_sjqr_tv = (TextView) view.findViewById(getId("usercenter_sjqr_tv"));
        TextView textView7 = (TextView) view.findViewById(getId(ComConstants.sdk_version_code));
        this.sdk_version = textView7;
        textView7.setText(SDKVersion.sdkVersion);
        String string = this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_personal_center_account"));
        this.tw_account_name_tv.setText(string + ManageBean.getInstance().getAccount() + "(" + Lxhw_XUserInfo.getInstance().getSdkId() + ")");
        ImageView imageView = (ImageView) view.findViewById(getId("tw_close_dai_iv"));
        this.tw_close_dai_iv = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_close_dai_iv, 15);
        if (ManageBean.getInstance().getLoginBean().getData().getAccount_type() == 1) {
            this.tw_confirm_zhsj_layout.setVisibility(8);
        }
        if (InitBean.getInstance().getGame_info() != null) {
            if (InitBean.getInstance().getGame_info().getAndroid_delete_account_switch() == 1) {
                this.tw_confirm_del_layout.setVisibility(0);
            } else {
                this.tw_confirm_del_layout.setVisibility(8);
            }
        }
        TextView textView8 = (TextView) view.findViewById(getId("tw_tv_title"));
        this.tvTitle = textView8;
        if (textView8 != null) {
            textView8.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_UserCenterDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (Lxhw_UserCenterDialog.this.clickCount < 10) {
                        Lxhw_UserCenterDialog.access$008(Lxhw_UserCenterDialog.this);
                        return;
                    }
                    if (LLog.isIsOpenLog() && Lxhw_UserCenterDialog.this.clickCount == 10) {
                        if ("MAX".equals(InitBean.getInstance().getGame_info().getAndroid_video_advert_platform())) {
                            MaxLibHelper.getInstance().openDebugView(Lxhw_XSDK.getInstance().getContext());
                        } else if ("Admob".equals(InitBean.getInstance().getGame_info().getAndroid_video_advert_platform())) {
                            AdmobLibHelper.getInstance().openDebugView(Lxhw_XSDK.getInstance().getContext());
                        }
                    }
                }
            });
        }
        this.presenter.doGetUserInfo();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, UserInfoBean userInfoBean) {
        Lxhw_AreaPlatform.getInstance().setUserInfoBean(userInfoBean);
        if (userInfoBean != null) {
            if (userInfoBean.getData() != null && !TextUtils.isEmpty(userInfoBean.getData().getEmail())) {
                this.emial = userInfoBean.getData().getEmail();
            }
            if (!TextUtils.isEmpty(this.emial) && this.usercenter_bdxx_tv != null) {
                this.usercenter_bdxx_tv.setText(this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_change_email")));
            }
            if (userInfoBean.getData() != null && !TextUtils.isEmpty(userInfoBean.getData().getPhone())) {
                this.mobile = userInfoBean.getData().getPhone();
            }
            if (userInfoBean.getData() != null && !TextUtils.isEmpty(userInfoBean.getData().getPhone_prefix())) {
                this.phone_prefix = userInfoBean.getData().getPhone_prefix();
            }
            if (TextUtils.isEmpty(this.mobile) || this.usercenter_sjqr_tv == null) {
                return;
            }
            this.usercenter_sjqr_tv.setText(this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_change_mobile")));
        }
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_UserCenterView
    public void onUnBindPhoneSuccess(String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("chooseCheckBoxType", 1);
        bundle.putInt("viewType", 1);
        bundle.putString("mobile", this.mobile);
        bundle.putString("phone_prefix", this.phone_prefix);
        bundle.putString("uname", ManageBean.getInstance().getAccount());
        Lxhw_DialogManage.getInstance().enterForgetPwcodeCenter(this.mContext, bundle);
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_UserCenterView
    public void onUnBindEmailSuccess(String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("chooseCheckBoxType", 2);
        bundle.putInt("viewType", 1);
        bundle.putString("email", this.emial);
        bundle.putString("uname", ManageBean.getInstance().getAccount());
        Lxhw_DialogManage.getInstance().enterForgetPwcodeCenter(this.mContext, bundle);
    }
}
