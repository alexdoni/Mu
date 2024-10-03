package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzsn;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzpj<MessageType extends zzsn> implements zzsu<MessageType> {
    private static final zzqr zza = zzqr.zza();

    private static final MessageType zze(MessageType messagetype) throws zzrr {
        if (messagetype == null || messagetype.zzp()) {
            return messagetype;
        }
        zzrr zzrrVar = new zzrr(new zztq(messagetype).getMessage());
        zzrrVar.zzh(messagetype);
        throw zzrrVar;
    }

    public final MessageType zza(InputStream inputStream, zzqr zzqrVar) throws zzrr {
        zzqc zzqbVar;
        int i = zzqc.zzd;
        if (inputStream == null) {
            byte[] bArr = zzrp.zzc;
            int length = bArr.length;
            zzqbVar = zzqc.zzH(bArr, 0, 0, false);
        } else {
            zzqbVar = new zzqb(inputStream, 4096, null);
        }
        MessageType messagetype = (MessageType) zzd(zzqbVar, zzqrVar);
        try {
            zzqbVar.zzz(0);
            return messagetype;
        } catch (zzrr e) {
            e.zzh(messagetype);
            throw e;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsu
    public final /* bridge */ /* synthetic */ Object zzb(InputStream inputStream) throws zzrr {
        MessageType zza2 = zza(inputStream, zza);
        zze(zza2);
        return zza2;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsu
    public final /* bridge */ /* synthetic */ Object zzc(InputStream inputStream, zzqr zzqrVar) throws zzrr {
        MessageType zza2 = zza(inputStream, zzqrVar);
        zze(zza2);
        return zza2;
    }
}
