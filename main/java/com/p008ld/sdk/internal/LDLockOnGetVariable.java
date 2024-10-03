package com.p008ld.sdk.internal;

import com.p008ld.sdk.LDSdk;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDLockOnGetVariable.kt */
/* loaded from: classes2.dex */
public final class LDLockOnGetVariable<T> {
    private CountDownLatch initLatch;
    private T storedValue;

    public final T getValue() {
        waitOnInit();
        return this.storedValue;
    }

    public LDLockOnGetVariable(T t) {
        this.storedValue = t;
    }

    public LDLockOnGetVariable(final Callable<T> callable) {
        Intrinsics.checkNotNullParameter(callable, "callable");
        this.initLatch = new CountDownLatch(1);
        LDSdk.getExecutor().execute(new FutureTask(new Callable() { // from class: com.ld.sdk.internal.LDLockOnGetVariable$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void _init_$lambda$0;
                _init_$lambda$0 = LDLockOnGetVariable._init_$lambda$0(LDLockOnGetVariable.this, callable);
                return _init_$lambda$0;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void _init_$lambda$0(LDLockOnGetVariable this$0, Callable callable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callable, "$callable");
        try {
            this$0.storedValue = (T) callable.call();
        } finally {
            CountDownLatch countDownLatch = this$0.initLatch;
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    private final void waitOnInit() {
        CountDownLatch countDownLatch = this.initLatch;
        if (countDownLatch == null) {
            return;
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
        }
    }
}
