package com.linecorp.linesdk.message.template;

import com.facebook.share.internal.ShareConstants;
import com.google.firebase.messaging.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class UriAction extends ClickActionForTemplateMessage {
    private String label;
    private String uri;

    public UriAction(String str, String str2) {
        this.type = ShareConstants.MEDIA_URI;
        this.uri = str2;
        this.label = str;
    }

    @Override // com.linecorp.linesdk.message.template.ClickActionForTemplateMessage, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put(ShareConstants.MEDIA_URI, this.uri);
        jsonObject.put(Constants.ScionAnalytics.PARAM_LABEL, this.label);
        return jsonObject;
    }
}
