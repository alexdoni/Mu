package com.xsdk.ab_firstbase.statisics.util.json;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class JsonUtil {
    public static Object get(String str, String str2) {
        try {
            return new JSONObject(str).get(str2);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
