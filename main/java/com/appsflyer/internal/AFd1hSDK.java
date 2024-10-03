package com.appsflyer.internal;

import android.os.Build;
import com.appsflyer.AFLogger;
import com.appsflyer.internal.AFd1iSDK;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Typography;
import org.json.JSONObject;
import org.spongycastle.i18n.LocalizedMessage;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes.dex */
public final class AFd1hSDK implements AFd1iSDK {
    private static int $10 = 0;
    private static int $11 = 1;
    private static int afInfoLog = 1;
    private static char[] force = {31715, 31672, 31675, 31669, 31675};

    /* renamed from: i */
    private static int f180i;
    private final Lazy AFInAppEventParameterName;
    private final Lazy AFInAppEventType;
    private final Lazy AFKeystoreWrapper;
    private final String AFLogger;

    /* renamed from: d */
    private AFd1iSDK.AFa1ySDK f181d;

    /* renamed from: e */
    private final Lazy f182e;
    private final Lazy registerClient;
    private final Lazy unregisterClient;
    private AFd1mSDK valueOf;
    private final Lazy values;

    public AFd1hSDK(AFd1mSDK aFd1mSDK) {
        Intrinsics.checkNotNullParameter(aFd1mSDK, "");
        this.valueOf = aFd1mSDK;
        this.AFKeystoreWrapper = LazyKt.lazy(new Function0<AFf1bSDK>() { // from class: com.appsflyer.internal.AFd1hSDK.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: valueOf, reason: merged with bridge method [inline-methods] */
            public final AFf1bSDK invoke() {
                AFf1bSDK mo76d = AFd1hSDK.values(AFd1hSDK.this).mo76d();
                Intrinsics.checkNotNullExpressionValue(mo76d, "");
                return mo76d;
            }
        });
        this.AFInAppEventType = LazyKt.lazy(new Function0<AFd1sSDK>() { // from class: com.appsflyer.internal.AFd1hSDK.2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: values, reason: merged with bridge method [inline-methods] */
            public final AFd1sSDK invoke() {
                AFd1sSDK AFInAppEventType = AFd1hSDK.values(AFd1hSDK.this).AFInAppEventType();
                Intrinsics.checkNotNullExpressionValue(AFInAppEventType, "");
                return AFInAppEventType;
            }
        });
        this.AFInAppEventParameterName = LazyKt.lazy(new Function0<AFd1tSDK>() { // from class: com.appsflyer.internal.AFd1hSDK.3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFInAppEventType, reason: merged with bridge method [inline-methods] */
            public final AFd1tSDK invoke() {
                AFd1tSDK values = AFd1hSDK.values(AFd1hSDK.this).values();
                Intrinsics.checkNotNullExpressionValue(values, "");
                return values;
            }
        });
        this.values = LazyKt.lazy(new Function0<AFg1xSDK>() { // from class: com.appsflyer.internal.AFd1hSDK.10
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFKeystoreWrapper, reason: merged with bridge method [inline-methods] */
            public final AFg1xSDK invoke() {
                AFg1xSDK force2 = AFd1hSDK.values(AFd1hSDK.this).force();
                Intrinsics.checkNotNullExpressionValue(force2, "");
                return force2;
            }
        });
        this.unregisterClient = LazyKt.lazy(new Function0<ExecutorService>() { // from class: com.appsflyer.internal.AFd1hSDK.4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFInAppEventParameterName, reason: merged with bridge method [inline-methods] */
            public final ExecutorService invoke() {
                ExecutorService AFInAppEventParameterName = AFd1hSDK.values(AFd1hSDK.this).AFInAppEventParameterName();
                Intrinsics.checkNotNullExpressionValue(AFInAppEventParameterName, "");
                return AFInAppEventParameterName;
            }
        });
        this.AFLogger = "6.13.1";
        this.registerClient = LazyKt.lazy(new Function0<AFd1jSDK>() { // from class: com.appsflyer.internal.AFd1hSDK.5
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFInAppEventParameterName, reason: merged with bridge method [inline-methods] */
            public final AFd1jSDK invoke() {
                AFd1kSDK mo80w = AFd1hSDK.values(AFd1hSDK.this).mo80w();
                Intrinsics.checkNotNullExpressionValue(mo80w, "");
                return new AFd1jSDK(mo80w);
            }
        });
        this.f182e = LazyKt.lazy(new Function0<AFd1bSDK>() { // from class: com.appsflyer.internal.AFd1hSDK.6
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: values, reason: merged with bridge method [inline-methods] */
            public final AFd1bSDK invoke() {
                return new AFd1bSDK(AFd1hSDK.this.values());
            }
        });
    }

    public static final /* synthetic */ AFd1mSDK values(AFd1hSDK aFd1hSDK) {
        int i = afInfoLog + 97;
        f180i = i % 128;
        boolean z = i % 2 == 0;
        AFd1mSDK aFd1mSDK = aFd1hSDK.valueOf;
        if (z) {
            return aFd1mSDK;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    private final AFf1bSDK AFKeystoreWrapper() {
        int i = afInfoLog + 125;
        f180i = i % 128;
        int i2 = i % 2;
        AFf1bSDK aFf1bSDK = (AFf1bSDK) this.AFKeystoreWrapper.getValue();
        int i3 = f180i + 31;
        afInfoLog = i3 % 128;
        if ((i3 % 2 == 0 ? '/' : '\n') == '\n') {
            return aFf1bSDK;
        }
        throw null;
    }

    private final AFd1sSDK valueOf() {
        int i = f180i + 37;
        afInfoLog = i % 128;
        int i2 = i % 2;
        AFd1sSDK aFd1sSDK = (AFd1sSDK) this.AFInAppEventType.getValue();
        int i3 = f180i + 69;
        afInfoLog = i3 % 128;
        int i4 = i3 % 2;
        return aFd1sSDK;
    }

    /* renamed from: d */
    private final AFd1tSDK m73d() {
        int i = f180i + 59;
        afInfoLog = i % 128;
        Object obj = null;
        if (i % 2 == 0) {
            throw null;
        }
        AFd1tSDK aFd1tSDK = (AFd1tSDK) this.AFInAppEventParameterName.getValue();
        int i2 = afInfoLog + 61;
        f180i = i2 % 128;
        if ((i2 % 2 == 0 ? 'G' : ';') == 'G') {
            return aFd1tSDK;
        }
        obj.hashCode();
        throw null;
    }

    private final AFg1xSDK AFLogger() {
        int i = afInfoLog + 41;
        f180i = i % 128;
        if (!(i % 2 != 0)) {
            return (AFg1xSDK) this.values.getValue();
        }
        int i2 = 51 / 0;
        return (AFg1xSDK) this.values.getValue();
    }

    private final ExecutorService unregisterClient() {
        int i = afInfoLog + 71;
        f180i = i % 128;
        if ((i % 2 != 0 ? 'K' : '\"') == '\"') {
            return (ExecutorService) this.unregisterClient.getValue();
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    /* renamed from: e */
    private final AFh1nSDK m74e() {
        AFh1hSDK aFh1hSDK = AFKeystoreWrapper().valueOf.AFInAppEventParameterName;
        Object obj = null;
        if (aFh1hSDK != null) {
            int i = f180i + 125;
            afInfoLog = i % 128;
            int i2 = i % 2;
            AFh1gSDK aFh1gSDK = aFh1hSDK.AFInAppEventType;
            if (!(aFh1gSDK == null)) {
                int i3 = afInfoLog + 93;
                f180i = i3 % 128;
                boolean z = i3 % 2 == 0;
                AFh1nSDK aFh1nSDK = aFh1gSDK.AFKeystoreWrapper;
                if (z) {
                    return aFh1nSDK;
                }
                obj.hashCode();
                throw null;
            }
        }
        return null;
    }

    public final AFd1gSDK values() {
        int i = afInfoLog + 101;
        f180i = i % 128;
        int i2 = i % 2;
        AFd1gSDK aFd1gSDK = (AFd1gSDK) this.registerClient.getValue();
        int i3 = f180i + 5;
        afInfoLog = i3 % 128;
        int i4 = i3 % 2;
        return aFd1gSDK;
    }

    private AFd1dSDK registerClient() {
        int i = f180i + 105;
        afInfoLog = i % 128;
        int i2 = i % 2;
        AFd1dSDK aFd1dSDK = (AFd1dSDK) this.f182e.getValue();
        int i3 = f180i + 17;
        afInfoLog = i3 % 128;
        if ((i3 % 2 == 0 ? (char) 4 : '.') == '.') {
            return aFd1dSDK;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    @Override // com.appsflyer.internal.AFd1iSDK
    public final void AFKeystoreWrapper(final Throwable th, final String str) {
        int i = afInfoLog + 83;
        f180i = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(th, "");
        Intrinsics.checkNotNullParameter(str, "");
        unregisterClient().execute(new Runnable() { // from class: com.appsflyer.internal.AFd1hSDK$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AFd1hSDK.values(AFd1hSDK.this, th, str);
            }
        });
        int i3 = f180i + 39;
        afInfoLog = i3 % 128;
        int i4 = i3 % 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void values(AFd1hSDK aFd1hSDK, Throwable th, String str) {
        int i = afInfoLog + 107;
        f180i = i % 128;
        if (i % 2 != 0) {
            Intrinsics.checkNotNullParameter(aFd1hSDK, "");
            Intrinsics.checkNotNullParameter(th, "");
            Intrinsics.checkNotNullParameter(str, "");
            aFd1hSDK.m74e();
            throw null;
        }
        Intrinsics.checkNotNullParameter(aFd1hSDK, "");
        Intrinsics.checkNotNullParameter(th, "");
        Intrinsics.checkNotNullParameter(str, "");
        AFh1nSDK m74e = aFd1hSDK.m74e();
        boolean z = false;
        if (!(m74e == null)) {
            int i2 = f180i + 103;
            afInfoLog = i2 % 128;
            int i3 = i2 % 2;
            if (aFd1hSDK.valueOf(m74e)) {
                z = true;
            }
        }
        if (z) {
            aFd1hSDK.values().valueOf(th, str);
        }
    }

    @Override // com.appsflyer.internal.AFd1iSDK
    public final void valueOf(AFd1iSDK.AFa1ySDK aFa1ySDK) {
        int i = afInfoLog + 27;
        f180i = i % 128;
        int i2 = i % 2;
        this.f181d = aFa1ySDK;
        unregisterClient().execute(new Runnable() { // from class: com.appsflyer.internal.AFd1hSDK$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AFd1hSDK.AFInAppEventType(AFd1hSDK.this);
            }
        });
        int i3 = f180i + 101;
        afInfoLog = i3 % 128;
        if (i3 % 2 == 0) {
            int i4 = 81 / 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AFInAppEventType(AFd1hSDK aFd1hSDK) {
        int i = afInfoLog + 25;
        f180i = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(aFd1hSDK, "");
        aFd1hSDK.afInfoLog();
        int i3 = f180i + 119;
        afInfoLog = i3 % 128;
        if (!(i3 % 2 == 0)) {
        } else {
            throw null;
        }
    }

    @Override // com.appsflyer.internal.AFd1iSDK
    public final void AFInAppEventParameterName() {
        int i = afInfoLog + 19;
        f180i = i % 128;
        int i2 = i % 2;
        unregisterClient().execute(new Runnable() { // from class: com.appsflyer.internal.AFd1hSDK$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AFd1hSDK.valueOf(AFd1hSDK.this);
            }
        });
        int i3 = f180i + 63;
        afInfoLog = i3 % 128;
        if (i3 % 2 == 0) {
            int i4 = 7 / 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void valueOf(AFd1hSDK aFd1hSDK) {
        int i = f180i + 3;
        afInfoLog = i % 128;
        if ((i % 2 == 0 ? '9' : 'G') != '9') {
            Intrinsics.checkNotNullParameter(aFd1hSDK, "");
            aFd1hSDK.force();
        } else {
            Intrinsics.checkNotNullParameter(aFd1hSDK, "");
            aFd1hSDK.force();
            Object obj = null;
            obj.hashCode();
            throw null;
        }
    }

    @Override // com.appsflyer.internal.AFd1iSDK
    public final void AFInAppEventType() {
        int i = afInfoLog + 103;
        f180i = i % 128;
        int i2 = i % 2;
        unregisterClient().execute(new Runnable() { // from class: com.appsflyer.internal.AFd1hSDK$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AFd1hSDK.AFInAppEventParameterName(AFd1hSDK.this);
            }
        });
        int i3 = afInfoLog + 31;
        f180i = i3 % 128;
        if (i3 % 2 != 0) {
            int i4 = 52 / 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AFInAppEventParameterName(AFd1hSDK aFd1hSDK) {
        int i = f180i + 65;
        afInfoLog = i % 128;
        int i2 = i % 2;
        Intrinsics.checkNotNullParameter(aFd1hSDK, "");
        aFd1hSDK.m75v();
        int i3 = afInfoLog + 3;
        f180i = i3 % 128;
        int i4 = i3 % 2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x014c, code lost:
    
        if (r0.intValue() == (-1)) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x020b, code lost:
    
        if (r1 != null) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0216, code lost:
    
        r3 = valueOf(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0214, code lost:
    
        if (r1 != null) goto L133;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0148 A[Catch: all -> 0x0221, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0011, B:6:0x0016, B:8:0x0024, B:12:0x0045, B:18:0x005d, B:20:0x0065, B:22:0x0069, B:24:0x007d, B:30:0x00aa, B:36:0x00df, B:40:0x00f2, B:42:0x00f8, B:44:0x00fe, B:45:0x0104, B:46:0x0107, B:47:0x010d, B:51:0x011b, B:53:0x011f, B:54:0x0125, B:56:0x012b, B:58:0x0136, B:60:0x013a, B:64:0x0150, B:65:0x01ed, B:67:0x01f1, B:71:0x0204, B:76:0x021a, B:79:0x0216, B:82:0x020f, B:83:0x0210, B:89:0x0161, B:91:0x016c, B:92:0x0188, B:97:0x01a5, B:102:0x01b0, B:103:0x01cc, B:105:0x0148, B:107:0x013f, B:112:0x0143, B:118:0x00c0, B:123:0x00cc, B:127:0x00d9, B:131:0x008f, B:133:0x0095, B:135:0x009b, B:139:0x01dd, B:140:0x0054, B:109:0x0141, B:94:0x01a3, B:74:0x020a), top: B:2:0x0001, inners: #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x00c0 A[Catch: all -> 0x0221, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0011, B:6:0x0016, B:8:0x0024, B:12:0x0045, B:18:0x005d, B:20:0x0065, B:22:0x0069, B:24:0x007d, B:30:0x00aa, B:36:0x00df, B:40:0x00f2, B:42:0x00f8, B:44:0x00fe, B:45:0x0104, B:46:0x0107, B:47:0x010d, B:51:0x011b, B:53:0x011f, B:54:0x0125, B:56:0x012b, B:58:0x0136, B:60:0x013a, B:64:0x0150, B:65:0x01ed, B:67:0x01f1, B:71:0x0204, B:76:0x021a, B:79:0x0216, B:82:0x020f, B:83:0x0210, B:89:0x0161, B:91:0x016c, B:92:0x0188, B:97:0x01a5, B:102:0x01b0, B:103:0x01cc, B:105:0x0148, B:107:0x013f, B:112:0x0143, B:118:0x00c0, B:123:0x00cc, B:127:0x00d9, B:131:0x008f, B:133:0x0095, B:135:0x009b, B:139:0x01dd, B:140:0x0054, B:109:0x0141, B:94:0x01a3, B:74:0x020a), top: B:2:0x0001, inners: #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f2 A[Catch: all -> 0x0221, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0011, B:6:0x0016, B:8:0x0024, B:12:0x0045, B:18:0x005d, B:20:0x0065, B:22:0x0069, B:24:0x007d, B:30:0x00aa, B:36:0x00df, B:40:0x00f2, B:42:0x00f8, B:44:0x00fe, B:45:0x0104, B:46:0x0107, B:47:0x010d, B:51:0x011b, B:53:0x011f, B:54:0x0125, B:56:0x012b, B:58:0x0136, B:60:0x013a, B:64:0x0150, B:65:0x01ed, B:67:0x01f1, B:71:0x0204, B:76:0x021a, B:79:0x0216, B:82:0x020f, B:83:0x0210, B:89:0x0161, B:91:0x016c, B:92:0x0188, B:97:0x01a5, B:102:0x01b0, B:103:0x01cc, B:105:0x0148, B:107:0x013f, B:112:0x0143, B:118:0x00c0, B:123:0x00cc, B:127:0x00d9, B:131:0x008f, B:133:0x0095, B:135:0x009b, B:139:0x01dd, B:140:0x0054, B:109:0x0141, B:94:0x01a3, B:74:0x020a), top: B:2:0x0001, inners: #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0150 A[Catch: all -> 0x0221, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0011, B:6:0x0016, B:8:0x0024, B:12:0x0045, B:18:0x005d, B:20:0x0065, B:22:0x0069, B:24:0x007d, B:30:0x00aa, B:36:0x00df, B:40:0x00f2, B:42:0x00f8, B:44:0x00fe, B:45:0x0104, B:46:0x0107, B:47:0x010d, B:51:0x011b, B:53:0x011f, B:54:0x0125, B:56:0x012b, B:58:0x0136, B:60:0x013a, B:64:0x0150, B:65:0x01ed, B:67:0x01f1, B:71:0x0204, B:76:0x021a, B:79:0x0216, B:82:0x020f, B:83:0x0210, B:89:0x0161, B:91:0x016c, B:92:0x0188, B:97:0x01a5, B:102:0x01b0, B:103:0x01cc, B:105:0x0148, B:107:0x013f, B:112:0x0143, B:118:0x00c0, B:123:0x00cc, B:127:0x00d9, B:131:0x008f, B:133:0x0095, B:135:0x009b, B:139:0x01dd, B:140:0x0054, B:109:0x0141, B:94:0x01a3, B:74:0x020a), top: B:2:0x0001, inners: #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0161 A[Catch: all -> 0x0221, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0011, B:6:0x0016, B:8:0x0024, B:12:0x0045, B:18:0x005d, B:20:0x0065, B:22:0x0069, B:24:0x007d, B:30:0x00aa, B:36:0x00df, B:40:0x00f2, B:42:0x00f8, B:44:0x00fe, B:45:0x0104, B:46:0x0107, B:47:0x010d, B:51:0x011b, B:53:0x011f, B:54:0x0125, B:56:0x012b, B:58:0x0136, B:60:0x013a, B:64:0x0150, B:65:0x01ed, B:67:0x01f1, B:71:0x0204, B:76:0x021a, B:79:0x0216, B:82:0x020f, B:83:0x0210, B:89:0x0161, B:91:0x016c, B:92:0x0188, B:97:0x01a5, B:102:0x01b0, B:103:0x01cc, B:105:0x0148, B:107:0x013f, B:112:0x0143, B:118:0x00c0, B:123:0x00cc, B:127:0x00d9, B:131:0x008f, B:133:0x0095, B:135:0x009b, B:139:0x01dd, B:140:0x0054, B:109:0x0141, B:94:0x01a3, B:74:0x020a), top: B:2:0x0001, inners: #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final synchronized void afInfoLog() {
        /*
            Method dump skipped, instructions count: 548
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFd1hSDK.afInfoLog():void");
    }

    private final void force() {
        int i = afInfoLog + 23;
        f180i = i % 128;
        if (i % 2 != 0) {
            m74e();
            throw null;
        }
        AFh1nSDK m74e = m74e();
        if (m74e != null) {
            int i2 = f180i + 59;
            afInfoLog = i2 % 128;
            int i3 = i2 % 2;
            if ((AFInAppEventParameterName(m74e) ? Typography.quote : (char) 0) == 0) {
                AFg1hSDK.v$default(AFLogger.INSTANCE, AFg1gSDK.EXCEPTION_MANAGER, "skipping", false, 4, null);
                int i4 = afInfoLog + 97;
                f180i = i4 % 128;
                int i5 = i4 % 2;
                return;
            }
            String str = AFLogger().registerClient;
            if (str == null) {
                int i6 = afInfoLog + 9;
                f180i = i6 % 128;
                if (i6 % 2 == 0) {
                    return;
                } else {
                    throw null;
                }
            }
            String jSONObject = new JSONObject(AFKeystoreWrapper(AFKeystoreWrapper(m74e), values().AFInAppEventType())).toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject, "");
            Intrinsics.checkNotNullExpressionValue(str, "");
            valueOf(jSONObject, str);
        }
    }

    /* renamed from: v */
    private final synchronized void m75v() {
        boolean z;
        AFh1nSDK m74e = m74e();
        char c = 14;
        Object obj = null;
        if (m74e != null) {
            if (m74e.AFKeystoreWrapper == -1) {
                int i = f180i + 107;
                afInfoLog = i % 128;
                if (i % 2 == 0) {
                    m73d().AFInAppEventType("af_send_exc_to_server_window");
                    throw null;
                }
                m73d().AFInAppEventType("af_send_exc_to_server_window");
            } else {
                if ((m73d().AFKeystoreWrapper("af_send_exc_to_server_window", -1L) == -1 ? (char) 14 : 'W') != 'W') {
                    values(m74e);
                    int i2 = f180i + 25;
                    afInfoLog = i2 % 128;
                    int i3 = i2 % 2;
                }
            }
            z = valueOf(m74e);
        } else {
            z = false;
        }
        AFd1iSDK.AFa1ySDK aFa1ySDK = this.f181d;
        if (aFa1ySDK == null) {
            c = '^';
        }
        if (c != '^') {
            aFa1ySDK.onConfigurationChanged(z);
            return;
        }
        int i4 = f180i + 63;
        afInfoLog = i4 % 128;
        if ((i4 % 2 == 0 ? '2' : (char) 23) != '2') {
            return;
        }
        obj.hashCode();
        throw null;
    }

    private final void values(AFh1nSDK aFh1nSDK) {
        int i = f180i + 73;
        afInfoLog = i % 128;
        int i2 = i % 2;
        int i3 = aFh1nSDK.AFInAppEventType;
        long currentTimeMillis = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(aFh1nSDK.AFKeystoreWrapper);
        AFd1tSDK m73d = m73d();
        m73d.AFInAppEventParameterName("af_send_exc_to_server_window", currentTimeMillis);
        m73d.values("af_send_exc_min", i3);
        int i4 = f180i + 75;
        afInfoLog = i4 % 128;
        if (i4 % 2 != 0) {
        } else {
            throw null;
        }
    }

    private final Map<String, String> AFKeystoreWrapper(AFh1nSDK aFh1nSDK) {
        Object[] objArr = new Object[1];
        m72a(false, "\u0000\u0000\u0001\u0001\u0000", new int[]{0, 5, 0, 0}, objArr);
        AFd1sSDK valueOf = valueOf();
        Map<String, String> mapOf = MapsKt.mapOf(TuplesKt.m1402to(((String) objArr[0]).intern(), Build.BRAND), TuplesKt.m1402to("model", Build.MODEL), TuplesKt.m1402to("app_id", valueOf().AFInAppEventParameterName.valueOf.getPackageName()), TuplesKt.m1402to("p_ex", new AFb1cSDK().valueOf()), TuplesKt.m1402to("api", String.valueOf(Build.VERSION.SDK_INT)), TuplesKt.m1402to(ServerProtocol.DIALOG_PARAM_SDK_VERSION, this.AFLogger), TuplesKt.m1402to("uid", AFb1kSDK.AFInAppEventType(valueOf.AFInAppEventParameterName, valueOf.AFKeystoreWrapper)), TuplesKt.m1402to("exc_config", aFh1nSDK.AFInAppEventParameterName()));
        int i = f180i + 71;
        afInfoLog = i % 128;
        if ((i % 2 == 0 ? 'R' : '\'') != 'R') {
            return mapOf;
        }
        throw null;
    }

    private static Map<String, Object> AFKeystoreWrapper(Map<String, ? extends Object> map, List<AFd1fSDK> list) {
        Map<String, Object> mapOf;
        int i = afInfoLog + 73;
        f180i = i % 128;
        if ((i % 2 != 0 ? '?' : 'G') != 'G') {
            Pair[] pairArr = new Pair[3];
            pairArr[0] = TuplesKt.m1402to("deviceInfo", map);
            pairArr[0] = TuplesKt.m1402to("excs", AFd1eSDK.AFInAppEventParameterName(list));
            mapOf = MapsKt.mapOf(pairArr);
        } else {
            mapOf = MapsKt.mapOf(TuplesKt.m1402to("deviceInfo", map), TuplesKt.m1402to("excs", AFd1eSDK.AFInAppEventParameterName(list)));
        }
        int i2 = f180i + 65;
        afInfoLog = i2 % 128;
        int i3 = i2 % 2;
        return mapOf;
    }

    private final boolean AFInAppEventParameterName(AFh1nSDK aFh1nSDK) {
        long currentTimeMillis = System.currentTimeMillis();
        long AFKeystoreWrapper = m73d().AFKeystoreWrapper("af_send_exc_to_server_window", -1L);
        if (aFh1nSDK.values < TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis)) {
            int i = f180i + 43;
            afInfoLog = i % 128;
            int i2 = i % 2;
            return false;
        }
        if ((AFKeystoreWrapper != -1 ? 'J' : '*') == 'J') {
            if (!(AFKeystoreWrapper < currentTimeMillis)) {
                int AFInAppEventParameterName = m73d().AFInAppEventParameterName("af_send_exc_min", -1);
                if (AFInAppEventParameterName != -1 && values().AFKeystoreWrapper() >= AFInAppEventParameterName) {
                    return AFInAppEventType(aFh1nSDK);
                }
                int i3 = afInfoLog + 57;
                f180i = i3 % 128;
                int i4 = i3 % 2;
                return false;
            }
        }
        int i5 = f180i + 53;
        afInfoLog = i5 % 128;
        int i6 = i5 % 2;
        return false;
    }

    private final boolean valueOf(AFh1nSDK aFh1nSDK) {
        int i = afInfoLog + 83;
        f180i = i % 128;
        if (i % 2 == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            long AFKeystoreWrapper = m73d().AFKeystoreWrapper("af_send_exc_to_server_window", -1L);
            if (aFh1nSDK.values < TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis)) {
                int i2 = afInfoLog + 85;
                f180i = i2 % 128;
                int i3 = i2 % 2;
                return false;
            }
            if ((AFKeystoreWrapper != -1 ? (char) 2 : '`') != '`') {
                int i4 = f180i + 93;
                afInfoLog = i4 % 128;
                int i5 = i4 % 2;
                if ((AFKeystoreWrapper < currentTimeMillis ? (char) 5 : 'U') == 'U') {
                    return AFInAppEventType(aFh1nSDK);
                }
            }
            return false;
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        m73d().AFKeystoreWrapper("af_send_exc_to_server_window", -1L);
        long j = aFh1nSDK.values;
        TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis2);
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    private final boolean AFInAppEventType(AFh1nSDK aFh1nSDK) {
        new AFe1ySDK();
        String str = this.AFLogger;
        String str2 = aFh1nSDK.AFInAppEventParameterName;
        Intrinsics.checkNotNullExpressionValue(str2, "");
        boolean values = AFe1ySDK.values(str, str2);
        int i = afInfoLog + 83;
        f180i = i % 128;
        int i2 = i % 2;
        return values;
    }

    private final void valueOf(String str, String str2) {
        int i = afInfoLog + 21;
        f180i = i % 128;
        int i2 = i % 2;
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "");
        registerClient().valueOf(bytes, MapsKt.mapOf(TuplesKt.m1402to("Authorization", AFb1mSDK.AFInAppEventType(str, str2))), CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE);
        int i3 = f180i + 105;
        afInfoLog = i3 % 128;
        if ((i3 % 2 == 0 ? Matrix.MATRIX_TYPE_ZERO : 'X') != 'X') {
            int i4 = 98 / 0;
        }
    }

    /* renamed from: a */
    private static void m72a(boolean z, String str, int[] iArr, Object[] objArr) {
        char[] cArr;
        int length;
        char[] cArr2;
        String str2 = str;
        byte[] bArr = str2;
        if (str2 != null) {
            bArr = str2.getBytes(LocalizedMessage.DEFAULT_ENCODING);
        }
        byte[] bArr2 = bArr;
        AFj1lSDK aFj1lSDK = new AFj1lSDK();
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        char[] cArr3 = force;
        if (cArr3 != null) {
            int i5 = $11 + 103;
            $10 = i5 % 128;
            if (i5 % 2 != 0) {
                length = cArr3.length;
                cArr2 = new char[length];
            } else {
                length = cArr3.length;
                cArr2 = new char[length];
            }
            int i6 = 0;
            while (true) {
                if (i6 >= length) {
                    break;
                }
                cArr2[i6] = (char) (cArr3[i6] ^ 5319028286697339858L);
                i6++;
            }
            cArr3 = cArr2;
        }
        char[] cArr4 = new char[i2];
        System.arraycopy(cArr3, i, cArr4, 0, i2);
        if (bArr2 != null) {
            char[] cArr5 = new char[i2];
            aFj1lSDK.AFKeystoreWrapper = 0;
            char c = 0;
            while (aFj1lSDK.AFKeystoreWrapper < i2) {
                if (bArr2[aFj1lSDK.AFKeystoreWrapper] == 1) {
                    cArr5[aFj1lSDK.AFKeystoreWrapper] = (char) (((cArr4[aFj1lSDK.AFKeystoreWrapper] * 2) + 1) - c);
                } else {
                    cArr5[aFj1lSDK.AFKeystoreWrapper] = (char) ((cArr4[aFj1lSDK.AFKeystoreWrapper] * 2) - c);
                }
                c = cArr5[aFj1lSDK.AFKeystoreWrapper];
                aFj1lSDK.AFKeystoreWrapper++;
            }
            cArr4 = cArr5;
        }
        if ((i4 > 0 ? (char) 16 : 'C') == 16) {
            int i7 = $11 + 45;
            $10 = i7 % 128;
            if (!(i7 % 2 != 0)) {
                char[] cArr6 = new char[i2];
                System.arraycopy(cArr4, 0, cArr6, 0, i2);
                int i8 = i2 - i4;
                System.arraycopy(cArr6, 0, cArr4, i8, i4);
                System.arraycopy(cArr6, i4, cArr4, 0, i8);
            } else {
                char[] cArr7 = new char[i2];
                System.arraycopy(cArr4, 1, cArr7, 1, i2);
                System.arraycopy(cArr7, 0, cArr4, i2 - i4, i4);
                System.arraycopy(cArr7, i4, cArr4, 1, i2 >>> i4);
            }
        }
        if (z) {
            int i9 = $10 + 5;
            $11 = i9 % 128;
            if (i9 % 2 == 0) {
                cArr = new char[i2];
                aFj1lSDK.AFKeystoreWrapper = 1;
            } else {
                cArr = new char[i2];
                aFj1lSDK.AFKeystoreWrapper = 0;
            }
            while (aFj1lSDK.AFKeystoreWrapper < i2) {
                cArr[aFj1lSDK.AFKeystoreWrapper] = cArr4[(i2 - aFj1lSDK.AFKeystoreWrapper) - 1];
                aFj1lSDK.AFKeystoreWrapper++;
            }
            cArr4 = cArr;
        }
        if (i3 > 0) {
            int i10 = $10 + 15;
            $11 = i10 % 128;
            if (i10 % 2 == 0) {
                aFj1lSDK.AFKeystoreWrapper = 1;
            } else {
                aFj1lSDK.AFKeystoreWrapper = 0;
            }
            while (aFj1lSDK.AFKeystoreWrapper < i2) {
                cArr4[aFj1lSDK.AFKeystoreWrapper] = (char) (cArr4[aFj1lSDK.AFKeystoreWrapper] - iArr[2]);
                aFj1lSDK.AFKeystoreWrapper++;
            }
        }
        String str3 = new String(cArr4);
        int i11 = $10 + 13;
        $11 = i11 % 128;
        int i12 = i11 % 2;
        objArr[0] = str3;
    }
}
