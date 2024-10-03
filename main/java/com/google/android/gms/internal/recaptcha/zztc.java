package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zztc {
    private static final Class<?> zza;
    private static final zztr<?, ?> zzb;
    private static final zztr<?, ?> zzc;
    private static final zztr<?, ?> zzd;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
        zzb = zzab(false);
        zzc = zzab(true);
        zzd = new zztt();
    }

    public static zztr<?, ?> zzA() {
        return zzc;
    }

    public static zztr<?, ?> zzB() {
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB zzC(int i, List<Integer> list, zzrk zzrkVar, UB ub, zztr<UT, UB> zztrVar) {
        if (zzrkVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzrkVar.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = (UB) zzD(i, intValue, ub, zztrVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return ub;
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzrkVar.zza(intValue2)) {
                    ub = (UB) zzD(i, intValue2, ub, zztrVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB zzD(int i, int i2, UB ub, zztr<UT, UB> zztrVar) {
        if (ub == null) {
            ub = zztrVar.zzf();
        }
        zztrVar.zzl(ub, i, i2);
        return ub;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, FT extends zzqv<FT>> void zzE(zzqs<FT> zzqsVar, T t, T t2) {
        zzqsVar.zza(t2);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, UT, UB> void zzF(zztr<UT, UB> zztrVar, T t, T t2) {
        zztrVar.zzo(t, zztrVar.zze(zztrVar.zzd(t), zztrVar.zzd(t2)));
    }

    public static void zzG(Class<?> cls) {
        Class<?> cls2;
        if (!zzrg.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzH(int i, List<Boolean> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzc(i, list, z);
    }

    public static void zzI(int i, List<zzpy> list, zzuj zzujVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zze(i, list);
    }

    public static void zzJ(int i, List<Double> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzg(i, list, z);
    }

    public static void zzK(int i, List<Integer> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzj(i, list, z);
    }

    public static void zzL(int i, List<Integer> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzl(i, list, z);
    }

    public static void zzM(int i, List<Long> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzn(i, list, z);
    }

    public static void zzN(int i, List<Float> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzp(i, list, z);
    }

    public static void zzO(int i, List<?> list, zzuj zzujVar, zzta zztaVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzqk) zzujVar).zzq(i, list.get(i2), zztaVar);
        }
    }

    public static void zzP(int i, List<Integer> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzs(i, list, z);
    }

    public static void zzQ(int i, List<Long> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzu(i, list, z);
    }

    public static void zzR(int i, List<?> list, zzuj zzujVar, zzta zztaVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzqk) zzujVar).zzw(i, list.get(i2), zztaVar);
        }
    }

    public static void zzS(int i, List<Integer> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzy(i, list, z);
    }

    public static void zzT(int i, List<Long> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzA(i, list, z);
    }

    public static void zzU(int i, List<Integer> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzC(i, list, z);
    }

    public static void zzV(int i, List<Long> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzE(i, list, z);
    }

    public static void zzW(int i, List<String> list, zzuj zzujVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzH(i, list);
    }

    public static void zzX(int i, List<Integer> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzJ(i, list, z);
    }

    public static void zzY(int i, List<Long> list, zzuj zzujVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzujVar.zzL(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzZ(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzqj.zzK(i << 3) + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void zzaa(zzsi zzsiVar, T t, T t2, long j) {
        zzub.zzs(t, j, zzsi.zzb(zzub.zzf(t, j), zzub.zzf(t2, j)));
    }

    private static zztr<?, ?> zzab(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zztr) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(List<?> list) {
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i, List<zzpy> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzJ = size * zzqj.zzJ(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzJ += zzqj.zzA(list.get(i2));
        }
        return zzJ;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzqj.zzJ(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzrh) {
            zzrh zzrhVar = (zzrh) list;
            i = 0;
            while (i2 < size) {
                i += zzqj.zzD(zzrhVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzqj.zzD(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzqj.zzK(i << 3) + 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(List<?> list) {
        return list.size() * 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzqj.zzK(i << 3) + 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(List<?> list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(int i, List<zzsn> list, zzta zztaVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzqj.zzB(i, list.get(i3), zztaVar);
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzqj.zzJ(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzrh) {
            zzrh zzrhVar = (zzrh) list;
            i = 0;
            while (i2 < size) {
                i += zzqj.zzD(zzrhVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzqj.zzD(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzm(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzqj.zzJ(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzn(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzsb) {
            zzsb zzsbVar = (zzsb) list;
            i = 0;
            while (i2 < size) {
                i += zzqj.zzL(zzsbVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzqj.zzL(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(int i, Object obj, zzta zztaVar) {
        if (obj instanceof zzru) {
            int zzK = zzqj.zzK(i << 3);
            int zza2 = ((zzru) obj).zza();
            return zzK + zzqj.zzK(zza2) + zza2;
        }
        return zzqj.zzK(i << 3) + zzqj.zzG((zzsn) obj, zztaVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int i, List<?> list, zzta zztaVar) {
        int zzG;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzJ = zzqj.zzJ(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzru) {
                zzG = zzqj.zzE((zzru) obj);
            } else {
                zzG = zzqj.zzG((zzsn) obj, zztaVar);
            }
            zzJ += zzG;
        }
        return zzJ;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzqj.zzJ(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzrh) {
            zzrh zzrhVar = (zzrh) list;
            i = 0;
            while (i2 < size) {
                int zze = zzrhVar.zze(i2);
                i += zzqj.zzK((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                int intValue = list.get(i2).intValue();
                i += zzqj.zzK((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzs(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzqj.zzJ(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzsb) {
            zzsb zzsbVar = (zzsb) list;
            i = 0;
            while (i2 < size) {
                long zze = zzsbVar.zze(i2);
                i += zzqj.zzL((zze >> 63) ^ (zze + zze));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                long longValue = list.get(i2).longValue();
                i += zzqj.zzL((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(int i, List<?> list) {
        int zzI;
        int zzI2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzJ = zzqj.zzJ(i) * size;
        if (list instanceof zzrw) {
            zzrw zzrwVar = (zzrw) list;
            while (i2 < size) {
                Object zzf = zzrwVar.zzf(i2);
                if (zzf instanceof zzpy) {
                    zzI2 = zzqj.zzA((zzpy) zzf);
                } else {
                    zzI2 = zzqj.zzI((String) zzf);
                }
                zzJ += zzI2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzpy) {
                    zzI = zzqj.zzA((zzpy) obj);
                } else {
                    zzI = zzqj.zzI((String) obj);
                }
                zzJ += zzI;
                i2++;
            }
        }
        return zzJ;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzqj.zzJ(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzrh) {
            zzrh zzrhVar = (zzrh) list;
            i = 0;
            while (i2 < size) {
                i += zzqj.zzK(zzrhVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzqj.zzK(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzqj.zzJ(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzy(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzsb) {
            zzsb zzsbVar = (zzsb) list;
            i = 0;
            while (i2 < size) {
                i += zzqj.zzL(zzsbVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzqj.zzL(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zztr<?, ?> zzz() {
        return zzb;
    }
}
