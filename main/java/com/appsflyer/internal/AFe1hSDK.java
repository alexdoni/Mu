package com.appsflyer.internal;

import com.appsflyer.internal.components.network.http.ResponseNetwork;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AFe1hSDK<Body> implements ResponseNetwork<Body> {
    public final AFe1mSDK AFInAppEventParameterName;
    private final Body AFInAppEventType;
    final Map<String, List<String>> AFKeystoreWrapper;
    final boolean valueOf;
    final int values;

    public AFe1hSDK(Body body, int i, boolean z, Map<String, List<String>> map, AFe1mSDK aFe1mSDK) {
        this.AFInAppEventType = body;
        this.values = i;
        this.valueOf = z;
        this.AFKeystoreWrapper = new HashMap(map);
        this.AFInAppEventParameterName = aFe1mSDK;
    }

    @Override // com.appsflyer.internal.components.network.http.ResponseNetwork
    public Body getBody() {
        return this.AFInAppEventType;
    }

    @Override // com.appsflyer.internal.components.network.http.ResponseNetwork
    public int getStatusCode() {
        return this.values;
    }

    @Override // com.appsflyer.internal.components.network.http.ResponseNetwork
    public boolean isSuccessful() {
        return this.valueOf;
    }

    @Override // com.appsflyer.internal.components.network.http.ResponseNetwork
    public List<String> getHeaderField(String str) {
        for (String str2 : this.AFKeystoreWrapper.keySet()) {
            if (str2 != null && str2.equalsIgnoreCase(str)) {
                return this.AFKeystoreWrapper.get(str2);
            }
        }
        return null;
    }

    public final String values(String str) {
        List<String> headerField = getHeaderField(str);
        if (headerField == null || headerField.isEmpty()) {
            return null;
        }
        Iterator<String> it = headerField.iterator();
        StringBuilder sb = new StringBuilder(it.next());
        while (it.hasNext()) {
            sb.append(", ");
            sb.append(it.next());
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AFe1hSDK aFe1hSDK = (AFe1hSDK) obj;
        if (this.values == aFe1hSDK.values && this.valueOf == aFe1hSDK.valueOf && this.AFInAppEventType.equals(aFe1hSDK.AFInAppEventType) && this.AFKeystoreWrapper.equals(aFe1hSDK.AFKeystoreWrapper)) {
            return this.AFInAppEventParameterName.equals(aFe1hSDK.AFInAppEventParameterName);
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.AFInAppEventType.hashCode() * 31) + this.values) * 31) + (this.valueOf ? 1 : 0)) * 31) + this.AFKeystoreWrapper.hashCode()) * 31) + this.AFInAppEventParameterName.hashCode();
    }
}
