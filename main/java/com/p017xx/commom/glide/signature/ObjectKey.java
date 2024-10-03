package com.p017xx.commom.glide.signature;

import com.p017xx.commom.glide.load.Key;
import com.p017xx.commom.glide.util.Preconditions;
import java.security.MessageDigest;

/* loaded from: classes3.dex */
public final class ObjectKey implements Key {
    private final Object object;

    public ObjectKey(Object obj) {
        this.object = Preconditions.checkNotNull(obj);
    }

    public String toString() {
        return "ObjectKey{object=" + this.object + '}';
    }

    @Override // com.p017xx.commom.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof ObjectKey) {
            return this.object.equals(((ObjectKey) obj).object);
        }
        return false;
    }

    @Override // com.p017xx.commom.glide.load.Key
    public int hashCode() {
        return this.object.hashCode();
    }

    @Override // com.p017xx.commom.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(this.object.toString().getBytes(CHARSET));
    }
}
