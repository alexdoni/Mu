package com.appsflyer.internal;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010%\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0004\b!\u0010\"J\u001a\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tH×\u0001¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0087\u0002¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\f\u001a\u0004\u0018\u00010\tX\u0087\u0002¢\u0006\u0006\n\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0087\u0002¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0087\u0002¢\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0087\u0002¢\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0087\u0002¢\u0006\u0006\n\u0004\b\u000e\u0010\u0011R\u001e\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0015X\u0087\u0002¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u0003X\u0087\u0002¢\u0006\u0006\n\u0004\b\u0018\u0010\u0011"}, m1395d2 = {"Lcom/appsflyer/internal/AFh1xSDK;", "", "p0", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "values", "Ljava/lang/String;", "AFInAppEventType", "AFLogger", "valueOf", "Ljava/lang/Boolean;", "AFKeystoreWrapper", "AFInAppEventParameterName", "unregisterClient", "", "registerClient", "Ljava/util/Map;", "e", "d", "p1", "p2", "p3", "p4", "p5", "p6", "p7", "<init>", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/Map;)V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final /* data */ class AFh1xSDK {

    /* renamed from: AFInAppEventParameterName, reason: from kotlin metadata */
    public Boolean AFKeystoreWrapper;

    /* renamed from: AFInAppEventType, reason: from kotlin metadata */
    public Boolean unregisterClient;

    /* renamed from: AFKeystoreWrapper, reason: from kotlin metadata */
    public Boolean AFInAppEventParameterName;

    /* renamed from: AFLogger, reason: from kotlin metadata */
    public String values;

    /* renamed from: e, reason: from kotlin metadata */
    public Boolean d;

    /* renamed from: registerClient, reason: from kotlin metadata */
    public Map<String, Object> e;
    public Boolean valueOf;

    /* renamed from: values, reason: from kotlin metadata */
    public String AFInAppEventType;

    public AFh1xSDK() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    public final boolean equals(Object p0) {
        if (this == p0) {
            return true;
        }
        if (!(p0 instanceof AFh1xSDK)) {
            return false;
        }
        AFh1xSDK aFh1xSDK = (AFh1xSDK) p0;
        return Intrinsics.areEqual(this.AFInAppEventType, aFh1xSDK.AFInAppEventType) && Intrinsics.areEqual(this.unregisterClient, aFh1xSDK.unregisterClient) && Intrinsics.areEqual(this.valueOf, aFh1xSDK.valueOf) && Intrinsics.areEqual(this.AFInAppEventParameterName, aFh1xSDK.AFInAppEventParameterName) && Intrinsics.areEqual(this.AFKeystoreWrapper, aFh1xSDK.AFKeystoreWrapper) && Intrinsics.areEqual(this.values, aFh1xSDK.values) && Intrinsics.areEqual(this.d, aFh1xSDK.d) && Intrinsics.areEqual(this.e, aFh1xSDK.e);
    }

    public final int hashCode() {
        String str = this.AFInAppEventType;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Boolean bool = this.unregisterClient;
        int hashCode2 = (hashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.valueOf;
        int hashCode3 = (hashCode2 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.AFInAppEventParameterName;
        int hashCode4 = (hashCode3 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Boolean bool4 = this.AFKeystoreWrapper;
        int hashCode5 = (hashCode4 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        String str2 = this.values;
        int hashCode6 = (hashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool5 = this.d;
        return ((hashCode6 + (bool5 != null ? bool5.hashCode() : 0)) * 31) + this.e.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AFh1xSDK(AFInAppEventType=");
        sb.append(this.AFInAppEventType);
        sb.append(", unregisterClient=");
        sb.append(this.unregisterClient);
        sb.append(", valueOf=");
        sb.append(this.valueOf);
        sb.append(", AFInAppEventParameterName=");
        sb.append(this.AFInAppEventParameterName);
        sb.append(", AFKeystoreWrapper=");
        sb.append(this.AFKeystoreWrapper);
        sb.append(", values=");
        sb.append(this.values);
        sb.append(", d=");
        sb.append(this.d);
        sb.append(", e=");
        sb.append(this.e);
        sb.append(')');
        return sb.toString();
    }

    private AFh1xSDK(String str, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, String str2, Boolean bool5, Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "");
        this.AFInAppEventType = str;
        this.unregisterClient = bool;
        this.valueOf = bool2;
        this.AFInAppEventParameterName = bool3;
        this.AFKeystoreWrapper = bool4;
        this.values = str2;
        this.d = bool5;
        this.e = map;
    }

    public /* synthetic */ AFh1xSDK(String str, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, String str2, Boolean bool5, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : bool, (i & 4) != 0 ? null : bool2, (i & 8) != 0 ? null : bool3, (i & 16) != 0 ? null : bool4, (i & 32) != 0 ? null : str2, (i & 64) == 0 ? bool5 : null, (i & 128) != 0 ? new HashMap() : map);
    }
}
