package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SingleProcessDataStore.kt */
@Metadata(m1396k = 3, m1397mv = {1, 5, 1}, m1399xi = 48)
@DebugMetadata(m1414c = "androidx.datastore.core.SingleProcessDataStore", m1415f = "SingleProcessDataStore.kt", m1416i = {1, 1}, m1417l = {276, 281, 284}, m1418m = "handleUpdate", m1419n = {"update", "$this$handleUpdate_u24lambda_u2d0"}, m1420s = {"L$0", "L$1"})
/* loaded from: classes.dex */
public final class SingleProcessDataStore$handleUpdate$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SingleProcessDataStore<T> this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SingleProcessDataStore$handleUpdate$1(SingleProcessDataStore<T> singleProcessDataStore, Continuation<? super SingleProcessDataStore$handleUpdate$1> continuation) {
        super(continuation);
        this.this$0 = singleProcessDataStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object handleUpdate;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        handleUpdate = this.this$0.handleUpdate(null, this);
        return handleUpdate;
    }
}
