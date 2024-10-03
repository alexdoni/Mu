package com.google.android.gms.internal.recaptcha;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzco {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzpy zza(int i) {
        ByteBuffer order = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        order.putInt(i).rewind();
        return zzpy.zzm(order);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzb(byte[] bArr) {
        return zzmg.zzf().zzd().zzg(bArr, 0, bArr.length);
    }
}
