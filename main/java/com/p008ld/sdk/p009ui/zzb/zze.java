package com.p008ld.sdk.p009ui.zzb;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.p008ld.sdk.util.zzt;

/* compiled from: FunctionHintDialog.java */
/* loaded from: classes2.dex */
public class zze extends Dialog {
    public zze(Context context, String str) {
        super(context, zzt.zzh(context, "package_code_dialog_shadow"));
        zza(context, str);
    }

    private void zza(final Context context, final String str) {
        View zza = zzt.zza(getContext(), "ld_time_out_dialog_layout", (ViewGroup) null);
        final LinearLayout linearLayout = (LinearLayout) zzt.zza(context, "dialog_content_layout", zza);
        zzt.zza(context, "dialog_close", zza).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zze.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zze.this.dismiss();
            }
        });
        zza.postDelayed(new Runnable() { // from class: com.ld.sdk.ui.zzb.zze.2
            @Override // java.lang.Runnable
            public void run() {
                WebView webView = new WebView(context);
                webView.getSettings().setDefaultTextEncodingName("utf-8");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                webView.getSettings().setDomStorageEnabled(true);
                webView.setWebViewClient(new WebViewClient() { // from class: com.ld.sdk.ui.zzb.zze.2.1
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
                linearLayout.addView(webView);
            }
        }, 200L);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setContentView(zza, new LinearLayout.LayoutParams(-2, -2));
        show();
    }
}
