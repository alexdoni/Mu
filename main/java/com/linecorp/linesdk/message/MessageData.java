package com.linecorp.linesdk.message;

import com.facebook.share.internal.ShareConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class MessageData implements Jsonable {
    public abstract Type getType();

    @Override // com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ShareConstants.MEDIA_TYPE, getType().name().toLowerCase());
        return jSONObject;
    }
}
