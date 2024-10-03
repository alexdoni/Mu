package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.appsflyer.internal.components.network.http.exceptions.HttpException;
import com.appsflyer.internal.components.network.http.exceptions.ParsingException;
import com.appsflyer.internal.components.queue.exceptions.CreateHttpCallException;
import java.io.IOException;

/* loaded from: classes.dex */
public abstract class AFf1rSDK<Result> extends AFe1eSDK<AFe1hSDK<Result>> {
    public AFe1hSDK<Result> AFLogger;

    /* renamed from: d */
    protected final AFe1wSDK f234d;

    /* renamed from: e */
    private AFb1vSDK f235e;

    /* renamed from: i */
    private String f236i;
    public final AFg1xSDK registerClient;
    protected final AFb1bSDK unregisterClient;

    @Override // com.appsflyer.internal.AFe1eSDK
    public long AFInAppEventType() {
        return 60000L;
    }

    protected abstract AppsFlyerRequestListener registerClient();

    protected abstract boolean unregisterClient();

    /* renamed from: v */
    protected boolean mo91v() {
        return true;
    }

    protected abstract AFe1uSDK<Result> valueOf(String str);

    private AFf1rSDK(AFf1zSDK aFf1zSDK, AFf1zSDK[] aFf1zSDKArr, AFe1wSDK aFe1wSDK, AFg1xSDK aFg1xSDK, AFb1bSDK aFb1bSDK, AFb1vSDK aFb1vSDK, String str) {
        super(aFf1zSDK, aFf1zSDKArr, str);
        this.f234d = aFe1wSDK;
        this.registerClient = aFg1xSDK;
        this.unregisterClient = aFb1bSDK;
        this.f235e = aFb1vSDK;
    }

    public AFf1rSDK(AFf1zSDK aFf1zSDK, AFf1zSDK[] aFf1zSDKArr, AFd1mSDK aFd1mSDK, String str) {
        this(aFf1zSDK, aFf1zSDKArr, aFd1mSDK.AFKeystoreWrapper(), aFd1mSDK.force(), aFd1mSDK.mo78i(), aFd1mSDK.afRDLog(), str);
    }

    public AFf1rSDK(AFf1zSDK aFf1zSDK, AFf1zSDK[] aFf1zSDKArr, AFd1mSDK aFd1mSDK, String str, String str2) {
        this(aFf1zSDK, aFf1zSDKArr, aFd1mSDK.AFKeystoreWrapper(), aFd1mSDK.force(), aFd1mSDK.mo78i(), aFd1mSDK.afRDLog(), str);
        this.f236i = str2;
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final void valueOf() {
        String str;
        super.valueOf();
        if (!unregisterClient() || (str = this.registerClient.registerClient) == null || str.trim().isEmpty()) {
            return;
        }
        AFe1uSDK<Result> valueOf = valueOf(str);
        if (valueOf != null) {
            AFInAppEventParameterName(valueOf.AFKeystoreWrapper);
        } else {
            AFLogger.afErrorLogForExcManagerOnly("Failed to create a cached HTTP call", new CreateHttpCallException("createHttpCall returned null"));
        }
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public AFe1dSDK values() throws Exception {
        if (mo91v() && this.registerClient.AFKeystoreWrapper()) {
            AppsFlyerRequestListener registerClient = registerClient();
            if (registerClient != null) {
                registerClient.onError(11, "Skipping event because 'isStopped' is true");
            }
            throw new AFf1wSDK();
        }
        String str = this.registerClient.registerClient;
        if (str != null && !str.trim().isEmpty()) {
            AFe1uSDK<Result> valueOf = valueOf(str);
            if (valueOf == null) {
                AFLogger.afErrorLogForExcManagerOnly("Failed to create a cached HTTP call", new CreateHttpCallException("createHttpCall returned null"));
                return AFe1dSDK.FAILURE;
            }
            if (unregisterClient()) {
                AFInAppEventParameterName(valueOf.AFKeystoreWrapper);
            }
            AFe1hSDK<Result> AFInAppEventParameterName = valueOf.AFInAppEventParameterName();
            this.AFLogger = AFInAppEventParameterName;
            this.unregisterClient.AFInAppEventParameterName(valueOf.AFKeystoreWrapper.valueOf, AFInAppEventParameterName.getStatusCode(), AFInAppEventParameterName.getBody().toString());
            AppsFlyerRequestListener registerClient2 = registerClient();
            if (registerClient2 != null) {
                if (AFInAppEventParameterName.isSuccessful()) {
                    registerClient2.onSuccess();
                } else {
                    StringBuilder sb = new StringBuilder("Status code failure ");
                    sb.append(AFInAppEventParameterName.getStatusCode());
                    registerClient2.onError(50, sb.toString());
                }
            }
            if (AFInAppEventParameterName.isSuccessful()) {
                return AFe1dSDK.SUCCESS;
            }
            return AFe1dSDK.FAILURE;
        }
        AppsFlyerRequestListener registerClient3 = registerClient();
        if (registerClient3 != null) {
            registerClient3.onError(41, "No dev key");
        }
        throw new AFe1aSDK();
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public boolean AFInAppEventParameterName() {
        if (m85e() instanceof AFf1wSDK) {
            return false;
        }
        if (this.AFInAppEventType == AFe1dSDK.TIMEOUT) {
            return true;
        }
        Throwable m85e = m85e();
        return (m85e instanceof IOException) && !(m85e instanceof ParsingException);
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final void AFInAppEventType(Throwable th) {
        boolean z = !(th instanceof HttpException);
        if (th instanceof AFf1wSDK) {
            AFLogger.INSTANCE.m99e(AFg1gSDK.HTTP_CLIENT, "AppsFlyer SDK is stopped: the request was not sent to the server", th, true, false);
        } else {
            AFLogger.INSTANCE.m100e(AFg1gSDK.HTTP_CLIENT, "Error while sending request to server: ".concat(String.valueOf(th)), th, true, true, z);
        }
        AppsFlyerRequestListener registerClient = registerClient();
        if (registerClient != null) {
            String message = th.getMessage();
            if (message == null) {
                message = "";
            }
            registerClient.onError(40, message);
        }
    }

    private void AFInAppEventParameterName(AFe1oSDK aFe1oSDK) {
        String str = this.f236i;
        this.f236i = this.f235e.AFKeystoreWrapper(new AFb1qSDK(aFe1oSDK.valueOf, aFe1oSDK.values(), "6.13.1", this.AFInAppEventParameterName));
        if (str != null) {
            this.f235e.AFInAppEventParameterName(str);
        }
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public void AFKeystoreWrapper() {
        String str;
        if (this.AFInAppEventType != AFe1dSDK.SUCCESS) {
            if (AFInAppEventParameterName() || (str = this.f236i) == null) {
                return;
            }
            this.f235e.AFInAppEventParameterName(str);
            return;
        }
        String str2 = this.f236i;
        if (str2 != null) {
            this.f235e.AFInAppEventParameterName(str2);
        }
    }
}
