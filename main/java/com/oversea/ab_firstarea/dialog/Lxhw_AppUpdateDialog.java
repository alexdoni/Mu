package com.oversea.ab_firstarea.dialog;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.DensityUtil;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_AppUpdateDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    private ImageView iv_close;
    private Button tw_app_update_btn;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xapp_update";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        int dip2px = DensityUtil.dip2px(this.mContext, 280.0f);
        if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
            getDialog().getWindow().setLayout((int) (displayMetrics.widthPixels * 0.5d), dip2px);
        } else {
            getDialog().getWindow().setLayout((int) (displayMetrics.widthPixels * 0.85d), dip2px);
        }
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        ImageView imageView;
        setCancelable(false);
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_app_update_btn"));
        this.tw_app_update_btn = button;
        button.setOnClickListener(this);
        ImageView imageView2 = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "iv_close"));
        this.iv_close = imageView2;
        imageView2.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.iv_close, 15);
        int android_update_switch = InitBean.getInstance().getGame_info() != null ? InitBean.getInstance().getGame_info().getAndroid_update_switch() : 0;
        if (android_update_switch == 1 && (imageView = this.iv_close) != null) {
            imageView.setVisibility(8);
        }
        Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(this.mContext, ComConfig.CUSTOM_SDK_UPDATE_SHOW, android_update_switch + "");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_app_update_btn) {
            Lxhw_DialogManage.getInstance().checkUpdata(this.mContext, Util.getGoogleShopUrl(this.mContext));
            Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_UPDATE_CLICK_OK);
        } else if (view == this.iv_close) {
            Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_UPDATE_CLICK_CANCEL);
            dismissAllowingStateLoss();
            Lxhw_Platform.getInstance().login(Lxhw_XSDK.getInstance().getContext());
        }
    }
}
