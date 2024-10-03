package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Deprecated.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = 48)
@DebugMetadata(m1414c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m1415f = "Deprecated.kt", m1416i = {0, 0, 1, 1, 1}, m1417l = {97, 100}, m1418m = "last", m1419n = {"$this$consume$iv", "iterator", "$this$consume$iv", "iterator", "last"}, m1420s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$last$1<E> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$last$1(Continuation<? super ChannelsKt__DeprecatedKt$last$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object last;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        last = ChannelsKt__DeprecatedKt.last(null, this);
        return last;
    }
}
