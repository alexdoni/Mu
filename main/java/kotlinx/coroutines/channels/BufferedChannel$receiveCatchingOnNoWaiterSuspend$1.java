package kotlinx.coroutines.channels;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BufferedChannel.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = 48)
@DebugMetadata(m1414c = "kotlinx.coroutines.channels.BufferedChannel", m1415f = "BufferedChannel.kt", m1416i = {0, 0, 0, 0}, m1417l = {3056}, m1418m = "receiveCatchingOnNoWaiterSuspend-GKJJFZk", m1419n = {"this", "segment", FirebaseAnalytics.Param.INDEX, "r"}, m1420s = {"L$0", "L$1", "I$0", "J$0"})
/* loaded from: classes3.dex */
public final class BufferedChannel$receiveCatchingOnNoWaiterSuspend$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BufferedChannel<E> this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BufferedChannel$receiveCatchingOnNoWaiterSuspend$1(BufferedChannel<E> bufferedChannel, Continuation<? super BufferedChannel$receiveCatchingOnNoWaiterSuspend$1> continuation) {
        super(continuation);
        this.this$0 = bufferedChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m3388receiveCatchingOnNoWaiterSuspendGKJJFZk;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        m3388receiveCatchingOnNoWaiterSuspendGKJJFZk = this.this$0.m3388receiveCatchingOnNoWaiterSuspendGKJJFZk(null, 0, 0L, this);
        return m3388receiveCatchingOnNoWaiterSuspendGKJJFZk == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? m3388receiveCatchingOnNoWaiterSuspendGKJJFZk : ChannelResult.m3395boximpl(m3388receiveCatchingOnNoWaiterSuspendGKJJFZk);
    }
}
