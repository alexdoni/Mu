package com.appsflyer.internal;

import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001BC\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0012\u0012\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u0015\u0012\u0006\u0010\u001c\u001a\u00020\u0006\u0012\u0006\u0010\u001d\u001a\u00020\f\u0012\u0006\u0010\u001e\u001a\u00020\f¢\u0006\u0004\b\u001f\u0010 J\u001a\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u000b\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000f\u001a\u00020\fX\u0007¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\u0006X\u0007¢\u0006\u0006\n\u0004\b\t\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0012X\u0007¢\u0006\u0006\n\u0004\b\u0011\u0010\u0013R\u0011\u0010\r\u001a\u00020\fX\u0007¢\u0006\u0006\n\u0004\b\u0014\u0010\u000eR\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0015X\u0007¢\u0006\u0006\n\u0004\b\u000b\u0010\u0016R\u0011\u0010\u0014\u001a\u00020\u0018X\u0007¢\u0006\u0006\n\u0004\b\u000f\u0010\u0019"}, m1395d2 = {"Lcom/appsflyer/internal/AFh1iSDK;", "", "p0", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "AFInAppEventType", "()Z", "AFInAppEventParameterName", "", "AFKeystoreWrapper", "Ljava/lang/String;", "valueOf", "I", "values", "", "F", "AFLogger", "", "Ljava/util/List;", "unregisterClient", "", "J", "p1", "p2", "p3", "p4", "p5", "<init>", "(JFLjava/util/List;ILjava/lang/String;Ljava/lang/String;)V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AFh1iSDK {

    /* renamed from: AFInAppEventParameterName, reason: from kotlin metadata */
    public List<String> unregisterClient;

    /* renamed from: AFInAppEventType, reason: from kotlin metadata */
    public final int values;

    /* renamed from: AFKeystoreWrapper, reason: from kotlin metadata */
    public final String valueOf;

    /* renamed from: AFLogger, reason: from kotlin metadata */
    public final String AFKeystoreWrapper;

    /* renamed from: valueOf, reason: from kotlin metadata */
    public long AFLogger;

    /* renamed from: values, reason: from kotlin metadata */
    public final float AFInAppEventType;

    public AFh1iSDK(long j, float f, List<String> list, int i, String str, String str2) {
        Intrinsics.checkNotNullParameter(list, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        this.AFLogger = j;
        this.AFInAppEventType = f;
        this.unregisterClient = list;
        this.values = i;
        this.valueOf = str;
        this.AFKeystoreWrapper = str2;
    }

    public final boolean AFInAppEventType() {
        return TimeUnit.SECONDS.toMillis(this.AFLogger) > AFb1tSDK.valueOf().values().unregisterClient().values();
    }

    public final boolean equals(Object p0) {
        if (this == p0) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), p0 != null ? p0.getClass() : null)) {
            return false;
        }
        if (p0 != null) {
            AFh1iSDK aFh1iSDK = (AFh1iSDK) p0;
            if (this.AFLogger != aFh1iSDK.AFLogger) {
                return false;
            }
            return ((this.AFInAppEventType > aFh1iSDK.AFInAppEventType ? 1 : (this.AFInAppEventType == aFh1iSDK.AFInAppEventType ? 0 : -1)) == 0) && Intrinsics.areEqual(this.unregisterClient, aFh1iSDK.unregisterClient) && this.values == aFh1iSDK.values && Intrinsics.areEqual(this.valueOf, aFh1iSDK.valueOf) && Intrinsics.areEqual(this.AFKeystoreWrapper, aFh1iSDK.AFKeystoreWrapper);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.appsflyer.internal.model.rc.RDConfiguration");
    }

    public final int hashCode() {
        return (((((((((UByte$$ExternalSyntheticBackport0.m1405m(this.AFLogger) * 31) + Float.floatToIntBits(this.AFInAppEventType)) * 31) + this.unregisterClient.hashCode()) * 31) + this.values) * 31) + this.valueOf.hashCode()) * 31) + this.AFKeystoreWrapper.hashCode();
    }
}
