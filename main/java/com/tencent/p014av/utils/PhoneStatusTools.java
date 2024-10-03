package com.tencent.p014av.utils;

import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.luck.picture.lib.config.PictureMimeType;
import com.tencent.kapalaiadapter.ReflecterHelper;

/* loaded from: classes3.dex */
public class PhoneStatusTools {
    static final String TAG = "PhoneStatusTools";

    public static boolean isHardCodeTabletDevice() {
        return false;
    }

    public static boolean isRingerSilent(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        return audioManager != null && audioManager.getRingerMode() == 0;
    }

    public static boolean isRingerVibrate(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        return audioManager != null && audioManager.getRingerMode() == 1;
    }

    public static boolean isRingerNormal(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        return audioManager != null && audioManager.getRingerMode() == 2;
    }

    public static boolean isRingEqualsZero(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        return audioManager != null && audioManager.getStreamVolume(2) == 0;
    }

    public static void listen(Context context, PhoneStateListener phoneStateListener, int i) {
        TelephonyManager telephonyManager;
        TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager2 != null) {
            try {
                ReflecterHelper.invokeMethod(telephonyManager2, "listen", new Class[]{PhoneStateListener.class, Integer.TYPE}, new Object[]{phoneStateListener, Integer.valueOf(i)});
                ReflecterHelper.invokeMethod(telephonyManager2, "listenGemini", new Class[]{Integer.TYPE, PhoneStateListener.class, Integer.TYPE}, new Object[]{0, phoneStateListener, Integer.valueOf(i)});
                ReflecterHelper.invokeMethod(telephonyManager2, "listenGemini", new Class[]{Integer.TYPE, PhoneStateListener.class, Integer.TYPE}, new Object[]{1, phoneStateListener, Integer.valueOf(i)});
            } catch (Exception unused) {
            }
        }
        try {
            telephonyManager = (TelephonyManager) context.getSystemService("phone2");
        } catch (Exception unused2) {
            telephonyManager = null;
        }
        if (telephonyManager != null) {
            try {
                ReflecterHelper.invokeMethod(telephonyManager, "listen", new Class[]{PhoneStateListener.class, Integer.TYPE}, new Object[]{phoneStateListener, Integer.valueOf(i)});
                ReflecterHelper.invokeMethod(telephonyManager, "listenGemini", new Class[]{Integer.TYPE, PhoneStateListener.class, Integer.TYPE}, new Object[]{0, phoneStateListener, Integer.valueOf(i)});
                ReflecterHelper.invokeMethod(telephonyManager, "listenGemini", new Class[]{Integer.TYPE, PhoneStateListener.class, Integer.TYPE}, new Object[]{1, phoneStateListener, Integer.valueOf(i)});
            } catch (Exception unused3) {
            }
        }
        try {
            ReflecterHelper.invokeMethod(ReflecterHelper.invokeStaticMethod("android.telephony.MSimTelephonyManager", "getDefault", null, null), "listen", new Class[]{PhoneStateListener.class, Integer.TYPE}, new Object[]{phoneStateListener, Integer.valueOf(i)});
        } catch (Exception unused4) {
        }
    }

    public static int getNetWorkType(Context context) {
        NetworkInfo networkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            String typeName = activeNetworkInfo.getTypeName();
            if (typeName.equalsIgnoreCase("WIFI")) {
                return 2;
            }
            if (typeName.equalsIgnoreCase("MOBILE") && (networkInfo = connectivityManager.getNetworkInfo(0)) != null && networkInfo.getType() == 0) {
                switch (networkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return 4;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return 3;
                    case 13:
                        return 5;
                    default:
                        return 0;
                }
            }
        }
        return 0;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:6|(5:8|(3:13|14|(1:16)(1:17))|10|11|12)|22|23|24|(5:26|(3:28|29|(1:31)(1:32))|10|11|12)|37|38|(3:40|(1:42)(1:43)|10)|11|12) */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0056, code lost:
    
        if (((java.lang.Integer) com.tencent.kapalaiadapter.ReflecterHelper.invokeMethod(r4, "getCallStateGemini", new java.lang.Class[]{java.lang.Integer.TYPE}, new java.lang.Object[]{1})).intValue() != 0) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0096, code lost:
    
        if (((java.lang.Integer) com.tencent.kapalaiadapter.ReflecterHelper.invokeMethod(r10, "getCallStateGemini", new java.lang.Object[]{1})).intValue() != 0) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00cd, code lost:
    
        if (((java.lang.Integer) com.tencent.kapalaiadapter.ReflecterHelper.invokeMethod(r10, "getCallState", new java.lang.Object[]{1})).intValue() != 0) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0069, code lost:
    
        r10 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isCalling(android.content.Context r10) {
        /*
            java.lang.String r0 = "getCallState"
            java.lang.String r1 = "PhoneStatusTools"
            r2 = 0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            if (r10 != 0) goto L11
            java.lang.String r10 = "context is null"
            com.tencent.p014av.utils.QLog.m598d(r1, r10)
            return r2
        L11:
            java.lang.String r4 = "phone"
            java.lang.Object r4 = r10.getSystemService(r4)
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4
            java.lang.String r5 = "getCallStateGemini"
            r6 = 1
            if (r4 == 0) goto L5f
            int r7 = r4.getCallState()
            if (r7 == 0) goto L27
        L24:
            r2 = r6
            goto Ld1
        L27:
            java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch: java.lang.Exception -> L59
            java.lang.Class r8 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L59
            r7[r2] = r8     // Catch: java.lang.Exception -> L59
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch: java.lang.Exception -> L59
            r8[r2] = r3     // Catch: java.lang.Exception -> L59
            java.lang.Object r7 = com.tencent.kapalaiadapter.ReflecterHelper.invokeMethod(r4, r5, r7, r8)     // Catch: java.lang.Exception -> L59
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch: java.lang.Exception -> L59
            int r7 = r7.intValue()     // Catch: java.lang.Exception -> L59
            if (r7 == 0) goto L3e
            goto L24
        L3e:
            java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch: java.lang.Exception -> L59
            java.lang.Class r8 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L59
            r7[r2] = r8     // Catch: java.lang.Exception -> L59
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch: java.lang.Exception -> L59
            java.lang.Integer r9 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> L59
            r8[r2] = r9     // Catch: java.lang.Exception -> L59
            java.lang.Object r4 = com.tencent.kapalaiadapter.ReflecterHelper.invokeMethod(r4, r5, r7, r8)     // Catch: java.lang.Exception -> L59
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch: java.lang.Exception -> L59
            int r4 = r4.intValue()     // Catch: java.lang.Exception -> L59
            if (r4 == 0) goto L5f
            goto L24
        L59:
            r4 = move-exception
            java.lang.String r7 = "1 isCalling Exception"
            com.tencent.p014av.utils.QLog.m599d(r1, r7, r4)
        L5f:
            r4 = 0
            java.lang.String r7 = "phone2"
            java.lang.Object r10 = r10.getSystemService(r7)     // Catch: java.lang.Exception -> L69
            android.telephony.TelephonyManager r10 = (android.telephony.TelephonyManager) r10     // Catch: java.lang.Exception -> L69
            goto L6a
        L69:
            r10 = r4
        L6a:
            if (r10 == 0) goto L9f
            int r7 = r10.getCallState()
            if (r7 == 0) goto L73
            goto L24
        L73:
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch: java.lang.Exception -> L99
            r7[r2] = r3     // Catch: java.lang.Exception -> L99
            java.lang.Object r7 = com.tencent.kapalaiadapter.ReflecterHelper.invokeMethod(r10, r5, r7)     // Catch: java.lang.Exception -> L99
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch: java.lang.Exception -> L99
            int r7 = r7.intValue()     // Catch: java.lang.Exception -> L99
            if (r7 == 0) goto L84
            goto L24
        L84:
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch: java.lang.Exception -> L99
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> L99
            r7[r2] = r8     // Catch: java.lang.Exception -> L99
            java.lang.Object r10 = com.tencent.kapalaiadapter.ReflecterHelper.invokeMethod(r10, r5, r7)     // Catch: java.lang.Exception -> L99
            java.lang.Integer r10 = (java.lang.Integer) r10     // Catch: java.lang.Exception -> L99
            int r10 = r10.intValue()     // Catch: java.lang.Exception -> L99
            if (r10 == 0) goto L9f
            goto L24
        L99:
            r10 = move-exception
            java.lang.String r5 = "2 isCalling Exception"
            com.tencent.p014av.utils.QLog.m599d(r1, r5, r10)
        L9f:
            java.lang.String r10 = "android.telephony.MSimTelephonyManager"
            java.lang.String r5 = "getDefault"
            java.lang.Object r10 = com.tencent.kapalaiadapter.ReflecterHelper.invokeStaticMethod(r10, r5, r4, r4)     // Catch: java.lang.Exception -> Ld1
            if (r10 == 0) goto Ld1
            java.lang.Object[] r4 = new java.lang.Object[r6]     // Catch: java.lang.Exception -> Ld1
            r4[r2] = r3     // Catch: java.lang.Exception -> Ld1
            java.lang.Object r3 = com.tencent.kapalaiadapter.ReflecterHelper.invokeMethod(r10, r0, r4)     // Catch: java.lang.Exception -> Ld1
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch: java.lang.Exception -> Ld1
            int r3 = r3.intValue()     // Catch: java.lang.Exception -> Ld1
            if (r3 == 0) goto Lbb
            goto L24
        Lbb:
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch: java.lang.Exception -> Ld1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> Ld1
            r3[r2] = r4     // Catch: java.lang.Exception -> Ld1
            java.lang.Object r10 = com.tencent.kapalaiadapter.ReflecterHelper.invokeMethod(r10, r0, r3)     // Catch: java.lang.Exception -> Ld1
            java.lang.Integer r10 = (java.lang.Integer) r10     // Catch: java.lang.Exception -> Ld1
            int r10 = r10.intValue()     // Catch: java.lang.Exception -> Ld1
            if (r10 == 0) goto Ld1
            goto L24
        Ld1:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r0 = "isCalling: "
            r10.<init>(r0)
            r10.append(r2)
            java.lang.String r10 = r10.toString()
            com.tencent.p014av.utils.QLog.m598d(r1, r10)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.utils.PhoneStatusTools.isCalling(android.content.Context):boolean");
    }

    public static boolean isWifiEnv(Context context) {
        NetworkInfo netWorkInfo = getNetWorkInfo(context);
        return netWorkInfo != null && netWorkInfo.getType() == 1;
    }

    public static NetworkInfo getNetWorkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        }
        return null;
    }

    public static boolean isHardCodePhoneDevice() {
        String str = Build.MODEL;
        String str2 = Build.MANUFACTURER;
        if ((str2.equalsIgnoreCase("samsung") && str.equalsIgnoreCase("SM-T230")) || str2.equalsIgnoreCase("SF101")) {
            return true;
        }
        return str2.equalsIgnoreCase("htc") && str.equalsIgnoreCase("Nexus 9");
    }

    public static boolean isTablet(Context context) {
        double d;
        boolean z;
        if (isHardCodePhoneDevice()) {
            return false;
        }
        if (isHardCodeTabletDevice()) {
            return true;
        }
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            d = Math.sqrt(Math.pow(displayMetrics.widthPixels / displayMetrics.xdpi, 2.0d) + Math.pow(displayMetrics.heightPixels / displayMetrics.ydpi, 2.0d));
        } catch (Throwable unused) {
            d = 0.0d;
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (((TelephonyManager) context.getSystemService("phone")).getPhoneType() == 0) {
            z = false;
            QLog.m598d(TAG, "device size : " + d + ", hasTelephone : " + z);
            return d <= 6.5d && !z;
        }
        z = true;
        QLog.m598d(TAG, "device size : " + d + ", hasTelephone : " + z);
        if (d <= 6.5d) {
        }
    }
}
