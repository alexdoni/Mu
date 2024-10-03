package com.google.android.gms.internal.recaptcha;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzly extends zzle implements Serializable {
    private final MessageDigest zza;
    private final int zzb;
    private final boolean zzc;
    private final String zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzly(String str, String str2) {
        MessageDigest zzf = zzf(McElieceCCA2KeyGenParameterSpec.SHA256);
        this.zza = zzf;
        this.zzb = zzf.getDigestLength();
        this.zzd = "Hashing.sha256()";
        this.zzc = zzg(zzf);
    }

    private static MessageDigest zzf(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    private static boolean zzg(MessageDigest messageDigest) {
        try {
            messageDigest.clone();
            return true;
        } catch (CloneNotSupportedException unused) {
            return false;
        }
    }

    public final String toString() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzln
    public final zzlo zze() {
        zzlw zzlwVar = null;
        if (this.zzc) {
            try {
                return new zzlx((MessageDigest) this.zza.clone(), this.zzb, zzlwVar);
            } catch (CloneNotSupportedException unused) {
            }
        }
        return new zzlx(zzf(this.zza.getAlgorithm()), this.zzb, zzlwVar);
    }
}
