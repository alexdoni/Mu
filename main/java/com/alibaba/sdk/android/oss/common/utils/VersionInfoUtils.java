package com.alibaba.sdk.android.oss.common.utils;

import android.os.Build;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.google.firebase.sessions.settings.RemoteSettings;

/* loaded from: classes.dex */
public class VersionInfoUtils {
    private static String userAgent;

    public static String getVersion() {
        return "2.9.13";
    }

    public static String getUserAgent(String str) {
        if (OSSUtils.isEmptyString(userAgent)) {
            userAgent = "aliyun-sdk-android/" + getVersion() + getSystemInfo();
        }
        if (OSSUtils.isEmptyString(str)) {
            return userAgent;
        }
        return userAgent + RemoteSettings.FORWARD_SLASH_STRING + str;
    }

    private static String getSystemInfo() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(System.getProperty("os.name"));
        sb.append("/Android " + Build.VERSION.RELEASE);
        sb.append(RemoteSettings.FORWARD_SLASH_STRING);
        sb.append(HttpUtil.urlEncode(Build.MODEL, "utf-8") + ";" + HttpUtil.urlEncode(Build.ID, "utf-8"));
        sb.append(")");
        String sb2 = sb.toString();
        OSSLog.logDebug("user agent : " + sb2);
        return OSSUtils.isEmptyString(sb2) ? System.getProperty("http.agent").replaceAll("[^\\p{ASCII}]", "?") : sb2;
    }
}
