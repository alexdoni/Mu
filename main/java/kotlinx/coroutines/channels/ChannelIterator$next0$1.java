package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.channels.ChannelIterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Channel.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = 48)
@DebugMetadata(m1414c = "kotlinx.coroutines.channels.ChannelIterator$DefaultImpls", m1415f = "Channel.kt", m1416i = {0}, m1417l = {589}, m1418m = "next", m1419n = {"$this"}, m1420s = {"L$0"})
/* loaded from: classes3.dex */
public final class ChannelIterator$next0$1<E> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelIterator$next0$1(Continuation<? super ChannelIterator$next0$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelIterator.DefaultImpls.next(null, this);
    }
}
