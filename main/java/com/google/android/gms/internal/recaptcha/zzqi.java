package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzqi extends zzqf {
    private final OutputStream zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzqi(OutputStream outputStream, int i) {
        super(i);
        if (outputStream != null) {
            this.zzf = outputStream;
            return;
        }
        throw new NullPointerException("out");
    }

    private final void zzR() throws IOException {
        this.zzf.write(this.zza, 0, this.zzc);
        this.zzc = 0;
    }

    private final void zzS(int i) throws IOException {
        if (this.zzb - this.zzc < i) {
            zzR();
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzT() throws IOException {
        if (this.zzc > 0) {
            zzR();
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzU(byte b) throws IOException {
        if (this.zzc == this.zzb) {
            zzR();
        }
        zzc(b);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzV(int i, boolean z) throws IOException {
        zzS(11);
        zzf(i << 3);
        zzc(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzW(byte[] bArr, int i, int i2) throws IOException {
        zzv(i2);
        zzy(bArr, 0, i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj, com.google.android.gms.internal.recaptcha.zzpn
    public final void zza(byte[] bArr, int i, int i2) throws IOException {
        zzy(bArr, 0, i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzh(int i, zzpy zzpyVar) throws IOException {
        zzv((i << 3) | 2);
        zzi(zzpyVar);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzi(zzpy zzpyVar) throws IOException {
        zzv(zzpyVar.zzd());
        zzpyVar.zzi(this);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzj(int i, int i2) throws IOException {
        zzS(14);
        zzf((i << 3) | 5);
        zzd(i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzk(int i) throws IOException {
        zzS(4);
        zzd(i);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzl(int i, long j) throws IOException {
        zzS(18);
        zzf((i << 3) | 1);
        zze(j);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzm(long j) throws IOException {
        zzS(8);
        zze(j);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzn(int i, int i2) throws IOException {
        zzS(20);
        zzf(i << 3);
        if (i2 >= 0) {
            zzf(i2);
        } else {
            zzg(i2);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzo(int i) throws IOException {
        if (i >= 0) {
            zzv(i);
        } else {
            zzx(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzp(int i, zzsn zzsnVar, zzta zztaVar) throws IOException {
        zzv((i << 3) | 2);
        zzph zzphVar = (zzph) zzsnVar;
        int zzo = zzphVar.zzo();
        if (zzo == -1) {
            zzo = zztaVar.zza(zzphVar);
            zzphVar.zzr(zzo);
        }
        zzv(zzo);
        zztaVar.zzg(zzsnVar, this.zze);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzq(zzsn zzsnVar) throws IOException {
        zzv(zzsnVar.zzt());
        zzsnVar.zzM(this);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzr(int i, String str) throws IOException {
        zzv((i << 3) | 2);
        zzs(str);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzs(String str) throws IOException {
        int zzc;
        try {
            int length = str.length() * 3;
            int zzK = zzK(length);
            int i = zzK + length;
            int i2 = this.zzb;
            if (i <= i2) {
                if (i > i2 - this.zzc) {
                    zzR();
                }
                int zzK2 = zzK(str.length());
                int i3 = this.zzc;
                try {
                    if (zzK2 == zzK) {
                        int i4 = i3 + zzK2;
                        this.zzc = i4;
                        int zzb = zzug.zzb(str, this.zza, i4, this.zzb - i4);
                        this.zzc = i3;
                        zzc = (zzb - i3) - zzK2;
                        zzf(zzc);
                        this.zzc = zzb;
                    } else {
                        zzc = zzug.zzc(str);
                        zzf(zzc);
                        this.zzc = zzug.zzb(str, this.zza, this.zzc, zzc);
                    }
                    this.zzd += zzc;
                    return;
                } catch (zzuf e) {
                    this.zzd -= this.zzc - i3;
                    this.zzc = i3;
                    throw e;
                } catch (ArrayIndexOutOfBoundsException e2) {
                    throw new zzqh(e2);
                }
            }
            byte[] bArr = new byte[length];
            int zzb2 = zzug.zzb(str, bArr, 0, length);
            zzv(zzb2);
            zzy(bArr, 0, zzb2);
        } catch (zzuf e3) {
            zzP(str, e3);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzt(int i, int i2) throws IOException {
        zzv((i << 3) | i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzu(int i, int i2) throws IOException {
        zzS(20);
        zzf(i << 3);
        zzf(i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzv(int i) throws IOException {
        zzS(5);
        zzf(i);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzw(int i, long j) throws IOException {
        zzS(20);
        zzf(i << 3);
        zzg(j);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzx(long j) throws IOException {
        zzS(10);
        zzg(j);
    }

    public final void zzy(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.zzb;
        int i4 = this.zzc;
        int i5 = i3 - i4;
        if (i5 >= i2) {
            System.arraycopy(bArr, 0, this.zza, i4, i2);
            this.zzc += i2;
            this.zzd += i2;
            return;
        }
        System.arraycopy(bArr, 0, this.zza, i4, i5);
        int i6 = i2 - i5;
        this.zzc = this.zzb;
        this.zzd += i5;
        zzR();
        if (i6 <= this.zzb) {
            System.arraycopy(bArr, i5, this.zza, 0, i6);
            this.zzc = i6;
        } else {
            this.zzf.write(bArr, i5, i6);
        }
        this.zzd += i6;
    }
}
