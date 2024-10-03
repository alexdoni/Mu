package com.linecorp.linesdk.message.template;

import com.linecorp.linesdk.utils.JSONUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ConfirmLayoutTemplate extends LayoutTemplate {
    private List<ClickActionForTemplateMessage> actions;
    private String text;

    public ConfirmLayoutTemplate(String str, List<ClickActionForTemplateMessage> list) {
        super(Type.CONFIRM);
        this.text = str;
        this.actions = list;
    }

    @Override // com.linecorp.linesdk.message.template.LayoutTemplate, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        JSONUtils.put(jsonObject, "text", this.text);
        JSONUtils.putArray(jsonObject, "actions", this.actions);
        return jsonObject;
    }
}
