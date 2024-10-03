package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzeq {
    private OutputStream zza;
    private zzey zzb;

    public final void zza(List<OutputStream> list) throws IOException {
        OutputStream outputStream = (OutputStream) zzko.zza(list);
        if (outputStream instanceof zzey) {
            this.zzb = (zzey) outputStream;
            this.zza = list.get(0);
        }
    }

    public final void zzb() throws IOException {
        if (this.zzb == null) {
            throw new zzew("Cannot sync underlying stream");
        }
        this.zza.flush();
        this.zzb.zzb();
    }
}
