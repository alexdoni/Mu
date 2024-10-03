package com.oversea.ab_firstarea.dialog;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import com.oversea.ab_firstarea.dview.Lxhw_DelAccountView;
import com.oversea.ab_firstarea.haiwai.AppsFlyerControl;
import com.oversea.ab_firstarea.haiwai.FirebaseControl;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class lxhw_GdprDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener, Lxhw_DelAccountView {
    private Switch ad_personalization_signals_switch;
    private Switch ad_storage_switch;
    private Switch ad_user_data_switch;
    private Switch analytics_storage_switch;
    private TextView btnCancel;
    private TextView btnConfirm;
    private boolean isShowSecond = false;
    private ImageView ivBack;
    private ScrollView llFirst;
    private LinearLayout llSecond;
    private TextView tv_title;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xgdpr_dialog_layout";
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, Object obj) {
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        int i;
        int i2;
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        if (displayMetrics.widthPixels >= displayMetrics.heightPixels) {
            i = (int) (displayMetrics.heightPixels * 0.95d);
            i2 = (int) (displayMetrics.heightPixels * 0.95d);
        } else {
            i = (int) (displayMetrics.widthPixels * 0.85d);
            i2 = (int) (displayMetrics.heightPixels * 0.6d);
        }
        getDialog().getWindow().setLayout(i, i2);
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.btnCancel = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "btn_cancel"));
        this.btnConfirm = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "btn_confirm"));
        this.btnCancel.setOnClickListener(this);
        this.btnConfirm.setOnClickListener(this);
        this.llFirst = (ScrollView) view.findViewById(Util.getIdByName(this.mContext, "id", "ll_first"));
        this.llSecond = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "ll_second"));
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "iv_back"));
        this.ivBack = imageView;
        imageView.setOnClickListener(this);
        this.analytics_storage_switch = (Switch) view.findViewById(Util.getIdByName(this.mContext, "id", "analytics_storage_switch"));
        this.ad_storage_switch = (Switch) view.findViewById(Util.getIdByName(this.mContext, "id", "ad_storage_switch"));
        this.ad_user_data_switch = (Switch) view.findViewById(Util.getIdByName(this.mContext, "id", "ad_user_data_switch"));
        this.ad_personalization_signals_switch = (Switch) view.findViewById(Util.getIdByName(this.mContext, "id", "ad_personalization_signals_switch"));
        boolean booleanValue = ImageUtils.getStringKeyForBoolValue(this.mContext, Constants.SDK_GDPR_ANALYTICS, true).booleanValue();
        boolean booleanValue2 = ImageUtils.getStringKeyForBoolValue(this.mContext, Constants.SDK_GDPR_STORAGE, true).booleanValue();
        boolean booleanValue3 = ImageUtils.getStringKeyForBoolValue(this.mContext, Constants.SDK_GDPR_USER_DATA, true).booleanValue();
        boolean booleanValue4 = ImageUtils.getStringKeyForBoolValue(this.mContext, Constants.SDK_GDPR_PERSONALIZATION, true).booleanValue();
        this.analytics_storage_switch.setChecked(booleanValue);
        this.ad_storage_switch.setChecked(booleanValue2);
        this.ad_user_data_switch.setChecked(booleanValue3);
        this.ad_personalization_signals_switch.setChecked(booleanValue4);
        setCancelable(false);
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_GDRPR_SHOW);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.btnCancel) {
            if (!this.isShowSecond) {
                showSecond();
                return;
            }
            FirebaseControl.getInstance().setConsent(true, true, true, true);
            AppsFlyerControl.getInstance().setConsentAndStart(this.mContext, true, true, true);
            ImageUtils.setSharePreferences((Context) this.mContext, Constants.SDK_GDPR_ANALYTICS, true);
            ImageUtils.setSharePreferences((Context) this.mContext, Constants.SDK_GDPR_STORAGE, true);
            ImageUtils.setSharePreferences((Context) this.mContext, Constants.SDK_GDPR_USER_DATA, true);
            ImageUtils.setSharePreferences((Context) this.mContext, Constants.SDK_GDPR_PERSONALIZATION, true);
            Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_GDPR_AGREE_ALL);
            dismissAllowingStateLoss();
            return;
        }
        if (view == this.btnConfirm) {
            if (!this.isShowSecond) {
                FirebaseControl.getInstance().setConsent(true, true, true, true);
                AppsFlyerControl.getInstance().setConsentAndStart(this.mContext, true, true, true);
                ImageUtils.setSharePreferences((Context) this.mContext, Constants.SDK_GDPR_ANALYTICS, true);
                ImageUtils.setSharePreferences((Context) this.mContext, Constants.SDK_GDPR_STORAGE, true);
                ImageUtils.setSharePreferences((Context) this.mContext, Constants.SDK_GDPR_USER_DATA, true);
                ImageUtils.setSharePreferences((Context) this.mContext, Constants.SDK_GDPR_PERSONALIZATION, true);
                Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_GDPR_AGREE_ALL);
            } else {
                FirebaseControl.getInstance().setConsent(this.analytics_storage_switch.isChecked(), this.ad_storage_switch.isChecked(), this.ad_user_data_switch.isChecked(), this.ad_personalization_signals_switch.isChecked());
                AppsFlyerControl.getInstance().setConsentAndStart(this.mContext, true, this.ad_user_data_switch.isChecked(), this.ad_personalization_signals_switch.isChecked());
                ImageUtils.setSharePreferences(this.mContext, Constants.SDK_GDPR_ANALYTICS, this.analytics_storage_switch.isChecked());
                ImageUtils.setSharePreferences(this.mContext, Constants.SDK_GDPR_STORAGE, this.ad_storage_switch.isChecked());
                ImageUtils.setSharePreferences(this.mContext, Constants.SDK_GDPR_USER_DATA, this.ad_user_data_switch.isChecked());
                ImageUtils.setSharePreferences(this.mContext, Constants.SDK_GDPR_PERSONALIZATION, this.ad_personalization_signals_switch.isChecked());
                Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_GDPR_CLICK_CONFIRM);
                Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_GDPR_ADSTORAGE + this.ad_storage_switch.isChecked());
                Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_GDPR_ANALYTICTORAGE + this.analytics_storage_switch.isChecked());
                Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_GDPR_ADUSERDATA + this.ad_user_data_switch.isChecked());
                Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_GDPR_ADPERSONAL + this.ad_personalization_signals_switch.isChecked());
            }
            dismissAllowingStateLoss();
            return;
        }
        if (view == this.ivBack) {
            showFirst();
        }
    }

    private void showFirst() {
        this.isShowSecond = false;
        this.llFirst.setVisibility(0);
        this.llSecond.setVisibility(8);
        this.btnCancel.setText("Manage options");
        this.btnConfirm.setText("Consent");
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_GDRPR_SHOW);
    }

    private void showSecond() {
        this.isShowSecond = true;
        this.llFirst.setVisibility(8);
        this.llSecond.setVisibility(0);
        this.btnCancel.setText("Accept all");
        this.btnConfirm.setText("Confirm choices");
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_GDPR_MANAGE_SHOW);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        ToastUtils.toastShow(this.mContext, ComUtil.getBaseBeanTip(baseBean));
    }
}
