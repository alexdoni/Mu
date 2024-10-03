package com.linecorp.linesdk.message.flex.component;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.linecorp.linesdk.message.flex.action.Action;
import com.linecorp.linesdk.message.flex.component.FlexMessageComponent;
import com.linecorp.linesdk.utils.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FlexButtonComponent extends FlexMessageComponent {
    private Action action;
    private String color;
    private int flex;
    private FlexMessageComponent.Gravity gravity;
    private FlexMessageComponent.Height height;
    private FlexMessageComponent.Margin margin;
    private FlexMessageComponent.Style style;

    private FlexButtonComponent() {
        super(FlexMessageComponent.Type.BUTTON);
    }

    private FlexButtonComponent(Builder builder) {
        this();
        this.action = builder.action;
        this.flex = builder.flex;
        this.margin = builder.margin;
        this.height = builder.height;
        this.style = builder.style;
        this.color = builder.color;
        this.gravity = builder.gravity;
    }

    public static Builder newBuilder(Action action) {
        return new Builder(action);
    }

    @Override // com.linecorp.linesdk.message.flex.component.FlexMessageComponent, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        JSONUtils.put(jsonObject, NativeProtocol.WEB_DIALOG_ACTION, this.action);
        JSONUtils.put(jsonObject, "margin", this.margin);
        JSONUtils.put(jsonObject, ViewHierarchyConstants.DIMENSION_HEIGHT_KEY, this.height);
        JSONUtils.put(jsonObject, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, this.style);
        JSONUtils.put(jsonObject, TypedValues.Custom.S_COLOR, this.color);
        JSONUtils.put(jsonObject, "gravity", this.gravity);
        int i = this.flex;
        if (i != -1) {
            jsonObject.put("flex", i);
        }
        return jsonObject;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private Action action;
        private String color;
        private int flex;
        private FlexMessageComponent.Gravity gravity;
        private FlexMessageComponent.Height height;
        private FlexMessageComponent.Margin margin;
        private FlexMessageComponent.Style style;

        private Builder(Action action) {
            this.flex = -1;
            this.action = action;
        }

        public Builder setFlex(int i) {
            this.flex = i;
            return this;
        }

        public Builder setMargin(FlexMessageComponent.Margin margin) {
            this.margin = margin;
            return this;
        }

        public Builder setHeight(FlexMessageComponent.Height height) {
            this.height = height;
            return this;
        }

        public Builder setStyle(FlexMessageComponent.Style style) {
            this.style = style;
            return this;
        }

        public Builder setColor(String str) {
            this.color = str;
            return this;
        }

        public Builder setGravity(FlexMessageComponent.Gravity gravity) {
            this.gravity = gravity;
            return this;
        }

        public FlexButtonComponent build() {
            return new FlexButtonComponent(this);
        }
    }
}
