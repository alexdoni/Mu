package com.linecorp.linesdk.message;

import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class OttRequest {
    private List<String> targetUserIds;

    public OttRequest(List<String> list) {
        this.targetUserIds = list;
    }

    public String toJsonString() throws JSONException {
        return toJsonObject().toString();
    }

    private JSONObject toJsonObject() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = this.targetUserIds.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        return new JSONObject().put("userIds", jSONArray);
    }
}
