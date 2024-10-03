package kotlinx.coroutines.selects;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Select.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = 48)
@DebugMetadata(m1414c = "kotlinx.coroutines.selects.SelectImplementation", m1415f = "Select.kt", m1416i = {}, m1417l = {TypedValues.TransitionType.TYPE_STAGGERED}, m1418m = "processResultAndInvokeBlockRecoveringException", m1419n = {}, m1420s = {})
/* renamed from: kotlinx.coroutines.selects.SelectImplementation$processResultAndInvokeBlockRecoveringException$1 */
/* loaded from: classes3.dex */
public final class C3005xf491fb2a extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SelectImplementation<R> this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3005xf491fb2a(SelectImplementation<R> selectImplementation, Continuation<? super C3005xf491fb2a> continuation) {
        super(continuation);
        this.this$0 = selectImplementation;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object processResultAndInvokeBlockRecoveringException;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        processResultAndInvokeBlockRecoveringException = this.this$0.processResultAndInvokeBlockRecoveringException(null, null, this);
        return processResultAndInvokeBlockRecoveringException;
    }
}
