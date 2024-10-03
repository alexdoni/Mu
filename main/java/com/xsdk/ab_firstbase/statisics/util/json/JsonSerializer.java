package com.xsdk.ab_firstbase.statisics.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class JsonSerializer {
    public static final String False = "false";
    public static final String NaN = "NaN";
    public static final String NegativeInfinity = "-Infinity";
    public static final String Null = "null";
    public static final String PositiveInfinity = "Infinity";
    public static final String True = "true";
    public static final String Undefined = "undefined";

    public Object deSerialize(String str) throws Exception {
        JsonTextReader jsonTextReader = new JsonTextReader(new StringTextReader(str));
        JsonSerializer jsonSerializer = new JsonSerializer();
        if (!jsonTextReader.read()) {
            return null;
        }
        Object createObject = jsonSerializer.createObject(jsonTextReader);
        jsonTextReader.close();
        return createObject;
    }

    private Object createObject(JsonReader jsonReader) throws Exception {
        JsonToken tokenType = jsonReader.getTokenType();
        if (tokenType == JsonToken.StartObject) {
            return createMap(jsonReader);
        }
        if (tokenType == JsonToken.StartArray) {
            return createList(jsonReader);
        }
        if (tokenType == JsonToken.Integer || tokenType == JsonToken.Float || tokenType == JsonToken.String || tokenType == JsonToken.Boolean || tokenType == JsonToken.Date) {
            return jsonReader.getValue();
        }
        if (tokenType == JsonToken.StartConstructor || tokenType == JsonToken.EndConstructor) {
            return jsonReader.getValue();
        }
        if (tokenType == JsonToken.Null || tokenType == JsonToken.Undefined) {
            return null;
        }
        throw new Exception("反序列化时遇到了错误的token: " + jsonReader.getTokenType());
    }

    private Map<Object, Object> createMap(JsonReader jsonReader) throws Exception {
        HashMap hashMap = new HashMap();
        while (jsonReader.read()) {
            JsonToken tokenType = jsonReader.getTokenType();
            if (tokenType == JsonToken.PropertyName) {
                Object value = jsonReader.getValue();
                jsonReader.read();
                hashMap.put(value, createObject(jsonReader));
            } else {
                if (tokenType == JsonToken.EndObject) {
                    return hashMap;
                }
                throw new Exception("反序列化时遇到了错误的token: " + jsonReader.getTokenType());
            }
        }
        throw new Exception("未知错误");
    }

    private List<Object> createList(JsonReader jsonReader) throws Exception {
        ArrayList arrayList = new ArrayList();
        while (jsonReader.read()) {
            JsonToken tokenType = jsonReader.getTokenType();
            if (tokenType == JsonToken.EndArray) {
                return arrayList;
            }
            if (tokenType != JsonToken.Comment) {
                arrayList.add(createObject(jsonReader));
            }
        }
        throw new Exception("反序列化时遇到未知错误");
    }

    public static void main(String[] strArr) {
        Object obj;
        try {
            obj = new JsonSerializer().deSerialize("[{Name:\"ruanxz\",'Key':true},{Password:\"123466\"},{Rules:[10,20]}]");
        } catch (Exception e) {
            e.printStackTrace();
            obj = null;
        }
        System.out.println(obj.toString());
    }
}
