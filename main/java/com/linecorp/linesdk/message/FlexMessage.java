package com.linecorp.linesdk.message;

import com.linecorp.linesdk.message.flex.container.FlexMessageContainer;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FlexMessage extends MessageData {
    private String altText;
    private FlexMessageContainer contents;

    public FlexMessage(String str, FlexMessageContainer flexMessageContainer) {
        this.altText = str;
        this.contents = flexMessageContainer;
    }

    @Override // com.linecorp.linesdk.message.MessageData
    public Type getType() {
        return Type.FLEX;
    }

    @Override // com.linecorp.linesdk.message.MessageData, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put("altText", this.altText);
        jsonObject.put("contents", this.contents.toJsonObject());
        return jsonObject;
    }
}
