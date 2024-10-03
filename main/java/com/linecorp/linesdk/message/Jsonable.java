package com.linecorp.linesdk.message;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public interface Jsonable {
    JSONObject toJsonObject() throws JSONException;
}
