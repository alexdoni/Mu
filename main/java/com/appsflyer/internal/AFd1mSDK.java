package com.appsflyer.internal;

import com.appsflyer.PurchaseHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
public interface AFd1mSDK {
    ExecutorService AFInAppEventParameterName();

    AFd1sSDK AFInAppEventType();

    AFe1wSDK AFKeystoreWrapper();

    AFg1cSDK AFLogger();

    AFd1xSDK AFLogger$LogLevel();

    AFi1ySDK AFVersionDeclaration();

    AFc1oSDK AppsFlyer2dXConversionCallback();

    AFi1qSDK afDebugLog();

    AFc1hSDK afErrorLog();

    AFd1qSDK afErrorLogForExcManagerOnly();

    AFe1fSDK afInfoLog();

    AFg1fSDK afLogForce();

    AFb1vSDK afRDLog();

    AFe1gSDK afVerboseLog();

    AFc1uSDK afWarnLog();

    /* renamed from: d */
    AFf1bSDK mo76d();

    /* renamed from: e */
    PurchaseHandler mo77e();

    AFg1xSDK force();

    AFh1aSDK getLevel();

    /* renamed from: i */
    AFb1bSDK mo78i();

    AFd1iSDK onAppOpenAttributionNative();

    AFg1vSDK onInstallConversionFailureNative();

    AFi1dSDK registerClient();

    AFg1qSDK unregisterClient();

    /* renamed from: v */
    AFi1iSDK mo79v();

    ScheduledExecutorService valueOf();

    AFd1tSDK values();

    /* renamed from: w */
    AFd1kSDK mo80w();
}
