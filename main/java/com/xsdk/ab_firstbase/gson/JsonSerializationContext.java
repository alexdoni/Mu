package com.xsdk.ab_firstbase.gson;

import java.lang.reflect.Type;

/* loaded from: classes3.dex */
public interface JsonSerializationContext {
    JsonElement serialize(Object obj);

    JsonElement serialize(Object obj, Type type);
}
