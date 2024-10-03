package com.linecorp.linesdk.internal;

import com.linecorp.android.security.SecurityUtils;
import com.linecorp.linesdk.LineIdToken;
import com.linecorp.linesdk.Scope;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class IssueAccessTokenResult {
    private final InternalAccessToken accessToken;
    private final LineIdToken idToken;
    private final List<Scope> scopes;

    public IssueAccessTokenResult(InternalAccessToken internalAccessToken, List<Scope> list, LineIdToken lineIdToken) {
        this.accessToken = internalAccessToken;
        this.scopes = Collections.unmodifiableList(list);
        this.idToken = lineIdToken;
    }

    public InternalAccessToken getAccessToken() {
        return this.accessToken;
    }

    public List<Scope> getScopes() {
        return this.scopes;
    }

    public LineIdToken getIdToken() {
        return this.idToken;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IssueAccessTokenResult issueAccessTokenResult = (IssueAccessTokenResult) obj;
        if (!this.accessToken.equals(issueAccessTokenResult.accessToken) || !this.scopes.equals(issueAccessTokenResult.scopes)) {
            return false;
        }
        LineIdToken lineIdToken = this.idToken;
        LineIdToken lineIdToken2 = issueAccessTokenResult.idToken;
        return lineIdToken != null ? lineIdToken.equals(lineIdToken2) : lineIdToken2 == null;
    }

    public int hashCode() {
        int hashCode = ((this.accessToken.hashCode() * 31) + this.scopes.hashCode()) * 31;
        LineIdToken lineIdToken = this.idToken;
        return hashCode + (lineIdToken != null ? lineIdToken.hashCode() : 0);
    }

    public String toString() {
        return "IssueAccessTokenResult{accessToken=" + SecurityUtils.hideIfNotDebug(this.accessToken) + ", scopes=" + this.scopes + ", idToken=" + this.idToken + '}';
    }
}
