package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzps extends zzpw {
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzps(byte[] bArr, int i, int i2) {
        super(bArr);
        zzk(0, i2, bArr.length);
        this.zzc = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzpw, com.google.android.gms.internal.recaptcha.zzpy
    public final byte zzb(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpw
    protected final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpw, com.google.android.gms.internal.recaptcha.zzpy
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpw, com.google.android.gms.internal.recaptcha.zzpy
    protected final void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpw, com.google.android.gms.internal.recaptcha.zzpy
    public final byte zza(int i) {
        int i2 = this.zzc;
        if (((i2 - (i + 1)) | i) >= 0) {
            return this.zza[i];
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i);
        sb2.append(", ");
        sb2.append(i2);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }
}
