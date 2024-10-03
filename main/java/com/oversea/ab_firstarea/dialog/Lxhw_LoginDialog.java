package com.oversea.ab_firstarea.dialog;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.oversea.ab_firstarea.channel.ProjectType;
import com.oversea.ab_firstarea.dpresenter.PresenterLogin;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_LoginPresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_LoginView;
import com.oversea.ab_firstarea.haiwai.FaceBookControl;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.model.LoginInfo;
import com.oversea.ab_firstplatform.model.LoginInfoManage;
import com.xsdk.ab_firstbase.loading.LoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.IsFastClickUtils;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;
import java.util.List;

/* loaded from: classes.dex */
public class Lxhw_LoginDialog extends Lxhw_BaseDialogFragment implements Lxhw_LoginView, View.OnClickListener {
    private static final String TAG = "LoginDialog";
    private static List<LoginInfo> mLoginInfoList;
    private LinearLayout fb_linearlayout;
    private LinearLayout google_linearlayout;
    private ImageView img_sdk_member;
    private TextView img_sdk_member_tv;
    private EditText mAccount;
    private String mAccountText;
    private LoginHistoryAdapter mAdapter;
    private ImageView mDeletePassword;
    private LinearLayout mLayout;
    private ViewGroup.LayoutParams mLayoutParams;
    private ListView mListView;
    private Button mLoginButton;
    private ImageView mLoginMore;
    private EditText mPassword;
    private String mPasswordText;
    private PopupWindow pop;
    private PresenterLogin presenter;
    private LinearLayout scrollToInput;
    private CheckBox select_remember;
    private Button tw_account_register_account;
    private ImageView tw_fb_btn;
    private ImageView tw_forgetpwd;
    private ImageView tw_google_btn;
    private ImageView tw_sdk_back_iv;
    private ImageView tw_visitor_btn;
    private View view_perform_click;
    private LinearLayout visitor_linearlayout;
    private boolean isInput = false;
    private boolean isLogining = false;
    private String mCurrPassword = "";
    boolean isremember = false;
    private boolean loginDialogIsClose = false;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xlogin_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        if (!Lxhw_AreaPlatform.getInstance().isUseNewSdkLogin) {
            setCancelable(false);
        }
        this.presenter = new Lxhw_LoginPresenterImpl(this.mContext, this);
        this.mAccount = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_login_account"));
        this.mPassword = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_login_password"));
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_login_log"));
        this.mLoginButton = button;
        button.setOnClickListener(this);
        View findViewById = view.findViewById(Util.getIdByName(this.mContext, "id", "view_perform_click"));
        this.view_perform_click = findViewById;
        findViewById.setOnClickListener(this);
        new Handler().postDelayed(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_LoginDialog.1
            @Override // java.lang.Runnable
            public void run() {
                if (Lxhw_LoginDialog.this.loginDialogIsClose) {
                    return;
                }
                Lxhw_LoginDialog.this.view_perform_click.performClick();
            }
        }, 3000L);
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
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_login_more"));
        this.mLoginMore = imageView;
        imageView.setOnClickListener(this);
        this.mLayout = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_layout"));
        this.mAccount.setText(this.mAccountText);
        this.mPassword.setText(this.mPasswordText);
        ImageView imageView2 = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_login_del_password"));
        this.mDeletePassword = imageView2;
        imageView2.setOnClickListener(this);
        this.mCurrPassword = this.mPassword.getText().toString();
        this.tw_forgetpwd = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_forgetpwd"));
        this.select_remember = (CheckBox) view.findViewById(Util.getIdByName(this.mContext, "id", "select_remember"));
        boolean booleanValue = ImageUtils.getStringKeyForBoolValue(this.mContext, Constants.SDK_IS_REMEMBER_PASSWORD, true).booleanValue();
        this.isremember = booleanValue;
        this.select_remember.setChecked(booleanValue);
        this.tw_account_register_account = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_register_account"));
        this.tw_fb_btn = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_fb_btn"));
        this.tw_google_btn = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_google_btn"));
        this.tw_visitor_btn = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_visitor_btn"));
        this.fb_linearlayout = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "fb_linearlayout"));
        this.google_linearlayout = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "google_linearlayout"));
        this.visitor_linearlayout = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "visitor_linearlayout"));
        if (InitBean.getInstance().getAndroid_third_login_switch_config() != null) {
            if (InitBean.getInstance().getAndroid_third_login_switch_config().getFacebook_switch() == 0 || Lxhw_AreaPlatform.getInstance().isUseNewSdkLogin) {
                this.fb_linearlayout.setVisibility(8);
            }
            if (InitBean.getInstance().getAndroid_third_login_switch_config().getGoogle_swtich() == 0 || Lxhw_AreaPlatform.getInstance().isUseNewSdkLogin) {
                this.google_linearlayout.setVisibility(8);
            }
            if (InitBean.getInstance().getAndroid_third_login_switch_config().getGuest_switch() == 0 || Lxhw_AreaPlatform.getInstance().isUseNewSdkLogin) {
                this.visitor_linearlayout.setVisibility(8);
            }
        }
        this.tw_forgetpwd.setOnClickListener(this);
        ImageView imageView3 = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView3;
        if (imageView3 != null && Lxhw_AreaPlatform.getInstance().isUseNewSdkLogin) {
            this.tw_sdk_back_iv.setVisibility(0);
            this.tw_sdk_back_iv.setOnClickListener(this);
        }
        this.tw_account_register_account.setOnClickListener(this);
        this.fb_linearlayout.setOnClickListener(this);
        this.google_linearlayout.setOnClickListener(this);
        this.visitor_linearlayout.setOnClickListener(this);
        this.scrollToInput = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_pwd_layout"));
        mLoginInfoList = LoginInfoManage.getInstance().getLoginInfos(this.mContext);
        LoginInfoManage.getInstance();
        LoginInfo lastLoginInfo = LoginInfoManage.getLastLoginInfo(this.mContext, mLoginInfoList);
        setAccountText(lastLoginInfo.getU());
        setPasswordText(lastLoginInfo.getP());
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_SDK_LOGIN_SHOW);
    }

    public static void setList(List<LoginInfo> list) {
        mLoginInfoList = list;
    }

    public void setViewSize(ViewGroup.LayoutParams layoutParams) {
        this.mLayoutParams = layoutParams;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mLoginButton) {
            try {
                if (IsFastClickUtils.isFastClick()) {
                    return;
                }
                this.isLogining = true;
                String obj = this.mAccount.getText().toString();
                String obj2 = this.mPassword.getText().toString();
                closePopWindow();
                try {
                    Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(this.mContext, ComConfig.PC_FF_LOGIN_BUTTON_CLICK, "custom_sdk_login_button_click:" + obj);
                } catch (Exception unused) {
                }
                if (checkLoginInputText(obj, obj2)) {
                    LoadingDialog.showDialogForLoading(this.mContext);
                    this.presenter.doLogin(this.select_remember.isChecked(), obj, obj2);
                    return;
                } else {
                    this.isLogining = false;
                    return;
                }
            } catch (Throwable th) {
                Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(this.mContext, ComConfig.PC_FF_ACCOUNT_LOGIN_ERROR, "account login throwable: " + th.toString());
                Log.d(TAG, "account login try catch Throwable: " + th.toString());
                return;
            }
        }
        if (view == this.mLoginMore) {
            clickLoginMore();
            return;
        }
        if (view == this.mDeletePassword) {
            this.mPassword.setText("");
            this.mDeletePassword.setVisibility(8);
            return;
        }
        if (view == this.tw_forgetpwd) {
            this.presenter.jumpForgetPwdCenter();
            return;
        }
        if (view == this.tw_account_register_account) {
            Log.i(TAG, "onClick tw_account_register_account");
            Lxhw_DialogManage.getInstance().showRegister(this.mContext);
            Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_REGISTER_BUTTON_CLICK);
            return;
        }
        if (view == this.fb_linearlayout) {
            try {
                if (IsFastClickUtils.isFastClick()) {
                    return;
                }
                LoadingDialog.showDialogForLoading(this.mContext);
                FaceBookControl.getInstance().loginFacebook(false);
                Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_FACEBOOK_BUTTON_CLICK);
                return;
            } catch (Throwable th2) {
                Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(this.mContext, ComConfig.PC_FF_FACEBOOK_LOGIN_ERROR, "fb login throwable: " + th2.toString());
                Log.d(TAG, "fb login try catch Throwable: " + th2.toString());
                return;
            }
        }
        if (view == this.google_linearlayout) {
            Log.i(TAG, "onClick tw_google_btn");
            try {
                if (IsFastClickUtils.isFastClick()) {
                    return;
                }
                LoadingDialog.showDialogForLoading(this.mContext);
                Lxhw_DialogManage.getInstance().showGoogleLogin(this.mContext);
                Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_GOOGLE_BUTTON_CLICK);
                return;
            } catch (Exception e) {
                Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(this.mContext, ComConfig.PC_FF_GOOGLE_LOGIN_ERROR, "google login throwable: " + e.toString());
                Log.d(TAG, "google login try catch Throwable: " + e.toString());
                return;
            }
        }
        if (view == this.visitor_linearlayout) {
            Log.i(TAG, "onClick tw_visitor_btn");
            try {
                if (IsFastClickUtils.isFastClick()) {
                    return;
                }
                LoadingDialog.showDialogForLoading(this.mContext);
                this.presenter.loginVisitor();
                Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_GUEST_BUTTON_CLICK);
                return;
            } catch (Throwable th3) {
                Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(this.mContext, ComConfig.PC_FF_GUEST_LOGIN_ERROR, "guest login throwable: " + th3.toString());
                Log.d(TAG, "guest login try catch Throwable: " + th3.toString());
                return;
            }
        }
        if (view == this.view_perform_click) {
            Log.i(TAG, "onClick view_perform_click");
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(this.mContext, ComConfig.PC_FF_PERFORM_CLICK, "1");
        } else if (view == this.tw_sdk_back_iv) {
            dismissAllowingStateLoss();
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    private void clickLoginMore() {
        List<LoginInfo> list = mLoginInfoList;
        if (list == null || list.size() < 1) {
            return;
        }
        PopupWindow popupWindow = this.pop;
        if (popupWindow == null) {
            if (this.mAdapter == null) {
                this.mAdapter = new LoginHistoryAdapter();
                ListView listView = new ListView(this.mContext);
                this.mListView = listView;
                listView.setDividerHeight(1);
                this.mListView.setDivider(this.mContext.getApplicationContext().getResources().getDrawable(Util.getIdByName(this.mContext, "drawable", "drhw_divider")));
                this.pop = new PopupWindow(this.mListView, this.mLayout.getWidth(), -2);
                this.mListView.setAdapter((ListAdapter) this.mAdapter);
                this.pop.setFocusable(true);
                this.pop.setOutsideTouchable(true);
                this.pop.showAsDropDown(this.mLayout);
                return;
            }
            return;
        }
        if (popupWindow.isShowing()) {
            this.pop.dismiss();
        } else {
            if (this.pop.isShowing()) {
                return;
            }
            this.pop.showAsDropDown(this.mLayout);
        }
    }

    public boolean checkLoginInputText(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_account_input_hint")), 0).show();
            return false;
        }
        if (!TextUtils.isEmpty(str2)) {
            return true;
        }
        Toast.makeText(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_password_input_hint")), 0).show();
        return false;
    }

    public void setAccountText(String str) {
        if (str == null) {
            return;
        }
        this.mAccountText = str;
        EditText editText = this.mAccount;
        if (editText != null) {
            editText.setText(str);
        }
    }

    public void setPasswordText(String str) {
        if (str == null) {
            return;
        }
        this.mPasswordText = str;
        EditText editText = this.mPassword;
        if (editText != null) {
            editText.setText(str);
        }
    }

    public void setIsInput(boolean z) {
        this.isInput = z;
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, Object obj) {
        LoadingDialog.cancelDialogForLoading();
        dismiss();
        if (Lxhw_AreaPlatform.getInstance().isUseNewSdkLogin) {
            Lxhw_DialogManage.getInstance().removeFragment(Lxhw_XSDK.getInstance().getContext(), "loginSelectDialog");
        }
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        LoadingDialog.cancelDialogForLoading();
        ToastUtils.toastLongShow(this.mContext, ComUtil.getBaseBeanTip(baseBean));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class LoginHistoryAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public LoginHistoryAdapter() {
            this.mInflater = (LayoutInflater) Lxhw_LoginDialog.this.mContext.getSystemService("layout_inflater");
            Log.i(Lxhw_LoginDialog.TAG, "mLoginInfoList size:" + Lxhw_LoginDialog.mLoginInfoList.size());
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return Lxhw_LoginDialog.mLoginInfoList.size();
        }

        @Override // android.widget.Adapter
        public View getView(final int i, View view, ViewGroup viewGroup) {
            Holder holder;
            Log.i(Lxhw_LoginDialog.TAG, "getView position:" + i);
            final String u = ((LoginInfo) Lxhw_LoginDialog.mLoginInfoList.get(i)).getU();
            final String p = ((LoginInfo) Lxhw_LoginDialog.mLoginInfoList.get(i)).getP();
            if (view == null) {
                view = this.mInflater.inflate(Util.getIdByName(Lxhw_LoginDialog.this.mContext, "layout", "drhw_xlogin_history_popup"), (ViewGroup) null);
                holder = new Holder();
                holder.view = (TextView) view.findViewById(Util.getIdByName(Lxhw_LoginDialog.this.mContext, "id", "tw_history_account"));
                holder.image = (ImageView) view.findViewById(Util.getIdByName(Lxhw_LoginDialog.this.mContext, "id", "tw_account_is_select"));
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            if (holder != null) {
                view.setId(i);
                holder.setId(i);
                holder.view.setText(u);
                holder.view.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_LoginDialog.LoginHistoryAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Lxhw_LoginDialog.this.pop.dismiss();
                        Lxhw_LoginDialog.this.mAccount.setText(u);
                        Lxhw_LoginDialog.this.mPassword.setText(p);
                        Lxhw_LoginDialog.this.mCurrPassword = p;
                        Lxhw_LoginDialog.this.mAdapter.notifyDataSetChanged();
                    }
                });
                holder.image.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_LoginDialog.LoginHistoryAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Lxhw_LoginDialog.this.pop.dismiss();
                        if (Lxhw_LoginDialog.mLoginInfoList != null && Lxhw_LoginDialog.mLoginInfoList.size() > 0) {
                            String stringKeyForValue = ImageUtils.getStringKeyForValue(Lxhw_LoginDialog.this.mContext, Constants.SDK_ACCOUNT);
                            if (!TextUtils.isEmpty(stringKeyForValue) && ((LoginInfo) Lxhw_LoginDialog.mLoginInfoList.get(i)).getU().equals(stringKeyForValue)) {
                                ImageUtils.setSharePreferences(Lxhw_LoginDialog.this.mContext, Constants.SDK_ACCOUNT, "");
                                ImageUtils.setSharePreferences(Lxhw_LoginDialog.this.mContext, Constants.SDK_PASSWORD, "");
                                ImageUtils.setSharePreferences(Lxhw_LoginDialog.this.mContext, Constants.SDK_LOGIN_TYPE, Constants.SDK_LOGIN_TYPE_LOGOUT);
                            }
                            if (((LoginInfo) Lxhw_LoginDialog.mLoginInfoList.get(i)).getU().equals(Lxhw_LoginDialog.this.mAccount.getText().toString())) {
                                Lxhw_LoginDialog.this.mAccount.setText("");
                                Lxhw_LoginDialog.this.mPassword.setText("");
                            }
                            Lxhw_LoginDialog.mLoginInfoList.remove(i);
                            LoginInfoManage.getInstance().setLoginInfos(Lxhw_LoginDialog.this.mContext, Lxhw_LoginDialog.mLoginInfoList);
                        }
                        Lxhw_LoginDialog.this.mAdapter.notifyDataSetChanged();
                    }
                });
            }
            return view;
        }

        /* loaded from: classes.dex */
        class Holder {
            ImageView image;
            TextView view;

            Holder() {
            }

            void setId(int i) {
                this.view.setId(i);
                this.image.setId(i);
            }
        }
    }

    @Override // android.app.DialogFragment
    public void dismiss() {
        this.loginDialogIsClose = true;
        closePopWindow();
        super.dismiss();
    }

    private void closePopWindow() {
        PopupWindow popupWindow = this.pop;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.pop.dismiss();
        this.pop = null;
    }
}
