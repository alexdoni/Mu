package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.facebook.internal.WebDialog;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FacebookWebFallbackDialog.kt */
@Metadata(m1394d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m1395d2 = {"Lcom/facebook/internal/FacebookWebFallbackDialog;", "Lcom/facebook/internal/WebDialog;", "context", "Landroid/content/Context;", "url", "", "expectedRedirectUrl", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V", "waitingForDialogToClose", "", "cancel", "", "parseResponseUri", "Landroid/os/Bundle;", "Companion", "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class FacebookWebFallbackDialog extends WebDialog {
    private static final int OS_BACK_BUTTON_RESPONSE_TIMEOUT_MILLISECONDS = 1500;
    private boolean waitingForDialogToClose;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = FacebookWebFallbackDialog.class.getName();

    public /* synthetic */ FacebookWebFallbackDialog(Context context, String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, str2);
    }

    @JvmStatic
    public static final FacebookWebFallbackDialog newInstance(Context context, String str, String str2) {
        return INSTANCE.newInstance(context, str, str2);
    }

    private FacebookWebFallbackDialog(Context context, String str, String str2) {
        super(context, str);
        setExpectedRedirectUrl(str2);
    }

    @Override // com.facebook.internal.WebDialog
    public Bundle parseResponseUri(String url) {
        Uri parse = Uri.parse(url);
        Utility utility = Utility.INSTANCE;
        Bundle parseUrlQueryString = Utility.parseUrlQueryString(parse.getQuery());
        String string = parseUrlQueryString.getString(ServerProtocol.FALLBACK_DIALOG_PARAM_BRIDGE_ARGS);
        parseUrlQueryString.remove(ServerProtocol.FALLBACK_DIALOG_PARAM_BRIDGE_ARGS);
        Utility utility2 = Utility.INSTANCE;
        if (!Utility.isNullOrEmpty(string)) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                BundleJSONConverter bundleJSONConverter = BundleJSONConverter.INSTANCE;
                parseUrlQueryString.putBundle(NativeProtocol.EXTRA_PROTOCOL_BRIDGE_ARGS, BundleJSONConverter.convertToBundle(jSONObject));
            } catch (JSONException e) {
                Utility utility3 = Utility.INSTANCE;
                Utility.logd(TAG, "Unable to parse bridge_args JSON", e);
            }
        }
        String string2 = parseUrlQueryString.getString(ServerProtocol.FALLBACK_DIALOG_PARAM_METHOD_RESULTS);
        parseUrlQueryString.remove(ServerProtocol.FALLBACK_DIALOG_PARAM_METHOD_RESULTS);
        Utility utility4 = Utility.INSTANCE;
        if (!Utility.isNullOrEmpty(string2)) {
            try {
                JSONObject jSONObject2 = new JSONObject(string2);
                BundleJSONConverter bundleJSONConverter2 = BundleJSONConverter.INSTANCE;
                parseUrlQueryString.putBundle(NativeProtocol.EXTRA_PROTOCOL_METHOD_RESULTS, BundleJSONConverter.convertToBundle(jSONObject2));
            } catch (JSONException e2) {
                Utility utility5 = Utility.INSTANCE;
                Utility.logd(TAG, "Unable to parse bridge_args JSON", e2);
            }
        }
        parseUrlQueryString.remove(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
        NativeProtocol nativeProtocol = NativeProtocol.INSTANCE;
        parseUrlQueryString.putInt(NativeProtocol.EXTRA_PROTOCOL_VERSION, NativeProtocol.getLatestKnownVersion());
        return parseUrlQueryString;
    }

    @Override // com.facebook.internal.WebDialog, android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        WebView webView = getWebView();
        if (!getIsPageFinished() || getIsListenerCalled() || webView == null || !webView.isShown()) {
            super.cancel();
        } else {
            if (this.waitingForDialogToClose) {
                return;
            }
            this.waitingForDialogToClose = true;
            webView.loadUrl(Intrinsics.stringPlus("javascript:", "(function() {  var event = document.createEvent('Event');  event.initEvent('fbPlatformDialogMustClose',true,true);  document.dispatchEvent(event);})();"));
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.facebook.internal.FacebookWebFallbackDialog$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    FacebookWebFallbackDialog.m1734cancel$lambda0(FacebookWebFallbackDialog.this);
                }
            }, 1500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: cancel$lambda-0, reason: not valid java name */
    public static final void m1734cancel$lambda0(FacebookWebFallbackDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        super.cancel();
    }

    /* compiled from: FacebookWebFallbackDialog.kt */
    @Metadata(m1394d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m1395d2 = {"Lcom/facebook/internal/FacebookWebFallbackDialog$Companion;", "", "()V", "OS_BACK_BUTTON_RESPONSE_TIMEOUT_MILLISECONDS", "", "TAG", "", "kotlin.jvm.PlatformType", "newInstance", "Lcom/facebook/internal/FacebookWebFallbackDialog;", "context", "Landroid/content/Context;", "url", "expectedRedirectUrl", "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FacebookWebFallbackDialog newInstance(Context context, String url, String expectedRedirectUrl) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(expectedRedirectUrl, "expectedRedirectUrl");
            WebDialog.Companion companion = WebDialog.INSTANCE;
            WebDialog.initDefaultTheme(context);
            return new FacebookWebFallbackDialog(context, url, expectedRedirectUrl, null);
        }
    }
}
