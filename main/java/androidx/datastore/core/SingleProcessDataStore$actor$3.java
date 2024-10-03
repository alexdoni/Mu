package androidx.datastore.core;

import androidx.core.app.NotificationCompat;
import androidx.datastore.core.SingleProcessDataStore;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: SingleProcessDataStore.kt */
@Metadata(m1394d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, m1395d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, NotificationCompat.CATEGORY_MESSAGE, "Landroidx/datastore/core/SingleProcessDataStore$Message;"}, m1396k = 3, m1397mv = {1, 5, 1}, m1399xi = 48)
@DebugMetadata(m1414c = "androidx.datastore.core.SingleProcessDataStore$actor$3", m1415f = "SingleProcessDataStore.kt", m1416i = {}, m1417l = {239, 242}, m1418m = "invokeSuspend", m1419n = {}, m1420s = {})
/* loaded from: classes.dex */
public final class SingleProcessDataStore$actor$3<T> extends SuspendLambda implements Function2<SingleProcessDataStore.Message<T>, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SingleProcessDataStore<T> this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SingleProcessDataStore$actor$3(SingleProcessDataStore<T> singleProcessDataStore, Continuation<? super SingleProcessDataStore$actor$3> continuation) {
        super(2, continuation);
        this.this$0 = singleProcessDataStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SingleProcessDataStore$actor$3 singleProcessDataStore$actor$3 = new SingleProcessDataStore$actor$3(this.this$0, continuation);
        singleProcessDataStore$actor$3.L$0 = obj;
        return singleProcessDataStore$actor$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SingleProcessDataStore.Message<T> message, Continuation<? super Unit> continuation) {
        return ((SingleProcessDataStore$actor$3) create(message, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object handleUpdate;
        Object handleRead;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SingleProcessDataStore.Message message = (SingleProcessDataStore.Message) this.L$0;
            if (message instanceof SingleProcessDataStore.Message.Read) {
                this.label = 1;
                handleRead = this.this$0.handleRead((SingleProcessDataStore.Message.Read) message, this);
                if (handleRead == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (message instanceof SingleProcessDataStore.Message.Update) {
                this.label = 2;
                handleUpdate = this.this$0.handleUpdate((SingleProcessDataStore.Message.Update) message, this);
                if (handleUpdate == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1 || i == 2) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
