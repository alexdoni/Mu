package com.appsflyer.internal;

import com.appsflyer.AFLogger;

/* loaded from: classes.dex */
public final class AFb1lSDK {
    private static String valueOf;
    private static String values;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void valueOf(String str) {
        valueOf = str;
        if (str == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || i == str.length() - 1) {
                sb.append(str.charAt(i));
            } else {
                sb.append("*");
            }
        }
        values = sb.toString();
    }

    public static void values(String str) {
        if (valueOf == null) {
            valueOf(AFb1tSDK.valueOf().values().force().registerClient);
        }
        String str2 = valueOf;
        if (str2 != null) {
            AFLogger.afInfoLog(str.replace(str2, values));
        }
    }
}
