package com.p008ld.sdk.p009ui.zzb;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzt;

/* compiled from: PayH5PageDialog.java */
/* loaded from: classes2.dex */
public class zzm extends Dialog {
    DialogInterface.OnKeyListener zza;
    private zzb zzb;
    private long zzc;
    private String zzd;

    /* compiled from: PayH5PageDialog.java */
    /* loaded from: classes2.dex */
    public interface zzb {
        void zza();

        void zzb();
    }

    public zzm(Context context, String str, zzb zzbVar) {
        super(context, zzt.zzh(context, "KKKDialog"));
        this.zza = new DialogInterface.OnKeyListener() { // from class: com.ld.sdk.ui.zzb.zzm.3
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4 || keyEvent.getRepeatCount() != 0) {
                    return true;
                }
                if (keyEvent.getAction() != 0) {
                    return false;
                }
                if (System.currentTimeMillis() - zzm.this.zzc <= 2000) {
                    zzm.this.zzb.zzb();
                    zzm.this.dismiss();
                    return false;
                }
                zzm.this.zzc = System.currentTimeMillis();
                return false;
            }
        };
        this.zzb = zzbVar;
        zza(context, str);
    }

    private void zza(final Context context, final String str) {
        setCanceledOnTouchOutside(false);
        requestWindowFeature(1);
        View zza2 = zzt.zza(context, "ld_pay_h5_layout", (ViewGroup) null);
        final FrameLayout frameLayout = (FrameLayout) zzt.zza(context, "root_layout", zza2);
        setContentView(zza2);
        ((ImageView) zzt.zza(context, "pay_dialog_close_img", zza2)).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzm.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzm.this.zzb.zzb();
                zzm.this.dismiss();
            }
        });
        zza2.postDelayed(new Runnable() { // from class: com.ld.sdk.ui.zzb.zzm.2
            @Override // java.lang.Runnable
            public void run() {
                WebView webView = new WebView(context);
                WebSettings settings = webView.getSettings();
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
                settings.setLoadsImagesAutomatically(true);
                settings.setJavaScriptEnabled(true);
                settings.setAllowFileAccessFromFileURLs(true);
                settings.setAllowContentAccess(true);
                settings.setDomStorageEnabled(true);
                settings.setAllowFileAccess(true);
                settings.setUseWideViewPort(true);
                settings.setLoadWithOverviewMode(true);
                settings.setBuiltInZoomControls(false);
                settings.setSupportZoom(true);
                settings.setDisplayZoomControls(true);
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
                settings.setMediaPlaybackRequiresUserGesture(false);
                webView.addJavascriptInterface(new zza(), "androidSdk");
                webView.setWebChromeClient(new WebChromeClient() { // from class: com.ld.sdk.ui.zzb.zzm.2.1
                    @Override // android.webkit.WebChromeClient
                    public boolean onJsAlert(WebView webView2, String str2, String str3, JsResult jsResult) {
                        return super.onJsAlert(webView2, str2, str3, jsResult);
                    }
                });
                webView.setWebViewClient(new WebViewClient() { // from class: com.ld.sdk.ui.zzb.zzm.2.2
                    @Override // android.webkit.WebViewClient
                    public boolean shouldOverrideUrlLoading(WebView webView2, String str2) {
                        if (str2.startsWith("http:") || str2.startsWith("https:")) {
                            return false;
                        }
                        zzm.this.zza(str2);
                        return true;
                    }
                });
                webView.canGoBack();
                webView.canGoForward();
                webView.loadUrl(str);
                frameLayout.addView(webView, 0);
            }
        }, 100L);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        getWindow().setAttributes(attributes);
        setCancelable(false);
        show();
        setOnKeyListener(this.zza);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(String str) {
        Intent intent;
        try {
            if (str.startsWith("intent://")) {
                intent = Intent.parseUri(str, 1);
                this.zzd = intent.getPackage();
            } else {
                intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            }
            getContext().startActivity(intent);
        } catch (Exception e) {
            zza(e);
        }
    }

    private void zza(Exception exc) {
        String str = this.zzd;
        if (str != null) {
            zzb(str);
        } else {
            LDUtil.toast(exc.getMessage());
        }
    }

    private void zzb(String str) {
        try {
            getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)));
        } catch (ActivityNotFoundException unused) {
            getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + str)));
        }
    }

    /* compiled from: PayH5PageDialog.java */
    /* loaded from: classes2.dex */
    public class zza {
        public zza() {
        }

        @JavascriptInterface
        public void payComplete() {
            zzm.this.zzb.zza();
            zzm.this.dismiss();
        }

        @JavascriptInterface
        public void payState(int i) {
            if (i == 0) {
                zzm.this.zzb.zzb();
            } else {
                zzm.this.zzb.zza();
            }
            zzm.this.dismiss();
        }
    }
}
