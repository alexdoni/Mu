package com.linecorp.linesdk.message.flex.component;

import com.linecorp.linesdk.message.flex.component.FlexMessageComponent;
import com.linecorp.linesdk.utils.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FlexSpacerComponent extends FlexMessageComponent {
    private FlexMessageComponent.Size size;

    public FlexSpacerComponent() {
        super(FlexMessageComponent.Type.SPACER);
    }

    public void setSize(FlexMessageComponent.Size size) {
        this.size = size;
    }

    @Override // com.linecorp.linesdk.message.flex.component.FlexMessageComponent, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        JSONUtils.put(jsonObject, "size", this.size);
        return jsonObject;
    }
}
