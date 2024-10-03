package com.oversea.ab_firstarea.dialog;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.appevents.integrity.IntegrityManager;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;
import com.xsdk.ab_firstbase.statisics.util.DatePickerDialogUtil;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.IsFastClickUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/* loaded from: classes.dex */
public class UpData_UserInfo_VI extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    EditText address_et;
    TextView birthday_tv;
    EditText email_et;
    RadioButton female_id;
    EditText fullname_et;
    TextView issued_on_tv;
    EditText issued_place_et;
    private DatePickerDialogUtil mDatePickerDialog;
    protected String mInitDateTimeEnd;
    protected String mInitDateTimeStart;
    RadioButton male_id;
    RadioButton not_specified_id;
    EditText number_et;
    EditText phont_et;
    Button submit_btn;
    TextView tv_later;
    ScrollView userinfo_lv;
    String TAG = "userifovi";
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xupdata_userinfo_vi";
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
            i = displayMetrics.heightPixels;
            i2 = (int) (displayMetrics.heightPixels * 0.95d);
        } else {
            i = (int) (displayMetrics.widthPixels * 0.95d);
            i2 = -2;
        }
        getDialog().getWindow().setLayout(i, i2);
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        setCancelable(false);
        this.email_et = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "email_et"));
        this.phont_et = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "phont_et"));
        this.fullname_et = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "fullname_et"));
        TextView textView = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "birthday_tv"));
        this.birthday_tv = textView;
        textView.setOnClickListener(this);
        this.address_et = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "address_et"));
        this.number_et = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "number_et"));
        TextView textView2 = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "issued_on_tv"));
        this.issued_on_tv = textView2;
        textView2.setOnClickListener(this);
        this.issued_place_et = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "issued_place_et"));
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "submit_btn"));
        this.submit_btn = button;
        button.setOnClickListener(this);
        RadioButton radioButton = (RadioButton) view.findViewById(Util.getIdByName(this.mContext, "id", "male_id"));
        this.male_id = radioButton;
        radioButton.setOnClickListener(this);
        RadioButton radioButton2 = (RadioButton) view.findViewById(Util.getIdByName(this.mContext, "id", "female_id"));
        this.female_id = radioButton2;
        radioButton2.setOnClickListener(this);
        TextView textView3 = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tv_later"));
        this.tv_later = textView3;
        textView3.getPaint().setFlags(8);
        this.tv_later.getPaint().setAntiAlias(true);
        this.mInitDateTimeStart = this.sdf.format(Long.valueOf(System.currentTimeMillis()));
        this.mInitDateTimeEnd = this.sdf.format(Long.valueOf(System.currentTimeMillis()));
        this.mDatePickerDialog = new DatePickerDialogUtil(this.mContext, this.mInitDateTimeStart);
        if (InitBean.getInstance().getGame_info() != null && InitBean.getInstance().getGame_info().getCertification_switch() == 2) {
            this.tv_later.setVisibility(0);
            this.tv_later.setOnClickListener(this);
        } else {
            this.tv_later.setVisibility(8);
        }
    }

    private void showData(final TextView textView) {
        DatePickerDialogUtil datePickerDialogUtil = this.mDatePickerDialog;
        if (datePickerDialogUtil == null || textView == null || datePickerDialogUtil.isShow) {
            return;
        }
        this.mDatePickerDialog.isShow = true;
        this.mContext.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.UpData_UserInfo_VI.1
            @Override // java.lang.Runnable
            public void run() {
                UpData_UserInfo_VI.this.mDatePickerDialog.datePickerDialog(textView);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.submit_btn) {
            if (IsFastClickUtils.isFastClick(Lxhw_Platform.getInstance().fastClickTime)) {
                Log.e(this.TAG, "多次点击，返回...................");
                return;
            }
            String trim = this.email_et.getText().toString().trim();
            String trim2 = this.phont_et.getText().toString().trim();
            String trim3 = this.fullname_et.getText().toString().trim();
            String trim4 = this.birthday_tv.getText().toString().trim();
            String trim5 = this.address_et.getText().toString().trim();
            String trim6 = this.number_et.getText().toString().trim();
            String trim7 = this.issued_on_tv.getText().toString().trim();
            String trim8 = this.issued_place_et.getText().toString().trim();
            this.male_id.isChecked();
            String str = this.female_id.isChecked() ? "Female" : "Male";
            if (TextUtils.isEmpty(trim3) || TextUtils.isEmpty(trim5) || TextUtils.isEmpty(trim) || TextUtils.isEmpty(trim2) || TextUtils.isEmpty(trim6) || TextUtils.isEmpty(trim4) || TextUtils.isEmpty(trim8) || TextUtils.isEmpty(trim7)) {
                ToastUtils.toastLongShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "vi_empty_tips")));
                return;
            }
            if (!Util.isEmail(trim)) {
                ToastUtils.toastLongShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "vi_input_valid_email")));
                return;
            }
            if (trim2.length() != 10) {
                ToastUtils.toastLongShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "vi_input_valid_phone")));
                return;
            }
            int length = trim6.length();
            if (length != 9 && length != 12) {
                ToastUtils.toastLongShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "vi_input_id_number")));
                return;
            }
            if (!iOverEighteen(trim4)) {
                ToastUtils.toastLongShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "vi_underage")));
                return;
            }
            HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
            Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("user_info_certification");
            createCommonParams.put("email", trim);
            createCommonParams.put("phone", trim2);
            createCommonParams.put("full_name", trim3);
            createCommonParams.put("gender", str);
            createCommonParams.put("birthday", trim4);
            createCommonParams.put(IntegrityManager.INTEGRITY_TYPE_ADDRESS, trim5);
            createCommonParams.put("id_number", trim6);
            createCommonParams.put("issued_on", trim7);
            createCommonParams.put("issued_place", trim8);
            httpParamsCommon.addSign(createCommonParams, AreaBaseService.USER_INFO_CERTIFCATION_VI_ROUTE);
            Lxhw_DialogManage.getInstance().showDialog();
            HttpRequestCenter.getInstance().doRequestCommonAddHead("user_info_certification", createCommonParams, AreaBaseService.USER_INFO_CERTIFCATION_VI_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dialog.UpData_UserInfo_VI.2
                @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
                public void httpRequestCallBackListener(String str2) {
                    try {
                        Lxhw_DialogManage.getInstance().cancelDialog();
                        if (!TextUtils.isEmpty(str2)) {
                            BaseBean baseBean = new BaseBean();
                            LLog.v_noControl("updata_userinfo_vi code=" + baseBean.getCode());
                            if (baseBean.getCode() == 0) {
                                ToastUtils.toastShow(UpData_UserInfo_VI.this.mContext, "Đăng ký thàng công");
                                UpData_UserInfo_VI.this.dismissAllowingStateLoss();
                            } else {
                                ToastUtils.toastShow(UpData_UserInfo_VI.this.mContext, baseBean.getMessage());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
                public void httpRequestFail(BaseBean baseBean) {
                    Lxhw_DialogManage.getInstance().cancelDialog();
                }
            });
            return;
        }
        TextView textView = this.birthday_tv;
        if (view == textView) {
            showData(textView);
            return;
        }
        TextView textView2 = this.issued_on_tv;
        if (view == textView2) {
            showData(textView2);
        } else if (view == this.tv_later) {
            saveTime();
            dismissAllowingStateLoss();
        }
    }

    private static boolean iOverEighteen(String str) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            Date parse = simpleDateFormat.parse(str);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(parse);
            Integer valueOf = Integer.valueOf(calendar.get(1) - calendar2.get(1));
            if (calendar.get(6) < calendar2.get(6)) {
                valueOf = Integer.valueOf(valueOf.intValue() - 1);
            }
            return valueOf.intValue() >= 18;
        } catch (Exception e) {
            Log.v("iOverEighteen", " e=" + e.toString());
            return false;
        }
    }

    private void saveTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        long timeInMillis = calendar.getTimeInMillis();
        ImageUtils.setSharePreferences(this.mContext, "SDK_AUTH_TIME_SHOW_" + Lxhw_XUserInfo.getInstance().getSdkId(), timeInMillis + "");
    }
}
