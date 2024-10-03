package com.appsflyer.internal;

import com.appsflyer.AppsFlyerLib;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B/\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0003\u001a\u00020\u0002*\u0004\u0018\u00010\u0002H\u0017¢\u0006\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0006\u001a\u00020\u00058\u0017X\u0097\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0003\u0010\bR\u0014\u0010\n\u001a\u00020\u00028WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\t"}, m1395d2 = {"Lcom/appsflyer/internal/AFd1cSDK;", "Lcom/appsflyer/internal/AFd1oSDK;", "", "AFInAppEventParameterName", "(Ljava/lang/String;)Ljava/lang/String;", "Lcom/appsflyer/internal/AFe1rSDK;", "AFKeystoreWrapper", "Lcom/appsflyer/internal/AFe1rSDK;", "()Lcom/appsflyer/internal/AFe1rSDK;", "()Ljava/lang/String;", "AFInAppEventType", "", "p0", "", "p1", "", "p2", "<init>", "([BLjava/util/Map;I)V", "AFa1tSDK"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AFd1cSDK extends AFd1oSDK {
    public static String valueOf = "https://%smonitorsdk.%s/remote-debug/exception-manager";
    private final AFe1rSDK AFKeystoreWrapper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AFd1cSDK(byte[] bArr, Map<String, String> map, int i) {
        super(bArr, map, i);
        Intrinsics.checkNotNullParameter(bArr, "");
        this.AFKeystoreWrapper = AFe1rSDK.JSON;
    }

    @Override // com.appsflyer.internal.AFd1oSDK
    public final String AFKeystoreWrapper() {
        String format = String.format(valueOf, AppsFlyerLib.getInstance().getHostPrefix(), AFb1tSDK.valueOf().getHostName());
        Intrinsics.checkNotNullExpressionValue(format, "");
        return format;
    }

    @Override // com.appsflyer.internal.AFd1oSDK
    /* renamed from: AFInAppEventParameterName, reason: from getter */
    public final AFe1rSDK getAFKeystoreWrapper() {
        return this.AFKeystoreWrapper;
    }

    @Override // com.appsflyer.internal.AFd1oSDK
    public final String AFInAppEventParameterName(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str, "");
        return "[Exception Manager]: ".concat(String.valueOf(str));
    }
}
