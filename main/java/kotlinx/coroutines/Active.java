package kotlinx.coroutines;

import kotlin.Metadata;

/* compiled from: CancellableContinuationImpl.kt */
@Metadata(m1394d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m1395d2 = {"Lkotlinx/coroutines/Active;", "Lkotlinx/coroutines/NotCompleted;", "()V", "toString", "", "kotlinx-coroutines-core"}, m1396k = 1, m1397mv = {1, 8, 0}, m1399xi = 48)
/* loaded from: classes3.dex */
final class Active implements NotCompleted {
    public static final Active INSTANCE = new Active();

    public String toString() {
        return "Active";
    }

    private Active() {
    }
}
