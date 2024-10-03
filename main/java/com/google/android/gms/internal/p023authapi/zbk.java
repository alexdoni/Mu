package com.google.android.gms.internal.p023authapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-auth@@20.6.0 */
/* loaded from: classes2.dex */
final class zbk extends zbd {
    private final BaseImplementation.ResultHolder zba;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zbk(BaseImplementation.ResultHolder resultHolder) {
        this.zba = resultHolder;
    }

    @Override // com.google.android.gms.internal.p023authapi.zbd, com.google.android.gms.internal.p023authapi.zbs
    public final void zbc(Status status) {
        this.zba.setResult(status);
    }
}
