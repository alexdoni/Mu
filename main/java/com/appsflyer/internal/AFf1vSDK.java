package com.appsflyer.internal;

import android.content.Context;
import com.appsflyer.AFLogger;
import com.google.android.gms.common.GoogleApiAvailability;
import com.samsung.android.game.cloudgame.dev.sdk.CloudDevCallback;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(m1394d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 +2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002+,B\u000f\u0012\u0006\u0010\u0007\u001a\u00020(¢\u0006\u0004\b)\u0010*J\u000f\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u0003¢\u0006\u0004\b\n\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\tJ\u001f\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000b\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\b\u001a\u00020\u0011H\u0017¢\u0006\u0004\b\b\u0010\u0012J\u000f\u0010\n\u001a\u00020\u0013H\u0017¢\u0006\u0004\b\n\u0010\u0014J\u0017\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0010\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0003H\u0014¢\u0006\u0004\b\u0016\u0010\u0005J\u001d\u0010\u000b\u001a\u00020\u0018*\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0017H\u0002¢\u0006\u0004\b\u000b\u0010\u0019R\u0014\u0010\n\u001a\u00020\u00028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u000b\u001a\u00020\u001c8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\b\u001a\u00020\u001f8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\u0016\u001a\u00020\u00038CX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0005R\u001b\u0010\u0010\u001a\u00020\u00038CX\u0083\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b\u001d\u0010\u0005R\u0014\u0010'\u001a\u00020$8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0013\u0010 \u001a\u00020\u0011X\u0083\u0080\u0002¢\u0006\u0006\n\u0004\b'\u0010#"}, m1395d2 = {"Lcom/appsflyer/internal/AFf1vSDK;", "Lcom/appsflyer/internal/AFe1eSDK;", "Lcom/appsflyer/internal/AFh1xSDK;", "", "force", "()Z", "", "p0", "AFInAppEventType", "(I)Z", "values", "AFKeystoreWrapper", "Landroid/content/Context;", "Lcom/appsflyer/internal/AFf1vSDK$AFa1vSDK;", "p1", "(Landroid/content/Context;Lcom/appsflyer/internal/AFf1vSDK$AFa1vSDK;)Z", "valueOf", "", "()J", "Lcom/appsflyer/internal/AFe1dSDK;", "()Lcom/appsflyer/internal/AFe1dSDK;", "(Landroid/content/Context;)I", "AFInAppEventParameterName", "", "", "(Lcom/appsflyer/internal/AFh1xSDK;Ljava/lang/String;)V", "unregisterClient", "Lcom/appsflyer/internal/AFh1xSDK;", "Lcom/appsflyer/internal/AFd1kSDK;", "registerClient", "Lcom/appsflyer/internal/AFd1kSDK;", "Lcom/appsflyer/internal/AFd1sSDK;", "AFLogger", "Lcom/appsflyer/internal/AFd1sSDK;", "w", "Lkotlin/Lazy;", "Lcom/appsflyer/internal/AFd1qSDK;", "d", "Lcom/appsflyer/internal/AFd1qSDK;", "e", "Lcom/appsflyer/internal/AFd1mSDK;", "<init>", "(Lcom/appsflyer/internal/AFd1mSDK;)V", "AFa1tSDK", "AFa1vSDK"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AFf1vSDK extends AFe1eSDK<AFh1xSDK> {

    /* renamed from: AFLogger, reason: from kotlin metadata */
    private final AFd1sSDK AFInAppEventType;

    /* renamed from: d, reason: from kotlin metadata */
    private final AFd1qSDK e;

    /* renamed from: e, reason: from kotlin metadata */
    private final Lazy AFLogger;

    /* renamed from: registerClient, reason: from kotlin metadata */
    private final AFd1kSDK AFKeystoreWrapper;

    /* renamed from: unregisterClient, reason: from kotlin metadata */
    private final AFh1xSDK values;

    /* renamed from: w, reason: from kotlin metadata */
    private final Lazy valueOf;

    @Override // com.appsflyer.internal.AFe1eSDK
    public final boolean AFInAppEventParameterName() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AFf1vSDK(AFd1mSDK aFd1mSDK) {
        super(AFf1zSDK.FETCH_ADVERTISING_ID, new AFf1zSDK[0], "FetchAdvertisingIdTask");
        Intrinsics.checkNotNullParameter(aFd1mSDK, "");
        AFd1kSDK mo80w = aFd1mSDK.mo80w();
        Intrinsics.checkNotNullExpressionValue(mo80w, "");
        this.AFKeystoreWrapper = mo80w;
        AFd1sSDK AFInAppEventType = aFd1mSDK.AFInAppEventType();
        Intrinsics.checkNotNullExpressionValue(AFInAppEventType, "");
        this.AFInAppEventType = AFInAppEventType;
        AFd1qSDK afErrorLogForExcManagerOnly = aFd1mSDK.afErrorLogForExcManagerOnly();
        Intrinsics.checkNotNullExpressionValue(afErrorLogForExcManagerOnly, "");
        this.e = afErrorLogForExcManagerOnly;
        this.values = new AFh1xSDK(null, null, null, null, null, null, null, null, 255, null);
        this.AFLogger = LazyKt.lazy(new Function0<Long>() { // from class: com.appsflyer.internal.AFf1vSDK.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Long invoke() {
                Long longOrNull;
                String AFInAppEventParameterName = AFf1vSDK.this.AFInAppEventType.AFInAppEventParameterName("com.appsflyer.fetch_ids.timeout");
                return Long.valueOf((AFInAppEventParameterName == null || (longOrNull = StringsKt.toLongOrNull(AFInAppEventParameterName)) == null) ? 1000L : longOrNull.longValue());
            }
        });
        this.valueOf = LazyKt.lazy(new Function0<Boolean>() { // from class: com.appsflyer.internal.AFf1vSDK.4
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(Boolean.parseBoolean(AFf1vSDK.this.AFInAppEventType.AFInAppEventParameterName("com.appsflyer.enable_instant_plays")));
            }
        });
    }

    private final boolean registerClient() {
        return ((Boolean) this.valueOf.getValue()).booleanValue();
    }

    private static boolean unregisterClient() {
        String obj;
        try {
            Class.forName("com.samsung.android.game.cloudgame.dev.sdk.CloudDevSdk");
            return true;
        } catch (Throwable th) {
            if (th instanceof ClassNotFoundException) {
                obj = "CloudDevSdk not found";
            } else {
                StringBuilder sb = new StringBuilder("Unexpected exception while checking if running in cloud environment: ");
                sb.append(th.getMessage());
                obj = sb.toString();
            }
            AFLogger.afErrorLog(obj, th, true);
            return false;
        }
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final AFe1dSDK values() {
        AFe1dSDK aFe1dSDK;
        if (this.e.AFInAppEventType()) {
            AFLogger.afRDLog("QUEUE: Advertising ID collection is disabled. Skipping fetching... ");
            return AFe1dSDK.FAILURE;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (CollectionsKt.listOf((Object[]) new Boolean[]{Boolean.valueOf(force()), Boolean.FALSE, Boolean.FALSE}).contains(Boolean.TRUE)) {
            aFe1dSDK = AFe1dSDK.SUCCESS;
        } else {
            aFe1dSDK = AFe1dSDK.FAILURE;
        }
        AFd1qSDK aFd1qSDK = this.e;
        AFe1mSDK aFe1mSDK = new AFe1mSDK(System.currentTimeMillis() - currentTimeMillis);
        StringBuilder sb = new StringBuilder("QUEUE: FetchAdvertisingIdTask: took ");
        sb.append(aFe1mSDK.AFKeystoreWrapper);
        sb.append("ms");
        AFLogger.afRDLog(sb.toString());
        aFd1qSDK.AFInAppEventType(aFe1mSDK);
        return aFe1dSDK;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d A[LOOP:0: B:2:0x0003->B:10:0x002d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0030 A[EDGE_INSN: B:11:0x0030->B:12:0x0030 BREAK  A[LOOP:0: B:2:0x0003->B:10:0x002d], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean force() {
        /*
            r4 = this;
            r0 = 2
            r1 = 0
            r2 = r1
        L3:
            if (r0 <= 0) goto L30
            boolean r2 = r4.registerClient()
            r3 = 1
            if (r2 == 0) goto L18
            boolean r2 = r4.AFKeystoreWrapper(r0)
            if (r2 == 0) goto L18
            java.lang.String r2 = "GAID fetched using Samsung Cloud dev SDK"
            com.appsflyer.AFLogger.afRDLog(r2)
            goto L23
        L18:
            boolean r2 = r4.AFInAppEventType(r0)
            if (r2 == 0) goto L25
            java.lang.String r2 = "GAID fetched using GMS"
            com.appsflyer.AFLogger.afRDLog(r2)
        L23:
            r2 = r3
            goto L2b
        L25:
            java.lang.String r2 = "Failed to fetch GAID"
            com.appsflyer.AFLogger.afRDLog(r2)
            r2 = r1
        L2b:
            if (r2 != 0) goto L30
            int r0 = r0 + (-1)
            goto L3
        L30:
            com.appsflyer.internal.AFd1qSDK r0 = r4.e
            com.appsflyer.internal.AFh1xSDK r1 = r4.values
            r0.f189e = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFf1vSDK.force():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0084 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean AFInAppEventType(int r9) {
        /*
            r8 = this;
            java.lang.String r0 = "Trying to fetch GAID..."
            com.appsflyer.AFLogger.afInfoLog(r0)
            com.appsflyer.internal.AFf1vSDK$AFa1vSDK r0 = new com.appsflyer.internal.AFf1vSDK$AFa1vSDK
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 15
            r7 = 0
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7)
            com.appsflyer.internal.AFd1kSDK r1 = r8.AFKeystoreWrapper
            android.content.Context r1 = r1.valueOf
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r1 = valueOf(r1)
            com.appsflyer.internal.AFd1kSDK r2 = r8.AFKeystoreWrapper
            android.content.Context r2 = r2.valueOf
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            boolean r2 = r8.valueOf(r2, r0)
            r3 = 0
            r4 = 1
            if (r2 != 0) goto L85
            com.appsflyer.AppsFlyerProperties r2 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r5 = "enableGpsFallback"
            boolean r2 = r2.getBoolean(r5, r4)
            if (r2 == 0) goto L47
            com.appsflyer.internal.AFd1kSDK r2 = r8.AFKeystoreWrapper
            android.content.Context r2 = r2.valueOf
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            boolean r2 = r8.AFKeystoreWrapper(r2, r0)
            if (r2 == 0) goto L47
            r2 = r4
            goto L48
        L47:
            r2 = r3
        L48:
            java.lang.StringBuilder r5 = r0.getGaidError()
            java.lang.String r5 = r5.toString()
            r6 = r5
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            if (r6 == 0) goto L5e
            boolean r7 = kotlin.text.StringsKt.isBlank(r6)
            if (r7 == 0) goto L5c
            goto L5e
        L5c:
            r7 = r3
            goto L5f
        L5e:
            r7 = r4
        L5f:
            if (r7 != 0) goto L7d
            java.lang.CharSequence r5 = kotlin.text.StringsKt.trim(r6)
            java.lang.String r5 = r5.toString()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r1)
            java.lang.String r1 = ": "
            r6.append(r1)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
        L7d:
            com.appsflyer.internal.AFh1xSDK r1 = r8.values
            AFKeystoreWrapper(r1, r5)
            if (r2 != 0) goto L85
            return r3
        L85:
            com.appsflyer.internal.AFh1xSDK r1 = r8.values
            java.lang.String r2 = r0.getAdvertisingId()
            r1.AFInAppEventType = r2
            java.lang.Boolean r2 = r0.isLimitAdTrackingEnabled()
            r1.unregisterClient = r2
            java.lang.Boolean r2 = r0.isLimitAdTrackingEnabled()
            if (r2 == 0) goto La3
            boolean r2 = r2.booleanValue()
            r2 = r2 ^ r4
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            goto La4
        La3:
            r2 = 0
        La4:
            r1.valueOf = r2
            boolean r0 = r0.getAdvertisingIdWithGps()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r1.AFInAppEventParameterName = r0
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r1.AFKeystoreWrapper = r0
            r0 = 2
            if (r9 == r0) goto Lb8
            r3 = r4
        Lb8:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r3)
            r1.d = r9
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFf1vSDK.AFInAppEventType(int):boolean");
    }

    private static int valueOf(Context p0) {
        try {
            return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(p0);
        } catch (Throwable th) {
            AFLogger.afErrorLogForExcManagerOnly("isGooglePlayServicesAvailable error", th);
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002a A[Catch: all -> 0x0050, TryCatch #0 {all -> 0x0050, blocks: (B:3:0x0002, B:5:0x001e, B:10:0x002a, B:11:0x0033, B:16:0x0038, B:17:0x004f), top: B:2:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0038 A[Catch: all -> 0x0050, TryCatch #0 {all -> 0x0050, blocks: (B:3:0x0002, B:5:0x001e, B:10:0x002a, B:11:0x0033, B:16:0x0038, B:17:0x004f), top: B:2:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean AFKeystoreWrapper(android.content.Context r5, com.appsflyer.internal.AFf1vSDK.AFa1vSDK r6) throws java.lang.IllegalStateException {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            com.appsflyer.internal.AFb1zSDK$AFa1uSDK r5 = com.appsflyer.internal.AFb1zSDK.valueOf(r5)     // Catch: java.lang.Throwable -> L50
            java.lang.String r2 = r5.valueOf     // Catch: java.lang.Throwable -> L50
            r6.setAdvertisingId(r2)     // Catch: java.lang.Throwable -> L50
            boolean r5 = r5.values()     // Catch: java.lang.Throwable -> L50
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch: java.lang.Throwable -> L50
            r6.setLimitAdTrackingEnabled(r5)     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = r6.getAdvertisingId()     // Catch: java.lang.Throwable -> L50
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch: java.lang.Throwable -> L50
            if (r5 == 0) goto L27
            int r5 = r5.length()     // Catch: java.lang.Throwable -> L50
            if (r5 != 0) goto L25
            goto L27
        L25:
            r5 = r1
            goto L28
        L27:
            r5 = r0
        L28:
            if (r5 == 0) goto L33
            java.lang.StringBuilder r5 = r6.getGaidError()     // Catch: java.lang.Throwable -> L50
            java.lang.String r2 = "emptyOrNull (bypass) |"
            r5.append(r2)     // Catch: java.lang.Throwable -> L50
        L33:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L50
            if (r5 == 0) goto L38
            goto L88
        L38:
            r5 = r4
            com.appsflyer.internal.AFf1vSDK r5 = (com.appsflyer.internal.AFf1vSDK) r5     // Catch: java.lang.Throwable -> L50
            java.lang.StringBuilder r5 = r6.getGaidError()     // Catch: java.lang.Throwable -> L50
            java.lang.String r2 = "gpsAdInfo-null (bypass) |"
            r5.append(r2)     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = "GpsAdInfo is null (bypass)"
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L50
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L50
            throw r2     // Catch: java.lang.Throwable -> L50
        L50:
            r5 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Failed to fetch GAID: "
            r2.<init>(r3)
            java.lang.String r3 = r5.getMessage()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.appsflyer.AFLogger.afErrorLog(r2, r5, r0, r1, r1)
            java.lang.StringBuilder r6 = r6.getGaidError()
            java.lang.Class r0 = r5.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r6.append(r0)
            java.lang.String r0 = " |"
            r6.append(r0)
            java.lang.String r6 = r5.getLocalizedMessage()
            if (r6 != 0) goto L84
            java.lang.String r6 = r5.toString()
        L84:
            com.appsflyer.AFLogger.afInfoLog(r6)
            r0 = r1
        L88:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFf1vSDK.AFKeystoreWrapper(android.content.Context, com.appsflyer.internal.AFf1vSDK$AFa1vSDK):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0036 A[Catch: all -> 0x005f, TryCatch #0 {all -> 0x005f, blocks: (B:3:0x0001, B:5:0x000d, B:7:0x002a, B:12:0x0036, B:13:0x003f, B:18:0x0047, B:19:0x005e), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean valueOf(android.content.Context r4, com.appsflyer.internal.AFf1vSDK.AFa1vSDK r5) throws java.lang.IllegalStateException {
        /*
            r3 = this;
            r0 = 0
            java.lang.String r1 = "com.google.android.gms.ads.identifier.AdvertisingIdClient"
            java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> L5f
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r4 = com.google.android.gms.ads.identifier.AdvertisingIdClient.getAdvertisingIdInfo(r4)     // Catch: java.lang.Throwable -> L5f
            r1 = 1
            if (r4 == 0) goto L42
            java.lang.String r2 = r4.getId()     // Catch: java.lang.Throwable -> L5f
            r5.setAdvertisingId(r2)     // Catch: java.lang.Throwable -> L5f
            boolean r4 = r4.isLimitAdTrackingEnabled()     // Catch: java.lang.Throwable -> L5f
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch: java.lang.Throwable -> L5f
            r5.setLimitAdTrackingEnabled(r4)     // Catch: java.lang.Throwable -> L5f
            r5.setAdvertisingIdWithGps(r1)     // Catch: java.lang.Throwable -> L5f
            java.lang.String r4 = r5.getAdvertisingId()     // Catch: java.lang.Throwable -> L5f
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch: java.lang.Throwable -> L5f
            if (r4 == 0) goto L33
            int r4 = r4.length()     // Catch: java.lang.Throwable -> L5f
            if (r4 != 0) goto L31
            goto L33
        L31:
            r4 = r0
            goto L34
        L33:
            r4 = r1
        L34:
            if (r4 == 0) goto L3f
            java.lang.StringBuilder r4 = r5.getGaidError()     // Catch: java.lang.Throwable -> L5f
            java.lang.String r2 = "emptyOrNull |"
            r4.append(r2)     // Catch: java.lang.Throwable -> L5f
        L3f:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L5f
            goto L43
        L42:
            r4 = 0
        L43:
            if (r4 == 0) goto L47
            r0 = r1
            goto L8e
        L47:
            r4 = r3
            com.appsflyer.internal.AFf1vSDK r4 = (com.appsflyer.internal.AFf1vSDK) r4     // Catch: java.lang.Throwable -> L5f
            java.lang.StringBuilder r4 = r5.getGaidError()     // Catch: java.lang.Throwable -> L5f
            java.lang.String r1 = "gpsAdInfo-null |"
            r4.append(r1)     // Catch: java.lang.Throwable -> L5f
            java.lang.String r4 = "GpsAdIndo is null"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L5f
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L5f
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L5f
            throw r1     // Catch: java.lang.Throwable -> L5f
        L5f:
            r4 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Google Play Services is missing "
            r1.<init>(r2)
            java.lang.String r2 = r4.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.appsflyer.AFLogger.afErrorLog(r1, r4)
            java.lang.StringBuilder r5 = r5.getGaidError()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getSimpleName()
            r5.append(r4)
            java.lang.String r4 = " |"
            r5.append(r4)
            java.lang.String r4 = "WARNING: Google Play Services is missing."
            com.appsflyer.AFLogger.afInfoLog(r4)
        L8e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFf1vSDK.valueOf(android.content.Context, com.appsflyer.internal.AFf1vSDK$AFa1vSDK):boolean");
    }

    private final boolean AFKeystoreWrapper(int p0) {
        return values(p0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0069, code lost:
    
        if ((r0.getGaidError().length() > 0) != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x006b, code lost:
    
        AFKeystoreWrapper(r9.values, r0.getGaidError().toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00c3, code lost:
    
        r2 = r0.getAdvertisingId();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00c9, code lost:
    
        if (r2 == null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00cf, code lost:
    
        if (r2.length() != 0) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00d2, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00d5, code lost:
    
        if (r2 != false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00d7, code lost:
    
        r2 = r9.values;
        r2.AFInAppEventType = r0.getAdvertisingId();
        r2.unregisterClient = java.lang.Boolean.FALSE;
        r2.valueOf = java.lang.Boolean.TRUE;
        r2.AFInAppEventParameterName = java.lang.Boolean.FALSE;
        r2.AFKeystoreWrapper = java.lang.Boolean.TRUE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00f0, code lost:
    
        if (r10 == 2) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00f2, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00f3, code lost:
    
        r2.d = java.lang.Boolean.valueOf(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00f9, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00fa, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00d4, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c0, code lost:
    
        if ((r0.getGaidError().length() > 0) != false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean values(int r10) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFf1vSDK.values(int):boolean");
    }

    @Metadata(m1394d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001c\u0010\u0006\u001a\u00020\u00032\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\bH\u0016¨\u0006\t"}, m1395d2 = {"com/appsflyer/internal/components/queue/tasks/FetchAdvertisingIdTask$fetchGaidUsingSamsungSdk$1", "Lcom/samsung/android/game/cloudgame/dev/sdk/CloudDevCallback;", "onError", "", "reason", "", "onSuccess", "kinds", "", "SDK_prodRelease"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final class AFa1ySDK implements CloudDevCallback {
        final /* synthetic */ AFa1vSDK $fetchGaidData;
        final /* synthetic */ CountDownLatch $latch;

        AFa1ySDK(AFa1vSDK aFa1vSDK, CountDownLatch countDownLatch) {
            this.$fetchGaidData = aFa1vSDK;
            this.$latch = countDownLatch;
        }

        public final void onError(String reason) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            AFLogger.afWarnLog("Could not fetch GAID using CloudDevSdk: " + reason);
            StringBuilder gaidError = this.$fetchGaidData.getGaidError();
            gaidError.append(reason);
            gaidError.append(" |");
            this.$latch.countDown();
        }

        public final void onSuccess(Map<String, String> kinds) {
            Intrinsics.checkNotNullParameter(kinds, "kinds");
            AFLogger.afRDLog("CloudDevCallback received onSuccess");
            this.$fetchGaidData.setAdvertisingId(kinds.get("gaid"));
            this.$latch.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(m1394d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0016\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\f\b\u0002\u0010\u0011\u001a\u00060\nj\u0002`\u000b¢\u0006\u0004\b+\u0010,J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÇ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÇ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\f\u001a\u00060\nj\u0002`\u000bHÇ\u0003¢\u0006\u0004\b\f\u0010\rJ@\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\f\b\u0002\u0010\u0011\u001a\u00060\nj\u0002`\u000bHÇ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0014\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0016HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002H×\u0001¢\u0006\u0004\b\u0019\u0010\u0004R$\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u0004\"\u0004\b\u001d\u0010\u001eR\"\u0010\u001f\u001a\u00020\u00058\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\t\"\u0004\b\"\u0010#R\u001e\u0010$\u001a\u00060\nj\u0002`\u000b8\u0007X\u0087\u0004¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010\rR$\u0010'\u001a\u0004\u0018\u00010\u00058\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b'\u0010\u0007\"\u0004\b)\u0010*"}, m1395d2 = {"Lcom/appsflyer/internal/AFf1vSDK$AFa1vSDK;", "", "", "component1", "()Ljava/lang/String;", "", "component2", "()Ljava/lang/Boolean;", "component3", "()Z", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "component4", "()Ljava/lang/StringBuilder;", "p0", "p1", "p2", "p3", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;ZLjava/lang/StringBuilder;)Lcom/appsflyer/internal/AFf1vSDK$AFa1vSDK;", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "advertisingId", "Ljava/lang/String;", "getAdvertisingId", "setAdvertisingId", "(Ljava/lang/String;)V", "advertisingIdWithGps", "Z", "getAdvertisingIdWithGps", "setAdvertisingIdWithGps", "(Z)V", "gaidError", "Ljava/lang/StringBuilder;", "getGaidError", "isLimitAdTrackingEnabled", "Ljava/lang/Boolean;", "setLimitAdTrackingEnabled", "(Ljava/lang/Boolean;)V", "<init>", "(Ljava/lang/String;Ljava/lang/Boolean;ZLjava/lang/StringBuilder;)V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final /* data */ class AFa1vSDK {
        private String advertisingId;
        private boolean advertisingIdWithGps;
        private final StringBuilder gaidError;
        private Boolean isLimitAdTrackingEnabled;

        public AFa1vSDK() {
            this(null, null, false, null, 15, null);
        }

        public static /* synthetic */ AFa1vSDK copy$default(AFa1vSDK aFa1vSDK, String str, Boolean bool, boolean z, StringBuilder sb, int i, Object obj) {
            if ((i & 1) != 0) {
                str = aFa1vSDK.advertisingId;
            }
            if ((i & 2) != 0) {
                bool = aFa1vSDK.isLimitAdTrackingEnabled;
            }
            if ((i & 4) != 0) {
                z = aFa1vSDK.advertisingIdWithGps;
            }
            if ((i & 8) != 0) {
                sb = aFa1vSDK.gaidError;
            }
            return aFa1vSDK.copy(str, bool, z, sb);
        }

        /* renamed from: component1, reason: from getter */
        public final String getAdvertisingId() {
            return this.advertisingId;
        }

        /* renamed from: component2, reason: from getter */
        public final Boolean getIsLimitAdTrackingEnabled() {
            return this.isLimitAdTrackingEnabled;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getAdvertisingIdWithGps() {
            return this.advertisingIdWithGps;
        }

        /* renamed from: component4, reason: from getter */
        public final StringBuilder getGaidError() {
            return this.gaidError;
        }

        public final AFa1vSDK copy(String p0, Boolean p1, boolean p2, StringBuilder p3) {
            Intrinsics.checkNotNullParameter(p3, "gaidError");
            return new AFa1vSDK(p0, p1, p2, p3);
        }

        public final boolean equals(Object p0) {
            if (this == p0) {
                return true;
            }
            if (!(p0 instanceof AFa1vSDK)) {
                return false;
            }
            AFa1vSDK aFa1vSDK = (AFa1vSDK) p0;
            return Intrinsics.areEqual(this.advertisingId, aFa1vSDK.advertisingId) && Intrinsics.areEqual(this.isLimitAdTrackingEnabled, aFa1vSDK.isLimitAdTrackingEnabled) && this.advertisingIdWithGps == aFa1vSDK.advertisingIdWithGps && Intrinsics.areEqual(this.gaidError, aFa1vSDK.gaidError);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final int hashCode() {
            String str = this.advertisingId;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            Boolean bool = this.isLimitAdTrackingEnabled;
            int hashCode2 = (hashCode + (bool != null ? bool.hashCode() : 0)) * 31;
            boolean z = this.advertisingIdWithGps;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return ((hashCode2 + i) * 31) + this.gaidError.hashCode();
        }

        public final String toString() {
            return "AFa1vSDK(advertisingId=" + this.advertisingId + ", isLimitAdTrackingEnabled=" + this.isLimitAdTrackingEnabled + ", advertisingIdWithGps=" + this.advertisingIdWithGps + ", gaidError=" + ((Object) this.gaidError) + ')';
        }

        public AFa1vSDK(String str, Boolean bool, boolean z, StringBuilder gaidError) {
            Intrinsics.checkNotNullParameter(gaidError, "gaidError");
            this.advertisingId = str;
            this.isLimitAdTrackingEnabled = bool;
            this.advertisingIdWithGps = z;
            this.gaidError = gaidError;
        }

        public final String getAdvertisingId() {
            return this.advertisingId;
        }

        public final void setAdvertisingId(String str) {
            this.advertisingId = str;
        }

        public final Boolean isLimitAdTrackingEnabled() {
            return this.isLimitAdTrackingEnabled;
        }

        public final void setLimitAdTrackingEnabled(Boolean bool) {
            this.isLimitAdTrackingEnabled = bool;
        }

        public final boolean getAdvertisingIdWithGps() {
            return this.advertisingIdWithGps;
        }

        public final void setAdvertisingIdWithGps(boolean z) {
            this.advertisingIdWithGps = z;
        }

        public /* synthetic */ AFa1vSDK(String str, Boolean bool, boolean z, StringBuilder sb, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : bool, (i & 4) != 0 ? false : z, (i & 8) != 0 ? new StringBuilder() : sb);
        }

        public final StringBuilder getGaidError() {
            return this.gaidError;
        }
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final long AFInAppEventType() {
        return ((Number) this.AFLogger.getValue()).longValue();
    }

    private static void AFKeystoreWrapper(AFh1xSDK aFh1xSDK, String str) {
        if (str == null) {
            return;
        }
        String str2 = aFh1xSDK.values;
        if (str2 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(" | ");
            sb.append(str);
            String obj = sb.toString();
            if (obj != null) {
                str = obj;
            }
        }
        aFh1xSDK.values = str;
    }
}
