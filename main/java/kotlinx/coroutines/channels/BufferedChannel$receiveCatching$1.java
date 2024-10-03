package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BufferedChannel.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = 48)
@DebugMetadata(m1414c = "kotlinx.coroutines.channels.BufferedChannel", m1415f = "BufferedChannel.kt", m1416i = {}, m1417l = {739}, m1418m = "receiveCatching-JP2dKIU$suspendImpl", m1419n = {}, m1420s = {})
/* loaded from: classes3.dex */
public final class BufferedChannel$receiveCatching$1<E> extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BufferedChannel<E> this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BufferedChannel$receiveCatching$1(BufferedChannel<E> bufferedChannel, Continuation<? super BufferedChannel$receiveCatching$1> continuation) {
        super(continuation);
        this.this$0 = bufferedChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object m3387receiveCatchingJP2dKIU$suspendImpl = BufferedChannel.m3387receiveCatchingJP2dKIU$suspendImpl(this.this$0, this);
        return m3387receiveCatchingJP2dKIU$suspendImpl == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? m3387receiveCatchingJP2dKIU$suspendImpl : ChannelResult.m3395boximpl(m3387receiveCatchingJP2dKIU$suspendImpl);
    }
}
