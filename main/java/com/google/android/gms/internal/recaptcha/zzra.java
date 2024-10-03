package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzra implements zzsl {
    private static final zzra zza = new zzra();

    private zzra() {
    }

    public static zzra zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsl
    public final zzsk zzb(Class<?> cls) {
        if (!zzrg.class.isAssignableFrom(cls)) {
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Unsupported message type: ".concat(valueOf) : new String("Unsupported message type: "));
        }
        try {
            return (zzsk) zzrg.zzw(cls.asSubclass(zzrg.class)).zzh(3, null, null);
        } catch (Exception e) {
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? "Unable to get message info for ".concat(valueOf2) : new String("Unable to get message info for "), e);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsl
    public final boolean zzc(Class<?> cls) {
        return zzrg.class.isAssignableFrom(cls);
    }
}
