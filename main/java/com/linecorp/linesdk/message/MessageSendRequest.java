package com.linecorp.linesdk.message;

import com.linecorp.linesdk.utils.JSONUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MessageSendRequest {
    private List<MessageData> messages;
    private String ott;
    private String targetUserId;
    private List<String> targetUserIds;

    public static MessageSendRequest createSingleUserType(String str, List<MessageData> list) {
        return new MessageSendRequest().setTargetUserId(str).setMessages(list);
    }

    public static MessageSendRequest createMultiUsersType(List<String> list, List<MessageData> list2) {
        return new MessageSendRequest().setTargetUserIds(list).setMessages(list2);
    }

    public static MessageSendRequest createOttType(String str, List<MessageData> list) {
        return new MessageSendRequest().setOtt(str).setMessages(list);
    }

    private MessageSendRequest() {
    }

    public String toJsonString() throws JSONException {
        return toJsonObject().toString();
    }

    private MessageSendRequest setOtt(String str) {
        this.ott = str;
        return this;
    }

    private MessageSendRequest setTargetUserId(String str) {
        this.targetUserId = str;
        return this;
    }

    private MessageSendRequest setTargetUserIds(List<String> list) {
        this.targetUserIds = list;
        return this;
    }

    private MessageSendRequest setMessages(List<MessageData> list) {
        this.messages = list;
        return this;
    }

    private JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONUtils.put(jSONObject, "to", this.targetUserId);
        JSONUtils.putArray(jSONObject, "to", this.targetUserIds);
        JSONUtils.put(jSONObject, "token", this.ott);
        JSONUtils.putArray(jSONObject, "messages", this.messages);
        return jSONObject;
    }
}
