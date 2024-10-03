package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.internal.AFi1jSDK;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class AFi1iSDK {
    public final List<AFi1jSDK> AFInAppEventParameterName = new ArrayList();
    public final AFd1mSDK AFInAppEventType;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void values() {
    }

    public AFi1iSDK(AFd1mSDK aFd1mSDK) {
        this.AFInAppEventType = aFd1mSDK;
    }

    public final synchronized void valueOf(AFi1jSDK aFi1jSDK) {
        this.AFInAppEventParameterName.add(aFi1jSDK);
    }

    public final synchronized AFi1jSDK[] AFKeystoreWrapper() {
        return (AFi1jSDK[]) this.AFInAppEventParameterName.toArray(new AFi1jSDK[0]);
    }

    public final void values(final Runnable runnable) {
        valueOf(new AFi1mSDK(this.AFInAppEventType.AFInAppEventType(), this.AFInAppEventType.AFInAppEventParameterName(), AFi1kSDK.FACEBOOK, runnable, new Runnable() { // from class: com.appsflyer.internal.AFi1iSDK$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AFi1iSDK.this.valueOf(runnable);
            }
        }));
    }

    public final AFi1sSDK AFKeystoreWrapper(final Runnable runnable) {
        return new AFi1sSDK(new Runnable() { // from class: com.appsflyer.internal.AFi1iSDK$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AFi1iSDK.this.AFInAppEventParameterName(runnable);
            }
        }, this.AFInAppEventType.AFInAppEventParameterName(), this.AFInAppEventType.AFInAppEventType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void AFInAppEventParameterName(final Runnable runnable) {
        AFj1zSDK.AFKeystoreWrapper(this.AFInAppEventType.valueOf(), new Runnable() { // from class: com.appsflyer.internal.AFi1iSDK$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AFi1iSDK.this.AFInAppEventType(runnable);
            }
        }, 0L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void AFInAppEventType(Runnable runnable) {
        try {
            if (AFInAppEventType(new AFh1wSDK())) {
                runnable.run();
            }
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
        }
    }

    public final boolean AFInAppEventType(AFa1pSDK aFa1pSDK) {
        int AFInAppEventParameterName = this.AFInAppEventType.AFInAppEventType().AFKeystoreWrapper.AFInAppEventParameterName("appsFlyerCount", 0);
        return (!this.AFInAppEventType.values().values(AppsFlyerProperties.NEW_REFERRER_SENT) && AFInAppEventParameterName == 1) || (AFInAppEventParameterName == 1 && !(aFa1pSDK instanceof AFh1wSDK));
    }

    public final Runnable AFInAppEventType(final AFi1sSDK aFi1sSDK, final Runnable runnable) {
        return new Runnable() { // from class: com.appsflyer.internal.AFi1iSDK$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AFi1iSDK.this.AFInAppEventParameterName(aFi1sSDK, runnable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void AFInAppEventParameterName(AFi1sSDK aFi1sSDK, Runnable runnable) {
        AFd1tSDK values = this.AFInAppEventType.values();
        int AFInAppEventParameterName = this.AFInAppEventType.AFInAppEventType().AFKeystoreWrapper.AFInAppEventParameterName("appsFlyerCount", 0);
        boolean values2 = values.values(AppsFlyerProperties.NEW_REFERRER_SENT);
        boolean z = aFi1sSDK.f285d == AFi1jSDK.AFa1tSDK.NOT_STARTED;
        if (AFInAppEventParameterName == 1) {
            if (z || values2) {
                runnable.run();
            }
        }
    }

    public final boolean valueOf() {
        return this.AFInAppEventType.AFInAppEventType().values("AF_PREINSTALL_DISABLED");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void valueOf(Runnable runnable) {
        AFi1mSDK aFi1mSDK = new AFi1mSDK(this.AFInAppEventType.AFInAppEventType(), this.AFInAppEventType.AFInAppEventParameterName(), AFi1kSDK.INSTAGRAM, runnable, new Runnable() { // from class: com.appsflyer.internal.AFi1iSDK$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                AFi1iSDK.values();
            }
        });
        valueOf(aFi1mSDK);
        aFi1mSDK.valueOf(this.AFInAppEventType.mo80w().valueOf);
    }
}
