package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzqu {
    private static final zzqs<?> zza = new zzqt();
    private static final zzqs<?> zzb;

    static {
        zzqs<?> zzqsVar;
        try {
            zzqsVar = (zzqs) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzqsVar = null;
        }
        zzb = zzqsVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzqs<?> zza() {
        zzqs<?> zzqsVar = zzb;
        if (zzqsVar != null) {
            return zzqsVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzqs<?> zzb() {
        return zza;
    }
}
