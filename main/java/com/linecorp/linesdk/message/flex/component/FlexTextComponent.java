package com.linecorp.linesdk.message.flex.component;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.internal.NativeProtocol;
import com.linecorp.linesdk.message.flex.action.Action;
import com.linecorp.linesdk.message.flex.component.FlexMessageComponent;
import com.linecorp.linesdk.utils.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FlexTextComponent extends FlexMessageComponent {
    protected static final int MAXLINES_VALUE_NONE = -1;
    private Action action;
    private FlexMessageComponent.Alignment align;
    private String color;
    private int flex;
    private FlexMessageComponent.Gravity gravity;
    private FlexMessageComponent.Margin margin;
    private int maxLines;
    private FlexMessageComponent.Size size;
    private String text;
    private FlexMessageComponent.Weight weight;
    private Boolean wrap;

    private FlexTextComponent() {
        super(FlexMessageComponent.Type.TEXT);
    }

    private FlexTextComponent(Builder builder) {
        this();
        this.text = builder.text;
        this.flex = builder.flex;
        this.margin = builder.margin;
        this.size = builder.size;
        this.align = builder.align;
        this.gravity = builder.gravity;
        this.wrap = builder.wrap;
        this.maxLines = builder.maxLines;
        this.weight = builder.weight;
        this.color = builder.color;
        this.action = builder.action;
    }

    public static Builder newBuilder(String str) {
        return new Builder(str);
    }

    @Override // com.linecorp.linesdk.message.flex.component.FlexMessageComponent, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put("text", this.text);
        JSONUtils.put(jsonObject, "margin", this.margin);
        FlexMessageComponent.Size size = this.size;
        JSONUtils.put(jsonObject, "size", size != null ? size.getValue() : null);
        JSONUtils.put(jsonObject, "align", this.align);
        JSONUtils.put(jsonObject, "gravity", this.gravity);
        JSONUtils.put(jsonObject, "wrap", this.wrap);
        JSONUtils.put(jsonObject, "weight", this.weight);
        JSONUtils.put(jsonObject, TypedValues.Custom.S_COLOR, this.color);
        JSONUtils.put(jsonObject, NativeProtocol.WEB_DIALOG_ACTION, this.action);
        int i = this.flex;
        if (i != -1) {
            jsonObject.put("flex", i);
        }
        int i2 = this.maxLines;
        if (i2 != -1) {
            jsonObject.put("maxLines", i2);
        }
        return jsonObject;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private Action action;
        private FlexMessageComponent.Alignment align;
        private String color;
        private int flex;
        private FlexMessageComponent.Gravity gravity;
        private FlexMessageComponent.Margin margin;
        private int maxLines;
        private FlexMessageComponent.Size size;
        private String text;
        private FlexMessageComponent.Weight weight;
        private Boolean wrap;

        private Builder() {
            this.flex = -1;
            this.maxLines = -1;
        }

        public Builder(String str) {
            this();
            this.text = str;
        }

        public Builder setFlex(int i) {
            this.flex = i;
            return this;
        }

        public Builder setMargin(FlexMessageComponent.Margin margin) {
            this.margin = margin;
            return this;
        }

        public Builder setSize(FlexMessageComponent.Size size) {
            this.size = size;
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

        public Builder setWrap(Boolean bool) {
            this.wrap = bool;
            return this;
        }

        public Builder setMaxLines(int i) {
            this.maxLines = i;
            return this;
        }

        public Builder setWeight(FlexMessageComponent.Weight weight) {
            this.weight = weight;
            return this;
        }

        public Builder setColor(String str) {
            this.color = str;
            return this;
        }

        public Builder setAction(Action action) {
            this.action = action;
            return this;
        }

        public FlexTextComponent build() {
            return new FlexTextComponent(this);
        }
    }
}
