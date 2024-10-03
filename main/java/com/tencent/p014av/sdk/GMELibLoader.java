package com.tencent.p014av.sdk;

import android.content.Context;
import com.tencent.p014av.utils.QLog;

/* loaded from: classes3.dex */
public class GMELibLoader {
    private static final String TAG = "SoUtil";
    private static boolean sLoadLibrary = false;
    private static final String[] abiList = {"armeabi-v7a", "arm64-v8a", "x86", "x86_64"};
    private static final String[] soList = {"libgmesdk.so", "libtraeimp.so", "libsilk.so"};
    private static String gmesoVersion = "c37d0e20d5d02337d209f0bbb8f159fac0e83482";
    private static String traesoVersion = "2.8.4.8ccc5906";

    private static native String nativeGetGmeSoVersion();

    public static native String nativeGetTraeSoVersion();

    public static int loadSdkLibrary(Context context) {
        if (!sLoadLibrary) {
            r1 = loadSo("traeimp") && loadSo("silk") && loadSo("gmesdk");
            sLoadLibrary = r1;
            if (!r1) {
                return AVError.AV_ERR_LOAD_LIB_FAILED;
            }
            r1 = checkSo();
        }
        if (r1) {
            return 0;
        }
        return AVError.AV_ERR_SDK_NOT_FULL_UPDATE;
    }

    public static boolean checkSo() {
        boolean z;
        try {
            if (gmesoVersion.equals("localGmeSoVersion")) {
                z = true;
            } else {
                String nativeGetGmeSoVersion = nativeGetGmeSoVersion();
                z = gmesoVersion.equals(nativeGetGmeSoVersion);
                QLog.m598d(TAG, "gmeSoVersion =  " + gmesoVersion + "nativeGmeSoVersion = " + nativeGetGmeSoVersion);
            }
            if (!z || traesoVersion.equals("localTraeSoVersion")) {
                return z;
            }
            String replace = nativeGetTraeSoVersion().replace("\"", "");
            boolean equals = traesoVersion.equals(replace);
            QLog.m598d(TAG, "traeSoVersion =  " + traesoVersion + "nativeTraeSoVersion = " + replace);
            return equals;
        } catch (UnsatisfiedLinkError e) {
            QLog.m598d(TAG, "use nativeGetTrae/GmeVersion failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean loadSo(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            QLog.m598d(TAG, "load so failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean isLoadLibrary() {
        return sLoadLibrary;
    }
}
