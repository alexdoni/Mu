package com.linecorp.linesdk.internal;

import com.linecorp.android.security.SecurityUtils;

/* loaded from: classes2.dex */
public class InternalAccessToken {
    private final String accessToken;
    private final long expiresInMillis;
    private final long issuedClientTimeMillis;
    private final String refreshToken;

    public InternalAccessToken(String str, long j, long j2, String str2) {
        this.accessToken = str;
        this.expiresInMillis = j;
        this.issuedClientTimeMillis = j2;
        this.refreshToken = str2;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public long getExpiresInMillis() {
        return this.expiresInMillis;
    }

    public long getIssuedClientTimeMillis() {
        return this.issuedClientTimeMillis;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InternalAccessToken internalAccessToken = (InternalAccessToken) obj;
        if (this.expiresInMillis == internalAccessToken.expiresInMillis && this.issuedClientTimeMillis == internalAccessToken.issuedClientTimeMillis && this.accessToken.equals(internalAccessToken.accessToken)) {
            return this.refreshToken.equals(internalAccessToken.refreshToken);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.accessToken.hashCode() * 31;
        long j = this.expiresInMillis;
        int i = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.issuedClientTimeMillis;
        return ((i + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.refreshToken.hashCode();
    }

    public String toString() {
        return "InternalAccessToken{accessToken='" + SecurityUtils.hideIfNotDebug(this.accessToken) + "', expiresInMillis=" + this.expiresInMillis + ", issuedClientTimeMillis=" + this.issuedClientTimeMillis + ", refreshToken='" + SecurityUtils.hideIfNotDebug(this.refreshToken) + "'}";
    }
}
