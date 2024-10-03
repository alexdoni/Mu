package com.google.android.gms.internal.recaptcha;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzdn extends zzrg<zzdn, zzdm> implements zzso {
    private static final zzrm<Integer, zzvj> zzb = new zzdl();
    private static final zzdn zze;
    private static volatile zzsu<zzdn> zzf;
    private zztp zzh;
    private zzvf zzk;
    private String zzg = "";
    private zzro<String> zzi = zzrg.zzz();
    private zzrl zzj = zzy();

    static {
        zzdn zzdnVar = new zzdn();
        zze = zzdnVar;
        zzrg.zzD(zzdn.class, zzdnVar);
    }

    private zzdn() {
    }

    public static zzdm zza() {
        return zze.zzu();
    }

    public static zzdn zzc() {
        return zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzj(zzdn zzdnVar, Iterable iterable) {
        zzro<String> zzroVar = zzdnVar.zzi;
        if (!zzroVar.zzc()) {
            int size = zzroVar.size();
            zzdnVar.zzi = zzroVar.zzd(size == 0 ? 10 : size + size);
        }
        zzph.zzq(iterable, zzdnVar.zzi);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzk(zzdn zzdnVar, Iterable iterable) {
        zzrl zzrlVar = zzdnVar.zzj;
        if (!zzrlVar.zzc()) {
            int size = zzrlVar.size();
            zzdnVar.zzj = zzrlVar.zzd(size == 0 ? 10 : size + size);
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            zzdnVar.zzj.zzh(((zzvj) it.next()).zza());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzl(zzdn zzdnVar, zzvf zzvfVar) {
        zzvfVar.getClass();
        zzdnVar.zzk = zzvfVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzm(zzdn zzdnVar, String str) {
        str.getClass();
        zzdnVar.zzg = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzn(zzdn zzdnVar, zztp zztpVar) {
        zztpVar.getClass();
        zzdnVar.zzh = zztpVar;
    }

    public final zztp zzd() {
        zztp zztpVar = this.zzh;
        return zztpVar == null ? zztp.zzi() : zztpVar;
    }

    public final zzvf zze() {
        zzvf zzvfVar = this.zzk;
        return zzvfVar == null ? zzvf.zzk() : zzvfVar;
    }

    public final String zzf() {
        return this.zzg;
    }

    public final List<String> zzg() {
        return this.zzi;
    }

    public final List<zzvj> zzi() {
        return new zzrn(this.zzj, zzb);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zze, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0002\u0000\u0001Ȉ\u0002\t\u0003Ț\u0004,\u0005\t", new Object[]{"zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        if (i2 == 3) {
            return new zzdn();
        }
        zzdg zzdgVar = null;
        if (i2 == 4) {
            return new zzdm(zzdgVar);
        }
        if (i2 == 5) {
            return zze;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzdn> zzsuVar = zzf;
        if (zzsuVar == null) {
            synchronized (zzdn.class) {
                zzsuVar = zzf;
                if (zzsuVar == null) {
                    zzsuVar = new zzrc<>(zze);
                    zzf = zzsuVar;
                }
            }
        }
        return zzsuVar;
    }
}
