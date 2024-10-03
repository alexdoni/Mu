package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzmw implements zznf {
    final /* synthetic */ zzmp zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmw(zzmp zzmpVar) {
        this.zza = zzmpVar;
    }

    @Override // com.google.android.gms.measurement.internal.zznf
    public final void zza(String str, String str2, Bundle bundle) {
        zzhf zzhfVar;
        zzhf zzhfVar2;
        if (TextUtils.isEmpty(str)) {
            zzhfVar = this.zza.zzm;
            if (zzhfVar != null) {
                zzhfVar2 = this.zza.zzm;
                zzhfVar2.zzj().zzg().zza("AppId not known when logging event", str2);
                return;
            }
            return;
        }
        this.zza.zzl().zzb(new zzmv(this, str, str2, bundle));
    }
}
