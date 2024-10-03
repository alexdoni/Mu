package com.linecorp.linesdk.message.template;

import com.facebook.internal.NativeProtocol;
import com.linecorp.linesdk.message.Jsonable;
import com.linecorp.linesdk.utils.JSONUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ImageCarouselLayoutTemplate extends LayoutTemplate {
    private List<ImageCarouselColumn> columns;

    public ImageCarouselLayoutTemplate(List<ImageCarouselColumn> list) {
        super(Type.IMAGE_CAROUSEL);
        this.columns = list;
    }

    @Override // com.linecorp.linesdk.message.template.LayoutTemplate, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        JSONUtils.putArray(jsonObject, "columns", this.columns);
        return jsonObject;
    }

    /* loaded from: classes2.dex */
    public static class ImageCarouselColumn implements Jsonable {
        private ClickActionForTemplateMessage action;
        private String imageUrl;

        public ImageCarouselColumn(String str, ClickActionForTemplateMessage clickActionForTemplateMessage) {
            this.imageUrl = str;
            this.action = clickActionForTemplateMessage;
        }

        @Override // com.linecorp.linesdk.message.Jsonable
        public JSONObject toJsonObject() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            JSONUtils.put(jSONObject, "imageUrl", this.imageUrl);
            JSONUtils.put(jSONObject, NativeProtocol.WEB_DIALOG_ACTION, this.action);
            return jSONObject;
        }
    }
}
