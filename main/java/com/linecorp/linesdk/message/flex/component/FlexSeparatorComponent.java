package com.linecorp.linesdk.message.flex.component;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.linecorp.linesdk.message.flex.component.FlexMessageComponent;
import com.linecorp.linesdk.utils.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FlexSeparatorComponent extends FlexMessageComponent {
    private String color;
    private FlexMessageComponent.Margin margin;

    public FlexSeparatorComponent() {
        super(FlexMessageComponent.Type.SEPARATOR);
    }

    private FlexSeparatorComponent(Builder builder) {
        this();
        this.margin = builder.margin;
        this.color = builder.color;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.linecorp.linesdk.message.flex.component.FlexMessageComponent, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        JSONUtils.put(jsonObject, "margin", this.margin);
        JSONUtils.put(jsonObject, TypedValues.Custom.S_COLOR, this.color);
        return jsonObject;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private String color;
        private FlexMessageComponent.Margin margin;

        private Builder() {
        }

        public Builder setMargin(FlexMessageComponent.Margin margin) {
            this.margin = margin;
            return this;
        }

        public Builder setColor(String str) {
            this.color = str;
            return this;
        }

        public FlexSeparatorComponent build() {
            return new FlexSeparatorComponent(this);
        }
    }
}
