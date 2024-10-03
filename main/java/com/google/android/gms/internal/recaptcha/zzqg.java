package com.google.android.gms.internal.recaptcha;

import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzqg extends zzqj {
    private final byte[] zza;
    private final int zzb;
    private int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzqg(byte[] bArr, int i, int i2) {
        super(null);
        if (bArr != null) {
            int length = bArr.length;
            if (((length - i2) | i2) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(i2)));
            }
            this.zza = bArr;
            this.zzc = 0;
            this.zzb = i2;
            return;
        }
        throw new NullPointerException("buffer");
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzT() {
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzV(int i, boolean z) throws IOException {
        zzv(i << 3);
        zzU(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzW(byte[] bArr, int i, int i2) throws IOException {
        zzv(i2);
        zze(bArr, 0, i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj, com.google.android.gms.internal.recaptcha.zzpn
    public final void zza(byte[] bArr, int i, int i2) throws IOException {
        zze(bArr, 0, i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final int zzb() {
        return this.zzb - this.zzc;
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
        zzv((i << 3) | 5);
        zzk(i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzk(int i) throws IOException {
        try {
            byte[] bArr = this.zza;
            int i2 = this.zzc;
            int i3 = i2 + 1;
            bArr[i2] = (byte) (i & 255);
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((i >> 8) & 255);
            int i5 = i4 + 1;
            bArr[i4] = (byte) ((i >> 16) & 255);
            this.zzc = i5 + 1;
            bArr[i5] = (byte) ((i >> 24) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new zzqh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1), e);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzl(int i, long j) throws IOException {
        zzv((i << 3) | 1);
        zzm(j);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzm(long j) throws IOException {
        try {
            byte[] bArr = this.zza;
            int i = this.zzc;
            int i2 = i + 1;
            bArr[i] = (byte) (((int) j) & 255);
            int i3 = i2 + 1;
            bArr[i2] = (byte) (((int) (j >> 8)) & 255);
            int i4 = i3 + 1;
            bArr[i3] = (byte) (((int) (j >> 16)) & 255);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (((int) (j >> 24)) & 255);
            int i6 = i5 + 1;
            bArr[i5] = (byte) (((int) (j >> 32)) & 255);
            int i7 = i6 + 1;
            bArr[i6] = (byte) (((int) (j >> 40)) & 255);
            int i8 = i7 + 1;
            bArr[i7] = (byte) (((int) (j >> 48)) & 255);
            this.zzc = i8 + 1;
            bArr[i8] = (byte) (((int) (j >> 56)) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new zzqh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1), e);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzn(int i, int i2) throws IOException {
        zzv(i << 3);
        zzo(i2);
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
        int i = this.zzc;
        try {
            int zzK = zzK(str.length() * 3);
            int zzK2 = zzK(str.length());
            if (zzK2 == zzK) {
                int i2 = i + zzK2;
                this.zzc = i2;
                int zzb = zzug.zzb(str, this.zza, i2, this.zzb - i2);
                this.zzc = i;
                zzv((zzb - i) - zzK2);
                this.zzc = zzb;
                return;
            }
            zzv(zzug.zzc(str));
            byte[] bArr = this.zza;
            int i3 = this.zzc;
            this.zzc = zzug.zzb(str, bArr, i3, this.zzb - i3);
        } catch (zzuf e) {
            this.zzc = i;
            zzP(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzqh(e2);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzt(int i, int i2) throws IOException {
        zzv((i << 3) | i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzu(int i, int i2) throws IOException {
        zzv(i << 3);
        zzv(i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzv(int i) throws IOException {
        boolean z;
        z = zzqj.zzb;
        if (z) {
            int i2 = zzpl.zza;
        }
        while ((i & (-128)) != 0) {
            try {
                byte[] bArr = this.zza;
                int i3 = this.zzc;
                this.zzc = i3 + 1;
                bArr[i3] = (byte) ((i & 127) | 128);
                i >>>= 7;
            } catch (IndexOutOfBoundsException e) {
                throw new zzqh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1), e);
            }
        }
        byte[] bArr2 = this.zza;
        int i4 = this.zzc;
        this.zzc = i4 + 1;
        bArr2[i4] = (byte) i;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzw(int i, long j) throws IOException {
        zzv(i << 3);
        zzx(j);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzU(byte b) throws IOException {
        try {
            byte[] bArr = this.zza;
            int i = this.zzc;
            this.zzc = i + 1;
            bArr[i] = b;
        } catch (IndexOutOfBoundsException e) {
            throw new zzqh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1), e);
        }
    }

    public final void zze(byte[] bArr, int i, int i2) throws IOException {
        try {
            System.arraycopy(bArr, 0, this.zza, this.zzc, i2);
            this.zzc += i2;
        } catch (IndexOutOfBoundsException e) {
            throw new zzqh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), Integer.valueOf(i2)), e);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqj
    public final void zzx(long j) throws IOException {
        boolean z;
        z = zzqj.zzb;
        if (!z || this.zzb - this.zzc < 10) {
            while ((j & (-128)) != 0) {
                try {
                    byte[] bArr = this.zza;
                    int i = this.zzc;
                    this.zzc = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzqh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1), e);
                }
            }
            byte[] bArr2 = this.zza;
            int i2 = this.zzc;
            this.zzc = i2 + 1;
            bArr2[i2] = (byte) j;
            return;
        }
        while ((j & (-128)) != 0) {
            byte[] bArr3 = this.zza;
            int i3 = this.zzc;
            this.zzc = i3 + 1;
            zzub.zzn(bArr3, i3, (byte) ((((int) j) & 127) | 128));
            j >>>= 7;
        }
        byte[] bArr4 = this.zza;
        int i4 = this.zzc;
        this.zzc = i4 + 1;
        zzub.zzn(bArr4, i4, (byte) j);
    }
}
