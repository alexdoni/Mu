package com.appsflyer.internal;

import android.util.Base64;
import com.appsflyer.AFLogger;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public final class AFg1ySDK {
    public final AFd1tSDK AFKeystoreWrapper;
    public long valueOf;
    public long values;
    public AFh1hSDK AFInAppEventType = null;
    public AFh1hSDK AFInAppEventParameterName = AFKeystoreWrapper();

    public AFg1ySDK(AFd1tSDK aFd1tSDK) {
        this.AFKeystoreWrapper = aFd1tSDK;
        this.values = aFd1tSDK.AFKeystoreWrapper("af_rc_timestamp", 0L);
        this.valueOf = aFd1tSDK.AFKeystoreWrapper("af_rc_max_age", 0L);
    }

    private AFh1hSDK AFKeystoreWrapper() {
        String AFKeystoreWrapper = this.AFKeystoreWrapper.AFKeystoreWrapper("af_remote_config", (String) null);
        if (AFKeystoreWrapper == null) {
            AFLogger.INSTANCE.m96d(AFg1gSDK.REMOTE_CONTROL, "No configuration found in cache");
            return null;
        }
        try {
            return new AFh1hSDK(new String(Base64.decode(AFKeystoreWrapper, 2), Charset.defaultCharset()));
        } catch (Exception e) {
            AFLogger.INSTANCE.m98e(AFg1gSDK.REMOTE_CONTROL, "Error reading malformed configuration from cache, requires fetching from remote again", e, true);
            return null;
        }
    }
}
