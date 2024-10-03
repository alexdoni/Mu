package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Metadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Future.kt */
@Metadata(m1394d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0007¨\u0006\b"}, m1395d2 = {"cancelFutureOnCancellation", "", "Lkotlinx/coroutines/CancellableContinuation;", "future", "Ljava/util/concurrent/Future;", "cancelFutureOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/Job;", "kotlinx-coroutines-core"}, m1396k = 5, m1397mv = {1, 8, 0}, m1399xi = 48, m1400xs = "kotlinx/coroutines/JobKt")
/* loaded from: classes3.dex */
public final /* synthetic */ class JobKt__FutureKt {
    public static final DisposableHandle cancelFutureOnCompletion(Job job, Future<?> future) {
        return job.invokeOnCompletion(new CancelFutureOnCompletion(future));
    }

    public static final void cancelFutureOnCancellation(CancellableContinuation<?> cancellableContinuation, Future<?> future) {
        cancellableContinuation.invokeOnCancellation(new CancelFutureOnCancel(future));
    }
}
