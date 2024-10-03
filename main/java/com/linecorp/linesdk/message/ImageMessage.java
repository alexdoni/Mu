package com.linecorp.linesdk.message;

import com.facebook.share.internal.ShareConstants;
import com.linecorp.linesdk.utils.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ImageMessage extends MessageData {
    private Boolean animated = false;
    private String extension;
    private Long fileSize;
    private final String originalContentUrl;
    private final String previewImageUrl;
    private MessageSender sentBy;

    public ImageMessage(String str, String str2) {
        this.originalContentUrl = str;
        this.previewImageUrl = str2;
    }

    public void setAnimated(Boolean bool) {
        this.animated = bool;
    }

    public void setExtension(String str) {
        this.extension = str;
    }

    public void setFileSize(Long l) {
        this.fileSize = l;
    }

    public void setSentBy(MessageSender messageSender) {
        this.sentBy = messageSender;
    }

    @Override // com.linecorp.linesdk.message.MessageData
    public Type getType() {
        return Type.IMAGE;
    }

    @Override // com.linecorp.linesdk.message.MessageData, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put("originalContentUrl", this.originalContentUrl);
        jsonObject.put("previewImageUrl", this.previewImageUrl);
        jsonObject.put("animated", this.animated);
        jsonObject.put(ShareConstants.MEDIA_EXTENSION, this.extension);
        jsonObject.put("fileSize", this.fileSize);
        JSONUtils.put(jsonObject, "sentBy", this.sentBy);
        return jsonObject;
    }
}
