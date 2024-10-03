package com.xsdk.ab_firstbase.gson.internal.bind;

import com.xsdk.ab_firstbase.gson.Gson;
import com.xsdk.ab_firstbase.gson.TypeAdapter;
import com.xsdk.ab_firstbase.gson.TypeAdapterFactory;
import com.xsdk.ab_firstbase.gson.internal.C$Gson$Types;
import com.xsdk.ab_firstbase.gson.internal.ConstructorConstructor;
import com.xsdk.ab_firstbase.gson.internal.ObjectConstructor;
import com.xsdk.ab_firstbase.gson.reflect.TypeToken;
import com.xsdk.ab_firstbase.gson.stream.JsonReader;
import com.xsdk.ab_firstbase.gson.stream.JsonToken;
import com.xsdk.ab_firstbase.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes3.dex */
public final class CollectionTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public CollectionTypeAdapterFactory(ConstructorConstructor constructorConstructor) {
        this.constructorConstructor = constructorConstructor;
    }

    @Override // com.xsdk.ab_firstbase.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        Class<? super T> rawType = typeToken.getRawType();
        if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
        }
        Type collectionElementType = C$Gson$Types.getCollectionElementType(type, rawType);
        return new Adapter(gson, collectionElementType, gson.getAdapter(TypeToken.get(collectionElementType)), this.constructorConstructor.get(typeToken));
    }

    /* loaded from: classes3.dex */
    private static final class Adapter<E> extends TypeAdapter<Collection<E>> {
        private final ObjectConstructor<? extends Collection<E>> constructor;
        private final TypeAdapter<E> elementTypeAdapter;

        public Adapter(Gson gson, Type type, TypeAdapter<E> typeAdapter, ObjectConstructor<? extends Collection<E>> objectConstructor) {
            this.elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
            this.constructor = objectConstructor;
        }

        @Override // com.xsdk.ab_firstbase.gson.TypeAdapter
        public Collection<E> read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Collection<E> construct = this.constructor.construct();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                construct.add(this.elementTypeAdapter.read(jsonReader));
            }
            jsonReader.endArray();
            return construct;
        }

        @Override // com.xsdk.ab_firstbase.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Collection<E> collection) throws IOException {
            if (collection == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginArray();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.elementTypeAdapter.write(jsonWriter, it.next());
            }
            jsonWriter.endArray();
        }
    }
}
