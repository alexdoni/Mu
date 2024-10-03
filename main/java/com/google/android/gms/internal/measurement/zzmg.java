package com.google.android.gms.internal.measurement;

import com.facebook.appevents.integrity.IntegrityManager;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzmg {
    static final boolean zza;
    private static final Unsafe zzb;
    private static final Class<?> zzc;
    private static final boolean zzd;
    private static final boolean zze;
    private static final zzc zzf;
    private static final boolean zzg;
    private static final boolean zzh;
    private static final long zzi;
    private static final long zzj;
    private static final long zzk;
    private static final long zzl;
    private static final long zzm;
    private static final long zzn;
    private static final long zzo;
    private static final long zzp;
    private static final long zzq;
    private static final long zzr;
    private static final long zzs;
    private static final long zzt;
    private static final long zzu;
    private static final long zzv;
    private static final int zzw;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
    /* loaded from: classes2.dex */
    private static final class zza extends zzc {
        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final double zza(Object obj, long j) {
            return Double.longBitsToDouble(zze(obj, j));
        }

        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final float zzb(Object obj, long j) {
            return Float.intBitsToFloat(zzd(obj, j));
        }

        zza(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final void zza(Object obj, long j, boolean z) {
            if (zzmg.zza) {
                zzmg.zza(obj, j, z);
            } else {
                zzmg.zzb(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final void zza(Object obj, long j, byte b) {
            if (zzmg.zza) {
                zzmg.zzc(obj, j, b);
            } else {
                zzmg.zzd(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final boolean zzc(Object obj, long j) {
            if (zzmg.zza) {
                return zzmg.zzf(obj, j);
            }
            return zzmg.zzg(obj, j);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
    /* loaded from: classes2.dex */
    private static final class zzb extends zzc {
        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final double zza(Object obj, long j) {
            return Double.longBitsToDouble(zze(obj, j));
        }

        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final float zzb(Object obj, long j) {
            return Float.intBitsToFloat(zzd(obj, j));
        }

        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final void zza(Object obj, long j, boolean z) {
            if (zzmg.zza) {
                zzmg.zza(obj, j, z);
            } else {
                zzmg.zzb(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final void zza(Object obj, long j, byte b) {
            if (zzmg.zza) {
                zzmg.zzc(obj, j, b);
            } else {
                zzmg.zzd(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.measurement.zzmg.zzc
        public final boolean zzc(Object obj, long j) {
            if (zzmg.zza) {
                return zzmg.zzf(obj, j);
            }
            return zzmg.zzg(obj, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
    /* loaded from: classes2.dex */
    public static abstract class zzc {
        Unsafe zza;

        public abstract double zza(Object obj, long j);

        public abstract void zza(Object obj, long j, byte b);

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract float zzb(Object obj, long j);

        public abstract boolean zzc(Object obj, long j);

        public final int zzd(Object obj, long j) {
            return this.zza.getInt(obj, j);
        }

        public final long zze(Object obj, long j) {
            return this.zza.getLong(obj, j);
        }

        zzc(Unsafe unsafe) {
            this.zza = unsafe;
        }

        public final void zza(Object obj, long j, int i) {
            this.zza.putInt(obj, j, i);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zza.putLong(obj, j, j2);
        }

        public final boolean zza() {
            Unsafe unsafe = this.zza;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                cls.getMethod("arrayBaseOffset", Class.class);
                cls.getMethod("arrayIndexScale", Class.class);
                cls.getMethod("getInt", Object.class, Long.TYPE);
                cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                cls.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
                cls.getMethod("getObject", Object.class, Long.TYPE);
                cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
                return true;
            } catch (Throwable th) {
                zzmg.zza(th);
                return false;
            }
        }

        public final boolean zzb() {
            Unsafe unsafe = this.zza;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                return zzmg.zza() != null;
            } catch (Throwable th) {
                zzmg.zza(th);
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double zza(Object obj, long j) {
        return zzf.zza(obj, j);
    }

    static /* synthetic */ Field zza() {
        return zze();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float zzb(Object obj, long j) {
        return zzf.zzb(obj, j);
    }

    private static int zzb(Class<?> cls) {
        if (zzh) {
            return zzf.zza.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzc(Class<?> cls) {
        if (zzh) {
            return zzf.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(Object obj, long j) {
        return zzf.zzd(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzd(Object obj, long j) {
        return zzf.zze(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zza(Class<T> cls) {
        try {
            return (T) zzb.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zze(Object obj, long j) {
        return zzf.zza.getObject(obj, j);
    }

    private static Field zze() {
        Field zza2 = zza((Class<?>) Buffer.class, "effectiveDirectAddress");
        if (zza2 != null) {
            return zza2;
        }
        Field zza3 = zza((Class<?>) Buffer.class, IntegrityManager.INTEGRITY_TYPE_ADDRESS);
        if (zza3 == null || zza3.getType() != Long.TYPE) {
            return null;
        }
        return zza3;
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Unsafe zzb() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzmf());
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* synthetic */ void zza(Throwable th) {
        Logger.getLogger(zzmg.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: " + String.valueOf(th));
    }

    static /* synthetic */ void zza(Object obj, long j, boolean z) {
        zzc(obj, j, z ? (byte) 1 : (byte) 0);
    }

    static /* synthetic */ void zzb(Object obj, long j, boolean z) {
        zzd(obj, j, z ? (byte) 1 : (byte) 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0034  */
    static {
        /*
            sun.misc.Unsafe r0 = zzb()
            com.google.android.gms.internal.measurement.zzmg.zzb = r0
            java.lang.Class r1 = com.google.android.gms.internal.measurement.zzhj.zza()
            com.google.android.gms.internal.measurement.zzmg.zzc = r1
            java.lang.Class r1 = java.lang.Long.TYPE
            boolean r1 = zzd(r1)
            com.google.android.gms.internal.measurement.zzmg.zzd = r1
            java.lang.Class r2 = java.lang.Integer.TYPE
            boolean r2 = zzd(r2)
            com.google.android.gms.internal.measurement.zzmg.zze = r2
            if (r0 == 0) goto L2e
            if (r1 == 0) goto L26
            com.google.android.gms.internal.measurement.zzmg$zza r1 = new com.google.android.gms.internal.measurement.zzmg$zza
            r1.<init>(r0)
            goto L2f
        L26:
            if (r2 == 0) goto L2e
            com.google.android.gms.internal.measurement.zzmg$zzb r1 = new com.google.android.gms.internal.measurement.zzmg$zzb
            r1.<init>(r0)
            goto L2f
        L2e:
            r1 = 0
        L2f:
            com.google.android.gms.internal.measurement.zzmg.zzf = r1
            r0 = 0
            if (r1 != 0) goto L36
            r2 = r0
            goto L3a
        L36:
            boolean r2 = r1.zzb()
        L3a:
            com.google.android.gms.internal.measurement.zzmg.zzg = r2
            if (r1 != 0) goto L40
            r2 = r0
            goto L44
        L40:
            boolean r2 = r1.zza()
        L44:
            com.google.android.gms.internal.measurement.zzmg.zzh = r2
            java.lang.Class<byte[]> r2 = byte[].class
            int r2 = zzb(r2)
            long r2 = (long) r2
            com.google.android.gms.internal.measurement.zzmg.zzi = r2
            java.lang.Class<boolean[]> r4 = boolean[].class
            int r5 = zzb(r4)
            long r5 = (long) r5
            com.google.android.gms.internal.measurement.zzmg.zzj = r5
            int r4 = zzc(r4)
            long r4 = (long) r4
            com.google.android.gms.internal.measurement.zzmg.zzk = r4
            java.lang.Class<int[]> r4 = int[].class
            int r5 = zzb(r4)
            long r5 = (long) r5
            com.google.android.gms.internal.measurement.zzmg.zzl = r5
            int r4 = zzc(r4)
            long r4 = (long) r4
            com.google.android.gms.internal.measurement.zzmg.zzm = r4
            java.lang.Class<long[]> r4 = long[].class
            int r5 = zzb(r4)
            long r5 = (long) r5
            com.google.android.gms.internal.measurement.zzmg.zzn = r5
            int r4 = zzc(r4)
            long r4 = (long) r4
            com.google.android.gms.internal.measurement.zzmg.zzo = r4
            java.lang.Class<float[]> r4 = float[].class
            int r5 = zzb(r4)
            long r5 = (long) r5
            com.google.android.gms.internal.measurement.zzmg.zzp = r5
            int r4 = zzc(r4)
            long r4 = (long) r4
            com.google.android.gms.internal.measurement.zzmg.zzq = r4
            java.lang.Class<double[]> r4 = double[].class
            int r5 = zzb(r4)
            long r5 = (long) r5
            com.google.android.gms.internal.measurement.zzmg.zzr = r5
            int r4 = zzc(r4)
            long r4 = (long) r4
            com.google.android.gms.internal.measurement.zzmg.zzs = r4
            java.lang.Class<java.lang.Object[]> r4 = java.lang.Object[].class
            int r4 = zzb(r4)
            long r4 = (long) r4
            com.google.android.gms.internal.measurement.zzmg.zzt = r4
            java.lang.Class<java.lang.Object[]> r4 = java.lang.Object[].class
            int r4 = zzc(r4)
            long r4 = (long) r4
            com.google.android.gms.internal.measurement.zzmg.zzu = r4
            java.lang.reflect.Field r4 = zze()
            if (r4 == 0) goto Lc1
            if (r1 != 0) goto Lba
            goto Lc1
        Lba:
            sun.misc.Unsafe r1 = r1.zza
            long r4 = r1.objectFieldOffset(r4)
            goto Lc3
        Lc1:
            r4 = -1
        Lc3:
            com.google.android.gms.internal.measurement.zzmg.zzv = r4
            r4 = 7
            long r1 = r2 & r4
            int r1 = (int) r1
            com.google.android.gms.internal.measurement.zzmg.zzw = r1
            java.nio.ByteOrder r1 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r2 = java.nio.ByteOrder.BIG_ENDIAN
            if (r1 != r2) goto Ld5
            r0 = 1
        Ld5:
            com.google.android.gms.internal.measurement.zzmg.zza = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzmg.<clinit>():void");
    }

    private zzmg() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzc(Object obj, long j, boolean z) {
        zzf.zza(obj, j, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(byte[] bArr, long j, byte b) {
        zzf.zza((Object) bArr, zzi + j, b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        int zzc2 = zzc(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zzc2 & (~(255 << i))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzd(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        int i = (((int) j) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zzc(obj, j2) & (~(255 << i))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j, double d) {
        zzf.zza(obj, j, d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j, float f) {
        zzf.zza(obj, j, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j, int i) {
        zzf.zza(obj, j, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j, long j2) {
        zzf.zza(obj, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j, Object obj2) {
        zzf.zza.putObject(obj, j, obj2);
    }

    static /* synthetic */ boolean zzf(Object obj, long j) {
        return ((byte) (zzc(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3)))) != 0;
    }

    static /* synthetic */ boolean zzg(Object obj, long j) {
        return ((byte) (zzc(obj, (-4) & j) >>> ((int) ((j & 3) << 3)))) != 0;
    }

    private static boolean zzd(Class<?> cls) {
        try {
            Class<?> cls2 = zzc;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzh(Object obj, long j) {
        return zzf.zzc(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzc() {
        return zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzd() {
        return zzg;
    }
}
