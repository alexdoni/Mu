package com.tencent.p014av.config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

/* loaded from: classes3.dex */
public class Common {
    public static final String AV_ENGINE_VERSION = "V2.9.0304.001";
    public static final String AV_SHARP_VERSION = "1.0";
    public static final String CONFIG_INFO = "ConfigInfo";
    public static final int EM_Device_Android = 101;
    public static final String FILE_NAME = "VideoConfigInfo";
    public static final String SHARP_CONFIG_PAYLOAD_FILE_NAME = "SharpConfigPayload";
    public static final String SHARP_CONFIG_TYPE_CLEAR = "0";
    public static final String SHARP_CONFIG_TYPE_PAYLOAD = "1";
    public static final String SHARP_CONFIG_TYPE_URL = "2";
    static final String TAG = "Common";

    public static int intPow(int i, int i2) {
        if (i2 < 0) {
            return 0;
        }
        int i3 = 1;
        while (i2 > 0) {
            i3 *= i;
            i2--;
        }
        return i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0012 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void writeFile(android.content.Context r1, java.lang.String r2, byte[] r3) {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = r1.openFileOutput(r2, r0)     // Catch: java.lang.Exception -> Lb
            r1.write(r3)     // Catch: java.lang.Exception -> L9
            goto L10
        L9:
            r2 = move-exception
            goto Ld
        Lb:
            r2 = move-exception
            r1 = 0
        Ld:
            r2.printStackTrace()
        L10:
            if (r1 == 0) goto L1a
            r1.close()     // Catch: java.lang.Exception -> L16
            goto L1a
        L16:
            r1 = move-exception
            r1.printStackTrace()
        L1a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.config.Common.writeFile(android.content.Context, java.lang.String, byte[]):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0035 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] readFile(android.content.Context r5, java.lang.String r6) {
        /*
            java.lang.String r0 = "read file error.  fileName:"
            r1 = 0
            java.io.FileInputStream r5 = r5.openFileInput(r6)     // Catch: java.lang.Exception -> L2d
            int r2 = r5.available()     // Catch: java.lang.Exception -> L2b
            if (r2 > 0) goto Le
            return r1
        Le:
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L2b
            int r3 = r5.read(r2)     // Catch: java.lang.Exception -> L28
            if (r3 > 0) goto L33
            java.lang.String r3 = "Common"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L28
            r4.<init>(r0)     // Catch: java.lang.Exception -> L28
            r4.append(r6)     // Catch: java.lang.Exception -> L28
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Exception -> L28
            com.tencent.p014av.utils.QLog.m600e(r3, r6)     // Catch: java.lang.Exception -> L28
            return r1
        L28:
            r6 = move-exception
            r1 = r2
            goto L2f
        L2b:
            r6 = move-exception
            goto L2f
        L2d:
            r6 = move-exception
            r5 = r1
        L2f:
            r6.printStackTrace()
            r2 = r1
        L33:
            if (r5 == 0) goto L3d
            r5.close()     // Catch: java.lang.Exception -> L39
            goto L3d
        L39:
            r5 = move-exception
            r5.printStackTrace()
        L3d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.config.Common.readFile(android.content.Context, java.lang.String):byte[]");
    }

    public static String getVersion(Context context) {
        if (context == null) {
            return "1.0";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return (packageInfo == null || TextUtils.isEmpty(packageInfo.versionName.trim())) ? "1.0" : packageInfo.versionName.trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }
}
