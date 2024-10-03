package io.jsonwebtoken.io;

import io.jsonwebtoken.lang.Classes;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.DateFormats;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Strings;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OrgJsonSerializer<T> implements Serializer<T> {
    private static final Class JSON_STRING_CLASS;
    private static final String JSON_STRING_CLASS_NAME = "org.json.JSONString";
    private static final String JSON_WRITER_CLASS_NAME = "org.json.JSONWriter";
    private static final Class[] VALUE_TO_STRING_ARG_TYPES = {Object.class};

    static {
        if (Classes.isAvailable(JSON_STRING_CLASS_NAME)) {
            JSON_STRING_CLASS = Classes.forName(JSON_STRING_CLASS_NAME);
        } else {
            JSON_STRING_CLASS = null;
        }
    }

    @Override // io.jsonwebtoken.io.Serializer
    public byte[] serialize(T t) throws SerializationException {
        try {
            return toBytes(toJSONInstance(t));
        } catch (SerializationException e) {
            throw e;
        } catch (Exception e2) {
            throw new SerializationException("Unable to serialize object of type " + t.getClass().getName() + " to JSON: " + e2.getMessage(), e2);
        }
    }

    private static boolean isJSONString(Object obj) {
        Class cls = JSON_STRING_CLASS;
        if (cls != null) {
            return cls.isInstance(obj);
        }
        return false;
    }

    private Object toJSONInstance(Object obj) {
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof JSONObject) || (obj instanceof JSONArray) || JSONObject.NULL.equals(obj) || isJSONString(obj) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof BigInteger) || (obj instanceof BigDecimal) || (obj instanceof Enum)) {
            return obj;
        }
        if (obj instanceof Calendar) {
            obj = ((Calendar) obj).getTime();
        }
        if (obj instanceof Date) {
            return DateFormats.formatIso8601((Date) obj);
        }
        if (obj instanceof byte[]) {
            return Encoders.BASE64.encode((byte[]) obj);
        }
        if (obj instanceof char[]) {
            return new String((char[]) obj);
        }
        if (obj instanceof Map) {
            return toJSONObject((Map) obj);
        }
        if (obj instanceof Collection) {
            return toJSONArray((Collection) obj);
        }
        if (Objects.isArray(obj)) {
            return toJSONArray(Collections.arrayToList(obj));
        }
        throw new SerializationException("Unable to serialize object of type " + obj.getClass().getName() + " to JSON using known heuristics.");
    }

    private JSONObject toJSONObject(Map<?, ?> map) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            jSONObject.put(String.valueOf(entry.getKey()), toJSONInstance(entry.getValue()));
        }
        return jSONObject;
    }

    private JSONArray toJSONArray(Collection collection) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            jSONArray.put(toJSONInstance(it.next()));
        }
        return jSONArray;
    }

    protected byte[] toBytes(Object obj) {
        return (obj instanceof JSONObject ? obj.toString() : (String) Classes.invokeStatic(JSON_WRITER_CLASS_NAME, "valueToString", VALUE_TO_STRING_ARG_TYPES, obj)).getBytes(Strings.UTF_8);
    }
}
