package com.google.android.gms.internal.recaptcha;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zztt extends zztr<zzts, zzts> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final /* bridge */ /* synthetic */ int zza(zzts zztsVar) {
        return zztsVar.zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final /* bridge */ /* synthetic */ int zzb(zzts zztsVar) {
        return zztsVar.zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final /* bridge */ /* synthetic */ zzts zzc(Object obj) {
        zzrg zzrgVar = (zzrg) obj;
        zzts zztsVar = zzrgVar.zzc;
        if (zztsVar != zzts.zzc()) {
            return zztsVar;
        }
        zzts zze = zzts.zze();
        zzrgVar.zzc = zze;
        return zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final /* bridge */ /* synthetic */ zzts zzd(Object obj) {
        return ((zzrg) obj).zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final /* bridge */ /* synthetic */ zzts zze(zzts zztsVar, zzts zztsVar2) {
        zzts zztsVar3 = zztsVar2;
        return zztsVar3.equals(zzts.zzc()) ? zztsVar : zzts.zzd(zztsVar, zztsVar3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final /* bridge */ /* synthetic */ zzts zzf() {
        return zzts.zze();
    }

    @Override // com.google.android.gms.internal.recaptcha.zztr
    final /* bridge */ /* synthetic */ zzts zzg(zzts zztsVar) {
        zzts zztsVar2 = zztsVar;
        zztsVar2.zzf();
        return zztsVar2;
    }

    @Override // com.google.android.gms.internal.recaptcha.zztr
    final /* bridge */ /* synthetic */ void zzh(zzts zztsVar, int i, int i2) {
        zztsVar.zzh((i << 3) | 5, Integer.valueOf(i2));
    }

    @Override // com.google.android.gms.internal.recaptcha.zztr
    final /* bridge */ /* synthetic */ void zzi(zzts zztsVar, int i, long j) {
        zztsVar.zzh((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.recaptcha.zztr
    final /* bridge */ /* synthetic */ void zzj(zzts zztsVar, int i, zzts zztsVar2) {
        zztsVar.zzh((i << 3) | 3, zztsVar2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final /* bridge */ /* synthetic */ void zzk(zzts zztsVar, int i, zzpy zzpyVar) {
        zztsVar.zzh((i << 3) | 2, zzpyVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final /* bridge */ /* synthetic */ void zzl(zzts zztsVar, int i, long j) {
        zztsVar.zzh(i << 3, Long.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final void zzm(Object obj) {
        ((zzrg) obj).zzc.zzf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final /* bridge */ /* synthetic */ void zzn(Object obj, zzts zztsVar) {
        ((zzrg) obj).zzc = zztsVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final /* bridge */ /* synthetic */ void zzo(Object obj, zzts zztsVar) {
        ((zzrg) obj).zzc = zztsVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final /* bridge */ /* synthetic */ void zzp(zzts zztsVar, zzuj zzujVar) throws IOException {
        zztsVar.zzi(zzujVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zztr
    public final boolean zzr(zzsz zzszVar) {
        return false;
    }
}
