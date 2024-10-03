package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzqk implements zzuj {
    private final zzqj zza;

    private zzqk(zzqj zzqjVar) {
        zzrp.zzf(zzqjVar, "output");
        this.zza = zzqjVar;
        zzqjVar.zze = this;
    }

    public static zzqk zza(zzqj zzqjVar) {
        zzqk zzqkVar = zzqjVar.zze;
        return zzqkVar != null ? zzqkVar : new zzqk(zzqjVar);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzB(int i, int i2) throws IOException {
        this.zza.zzu(i, (i2 >> 31) ^ (i2 + i2));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzD(int i, long j) throws IOException {
        this.zza.zzw(i, (j >> 63) ^ (j + j));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzF(int i) throws IOException {
        this.zza.zzt(i, 3);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzG(int i, String str) throws IOException {
        this.zza.zzr(i, str);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzH(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzrw)) {
            while (i2 < list.size()) {
                this.zza.zzr(i, list.get(i2));
                i2++;
            }
            return;
        }
        zzrw zzrwVar = (zzrw) list;
        while (i2 < list.size()) {
            Object zzf = zzrwVar.zzf(i2);
            if (zzf instanceof String) {
                this.zza.zzr(i, (String) zzf);
            } else {
                this.zza.zzh(i, (zzpy) zzf);
            }
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzI(int i, int i2) throws IOException {
        this.zza.zzu(i, i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzK(int i, long j) throws IOException {
        this.zza.zzw(i, j);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzb(int i, boolean z) throws IOException {
        this.zza.zzV(i, z);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzd(int i, zzpy zzpyVar) throws IOException {
        this.zza.zzh(i, zzpyVar);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zze(int i, List<zzpy> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zzh(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzf(int i, double d) throws IOException {
        this.zza.zzl(i, Double.doubleToRawLongBits(d));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzh(int i) throws IOException {
        this.zza.zzt(i, 4);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzi(int i, int i2) throws IOException {
        this.zza.zzn(i, i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzk(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzm(int i, long j) throws IOException {
        this.zza.zzl(i, j);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzo(int i, float f) throws IOException {
        this.zza.zzj(i, Float.floatToRawIntBits(f));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzq(int i, Object obj, zzta zztaVar) throws IOException {
        zzqj zzqjVar = this.zza;
        zzqjVar.zzt(i, 3);
        zztaVar.zzg((zzsn) obj, zzqjVar.zze);
        zzqjVar.zzt(i, 4);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzr(int i, int i2) throws IOException {
        this.zza.zzn(i, i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzt(int i, long j) throws IOException {
        this.zza.zzw(i, j);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final <K, V> void zzv(int i, zzsf<K, V> zzsfVar, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zza.zzt(i, 2);
            this.zza.zzv(zzsg.zzb(zzsfVar, entry.getKey(), entry.getValue()));
            zzsg.zze(this.zza, zzsfVar, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzw(int i, Object obj, zzta zztaVar) throws IOException {
        this.zza.zzp(i, (zzsn) obj, zztaVar);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzx(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzz(int i, long j) throws IOException {
        this.zza.zzl(i, j);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzA(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzl(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).longValue();
            i3 += 8;
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            this.zza.zzm(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzC(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                zzqj zzqjVar = this.zza;
                int intValue = list.get(i2).intValue();
                zzqjVar.zzu(i, (intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            int intValue2 = list.get(i4).intValue();
            i3 += zzqj.zzK((intValue2 >> 31) ^ (intValue2 + intValue2));
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            zzqj zzqjVar2 = this.zza;
            int intValue3 = list.get(i2).intValue();
            zzqjVar2.zzv((intValue3 >> 31) ^ (intValue3 + intValue3));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzE(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                zzqj zzqjVar = this.zza;
                long longValue = list.get(i2).longValue();
                zzqjVar.zzw(i, (longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            long longValue2 = list.get(i4).longValue();
            i3 += zzqj.zzL((longValue2 >> 63) ^ (longValue2 + longValue2));
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            zzqj zzqjVar2 = this.zza;
            long longValue3 = list.get(i2).longValue();
            zzqjVar2.zzx((longValue3 >> 63) ^ (longValue3 + longValue3));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzJ(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzu(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzqj.zzK(list.get(i4).intValue());
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            this.zza.zzv(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzL(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzw(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzqj.zzL(list.get(i4).longValue());
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            this.zza.zzx(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzc(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzV(i, list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).booleanValue();
            i3++;
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            this.zza.zzU(list.get(i2).booleanValue() ? (byte) 1 : (byte) 0);
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzn(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzqj.zzD(list.get(i4).intValue());
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            this.zza.zzo(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzl(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzj(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).intValue();
            i3 += 4;
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            this.zza.zzk(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzl(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).longValue();
            i3 += 8;
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            this.zza.zzm(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzs(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzn(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzqj.zzD(list.get(i4).intValue());
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            this.zza.zzo(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzu(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzw(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzqj.zzL(list.get(i4).longValue());
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            this.zza.zzx(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzy(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzj(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).intValue();
            i3 += 4;
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            this.zza.zzk(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzl(i, Double.doubleToRawLongBits(list.get(i2).doubleValue()));
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).doubleValue();
            i3 += 8;
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            this.zza.zzm(Double.doubleToRawLongBits(list.get(i2).doubleValue()));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzuj
    public final void zzp(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzj(i, Float.floatToRawIntBits(list.get(i2).floatValue()));
                i2++;
            }
            return;
        }
        this.zza.zzt(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).floatValue();
            i3 += 4;
        }
        this.zza.zzv(i3);
        while (i2 < list.size()) {
            this.zza.zzk(Float.floatToRawIntBits(list.get(i2).floatValue()));
            i2++;
        }
    }
}
