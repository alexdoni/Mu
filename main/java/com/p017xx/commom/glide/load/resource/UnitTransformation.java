package com.p017xx.commom.glide.load.resource;

import android.content.Context;
import com.p017xx.commom.glide.load.Transformation;
import com.p017xx.commom.glide.load.engine.Resource;
import java.security.MessageDigest;

/* loaded from: classes3.dex */
public final class UnitTransformation<T> implements Transformation<T> {
    private static final Transformation<?> TRANSFORMATION = new UnitTransformation();

    @Override // com.p017xx.commom.glide.load.Transformation
    public Resource<T> transform(Context context, Resource<T> resource, int i, int i2) {
        return resource;
    }

    @Override // com.p017xx.commom.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    public static <T> UnitTransformation<T> get() {
        return (UnitTransformation) TRANSFORMATION;
    }

    private UnitTransformation() {
    }
}
