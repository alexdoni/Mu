package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.internal.components.network.http.exceptions.ParsingException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;

/* loaded from: classes.dex */
public final class AFe1uSDK<ResponseBody> {
    private final AFe1sSDK AFInAppEventParameterName;
    private final AtomicBoolean AFInAppEventType = new AtomicBoolean(false);
    public final AFe1oSDK AFKeystoreWrapper;
    private final ExecutorService valueOf;
    private final AFe1jSDK<ResponseBody> values;

    public AFe1uSDK(AFe1oSDK aFe1oSDK, ExecutorService executorService, AFe1sSDK aFe1sSDK, AFe1jSDK<ResponseBody> aFe1jSDK) {
        this.AFKeystoreWrapper = aFe1oSDK;
        this.valueOf = executorService;
        this.AFInAppEventParameterName = aFe1sSDK;
        this.values = aFe1jSDK;
    }

    public final AFe1hSDK<ResponseBody> AFInAppEventParameterName() throws IOException {
        if (!this.AFInAppEventType.getAndSet(true)) {
            AFe1hSDK<String> AFKeystoreWrapper = this.AFInAppEventParameterName.AFKeystoreWrapper(this.AFKeystoreWrapper);
            try {
                return new AFe1hSDK<>(this.values.AFKeystoreWrapper(AFKeystoreWrapper.getBody()), AFKeystoreWrapper.values, AFKeystoreWrapper.valueOf, AFKeystoreWrapper.AFKeystoreWrapper, AFKeystoreWrapper.AFInAppEventParameterName);
            } catch (JSONException e) {
                AFLogger.afErrorLogForExcManagerOnly("could not parse raw response - execute", e);
                throw new ParsingException(e.getMessage(), e, AFKeystoreWrapper);
            }
        }
        throw new IllegalStateException("Http call is already executed");
    }
}
