package com.google.android.gms.internal.fido;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
/* loaded from: classes2.dex */
public final class zzar extends zzao {
    private final zzat zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzar(zzat zzatVar, int i) {
        super(zzatVar.size(), i);
        this.zza = zzatVar;
    }

    @Override // com.google.android.gms.internal.fido.zzao
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}
