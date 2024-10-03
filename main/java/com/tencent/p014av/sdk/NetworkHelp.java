package com.tencent.p014av.sdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.tencent.p014av.utils.QLog;

/* loaded from: classes3.dex */
public class NetworkHelp {
    public static final String TAG = "NetworkHelp";

    /* loaded from: classes3.dex */
    public static class APInfo {
        public int apType = APType.AP_UNKNOWN.value();
        public String apName = "AP_UNKNOWN";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum MobileCarrier {
        UNKNOWN,
        CHINA_MOBILE,
        CHINA_UNICOM,
        CHINA_TELECOM
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum APType {
        AP_UNKNOWN(0),
        AP_WIFI(1),
        AP_2G(2),
        AP_3G(3),
        AP_4G(4);

        private int value;

        APType(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }

    protected static APInfo getAPInfo(Context context) {
        APInfo aPInfo = new APInfo();
        if (context == null) {
            QLog.m600e(TAG, "getAPInfo initial context is null");
            return aPInfo;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return aPInfo;
        }
        int type = activeNetworkInfo.getType();
        if (type == 0) {
            return getMobileAPInfo(context, activeNetworkInfo.getSubtype());
        }
        if (type != 1) {
            return aPInfo;
        }
        aPInfo.apType = APType.AP_WIFI.value();
        aPInfo.apName = "AP_WIFI";
        return aPInfo;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00d0, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.tencent.av.sdk.NetworkHelp.APInfo getMobileAPInfo(android.content.Context r2, int r3) {
        /*
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r0 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.UNKNOWN
            java.lang.String r1 = "phone"
            java.lang.Object r2 = r2.getSystemService(r1)     // Catch: java.lang.Exception -> Lf
            android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2     // Catch: java.lang.Exception -> Lf
            java.lang.String r2 = r2.getSubscriberId()     // Catch: java.lang.Exception -> Lf
            goto L14
        Lf:
            r2 = move-exception
            r2.printStackTrace()
            r2 = 0
        L14:
            if (r2 != 0) goto L1e
            java.lang.String r2 = "NetworkHelp"
            java.lang.String r1 = "getAPInfo IMSI is null"
            com.tencent.p014av.utils.QLog.m600e(r2, r1)
            goto L60
        L1e:
            java.lang.String r1 = "46000"
            boolean r1 = r2.startsWith(r1)
            if (r1 != 0) goto L5e
            java.lang.String r1 = "46002"
            boolean r1 = r2.startsWith(r1)
            if (r1 != 0) goto L5e
            java.lang.String r1 = "46007"
            boolean r1 = r2.startsWith(r1)
            if (r1 == 0) goto L37
            goto L5e
        L37:
            java.lang.String r1 = "46001"
            boolean r1 = r2.startsWith(r1)
            if (r1 != 0) goto L5b
            java.lang.String r1 = "46006"
            boolean r1 = r2.startsWith(r1)
            if (r1 == 0) goto L48
            goto L5b
        L48:
            java.lang.String r1 = "46003"
            boolean r1 = r2.startsWith(r1)
            if (r1 != 0) goto L58
            java.lang.String r1 = "46005"
            boolean r2 = r2.startsWith(r1)
            if (r2 == 0) goto L60
        L58:
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r0 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.CHINA_TELECOM
            goto L60
        L5b:
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r0 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.CHINA_UNICOM
            goto L60
        L5e:
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r0 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.CHINA_MOBILE
        L60:
            com.tencent.av.sdk.NetworkHelp$APInfo r2 = new com.tencent.av.sdk.NetworkHelp$APInfo
            r2.<init>()
            java.lang.String r1 = "AP_CTNET"
            switch(r3) {
                case 1: goto Lb0;
                case 2: goto Lb0;
                case 3: goto L8f;
                case 4: goto Lb0;
                case 5: goto L8f;
                case 6: goto L8f;
                case 7: goto Lb0;
                case 8: goto L8f;
                case 9: goto L8f;
                case 10: goto L8f;
                case 11: goto Lb0;
                case 12: goto L8f;
                case 13: goto L6c;
                case 14: goto L8f;
                case 15: goto L8f;
                default: goto L6a;
            }
        L6a:
            goto Ld0
        L6c:
            com.tencent.av.sdk.NetworkHelp$APType r3 = com.tencent.av.sdk.NetworkHelp.APType.AP_4G
            int r3 = r3.value()
            r2.apType = r3
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r3 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.CHINA_MOBILE
            if (r3 != r0) goto L7d
            java.lang.String r3 = "AP_CMLTE"
            r2.apName = r3
            goto Ld0
        L7d:
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r3 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.CHINA_UNICOM
            if (r3 != r0) goto L86
            java.lang.String r3 = "AP_UNLTE"
            r2.apName = r3
            goto Ld0
        L86:
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r3 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.CHINA_TELECOM
            if (r3 != r0) goto Ld0
            java.lang.String r3 = "AP_CTLTE"
            r2.apName = r3
            goto Ld0
        L8f:
            com.tencent.av.sdk.NetworkHelp$APType r3 = com.tencent.av.sdk.NetworkHelp.APType.AP_3G
            int r3 = r3.value()
            r2.apType = r3
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r3 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.CHINA_MOBILE
            if (r3 != r0) goto La0
            java.lang.String r3 = "AP_CM3G"
            r2.apName = r3
            goto Ld0
        La0:
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r3 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.CHINA_UNICOM
            if (r3 != r0) goto La9
            java.lang.String r3 = "AP_3GNET"
            r2.apName = r3
            goto Ld0
        La9:
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r3 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.CHINA_TELECOM
            if (r3 != r0) goto Ld0
            r2.apName = r1
            goto Ld0
        Lb0:
            com.tencent.av.sdk.NetworkHelp$APType r3 = com.tencent.av.sdk.NetworkHelp.APType.AP_2G
            int r3 = r3.value()
            r2.apType = r3
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r3 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.CHINA_MOBILE
            if (r3 != r0) goto Lc1
            java.lang.String r3 = "AP_CMNET"
            r2.apName = r3
            goto Ld0
        Lc1:
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r3 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.CHINA_UNICOM
            if (r3 != r0) goto Lca
            java.lang.String r3 = "AP_UNINET"
            r2.apName = r3
            goto Ld0
        Lca:
            com.tencent.av.sdk.NetworkHelp$MobileCarrier r3 = com.tencent.av.sdk.NetworkHelp.MobileCarrier.CHINA_TELECOM
            if (r3 != r0) goto Ld0
            r2.apName = r1
        Ld0:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.sdk.NetworkHelp.getMobileAPInfo(android.content.Context, int):com.tencent.av.sdk.NetworkHelp$APInfo");
    }
}
