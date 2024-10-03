package com.google.android.gms.internal.recaptcha;

import com.oversea.ab_firstarea.utils.UpdateHelper;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.spongycastle.i18n.LocalizedMessage;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzrp {
    static final Charset zza = Charset.forName("UTF-8");
    static final Charset zzb = Charset.forName(LocalizedMessage.DEFAULT_ENCODING);
    public static final byte[] zzc;
    public static final ByteBuffer zzd;
    public static final zzqc zze;

    static {
        byte[] bArr = new byte[0];
        zzc = bArr;
        zzd = ByteBuffer.wrap(bArr);
        int i = zzqc.zzd;
        zze = zzqc.zzH(bArr, 0, 0, false);
    }

    public static int zza(boolean z) {
        if (z) {
            return UpdateHelper.UPDATE_REQUEST_CODE;
        }
        return 1237;
    }

    public static int zzb(byte[] bArr) {
        int length = bArr.length;
        int zzd2 = zzd(length, bArr, 0, length);
        if (zzd2 == 0) {
            return 1;
        }
        return zzd2;
    }

    public static int zzc(long j) {
        return (int) (j ^ (j >>> 32));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zze(T t) {
        t.getClass();
        return t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zzf(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzg(Object obj, Object obj2) {
        return ((zzsn) obj).zzL().zzh((zzsn) obj2).zzm();
    }

    public static String zzh(byte[] bArr) {
        return new String(bArr, zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzi(zzsn zzsnVar) {
        if (!(zzsnVar instanceof zzpi)) {
            return false;
        }
        throw null;
    }

    public static boolean zzj(byte[] bArr) {
        return zzug.zze(bArr);
    }
}
