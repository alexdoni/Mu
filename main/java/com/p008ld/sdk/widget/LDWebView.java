package com.p008ld.sdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.p008ld.sdk.LDSdk;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDWebView.kt */
/* loaded from: classes2.dex */
public final class LDWebView extends WebView {
    public /* synthetic */ LDWebView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LDWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        init();
    }

    private final void init() {
        WebSettings settings = getSettings();
        settings.setDatabaseEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setMixedContentMode(0);
        settings.setCacheMode(-1);
        settings.setUseWideViewPort(true);
        if (LDSdk.getDebugLogEnable()) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    @Override // android.webkit.WebView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (i == 4) {
            if (!canGoBack()) {
                return true;
            }
            goBack();
            return true;
        }
        return super.onKeyDown(i, event);
    }

    @Override // android.webkit.WebView, android.view.View
    public void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
        } catch (NullPointerException unused) {
        }
    }

    public final void onDestroy() {
        try {
            ViewParent parent = getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(this);
            }
            removeAllViews();
            stopLoading();
            setWebChromeClient(null);
            removeJavascriptInterface("Android");
            removeAllViewsInLayout();
            destroy();
        } catch (Exception e) {
            destroy();
            e.printStackTrace();
        }
    }
}
