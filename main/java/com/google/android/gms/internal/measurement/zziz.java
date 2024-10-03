package com.google.android.gms.internal.measurement;

import com.oversea.ab_firstarea.utils.UpdateHelper;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.spongycastle.i18n.LocalizedMessage;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
/* loaded from: classes2.dex */
public final class zziz {
    public static final byte[] zzb;
    private static final ByteBuffer zze;
    private static final zzib zzf;
    private static final Charset zzc = Charset.forName("US-ASCII");
    static final Charset zza = Charset.forName("UTF-8");
    private static final Charset zzd = Charset.forName(LocalizedMessage.DEFAULT_ENCODING);

    public static int zza(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int zza(boolean z) {
        if (z) {
            return UpdateHelper.UPDATE_REQUEST_CODE;
        }
        return 1237;
    }

    public static int zza(byte[] bArr) {
        int length = bArr.length;
        int zza2 = zza(length, bArr, 0, length);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zza(T t) {
        t.getClass();
        return t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static String zzb(byte[] bArr) {
        return new String(bArr, zza);
    }

    static {
        byte[] bArr = new byte[0];
        zzb = bArr;
        zze = ByteBuffer.wrap(bArr);
        zzf = zzib.zza(bArr, 0, bArr.length, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zza(zzkj zzkjVar) {
        if (!(zzkjVar instanceof zzhe)) {
            return false;
        }
        return false;
    }

    public static boolean zzc(byte[] bArr) {
        return zzmh.zza(bArr);
    }
}
