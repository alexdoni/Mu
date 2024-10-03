package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.internal.components.network.http.exceptions.HttpException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;

/* loaded from: classes.dex */
public final class AFf1fSDK extends AFe1eSDK<AFf1gSDK> {
    private final AFf1eSDK AFLogger;
    private final AFg1xSDK afInfoLog;

    /* renamed from: d */
    public AFf1gSDK f207d;

    /* renamed from: e */
    public final AFf1cSDK f208e;
    private final AFe1wSDK force;

    /* renamed from: i */
    private final AFg1ySDK f209i;
    private final AFd1sSDK registerClient;
    public AFh1eSDK unregisterClient;

    /* renamed from: v */
    private final AFf1aSDK f210v;

    /* renamed from: w */
    private final String f211w;

    @Override // com.appsflyer.internal.AFe1eSDK
    public final boolean AFInAppEventParameterName() {
        return false;
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final long AFInAppEventType() {
        return 1500L;
    }

    public AFf1fSDK(AFf1eSDK aFf1eSDK, AFd1sSDK aFd1sSDK, AFg1xSDK aFg1xSDK, AFg1ySDK aFg1ySDK, AFe1wSDK aFe1wSDK, AFf1aSDK aFf1aSDK, String str, AFf1cSDK aFf1cSDK) {
        super(AFf1zSDK.RC_CDN, new AFf1zSDK[0], "UpdateRemoteConfiguration");
        this.f207d = null;
        this.AFLogger = aFf1eSDK;
        this.registerClient = aFd1sSDK;
        this.afInfoLog = aFg1xSDK;
        this.f209i = aFg1ySDK;
        this.force = aFe1wSDK;
        this.f210v = aFf1aSDK;
        this.f211w = str;
        this.f208e = aFf1cSDK;
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final AFe1dSDK values() throws Exception {
        try {
            AFf1gSDK unregisterClient = unregisterClient();
            this.f207d = unregisterClient;
            if (unregisterClient == AFf1gSDK.FAILURE) {
                return AFe1dSDK.FAILURE;
            }
            return AFe1dSDK.SUCCESS;
        } catch (SocketTimeoutException unused) {
            this.f207d = AFf1gSDK.FAILURE;
            return AFe1dSDK.TIMEOUT;
        } catch (InterruptedIOException e) {
            e = e;
            AFLogger.afErrorLogForExcManagerOnly("RC update config failed", e);
            this.f207d = AFf1gSDK.FAILURE;
            return AFe1dSDK.TIMEOUT;
        } catch (InterruptedException e2) {
            e = e2;
            AFLogger.afErrorLogForExcManagerOnly("RC update config failed", e);
            this.f207d = AFf1gSDK.FAILURE;
            return AFe1dSDK.TIMEOUT;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0065 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.appsflyer.internal.AFf1gSDK unregisterClient() throws java.lang.InterruptedException, java.io.InterruptedIOException {
        /*
            Method dump skipped, instructions count: 560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFf1fSDK.unregisterClient():com.appsflyer.internal.AFf1gSDK");
    }

    private void valueOf(String str, long j, AFh1bSDK aFh1bSDK, String str2, AFe1hSDK<AFh1hSDK> aFe1hSDK) {
        valueOf(str, j, aFe1hSDK, aFe1hSDK != null ? aFe1hSDK.getBody() : null, aFh1bSDK, str2 != null ? str2 : null, null);
    }

    private void valueOf(String str, long j, AFe1hSDK<?> aFe1hSDK, AFh1hSDK aFh1hSDK, AFh1bSDK aFh1bSDK, String str2, Throwable th) {
        long j2;
        int i;
        Throwable th2;
        long j3;
        if (aFe1hSDK != null) {
            j2 = aFe1hSDK.AFInAppEventParameterName.AFKeystoreWrapper;
            i = aFe1hSDK.getStatusCode();
        } else {
            j2 = 0;
            i = 0;
        }
        int i2 = i;
        if (th instanceof HttpException) {
            th2 = th.getCause();
            j3 = ((HttpException) th).getMetrics().AFKeystoreWrapper;
        } else {
            th2 = th;
            j3 = j2;
        }
        this.unregisterClient = new AFh1eSDK(aFh1hSDK != null ? aFh1hSDK.values : null, str, j3, System.currentTimeMillis() - j, i2, aFh1bSDK, str2, th2);
    }
}
