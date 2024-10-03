package com.appsflyer.internal;

import kotlin.Metadata;

@Metadata(m1394d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u001f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0002X\u0007¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!"}, m1395d2 = {"Lcom/appsflyer/internal/AFg1gSDK;", "", "", "values", "Ljava/lang/String;", "p0", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "AFKeystoreWrapper", "AFInAppEventParameterName", "AFInAppEventType", "valueOf", "AFLogger", "registerClient", "e", "d", "unregisterClient", "w", "getLevel", "i", "v", "AFLogger$LogLevel", "AFVersionDeclaration", "afLogForce", "afErrorLogForExcManagerOnly", "afInfoLog", "onInstallConversionFailureNative", "init", "force", "afRDLog", "afDebugLog", "afWarnLog", "afErrorLog", "afVerboseLog"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public enum AFg1gSDK {
    PRIVACY_SANDBOX("Privacy Sandbox"),
    OTHER("Other"),
    HTTP_CLIENT("HTTP Client"),
    QUEUE("Queue"),
    CACHE("Cache"),
    REMOTE_CONTROL("CFG"),
    DDL("DDL"),
    REFERRER("Referrer"),
    CROSS_PROMOTION("Cross Promotion"),
    EXCEPTION_MANAGER("Exception Manager"),
    ATTRIBUTION("Attribution"),
    RD("RD"),
    ENGAGEMENT("Engagement"),
    ANTI_FRAUD("Anti Fraud"),
    PUBLIC_API("Public API"),
    AD_REVENUE("Ad Revenue"),
    SDK_SETTERS("Setter"),
    PREDICT("Predict"),
    DEVICE_DATA("Device Data"),
    SECURITY("Security"),
    GENERAL("General"),
    PREINSTALL("Preinstall"),
    UNINSTALL("Uninstall"),
    PURCHASE_VALIDATION("Purchase Validation"),
    DMA("DMA"),
    PROXY("PROXY");

    final String values;

    AFg1gSDK(String str) {
        this.values = str;
    }
}
