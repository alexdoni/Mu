package com.appsflyer.internal;

import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* loaded from: classes.dex */
public final class AFf1eSDK {
    private static int $10 = 0;
    private static int $11 = 1;
    private static int AFKeystoreWrapper = 1;
    private static long valueOf;
    private static int values;

    static {
        valueOf();
        MotionEvent.axisFromString("");
        int i = AFKeystoreWrapper + 91;
        values = i % 128;
        if ((i % 2 != 0 ? '.' : '\b') != '.') {
        } else {
            throw null;
        }
    }

    static void valueOf() {
        valueOf = -3712288596210670646L;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.appsflyer.internal.AFh1cSDK AFInAppEventType(com.appsflyer.internal.AFh1hSDK r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r4 = this;
            r0 = 0
            if (r5 == 0) goto L29
            r1 = 1
            if (r7 == 0) goto L8
            r2 = r1
            goto L9
        L8:
            r2 = r0
        L9:
            if (r2 == r1) goto Lc
            goto L29
        Lc:
            int r2 = com.appsflyer.internal.AFf1eSDK.values
            int r2 = r2 + 61
            int r3 = r2 % 128
            com.appsflyer.internal.AFf1eSDK.AFKeystoreWrapper = r3
            int r2 = r2 % 2
            if (r8 == 0) goto L29
            int r3 = r3 + 27
            int r2 = r3 % 128
            com.appsflyer.internal.AFf1eSDK.values = r2
            int r3 = r3 % 2
            int r2 = r2 + 115
            int r3 = r2 % 128
            com.appsflyer.internal.AFf1eSDK.AFKeystoreWrapper = r3
            int r2 = r2 % 2
            goto L2a
        L29:
            r1 = r0
        L2a:
            if (r1 != 0) goto L34
            com.appsflyer.internal.AFh1cSDK r5 = new com.appsflyer.internal.AFh1cSDK
            com.appsflyer.internal.AFh1bSDK r6 = com.appsflyer.internal.AFh1bSDK.INTERNAL_ERROR
            r5.<init>(r0, r6)
            return r5
        L34:
            com.appsflyer.internal.AFh1cSDK r5 = AFInAppEventParameterName(r5, r6, r7, r8)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFf1eSDK.AFInAppEventType(com.appsflyer.internal.AFh1hSDK, java.lang.String, java.lang.String, java.lang.String):com.appsflyer.internal.AFh1cSDK");
    }

    private static AFh1cSDK AFInAppEventParameterName(AFh1hSDK aFh1hSDK, String str, String str2, String str3) {
        String str4;
        if (str == null) {
            return new AFh1cSDK(aFh1hSDK.valueOf == AFh1mSDK.DEFAULT, AFh1bSDK.NA);
        }
        Object[] objArr = new Object[1];
        m87a("峇ᕐ쿭耂窛㌱\ue549忣ၿ쪂茥疰⿒\ue06b媂ጟ얶뿅灞⫴\ue30a單ฺ쁝뫨獻▖鹝偆\u0adc썳떌渢₵髏卥\u05fe﹦낫櫂⍚閞万è路덎日\ude79邓䤧ι\uf5d6깯悀\ud91c鎴䗋㸪\uf0f0ꤌ掤퐼蹔䂜", (ViewConfiguration.getTouchSlop() >> 8) + 18839, objArr);
        String intern = ((String) objArr[0]).intern();
        if (aFh1hSDK.valueOf == AFh1mSDK.CUSTOM) {
            str4 = new StringBuilder(str2).reverse().toString();
        } else {
            str4 = "";
            str3 = intern;
        }
        boolean equals = valueOf(new StringBuilder(str3).reverse().toString(), aFh1hSDK.AFKeystoreWrapper, "android", "v1", str4).equals(str);
        return new AFh1cSDK(equals, equals ? AFh1bSDK.SUCCESS : AFh1bSDK.FAILURE);
    }

    private static String valueOf(String str, String str2, String str3, String str4, String str5) {
        int i = AFKeystoreWrapper + 49;
        values = i % 128;
        int i2 = i % 2;
        String AFInAppEventType = AFb1mSDK.AFInAppEventType(TextUtils.join("\u2063", new String[]{str2, str3, str4, str5, ""}), str);
        if (AFInAppEventType.length() >= 12) {
            return AFInAppEventType.substring(0, 12);
        }
        int i3 = AFKeystoreWrapper + 49;
        values = i3 % 128;
        int i4 = i3 % 2;
        return AFInAppEventType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v4, types: [char[]] */
    /* JADX WARN: Type inference failed for: r11v5, types: [char[]] */
    /* renamed from: a */
    private static void m87a(String str, int i, Object[] objArr) {
        int i2 = $11;
        int i3 = i2 + 87;
        $10 = i3 % 128;
        int i4 = i3 % 2;
        if (!(str == 0)) {
            int i5 = i2 + 51;
            $10 = i5 % 128;
            if (i5 % 2 != 0) {
                str = str.toCharArray();
                int i6 = 12 / 0;
            } else {
                str = str.toCharArray();
            }
        }
        char[] cArr = (char[]) str;
        AFj1iSDK aFj1iSDK = new AFj1iSDK();
        aFj1iSDK.values = i;
        int length = cArr.length;
        long[] jArr = new long[length];
        aFj1iSDK.AFInAppEventType = 0;
        while (true) {
            if (aFj1iSDK.AFInAppEventType >= cArr.length) {
                break;
            }
            jArr[aFj1iSDK.AFInAppEventType] = (cArr[aFj1iSDK.AFInAppEventType] ^ (aFj1iSDK.AFInAppEventType * aFj1iSDK.values)) ^ (valueOf ^ 3448363977863888702L);
            aFj1iSDK.AFInAppEventType++;
            int i7 = $10 + 17;
            $11 = i7 % 128;
            int i8 = i7 % 2;
        }
        char[] cArr2 = new char[length];
        aFj1iSDK.AFInAppEventType = 0;
        int i9 = $10 + 65;
        $11 = i9 % 128;
        int i10 = i9 % 2;
        while (aFj1iSDK.AFInAppEventType < cArr.length) {
            cArr2[aFj1iSDK.AFInAppEventType] = (char) jArr[aFj1iSDK.AFInAppEventType];
            aFj1iSDK.AFInAppEventType++;
        }
        objArr[0] = new String(cArr2);
    }
}
