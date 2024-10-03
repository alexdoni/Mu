package com.google.android.gms.internal.recaptcha;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzjt {
    private final zzja zza;
    private final boolean zzb;
    private final zzjq zzc;

    private zzjt(zzjq zzjqVar, boolean z, zzja zzjaVar, int i, byte[] bArr) {
        this.zzc = zzjqVar;
        this.zzb = z;
        this.zza = zzjaVar;
    }

    public static zzjt zzc(String str) {
        return new zzjt(new zzjq(new zzix("+".charAt(0))), false, zziz.zza, Integer.MAX_VALUE, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Iterator zze(zzjt zzjtVar, CharSequence charSequence) {
        return new zzjp(zzjtVar.zzc, zzjtVar, charSequence);
    }

    public final zzjt zzb() {
        return new zzjt(this.zzc, true, this.zza, Integer.MAX_VALUE, null);
    }

    public final Iterable<String> zzd(CharSequence charSequence) {
        charSequence.getClass();
        return new zzjr(this, charSequence);
    }
}
