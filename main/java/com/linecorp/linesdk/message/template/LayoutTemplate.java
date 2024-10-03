package com.linecorp.linesdk.message.template;

import com.facebook.share.internal.ShareConstants;
import com.linecorp.linesdk.message.Jsonable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class LayoutTemplate implements Jsonable {
    private final Type type;

    public LayoutTemplate(Type type) {
        this.type = type;
    }

    @Override // com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ShareConstants.MEDIA_TYPE, this.type.getServerKey());
        return jSONObject;
    }
}
