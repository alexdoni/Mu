package com.oversea.ab_firstarea.utils.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class ParameterizedTypeImpl implements ParameterizedType {
    private final Type[] args;
    private final Class raw;

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return null;
    }

    public ParameterizedTypeImpl(Class cls, Type[] typeArr) {
        this.raw = cls;
        this.args = typeArr == null ? new Type[0] : typeArr;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return this.args;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return this.raw;
    }
}
