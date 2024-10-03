package com.facebook.bolts;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExecutorException.kt */
@Metadata(m1394d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0011\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m1395d2 = {"Lcom/facebook/bolts/ExecutorException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;)V", "facebook-bolts_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class ExecutorException extends RuntimeException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExecutorException(Exception e) {
        super("An exception was thrown by an Executor", e);
        Intrinsics.checkNotNullParameter(e, "e");
    }
}
