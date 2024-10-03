package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzsd implements zzsl {
    private final zzsl[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzsd(zzsl... zzslVarArr) {
        this.zza = zzslVarArr;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsl
    public final zzsk zzb(Class<?> cls) {
        zzsl[] zzslVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzsl zzslVar = zzslVarArr[i];
            if (zzslVar.zzc(cls)) {
                return zzslVar.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsl
    public final boolean zzc(Class<?> cls) {
        zzsl[] zzslVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzslVarArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
