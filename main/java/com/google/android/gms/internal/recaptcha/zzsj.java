package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzsj {
    private static final zzsi zza;
    private static final zzsi zzb;

    static {
        zzsi zzsiVar;
        try {
            zzsiVar = (zzsi) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzsiVar = null;
        }
        zza = zzsiVar;
        zzb = new zzsi();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzsi zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzsi zzb() {
        return zzb;
    }
}
