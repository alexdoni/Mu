package com.linecorp.linesdk.message;

import com.facebook.appevents.integrity.IntegrityManager;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LocationMessage extends MessageData {
    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final String title;

    public LocationMessage(String str, String str2, Double d, Double d2) {
        this.title = str;
        this.address = str2;
        this.latitude = d;
        this.longitude = d2;
    }

    @Override // com.linecorp.linesdk.message.MessageData
    public Type getType() {
        return Type.LOCATION;
    }

    @Override // com.linecorp.linesdk.message.MessageData, com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put("title", this.title);
        jsonObject.put(IntegrityManager.INTEGRITY_TYPE_ADDRESS, this.address);
        jsonObject.put("latitude", this.latitude);
        jsonObject.put("longitude", this.longitude);
        return jsonObject;
    }
}
