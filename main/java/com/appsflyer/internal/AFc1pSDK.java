package com.appsflyer.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B+\b\u0002\u0012\u0014\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\t\u0010\nR\"\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\f\u001a\u0004\u0018\u00010\u000f8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u0010"}, m1395d2 = {"Lcom/appsflyer/internal/AFc1pSDK;", "", "", "p0", "", "values", "(Ljava/lang/String;)Z", "p1", "", "AFKeystoreWrapper", "(Ljava/lang/String;Ljava/lang/Object;)V", "", "AFInAppEventType", "Ljava/util/Map;", "AFInAppEventParameterName", "Lcom/appsflyer/internal/AFc1oSDK;", "Lcom/appsflyer/internal/AFc1oSDK;", "<init>", "(Ljava/util/Map;Lcom/appsflyer/internal/AFc1oSDK;)V", "AFa1ySDK"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AFc1pSDK {

    /* renamed from: AFa1ySDK, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: AFInAppEventParameterName, reason: from kotlin metadata */
    public final AFc1oSDK AFInAppEventType;

    /* renamed from: AFInAppEventType, reason: from kotlin metadata */
    public final Map<String, Object> AFInAppEventParameterName;

    public /* synthetic */ AFc1pSDK(Map map, AFc1oSDK aFc1oSDK, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, aFc1oSDK);
    }

    @JvmStatic
    public static final AFc1pSDK valueOf(AFc1oSDK aFc1oSDK) {
        return Companion.AFKeystoreWrapper(aFc1oSDK);
    }

    @JvmStatic
    public static final AFc1pSDK values(AFa1pSDK aFa1pSDK) {
        return Companion.AFInAppEventParameterName(aFa1pSDK);
    }

    private AFc1pSDK(Map<String, Object> map, AFc1oSDK aFc1oSDK) {
        this.AFInAppEventParameterName = map;
        this.AFInAppEventType = aFc1oSDK;
    }

    /* synthetic */ AFc1pSDK(Map map, AFc1oSDK aFc1oSDK, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, (i & 2) != 0 ? null : aFc1oSDK);
    }

    @Metadata(m1394d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\b\u0010\t"}, m1395d2 = {"Lcom/appsflyer/internal/AFc1pSDK$AFa1ySDK;", "", "Lcom/appsflyer/internal/AFa1pSDK;", "p0", "Lcom/appsflyer/internal/AFc1pSDK;", "AFInAppEventParameterName", "(Lcom/appsflyer/internal/AFa1pSDK;)Lcom/appsflyer/internal/AFc1pSDK;", "Lcom/appsflyer/internal/AFc1oSDK;", "AFKeystoreWrapper", "(Lcom/appsflyer/internal/AFc1oSDK;)Lcom/appsflyer/internal/AFc1pSDK;", "<init>", "()V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* renamed from: com.appsflyer.internal.AFc1pSDK$AFa1ySDK, reason: from kotlin metadata */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public static AFc1pSDK AFInAppEventParameterName(AFa1pSDK p0) {
            Intrinsics.checkNotNullParameter(p0, "");
            Map<String, Object> valueOf = p0.valueOf();
            Intrinsics.checkNotNullExpressionValue(valueOf, "");
            return new AFc1pSDK(valueOf, null, 2, 0 == true ? 1 : 0);
        }

        @JvmStatic
        public static AFc1pSDK AFKeystoreWrapper(AFc1oSDK p0) {
            Intrinsics.checkNotNullParameter(p0, "");
            return new AFc1pSDK(new LinkedHashMap(), p0, null);
        }
    }

    public final void AFKeystoreWrapper(String p0, Object p1) {
        Intrinsics.checkNotNullParameter(p0, "");
        this.AFInAppEventParameterName.put(p0, p1);
        AFc1oSDK aFc1oSDK = this.AFInAppEventType;
        if (aFc1oSDK != null) {
            aFc1oSDK.values(this.AFInAppEventParameterName);
        }
    }

    public final boolean values(String p0) {
        Intrinsics.checkNotNullParameter(p0, "");
        return this.AFInAppEventParameterName.containsKey(p0);
    }
}
