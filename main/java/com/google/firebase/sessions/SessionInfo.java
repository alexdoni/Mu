package com.google.firebase.sessions;

import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SessionEvent.kt */
@Metadata(m1394d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003JE\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0006HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006#"}, m1395d2 = {"Lcom/google/firebase/sessions/SessionInfo;", "", "sessionId", "", "firstSessionId", "sessionIndex", "", "eventTimestampUs", "", "dataCollectionStatus", "Lcom/google/firebase/sessions/DataCollectionStatus;", "firebaseInstallationId", "(Ljava/lang/String;Ljava/lang/String;IJLcom/google/firebase/sessions/DataCollectionStatus;Ljava/lang/String;)V", "getDataCollectionStatus", "()Lcom/google/firebase/sessions/DataCollectionStatus;", "getEventTimestampUs", "()J", "getFirebaseInstallationId", "()Ljava/lang/String;", "getFirstSessionId", "getSessionId", "getSessionIndex", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "com.google.firebase-firebase-sessions"}, m1396k = 1, m1397mv = {1, 7, 1}, m1399xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class SessionInfo {
    private final DataCollectionStatus dataCollectionStatus;
    private final long eventTimestampUs;
    private final String firebaseInstallationId;
    private final String firstSessionId;
    private final String sessionId;
    private final int sessionIndex;

    public static /* synthetic */ SessionInfo copy$default(SessionInfo sessionInfo, String str, String str2, int i, long j, DataCollectionStatus dataCollectionStatus, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = sessionInfo.sessionId;
        }
        if ((i2 & 2) != 0) {
            str2 = sessionInfo.firstSessionId;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            i = sessionInfo.sessionIndex;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            j = sessionInfo.eventTimestampUs;
        }
        long j2 = j;
        if ((i2 & 16) != 0) {
            dataCollectionStatus = sessionInfo.dataCollectionStatus;
        }
        DataCollectionStatus dataCollectionStatus2 = dataCollectionStatus;
        if ((i2 & 32) != 0) {
            str3 = sessionInfo.firebaseInstallationId;
        }
        return sessionInfo.copy(str, str4, i3, j2, dataCollectionStatus2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSessionId() {
        return this.sessionId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFirstSessionId() {
        return this.firstSessionId;
    }

    /* renamed from: component3, reason: from getter */
    public final int getSessionIndex() {
        return this.sessionIndex;
    }

    /* renamed from: component4, reason: from getter */
    public final long getEventTimestampUs() {
        return this.eventTimestampUs;
    }

    /* renamed from: component5, reason: from getter */
    public final DataCollectionStatus getDataCollectionStatus() {
        return this.dataCollectionStatus;
    }

    /* renamed from: component6, reason: from getter */
    public final String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    public final SessionInfo copy(String sessionId, String firstSessionId, int sessionIndex, long eventTimestampUs, DataCollectionStatus dataCollectionStatus, String firebaseInstallationId) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(firstSessionId, "firstSessionId");
        Intrinsics.checkNotNullParameter(dataCollectionStatus, "dataCollectionStatus");
        Intrinsics.checkNotNullParameter(firebaseInstallationId, "firebaseInstallationId");
        return new SessionInfo(sessionId, firstSessionId, sessionIndex, eventTimestampUs, dataCollectionStatus, firebaseInstallationId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SessionInfo)) {
            return false;
        }
        SessionInfo sessionInfo = (SessionInfo) other;
        return Intrinsics.areEqual(this.sessionId, sessionInfo.sessionId) && Intrinsics.areEqual(this.firstSessionId, sessionInfo.firstSessionId) && this.sessionIndex == sessionInfo.sessionIndex && this.eventTimestampUs == sessionInfo.eventTimestampUs && Intrinsics.areEqual(this.dataCollectionStatus, sessionInfo.dataCollectionStatus) && Intrinsics.areEqual(this.firebaseInstallationId, sessionInfo.firebaseInstallationId);
    }

    public int hashCode() {
        return (((((((((this.sessionId.hashCode() * 31) + this.firstSessionId.hashCode()) * 31) + this.sessionIndex) * 31) + UByte$$ExternalSyntheticBackport0.m1405m(this.eventTimestampUs)) * 31) + this.dataCollectionStatus.hashCode()) * 31) + this.firebaseInstallationId.hashCode();
    }

    public String toString() {
        return "SessionInfo(sessionId=" + this.sessionId + ", firstSessionId=" + this.firstSessionId + ", sessionIndex=" + this.sessionIndex + ", eventTimestampUs=" + this.eventTimestampUs + ", dataCollectionStatus=" + this.dataCollectionStatus + ", firebaseInstallationId=" + this.firebaseInstallationId + ')';
    }

    public SessionInfo(String sessionId, String firstSessionId, int i, long j, DataCollectionStatus dataCollectionStatus, String firebaseInstallationId) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(firstSessionId, "firstSessionId");
        Intrinsics.checkNotNullParameter(dataCollectionStatus, "dataCollectionStatus");
        Intrinsics.checkNotNullParameter(firebaseInstallationId, "firebaseInstallationId");
        this.sessionId = sessionId;
        this.firstSessionId = firstSessionId;
        this.sessionIndex = i;
        this.eventTimestampUs = j;
        this.dataCollectionStatus = dataCollectionStatus;
        this.firebaseInstallationId = firebaseInstallationId;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final String getFirstSessionId() {
        return this.firstSessionId;
    }

    public final int getSessionIndex() {
        return this.sessionIndex;
    }

    public final long getEventTimestampUs() {
        return this.eventTimestampUs;
    }

    public /* synthetic */ SessionInfo(String str, String str2, int i, long j, DataCollectionStatus dataCollectionStatus, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, j, (i2 & 16) != 0 ? new DataCollectionStatus(null, null, 0.0d, 7, null) : dataCollectionStatus, (i2 & 32) != 0 ? "" : str3);
    }

    public final DataCollectionStatus getDataCollectionStatus() {
        return this.dataCollectionStatus;
    }

    public final String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }
}
