package com.appsflyer.internal;

import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B;\b\u0007\u0012\u0006\u0010\u0013\u001a\u00020\n\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0016\b\u0002\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0016\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u0003\u001a\u00020\u0002*\u0004\u0018\u00010\u0002H\u0017¢\u0006\u0004\b\u0003\u0010\u0004R\u001a\u0010\t\u001a\u00020\u00058\u0017X\u0097\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0003\u0010\bR\u0011\u0010\r\u001a\u00020\nX\u0007¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u001a\u0010\u0011\u001a\u00020\u000e8\u0017X\u0097D¢\u0006\f\n\u0004\b\r\u0010\u000f\u001a\u0004\b\r\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0012"}, m1395d2 = {"Lcom/appsflyer/internal/AFe1xSDK;", "Lcom/appsflyer/internal/AFd1oSDK;", "", "AFInAppEventParameterName", "(Ljava/lang/String;)Ljava/lang/String;", "Lcom/appsflyer/internal/AFe1rSDK;", "AFLogger", "Lcom/appsflyer/internal/AFe1rSDK;", "()Lcom/appsflyer/internal/AFe1rSDK;", "values", "Lcom/appsflyer/internal/AFd1sSDK;", "AFKeystoreWrapper", "Lcom/appsflyer/internal/AFd1sSDK;", "valueOf", "", "Z", "()Z", "AFInAppEventType", "()Ljava/lang/String;", "p0", "", "p1", "", "p2", "", "p3", "<init>", "(Lcom/appsflyer/internal/AFd1sSDK;[BLjava/util/Map;I)V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AFe1xSDK extends AFd1oSDK {

    /* renamed from: AFKeystoreWrapper, reason: from kotlin metadata */
    public AFd1sSDK valueOf;

    /* renamed from: AFLogger, reason: from kotlin metadata */
    private final AFe1rSDK values;

    /* renamed from: valueOf, reason: from kotlin metadata */
    private final boolean AFInAppEventType;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AFe1xSDK(AFd1sSDK aFd1sSDK, byte[] bArr) {
        this(aFd1sSDK, bArr, null, 0, 12, null);
        Intrinsics.checkNotNullParameter(aFd1sSDK, "");
        Intrinsics.checkNotNullParameter(bArr, "");
    }

    public /* synthetic */ AFe1xSDK(AFd1sSDK aFd1sSDK, byte[] bArr, Map map, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(aFd1sSDK, bArr, (i2 & 4) != 0 ? null : map, (i2 & 8) != 0 ? CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private AFe1xSDK(AFd1sSDK aFd1sSDK, byte[] bArr, Map<String, String> map, int i) {
        super(bArr, map, i);
        Intrinsics.checkNotNullParameter(aFd1sSDK, "");
        Intrinsics.checkNotNullParameter(bArr, "");
        this.valueOf = aFd1sSDK;
        this.values = AFe1rSDK.OCTET_STREAM;
    }

    @Override // com.appsflyer.internal.AFd1oSDK
    /* renamed from: valueOf, reason: from getter */
    public final boolean getAFInAppEventType() {
        return this.AFInAppEventType;
    }

    @Override // com.appsflyer.internal.AFd1oSDK
    public final String AFKeystoreWrapper() {
        AFi1eSDK aFi1eSDK = new AFi1eSDK(this.valueOf, null, 2, 0 == true ? 1 : 0);
        String AFInAppEventType = aFi1eSDK.AFInAppEventType.AFInAppEventType(AFi1eSDK.registerClient);
        StringBuilder sb = new StringBuilder();
        sb.append(AFInAppEventType);
        sb.append(aFi1eSDK.AFKeystoreWrapper.AFInAppEventParameterName.valueOf.getPackageName());
        return sb.toString();
    }

    @Override // com.appsflyer.internal.AFd1oSDK
    /* renamed from: AFInAppEventParameterName, reason: from getter */
    public final AFe1rSDK getValues() {
        return this.values;
    }

    @Override // com.appsflyer.internal.AFd1oSDK
    public final String AFInAppEventParameterName(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        return "[RD]: ".concat(String.valueOf(str));
    }
}
