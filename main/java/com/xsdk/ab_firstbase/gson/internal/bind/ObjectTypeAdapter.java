package com.xsdk.ab_firstbase.gson.internal.bind;

import com.xsdk.ab_firstbase.gson.Gson;
import com.xsdk.ab_firstbase.gson.TypeAdapter;
import com.xsdk.ab_firstbase.gson.TypeAdapterFactory;
import com.xsdk.ab_firstbase.gson.internal.LinkedTreeMap;
import com.xsdk.ab_firstbase.gson.reflect.TypeToken;
import com.xsdk.ab_firstbase.gson.stream.JsonReader;
import com.xsdk.ab_firstbase.gson.stream.JsonToken;
import com.xsdk.ab_firstbase.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public final class ObjectTypeAdapter extends TypeAdapter<Object> {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() { // from class: com.xsdk.ab_firstbase.gson.internal.bind.ObjectTypeAdapter.1
        @Override // com.xsdk.ab_firstbase.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Object.class) {
                return new ObjectTypeAdapter(gson);
            }
            return null;
        }
    };
    private final Gson gson;

    private ObjectTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    /* renamed from: com.xsdk.ab_firstbase.gson.internal.bind.ObjectTypeAdapter$2 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class C27692 {
        static final /* synthetic */ int[] $SwitchMap$com$xsdk$ab_firstbase$gson$stream$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$xsdk$ab_firstbase$gson$stream$JsonToken = iArr;
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$xsdk$ab_firstbase$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$xsdk$ab_firstbase$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$xsdk$ab_firstbase$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$xsdk$ab_firstbase$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$xsdk$ab_firstbase$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // com.xsdk.ab_firstbase.gson.TypeAdapter
    public Object read(JsonReader jsonReader) throws IOException {
        switch (C27692.$SwitchMap$com$xsdk$ab_firstbase$gson$stream$JsonToken[jsonReader.peek().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(read(jsonReader));
                }
                jsonReader.endArray();
                return arrayList;
            case 2:
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    linkedTreeMap.put(jsonReader.nextName(), read(jsonReader));
                }
                jsonReader.endObject();
                return linkedTreeMap;
            case 3:
                return jsonReader.nextString();
            case 4:
                return Double.valueOf(jsonReader.nextDouble());
            case 5:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case 6:
                jsonReader.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // com.xsdk.ab_firstbase.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        TypeAdapter adapter = this.gson.getAdapter(obj.getClass());
        if (adapter instanceof ObjectTypeAdapter) {
            jsonWriter.beginObject();
            jsonWriter.endObject();
        } else {
            adapter.write(jsonWriter, obj);
        }
    }
}
