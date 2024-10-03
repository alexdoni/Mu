package com.linecorp.linesdk.message.template;

import com.linecorp.linesdk.message.Jsonable;
import com.linecorp.linesdk.utils.JSONUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CarouselLayoutTemplate extends LayoutTemplate {
    private List<CarouselColumn> columns;
    private ImageAspectRatio imageAspectRatio;
    private ImageScaleType imageScaleType;

    public CarouselLayoutTemplate(List<CarouselColumn> list) {
        super(Type.CAROUSEL);
        this.imageAspectRatio = ImageAspectRatio.RECTANGLE;
        this.imageScaleType = ImageScaleType.COVER;
        this.columns = list;
    }

    public void setImageAspectRatio(ImageAspectRatio imageAspectRatio) {
        this.imageAspectRatio = imageAspectRatio;
    }

    public void setImageScaleType(ImageScaleType imageScaleType) {
        this.imageScaleType = imageScaleType;
    }

    @Override // com.linecorp.linesdk.message.template.LayoutTemplate, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        JSONUtils.putArray(jsonObject, "columns", this.columns);
        JSONUtils.putArray(jsonObject, "columns", this.columns);
        JSONUtils.put(jsonObject, "imageAspectRatio", this.imageAspectRatio.getServerKey());
        JSONUtils.put(jsonObject, "imageSize", this.imageScaleType.getServerKey());
        return jsonObject;
    }

    /* loaded from: classes2.dex */
    public static class CarouselColumn implements Jsonable {
        private List<ClickActionForTemplateMessage> actions;
        private ClickActionForTemplateMessage defaultAction;
        private String imageBackgroundColor;
        private String text;
        private String thumbnailImageUrl;
        private String title;

        public CarouselColumn(String str, List<ClickActionForTemplateMessage> list) {
            this.text = str;
            this.actions = list;
        }

        public void setThumbnailImageUrl(String str) {
            this.thumbnailImageUrl = str;
        }

        public void setImageBackgroundColor(String str) {
            this.imageBackgroundColor = str;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public void setDefaultAction(ClickActionForTemplateMessage clickActionForTemplateMessage) {
            this.defaultAction = clickActionForTemplateMessage;
        }

        @Override // com.linecorp.linesdk.message.Jsonable
        public JSONObject toJsonObject() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            JSONUtils.put(jSONObject, "text", this.text);
            JSONUtils.putArray(jSONObject, "actions", this.actions);
            JSONUtils.put(jSONObject, "thumbnailImageUrl", this.thumbnailImageUrl);
            JSONUtils.put(jSONObject, "imageBackgroundColor", this.imageBackgroundColor);
            JSONUtils.put(jSONObject, "title", this.title);
            JSONUtils.put(jSONObject, "defaultAction", this.defaultAction);
            return jSONObject;
        }
    }
}
