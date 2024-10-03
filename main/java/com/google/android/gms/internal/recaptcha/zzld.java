package com.google.android.gms.internal.recaptcha;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzld extends zzlf {
    private final ByteBuffer zza = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

    @Override // com.google.android.gms.internal.recaptcha.zzlf, com.google.android.gms.internal.recaptcha.zzlo
    public final zzlo zza(byte[] bArr, int i, int i2) {
        zzjn.zzh(0, i2, bArr.length);
        zzb(bArr, 0, i2);
        return this;
    }

    protected void zzb(byte[] bArr, int i, int i2) {
        throw null;
    }
}
