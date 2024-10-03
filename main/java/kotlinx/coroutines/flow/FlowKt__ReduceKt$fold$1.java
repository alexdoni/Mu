package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Reduce.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = CipherSuite.TLS_PSK_WITH_NULL_SHA256)
@DebugMetadata(m1414c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", m1415f = "Reduce.kt", m1416i = {0}, m1417l = {44}, m1418m = "fold", m1419n = {"accumulator"}, m1420s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__ReduceKt$fold$1<T, R> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__ReduceKt$fold$1(Continuation<? super FlowKt__ReduceKt$fold$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt__ReduceKt.fold(null, null, null, this);
    }
}
