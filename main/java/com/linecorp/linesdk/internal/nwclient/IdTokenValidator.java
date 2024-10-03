package com.linecorp.linesdk.internal.nwclient;

import com.linecorp.linesdk.LineIdToken;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class IdTokenValidator {
    private static final long ALLOWED_CLOCK_SKEW_MILLISECONDS = TimeUnit.MINUTES.toMillis(5);
    private final String expectedChannelId;
    private final String expectedIssuer;
    private final String expectedNonce;
    private final String expectedUserId;
    private final LineIdToken idToken;

    private IdTokenValidator(Builder builder) {
        this.idToken = builder.idToken;
        this.expectedIssuer = builder.expectedIssuer;
        this.expectedUserId = builder.expectedUserId;
        this.expectedChannelId = builder.expectedChannelId;
        this.expectedNonce = builder.expectedNonce;
    }

    private static void notMatchedError(String str, Object obj, Object obj2) {
        throw new RuntimeException(str + " expected: " + obj + ", but received: " + obj2);
    }

    public void validate() {
        validateIssuer();
        validateSubject();
        validateAudience();
        validateNonce();
        validateTimestamp();
    }

    private void validateIssuer() {
        String issuer = this.idToken.getIssuer();
        if (this.expectedIssuer.equals(issuer)) {
            return;
        }
        notMatchedError("OpenId issuer does not match.", this.expectedIssuer, issuer);
    }

    private void validateSubject() {
        String subject = this.idToken.getSubject();
        String str = this.expectedUserId;
        if (str == null || str.equals(subject)) {
            return;
        }
        notMatchedError("OpenId subject does not match.", this.expectedUserId, subject);
    }

    private void validateAudience() {
        String audience = this.idToken.getAudience();
        if (this.expectedChannelId.equals(audience)) {
            return;
        }
        notMatchedError("OpenId audience does not match.", this.expectedChannelId, audience);
    }

    private void validateNonce() {
        String nonce = this.idToken.getNonce();
        String str = this.expectedNonce;
        if (str == null && nonce == null) {
            return;
        }
        if (str == null || !str.equals(nonce)) {
            notMatchedError("OpenId nonce does not match.", this.expectedNonce, nonce);
        }
    }

    private void validateTimestamp() {
        Date date = new Date();
        long time = this.idToken.getIssuedAt().getTime();
        long time2 = date.getTime();
        long j = ALLOWED_CLOCK_SKEW_MILLISECONDS;
        if (time > time2 + j) {
            throw new RuntimeException("OpenId issuedAt is after current time: " + this.idToken.getIssuedAt());
        }
        if (this.idToken.getExpiresAt().getTime() >= date.getTime() - j) {
            return;
        }
        throw new RuntimeException("OpenId expiresAt is before current time: " + this.idToken.getExpiresAt());
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private String expectedChannelId;
        private String expectedIssuer;
        private String expectedNonce;
        private String expectedUserId;
        private LineIdToken idToken;

        public Builder idToken(LineIdToken lineIdToken) {
            this.idToken = lineIdToken;
            return this;
        }

        public Builder expectedIssuer(String str) {
            this.expectedIssuer = str;
            return this;
        }

        public Builder expectedUserId(String str) {
            this.expectedUserId = str;
            return this;
        }

        public Builder expectedChannelId(String str) {
            this.expectedChannelId = str;
            return this;
        }

        public Builder expectedNonce(String str) {
            this.expectedNonce = str;
            return this;
        }

        public IdTokenValidator build() {
            return new IdTokenValidator(this);
        }
    }
}
