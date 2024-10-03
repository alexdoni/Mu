package com.google.android.gms.internal.recaptcha;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzey extends zzfa implements zzer {
    private final FileOutputStream zza;
    private final File zzb;

    public zzey(FileOutputStream fileOutputStream, File file) {
        super(fileOutputStream);
        this.zza = fileOutputStream;
        this.zzb = file;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzer
    public final File zza() {
        return this.zzb;
    }

    public final void zzb() throws IOException {
        this.zza.getFD().sync();
    }
}
