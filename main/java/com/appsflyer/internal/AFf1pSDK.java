package com.appsflyer.internal;

import android.adservices.measurement.MeasurementManager;
import android.content.Context;
import android.net.Uri;
import android.os.OutcomeReceiver;
import com.appsflyer.AFLogger;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* loaded from: classes.dex */
public final class AFf1pSDK extends AFe1eSDK<Unit> {
    private final AFg1qSDK AFLogger;

    /* renamed from: d */
    private final AFg1xSDK f230d;

    /* renamed from: e */
    private final AFd1sSDK f231e;
    private final AFd1kSDK registerClient;
    private final String unregisterClient;

    @Override // com.appsflyer.internal.AFe1eSDK
    public final boolean AFInAppEventParameterName() {
        return false;
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final long AFInAppEventType() {
        return 20000L;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AFf1pSDK(AFd1mSDK aFd1mSDK, String str) {
        super(AFf1zSDK.REGISTER_TRIGGER, new AFf1zSDK[]{AFf1zSDK.RC_CDN, AFf1zSDK.CONVERSION}, "RegisterTrigger");
        Intrinsics.checkNotNullParameter(aFd1mSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        this.unregisterClient = str;
        AFd1sSDK AFInAppEventType = aFd1mSDK.AFInAppEventType();
        Intrinsics.checkNotNullExpressionValue(AFInAppEventType, "");
        this.f231e = AFInAppEventType;
        AFd1kSDK mo80w = aFd1mSDK.mo80w();
        Intrinsics.checkNotNullExpressionValue(mo80w, "");
        this.registerClient = mo80w;
        AFg1qSDK unregisterClient = aFd1mSDK.unregisterClient();
        Intrinsics.checkNotNullExpressionValue(unregisterClient, "");
        this.AFLogger = unregisterClient;
        AFg1xSDK force = aFd1mSDK.force();
        Intrinsics.checkNotNullExpressionValue(force, "");
        this.f230d = force;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, com.appsflyer.internal.AFe1dSDK] */
    @Override // com.appsflyer.internal.AFe1eSDK
    public final AFe1dSDK values() {
        MeasurementManager measurementManager;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = AFe1dSDK.FAILURE;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            Context context = this.registerClient.valueOf;
            if (context != null && (measurementManager = (MeasurementManager) context.getSystemService(MeasurementManager.class)) != null) {
                new AFi1eSDK(this.f231e, null, 2, null);
                Uri.Builder buildUpon = Uri.parse(AFi1eSDK.values()).buildUpon();
                AFd1sSDK aFd1sSDK = this.f231e;
                Map mutableMapOf = MapsKt.mutableMapOf(TuplesKt.m1402to("event_name", this.unregisterClient), TuplesKt.m1402to("app_id", this.f231e.AFInAppEventParameterName.valueOf.getPackageName()), TuplesKt.m1402to("app_version", AFb1uSDK.AFKeystoreWrapper(aFd1sSDK.AFInAppEventParameterName.valueOf, aFd1sSDK.AFInAppEventParameterName.valueOf.getPackageName())), TuplesKt.m1402to(ComConstants.sdk_version_code, AFd1sSDK.AFInAppEventParameterName()), TuplesKt.m1402to("api_version", AFd1sSDK.AFKeystoreWrapper()), TuplesKt.m1402to("timestamp", String.valueOf(this.AFLogger.values())), TuplesKt.m1402to("request_id", AFd1sSDK.values()));
                AFd1sSDK aFd1sSDK2 = this.f231e;
                String AFInAppEventType = AFb1kSDK.AFInAppEventType(aFd1sSDK2.AFInAppEventParameterName, aFd1sSDK2.AFKeystoreWrapper);
                if (AFInAppEventType != null) {
                    mutableMapOf.put("appsflyer_id", AFInAppEventType);
                }
                Long AFInAppEventParameterName = this.AFLogger.AFInAppEventParameterName();
                if (AFInAppEventParameterName != null) {
                    mutableMapOf.put("install_time", String.valueOf(AFInAppEventParameterName.longValue()));
                }
                for (Map.Entry entry : mutableMapOf.entrySet()) {
                    buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
                }
                Uri build = buildUpon.build();
                Intrinsics.checkNotNullExpressionValue(build, "");
                measurementManager.registerTrigger(build, new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue()), new AFa1ySDK(objectRef, countDownLatch, this));
            }
            countDownLatch.await(4L, TimeUnit.SECONDS);
        } catch (Throwable th) {
            valueOf(th);
        }
        return (AFe1dSDK) objectRef.element;
    }

    /* loaded from: classes.dex */
    public static final class AFa1ySDK implements OutcomeReceiver<Object, Exception> {
        private /* synthetic */ Ref.ObjectRef<AFe1dSDK> AFInAppEventParameterName;
        private /* synthetic */ CountDownLatch AFInAppEventType;
        private /* synthetic */ AFf1pSDK values;

        AFa1ySDK(Ref.ObjectRef<AFe1dSDK> objectRef, CountDownLatch countDownLatch, AFf1pSDK aFf1pSDK) {
            this.AFInAppEventParameterName = objectRef;
            this.AFInAppEventType = countDownLatch;
            this.values = aFf1pSDK;
        }

        @Override // android.os.OutcomeReceiver
        public final /* synthetic */ void onError(Exception exc) {
            Exception exc2 = exc;
            Intrinsics.checkNotNullParameter(exc2, "");
            AFf1pSDK.valueOf(exc2);
            this.AFInAppEventType.countDown();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [T, com.appsflyer.internal.AFe1dSDK] */
        @Override // android.os.OutcomeReceiver
        public final void onResult(Object obj) {
            Intrinsics.checkNotNullParameter(obj, "");
            this.AFInAppEventParameterName.element = AFe1dSDK.SUCCESS;
            this.AFInAppEventType.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void valueOf(Throwable th) {
        AFLogger aFLogger = AFLogger.INSTANCE;
        AFg1gSDK aFg1gSDK = AFg1gSDK.PRIVACY_SANDBOX;
        StringBuilder sb = new StringBuilder("Error occurred: ");
        sb.append(th.getMessage());
        aFLogger.mo56e(aFg1gSDK, sb.toString(), th, false, false, true, true);
    }
}
