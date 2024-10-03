package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzpy implements Iterable<Byte>, Serializable {
    private static final Comparator<zzpy> zza;
    public static final zzpy zzb = new zzpw(zzrp.zzc);
    private static final zzpx zzd;
    private int zzc = 0;

    static {
        int i = zzpl.zza;
        zzd = new zzpx(null);
        zza = new zzpq();
    }

    public static zzpy zzm(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        zzk(0, remaining, byteBuffer.remaining());
        byte[] bArr = new byte[remaining];
        byteBuffer.get(bArr);
        return new zzpw(bArr);
    }

    public static zzpy zzn(byte[] bArr) {
        return zzo(bArr, 0, bArr.length);
    }

    public static zzpy zzo(byte[] bArr, int i, int i2) {
        zzk(i, i + i2, bArr.length);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new zzpw(bArr2);
    }

    public static zzpy zzp(String str) {
        return new zzpw(str.getBytes(zzrp.zza));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzpy zzq(byte[] bArr) {
        return new zzpw(bArr);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzc;
        if (i == 0) {
            int zzd2 = zzd();
            i = zzf(zzd2, 0, zzd2);
            if (i == 0) {
                i = 1;
            }
            this.zzc = i;
        }
        return i;
    }

    @Override // java.lang.Iterable
    public final /* bridge */ /* synthetic */ Iterator<Byte> iterator() {
        return new zzpo(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzd());
        objArr[2] = zzd() <= 50 ? zztm.zza(this) : String.valueOf(zztm.zza(zzg(0, 47))).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract byte zza(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte zzb(int i);

    public abstract int zzd();

    protected abstract void zze(byte[] bArr, int i, int i2, int i3);

    protected abstract int zzf(int i, int i2, int i3);

    public abstract zzpy zzg(int i, int i2);

    protected abstract String zzh(Charset charset);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzi(zzpn zzpnVar) throws IOException;

    public abstract boolean zzj();

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzl() {
        return this.zzc;
    }

    public final String zzr(Charset charset) {
        return zzd() == 0 ? "" : zzh(charset);
    }

    public final byte[] zzs() {
        int zzd2 = zzd();
        if (zzd2 == 0) {
            return zzrp.zzc;
        }
        byte[] bArr = new byte[zzd2];
        zze(bArr, 0, 0, zzd2);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i2 < i) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        StringBuilder sb3 = new StringBuilder(37);
        sb3.append("End index: ");
        sb3.append(i2);
        sb3.append(" >= ");
        sb3.append(i3);
        throw new IndexOutOfBoundsException(sb3.toString());
    }
}
