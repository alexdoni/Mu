package com.google.android.gms.internal.recaptcha;

import java.math.BigInteger;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzci {
    private zzvf zza = zzvf.zzk();
    private zzvg zzb = zzvh.zzd();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzvg zza(String str) {
        if (this.zza.zzi().zzd() == 0) {
            return this.zzb;
        }
        zzvg zzvgVar = this.zzb;
        zzln zzb = zzlq.zzb();
        byte[][] bArr = {this.zza.zzi().zzs(), str.getBytes()};
        int i = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            i += bArr[i2].length;
        }
        byte[] bArr2 = new byte[i];
        int i3 = 0;
        for (int i4 = 0; i4 < 2; i4++) {
            byte[] bArr3 = bArr[i4];
            int length = bArr3.length;
            System.arraycopy(bArr3, 0, bArr2, i3, length);
            i3 += length;
        }
        zzvgVar.zza(zzpy.zzn(zzb.zza(bArr2).zze()));
        return zzvgVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzvg zzb(int i) {
        zzvg zzvgVar = this.zzb;
        zzvgVar.zzb((this.zza.zzd() * i) + this.zza.zze());
        return zzvgVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzvg zzc(zzkn<String> zzknVar) {
        List<zzpy> zzl = this.zza.zzl();
        byte[] bArr = new byte[(zzl.size() / 8) + (zzl.size() % 8 == 0 ? 0 : 1)];
        for (int i = 0; i < zzl.size(); i++) {
            if (zzknVar.contains(zzco.zzb(zzl.get(i).zzs()))) {
                int i2 = i / 8;
                bArr[i2] = (byte) (bArr[i2] | ((byte) (1 << (i % 8))));
            }
        }
        zzvg zzvgVar = this.zzb;
        zzvgVar.zzc(zzpy.zzn(bArr));
        return zzvgVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzvg zzd(int i) {
        if (this.zza.zzg() == 0) {
            return this.zzb;
        }
        BigInteger valueOf = BigInteger.valueOf(i);
        BigInteger valueOf2 = BigInteger.valueOf(this.zza.zzf());
        BigInteger valueOf3 = BigInteger.valueOf(this.zza.zzg());
        zzvg zzvgVar = this.zzb;
        zzvgVar.zzd(valueOf.modPow(valueOf2, valueOf3).intValue());
        return zzvgVar;
    }

    public final zzvh zze() {
        return this.zzb.zzk();
    }

    public final void zzf(zzvf zzvfVar) {
        this.zza = zzvfVar;
        this.zzb = zzvh.zzd();
    }
}
