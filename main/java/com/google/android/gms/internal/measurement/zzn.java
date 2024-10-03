package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzn extends zzap {
    private final zzac zza;

    @Override // com.google.android.gms.internal.measurement.zzap, com.google.android.gms.internal.measurement.zzaq
    public final zzaq zza(String str, zzh zzhVar, List<zzaq> list) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 21624207:
                if (str.equals("getEventName")) {
                    c = 0;
                    break;
                }
                break;
            case 45521504:
                if (str.equals("getTimestamp")) {
                    c = 1;
                    break;
                }
                break;
            case 146575578:
                if (str.equals("getParamValue")) {
                    c = 2;
                    break;
                }
                break;
            case 700587132:
                if (str.equals("getParams")) {
                    c = 3;
                    break;
                }
                break;
            case 920706790:
                if (str.equals("setParamValue")) {
                    c = 4;
                    break;
                }
                break;
            case 1570616835:
                if (str.equals("setEventName")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                zzg.zza("getEventName", 0, list);
                return new zzas(this.zza.zzb().zzb());
            case 1:
                zzg.zza("getTimestamp", 0, list);
                return new zzai(Double.valueOf(this.zza.zzb().zza()));
            case 2:
                zzg.zza("getParamValue", 1, list);
                return zzj.zza(this.zza.zzb().zza(zzhVar.zza(list.get(0)).zzf()));
            case 3:
                zzg.zza("getParams", 0, list);
                Map<String, Object> zzc = this.zza.zzb().zzc();
                zzap zzapVar = new zzap();
                for (String str2 : zzc.keySet()) {
                    zzapVar.zza(str2, zzj.zza(zzc.get(str2)));
                }
                return zzapVar;
            case 4:
                zzg.zza("setParamValue", 2, list);
                String zzf = zzhVar.zza(list.get(0)).zzf();
                zzaq zza = zzhVar.zza(list.get(1));
                this.zza.zzb().zza(zzf, zzg.zza(zza));
                return zza;
            case 5:
                zzg.zza("setEventName", 1, list);
                zzaq zza2 = zzhVar.zza(list.get(0));
                if (zzc.equals(zza2) || zzd.equals(zza2)) {
                    throw new IllegalArgumentException("Illegal event name");
                }
                this.zza.zzb().zzb(zza2.zzf());
                return new zzas(zza2.zzf());
            default:
                return super.zza(str, zzhVar, list);
        }
    }

    public zzn(zzac zzacVar) {
        this.zza = zzacVar;
    }
}
