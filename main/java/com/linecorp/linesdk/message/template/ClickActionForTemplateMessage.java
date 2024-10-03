package com.linecorp.linesdk.message.template;

import com.facebook.share.internal.ShareConstants;
import com.linecorp.linesdk.message.Jsonable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ClickActionForTemplateMessage implements Jsonable {
    protected String type;

    @Override // com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ShareConstants.MEDIA_TYPE, this.type);
        return jSONObject;
    }
}
