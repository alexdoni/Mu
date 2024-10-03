package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.net.HttpURLConnection;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzm extends zzf {
    private zzjv<Integer> zza;
    private zzjv<Integer> zzb;
    private zzl zzc;
    private HttpURLConnection zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzm() {
        zzj zzjVar = new zzjv() { // from class: com.google.android.gms.internal.recaptcha.zzj
            @Override // com.google.android.gms.internal.recaptcha.zzjv
            public final Object zza() {
                return -1;
            }
        };
        zzk zzkVar = new zzjv() { // from class: com.google.android.gms.internal.recaptcha.zzk
            @Override // com.google.android.gms.internal.recaptcha.zzjv
            public final Object zza() {
                return -1;
            }
        };
        this.zza = zzjVar;
        this.zzb = zzkVar;
        this.zzc = null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        HttpURLConnection httpURLConnection = this.zzd;
        zzg.zza();
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public final HttpURLConnection zzb(zzl zzlVar, int i, int i2) throws IOException {
        final int i3 = 21504;
        this.zza = new zzjv(i3) { // from class: com.google.android.gms.internal.recaptcha.zzh
            @Override // com.google.android.gms.internal.recaptcha.zzjv
            public final Object zza() {
                return 21504;
            }
        };
        final int i4 = -1;
        this.zzb = new zzjv(i4) { // from class: com.google.android.gms.internal.recaptcha.zzi
            @Override // com.google.android.gms.internal.recaptcha.zzjv
            public final Object zza() {
                return -1;
            }
        };
        this.zzc = zzlVar;
        zzg.zzb(this.zza.zza().intValue(), this.zzb.zza().intValue());
        zzl zzlVar2 = this.zzc;
        zzlVar2.getClass();
        HttpURLConnection httpURLConnection = (HttpURLConnection) zzlVar2.zza();
        this.zzd = httpURLConnection;
        return httpURLConnection;
    }
}
