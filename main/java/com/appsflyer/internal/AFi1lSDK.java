package com.appsflyer.internal;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import com.appsflyer.AFLogger;

/* loaded from: classes.dex */
public final class AFi1lSDK extends AFi1jSDK {
    final ProviderInfo AFInAppEventType;
    private final AFd1mSDK values;

    public AFi1lSDK(ProviderInfo providerInfo, Runnable runnable, AFd1mSDK aFd1mSDK) {
        super("af_referrer", providerInfo.authority, runnable);
        this.values = aFd1mSDK;
        this.AFInAppEventType = providerInfo;
    }

    @Override // com.appsflyer.internal.AFi1jSDK
    public final void valueOf(final Context context) {
        this.values.AFInAppEventParameterName().execute(new Runnable() { // from class: com.appsflyer.internal.AFi1lSDK.4
            /* JADX WARN: Code restructure failed: missing block: B:37:0x008e, code lost:
            
                if (android.os.Build.VERSION.SDK_INT < 24) goto L25;
             */
            /* JADX WARN: Code restructure failed: missing block: B:38:0x0090, code lost:
            
                r0.close();
             */
            /* JADX WARN: Code restructure failed: missing block: B:39:0x0094, code lost:
            
                r0.release();
             */
            /* JADX WARN: Code restructure failed: missing block: B:43:0x007f, code lost:
            
                if (android.os.Build.VERSION.SDK_INT < 24) goto L25;
             */
            /* JADX WARN: Code restructure failed: missing block: B:47:0x0070, code lost:
            
                if (android.os.Build.VERSION.SDK_INT < 24) goto L25;
             */
            /* JADX WARN: Removed duplicated region for block: B:11:0x00aa  */
            /* JADX WARN: Removed duplicated region for block: B:25:0x00f1  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void run() {
                /*
                    Method dump skipped, instructions count: 311
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFi1lSDK.RunnableC07314.run():void");
            }
        });
    }

    public static ContentProviderClient values(Context context, Uri uri) {
        try {
            return context.getContentResolver().acquireUnstableContentProviderClient(uri);
        } catch (SecurityException e) {
            AFLogger.INSTANCE.m98e(AFg1gSDK.PREINSTALL, "Failed to acquire unstable content providerClient due to SecurityException", e, false);
            return null;
        } catch (Throwable th) {
            AFLogger.INSTANCE.m98e(AFg1gSDK.PREINSTALL, "Failed to acquire unstable content providerClient due to unexpected throwable", th, false);
            return null;
        }
    }
}
