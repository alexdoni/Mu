package com.linecorp.linesdk.internal;

import com.linecorp.linesdk.Scope;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class AccessTokenVerificationResult {
    private final String channelId;
    private final long expiresInMillis;
    private final List<Scope> scopes;

    public AccessTokenVerificationResult(String str, long j, List<Scope> list) {
        this.channelId = str;
        this.expiresInMillis = j;
        this.scopes = Collections.unmodifiableList(list);
    }

    public String getChannelId() {
        return this.channelId;
    }

    public long getExpiresInMillis() {
        return this.expiresInMillis;
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
        AccessTokenVerificationResult accessTokenVerificationResult = (AccessTokenVerificationResult) obj;
        if (this.expiresInMillis == accessTokenVerificationResult.expiresInMillis && this.channelId.equals(accessTokenVerificationResult.channelId)) {
            return this.scopes.equals(accessTokenVerificationResult.scopes);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.channelId.hashCode() * 31;
        long j = this.expiresInMillis;
        return ((hashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.scopes.hashCode();
    }

    public String toString() {
        return "AccessTokenVerificationResult{channelId='" + this.channelId + "', expiresInMillis=" + this.expiresInMillis + ", scopes=" + this.scopes + '}';
    }
}
