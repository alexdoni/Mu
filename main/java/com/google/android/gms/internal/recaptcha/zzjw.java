package com.google.android.gms.internal.recaptcha;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzjw {

    @CheckForNull
    private static final Object zza;

    @CheckForNull
    private static final Method zzb;

    @CheckForNull
    private static final Method zzc;

    static {
        Object zzb2 = zzb();
        zza = zzb2;
        zzb = zzb2 == null ? null : zzc("getStackTraceElement", Throwable.class, Integer.TYPE);
        zzc = zzb2 != null ? zzd(zzb2) : null;
    }

    public static void zza(Throwable th) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
    }

    @CheckForNull
    private static Object zzb() {
        try {
            return Class.forName("sun.misc.SharedSecrets", false, null).getMethod("getJavaLangAccess", new Class[0]).invoke(null, new Object[0]);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @CheckForNull
    private static Method zzc(String str, Class<?>... clsArr) throws ThreadDeath {
        try {
            return Class.forName("sun.misc.JavaLangAccess", false, null).getMethod(str, clsArr);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @CheckForNull
    private static Method zzd(Object obj) {
        try {
            Method zzc2 = zzc("getStackTraceDepth", Throwable.class);
            if (zzc2 == null) {
                return null;
            }
            zzc2.invoke(obj, new Throwable());
            return zzc2;
        } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException unused) {
            return null;
        }
    }
}
