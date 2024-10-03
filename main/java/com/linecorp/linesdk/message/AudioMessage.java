package com.linecorp.linesdk.message;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class AudioMessage extends MessageData {
    private final Long durationMillis;
    private final String originalContentUrl;

    public AudioMessage(String str, Long l) {
        this.originalContentUrl = str;
        this.durationMillis = l;
    }

    @Override // com.linecorp.linesdk.message.MessageData
    public Type getType() {
        return Type.AUDIO;
    }

    @Override // com.linecorp.linesdk.message.MessageData, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put("originalContentUrl", this.originalContentUrl);
        jsonObject.put(TypedValues.TransitionType.S_DURATION, this.durationMillis);
        return jsonObject;
    }
}
