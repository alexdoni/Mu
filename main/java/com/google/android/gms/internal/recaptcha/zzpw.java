package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public class zzpw extends zzpv {
    protected final byte[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzpw(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpy
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzpy) || zzd() != ((zzpy) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (obj instanceof zzpw) {
            zzpw zzpwVar = (zzpw) obj;
            int zzl = zzl();
            int zzl2 = zzpwVar.zzl();
            if (zzl != 0 && zzl2 != 0 && zzl != zzl2) {
                return false;
            }
            int zzd = zzd();
            if (zzd > zzpwVar.zzd()) {
                int zzd2 = zzd();
                StringBuilder sb = new StringBuilder(40);
                sb.append("Length too large: ");
                sb.append(zzd);
                sb.append(zzd2);
                throw new IllegalArgumentException(sb.toString());
            }
            if (zzd > zzpwVar.zzd()) {
                int zzd3 = zzpwVar.zzd();
                StringBuilder sb2 = new StringBuilder(59);
                sb2.append("Ran off end of other: 0, ");
                sb2.append(zzd);
                sb2.append(", ");
                sb2.append(zzd3);
                throw new IllegalArgumentException(sb2.toString());
            }
            if (zzpwVar instanceof zzpw) {
                byte[] bArr = this.zza;
                byte[] bArr2 = zzpwVar.zza;
                zzpwVar.zzc();
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
            return zzpwVar.zzg(0, zzd).equals(zzg(0, zzd));
        }
        return obj.equals(this);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpy
    public byte zza(int i) {
        return this.zza[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzpy
    public byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpy
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpy
    protected void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpy
    protected final int zzf(int i, int i2, int i3) {
        return zzrp.zzd(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpy
    public final zzpy zzg(int i, int i2) {
        int zzk = zzk(0, i2, zzd());
        return zzk == 0 ? zzpy.zzb : new zzps(this.zza, 0, zzk);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpy
    protected final String zzh(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzpy
    public final void zzi(zzpn zzpnVar) throws IOException {
        zzpnVar.zza(this.zza, 0, zzd());
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpy
    public final boolean zzj() {
        return zzug.zzf(this.zza, 0, zzd());
    }
}
