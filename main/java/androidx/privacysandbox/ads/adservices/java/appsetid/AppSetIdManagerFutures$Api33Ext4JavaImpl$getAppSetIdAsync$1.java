package androidx.privacysandbox.ads.adservices.java.appsetid;

import androidx.privacysandbox.ads.adservices.appsetid.AppSetId;
import androidx.privacysandbox.ads.adservices.appsetid.AppSetIdManager;
import androidx.privacysandbox.ads.adservices.java.appsetid.AppSetIdManagerFutures;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AppSetIdManagerFutures.kt */
@Metadata(m1394d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m1395d2 = {"<anonymous>", "Landroidx/privacysandbox/ads/adservices/appsetid/AppSetId;", "Lkotlinx/coroutines/CoroutineScope;"}, m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = 48)
@DebugMetadata(m1414c = "androidx.privacysandbox.ads.adservices.java.appsetid.AppSetIdManagerFutures$Api33Ext4JavaImpl$getAppSetIdAsync$1", m1415f = "AppSetIdManagerFutures.kt", m1416i = {}, m1417l = {50}, m1418m = "invokeSuspend", m1419n = {}, m1420s = {})
/* loaded from: classes.dex */
final class AppSetIdManagerFutures$Api33Ext4JavaImpl$getAppSetIdAsync$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AppSetId>, Object> {
    int label;
    final /* synthetic */ AppSetIdManagerFutures.Api33Ext4JavaImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppSetIdManagerFutures$Api33Ext4JavaImpl$getAppSetIdAsync$1(AppSetIdManagerFutures.Api33Ext4JavaImpl api33Ext4JavaImpl, Continuation<? super AppSetIdManagerFutures$Api33Ext4JavaImpl$getAppSetIdAsync$1> continuation) {
        super(2, continuation);
        this.this$0 = api33Ext4JavaImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppSetIdManagerFutures$Api33Ext4JavaImpl$getAppSetIdAsync$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AppSetId> continuation) {
        return ((AppSetIdManagerFutures$Api33Ext4JavaImpl$getAppSetIdAsync$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AppSetIdManager appSetIdManager;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            appSetIdManager = this.this$0.mAppSetIdManager;
            this.label = 1;
            obj = appSetIdManager.getAppSetId(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
