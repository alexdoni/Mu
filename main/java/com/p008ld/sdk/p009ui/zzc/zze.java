package com.p008ld.sdk.p009ui.zzc;

import android.app.Activity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;

/* compiled from: AgreementView.java */
/* loaded from: classes2.dex */
public class zze extends zzg {
    public zze(Activity activity, View.OnClickListener onClickListener) {
        super(activity, "ld_account_agreement");
    }

    public void zza(Activity activity, View.OnClickListener onClickListener, int i, int i2, String str) {
        TextView textView = (TextView) zzt.zza(activity, "title_tv", this.zzb);
        LinearLayout linearLayout = (LinearLayout) zzt.zza(activity, "account_back", this.zzb);
        WebView webView = (WebView) zzt.zza(activity, "account_content_agreement", this.zzb);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() { // from class: com.ld.sdk.ui.zzc.zze.1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str2) {
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str2) {
                webView2.loadUrl(str2);
                return true;
            }
        });
        ConfigBean zze = zza.zzf().zze();
        if (i2 == 33) {
            textView.setText("LD - " + zzt.zza(activity, "ld_terms_of_service_text"));
            if (zze != null && !zzi.zza((CharSequence) zze.termsOfServiceUrl)) {
                webView.loadUrl(zze.termsOfServiceUrl);
            } else {
                webView.loadUrl("https://mobile.ld-space.com/terms-of-service");
            }
        } else if (i2 == 35) {
            textView.setText("LD - " + zzt.zza(activity, "ld_pricacy_policy_text"));
            if (zze != null && !zzi.zza((CharSequence) zze.privacyPolicyUrl)) {
                webView.loadUrl(zze.privacyPolicyUrl);
            } else {
                webView.loadUrl("https://mobile.ld-space.com/privacy-policy");
            }
        }
        linearLayout.setTag(Integer.valueOf(i));
        linearLayout.setOnClickListener(onClickListener);
    }
}
