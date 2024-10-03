package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzsq<T> implements zzta<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzub.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzsn zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final int[] zzk;
    private final int zzl;
    private final int zzm;
    private final zzsa zzn;
    private final zztr<?, ?> zzo;
    private final zzqs<?> zzp;
    private final zzss zzq;
    private final zzsi zzr;

    /* JADX WARN: Multi-variable type inference failed */
    private zzsq(int[] iArr, int[] iArr2, Object[] objArr, int i, int i2, zzsn zzsnVar, boolean z, boolean z2, int[] iArr3, int i3, int i4, zzss zzssVar, zzsa zzsaVar, zztr<?, ?> zztrVar, zzqs<?> zzqsVar, zzsi zzsiVar) {
        this.zzc = iArr;
        this.zzd = iArr2;
        this.zze = objArr;
        this.zzf = i;
        this.zzi = i2 instanceof zzrg;
        this.zzj = zzsnVar;
        boolean z3 = false;
        if (zztrVar != 0 && zztrVar.zzh(i2)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzk = z2;
        this.zzl = iArr3;
        this.zzm = i3;
        this.zzq = i4;
        this.zzn = zzssVar;
        this.zzo = zzsaVar;
        this.zzp = zztrVar;
        this.zzg = i2;
        this.zzr = zzqsVar;
    }

    private final void zzA(T t, T t2, int i) {
        int zzs = zzs(i);
        int i2 = this.zzc[i];
        long j = zzs & 1048575;
        if (zzL(t2, i2, i)) {
            Object zzf = zzL(t, i2, i) ? zzub.zzf(t, j) : null;
            Object zzf2 = zzub.zzf(t2, j);
            if (zzf != null && zzf2 != null) {
                zzub.zzs(t, j, zzrp.zzg(zzf, zzf2));
                zzD(t, i2, i);
            } else if (zzf2 != null) {
                zzub.zzs(t, j, zzf2);
                zzD(t, i2, i);
            }
        }
    }

    private final void zzB(Object obj, int i, zzsz zzszVar) throws IOException {
        if (zzH(i)) {
            zzub.zzs(obj, i & 1048575, zzszVar.zzu());
        } else if (!this.zzi) {
            zzub.zzs(obj, i & 1048575, zzszVar.zzp());
        } else {
            zzub.zzs(obj, i & 1048575, zzszVar.zzt());
        }
    }

    private final void zzC(T t, int i) {
        int zzq = zzq(i);
        long j = 1048575 & zzq;
        if (j == 1048575) {
            return;
        }
        zzub.zzq(t, j, (1 << (zzq >>> 20)) | zzub.zzc(t, j));
    }

    private final void zzD(T t, int i, int i2) {
        zzub.zzq(t, zzq(i2) & 1048575, i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x0040. Please report as an issue. */
    private final void zzE(T t, zzuj zzujVar) throws IOException {
        int i;
        boolean z;
        if (!this.zzh) {
            int length = this.zzc.length;
            Unsafe unsafe = zzb;
            int i2 = 1048575;
            int i3 = 1048575;
            int i4 = 0;
            int i5 = 0;
            while (i4 < length) {
                int zzs = zzs(i4);
                int i6 = this.zzc[i4];
                int zzr = zzr(zzs);
                if (zzr <= 17) {
                    int i7 = this.zzc[i4 + 2];
                    int i8 = i7 & i2;
                    if (i8 != i3) {
                        i5 = unsafe.getInt(t, i8);
                        i3 = i8;
                    }
                    i = 1 << (i7 >>> 20);
                } else {
                    i = 0;
                }
                long j = zzs & i2;
                switch (zzr) {
                    case 0:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzf(i6, zzub.zza(t, j));
                            break;
                        }
                    case 1:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzo(i6, zzub.zzb(t, j));
                            break;
                        }
                    case 2:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzt(i6, unsafe.getLong(t, j));
                            break;
                        }
                    case 3:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzK(i6, unsafe.getLong(t, j));
                            break;
                        }
                    case 4:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzr(i6, unsafe.getInt(t, j));
                            break;
                        }
                    case 5:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzm(i6, unsafe.getLong(t, j));
                            break;
                        }
                    case 6:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzk(i6, unsafe.getInt(t, j));
                            break;
                        }
                    case 7:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzb(i6, zzub.zzw(t, j));
                            break;
                        }
                    case 8:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzN(i6, unsafe.getObject(t, j), zzujVar);
                            break;
                        }
                    case 9:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzw(i6, unsafe.getObject(t, j), zzv(i4));
                            break;
                        }
                    case 10:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzd(i6, (zzpy) unsafe.getObject(t, j));
                            break;
                        }
                    case 11:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzI(i6, unsafe.getInt(t, j));
                            break;
                        }
                    case 12:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzi(i6, unsafe.getInt(t, j));
                            break;
                        }
                    case 13:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzx(i6, unsafe.getInt(t, j));
                            break;
                        }
                    case 14:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzz(i6, unsafe.getLong(t, j));
                            break;
                        }
                    case 15:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzB(i6, unsafe.getInt(t, j));
                            break;
                        }
                    case 16:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzD(i6, unsafe.getLong(t, j));
                            break;
                        }
                    case 17:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzujVar.zzq(i6, unsafe.getObject(t, j), zzv(i4));
                            break;
                        }
                    case 18:
                        zztc.zzJ(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 19:
                        zztc.zzN(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 20:
                        zztc.zzQ(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 21:
                        zztc.zzY(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 22:
                        zztc.zzP(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 23:
                        zztc.zzM(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 24:
                        zztc.zzL(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 25:
                        zztc.zzH(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 26:
                        zztc.zzW(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar);
                        break;
                    case 27:
                        zztc.zzR(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, zzv(i4));
                        break;
                    case 28:
                        zztc.zzI(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar);
                        break;
                    case 29:
                        z = false;
                        zztc.zzX(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 30:
                        z = false;
                        zztc.zzK(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 31:
                        z = false;
                        zztc.zzS(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 32:
                        z = false;
                        zztc.zzT(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 33:
                        z = false;
                        zztc.zzU(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 34:
                        z = false;
                        zztc.zzV(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, false);
                        break;
                    case 35:
                        zztc.zzJ(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 36:
                        zztc.zzN(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 37:
                        zztc.zzQ(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 38:
                        zztc.zzY(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 39:
                        zztc.zzP(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 40:
                        zztc.zzM(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 41:
                        zztc.zzL(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 42:
                        zztc.zzH(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 43:
                        zztc.zzX(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 44:
                        zztc.zzK(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 45:
                        zztc.zzS(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 46:
                        zztc.zzT(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 47:
                        zztc.zzU(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 48:
                        zztc.zzV(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, true);
                        break;
                    case 49:
                        zztc.zzO(this.zzc[i4], (List) unsafe.getObject(t, j), zzujVar, zzv(i4));
                        break;
                    case 50:
                        zzF(zzujVar, i6, unsafe.getObject(t, j), i4);
                        break;
                    case 51:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzf(i6, zzl(t, j));
                        }
                        break;
                    case 52:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzo(i6, zzm(t, j));
                        }
                        break;
                    case 53:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzt(i6, zzt(t, j));
                        }
                        break;
                    case 54:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzK(i6, zzt(t, j));
                        }
                        break;
                    case 55:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzr(i6, zzp(t, j));
                        }
                        break;
                    case 56:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzm(i6, zzt(t, j));
                        }
                        break;
                    case 57:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzk(i6, zzp(t, j));
                        }
                        break;
                    case 58:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzb(i6, zzM(t, j));
                        }
                        break;
                    case 59:
                        if (zzL(t, i6, i4)) {
                            zzN(i6, unsafe.getObject(t, j), zzujVar);
                        }
                        break;
                    case 60:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzw(i6, unsafe.getObject(t, j), zzv(i4));
                        }
                        break;
                    case 61:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzd(i6, (zzpy) unsafe.getObject(t, j));
                        }
                        break;
                    case 62:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzI(i6, zzp(t, j));
                        }
                        break;
                    case 63:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzi(i6, zzp(t, j));
                        }
                        break;
                    case 64:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzx(i6, zzp(t, j));
                        }
                        break;
                    case 65:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzz(i6, zzt(t, j));
                        }
                        break;
                    case 66:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzB(i6, zzp(t, j));
                        }
                        break;
                    case 67:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzD(i6, zzt(t, j));
                        }
                        break;
                    case 68:
                        if (zzL(t, i6, i4)) {
                            zzujVar.zzq(i6, unsafe.getObject(t, j), zzv(i4));
                        }
                        break;
                }
                i4 += 3;
                i2 = 1048575;
            }
            zztr<?, ?> zztrVar = this.zzo;
            zztrVar.zzp(zztrVar.zzd(t), zzujVar);
            return;
        }
        this.zzp.zza(t);
        throw null;
    }

    private final <K, V> void zzF(zzuj zzujVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzujVar.zzv(i, ((zzsg) zzx(i2)).zzc(), (zzsh) obj);
        }
    }

    private final boolean zzG(T t, T t2, int i) {
        return zzI(t, i) == zzI(t2, i);
    }

    private static boolean zzH(int i) {
        return (i & PKIFailureInfo.duplicateCertReq) != 0;
    }

    private final boolean zzI(T t, int i) {
        int zzq = zzq(i);
        long j = zzq & 1048575;
        if (j != 1048575) {
            return (zzub.zzc(t, j) & (1 << (zzq >>> 20))) != 0;
        }
        int zzs = zzs(i);
        long j2 = zzs & 1048575;
        switch (zzr(zzs)) {
            case 0:
                return zzub.zza(t, j2) != 0.0d;
            case 1:
                return zzub.zzb(t, j2) != 0.0f;
            case 2:
                return zzub.zzd(t, j2) != 0;
            case 3:
                return zzub.zzd(t, j2) != 0;
            case 4:
                return zzub.zzc(t, j2) != 0;
            case 5:
                return zzub.zzd(t, j2) != 0;
            case 6:
                return zzub.zzc(t, j2) != 0;
            case 7:
                return zzub.zzw(t, j2);
            case 8:
                Object zzf = zzub.zzf(t, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                }
                if (zzf instanceof zzpy) {
                    return !zzpy.zzb.equals(zzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzub.zzf(t, j2) != null;
            case 10:
                return !zzpy.zzb.equals(zzub.zzf(t, j2));
            case 11:
                return zzub.zzc(t, j2) != 0;
            case 12:
                return zzub.zzc(t, j2) != 0;
            case 13:
                return zzub.zzc(t, j2) != 0;
            case 14:
                return zzub.zzd(t, j2) != 0;
            case 15:
                return zzub.zzc(t, j2) != 0;
            case 16:
                return zzub.zzd(t, j2) != 0;
            case 17:
                return zzub.zzf(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzJ(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzI(t, i);
        }
        return (i3 & i4) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zzK(Object obj, int i, zzta zztaVar) {
        return zztaVar.zzi(zzub.zzf(obj, i & 1048575));
    }

    private final boolean zzL(T t, int i, int i2) {
        return zzub.zzc(t, (long) (zzq(i2) & 1048575)) == i;
    }

    private static <T> boolean zzM(T t, long j) {
        return ((Boolean) zzub.zzf(t, j)).booleanValue();
    }

    private static final void zzN(int i, Object obj, zzuj zzujVar) throws IOException {
        if (obj instanceof String) {
            zzujVar.zzG(i, (String) obj);
        } else {
            zzujVar.zzd(i, (zzpy) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> zzsq<T> zzj(Class<T> cls, zzsk zzskVar, zzss zzssVar, zzsa zzsaVar, zztr<?, ?> zztrVar, zzqs<?> zzqsVar, zzsi zzsiVar) {
        if (zzskVar instanceof zzsy) {
            return zzk((zzsy) zzskVar, zzssVar, zzsaVar, zztrVar, zzqsVar, zzsiVar);
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0261  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static <T> com.google.android.gms.internal.recaptcha.zzsq<T> zzk(com.google.android.gms.internal.recaptcha.zzsy r34, com.google.android.gms.internal.recaptcha.zzss r35, com.google.android.gms.internal.recaptcha.zzsa r36, com.google.android.gms.internal.recaptcha.zztr<?, ?> r37, com.google.android.gms.internal.recaptcha.zzqs<?> r38, com.google.android.gms.internal.recaptcha.zzsi r39) {
        /*
            Method dump skipped, instructions count: 1017
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.recaptcha.zzsq.zzk(com.google.android.gms.internal.recaptcha.zzsy, com.google.android.gms.internal.recaptcha.zzss, com.google.android.gms.internal.recaptcha.zzsa, com.google.android.gms.internal.recaptcha.zztr, com.google.android.gms.internal.recaptcha.zzqs, com.google.android.gms.internal.recaptcha.zzsi):com.google.android.gms.internal.recaptcha.zzsq");
    }

    private static <T> double zzl(T t, long j) {
        return ((Double) zzub.zzf(t, j)).doubleValue();
    }

    private static <T> float zzm(T t, long j) {
        return ((Float) zzub.zzf(t, j)).floatValue();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x003a. Please report as an issue. */
    private final int zzn(T t) {
        int i;
        int zzK;
        int zzK2;
        int zzK3;
        int zzL;
        int zzK4;
        int zzD;
        int zzK5;
        int zzK6;
        int zzd;
        int zzK7;
        int zzo;
        int zzJ;
        int zzK8;
        int i2;
        Unsafe unsafe = zzb;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        for (int i6 = 0; i6 < this.zzc.length; i6 += 3) {
            int zzs = zzs(i6);
            int i7 = this.zzc[i6];
            int zzr = zzr(zzs);
            if (zzr <= 17) {
                int i8 = this.zzc[i6 + 2];
                int i9 = i8 & 1048575;
                i = 1 << (i8 >>> 20);
                if (i9 != i5) {
                    i4 = unsafe.getInt(t, i9);
                    i5 = i9;
                }
            } else {
                i = 0;
            }
            long j = zzs & 1048575;
            switch (zzr) {
                case 0:
                    if ((i4 & i) != 0) {
                        zzK = zzqj.zzK(i7 << 3);
                        zzo = zzK + 8;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if ((i4 & i) != 0) {
                        zzK2 = zzqj.zzK(i7 << 3);
                        zzo = zzK2 + 4;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if ((i4 & i) != 0) {
                        long j2 = unsafe.getLong(t, j);
                        zzK3 = zzqj.zzK(i7 << 3);
                        zzL = zzqj.zzL(j2);
                        zzo = zzK3 + zzL;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if ((i4 & i) != 0) {
                        long j3 = unsafe.getLong(t, j);
                        zzK3 = zzqj.zzK(i7 << 3);
                        zzL = zzqj.zzL(j3);
                        zzo = zzK3 + zzL;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if ((i4 & i) != 0) {
                        int i10 = unsafe.getInt(t, j);
                        zzK4 = zzqj.zzK(i7 << 3);
                        zzD = zzqj.zzD(i10);
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if ((i4 & i) != 0) {
                        zzK = zzqj.zzK(i7 << 3);
                        zzo = zzK + 8;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if ((i4 & i) != 0) {
                        zzK2 = zzqj.zzK(i7 << 3);
                        zzo = zzK2 + 4;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if ((i4 & i) != 0) {
                        zzK5 = zzqj.zzK(i7 << 3);
                        zzo = zzK5 + 1;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(t, j);
                        if (object instanceof zzpy) {
                            zzK6 = zzqj.zzK(i7 << 3);
                            zzd = ((zzpy) object).zzd();
                            zzK7 = zzqj.zzK(zzd);
                            i2 = zzK6 + zzK7 + zzd;
                            i3 += i2;
                            break;
                        } else {
                            zzK4 = zzqj.zzK(i7 << 3);
                            zzD = zzqj.zzI((String) object);
                            i2 = zzK4 + zzD;
                            i3 += i2;
                        }
                    }
                case 9:
                    if ((i4 & i) != 0) {
                        zzo = zztc.zzo(i7, unsafe.getObject(t, j), zzv(i6));
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if ((i4 & i) != 0) {
                        zzpy zzpyVar = (zzpy) unsafe.getObject(t, j);
                        zzK6 = zzqj.zzK(i7 << 3);
                        zzd = zzpyVar.zzd();
                        zzK7 = zzqj.zzK(zzd);
                        i2 = zzK6 + zzK7 + zzd;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if ((i4 & i) != 0) {
                        int i11 = unsafe.getInt(t, j);
                        zzK4 = zzqj.zzK(i7 << 3);
                        zzD = zzqj.zzK(i11);
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if ((i4 & i) != 0) {
                        int i12 = unsafe.getInt(t, j);
                        zzK4 = zzqj.zzK(i7 << 3);
                        zzD = zzqj.zzD(i12);
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if ((i4 & i) != 0) {
                        zzK2 = zzqj.zzK(i7 << 3);
                        zzo = zzK2 + 4;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if ((i4 & i) != 0) {
                        zzK = zzqj.zzK(i7 << 3);
                        zzo = zzK + 8;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if ((i4 & i) != 0) {
                        int i13 = unsafe.getInt(t, j);
                        zzK4 = zzqj.zzK(i7 << 3);
                        zzD = zzqj.zzK((i13 >> 31) ^ (i13 + i13));
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if ((i4 & i) != 0) {
                        long j4 = unsafe.getLong(t, j);
                        zzK4 = zzqj.zzK(i7 << 3);
                        zzD = zzqj.zzL((j4 >> 63) ^ (j4 + j4));
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if ((i4 & i) != 0) {
                        zzo = zzqj.zzB(i7, (zzsn) unsafe.getObject(t, j), zzv(i6));
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzo = zztc.zzh(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 19:
                    zzo = zztc.zzf(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 20:
                    zzo = zztc.zzm(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 21:
                    zzo = zztc.zzx(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 22:
                    zzo = zztc.zzk(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 23:
                    zzo = zztc.zzh(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 24:
                    zzo = zztc.zzf(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 25:
                    zzo = zztc.zza(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 26:
                    zzo = zztc.zzu(i7, (List) unsafe.getObject(t, j));
                    i3 += zzo;
                    break;
                case 27:
                    zzo = zztc.zzp(i7, (List) unsafe.getObject(t, j), zzv(i6));
                    i3 += zzo;
                    break;
                case 28:
                    zzo = zztc.zzc(i7, (List) unsafe.getObject(t, j));
                    i3 += zzo;
                    break;
                case 29:
                    zzo = zztc.zzv(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 30:
                    zzo = zztc.zzd(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 31:
                    zzo = zztc.zzf(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 32:
                    zzo = zztc.zzh(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 33:
                    zzo = zztc.zzq(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 34:
                    zzo = zztc.zzs(i7, (List) unsafe.getObject(t, j), false);
                    i3 += zzo;
                    break;
                case 35:
                    zzD = zztc.zzi((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    zzD = zztc.zzg((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    zzD = zztc.zzn((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    zzD = zztc.zzy((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    zzD = zztc.zzl((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    zzD = zztc.zzi((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    zzD = zztc.zzg((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    zzD = zztc.zzb((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    zzD = zztc.zzw((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    zzD = zztc.zze((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    zzD = zztc.zzg((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    zzD = zztc.zzi((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    zzD = zztc.zzr((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    zzD = zztc.zzt((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i7);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    zzo = zztc.zzj(i7, (List) unsafe.getObject(t, j), zzv(i6));
                    i3 += zzo;
                    break;
                case 50:
                    zzo = zzsi.zza(i7, unsafe.getObject(t, j), zzx(i6));
                    i3 += zzo;
                    break;
                case 51:
                    if (zzL(t, i7, i6)) {
                        zzK = zzqj.zzK(i7 << 3);
                        zzo = zzK + 8;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzL(t, i7, i6)) {
                        zzK2 = zzqj.zzK(i7 << 3);
                        zzo = zzK2 + 4;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzL(t, i7, i6)) {
                        long zzt = zzt(t, j);
                        zzK3 = zzqj.zzK(i7 << 3);
                        zzL = zzqj.zzL(zzt);
                        zzo = zzK3 + zzL;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzL(t, i7, i6)) {
                        long zzt2 = zzt(t, j);
                        zzK3 = zzqj.zzK(i7 << 3);
                        zzL = zzqj.zzL(zzt2);
                        zzo = zzK3 + zzL;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzL(t, i7, i6)) {
                        int zzp = zzp(t, j);
                        zzK4 = zzqj.zzK(i7 << 3);
                        zzD = zzqj.zzD(zzp);
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzL(t, i7, i6)) {
                        zzK = zzqj.zzK(i7 << 3);
                        zzo = zzK + 8;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzL(t, i7, i6)) {
                        zzK2 = zzqj.zzK(i7 << 3);
                        zzo = zzK2 + 4;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzL(t, i7, i6)) {
                        zzK5 = zzqj.zzK(i7 << 3);
                        zzo = zzK5 + 1;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzL(t, i7, i6)) {
                        break;
                    } else {
                        Object object2 = unsafe.getObject(t, j);
                        if (object2 instanceof zzpy) {
                            zzK6 = zzqj.zzK(i7 << 3);
                            zzd = ((zzpy) object2).zzd();
                            zzK7 = zzqj.zzK(zzd);
                            i2 = zzK6 + zzK7 + zzd;
                            i3 += i2;
                            break;
                        } else {
                            zzK4 = zzqj.zzK(i7 << 3);
                            zzD = zzqj.zzI((String) object2);
                            i2 = zzK4 + zzD;
                            i3 += i2;
                        }
                    }
                case 60:
                    if (zzL(t, i7, i6)) {
                        zzo = zztc.zzo(i7, unsafe.getObject(t, j), zzv(i6));
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzL(t, i7, i6)) {
                        zzpy zzpyVar2 = (zzpy) unsafe.getObject(t, j);
                        zzK6 = zzqj.zzK(i7 << 3);
                        zzd = zzpyVar2.zzd();
                        zzK7 = zzqj.zzK(zzd);
                        i2 = zzK6 + zzK7 + zzd;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzL(t, i7, i6)) {
                        int zzp2 = zzp(t, j);
                        zzK4 = zzqj.zzK(i7 << 3);
                        zzD = zzqj.zzK(zzp2);
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzL(t, i7, i6)) {
                        int zzp3 = zzp(t, j);
                        zzK4 = zzqj.zzK(i7 << 3);
                        zzD = zzqj.zzD(zzp3);
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzL(t, i7, i6)) {
                        zzK2 = zzqj.zzK(i7 << 3);
                        zzo = zzK2 + 4;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzL(t, i7, i6)) {
                        zzK = zzqj.zzK(i7 << 3);
                        zzo = zzK + 8;
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzL(t, i7, i6)) {
                        int zzp4 = zzp(t, j);
                        zzK4 = zzqj.zzK(i7 << 3);
                        zzD = zzqj.zzK((zzp4 >> 31) ^ (zzp4 + zzp4));
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzL(t, i7, i6)) {
                        long zzt3 = zzt(t, j);
                        zzK4 = zzqj.zzK(i7 << 3);
                        zzD = zzqj.zzL((zzt3 >> 63) ^ (zzt3 + zzt3));
                        i2 = zzK4 + zzD;
                        i3 += i2;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzL(t, i7, i6)) {
                        zzo = zzqj.zzB(i7, (zzsn) unsafe.getObject(t, j), zzv(i6));
                        i3 += zzo;
                        break;
                    } else {
                        break;
                    }
            }
        }
        zztr<?, ?> zztrVar = this.zzo;
        int zza2 = i3 + zztrVar.zza(zztrVar.zzd(t));
        if (!this.zzh) {
            return zza2;
        }
        this.zzp.zza(t);
        throw null;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0033. Please report as an issue. */
    private final int zzo(T t) {
        int zzK;
        int zzK2;
        int zzK3;
        int zzL;
        int zzK4;
        int zzD;
        int zzK5;
        int zzK6;
        int zzd;
        int zzK7;
        int zzo;
        int zzJ;
        int zzK8;
        int i;
        Unsafe unsafe = zzb;
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzc.length; i3 += 3) {
            int zzs = zzs(i3);
            int zzr = zzr(zzs);
            int i4 = this.zzc[i3];
            long j = zzs & 1048575;
            if (zzr >= zzqx.DOUBLE_LIST_PACKED.zza() && zzr <= zzqx.SINT64_LIST_PACKED.zza()) {
                int i5 = this.zzc[i3 + 2];
            }
            switch (zzr) {
                case 0:
                    if (zzI(t, i3)) {
                        zzK = zzqj.zzK(i4 << 3);
                        zzo = zzK + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzI(t, i3)) {
                        zzK2 = zzqj.zzK(i4 << 3);
                        zzo = zzK2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzI(t, i3)) {
                        long zzd2 = zzub.zzd(t, j);
                        zzK3 = zzqj.zzK(i4 << 3);
                        zzL = zzqj.zzL(zzd2);
                        i2 += zzK3 + zzL;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzI(t, i3)) {
                        long zzd3 = zzub.zzd(t, j);
                        zzK3 = zzqj.zzK(i4 << 3);
                        zzL = zzqj.zzL(zzd3);
                        i2 += zzK3 + zzL;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzI(t, i3)) {
                        int zzc = zzub.zzc(t, j);
                        zzK4 = zzqj.zzK(i4 << 3);
                        zzD = zzqj.zzD(zzc);
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzI(t, i3)) {
                        zzK = zzqj.zzK(i4 << 3);
                        zzo = zzK + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzI(t, i3)) {
                        zzK2 = zzqj.zzK(i4 << 3);
                        zzo = zzK2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzI(t, i3)) {
                        zzK5 = zzqj.zzK(i4 << 3);
                        zzo = zzK5 + 1;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zzI(t, i3)) {
                        break;
                    } else {
                        Object zzf = zzub.zzf(t, j);
                        if (zzf instanceof zzpy) {
                            zzK6 = zzqj.zzK(i4 << 3);
                            zzd = ((zzpy) zzf).zzd();
                            zzK7 = zzqj.zzK(zzd);
                            i = zzK6 + zzK7 + zzd;
                            i2 += i;
                            break;
                        } else {
                            zzK4 = zzqj.zzK(i4 << 3);
                            zzD = zzqj.zzI((String) zzf);
                            i = zzK4 + zzD;
                            i2 += i;
                        }
                    }
                case 9:
                    if (zzI(t, i3)) {
                        zzo = zztc.zzo(i4, zzub.zzf(t, j), zzv(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzI(t, i3)) {
                        zzpy zzpyVar = (zzpy) zzub.zzf(t, j);
                        zzK6 = zzqj.zzK(i4 << 3);
                        zzd = zzpyVar.zzd();
                        zzK7 = zzqj.zzK(zzd);
                        i = zzK6 + zzK7 + zzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzI(t, i3)) {
                        int zzc2 = zzub.zzc(t, j);
                        zzK4 = zzqj.zzK(i4 << 3);
                        zzD = zzqj.zzK(zzc2);
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzI(t, i3)) {
                        int zzc3 = zzub.zzc(t, j);
                        zzK4 = zzqj.zzK(i4 << 3);
                        zzD = zzqj.zzD(zzc3);
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzI(t, i3)) {
                        zzK2 = zzqj.zzK(i4 << 3);
                        zzo = zzK2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzI(t, i3)) {
                        zzK = zzqj.zzK(i4 << 3);
                        zzo = zzK + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzI(t, i3)) {
                        int zzc4 = zzub.zzc(t, j);
                        zzK4 = zzqj.zzK(i4 << 3);
                        zzD = zzqj.zzK((zzc4 >> 31) ^ (zzc4 + zzc4));
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzI(t, i3)) {
                        long zzd4 = zzub.zzd(t, j);
                        zzK4 = zzqj.zzK(i4 << 3);
                        zzD = zzqj.zzL((zzd4 >> 63) ^ (zzd4 + zzd4));
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzI(t, i3)) {
                        zzo = zzqj.zzB(i4, (zzsn) zzub.zzf(t, j), zzv(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzo = zztc.zzh(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 19:
                    zzo = zztc.zzf(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 20:
                    zzo = zztc.zzm(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 21:
                    zzo = zztc.zzx(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 22:
                    zzo = zztc.zzk(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 23:
                    zzo = zztc.zzh(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 24:
                    zzo = zztc.zzf(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 25:
                    zzo = zztc.zza(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 26:
                    zzo = zztc.zzu(i4, (List) zzub.zzf(t, j));
                    i2 += zzo;
                    break;
                case 27:
                    zzo = zztc.zzp(i4, (List) zzub.zzf(t, j), zzv(i3));
                    i2 += zzo;
                    break;
                case 28:
                    zzo = zztc.zzc(i4, (List) zzub.zzf(t, j));
                    i2 += zzo;
                    break;
                case 29:
                    zzo = zztc.zzv(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 30:
                    zzo = zztc.zzd(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 31:
                    zzo = zztc.zzf(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 32:
                    zzo = zztc.zzh(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 33:
                    zzo = zztc.zzq(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 34:
                    zzo = zztc.zzs(i4, (List) zzub.zzf(t, j), false);
                    i2 += zzo;
                    break;
                case 35:
                    zzD = zztc.zzi((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    zzD = zztc.zzg((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    zzD = zztc.zzn((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    zzD = zztc.zzy((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    zzD = zztc.zzl((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    zzD = zztc.zzi((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    zzD = zztc.zzg((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    zzD = zztc.zzb((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    zzD = zztc.zzw((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    zzD = zztc.zze((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    zzD = zztc.zzg((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    zzD = zztc.zzi((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    zzD = zztc.zzr((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    zzD = zztc.zzt((List) unsafe.getObject(t, j));
                    if (zzD > 0) {
                        zzJ = zzqj.zzJ(i4);
                        zzK8 = zzqj.zzK(zzD);
                        zzK4 = zzJ + zzK8;
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    zzo = zztc.zzj(i4, (List) zzub.zzf(t, j), zzv(i3));
                    i2 += zzo;
                    break;
                case 50:
                    zzo = zzsi.zza(i4, zzub.zzf(t, j), zzx(i3));
                    i2 += zzo;
                    break;
                case 51:
                    if (zzL(t, i4, i3)) {
                        zzK = zzqj.zzK(i4 << 3);
                        zzo = zzK + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzL(t, i4, i3)) {
                        zzK2 = zzqj.zzK(i4 << 3);
                        zzo = zzK2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzL(t, i4, i3)) {
                        long zzt = zzt(t, j);
                        zzK3 = zzqj.zzK(i4 << 3);
                        zzL = zzqj.zzL(zzt);
                        i2 += zzK3 + zzL;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzL(t, i4, i3)) {
                        long zzt2 = zzt(t, j);
                        zzK3 = zzqj.zzK(i4 << 3);
                        zzL = zzqj.zzL(zzt2);
                        i2 += zzK3 + zzL;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzL(t, i4, i3)) {
                        int zzp = zzp(t, j);
                        zzK4 = zzqj.zzK(i4 << 3);
                        zzD = zzqj.zzD(zzp);
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzL(t, i4, i3)) {
                        zzK = zzqj.zzK(i4 << 3);
                        zzo = zzK + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzL(t, i4, i3)) {
                        zzK2 = zzqj.zzK(i4 << 3);
                        zzo = zzK2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzL(t, i4, i3)) {
                        zzK5 = zzqj.zzK(i4 << 3);
                        zzo = zzK5 + 1;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzL(t, i4, i3)) {
                        break;
                    } else {
                        Object zzf2 = zzub.zzf(t, j);
                        if (zzf2 instanceof zzpy) {
                            zzK6 = zzqj.zzK(i4 << 3);
                            zzd = ((zzpy) zzf2).zzd();
                            zzK7 = zzqj.zzK(zzd);
                            i = zzK6 + zzK7 + zzd;
                            i2 += i;
                            break;
                        } else {
                            zzK4 = zzqj.zzK(i4 << 3);
                            zzD = zzqj.zzI((String) zzf2);
                            i = zzK4 + zzD;
                            i2 += i;
                        }
                    }
                case 60:
                    if (zzL(t, i4, i3)) {
                        zzo = zztc.zzo(i4, zzub.zzf(t, j), zzv(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzL(t, i4, i3)) {
                        zzpy zzpyVar2 = (zzpy) zzub.zzf(t, j);
                        zzK6 = zzqj.zzK(i4 << 3);
                        zzd = zzpyVar2.zzd();
                        zzK7 = zzqj.zzK(zzd);
                        i = zzK6 + zzK7 + zzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzL(t, i4, i3)) {
                        int zzp2 = zzp(t, j);
                        zzK4 = zzqj.zzK(i4 << 3);
                        zzD = zzqj.zzK(zzp2);
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzL(t, i4, i3)) {
                        int zzp3 = zzp(t, j);
                        zzK4 = zzqj.zzK(i4 << 3);
                        zzD = zzqj.zzD(zzp3);
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzL(t, i4, i3)) {
                        zzK2 = zzqj.zzK(i4 << 3);
                        zzo = zzK2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzL(t, i4, i3)) {
                        zzK = zzqj.zzK(i4 << 3);
                        zzo = zzK + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzL(t, i4, i3)) {
                        int zzp4 = zzp(t, j);
                        zzK4 = zzqj.zzK(i4 << 3);
                        zzD = zzqj.zzK((zzp4 >> 31) ^ (zzp4 + zzp4));
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzL(t, i4, i3)) {
                        long zzt3 = zzt(t, j);
                        zzK4 = zzqj.zzK(i4 << 3);
                        zzD = zzqj.zzL((zzt3 >> 63) ^ (zzt3 + zzt3));
                        i = zzK4 + zzD;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzL(t, i4, i3)) {
                        zzo = zzqj.zzB(i4, (zzsn) zzub.zzf(t, j), zzv(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
            }
        }
        zztr<?, ?> zztrVar = this.zzo;
        return i2 + zztrVar.zza(zztrVar.zzd(t));
    }

    private static <T> int zzp(T t, long j) {
        return ((Integer) zzub.zzf(t, j)).intValue();
    }

    private final int zzq(int i) {
        return this.zzc[i + 2];
    }

    private static int zzr(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzs(int i) {
        return this.zzc[i + 1];
    }

    private static <T> long zzt(T t, long j) {
        return ((Long) zzub.zzf(t, j)).longValue();
    }

    private final zzrk zzu(int i) {
        int i2 = i / 3;
        return (zzrk) this.zzd[i2 + i2 + 1];
    }

    private final zzta zzv(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzta zztaVar = (zzta) this.zzd[i3];
        if (zztaVar != null) {
            return zztaVar;
        }
        zzta<T> zzb2 = zzsw.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final <UT, UB> UB zzw(Object obj, int i, UB ub, zztr<UT, UB> zztrVar) {
        zzrk zzu;
        int i2 = this.zzc[i];
        Object zzf = zzub.zzf(obj, zzs(i) & 1048575);
        if (zzf == null || (zzu = zzu(i)) == null) {
            return ub;
        }
        zzsf zzc = ((zzsg) zzx(i)).zzc();
        Iterator it = ((zzsh) zzf).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!zzu.zza(((Integer) entry.getValue()).intValue())) {
                if (ub == null) {
                    ub = zztrVar.zzf();
                }
                int zzb2 = zzsg.zzb(zzc, entry.getKey(), entry.getValue());
                zzpy zzpyVar = zzpy.zzb;
                byte[] bArr = new byte[zzb2];
                zzqj zzM = zzqj.zzM(bArr);
                try {
                    zzsg.zze(zzM, zzc, entry.getKey(), entry.getValue());
                    zztrVar.zzk(ub, i2, zzpu.zza(zzM, bArr));
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private final Object zzx(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private static Field zzy(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    private final void zzz(T t, T t2, int i) {
        long zzs = zzs(i) & 1048575;
        if (zzI(t2, i)) {
            Object zzf = zzub.zzf(t, zzs);
            Object zzf2 = zzub.zzf(t2, zzs);
            if (zzf != null && zzf2 != null) {
                zzub.zzs(t, zzs, zzrp.zzg(zzf, zzf2));
                zzC(t, i);
            } else if (zzf2 != null) {
                zzub.zzs(t, zzs, zzf2);
                zzC(t, i);
            }
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final int zza(T t) {
        return this.zzj ? zzo(t) : zzn(t);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001a. Please report as an issue. */
    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final int zzb(T t) {
        int i;
        int zzc;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int zzs = zzs(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & zzs;
            int i5 = 37;
            switch (zzr(zzs)) {
                case 0:
                    i = i2 * 53;
                    zzc = zzrp.zzc(Double.doubleToLongBits(zzub.zza(t, j)));
                    i2 = i + zzc;
                    break;
                case 1:
                    i = i2 * 53;
                    zzc = Float.floatToIntBits(zzub.zzb(t, j));
                    i2 = i + zzc;
                    break;
                case 2:
                    i = i2 * 53;
                    zzc = zzrp.zzc(zzub.zzd(t, j));
                    i2 = i + zzc;
                    break;
                case 3:
                    i = i2 * 53;
                    zzc = zzrp.zzc(zzub.zzd(t, j));
                    i2 = i + zzc;
                    break;
                case 4:
                    i = i2 * 53;
                    zzc = zzub.zzc(t, j);
                    i2 = i + zzc;
                    break;
                case 5:
                    i = i2 * 53;
                    zzc = zzrp.zzc(zzub.zzd(t, j));
                    i2 = i + zzc;
                    break;
                case 6:
                    i = i2 * 53;
                    zzc = zzub.zzc(t, j);
                    i2 = i + zzc;
                    break;
                case 7:
                    i = i2 * 53;
                    zzc = zzrp.zza(zzub.zzw(t, j));
                    i2 = i + zzc;
                    break;
                case 8:
                    i = i2 * 53;
                    zzc = ((String) zzub.zzf(t, j)).hashCode();
                    i2 = i + zzc;
                    break;
                case 9:
                    Object zzf = zzub.zzf(t, j);
                    if (zzf != null) {
                        i5 = zzf.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zzc = zzub.zzf(t, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 11:
                    i = i2 * 53;
                    zzc = zzub.zzc(t, j);
                    i2 = i + zzc;
                    break;
                case 12:
                    i = i2 * 53;
                    zzc = zzub.zzc(t, j);
                    i2 = i + zzc;
                    break;
                case 13:
                    i = i2 * 53;
                    zzc = zzub.zzc(t, j);
                    i2 = i + zzc;
                    break;
                case 14:
                    i = i2 * 53;
                    zzc = zzrp.zzc(zzub.zzd(t, j));
                    i2 = i + zzc;
                    break;
                case 15:
                    i = i2 * 53;
                    zzc = zzub.zzc(t, j);
                    i2 = i + zzc;
                    break;
                case 16:
                    i = i2 * 53;
                    zzc = zzrp.zzc(zzub.zzd(t, j));
                    i2 = i + zzc;
                    break;
                case 17:
                    Object zzf2 = zzub.zzf(t, j);
                    if (zzf2 != null) {
                        i5 = zzf2.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    zzc = zzub.zzf(t, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 50:
                    i = i2 * 53;
                    zzc = zzub.zzf(t, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 51:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzrp.zzc(Double.doubleToLongBits(zzl(t, j)));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = Float.floatToIntBits(zzm(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzrp.zzc(zzt(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzrp.zzc(zzt(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzp(t, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzrp.zzc(zzt(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzp(t, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzrp.zza(zzM(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = ((String) zzub.zzf(t, j)).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzub.zzf(t, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzub.zzf(t, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzp(t, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzp(t, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzp(t, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzrp.zzc(zzt(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzp(t, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzrp.zzc(zzt(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzL(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzub.zzf(t, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.zzo.zzd(t).hashCode();
        if (!this.zzh) {
            return hashCode;
        }
        this.zzp.zza(t);
        throw null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final T zzc() {
        return (T) ((zzrg) this.zzg).zzh(4, null, null);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final void zzd(T t) {
        int i;
        int i2 = this.zzl;
        while (true) {
            i = this.zzm;
            if (i2 >= i) {
                break;
            }
            long zzs = zzs(this.zzk[i2]) & 1048575;
            Object zzf = zzub.zzf(t, zzs);
            if (zzf != null) {
                ((zzsh) zzf).zzc();
                zzub.zzs(t, zzs, zzf);
            }
            i2++;
        }
        int length = this.zzk.length;
        while (i < length) {
            this.zzn.zzb(t, this.zzk[i]);
            i++;
        }
        this.zzo.zzm(t);
        if (this.zzh) {
            this.zzp.zze(t);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x00b9. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ae A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0045 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.recaptcha.zzta
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzf(T r18, com.google.android.gms.internal.recaptcha.zzsz r19, com.google.android.gms.internal.recaptcha.zzqr r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1670
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.recaptcha.zzsq.zzf(java.lang.Object, com.google.android.gms.internal.recaptcha.zzsz, com.google.android.gms.internal.recaptcha.zzqr):void");
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final void zzg(T t, zzuj zzujVar) throws IOException {
        if (!this.zzj) {
            zzE(t, zzujVar);
            return;
        }
        if (!this.zzh) {
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzs = zzs(i);
                int i2 = this.zzc[i];
                switch (zzr(zzs)) {
                    case 0:
                        if (zzI(t, i)) {
                            zzujVar.zzf(i2, zzub.zza(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzI(t, i)) {
                            zzujVar.zzo(i2, zzub.zzb(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzI(t, i)) {
                            zzujVar.zzt(i2, zzub.zzd(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzI(t, i)) {
                            zzujVar.zzK(i2, zzub.zzd(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzI(t, i)) {
                            zzujVar.zzr(i2, zzub.zzc(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzI(t, i)) {
                            zzujVar.zzm(i2, zzub.zzd(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzI(t, i)) {
                            zzujVar.zzk(i2, zzub.zzc(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzI(t, i)) {
                            zzujVar.zzb(i2, zzub.zzw(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzI(t, i)) {
                            zzN(i2, zzub.zzf(t, zzs & 1048575), zzujVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zzI(t, i)) {
                            zzujVar.zzw(i2, zzub.zzf(t, zzs & 1048575), zzv(i));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzI(t, i)) {
                            zzujVar.zzd(i2, (zzpy) zzub.zzf(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzI(t, i)) {
                            zzujVar.zzI(i2, zzub.zzc(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzI(t, i)) {
                            zzujVar.zzi(i2, zzub.zzc(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzI(t, i)) {
                            zzujVar.zzx(i2, zzub.zzc(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzI(t, i)) {
                            zzujVar.zzz(i2, zzub.zzd(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzI(t, i)) {
                            zzujVar.zzB(i2, zzub.zzc(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzI(t, i)) {
                            zzujVar.zzD(i2, zzub.zzd(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzI(t, i)) {
                            zzujVar.zzq(i2, zzub.zzf(t, zzs & 1048575), zzv(i));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zztc.zzJ(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 19:
                        zztc.zzN(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 20:
                        zztc.zzQ(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 21:
                        zztc.zzY(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 22:
                        zztc.zzP(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 23:
                        zztc.zzM(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 24:
                        zztc.zzL(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 25:
                        zztc.zzH(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 26:
                        zztc.zzW(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar);
                        break;
                    case 27:
                        zztc.zzR(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, zzv(i));
                        break;
                    case 28:
                        zztc.zzI(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar);
                        break;
                    case 29:
                        zztc.zzX(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 30:
                        zztc.zzK(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 31:
                        zztc.zzS(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 32:
                        zztc.zzT(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 33:
                        zztc.zzU(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 34:
                        zztc.zzV(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, false);
                        break;
                    case 35:
                        zztc.zzJ(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 36:
                        zztc.zzN(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 37:
                        zztc.zzQ(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 38:
                        zztc.zzY(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 39:
                        zztc.zzP(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 40:
                        zztc.zzM(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 41:
                        zztc.zzL(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 42:
                        zztc.zzH(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 43:
                        zztc.zzX(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 44:
                        zztc.zzK(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 45:
                        zztc.zzS(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 46:
                        zztc.zzT(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 47:
                        zztc.zzU(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 48:
                        zztc.zzV(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, true);
                        break;
                    case 49:
                        zztc.zzO(this.zzc[i], (List) zzub.zzf(t, zzs & 1048575), zzujVar, zzv(i));
                        break;
                    case 50:
                        zzF(zzujVar, i2, zzub.zzf(t, zzs & 1048575), i);
                        break;
                    case 51:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzf(i2, zzl(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzo(i2, zzm(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzt(i2, zzt(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzK(i2, zzt(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzr(i2, zzp(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzm(i2, zzt(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzk(i2, zzp(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzb(i2, zzM(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzL(t, i2, i)) {
                            zzN(i2, zzub.zzf(t, zzs & 1048575), zzujVar);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzw(i2, zzub.zzf(t, zzs & 1048575), zzv(i));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzd(i2, (zzpy) zzub.zzf(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzI(i2, zzp(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzi(i2, zzp(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzx(i2, zzp(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzz(i2, zzt(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzB(i2, zzp(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzD(i2, zzt(t, zzs & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzL(t, i2, i)) {
                            zzujVar.zzq(i2, zzub.zzf(t, zzs & 1048575), zzv(i));
                            break;
                        } else {
                            break;
                        }
                }
            }
            zztr<?, ?> zztrVar = this.zzo;
            zztrVar.zzp(zztrVar.zzd(t), zzujVar);
            return;
        }
        this.zzp.zza(t);
        throw null;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:17:0x01c2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x01c3 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.recaptcha.zzta
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzh(T r9, T r10) {
        /*
            Method dump skipped, instructions count: 634
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.recaptcha.zzsq.zzh(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [com.google.android.gms.internal.recaptcha.zzta] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.google.android.gms.internal.recaptcha.zzta] */
    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final boolean zzi(T t) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            ?? r11 = 0;
            if (i5 >= this.zzl) {
                if (!this.zzh) {
                    return true;
                }
                this.zzp.zza(t);
                throw null;
            }
            int i6 = this.zzk[i5];
            int i7 = this.zzc[i6];
            int zzs = zzs(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(t, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if ((268435456 & zzs) != 0 && !zzJ(t, i6, i, i2, i10)) {
                return false;
            }
            int zzr = zzr(zzs);
            if (zzr != 9 && zzr != 17) {
                if (zzr != 27) {
                    if (zzr == 60 || zzr == 68) {
                        if (zzL(t, i7, i6) && !zzK(t, zzs, zzv(i6))) {
                            return false;
                        }
                    } else if (zzr != 49) {
                        if (zzr != 50) {
                            continue;
                        } else {
                            zzsh zzshVar = (zzsh) zzub.zzf(t, zzs & 1048575);
                            if (!zzshVar.isEmpty() && ((zzsg) zzx(i6)).zzc().zzc.zzb() == zzui.MESSAGE) {
                                for (Object obj : zzshVar.values()) {
                                    r11 = r11;
                                    if (r11 == 0) {
                                        r11 = zzsw.zza().zzb(obj.getClass());
                                    }
                                    if (!r11.zzi(obj)) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
                List list = (List) zzub.zzf(t, zzs & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    ?? zzv = zzv(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzv.zzi(list.get(i11))) {
                            return false;
                        }
                    }
                }
            } else if (zzJ(t, i6, i, i2, i10) && !zzK(t, zzs, zzv(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final void zze(T t, T t2) {
        t2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzs = zzs(i);
            long j = 1048575 & zzs;
            int i2 = this.zzc[i];
            switch (zzr(zzs)) {
                case 0:
                    if (zzI(t2, i)) {
                        zzub.zzo(t, j, zzub.zza(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzI(t2, i)) {
                        zzub.zzp(t, j, zzub.zzb(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzI(t2, i)) {
                        zzub.zzr(t, j, zzub.zzd(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzI(t2, i)) {
                        zzub.zzr(t, j, zzub.zzd(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzI(t2, i)) {
                        zzub.zzq(t, j, zzub.zzc(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzI(t2, i)) {
                        zzub.zzr(t, j, zzub.zzd(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzI(t2, i)) {
                        zzub.zzq(t, j, zzub.zzc(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzI(t2, i)) {
                        zzub.zzm(t, j, zzub.zzw(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzI(t2, i)) {
                        zzub.zzs(t, j, zzub.zzf(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzz(t, t2, i);
                    break;
                case 10:
                    if (zzI(t2, i)) {
                        zzub.zzs(t, j, zzub.zzf(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzI(t2, i)) {
                        zzub.zzq(t, j, zzub.zzc(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzI(t2, i)) {
                        zzub.zzq(t, j, zzub.zzc(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzI(t2, i)) {
                        zzub.zzq(t, j, zzub.zzc(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzI(t2, i)) {
                        zzub.zzr(t, j, zzub.zzd(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzI(t2, i)) {
                        zzub.zzq(t, j, zzub.zzc(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzI(t2, i)) {
                        zzub.zzr(t, j, zzub.zzd(t2, j));
                        zzC(t, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzz(t, t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzn.zzc(t, t2, j);
                    break;
                case 50:
                    zztc.zzaa(this.zzr, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzL(t2, i2, i)) {
                        zzub.zzs(t, j, zzub.zzf(t2, j));
                        zzD(t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzA(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzL(t2, i2, i)) {
                        zzub.zzs(t, j, zzub.zzf(t2, j));
                        zzD(t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzA(t, t2, i);
                    break;
            }
        }
        zztc.zzF(this.zzo, t, t2);
        if (this.zzh) {
            zztc.zzE(this.zzp, t, t2);
        }
    }
}
