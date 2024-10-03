package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzrb;
import com.google.android.gms.internal.recaptcha.zzrg;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public class zzrb<MessageType extends zzrg<MessageType, BuilderType>, BuilderType extends zzrb<MessageType, BuilderType>> extends zzpg<MessageType, BuilderType> {
    protected MessageType zza;
    protected boolean zzb = false;
    private final MessageType zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzrb(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (MessageType) messagetype.zzh(4, null, null);
    }

    private static final void zza(MessageType messagetype, MessageType messagetype2) {
        zzsw.zza().zzb(messagetype.getClass()).zze(messagetype, messagetype2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzso
    public final /* bridge */ /* synthetic */ zzsn zzO() {
        return this.zzc;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.recaptcha.zzpg
    protected final /* bridge */ /* synthetic */ zzpg zzg(zzph zzphVar) {
        zzj((zzrg) zzphVar);
        return this;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpg
    /* renamed from: zzi, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final BuilderType zzf() {
        BuilderType buildertype = (BuilderType) this.zzc.zzh(5, null, null);
        buildertype.zzj(zzm());
        return buildertype;
    }

    public final BuilderType zzj(MessageType messagetype) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zza(this.zza, messagetype);
        return this;
    }

    public final MessageType zzk() {
        MessageType zzm = zzm();
        if (zzm.zzp()) {
            return zzm;
        }
        throw new zztq(zzm);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsm
    /* renamed from: zzl, reason: merged with bridge method [inline-methods] */
    public MessageType zzm() {
        if (this.zzb) {
            return this.zza;
        }
        MessageType messagetype = this.zza;
        zzsw.zza().zzb(messagetype.getClass()).zzd(messagetype);
        this.zzb = true;
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzo() {
        MessageType messagetype = (MessageType) this.zza.zzh(4, null, null);
        zza(messagetype, this.zza);
        this.zza = messagetype;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzso
    public final boolean zzp() {
        throw null;
    }
}
