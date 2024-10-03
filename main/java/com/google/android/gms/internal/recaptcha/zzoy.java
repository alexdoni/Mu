package com.google.android.gms.internal.recaptcha;

import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzoy implements Runnable {

    @CheckForNull
    Runnable zza;
    final /* synthetic */ zzoz zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzoy(zzoz zzozVar, zzox zzoxVar) {
        this.zzb = zzozVar;
    }

    public final String toString() {
        int i;
        Runnable runnable = this.zza;
        if (runnable != null) {
            String valueOf = String.valueOf(runnable);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 34);
            sb.append("SequentialExecutorWorker{running=");
            sb.append(valueOf);
            sb.append("}");
            return sb.toString();
        }
        i = this.zzb.zzf;
        String str = i != 1 ? i != 2 ? i != 3 ? i != 4 ? JsonSerializer.Null : DebugCoroutineInfoImplKt.RUNNING : "QUEUED" : "QUEUING" : "IDLE";
        StringBuilder sb2 = new StringBuilder(str.length() + 32);
        sb2.append("SequentialExecutorWorker{state=");
        sb2.append(str);
        sb2.append("}");
        return sb2.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x004f, code lost:
    
        r1 = r1 | java.lang.Thread.interrupted();
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0051, code lost:
    
        r11.zza.run();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005e, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0060, code lost:
    
        r4 = com.google.android.gms.internal.recaptcha.zzoz.zza;
        r5 = java.util.logging.Level.SEVERE;
        r3 = java.lang.String.valueOf(r11.zza);
        r10 = new java.lang.StringBuilder(java.lang.String.valueOf(r3).length() + 35);
        r10.append("Exception while executing runnable ");
        r10.append(r3);
        r4.logp(r5, "com.google.common.util.concurrent.SequentialExecutor$QueueWorker", "workOnQueue", r10.toString(), (java.lang.Throwable) r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x008e, code lost:
    
        r11.zza = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005c, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0091, code lost:
    
        r11.zza = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0093, code lost:
    
        throw r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0049, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
    
        return;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r11 = this;
            r0 = 0
            r1 = r0
        L2:
            r2 = 1
            com.google.android.gms.internal.recaptcha.zzoz r3 = r11.zzb     // Catch: java.lang.Throwable -> L5a
            java.util.Deque r3 = com.google.android.gms.internal.recaptcha.zzoz.zzb(r3)     // Catch: java.lang.Throwable -> L5a
            monitor-enter(r3)     // Catch: java.lang.Throwable -> L5a
            if (r0 != 0) goto L2a
            com.google.android.gms.internal.recaptcha.zzoz r0 = r11.zzb     // Catch: java.lang.Throwable -> L94
            int r0 = com.google.android.gms.internal.recaptcha.zzoz.zzd(r0)     // Catch: java.lang.Throwable -> L94
            r4 = 4
            if (r0 != r4) goto L20
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L94
            if (r1 == 0) goto L49
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch: java.lang.Error -> La1
            r0.interrupt()     // Catch: java.lang.Error -> La1
            return
        L20:
            com.google.android.gms.internal.recaptcha.zzoz r0 = r11.zzb     // Catch: java.lang.Throwable -> L94
            com.google.android.gms.internal.recaptcha.zzoz.zza(r0)     // Catch: java.lang.Throwable -> L94
            com.google.android.gms.internal.recaptcha.zzoz r0 = r11.zzb     // Catch: java.lang.Throwable -> L94
            com.google.android.gms.internal.recaptcha.zzoz.zze(r0, r4)     // Catch: java.lang.Throwable -> L94
        L2a:
            com.google.android.gms.internal.recaptcha.zzoz r0 = r11.zzb     // Catch: java.lang.Throwable -> L94
            java.util.Deque r0 = com.google.android.gms.internal.recaptcha.zzoz.zzb(r0)     // Catch: java.lang.Throwable -> L94
            java.lang.Object r0 = r0.poll()     // Catch: java.lang.Throwable -> L94
            java.lang.Runnable r0 = (java.lang.Runnable) r0     // Catch: java.lang.Throwable -> L94
            r11.zza = r0     // Catch: java.lang.Throwable -> L94
            if (r0 != 0) goto L4a
            com.google.android.gms.internal.recaptcha.zzoz r0 = r11.zzb     // Catch: java.lang.Throwable -> L94
            com.google.android.gms.internal.recaptcha.zzoz.zze(r0, r2)     // Catch: java.lang.Throwable -> L94
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L94
            if (r1 == 0) goto L49
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch: java.lang.Error -> La1
            r0.interrupt()     // Catch: java.lang.Error -> La1
        L49:
            return
        L4a:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L94
            boolean r0 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L5a
            r1 = r1 | r0
            r0 = 0
            java.lang.Runnable r3 = r11.zza     // Catch: java.lang.Throwable -> L5c java.lang.RuntimeException -> L5e
            r3.run()     // Catch: java.lang.Throwable -> L5c java.lang.RuntimeException -> L5e
            r11.zza = r0     // Catch: java.lang.Throwable -> L5a
        L58:
            r0 = r2
            goto L2
        L5a:
            r0 = move-exception
            goto L97
        L5c:
            r3 = move-exception
            goto L91
        L5e:
            r3 = move-exception
            r9 = r3
            java.util.logging.Logger r4 = com.google.android.gms.internal.recaptcha.zzoz.zzc()     // Catch: java.lang.Throwable -> L5c
            java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L5c
            java.lang.String r6 = "com.google.common.util.concurrent.SequentialExecutor$QueueWorker"
            java.lang.String r7 = "workOnQueue"
            java.lang.Runnable r3 = r11.zza     // Catch: java.lang.Throwable -> L5c
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r8 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L5c
            int r8 = r8.length()     // Catch: java.lang.Throwable -> L5c
            int r8 = r8 + 35
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5c
            r10.<init>(r8)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r8 = "Exception while executing runnable "
            r10.append(r8)     // Catch: java.lang.Throwable -> L5c
            r10.append(r3)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r8 = r10.toString()     // Catch: java.lang.Throwable -> L5c
            r4.logp(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L5c
            r11.zza = r0     // Catch: java.lang.Throwable -> L5a
            goto L58
        L91:
            r11.zza = r0     // Catch: java.lang.Throwable -> L5a
            throw r3     // Catch: java.lang.Throwable -> L5a
        L94:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L94
            throw r0     // Catch: java.lang.Throwable -> L5a
        L97:
            if (r1 == 0) goto La0
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch: java.lang.Error -> La1
            r1.interrupt()     // Catch: java.lang.Error -> La1
        La0:
            throw r0     // Catch: java.lang.Error -> La1
        La1:
            r0 = move-exception
            com.google.android.gms.internal.recaptcha.zzoz r1 = r11.zzb
            java.util.Deque r1 = com.google.android.gms.internal.recaptcha.zzoz.zzb(r1)
            monitor-enter(r1)
            com.google.android.gms.internal.recaptcha.zzoz r3 = r11.zzb     // Catch: java.lang.Throwable -> Lb0
            com.google.android.gms.internal.recaptcha.zzoz.zze(r3, r2)     // Catch: java.lang.Throwable -> Lb0
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lb0
            throw r0
        Lb0:
            r0 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lb0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.recaptcha.zzoy.run():void");
    }
}
