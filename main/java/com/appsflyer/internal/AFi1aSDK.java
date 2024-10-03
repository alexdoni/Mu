package com.appsflyer.internal;

import android.content.Intent;
import com.appsflyer.AFLogger;
import java.util.ConcurrentModificationException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* loaded from: classes.dex */
public final class AFi1aSDK {
    final Intent AFInAppEventParameterName;

    public AFi1aSDK(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "");
        this.AFInAppEventParameterName = intent;
    }

    public final String AFKeystoreWrapper(final String str) {
        Intrinsics.checkNotNullParameter(str, "");
        Function0<String> function0 = new Function0<String>() { // from class: com.appsflyer.internal.AFi1aSDK.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: values, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return AFi1aSDK.this.AFInAppEventParameterName.getStringExtra(str);
            }
        };
        StringBuilder sb = new StringBuilder("Error while trying to read ");
        sb.append(str);
        sb.append(" extra from intent");
        return (String) AFInAppEventType(function0, sb.toString(), null, true);
    }

    public final boolean AFInAppEventType(final String str) {
        Intrinsics.checkNotNullParameter(str, "");
        Function0<Boolean> function0 = new Function0<Boolean>() { // from class: com.appsflyer.internal.AFi1aSDK.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: values, reason: merged with bridge method [inline-methods] */
            public final Boolean invoke() {
                return Boolean.valueOf(AFi1aSDK.this.AFInAppEventParameterName.hasExtra(str));
            }
        };
        StringBuilder sb = new StringBuilder("Error while trying to check presence of ");
        sb.append(str);
        sb.append(" extra from intent");
        Boolean bool = (Boolean) AFInAppEventType(function0, sb.toString(), Boolean.TRUE, true);
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    public final Intent AFInAppEventType(final String str, final long j) {
        Intrinsics.checkNotNullParameter(str, "");
        Function0<Intent> function0 = new Function0<Intent>() { // from class: com.appsflyer.internal.AFi1aSDK.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFInAppEventType, reason: merged with bridge method [inline-methods] */
            public final Intent invoke() {
                return AFi1aSDK.this.AFInAppEventParameterName.putExtra(str, j);
            }
        };
        StringBuilder sb = new StringBuilder("Error while trying to write ");
        sb.append(str);
        sb.append(" extra to intent");
        return (Intent) AFInAppEventType(function0, sb.toString(), null, true);
    }

    public final <T> T AFInAppEventType(Function0<? extends T> function0, String str, T t, boolean z) {
        Object m1882constructorimpl;
        Object m1882constructorimpl2;
        Object obj;
        Object m1882constructorimpl3;
        synchronized (this.AFInAppEventParameterName) {
            try {
                Result.Companion companion = Result.INSTANCE;
                AFi1aSDK aFi1aSDK = this;
                m1882constructorimpl = Result.m1882constructorimpl(function0.invoke());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
            }
            KClass[] kClassArr = {Reflection.getOrCreateKotlinClass(ConcurrentModificationException.class), Reflection.getOrCreateKotlinClass(ArrayIndexOutOfBoundsException.class)};
            Throwable m1885exceptionOrNullimpl = Result.m1885exceptionOrNullimpl(m1882constructorimpl);
            if (m1885exceptionOrNullimpl != null) {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    m1882constructorimpl2 = Result.m1882constructorimpl(ResultKt.createFailure(th2));
                }
                if (ArraysKt.contains(kClassArr, Reflection.getOrCreateKotlinClass(m1885exceptionOrNullimpl.getClass()))) {
                    if (z) {
                        obj = AFInAppEventType(function0, str, t, false);
                    } else {
                        AFLogger.afErrorLog(str, m1885exceptionOrNullimpl, false, false);
                        obj = t;
                    }
                    m1882constructorimpl2 = Result.m1882constructorimpl(obj);
                    m1882constructorimpl = m1882constructorimpl2;
                } else {
                    throw m1885exceptionOrNullimpl;
                }
            }
            KClass[] kClassArr2 = {Reflection.getOrCreateKotlinClass(RuntimeException.class)};
            Throwable m1885exceptionOrNullimpl2 = Result.m1885exceptionOrNullimpl(m1882constructorimpl);
            if (m1885exceptionOrNullimpl2 != null) {
                try {
                    Result.Companion companion5 = Result.INSTANCE;
                } catch (Throwable th3) {
                    Result.Companion companion6 = Result.INSTANCE;
                    m1882constructorimpl3 = Result.m1882constructorimpl(ResultKt.createFailure(th3));
                }
                if (ArraysKt.contains(kClassArr2, Reflection.getOrCreateKotlinClass(m1885exceptionOrNullimpl2.getClass()))) {
                    AFLogger.afErrorLog(str, m1885exceptionOrNullimpl2, false, false);
                    m1882constructorimpl3 = Result.m1882constructorimpl(t);
                    m1882constructorimpl = (T) m1882constructorimpl3;
                } else {
                    throw m1885exceptionOrNullimpl2;
                }
            }
            ResultKt.throwOnFailure(m1882constructorimpl);
        }
        return (T) m1882constructorimpl;
    }
}
