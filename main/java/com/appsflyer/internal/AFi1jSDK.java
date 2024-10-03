package com.appsflyer.internal;

import android.content.Context;
import com.facebook.share.internal.ShareConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/* loaded from: classes.dex */
public abstract class AFi1jSDK extends Observable {
    final Runnable AFInAppEventParameterName;
    public final String AFKeystoreWrapper;
    long registerClient;
    public final String unregisterClient;
    public final Map<String, Object> valueOf = new HashMap();

    /* renamed from: d */
    public AFa1tSDK f285d = AFa1tSDK.NOT_STARTED;

    /* loaded from: classes.dex */
    public enum AFa1tSDK {
        NOT_STARTED,
        STARTED,
        FINISHED
    }

    public abstract void valueOf(Context context);

    public AFi1jSDK(String str, String str2, Runnable runnable) {
        this.AFInAppEventParameterName = runnable;
        this.AFKeystoreWrapper = str2;
        this.unregisterClient = str;
    }

    /* renamed from: com.appsflyer.internal.AFi1jSDK$4 */
    /* loaded from: classes.dex */
    final class C07304 implements Observer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public C07304() {
        }

        @Override // java.util.Observer
        public final void update(Observable observable, Object obj) {
            AFi1jSDK.this.AFInAppEventParameterName.run();
        }
    }

    public final void AFKeystoreWrapper() {
        this.valueOf.put("source", this.AFKeystoreWrapper);
        this.valueOf.put(ShareConstants.MEDIA_TYPE, this.unregisterClient);
        this.valueOf.put("latency", Long.valueOf(System.currentTimeMillis() - this.registerClient));
        this.f285d = AFa1tSDK.FINISHED;
        setChanged();
        notifyObservers();
    }
}
