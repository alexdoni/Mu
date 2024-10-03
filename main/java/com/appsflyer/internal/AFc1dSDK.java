package com.appsflyer.internal;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class AFc1dSDK {
    public final Map<String, Object> AFKeystoreWrapper = new HashMap();
    public Map<String, Object> valueOf = new HashMap();

    public final void AFKeystoreWrapper(Map<String, Object> map) {
        if (!this.AFKeystoreWrapper.isEmpty()) {
            map.put("partner_data", this.AFKeystoreWrapper);
        }
        if (this.valueOf.isEmpty()) {
            return;
        }
        AFb1tSDK.values(map).put("partner_data", this.valueOf);
        this.valueOf = new HashMap();
    }
}
