package com.google.android.gms.internal.recaptcha;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzex extends zzez implements zzer {
    private final FileInputStream zza;
    private final File zzb;

    private zzex(FileInputStream fileInputStream, File file) {
        super(fileInputStream);
        this.zza = fileInputStream;
        this.zzb = file;
    }

    public static zzex zzb(File file) throws FileNotFoundException {
        return new zzex(new FileInputStream(file), file);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzer
    public final File zza() {
        return this.zzb;
    }
}
