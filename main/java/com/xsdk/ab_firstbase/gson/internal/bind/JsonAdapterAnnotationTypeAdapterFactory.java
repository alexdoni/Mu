package com.xsdk.ab_firstbase.gson.internal.bind;

import com.xsdk.ab_firstbase.gson.Gson;
import com.xsdk.ab_firstbase.gson.TypeAdapter;
import com.xsdk.ab_firstbase.gson.TypeAdapterFactory;
import com.xsdk.ab_firstbase.gson.annotations.JsonAdapter;
import com.xsdk.ab_firstbase.gson.internal.ConstructorConstructor;
import com.xsdk.ab_firstbase.gson.reflect.TypeToken;

/* loaded from: classes3.dex */
public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor) {
        this.constructorConstructor = constructorConstructor;
    }

    @Override // com.xsdk.ab_firstbase.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        JsonAdapter jsonAdapter = (JsonAdapter) typeToken.getRawType().getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return (TypeAdapter<T>) getTypeAdapter(this.constructorConstructor, gson, typeToken, jsonAdapter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TypeAdapter<?> getTypeAdapter(ConstructorConstructor constructorConstructor, Gson gson, TypeToken<?> typeToken, JsonAdapter jsonAdapter) {
        Class<?> value = jsonAdapter.value();
        if (TypeAdapter.class.isAssignableFrom(value)) {
            return (TypeAdapter) constructorConstructor.get(TypeToken.get((Class) value)).construct();
        }
        if (TypeAdapterFactory.class.isAssignableFrom(value)) {
            return ((TypeAdapterFactory) constructorConstructor.get(TypeToken.get((Class) value)).construct()).create(gson, typeToken);
        }
        throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
    }
}
