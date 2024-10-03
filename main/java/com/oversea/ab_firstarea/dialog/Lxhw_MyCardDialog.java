package com.oversea.ab_firstarea.dialog;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.facebook.share.internal.ShareConstants;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.loading.LoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.io.IOException;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Lxhw_MyCardDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    private EditText mAccount;
    private Button mLoginButton;
    private EditText mPassword;
    private String orderNum;
    private String productId;
    private ImageView tw_sdk_back_iv;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xmycard_layout";
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
            i = (int) (displayMetrics.widthPixels * 0.95d);
            i2 = (int) (displayMetrics.widthPixels * 0.85d);
        }
        getDialog().getWindow().setLayout(i, i2);
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        setCancelable(false);
        if (getArguments() != null) {
            this.orderNum = getArguments().getString("order_number");
            this.productId = getArguments().getString("product_id");
        }
        this.mAccount = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_mycard_account"));
        this.mPassword = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_mycard_password"));
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_submit"));
        this.mLoginButton = button;
        button.setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_sdk_back_iv) {
            dismissAllowingStateLoss();
            return;
        }
        if (view == this.mLoginButton) {
            if (TextUtils.isEmpty(this.mAccount.getText().toString())) {
                ToastUtils.toastShow(this.mContext, this.mAccount.getHint().toString());
            } else if (TextUtils.isEmpty(this.mPassword.getText().toString())) {
                ToastUtils.toastShow(this.mContext, this.mPassword.getHint().toString());
            } else {
                myCardPay(this.mAccount.getText().toString(), this.mPassword.getText().toString());
            }
        }
    }

    private void myCardPay(String str, String str2) {
        LoadingDialog.showDialogForLoading(this.mContext);
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("pay_order_mycard_ingame_pay");
        createCommonParams.put("order_number", this.orderNum);
        createCommonParams.put("card_pin", str);
        createCommonParams.put("card_seri", str2);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.PAYORDER_MYCARD_CREATE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("pay_order_mycard_ingame_pay", createCommonParams, AreaBaseService.PAYORDER_MYCARD_CREATE, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_MyCardDialog.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    LoadingDialog.cancelDialogForLoading();
                    JSONObject jSONObject = new JSONObject(str3);
                    if (jSONObject.isNull("code")) {
                        return;
                    }
                    if (jSONObject.getInt("code") == 0) {
                        if (jSONObject.isNull("data")) {
                            return;
                        }
                        JSONObject jSONObject2 = new JSONObject(jSONObject.getString("data"));
                        if (!jSONObject2.isNull("sync_pay_notify_url")) {
                            String string = jSONObject2.getString("sync_pay_notify_url");
                            LLog.i_Control("sync_pay_notify_url:" + string);
                            Lxhw_MyCardDialog.this.notifyPay(string);
                        }
                        Lxhw_XSDK.getInstance().onResult(16, "pay success");
                        Lxhw_MyCardDialog lxhw_MyCardDialog = Lxhw_MyCardDialog.this;
                        lxhw_MyCardDialog.uploadPurchase(lxhw_MyCardDialog.productId);
                        Lxhw_MyCardDialog.this.dismissAllowingStateLoss();
                        return;
                    }
                    ToastUtils.toastShow(Lxhw_MyCardDialog.this.mContext, jSONObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                LoadingDialog.cancelDialogForLoading();
                LLog.v_noControl("getMyCardPayUrl fail code=" + baseBean.getCode() + " " + baseBean.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyPay(final String str) {
        new Thread(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_MyCardDialog.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Response execute = new OkHttpClient().newCall(new Request.Builder().url(str).get().build()).execute();
                    if (execute.isSuccessful()) {
                        LLog.i_Control("notifyPay:" + execute.body().string());
                    } else {
                        LLog.e_Control("notifyPay:" + execute.code() + execute.message());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadPurchase(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), "custom_sdk_" + str.replace(".", "_"), "1");
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), "custom_sdk_all_recharge", "1");
        } catch (Exception unused) {
        }
    }
}
