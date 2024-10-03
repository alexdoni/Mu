package com.p017xx.commom.glide.load.resource;

import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.util.Preconditions;

/* loaded from: classes3.dex */
public class SimpleResource<T> implements Resource<T> {
    protected final T data;

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public final int getSize() {
        return 1;
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public void recycle() {
    }

    public SimpleResource(T t) {
        this.data = (T) Preconditions.checkNotNull(t);
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public Class<T> getResourceClass() {
        return (Class<T>) this.data.getClass();
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public final T get() {
        return this.data;
    }
}
