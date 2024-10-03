package com.linecorp.linesdk;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class SendMessageResponse {
    private String receiverId;
    private Status status;

    /* loaded from: classes2.dex */
    public enum Status {
        OK,
        DISCARDED
    }

    public SendMessageResponse(String str, Status status) {
        this.receiverId = str;
        this.status = status;
    }

    public String getTargetUserId() {
        return this.receiverId;
    }

    public Status getStatus() {
        return this.status;
    }

    public static SendMessageResponse fromJsonObject(JSONObject jSONObject) throws JSONException {
        Status status;
        if (jSONObject.get("status").equals(Status.OK.name().toLowerCase())) {
            status = Status.OK;
        } else {
            status = Status.DISCARDED;
        }
        return new SendMessageResponse(jSONObject.getString("to"), status);
    }

    public String toString() {
        return "SendMessageResponse{receiverId='" + this.receiverId + "', status='" + this.status + "'}";
    }
}
