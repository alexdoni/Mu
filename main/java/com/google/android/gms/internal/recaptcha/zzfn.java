package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import com.google.android.gms.internal.recaptcha.zzsn;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzfn<T extends zzsn> extends zzfy<T> {
    private Uri zza;
    private T zzb;
    private zzfp<T> zzc;
    private zzkj<zzfr<T>> zzd;
    private Boolean zze;
    private Boolean zzf;
    private Boolean zzg;
    private zzge zzh;

    @Override // com.google.android.gms.internal.recaptcha.zzfy
    public final zzfy<T> zza(boolean z) {
        this.zzg = false;
        return this;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfy
    public final zzfy<T> zzb(zzfp<T> zzfpVar) {
        this.zzc = zzfpVar;
        return this;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfy
    public final zzfy<T> zzc(T t) {
        if (t != null) {
            this.zzb = t;
            return this;
        }
        throw new NullPointerException("Null schema");
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfy
    public final zzfy<T> zzd(boolean z) {
        this.zzf = false;
        return this;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfy
    public final zzfy<T> zze(Uri uri) {
        if (uri != null) {
            this.zza = uri;
            return this;
        }
        throw new NullPointerException("Null uri");
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfy
    public final zzfy<T> zzf(boolean z) {
        this.zze = Boolean.valueOf(z);
        return this;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfy
    public final zzfz<T> zzg() {
        T t;
        zzfp<T> zzfpVar;
        zzge zzgeVar;
        Boolean bool;
        if (this.zzd == null) {
            this.zzd = zzkj.zzo();
        }
        Uri uri = this.zza;
        if (uri == null || (t = this.zzb) == null || (zzfpVar = this.zzc) == null || (zzgeVar = this.zzh) == null || (bool = this.zze) == null || this.zzf == null || this.zzg == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" uri");
            }
            if (this.zzb == null) {
                sb.append(" schema");
            }
            if (this.zzc == null) {
                sb.append(" handler");
            }
            if (this.zzh == null) {
                sb.append(" variantConfig");
            }
            if (this.zze == null) {
                sb.append(" useGeneratedExtensionRegistry");
            }
            if (this.zzf == null) {
                sb.append(" updateSequencingBugFix");
            }
            if (this.zzg == null) {
                sb.append(" enableTracing");
            }
            String valueOf = String.valueOf(sb);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 28);
            sb2.append("Missing required properties:");
            sb2.append(valueOf);
            throw new IllegalStateException(sb2.toString());
        }
        return new zzfo(uri, t, zzfpVar, this.zzd, zzgeVar, bool.booleanValue(), this.zzf.booleanValue(), this.zzg.booleanValue(), null, null);
    }

    public final zzfy<T> zzh(zzge zzgeVar) {
        this.zzh = zzgeVar;
        return this;
    }
}
