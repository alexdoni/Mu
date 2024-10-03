package com.google.android.gms.internal.recaptcha;

import com.google.common.base.Ascii;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzlm {
    private static final char[] zza = "0123456789abcdef".toCharArray();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzlm zzg(byte[] bArr) {
        return new zzlk(bArr);
    }

    public static zzlm zzh(long j) {
        return new zzll(j);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzlm) {
            zzlm zzlmVar = (zzlm) obj;
            if (zzb() == zzlmVar.zzb() && zzd(zzlmVar)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        if (zzb() >= 32) {
            return zza();
        }
        byte[] zzf = zzf();
        int i = zzf[0] & 255;
        for (int i2 = 1; i2 < zzf.length; i2++) {
            i |= (zzf[i2] & 255) << (i2 * 8);
        }
        return i;
    }

    public final String toString() {
        byte[] zzf = zzf();
        int length = zzf.length;
        StringBuilder sb = new StringBuilder(length + length);
        for (byte b : zzf) {
            char[] cArr = zza;
            sb.append(cArr[(b >> 4) & 15]);
            sb.append(cArr[b & Ascii.f555SI]);
        }
        return sb.toString();
    }

    public abstract int zza();

    public abstract int zzb();

    public abstract long zzc();

    abstract boolean zzd(zzlm zzlmVar);

    public abstract byte[] zze();

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] zzf() {
        return zze();
    }
}
