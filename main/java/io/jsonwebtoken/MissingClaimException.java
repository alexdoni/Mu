package io.jsonwebtoken;

/* loaded from: classes3.dex */
public class MissingClaimException extends InvalidClaimException {
    public MissingClaimException(Header header, Claims claims, String str) {
        super(header, claims, str);
    }

    public MissingClaimException(Header header, Claims claims, String str, Throwable th) {
        super(header, claims, str, th);
    }
}
