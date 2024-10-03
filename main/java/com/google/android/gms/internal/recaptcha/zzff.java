package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzff implements zzec<OutputStream> {
    private zzeq[] zza;

    private zzff() {
    }

    public static zzff zzb() {
        return new zzff();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzec
    public final /* bridge */ /* synthetic */ OutputStream zza(zzeb zzebVar) throws IOException {
        List<OutputStream> zzd = zzebVar.zzd(zzebVar.zzb().zzj(zzebVar.zza()));
        zzeq[] zzeqVarArr = this.zza;
        if (zzeqVarArr != null) {
            zzeqVarArr[0].zza(zzd);
        }
        return zzd.get(0);
    }

    public final zzff zzc(zzeq... zzeqVarArr) {
        this.zza = zzeqVarArr;
        return this;
    }
}
