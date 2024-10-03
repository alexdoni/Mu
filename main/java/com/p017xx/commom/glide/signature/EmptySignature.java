package com.p017xx.commom.glide.signature;

import com.p017xx.commom.glide.load.Key;
import java.security.MessageDigest;

/* loaded from: classes3.dex */
public final class EmptySignature implements Key {
    private static final EmptySignature EMPTY_KEY = new EmptySignature();

    public String toString() {
        return "EmptySignature";
    }

    @Override // com.p017xx.commom.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    public static EmptySignature obtain() {
        return EMPTY_KEY;
    }

    private EmptySignature() {
    }
}
