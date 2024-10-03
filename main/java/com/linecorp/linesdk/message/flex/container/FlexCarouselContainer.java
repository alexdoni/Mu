package com.linecorp.linesdk.message.flex.container;

import com.linecorp.linesdk.message.flex.container.FlexMessageContainer;
import com.linecorp.linesdk.utils.JSONUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FlexCarouselContainer extends FlexMessageContainer {
    private List<FlexBubbleContainer> contents;

    private FlexCarouselContainer() {
        super(FlexMessageContainer.Type.CAROUSEL);
    }

    public FlexCarouselContainer(List<FlexBubbleContainer> list) {
        this();
        this.contents = list;
    }

    @Override // com.linecorp.linesdk.message.flex.container.FlexMessageContainer, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        JSONUtils.putArray(jsonObject, "contents", this.contents);
        return jsonObject;
    }
}
