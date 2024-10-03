package com.xsdk.ab_firstbase.gson;

/* loaded from: classes3.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.xsdk.ab_firstbase.gson.LongSerializationPolicy.1
        @Override // com.xsdk.ab_firstbase.gson.LongSerializationPolicy
        public JsonElement serialize(Long l) {
            return new JsonPrimitive((Number) l);
        }
    },
    STRING { // from class: com.xsdk.ab_firstbase.gson.LongSerializationPolicy.2
        @Override // com.xsdk.ab_firstbase.gson.LongSerializationPolicy
        public JsonElement serialize(Long l) {
            return new JsonPrimitive(String.valueOf(l));
        }
    };

    public abstract JsonElement serialize(Long l);
}
