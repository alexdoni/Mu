package com.linecorp.linesdk.message;

import com.google.firebase.messaging.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MessageSender implements Jsonable {
    private final String footerIconUrl;
    private final String footerLinkUrl;
    private final String label;

    public MessageSender(String str, String str2, String str3) {
        this.label = str;
        this.footerIconUrl = str2;
        this.footerLinkUrl = str3;
    }

    @Override // com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.ScionAnalytics.PARAM_LABEL, this.label);
        jSONObject.put("iconUrl", this.footerIconUrl);
        String str = this.footerLinkUrl;
        if (str != null) {
            jSONObject.put("linkUrl", str);
        }
        return jSONObject;
    }
}
