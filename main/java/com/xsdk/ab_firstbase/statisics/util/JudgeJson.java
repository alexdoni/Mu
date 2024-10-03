package com.xsdk.ab_firstbase.statisics.util;

import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class JudgeJson {
    public static boolean isJsonObject(Object obj) {
        String obj2 = obj.toString();
        try {
            new JSONObject(obj2);
        } catch (Exception unused) {
        }
        return obj2.startsWith("{");
    }

    public static boolean isJsonArray(Object obj) {
        String obj2 = obj.toString();
        try {
            new JSONArray(obj2);
        } catch (Exception unused) {
        }
        return obj2.startsWith("[");
    }

    public static boolean isJson(Object obj) {
        return isJsonObject(obj) || isJsonArray(obj);
    }

    public static boolean isJsonObjectIsNull(Object obj) {
        try {
            new JSONObject(obj.toString());
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean isJsonArrayIsNull(Object obj) {
        return new JSONArray(obj.toString()).length() <= 0;
    }
}
