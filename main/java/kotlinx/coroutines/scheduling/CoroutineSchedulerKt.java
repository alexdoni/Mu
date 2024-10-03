package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlinx.coroutines.scheduling.CoroutineScheduler;

/* compiled from: CoroutineScheduler.kt */
@Metadata(m1394d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¨\u0006\u0005"}, m1395d2 = {"isSchedulerWorker", "", "thread", "Ljava/lang/Thread;", "mayNotBlock", "kotlinx-coroutines-core"}, m1396k = 2, m1397mv = {1, 8, 0}, m1399xi = 48)
/* loaded from: classes3.dex */
public final class CoroutineSchedulerKt {
    public static final boolean isSchedulerWorker(Thread thread) {
        return thread instanceof CoroutineScheduler.Worker;
    }

    public static final boolean mayNotBlock(Thread thread) {
        return (thread instanceof CoroutineScheduler.Worker) && ((CoroutineScheduler.Worker) thread).state == CoroutineScheduler.WorkerState.CPU_ACQUIRED;
    }
}
