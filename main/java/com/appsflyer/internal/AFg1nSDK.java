package com.appsflyer.internal;

/* loaded from: classes.dex */
public final class AFg1nSDK extends AFa1pSDK {
    @Override // com.appsflyer.internal.AFa1pSDK
    /* renamed from: e */
    public final boolean mo61e() {
        return false;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AFg1nSDK(android.content.Context r6) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = com.appsflyer.internal.AFg1oSDK.AFInAppEventParameterName
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            com.appsflyer.AppsFlyerLib r3 = com.appsflyer.AppsFlyerLib.getInstance()
            java.lang.String r3 = r3.getHostPrefix()
            r4 = 0
            r2[r4] = r3
            com.appsflyer.internal.AFb1tSDK r3 = com.appsflyer.internal.AFb1tSDK.valueOf()
            java.lang.String r3 = r3.getHostName()
            r4 = 1
            r2[r4] = r3
            java.lang.String r1 = java.lang.String.format(r1, r2)
            r0.append(r1)
            java.lang.String r6 = r6.getPackageName()
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            java.lang.String r1 = "Register"
            r5.<init>(r1, r6, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1nSDK.<init>(android.content.Context):void");
    }

    @Override // com.appsflyer.internal.AFa1pSDK
    public final AFf1zSDK AFKeystoreWrapper() {
        return AFf1zSDK.REGISTER;
    }
}
