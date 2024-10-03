package com.xsdk.ab_firstbase.gson.internal;

import com.xsdk.ab_firstbase.gson.ExclusionStrategy;
import com.xsdk.ab_firstbase.gson.FieldAttributes;
import com.xsdk.ab_firstbase.gson.Gson;
import com.xsdk.ab_firstbase.gson.TypeAdapter;
import com.xsdk.ab_firstbase.gson.TypeAdapterFactory;
import com.xsdk.ab_firstbase.gson.annotations.Expose;
import com.xsdk.ab_firstbase.gson.annotations.Since;
import com.xsdk.ab_firstbase.gson.annotations.Until;
import com.xsdk.ab_firstbase.gson.reflect.TypeToken;
import com.xsdk.ab_firstbase.gson.stream.JsonReader;
import com.xsdk.ab_firstbase.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes3.dex */
public final class Excluder implements TypeAdapterFactory, Cloneable {
    public static final Excluder DEFAULT = new Excluder();
    private static final double IGNORE_VERSIONS = -1.0d;
    private boolean requireExpose;
    private double version = IGNORE_VERSIONS;
    private int modifiers = CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA;
    private boolean serializeInnerClasses = true;
    private List<ExclusionStrategy> serializationStrategies = Collections.emptyList();
    private List<ExclusionStrategy> deserializationStrategies = Collections.emptyList();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Excluder m1875clone() {
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }

    public Excluder withVersion(double d) {
        Excluder m1875clone = m1875clone();
        m1875clone.version = d;
        return m1875clone;
    }

    public Excluder withModifiers(int... iArr) {
        Excluder m1875clone = m1875clone();
        m1875clone.modifiers = 0;
        for (int i : iArr) {
            m1875clone.modifiers = i | m1875clone.modifiers;
        }
        return m1875clone;
    }

    public Excluder disableInnerClassSerialization() {
        Excluder m1875clone = m1875clone();
        m1875clone.serializeInnerClasses = false;
        return m1875clone;
    }

    public Excluder excludeFieldsWithoutExposeAnnotation() {
        Excluder m1875clone = m1875clone();
        m1875clone.requireExpose = true;
        return m1875clone;
    }

    public Excluder withExclusionStrategy(ExclusionStrategy exclusionStrategy, boolean z, boolean z2) {
        Excluder m1875clone = m1875clone();
        if (z) {
            ArrayList arrayList = new ArrayList(this.serializationStrategies);
            m1875clone.serializationStrategies = arrayList;
            arrayList.add(exclusionStrategy);
        }
        if (z2) {
            ArrayList arrayList2 = new ArrayList(this.deserializationStrategies);
            m1875clone.deserializationStrategies = arrayList2;
            arrayList2.add(exclusionStrategy);
        }
        return m1875clone;
    }

    @Override // com.xsdk.ab_firstbase.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        final boolean excludeClass = excludeClass(rawType, true);
        final boolean excludeClass2 = excludeClass(rawType, false);
        if (excludeClass || excludeClass2) {
            return new TypeAdapter<T>() { // from class: com.xsdk.ab_firstbase.gson.internal.Excluder.1
                private TypeAdapter<T> delegate;

                @Override // com.xsdk.ab_firstbase.gson.TypeAdapter
                public T read(JsonReader jsonReader) throws IOException {
                    if (excludeClass2) {
                        jsonReader.skipValue();
                        return null;
                    }
                    return delegate().read(jsonReader);
                }

                @Override // com.xsdk.ab_firstbase.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, T t) throws IOException {
                    if (excludeClass) {
                        jsonWriter.nullValue();
                    } else {
                        delegate().write(jsonWriter, t);
                    }
                }

                private TypeAdapter<T> delegate() {
                    TypeAdapter<T> typeAdapter = this.delegate;
                    if (typeAdapter != null) {
                        return typeAdapter;
                    }
                    TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(Excluder.this, typeToken);
                    this.delegate = delegateAdapter;
                    return delegateAdapter;
                }
            };
        }
        return null;
    }

    public boolean excludeField(Field field, boolean z) {
        Expose expose;
        if ((this.modifiers & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.version != IGNORE_VERSIONS && !isValidVersion((Since) field.getAnnotation(Since.class), (Until) field.getAnnotation(Until.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.requireExpose && ((expose = (Expose) field.getAnnotation(Expose.class)) == null || (!z ? expose.deserialize() : expose.serialize()))) {
            return true;
        }
        if ((!this.serializeInnerClasses && isInnerClass(field.getType())) || isAnonymousOrLocal(field.getType())) {
            return true;
        }
        List<ExclusionStrategy> list = z ? this.serializationStrategies : this.deserializationStrategies;
        if (list.isEmpty()) {
            return false;
        }
        FieldAttributes fieldAttributes = new FieldAttributes(field);
        Iterator<ExclusionStrategy> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().shouldSkipField(fieldAttributes)) {
                return true;
            }
        }
        return false;
    }

    public boolean excludeClass(Class<?> cls, boolean z) {
        if (this.version != IGNORE_VERSIONS && !isValidVersion((Since) cls.getAnnotation(Since.class), (Until) cls.getAnnotation(Until.class))) {
            return true;
        }
        if ((!this.serializeInnerClasses && isInnerClass(cls)) || isAnonymousOrLocal(cls)) {
            return true;
        }
        Iterator<ExclusionStrategy> it = (z ? this.serializationStrategies : this.deserializationStrategies).iterator();
        while (it.hasNext()) {
            if (it.next().shouldSkipClass(cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnonymousOrLocal(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean isInnerClass(Class<?> cls) {
        return cls.isMemberClass() && !isStatic(cls);
    }

    private boolean isStatic(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean isValidVersion(Since since, Until until) {
        return isValidSince(since) && isValidUntil(until);
    }

    private boolean isValidSince(Since since) {
        return since == null || since.value() <= this.version;
    }

    private boolean isValidUntil(Until until) {
        return until == null || until.value() > this.version;
    }
}
