package com.appsflyer.internal.components.network.http.exceptions;

import com.appsflyer.internal.AFe1hSDK;
import java.io.IOException;

/* loaded from: classes.dex */
public class ParsingException extends IOException {
    private final AFe1hSDK<String> AFInAppEventType;

    public ParsingException(String str, Throwable th, AFe1hSDK<String> aFe1hSDK) {
        super(str, th);
        this.AFInAppEventType = aFe1hSDK;
    }

    public AFe1hSDK<String> getRawResponse() {
        return this.AFInAppEventType;
    }
}
