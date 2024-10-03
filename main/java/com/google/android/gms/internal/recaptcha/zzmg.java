package com.google.android.gms.internal.recaptcha;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzmg {
    private static final zzmg zza = new zzme("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final zzmg zzb = new zzme("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
    private static final zzmg zzc = new zzmf("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    private static final zzmg zzd = new zzmf("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    private static final zzmg zze = new zzmd("base16()", "0123456789ABCDEF");

    public static zzmg zzf() {
        return zze;
    }

    abstract void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException;

    abstract int zzc(int i);

    public abstract zzmg zzd();

    public final String zzg(byte[] bArr, int i, int i2) {
        zzjn.zzh(0, i2, bArr.length);
        StringBuilder sb = new StringBuilder(zzc(i2));
        try {
            zzb(sb, bArr, 0, i2);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
