package kotlinx.coroutines.internal;

import kotlin.Metadata;

/* compiled from: LimitedDispatcher.kt */
@Metadata(m1394d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\b\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, m1395d2 = {"checkParallelism", "", "", "kotlinx-coroutines-core"}, m1396k = 2, m1397mv = {1, 8, 0}, m1399xi = 48)
/* loaded from: classes3.dex */
public final class LimitedDispatcherKt {
    public static final void checkParallelism(int i) {
        if (i >= 1) {
            return;
        }
        throw new IllegalArgumentException(("Expected positive parallelism level, but got " + i).toString());
    }
}
