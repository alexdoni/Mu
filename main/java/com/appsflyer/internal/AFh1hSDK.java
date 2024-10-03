package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AFh1hSDK {
    private final boolean AFInAppEventParameterName;
    public final AFh1gSDK AFInAppEventType;
    public final String AFKeystoreWrapper;
    public final AFh1mSDK valueOf;
    public final String values;

    public AFh1hSDK(String str) throws JSONException {
        AFh1mSDK aFh1mSDK;
        if (str == null) {
            throw new JSONException("Failed to parse remote configuration JSON: originalJson is null");
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("ver");
            this.values = string;
            this.AFInAppEventParameterName = jSONObject.optBoolean("test_mode");
            this.AFKeystoreWrapper = str;
            if (string.startsWith("default")) {
                aFh1mSDK = AFh1mSDK.DEFAULT;
            } else {
                aFh1mSDK = AFh1mSDK.CUSTOM;
            }
            this.valueOf = aFh1mSDK;
            JSONObject optJSONObject = jSONObject.optJSONObject("features");
            this.AFInAppEventType = optJSONObject != null ? new AFh1gSDK(optJSONObject) : null;
        } catch (JSONException e) {
            AFLogger.afErrorLogForExcManagerOnly("Error in RC config parsing", e);
            throw new JSONException("Failed to parse remote configuration JSON");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AFh1hSDK aFh1hSDK = (AFh1hSDK) obj;
        if (this.AFInAppEventParameterName == aFh1hSDK.AFInAppEventParameterName && this.values.equals(aFh1hSDK.values)) {
            return this.AFKeystoreWrapper.equals(aFh1hSDK.AFKeystoreWrapper);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = ((((this.AFInAppEventParameterName ? 1 : 0) * 31) + this.values.hashCode()) * 31) + this.AFKeystoreWrapper.hashCode();
        AFh1gSDK aFh1gSDK = this.AFInAppEventType;
        return aFh1gSDK != null ? (hashCode * 31) + aFh1gSDK.hashCode() : hashCode;
    }
}
