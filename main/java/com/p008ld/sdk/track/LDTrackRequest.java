package com.p008ld.sdk.track;

import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDTrackRequest.kt */
/* loaded from: classes2.dex */
public final class LDTrackRequest {
    private final String appId;
    private final String eventId;
    private final String eventKey;
    private final LDTrackProperties properties;
    private final long timestamp;

    public static /* synthetic */ LDTrackRequest copy$default(LDTrackRequest lDTrackRequest, String str, String str2, String str3, LDTrackProperties lDTrackProperties, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lDTrackRequest.appId;
        }
        if ((i & 2) != 0) {
            str2 = lDTrackRequest.eventId;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = lDTrackRequest.eventKey;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            lDTrackProperties = lDTrackRequest.properties;
        }
        LDTrackProperties lDTrackProperties2 = lDTrackProperties;
        if ((i & 16) != 0) {
            j = lDTrackRequest.timestamp;
        }
        return lDTrackRequest.copy(str, str4, str5, lDTrackProperties2, j);
    }

    public final String component1() {
        return this.appId;
    }

    public final String component2() {
        return this.eventId;
    }

    public final String component3() {
        return this.eventKey;
    }

    public final LDTrackProperties component4() {
        return this.properties;
    }

    public final long component5() {
        return this.timestamp;
    }

    public final LDTrackRequest copy(String appId, String eventId, String eventKey, LDTrackProperties properties, long j) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        Intrinsics.checkNotNullParameter(eventKey, "eventKey");
        Intrinsics.checkNotNullParameter(properties, "properties");
        return new LDTrackRequest(appId, eventId, eventKey, properties, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDTrackRequest)) {
            return false;
        }
        LDTrackRequest lDTrackRequest = (LDTrackRequest) obj;
        return Intrinsics.areEqual(this.appId, lDTrackRequest.appId) && Intrinsics.areEqual(this.eventId, lDTrackRequest.eventId) && Intrinsics.areEqual(this.eventKey, lDTrackRequest.eventKey) && Intrinsics.areEqual(this.properties, lDTrackRequest.properties) && this.timestamp == lDTrackRequest.timestamp;
    }

    public int hashCode() {
        return (((((((this.appId.hashCode() * 31) + this.eventId.hashCode()) * 31) + this.eventKey.hashCode()) * 31) + this.properties.hashCode()) * 31) + UByte$$ExternalSyntheticBackport0.m1405m(this.timestamp);
    }

    public String toString() {
        return "LDTrackRequest(appId=" + this.appId + ", eventId=" + this.eventId + ", eventKey=" + this.eventKey + ", properties=" + this.properties + ", timestamp=" + this.timestamp + ')';
    }

    public LDTrackRequest(String appId, String eventId, String eventKey, LDTrackProperties properties, long j) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        Intrinsics.checkNotNullParameter(eventKey, "eventKey");
        Intrinsics.checkNotNullParameter(properties, "properties");
        this.appId = appId;
        this.eventId = eventId;
        this.eventKey = eventKey;
        this.properties = properties;
        this.timestamp = j;
    }

    public final String getAppId() {
        return this.appId;
    }

    public final String getEventId() {
        return this.eventId;
    }

    public final String getEventKey() {
        return this.eventKey;
    }

    public final LDTrackProperties getProperties() {
        return this.properties;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
