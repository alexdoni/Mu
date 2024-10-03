package com.p008ld.sdk.util;

import android.content.Context;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.util.zzo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LogFileUtils.kt */
/* loaded from: classes2.dex */
public final class zzo {
    private static Context zzc;
    private static zzo zzd;
    private static File zze;
    public static final zza zza = new zza(null);
    private static final zzp zzb = zzp.zza.zza();
    private static final SimpleDateFormat zzf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static int zzg = 5242880;

    public /* synthetic */ zzo(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private zzo() {
    }

    public final File zza() {
        String str;
        if (zzj.zza.zzb()) {
            if (LDSdk.getApp().getExternalFilesDir(null) != null) {
                StringBuilder sb = new StringBuilder();
                File externalFilesDir = LDSdk.getApp().getExternalFilesDir(null);
                sb.append(externalFilesDir != null ? externalFilesDir.getPath() : null);
                sb.append(File.separator);
                sb.append("ldsdk");
                str = sb.toString();
                boolean zzb2 = zzj.zza.zzb(str);
                File file = new File(str + File.separator + "ld-open-sdk.txt");
                LDLog.m573e("LogFileUtils -> isDirCreated = " + zzb2 + " ,isFileCreated = " + zzj.zzc(file) + " ,logFile = " + file.getPath());
                return file;
            }
        }
        str = LDSdk.getApp().getFilesDir().getPath() + File.separator + "ldsdk";
        boolean zzb22 = zzj.zza.zzb(str);
        File file2 = new File(str + File.separator + "ld-open-sdk.txt");
        LDLog.m573e("LogFileUtils -> isDirCreated = " + zzb22 + " ,isFileCreated = " + zzj.zzc(file2) + " ,logFile = " + file2.getPath());
        return file2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzi() {
        LDLog.m572d("LogFileUtils -> resetLogFile ... " + zzj.zza.zze(zze));
    }

    public final void zzb() {
        zzj zzjVar = zzj.zza;
        File file = zze;
        LDLog.m572d("LogFileUtils -> deleteLogFile ... " + zzjVar.zzf(file != null ? file.getParentFile() : null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zza(StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr != null) {
            return null;
        }
        return "[" + zzf.format(new Date()) + ']';
    }

    public final synchronized void zza(Object str) {
        File file;
        Intrinsics.checkNotNullParameter(str, "str");
        if (zzc != null && zzd != null && (file = zze) != null) {
            Intrinsics.checkNotNull(file);
            if (!file.exists()) {
                zzi();
            }
            zzb.zza(new zzb(str));
        }
    }

    /* compiled from: LogFileUtils.kt */
    /* loaded from: classes2.dex */
    private static final class zzb implements Runnable {
        private final Object zza;
        private final long zzb;

        public zzb(Object mStr) {
            Intrinsics.checkNotNullParameter(mStr, "mStr");
            this.zza = mStr;
            this.zzb = zza(zzo.zze);
        }

        @Override // java.lang.Runnable
        public void run() {
            zzo zza;
            if (zzo.zze != null) {
                if (this.zzb > zzo.zzg && (zza = zzo.zza.zza()) != null) {
                    zza.zzi();
                }
                try {
                    PrintWriter printWriter = new PrintWriter((Writer) new FileWriter(zzo.zze, true), true);
                    if (this.zza instanceof Throwable) {
                        zza(printWriter);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        zzo zza2 = zzo.zza.zza();
                        sb.append(zza2 != null ? zza2.zza((StackTraceElement[]) null) : null);
                        sb.append(" - ");
                        sb.append(this.zza);
                        printWriter.println(sb.toString());
                    }
                    printWriter.println("------>end of log");
                    printWriter.println();
                    printWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private final PrintWriter zza(PrintWriter printWriter) {
            printWriter.println("crash_time：" + zzo.zzf.format(new Date()));
            Object obj = this.zza;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Throwable");
            ((Throwable) obj).printStackTrace(printWriter);
            return printWriter;
        }

        private final long zza(File file) {
            if (file == null || !file.exists()) {
                return 0L;
            }
            return file.length();
        }
    }

    /* compiled from: LogFileUtils.kt */
    /* loaded from: classes2.dex */
    public static final class zza {
        public /* synthetic */ zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private zza() {
        }

        public final void zza(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (zzo.zzc != null && zzo.zzd != null && zzo.zze != null) {
                File file = zzo.zze;
                Intrinsics.checkNotNull(file);
                if (file.exists()) {
                    LDLog.m573e("LogFileUtils -> have been init...");
                    return;
                }
            }
            zzo.zzc = context.getApplicationContext();
            zzo.zzd = zza();
            zzo.zzb.zza(new Runnable() { // from class: com.ld.sdk.util.zzo$zza$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    zzo.zza.zzb();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void zzb() {
            zza zzaVar = zzo.zza;
            zzo zzoVar = zzo.zzd;
            Intrinsics.checkNotNull(zzoVar);
            zzo.zze = zzoVar.zza();
            if (zzo.zze != null) {
                File file = zzo.zze;
                Intrinsics.checkNotNull(file);
                if (file.exists()) {
                    File file2 = zzo.zze;
                    Intrinsics.checkNotNull(file2);
                    if (zzo.zzg < file2.length()) {
                        LDLog.m572d("LogFileUtils -> init reset log file");
                        zzo zzoVar2 = zzo.zzd;
                        Intrinsics.checkNotNull(zzoVar2);
                        zzoVar2.zzi();
                        return;
                    }
                    return;
                }
            }
            LDLog.m573e("LogFileUtils -> sLogFile is null ...");
        }

        public final zzo zza() {
            if (zzo.zzd == null) {
                synchronized (zzo.class) {
                    if (zzo.zzd == null) {
                        zza zzaVar = zzo.zza;
                        zzo.zzd = new zzo(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return zzo.zzd;
        }
    }
}
