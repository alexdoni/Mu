package com.google.firebase.sessions;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseKt;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.sessions.settings.SessionsSettings;
import io.jsonwebtoken.JwtParser;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: FirebaseSessions.kt */
@Metadata(m1394d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m1395d2 = {"Lcom/google/firebase/sessions/FirebaseSessions;", "", "firebaseApp", "Lcom/google/firebase/FirebaseApp;", "settings", "Lcom/google/firebase/sessions/settings/SessionsSettings;", "backgroundDispatcher", "Lkotlin/coroutines/CoroutineContext;", "(Lcom/google/firebase/FirebaseApp;Lcom/google/firebase/sessions/settings/SessionsSettings;Lkotlin/coroutines/CoroutineContext;)V", "Companion", "com.google.firebase-firebase-sessions"}, m1396k = 1, m1397mv = {1, 7, 1}, m1399xi = 48)
/* loaded from: classes2.dex */
public final class FirebaseSessions {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "FirebaseSessions";
    private final FirebaseApp firebaseApp;
    private final SessionsSettings settings;

    public FirebaseSessions(FirebaseApp firebaseApp, SessionsSettings settings, CoroutineContext backgroundDispatcher) {
        Intrinsics.checkNotNullParameter(firebaseApp, "firebaseApp");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(backgroundDispatcher, "backgroundDispatcher");
        this.firebaseApp = firebaseApp;
        this.settings = settings;
        Log.d(TAG, "Initializing Firebase Sessions SDK.");
        Context applicationContext = firebaseApp.getApplicationContext().getApplicationContext();
        if (applicationContext instanceof Application) {
            ((Application) applicationContext).registerActivityLifecycleCallbacks(SessionsActivityLifecycleCallbacks.INSTANCE);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(backgroundDispatcher), null, null, new C16711(backgroundDispatcher, null), 3, null);
        } else {
            Log.e(TAG, "Failed to register lifecycle callbacks, unexpected context " + applicationContext.getClass() + JwtParser.SEPARATOR_CHAR);
        }
    }

    /* compiled from: FirebaseSessions.kt */
    @Metadata(m1394d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m1395d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m1396k = 3, m1397mv = {1, 7, 1}, m1399xi = 48)
    @DebugMetadata(m1414c = "com.google.firebase.sessions.FirebaseSessions$1", m1415f = "FirebaseSessions.kt", m1416i = {}, m1417l = {44, 48}, m1418m = "invokeSuspend", m1419n = {}, m1420s = {})
    /* renamed from: com.google.firebase.sessions.FirebaseSessions$1 */
    /* loaded from: classes2.dex */
    static final class C16711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CoroutineContext $backgroundDispatcher;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C16711(CoroutineContext coroutineContext, Continuation<? super C16711> continuation) {
            super(2, continuation);
            this.$backgroundDispatcher = coroutineContext;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C16711(this.$backgroundDispatcher, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C16711) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x008a  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0084  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                java.lang.String r2 = "FirebaseSessions"
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L20
                if (r1 == r4) goto L1c
                if (r1 != r3) goto L14
                kotlin.ResultKt.throwOnFailure(r6)
                goto L78
            L14:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L1c:
                kotlin.ResultKt.throwOnFailure(r6)
                goto L31
            L20:
                kotlin.ResultKt.throwOnFailure(r6)
                com.google.firebase.sessions.api.FirebaseSessionsDependencies r6 = com.google.firebase.sessions.api.FirebaseSessionsDependencies.INSTANCE
                r1 = r5
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r5.label = r4
                java.lang.Object r6 = r6.getRegisteredSubscribers$com_google_firebase_firebase_sessions(r1)
                if (r6 != r0) goto L31
                return r0
            L31:
                java.util.Map r6 = (java.util.Map) r6
                java.util.Collection r6 = r6.values()
                java.lang.Iterable r6 = (java.lang.Iterable) r6
                boolean r1 = r6 instanceof java.util.Collection
                if (r1 == 0) goto L47
                r1 = r6
                java.util.Collection r1 = (java.util.Collection) r1
                boolean r1 = r1.isEmpty()
                if (r1 == 0) goto L47
                goto L5e
            L47:
                java.util.Iterator r6 = r6.iterator()
            L4b:
                boolean r1 = r6.hasNext()
                if (r1 == 0) goto L5e
                java.lang.Object r1 = r6.next()
                com.google.firebase.sessions.api.SessionSubscriber r1 = (com.google.firebase.sessions.api.SessionSubscriber) r1
                boolean r1 = r1.isDataCollectionEnabled()
                if (r1 == 0) goto L4b
                r4 = 0
            L5e:
                if (r4 == 0) goto L66
                java.lang.String r6 = "No Sessions subscribers. Not listening to lifecycle events."
                android.util.Log.d(r2, r6)
                goto La7
            L66:
                com.google.firebase.sessions.FirebaseSessions r6 = com.google.firebase.sessions.FirebaseSessions.this
                com.google.firebase.sessions.settings.SessionsSettings r6 = com.google.firebase.sessions.FirebaseSessions.access$getSettings$p(r6)
                r1 = r5
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r5.label = r3
                java.lang.Object r6 = r6.updateSettings(r1)
                if (r6 != r0) goto L78
                return r0
            L78:
                com.google.firebase.sessions.FirebaseSessions r6 = com.google.firebase.sessions.FirebaseSessions.this
                com.google.firebase.sessions.settings.SessionsSettings r6 = com.google.firebase.sessions.FirebaseSessions.access$getSettings$p(r6)
                boolean r6 = r6.getSessionsEnabled()
                if (r6 != 0) goto L8a
                java.lang.String r6 = "Sessions SDK disabled. Not listening to lifecycle events."
                android.util.Log.d(r2, r6)
                goto La7
            L8a:
                com.google.firebase.sessions.SessionLifecycleClient r6 = new com.google.firebase.sessions.SessionLifecycleClient
                kotlin.coroutines.CoroutineContext r0 = r5.$backgroundDispatcher
                r6.<init>(r0)
                r6.bindToService()
                com.google.firebase.sessions.SessionsActivityLifecycleCallbacks r0 = com.google.firebase.sessions.SessionsActivityLifecycleCallbacks.INSTANCE
                r0.setLifecycleClient(r6)
                com.google.firebase.sessions.FirebaseSessions r6 = com.google.firebase.sessions.FirebaseSessions.this
                com.google.firebase.FirebaseApp r6 = com.google.firebase.sessions.FirebaseSessions.access$getFirebaseApp$p(r6)
                com.google.firebase.sessions.FirebaseSessions$1$$ExternalSyntheticLambda0 r0 = new com.google.firebase.sessions.FirebaseSessions$1$$ExternalSyntheticLambda0
                r0.<init>()
                r6.addLifecycleEventListener(r0)
            La7:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.FirebaseSessions.C16711.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invokeSuspend$lambda-1, reason: not valid java name */
        public static final void m1844invokeSuspend$lambda1(String str, FirebaseOptions firebaseOptions) {
            Log.w(FirebaseSessions.TAG, "FirebaseApp instance deleted. Sessions library will stop collecting data.");
            SessionsActivityLifecycleCallbacks.INSTANCE.setLifecycleClient(null);
        }
    }

    /* compiled from: FirebaseSessions.kt */
    @Metadata(m1394d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, m1395d2 = {"Lcom/google/firebase/sessions/FirebaseSessions$Companion;", "", "()V", "TAG", "", "instance", "Lcom/google/firebase/sessions/FirebaseSessions;", "getInstance", "()Lcom/google/firebase/sessions/FirebaseSessions;", "com.google.firebase-firebase-sessions"}, m1396k = 1, m1397mv = {1, 7, 1}, m1399xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FirebaseSessions getInstance() {
            Object obj = FirebaseKt.getApp(Firebase.INSTANCE).get(FirebaseSessions.class);
            Intrinsics.checkNotNullExpressionValue(obj, "Firebase.app[FirebaseSessions::class.java]");
            return (FirebaseSessions) obj;
        }
    }
}
