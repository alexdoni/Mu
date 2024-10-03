package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Emitters.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = 48)
@DebugMetadata(m1414c = "kotlinx.coroutines.flow.FlowKt__EmittersKt", m1415f = "Emitters.kt", m1416i = {0}, m1417l = {216}, m1418m = "invokeSafely$FlowKt__EmittersKt", m1419n = {"cause"}, m1420s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__EmittersKt$invokeSafely$1<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__EmittersKt$invokeSafely$1(Continuation<? super FlowKt__EmittersKt$invokeSafely$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object invokeSafely$FlowKt__EmittersKt;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        invokeSafely$FlowKt__EmittersKt = FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(null, null, null, this);
        return invokeSafely$FlowKt__EmittersKt;
    }
}
