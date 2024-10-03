package com.p008ld.sdk.p009ui.zzb;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.p008ld.sdk.core.bean.LDLoginResult;
import com.p008ld.sdk.core.bean.LoginMode;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDThirdLoginCallback;
import com.p008ld.sdk.util.zzt;

/* compiled from: ThirdLoginDialog.java */
/* loaded from: classes2.dex */
public class zzo extends Dialog {
    private final LDThirdLoginCallback zza;
    private final LoginMode zzb;

    public zzo(final Context context, LoginMode loginMode, final String str, LDThirdLoginCallback lDThirdLoginCallback) {
        super(context, zzt.zzh(context, "KKKDialog"));
        this.zza = lDThirdLoginCallback;
        this.zzb = loginMode;
        final FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        frameLayout.postDelayed(new Runnable() { // from class: com.ld.sdk.ui.zzb.zzo.1
            @Override // java.lang.Runnable
            public void run() {
                WebView webView = new WebView(context);
                webView.getSettings().setDefaultTextEncodingName("utf-8");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                webView.getSettings().setDomStorageEnabled(true);
                webView.addJavascriptInterface(new zza(), "androidSdk");
                webView.setWebViewClient(new WebViewClient() { // from class: com.ld.sdk.ui.zzb.zzo.1.1
                    @Override // android.webkit.WebViewClient
                    public void onPageFinished(WebView webView2, String str2) {
                    }

                    @Override // android.webkit.WebViewClient
                    public boolean shouldOverrideUrlLoading(WebView webView2, String str2) {
                        webView2.loadUrl(str2);
                        return true;
                    }
                });
                webView.loadUrl(str);
                frameLayout.addView(webView);
            }
        }, 100L);
        setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.ld.sdk.ui.zzb.zzo.2
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4 || keyEvent.getRepeatCount() != 0 || keyEvent.getAction() != 1) {
                    return false;
                }
                if (zzo.this.zza != null) {
                    zzo.this.zza.onError(-1, new LDException(zzo.this.zzb.getValue().toLowerCase() + " login cancel"));
                }
                zzo.this.dismiss();
                return true;
            }
        });
        setContentView(frameLayout);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        getWindow().setAttributes(attributes);
        show();
    }

    /* compiled from: ThirdLoginDialog.java */
    /* loaded from: classes2.dex */
    public class zza {
        public zza() {
        }

        @JavascriptInterface
        public void loginCallback(boolean z, String str, String str2) {
            if (zzo.this.zza != null) {
                if (z) {
                    zzo.this.zza.onSuccess(zzo.this.zzb, new LDLoginResult(str, "", str2, "", -1));
                } else {
                    zzo.this.zza.onError(-1, new LDException(zzo.this.zzb.getValue().toLowerCase() + " login failed"));
                }
            }
            zzo.this.dismiss();
        }
    }
}
