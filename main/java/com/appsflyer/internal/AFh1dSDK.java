package com.appsflyer.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\t\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0003\u001a\u00020\u0002H%¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH&¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u000fX\u0085\u0002¢\u0006\u0006\n\u0004\b\u0006\u0010\u0010R\u0016\u0010\r\u001a\u0004\u0018\u00010\u00128\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u0013"}, m1395d2 = {"Lcom/appsflyer/internal/AFh1dSDK;", "", "", "values", "()Ljava/lang/String;", "Lcom/appsflyer/internal/AFi1zSDK;", "AFInAppEventType", "()Lcom/appsflyer/internal/AFi1zSDK;", "Landroid/net/NetworkInfo;", "p0", "", "AFInAppEventParameterName", "(Landroid/net/NetworkInfo;)Z", "valueOf", "()Z", "Landroid/net/ConnectivityManager;", "Landroid/net/ConnectivityManager;", "AFKeystoreWrapper", "Landroid/telephony/TelephonyManager;", "Landroid/telephony/TelephonyManager;", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "AFa1vSDK"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public abstract class AFh1dSDK {

    /* renamed from: AFInAppEventParameterName, reason: from kotlin metadata */
    private final TelephonyManager valueOf;

    /* renamed from: AFInAppEventType, reason: from kotlin metadata */
    ConnectivityManager AFKeystoreWrapper;

    public abstract boolean valueOf();

    protected abstract String values();

    public AFh1dSDK(Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        Object systemService = context.getSystemService("connectivity");
        this.AFKeystoreWrapper = systemService instanceof ConnectivityManager ? (ConnectivityManager) systemService : null;
        Object systemService2 = context.getSystemService("phone");
        this.valueOf = systemService2 instanceof TelephonyManager ? (TelephonyManager) systemService2 : null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean AFInAppEventParameterName(NetworkInfo p0) {
        if (p0 != null) {
            return p0.isConnectedOrConnecting();
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001e A[Catch: all -> 0x0028, TryCatch #1 {all -> 0x0028, blocks: (B:8:0x0009, B:10:0x0012, B:15:0x001e), top: B:7:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.appsflyer.internal.AFi1zSDK AFInAppEventType() {
        /*
            r6 = this;
            r0 = 0
            android.telephony.TelephonyManager r1 = r6.valueOf     // Catch: java.lang.Throwable -> L30
            if (r1 == 0) goto L2e
            java.lang.String r2 = r1.getSimOperatorName()     // Catch: java.lang.Throwable -> L30
            java.lang.String r0 = r1.getNetworkOperatorName()     // Catch: java.lang.Throwable -> L28
            r3 = r0
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch: java.lang.Throwable -> L28
            if (r3 == 0) goto L1b
            int r3 = r3.length()     // Catch: java.lang.Throwable -> L28
            if (r3 != 0) goto L19
            goto L1b
        L19:
            r3 = 0
            goto L1c
        L1b:
            r3 = 1
        L1c:
            if (r3 == 0) goto L3a
            int r1 = r1.getPhoneType()     // Catch: java.lang.Throwable -> L28
            r3 = 2
            if (r1 != r3) goto L3a
            java.lang.String r0 = "CDMA"
            goto L3a
        L28:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r2
            r2 = r5
            goto L33
        L2e:
            r2 = r0
            goto L3a
        L30:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L33:
            java.lang.String r3 = "Exception while collecting network info. "
            com.appsflyer.AFLogger.afErrorLog(r3, r2)
            r2 = r0
            r0 = r1
        L3a:
            com.appsflyer.internal.AFi1zSDK r1 = new com.appsflyer.internal.AFi1zSDK
            java.lang.String r3 = r6.values()
            boolean r4 = r6.valueOf()
            r1.<init>(r3, r0, r2, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFh1dSDK.AFInAppEventType():com.appsflyer.internal.AFi1zSDK");
    }
}
