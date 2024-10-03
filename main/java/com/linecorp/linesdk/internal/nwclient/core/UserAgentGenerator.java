package com.linecorp.linesdk.internal.nwclient.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.Locale;

/* loaded from: classes2.dex */
class UserAgentGenerator {
    private static final String DEFAULT_PACKAGE_NAME = "UNK";
    private static final String DEFAULT_VERSION_NAME = "UNK";
    private String cachedUserAgent;
    private final PackageInfo packageInfo;
    private final String sdkVersion;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserAgentGenerator(Context context, String str) {
        this.packageInfo = getPackageInfo(context);
        this.sdkVersion = str;
    }

    public String getUserAgent() {
        String str = this.cachedUserAgent;
        if (str != null) {
            return str;
        }
        PackageInfo packageInfo = this.packageInfo;
        String str2 = packageInfo == null ? "UNK" : packageInfo.packageName;
        PackageInfo packageInfo2 = this.packageInfo;
        String str3 = packageInfo2 != null ? packageInfo2.versionName : "UNK";
        Locale locale = Locale.getDefault();
        String str4 = str2 + RemoteSettings.FORWARD_SLASH_STRING + str3 + " ChannelSDK/" + this.sdkVersion + " (Linux; U; Android " + Build.VERSION.RELEASE + "; " + locale.getLanguage() + "-" + locale.getCountry() + "; " + Build.MODEL + " Build/" + Build.ID + ")";
        this.cachedUserAgent = str4;
        return str4;
    }

    private static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException unused) {
            throw null;
        }
    }
}
