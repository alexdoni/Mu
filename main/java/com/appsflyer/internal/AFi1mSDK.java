package com.appsflyer.internal;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFi1mSDK extends AFi1rSDK {
    private final ExecutorService AFInAppEventType;
    private final Runnable AFLogger;

    /* renamed from: e */
    private final AFi1kSDK f286e;

    /* renamed from: v */
    private String f287v;
    private final AFd1sSDK values;

    /* loaded from: classes.dex */
    public /* synthetic */ class AFa1tSDK {
        public static final /* synthetic */ int[] AFInAppEventType;

        static {
            int[] iArr = new int[AFi1kSDK.values().length];
            iArr[AFi1kSDK.FACEBOOK.ordinal()] = 1;
            iArr[AFi1kSDK.INSTAGRAM.ordinal()] = 2;
            AFInAppEventType = iArr;
        }
    }

    @Override // com.appsflyer.internal.AFi1jSDK
    public final void valueOf(final Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        if (!AFKeystoreWrapper(context)) {
            this.AFLogger.run();
        } else {
            this.AFInAppEventType.execute(new Runnable() { // from class: com.appsflyer.internal.AFi1mSDK$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AFi1mSDK.AFInAppEventType(AFi1mSDK.this, context);
                }
            });
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0070, code lost:
    
        if (r0 == null) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean AFKeystoreWrapper(android.content.Context r6) {
        /*
            r5 = this;
            boolean r0 = r5.AFInAppEventType()
            r1 = 0
            if (r0 != 0) goto Ld
            java.lang.String r6 = "[MetaReferrer]: Referrer collection disallowed by counter."
            com.appsflyer.AFLogger.afDebugLog(r6)
            return r1
        Ld:
            com.appsflyer.internal.AFd1sSDK r0 = r5.values
            java.lang.String r2 = "com.facebook.sdk.ApplicationId"
            java.lang.String r0 = r0.AFInAppEventParameterName(r2)
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r3 = 1
            if (r2 == 0) goto L24
            int r2 = r2.length()
            if (r2 != 0) goto L22
            goto L24
        L22:
            r2 = r1
            goto L25
        L24:
            r2 = r3
        L25:
            r4 = 0
            if (r2 == 0) goto L2e
            java.lang.String r0 = "[MetaReferrer]: Facebook app id Manifest metadata is not found."
            com.appsflyer.AFLogger.afDebugLog(r0)
            r0 = r4
        L2e:
            if (r0 != 0) goto L73
            com.appsflyer.internal.AFd1sSDK r0 = r5.values
            java.lang.String r2 = "facebook_application_id"
            java.lang.String r0 = r0.AFInAppEventType(r2)
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L46
            int r2 = r2.length()
            if (r2 != 0) goto L44
            goto L46
        L44:
            r2 = r1
            goto L47
        L46:
            r2 = r3
        L47:
            if (r2 == 0) goto L4f
            java.lang.String r0 = "[MetaReferrer]: Facebook app id string resource is not found."
            com.appsflyer.AFLogger.afDebugLog(r0)
            r0 = r4
        L4f:
            if (r0 != 0) goto L73
            com.appsflyer.internal.AFd1sSDK r0 = r5.values
            java.lang.String r2 = "com.appsflyer.FacebookApplicationId"
            java.lang.String r0 = r0.AFInAppEventParameterName(r2)
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L67
            int r2 = r2.length()
            if (r2 != 0) goto L65
            goto L67
        L65:
            r2 = r1
            goto L68
        L67:
            r2 = r3
        L68:
            if (r2 == 0) goto L70
            java.lang.String r0 = "[MetaReferrer]: AF Facebook app id Manifest metadata is not found."
            com.appsflyer.AFLogger.afDebugLog(r0)
            r0 = r4
        L70:
            if (r0 != 0) goto L73
            goto L74
        L73:
            r4 = r0
        L74:
            r5.f287v = r4
            if (r4 != 0) goto L7e
            java.lang.String r6 = "[MetaReferrer]: Referrer collection disallowed by missing Facebook app id."
            com.appsflyer.AFLogger.afDebugLog(r6)
            return r1
        L7e:
            boolean r6 = r5.AFInAppEventType(r6)
            if (r6 != 0) goto L8a
            java.lang.String r6 = "[MetaReferrer]: Referrer collection disallowed by missing content providers."
            com.appsflyer.AFLogger.afDebugLog(r6)
            return r1
        L8a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFi1mSDK.AFKeystoreWrapper(android.content.Context):boolean");
    }

    private final boolean AFInAppEventType(Context context) {
        int i = AFa1tSDK.AFInAppEventType[this.f286e.ordinal()];
        if (i == 1) {
            return values(context);
        }
        if (i == 2) {
            return AFInAppEventParameterName(context);
        }
        throw new NoWhenBranchMatchedException();
    }

    private static boolean values(Context context) {
        return context.getPackageManager().resolveContentProvider("com.facebook.katana.provider.InstallReferrerProvider", 0) != null;
    }

    private static boolean AFInAppEventParameterName(Context context) {
        return context.getPackageManager().resolveContentProvider("com.instagram.contentprovider.InstallReferrerProvider", 0) != null;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AFi1mSDK(com.appsflyer.internal.AFd1sSDK r3, java.util.concurrent.ExecutorService r4, com.appsflyer.internal.AFi1kSDK r5, java.lang.Runnable r6, java.lang.Runnable r7) {
        /*
            r2 = this;
            java.lang.String r0 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            int[] r0 = com.appsflyer.internal.AFi1nSDK.AFa1vSDK.AFInAppEventType
            int r1 = r5.ordinal()
            r0 = r0[r1]
            r1 = 1
            if (r0 == r1) goto L28
            r1 = 2
            if (r0 != r1) goto L22
            java.lang.String r0 = "instagram"
            goto L2a
        L22:
            kotlin.NoWhenBranchMatchedException r3 = new kotlin.NoWhenBranchMatchedException
            r3.<init>()
            throw r3
        L28:
            java.lang.String r0 = "facebook"
        L2a:
            java.lang.String r1 = "app"
            r2.<init>(r1, r0, r3, r6)
            r2.values = r3
            r2.AFInAppEventType = r4
            r2.f286e = r5
            r2.AFLogger = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFi1mSDK.<init>(com.appsflyer.internal.AFd1sSDK, java.util.concurrent.ExecutorService, com.appsflyer.internal.AFi1kSDK, java.lang.Runnable, java.lang.Runnable):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x01a5, code lost:
    
        if (r6 != null) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x01a8, code lost:
    
        if (r6 != null) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0191, code lost:
    
        if (r6 != null) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0194, code lost:
    
        if (r6 != null) goto L84;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void AFInAppEventType(com.appsflyer.internal.AFi1mSDK r18, android.content.Context r19) {
        /*
            Method dump skipped, instructions count: 496
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFi1mSDK.AFInAppEventType(com.appsflyer.internal.AFi1mSDK, android.content.Context):void");
    }
}
