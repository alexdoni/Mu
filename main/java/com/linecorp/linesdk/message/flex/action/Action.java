package com.linecorp.linesdk.message.flex.action;

import com.facebook.share.internal.ShareConstants;
import com.google.firebase.messaging.Constants;
import com.linecorp.linesdk.message.Jsonable;
import com.linecorp.linesdk.message.Stringable;
import com.linecorp.linesdk.utils.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class Action implements Jsonable {
    protected String label;
    protected final Type type;

    /* loaded from: classes2.dex */
    public enum Type implements Stringable {
        POSTBACK,
        MESSAGE,
        URI,
        DATETIMEPICKER,
        CAMERA,
        CAMERAROLL,
        LOCATION
    }

    public Action(Type type, String str) {
        this.type = type;
        this.label = str;
    }

    public Action(Type type) {
        this(type, null);
    }

    @Override // com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ShareConstants.MEDIA_TYPE, this.type.name().toLowerCase());
        JSONUtils.put(jSONObject, Constants.ScionAnalytics.PARAM_LABEL, this.label);
        return jSONObject;
    }
}
