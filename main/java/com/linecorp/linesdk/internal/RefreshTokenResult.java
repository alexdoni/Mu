package com.linecorp.linesdk.internal;

import com.linecorp.android.security.SecurityUtils;
import com.linecorp.linesdk.Scope;
import java.util.List;

/* loaded from: classes2.dex */
public class RefreshTokenResult {
    private final String accessToken;
    private final long expiresInMillis;
    private final String refreshToken;
    private final List<Scope> scopes;

    public RefreshTokenResult(String str, long j, String str2, List<Scope> list) {
        this.accessToken = str;
        this.expiresInMillis = j;
        this.refreshToken = str2;
        this.scopes = list;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public long getExpiresInMillis() {
        return this.expiresInMillis;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public List<Scope> getScopes() {
        return this.scopes;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RefreshTokenResult refreshTokenResult = (RefreshTokenResult) obj;
        if (this.expiresInMillis == refreshTokenResult.expiresInMillis && this.accessToken.equals(refreshTokenResult.accessToken) && this.refreshToken.equals(refreshTokenResult.refreshToken)) {
            return this.scopes.equals(refreshTokenResult.scopes);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.accessToken.hashCode() * 31;
        long j = this.expiresInMillis;
        return ((((hashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.refreshToken.hashCode()) * 31) + this.scopes.hashCode();
    }

    public String toString() {
        return "RefreshTokenResult{accessToken='" + SecurityUtils.hideIfNotDebug(this.accessToken) + "', expiresInMillis=" + this.expiresInMillis + ", refreshToken='" + SecurityUtils.hideIfNotDebug(this.refreshToken) + "', scopes=" + this.scopes + '}';
    }
}
