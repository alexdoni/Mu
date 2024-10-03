package com.oversea.ab_firstarea.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_ActActivity extends Activity {
    private ImageView mBackBtn;
    private ImageView mCloseBtn;
    private Context mContext;
    private WebView mPayWebView;
    private String mUrl = "";
    private String TAG = "Lxhw_ActActivity";

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setSystemUiVisibility(5894);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            attributes.systemUiVisibility = 2050;
            getWindow().setAttributes(attributes);
        }
        this.mContext = this;
        setContentView(Util.getIdByName(this, "layout", "drhw_activity_pay_layout"));
        this.mUrl = getIntent().getStringExtra("mUrl");
        this.mPayWebView = (WebView) findViewById(Util.getIdByName(this, "id", "pay_web_view_id"));
        ImageView imageView = (ImageView) findViewById(Util.getIdByName(this, "id", "tw_iv_close_pay_way"));
        this.mCloseBtn = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.activity.Lxhw_ActActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.i(Lxhw_ActActivity.this.TAG, "******onClick");
                Lxhw_ActActivity.this.finish();
            }
        });
        this.mCloseBtn.setVisibility(0);
        ImageView imageView2 = (ImageView) findViewById(Util.getIdByName(this, "id", "tw_iv_back_pay_way"));
        this.mBackBtn = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.activity.Lxhw_ActActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lxhw_ActActivity.this.mPayWebView != null) {
                    Lxhw_ActActivity.this.mPayWebView.goBack();
                }
            }
        });
        initWebViewSetting();
    }

    private void initWebViewSetting() {
        this.mPayWebView.getSettings().setJavaScriptEnabled(true);
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
        runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.activity.Lxhw_ActActivity.3
            @Override // java.lang.Runnable
            public void run() {
                Log.i("murl", "mul");
                Lxhw_ActActivity.this.mPayWebView.loadUrl(Lxhw_ActActivity.this.mUrl);
            }
        });
    }
}
