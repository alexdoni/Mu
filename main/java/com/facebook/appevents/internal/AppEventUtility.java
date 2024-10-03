package com.facebook.appevents.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: AppEventUtility.kt */
@Metadata(m1394d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0007J\b\u0010\n\u001a\u00020\tH\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0007J\b\u0010\u000e\u001a\u00020\u0004H\u0007J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\b\u0010\u0013\u001a\u00020\u0006H\u0007J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068CX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007¨\u0006\u0017"}, m1395d2 = {"Lcom/facebook/appevents/internal/AppEventUtility;", "", "()V", "PRICE_REGEX", "", "isMainThread", "", "()Z", "assertIsMainThread", "", "assertIsNotMainThread", "bytesToHex", "bytes", "", "getAppVersion", "getRootView", "Landroid/view/View;", "activity", "Landroid/app/Activity;", "isEmulator", "normalizePrice", "", "value", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AppEventUtility {
    public static final AppEventUtility INSTANCE = new AppEventUtility();
    private static final String PRICE_REGEX = "[-+]*\\d+([.,]\\d+)*([.,]\\d+)?";

    @JvmStatic
    public static final void assertIsMainThread() {
    }

    @JvmStatic
    public static final void assertIsNotMainThread() {
    }

    private AppEventUtility() {
    }

    @JvmStatic
    public static final double normalizePrice(String value) {
        try {
            Matcher matcher = Pattern.compile(PRICE_REGEX, 8).matcher(value);
            if (!matcher.find()) {
                return 0.0d;
            }
            String group = matcher.group(0);
            Utility utility = Utility.INSTANCE;
            return NumberFormat.getNumberInstance(Utility.getCurrentLocale()).parse(group).doubleValue();
        } catch (ParseException unused) {
            return 0.0d;
        }
    }

    @JvmStatic
    public static final String bytesToHex(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        StringBuffer stringBuffer = new StringBuffer();
        int length = bytes.length;
        int i = 0;
        while (i < length) {
            byte b = bytes[i];
            i++;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
            stringBuffer.append(format);
        }
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(stringBuffer2, "sb.toString()");
        return stringBuffer2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0081, code lost:
    
        if (kotlin.text.StringsKt.startsWith$default(r0, "generic", false, 2, (java.lang.Object) null) == false) goto L18;
     */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean isEmulator() {
        /*
            java.lang.String r0 = android.os.Build.FINGERPRINT
            java.lang.String r1 = "FINGERPRINT"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r2 = "generic"
            r3 = 0
            r4 = 2
            r5 = 0
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r2, r3, r4, r5)
            if (r0 != 0) goto L8b
            java.lang.String r0 = android.os.Build.FINGERPRINT
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r1 = "unknown"
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r1, r3, r4, r5)
            if (r0 != 0) goto L8b
            java.lang.String r0 = android.os.Build.MODEL
            java.lang.String r1 = "MODEL"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            java.lang.String r6 = "google_sdk"
            r7 = r6
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            boolean r0 = kotlin.text.StringsKt.contains$default(r0, r7, r3, r4, r5)
            if (r0 != 0) goto L8b
            java.lang.String r0 = android.os.Build.MODEL
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            java.lang.String r7 = "Emulator"
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            boolean r0 = kotlin.text.StringsKt.contains$default(r0, r7, r3, r4, r5)
            if (r0 != 0) goto L8b
            java.lang.String r0 = android.os.Build.MODEL
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            java.lang.String r1 = "Android SDK built for x86"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            boolean r0 = kotlin.text.StringsKt.contains$default(r0, r1, r3, r4, r5)
            if (r0 != 0) goto L8b
            java.lang.String r0 = android.os.Build.MANUFACTURER
            java.lang.String r1 = "MANUFACTURER"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            java.lang.String r1 = "Genymotion"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            boolean r0 = kotlin.text.StringsKt.contains$default(r0, r1, r3, r4, r5)
            if (r0 != 0) goto L8b
            java.lang.String r0 = android.os.Build.BRAND
            java.lang.String r1 = "BRAND"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r2, r3, r4, r5)
            if (r0 == 0) goto L83
            java.lang.String r0 = android.os.Build.DEVICE
            java.lang.String r1 = "DEVICE"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r2, r3, r4, r5)
            if (r0 != 0) goto L8b
        L83:
            java.lang.String r0 = android.os.Build.PRODUCT
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r0)
            if (r0 == 0) goto L8c
        L8b:
            r3 = 1
        L8c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.internal.AppEventUtility.isEmulator():boolean");
    }

    @JvmStatic
    private static final boolean isMainThread() {
        return Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper());
    }

    @JvmStatic
    public static final String getAppVersion() {
        FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
        Context applicationContext = FacebookSdk.getApplicationContext();
        try {
            String str = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0).versionName;
            Intrinsics.checkNotNullExpressionValue(str, "{\n      val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)\n      packageInfo.versionName\n    }");
            return str;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    @JvmStatic
    public static final View getRootView(Activity activity) {
        if (CrashShieldHandler.isObjectCrashing(AppEventUtility.class) || activity == null) {
            return null;
        }
        try {
            try {
                Window window = activity.getWindow();
                if (window == null) {
                    return null;
                }
                return window.getDecorView().getRootView();
            } catch (Exception unused) {
                return null;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventUtility.class);
            return null;
        }
    }
}
