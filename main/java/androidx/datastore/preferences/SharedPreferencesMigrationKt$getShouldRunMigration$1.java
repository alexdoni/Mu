package androidx.datastore.preferences;

import androidx.datastore.preferences.core.Preferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: SharedPreferencesMigration.kt */
@Metadata(m1394d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, m1395d2 = {"<anonymous>", "", "prefs", "Landroidx/datastore/preferences/core/Preferences;"}, m1396k = 3, m1397mv = {1, 5, 1}, m1399xi = 48)
@DebugMetadata(m1414c = "androidx.datastore.preferences.SharedPreferencesMigrationKt$getShouldRunMigration$1", m1415f = "SharedPreferencesMigration.kt", m1416i = {}, m1417l = {}, m1418m = "invokeSuspend", m1419n = {}, m1420s = {})
/* loaded from: classes.dex */
final class SharedPreferencesMigrationKt$getShouldRunMigration$1 extends SuspendLambda implements Function2<Preferences, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Set<String> $keysToMigrate;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SharedPreferencesMigrationKt$getShouldRunMigration$1(Set<String> set, Continuation<? super SharedPreferencesMigrationKt$getShouldRunMigration$1> continuation) {
        super(2, continuation);
        this.$keysToMigrate = set;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SharedPreferencesMigrationKt$getShouldRunMigration$1 sharedPreferencesMigrationKt$getShouldRunMigration$1 = new SharedPreferencesMigrationKt$getShouldRunMigration$1(this.$keysToMigrate, continuation);
        sharedPreferencesMigrationKt$getShouldRunMigration$1.L$0 = obj;
        return sharedPreferencesMigrationKt$getShouldRunMigration$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Preferences preferences, Continuation<? super Boolean> continuation) {
        return ((SharedPreferencesMigrationKt$getShouldRunMigration$1) create(preferences, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Set<Preferences.Key<?>> keySet = ((Preferences) this.L$0).asMap().keySet();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(keySet, 10));
            Iterator<T> it = keySet.iterator();
            while (it.hasNext()) {
                arrayList.add(((Preferences.Key) it.next()).getName());
            }
            ArrayList arrayList2 = arrayList;
            boolean z = true;
            if (this.$keysToMigrate != SharedPreferencesMigrationKt.getMIGRATE_ALL_KEYS()) {
                Set<String> set = this.$keysToMigrate;
                if (!(set instanceof Collection) || !set.isEmpty()) {
                    Iterator<T> it2 = set.iterator();
                    while (it2.hasNext()) {
                        if (Boxing.boxBoolean(!arrayList2.contains((String) it2.next())).booleanValue()) {
                            break;
                        }
                    }
                }
                z = false;
            }
            return Boxing.boxBoolean(z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
