package com.google.android.gms.internal.recaptcha;

import java.security.MessageDigest;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzlx extends zzld {
    private final MessageDigest zza;
    private final int zzb;
    private boolean zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzlx(MessageDigest messageDigest, int i, zzlw zzlwVar) {
        this.zza = messageDigest;
        this.zzb = i;
    }

    private final void zzc() {
        zzjn.zzj(!this.zzc, "Cannot re-use a Hasher after calling hash() on it");
    }

    @Override // com.google.android.gms.internal.recaptcha.zzld
    protected final void zzb(byte[] bArr, int i, int i2) {
        zzc();
        this.zza.update(bArr, 0, i2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlo
    public final zzlm zzd() {
        zzc();
        this.zzc = true;
        if (this.zzb == this.zza.getDigestLength()) {
            return zzlm.zzg(this.zza.digest());
        }
        return zzlm.zzg(Arrays.copyOf(this.zza.digest(), this.zzb));
    }
}
