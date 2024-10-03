package com.linecorp.linesdk.message;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class TextMessage extends MessageData {
    private final MessageSender sendBy;
    private final String text;

    public TextMessage(String str) {
        this.text = str;
        this.sendBy = null;
    }

    public TextMessage(String str, MessageSender messageSender) {
        this.text = str;
        this.sendBy = messageSender;
    }

    @Override // com.linecorp.linesdk.message.MessageData
    public Type getType() {
        return Type.TEXT;
    }

    @Override // com.linecorp.linesdk.message.MessageData, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put("text", this.text);
        MessageSender messageSender = this.sendBy;
        if (messageSender != null) {
            jsonObject.put("sentBy", messageSender.toJsonObject());
        }
        return jsonObject;
    }
}
