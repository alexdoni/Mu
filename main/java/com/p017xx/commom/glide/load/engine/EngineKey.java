package com.p017xx.commom.glide.load.engine;

import com.p017xx.commom.glide.load.Key;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.Transformation;
import com.p017xx.commom.glide.util.Preconditions;
import java.security.MessageDigest;
import java.util.Map;

/* loaded from: classes3.dex */
class EngineKey implements Key {
    private int hashCode;
    private final int height;
    private final Object model;
    private final Options options;
    private final Class<?> resourceClass;
    private final Key signature;
    private final Class<?> transcodeClass;
    private final Map<Class<?>, Transformation<?>> transformations;
    private final int width;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EngineKey(Object obj, Key key, int i, int i2, Map<Class<?>, Transformation<?>> map, Class<?> cls, Class<?> cls2, Options options) {
        this.model = Preconditions.checkNotNull(obj);
        this.signature = (Key) Preconditions.checkNotNull(key, "Signature must not be null");
        this.width = i;
        this.height = i2;
        this.transformations = (Map) Preconditions.checkNotNull(map);
        this.resourceClass = (Class) Preconditions.checkNotNull(cls, "Resource class must not be null");
        this.transcodeClass = (Class) Preconditions.checkNotNull(cls2, "Transcode class must not be null");
        this.options = (Options) Preconditions.checkNotNull(options);
    }

    @Override // com.p017xx.commom.glide.load.Key
    public boolean equals(Object obj) {
        if (!(obj instanceof EngineKey)) {
            return false;
        }
        EngineKey engineKey = (EngineKey) obj;
        return this.model.equals(engineKey.model) && this.signature.equals(engineKey.signature) && this.height == engineKey.height && this.width == engineKey.width && this.transformations.equals(engineKey.transformations) && this.resourceClass.equals(engineKey.resourceClass) && this.transcodeClass.equals(engineKey.transcodeClass) && this.options.equals(engineKey.options);
    }

    @Override // com.p017xx.commom.glide.load.Key
    public int hashCode() {
        if (this.hashCode == 0) {
            int hashCode = this.model.hashCode();
            this.hashCode = hashCode;
            int hashCode2 = (((((hashCode * 31) + this.signature.hashCode()) * 31) + this.width) * 31) + this.height;
            this.hashCode = hashCode2;
            int hashCode3 = (hashCode2 * 31) + this.transformations.hashCode();
            this.hashCode = hashCode3;
            int hashCode4 = (hashCode3 * 31) + this.resourceClass.hashCode();
            this.hashCode = hashCode4;
            int hashCode5 = (hashCode4 * 31) + this.transcodeClass.hashCode();
            this.hashCode = hashCode5;
            this.hashCode = (hashCode5 * 31) + this.options.hashCode();
        }
        return this.hashCode;
    }

    public String toString() {
        return "EngineKey{model=" + this.model + ", width=" + this.width + ", height=" + this.height + ", resourceClass=" + this.resourceClass + ", transcodeClass=" + this.transcodeClass + ", signature=" + this.signature + ", hashCode=" + this.hashCode + ", transformations=" + this.transformations + ", options=" + this.options + '}';
    }

    @Override // com.p017xx.commom.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }
}
