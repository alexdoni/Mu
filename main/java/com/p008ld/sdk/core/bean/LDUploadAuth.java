package com.p008ld.sdk.core.bean;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDLoginResult.kt */
/* loaded from: classes2.dex */
public final class LDUploadAuth {
    private final String bucketName;
    private final String cdnHost;
    private final String dir;
    private final String endpoint;
    private final String expireAt;
    private final String stsAccessKeyId;
    private final String stsAccessKeySecret;
    private final String stsSecurityToken;

    public final String component1() {
        return this.stsAccessKeyId;
    }

    public final String component2() {
        return this.stsAccessKeySecret;
    }

    public final String component3() {
        return this.stsSecurityToken;
    }

    public final String component4() {
        return this.expireAt;
    }

    public final String component5() {
        return this.endpoint;
    }

    public final String component6() {
        return this.bucketName;
    }

    public final String component7() {
        return this.dir;
    }

    public final String component8() {
        return this.cdnHost;
    }

    public final LDUploadAuth copy(String stsAccessKeyId, String stsAccessKeySecret, String stsSecurityToken, String expireAt, String endpoint, String bucketName, String dir, String cdnHost) {
        Intrinsics.checkNotNullParameter(stsAccessKeyId, "stsAccessKeyId");
        Intrinsics.checkNotNullParameter(stsAccessKeySecret, "stsAccessKeySecret");
        Intrinsics.checkNotNullParameter(stsSecurityToken, "stsSecurityToken");
        Intrinsics.checkNotNullParameter(expireAt, "expireAt");
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(bucketName, "bucketName");
        Intrinsics.checkNotNullParameter(dir, "dir");
        Intrinsics.checkNotNullParameter(cdnHost, "cdnHost");
        return new LDUploadAuth(stsAccessKeyId, stsAccessKeySecret, stsSecurityToken, expireAt, endpoint, bucketName, dir, cdnHost);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDUploadAuth)) {
            return false;
        }
        LDUploadAuth lDUploadAuth = (LDUploadAuth) obj;
        return Intrinsics.areEqual(this.stsAccessKeyId, lDUploadAuth.stsAccessKeyId) && Intrinsics.areEqual(this.stsAccessKeySecret, lDUploadAuth.stsAccessKeySecret) && Intrinsics.areEqual(this.stsSecurityToken, lDUploadAuth.stsSecurityToken) && Intrinsics.areEqual(this.expireAt, lDUploadAuth.expireAt) && Intrinsics.areEqual(this.endpoint, lDUploadAuth.endpoint) && Intrinsics.areEqual(this.bucketName, lDUploadAuth.bucketName) && Intrinsics.areEqual(this.dir, lDUploadAuth.dir) && Intrinsics.areEqual(this.cdnHost, lDUploadAuth.cdnHost);
    }

    public int hashCode() {
        return (((((((((((((this.stsAccessKeyId.hashCode() * 31) + this.stsAccessKeySecret.hashCode()) * 31) + this.stsSecurityToken.hashCode()) * 31) + this.expireAt.hashCode()) * 31) + this.endpoint.hashCode()) * 31) + this.bucketName.hashCode()) * 31) + this.dir.hashCode()) * 31) + this.cdnHost.hashCode();
    }

    public String toString() {
        return "LDUploadAuth(stsAccessKeyId=" + this.stsAccessKeyId + ", stsAccessKeySecret=" + this.stsAccessKeySecret + ", stsSecurityToken=" + this.stsSecurityToken + ", expireAt=" + this.expireAt + ", endpoint=" + this.endpoint + ", bucketName=" + this.bucketName + ", dir=" + this.dir + ", cdnHost=" + this.cdnHost + ')';
    }

    public LDUploadAuth(String stsAccessKeyId, String stsAccessKeySecret, String stsSecurityToken, String expireAt, String endpoint, String bucketName, String dir, String cdnHost) {
        Intrinsics.checkNotNullParameter(stsAccessKeyId, "stsAccessKeyId");
        Intrinsics.checkNotNullParameter(stsAccessKeySecret, "stsAccessKeySecret");
        Intrinsics.checkNotNullParameter(stsSecurityToken, "stsSecurityToken");
        Intrinsics.checkNotNullParameter(expireAt, "expireAt");
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(bucketName, "bucketName");
        Intrinsics.checkNotNullParameter(dir, "dir");
        Intrinsics.checkNotNullParameter(cdnHost, "cdnHost");
        this.stsAccessKeyId = stsAccessKeyId;
        this.stsAccessKeySecret = stsAccessKeySecret;
        this.stsSecurityToken = stsSecurityToken;
        this.expireAt = expireAt;
        this.endpoint = endpoint;
        this.bucketName = bucketName;
        this.dir = dir;
        this.cdnHost = cdnHost;
    }

    public final String getStsAccessKeyId() {
        return this.stsAccessKeyId;
    }

    public final String getStsAccessKeySecret() {
        return this.stsAccessKeySecret;
    }

    public final String getStsSecurityToken() {
        return this.stsSecurityToken;
    }

    public final String getExpireAt() {
        return this.expireAt;
    }

    public final String getEndpoint() {
        return this.endpoint;
    }

    public final String getBucketName() {
        return this.bucketName;
    }

    public final String getDir() {
        return this.dir;
    }

    public final String getCdnHost() {
        return this.cdnHost;
    }
}
