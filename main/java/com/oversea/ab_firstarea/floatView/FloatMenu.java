package com.oversea.ab_firstarea.floatView;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class FloatMenu extends PopupWindow implements View.OnClickListener {
    private Activity content;
    public int floatType;
    private ImageView float_community;
    private ImageView float_customer;
    private ImageView float_gift;
    private ImageView float_img;
    private ImageView float_switch;
    private ImageView float_user;
    private View red_view;
    private String TAG = "Lhwl_FloatMenu";
    private RelativeLayout rel_float_com = null;
    private LinearLayout rel_user = null;
    private LinearLayout rel_community = null;
    private LinearLayout rel_customer = null;
    private LinearLayout rel_gift = null;
    private LinearLayout rel_switch = null;
    public View floatMenuV = null;

    public FloatMenu(Activity activity, int i) {
        this.content = activity;
        this.floatType = i;
        initFloatMenu();
    }

    private void initFloatMenu() {
        View inflate = LayoutInflater.from(this.content).inflate(Util.getIdByName(this.content, "layout", "floatmenu"), (ViewGroup) null);
        this.floatMenuV = inflate;
        if (inflate == null) {
            Log.i(this.TAG, "initFloatMenu floatMenuV null");
        }
        this.rel_float_com = (RelativeLayout) this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "rel_float_com"));
        this.rel_user = (LinearLayout) this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "rel_user"));
        this.rel_community = (LinearLayout) this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "rel_community"));
        this.rel_customer = (LinearLayout) this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "rel_customer"));
        this.rel_gift = (LinearLayout) this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "rel_gift"));
        this.rel_switch = (LinearLayout) this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "rel_switch"));
        this.float_img = (ImageView) this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "float_img"));
        if (Util.getSystemLang().equals(LanguageType.SERVER_VI) && Lxhw_AreaPlatform.getInstance().iShowViInfo) {
            this.float_img.setImageResource(Util.getIdByName(this.content, "drawable", "drhw_toolbar_normalbtn_vi"));
        }
        this.float_user = (ImageView) this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "float_user"));
        this.float_community = (ImageView) this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "float_community"));
        this.float_customer = (ImageView) this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "float_customer"));
        this.float_gift = (ImageView) this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "float_gift"));
        this.float_switch = (ImageView) this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "float_switch"));
        this.float_img.setOnClickListener(this);
        this.rel_user.setOnClickListener(this);
        this.rel_community.setOnClickListener(this);
        this.rel_customer.setOnClickListener(this);
        this.rel_gift.setOnClickListener(this);
        this.rel_switch.setOnClickListener(this);
        this.rel_community.setVisibility(8);
        this.red_view = this.floatMenuV.findViewById(Util.getIdByName(this.content, "id", "red_view"));
        if (ManageBean.getInstance().getKfBaseInfoBean().getData().getHas_not_read_issue()) {
            this.red_view.setVisibility(0);
        } else {
            this.red_view.setVisibility(8);
        }
        if (ManageBean.getInstance().getKfBaseInfoBean() != null && !TextUtils.isEmpty(InitBean.getInstance().getGame_info().getFb_fans_url())) {
            this.rel_community.setVisibility(0);
        }
        if (InitBean.getInstance().getGame_info().getAndroid_gift_switch() == 0) {
            this.rel_gift.setVisibility(8);
        }
        if (InitBean.getInstance().getCustomer_service_config() != null && InitBean.getInstance().getCustomer_service_config().getOnline_customer_service_switch() == 0 && InitBean.getInstance().getCustomer_service_config().getEmail_customer_service_switch() == 0) {
            this.rel_customer.setVisibility(8);
        }
        setWidth(-2);
        setHeight(-2);
        setContentView(this.floatMenuV);
        fitPopupWindowOverStatusBar(this, true);
    }

    public static void fitPopupWindowOverStatusBar(PopupWindow popupWindow, boolean z) {
        try {
            Field declaredField = PopupWindow.class.getDeclaredField("mLayoutInScreen");
            declaredField.setAccessible(z);
            declaredField.set(popupWindow, Boolean.valueOf(z));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    public void showFloatmenu(final int i, final int i2) {
        if (this.floatMenuV == null || this.content.isFinishing()) {
            return;
        }
        this.content.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.floatView.FloatMenu.1
            @Override // java.lang.Runnable
            public void run() {
                FloatMenu floatMenu = FloatMenu.this;
                floatMenu.showAtLocation(floatMenu.floatMenuV, 51, i, i2);
            }
        });
    }

    public void hideFloatMenuView() {
        if (this.floatMenuV != null) {
            dismiss();
        }
    }

    public void removeFloatMenuView() {
        try {
            if (this.floatMenuV != null) {
                this.floatMenuV = null;
            }
            if (isShowing()) {
                dismiss();
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(final View view) {
        try {
            if (this.content == null) {
                Log.i(this.TAG, "onClick content null");
                if (Lxhw_XSDK.getInstance().getContext() != null) {
                    this.content = Lxhw_XSDK.getInstance().getContext();
                } else {
                    Log.i(this.TAG, "onClick getActivity() null");
                    return;
                }
            }
            new Handler(this.content.getMainLooper()).post(new Runnable() { // from class: com.oversea.ab_firstarea.floatView.FloatMenu.2
                @Override // java.lang.Runnable
                public void run() {
                    if (view == FloatMenu.this.float_img) {
                        FloatMenu.this.showFloatView();
                        return;
                    }
                    if (view == FloatMenu.this.rel_user) {
                        Lxhw_DialogManage.getInstance().showUserCenter(FloatMenu.this.content);
                        FloatMenu.this.showFloatView();
                        return;
                    }
                    if (view != FloatMenu.this.rel_community) {
                        if (view != FloatMenu.this.rel_customer) {
                            if (view != FloatMenu.this.rel_gift) {
                                if (view == FloatMenu.this.rel_switch) {
                                    Lxhw_AreaPlatform.getInstance().callbackSwitchAccount();
                                    return;
                                }
                                return;
                            } else if (Lxhw_Platform.getInstance().userExtraData == null || TextUtils.isEmpty(Lxhw_Platform.getInstance().userExtraData.getServerId()) || TextUtils.isEmpty(Lxhw_Platform.getInstance().userExtraData.getRoleID())) {
                                ToastUtils.toastShow(FloatMenu.this.content, Util.getString(FloatMenu.this.content, "tw_gift_show_tip"));
                                return;
                            } else {
                                Lxhw_DialogManage.getInstance().showGiftCenter(FloatMenu.this.content);
                                FloatMenu.this.showFloatView();
                                return;
                            }
                        }
                        ManageBean.getInstance().getKfBaseInfoBean().getData().setHas_not_read_issue(false);
                        FloatView.getInstance().showRedView();
                        Lxhw_DialogManage.getInstance().showCustomerService(FloatMenu.this.content);
                        FloatMenu.this.showFloatView();
                        return;
                    }
                    if (InitBean.getInstance().getGame_info() != null && !TextUtils.isEmpty(InitBean.getInstance().getGame_info().getFb_fans_url())) {
                        Lxhw_DialogManage.getInstance().jumpBrowserUrl(FloatMenu.this.content, InitBean.getInstance().getGame_info().getFb_fans_url());
                    }
                    FloatMenu.this.showFloatView();
                }
            });
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFloatView() {
        removeFloatMenuView();
        FloatView.getInstance().showPop();
        FloatView.getInstance().changeNormal(false);
    }
}
