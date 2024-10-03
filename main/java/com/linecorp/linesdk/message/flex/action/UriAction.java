package com.linecorp.linesdk.message.flex.action;

import com.facebook.share.internal.ShareConstants;
import com.linecorp.linesdk.message.flex.action.Action;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class UriAction extends Action {
    private String uri;

    public UriAction(String str, String str2) {
        super(Action.Type.URI, str2);
        this.uri = str;
    }

    public UriAction(String str) {
        this(str, null);
    }

    @Override // com.linecorp.linesdk.message.flex.action.Action, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put(ShareConstants.MEDIA_URI, this.uri);
        return jsonObject;
    }
}
