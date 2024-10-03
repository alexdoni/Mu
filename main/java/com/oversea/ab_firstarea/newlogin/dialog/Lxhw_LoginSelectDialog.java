package com.oversea.ab_firstarea.newlogin.dialog;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.ContextCompat;
import com.oversea.ab_firstarea.channel.ProjectType;
import com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dpresenter.PresenterLogin;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_LoginPresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_LoginView;
import com.oversea.ab_firstarea.haiwai.FaceBookControl;
import com.oversea.ab_firstarea.haiwai.HuaweiControl;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.model.LoginTypeBean;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.loading.LoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.IsFastClickUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class Lxhw_LoginSelectDialog extends Lxhw_BaseDialogFragment implements Lxhw_LoginView, View.OnClickListener {
    private LinearLayout fb_linearlayout;
    private LinearLayout google_linearlayout;
    private LinearLayout huawei_linearlayout;
    private ImageView img_sdk_member;
    private TextView img_sdk_member_tv;
    private LinearLayout llParentHor;
    private LinearLayout llParentVer;
    private boolean loginDialogIsClose = false;
    private List<LoginTypeBean> loginTypeList = new ArrayList();
    private LinearLayout platform_linearlayout;
    private PresenterLogin presenter;
    private TextView tw_tv_platform_name;
    private View view_perform_click;
    private LinearLayout visitor_linearlayout;

    private void setTextImage(Drawable drawable, String str, int i, int i2) {
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xlogin_select_layout";
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
            i2 = displayMetrics.heightPixels;
        } else {
            i = (int) (displayMetrics.widthPixels * 0.95d);
            i2 = displayMetrics.widthPixels;
        }
        getDialog().getWindow().setLayout(i, (int) (i2 * 0.9d));
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        setCancelable(false);
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_SDK_NEW_LOGIN_SHOW);
        this.presenter = new Lxhw_LoginPresenterImpl(this.mContext, this);
        this.img_sdk_member_tv = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "img_sdk_member_tv"));
        this.img_sdk_member = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "img_sdk_member"));
        if (Util.getSystemLang().equals(LanguageType.SERVER_VI) && Lxhw_AreaPlatform.getInstance().iShowViInfo) {
            this.img_sdk_member.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_sdk_member_tip_vi"));
        }
        if (ProjectType.ZIAN.equals(ProjectType.pType)) {
            this.img_sdk_member.setVisibility(4);
        }
        if (ProjectType.FIVEFIVE.equals(ProjectType.pType)) {
            this.img_sdk_member.setVisibility(8);
            this.img_sdk_member_tv.setVisibility(0);
            this.img_sdk_member_tv.setText(this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_account_login_title")));
        } else if (ProjectType.TEST.equals(ProjectType.pType)) {
            this.img_sdk_member_tv.setVisibility(0);
        }
        View findViewById = view.findViewById(Util.getIdByName(this.mContext, "id", "view_perform_click"));
        this.view_perform_click = findViewById;
        findViewById.setOnClickListener(this);
        new Handler().postDelayed(new Runnable() { // from class: com.oversea.ab_firstarea.newlogin.dialog.Lxhw_LoginSelectDialog.1
            @Override // java.lang.Runnable
            public void run() {
                if (Lxhw_LoginSelectDialog.this.loginDialogIsClose) {
                    return;
                }
                Lxhw_LoginSelectDialog.this.view_perform_click.performClick();
            }
        }, 3000L);
        this.llParentVer = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "ll_parent_ver"));
        this.llParentHor = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "ll_parent_hor"));
        initLoginType();
    }

    private void initLoginType() {
        InitBean.Android_third_login_switch_config android_third_login_switch_config = InitBean.getInstance().getAndroid_third_login_switch_config();
        this.loginTypeList.clear();
        if (android_third_login_switch_config == null || android_third_login_switch_config.getGuest_switch_new() == null || android_third_login_switch_config.getFacebook_switch_new() == null || android_third_login_switch_config.getGoogle_swtich_new() == null || android_third_login_switch_config.getPlatform_switch_new() == null || android_third_login_switch_config.getHuawei_switch_new() == null) {
            addVerLoginView();
            addHorLoginView();
            return;
        }
        if (android_third_login_switch_config.getGuest_switch_new().getSwitchA() > 0) {
            LoginTypeBean loginTypeBean = new LoginTypeBean();
            loginTypeBean.setSwitchx(android_third_login_switch_config.getGuest_switch_new().getSwitchA());
            loginTypeBean.setSort(android_third_login_switch_config.getGuest_switch_new().getSort());
            loginTypeBean.setLoginType(0);
            this.loginTypeList.add(loginTypeBean);
        }
        if (android_third_login_switch_config.getFacebook_switch_new().getSwitchA() > 0) {
            LoginTypeBean loginTypeBean2 = new LoginTypeBean();
            loginTypeBean2.setSwitchx(android_third_login_switch_config.getFacebook_switch_new().getSwitchA());
            loginTypeBean2.setSort(android_third_login_switch_config.getFacebook_switch_new().getSort());
            loginTypeBean2.setLoginType(1);
            this.loginTypeList.add(loginTypeBean2);
        }
        if (android_third_login_switch_config.getGoogle_swtich_new().getSwitchA() > 0) {
            LoginTypeBean loginTypeBean3 = new LoginTypeBean();
            loginTypeBean3.setSwitchx(android_third_login_switch_config.getGoogle_swtich_new().getSwitchA());
            loginTypeBean3.setSort(android_third_login_switch_config.getGoogle_swtich_new().getSort());
            loginTypeBean3.setLoginType(2);
            this.loginTypeList.add(loginTypeBean3);
        }
        if (android_third_login_switch_config.getPlatform_switch_new().getSwitchA() > 0) {
            LoginTypeBean loginTypeBean4 = new LoginTypeBean();
            loginTypeBean4.setSwitchx(android_third_login_switch_config.getPlatform_switch_new().getSwitchA());
            loginTypeBean4.setSort(android_third_login_switch_config.getPlatform_switch_new().getSort());
            loginTypeBean4.setLoginType(3);
            this.loginTypeList.add(loginTypeBean4);
        }
        if (android_third_login_switch_config.getHuawei_switch_new().getSwitchA() > 0) {
            LoginTypeBean loginTypeBean5 = new LoginTypeBean();
            loginTypeBean5.setSwitchx(android_third_login_switch_config.getHuawei_switch_new().getSwitchA());
            loginTypeBean5.setSort(android_third_login_switch_config.getHuawei_switch_new().getSort());
            loginTypeBean5.setLoginType(4);
            this.loginTypeList.add(loginTypeBean5);
        }
        if (this.loginTypeList.size() == 0) {
            addVerLoginView();
            addHorLoginView();
        } else {
            Collections.sort(this.loginTypeList, new Comparator<LoginTypeBean>() { // from class: com.oversea.ab_firstarea.newlogin.dialog.Lxhw_LoginSelectDialog.2
                @Override // java.util.Comparator
                public int compare(LoginTypeBean loginTypeBean6, LoginTypeBean loginTypeBean7) {
                    return loginTypeBean6.getSort() - loginTypeBean7.getSort();
                }
            });
            addLoginView();
        }
    }

    private void addLoginView() {
        View inflate;
        LinearLayout linearLayout;
        ImageView imageView;
        TextView textView;
        this.llParentHor.removeAllViews();
        this.llParentVer.removeAllViews();
        this.llParentHor.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 2.0f));
        for (int i = 0; i < this.loginTypeList.size(); i++) {
            if (i >= 3) {
                inflate = LayoutInflater.from(this.mContext).inflate(Util.getIdByName(this.mContext, "layout", "drhw_xlogin_select_hor_item"), (ViewGroup) this.llParentHor, false);
                linearLayout = (LinearLayout) inflate.findViewById(Util.getIdByName(this.mContext, "id", "ll_login_hor"));
                imageView = (ImageView) inflate.findViewById(Util.getIdByName(this.mContext, "id", "iv_login_hor"));
                textView = (TextView) inflate.findViewById(Util.getIdByName(this.mContext, "id", "tv_login_hor"));
            } else {
                inflate = LayoutInflater.from(this.mContext).inflate(Util.getIdByName(this.mContext, "layout", "drhw_xlogin_select_ver_item"), (ViewGroup) this.llParentVer, false);
                linearLayout = (LinearLayout) inflate.findViewById(Util.getIdByName(this.mContext, "id", "ll_login_ver"));
                TextView textView2 = (TextView) inflate.findViewById(Util.getIdByName(this.mContext, "id", "tv_login_ver"));
                imageView = (ImageView) inflate.findViewById(Util.getIdByName(this.mContext, "id", "iv_login_ver"));
                textView = textView2;
            }
            final int loginType = this.loginTypeList.get(i).getLoginType();
            if (loginType == 0) {
                if (i < 3) {
                    linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, Util.getIdByName(this.mContext, "drawable", "drhw_shape_gray")));
                }
                textView.setText(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_login_sel_guest"));
                textView.setTextColor(ContextCompat.getColor(this.mContext, Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_color_c444444")));
                imageView.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_login_select_guest"));
            } else if (loginType != 1) {
                if (loginType != 2) {
                    if (loginType == 3) {
                        if (i < 3) {
                            linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, Util.getIdByName(this.mContext, "drawable", "drhw_shape_blue_light")));
                        }
                        imageView.setImageResource(Util.getIdByName(this.mContext, "drawable", "tw_toolbar_normalbtn"));
                        textView.setText("9 Ring");
                    } else if (loginType == 4) {
                        if (i < 3) {
                            linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, Util.getIdByName(this.mContext, "drawable", "drhw_shape_green")));
                        }
                        imageView.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_huawei_btn"));
                        textView.setText("HUAWEI");
                    }
                } else if (i < 3) {
                    linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, Util.getIdByName(this.mContext, "drawable", "drhw_shape_green")));
                    textView.setText(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_login_sel_google"));
                    imageView.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_login_select_google"));
                } else {
                    textView.setText(Constants.SDK_ACCOUNT_TYPE_GOOGLE);
                    imageView.setImageResource(Util.getIdByName(this.mContext, "drawable", "google_btn"));
                }
            } else if (i < 3) {
                linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, Util.getIdByName(this.mContext, "drawable", "drhw_shape_blue")));
                imageView.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_login_select_fb"));
                textView.setText(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_login_sel_fb"));
            } else {
                textView.setText(Constants.SDK_ACCOUNT_TYPE_FACEBOOK);
                imageView.setImageResource(Util.getIdByName(this.mContext, "drawable", "fb_btn"));
            }
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.newlogin.dialog.Lxhw_LoginSelectDialog.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Lxhw_LoginSelectDialog.this.login(loginType);
                }
            });
            if (i >= 3) {
                this.llParentHor.addView(inflate);
            } else {
                this.llParentVer.addView(inflate);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.fb_linearlayout) {
            fbLogin();
            return;
        }
        if (view == this.google_linearlayout) {
            googleLogin();
            return;
        }
        if (view == this.visitor_linearlayout) {
            visitorLogin();
            return;
        }
        if (view == this.huawei_linearlayout) {
            huaweiLogin();
        } else if (view == this.platform_linearlayout) {
            platformLogin();
        } else if (view == this.view_perform_click) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_PERFORM_CLICK, "1");
        }
    }

    private void fbLogin() {
        try {
            if (IsFastClickUtils.isFastClick()) {
                return;
            }
            LoadingDialog.showDialogForLoading(this.mContext);
            FaceBookControl.getInstance().loginFacebook(false);
            Lxhw_AreaPlatform.getInstance().onTrackEventConst(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_FACEBOOK_BUTTON_CLICK);
        } catch (Exception e) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_FACEBOOK_LOGIN_ERROR, "fb login throwable: " + e.toString());
            LLog.e_noControl("fb login try catch Throwable: " + e.getMessage());
        }
    }

    private void googleLogin() {
        try {
            if (IsFastClickUtils.isFastClick()) {
                return;
            }
            LoadingDialog.showDialogForLoading(this.mContext);
            Lxhw_DialogManage.getInstance().showGoogleLogin(this.mContext);
            Lxhw_AreaPlatform.getInstance().onTrackEventConst(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_BUTTON_CLICK);
        } catch (Exception e) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_LOGIN_ERROR, "google login throwable: " + e.toString());
            LLog.e_noControl("google login try catch Throwable: " + e.getMessage());
        }
    }

    private void huaweiLogin() {
        LoadingDialog.showDialogForLoading(this.mContext);
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_HUAWEI_BUTTON_CLICK);
        HuaweiControl.getInstance().login(this.mContext);
    }

    private void visitorLogin() {
        try {
            if (IsFastClickUtils.isFastClick()) {
                return;
            }
            LoadingDialog.showDialogForLoading(this.mContext);
            this.presenter.loginVisitor();
            Lxhw_AreaPlatform.getInstance().onTrackEventConst(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GUEST_BUTTON_CLICK);
        } catch (Exception e) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GUEST_LOGIN_ERROR, "guest login throwable: " + e.toString());
            LLog.e_noControl("guest login try catch Throwable: " + e.getMessage());
        }
    }

    private void platformLogin() {
        Lxhw_DialogManage.getInstance().showLogin(this.mContext);
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, Object obj) {
        LoadingDialog.cancelDialogForLoading();
        dismissAllowingStateLoss();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        LoadingDialog.cancelDialogForLoading();
        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.loginDialogIsClose = true;
        super.onDismiss(dialogInterface);
    }

    private void addVerLoginView() {
        final int i;
        this.llParentVer.removeAllViews();
        for (int i2 = 0; i2 < 3; i2++) {
            View inflate = LayoutInflater.from(this.mContext).inflate(Util.getIdByName(this.mContext, "layout", "drhw_xlogin_select_ver_item"), (ViewGroup) this.llParentVer, false);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(Util.getIdByName(this.mContext, "id", "ll_login_ver"));
            TextView textView = (TextView) inflate.findViewById(Util.getIdByName(this.mContext, "id", "tv_login_ver"));
            ImageView imageView = (ImageView) inflate.findViewById(Util.getIdByName(this.mContext, "id", "iv_login_ver"));
            if (i2 != 0) {
                i = 1;
                if (i2 != 1) {
                    i = 2;
                    if (i2 != 2) {
                        i = -1;
                    } else {
                        linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, Util.getIdByName(this.mContext, "drawable", "drhw_shape_green")));
                        textView.setText(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_login_sel_google"));
                        imageView.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_login_select_google"));
                    }
                } else {
                    linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, Util.getIdByName(this.mContext, "drawable", "drhw_shape_blue")));
                    textView.setText(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_login_sel_fb"));
                    imageView.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_login_select_fb"));
                }
            } else {
                linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, Util.getIdByName(this.mContext, "drawable", "drhw_shape_gray")));
                textView.setText(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_login_sel_guest"));
                textView.setTextColor(ContextCompat.getColor(this.mContext, Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_color_c444444")));
                imageView.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_login_select_guest"));
                i = 0;
            }
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.newlogin.dialog.Lxhw_LoginSelectDialog.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Lxhw_LoginSelectDialog.this.login(i);
                }
            });
            this.llParentVer.addView(inflate);
        }
    }

    private void addHorLoginView() {
        this.llParentHor.removeAllViews();
        this.llParentHor.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 2.0f));
        View inflate = LayoutInflater.from(this.mContext).inflate(Util.getIdByName(this.mContext, "layout", "drhw_xlogin_select_hor_item"), (ViewGroup) this.llParentHor, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(Util.getIdByName(this.mContext, "id", "ll_login_hor"));
        ImageView imageView = (ImageView) inflate.findViewById(Util.getIdByName(this.mContext, "id", "iv_login_hor"));
        TextView textView = (TextView) inflate.findViewById(Util.getIdByName(this.mContext, "id", "tv_login_hor"));
        imageView.setImageResource(Util.getIdByName(this.mContext, "drawable", "tw_toolbar_normalbtn"));
        textView.setText("9 Ring");
        final int i = 3;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.newlogin.dialog.Lxhw_LoginSelectDialog.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lxhw_LoginSelectDialog.this.login(i);
            }
        });
        this.llParentHor.addView(inflate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void login(int i) {
        if (i == 0) {
            visitorLogin();
            return;
        }
        if (i == 1) {
            fbLogin();
            return;
        }
        if (i == 2) {
            googleLogin();
        } else if (i == 3) {
            platformLogin();
        } else {
            if (i != 4) {
                return;
            }
            huaweiLogin();
        }
    }
}
