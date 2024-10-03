package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.io.Serializer;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Classes;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.UByte$$ExternalSyntheticBackport0;

/* loaded from: classes3.dex */
public class RuntimeClasspathSerializerLocator implements InstanceLocator<Serializer> {
    private static final AtomicReference<Serializer<Object>> SERIALIZER = new AtomicReference<>();

    @Override // io.jsonwebtoken.impl.io.InstanceLocator
    /* renamed from: getInstance, reason: merged with bridge method [inline-methods] */
    public Serializer getInstance2() {
        AtomicReference<Serializer<Object>> atomicReference = SERIALIZER;
        Serializer<Object> serializer = atomicReference.get();
        if (serializer == null) {
            serializer = locate();
            Assert.state(serializer != null, "locate() cannot return null.");
            if (!compareAndSet(serializer)) {
                serializer = atomicReference.get();
            }
        }
        Assert.state(serializer != null, "serializer cannot be null.");
        return serializer;
    }

    protected Serializer<Object> locate() {
        if (isAvailable("com.fasterxml.jackson.databind.ObjectMapper")) {
            return (Serializer) Classes.newInstance("io.jsonwebtoken.io.JacksonSerializer");
        }
        if (isAvailable("org.json.JSONObject")) {
            return (Serializer) Classes.newInstance("io.jsonwebtoken.io.OrgJsonSerializer");
        }
        throw new IllegalStateException("Unable to discover any JSON Serializer implementations on the classpath.");
    }

    protected boolean compareAndSet(Serializer<Object> serializer) {
        return UByte$$ExternalSyntheticBackport0.m1410m(SERIALIZER, null, serializer);
    }

    protected boolean isAvailable(String str) {
        return Classes.isAvailable(str);
    }
}
