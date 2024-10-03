package com.google.firebase.sessions;

import android.util.Log;
import com.google.firebase.sessions.api.FirebaseSessionsDependencies;
import com.google.firebase.sessions.api.SessionSubscriber;
import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SessionLifecycleClient.kt */
@Metadata(m1394d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m1395d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m1396k = 3, m1397mv = {1, 7, 1}, m1399xi = 48)
@DebugMetadata(m1414c = "com.google.firebase.sessions.SessionLifecycleClient$ClientUpdateHandler$handleSessionUpdate$1", m1415f = "SessionLifecycleClient.kt", m1416i = {}, m1417l = {74}, m1418m = "invokeSuspend", m1419n = {}, m1420s = {})
/* loaded from: classes2.dex */
final class SessionLifecycleClient$ClientUpdateHandler$handleSessionUpdate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $sessionId;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionLifecycleClient$ClientUpdateHandler$handleSessionUpdate$1(String str, Continuation<? super SessionLifecycleClient$ClientUpdateHandler$handleSessionUpdate$1> continuation) {
        super(2, continuation);
        this.$sessionId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionLifecycleClient$ClientUpdateHandler$handleSessionUpdate$1(this.$sessionId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SessionLifecycleClient$ClientUpdateHandler$handleSessionUpdate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = FirebaseSessionsDependencies.INSTANCE.getRegisteredSubscribers$com_google_firebase_firebase_sessions(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Collection<SessionSubscriber> values = ((Map) obj).values();
        String str = this.$sessionId;
        for (SessionSubscriber sessionSubscriber : values) {
            sessionSubscriber.onSessionChanged(new SessionSubscriber.SessionDetails(str));
            Log.d(SessionLifecycleClient.TAG, "Notified " + sessionSubscriber.getSessionSubscriberName() + " of new session " + str);
        }
        return Unit.INSTANCE;
    }
}
