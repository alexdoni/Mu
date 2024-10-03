package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import java.util.concurrent.TimeUnit;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes.dex */
public final class AFf1dSDK {
    public final boolean AFKeystoreWrapper(String str) {
        return AFInAppEventParameterName(this, str);
    }

    private static /* synthetic */ boolean AFInAppEventParameterName(AFf1dSDK aFf1dSDK, String str) {
        return valueOf(str, TimeUnit.HOURS, 1L);
    }

    private static boolean valueOf(String str, TimeUnit timeUnit, long j) {
        Long longOrNull;
        Object m1882constructorimpl;
        Intrinsics.checkNotNullParameter(timeUnit, "");
        if (str != null && (longOrNull = StringsKt.toLongOrNull(str)) != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                m1882constructorimpl = Result.m1882constructorimpl(Boolean.valueOf(Math.abs(longOrNull.longValue() - TimeUnit.MILLISECONDS.toSeconds(AFb1tSDK.valueOf().values().unregisterClient().values())) < timeUnit.toSeconds(1L)));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
            }
            Throwable m1885exceptionOrNullimpl = Result.m1885exceptionOrNullimpl(m1882constructorimpl);
            if (m1885exceptionOrNullimpl != null) {
                StringBuilder sb = new StringBuilder("Could not convert ");
                sb.append(str);
                sb.append(" to TS");
                AFLogger.afErrorLog(sb.toString(), m1885exceptionOrNullimpl);
            }
            if (Result.m1888isFailureimpl(m1882constructorimpl)) {
                m1882constructorimpl = null;
            }
            Boolean bool = (Boolean) m1882constructorimpl;
            if (bool != null) {
                return bool.booleanValue();
            }
        }
        return false;
    }
}
