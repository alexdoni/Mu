package io.jsonwebtoken.io;

/* loaded from: classes3.dex */
public interface Serializer<T> {
    byte[] serialize(T t) throws SerializationException;
}
