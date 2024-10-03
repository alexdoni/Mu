package com.linecorp.linesdk.message;

import com.linecorp.linesdk.message.template.LayoutTemplate;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class TemplateMessage extends MessageData {
    private String altText;
    private LayoutTemplate template;

    @Override // com.linecorp.linesdk.message.MessageData
    public Type getType() {
        return Type.TEMPLATE;
    }

    public TemplateMessage(String str, LayoutTemplate layoutTemplate) {
        this.altText = str;
        this.template = layoutTemplate;
    }

    @Override // com.linecorp.linesdk.message.MessageData, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put("altText", this.altText);
        jsonObject.put("template", this.template.toJsonObject());
        return jsonObject;
    }
}
