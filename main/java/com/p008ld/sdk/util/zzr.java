package com.p008ld.sdk.util;

import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OssCredentialsProvider.kt */
/* loaded from: classes2.dex */
public final class zzr extends OSSFederationCredentialProvider {
    private OSSFederationToken zza;

    public final void zza(String accessKeyId, String accessKeySecret, String securityToken, String expiration) {
        Intrinsics.checkNotNullParameter(accessKeyId, "accessKeyId");
        Intrinsics.checkNotNullParameter(accessKeySecret, "accessKeySecret");
        Intrinsics.checkNotNullParameter(securityToken, "securityToken");
        Intrinsics.checkNotNullParameter(expiration, "expiration");
        this.zza = new OSSFederationToken(accessKeyId, accessKeySecret, securityToken, expiration);
    }

    @Override // com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider, com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
    public OSSFederationToken getFederationToken() {
        OSSFederationToken oSSFederationToken = this.zza;
        Intrinsics.checkNotNull(oSSFederationToken);
        return oSSFederationToken;
    }
}
