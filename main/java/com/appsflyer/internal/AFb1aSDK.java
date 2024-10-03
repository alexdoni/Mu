package com.appsflyer.internal;

import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import kotlin.text.Typography;
import org.json.JSONObject;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes.dex */
public final class AFb1aSDK implements AFb1bSDK {
    private static int $10 = 0;
    private static int $11 = 1;
    private static int afInfoLog = 0;

    /* renamed from: e */
    private static int f163e = 0;
    private static int force = 1;

    /* renamed from: i */
    private static int f164i;
    private static int registerClient;

    /* renamed from: v */
    private static short[] f165v;
    private static final int valueOf;

    /* renamed from: w */
    private static byte[] f166w;

    /* renamed from: d */
    private final AFd1mSDK f167d;
    private List<String> values = new ArrayList();
    private boolean AFInAppEventType = true;
    private final Map<String, Object> AFKeystoreWrapper = new HashMap();
    private boolean AFLogger = true ^ AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DPM, false);
    private int AFInAppEventParameterName = 0;
    private boolean unregisterClient = false;

    static void AFLogger() {
        f163e = -97950892;
        registerClient = 772570790;
        afInfoLog = -746421743;
        f166w = new byte[]{95, -109, -6, -100, -3};
    }

    static {
        AFLogger();
        valueOf = 98166;
        int i = force + 49;
        f164i = i % 128;
        int i2 = i % 2;
    }

    public AFb1aSDK(AFd1mSDK aFd1mSDK) {
        this.f167d = aFd1mSDK;
    }

    @Override // com.appsflyer.internal.AFb1bSDK
    public final boolean values() {
        boolean AFInAppEventType = AFInAppEventType(values(this.f167d.mo76d().valueOf.AFInAppEventParameterName), values(this.f167d.mo76d().valueOf.AFInAppEventType));
        if (!AFInAppEventType) {
            AFInAppEventType();
            AFKeystoreWrapper();
        } else {
            int i = f164i + 27;
            force = i % 128;
            int i2 = i % 2;
            unregisterClient();
            int i3 = f164i + 33;
            force = i3 % 128;
            int i4 = i3 % 2;
        }
        return AFInAppEventType;
    }

    private synchronized void unregisterClient() {
        int i = f164i + 109;
        int i2 = i % 128;
        force = i2;
        int i3 = i % 2;
        if (!this.unregisterClient) {
            this.unregisterClient = true;
            try {
                values("r_debugging_on", new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis())), new String[0]);
                int i4 = f164i + 57;
                force = i4 % 128;
                if (!(i4 % 2 == 0)) {
                    return;
                }
                int i5 = 34 / 0;
                return;
            } catch (Throwable th) {
                AFLogger.INSTANCE.m100e(AFg1gSDK.PROXY, "Error while starting remote debugger", th, true, true, true);
                return;
            }
        }
        int i6 = i2 + 113;
        f164i = i6 % 128;
        int i7 = i6 % 2;
    }

    @Override // com.appsflyer.internal.AFb1bSDK
    public final synchronized void AFKeystoreWrapper() {
        int i = force + 89;
        int i2 = i % 128;
        f164i = i2;
        int i3 = i % 2;
        if ((!this.unregisterClient ? (char) 7 : 'U') != 'U') {
            int i4 = i2 + 55;
            force = i4 % 128;
            if ((i4 % 2 == 0 ? Matrix.MATRIX_TYPE_RANDOM_REGULAR : 'Q') != 'Q') {
                throw null;
            }
            if (!this.AFInAppEventType) {
                int i5 = i2 + 41;
                force = i5 % 128;
                int i6 = i5 % 2;
                return;
            }
        }
        this.unregisterClient = false;
        this.AFInAppEventType = false;
        try {
            values("r_debugging_off", new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis())), new String[0]);
        } catch (Throwable th) {
            AFLogger.INSTANCE.m100e(AFg1gSDK.PROXY, "Error while stopping remote debugger", th, true, true, true);
        }
    }

    @Override // com.appsflyer.internal.AFb1bSDK
    public final synchronized void valueOf() {
        int i = force + 47;
        f164i = i % 128;
        int i2 = i % 2;
        this.AFKeystoreWrapper.clear();
        this.values.clear();
        this.AFInAppEventParameterName = 0;
        int i3 = f164i + 101;
        force = i3 % 128;
        if ((i3 % 2 == 0 ? 'U' : Typography.dollar) != 'U') {
            return;
        }
        int i4 = 35 / 0;
    }

    @Override // com.appsflyer.internal.AFb1bSDK
    public final void valueOf(String str, PackageManager packageManager) {
        int i = force + 115;
        f164i = i % 128;
        int i2 = i % 2;
        try {
            final AFe1xSDK AFInAppEventType = this.f167d.AFKeystoreWrapper().AFInAppEventType(values(str, packageManager), this.f167d.force().registerClient);
            if (AFInAppEventType == null) {
                AFLogger.afErrorLogForExcManagerOnly("could not send null proxy data", new NullPointerException("request was null"));
                int i3 = force + 63;
                f164i = i3 % 128;
                int i4 = i3 % 2;
                return;
            }
            ExecutorService AFInAppEventParameterName = this.f167d.AFInAppEventParameterName();
            Objects.requireNonNull(AFInAppEventType);
            AFInAppEventParameterName.execute(new Runnable() { // from class: com.appsflyer.internal.AFb1aSDK$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AFe1xSDK.this.values();
                }
            });
        } catch (Throwable th) {
            AFLogger.afErrorLogForExcManagerOnly("could not send proxy data", th);
        }
    }

    @Override // com.appsflyer.internal.AFb1bSDK
    public final void AFInAppEventParameterName(String str, String... strArr) {
        int i = f164i + 45;
        force = i % 128;
        boolean z = i % 2 != 0;
        values("public_api_call", str, strArr);
        if (z) {
            return;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    @Override // com.appsflyer.internal.AFb1bSDK
    public final void AFInAppEventType(Throwable th) {
        String message;
        StackTraceElement[] stackTrace;
        int i = force + 109;
        f164i = i % 128;
        int i2 = i % 2;
        Throwable cause = th.getCause();
        String simpleName = th.getClass().getSimpleName();
        if (!(cause != null)) {
            int i3 = f164i + 13;
            force = i3 % 128;
            if (i3 % 2 == 0) {
                th.getMessage();
                Object obj = null;
                obj.hashCode();
                throw null;
            }
            message = th.getMessage();
        } else {
            message = cause.getMessage();
        }
        if ((cause == null ? '\f' : 'A') != 'A') {
            stackTrace = th.getStackTrace();
            int i4 = f164i + 27;
            force = i4 % 128;
            int i5 = i4 % 2;
        } else {
            stackTrace = cause.getStackTrace();
        }
        values("exception", simpleName, AFKeystoreWrapper(message, stackTrace));
        int i6 = force + 15;
        f164i = i6 % 128;
        int i7 = i6 % 2;
    }

    @Override // com.appsflyer.internal.AFb1bSDK
    public final void valueOf(String str, String str2) {
        int i = force + 55;
        f164i = i % 128;
        if ((i % 2 != 0 ? (char) 11 : '\r') != 11) {
            values("server_request", str, str2);
            return;
        }
        String[] strArr = new String[1];
        strArr[1] = str2;
        values("server_request", str, strArr);
    }

    @Override // com.appsflyer.internal.AFb1bSDK
    public final void AFInAppEventParameterName(String str, int i, String str2) {
        int i2 = f164i + 45;
        force = i2 % 128;
        if ((i2 % 2 == 0 ? '/' : 'W') != 'W') {
            String[] strArr = new String[3];
            strArr[0] = String.valueOf(i);
            strArr[0] = str2;
            values("server_response", str, strArr);
        } else {
            values("server_response", str, String.valueOf(i), str2);
        }
        int i3 = f164i + 29;
        force = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.internal.AFb1bSDK
    public final void AFInAppEventType(String str, String str2) {
        int i = f164i + 59;
        force = i % 128;
        int i2 = i % 2;
        String[] strArr = {str2};
        Object obj = null;
        values(null, str, strArr);
        int i3 = force + 119;
        f164i = i3 % 128;
        if ((i3 % 2 != 0 ? (char) 19 : 'C') == 'C') {
            return;
        }
        obj.hashCode();
        throw null;
    }

    @Override // com.appsflyer.internal.AFb1bSDK
    public final synchronized void AFInAppEventType() {
        int i = force + 9;
        f164i = i % 128;
        int i2 = i % 2;
        this.AFInAppEventType = false;
        valueOf();
        m64i();
        int i3 = f164i + 85;
        force = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.internal.AFb1bSDK
    public final void AFInAppEventParameterName() {
        int i = f164i;
        int i2 = i + 97;
        force = i2 % 128;
        int i3 = i2 % 2;
        this.AFLogger = false;
        int i4 = i + 115;
        force = i4 % 128;
        if (!(i4 % 2 != 0)) {
            int i5 = 58 / 0;
        }
    }

    @Override // com.appsflyer.internal.AFb1bSDK
    /* renamed from: d */
    public final boolean mo67d() {
        int i = f164i;
        int i2 = i + 37;
        force = i2 % 128;
        int i3 = i2 % 2;
        boolean z = this.unregisterClient;
        int i4 = i + 63;
        force = i4 % 128;
        if (i4 % 2 != 0) {
            return z;
        }
        int i5 = 48 / 0;
        return z;
    }

    /* renamed from: e */
    private static float m63e() {
        float nextFloat = new Random().nextFloat();
        int i = f164i + 57;
        force = i % 128;
        if (i % 2 != 0) {
            return nextFloat;
        }
        int i2 = 93 / 0;
        return nextFloat;
    }

    private Map<String, Object> values(String str, PackageManager packageManager) {
        int i = force + 85;
        f164i = i % 128;
        int i2 = i % 2;
        AFInAppEventType(str, packageManager, this.f167d.force(), this.f167d.afErrorLogForExcManagerOnly());
        Map<String, Object> m66w = m66w();
        int i3 = f164i + 49;
        force = i3 % 128;
        if ((i3 % 2 == 0 ? 'J' : '\f') == '\f') {
            return m66w;
        }
        int i4 = 65 / 0;
        return m66w;
    }

    private static String registerClient() {
        int i = f164i + 93;
        int i2 = i % 128;
        force = i2;
        int i3 = i % 2;
        int i4 = i2 + 45;
        f164i = i4 % 128;
        int i5 = i4 % 2;
        return "6.13.1";
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0024, code lost:
    
        if ((r1 % 2) != 0) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0026, code lost:
    
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
    
        if (r1 == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
    
        if (r4.AFInAppEventType != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004c, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
    
        if (r4.unregisterClient == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
    
        r1 = '$';
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0049, code lost:
    
        if (r1 == '$') goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
    
        r1 = ')';
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0034, code lost:
    
        r3 = 14 / 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0037, code lost:
    
        if (r4.AFInAppEventType != false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0039, code lost:
    
        r1 = org.spongycastle.pqc.math.linearalgebra.Matrix.MATRIX_TYPE_RANDOM_UT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003d, code lost:
    
        if (r1 == ']') goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x003c, code lost:
    
        r1 = ']';
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0028, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0019, code lost:
    
        if (r4.AFLogger != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0012, code lost:
    
        if (r4.AFLogger != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
    
        r1 = r1 + 107;
        com.appsflyer.internal.AFb1aSDK.force = r1 % 128;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean afInfoLog() {
        /*
            r4 = this;
            int r0 = com.appsflyer.internal.AFb1aSDK.force
            int r0 = r0 + 57
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1aSDK.f164i = r1
            int r0 = r0 % 2
            r2 = 0
            if (r0 == 0) goto L17
            boolean r0 = r4.AFLogger
            r3 = 66
            int r3 = r3 / r2
            if (r0 == 0) goto L4f
            goto L1b
        L15:
            r0 = move-exception
            throw r0
        L17:
            boolean r0 = r4.AFLogger
            if (r0 == 0) goto L4f
        L1b:
            int r1 = r1 + 107
            int r0 = r1 % 128
            com.appsflyer.internal.AFb1aSDK.force = r0
            int r1 = r1 % 2
            r0 = 1
            if (r1 != 0) goto L28
            r1 = r2
            goto L29
        L28:
            r1 = r0
        L29:
            if (r1 == 0) goto L30
            boolean r1 = r4.AFInAppEventType
            if (r1 != 0) goto L4c
            goto L3f
        L30:
            boolean r1 = r4.AFInAppEventType
            r3 = 14
            int r3 = r3 / r2
            r3 = 93
            if (r1 != 0) goto L3c
            r1 = 85
            goto L3d
        L3c:
            r1 = r3
        L3d:
            if (r1 == r3) goto L4c
        L3f:
            boolean r1 = r4.unregisterClient
            r3 = 36
            if (r1 == 0) goto L47
            r1 = r3
            goto L49
        L47:
            r1 = 41
        L49:
            if (r1 == r3) goto L4c
            goto L4f
        L4c:
            return r0
        L4d:
            r0 = move-exception
            throw r0
        L4f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1aSDK.afInfoLog():boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0091, code lost:
    
        if (r12.length() > 0) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void AFInAppEventType(java.lang.String r12, java.lang.String r13, java.lang.String r14) {
        /*
            r11 = this;
            monitor-enter(r11)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r11.AFKeystoreWrapper     // Catch: java.lang.Throwable -> Lcb
            long r1 = android.view.ViewConfiguration.getZoomControlsTimeout()     // Catch: java.lang.Throwable -> Lcb
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            int r1 = (-52) - r1
            short r5 = (short) r1     // Catch: java.lang.Throwable -> Lcb
            int r1 = android.view.ViewConfiguration.getFadingEdgeLength()     // Catch: java.lang.Throwable -> Lcb
            r2 = 16
            int r1 = r1 >> r2
            r6 = 40960953(0x27103b9, float:1.7706952E-37)
            int r6 = r6 + r1
            long r7 = android.view.ViewConfiguration.getGlobalActionKeyTimeout()     // Catch: java.lang.Throwable -> Lcb
            int r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            int r1 = r1 + (-1)
            byte r7 = (byte) r1     // Catch: java.lang.Throwable -> Lcb
            float r1 = android.media.AudioTrack.getMaxVolume()     // Catch: java.lang.Throwable -> Lcb
            r8 = 0
            int r1 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            int r8 = (-30) - r1
            long r9 = android.view.ViewConfiguration.getZoomControlsTimeout()     // Catch: java.lang.Throwable -> Lcb
            int r1 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            r3 = 735765011(0x2bdae213, float:1.555258E-12)
            int r9 = r1 + r3
            r1 = 1
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Lcb
            r10 = r3
            m62a(r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> Lcb
            r4 = 0
            r3 = r3[r4]     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r3 = r3.intern()     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r5 = android.os.Build.BRAND     // Catch: java.lang.Throwable -> Lcb
            r0.put(r3, r5)     // Catch: java.lang.Throwable -> Lcb
            java.util.Map<java.lang.String, java.lang.Object> r0 = r11.AFKeystoreWrapper     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r3 = "model"
            java.lang.String r5 = android.os.Build.MODEL     // Catch: java.lang.Throwable -> Lcb
            r0.put(r3, r5)     // Catch: java.lang.Throwable -> Lcb
            java.util.Map<java.lang.String, java.lang.Object> r0 = r11.AFKeystoreWrapper     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r3 = "platform"
            java.lang.String r5 = "Android"
            r0.put(r3, r5)     // Catch: java.lang.Throwable -> Lcb
            java.util.Map<java.lang.String, java.lang.Object> r0 = r11.AFKeystoreWrapper     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r3 = "platform_version"
            java.lang.String r5 = android.os.Build.VERSION.RELEASE     // Catch: java.lang.Throwable -> Lcb
            r0.put(r3, r5)     // Catch: java.lang.Throwable -> Lcb
            if (r12 == 0) goto L6a
            r0 = r2
            goto L6c
        L6a:
            r0 = 70
        L6c:
            if (r0 == r2) goto L6f
            goto L9a
        L6f:
            int r0 = com.appsflyer.internal.AFb1aSDK.force     // Catch: java.lang.Throwable -> Lc8
            int r0 = r0 + 7
            int r2 = r0 % 128
            com.appsflyer.internal.AFb1aSDK.f164i = r2     // Catch: java.lang.Throwable -> Lc8
            int r0 = r0 % 2
            if (r0 == 0) goto L8d
            int r0 = r12.length()     // Catch: java.lang.Throwable -> Lcb
            r2 = 9
            int r2 = r2 / r4
            r2 = 9
            if (r0 <= 0) goto L88
            r0 = r2
            goto L8a
        L88:
            r0 = 82
        L8a:
            if (r0 == r2) goto L93
            goto L9a
        L8d:
            int r0 = r12.length()     // Catch: java.lang.Throwable -> Lcb
            if (r0 <= 0) goto L9a
        L93:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r11.AFKeystoreWrapper     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r2 = "advertiserId"
            r0.put(r2, r12)     // Catch: java.lang.Throwable -> Lcb
        L9a:
            if (r13 == 0) goto La9
            int r12 = r13.length()     // Catch: java.lang.Throwable -> Lcb
            if (r12 <= 0) goto La9
            java.util.Map<java.lang.String, java.lang.Object> r12 = r11.AFKeystoreWrapper     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r0 = "imei"
            r12.put(r0, r13)     // Catch: java.lang.Throwable -> Lcb
        La9:
            if (r14 == 0) goto Lc6
            int r12 = r14.length()     // Catch: java.lang.Throwable -> Lcb
            if (r12 <= 0) goto Lb2
            r1 = r4
        Lb2:
            if (r1 == 0) goto Lb5
            goto Lc6
        Lb5:
            java.util.Map<java.lang.String, java.lang.Object> r12 = r11.AFKeystoreWrapper     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r13 = "android_id"
            r12.put(r13, r14)     // Catch: java.lang.Throwable -> Lcb
            int r12 = com.appsflyer.internal.AFb1aSDK.force     // Catch: java.lang.Throwable -> Lc8
            int r12 = r12 + 45
            int r13 = r12 % 128
            com.appsflyer.internal.AFb1aSDK.f164i = r13     // Catch: java.lang.Throwable -> Lc8
            int r12 = r12 % 2
        Lc6:
            monitor-exit(r11)
            return
        Lc8:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        Lcb:
            monitor-exit(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1aSDK.AFInAppEventType(java.lang.String, java.lang.String, java.lang.String):void");
    }

    private synchronized void values(String str, String str2, String str3, String str4) {
        try {
            this.AFKeystoreWrapper.put(ComConstants.sdk_version_code, str);
            if (str2 != null && str2.length() > 0) {
                this.AFKeystoreWrapper.put("devkey", str2);
            }
            Object obj = null;
            if (str3 != null) {
                try {
                    int i = force + 79;
                    f164i = i % 128;
                    if (i % 2 != 0) {
                        str3.length();
                        obj.hashCode();
                        throw null;
                    }
                    if (str3.length() > 0) {
                        int i2 = force + 39;
                        f164i = i2 % 128;
                        int i3 = i2 % 2;
                        this.AFKeystoreWrapper.put("originalAppsFlyerId", str3);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (str4 != null && str4.length() > 0) {
                this.AFKeystoreWrapper.put("uid", str4);
            }
            int i4 = f164i + 41;
            force = i4 % 128;
            if (i4 % 2 == 0) {
                throw null;
            }
        } catch (Throwable unused) {
        }
    }

    private synchronized void AFKeystoreWrapper(String str, String str2, String str3, String str4) {
        int i = force + 73;
        f164i = i % 128;
        int i2 = i % 2;
        if ((str != null ? (char) 24 : (char) 20) != 20) {
            try {
                if (str.length() > 0) {
                    this.AFKeystoreWrapper.put("app_id", str);
                }
            } catch (Throwable unused) {
                return;
            }
        }
        if (str2 != null) {
            int i3 = f164i + 107;
            force = i3 % 128;
            int i4 = i3 % 2;
            if (str2.length() > 0) {
                this.AFKeystoreWrapper.put("app_version", str2);
            }
        }
        Object obj = null;
        if (!(str3 == null)) {
            int i5 = f164i + 37;
            force = i5 % 128;
            int i6 = i5 % 2;
            if (str3.length() > 0) {
                int i7 = f164i + 39;
                force = i7 % 128;
                if (i7 % 2 == 0) {
                    this.AFKeystoreWrapper.put(AppsFlyerProperties.CHANNEL, str3);
                    throw null;
                }
                this.AFKeystoreWrapper.put(AppsFlyerProperties.CHANNEL, str3);
            }
        }
        if (str4 != null) {
            int i8 = f164i + 55;
            force = i8 % 128;
            int i9 = i8 % 2;
            if ((str4.length() > 0 ? 'K' : '7') != '7') {
                int i10 = f164i + 67;
                force = i10 % 128;
                if (i10 % 2 == 0) {
                    this.AFKeystoreWrapper.put("preInstall", str4);
                    obj.hashCode();
                    throw null;
                }
                this.AFKeystoreWrapper.put("preInstall", str4);
            }
        }
    }

    private synchronized void values(String str, String str2, String... strArr) {
        String obj;
        boolean z;
        int i = f164i + 61;
        force = i % 128;
        if (i % 2 == 0) {
            afInfoLog();
            throw null;
        }
        if (!afInfoLog() || this.AFInAppEventParameterName >= 98304) {
            return;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String join = TextUtils.join(", ", strArr);
            if (str != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(currentTimeMillis);
                sb.append(" ");
                sb.append(Thread.currentThread().getId());
                sb.append(" _/AppsFlyer_6.13.1 [");
                sb.append(str);
                sb.append("] ");
                sb.append(str2);
                sb.append(" ");
                sb.append(join);
                obj = sb.toString();
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(currentTimeMillis);
                sb2.append(" ");
                sb2.append(Thread.currentThread().getId());
                sb2.append(" ");
                sb2.append(str2);
                sb2.append("/AppsFlyer_6.13.1 ");
                sb2.append(join);
                obj = sb2.toString();
                int i2 = f164i + 53;
                force = i2 % 128;
                int i3 = i2 % 2;
            }
            int length = this.AFInAppEventParameterName + (obj.length() << 1);
            int i4 = valueOf;
            if (length > i4) {
                obj = obj.substring(0, (i4 - this.AFInAppEventParameterName) / 2);
                z = true;
            } else {
                z = false;
            }
            this.values.add(obj);
            this.AFInAppEventParameterName += obj.length() << 1;
            if (z) {
                int i5 = f164i + 59;
                force = i5 % 128;
                int i6 = i5 % 2;
                this.values.add("+~+~ The limit has been exceeded, and no more data is available. +~+~");
                this.AFInAppEventParameterName += CipherSuite.TLS_PSK_WITH_RC4_128_SHA;
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: w */
    private synchronized Map<String, Object> m66w() {
        int i = force + 81;
        f164i = i % 128;
        if (i % 2 == 0) {
            this.AFKeystoreWrapper.put("data", this.values);
            m64i();
        } else {
            this.AFKeystoreWrapper.put("data", this.values);
            m64i();
            throw null;
        }
        return this.AFKeystoreWrapper;
    }

    private synchronized void AFInAppEventType(String str, PackageManager packageManager, AFg1xSDK aFg1xSDK, AFd1qSDK aFd1qSDK) {
        AFa1bSDK aFa1bSDK;
        int i = force + 49;
        f164i = i % 128;
        String str2 = null;
        if ((i % 2 != 0 ? '\'' : '.') != '.') {
            AppsFlyerProperties.getInstance().getString("remote_debug_static_data");
            this.AFKeystoreWrapper.clear();
            str2.hashCode();
            throw null;
        }
        AppsFlyerProperties appsFlyerProperties = AppsFlyerProperties.getInstance();
        String string = appsFlyerProperties.getString("remote_debug_static_data");
        this.AFKeystoreWrapper.clear();
        if (string != null) {
            try {
                this.AFKeystoreWrapper.putAll(AFa1qSDK.values(new JSONObject(string)));
            } catch (Throwable unused) {
            }
        } else {
            AFb1tSDK valueOf2 = AFb1tSDK.valueOf();
            AFh1xSDK aFh1xSDK = AFb1tSDK.valueOf().values().AFInAppEventType().valueOf.f189e;
            if (aFh1xSDK != null) {
                aFa1bSDK = new AFa1bSDK(aFh1xSDK.AFInAppEventType, aFh1xSDK.unregisterClient);
                int i2 = force + 83;
                f164i = i2 % 128;
                int i3 = i2 % 2;
            } else {
                aFa1bSDK = null;
            }
            if (aFa1bSDK != null) {
                str2 = aFa1bSDK.AFInAppEventParameterName;
            }
            AFInAppEventType(str2, aFg1xSDK.unregisterClient, aFd1qSDK.values);
            StringBuilder sb = new StringBuilder("6.13.1.");
            sb.append(AFb1tSDK.AFInAppEventParameterName);
            values(sb.toString(), valueOf2.values().force().registerClient, appsFlyerProperties.getString("KSAppsFlyerId"), appsFlyerProperties.getString("uid"));
            try {
                AFKeystoreWrapper(str, String.valueOf(packageManager.getPackageInfo(str, 0).versionCode), appsFlyerProperties.getString(AppsFlyerProperties.CHANNEL), appsFlyerProperties.getString("preInstallName"));
                int i4 = force + 125;
                f164i = i4 % 128;
                int i5 = i4 % 2;
            } catch (Throwable unused2) {
            }
            appsFlyerProperties.set("remote_debug_static_data", new JSONObject(this.AFKeystoreWrapper).toString());
        }
        this.AFKeystoreWrapper.put("launch_counter", String.valueOf(this.f167d.AFInAppEventType().AFKeystoreWrapper.AFInAppEventParameterName("appsFlyerCount", 0)));
    }

    private static String[] AFKeystoreWrapper(String str, StackTraceElement[] stackTraceElementArr) {
        if ((stackTraceElementArr == null ? '#' : 'P') == '#') {
            int i = force + 95;
            f164i = i % 128;
            int i2 = i % 2;
            return new String[]{str};
        }
        String[] strArr = new String[stackTraceElementArr.length + 1];
        strArr[0] = str;
        int i3 = 1;
        while (true) {
            if (i3 >= stackTraceElementArr.length) {
                return strArr;
            }
            strArr[i3] = stackTraceElementArr[i3].toString();
            i3++;
            int i4 = f164i + 61;
            force = i4 % 128;
            int i5 = i4 % 2;
        }
    }

    /* renamed from: i */
    private synchronized void m64i() {
        this.values = new ArrayList();
        this.AFInAppEventParameterName = 0;
        int i = f164i + 95;
        force = i % 128;
        int i2 = i % 2;
    }

    private synchronized boolean AFInAppEventType(AFh1iSDK aFh1iSDK, AFh1iSDK aFh1iSDK2) {
        boolean z;
        if (!(aFh1iSDK != null)) {
            int i = f164i + 77;
            force = i % 128;
            if ((i % 2 == 0 ? '.' : 'G') != '.') {
                m65v();
                return false;
            }
            m65v();
            return false;
        }
        if (!aFh1iSDK.AFInAppEventType()) {
            int i2 = f164i + 71;
            force = i2 % 128;
            return i2 % 2 == 0 ? false : false;
        }
        if (this.f167d.AFInAppEventType().AFKeystoreWrapper.AFInAppEventParameterName("appsFlyerCount", 0) <= aFh1iSDK.values) {
            int i3 = f164i + 13;
            force = i3 % 128;
            if (i3 % 2 == 0) {
            }
            z = true;
        } else {
            int i4 = force + 53;
            f164i = i4 % 128;
            int i5 = i4 % 2;
            z = false;
        }
        if (!z) {
            int i6 = force + 125;
            f164i = i6 % 128;
            int i7 = i6 % 2;
            return false;
        }
        if (AFInAppEventParameterName(aFh1iSDK, aFh1iSDK2)) {
            if (AFKeystoreWrapper(aFh1iSDK.valueOf)) {
                return AFInAppEventParameterName(aFh1iSDK.AFKeystoreWrapper);
            }
            return false;
        }
        int i8 = force + 91;
        f164i = i8 % 128;
        int i9 = i8 % 2;
        return false;
    }

    private boolean AFInAppEventParameterName(AFh1iSDK aFh1iSDK, AFh1iSDK aFh1iSDK2) {
        int i = force + 39;
        f164i = i % 128;
        if (i % 2 == 0) {
            if (aFh1iSDK.equals(aFh1iSDK2)) {
                boolean force2 = force();
                int i2 = force + 29;
                f164i = i2 % 128;
                int i3 = i2 % 2;
                return force2;
            }
            boolean values = values(aFh1iSDK.AFInAppEventType);
            AFKeystoreWrapper(values);
            return values;
        }
        aFh1iSDK.equals(aFh1iSDK2);
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0053, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0033, code lost:
    
        new com.appsflyer.internal.AFe1ySDK();
        r4 = com.appsflyer.internal.AFe1ySDK.values(registerClient(), r4);
        r0 = com.appsflyer.internal.AFb1aSDK.force + 65;
        com.appsflyer.internal.AFb1aSDK.f164i = r0 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x004a, code lost:
    
        if ((r0 % 2) == 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004e, code lost:
    
        r0 = 77 / 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004f, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0052, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0031, code lost:
    
        if ((com.appsflyer.internal.AFc1rSDK.AFKeystoreWrapper(r4) ? '\r' : '!') != '\r') goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
    
        if ((com.appsflyer.internal.AFc1rSDK.AFKeystoreWrapper(r4)) != true) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean AFInAppEventParameterName(java.lang.String r4) {
        /*
            int r0 = com.appsflyer.internal.AFb1aSDK.force
            int r0 = r0 + 7
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1aSDK.f164i = r1
            int r0 = r0 % 2
            r1 = 56
            if (r0 == 0) goto L10
            r0 = r1
            goto L12
        L10:
            r0 = 60
        L12:
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L22
            boolean r0 = com.appsflyer.internal.AFc1rSDK.AFKeystoreWrapper(r4)
            if (r0 == 0) goto L1e
            r0 = r2
            goto L1f
        L1e:
            r0 = r3
        L1f:
            if (r0 == r2) goto L53
            goto L33
        L22:
            boolean r0 = com.appsflyer.internal.AFc1rSDK.AFKeystoreWrapper(r4)
            r1 = 73
            int r1 = r1 / r3
            r1 = 13
            if (r0 == 0) goto L2f
            r0 = r1
            goto L31
        L2f:
            r0 = 33
        L31:
            if (r0 == r1) goto L53
        L33:
            com.appsflyer.internal.AFe1ySDK r0 = new com.appsflyer.internal.AFe1ySDK
            r0.<init>()
            java.lang.String r0 = registerClient()
            boolean r4 = com.appsflyer.internal.AFe1ySDK.values(r0, r4)
            int r0 = com.appsflyer.internal.AFb1aSDK.force
            int r0 = r0 + 65
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1aSDK.f164i = r1
            int r0 = r0 % 2
            if (r0 == 0) goto L52
            r0 = 77
            int r0 = r0 / r3
            return r4
        L50:
            r4 = move-exception
            throw r4
        L52:
            return r4
        L53:
            return r2
        L54:
            r4 = move-exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1aSDK.AFInAppEventParameterName(java.lang.String):boolean");
    }

    private boolean AFKeystoreWrapper(String str) {
        if (AFc1rSDK.AFKeystoreWrapper(str)) {
            int i = f164i + 45;
            int i2 = i % 128;
            force = i2;
            int i3 = i % 2;
            int i4 = i2 + 105;
            f164i = i4 % 128;
            if ((i4 % 2 != 0 ? '.' : '8') != '.') {
                return true;
            }
            throw null;
        }
        AFd1sSDK AFInAppEventType = this.f167d.AFInAppEventType();
        return str.equals(AFb1uSDK.AFKeystoreWrapper(AFInAppEventType.AFInAppEventParameterName.valueOf, AFInAppEventType.AFInAppEventParameterName.valueOf.getPackageName()));
    }

    private static boolean values(float f) {
        double d = f;
        if (d >= 1.0d) {
            return true;
        }
        if (d > 0.0d) {
            if ((m63e() > f ? (char) 2 : '\'') == 2) {
                return false;
            }
            int i = f164i;
            int i2 = i + 25;
            force = i2 % 128;
            int i3 = i2 % 2;
            int i4 = i + 67;
            force = i4 % 128;
            if (i4 % 2 != 0) {
                return true;
            }
            throw null;
        }
        int i5 = force + 39;
        f164i = i5 % 128;
        int i6 = i5 % 2;
        return false;
    }

    private static AFh1iSDK values(AFh1hSDK aFh1hSDK) {
        int i = force + 23;
        f164i = i % 128;
        int i2 = i % 2;
        if ((aFh1hSDK != null ? ']' : (char) 16) != ']') {
            return null;
        }
        AFh1gSDK aFh1gSDK = aFh1hSDK.AFInAppEventType;
        if ((aFh1gSDK != null ? '\'' : (char) 4) != '\'') {
            return null;
        }
        AFh1iSDK aFh1iSDK = aFh1gSDK.valueOf;
        int i3 = f164i + 25;
        force = i3 % 128;
        int i4 = i3 % 2;
        return aFh1iSDK;
    }

    /* renamed from: v */
    private void m65v() {
        int i = force + 99;
        f164i = i % 128;
        if (!(i % 2 != 0)) {
            this.f167d.values().AFInAppEventType("participantInProxy");
        } else {
            this.f167d.values().AFInAppEventType("participantInProxy");
            int i2 = 16 / 0;
        }
        int i3 = f164i + 31;
        force = i3 % 128;
        if ((i3 % 2 == 0 ? '3' : '\b') != '3') {
        } else {
            throw null;
        }
    }

    private void AFKeystoreWrapper(boolean z) {
        int i = force + 39;
        f164i = i % 128;
        int i2 = i % 2;
        this.f167d.values().values("participantInProxy", z);
        int i3 = f164i + 71;
        force = i3 % 128;
        if ((i3 % 2 == 0 ? (char) 31 : '5') == '5') {
            return;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    private boolean force() {
        int i = force + 61;
        f164i = i % 128;
        int i2 = i % 2;
        boolean values = this.f167d.values().values("participantInProxy");
        int i3 = force + 5;
        f164i = i3 % 128;
        if (i3 % 2 == 0) {
            return values;
        }
        int i4 = 29 / 0;
        return values;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x008f, code lost:
    
        if ((!r3) != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x009e, code lost:
    
        r3 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a0, code lost:
    
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x009c, code lost:
    
        if (r3 != false) goto L34;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m62a(short r13, int r14, byte r15, int r16, int r17, java.lang.Object[] r18) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1aSDK.m62a(short, int, byte, int, int, java.lang.Object[]):void");
    }
}
