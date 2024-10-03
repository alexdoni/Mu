package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfi;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzx {
    private zzfi.zze zza;
    private Long zzb;
    private long zzc;
    private final /* synthetic */ zzt zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzfi.zze zza(String str, zzfi.zze zzeVar) {
        String zzg = zzeVar.zzg();
        List<zzfi.zzg> zzh = zzeVar.zzh();
        this.zzd.mo326g_();
        Long l = (Long) zzmz.zzb(zzeVar, "_eid");
        boolean z = l != null;
        if (z && zzg.equals("_ep")) {
            Preconditions.checkNotNull(l);
            this.zzd.mo326g_();
            zzg = (String) zzmz.zzb(zzeVar, "_en");
            if (TextUtils.isEmpty(zzg)) {
                this.zzd.zzj().zzm().zza("Extra parameter without an event name. eventId", l);
                return null;
            }
            if (this.zza == null || this.zzb == null || l.longValue() != this.zzb.longValue()) {
                Pair<zzfi.zze, Long> zza = this.zzd.zzh().zza(str, l);
                if (zza == null || zza.first == null) {
                    this.zzd.zzj().zzm().zza("Extra parameter without existing main event. eventName, eventId", zzg, l);
                    return null;
                }
                this.zza = (zzfi.zze) zza.first;
                this.zzc = ((Long) zza.second).longValue();
                this.zzd.mo326g_();
                this.zzb = (Long) zzmz.zzb(this.zza, "_eid");
            }
            long j = this.zzc - 1;
            this.zzc = j;
            if (j <= 0) {
                zzao zzh2 = this.zzd.zzh();
                zzh2.zzt();
                zzh2.zzj().zzp().zza("Clearing complex main event info. appId", str);
                try {
                    zzh2.m324e_().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                } catch (SQLiteException e) {
                    zzh2.zzj().zzg().zza("Error clearing complex main event", e);
                }
            } else {
                this.zzd.zzh().zza(str, l, this.zzc, this.zza);
            }
            ArrayList arrayList = new ArrayList();
            for (zzfi.zzg zzgVar : this.zza.zzh()) {
                this.zzd.mo326g_();
                if (zzmz.zza(zzeVar, zzgVar.zzg()) == null) {
                    arrayList.add(zzgVar);
                }
            }
            if (!arrayList.isEmpty()) {
                arrayList.addAll(zzh);
                zzh = arrayList;
            } else {
                this.zzd.zzj().zzm().zza("No unique parameters in main event. eventName", zzg);
            }
        } else if (z) {
            this.zzb = l;
            this.zza = zzeVar;
            this.zzd.mo326g_();
            Object zzb = zzmz.zzb(zzeVar, "_epc");
            long longValue = ((Long) (zzb != null ? zzb : 0L)).longValue();
            this.zzc = longValue;
            if (longValue <= 0) {
                this.zzd.zzj().zzm().zza("Complex event with zero extra param count. eventName", zzg);
            } else {
                this.zzd.zzh().zza(str, (Long) Preconditions.checkNotNull(l), this.zzc, zzeVar);
            }
        }
        return (zzfi.zze) ((com.google.android.gms.internal.measurement.zzix) zzeVar.zzby().zza(zzg).zzd().zza(zzh).zzab());
    }

    private zzx(zzt zztVar) {
        this.zzd = zztVar;
    }
}
