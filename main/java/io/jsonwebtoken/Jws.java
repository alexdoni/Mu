package io.jsonwebtoken;

/* loaded from: classes3.dex */
public interface Jws<B> extends Jwt<JwsHeader, B> {
    String getSignature();
}
