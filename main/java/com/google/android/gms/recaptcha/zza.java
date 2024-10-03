package com.google.android.gms.recaptcha;

import com.google.android.gms.internal.recaptcha.zzcs;
import com.google.android.gms.internal.recaptcha.zzqo;
import com.google.android.gms.internal.recaptcha.zztp;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zza extends VerificationHandle {
    private final String zza;
    private final String zzb;
    private final long zzc;
    private final int zzd;
    private final String zze;
    private final zzcs zzf;
    private final zztp zzg;
    private final zzqo zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(String str, String str2, long j, int i, String str3, zzcs zzcsVar, zztp zztpVar, zzqo zzqoVar) {
        if (str != null) {
            this.zza = str;
            if (str2 != null) {
                this.zzb = str2;
                this.zzc = j;
                this.zzd = i;
                if (str3 != null) {
                    this.zze = str3;
                    if (zzcsVar != null) {
                        this.zzf = zzcsVar;
                        if (zztpVar != null) {
                            this.zzg = zztpVar;
                            if (zzqoVar != null) {
                                this.zzh = zzqoVar;
                                return;
                            }
                            throw new NullPointerException("Null validityDuration");
                        }
                        throw new NullPointerException("Null creationTimestamp");
                    }
                    throw new NullPointerException("Null recaptchaTimeProvider");
                }
                throw new NullPointerException("Null operationAbortedToken");
            }
            throw new NullPointerException("Null siteKey");
        }
        throw new NullPointerException("Null verificationToken");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VerificationHandle) {
            VerificationHandle verificationHandle = (VerificationHandle) obj;
            if (this.zza.equals(verificationHandle.getVerificationToken()) && this.zzb.equals(verificationHandle.getSiteKey()) && this.zzc == verificationHandle.getTimeoutMinutes() && this.zzd == verificationHandle.getCodeLength() && this.zze.equals(verificationHandle.getOperationAbortedToken()) && this.zzf.equals(verificationHandle.zza()) && this.zzg.equals(verificationHandle.zzc()) && this.zzh.equals(verificationHandle.zzb())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.recaptcha.VerificationHandle
    public final int getCodeLength() {
        return this.zzd;
    }

    @Override // com.google.android.gms.recaptcha.VerificationHandle
    public final String getOperationAbortedToken() {
        return this.zze;
    }

    @Override // com.google.android.gms.recaptcha.VerificationHandle
    public final String getSiteKey() {
        return this.zzb;
    }

    @Override // com.google.android.gms.recaptcha.VerificationHandle
    public final long getTimeoutMinutes() {
        return this.zzc;
    }

    @Override // com.google.android.gms.recaptcha.VerificationHandle
    public final String getVerificationToken() {
        return this.zza;
    }

    public final int hashCode() {
        int hashCode = this.zza.hashCode();
        int hashCode2 = this.zzb.hashCode();
        long j = this.zzc;
        return ((((((((((((((hashCode ^ 1000003) * 1000003) ^ hashCode2) * 1000003) ^ ((int) ((j >>> 32) ^ j))) * 1000003) ^ this.zzd) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf.hashCode()) * 1000003) ^ this.zzg.hashCode()) * 1000003) ^ this.zzh.hashCode();
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        long j = this.zzc;
        int i = this.zzd;
        String str3 = this.zze;
        String valueOf = String.valueOf(this.zzf);
        String valueOf2 = String.valueOf(this.zzg);
        String valueOf3 = String.valueOf(this.zzh);
        int length = str.length();
        int length2 = str2.length();
        int length3 = str3.length();
        int length4 = String.valueOf(valueOf).length();
        int length5 = String.valueOf(valueOf2).length();
        StringBuilder sb = new StringBuilder(length + CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 + length2 + length3 + length4 + length5 + String.valueOf(valueOf3).length());
        sb.append("VerificationHandle{verificationToken=");
        sb.append(str);
        sb.append(", siteKey=");
        sb.append(str2);
        sb.append(", timeoutMinutes=");
        sb.append(j);
        sb.append(", codeLength=");
        sb.append(i);
        sb.append(", operationAbortedToken=");
        sb.append(str3);
        sb.append(", recaptchaTimeProvider=");
        sb.append(valueOf);
        sb.append(", creationTimestamp=");
        sb.append(valueOf2);
        sb.append(", validityDuration=");
        sb.append(valueOf3);
        sb.append("}");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.recaptcha.VerificationHandle
    public final zzcs zza() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.recaptcha.VerificationHandle
    public final zzqo zzb() {
        return this.zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.recaptcha.VerificationHandle
    public final zztp zzc() {
        return this.zzg;
    }
}
