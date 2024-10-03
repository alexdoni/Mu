package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes2.dex */
public final class zzdi<T> implements zzdp<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzeq.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzdf zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzct zzm;
    private final zzeg zzn;
    private final zzbo zzo;
    private final zzdk zzp;
    private final zzda zzq;

    private zzdi(int[] iArr, Object[] objArr, int i, int i2, zzdf zzdfVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzdk zzdkVar, zzct zzctVar, zzeg zzegVar, zzbo zzboVar, zzda zzdaVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = z;
        boolean z3 = false;
        if (zzboVar != null && zzboVar.zzc(zzdfVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzj = iArr2;
        this.zzk = i3;
        this.zzl = i4;
        this.zzp = zzdkVar;
        this.zzm = zzctVar;
        this.zzn = zzegVar;
        this.zzo = zzboVar;
        this.zzg = zzdfVar;
        this.zzq = zzdaVar;
    }

    private final zzce zzA(int i) {
        int i2 = i / 3;
        return (zzce) this.zzd[i2 + i2 + 1];
    }

    private final zzdp zzB(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzdp zzdpVar = (zzdp) this.zzd[i3];
        if (zzdpVar != null) {
            return zzdpVar;
        }
        zzdp zzb2 = zzdn.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzC(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzD(Object obj, int i) {
        zzdp zzB = zzB(i);
        int zzy = zzy(i) & 1048575;
        if (!zzP(obj, i)) {
            return zzB.zze();
        }
        Object object = zzb.getObject(obj, zzy);
        if (zzS(object)) {
            return object;
        }
        Object zze = zzB.zze();
        if (object != null) {
            zzB.zzg(zze, object);
        }
        return zze;
    }

    private final Object zzE(Object obj, int i, int i2) {
        zzdp zzB = zzB(i2);
        if (!zzT(obj, i, i2)) {
            return zzB.zze();
        }
        Object object = zzb.getObject(obj, zzy(i2) & 1048575);
        if (zzS(object)) {
            return object;
        }
        Object zze = zzB.zze();
        if (object != null) {
            zzB.zzg(zze, object);
        }
        return zze;
    }

    private static Field zzF(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzG(Object obj) {
        if (!zzS(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzH(Object obj, Object obj2, int i) {
        if (zzP(obj2, i)) {
            int zzy = zzy(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = zzy;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzdp zzB = zzB(i);
            if (!zzP(obj, i)) {
                if (!zzS(object)) {
                    unsafe.putObject(obj, j, object);
                } else {
                    Object zze = zzB.zze();
                    zzB.zzg(zze, object);
                    unsafe.putObject(obj, j, zze);
                }
                zzJ(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzS(object2)) {
                Object zze2 = zzB.zze();
                zzB.zzg(zze2, object2);
                unsafe.putObject(obj, j, zze2);
                object2 = zze2;
            }
            zzB.zzg(object2, object);
        }
    }

    private final void zzI(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzT(obj2, i2, i)) {
            int zzy = zzy(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = zzy;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzdp zzB = zzB(i);
            if (!zzT(obj, i2, i)) {
                if (!zzS(object)) {
                    unsafe.putObject(obj, j, object);
                } else {
                    Object zze = zzB.zze();
                    zzB.zzg(zze, object);
                    unsafe.putObject(obj, j, zze);
                }
                zzK(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzS(object2)) {
                Object zze2 = zzB.zze();
                zzB.zzg(zze2, object2);
                unsafe.putObject(obj, j, zze2);
                object2 = zze2;
            }
            zzB.zzg(object2, object);
        }
    }

    private final void zzJ(Object obj, int i) {
        int zzv = zzv(i);
        long j = 1048575 & zzv;
        if (j == 1048575) {
            return;
        }
        zzeq.zzq(obj, j, (1 << (zzv >>> 20)) | zzeq.zzc(obj, j));
    }

    private final void zzK(Object obj, int i, int i2) {
        zzeq.zzq(obj, zzv(i2) & 1048575, i);
    }

    private final void zzL(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzy(i) & 1048575, obj2);
        zzJ(obj, i);
    }

    private final void zzM(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzy(i2) & 1048575, obj2);
        zzK(obj, i, i2);
    }

    private final void zzN(zzey zzeyVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        throw null;
    }

    private final boolean zzO(Object obj, Object obj2, int i) {
        return zzP(obj, i) == zzP(obj2, i);
    }

    private final boolean zzP(Object obj, int i) {
        int zzv = zzv(i);
        long j = zzv & 1048575;
        if (j != 1048575) {
            return (zzeq.zzc(obj, j) & (1 << (zzv >>> 20))) != 0;
        }
        int zzy = zzy(i);
        long j2 = zzy & 1048575;
        switch (zzx(zzy)) {
            case 0:
                return Double.doubleToRawLongBits(zzeq.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzeq.zzb(obj, j2)) != 0;
            case 2:
                return zzeq.zzd(obj, j2) != 0;
            case 3:
                return zzeq.zzd(obj, j2) != 0;
            case 4:
                return zzeq.zzc(obj, j2) != 0;
            case 5:
                return zzeq.zzd(obj, j2) != 0;
            case 6:
                return zzeq.zzc(obj, j2) != 0;
            case 7:
                return zzeq.zzw(obj, j2);
            case 8:
                Object zzf = zzeq.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                }
                if (zzf instanceof zzba) {
                    return !zzba.zzb.equals(zzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzeq.zzf(obj, j2) != null;
            case 10:
                return !zzba.zzb.equals(zzeq.zzf(obj, j2));
            case 11:
                return zzeq.zzc(obj, j2) != 0;
            case 12:
                return zzeq.zzc(obj, j2) != 0;
            case 13:
                return zzeq.zzc(obj, j2) != 0;
            case 14:
                return zzeq.zzd(obj, j2) != 0;
            case 15:
                return zzeq.zzc(obj, j2) != 0;
            case 16:
                return zzeq.zzd(obj, j2) != 0;
            case 17:
                return zzeq.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzQ(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzP(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzR(Object obj, int i, zzdp zzdpVar) {
        return zzdpVar.zzk(zzeq.zzf(obj, i & 1048575));
    }

    private static boolean zzS(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzcb) {
            return ((zzcb) obj).zzs();
        }
        return true;
    }

    private final boolean zzT(Object obj, int i, int i2) {
        return zzeq.zzc(obj, (long) (zzv(i2) & 1048575)) == i;
    }

    private static boolean zzU(Object obj, long j) {
        return ((Boolean) zzeq.zzf(obj, j)).booleanValue();
    }

    private static final void zzV(int i, Object obj, zzey zzeyVar) throws IOException {
        if (obj instanceof String) {
            zzeyVar.zzF(i, (String) obj);
        } else {
            zzeyVar.zzd(i, (zzba) obj);
        }
    }

    static zzeh zzd(Object obj) {
        zzcb zzcbVar = (zzcb) obj;
        zzeh zzehVar = zzcbVar.zzc;
        if (zzehVar != zzeh.zzc()) {
            return zzehVar;
        }
        zzeh zzf = zzeh.zzf();
        zzcbVar.zzc = zzf;
        return zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:65:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0270  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.internal.play_billing.zzdi zzl(java.lang.Class r31, com.google.android.gms.internal.play_billing.zzdc r32, com.google.android.gms.internal.play_billing.zzdk r33, com.google.android.gms.internal.play_billing.zzct r34, com.google.android.gms.internal.play_billing.zzeg r35, com.google.android.gms.internal.play_billing.zzbo r36, com.google.android.gms.internal.play_billing.zzda r37) {
        /*
            Method dump skipped, instructions count: 990
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzdi.zzl(java.lang.Class, com.google.android.gms.internal.play_billing.zzdc, com.google.android.gms.internal.play_billing.zzdk, com.google.android.gms.internal.play_billing.zzct, com.google.android.gms.internal.play_billing.zzeg, com.google.android.gms.internal.play_billing.zzbo, com.google.android.gms.internal.play_billing.zzda):com.google.android.gms.internal.play_billing.zzdi");
    }

    private static double zzm(Object obj, long j) {
        return ((Double) zzeq.zzf(obj, j)).doubleValue();
    }

    private static float zzn(Object obj, long j) {
        return ((Float) zzeq.zzf(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x003a. Please report as an issue. */
    private final int zzo(Object obj) {
        int i;
        int zzx;
        int zzx2;
        int zzy;
        int zzx3;
        int zzx4;
        int zzx5;
        int zzx6;
        int zzt;
        boolean z;
        int zzc;
        int zzh;
        int zzx7;
        int zzx8;
        int i2;
        int zzx9;
        int zzx10;
        int zzx11;
        int zzx12;
        Unsafe unsafe = zzb;
        int i3 = 1048575;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < this.zzc.length) {
            int zzy2 = zzy(i5);
            int[] iArr = this.zzc;
            int i8 = iArr[i5];
            int zzx13 = zzx(zzy2);
            if (zzx13 <= 17) {
                int i9 = iArr[i5 + 2];
                int i10 = i9 & i3;
                int i11 = i9 >>> 20;
                if (i10 != i4) {
                    i7 = unsafe.getInt(obj, i10);
                    i4 = i10;
                }
                i = 1 << i11;
            } else {
                i = 0;
            }
            long j = zzy2 & i3;
            switch (zzx13) {
                case 0:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx = zzbi.zzx(i8 << 3);
                        zzx4 = zzx + 8;
                        i6 += zzx4;
                        break;
                    }
                case 1:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx2 = zzbi.zzx(i8 << 3);
                        zzx4 = zzx2 + 4;
                        i6 += zzx4;
                        break;
                    }
                case 2:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzy = zzbi.zzy(unsafe.getLong(obj, j));
                        zzx3 = zzbi.zzx(i8 << 3);
                        i6 += zzx3 + zzy;
                        break;
                    }
                case 3:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzy = zzbi.zzy(unsafe.getLong(obj, j));
                        zzx3 = zzbi.zzx(i8 << 3);
                        i6 += zzx3 + zzy;
                        break;
                    }
                case 4:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzy = zzbi.zzu(unsafe.getInt(obj, j));
                        zzx3 = zzbi.zzx(i8 << 3);
                        i6 += zzx3 + zzy;
                        break;
                    }
                case 5:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx = zzbi.zzx(i8 << 3);
                        zzx4 = zzx + 8;
                        i6 += zzx4;
                        break;
                    }
                case 6:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx2 = zzbi.zzx(i8 << 3);
                        zzx4 = zzx2 + 4;
                        i6 += zzx4;
                        break;
                    }
                case 7:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx4 = zzbi.zzx(i8 << 3) + 1;
                        i6 += zzx4;
                        break;
                    }
                case 8:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzba) {
                            int i12 = zzbi.zzb;
                            int zzd = ((zzba) object).zzd();
                            zzx5 = zzbi.zzx(zzd) + zzd;
                            zzx6 = zzbi.zzx(i8 << 3);
                            zzx4 = zzx6 + zzx5;
                            i6 += zzx4;
                            break;
                        } else {
                            zzy = zzbi.zzw((String) object);
                            zzx3 = zzbi.zzx(i8 << 3);
                            i6 += zzx3 + zzy;
                            break;
                        }
                    }
                case 9:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx4 = zzdr.zzn(i8, unsafe.getObject(obj, j), zzB(i5));
                        i6 += zzx4;
                        break;
                    }
                case 10:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzba zzbaVar = (zzba) unsafe.getObject(obj, j);
                        int i13 = zzbi.zzb;
                        int zzd2 = zzbaVar.zzd();
                        zzx5 = zzbi.zzx(zzd2) + zzd2;
                        zzx6 = zzbi.zzx(i8 << 3);
                        zzx4 = zzx6 + zzx5;
                        i6 += zzx4;
                        break;
                    }
                case 11:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzy = zzbi.zzx(unsafe.getInt(obj, j));
                        zzx3 = zzbi.zzx(i8 << 3);
                        i6 += zzx3 + zzy;
                        break;
                    }
                case 12:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzy = zzbi.zzu(unsafe.getInt(obj, j));
                        zzx3 = zzbi.zzx(i8 << 3);
                        i6 += zzx3 + zzy;
                        break;
                    }
                case 13:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx2 = zzbi.zzx(i8 << 3);
                        zzx4 = zzx2 + 4;
                        i6 += zzx4;
                        break;
                    }
                case 14:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx = zzbi.zzx(i8 << 3);
                        zzx4 = zzx + 8;
                        i6 += zzx4;
                        break;
                    }
                case 15:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        int i14 = unsafe.getInt(obj, j);
                        zzx3 = zzbi.zzx(i8 << 3);
                        zzy = zzbi.zzx((i14 >> 31) ^ (i14 + i14));
                        i6 += zzx3 + zzy;
                        break;
                    }
                case 16:
                    if ((i & i7) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(obj, j);
                        i6 += zzbi.zzx(i8 << 3) + zzbi.zzy((j2 >> 63) ^ (j2 + j2));
                        break;
                    }
                case 17:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx4 = zzbi.zzt(i8, (zzdf) unsafe.getObject(obj, j), zzB(i5));
                        i6 += zzx4;
                        break;
                    }
                case 18:
                    zzx4 = zzdr.zzg(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzx4;
                    break;
                case 19:
                    zzx4 = zzdr.zze(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzx4;
                    break;
                case 20:
                    zzx4 = zzdr.zzl(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzx4;
                    break;
                case 21:
                    zzx4 = zzdr.zzw(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzx4;
                    break;
                case 22:
                    zzx4 = zzdr.zzj(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzx4;
                    break;
                case 23:
                    zzx4 = zzdr.zzg(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzx4;
                    break;
                case 24:
                    zzx4 = zzdr.zze(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzx4;
                    break;
                case 25:
                    zzx4 = zzdr.zza(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzx4;
                    break;
                case 26:
                    zzt = zzdr.zzt(i8, (List) unsafe.getObject(obj, j));
                    i6 += zzt;
                    break;
                case 27:
                    zzt = zzdr.zzo(i8, (List) unsafe.getObject(obj, j), zzB(i5));
                    i6 += zzt;
                    break;
                case 28:
                    zzt = zzdr.zzb(i8, (List) unsafe.getObject(obj, j));
                    i6 += zzt;
                    break;
                case 29:
                    zzt = zzdr.zzu(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzt;
                    break;
                case 30:
                    z = false;
                    zzc = zzdr.zzc(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzc;
                    break;
                case 31:
                    z = false;
                    zzc = zzdr.zze(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzc;
                    break;
                case 32:
                    z = false;
                    zzc = zzdr.zzg(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzc;
                    break;
                case 33:
                    z = false;
                    zzc = zzdr.zzp(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzc;
                    break;
                case 34:
                    z = false;
                    zzc = zzdr.zzr(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzc;
                    break;
                case 35:
                    zzh = zzdr.zzh((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 36:
                    zzh = zzdr.zzf((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 37:
                    zzh = zzdr.zzm((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 38:
                    zzh = zzdr.zzx((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 39:
                    zzh = zzdr.zzk((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 40:
                    zzh = zzdr.zzh((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 41:
                    zzh = zzdr.zzf((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 42:
                    List list = (List) unsafe.getObject(obj, j);
                    int i15 = zzdr.zza;
                    zzh = list.size();
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 43:
                    zzh = zzdr.zzv((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 44:
                    zzh = zzdr.zzd((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 45:
                    zzh = zzdr.zzf((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 46:
                    zzh = zzdr.zzh((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 47:
                    zzh = zzdr.zzq((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 48:
                    zzh = zzdr.zzs((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzbi.zzx(zzh);
                        zzx8 = zzbi.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i6 += i2 + zzh;
                    }
                    break;
                case 49:
                    zzt = zzdr.zzi(i8, (List) unsafe.getObject(obj, j), zzB(i5));
                    i6 += zzt;
                    break;
                case 50:
                    zzda.zza(i8, unsafe.getObject(obj, j), zzC(i5));
                    break;
                case 51:
                    if (zzT(obj, i8, i5)) {
                        zzx9 = zzbi.zzx(i8 << 3);
                        zzt = zzx9 + 8;
                        i6 += zzt;
                    }
                    break;
                case 52:
                    if (zzT(obj, i8, i5)) {
                        zzx10 = zzbi.zzx(i8 << 3);
                        zzt = zzx10 + 4;
                        i6 += zzt;
                    }
                    break;
                case 53:
                    if (zzT(obj, i8, i5)) {
                        zzh = zzbi.zzy(zzz(obj, j));
                        i2 = zzbi.zzx(i8 << 3);
                        i6 += i2 + zzh;
                    }
                    break;
                case 54:
                    if (zzT(obj, i8, i5)) {
                        zzh = zzbi.zzy(zzz(obj, j));
                        i2 = zzbi.zzx(i8 << 3);
                        i6 += i2 + zzh;
                    }
                    break;
                case 55:
                    if (zzT(obj, i8, i5)) {
                        zzh = zzbi.zzu(zzp(obj, j));
                        i2 = zzbi.zzx(i8 << 3);
                        i6 += i2 + zzh;
                    }
                    break;
                case 56:
                    if (zzT(obj, i8, i5)) {
                        zzx9 = zzbi.zzx(i8 << 3);
                        zzt = zzx9 + 8;
                        i6 += zzt;
                    }
                    break;
                case 57:
                    if (zzT(obj, i8, i5)) {
                        zzx10 = zzbi.zzx(i8 << 3);
                        zzt = zzx10 + 4;
                        i6 += zzt;
                    }
                    break;
                case 58:
                    if (zzT(obj, i8, i5)) {
                        zzt = zzbi.zzx(i8 << 3) + 1;
                        i6 += zzt;
                    }
                    break;
                case 59:
                    if (zzT(obj, i8, i5)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzba) {
                            int i16 = zzbi.zzb;
                            int zzd3 = ((zzba) object2).zzd();
                            zzx11 = zzbi.zzx(zzd3) + zzd3;
                            zzx12 = zzbi.zzx(i8 << 3);
                            zzt = zzx12 + zzx11;
                            i6 += zzt;
                        } else {
                            zzh = zzbi.zzw((String) object2);
                            i2 = zzbi.zzx(i8 << 3);
                            i6 += i2 + zzh;
                        }
                    }
                    break;
                case 60:
                    if (zzT(obj, i8, i5)) {
                        zzt = zzdr.zzn(i8, unsafe.getObject(obj, j), zzB(i5));
                        i6 += zzt;
                    }
                    break;
                case 61:
                    if (zzT(obj, i8, i5)) {
                        zzba zzbaVar2 = (zzba) unsafe.getObject(obj, j);
                        int i17 = zzbi.zzb;
                        int zzd4 = zzbaVar2.zzd();
                        zzx11 = zzbi.zzx(zzd4) + zzd4;
                        zzx12 = zzbi.zzx(i8 << 3);
                        zzt = zzx12 + zzx11;
                        i6 += zzt;
                    }
                    break;
                case 62:
                    if (zzT(obj, i8, i5)) {
                        zzh = zzbi.zzx(zzp(obj, j));
                        i2 = zzbi.zzx(i8 << 3);
                        i6 += i2 + zzh;
                    }
                    break;
                case 63:
                    if (zzT(obj, i8, i5)) {
                        zzh = zzbi.zzu(zzp(obj, j));
                        i2 = zzbi.zzx(i8 << 3);
                        i6 += i2 + zzh;
                    }
                    break;
                case 64:
                    if (zzT(obj, i8, i5)) {
                        zzx10 = zzbi.zzx(i8 << 3);
                        zzt = zzx10 + 4;
                        i6 += zzt;
                    }
                    break;
                case 65:
                    if (zzT(obj, i8, i5)) {
                        zzx9 = zzbi.zzx(i8 << 3);
                        zzt = zzx9 + 8;
                        i6 += zzt;
                    }
                    break;
                case 66:
                    if (zzT(obj, i8, i5)) {
                        int zzp = zzp(obj, j);
                        i2 = zzbi.zzx(i8 << 3);
                        zzh = zzbi.zzx((zzp >> 31) ^ (zzp + zzp));
                        i6 += i2 + zzh;
                    }
                    break;
                case 67:
                    if (zzT(obj, i8, i5)) {
                        long zzz = zzz(obj, j);
                        i6 += zzbi.zzx(i8 << 3) + zzbi.zzy((zzz >> 63) ^ (zzz + zzz));
                    }
                    break;
                case 68:
                    if (zzT(obj, i8, i5)) {
                        zzt = zzbi.zzt(i8, (zzdf) unsafe.getObject(obj, j), zzB(i5));
                        i6 += zzt;
                    }
                    break;
            }
            i5 += 3;
            i3 = 1048575;
        }
        zzeg zzegVar = this.zzn;
        int zza2 = i6 + zzegVar.zza(zzegVar.zzd(obj));
        if (!this.zzh) {
            return zza2;
        }
        this.zzo.zza(obj);
        throw null;
    }

    private static int zzp(Object obj, long j) {
        return ((Integer) zzeq.zzf(obj, j)).intValue();
    }

    private final int zzq(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzan zzanVar) throws IOException {
        Unsafe unsafe = zzb;
        Object zzC = zzC(i3);
        Object object = unsafe.getObject(obj, j);
        if (!((zzcz) object).zze()) {
            zzcz zzb2 = zzcz.zza().zzb();
            zzda.zzb(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        throw null;
    }

    private final int zzr(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzan zzanVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzao.zzp(bArr, i))));
                    int i9 = i + 8;
                    unsafe.putInt(obj, j2, i4);
                    return i9;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzao.zzb(bArr, i))));
                    int i10 = i + 4;
                    unsafe.putInt(obj, j2, i4);
                    return i10;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int zzm = zzao.zzm(bArr, i, zzanVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzanVar.zzb));
                    unsafe.putInt(obj, j2, i4);
                    return zzm;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int zzj = zzao.zzj(bArr, i, zzanVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzanVar.zza));
                    unsafe.putInt(obj, j2, i4);
                    return zzj;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzao.zzp(bArr, i)));
                    int i11 = i + 8;
                    unsafe.putInt(obj, j2, i4);
                    return i11;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzao.zzb(bArr, i)));
                    int i12 = i + 4;
                    unsafe.putInt(obj, j2, i4);
                    return i12;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int zzm2 = zzao.zzm(bArr, i, zzanVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zzanVar.zzb != 0));
                    unsafe.putInt(obj, j2, i4);
                    return zzm2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int zzj2 = zzao.zzj(bArr, i, zzanVar);
                    int i13 = zzanVar.zza;
                    if (i13 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else if ((i6 & PKIFailureInfo.duplicateCertReq) == 0 || zzev.zze(bArr, zzj2, zzj2 + i13)) {
                        unsafe.putObject(obj, j, new String(bArr, zzj2, i13, zzcg.zzb));
                        zzj2 += i13;
                    } else {
                        throw zzci.zzc();
                    }
                    unsafe.putInt(obj, j2, i4);
                    return zzj2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    Object zzE = zzE(obj, i4, i8);
                    int zzo = zzao.zzo(zzE, zzB(i8), bArr, i, i2, zzanVar);
                    zzM(obj, i4, i8, zzE);
                    return zzo;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int zza2 = zzao.zza(bArr, i, zzanVar);
                    unsafe.putObject(obj, j, zzanVar.zzc);
                    unsafe.putInt(obj, j2, i4);
                    return zza2;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int zzj3 = zzao.zzj(bArr, i, zzanVar);
                    int i14 = zzanVar.zza;
                    zzce zzA = zzA(i8);
                    if (zzA == null || zzA.zza(i14)) {
                        unsafe.putObject(obj, j, Integer.valueOf(i14));
                        unsafe.putInt(obj, j2, i4);
                    } else {
                        zzd(obj).zzj(i3, Long.valueOf(i14));
                    }
                    return zzj3;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int zzj4 = zzao.zzj(bArr, i, zzanVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzbe.zzb(zzanVar.zza)));
                    unsafe.putInt(obj, j2, i4);
                    return zzj4;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int zzm3 = zzao.zzm(bArr, i, zzanVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzbe.zzc(zzanVar.zzb)));
                    unsafe.putInt(obj, j2, i4);
                    return zzm3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    Object zzE2 = zzE(obj, i4, i8);
                    int zzn = zzao.zzn(zzE2, zzB(i8), bArr, i, i2, (i3 & (-8)) | 4, zzanVar);
                    zzM(obj, i4, i8, zzE2);
                    return zzn;
                }
                break;
        }
        return i;
    }

    private final int zzs(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzan zzanVar) throws IOException {
        int i8;
        int i9;
        int i10;
        int i11;
        int zzl;
        int i12 = i;
        Unsafe unsafe = zzb;
        zzcf zzcfVar = (zzcf) unsafe.getObject(obj, j2);
        if (!zzcfVar.zzc()) {
            int size = zzcfVar.size();
            zzcfVar = zzcfVar.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(obj, j2, zzcfVar);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzbk zzbkVar = (zzbk) zzcfVar;
                    int zzj = zzao.zzj(bArr, i12, zzanVar);
                    int i13 = zzanVar.zza + zzj;
                    while (zzj < i13) {
                        zzbkVar.zze(Double.longBitsToDouble(zzao.zzp(bArr, zzj)));
                        zzj += 8;
                    }
                    if (zzj == i13) {
                        return zzj;
                    }
                    throw zzci.zzg();
                }
                if (i5 == 1) {
                    zzbk zzbkVar2 = (zzbk) zzcfVar;
                    zzbkVar2.zze(Double.longBitsToDouble(zzao.zzp(bArr, i)));
                    while (true) {
                        i8 = i12 + 8;
                        if (i8 < i2) {
                            i12 = zzao.zzj(bArr, i8, zzanVar);
                            if (i3 == zzanVar.zza) {
                                zzbkVar2.zze(Double.longBitsToDouble(zzao.zzp(bArr, i12)));
                            }
                        }
                    }
                    return i8;
                }
                break;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzbu zzbuVar = (zzbu) zzcfVar;
                    int zzj2 = zzao.zzj(bArr, i12, zzanVar);
                    int i14 = zzanVar.zza + zzj2;
                    while (zzj2 < i14) {
                        zzbuVar.zze(Float.intBitsToFloat(zzao.zzb(bArr, zzj2)));
                        zzj2 += 4;
                    }
                    if (zzj2 == i14) {
                        return zzj2;
                    }
                    throw zzci.zzg();
                }
                if (i5 == 5) {
                    zzbu zzbuVar2 = (zzbu) zzcfVar;
                    zzbuVar2.zze(Float.intBitsToFloat(zzao.zzb(bArr, i)));
                    while (true) {
                        i9 = i12 + 4;
                        if (i9 < i2) {
                            i12 = zzao.zzj(bArr, i9, zzanVar);
                            if (i3 == zzanVar.zza) {
                                zzbuVar2.zze(Float.intBitsToFloat(zzao.zzb(bArr, i12)));
                            }
                        }
                    }
                    return i9;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzcu zzcuVar = (zzcu) zzcfVar;
                    int zzj3 = zzao.zzj(bArr, i12, zzanVar);
                    int i15 = zzanVar.zza + zzj3;
                    while (zzj3 < i15) {
                        zzj3 = zzao.zzm(bArr, zzj3, zzanVar);
                        zzcuVar.zzf(zzanVar.zzb);
                    }
                    if (zzj3 == i15) {
                        return zzj3;
                    }
                    throw zzci.zzg();
                }
                if (i5 == 0) {
                    zzcu zzcuVar2 = (zzcu) zzcfVar;
                    int zzm = zzao.zzm(bArr, i12, zzanVar);
                    zzcuVar2.zzf(zzanVar.zzb);
                    while (zzm < i2) {
                        int zzj4 = zzao.zzj(bArr, zzm, zzanVar);
                        if (i3 != zzanVar.zza) {
                            return zzm;
                        }
                        zzm = zzao.zzm(bArr, zzj4, zzanVar);
                        zzcuVar2.zzf(zzanVar.zzb);
                    }
                    return zzm;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzao.zzf(bArr, i12, zzcfVar, zzanVar);
                }
                if (i5 == 0) {
                    return zzao.zzl(i3, bArr, i, i2, zzcfVar, zzanVar);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzcu zzcuVar3 = (zzcu) zzcfVar;
                    int zzj5 = zzao.zzj(bArr, i12, zzanVar);
                    int i16 = zzanVar.zza + zzj5;
                    while (zzj5 < i16) {
                        zzcuVar3.zzf(zzao.zzp(bArr, zzj5));
                        zzj5 += 8;
                    }
                    if (zzj5 == i16) {
                        return zzj5;
                    }
                    throw zzci.zzg();
                }
                if (i5 == 1) {
                    zzcu zzcuVar4 = (zzcu) zzcfVar;
                    zzcuVar4.zzf(zzao.zzp(bArr, i));
                    while (true) {
                        i10 = i12 + 8;
                        if (i10 < i2) {
                            i12 = zzao.zzj(bArr, i10, zzanVar);
                            if (i3 == zzanVar.zza) {
                                zzcuVar4.zzf(zzao.zzp(bArr, i12));
                            }
                        }
                    }
                    return i10;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzcc zzccVar = (zzcc) zzcfVar;
                    int zzj6 = zzao.zzj(bArr, i12, zzanVar);
                    int i17 = zzanVar.zza + zzj6;
                    while (zzj6 < i17) {
                        zzccVar.zzf(zzao.zzb(bArr, zzj6));
                        zzj6 += 4;
                    }
                    if (zzj6 == i17) {
                        return zzj6;
                    }
                    throw zzci.zzg();
                }
                if (i5 == 5) {
                    zzcc zzccVar2 = (zzcc) zzcfVar;
                    zzccVar2.zzf(zzao.zzb(bArr, i));
                    while (true) {
                        i11 = i12 + 4;
                        if (i11 < i2) {
                            i12 = zzao.zzj(bArr, i11, zzanVar);
                            if (i3 == zzanVar.zza) {
                                zzccVar2.zzf(zzao.zzb(bArr, i12));
                            }
                        }
                    }
                    return i11;
                }
                break;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzap zzapVar = (zzap) zzcfVar;
                    int zzj7 = zzao.zzj(bArr, i12, zzanVar);
                    int i18 = zzanVar.zza + zzj7;
                    while (zzj7 < i18) {
                        zzj7 = zzao.zzm(bArr, zzj7, zzanVar);
                        zzapVar.zze(zzanVar.zzb != 0);
                    }
                    if (zzj7 == i18) {
                        return zzj7;
                    }
                    throw zzci.zzg();
                }
                if (i5 == 0) {
                    zzap zzapVar2 = (zzap) zzcfVar;
                    int zzm2 = zzao.zzm(bArr, i12, zzanVar);
                    zzapVar2.zze(zzanVar.zzb != 0);
                    while (zzm2 < i2) {
                        int zzj8 = zzao.zzj(bArr, zzm2, zzanVar);
                        if (i3 != zzanVar.zza) {
                            return zzm2;
                        }
                        zzm2 = zzao.zzm(bArr, zzj8, zzanVar);
                        zzapVar2.zze(zzanVar.zzb != 0);
                    }
                    return zzm2;
                }
                break;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) != 0) {
                        int zzj9 = zzao.zzj(bArr, i12, zzanVar);
                        int i19 = zzanVar.zza;
                        if (i19 < 0) {
                            throw zzci.zzd();
                        }
                        if (i19 == 0) {
                            zzcfVar.add("");
                        } else {
                            int i20 = zzj9 + i19;
                            if (!zzev.zze(bArr, zzj9, i20)) {
                                throw zzci.zzc();
                            }
                            zzcfVar.add(new String(bArr, zzj9, i19, zzcg.zzb));
                            zzj9 = i20;
                        }
                        while (zzj9 < i2) {
                            int zzj10 = zzao.zzj(bArr, zzj9, zzanVar);
                            if (i3 != zzanVar.zza) {
                                return zzj9;
                            }
                            zzj9 = zzao.zzj(bArr, zzj10, zzanVar);
                            int i21 = zzanVar.zza;
                            if (i21 < 0) {
                                throw zzci.zzd();
                            }
                            if (i21 == 0) {
                                zzcfVar.add("");
                            } else {
                                int i22 = zzj9 + i21;
                                if (zzev.zze(bArr, zzj9, i22)) {
                                    zzcfVar.add(new String(bArr, zzj9, i21, zzcg.zzb));
                                    zzj9 = i22;
                                } else {
                                    throw zzci.zzc();
                                }
                            }
                        }
                        return zzj9;
                    }
                    int zzj11 = zzao.zzj(bArr, i12, zzanVar);
                    int i23 = zzanVar.zza;
                    if (i23 < 0) {
                        throw zzci.zzd();
                    }
                    if (i23 == 0) {
                        zzcfVar.add("");
                    } else {
                        zzcfVar.add(new String(bArr, zzj11, i23, zzcg.zzb));
                        zzj11 += i23;
                    }
                    while (zzj11 < i2) {
                        int zzj12 = zzao.zzj(bArr, zzj11, zzanVar);
                        if (i3 != zzanVar.zza) {
                            return zzj11;
                        }
                        zzj11 = zzao.zzj(bArr, zzj12, zzanVar);
                        int i24 = zzanVar.zza;
                        if (i24 < 0) {
                            throw zzci.zzd();
                        }
                        if (i24 == 0) {
                            zzcfVar.add("");
                        } else {
                            zzcfVar.add(new String(bArr, zzj11, i24, zzcg.zzb));
                            zzj11 += i24;
                        }
                    }
                    return zzj11;
                }
                break;
            case 27:
                if (i5 == 2) {
                    return zzao.zze(zzB(i6), i3, bArr, i, i2, zzcfVar, zzanVar);
                }
                break;
            case 28:
                if (i5 == 2) {
                    int zzj13 = zzao.zzj(bArr, i12, zzanVar);
                    int i25 = zzanVar.zza;
                    if (i25 < 0) {
                        throw zzci.zzd();
                    }
                    if (i25 > bArr.length - zzj13) {
                        throw zzci.zzg();
                    }
                    if (i25 == 0) {
                        zzcfVar.add(zzba.zzb);
                    } else {
                        zzcfVar.add(zzba.zzl(bArr, zzj13, i25));
                        zzj13 += i25;
                    }
                    while (zzj13 < i2) {
                        int zzj14 = zzao.zzj(bArr, zzj13, zzanVar);
                        if (i3 != zzanVar.zza) {
                            return zzj13;
                        }
                        zzj13 = zzao.zzj(bArr, zzj14, zzanVar);
                        int i26 = zzanVar.zza;
                        if (i26 >= 0) {
                            if (i26 > bArr.length - zzj13) {
                                throw zzci.zzg();
                            }
                            if (i26 == 0) {
                                zzcfVar.add(zzba.zzb);
                            } else {
                                zzcfVar.add(zzba.zzl(bArr, zzj13, i26));
                                zzj13 += i26;
                            }
                        } else {
                            throw zzci.zzd();
                        }
                    }
                    return zzj13;
                }
                break;
            case 30:
            case 44:
                if (i5 == 2) {
                    zzl = zzao.zzf(bArr, i12, zzcfVar, zzanVar);
                } else if (i5 == 0) {
                    zzl = zzao.zzl(i3, bArr, i, i2, zzcfVar, zzanVar);
                }
                zzce zzA = zzA(i6);
                zzeg zzegVar = this.zzn;
                int i27 = zzdr.zza;
                if (zzA != null) {
                    Object obj2 = null;
                    if (zzcfVar instanceof RandomAccess) {
                        int size2 = zzcfVar.size();
                        int i28 = 0;
                        for (int i29 = 0; i29 < size2; i29++) {
                            int intValue = ((Integer) zzcfVar.get(i29)).intValue();
                            if (zzA.zza(intValue)) {
                                if (i29 != i28) {
                                    zzcfVar.set(i28, Integer.valueOf(intValue));
                                }
                                i28++;
                            } else {
                                obj2 = zzdr.zzB(obj, i4, intValue, obj2, zzegVar);
                            }
                        }
                        if (i28 != size2) {
                            zzcfVar.subList(i28, size2).clear();
                            return zzl;
                        }
                    } else {
                        Iterator it = zzcfVar.iterator();
                        while (it.hasNext()) {
                            int intValue2 = ((Integer) it.next()).intValue();
                            if (!zzA.zza(intValue2)) {
                                obj2 = zzdr.zzB(obj, i4, intValue2, obj2, zzegVar);
                                it.remove();
                            }
                        }
                    }
                }
                return zzl;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzcc zzccVar3 = (zzcc) zzcfVar;
                    int zzj15 = zzao.zzj(bArr, i12, zzanVar);
                    int i30 = zzanVar.zza + zzj15;
                    while (zzj15 < i30) {
                        zzj15 = zzao.zzj(bArr, zzj15, zzanVar);
                        zzccVar3.zzf(zzbe.zzb(zzanVar.zza));
                    }
                    if (zzj15 == i30) {
                        return zzj15;
                    }
                    throw zzci.zzg();
                }
                if (i5 == 0) {
                    zzcc zzccVar4 = (zzcc) zzcfVar;
                    int zzj16 = zzao.zzj(bArr, i12, zzanVar);
                    zzccVar4.zzf(zzbe.zzb(zzanVar.zza));
                    while (zzj16 < i2) {
                        int zzj17 = zzao.zzj(bArr, zzj16, zzanVar);
                        if (i3 != zzanVar.zza) {
                            return zzj16;
                        }
                        zzj16 = zzao.zzj(bArr, zzj17, zzanVar);
                        zzccVar4.zzf(zzbe.zzb(zzanVar.zza));
                    }
                    return zzj16;
                }
                break;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzcu zzcuVar5 = (zzcu) zzcfVar;
                    int zzj18 = zzao.zzj(bArr, i12, zzanVar);
                    int i31 = zzanVar.zza + zzj18;
                    while (zzj18 < i31) {
                        zzj18 = zzao.zzm(bArr, zzj18, zzanVar);
                        zzcuVar5.zzf(zzbe.zzc(zzanVar.zzb));
                    }
                    if (zzj18 == i31) {
                        return zzj18;
                    }
                    throw zzci.zzg();
                }
                if (i5 == 0) {
                    zzcu zzcuVar6 = (zzcu) zzcfVar;
                    int zzm3 = zzao.zzm(bArr, i12, zzanVar);
                    zzcuVar6.zzf(zzbe.zzc(zzanVar.zzb));
                    while (zzm3 < i2) {
                        int zzj19 = zzao.zzj(bArr, zzm3, zzanVar);
                        if (i3 != zzanVar.zza) {
                            return zzm3;
                        }
                        zzm3 = zzao.zzm(bArr, zzj19, zzanVar);
                        zzcuVar6.zzf(zzbe.zzc(zzanVar.zzb));
                    }
                    return zzm3;
                }
                break;
            default:
                if (i5 == 3) {
                    zzdp zzB = zzB(i6);
                    int i32 = (i3 & (-8)) | 4;
                    int zzc = zzao.zzc(zzB, bArr, i, i2, i32, zzanVar);
                    zzcfVar.add(zzanVar.zzc);
                    while (zzc < i2) {
                        int zzj20 = zzao.zzj(bArr, zzc, zzanVar);
                        if (i3 != zzanVar.zza) {
                            return zzc;
                        }
                        zzc = zzao.zzc(zzB, bArr, zzj20, i2, i32, zzanVar);
                        zzcfVar.add(zzanVar.zzc);
                    }
                    return zzc;
                }
                break;
        }
        return i12;
    }

    private final int zzt(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzw(i, 0);
    }

    private final int zzu(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzw(i, i2);
    }

    private final int zzv(int i) {
        return this.zzc[i + 2];
    }

    private final int zzw(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzx(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzy(int i) {
        return this.zzc[i + 1];
    }

    private static long zzz(Object obj, long j) {
        return ((Long) zzeq.zzf(obj, j)).longValue();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x0037. Please report as an issue. */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final int zza(Object obj) {
        int zzx;
        int zzx2;
        int zzy;
        int zzx3;
        int zzx4;
        int zzx5;
        int zzx6;
        int zzn;
        int zzx7;
        int zzy2;
        int zzx8;
        int zzx9;
        if (this.zzi) {
            Unsafe unsafe = zzb;
            int i = 0;
            for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
                int zzy3 = zzy(i2);
                int zzx10 = zzx(zzy3);
                int i3 = this.zzc[i2];
                int i4 = zzy3 & 1048575;
                if (zzx10 >= zzbt.DOUBLE_LIST_PACKED.zza() && zzx10 <= zzbt.SINT64_LIST_PACKED.zza()) {
                    int i5 = this.zzc[i2 + 2];
                }
                long j = i4;
                switch (zzx10) {
                    case 0:
                        if (zzP(obj, i2)) {
                            zzx = zzbi.zzx(i3 << 3);
                            zzn = zzx + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzP(obj, i2)) {
                            zzx2 = zzbi.zzx(i3 << 3);
                            zzn = zzx2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzP(obj, i2)) {
                            zzy = zzbi.zzy(zzeq.zzd(obj, j));
                            zzx3 = zzbi.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzP(obj, i2)) {
                            zzy = zzbi.zzy(zzeq.zzd(obj, j));
                            zzx3 = zzbi.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzP(obj, i2)) {
                            zzy = zzbi.zzu(zzeq.zzc(obj, j));
                            zzx3 = zzbi.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzP(obj, i2)) {
                            zzx = zzbi.zzx(i3 << 3);
                            zzn = zzx + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzP(obj, i2)) {
                            zzx2 = zzbi.zzx(i3 << 3);
                            zzn = zzx2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzP(obj, i2)) {
                            zzx4 = zzbi.zzx(i3 << 3);
                            zzn = zzx4 + 1;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzP(obj, i2)) {
                            Object zzf = zzeq.zzf(obj, j);
                            if (zzf instanceof zzba) {
                                int i6 = i3 << 3;
                                int i7 = zzbi.zzb;
                                int zzd = ((zzba) zzf).zzd();
                                zzx5 = zzbi.zzx(zzd) + zzd;
                                zzx6 = zzbi.zzx(i6);
                                zzn = zzx6 + zzx5;
                                i += zzn;
                                break;
                            } else {
                                zzy = zzbi.zzw((String) zzf);
                                zzx3 = zzbi.zzx(i3 << 3);
                                i += zzx3 + zzy;
                                break;
                            }
                        } else {
                            break;
                        }
                    case 9:
                        if (zzP(obj, i2)) {
                            zzn = zzdr.zzn(i3, zzeq.zzf(obj, j), zzB(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzP(obj, i2)) {
                            zzba zzbaVar = (zzba) zzeq.zzf(obj, j);
                            int i8 = i3 << 3;
                            int i9 = zzbi.zzb;
                            int zzd2 = zzbaVar.zzd();
                            zzx5 = zzbi.zzx(zzd2) + zzd2;
                            zzx6 = zzbi.zzx(i8);
                            zzn = zzx6 + zzx5;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzP(obj, i2)) {
                            zzy = zzbi.zzx(zzeq.zzc(obj, j));
                            zzx3 = zzbi.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzP(obj, i2)) {
                            zzy = zzbi.zzu(zzeq.zzc(obj, j));
                            zzx3 = zzbi.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzP(obj, i2)) {
                            zzx2 = zzbi.zzx(i3 << 3);
                            zzn = zzx2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzP(obj, i2)) {
                            zzx = zzbi.zzx(i3 << 3);
                            zzn = zzx + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzP(obj, i2)) {
                            int zzc = zzeq.zzc(obj, j);
                            zzx3 = zzbi.zzx(i3 << 3);
                            zzy = zzbi.zzx((zzc >> 31) ^ (zzc + zzc));
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzP(obj, i2)) {
                            long zzd3 = zzeq.zzd(obj, j);
                            zzx7 = zzbi.zzx(i3 << 3);
                            zzy2 = zzbi.zzy((zzd3 + zzd3) ^ (zzd3 >> 63));
                            zzn = zzx7 + zzy2;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzP(obj, i2)) {
                            zzn = zzbi.zzt(i3, (zzdf) zzeq.zzf(obj, j), zzB(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzn = zzdr.zzg(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 19:
                        zzn = zzdr.zze(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 20:
                        zzn = zzdr.zzl(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 21:
                        zzn = zzdr.zzw(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 22:
                        zzn = zzdr.zzj(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 23:
                        zzn = zzdr.zzg(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 24:
                        zzn = zzdr.zze(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 25:
                        zzn = zzdr.zza(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 26:
                        zzn = zzdr.zzt(i3, (List) zzeq.zzf(obj, j));
                        i += zzn;
                        break;
                    case 27:
                        zzn = zzdr.zzo(i3, (List) zzeq.zzf(obj, j), zzB(i2));
                        i += zzn;
                        break;
                    case 28:
                        zzn = zzdr.zzb(i3, (List) zzeq.zzf(obj, j));
                        i += zzn;
                        break;
                    case 29:
                        zzn = zzdr.zzu(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 30:
                        zzn = zzdr.zzc(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 31:
                        zzn = zzdr.zze(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 32:
                        zzn = zzdr.zzg(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 33:
                        zzn = zzdr.zzp(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 34:
                        zzn = zzdr.zzr(i3, (List) zzeq.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 35:
                        zzy = zzdr.zzh((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i10 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i10);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        zzy = zzdr.zzf((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i11 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i11);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        zzy = zzdr.zzm((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i12 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i12);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        zzy = zzdr.zzx((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i13 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i13);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        zzy = zzdr.zzk((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i14 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i14);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        zzy = zzdr.zzh((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i15 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i15);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        zzy = zzdr.zzf((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i16 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i16);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        List list = (List) unsafe.getObject(obj, j);
                        int i17 = zzdr.zza;
                        zzy = list.size();
                        if (zzy > 0) {
                            int i18 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i18);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        zzy = zzdr.zzv((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i19 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i19);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        zzy = zzdr.zzd((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i20 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i20);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        zzy = zzdr.zzf((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i21 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i21);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        zzy = zzdr.zzh((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i22 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i22);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        zzy = zzdr.zzq((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i23 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i23);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        zzy = zzdr.zzs((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i24 = i3 << 3;
                            zzx8 = zzbi.zzx(zzy);
                            zzx9 = zzbi.zzx(i24);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        zzn = zzdr.zzi(i3, (List) zzeq.zzf(obj, j), zzB(i2));
                        i += zzn;
                        break;
                    case 50:
                        zzda.zza(i3, zzeq.zzf(obj, j), zzC(i2));
                        break;
                    case 51:
                        if (zzT(obj, i3, i2)) {
                            zzx = zzbi.zzx(i3 << 3);
                            zzn = zzx + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzT(obj, i3, i2)) {
                            zzx2 = zzbi.zzx(i3 << 3);
                            zzn = zzx2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzT(obj, i3, i2)) {
                            zzy = zzbi.zzy(zzz(obj, j));
                            zzx3 = zzbi.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzT(obj, i3, i2)) {
                            zzy = zzbi.zzy(zzz(obj, j));
                            zzx3 = zzbi.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzT(obj, i3, i2)) {
                            zzy = zzbi.zzu(zzp(obj, j));
                            zzx3 = zzbi.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzT(obj, i3, i2)) {
                            zzx = zzbi.zzx(i3 << 3);
                            zzn = zzx + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzT(obj, i3, i2)) {
                            zzx2 = zzbi.zzx(i3 << 3);
                            zzn = zzx2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzT(obj, i3, i2)) {
                            zzx4 = zzbi.zzx(i3 << 3);
                            zzn = zzx4 + 1;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzT(obj, i3, i2)) {
                            Object zzf2 = zzeq.zzf(obj, j);
                            if (zzf2 instanceof zzba) {
                                int i25 = i3 << 3;
                                int i26 = zzbi.zzb;
                                int zzd4 = ((zzba) zzf2).zzd();
                                zzx5 = zzbi.zzx(zzd4) + zzd4;
                                zzx6 = zzbi.zzx(i25);
                                zzn = zzx6 + zzx5;
                                i += zzn;
                                break;
                            } else {
                                zzy = zzbi.zzw((String) zzf2);
                                zzx3 = zzbi.zzx(i3 << 3);
                                i += zzx3 + zzy;
                                break;
                            }
                        } else {
                            break;
                        }
                    case 60:
                        if (zzT(obj, i3, i2)) {
                            zzn = zzdr.zzn(i3, zzeq.zzf(obj, j), zzB(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zzT(obj, i3, i2)) {
                            zzba zzbaVar2 = (zzba) zzeq.zzf(obj, j);
                            int i27 = i3 << 3;
                            int i28 = zzbi.zzb;
                            int zzd5 = zzbaVar2.zzd();
                            zzx5 = zzbi.zzx(zzd5) + zzd5;
                            zzx6 = zzbi.zzx(i27);
                            zzn = zzx6 + zzx5;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzT(obj, i3, i2)) {
                            zzy = zzbi.zzx(zzp(obj, j));
                            zzx3 = zzbi.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzT(obj, i3, i2)) {
                            zzy = zzbi.zzu(zzp(obj, j));
                            zzx3 = zzbi.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzT(obj, i3, i2)) {
                            zzx2 = zzbi.zzx(i3 << 3);
                            zzn = zzx2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzT(obj, i3, i2)) {
                            zzx = zzbi.zzx(i3 << 3);
                            zzn = zzx + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzT(obj, i3, i2)) {
                            int zzp = zzp(obj, j);
                            zzx3 = zzbi.zzx(i3 << 3);
                            zzy = zzbi.zzx((zzp >> 31) ^ (zzp + zzp));
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzT(obj, i3, i2)) {
                            long zzz = zzz(obj, j);
                            zzx7 = zzbi.zzx(i3 << 3);
                            zzy2 = zzbi.zzy((zzz + zzz) ^ (zzz >> 63));
                            zzn = zzx7 + zzy2;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzT(obj, i3, i2)) {
                            zzn = zzbi.zzt(i3, (zzdf) zzeq.zzf(obj, j), zzB(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                }
            }
            zzeg zzegVar = this.zzn;
            return i + zzegVar.zza(zzegVar.zzd(obj));
        }
        return zzo(obj);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001c. Please report as an issue. */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final int zzb(Object obj) {
        int i;
        long doubleToLongBits;
        int floatToIntBits;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int zzy = zzy(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & zzy;
            int i5 = 37;
            switch (zzx(zzy)) {
                case 0:
                    i = i2 * 53;
                    doubleToLongBits = Double.doubleToLongBits(zzeq.zza(obj, j));
                    byte[] bArr = zzcg.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 1:
                    i = i2 * 53;
                    floatToIntBits = Float.floatToIntBits(zzeq.zzb(obj, j));
                    i2 = i + floatToIntBits;
                    break;
                case 2:
                    i = i2 * 53;
                    doubleToLongBits = zzeq.zzd(obj, j);
                    byte[] bArr2 = zzcg.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 3:
                    i = i2 * 53;
                    doubleToLongBits = zzeq.zzd(obj, j);
                    byte[] bArr3 = zzcg.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 4:
                    i = i2 * 53;
                    floatToIntBits = zzeq.zzc(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 5:
                    i = i2 * 53;
                    doubleToLongBits = zzeq.zzd(obj, j);
                    byte[] bArr4 = zzcg.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 6:
                    i = i2 * 53;
                    floatToIntBits = zzeq.zzc(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 7:
                    i = i2 * 53;
                    floatToIntBits = zzcg.zza(zzeq.zzw(obj, j));
                    i2 = i + floatToIntBits;
                    break;
                case 8:
                    i = i2 * 53;
                    floatToIntBits = ((String) zzeq.zzf(obj, j)).hashCode();
                    i2 = i + floatToIntBits;
                    break;
                case 9:
                    Object zzf = zzeq.zzf(obj, j);
                    if (zzf != null) {
                        i5 = zzf.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    floatToIntBits = zzeq.zzf(obj, j).hashCode();
                    i2 = i + floatToIntBits;
                    break;
                case 11:
                    i = i2 * 53;
                    floatToIntBits = zzeq.zzc(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 12:
                    i = i2 * 53;
                    floatToIntBits = zzeq.zzc(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 13:
                    i = i2 * 53;
                    floatToIntBits = zzeq.zzc(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 14:
                    i = i2 * 53;
                    doubleToLongBits = zzeq.zzd(obj, j);
                    byte[] bArr5 = zzcg.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 15:
                    i = i2 * 53;
                    floatToIntBits = zzeq.zzc(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 16:
                    i = i2 * 53;
                    doubleToLongBits = zzeq.zzd(obj, j);
                    byte[] bArr6 = zzcg.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 17:
                    Object zzf2 = zzeq.zzf(obj, j);
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
                    floatToIntBits = zzeq.zzf(obj, j).hashCode();
                    i2 = i + floatToIntBits;
                    break;
                case 50:
                    i = i2 * 53;
                    floatToIntBits = zzeq.zzf(obj, j).hashCode();
                    i2 = i + floatToIntBits;
                    break;
                case 51:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = Double.doubleToLongBits(zzm(obj, j));
                        byte[] bArr7 = zzcg.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = Float.floatToIntBits(zzn(obj, j));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = zzz(obj, j);
                        byte[] bArr8 = zzcg.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = zzz(obj, j);
                        byte[] bArr9 = zzcg.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzp(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = zzz(obj, j);
                        byte[] bArr10 = zzcg.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzp(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzcg.zza(zzU(obj, j));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = ((String) zzeq.zzf(obj, j)).hashCode();
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzeq.zzf(obj, j).hashCode();
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzeq.zzf(obj, j).hashCode();
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzp(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzp(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzp(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = zzz(obj, j);
                        byte[] bArr11 = zzcg.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzp(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = zzz(obj, j);
                        byte[] bArr12 = zzcg.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzeq.zzf(obj, j).hashCode();
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.zzn.zzd(obj).hashCode();
        if (!this.zzh) {
            return hashCode;
        }
        this.zzo.zza(obj);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0403, code lost:
    
        if (r6 == 1048575) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0405, code lost:
    
        r27.putInt(r12, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x040b, code lost:
    
        r2 = r8.zzk;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x040f, code lost:
    
        if (r2 >= r8.zzl) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0411, code lost:
    
        r4 = r8.zzj[r2];
        r5 = r8.zzc[r4];
        r5 = com.google.android.gms.internal.play_billing.zzeq.zzf(r12, r8.zzy(r4) & 1048575);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0423, code lost:
    
        if (r5 != null) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x042a, code lost:
    
        if (r8.zzA(r4) != null) goto L208;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x042f, code lost:
    
        r5 = (com.google.android.gms.internal.play_billing.zzcz) r5;
        r0 = (com.google.android.gms.internal.play_billing.zzcy) r8.zzC(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0437, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x042c, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0438, code lost:
    
        if (r9 != 0) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x043c, code lost:
    
        if (r0 != r32) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0443, code lost:
    
        throw com.google.android.gms.internal.play_billing.zzci.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x044a, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0446, code lost:
    
        if (r0 > r32) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0448, code lost:
    
        if (r3 != r9) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x044f, code lost:
    
        throw com.google.android.gms.internal.play_billing.zzci.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x030e, code lost:
    
        if (r0 != r22) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0310, code lost:
    
        r15 = r28;
        r14 = r29;
        r12 = r30;
        r1 = r31;
        r13 = r32;
        r11 = r33;
        r9 = r34;
        r8 = r19;
        r5 = r20;
        r3 = r21;
        r2 = r22;
        r6 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x032c, code lost:
    
        r2 = r0;
        r7 = r21;
        r6 = r24;
        r0 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0360, code lost:
    
        if (r0 != r15) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0388, code lost:
    
        if (r0 != r15) goto L110;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:104:0x0093. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzc(java.lang.Object r29, byte[] r30, int r31, int r32, int r33, com.google.android.gms.internal.play_billing.zzan r34) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1142
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzdi.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.play_billing.zzan):int");
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final Object zze() {
        return ((zzcb) this.zzg).zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final void zzf(Object obj) {
        if (zzS(obj)) {
            if (obj instanceof zzcb) {
                zzcb zzcbVar = (zzcb) obj;
                zzcbVar.zzp(Integer.MAX_VALUE);
                zzcbVar.zza = 0;
                zzcbVar.zzn();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzy = zzy(i);
                int i2 = 1048575 & zzy;
                int zzx = zzx(zzy);
                long j = i2;
                if (zzx != 9) {
                    if (zzx == 60 || zzx == 68) {
                        if (zzT(obj, this.zzc[i], i)) {
                            zzB(i).zzf(zzb.getObject(obj, j));
                        }
                    } else {
                        switch (zzx) {
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
                                this.zzm.zza(obj, j);
                                break;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzcz) object).zzc();
                                    unsafe.putObject(obj, j, object);
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                }
                if (zzP(obj, i)) {
                    zzB(i).zzf(zzb.getObject(obj, j));
                }
            }
            this.zzn.zzg(obj);
            if (this.zzh) {
                this.zzo.zzb(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final void zzg(Object obj, Object obj2) {
        zzG(obj);
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzy = zzy(i);
            int i2 = this.zzc[i];
            long j = 1048575 & zzy;
            switch (zzx(zzy)) {
                case 0:
                    if (zzP(obj2, i)) {
                        zzeq.zzo(obj, j, zzeq.zza(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzP(obj2, i)) {
                        zzeq.zzp(obj, j, zzeq.zzb(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzP(obj2, i)) {
                        zzeq.zzr(obj, j, zzeq.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzP(obj2, i)) {
                        zzeq.zzr(obj, j, zzeq.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzP(obj2, i)) {
                        zzeq.zzq(obj, j, zzeq.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzP(obj2, i)) {
                        zzeq.zzr(obj, j, zzeq.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzP(obj2, i)) {
                        zzeq.zzq(obj, j, zzeq.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzP(obj2, i)) {
                        zzeq.zzm(obj, j, zzeq.zzw(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzP(obj2, i)) {
                        zzeq.zzs(obj, j, zzeq.zzf(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzH(obj, obj2, i);
                    break;
                case 10:
                    if (zzP(obj2, i)) {
                        zzeq.zzs(obj, j, zzeq.zzf(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzP(obj2, i)) {
                        zzeq.zzq(obj, j, zzeq.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzP(obj2, i)) {
                        zzeq.zzq(obj, j, zzeq.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzP(obj2, i)) {
                        zzeq.zzq(obj, j, zzeq.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzP(obj2, i)) {
                        zzeq.zzr(obj, j, zzeq.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzP(obj2, i)) {
                        zzeq.zzq(obj, j, zzeq.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzP(obj2, i)) {
                        zzeq.zzr(obj, j, zzeq.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzH(obj, obj2, i);
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
                    this.zzm.zzb(obj, obj2, j);
                    break;
                case 50:
                    int i3 = zzdr.zza;
                    zzeq.zzs(obj, j, zzda.zzb(zzeq.zzf(obj, j), zzeq.zzf(obj2, j)));
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
                    if (zzT(obj2, i2, i)) {
                        zzeq.zzs(obj, j, zzeq.zzf(obj2, j));
                        zzK(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzI(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzT(obj2, i2, i)) {
                        zzeq.zzs(obj, j, zzeq.zzf(obj2, j));
                        zzK(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzI(obj, obj2, i);
                    break;
            }
        }
        zzdr.zzC(this.zzn, obj, obj2);
        if (this.zzh) {
            this.zzo.zza(obj2);
            throw null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x02e8, code lost:
    
        if (r0 != r24) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x02ea, code lost:
    
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r2 = r15;
        r10 = r18;
        r1 = r23;
        r6 = r25;
        r7 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x02fd, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0329, code lost:
    
        if (r0 != r14) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x034c, code lost:
    
        if (r0 != r14) goto L102;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:63:0x0095. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzh(java.lang.Object r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.play_billing.zzan r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 970
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzdi.zzh(java.lang.Object, byte[], int, int, com.google.android.gms.internal.play_billing.zzan):void");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:17:0x01c2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x01c3 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzj(java.lang.Object r9, java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 634
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzdi.zzj(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final boolean zzk(Object obj) {
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i4 < this.zzk) {
            int i6 = this.zzj[i4];
            int i7 = this.zzc[i6];
            int zzy = zzy(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i5) {
                if (i9 != 1048575) {
                    i3 = zzb.getInt(obj, i9);
                }
                i2 = i3;
                i = i9;
            } else {
                i = i5;
                i2 = i3;
            }
            if ((268435456 & zzy) != 0 && !zzQ(obj, i6, i, i2, i10)) {
                return false;
            }
            int zzx = zzx(zzy);
            if (zzx != 9 && zzx != 17) {
                if (zzx != 27) {
                    if (zzx == 60 || zzx == 68) {
                        if (zzT(obj, i7, i6) && !zzR(obj, zzy, zzB(i6))) {
                            return false;
                        }
                    } else if (zzx != 49) {
                        if (zzx == 50 && !((zzcz) zzeq.zzf(obj, zzy & 1048575)).isEmpty()) {
                            throw null;
                        }
                    }
                }
                List list = (List) zzeq.zzf(obj, zzy & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzdp zzB = zzB(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzB.zzk(list.get(i11))) {
                            return false;
                        }
                    }
                }
            } else if (zzQ(obj, i6, i, i2, i10) && !zzR(obj, zzy, zzB(i6))) {
                return false;
            }
            i4++;
            i5 = i;
            i3 = i2;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzo.zza(obj);
        throw null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:274:0x0496. Please report as an issue. */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final void zzi(Object obj, zzey zzeyVar) throws IOException {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int i5 = 1048575;
        if (this.zzi) {
            if (!this.zzh) {
                int length = this.zzc.length;
                for (int i6 = 0; i6 < length; i6 += 3) {
                    int zzy = zzy(i6);
                    int i7 = this.zzc[i6];
                    switch (zzx(zzy)) {
                        case 0:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzf(i7, zzeq.zza(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzo(i7, zzeq.zzb(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzt(i7, zzeq.zzd(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzJ(i7, zzeq.zzd(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzr(i7, zzeq.zzc(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzm(i7, zzeq.zzd(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzk(i7, zzeq.zzc(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzb(i7, zzeq.zzw(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if (zzP(obj, i6)) {
                                zzV(i7, zzeq.zzf(obj, zzy & 1048575), zzeyVar);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzv(i7, zzeq.zzf(obj, zzy & 1048575), zzB(i6));
                                break;
                            } else {
                                break;
                            }
                        case 10:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzd(i7, (zzba) zzeq.zzf(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzH(i7, zzeq.zzc(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzi(i7, zzeq.zzc(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzw(i7, zzeq.zzc(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzy(i7, zzeq.zzd(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzA(i7, zzeq.zzc(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzC(i7, zzeq.zzd(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if (zzP(obj, i6)) {
                                zzeyVar.zzq(i7, zzeq.zzf(obj, zzy & 1048575), zzB(i6));
                                break;
                            } else {
                                break;
                            }
                        case 18:
                            zzdr.zzG(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 19:
                            zzdr.zzK(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 20:
                            zzdr.zzN(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 21:
                            zzdr.zzV(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 22:
                            zzdr.zzM(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 23:
                            zzdr.zzJ(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 24:
                            zzdr.zzI(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 25:
                            zzdr.zzE(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 26:
                            zzdr.zzT(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar);
                            break;
                        case 27:
                            zzdr.zzO(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, zzB(i6));
                            break;
                        case 28:
                            zzdr.zzF(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar);
                            break;
                        case 29:
                            zzdr.zzU(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 30:
                            zzdr.zzH(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 31:
                            zzdr.zzP(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 32:
                            zzdr.zzQ(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 33:
                            zzdr.zzR(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 34:
                            zzdr.zzS(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, false);
                            break;
                        case 35:
                            zzdr.zzG(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 36:
                            zzdr.zzK(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 37:
                            zzdr.zzN(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 38:
                            zzdr.zzV(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 39:
                            zzdr.zzM(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 40:
                            zzdr.zzJ(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 41:
                            zzdr.zzI(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 42:
                            zzdr.zzE(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 43:
                            zzdr.zzU(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 44:
                            zzdr.zzH(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 45:
                            zzdr.zzP(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 46:
                            zzdr.zzQ(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 47:
                            zzdr.zzR(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 48:
                            zzdr.zzS(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, true);
                            break;
                        case 49:
                            zzdr.zzL(i7, (List) zzeq.zzf(obj, zzy & 1048575), zzeyVar, zzB(i6));
                            break;
                        case 50:
                            zzN(zzeyVar, i7, zzeq.zzf(obj, zzy & 1048575), i6);
                            break;
                        case 51:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzf(i7, zzm(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzo(i7, zzn(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzt(i7, zzz(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzJ(i7, zzz(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzr(i7, zzp(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzm(i7, zzz(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzk(i7, zzp(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzb(i7, zzU(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (zzT(obj, i7, i6)) {
                                zzV(i7, zzeq.zzf(obj, zzy & 1048575), zzeyVar);
                                break;
                            } else {
                                break;
                            }
                        case 60:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzv(i7, zzeq.zzf(obj, zzy & 1048575), zzB(i6));
                                break;
                            } else {
                                break;
                            }
                        case 61:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzd(i7, (zzba) zzeq.zzf(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 62:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzH(i7, zzp(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzi(i7, zzp(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzw(i7, zzp(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzy(i7, zzz(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzA(i7, zzp(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzC(i7, zzz(obj, zzy & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (zzT(obj, i7, i6)) {
                                zzeyVar.zzq(i7, zzeq.zzf(obj, zzy & 1048575), zzB(i6));
                                break;
                            } else {
                                break;
                            }
                    }
                }
                zzeg zzegVar = this.zzn;
                zzegVar.zzi(zzegVar.zzd(obj), zzeyVar);
                return;
            }
            this.zzo.zza(obj);
            throw null;
        }
        if (!this.zzh) {
            int length2 = this.zzc.length;
            Unsafe unsafe = zzb;
            int i8 = 0;
            int i9 = 0;
            int i10 = 1048575;
            while (i8 < length2) {
                int zzy2 = zzy(i8);
                int[] iArr = this.zzc;
                int i11 = iArr[i8];
                int zzx = zzx(zzy2);
                if (zzx <= 17) {
                    int i12 = iArr[i8 + 2];
                    int i13 = i12 & i5;
                    if (i13 != i10) {
                        i9 = unsafe.getInt(obj, i13);
                        i10 = i13;
                    }
                    i = 1 << (i12 >>> 20);
                } else {
                    i = i4;
                }
                long j = zzy2 & i5;
                switch (zzx) {
                    case 0:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzf(i11, zzeq.zza(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzo(i11, zzeq.zzb(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzt(i11, unsafe.getLong(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzJ(i11, unsafe.getLong(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzr(i11, unsafe.getInt(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzm(i11, unsafe.getLong(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzk(i11, unsafe.getInt(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzb(i11, zzeq.zzw(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzV(i11, unsafe.getObject(obj, j), zzeyVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzv(i11, unsafe.getObject(obj, j), zzB(i8));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzd(i11, (zzba) unsafe.getObject(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzH(i11, unsafe.getInt(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzi(i11, unsafe.getInt(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzw(i11, unsafe.getInt(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzy(i11, unsafe.getLong(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzA(i11, unsafe.getInt(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzC(i11, unsafe.getLong(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        i2 = 0;
                        if ((i9 & i) != 0) {
                            zzeyVar.zzq(i11, unsafe.getObject(obj, j), zzB(i8));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        i2 = 0;
                        zzdr.zzG(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        break;
                    case 19:
                        i2 = 0;
                        zzdr.zzK(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        break;
                    case 20:
                        i2 = 0;
                        zzdr.zzN(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        break;
                    case 21:
                        i2 = 0;
                        zzdr.zzV(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        break;
                    case 22:
                        i2 = 0;
                        zzdr.zzM(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        break;
                    case 23:
                        i2 = 0;
                        zzdr.zzJ(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        break;
                    case 24:
                        i2 = 0;
                        zzdr.zzI(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        break;
                    case 25:
                        i2 = 0;
                        zzdr.zzE(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        break;
                    case 26:
                        zzdr.zzT(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar);
                        i2 = 0;
                        break;
                    case 27:
                        zzdr.zzO(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, zzB(i8));
                        i2 = 0;
                        break;
                    case 28:
                        zzdr.zzF(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar);
                        i2 = 0;
                        break;
                    case 29:
                        i3 = 0;
                        zzdr.zzU(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        i2 = i3;
                        break;
                    case 30:
                        i3 = 0;
                        zzdr.zzH(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        i2 = i3;
                        break;
                    case 31:
                        i3 = 0;
                        zzdr.zzP(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        i2 = i3;
                        break;
                    case 32:
                        i3 = 0;
                        zzdr.zzQ(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        i2 = i3;
                        break;
                    case 33:
                        i3 = 0;
                        zzdr.zzR(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        i2 = i3;
                        break;
                    case 34:
                        i3 = 0;
                        zzdr.zzS(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, false);
                        i2 = i3;
                        break;
                    case 35:
                        zzdr.zzG(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 36:
                        zzdr.zzK(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 37:
                        zzdr.zzN(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 38:
                        zzdr.zzV(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 39:
                        zzdr.zzM(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 40:
                        zzdr.zzJ(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 41:
                        zzdr.zzI(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 42:
                        zzdr.zzE(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 43:
                        zzdr.zzU(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 44:
                        zzdr.zzH(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 45:
                        zzdr.zzP(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 46:
                        zzdr.zzQ(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 47:
                        zzdr.zzR(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 48:
                        zzdr.zzS(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, true);
                        i2 = 0;
                        break;
                    case 49:
                        zzdr.zzL(this.zzc[i8], (List) unsafe.getObject(obj, j), zzeyVar, zzB(i8));
                        i2 = 0;
                        break;
                    case 50:
                        zzN(zzeyVar, i11, unsafe.getObject(obj, j), i8);
                        i2 = 0;
                        break;
                    case 51:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzf(i11, zzm(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 52:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzo(i11, zzn(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 53:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzt(i11, zzz(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 54:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzJ(i11, zzz(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 55:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzr(i11, zzp(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 56:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzm(i11, zzz(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 57:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzk(i11, zzp(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 58:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzb(i11, zzU(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 59:
                        if (zzT(obj, i11, i8)) {
                            zzV(i11, unsafe.getObject(obj, j), zzeyVar);
                        }
                        i2 = 0;
                        break;
                    case 60:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzv(i11, unsafe.getObject(obj, j), zzB(i8));
                        }
                        i2 = 0;
                        break;
                    case 61:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzd(i11, (zzba) unsafe.getObject(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 62:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzH(i11, zzp(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 63:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzi(i11, zzp(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 64:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzw(i11, zzp(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 65:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzy(i11, zzz(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 66:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzA(i11, zzp(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 67:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzC(i11, zzz(obj, j));
                        }
                        i2 = 0;
                        break;
                    case 68:
                        if (zzT(obj, i11, i8)) {
                            zzeyVar.zzq(i11, unsafe.getObject(obj, j), zzB(i8));
                        }
                        i2 = 0;
                        break;
                    default:
                        i2 = 0;
                        break;
                }
                i8 += 3;
                i4 = i2;
                i5 = 1048575;
            }
            zzeg zzegVar2 = this.zzn;
            zzegVar2.zzi(zzegVar2.zzd(obj), zzeyVar);
            return;
        }
        this.zzo.zza(obj);
        throw null;
    }
}
