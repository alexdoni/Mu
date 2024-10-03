package com.appsflyer.internal;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.appsflyer.AFLogger;
import com.appsflyer.internal.AFi1jSDK;
import com.appsflyer.internal.AFi1sSDK;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class AFi1sSDK extends AFi1rSDK {
    private final ExecutorService AFInAppEventType;
    public final Map<String, Object> values;

    public AFi1sSDK(Runnable runnable, ExecutorService executorService, AFd1sSDK aFd1sSDK) {
        super("store", "google", aFd1sSDK, runnable);
        this.values = new HashMap();
        this.AFInAppEventType = executorService;
    }

    private boolean AFInAppEventType(Context context) {
        if (!AFInAppEventType()) {
            return false;
        }
        try {
            Class.forName("com.android.installreferrer.api.InstallReferrerClient");
            if (AFb1uSDK.AFInAppEventParameterName(context, "com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE")) {
                AFLogger.INSTANCE.m96d(AFg1gSDK.REFERRER, "Install referrer is allowed");
                return true;
            }
            AFLogger.INSTANCE.m96d(AFg1gSDK.REFERRER, "Install referrer is not allowed");
            return false;
        } catch (ClassNotFoundException e) {
            AFLogger.afErrorLogForExcManagerOnly("InstallReferrerClient not found", e);
            AFLogger.INSTANCE.m102v(AFg1gSDK.REFERRER, "Class com.android.installreferrer.api.InstallReferrerClient not found");
            return false;
        } catch (Throwable th) {
            AFLogger.INSTANCE.m97e(AFg1gSDK.REFERRER, "An error occurred while trying to verify manifest : ".concat("com.android.installreferrer.api.InstallReferrerClient"), th);
            return false;
        }
    }

    @Override // com.appsflyer.internal.AFi1jSDK
    public final void valueOf(Context context) {
        if (AFInAppEventType(context)) {
            this.registerClient = System.currentTimeMillis();
            this.f285d = AFi1jSDK.AFa1tSDK.STARTED;
            addObserver(new AFi1jSDK.C07304());
            try {
                InstallReferrerClient build = InstallReferrerClient.newBuilder(context).build();
                AFLogger.INSTANCE.m96d(AFg1gSDK.REFERRER, "Connecting to Install Referrer Library...");
                build.startConnection(new C07335(build, context));
            } catch (Throwable th) {
                AFLogger.INSTANCE.m97e(AFg1gSDK.REFERRER, "referrerClient -> startConnection", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.appsflyer.internal.AFi1sSDK$5 */
    /* loaded from: classes.dex */
    public final class C07335 implements InstallReferrerStateListener {
        final /* synthetic */ Context val$context;
        final /* synthetic */ InstallReferrerClient val$referrerClient;

        C07335(InstallReferrerClient installReferrerClient, Context context) {
            this.val$referrerClient = installReferrerClient;
            this.val$context = context;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onInstallReferrerSetupFinished$0$com-appsflyer-internal-AFi1sSDK$5 */
        public /* synthetic */ void m117x210c3f03(InstallReferrerClient installReferrerClient, Context context, int i) {
            AFi1sSDK.this.AFKeystoreWrapper(installReferrerClient, context, i);
        }

        @Override // com.android.installreferrer.api.InstallReferrerStateListener
        public final void onInstallReferrerSetupFinished(final int i) {
            ExecutorService executorService = AFi1sSDK.this.AFInAppEventType;
            final InstallReferrerClient installReferrerClient = this.val$referrerClient;
            final Context context = this.val$context;
            executorService.execute(new Runnable() { // from class: com.appsflyer.internal.AFi1sSDK$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AFi1sSDK.C07335.this.m117x210c3f03(installReferrerClient, context, i);
                }
            });
        }

        @Override // com.android.installreferrer.api.InstallReferrerStateListener
        public final void onInstallReferrerServiceDisconnected() {
            AFLogger.INSTANCE.m96d(AFg1gSDK.REFERRER, "Install Referrer service disconnected");
        }
    }

    protected final void AFKeystoreWrapper(InstallReferrerClient installReferrerClient, Context context, int i) {
        this.values.put("code", String.valueOf(i));
        this.valueOf.put("api_ver", Long.valueOf(AFb1uSDK.values(context, "com.android.vending")));
        this.valueOf.put("api_ver_name", AFb1uSDK.AFKeystoreWrapper(context, "com.android.vending"));
        if (i == -1) {
            AFLogger.INSTANCE.m103w(AFg1gSDK.REFERRER, "InstallReferrer SERVICE_DISCONNECTED");
            this.valueOf.put("response", "SERVICE_DISCONNECTED");
        } else if (i == 0) {
            this.valueOf.put("response", "OK");
            try {
                AFLogger.INSTANCE.m96d(AFg1gSDK.REFERRER, "InstallReferrer connected");
                if (installReferrerClient.isReady()) {
                    ReferrerDetails installReferrer = installReferrerClient.getInstallReferrer();
                    String installReferrer2 = installReferrer.getInstallReferrer();
                    if (installReferrer2 != null) {
                        this.values.put("val", installReferrer2);
                        this.valueOf.put("referrer", installReferrer2);
                    }
                    long referrerClickTimestampSeconds = installReferrer.getReferrerClickTimestampSeconds();
                    this.values.put("clk", Long.toString(referrerClickTimestampSeconds));
                    this.valueOf.put("click_ts", Long.valueOf(referrerClickTimestampSeconds));
                    long installBeginTimestampSeconds = installReferrer.getInstallBeginTimestampSeconds();
                    this.values.put("install", Long.toString(installBeginTimestampSeconds));
                    this.valueOf.put("install_begin_ts", Long.valueOf(installBeginTimestampSeconds));
                    HashMap hashMap = new HashMap();
                    try {
                        boolean googlePlayInstantParam = installReferrer.getGooglePlayInstantParam();
                        this.values.put("instant", Boolean.valueOf(googlePlayInstantParam));
                        hashMap.put("instant", Boolean.valueOf(googlePlayInstantParam));
                    } catch (NoSuchMethodError e) {
                        AFLogger.afErrorLogForExcManagerOnly("getGooglePlayInstantParam not exist", e);
                    }
                    try {
                        hashMap.put("click_server_ts", Long.valueOf(installReferrer.getReferrerClickTimestampServerSeconds()));
                        hashMap.put("install_begin_server_ts", Long.valueOf(installReferrer.getInstallBeginTimestampServerSeconds()));
                        hashMap.put("install_version", installReferrer.getInstallVersion());
                    } catch (NoSuchMethodError e2) {
                        AFLogger.INSTANCE.m99e(AFg1gSDK.REFERRER, "some method not exist", e2, false, false);
                    }
                    if (!hashMap.isEmpty()) {
                        this.valueOf.put("google_custom", hashMap);
                    }
                    installReferrerClient.endConnection();
                } else {
                    AFLogger.INSTANCE.m103w(AFg1gSDK.REFERRER, "ReferrerClient: InstallReferrer is not ready");
                    this.values.put(NotificationCompat.CATEGORY_ERROR, "ReferrerClient: InstallReferrer is not ready");
                }
            } catch (Throwable th) {
                AFLogger aFLogger = AFLogger.INSTANCE;
                AFg1gSDK aFg1gSDK = AFg1gSDK.REFERRER;
                StringBuilder sb = new StringBuilder("Failed to get install referrer: ");
                sb.append(th.getMessage());
                aFLogger.m103w(aFg1gSDK, sb.toString());
                this.values.put(NotificationCompat.CATEGORY_ERROR, th.getMessage());
                AFLogger.INSTANCE.m99e(AFg1gSDK.REFERRER, "Failed to get install referrer", th, false, false);
            }
        } else if (i == 1) {
            this.valueOf.put("response", "SERVICE_UNAVAILABLE");
            AFLogger.INSTANCE.m103w(AFg1gSDK.REFERRER, "InstallReferrer not supported");
        } else if (i == 2) {
            AFLogger.INSTANCE.m103w(AFg1gSDK.REFERRER, "InstallReferrer FEATURE_NOT_SUPPORTED");
            this.valueOf.put("response", "FEATURE_NOT_SUPPORTED");
        } else if (i == 3) {
            AFLogger.INSTANCE.m103w(AFg1gSDK.REFERRER, "InstallReferrer DEVELOPER_ERROR");
            this.valueOf.put("response", "DEVELOPER_ERROR");
        } else {
            AFLogger.INSTANCE.m103w(AFg1gSDK.REFERRER, "responseCode not found.");
        }
        AFLogger.INSTANCE.m96d(AFg1gSDK.REFERRER, "Install Referrer collected locally");
        AFKeystoreWrapper();
    }
}
