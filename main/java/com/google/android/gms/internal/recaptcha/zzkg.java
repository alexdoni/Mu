package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzkg<E> extends zzjy<E> {
    private final zzkj<E> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkg(zzkj<E> zzkjVar, int i) {
        super(zzkjVar.size(), i);
        this.zza = zzkjVar;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzjy
    protected final E zza(int i) {
        return this.zza.get(i);
    }
}
