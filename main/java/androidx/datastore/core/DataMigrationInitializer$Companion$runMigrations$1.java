package androidx.datastore.core;

import androidx.datastore.core.DataMigrationInitializer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DataMigrationInitializer.kt */
@Metadata(m1396k = 3, m1397mv = {1, 5, 1}, m1399xi = 48)
@DebugMetadata(m1414c = "androidx.datastore.core.DataMigrationInitializer$Companion", m1415f = "DataMigrationInitializer.kt", m1416i = {0, 1}, m1417l = {42, 57}, m1418m = "runMigrations", m1419n = {"cleanUps", "cleanUpFailure"}, m1420s = {"L$0", "L$0"})
/* loaded from: classes.dex */
public final class DataMigrationInitializer$Companion$runMigrations$1<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DataMigrationInitializer.Companion this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataMigrationInitializer$Companion$runMigrations$1(DataMigrationInitializer.Companion companion, Continuation<? super DataMigrationInitializer$Companion$runMigrations$1> continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object runMigrations;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        runMigrations = this.this$0.runMigrations(null, null, this);
        return runMigrations;
    }
}
