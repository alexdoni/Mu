package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzpg;
import com.google.android.gms.internal.recaptcha.zzph;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzpg<MessageType extends zzph<MessageType, BuilderType>, BuilderType extends zzpg<MessageType, BuilderType>> implements zzsm {
    @Override // 
    public abstract BuilderType zzf();

    protected abstract BuilderType zzg(MessageType messagetype);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.recaptcha.zzsm
    public final /* bridge */ /* synthetic */ zzsm zzh(zzsn zzsnVar) {
        if (!zzO().getClass().isInstance(zzsnVar)) {
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
        return zzg((zzph) zzsnVar);
    }
}
