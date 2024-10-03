package com.appsflyer.internal;

import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.internal.AFd1uSDK;
import com.facebook.appevents.UserDataStore;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.jsonwebtoken.JwtParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.json.JSONObject;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes.dex */
public final class AFg1lSDK implements AFg1qSDK {
    private static int $10 = 0;
    private static int $11 = 1;
    private static long afDebugLog = -215349531926575058L;
    private static int afErrorLog = 0;
    private static int afRDLog = 1;
    private final AFd1uSDK AFInAppEventParameterName;
    private final AFi1qSDK AFInAppEventType;
    private final AFb1ySDK AFKeystoreWrapper;
    private final AFd1sSDK AFLogger;
    private final AFd1kSDK afInfoLog;
    private final Lazy afVerboseLog;

    /* renamed from: d */
    private final AFg1xSDK f268d;

    /* renamed from: e */
    private final AFd1tSDK f269e;
    private final AFd1qSDK force;

    /* renamed from: i */
    private final Lazy f270i;
    private final AFg1cSDK registerClient;
    private final AFh1aSDK unregisterClient;

    /* renamed from: v */
    private final AFg1pSDK f271v;
    private final AFi1dSDK valueOf;
    private final Context values;

    /* renamed from: w */
    private final AFb1cSDK f272w;

    public AFg1lSDK(Context context, AFi1qSDK aFi1qSDK, AFd1uSDK aFd1uSDK, AFi1dSDK aFi1dSDK, AFb1ySDK aFb1ySDK, AFg1cSDK aFg1cSDK, AFd1tSDK aFd1tSDK, AFd1sSDK aFd1sSDK, AFh1aSDK aFh1aSDK, AFg1xSDK aFg1xSDK, AFb1cSDK aFb1cSDK, AFd1kSDK aFd1kSDK, AFg1pSDK aFg1pSDK, AFd1qSDK aFd1qSDK) {
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(aFi1qSDK, "");
        Intrinsics.checkNotNullParameter(aFd1uSDK, "");
        Intrinsics.checkNotNullParameter(aFi1dSDK, "");
        Intrinsics.checkNotNullParameter(aFb1ySDK, "");
        Intrinsics.checkNotNullParameter(aFg1cSDK, "");
        Intrinsics.checkNotNullParameter(aFd1tSDK, "");
        Intrinsics.checkNotNullParameter(aFd1sSDK, "");
        Intrinsics.checkNotNullParameter(aFh1aSDK, "");
        Intrinsics.checkNotNullParameter(aFg1xSDK, "");
        Intrinsics.checkNotNullParameter(aFb1cSDK, "");
        Intrinsics.checkNotNullParameter(aFd1kSDK, "");
        Intrinsics.checkNotNullParameter(aFg1pSDK, "");
        Intrinsics.checkNotNullParameter(aFd1qSDK, "");
        this.values = context;
        this.AFInAppEventType = aFi1qSDK;
        this.AFInAppEventParameterName = aFd1uSDK;
        this.valueOf = aFi1dSDK;
        this.AFKeystoreWrapper = aFb1ySDK;
        this.registerClient = aFg1cSDK;
        this.f269e = aFd1tSDK;
        this.AFLogger = aFd1sSDK;
        this.unregisterClient = aFh1aSDK;
        this.f268d = aFg1xSDK;
        this.f272w = aFb1cSDK;
        this.afInfoLog = aFd1kSDK;
        this.f271v = aFg1pSDK;
        this.force = aFd1qSDK;
        this.f270i = LazyKt.lazy(new Function0<AppsFlyerProperties>() { // from class: com.appsflyer.internal.AFg1lSDK.2
            @Override // kotlin.jvm.functions.Function0
            /* renamed from: valueOf, reason: merged with bridge method [inline-methods] */
            public final AppsFlyerProperties invoke() {
                return AppsFlyerProperties.getInstance();
            }
        });
        this.afVerboseLog = LazyKt.lazy(new Function0<SimpleDateFormat>() { // from class: com.appsflyer.internal.AFg1lSDK.3
            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFKeystoreWrapper, reason: merged with bridge method [inline-methods] */
            public final SimpleDateFormat invoke() {
                return new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
            }
        });
    }

    private final AppsFlyerProperties AFKeystoreWrapper() {
        int i = afErrorLog + 83;
        afRDLog = i % 128;
        int i2 = i % 2;
        AppsFlyerProperties appsFlyerProperties = (AppsFlyerProperties) this.f270i.getValue();
        int i3 = afRDLog + 83;
        afErrorLog = i3 % 128;
        int i4 = i3 % 2;
        return appsFlyerProperties;
    }

    private final SimpleDateFormat AFInAppEventType() {
        int i = afRDLog + 109;
        afErrorLog = i % 128;
        int i2 = i % 2;
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) this.afVerboseLog.getValue();
        int i3 = afRDLog + 123;
        afErrorLog = i3 % 128;
        int i4 = i3 % 2;
        return simpleDateFormat;
    }

    @Override // com.appsflyer.internal.AFg1qSDK
    public final void valueOf(AFa1pSDK aFa1pSDK) {
        int i = afErrorLog + 95;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? 'K' : 'Q') == 'Q') {
            Intrinsics.checkNotNullParameter(aFa1pSDK, "");
            Map<String, Object> valueOf = aFa1pSDK.valueOf();
            if ((aFa1pSDK.values() ? ' ' : '8') != '8') {
                values(aFa1pSDK, aFa1pSDK.f160d, this.force.valueOf, this.force.AFInAppEventParameterName);
                int i2 = afRDLog + 39;
                afErrorLog = i2 % 128;
                int i3 = i2 % 2;
            } else {
                if (!(aFa1pSDK instanceof AFh1lSDK)) {
                    Intrinsics.checkNotNullExpressionValue(valueOf, "");
                    String str = aFa1pSDK.AFLogger;
                    Intrinsics.checkNotNullExpressionValue(str, "");
                    AFInAppEventParameterName(valueOf, str);
                }
            }
            Intrinsics.checkNotNullExpressionValue(valueOf, "");
            AFVersionDeclaration(valueOf);
            m109i(valueOf);
            afVerboseLog(valueOf);
            afWarnLog(valueOf);
            afDebugLog(valueOf);
            AFInAppEventType(valueOf, aFa1pSDK.values());
            afErrorLogForExcManagerOnly(valueOf);
            AFLogger$LogLevel(valueOf);
            valueOf(valueOf, aFa1pSDK);
            valueOf.put("af_events_api", "1");
            return;
        }
        Intrinsics.checkNotNullParameter(aFa1pSDK, "");
        aFa1pSDK.valueOf();
        aFa1pSDK.values();
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    @Override // com.appsflyer.internal.AFg1qSDK
    public final void AFKeystoreWrapper(Map<String, Object> map, boolean z, Function0<String> function0) {
        int i = afRDLog + 67;
        afErrorLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(map, "");
        Intrinsics.checkNotNullParameter(function0, "");
        AFInAppEventType(map);
        values(map);
        afErrorLog(map);
        values(map, z);
        values(map, function0);
        int i3 = afRDLog + 75;
        afErrorLog = i3 % 128;
        if (!(i3 % 2 != 0)) {
            return;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    private final void AFInAppEventType(Map<String, Object> map) {
        try {
            long j = this.values.getPackageManager().getPackageInfo(this.values.getPackageName(), 0).firstInstallTime;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            map.put("installDate", simpleDateFormat.format(new Date(j)));
            int i = afRDLog + 99;
            afErrorLog = i % 128;
            if ((i % 2 != 0 ? '3' : 'A') != '3') {
                return;
            }
            int i2 = 61 / 0;
        } catch (Exception e) {
            AFLogger.afErrorLog("Exception while collecting install date. ", e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0056, code lost:
    
        if ((r1.versionCode > r10.f269e.AFInAppEventParameterName("versionCode", 0)) != true) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void values(java.util.Map<java.lang.String, java.lang.Object> r11) {
        /*
            Method dump skipped, instructions count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.values(java.util.Map):void");
    }

    @Override // com.appsflyer.internal.AFg1qSDK
    public final void AFInAppEventType(AFa1pSDK aFa1pSDK) {
        int i = afRDLog + 67;
        afErrorLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(aFa1pSDK, "");
        Map<String, Object> valueOf = aFa1pSDK.valueOf();
        Intrinsics.checkNotNullExpressionValue(valueOf, "");
        AFInAppEventParameterName(valueOf, aFa1pSDK.values());
        registerClient(valueOf);
        afRDLog(valueOf);
        AFInAppEventParameterName(valueOf);
        values(valueOf, this.force.values);
        afLogForce(valueOf);
        valueOf.put("cell", MapsKt.mapOf(TuplesKt.m1402to("mcc", Integer.valueOf(this.values.getResources().getConfiguration().mcc)), TuplesKt.m1402to("mnc", Integer.valueOf(this.values.getResources().getConfiguration().mnc))));
        valueOf.put("sig", valueOf());
        valueOf.put("last_boot_time", Long.valueOf(m107e()));
        valueOf.put("disk", registerClient());
        int i3 = afRDLog + 91;
        afErrorLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.internal.AFg1qSDK
    public final void valueOf(Map<String, Object> map) {
        int i = afRDLog + 35;
        afErrorLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(map, "");
        String string = AFKeystoreWrapper().getString(AppsFlyerProperties.APP_ID);
        if (!(string == null)) {
            map.put(AppsFlyerProperties.APP_ID, string);
        }
        String string2 = AFKeystoreWrapper().getString(AppsFlyerProperties.CURRENCY_CODE);
        if (string2 != null) {
            if (string2.length() != 3) {
                StringBuilder sb = new StringBuilder("WARNING: currency code should be 3 characters!!! '");
                sb.append(string2);
                sb.append("' is not a legal value.");
                String obj = sb.toString();
                Intrinsics.checkNotNullExpressionValue(obj, "");
                AFLogger.afWarnLog(obj);
            }
            map.put(FirebaseAnalytics.Param.CURRENCY, string2);
        }
        String string3 = AFKeystoreWrapper().getString(AppsFlyerProperties.IS_UPDATE);
        if (string3 != null) {
            int i3 = afErrorLog + 21;
            afRDLog = i3 % 128;
            int i4 = i3 % 2;
            map.put("isUpdate", string3);
        }
        String string4 = AFKeystoreWrapper().getString(AppsFlyerProperties.ADDITIONAL_CUSTOM_DATA);
        if ((string4 != null ? (char) 5 : '#') != '#') {
            map.put("customData", string4);
        }
        String string5 = AFKeystoreWrapper().getString(AppsFlyerProperties.APP_USER_ID);
        if (string5 != null) {
            int i5 = afRDLog + 5;
            afErrorLog = i5 % 128;
            int i6 = i5 % 2;
            map.put("appUserId", string5);
        }
        String string6 = AFKeystoreWrapper().getString(AppsFlyerProperties.USER_EMAILS);
        if (string6 != null) {
            map.put("user_emails", string6);
            int i7 = afRDLog + 93;
            afErrorLog = i7 % 128;
            int i8 = i7 % 2;
        }
        AFd1ySDK aFd1ySDK = this.force.AFInAppEventType;
        if (aFd1ySDK != null) {
            int i9 = afRDLog + 11;
            afErrorLog = i9 % 128;
            int i10 = i9 % 2;
            String[] strArr = aFd1ySDK.AFInAppEventType;
            if (strArr != null) {
                map.put("sharing_filter", strArr);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0045, code lost:
    
        r0 = r8.AFLogger.valueOf.f189e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x004b, code lost:
    
        if (r0 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x004d, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0050, code lost:
    
        if (r5 == true) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0052, code lost:
    
        r9 = com.appsflyer.internal.AFg1lSDK.afRDLog + 47;
        r0 = r9 % 128;
        com.appsflyer.internal.AFg1lSDK.afErrorLog = r0;
        r9 = r9 % 2;
        r0 = r0 + 23;
        com.appsflyer.internal.AFg1lSDK.afRDLog = r0 % 128;
        r0 = r0 % 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0062, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0063, code lost:
    
        r5 = r0.values;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0067, code lost:
    
        if (r5 == null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0069, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x006c, code lost:
    
        if (r6 == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x006f, code lost:
    
        r6 = com.appsflyer.internal.AFg1lSDK.afErrorLog + 15;
        com.appsflyer.internal.AFg1lSDK.afRDLog = r6 % 128;
        r6 = r6 % 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x007c, code lost:
    
        if (r5.length() != 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0080, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0081, code lost:
    
        if (r5 != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0083, code lost:
    
        r9.AFInAppEventType("gaidError", r0.values);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x008c, code lost:
    
        if (r0.AFInAppEventType == null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x008e, code lost:
    
        r5 = com.appsflyer.internal.AFg1lSDK.afErrorLog + 95;
        com.appsflyer.internal.AFg1lSDK.afRDLog = r5 % 128;
        r5 = r5 % 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0099, code lost:
    
        if (r0.valueOf == null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x009b, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x009e, code lost:
    
        if (r5 == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00a0, code lost:
    
        r9.AFInAppEventType("advertiserId", r0.AFInAppEventType);
        r9.AFInAppEventType("advertiserIdEnabled", java.lang.String.valueOf(r0.valueOf));
        r9.AFInAppEventType("isGaidWithGps", java.lang.String.valueOf(r0.AFInAppEventParameterName));
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x009d, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x007e, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x006b, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x004f, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x002f, code lost:
    
        if (r8.AFLogger.unregisterClient() == false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
    
        if ((!r8.AFLogger.unregisterClient()) != true) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0031, code lost:
    
        r0 = com.appsflyer.internal.AFb1tSDK.values(r9.valueOf());
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "");
        r0.put("ad_ids_disabled", java.lang.Boolean.TRUE);
     */
    @Override // com.appsflyer.internal.AFg1qSDK
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void values(com.appsflyer.internal.AFa1pSDK r9) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.values(com.appsflyer.internal.AFa1pSDK):void");
    }

    private static PackageInfo valueOf(PackageManager packageManager, String str) {
        int i = afErrorLog + 37;
        afRDLog = i % 128;
        int i2 = i % 2;
        if (!(Build.VERSION.SDK_INT < 33)) {
            int i3 = afRDLog + 43;
            afErrorLog = i3 % 128;
            PackageInfo packageInfo = packageManager.getPackageInfo(str, PackageManager.PackageInfoFlags.of(i3 % 2 == 0 ? 0L : 1L));
            Intrinsics.checkNotNullExpressionValue(packageInfo, "");
            return packageInfo;
        }
        PackageInfo packageInfo2 = packageManager.getPackageInfo(str, 0);
        Intrinsics.checkNotNullExpressionValue(packageInfo2, "");
        int i4 = afErrorLog + 103;
        afRDLog = i4 % 128;
        int i5 = i4 % 2;
        return packageInfo2;
    }

    @Override // com.appsflyer.internal.AFg1qSDK
    public final Long AFInAppEventParameterName() {
        int i = afErrorLog + 29;
        afRDLog = i % 128;
        int i2 = i % 2;
        Context context = this.afInfoLog.valueOf;
        if (context != null) {
            PackageManager packageManager = context.getPackageManager();
            if (!(packageManager == null)) {
                String packageName = context.getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "");
                PackageInfo valueOf = valueOf(packageManager, packageName);
                if (valueOf != null) {
                    Long valueOf2 = Long.valueOf(valueOf.firstInstallTime);
                    int i3 = afErrorLog + 111;
                    afRDLog = i3 % 128;
                    if (i3 % 2 != 0) {
                        return valueOf2;
                    }
                    throw null;
                }
            }
            int i4 = afErrorLog + 53;
            afRDLog = i4 % 128;
            int i5 = i4 % 2;
        }
        return null;
    }

    private String valueOf() throws CertificateException, NoSuchAlgorithmException, PackageManager.NameNotFoundException {
        int i = afErrorLog + 59;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? 'b' : '&') != '&') {
            AFb1uSDK.AFInAppEventParameterName(this.values.getApplicationContext().getPackageManager(), this.values.getApplicationContext().getPackageName());
            throw null;
        }
        String AFInAppEventParameterName = AFb1uSDK.AFInAppEventParameterName(this.values.getApplicationContext().getPackageManager(), this.values.getApplicationContext().getPackageName());
        int i2 = afRDLog + 83;
        afErrorLog = i2 % 128;
        int i3 = i2 % 2;
        return AFInAppEventParameterName;
    }

    /* renamed from: e */
    private static long m107e() {
        int i = afRDLog + 99;
        afErrorLog = i % 128;
        int i2 = i % 2;
        long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        int i3 = afErrorLog + 123;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
        return currentTimeMillis;
    }

    @Override // com.appsflyer.internal.AFg1qSDK
    public final long values() {
        int i = afErrorLog + 95;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? ',' : 'R') != 'R') {
            System.currentTimeMillis();
            Object obj = null;
            obj.hashCode();
            throw null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = afErrorLog + 27;
        afRDLog = i2 % 128;
        int i3 = i2 % 2;
        return currentTimeMillis;
    }

    private static String registerClient() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        long blockSizeLong = statFs.getBlockSizeLong();
        long availableBlocksLong = statFs.getAvailableBlocksLong() * blockSizeLong;
        long blockCountLong = statFs.getBlockCountLong() * blockSizeLong;
        double pow = Math.pow(2.0d, 20.0d);
        StringBuilder sb = new StringBuilder();
        sb.append((long) (availableBlocksLong / pow));
        sb.append('/');
        sb.append((long) (blockCountLong / pow));
        String obj = sb.toString();
        int i = afErrorLog + 123;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? '\n' : '6') != '\n') {
            return obj;
        }
        int i2 = 65 / 0;
        return obj;
    }

    private void AFInAppEventType(Map<String, Object> map, boolean z) {
        int i = afRDLog + 123;
        afErrorLog = i % 128;
        if ((i % 2 != 0 ? (char) 2 : (char) 18) != 18) {
            Intrinsics.checkNotNullParameter(map, "");
            map.put("platformextension", this.f272w.valueOf());
            int i2 = 6 / 0;
            if ((z ? ')' : 'G') != ')') {
                return;
            }
        } else {
            Intrinsics.checkNotNullParameter(map, "");
            map.put("platformextension", this.f272w.valueOf());
            if (z ? false : true) {
                return;
            }
        }
        int i3 = afErrorLog + 121;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
        map.put("platform_extension_v2", this.AFInAppEventType.valueOf());
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0060, code lost:
    
        if (r5.AFLogger.AFKeystoreWrapper.AFInAppEventParameterName("appsFlyerCount", 0) <= 5) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0070, code lost:
    
        r0.putAll(r5.valueOf.AFKeystoreWrapper());
        r7 = com.appsflyer.internal.AFg1lSDK.afRDLog + 93;
        com.appsflyer.internal.AFg1lSDK.afErrorLog = r7 % 128;
        r7 = r7 % 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x006e, code lost:
    
        if (r5.AFLogger.AFKeystoreWrapper.AFInAppEventParameterName("appsFlyerCount", 0) <= 2) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void AFInAppEventParameterName(java.util.Map<java.lang.String, java.lang.Object> r6, boolean r7) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            java.lang.String r1 = "ro.product.cpu.abi"
            java.lang.String r1 = AFKeystoreWrapper(r1)
            java.lang.String r2 = "cpu_abi"
            r0.put(r2, r1)
            java.lang.String r1 = "ro.product.cpu.abi2"
            java.lang.String r1 = AFKeystoreWrapper(r1)
            java.lang.String r2 = "cpu_abi2"
            r0.put(r2, r1)
            java.lang.String r1 = "os.arch"
            java.lang.String r1 = AFKeystoreWrapper(r1)
            java.lang.String r2 = "arch"
            r0.put(r2, r1)
            java.lang.String r1 = "ro.build.display.id"
            java.lang.String r1 = AFKeystoreWrapper(r1)
            java.lang.String r2 = "build_display_id"
            r0.put(r2, r1)
            r1 = 1
            r2 = 0
            if (r7 == 0) goto L3e
            r7 = r1
            goto L3f
        L3e:
            r7 = r2
        L3f:
            if (r7 == 0) goto L82
            int r7 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r7 = r7 + 49
            int r3 = r7 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r3
            r3 = 2
            int r7 = r7 % r3
            if (r7 != 0) goto L4f
            r7 = r2
            goto L50
        L4f:
            r7 = r1
        L50:
            java.lang.String r4 = "appsFlyerCount"
            if (r7 == r1) goto L63
            r5.m105d(r0)
            com.appsflyer.internal.AFd1sSDK r7 = r5.AFLogger
            com.appsflyer.internal.AFd1tSDK r7 = r7.AFKeystoreWrapper
            int r7 = r7.AFInAppEventParameterName(r4, r2)
            r1 = 5
            if (r7 > r1) goto L82
            goto L70
        L63:
            r5.m105d(r0)
            com.appsflyer.internal.AFd1sSDK r7 = r5.AFLogger
            com.appsflyer.internal.AFd1tSDK r7 = r7.AFKeystoreWrapper
            int r7 = r7.AFInAppEventParameterName(r4, r2)
            if (r7 > r3) goto L82
        L70:
            com.appsflyer.internal.AFi1dSDK r7 = r5.valueOf
            java.util.Map r7 = r7.AFKeystoreWrapper()
            r0.putAll(r7)
            int r7 = com.appsflyer.internal.AFg1lSDK.afRDLog
            int r7 = r7 + 93
            int r1 = r7 % 128
            com.appsflyer.internal.AFg1lSDK.afErrorLog = r1
            int r7 = r7 % r3
        L82:
            com.appsflyer.internal.AFb1ySDK r7 = r5.AFKeystoreWrapper
            android.content.Context r1 = r5.values
            java.util.Map r7 = r7.AFInAppEventType(r1)
            java.lang.String r1 = "dim"
            r0.put(r1, r7)
            java.lang.String r7 = "deviceData"
            r6.put(r7, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.AFInAppEventParameterName(java.util.Map, boolean):void");
    }

    @Override // com.appsflyer.internal.AFg1qSDK
    public final void AFInAppEventParameterName(Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "");
        AFh1dSDK aFh1dSDK = this.unregisterClient.values;
        AFi1zSDK AFInAppEventType = aFh1dSDK == null ? null : aFh1dSDK.AFInAppEventType();
        if ((AFInAppEventType != null ? '8' : (char) 2) != 2) {
            map.put("network", AFInAppEventType.AFInAppEventParameterName);
            map.put("ivc", Boolean.valueOf(AFInAppEventType.AFInAppEventParameterName()));
            if (!AFKeystoreWrapper().getBoolean(AppsFlyerProperties.DISABLE_NETWORK_DATA, false)) {
                String str = AFInAppEventType.valueOf;
                if ((str != null ? '\f' : '-') != '-') {
                    map.put("operator", str);
                    int i = afRDLog + 43;
                    afErrorLog = i % 128;
                    int i2 = i % 2;
                }
                String str2 = AFInAppEventType.AFKeystoreWrapper;
                if (str2 != null) {
                    map.put("carrier", str2);
                    int i3 = afErrorLog + 9;
                    afRDLog = i3 % 128;
                    int i4 = i3 % 2;
                }
            }
        }
    }

    @Override // com.appsflyer.internal.AFg1qSDK
    public final void AFInAppEventParameterName(Map<String, Object> map, int i, int i2) {
        boolean z;
        int i3 = afErrorLog + 77;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
        Intrinsics.checkNotNullParameter(map, "");
        map.put("counter", String.valueOf(i));
        map.put("iaecounter", String.valueOf(i2));
        if ((!m114w() ? (char) 17 : '\t') != 17) {
            int i5 = afRDLog + 123;
            afErrorLog = i5 % 128;
            int i6 = i5 % 2;
            z = false;
        } else {
            int i7 = afErrorLog + 101;
            afRDLog = i7 % 128;
            int i8 = i7 % 2;
            z = true;
        }
        map.put("isFirstCall", String.valueOf(z));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    @Override // com.appsflyer.internal.AFg1qSDK
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void AFKeystoreWrapper(com.appsflyer.internal.AFa1pSDK r6) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.util.Map r1 = r6.valueOf()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            java.lang.String r0 = "open_referrer"
            java.lang.String r2 = r6.values
            r1.put(r0, r2)
            java.lang.String r0 = r6.f161e
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L1d
            r4 = r3
            goto L1e
        L1d:
            r4 = r2
        L1e:
            if (r4 == 0) goto L37
            boolean r0 = kotlin.text.StringsKt.isBlank(r0)
            if (r0 == 0) goto L28
            r0 = r3
            goto L29
        L28:
            r0 = r2
        L29:
            if (r0 == r3) goto L37
            int r0 = com.appsflyer.internal.AFg1lSDK.afRDLog
            int r0 = r0 + 101
            int r4 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afErrorLog = r4
            int r0 = r0 % 2
            r0 = r2
            goto L38
        L37:
            r0 = r3
        L38:
            if (r0 != 0) goto L3b
            goto L3c
        L3b:
            r2 = r3
        L3c:
            if (r2 == r3) goto L4f
            int r0 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r0 = r0 + 89
            int r2 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r2
            int r0 = r0 % 2
            java.lang.String r0 = "af_web_referrer"
            java.lang.String r6 = r6.f161e
            r1.put(r0, r6)
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.AFKeystoreWrapper(com.appsflyer.internal.AFa1pSDK):void");
    }

    /* renamed from: d */
    private final void m105d(Map<String, Object> map) {
        int i = afRDLog + 91;
        afErrorLog = i % 128;
        Object obj = null;
        if (i % 2 == 0) {
            AFd1uSDK.AFa1zSDK values = this.AFInAppEventParameterName.values(this.values);
            float f = values.AFKeystoreWrapper;
            String str = values.values;
            map.put("btl", String.valueOf(f));
            if ((str != null ? '$' : 'F') != '$') {
                return;
            }
            int i2 = afRDLog + 91;
            afErrorLog = i2 % 128;
            if ((i2 % 2 != 0 ? 'T' : 'I') == 'I') {
                map.put("btch", str);
                return;
            } else {
                map.put("btch", str);
                throw null;
            }
        }
        AFd1uSDK.AFa1zSDK values2 = this.AFInAppEventParameterName.values(this.values);
        float f2 = values2.AFKeystoreWrapper;
        String str2 = values2.values;
        map.put("btl", String.valueOf(f2));
        obj.hashCode();
        throw null;
    }

    private void AFLogger(Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "");
        String string = AFKeystoreWrapper().getString(AppsFlyerProperties.ONELINK_ID);
        String string2 = AFKeystoreWrapper().getString(AppsFlyerProperties.ONELINK_VERSION);
        if ((string != null ? (char) 18 : 'B') == 18) {
            int i = afErrorLog + 111;
            afRDLog = i % 128;
            int i2 = i % 2;
            map.put("onelink_id", string);
        }
        if ((string2 != null ? ')' : '\\') != ')') {
            return;
        }
        int i3 = afRDLog + 53;
        afErrorLog = i3 % 128;
        int i4 = i3 % 2;
        map.put("onelink_ver", string2);
    }

    /* renamed from: e */
    private void m108e(Map<String, ? extends Object> map) {
        boolean z;
        Intrinsics.checkNotNullParameter(map, "");
        AFg1cSDK aFg1cSDK = this.registerClient;
        HashMap hashMap = new HashMap(aFg1cSDK.values);
        aFg1cSDK.values.clear();
        this.registerClient.AFInAppEventType.AFInAppEventType("gcd");
        Intrinsics.checkNotNullExpressionValue(hashMap, "");
        if ((!hashMap.isEmpty() ? Typography.amp : (char) 19) != 19) {
            int i = afRDLog + 13;
            afErrorLog = i % 128;
            int i2 = i % 2;
            z = true;
        } else {
            z = false;
        }
        if (z) {
            Map<String, Object> values = AFb1tSDK.values(map);
            Intrinsics.checkNotNullExpressionValue(values, "");
            values.put("gcd", hashMap);
            int i3 = afErrorLog + 125;
            afRDLog = i3 % 128;
            int i4 = i3 % 2;
        }
        int i5 = afRDLog + 53;
        afErrorLog = i5 % 128;
        if ((i5 % 2 != 0 ? (char) 26 : 'T') != 26) {
        } else {
            throw null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0060, code lost:
    
        r7.f269e.valueOf("prev_event_name", r9);
        r7.f269e.AFInAppEventParameterName("prev_event_timestamp", java.lang.System.currentTimeMillis());
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x006e, code lost:
    
        r8 = com.appsflyer.internal.AFg1lSDK.afRDLog + 33;
        com.appsflyer.internal.AFg1lSDK.afErrorLog = r8 % 128;
        r8 = r8 % 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0078, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003e, code lost:
    
        r1 = new org.json.JSONObject();
        r1.put("prev_event_timestamp", r7.f269e.AFKeystoreWrapper("prev_event_timestamp", -1));
        r1.put("prev_event_name", r0);
        r8.put("prev_event", r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0056, code lost:
    
        r8 = com.appsflyer.internal.AFg1lSDK.afErrorLog + 1;
        com.appsflyer.internal.AFg1lSDK.afRDLog = r8 % 128;
        r8 = r8 % 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003c, code lost:
    
        if (r0 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0027, code lost:
    
        if (r0 != null) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void AFInAppEventParameterName(java.util.Map<java.lang.String, java.lang.Object> r8, java.lang.String r9) {
        /*
            r7 = this;
            int r0 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r0 = r0 + 53
            int r1 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r1
            int r0 = r0 % 2
            r1 = 91
            if (r0 != 0) goto L10
            r0 = r1
            goto L12
        L10:
            r0 = 90
        L12:
            r2 = 0
            java.lang.String r3 = "prev_event_timestamp"
            java.lang.String r4 = "prev_event_name"
            java.lang.String r5 = ""
            if (r0 == r1) goto L2c
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r5)
            com.appsflyer.internal.AFd1tSDK r0 = r7.f269e     // Catch: java.lang.Exception -> L2a
            java.lang.String r0 = r0.AFKeystoreWrapper(r4, r2)     // Catch: java.lang.Exception -> L2a
            if (r0 == 0) goto L60
            goto L3e
        L2a:
            r8 = move-exception
            goto L7b
        L2c:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r5)
            com.appsflyer.internal.AFd1tSDK r0 = r7.f269e     // Catch: java.lang.Exception -> L2a
            java.lang.String r0 = r0.AFKeystoreWrapper(r4, r2)     // Catch: java.lang.Exception -> L2a
            r1 = 9
            int r1 = r1 / 0
            if (r0 == 0) goto L60
        L3e:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> L2a
            r1.<init>()     // Catch: java.lang.Exception -> L2a
            com.appsflyer.internal.AFd1tSDK r2 = r7.f269e     // Catch: java.lang.Exception -> L2a
            r5 = -1
            long r5 = r2.AFKeystoreWrapper(r3, r5)     // Catch: java.lang.Exception -> L2a
            r1.put(r3, r5)     // Catch: java.lang.Exception -> L2a
            r1.put(r4, r0)     // Catch: java.lang.Exception -> L2a
            java.lang.String r0 = "prev_event"
            r8.put(r0, r1)     // Catch: java.lang.Exception -> L2a
            int r8 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r8 = r8 + 1
            int r0 = r8 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r0
            int r8 = r8 % 2
        L60:
            com.appsflyer.internal.AFd1tSDK r8 = r7.f269e     // Catch: java.lang.Exception -> L2a
            r8.valueOf(r4, r9)     // Catch: java.lang.Exception -> L2a
            com.appsflyer.internal.AFd1tSDK r8 = r7.f269e     // Catch: java.lang.Exception -> L2a
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L2a
            r8.AFInAppEventParameterName(r3, r0)     // Catch: java.lang.Exception -> L2a
            int r8 = com.appsflyer.internal.AFg1lSDK.afRDLog
            int r8 = r8 + 33
            int r9 = r8 % 128
            com.appsflyer.internal.AFg1lSDK.afErrorLog = r9
            int r8 = r8 % 2
            return
        L79:
            r8 = move-exception
            throw r8
        L7b:
            java.lang.String r9 = "Error while processing previous event."
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            com.appsflyer.AFLogger.afErrorLog(r9, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.AFInAppEventParameterName(java.util.Map, java.lang.String):void");
    }

    private String unregisterClient() {
        String str = null;
        if ((this.f269e.valueOf("INSTALL_STORE") ? 'H' : '*') == '*') {
            if (!(!m106d())) {
                str = AFLogger();
            } else {
                int i = afRDLog + 119;
                afErrorLog = i % 128;
                int i2 = i % 2;
            }
            this.f269e.valueOf("INSTALL_STORE", str);
            int i3 = afRDLog + 5;
            afErrorLog = i3 % 128;
            int i4 = i3 % 2;
            return str;
        }
        int i5 = afErrorLog + 45;
        afRDLog = i5 % 128;
        int i6 = i5 % 2;
        return this.f269e.AFKeystoreWrapper("INSTALL_STORE", (String) null);
    }

    private String AFLogger() {
        int i = afErrorLog + 37;
        afRDLog = i % 128;
        int i2 = i % 2;
        String string = AFKeystoreWrapper().getString(AppsFlyerProperties.AF_STORE_FROM_API);
        if ((string == null ? Typography.dollar : (char) 11) != 11) {
            string = valueOf("AF_STORE");
        }
        int i3 = afRDLog + 35;
        afErrorLog = i3 % 128;
        int i4 = i3 % 2;
        return string;
    }

    private String values(SimpleDateFormat simpleDateFormat) {
        int i = afErrorLog + 113;
        afRDLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(simpleDateFormat, "");
        Object obj = null;
        String AFKeystoreWrapper = this.f269e.AFKeystoreWrapper("appsFlyerFirstInstall", (String) null);
        if ((AFKeystoreWrapper == null ? (char) 15 : (char) 0) != 0) {
            int i3 = afErrorLog + 111;
            afRDLog = i3 % 128;
            if ((i3 % 2 == 0 ? 'V' : (char) 26) == 26) {
                if (m106d()) {
                    AFLogger.afDebugLog("AppsFlyer: first launch detected");
                    AFKeystoreWrapper = simpleDateFormat.format(new Date());
                } else {
                    AFKeystoreWrapper = "";
                }
                this.f269e.valueOf("appsFlyerFirstInstall", AFKeystoreWrapper);
            } else {
                m106d();
                obj.hashCode();
                throw null;
            }
        }
        AFg1hSDK.i$default(AFLogger.INSTANCE, AFg1gSDK.GENERAL, "AppsFlyer: first launch date: ".concat(String.valueOf(AFKeystoreWrapper)), false, 4, null);
        Intrinsics.checkNotNullExpressionValue(AFKeystoreWrapper, "");
        return AFKeystoreWrapper;
    }

    /* renamed from: d */
    private boolean m106d() {
        if ((!this.f269e.valueOf("appsFlyerCount") ? 'c' : (char) 11) != 11) {
            int i = afRDLog + 69;
            afErrorLog = i % 128;
            int i2 = i % 2;
            return true;
        }
        int i3 = afRDLog + 31;
        afErrorLog = i3 % 128;
        int i4 = i3 % 2;
        return false;
    }

    /* renamed from: w */
    private boolean m114w() {
        int i = afErrorLog + 53;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? '9' : 'D') == '9') {
            Boolean.parseBoolean(this.f269e.AFKeystoreWrapper("sentSuccessfully", (String) null));
            throw null;
        }
        boolean parseBoolean = Boolean.parseBoolean(this.f269e.AFKeystoreWrapper("sentSuccessfully", (String) null));
        int i2 = afErrorLog + 105;
        afRDLog = i2 % 128;
        int i3 = i2 % 2;
        return parseBoolean;
    }

    private String force() {
        int i = afErrorLog + 85;
        afRDLog = i % 128;
        if (!(i % 2 != 0)) {
            AFKeystoreWrapper().getString("preInstallName");
            throw null;
        }
        String string = AFKeystoreWrapper().getString("preInstallName");
        if (!(string == null)) {
            int i2 = afRDLog + 27;
            afErrorLog = i2 % 128;
            if (i2 % 2 != 0) {
                int i3 = 89 / 0;
            }
            return string;
        }
        if (this.f269e.valueOf("preInstallName")) {
            string = this.f269e.AFKeystoreWrapper("preInstallName", (String) null);
        } else {
            if (m106d() && (string = afInfoLog()) == null) {
                string = valueOf("AF_PRE_INSTALL_NAME");
            }
            if (string != null) {
                int i4 = afErrorLog + 87;
                afRDLog = i4 % 128;
                int i5 = i4 % 2;
                this.f269e.valueOf("preInstallName", string);
                int i6 = afRDLog + 57;
                afErrorLog = i6 % 128;
                int i7 = i6 % 2;
            }
        }
        if (!(string == null)) {
            AFKeystoreWrapper().set("preInstallName", string);
        }
        return string;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void values(java.util.Map<java.lang.String, java.lang.Object> r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.values(java.util.Map, java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0067, code lost:
    
        if (r7 != null) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.String AFInAppEventParameterName(java.lang.String r7) {
        /*
            r6 = this;
            int r0 = com.appsflyer.internal.AFg1lSDK.afRDLog
            r1 = 1
            int r0 = r0 + r1
            int r2 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afErrorLog = r2
            int r0 = r0 % 2
            com.appsflyer.AppsFlyerProperties r0 = r6.AFKeystoreWrapper()
            java.lang.String r2 = "collectAndroidId"
            r3 = 0
            boolean r0 = r0.getBoolean(r2, r3)
            r2 = 0
            if (r0 == 0) goto L67
            r0 = r7
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L40
            int r4 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r4 = r4 + 45
            int r5 = r4 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r5
            int r4 = r4 % 2
            r5 = 89
            if (r4 != 0) goto L2d
            r4 = r5
            goto L2f
        L2d:
            r4 = 47
        L2f:
            if (r4 == r5) goto L3a
            int r0 = r0.length()
            if (r0 != 0) goto L38
            goto L40
        L38:
            r0 = r3
            goto L41
        L3a:
            r0.length()
            throw r2     // Catch: java.lang.Throwable -> L3e
        L3e:
            r7 = move-exception
            throw r7
        L40:
            r0 = r1
        L41:
            if (r0 == 0) goto L67
            int r7 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r7 = r7 + 81
            int r0 = r7 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r0
            int r7 = r7 % 2
            if (r7 == 0) goto L5e
            boolean r7 = r6.m110i()
            if (r7 == 0) goto L56
            goto L57
        L56:
            r1 = r3
        L57:
            if (r1 == 0) goto L6a
            java.lang.String r7 = r6.m111v()
            goto L6b
        L5e:
            r6.m110i()
            r2.hashCode()     // Catch: java.lang.Throwable -> L65
            throw r2     // Catch: java.lang.Throwable -> L65
        L65:
            r7 = move-exception
            throw r7
        L67:
            if (r7 == 0) goto L6a
            goto L6b
        L6a:
            r7 = r2
        L6b:
            int r0 = com.appsflyer.internal.AFg1lSDK.afRDLog
            int r0 = r0 + 39
            int r1 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afErrorLog = r1
            int r0 = r0 % 2
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.AFInAppEventParameterName(java.lang.String):java.lang.String");
    }

    /* renamed from: v */
    private final String m111v() {
        Object obj = null;
        String AFKeystoreWrapper = this.f269e.AFKeystoreWrapper("androidIdCached", (String) null);
        try {
            String string = Settings.Secure.getString(this.values.getContentResolver(), "android_id");
            if ((string != null ? (char) 14 : (char) 23) != 23) {
                int i = afRDLog + 9;
                afErrorLog = i % 128;
                int i2 = i % 2;
                return string;
            }
        } catch (Exception e) {
            AFLogger.afErrorLog(e.getMessage(), e);
        }
        if ((AFKeystoreWrapper != null ? (char) 18 : (char) 19) != 18) {
            int i3 = afErrorLog + 43;
            afRDLog = i3 % 128;
            int i4 = i3 % 2;
            return null;
        }
        int i5 = afRDLog + 103;
        afErrorLog = i5 % 128;
        if ((i5 % 2 == 0 ? (char) 16 : (char) 18) == 16) {
            AFLogger.afDebugLog("use cached AndroidId: ".concat(String.valueOf(AFKeystoreWrapper)));
            return AFKeystoreWrapper;
        }
        AFLogger.afDebugLog("use cached AndroidId: ".concat(String.valueOf(AFKeystoreWrapper)));
        obj.hashCode();
        throw null;
    }

    private static void registerClient(Map<String, Object> map) {
        int i = afRDLog + 41;
        afErrorLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(map, "");
        Object[] objArr = new Object[1];
        m104a("蟤螆⫚㰁娚\udd04蓒횪䕈", TextUtils.indexOf("", ""), objArr);
        map.put(((String) objArr[0]).intern(), Build.BRAND);
        map.put(DeviceRequestsHelper.DEVICE_INFO_DEVICE, Build.DEVICE);
        map.put("product", Build.PRODUCT);
        map.put(ServerProtocol.DIALOG_PARAM_SDK_VERSION, String.valueOf(Build.VERSION.SDK_INT));
        map.put("model", Build.MODEL);
        map.put("deviceType", Build.TYPE);
        int i3 = afRDLog + 81;
        afErrorLog = i3 % 128;
        int i4 = i3 % 2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002a, code lost:
    
        if ((r7.AFKeystoreWrapper() != com.appsflyer.internal.AFf1zSDK.CONVERSION) != false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0043, code lost:
    
        r7 = com.appsflyer.internal.AFg1lSDK.afErrorLog + 11;
        com.appsflyer.internal.AFg1lSDK.afRDLog = r7 % 128;
        r7 = r7 % 2;
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "");
        unregisterClient(r0);
        m112v(r0);
        force(r0);
        com.appsflyer.internal.AFb1gSDK.AFKeystoreWrapper(r6.afInfoLog, r6.f269e);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0060, code lost:
    
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "");
        afInfoLog(r0);
        AFLogger(r0);
        m108e(r0);
        AFInAppEventType(r0, r9);
        valueOf(r0, r8);
        m113w(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0075, code lost:
    
        if (r10 == null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0078, code lost:
    
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0079, code lost:
    
        if (r1 == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x007b, code lost:
    
        r7 = com.appsflyer.internal.AFg1lSDK.afRDLog + 5;
        com.appsflyer.internal.AFg1lSDK.afErrorLog = r7 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0085, code lost:
    
        if ((r7 % 2) == 0) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0087, code lost:
    
        r10.AFKeystoreWrapper(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x008c, code lost:
    
        r7 = 29 / 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x008d, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0090, code lost:
    
        r10.AFKeystoreWrapper(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0093, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0041, code lost:
    
        if ((r7.AFKeystoreWrapper() == com.appsflyer.internal.AFf1zSDK.CONVERSION) != false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void values(com.appsflyer.internal.AFa1pSDK r7, java.lang.String r8, java.lang.String r9, com.appsflyer.internal.AFc1dSDK r10) {
        /*
            r6 = this;
            int r0 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r0 = r0 + 83
            int r1 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r1
            int r0 = r0 % 2
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L10
            r0 = r1
            goto L11
        L10:
            r0 = r2
        L11:
            java.lang.String r3 = ""
            if (r0 == 0) goto L2f
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r3)
            java.util.Map r0 = r7.valueOf()
            com.appsflyer.internal.AFf1zSDK r7 = r7.AFKeystoreWrapper()
            com.appsflyer.internal.AFf1zSDK r4 = com.appsflyer.internal.AFf1zSDK.CONVERSION
            r5 = 90
            int r5 = r5 / r2
            if (r7 != r4) goto L29
            r7 = r2
            goto L2a
        L29:
            r7 = r1
        L2a:
            if (r7 == 0) goto L43
            goto L60
        L2d:
            r7 = move-exception
            throw r7
        L2f:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r3)
            java.util.Map r0 = r7.valueOf()
            com.appsflyer.internal.AFf1zSDK r7 = r7.AFKeystoreWrapper()
            com.appsflyer.internal.AFf1zSDK r4 = com.appsflyer.internal.AFf1zSDK.CONVERSION
            if (r7 != r4) goto L40
            r7 = r1
            goto L41
        L40:
            r7 = r2
        L41:
            if (r7 == 0) goto L60
        L43:
            int r7 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r7 = r7 + 11
            int r4 = r7 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r4
            int r7 = r7 % 2
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            r6.unregisterClient(r0)
            r6.m112v(r0)
            r6.force(r0)
            com.appsflyer.internal.AFd1kSDK r7 = r6.afInfoLog
            com.appsflyer.internal.AFd1tSDK r4 = r6.f269e
            com.appsflyer.internal.AFb1gSDK.AFKeystoreWrapper(r7, r4)
        L60:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            r6.afInfoLog(r0)
            r6.AFLogger(r0)
            r6.m108e(r0)
            AFInAppEventType(r0, r9)
            r6.valueOf(r0, r8)
            r6.m113w(r0)
            if (r10 == 0) goto L78
            goto L79
        L78:
            r1 = r2
        L79:
            if (r1 == 0) goto L93
            int r7 = com.appsflyer.internal.AFg1lSDK.afRDLog
            int r7 = r7 + 5
            int r8 = r7 % 128
            com.appsflyer.internal.AFg1lSDK.afErrorLog = r8
            int r7 = r7 % 2
            if (r7 == 0) goto L90
            r10.AFKeystoreWrapper(r0)
            r7 = 29
            int r7 = r7 / r2
            return
        L8e:
            r7 = move-exception
            throw r7
        L90:
            r10.AFKeystoreWrapper(r0)
        L93:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.values(com.appsflyer.internal.AFa1pSDK, java.lang.String, java.lang.String, com.appsflyer.internal.AFc1dSDK):void");
    }

    private final void unregisterClient(Map<String, Object> map) {
        int i = afErrorLog + 3;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? (char) 21 : 'W') != 21) {
            if ((!AFKeystoreWrapper().isOtherSdkStringDisabled() ? Typography.greater : 'Z') == 'Z') {
                return;
            }
        } else {
            int i2 = 20 / 0;
            if (AFKeystoreWrapper().isOtherSdkStringDisabled()) {
                return;
            }
        }
        map.put("batteryLevel", String.valueOf(this.AFInAppEventParameterName.values(this.values).AFKeystoreWrapper));
        int i3 = afErrorLog + 75;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
    }

    /* renamed from: v */
    private final void m112v(Map<String, Object> map) {
        int i = afRDLog + 77;
        afErrorLog = i % 128;
        if (!(i % 2 != 0)) {
            UiModeManager uiModeManager = (UiModeManager) this.values.getSystemService(UiModeManager.class);
            if (uiModeManager != null) {
                int i2 = afErrorLog + 119;
                afRDLog = i2 % 128;
                int i3 = i2 % 2;
                if ((uiModeManager.getCurrentModeType() == 4 ? 'V' : 'C') != 'V') {
                    return;
                }
                map.put("tv", Boolean.TRUE);
                return;
            }
            return;
        }
        throw null;
    }

    private final void force(Map<String, Object> map) {
        int i = afErrorLog + 43;
        afRDLog = i % 128;
        if (i % 2 != 0) {
            if (!(AFg1kSDK.AFInAppEventType(this.values))) {
                return;
            }
            int i2 = afErrorLog + 63;
            afRDLog = i2 % 128;
            int i3 = i2 % 2;
            map.put("inst_app", Boolean.TRUE);
            return;
        }
        AFg1kSDK.AFInAppEventType(this.values);
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0061, code lost:
    
        r0 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004f, code lost:
    
        r0 = com.appsflyer.internal.AFg1lSDK.afRDLog + 27;
        com.appsflyer.internal.AFg1lSDK.afErrorLog = r0 % 128;
        r0 = r0 % 2;
        r0 = java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(r6 - r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004d, code lost:
    
        if (r4 > 1) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0034, code lost:
    
        if ((r4 <= 0) != true) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void afInfoLog(java.util.Map<java.lang.String, java.lang.Object> r9) {
        /*
            r8 = this;
            int r0 = com.appsflyer.internal.AFg1lSDK.afRDLog
            int r0 = r0 + 7
            int r1 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afErrorLog = r1
            int r0 = r0 % 2
            r1 = 70
            if (r0 == 0) goto L10
            r0 = r1
            goto L12
        L10:
            r0 = 63
        L12:
            java.lang.String r2 = ""
            java.lang.String r3 = "AppsFlyerTimePassedSincePrevLaunch"
            if (r0 == r1) goto L37
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r2)
            com.appsflyer.internal.AFd1tSDK r0 = r8.f269e
            r1 = 0
            long r4 = r0.AFKeystoreWrapper(r3, r1)
            long r6 = java.lang.System.currentTimeMillis()
            com.appsflyer.internal.AFd1tSDK r0 = r8.f269e
            r0.AFInAppEventParameterName(r3, r6)
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            r1 = 1
            if (r0 <= 0) goto L33
            r0 = 0
            goto L34
        L33:
            r0 = r1
        L34:
            if (r0 == r1) goto L61
            goto L4f
        L37:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r2)
            com.appsflyer.internal.AFd1tSDK r0 = r8.f269e
            r1 = 1
            long r4 = r0.AFKeystoreWrapper(r3, r1)
            long r6 = java.lang.System.currentTimeMillis()
            com.appsflyer.internal.AFd1tSDK r0 = r8.f269e
            r0.AFInAppEventParameterName(r3, r6)
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 <= 0) goto L61
        L4f:
            int r0 = com.appsflyer.internal.AFg1lSDK.afRDLog
            int r0 = r0 + 27
            int r1 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afErrorLog = r1
            int r0 = r0 % 2
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r6 = r6 - r4
            long r0 = r0.toSeconds(r6)
            goto L63
        L61:
            r0 = -1
        L63:
            java.lang.String r2 = "timepassedsincelastlaunch"
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r9.put(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.afInfoLog(java.util.Map):void");
    }

    private static void AFInAppEventType(Map<String, Object> map, String str) {
        int i = afRDLog + 59;
        afErrorLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(map, "");
        if ((str != null ? 'V' : ']') != 'V') {
            return;
        }
        map.put("phone", str);
        int i3 = afErrorLog + 41;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0091, code lost:
    
        if (r0.length() == 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a2, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x009f, code lost:
    
        if ((r0.length() != 0) != true) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void valueOf(java.util.Map<java.lang.String, java.lang.Object> r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 196
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.valueOf(java.util.Map, java.lang.String):void");
    }

    /* renamed from: w */
    private void m113w(Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "");
        long j = this.registerClient.f257i;
        if (!(j == 0)) {
            int i = afRDLog + 71;
            afErrorLog = i % 128;
            int i2 = i % 2;
            map.put("prev_session_dur", Long.valueOf(j));
        }
        int i3 = afErrorLog + 29;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
    }

    /* renamed from: i */
    private static void m109i(Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "");
        AFb1gSDK aFb1gSDK = AFb1gSDK.INSTANCE;
        Object AFKeystoreWrapper = AFb1gSDK.AFKeystoreWrapper();
        AFb1gSDK aFb1gSDK2 = AFb1gSDK.INSTANCE;
        String valueOf = AFb1gSDK.valueOf();
        if (!(AFKeystoreWrapper == null)) {
            if (valueOf != null) {
                int i = afRDLog + 101;
                afErrorLog = i % 128;
                if (i % 2 != 0) {
                    Integer.parseInt(valueOf);
                    Object obj = null;
                    obj.hashCode();
                    throw null;
                }
                if (Integer.parseInt(valueOf) > 0) {
                    map.put("reinstallCounter", valueOf);
                    map.put("originalAppsflyerId", AFKeystoreWrapper);
                    int i2 = afErrorLog + 123;
                    afRDLog = i2 % 128;
                    int i3 = i2 % 2;
                }
            }
        }
    }

    private void afVerboseLog(Map<String, Object> map) {
        int i = afErrorLog + 101;
        afRDLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(map, "");
        map.putAll(this.f271v.values());
        int i3 = afErrorLog + 105;
        afRDLog = i3 % 128;
        if (i3 % 2 == 0) {
            throw null;
        }
    }

    private void afWarnLog(Map<String, Object> map) {
        int i = afRDLog + 109;
        afErrorLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(map, "");
        String string = AFKeystoreWrapper().getString(AppsFlyerProperties.EXTENSION);
        String str = string;
        boolean z = true;
        if (str != null) {
            if (!(str.length() == 0)) {
                z = false;
            }
        }
        if (!z) {
            int i3 = afErrorLog + 59;
            afRDLog = i3 % 128;
            int i4 = i3 % 2;
            map.put(AppsFlyerProperties.EXTENSION, string);
            if (i4 == 0) {
                throw null;
            }
        }
        int i5 = afErrorLog + 19;
        afRDLog = i5 % 128;
        if (i5 % 2 == 0) {
            int i6 = 20 / 0;
        }
    }

    private void afErrorLog(Map<String, Object> map) {
        boolean z;
        int i = afRDLog + 51;
        afErrorLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(map, "");
        String AFLogger = this.AFLogger.AFLogger();
        String values = values(this.f269e, AFLogger);
        boolean z2 = (values != null ? '<' : Matrix.MATRIX_TYPE_RANDOM_REGULAR) == '<' && !Intrinsics.areEqual(values, AFLogger);
        if (values != null || AFLogger == null) {
            int i3 = afRDLog + 29;
            afErrorLog = i3 % 128;
            int i4 = i3 % 2;
            z = false;
        } else {
            z = true;
        }
        if ((!z2 ? '%' : (char) 1) != '%' || z) {
            map.put("af_latestchannel", AFLogger);
        }
        String unregisterClient = unregisterClient();
        if (unregisterClient != null) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "");
            Object lowerCase = unregisterClient.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "");
            map.put("af_installstore", lowerCase);
        }
        String force = force();
        if (force != null) {
            int i5 = afErrorLog + 71;
            afRDLog = i5 % 128;
            int i6 = i5 % 2;
            Locale locale2 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale2, "");
            Object lowerCase2 = force.toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "");
            map.put("af_preinstall_name", lowerCase2);
        }
        String AFLogger2 = AFLogger();
        if (AFLogger2 != null) {
            int i7 = afErrorLog + 63;
            afRDLog = i7 % 128;
            int i8 = i7 % 2;
            Locale locale3 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale3, "");
            Object lowerCase3 = AFLogger2.toLowerCase(locale3);
            Intrinsics.checkNotNullExpressionValue(lowerCase3, "");
            map.put("af_currentstore", lowerCase3);
        }
    }

    private static void values(Map<String, Object> map, boolean z) {
        int i = afErrorLog + 49;
        afRDLog = i % 128;
        if (i % 2 == 0) {
            Intrinsics.checkNotNullParameter(map, "");
            map.put("af_preinstalled", String.valueOf(z));
            int i2 = 80 / 0;
        } else {
            Intrinsics.checkNotNullParameter(map, "");
            map.put("af_preinstalled", String.valueOf(z));
        }
        int i3 = afErrorLog + 113;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x003e -> B:9:0x0045). Please report as a decompilation issue!!! */
    private static void afRDLog(Map<String, Object> map) {
        int i = afRDLog + 47;
        afErrorLog = i % 128;
        try {
            if ((i % 2 != 0 ? '@' : '-') != '@') {
                Intrinsics.checkNotNullParameter(map, "");
                map.put("lang", Locale.getDefault().getDisplayLanguage());
            } else {
                Intrinsics.checkNotNullParameter(map, "");
                map.put("lang", Locale.getDefault().getDisplayLanguage());
                int i2 = 12 / 0;
            }
        } catch (Exception e) {
            AFLogger.afErrorLog("Exception while collecting display language name. ", e);
        }
        try {
            map.put("lang_code", Locale.getDefault().getLanguage());
            int i3 = afRDLog + 75;
            afErrorLog = i3 % 128;
            int i4 = i3 % 2;
        } catch (Exception e2) {
            AFLogger.afErrorLog("Exception while collecting display language code. ", e2);
        }
        try {
            map.put(UserDataStore.COUNTRY, Locale.getDefault().getCountry());
        } catch (Exception e3) {
            AFLogger.afErrorLog("Exception while collecting country name. ", e3);
        }
    }

    private void afDebugLog(Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "");
        try {
            String AFInAppEventType = AFb1kSDK.AFInAppEventType(this.afInfoLog, this.f269e);
            if (AFInAppEventType != null) {
                map.put("uid", AFInAppEventType);
                int i = afRDLog + 35;
                afErrorLog = i % 128;
                int i2 = i % 2;
                return;
            }
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("ERROR: could not get uid ");
            sb.append(th.getMessage());
            String obj = sb.toString();
            Intrinsics.checkNotNullExpressionValue(obj, "");
            AFLogger.afErrorLog(obj, th);
        }
        int i3 = afErrorLog + 83;
        afRDLog = i3 % 128;
        if (i3 % 2 == 0) {
            throw null;
        }
    }

    private void afErrorLogForExcManagerOnly(Map<String, Object> map) {
        int i = afRDLog + 119;
        afErrorLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(map, "");
        boolean valueOf = AFg1oSDK.valueOf(this.values);
        AFLogger.afDebugLog("didConfigureTokenRefreshService=".concat(String.valueOf(valueOf)));
        if ((!valueOf ? 'G' : (char) 24) != 24) {
            int i3 = afErrorLog + 85;
            afRDLog = i3 % 128;
            if ((i3 % 2 == 0 ? Typography.less : '!') != '!') {
                map.put("tokenRefreshConfigured", Boolean.FALSE);
                Object obj = null;
                obj.hashCode();
                throw null;
            }
            map.put("tokenRefreshConfigured", Boolean.FALSE);
        }
        map.put("registeredUninstall", Boolean.valueOf(AFg1oSDK.values(this.f269e)));
    }

    private void afLogForce(Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "");
        AFa1bSDK valueOf = AFb1rSDK.valueOf(this.values.getContentResolver());
        if ((valueOf != null ? (char) 17 : '6') == '6') {
            int i = afRDLog + 97;
            afErrorLog = i % 128;
            if (i % 2 != 0) {
                int i2 = 99 / 0;
                return;
            }
            return;
        }
        int i3 = afRDLog + 59;
        afErrorLog = i3 % 128;
        if (i3 % 2 == 0) {
            map.put("amazon_aid", valueOf.AFInAppEventParameterName);
            map.put("amazon_aid_limit", String.valueOf(valueOf.AFKeystoreWrapper));
        } else {
            map.put("amazon_aid", valueOf.AFInAppEventParameterName);
            map.put("amazon_aid_limit", String.valueOf(valueOf.AFKeystoreWrapper));
            Object obj = null;
            obj.hashCode();
            throw null;
        }
    }

    private void AFLogger$LogLevel(Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "");
        if (!(!this.f269e.valueOf("is_stop_tracking_used"))) {
            int i = afRDLog + 119;
            afErrorLog = i % 128;
            int i2 = i % 2;
            map.put("istu", String.valueOf(this.f269e.values("is_stop_tracking_used")));
        }
        int i3 = afRDLog + 55;
        afErrorLog = i3 % 128;
        if ((i3 % 2 != 0 ? '!' : '8') != '8') {
            int i4 = 69 / 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0030, code lost:
    
        if ((r1.length() == 0) != false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0040, code lost:
    
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x003e, code lost:
    
        if ((r1.length() != 0) != false) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void AFVersionDeclaration(java.util.Map<java.lang.String, java.lang.Object> r7) {
        /*
            r6 = this;
            java.lang.String r0 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            com.appsflyer.internal.AFg1xSDK r0 = r6.f268d
            java.lang.String r0 = r0.registerClient
            r1 = r0
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r2 = 83
            if (r1 == 0) goto L13
            r3 = 41
            goto L14
        L13:
            r3 = r2
        L14:
            r4 = 1
            r5 = 0
            if (r3 == r2) goto L42
            int r2 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r2 = r2 + 119
            int r3 = r2 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r3
            int r2 = r2 % 2
            if (r2 != 0) goto L35
            int r1 = r1.length()
            r2 = 84
            int r2 = r2 / r5
            if (r1 != 0) goto L2f
            r1 = r4
            goto L30
        L2f:
            r1 = r5
        L30:
            if (r1 == 0) goto L40
            goto L42
        L33:
            r7 = move-exception
            throw r7
        L35:
            int r1 = r1.length()
            if (r1 != 0) goto L3d
            r1 = r5
            goto L3e
        L3d:
            r1 = r4
        L3e:
            if (r1 == 0) goto L42
        L40:
            r1 = r5
            goto L4d
        L42:
            int r1 = com.appsflyer.internal.AFg1lSDK.afRDLog
            int r1 = r1 + 117
            int r2 = r1 % 128
            com.appsflyer.internal.AFg1lSDK.afErrorLog = r2
            int r1 = r1 % 2
            r1 = r4
        L4d:
            if (r1 != 0) goto L51
            r1 = r4
            goto L52
        L51:
            r1 = r5
        L52:
            if (r1 == r4) goto L55
            goto L6c
        L55:
            int r1 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r1 = r1 + 37
            int r2 = r1 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r2
            int r1 = r1 % 2
            java.lang.String r2 = "appsflyerKey"
            r7.put(r2, r0)
            if (r1 != 0) goto L6c
            r7 = 87
            int r7 = r7 / r5
            goto L6c
        L6a:
            r7 = move-exception
            throw r7
        L6c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.AFVersionDeclaration(java.util.Map):void");
    }

    private void values(Map<String, Object> map, Function0<String> function0) {
        int i = afErrorLog + 27;
        afRDLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(map, "");
        Intrinsics.checkNotNullParameter(function0, "");
        if ((AFKeystoreWrapper().getBoolean(AppsFlyerProperties.COLLECT_FACEBOOK_ATTR_ID, true) ? (char) 24 : 'D') != 'D') {
            String str = null;
            try {
                this.values.getPackageManager().getApplicationInfo("com.facebook.katana", 0);
                str = function0.invoke();
            } catch (PackageManager.NameNotFoundException e) {
                AFLogger.afErrorLogForExcManagerOnly("com.facebook.katana not found", e, true);
                AFLogger.afWarnLog("Exception while collecting facebook's attribution ID. ");
            } catch (Throwable th) {
                AFLogger.afErrorLog("Exception while collecting facebook's attribution ID. ", th);
            }
            if ((str != null ? (char) 4 : '/') != 4) {
                return;
            }
            int i3 = afRDLog + 73;
            afErrorLog = i3 % 128;
            int i4 = i3 % 2;
            map.put("fb", str);
            int i5 = afErrorLog + 95;
            afRDLog = i5 % 128;
            int i6 = i5 % 2;
        }
    }

    private static String values(AFd1tSDK aFd1tSDK, String str) {
        int i = afErrorLog + 57;
        afRDLog = i % 128;
        int i2 = i % 2;
        String AFKeystoreWrapper = aFd1tSDK.AFKeystoreWrapper("CACHED_CHANNEL", (String) null);
        if (AFKeystoreWrapper == null) {
            aFd1tSDK.valueOf("CACHED_CHANNEL", str);
            return str;
        }
        int i3 = afRDLog + 97;
        afErrorLog = i3 % 128;
        if (!(i3 % 2 == 0)) {
            int i4 = 75 / 0;
        }
        return AFKeystoreWrapper;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0047, code lost:
    
        if (r7 != null) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String AFKeystoreWrapper(java.lang.String r7) {
        /*
            int r0 = com.appsflyer.internal.AFg1lSDK.afRDLog
            int r0 = r0 + 115
            int r1 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afErrorLog = r1
            int r0 = r0 % 2
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L10
            r0 = r1
            goto L11
        L10:
            r0 = r2
        L11:
            java.lang.String r3 = "get"
            java.lang.String r4 = "android.os.SystemProperties"
            r5 = 0
            if (r0 == 0) goto L31
            java.lang.Class r0 = java.lang.Class.forName(r4)     // Catch: java.lang.Throwable -> L5e
            java.lang.Class[] r1 = new java.lang.Class[r1]     // Catch: java.lang.Throwable -> L5e
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            r1[r2] = r4     // Catch: java.lang.Throwable -> L5e
            java.lang.reflect.Method r0 = r0.getMethod(r3, r1)     // Catch: java.lang.Throwable -> L5e
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L5e
            r1[r2] = r7     // Catch: java.lang.Throwable -> L5e
            java.lang.Object r7 = r0.invoke(r5, r1)     // Catch: java.lang.Throwable -> L5e
            if (r7 == 0) goto L56
            goto L49
        L31:
            java.lang.Class r0 = java.lang.Class.forName(r4)     // Catch: java.lang.Throwable -> L5e
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch: java.lang.Throwable -> L5e
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r4[r2] = r6     // Catch: java.lang.Throwable -> L5e
            java.lang.reflect.Method r0 = r0.getMethod(r3, r4)     // Catch: java.lang.Throwable -> L5e
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L5e
            r1[r2] = r7     // Catch: java.lang.Throwable -> L5e
            java.lang.Object r7 = r0.invoke(r5, r1)     // Catch: java.lang.Throwable -> L5e
            if (r7 == 0) goto L56
        L49:
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Throwable -> L5e
            int r0 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r0 = r0 + 109
            int r1 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r1
            int r0 = r0 % 2
            return r7
        L56:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException     // Catch: java.lang.Throwable -> L5e
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.String"
            r7.<init>(r0)     // Catch: java.lang.Throwable -> L5e
            throw r7     // Catch: java.lang.Throwable -> L5e
        L5e:
            r7 = move-exception
            java.lang.String r0 = r7.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r0, r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.AFKeystoreWrapper(java.lang.String):java.lang.String");
    }

    private final String valueOf(String str) {
        int i = afRDLog + 115;
        afErrorLog = i % 128;
        int i2 = i % 2;
        String AFInAppEventParameterName = this.AFLogger.AFInAppEventParameterName(str);
        int i3 = afErrorLog + 89;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
        return AFInAppEventParameterName;
    }

    private final String afInfoLog() {
        File values = values(AFKeystoreWrapper("ro.appsflyer.preinstall.path"));
        if ((AFInAppEventParameterName(values) ? 'c' : 'R') != 'R') {
            values = values(valueOf("AF_PRE_INSTALL_PATH"));
        }
        if (AFInAppEventParameterName(values)) {
            values = values("/data/local/tmp/pre_install.appsflyer");
        }
        if (AFInAppEventParameterName(values)) {
            int i = afRDLog + 121;
            afErrorLog = i % 128;
            if (i % 2 != 0) {
                values = values("/etc/pre_install.appsflyer");
                int i2 = 93 / 0;
            } else {
                values = values("/etc/pre_install.appsflyer");
            }
        }
        if (AFInAppEventParameterName(values)) {
            return null;
        }
        String packageName = this.values.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "");
        String valueOf = valueOf(values, packageName);
        int i3 = afRDLog + 9;
        afErrorLog = i3 % 128;
        int i4 = i3 % 2;
        return valueOf;
    }

    private static File values(String str) {
        int i = afRDLog + 51;
        afErrorLog = i % 128;
        boolean z = true;
        if ((i % 2 != 0 ? Typography.amp : 'c') != 'c') {
            try {
                int i2 = 61 / 0;
                if ((str != null ? '-' : Matrix.MATRIX_TYPE_RANDOM_LT) != '-') {
                    return null;
                }
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getMessage(), th);
                return null;
            }
        } else {
            if (!(str != null)) {
                return null;
            }
        }
        if ((StringsKt.trim((CharSequence) str).toString().length() > 0 ? '9' : 'V') != '9') {
            z = false;
        } else {
            int i3 = afErrorLog + 83;
            afRDLog = i3 % 128;
            int i4 = i3 % 2;
        }
        if (z) {
            return new File(StringsKt.trim((CharSequence) str).toString());
        }
        return null;
    }

    private static boolean AFInAppEventParameterName(File file) {
        int i = afRDLog + 121;
        afErrorLog = i % 128;
        int i2 = i % 2;
        if ((file != null ? (char) 27 : (char) 5) != 5 && file.exists()) {
            return false;
        }
        int i3 = afErrorLog + 93;
        afRDLog = i3 % 128;
        if (!(i3 % 2 == 0)) {
            return true;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    private static String valueOf(File file, String str) {
        InputStreamReader inputStreamReader;
        Object obj = null;
        try {
            try {
                if (file == null) {
                    return null;
                }
                try {
                    Properties properties = new Properties();
                    inputStreamReader = new InputStreamReader(new FileInputStream(file), Charset.defaultCharset());
                    try {
                        properties.load(inputStreamReader);
                        AFLogger.afInfoLog("Found PreInstall property!");
                        String property = properties.getProperty(str);
                        try {
                            inputStreamReader.close();
                        } catch (Throwable th) {
                            AFLogger.afErrorLog(th.getMessage(), th);
                        }
                        int i = afRDLog + 23;
                        afErrorLog = i % 128;
                        if (!(i % 2 != 0)) {
                            return property;
                        }
                        obj.hashCode();
                        throw null;
                    } catch (FileNotFoundException unused) {
                        StringBuilder sb = new StringBuilder("PreInstall file wasn't found: ");
                        sb.append(file.getAbsolutePath());
                        AFLogger.afDebugLog(sb.toString());
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                            return null;
                        }
                        int i2 = afRDLog + 45;
                        afErrorLog = i2 % 128;
                        int i3 = i2 % 2;
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        AFLogger.afErrorLog(th.getMessage(), th);
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        return null;
                    }
                } catch (FileNotFoundException unused2) {
                    inputStreamReader = null;
                } catch (Throwable th3) {
                    th = th3;
                    inputStreamReader = null;
                }
            } catch (Throwable th4) {
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (Throwable th5) {
                        AFLogger.afErrorLog(th5.getMessage(), th5);
                    }
                }
                throw th4;
            }
        } catch (Throwable th6) {
            AFLogger.afErrorLog(th6.getMessage(), th6);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0035, code lost:
    
        r1 = 25;
        r0 = com.appsflyer.internal.AFg1lSDK.afRDLog + 25;
        com.appsflyer.internal.AFg1lSDK.afErrorLog = r0 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0042, code lost:
    
        if ((r0 % 2) == 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004e, code lost:
    
        if (AFKeystoreWrapper().getBoolean(com.appsflyer.AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, true) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0050, code lost:
    
        r1 = 'H';
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0051, code lost:
    
        if (r1 == 'H') goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0067, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005e, code lost:
    
        if (AFKeystoreWrapper().getBoolean(com.appsflyer.AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, false) == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0060, code lost:
    
        r0 = ')';
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0064, code lost:
    
        if (r0 == 'J') goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0063, code lost:
    
        r0 = 'J';
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0033, code lost:
    
        if ((!AFKeystoreWrapper().getBoolean(com.appsflyer.AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, false) ? '?' : 31) != 31) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0020, code lost:
    
        if (AFKeystoreWrapper().getBoolean(com.appsflyer.AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, true) == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0069, code lost:
    
        r0 = true;
     */
    /* renamed from: i */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean m110i() {
        /*
            r5 = this;
            int r0 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r0 = r0 + 123
            int r1 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r1
            int r0 = r0 % 2
            r1 = 87
            if (r0 != 0) goto L11
            r0 = 76
            goto L12
        L11:
            r0 = r1
        L12:
            java.lang.String r2 = "collectAndroidIdForceByUser"
            r3 = 0
            r4 = 1
            if (r0 == r1) goto L23
            com.appsflyer.AppsFlyerProperties r0 = r5.AFKeystoreWrapper()
            boolean r0 = r0.getBoolean(r2, r4)
            if (r0 != 0) goto L69
            goto L35
        L23:
            com.appsflyer.AppsFlyerProperties r0 = r5.AFKeystoreWrapper()
            boolean r0 = r0.getBoolean(r2, r3)
            r1 = 31
            if (r0 != 0) goto L32
            r0 = 63
            goto L33
        L32:
            r0 = r1
        L33:
            if (r0 == r1) goto L69
        L35:
            int r0 = com.appsflyer.internal.AFg1lSDK.afRDLog
            r1 = 25
            int r0 = r0 + r1
            int r2 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afErrorLog = r2
            int r0 = r0 % 2
            java.lang.String r2 = "collectIMEIForceByUser"
            if (r0 == 0) goto L54
            com.appsflyer.AppsFlyerProperties r0 = r5.AFKeystoreWrapper()
            boolean r0 = r0.getBoolean(r2, r4)
            r2 = 72
            if (r0 == 0) goto L51
            r1 = r2
        L51:
            if (r1 == r2) goto L69
            goto L67
        L54:
            com.appsflyer.AppsFlyerProperties r0 = r5.AFKeystoreWrapper()
            boolean r0 = r0.getBoolean(r2, r3)
            r1 = 74
            if (r0 == 0) goto L63
            r0 = 41
            goto L64
        L63:
            r0 = r1
        L64:
            if (r0 == r1) goto L67
            goto L69
        L67:
            r0 = r3
            goto L6a
        L69:
            r0 = r4
        L6a:
            if (r0 != 0) goto L6e
            r0 = r3
            goto L6f
        L6e:
            r0 = r4
        L6f:
            if (r0 == 0) goto L72
            goto L89
        L72:
            int r0 = com.appsflyer.internal.AFg1lSDK.afErrorLog
            int r0 = r0 + 99
            int r1 = r0 % 128
            com.appsflyer.internal.AFg1lSDK.afRDLog = r1
            int r0 = r0 % 2
            com.appsflyer.internal.AFb1tSDK.valueOf()
            if (r0 == 0) goto L8b
            android.content.Context r0 = r5.values
            boolean r0 = com.appsflyer.internal.AFb1tSDK.valueOf(r0)
            if (r0 != 0) goto L8a
        L89:
            return r4
        L8a:
            return r3
        L8b:
            android.content.Context r0 = r5.values
            com.appsflyer.internal.AFb1tSDK.valueOf(r0)
            r0 = 0
            throw r0     // Catch: java.lang.Throwable -> L92
        L92:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1lSDK.m110i():boolean");
    }

    @Override // com.appsflyer.internal.AFg1qSDK
    public final void AFKeystoreWrapper(Map<String, Object> map) {
        Object m1882constructorimpl;
        int i = afErrorLog + 53;
        afRDLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(map, "");
        String str = this.force.AFKeystoreWrapper;
        if ((str != null ? '#' : Matrix.MATRIX_TYPE_RANDOM_UT) == '#') {
            if (map.get("af_deeplink") != null) {
                AFLogger.afDebugLog("Skip 'af' payload as deeplink was found by path");
            } else {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    AFg1lSDK aFg1lSDK = this;
                    JSONObject jSONObject = new JSONObject(str);
                    jSONObject.put("isPush", "true");
                    map.put("af_deeplink", jSONObject.toString());
                    m1882constructorimpl = Result.m1882constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
                }
                Throwable m1885exceptionOrNullimpl = Result.m1885exceptionOrNullimpl(m1882constructorimpl);
                if (!(m1885exceptionOrNullimpl == null)) {
                    int i3 = afErrorLog + 79;
                    afRDLog = i3 % 128;
                    int i4 = i3 % 2;
                    AFg1hSDK.e$default(AFLogger.INSTANCE, AFg1gSDK.GENERAL, "Exception while trying to create JSONObject from pushPayload", m1885exceptionOrNullimpl, false, false, false, false, 120, null);
                } else {
                    int i5 = afRDLog + 103;
                    afErrorLog = i5 % 128;
                    int i6 = i5 % 2;
                }
                Result.m1881boximpl(m1882constructorimpl);
            }
        }
        this.force.AFKeystoreWrapper = null;
    }

    private static void valueOf(Map<String, Object> map, AFa1pSDK aFa1pSDK) {
        Intrinsics.checkNotNullParameter(map, "");
        Intrinsics.checkNotNullParameter(aFa1pSDK, "");
        String str = aFa1pSDK.AFLogger;
        if (str != null) {
            map.put("eventName", str);
            map.put("eventValue", new JSONObject(aFa1pSDK.AFKeystoreWrapper == null ? new HashMap() : aFa1pSDK.AFKeystoreWrapper).toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v5, types: [char[]] */
    /* renamed from: a */
    private static void m104a(String str, int i, Object[] objArr) {
        int i2 = $10 + 33;
        $11 = i2 % 128;
        int i3 = i2 % 2;
        if ((str != 0 ? (char) 18 : JwtParser.SEPARATOR_CHAR) == 18) {
            str = str.toCharArray();
            int i4 = $10 + 49;
            $11 = i4 % 128;
            int i5 = i4 % 2;
        }
        AFj1nSDK aFj1nSDK = new AFj1nSDK();
        char[] AFInAppEventType = AFj1nSDK.AFInAppEventType(afDebugLog ^ 6430695199931119772L, (char[]) str, i);
        aFj1nSDK.values = 4;
        while (true) {
            if ((aFj1nSDK.values < AFInAppEventType.length ? 'Y' : '+') == '+') {
                objArr[0] = new String(AFInAppEventType, 4, AFInAppEventType.length - 4);
                return;
            }
            int i6 = $11 + 33;
            $10 = i6 % 128;
            int i7 = i6 % 2;
            aFj1nSDK.valueOf = aFj1nSDK.values - 4;
            AFInAppEventType[aFj1nSDK.values] = (char) ((AFInAppEventType[aFj1nSDK.values] ^ AFInAppEventType[aFj1nSDK.values % 4]) ^ (aFj1nSDK.valueOf * (afDebugLog ^ 6430695199931119772L)));
            aFj1nSDK.values++;
        }
    }
}
