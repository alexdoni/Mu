package com.appsflyer.internal;

import android.text.TextUtils;
import com.appsflyer.AppsFlyerLib;
import com.google.common.net.HttpHeaders;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \r2\u00020\u0001:\u0001\rBE\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\f"}, m1395d2 = {"Lcom/appsflyer/internal/AFe1qSDK;", "Lcom/appsflyer/internal/AFe1oSDK;", "", "p0", "", "p1", "", "p2", "p3", "", "p4", "<init>", "(Ljava/lang/String;Ljava/util/Map;[BLjava/lang/String;Z)V", "AFa1vSDK"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AFe1qSDK extends AFe1oSDK {

    /* renamed from: AFa1vSDK, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* synthetic */ AFe1qSDK(String str, Map map, byte[] bArr, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, map, (i & 4) != 0 ? null : bArr, (i & 8) != 0 ? "GET" : str2, (i & 16) != 0 ? false : z);
    }

    private AFe1qSDK(String str, Map<String, String> map, byte[] bArr, String str2, boolean z) {
        super(str, bArr, str2, map, z);
    }

    @Metadata(m1394d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ=\u0010\b\u001a\u0006*\u00020\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\b\u0010\tJ)\u0010\n\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\n\u0010\u000b"}, m1395d2 = {"Lcom/appsflyer/internal/AFe1qSDK$AFa1vSDK;", "", "", "p0", "p1", "p2", "p3", "p4", "valueOf", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "values", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "<init>", "()V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* renamed from: com.appsflyer.internal.AFe1qSDK$AFa1vSDK, reason: from kotlin metadata */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        static String values(String p0, String p1, String p2) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format(AFe1wSDK.values, AppsFlyerLib.getInstance().getHostPrefix(), AFb1tSDK.valueOf().getHostName()));
            sb.append(p0);
            sb.append(p2);
            sb.append("?device_id=");
            sb.append(p1);
            return sb.toString();
        }

        public static String valueOf(String p0, String p1, String p2, String p3, String p4) {
            Intrinsics.checkNotNullParameter(p0, "");
            Intrinsics.checkNotNullParameter(p1, "");
            Intrinsics.checkNotNullParameter(p3, "");
            Intrinsics.checkNotNullParameter(p4, "");
            StringBuilder sb = new StringBuilder();
            sb.append(p0);
            sb.append(p1);
            return AFb1mSDK.AFInAppEventType(TextUtils.join("\u2063", new String[]{p4, p2, sb.toString()}), p3);
        }
    }

    @JvmStatic
    public static final AFe1qSDK valueOf(String str, String str2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        String values = Companion.values(str, str2, str3);
        String valueOf = String.valueOf(System.currentTimeMillis());
        AFe1qSDK aFe1qSDK = new AFe1qSDK(values, MapsKt.mapOf(TuplesKt.m1402to(HttpHeaders.CONNECTION, "close"), TuplesKt.m1402to("af_request_epoch_ms", valueOf), TuplesKt.m1402to("af_sig", Companion.valueOf(str, str3, str2, str4, valueOf))), null, null, false, 28, null);
        aFe1qSDK.unregisterClient = 10000;
        return aFe1qSDK;
    }
}
