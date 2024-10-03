package com.linecorp.linesdk.message.flex.component;

import com.facebook.internal.NativeProtocol;
import com.linecorp.linesdk.message.flex.action.Action;
import com.linecorp.linesdk.message.flex.component.FlexMessageComponent;
import com.linecorp.linesdk.utils.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FlexImageComponent extends FlexMessageComponent {
    private Action action;
    private FlexMessageComponent.Alignment align;
    private FlexMessageComponent.AspectMode aspectMode;
    private FlexMessageComponent.AspectRatio aspectRatio;
    private String backgroundColor;
    private int flex;
    private FlexMessageComponent.Gravity gravity;
    private FlexMessageComponent.Margin margin;
    private FlexMessageComponent.Size size;
    private String url;

    private FlexImageComponent() {
        super(FlexMessageComponent.Type.IMAGE);
        this.align = FlexMessageComponent.Alignment.CENTER;
        this.gravity = FlexMessageComponent.Gravity.TOP;
    }

    private FlexImageComponent(Builder builder) {
        this();
        this.url = builder.url;
        this.flex = builder.flex;
        this.margin = builder.margin;
        this.align = builder.align;
        this.gravity = builder.gravity;
        this.size = builder.size;
        this.aspectRatio = builder.aspectRatio;
        this.aspectMode = builder.aspectMode;
        this.backgroundColor = builder.backgroundColor;
        this.action = builder.action;
    }

    public static Builder newBuilder(String str) {
        return new Builder(str);
    }

    @Override // com.linecorp.linesdk.message.flex.component.FlexMessageComponent, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put("url", this.url);
        int i = this.flex;
        if (i != -1) {
            jsonObject.put("flex", i);
        }
        JSONUtils.put(jsonObject, "margin", this.margin);
        JSONUtils.put(jsonObject, "align", this.align);
        JSONUtils.put(jsonObject, "gravity", this.gravity);
        FlexMessageComponent.Size size = this.size;
        JSONUtils.put(jsonObject, "size", size != null ? size.getValue() : null);
        FlexMessageComponent.AspectRatio aspectRatio = this.aspectRatio;
        JSONUtils.put(jsonObject, "aspectRatio", aspectRatio != null ? aspectRatio.getValue() : null);
        JSONUtils.put(jsonObject, "aspectMode", this.aspectMode);
        JSONUtils.put(jsonObject, "backgroundColor", this.backgroundColor);
        JSONUtils.put(jsonObject, NativeProtocol.WEB_DIALOG_ACTION, this.action);
        return jsonObject;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private Action action;
        private FlexMessageComponent.Alignment align;
        private FlexMessageComponent.AspectMode aspectMode;
        private FlexMessageComponent.AspectRatio aspectRatio;
        private String backgroundColor;
        private int flex;
        private FlexMessageComponent.Gravity gravity;
        private FlexMessageComponent.Margin margin;
        private FlexMessageComponent.Size size;
        private String url;

        private Builder(String str) {
            this.flex = -1;
            this.url = str;
        }

        public Builder setFlex(int i) {
            this.flex = i;
            return this;
        }

        public Builder setMargin(FlexMessageComponent.Margin margin) {
            this.margin = margin;
            return this;
        }

        public Builder setAlign(FlexMessageComponent.Alignment alignment) {
            this.align = alignment;
            return this;
        }

        public Builder setGravity(FlexMessageComponent.Gravity gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setSize(FlexMessageComponent.Size size) {
            this.size = size;
            return this;
        }

        public Builder setAspectRatio(FlexMessageComponent.AspectRatio aspectRatio) {
            this.aspectRatio = aspectRatio;
            return this;
        }

        public Builder setAspectMode(FlexMessageComponent.AspectMode aspectMode) {
            this.aspectMode = aspectMode;
            return this;
        }

        public Builder setBackgroundColor(String str) {
            this.backgroundColor = str;
            return this;
        }

        public Builder setAction(Action action) {
            this.action = action;
            return this;
        }

        public FlexImageComponent build() {
            return new FlexImageComponent(this);
        }
    }
}
