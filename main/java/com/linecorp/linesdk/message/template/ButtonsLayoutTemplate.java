package com.linecorp.linesdk.message.template;

import androidx.core.view.ViewCompat;
import com.linecorp.linesdk.message.MessageSender;
import com.linecorp.linesdk.utils.JSONUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ButtonsLayoutTemplate extends LayoutTemplate {
    private List<ClickActionForTemplateMessage> actions;
    private ClickActionForTemplateMessage defaultAction;
    private ImageAspectRatio imageAspectRatio;
    private int imageBackgroundColor;
    private ImageScaleType imageScaleType;
    private MessageSender messageSender;
    private String text;
    private String thumbnailImageUrl;
    private String title;

    public ButtonsLayoutTemplate(String str, List<ClickActionForTemplateMessage> list) {
        super(Type.BUTTONS);
        this.imageAspectRatio = ImageAspectRatio.RECTANGLE;
        this.imageScaleType = ImageScaleType.COVER;
        this.imageBackgroundColor = -1;
        this.text = str;
        this.actions = list;
    }

    public void setThumbnailImageUrl(String str) {
        this.thumbnailImageUrl = str;
    }

    public void setImageAspectRatio(ImageAspectRatio imageAspectRatio) {
        this.imageAspectRatio = imageAspectRatio;
    }

    public void setImageScaleType(ImageScaleType imageScaleType) {
        this.imageScaleType = imageScaleType;
    }

    public void setImageBackgroundColor(int i) {
        this.imageBackgroundColor = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setDefaultAction(ClickActionForTemplateMessage clickActionForTemplateMessage) {
        this.defaultAction = clickActionForTemplateMessage;
    }

    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override // com.linecorp.linesdk.message.template.LayoutTemplate, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        JSONUtils.put(jsonObject, "text", this.text);
        JSONUtils.put(jsonObject, "thumbnailImageUrl", this.thumbnailImageUrl);
        JSONUtils.put(jsonObject, "imageAspectRatio", this.imageAspectRatio.getServerKey());
        JSONUtils.put(jsonObject, "imageSize", this.imageScaleType.getServerKey());
        JSONUtils.put(jsonObject, "imageBackgroundColor", getColorString(this.imageBackgroundColor));
        JSONUtils.put(jsonObject, "title", this.title);
        JSONUtils.put(jsonObject, "defaultAction", this.defaultAction);
        JSONUtils.put(jsonObject, "sentBy", this.messageSender);
        JSONUtils.putArray(jsonObject, "actions", this.actions);
        return jsonObject;
    }

    private String getColorString(int i) {
        return String.format("#%06X", Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK));
    }
}
