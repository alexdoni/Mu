package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0017\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0011\u001a\u00020\u000e¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u001b\u0010\t\u001a\u00020\u00058GX\u0087\u0084\u0002¢\u0006\f\n\u0004\b\u0003\u0010\f\u001a\u0004\b\r\u0010\u0007R\u001b\u0010\r\u001a\u00020\u00058GX\u0087\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\u0006\u001a\u00020\u000e8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u000f"}, m1395d2 = {"Lcom/appsflyer/internal/AFf1aSDK;", "", "", "AFInAppEventParameterName", "()J", "", "AFKeystoreWrapper", "()Z", "Lcom/appsflyer/internal/AFd1sSDK;", "AFInAppEventType", "Lcom/appsflyer/internal/AFd1sSDK;", "values", "Lkotlin/Lazy;", "valueOf", "Lcom/appsflyer/internal/AFg1ySDK;", "Lcom/appsflyer/internal/AFg1ySDK;", "p0", "p1", "<init>", "(Lcom/appsflyer/internal/AFd1sSDK;Lcom/appsflyer/internal/AFg1ySDK;)V", "AFa1zSDK"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AFf1aSDK {
    private static final long valueOf = TimeUnit.HOURS.toSeconds(24);

    /* renamed from: AFInAppEventParameterName, reason: from kotlin metadata */
    private final Lazy AFInAppEventType;

    /* renamed from: AFInAppEventType, reason: from kotlin metadata */
    private final AFd1sSDK values;
    private final AFg1ySDK AFKeystoreWrapper;

    /* renamed from: values, reason: from kotlin metadata */
    private final Lazy valueOf;

    public AFf1aSDK(AFd1sSDK aFd1sSDK, AFg1ySDK aFg1ySDK) {
        Intrinsics.checkNotNullParameter(aFd1sSDK, "");
        Intrinsics.checkNotNullParameter(aFg1ySDK, "");
        this.values = aFd1sSDK;
        this.AFKeystoreWrapper = aFg1ySDK;
        this.AFInAppEventType = LazyKt.lazy(new Function0<Boolean>() { // from class: com.appsflyer.internal.AFf1aSDK.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFInAppEventType, reason: merged with bridge method [inline-methods] */
            public final Boolean invoke() {
                return Boolean.valueOf(Boolean.parseBoolean(AFf1aSDK.this.values.AFInAppEventParameterName("com.appsflyer.rc.sandbox")));
            }
        });
        this.valueOf = LazyKt.lazy(new Function0<Boolean>() { // from class: com.appsflyer.internal.AFf1aSDK.5
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFKeystoreWrapper, reason: merged with bridge method [inline-methods] */
            public final Boolean invoke() {
                return Boolean.valueOf(Boolean.parseBoolean(AFf1aSDK.this.values.AFInAppEventParameterName("com.appsflyer.rc.staging")));
            }
        });
    }

    public final boolean valueOf() {
        return ((Boolean) this.AFInAppEventType.getValue()).booleanValue();
    }

    public final boolean AFInAppEventType() {
        return ((Boolean) this.valueOf.getValue()).booleanValue();
    }

    public final long AFInAppEventParameterName() {
        Object m1882constructorimpl;
        String AFInAppEventParameterName = this.values.AFInAppEventParameterName("com.appsflyer.rc.cache.max-age-fallback");
        if (AFInAppEventParameterName != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                AFf1aSDK aFf1aSDK = this;
                m1882constructorimpl = Result.m1882constructorimpl(Long.valueOf(Long.parseLong(AFInAppEventParameterName)));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
            }
            Throwable m1885exceptionOrNullimpl = Result.m1885exceptionOrNullimpl(m1882constructorimpl);
            if (m1885exceptionOrNullimpl != null) {
                StringBuilder sb = new StringBuilder("Can't read maxAgeFallback from Manifest: ");
                sb.append(m1885exceptionOrNullimpl.getMessage());
                AFLogger.afErrorLog(sb.toString(), m1885exceptionOrNullimpl);
                m1882constructorimpl = Long.valueOf(valueOf);
            }
            return ((Number) m1882constructorimpl).longValue();
        }
        return valueOf;
    }

    public final boolean AFKeystoreWrapper() {
        AFh1iSDK aFh1iSDK;
        AFh1hSDK aFh1hSDK = this.AFKeystoreWrapper.AFInAppEventParameterName;
        if (aFh1hSDK == null) {
            AFg1hSDK.i$default(AFLogger.INSTANCE, AFg1gSDK.REMOTE_CONTROL, "active config is missing - fetching from CDN", false, 4, null);
            return true;
        }
        AFh1gSDK aFh1gSDK = aFh1hSDK.AFInAppEventType;
        return ((aFh1gSDK == null || (aFh1iSDK = aFh1gSDK.valueOf) == null) ? false : aFh1iSDK.AFInAppEventType()) || System.currentTimeMillis() - this.AFKeystoreWrapper.values > TimeUnit.SECONDS.toMillis(this.AFKeystoreWrapper.valueOf);
    }
}
