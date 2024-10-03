package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Count.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = 48)
@DebugMetadata(m1414c = "kotlinx.coroutines.flow.FlowKt__CountKt$count$4", m1415f = "Count.kt", m1416i = {0}, m1417l = {30}, m1418m = "emit", m1419n = {"this"}, m1420s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__CountKt$count$4$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__CountKt$count$4<T> this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__CountKt$count$4$emit$1(FlowKt__CountKt$count$4<? super T> flowKt__CountKt$count$4, Continuation<? super FlowKt__CountKt$count$4$emit$1> continuation) {
        super(continuation);
        this.this$0 = flowKt__CountKt$count$4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
