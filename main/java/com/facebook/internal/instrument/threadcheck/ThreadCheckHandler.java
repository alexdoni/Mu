package com.facebook.internal.instrument.threadcheck;

import android.os.Looper;
import android.util.Log;
import com.facebook.internal.instrument.InstrumentData;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: ThreadCheckHandler.kt */
@Metadata(m1394d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0007J,\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00042\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0002J$\u0010\u0010\u001a\u00020\t2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0007J$\u0010\u0011\u001a\u00020\t2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1395d2 = {"Lcom/facebook/internal/instrument/threadcheck/ThreadCheckHandler;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "enabled", "", "enable", "", "log", "annotation", "clazz", "Ljava/lang/Class;", "methodName", "methodDesc", "uiThreadViolationDetected", "workerThreadViolationDetected", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class ThreadCheckHandler {
    public static final ThreadCheckHandler INSTANCE = new ThreadCheckHandler();
    private static final String TAG = ThreadCheckHandler.class.getCanonicalName();
    private static boolean enabled;

    private ThreadCheckHandler() {
    }

    @JvmStatic
    public static final void enable() {
        enabled = true;
    }

    @JvmStatic
    public static final void uiThreadViolationDetected(Class<?> clazz, String methodName, String methodDesc) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(methodDesc, "methodDesc");
        INSTANCE.log("@UiThread", clazz, methodName, methodDesc);
    }

    @JvmStatic
    public static final void workerThreadViolationDetected(Class<?> clazz, String methodName, String methodDesc) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(methodDesc, "methodDesc");
        INSTANCE.log("@WorkerThread", clazz, methodName, methodDesc);
    }

    private final void log(String annotation, Class<?> clazz, String methodName, String methodDesc) {
        if (enabled) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.US, "%s annotation violation detected in %s.%s%s. Current looper is %s and main looper is %s.", Arrays.copyOf(new Object[]{annotation, clazz.getName(), methodName, methodDesc, Looper.myLooper(), Looper.getMainLooper()}, 6));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(locale, format, *args)");
            Exception exc = new Exception();
            Log.e(TAG, format, exc);
            InstrumentData.Builder builder = InstrumentData.Builder.INSTANCE;
            InstrumentData.Builder.build(exc, InstrumentData.Type.ThreadCheck).save();
        }
    }
}
