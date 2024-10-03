package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import android.system.Os;
import android.system.StructStat;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzhe {
    public static IOException zza(zzed zzedVar, Uri uri, IOException iOException) {
        try {
            zzfd zzb = zzfd.zzb();
            zzb.zzc();
            File file = (File) zzedVar.zza(uri, zzb);
            if (file.exists()) {
                if (file.isFile()) {
                    if (file.canRead()) {
                        if (file.canWrite()) {
                            return zzc(file, iOException);
                        }
                        return zzc(file, iOException);
                    }
                    if (file.canWrite()) {
                        return zzc(file, iOException);
                    }
                    return zzc(file, iOException);
                }
                if (file.canRead()) {
                    if (file.canWrite()) {
                        return zzc(file, iOException);
                    }
                    return zzc(file, iOException);
                }
                if (file.canWrite()) {
                    return zzc(file, iOException);
                }
                return zzc(file, iOException);
            }
            return zzc(file, iOException);
        } catch (IOException unused) {
            return new IOException(iOException);
        }
    }

    private static IOException zzb(File file, IOException iOException) {
        String concat;
        try {
            String valueOf = String.valueOf(String.format(Locale.US, " canonical[%s] freeSpace[%d]", file.getCanonicalPath(), Long.valueOf(file.getFreeSpace())));
            concat = valueOf.length() != 0 ? "Inoperable file:".concat(valueOf) : new String("Inoperable file:");
            try {
                StructStat stat = Os.stat(file.getCanonicalPath());
                String valueOf2 = String.valueOf(concat);
                String valueOf3 = String.valueOf(String.format(Locale.US, " mode[%d]", Integer.valueOf(stat.st_mode)));
                concat = valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2);
            } catch (Exception unused) {
            }
        } catch (IOException unused2) {
            concat = String.valueOf("Inoperable file:").concat(" failed");
        }
        return new IOException(concat, iOException);
    }

    private static IOException zzc(File file, IOException iOException) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return zzb(file, iOException);
        }
        if (parentFile.exists()) {
            if (parentFile.isDirectory()) {
                if (parentFile.canRead()) {
                    if (parentFile.canWrite()) {
                        return zzb(file, iOException);
                    }
                    return zzb(file, iOException);
                }
                if (parentFile.canWrite()) {
                    return zzb(file, iOException);
                }
                return zzb(file, iOException);
            }
            if (parentFile.canRead()) {
                if (parentFile.canWrite()) {
                    return zzb(file, iOException);
                }
                return zzb(file, iOException);
            }
            if (parentFile.canWrite()) {
                return zzb(file, iOException);
            }
            return zzb(file, iOException);
        }
        return zzb(file, iOException);
    }
}
