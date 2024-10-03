package com.appsflyer.internal;

import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFd1bSDK implements AFd1dSDK {
    private final AFd1gSDK AFKeystoreWrapper;

    public AFd1bSDK(AFd1gSDK aFd1gSDK) {
        Intrinsics.checkNotNullParameter(aFd1gSDK, "");
        this.AFKeystoreWrapper = aFd1gSDK;
    }

    @Override // com.appsflyer.internal.AFd1dSDK
    public final void valueOf(byte[] bArr, Map<String, String> map, int i) {
        Intrinsics.checkNotNullParameter(bArr, "");
        Intrinsics.checkNotNullParameter(bArr, "");
        if (new AFd1cSDK(bArr, map, CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE).values()) {
            this.AFKeystoreWrapper.AFInAppEventParameterName();
        }
    }
}
