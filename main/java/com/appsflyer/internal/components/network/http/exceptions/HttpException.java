package com.appsflyer.internal.components.network.http.exceptions;

import com.appsflyer.internal.AFe1mSDK;
import java.io.IOException;

/* loaded from: classes.dex */
public class HttpException extends IOException {
    private final AFe1mSDK values;

    public HttpException(Throwable th, AFe1mSDK aFe1mSDK) {
        super(th.getMessage(), th);
        this.values = aFe1mSDK;
    }

    public AFe1mSDK getMetrics() {
        return this.values;
    }
}
