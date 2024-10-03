package com.appsflyer.internal;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AFh1nSDK {
    public final String AFInAppEventParameterName;
    public final int AFInAppEventType;
    public final int AFKeystoreWrapper;
    public final long values;

    public AFh1nSDK(String str, int i, int i2, long j) {
        this.AFInAppEventParameterName = str;
        this.AFInAppEventType = i;
        this.AFKeystoreWrapper = i2;
        this.values = j;
    }

    public final String AFInAppEventParameterName() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sdk_ver", this.AFInAppEventParameterName);
            jSONObject.put("min", this.AFInAppEventType);
            jSONObject.put("expire", this.AFKeystoreWrapper);
            jSONObject.put("ttl", this.values);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public final int hashCode() {
        String str = this.AFInAppEventParameterName;
        return ((((((str != null ? str.hashCode() : 0) * 31) + this.AFInAppEventType) * 31) + this.AFKeystoreWrapper) * 31) + ((int) this.values);
    }

    public final boolean equals(Object obj) {
        String str;
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            AFh1nSDK aFh1nSDK = (AFh1nSDK) obj;
            if (this.AFInAppEventType == aFh1nSDK.AFInAppEventType && this.AFKeystoreWrapper == aFh1nSDK.AFKeystoreWrapper && this.values == aFh1nSDK.values && (str = this.AFInAppEventParameterName) != null && str.equals(aFh1nSDK.AFInAppEventParameterName)) {
                return true;
            }
        }
        return false;
    }
}
