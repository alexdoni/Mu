package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import com.google.android.gms.internal.recaptcha.zzsn;
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzfo<T extends zzsn> extends zzfz<T> {
    private final Uri zza;
    private final T zzb;
    private final zzfp<T> zzc;
    private final zzkj<zzfr<T>> zzd;
    private final boolean zze;
    private final boolean zzf;
    private final boolean zzg;
    private final zzge zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ zzfo(Uri uri, zzsn zzsnVar, zzfp zzfpVar, zzkj zzkjVar, zzge zzgeVar, boolean z, boolean z2, boolean z3, zzfm zzfmVar, byte[] bArr) {
        this.zza = uri;
        this.zzb = zzsnVar;
        this.zzc = zzfpVar;
        this.zzd = zzkjVar;
        this.zzh = zzgeVar;
        this.zze = z;
        this.zzf = z2;
        this.zzg = z3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfz) {
            zzfz zzfzVar = (zzfz) obj;
            if (this.zza.equals(zzfzVar.zza()) && this.zzb.equals(zzfzVar.zzd()) && this.zzc.equals(zzfzVar.zzb()) && this.zzd.equals(zzfzVar.zzc()) && this.zzh.equals(zzfzVar.zzh()) && this.zze == zzfzVar.zzg() && this.zzf == zzfzVar.zzf() && this.zzg == zzfzVar.zze()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ this.zzc.hashCode()) * 1000003) ^ this.zzd.hashCode()) * 1000003) ^ this.zzh.hashCode()) * 1000003) ^ (true != this.zze ? 1237 : 1231)) * 1000003) ^ (true != this.zzf ? 1237 : 1231)) * 1000003) ^ (true == this.zzg ? 1231 : 1237);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        String valueOf3 = String.valueOf(this.zzc);
        String valueOf4 = String.valueOf(this.zzd);
        String valueOf5 = String.valueOf(this.zzh);
        boolean z = this.zze;
        boolean z2 = this.zzf;
        boolean z3 = this.zzg;
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        int length3 = String.valueOf(valueOf3).length();
        int length4 = String.valueOf(valueOf4).length();
        StringBuilder sb = new StringBuilder(length + CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 + length2 + length3 + length4 + String.valueOf(valueOf5).length());
        sb.append("ProtoDataStoreConfig{uri=");
        sb.append(valueOf);
        sb.append(", schema=");
        sb.append(valueOf2);
        sb.append(", handler=");
        sb.append(valueOf3);
        sb.append(", migrations=");
        sb.append(valueOf4);
        sb.append(", variantConfig=");
        sb.append(valueOf5);
        sb.append(", useGeneratedExtensionRegistry=");
        sb.append(z);
        sb.append(", updateSequencingBugFix=");
        sb.append(z2);
        sb.append(", enableTracing=");
        sb.append(z3);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfz
    public final Uri zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfz
    public final zzfp<T> zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfz
    public final zzkj<zzfr<T>> zzc() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfz
    public final T zzd() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzfz
    public final boolean zze() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfz
    public final boolean zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfz
    public final boolean zzg() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfz
    public final zzge zzh() {
        return this.zzh;
    }
}
