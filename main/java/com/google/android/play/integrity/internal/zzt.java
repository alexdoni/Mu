package com.google.android.play.integrity.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
public final class zzt {
    private static final Map zza = new HashMap();
    private final Context zzb;
    private final zzi zzc;
    private boolean zzh;
    private final Intent zzi;
    private ServiceConnection zzm;
    private IInterface zzn;
    private final com.google.android.play.core.integrity.zzq zzo;
    private final List zze = new ArrayList();
    private final Set zzf = new HashSet();
    private final Object zzg = new Object();
    private final IBinder.DeathRecipient zzk = new IBinder.DeathRecipient() { // from class: com.google.android.play.integrity.internal.zzk
        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            zzt.zzh(zzt.this);
        }
    };
    private final AtomicInteger zzl = new AtomicInteger(0);
    private final String zzd = "IntegrityService";
    private final WeakReference zzj = new WeakReference(null);

    public zzt(Context context, zzi zziVar, String str, Intent intent, com.google.android.play.core.integrity.zzq zzqVar, zzo zzoVar, byte[] bArr) {
        this.zzb = context;
        this.zzc = zziVar;
        this.zzi = intent;
        this.zzo = zzqVar;
    }

    public static /* synthetic */ void zzh(zzt zztVar) {
        zztVar.zzc.zzd("reportBinderDeath", new Object[0]);
        zzo zzoVar = (zzo) zztVar.zzj.get();
        if (zzoVar == null) {
            zztVar.zzc.zzd("%s : Binder has died.", zztVar.zzd);
            Iterator it = zztVar.zze.iterator();
            while (it.hasNext()) {
                ((zzj) it.next()).zza(zztVar.zzs());
            }
            zztVar.zze.clear();
        } else {
            zztVar.zzc.zzd("calling onBinderDied", new Object[0]);
            zzoVar.zza();
        }
        zztVar.zzt();
    }

    public static /* bridge */ /* synthetic */ void zzn(zzt zztVar) {
        zztVar.zzc.zzd("linkToDeath", new Object[0]);
        try {
            zztVar.zzn.asBinder().linkToDeath(zztVar.zzk, 0);
        } catch (RemoteException e) {
            zztVar.zzc.zzc(e, "linkToDeath failed", new Object[0]);
        }
    }

    public static /* bridge */ /* synthetic */ void zzo(zzt zztVar) {
        zztVar.zzc.zzd("unlinkToDeath", new Object[0]);
        zztVar.zzn.asBinder().unlinkToDeath(zztVar.zzk, 0);
    }

    public final void zzt() {
        synchronized (this.zzg) {
            Iterator it = this.zzf.iterator();
            while (it.hasNext()) {
                ((TaskCompletionSource) it.next()).trySetException(zzs());
            }
            this.zzf.clear();
        }
    }

    public final Handler zzc() {
        Handler handler;
        Map map = zza;
        synchronized (map) {
            if (!map.containsKey(this.zzd)) {
                HandlerThread handlerThread = new HandlerThread(this.zzd, 10);
                handlerThread.start();
                map.put(this.zzd, new Handler(handlerThread.getLooper()));
            }
            handler = (Handler) map.get(this.zzd);
        }
        return handler;
    }

    public final IInterface zze() {
        return this.zzn;
    }

    public final void zzp(zzj zzjVar, final TaskCompletionSource taskCompletionSource) {
        synchronized (this.zzg) {
            this.zzf.add(taskCompletionSource);
            taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.play.integrity.internal.zzl
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    zzt.this.zzq(taskCompletionSource, task);
                }
            });
        }
        synchronized (this.zzg) {
            if (this.zzl.getAndIncrement() > 0) {
                this.zzc.zza("Already connected to the service.", new Object[0]);
            }
        }
        zzc().post(new zzm(this, zzjVar.zzc(), zzjVar));
    }

    public final /* synthetic */ void zzq(TaskCompletionSource taskCompletionSource, Task task) {
        synchronized (this.zzg) {
            this.zzf.remove(taskCompletionSource);
        }
    }

    public final void zzr(TaskCompletionSource taskCompletionSource) {
        synchronized (this.zzg) {
            this.zzf.remove(taskCompletionSource);
        }
        synchronized (this.zzg) {
            if (this.zzl.get() <= 0 || this.zzl.decrementAndGet() <= 0) {
                zzc().post(new zzn(this));
            } else {
                this.zzc.zzd("Leaving the connection open for other ongoing calls.", new Object[0]);
            }
        }
    }

    private final RemoteException zzs() {
        return new RemoteException(String.valueOf(this.zzd).concat(" : Binder has died."));
    }

    public static /* bridge */ /* synthetic */ void zzm(zzt zztVar, zzj zzjVar) {
        if (zztVar.zzn != null || zztVar.zzh) {
            if (zztVar.zzh) {
                zztVar.zzc.zzd("Waiting to bind to the service.", new Object[0]);
                zztVar.zze.add(zzjVar);
                return;
            } else {
                zzjVar.run();
                return;
            }
        }
        zztVar.zzc.zzd("Initiate binding to the service.", new Object[0]);
        zztVar.zze.add(zzjVar);
        zzs zzsVar = new zzs(zztVar, null);
        zztVar.zzm = zzsVar;
        zztVar.zzh = true;
        if (zztVar.zzb.bindService(zztVar.zzi, zzsVar, 1)) {
            return;
        }
        zztVar.zzc.zzd("Failed to bind to the service.", new Object[0]);
        zztVar.zzh = false;
        Iterator it = zztVar.zze.iterator();
        while (it.hasNext()) {
            ((zzj) it.next()).zza(new zzu());
        }
        zztVar.zze.clear();
    }
}
