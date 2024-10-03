package com.appsflyer.internal;

import android.net.Uri;
import com.appsflyer.AFLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001b\b\u0007\u0012\u0006\u0010\u0006\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0003\u0010\u0007J\u001b\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\u0002*\u00020\u0002H\u0002¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u000f\u001a\u00020\r*\u00020\r2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0002¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u00118\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u0014\u0010\t\u001a\u00020\u00148\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0015"}, m1395d2 = {"Lcom/appsflyer/internal/AFi1eSDK;", "", "", "values", "()Ljava/lang/String;", "Lcom/appsflyer/internal/AFh1lSDK;", "p0", "(Lcom/appsflyer/internal/AFh1lSDK;)Ljava/lang/String;", "", "AFInAppEventType", "(Ljava/lang/String;Z)Ljava/lang/String;", "valueOf", "(Ljava/lang/String;)Ljava/lang/String;", "Landroid/net/Uri$Builder;", "p1", "AFInAppEventParameterName", "(Landroid/net/Uri$Builder;Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;", "Lcom/appsflyer/internal/AFd1sSDK;", "Lcom/appsflyer/internal/AFd1sSDK;", "AFKeystoreWrapper", "Lcom/appsflyer/internal/AFj1wSDK;", "Lcom/appsflyer/internal/AFj1wSDK;", "<init>", "(Lcom/appsflyer/internal/AFd1sSDK;Lcom/appsflyer/internal/AFj1wSDK;)V", "AFa1tSDK"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AFi1eSDK {
    public static final String AFInAppEventParameterName;
    public static final String AFInAppEventType;
    public static final String AFLogger;
    private static final String afInfoLog;

    /* renamed from: d */
    public static final String f281d;

    /* renamed from: e */
    public static final String f282e;
    public static final String registerClient;
    public static String unregisterClient;
    public static final String valueOf;

    /* renamed from: AFKeystoreWrapper, reason: from kotlin metadata */
    public final AFj1wSDK AFInAppEventType;

    /* renamed from: values, reason: from kotlin metadata */
    public final AFd1sSDK AFKeystoreWrapper;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AFi1eSDK(AFd1sSDK aFd1sSDK) {
        this(aFd1sSDK, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(aFd1sSDK, "");
    }

    public static String values() {
        return "https://aps-webhandler.appsflyer.com/api/trigger";
    }

    private AFi1eSDK(AFd1sSDK aFd1sSDK, AFj1wSDK aFj1wSDK) {
        Intrinsics.checkNotNullParameter(aFd1sSDK, "");
        Intrinsics.checkNotNullParameter(aFj1wSDK, "");
        this.AFKeystoreWrapper = aFd1sSDK;
        this.AFInAppEventType = aFj1wSDK;
    }

    public /* synthetic */ AFi1eSDK(AFd1sSDK aFd1sSDK, AFj1xSDK aFj1xSDK, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(aFd1sSDK, (i & 2) != 0 ? new AFj1xSDK() : aFj1xSDK);
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(AFb1tSDK.values);
        sb.append("/androidevent?app_id=");
        String obj = sb.toString();
        afInfoLog = obj;
        StringBuilder sb2 = new StringBuilder("https://%sattr.%s/api/v");
        sb2.append(obj);
        AFInAppEventParameterName = sb2.toString();
        AFInAppEventType = "https://%sadrevenue.%s/api/v2/generic/v6.13.1/android?app_id=";
        valueOf = "https://%sadrevenue.%s/api/v2/log/AdImpression/v6.13.1/android?app_id=";
        StringBuilder sb3 = new StringBuilder("https://%sconversions.%s/api/v");
        sb3.append(obj);
        AFLogger = sb3.toString();
        StringBuilder sb4 = new StringBuilder("https://%slaunches.%s/api/v");
        sb4.append(obj);
        f281d = sb4.toString();
        StringBuilder sb5 = new StringBuilder("https://%sinapps.%s/api/v");
        sb5.append(obj);
        f282e = sb5.toString();
        registerClient = "https://%smonitorsdk.%s/api/remote-debug/v2.0?app_id=";
        StringBuilder sb6 = new StringBuilder("https://%svalidate.%s/api/v");
        sb6.append(AFb1tSDK.values);
        sb6.append("/androidevent?buildnumber=6.13.1&app_id=");
        unregisterClient = sb6.toString();
    }

    public static Uri.Builder AFInAppEventParameterName(Uri.Builder builder, String str, String str2) {
        if (str == null || str2 == null) {
            String str3 = str == null ? "devKey" : "timestamp";
            AFLogger.afErrorLog(str3.concat(" is null at attempt to generate ddl event url"), new IllegalStateException(str3.concat(" is null")));
            return builder;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(str);
        builder.appendQueryParameter("af_sig", AFb1mSDK.AFInAppEventType(sb.toString(), str));
        return builder;
    }

    public final String values(AFh1lSDK p0) {
        Intrinsics.checkNotNullParameter(p0, "");
        if (!(p0 instanceof AFh1jSDK)) {
            if (!(p0 instanceof AFh1kSDK)) {
                throw new UnsupportedOperationException();
            }
            String AFInAppEventType2 = this.AFInAppEventType.AFInAppEventType(unregisterClient);
            StringBuilder sb = new StringBuilder();
            sb.append(AFInAppEventType2);
            sb.append(this.AFKeystoreWrapper.AFInAppEventParameterName.valueOf.getPackageName());
            return sb.toString();
        }
        return this.AFInAppEventType.AFInAppEventType("https://%ssdk-services.%s/validate-android-signature");
    }

    public static String AFInAppEventType(String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(!z ? "&buildnumber=6.13.1" : "");
        return sb.toString();
    }

    public final String valueOf(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String AFLogger2 = this.AFKeystoreWrapper.AFLogger();
        String concat = AFLogger2 != null ? "&channel=".concat(String.valueOf(AFLogger2)) : null;
        if (concat == null) {
            concat = "";
        }
        sb.append(concat);
        return sb.toString();
    }
}
