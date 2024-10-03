package com.p008ld.sdk.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import com.p008ld.sdk.LDSdk;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDPathUtils.kt */
/* loaded from: classes2.dex */
public final class zzj {
    public static final zzj zza = new zzj();

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean zzi(File file) {
        return true;
    }

    private zzj() {
    }

    @JvmStatic
    public static final String zza(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT < 24) {
            return context.getApplicationInfo().dataDir;
        }
        return zza.zzg(context.getDataDir());
    }

    @JvmStatic
    public static final boolean zza(File directory) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        return (directory.mkdirs() || directory.exists()) && directory.canWrite();
    }

    public final String zza() {
        return zzg(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
    }

    private final String zzg(File file) {
        return file == null ? "" : file.getAbsolutePath();
    }

    public final boolean zzb() {
        return Intrinsics.areEqual("mounted", Environment.getExternalStorageState());
    }

    private final boolean zzd(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @JvmStatic
    public static final File zza(String str) {
        if (zza.zzd(str)) {
            return null;
        }
        return new File(str);
    }

    public final boolean zzb(String str) {
        return zzb(zza(str));
    }

    public final boolean zzb(File file) {
        if (file != null) {
            if (file.exists() ? file.isDirectory() : file.mkdirs()) {
                return true;
            }
        }
        return false;
    }

    @JvmStatic
    public static final boolean zzc(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!zza.zzb(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final boolean zzd(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        String absolutePath = file.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
        return zzc(absolutePath);
    }

    @JvmStatic
    public static final boolean zzc(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        zzj zzjVar = zza;
        File zza2 = zza(filePath);
        if (zza2 == null) {
            return false;
        }
        if (zza2.exists()) {
            return true;
        }
        return zzjVar.zze(filePath);
    }

    private final boolean zze(String str) {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                Uri parse = Uri.parse(str);
                ContentResolver contentResolver = LDSdk.getApp().getContentResolver();
                Intrinsics.checkNotNullExpressionValue(contentResolver, "getApp().contentResolver");
                AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(parse, "r");
                if (openAssetFileDescriptor == null) {
                    return false;
                }
                try {
                    openAssetFileDescriptor.close();
                    return true;
                } catch (IOException unused) {
                    return true;
                }
            } catch (FileNotFoundException unused2) {
            }
        }
        return false;
    }

    public final boolean zze(File file) {
        if (file == null) {
            return false;
        }
        if ((file.exists() && !file.delete()) || !zzb(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean zzf(File file) {
        return zza(file, new FileFilter() { // from class: com.ld.sdk.util.zzj$$ExternalSyntheticLambda0
            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                boolean zzi;
                zzi = zzj.zzi(file2);
                return zzi;
            }
        });
    }

    private final boolean zza(File file, FileFilter fileFilter) {
        if (file == null || fileFilter == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            if (!(listFiles.length == 0)) {
                for (File file2 : listFiles) {
                    if (fileFilter.accept(file2)) {
                        if (file2.isFile()) {
                            if (!file2.delete()) {
                                return false;
                            }
                        } else if (file2.isDirectory() && !zzh(file2)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private final boolean zzh(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            if (true ^ (listFiles.length == 0)) {
                for (File file2 : listFiles) {
                    if (file2.isFile()) {
                        if (!file2.delete()) {
                            return false;
                        }
                    } else if (file2.isDirectory() && !zzh(file2)) {
                        return false;
                    }
                }
            }
        }
        return file.delete();
    }
}
