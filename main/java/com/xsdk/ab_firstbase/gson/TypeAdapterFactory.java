package com.xsdk.ab_firstbase.gson;

import com.xsdk.ab_firstbase.gson.reflect.TypeToken;

/* loaded from: classes3.dex */
public interface TypeAdapterFactory {
    <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken);
}
