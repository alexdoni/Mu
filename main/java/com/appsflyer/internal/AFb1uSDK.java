package com.appsflyer.internal;

import android.adservices.measurement.MeasurementManager;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Process;
import com.appsflyer.AFLogger;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* loaded from: classes.dex */
public final class AFb1uSDK {
    public static boolean valueOf(Context context, Intent intent) {
        return context.getPackageManager().queryIntentServices(intent, 0).size() > 0;
    }

    public static boolean AFKeystoreWrapper(Context context) {
        if (context != null && Build.VERSION.SDK_INT >= 33) {
            try {
                return ((MeasurementManager) context.getApplicationContext().getSystemService(MeasurementManager.class)) != null;
            } catch (Throwable th) {
                AFLogger.INSTANCE.m99e(AFg1gSDK.PRIVACY_SANDBOX, th.getMessage() != null ? th.getMessage() : "", th, false, false);
            }
        }
        return false;
    }

    public static boolean AFInAppEventParameterName(Context context, String str) {
        int checkPermission = context.checkPermission(str, Process.myPid(), Process.myUid());
        StringBuilder sb = new StringBuilder("is Permission Available: ");
        sb.append(str);
        sb.append("; res: ");
        sb.append(checkPermission);
        AFLogger.afRDLog(sb.toString());
        return checkPermission == 0;
    }

    public static boolean valueOf() {
        return Build.BRAND.equals("OPPO");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004a, code lost:
    
        if (r2.equals("c") != false) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map<java.lang.String, java.lang.String> values(android.content.Context r11, java.util.Map<java.lang.String, java.lang.String> r12, android.net.Uri r13) {
        /*
            java.lang.String r0 = "install_time"
            java.util.Set r1 = r13.getQueryParameterNames()
            java.util.Iterator r1 = r1.iterator()
        La:
            boolean r2 = r1.hasNext()
            r3 = 0
            java.lang.String r4 = "media_source"
            java.lang.String r5 = "agency"
            if (r2 == 0) goto L69
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r6 = r13.getQueryParameter(r2)
            boolean r7 = r12.containsKey(r2)
            if (r7 != 0) goto L64
            int r7 = r2.hashCode()
            r8 = -1420799080(0xffffffffab505398, float:-7.4012454E-13)
            r9 = 2
            r10 = 1
            if (r7 == r8) goto L4d
            r8 = 99
            if (r7 == r8) goto L44
            r3 = 110987(0x1b18b, float:1.55526E-40)
            if (r7 == r3) goto L3a
            goto L57
        L3a:
            java.lang.String r3 = "pid"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L57
            r3 = r10
            goto L58
        L44:
            java.lang.String r7 = "c"
            boolean r7 = r2.equals(r7)
            if (r7 == 0) goto L57
            goto L58
        L4d:
            java.lang.String r3 = "af_prt"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L57
            r3 = r9
            goto L58
        L57:
            r3 = -1
        L58:
            if (r3 == 0) goto L61
            if (r3 == r10) goto L65
            if (r3 == r9) goto L5f
            goto L64
        L5f:
            r4 = r5
            goto L65
        L61:
            java.lang.String r4 = "campaign"
            goto L65
        L64:
            r4 = r2
        L65:
            r12.put(r4, r6)
            goto La
        L69:
            boolean r1 = r12.containsKey(r0)     // Catch: java.lang.Exception -> L9d
            if (r1 != 0) goto La3
            java.lang.String r1 = "yyyy-MM-dd HH:mm:ss"
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat     // Catch: java.lang.Exception -> L9d
            java.util.Locale r6 = java.util.Locale.US     // Catch: java.lang.Exception -> L9d
            r2.<init>(r1, r6)     // Catch: java.lang.Exception -> L9d
            android.content.pm.PackageManager r1 = r11.getPackageManager()     // Catch: java.lang.Exception -> L9d
            java.lang.String r11 = r11.getPackageName()     // Catch: java.lang.Exception -> L9d
            android.content.pm.PackageInfo r11 = r1.getPackageInfo(r11, r3)     // Catch: java.lang.Exception -> L9d
            long r6 = r11.firstInstallTime     // Catch: java.lang.Exception -> L9d
            java.lang.String r11 = "UTC"
            java.util.TimeZone r11 = java.util.TimeZone.getTimeZone(r11)     // Catch: java.lang.Exception -> L9d
            r2.setTimeZone(r11)     // Catch: java.lang.Exception -> L9d
            java.util.Date r11 = new java.util.Date     // Catch: java.lang.Exception -> L9d
            r11.<init>(r6)     // Catch: java.lang.Exception -> L9d
            java.lang.String r11 = r2.format(r11)     // Catch: java.lang.Exception -> L9d
            r12.put(r0, r11)     // Catch: java.lang.Exception -> L9d
            goto La3
        L9d:
            r11 = move-exception
            java.lang.String r0 = "Could not fetch install time. "
            com.appsflyer.AFLogger.afErrorLog(r0, r11)
        La3:
            java.lang.String r11 = "af_deeplink"
            boolean r11 = r12.containsKey(r11)
            if (r11 == 0) goto Lb8
            java.lang.String r11 = "af_status"
            boolean r0 = r12.containsKey(r11)
            if (r0 != 0) goto Lb8
            java.lang.String r0 = "Non-organic"
            r12.put(r11, r0)
        Lb8:
            boolean r11 = r12.containsKey(r5)
            if (r11 == 0) goto Lc1
            r12.remove(r4)
        Lc1:
            java.lang.String r11 = r13.getPath()
            if (r11 == 0) goto Lcc
            java.lang.String r0 = "path"
            r12.put(r0, r11)
        Lcc:
            java.lang.String r11 = r13.getScheme()
            if (r11 == 0) goto Ld7
            java.lang.String r0 = "scheme"
            r12.put(r0, r11)
        Ld7:
            java.lang.String r11 = r13.getHost()
            if (r11 == 0) goto Le2
            java.lang.String r13 = "host"
            r12.put(r13, r11)
        Le2:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1uSDK.values(android.content.Context, java.util.Map, android.net.Uri):java.util.Map");
    }

    public static String AFInAppEventParameterName(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException, CertificateException, NoSuchAlgorithmException {
        Signature[] signatureArr = packageManager.getPackageInfo(str, 64).signatures;
        if (signatureArr == null) {
            return null;
        }
        X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()));
        MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
        messageDigest.update(x509Certificate.getEncoded());
        return String.format("%032X", new BigInteger(1, messageDigest.digest()));
    }

    public static long values(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (Build.VERSION.SDK_INT >= 28) {
                return packageInfo.getLongVersionCode();
            }
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            AFLogger.afErrorLog(e.getMessage(), e);
            return 0L;
        }
    }

    public static String AFKeystoreWrapper(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            AFLogger.afErrorLog(e.getMessage(), e);
            return "";
        }
    }

    public static Application valueOf(Context context) {
        if (context instanceof Application) {
            return (Application) context;
        }
        if (context instanceof Activity) {
            return ((Activity) context).getApplication();
        }
        try {
            return (Application) context.getApplicationContext();
        } catch (ClassCastException unused) {
            AFLogger.afErrorLog("Application or Activity Context should be used", new IllegalStateException(), true, true);
            return null;
        }
    }
}
