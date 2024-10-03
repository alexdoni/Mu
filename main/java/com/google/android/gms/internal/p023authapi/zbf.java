package com.google.android.gms.internal.p023authapi;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-auth@@20.6.0 */
/* loaded from: classes2.dex */
final class zbf extends zbd {
    final /* synthetic */ zbg zba;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zbf(zbg zbgVar) {
        this.zba = zbgVar;
    }

    @Override // com.google.android.gms.internal.p023authapi.zbd, com.google.android.gms.internal.p023authapi.zbs
    public final void zbb(Status status, Credential credential) {
        this.zba.setResult((zbg) new zbe(status, credential));
    }

    @Override // com.google.android.gms.internal.p023authapi.zbd, com.google.android.gms.internal.p023authapi.zbs
    public final void zbc(Status status) {
        this.zba.setResult((zbg) new zbe(status, null));
    }
}
