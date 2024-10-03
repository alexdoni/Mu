package com.appsflyer.internal;

import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import com.appsflyer.AFLogger;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public abstract class AFc1bSDK<T> {
    private final String[] AFInAppEventParameterName;
    public final Context AFInAppEventType;
    public final AFd1mSDK AFKeystoreWrapper;
    public final FutureTask<T> valueOf = new FutureTask<>(new Callable<T>() { // from class: com.appsflyer.internal.AFc1bSDK.4
        @Override // java.util.concurrent.Callable
        public final T call() {
            if (AFc1bSDK.this.AFKeystoreWrapper()) {
                return (T) AFc1bSDK.this.AFInAppEventParameterName();
            }
            return null;
        }
    });
    public final String values;

    protected abstract T AFInAppEventParameterName();

    public AFc1bSDK(Context context, AFd1mSDK aFd1mSDK, String str, String... strArr) {
        this.AFInAppEventType = context;
        this.values = str;
        this.AFInAppEventParameterName = strArr;
        this.AFKeystoreWrapper = aFd1mSDK;
    }

    public T valueOf() {
        try {
            return this.valueOf.get(500L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e = e;
            AFLogger.afErrorLog(e.getMessage(), e, false, true);
            return null;
        } catch (ExecutionException e2) {
            e = e2;
            AFLogger.afErrorLog(e.getMessage(), e, false, true);
            return null;
        } catch (TimeoutException e3) {
            AFLogger.afErrorLog(e3.getMessage(), e3, false, false);
            return null;
        }
    }

    public final boolean AFKeystoreWrapper() {
        try {
            ProviderInfo resolveContentProvider = this.AFInAppEventType.getPackageManager().resolveContentProvider(this.values, 128);
            if (resolveContentProvider != null) {
                if (Arrays.asList(this.AFInAppEventParameterName).contains(AFb1uSDK.AFInAppEventParameterName(this.AFInAppEventType.getPackageManager(), ((PackageItemInfo) resolveContentProvider).packageName))) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException | CertificateException e) {
            AFLogger.afErrorLog(e.getMessage(), e, false, true);
            return false;
        }
    }
}
