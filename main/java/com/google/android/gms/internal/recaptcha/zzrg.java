package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzrb;
import com.google.android.gms.internal.recaptcha.zzrg;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzrg<MessageType extends zzrg<MessageType, BuilderType>, BuilderType extends zzrb<MessageType, BuilderType>> extends zzph<MessageType, BuilderType> {
    private static final Map<Object, zzrg<?, ?>> zzb = new ConcurrentHashMap();
    protected zzts zzc = zzts.zzc();
    protected int zzd = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzB(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object zzC(zzsn zzsnVar, String str, Object[] objArr) {
        return new zzsy(zzsnVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends zzrg> void zzD(Class<T> cls, T t) {
        zzb.put(cls, t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends zzrg> T zzw(Class<T> cls) {
        Map<Object, zzrg<?, ?>> map = zzb;
        zzrg<?, ?> zzrgVar = map.get(cls);
        if (zzrgVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzrgVar = map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzrgVar == null) {
            zzrgVar = (zzrg) ((zzrg) zzub.zze(cls)).zzh(6, null, null);
            if (zzrgVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzrgVar);
        }
        return zzrgVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends zzrg<T, ?>> T zzx(T t, zzqc zzqcVar, zzqr zzqrVar) throws zzrr {
        T t2 = (T) t.zzh(4, null, null);
        try {
            zzta zzb2 = zzsw.zza().zzb(t2.getClass());
            zzb2.zzf(t2, zzqd.zzq(zzqcVar), zzqrVar);
            zzb2.zzd(t2);
            return t2;
        } catch (zzrr e) {
            e = e;
            if (e.zzl()) {
                e = new zzrr(e);
            }
            e.zzh(t2);
            throw e;
        } catch (IOException e2) {
            if (e2.getCause() instanceof zzrr) {
                throw ((zzrr) e2.getCause());
            }
            zzrr zzrrVar = new zzrr(e2);
            zzrrVar.zzh(t2);
            throw zzrrVar;
        } catch (RuntimeException e3) {
            if (e3.getCause() instanceof zzrr) {
                throw ((zzrr) e3.getCause());
            }
            throw e3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzrl zzy() {
        return zzrh.zzf();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <E> zzro<E> zzz() {
        return zzsx.zze();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzsw.zza().zzb(getClass()).zzh(this, (zzrg) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzb2 = zzsw.zza().zzb(getClass()).zzb(this);
        this.zza = zzb2;
        return zzb2;
    }

    public final String toString() {
        return zzsp.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsn
    public final zzsu<MessageType> zzA() {
        return (zzsu) zzh(7, null, null);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsn
    public final /* bridge */ /* synthetic */ zzsm zzK() {
        return (zzrb) zzh(5, null, null);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsn
    public final /* bridge */ /* synthetic */ zzsm zzL() {
        zzrb zzrbVar = (zzrb) zzh(5, null, null);
        zzrbVar.zzj(this);
        return zzrbVar;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsn
    public final void zzM(zzqj zzqjVar) throws IOException {
        zzsw.zza().zzb(getClass()).zzg(this, zzqk.zza(zzqjVar));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzso
    public final /* bridge */ /* synthetic */ zzsn zzO() {
        return (zzrg) zzh(6, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zzh(int i, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.recaptcha.zzph
    final int zzo() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzph
    final void zzr(int i) {
        this.zzd = i;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsn
    public final int zzt() {
        int i = this.zzd;
        if (i != -1) {
            return i;
        }
        int zza = zzsw.zza().zzb(getClass()).zza(this);
        this.zzd = zza;
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <MessageType extends zzrg<MessageType, BuilderType>, BuilderType extends zzrb<MessageType, BuilderType>> BuilderType zzu() {
        return (BuilderType) zzh(5, null, null);
    }

    public final BuilderType zzv() {
        BuilderType buildertype = (BuilderType) zzh(5, null, null);
        buildertype.zzj(this);
        return buildertype;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzso
    public final boolean zzp() {
        Boolean bool = Boolean.TRUE;
        byte byteValue = ((Byte) zzh(1, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzi = zzsw.zza().zzb(getClass()).zzi(this);
        zzh(2, true != zzi ? null : this, null);
        return zzi;
    }
}
