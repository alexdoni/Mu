package com.linecorp.linesdk.utils;

import com.linecorp.linesdk.message.Jsonable;
import com.linecorp.linesdk.message.Stringable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class JSONUtils {
    public static <T> void put(JSONObject jSONObject, String str, T t) throws JSONException {
        if (t == null) {
            return;
        }
        if (t instanceof Jsonable) {
            jSONObject.put(str, ((Jsonable) t).toJsonObject());
        } else if (t instanceof Stringable) {
            jSONObject.put(str, ((Stringable) t).name().toLowerCase());
        } else {
            jSONObject.put(str, t);
        }
    }

    public static <T> void putArray(JSONObject jSONObject, String str, List<T> list) throws JSONException {
        if (list == null) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        for (T t : list) {
            if (t instanceof Jsonable) {
                jSONArray.put(((Jsonable) t).toJsonObject());
            } else {
                jSONArray.put(t);
            }
        }
        jSONObject.put(str, jSONArray);
    }

    public static List<String> toStringList(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }
}
