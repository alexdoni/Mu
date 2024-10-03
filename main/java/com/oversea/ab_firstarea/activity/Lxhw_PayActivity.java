package com.oversea.ab_firstarea.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.DealPay;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;
import com.xsdk.ab_firstbase.statisics.util.AESUtil;
import com.xsdk.ab_firstbase.statisics.util.Base64;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.Util;
import com.xsdk.ab_firstbase.statisics.util.languagelib.MultiLanguageUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Lxhw_PayActivity extends Activity {
    private boolean isUpload;
    private ImageView mBackBtn;
    private ImageView mCloseBtn;
    private Context mContext;
    private Lxhw_PayParams mParams;
    private WebView mPayWebView;
    private String returnMsg;
    private String mUrl = "";
    private final int SUCCESS = 0;
    private final int FAIL = 1;
    private final int GOOGLEPLAY = 2;
    private Lxhw_PayActivity instance = null;
    Map<String, Object> params = new HashMap();
    private Handler mHandler = new Handler();
    private Runnable runnable = new Runnable() { // from class: com.oversea.ab_firstarea.activity.Lxhw_PayActivity.7
        @Override // java.lang.Runnable
        public void run() {
            Lxhw_PayActivity.this.queryPay();
            Lxhw_PayActivity.this.mHandler.postDelayed(Lxhw_PayActivity.this.runnable, 1500L);
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        setContentView(Util.getIdByName(this, "layout", "drhw_activity_pay_layout"));
        ImageView imageView = (ImageView) findViewById(Util.getIdByName(this, "id", "tw_iv_close_pay_way"));
        this.mCloseBtn = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.activity.Lxhw_PayActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lxhw_PayActivity.this.finish();
            }
        });
        this.mCloseBtn.setVisibility(0);
        ImageView imageView2 = (ImageView) findViewById(Util.getIdByName(this, "id", "tw_iv_back_pay_way"));
        this.mBackBtn = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.activity.Lxhw_PayActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lxhw_PayActivity.this.mPayWebView != null) {
                    Lxhw_PayActivity.this.mPayWebView.goBack();
                }
            }
        });
        this.instance = this;
        initWebView();
    }

    private void initWebView() {
        WebView webView = (WebView) findViewById(Util.getIdByName(this, "id", "pay_web_view_id"));
        this.mPayWebView = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        this.mPayWebView.requestFocus();
        this.mPayWebView.setVerticalScrollbarOverlay(true);
        this.mPayWebView.setScrollBarStyle(0);
        this.mPayWebView.getSettings().setUseWideViewPort(true);
        this.mPayWebView.getSettings().setSavePassword(false);
        this.mPayWebView.getSettings().setSaveFormData(false);
        this.mPayWebView.getSettings().setCacheMode(2);
        this.mPayWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.mPayWebView.getSettings().setJavaScriptEnabled(true);
        this.mPayWebView.getSettings().setDomStorageEnabled(true);
        this.mPayWebView.setWebViewClient(new WebViewClient() { // from class: com.oversea.ab_firstarea.activity.Lxhw_PayActivity.3
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                try {
                    if (!str.startsWith("gojek:") && !str.startsWith("line:")) {
                        if (!str.startsWith("http:") && !str.startsWith("https:")) {
                            if (str.startsWith("intent://")) {
                                Lxhw_PayActivity.this.startActivity(Intent.parseUri(str, 1));
                            } else {
                                Lxhw_PayActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                            }
                            return true;
                        }
                        webView2.loadUrl(str);
                        return true;
                    }
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                        intent.addFlags(268435456);
                        Lxhw_PayActivity.this.startActivity(intent);
                    } catch (Throwable th) {
                        th.getMessage();
                    }
                    return true;
                } catch (Exception unused) {
                    return false;
                }
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                super.onPageFinished(webView2, str);
                if (Lxhw_PayActivity.this.mPayWebView.getProgress() == 100) {
                    Lxhw_AreaPlatform.getInstance().onTrackEventConst(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_SDK_PAY_WEB_LOAD_FINISH);
                }
                if (!Lxhw_PayActivity.this.isUpload && Lxhw_XUserInfo.getInstance().isEvent_status()) {
                    Lxhw_PayActivity.this.checkLoop();
                    Lxhw_PayActivity.this.isUpload = true;
                }
                if (TextUtils.isEmpty(str) || !str.contains("pay-result?") || Lxhw_PayActivity.this.getIntent().getBundleExtra("bundle") == null || TextUtils.isEmpty(Lxhw_PayActivity.this.getIntent().getBundleExtra("bundle").getString("payMethod"))) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.oversea.ab_firstarea.activity.Lxhw_PayActivity.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Lxhw_PayActivity.this.finish();
                    }
                }, 2000L);
            }
        });
        this.mPayWebView.setWebChromeClient(new WebChromeClient() { // from class: com.oversea.ab_firstarea.activity.Lxhw_PayActivity.4
            @Override // android.webkit.WebChromeClient
            public void onCloseWindow(WebView webView2) {
                super.onCloseWindow(webView2);
            }

            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView2, int i) {
                super.onProgressChanged(webView2, i);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView webView2, String str, String str2, JsResult jsResult) {
                return super.onJsAlert(webView2, str, str2, jsResult);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsConfirm(WebView webView2, String str, String str2, JsResult jsResult) {
                return super.onJsConfirm(webView2, str, str2, jsResult);
            }
        });
        String string = getIntent().getBundleExtra("bundle") != null ? getIntent().getBundleExtra("bundle").getString("payMethod") : "";
        if (!TextUtils.isEmpty(string) && string.startsWith("http")) {
            this.mUrl = getIntent().getBundleExtra("bundle").getString("payMethod");
        } else {
            zuhecanshu();
        }
        LLog.i_Control("pay_url: " + this.mUrl);
        runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.activity.Lxhw_PayActivity.5
            @Override // java.lang.Runnable
            public void run() {
                LLog.i_Control(Lxhw_PayActivity.this.mUrl);
                Lxhw_PayActivity.this.mPayWebView.loadUrl(Lxhw_PayActivity.this.mUrl);
            }
        });
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_SDK_PAY_WEB_SHOW);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        removeLoop();
        Lxhw_XSDK.getInstance().onResult(19, "pay web close");
    }

    public void zuhecanshu() {
        this.params.clear();
        commonParameter();
        this.mUrl = AreaBaseService.PAYPLATFORMURL + "?" + getCommonParamsString();
    }

    public void commonParameter() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("game_id", Integer.valueOf(Lxhw_AppInfoDecorator.getGame_id()));
            jSONObject.putOpt("server_id", Util.urlEncoded(DealPay.getInstance().getPayParams().getServerId()));
            jSONObject.putOpt("server_name", Util.urlEncoded(DealPay.getInstance().getPayParams().getServerName()));
            jSONObject.putOpt("role_id", Util.urlEncoded(DealPay.getInstance().getPayParams().getRoleId()));
            jSONObject.putOpt("role_name", Util.urlEncoded(DealPay.getInstance().getPayParams().getRoleName()));
            jSONObject.putOpt("role_level", Integer.valueOf(DealPay.getInstance().getPayParams().getRoleLevel()));
            jSONObject.putOpt("token", Util.urlEncoded(Lxhw_XUserInfo.getInstance().getToken()));
            jSONObject.putOpt("system_area_code", Util.getCountry());
            jSONObject.putOpt("system_lang_code", MultiLanguageUtil.getInstance().getCurrentSystemLauguage());
            jSONObject.putOpt("os", 2);
            jSONObject.putOpt("product_id", Util.urlEncoded(DealPay.getInstance().getPayParams().getProductId()));
            jSONObject.putOpt("cp_order_number", Util.urlEncoded(DealPay.getInstance().getPayParams().getCpOrderID()));
            jSONObject.putOpt("product_type", Util.urlEncoded(DealPay.getInstance().getPayParams().getProduct_type()));
            jSONObject.putOpt("product_name", Util.urlEncoded(DealPay.getInstance().getPayParams().getProductName()));
            jSONObject.putOpt("cp_ext_params", Util.urlEncoded(DealPay.getInstance().getPayParams().getExtension()));
            LLog.i_Control("pay_parameters:" + jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.params.put("sdk_data", AESUtil.aesGcmEncrypt(jSONObject.toString(), new String(Base64.decode("cXdlIUAjMTIzLyp6eGMtJQ=="))));
        this.params.put("game_id", Integer.valueOf(Lxhw_AppInfoDecorator.getGame_id()));
        this.params.put("platform_account", ManageBean.getInstance().getAccount());
        this.params.put(DeviceRequestsHelper.DEVICE_INFO_DEVICE, Util.getAndroidID(Lxhw_XSDK.getInstance().getApplication()));
        this.params.put("os", 2);
    }

    public String getCommonParamsString() {
        Map<String, Object> map = this.params;
        String str = "";
        if (map != null && map.size() > 0) {
            for (String str2 : this.params.keySet()) {
                str = TextUtils.isEmpty(str) ? str2 + "=" + this.params.get(str2) : str + "&" + str2 + "=" + this.params.get(str2);
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryPay() {
        try {
            HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
            Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("payquery");
            createCommonParams.put("cp_order_number", DealPay.getInstance().getPayParams().getCpOrderID());
            createCommonParams.put("server_id", DealPay.getInstance().getPayParams().getServerId());
            createCommonParams.put("role_id", DealPay.getInstance().getPayParams().getRoleId());
            httpParamsCommon.addSign(createCommonParams, AreaBaseService.PAY_QUERY_ROUTE);
            HttpRequestCenter.getInstance().doRequestCommonAddHead("payquery", createCommonParams, AreaBaseService.PAYQUERY, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.activity.Lxhw_PayActivity.6
                @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
                public void httpRequestCallBackListener(String str) {
                    try {
                        LLog.v_Control(str);
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.isNull("code") || jSONObject.getInt("code") != 0 || jSONObject.isNull("data")) {
                            return;
                        }
                        JSONObject jSONObject2 = new JSONObject(jSONObject.getString("data"));
                        if (jSONObject2.isNull("pay_status") || jSONObject2.getInt("pay_status") != 1) {
                            return;
                        }
                        Lxhw_PayActivity.this.removeLoop();
                        Lxhw_PayActivity.this.uploadPurchase(DealPay.getInstance().getPayParams().getProductId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
                public void httpRequestFail(BaseBean baseBean) {
                    LLog.v_noControl("queryPay " + baseBean.getCode() + " " + baseBean.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void checkLoop() {
        try {
            LLog.i_Control("checkLoop");
            this.mHandler.postDelayed(this.runnable, 1500L);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeLoop() {
        Runnable runnable;
        Handler handler = this.mHandler;
        if (handler == null || (runnable = this.runnable) == null) {
            return;
        }
        handler.removeCallbacks(runnable);
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
