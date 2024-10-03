package com.linecorp.linesdk.message.flex.container;

import com.facebook.share.internal.ShareConstants;
import com.linecorp.linesdk.message.Jsonable;
import com.linecorp.linesdk.message.Stringable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class FlexMessageContainer implements Jsonable {
    protected final Type type;

    /* loaded from: classes2.dex */
    public enum Type implements Stringable {
        BUBBLE,
        CAROUSEL
    }

    public FlexMessageContainer(Type type) {
        this.type = type;
    }

    @Override // com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ShareConstants.MEDIA_TYPE, this.type.name().toLowerCase());
        return jSONObject;
    }
}
