package com.linecorp.linesdk.message.flex.component;

import com.facebook.internal.NativeProtocol;
import com.linecorp.linesdk.message.flex.action.Action;
import com.linecorp.linesdk.message.flex.component.FlexMessageComponent;
import com.linecorp.linesdk.utils.JSONUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FlexBoxComponent extends FlexMessageComponent {
    private Action action;
    private List<FlexMessageComponent> contents;
    private int flex;
    private FlexMessageComponent.Layout layout;
    private FlexMessageComponent.Margin margin;
    private FlexMessageComponent.Margin spacing;

    private FlexBoxComponent() {
        super(FlexMessageComponent.Type.BOX);
    }

    private FlexBoxComponent(Builder builder) {
        this();
        this.layout = builder.layout;
        this.contents = builder.contents;
        this.flex = builder.flex;
        this.spacing = builder.spacing;
        this.margin = builder.margin;
        this.action = builder.action;
    }

    public static Builder newBuilder(FlexMessageComponent.Layout layout, List<FlexMessageComponent> list) {
        return new Builder(layout, list);
    }

    @Override // com.linecorp.linesdk.message.flex.component.FlexMessageComponent, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        JSONUtils.put(jsonObject, "layout", this.layout);
        JSONUtils.putArray(jsonObject, "contents", this.contents);
        JSONUtils.put(jsonObject, "spacing", this.spacing);
        JSONUtils.put(jsonObject, "margin", this.margin);
        JSONUtils.put(jsonObject, NativeProtocol.WEB_DIALOG_ACTION, this.action);
        int i = this.flex;
        if (i != -1) {
            jsonObject.put("flex", i);
        }
        return jsonObject;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private Action action;
        private List<FlexMessageComponent> contents;
        private int flex;
        private FlexMessageComponent.Layout layout;
        private FlexMessageComponent.Margin margin;
        private FlexMessageComponent.Margin spacing;

        private Builder(FlexMessageComponent.Layout layout, List<FlexMessageComponent> list) {
            this.flex = -1;
            this.layout = layout;
            this.contents = list;
        }

        public Builder setFlex(int i) {
            this.flex = i;
            return this;
        }

        public Builder setSpacing(FlexMessageComponent.Margin margin) {
            this.spacing = margin;
            return this;
        }

        public Builder setMargin(FlexMessageComponent.Margin margin) {
            this.margin = margin;
            return this;
        }

        public Builder setAction(Action action) {
            this.action = action;
            return this;
        }

        public FlexBoxComponent build() {
            return new FlexBoxComponent(this);
        }
    }
}
