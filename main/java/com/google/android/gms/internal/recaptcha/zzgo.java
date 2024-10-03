package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzgo<T> implements zzhc<T> {
    private final String zza;
    private final zzop<Uri> zzb;
    private final zzgd<T> zzc;
    private final Executor zzd;
    private final zzed zze;
    private final zzfp<T> zzf;
    private final zzia zzg;
    private final Object zzh = new Object();
    private final zznu zzi = zznu.zzc();
    private zzop<T> zzj = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgo(String str, zzop<Uri> zzopVar, zzgd<T> zzgdVar, Executor executor, zzed zzedVar, zzfp<T> zzfpVar, zzia zziaVar) {
        this.zza = str;
        this.zzb = zzof.zzh(zzopVar);
        this.zzc = zzgdVar;
        this.zzd = zzow.zzc(executor);
        this.zze = zzedVar;
        this.zzf = zzfpVar;
        this.zzg = zziaVar;
    }

    public static zzhd zza() {
        return zzgn.zza();
    }

    private final zzop<T> zzl() {
        zzop<T> zzopVar;
        synchronized (this.zzh) {
            zzop<T> zzopVar2 = this.zzj;
            if (zzopVar2 != null && zzopVar2.isDone()) {
                try {
                    zzof.zzl(this.zzj);
                } catch (ExecutionException unused) {
                    this.zzj = null;
                }
            }
            if (this.zzj == null) {
                this.zzj = zzof.zzh(this.zzi.zzd(zzim.zzb(new zznf() { // from class: com.google.android.gms.internal.recaptcha.zzgg
                    @Override // com.google.android.gms.internal.recaptcha.zznf
                    public final zzop zza() {
                        return zzgo.this.zzg();
                    }
                }), this.zzd));
            }
            zzopVar = this.zzj;
        }
        return zzopVar;
    }

    private final T zzm(Uri uri) throws IOException {
        try {
            try {
                zzia zziaVar = this.zzg;
                String valueOf = String.valueOf(this.zza);
                zzie zzb = zziaVar.zzb(valueOf.length() != 0 ? "Read ".concat(valueOf) : new String("Read "), 1);
                try {
                    InputStream inputStream = (InputStream) this.zze.zza(uri, zzfe.zzb());
                    try {
                        zzgd<T> zzgdVar = this.zzc;
                        T t = (T) ((zzhj) zzgdVar).zzc().zzA().zzc(inputStream, ((zzhj) zzgdVar).zzb());
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        zzb.close();
                        return t;
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        zzb.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (FileNotFoundException e) {
                if (this.zze.zzd(uri)) {
                    throw e;
                }
                return this.zzc.zza();
            }
        } catch (IOException e2) {
            throw zzhe.zza(this.zze, uri, e2);
        }
    }

    public final /* synthetic */ zzop zzb(Object obj) throws Exception {
        zzop<T> zzopVar;
        synchronized (this.zzh) {
            zzopVar = this.zzj;
        }
        return zzopVar;
    }

    public final /* synthetic */ zzop zzc() throws Exception {
        return zzof.zzh(zzof.zzk(this.zzb, zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzgj
            @Override // com.google.android.gms.internal.recaptcha.zzng
            public final zzop zza(Object obj) {
                return zzgo.this.zzf((Uri) obj);
            }
        }), this.zzd));
    }

    public final /* synthetic */ zzop zzd(Void r1) throws Exception {
        return zzof.zzf(zzm((Uri) zzof.zzl(this.zzb)));
    }

    public final /* synthetic */ zzop zze(zzop zzopVar, final zzop zzopVar2, Object obj) throws Exception {
        if (!zzof.zzl(zzopVar).equals(zzof.zzl(zzopVar2))) {
            zzop zzk = zzof.zzk(zzopVar2, zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzgl
                @Override // com.google.android.gms.internal.recaptcha.zzng
                public final zzop zza(Object obj2) {
                    return zzgo.this.zzh(zzopVar2, obj2);
                }
            }), this.zzd);
            synchronized (this.zzh) {
            }
            return zzk;
        }
        return zzof.zzg();
    }

    public final /* synthetic */ zzop zzf(Uri uri) throws Exception {
        Uri zza = zzhf.zza(uri, ".bak");
        try {
            if (this.zze.zzd(zza)) {
                this.zze.zzc(zza, uri);
            }
            return zzof.zzg();
        } catch (IOException e) {
            return zzof.zze(e);
        }
    }

    public final /* synthetic */ zzop zzg() throws Exception {
        try {
            return zzof.zzf(zzm((Uri) zzof.zzl(this.zzb)));
        } catch (IOException e) {
            if ((e instanceof zzes) || (e.getCause() instanceof zzes)) {
                return zzof.zze(e);
            }
            return zzof.zzk(zzof.zze(e), zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzgk
                @Override // com.google.android.gms.internal.recaptcha.zzng
                public final zzop zza(Object obj) {
                    return zzgo.this.zzd((Void) obj);
                }
            }), this.zzd);
        }
    }

    public final /* synthetic */ zzop zzh(zzop zzopVar, Object obj) throws Exception {
        Uri uri = (Uri) zzof.zzl(this.zzb);
        Uri zza = zzhf.zza(uri, ".tmp");
        try {
            zzia zziaVar = this.zzg;
            String valueOf = String.valueOf(this.zza);
            zzie zzb = zziaVar.zzb(valueOf.length() != 0 ? "Write ".concat(valueOf) : new String("Write "), 1);
            try {
                zzeq zzeqVar = new zzeq();
                try {
                    zzed zzedVar = this.zze;
                    zzff zzb2 = zzff.zzb();
                    zzb2.zzc(zzeqVar);
                    OutputStream outputStream = (OutputStream) zzedVar.zza(zza, zzb2);
                    try {
                        ((zzsn) obj).zzs(outputStream);
                        zzeqVar.zzb();
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        zzb.close();
                        this.zze.zzc(zza, uri);
                        synchronized (this.zzh) {
                            this.zzj = zzopVar;
                        }
                        return zzof.zzg();
                    } catch (Throwable th) {
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (IOException e) {
                    throw zzhe.zza(this.zze, uri, e);
                }
            } finally {
            }
        } catch (IOException e2) {
            if (this.zze.zzd(zza)) {
                try {
                    this.zze.zzb(zza);
                } catch (IOException e3) {
                    e2.addSuppressed(e3);
                }
            }
            throw e2;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzhc
    public final zzop<T> zzi(zzha zzhaVar) {
        return zzl();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzhc
    public final zzop<Void> zzj(final zzng<? super T, T> zzngVar, final Executor executor, zzhb zzhbVar) {
        final zzop<T> zzl = zzl();
        return this.zzi.zzd(zzim.zzb(new zznf() { // from class: com.google.android.gms.internal.recaptcha.zzgh
            @Override // com.google.android.gms.internal.recaptcha.zznf
            public final zzop zza() {
                final zzgo zzgoVar = zzgo.this;
                zzop zzopVar = zzl;
                zzng zzngVar2 = zzngVar;
                Executor executor2 = executor;
                final zzop zzk = zzof.zzk(zzopVar, new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzgi
                    @Override // com.google.android.gms.internal.recaptcha.zzng
                    public final zzop zza(Object obj) {
                        return zzgo.this.zzb(obj);
                    }
                }, zzow.zzb());
                final zzop zzk2 = zzof.zzk(zzk, zzngVar2, executor2);
                return zzof.zzk(zzk2, zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzgm
                    @Override // com.google.android.gms.internal.recaptcha.zzng
                    public final zzop zza(Object obj) {
                        return zzgo.this.zze(zzk, zzk2, obj);
                    }
                }), zzow.zzb());
            }
        }), zzow.zzb());
    }

    @Override // com.google.android.gms.internal.recaptcha.zzhc
    public final String zzk() {
        return this.zza;
    }
}
