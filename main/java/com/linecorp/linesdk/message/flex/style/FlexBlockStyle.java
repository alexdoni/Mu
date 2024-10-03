package com.linecorp.linesdk.message.flex.style;

import com.linecorp.linesdk.message.Jsonable;
import com.linecorp.linesdk.utils.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FlexBlockStyle implements Jsonable {
    private String backgroundColor;
    private boolean separator;
    private String separatorColor;

    public FlexBlockStyle(String str, boolean z, String str2) {
        this.backgroundColor = str;
        this.separator = z;
        this.separatorColor = str2;
    }

    @Override // com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONUtils.put(jSONObject, "backgroundColor", this.backgroundColor);
        JSONUtils.put(jSONObject, "separator", Boolean.valueOf(this.separator));
        JSONUtils.put(jSONObject, "separatorColor", this.separatorColor);
        return jSONObject;
    }
}
