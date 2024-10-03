package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzqv;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzqw<T extends zzqv<T>> {
    private static final zzqw zzb = new zzqw(true);
    final zztk<T, Object> zza = new zztd(16);
    private boolean zzc;
    private boolean zzd;

    private zzqw() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0018. Please report as an issue. */
    public static int zza(zzuh zzuhVar, int i, Object obj) {
        int zzJ = zzqj.zzJ(i);
        if (zzuhVar == zzuh.GROUP) {
            zzrp.zzi((zzsn) obj);
            zzJ += zzJ;
        }
        zzui zzuiVar = zzui.INT;
        int i2 = 4;
        switch (zzuhVar) {
            case DOUBLE:
                ((Double) obj).doubleValue();
                i2 = 8;
                return zzJ + i2;
            case FLOAT:
                ((Float) obj).floatValue();
                return zzJ + i2;
            case INT64:
                i2 = zzqj.zzL(((Long) obj).longValue());
                return zzJ + i2;
            case UINT64:
                i2 = zzqj.zzL(((Long) obj).longValue());
                return zzJ + i2;
            case INT32:
                i2 = zzqj.zzD(((Integer) obj).intValue());
                return zzJ + i2;
            case FIXED64:
                ((Long) obj).longValue();
                i2 = 8;
                return zzJ + i2;
            case FIXED32:
                ((Integer) obj).intValue();
                return zzJ + i2;
            case BOOL:
                ((Boolean) obj).booleanValue();
                i2 = 1;
                return zzJ + i2;
            case STRING:
                if (obj instanceof zzpy) {
                    i2 = zzqj.zzA((zzpy) obj);
                } else {
                    i2 = zzqj.zzI((String) obj);
                }
                return zzJ + i2;
            case GROUP:
                i2 = zzqj.zzC((zzsn) obj);
                return zzJ + i2;
            case MESSAGE:
                if (obj instanceof zzrt) {
                    i2 = zzqj.zzE((zzrt) obj);
                } else {
                    i2 = zzqj.zzF((zzsn) obj);
                }
                return zzJ + i2;
            case BYTES:
                if (obj instanceof zzpy) {
                    i2 = zzqj.zzA((zzpy) obj);
                } else {
                    i2 = zzqj.zzz((byte[]) obj);
                }
                return zzJ + i2;
            case UINT32:
                i2 = zzqj.zzK(((Integer) obj).intValue());
                return zzJ + i2;
            case ENUM:
                if (obj instanceof zzri) {
                    i2 = zzqj.zzD(((zzri) obj).zza());
                } else {
                    i2 = zzqj.zzD(((Integer) obj).intValue());
                }
                return zzJ + i2;
            case SFIXED32:
                ((Integer) obj).intValue();
                return zzJ + i2;
            case SFIXED64:
                ((Long) obj).longValue();
                i2 = 8;
                return zzJ + i2;
            case SINT32:
                int intValue = ((Integer) obj).intValue();
                i2 = zzqj.zzK((intValue >> 31) ^ (intValue + intValue));
                return zzJ + i2;
            case SINT64:
                long longValue = ((Long) obj).longValue();
                i2 = zzqj.zzL((longValue >> 63) ^ (longValue + longValue));
                return zzJ + i2;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static <T extends zzqv<T>> zzqw<T> zzb() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zze(zzqj zzqjVar, zzuh zzuhVar, int i, Object obj) throws IOException {
        if (zzuhVar != zzuh.GROUP) {
            zzqjVar.zzt(i, zzuhVar.zza());
            zzui zzuiVar = zzui.INT;
            switch (zzuhVar) {
                case DOUBLE:
                    zzqjVar.zzm(Double.doubleToRawLongBits(((Double) obj).doubleValue()));
                    return;
                case FLOAT:
                    zzqjVar.zzk(Float.floatToRawIntBits(((Float) obj).floatValue()));
                    return;
                case INT64:
                    zzqjVar.zzx(((Long) obj).longValue());
                    return;
                case UINT64:
                    zzqjVar.zzx(((Long) obj).longValue());
                    return;
                case INT32:
                    zzqjVar.zzo(((Integer) obj).intValue());
                    return;
                case FIXED64:
                    zzqjVar.zzm(((Long) obj).longValue());
                    return;
                case FIXED32:
                    zzqjVar.zzk(((Integer) obj).intValue());
                    return;
                case BOOL:
                    zzqjVar.zzU(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
                    return;
                case STRING:
                    if (obj instanceof zzpy) {
                        zzqjVar.zzi((zzpy) obj);
                        return;
                    } else {
                        zzqjVar.zzs((String) obj);
                        return;
                    }
                case GROUP:
                    ((zzsn) obj).zzM(zzqjVar);
                    return;
                case MESSAGE:
                    zzqjVar.zzq((zzsn) obj);
                    return;
                case BYTES:
                    if (obj instanceof zzpy) {
                        zzqjVar.zzi((zzpy) obj);
                        return;
                    } else {
                        byte[] bArr = (byte[]) obj;
                        zzqjVar.zzW(bArr, 0, bArr.length);
                        return;
                    }
                case UINT32:
                    zzqjVar.zzv(((Integer) obj).intValue());
                    return;
                case ENUM:
                    if (obj instanceof zzri) {
                        zzqjVar.zzo(((zzri) obj).zza());
                        return;
                    } else {
                        zzqjVar.zzo(((Integer) obj).intValue());
                        return;
                    }
                case SFIXED32:
                    zzqjVar.zzk(((Integer) obj).intValue());
                    return;
                case SFIXED64:
                    zzqjVar.zzm(((Long) obj).longValue());
                    return;
                case SINT32:
                    int intValue = ((Integer) obj).intValue();
                    zzqjVar.zzv((intValue >> 31) ^ (intValue + intValue));
                    return;
                case SINT64:
                    long longValue = ((Long) obj).longValue();
                    zzqjVar.zzx((longValue >> 63) ^ (longValue + longValue));
                    return;
                default:
                    return;
            }
        }
        zzsn zzsnVar = (zzsn) obj;
        zzrp.zzi(zzsnVar);
        zzqjVar.zzt(i, 3);
        zzsnVar.zzM(zzqjVar);
        zzqjVar.zzt(i, 4);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void zzf(T r4, java.lang.Object r5) {
        /*
            com.google.android.gms.internal.recaptcha.zzuh r0 = r4.zzb()
            com.google.android.gms.internal.recaptcha.zzrp.zze(r5)
            com.google.android.gms.internal.recaptcha.zzuh r1 = com.google.android.gms.internal.recaptcha.zzuh.DOUBLE
            com.google.android.gms.internal.recaptcha.zzui r1 = com.google.android.gms.internal.recaptcha.zzui.INT
            com.google.android.gms.internal.recaptcha.zzui r0 = r0.zzb()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L41;
                case 1: goto L3e;
                case 2: goto L3b;
                case 3: goto L38;
                case 4: goto L35;
                case 5: goto L32;
                case 6: goto L29;
                case 7: goto L20;
                case 8: goto L17;
                default: goto L16;
            }
        L16:
            goto L46
        L17:
            boolean r0 = r5 instanceof com.google.android.gms.internal.recaptcha.zzsn
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof com.google.android.gms.internal.recaptcha.zzrt
            if (r0 == 0) goto L46
            goto L45
        L20:
            boolean r0 = r5 instanceof java.lang.Integer
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof com.google.android.gms.internal.recaptcha.zzri
            if (r0 == 0) goto L46
            goto L45
        L29:
            boolean r0 = r5 instanceof com.google.android.gms.internal.recaptcha.zzpy
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof byte[]
            if (r0 == 0) goto L46
            goto L45
        L32:
            boolean r0 = r5 instanceof java.lang.String
            goto L43
        L35:
            boolean r0 = r5 instanceof java.lang.Boolean
            goto L43
        L38:
            boolean r0 = r5 instanceof java.lang.Double
            goto L43
        L3b:
            boolean r0 = r5 instanceof java.lang.Float
            goto L43
        L3e:
            boolean r0 = r5 instanceof java.lang.Long
            goto L43
        L41:
            boolean r0 = r5 instanceof java.lang.Integer
        L43:
            if (r0 == 0) goto L46
        L45:
            return
        L46:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            int r2 = r4.zza()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3 = 0
            r1[r3] = r2
            com.google.android.gms.internal.recaptcha.zzuh r4 = r4.zzb()
            com.google.android.gms.internal.recaptcha.zzui r4 = r4.zzb()
            r2 = 1
            r1[r2] = r4
            java.lang.Class r4 = r5.getClass()
            java.lang.String r4 = r4.getName()
            r5 = 2
            r1[r5] = r4
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r4 = java.lang.String.format(r4, r1)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.recaptcha.zzqw.zzf(com.google.android.gms.internal.recaptcha.zzqv, java.lang.Object):void");
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzqw zzqwVar = new zzqw();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry<T, Object> zzg = this.zza.zzg(i);
            zzqwVar.zzd(zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry<T, Object> entry : this.zza.zzc()) {
            zzqwVar.zzd(entry.getKey(), entry.getValue());
        }
        zzqwVar.zzd = this.zzd;
        return zzqwVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzqw) {
            return this.zza.equals(((zzqw) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzc() {
        if (this.zzc) {
            return;
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzd(T t, Object obj) {
        if (t.zzc()) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzf(t, arrayList.get(i));
            }
            obj = arrayList;
        } else {
            zzf(t, obj);
        }
        if (obj instanceof zzrt) {
            this.zzd = true;
        }
        this.zza.put(t, obj);
    }

    private zzqw(boolean z) {
        zzc();
        zzc();
    }
}
