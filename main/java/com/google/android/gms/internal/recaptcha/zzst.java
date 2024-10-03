package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzst {
    private static final zzss zza;
    private static final zzss zzb;

    static {
        zzss zzssVar;
        try {
            zzssVar = (zzss) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzssVar = null;
        }
        zza = zzssVar;
        zzb = new zzss();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzss zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzss zzb() {
        return zzb;
    }
}
