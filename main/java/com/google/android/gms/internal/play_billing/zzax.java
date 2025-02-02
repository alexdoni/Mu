package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes2.dex */
public class zzax extends zzaw {
    protected final byte[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzax(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzba) || zzd() != ((zzba) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (obj instanceof zzax) {
            zzax zzaxVar = (zzax) obj;
            int zzk = zzk();
            int zzk2 = zzaxVar.zzk();
            if (zzk != 0 && zzk2 != 0 && zzk != zzk2) {
                return false;
            }
            int zzd = zzd();
            if (zzd > zzaxVar.zzd()) {
                throw new IllegalArgumentException("Length too large: " + zzd + zzd());
            }
            if (zzd > zzaxVar.zzd()) {
                throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzaxVar.zzd());
            }
            if (zzaxVar instanceof zzax) {
                byte[] bArr = this.zza;
                byte[] bArr2 = zzaxVar.zza;
                zzaxVar.zzc();
                int i = 0;
                int i2 = 0;
                while (i < zzd) {
                    if (bArr[i] != bArr2[i2]) {
                        return false;
                    }
                    i++;
                    i2++;
                }
                return true;
            }
            return zzaxVar.zzf(0, zzd).equals(zzf(0, zzd));
        }
        return obj.equals(this);
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public byte zza(int i) {
        return this.zza[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzba
    public byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    protected final int zze(int i, int i2, int i3) {
        return zzcg.zzb(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final zzba zzf(int i, int i2) {
        int zzj = zzj(0, i2, zzd());
        return zzj == 0 ? zzba.zzb : new zzau(this.zza, 0, zzj);
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    protected final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzba
    public final void zzh(zzaq zzaqVar) throws IOException {
        ((zzbf) zzaqVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final boolean zzi() {
        return zzev.zze(this.zza, 0, zzd());
    }
}
