package com.oversea.ab_firstarea.dialog;

import android.app.Activity;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.oversea.ab_firstarea.dpresenter.PresenterEmailConfirm;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_EmailConfirmPresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_EmailConfirmView;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.IsFastClickUtils;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_EmailConfirmDialog extends Lxhw_BaseDialogFragment implements Lxhw_EmailConfirmView, View.OnClickListener {
    private CountDownTimer countDownTimer;
    private TextView hideformterm_tv;
    private CheckBox isAngreement;
    private Activity mContext;
    protected String mEmailaddress;
    protected String mEmailcode;
    private float mWith;
    private int msubmitType;
    private TextView platformterm_tv;
    private PresenterEmailConfirm presenter;
    private ImageView tw_close_dia_iv;
    private Button tw_confirm_btn;
    private TextView tw_get_verification_tv;
    private EditText tw_input_email_et;
    private EditText tw_input_verification_et;
    private String TAG = "EmailConfirmDialog";
    private String telArea = "";

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xemail_confirm_dialog";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.presenter = new Lxhw_EmailConfirmPresenterImpl(this.mContext, this);
        ImageView imageView = (ImageView) view.findViewById(getId("tw_sdk_back_iv"));
        this.tw_close_dia_iv = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_close_dia_iv, 15);
        this.tw_input_email_et = (EditText) view.findViewById(getId("tw_input_email_et"));
        this.tw_input_verification_et = (EditText) view.findViewById(getId("tw_input_verification_et"));
        this.tw_input_email_et.setLayerType(2, null);
        this.tw_input_verification_et.setLayerType(2, null);
        TextView textView = (TextView) view.findViewById(getId("tw_get_verification_tv"));
        this.tw_get_verification_tv = textView;
        textView.setOnClickListener(this);
        Button button = (Button) view.findViewById(getId("tw_confirm_btn"));
        this.tw_confirm_btn = button;
        button.setOnClickListener(this);
        CheckBox checkBox = (CheckBox) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_register_is_agree"));
        this.isAngreement = checkBox;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_EmailConfirmDialog.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            }
        });
        this.countDownTimer = new CountDownTimer(60000L, 1000L) { // from class: com.oversea.ab_firstarea.dialog.Lxhw_EmailConfirmDialog.2
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (Lxhw_EmailConfirmDialog.this.mContext != null) {
                    String string = Lxhw_EmailConfirmDialog.this.mContext.getString(Util.getIdByName(Lxhw_EmailConfirmDialog.this.mContext, TypedValues.Custom.S_STRING, "tw_send_second"));
                    Lxhw_EmailConfirmDialog.this.tw_get_verification_tv.setClickable(false);
                    Lxhw_EmailConfirmDialog.this.tw_get_verification_tv.setText(string + (j / 1000) + ExifInterface.LATITUDE_SOUTH);
                }
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (Lxhw_EmailConfirmDialog.this.mContext != null) {
                    Lxhw_EmailConfirmDialog.this.tw_get_verification_tv.setText(Lxhw_EmailConfirmDialog.this.mContext.getString(Util.getIdByName(Lxhw_EmailConfirmDialog.this.mContext, TypedValues.Custom.S_STRING, "tw_send_second")));
                    Lxhw_EmailConfirmDialog.this.tw_get_verification_tv.setTextColor(Lxhw_EmailConfirmDialog.this.mContext.getResources().getColor(Util.getIdByName(Lxhw_EmailConfirmDialog.this.mContext, TypedValues.Custom.S_COLOR, "tw_sdk_background_font_color")));
                    Lxhw_EmailConfirmDialog.this.tw_get_verification_tv.setClickable(true);
                }
            }
        };
        this.hideformterm_tv = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "hideformterm_tv"));
        this.platformterm_tv = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "platformterm_tv"));
        this.hideformterm_tv.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_EmailConfirmDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (InitBean.getInstance().getTerm_info() == null) {
                    Lxhw_DialogManage.getInstance().showBigTextCommon(Lxhw_EmailConfirmDialog.this.mContext, "2");
                } else if (TextUtils.isEmpty(InitBean.getInstance().getTerm_info().getTerm_privacy_url())) {
                    Lxhw_DialogManage.getInstance().showBigTextCommon(Lxhw_EmailConfirmDialog.this.mContext, "2");
                } else {
                    Lxhw_DialogManage.getInstance().showWebCommon(Lxhw_EmailConfirmDialog.this.mContext, InitBean.getInstance().getTerm_info().getTerm_privacy_url(), "");
                }
            }
        });
        this.platformterm_tv.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_EmailConfirmDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (InitBean.getInstance().getTerm_info() == null) {
                    Lxhw_DialogManage.getInstance().showBigTextCommon(Lxhw_EmailConfirmDialog.this.mContext, "1");
                } else if (TextUtils.isEmpty(InitBean.getInstance().getTerm_info().getTerm_service_url())) {
                    Lxhw_DialogManage.getInstance().showBigTextCommon(Lxhw_EmailConfirmDialog.this.mContext, "1");
                } else {
                    Lxhw_DialogManage.getInstance().showWebCommon(Lxhw_EmailConfirmDialog.this.mContext, InitBean.getInstance().getTerm_info().getTerm_service_url(), "");
                }
            }
        });
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, "custom_sdk_email_show");
    }

    private int getId(String str) {
        return Util.getIdByName(this.mContext, "id", str);
    }

    public void dismissDialog() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
        dismissAllowingStateLoss();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_close_dia_iv) {
            dismissDialog();
            return;
        }
        if (this.tw_get_verification_tv == view) {
            if (TextUtils.isEmpty(this.tw_input_email_et.getText())) {
                Activity activity = this.mContext;
                Toast.makeText(this.mContext, activity.getString(Util.getIdByName(activity, TypedValues.Custom.S_STRING, "tw_mail_add_nil")), 0).show();
                return;
            } else {
                if (IsFastClickUtils.isFastClick(Lxhw_Platform.getInstance().fastClickTime)) {
                    Log.e(this.TAG, "多次点击，返回...................");
                    return;
                }
                this.mEmailaddress = this.tw_input_email_et.getText().toString().trim();
                this.msubmitType = 1;
                this.tw_get_verification_tv.setClickable(false);
                this.tw_get_verification_tv.setTextColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_hint_text_black")));
                this.presenter.doBindEmailSendCode(this.mEmailaddress);
                return;
            }
        }
        if (this.tw_confirm_btn == view) {
            if (!this.isAngreement.isChecked()) {
                Activity activity2 = this.mContext;
                Toast.makeText(this.mContext, activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "tw_aggre_auth")), 0).show();
            } else if (TextUtils.isEmpty(this.tw_input_verification_et.getText())) {
                Activity activity3 = this.mContext;
                Toast.makeText(this.mContext, activity3.getString(Util.getIdByName(activity3, TypedValues.Custom.S_STRING, "tw_check_code_nil")), 0).show();
            } else {
                this.presenter.doBindEmail(this.tw_input_email_et.getText().toString().trim(), this.tw_input_verification_et.getText().toString().trim());
                Lxhw_DialogManage.getInstance().showDialog();
            }
        }
    }

    public void startCountDown() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer == null) {
            return;
        }
        countDownTimer.start();
        this.tw_get_verification_tv.setTextColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_hint_text_black")));
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, String str2) {
        Lxhw_DialogManage.getInstance().removeFragment(this.mContext, "UserCenterDialog");
        AreaSdk.getInstance().userBindResult();
        dismissDialog();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
        this.tw_get_verification_tv.setClickable(true);
        this.tw_get_verification_tv.setTextColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_sdk_background_font_color")));
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_EmailConfirmView
    public void onReqCodeSuccess() {
        startCountDown();
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
