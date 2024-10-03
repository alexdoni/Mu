package com.appsflyer.internal;

import com.appsflyer.deeplink.DeepLink;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tH×\u0001¢\u0006\u0004\b\n\u0010\u000bR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\fX\u0007¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u00038\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013"}, m1395d2 = {"Lcom/appsflyer/internal/AFc1qSDK;", "", "p0", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lcom/appsflyer/deeplink/DeepLink;", "AFInAppEventType", "Lcom/appsflyer/deeplink/DeepLink;", "values", "valueOf", "Z", "AFKeystoreWrapper", "()Z", "p1", "<init>", "(ZLcom/appsflyer/deeplink/DeepLink;)V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final /* data */ class AFc1qSDK {

    /* renamed from: AFInAppEventType, reason: from kotlin metadata */
    public final DeepLink values;

    /* renamed from: valueOf, reason: from kotlin metadata */
    private final boolean AFKeystoreWrapper;

    public AFc1qSDK() {
        this(false, null, 3, 0 == true ? 1 : 0);
    }

    public final boolean equals(Object p0) {
        if (this == p0) {
            return true;
        }
        if (!(p0 instanceof AFc1qSDK)) {
            return false;
        }
        AFc1qSDK aFc1qSDK = (AFc1qSDK) p0;
        return this.AFKeystoreWrapper == aFc1qSDK.AFKeystoreWrapper && Intrinsics.areEqual(this.values, aFc1qSDK.values);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public final int hashCode() {
        boolean z = this.AFKeystoreWrapper;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        DeepLink deepLink = this.values;
        return i + (deepLink == null ? 0 : deepLink.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AFc1qSDK(AFKeystoreWrapper=");
        sb.append(this.AFKeystoreWrapper);
        sb.append(", values=");
        sb.append(this.values);
        sb.append(')');
        return sb.toString();
    }

    public AFc1qSDK(boolean z, DeepLink deepLink) {
        this.AFKeystoreWrapper = z;
        this.values = deepLink;
    }

    public /* synthetic */ AFc1qSDK(boolean z, DeepLink deepLink, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? null : deepLink);
    }

    /* renamed from: AFKeystoreWrapper, reason: from getter */
    public final boolean getAFKeystoreWrapper() {
        return this.AFKeystoreWrapper;
    }
}
