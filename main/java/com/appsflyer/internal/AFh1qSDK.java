package com.appsflyer.internal;

/* loaded from: classes.dex */
public final class AFh1qSDK extends AFa1pSDK {

    /* renamed from: w */
    public final AFf1zSDK f275w;

    @Deprecated
    public AFh1qSDK() {
        this.f275w = null;
    }

    public AFh1qSDK(String str, byte[] bArr, String str2, AFf1zSDK aFf1zSDK) {
        super(null, str, Boolean.FALSE);
        this.AFInAppEventParameterName = str2;
        values(bArr);
        this.f275w = aFf1zSDK;
    }

    @Override // com.appsflyer.internal.AFa1pSDK
    public final AFf1zSDK AFKeystoreWrapper() {
        AFf1zSDK aFf1zSDK = this.f275w;
        return aFf1zSDK != null ? aFf1zSDK : AFf1zSDK.CACHED_EVENT;
    }
}
