package androidx.datastore.core;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
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
/* compiled from: DataMigrationInitializer.kt */
@Metadata(m1394d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, m1395d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "api", "Landroidx/datastore/core/InitializerApi;"}, m1396k = 3, m1397mv = {1, 5, 1}, m1399xi = 48)
@DebugMetadata(m1414c = "androidx.datastore.core.DataMigrationInitializer$Companion$getInitializer$1", m1415f = "DataMigrationInitializer.kt", m1416i = {}, m1417l = {33}, m1418m = "invokeSuspend", m1419n = {}, m1420s = {})
/* loaded from: classes.dex */
public final class DataMigrationInitializer$Companion$getInitializer$1<T> extends SuspendLambda implements Function2<InitializerApi<T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<DataMigration<T>> $migrations;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DataMigrationInitializer$Companion$getInitializer$1(List<? extends DataMigration<T>> list, Continuation<? super DataMigrationInitializer$Companion$getInitializer$1> continuation) {
        super(2, continuation);
        this.$migrations = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DataMigrationInitializer$Companion$getInitializer$1 dataMigrationInitializer$Companion$getInitializer$1 = new DataMigrationInitializer$Companion$getInitializer$1(this.$migrations, continuation);
        dataMigrationInitializer$Companion$getInitializer$1.L$0 = obj;
        return dataMigrationInitializer$Companion$getInitializer$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(InitializerApi<T> initializerApi, Continuation<? super Unit> continuation) {
        return ((DataMigrationInitializer$Companion$getInitializer$1) create(initializerApi, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object runMigrations;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            InitializerApi initializerApi = (InitializerApi) this.L$0;
            this.label = 1;
            runMigrations = DataMigrationInitializer.INSTANCE.runMigrations(this.$migrations, initializerApi, this);
            if (runMigrations == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
