package com.linecorp.linesdk.message;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class VideoMessage extends MessageData {
    private final String originalContentUrl;
    private final String previewImageUrl;

    public VideoMessage(String str, String str2) {
        this.originalContentUrl = str;
        this.previewImageUrl = str2;
    }

    @Override // com.linecorp.linesdk.message.MessageData
    public Type getType() {
        return Type.VIDEO;
    }

    @Override // com.linecorp.linesdk.message.MessageData, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put("originalContentUrl", this.originalContentUrl);
        jsonObject.put("previewImageUrl", this.previewImageUrl);
        return jsonObject;
    }
}
