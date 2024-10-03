package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SingleProcessDataStore.kt */
@Metadata(m1396k = 3, m1397mv = {1, 5, 1}, m1399xi = 48)
@DebugMetadata(m1414c = "androidx.datastore.core.SingleProcessDataStore", m1415f = "SingleProcessDataStore.kt", m1416i = {0}, m1417l = {381}, m1418m = "readData", m1419n = {"this"}, m1420s = {"L$0"})
/* loaded from: classes.dex */
public final class SingleProcessDataStore$readData$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SingleProcessDataStore<T> this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SingleProcessDataStore$readData$1(SingleProcessDataStore<T> singleProcessDataStore, Continuation<? super SingleProcessDataStore$readData$1> continuation) {
        super(continuation);
        this.this$0 = singleProcessDataStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object readData;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        readData = this.this$0.readData(this);
        return readData;
    }
}
