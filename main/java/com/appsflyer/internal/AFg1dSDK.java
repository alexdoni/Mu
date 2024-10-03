package com.appsflyer.internal;

import android.util.Log;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000  2\u00020\u0001:\u0001 B\u0007¢\u0006\u0004\b\u001e\u0010\u001fJ'\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJG\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0014\u0010\nJ1\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0016\u0010\u0018J'\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0019\u0010\nJ'\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u001a\u0010\nR\u0014\u0010\u001d\u001a\u00020\u00068WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c"}, m1395d2 = {"Lcom/appsflyer/internal/AFg1dSDK;", "Lcom/appsflyer/internal/AFg1hSDK;", "Lcom/appsflyer/internal/AFg1gSDK;", "p0", "", "p1", "", "p2", "", "d", "(Lcom/appsflyer/internal/AFg1gSDK;Ljava/lang/String;Z)V", "", "p3", "p4", "p5", "p6", "e", "(Lcom/appsflyer/internal/AFg1gSDK;Ljava/lang/String;Ljava/lang/Throwable;ZZZZ)V", "force", "(Lcom/appsflyer/internal/AFg1gSDK;Ljava/lang/String;)V", "i", "Lcom/appsflyer/AFLogger$LogLevel;", "AFKeystoreWrapper", "(Lcom/appsflyer/AFLogger$LogLevel;Lcom/appsflyer/internal/AFg1gSDK;Ljava/lang/String;Ljava/lang/Throwable;)V", "(Lcom/appsflyer/AFLogger$LogLevel;)Z", "v", "w", "getShouldExtendMsg", "()Z", "valueOf", "<init>", "()V", "AFa1zSDK"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AFg1dSDK extends AFg1hSDK {

    /* loaded from: classes.dex */
    public /* synthetic */ class AFa1vSDK {
        public static final /* synthetic */ int[] values;

        static {
            int[] iArr = new int[AFLogger.LogLevel.values().length];
            iArr[AFLogger.LogLevel.DEBUG.ordinal()] = 1;
            iArr[AFLogger.LogLevel.INFO.ordinal()] = 2;
            iArr[AFLogger.LogLevel.WARNING.ordinal()] = 3;
            iArr[AFLogger.LogLevel.VERBOSE.ordinal()] = 4;
            iArr[AFLogger.LogLevel.ERROR.ordinal()] = 5;
            iArr[AFLogger.LogLevel.NONE.ordinal()] = 6;
            values = iArr;
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    public final boolean getShouldExtendMsg() {
        return AFLogger.LogLevel.VERBOSE.getCom.google.firebase.analytics.FirebaseAnalytics.Param.LEVEL java.lang.String() <= AppsFlyerProperties.getInstance().getLogLevel();
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: d */
    public final void mo55d(AFg1gSDK p0, String p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        AFKeystoreWrapper(AFLogger.LogLevel.DEBUG, p0, p1, null);
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: e */
    public final void mo56e(AFg1gSDK p0, String p1, Throwable p2, boolean p3, boolean p4, boolean p5, boolean p6) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        Intrinsics.checkNotNullParameter(p2, "");
        if (p4) {
            AFKeystoreWrapper(AFLogger.LogLevel.ERROR, p0, p1, p2);
        } else if (p3) {
            AFKeystoreWrapper(AFLogger.LogLevel.DEBUG, p0, p1, null);
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: i */
    public final void mo57i(AFg1gSDK p0, String p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        AFKeystoreWrapper(AFLogger.LogLevel.INFO, p0, p1, null);
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: w */
    public final void mo59w(AFg1gSDK p0, String p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        AFKeystoreWrapper(AFLogger.LogLevel.WARNING, p0, p1, null);
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: v */
    public final void mo58v(AFg1gSDK p0, String p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        AFKeystoreWrapper(AFLogger.LogLevel.VERBOSE, p0, p1, null);
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    public final void force(AFg1gSDK p0, String p1) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        if (AppsFlyerProperties.getInstance().isLogsDisabledCompletely()) {
            return;
        }
        Log.d("AppsFlyer_6.13.1", withTag$SDK_prodRelease(p1, p0));
    }

    private final void AFKeystoreWrapper(AFLogger.LogLevel p0, AFg1gSDK p1, String p2, Throwable p3) {
        if (AFKeystoreWrapper(p0)) {
            String AFInAppEventParameterName = AFInAppEventParameterName(p2, p1);
            int i = AFa1vSDK.values[p0.ordinal()];
            if (i == 1) {
                Log.d("AppsFlyer_6.13.1", AFInAppEventParameterName);
                return;
            }
            if (i == 2) {
                Log.i("AppsFlyer_6.13.1", AFInAppEventParameterName);
                return;
            }
            if (i == 3) {
                Log.w("AppsFlyer_6.13.1", AFInAppEventParameterName);
            } else if (i == 4) {
                Log.v("AppsFlyer_6.13.1", AFInAppEventParameterName);
            } else {
                if (i != 5) {
                    return;
                }
                Log.e("AppsFlyer_6.13.1", AFInAppEventParameterName, p3);
            }
        }
    }

    private static boolean AFKeystoreWrapper(AFLogger.LogLevel p0) {
        return p0.getCom.google.firebase.analytics.FirebaseAnalytics.Param.LEVEL java.lang.String() <= AppsFlyerProperties.getInstance().getLogLevel();
    }
}
