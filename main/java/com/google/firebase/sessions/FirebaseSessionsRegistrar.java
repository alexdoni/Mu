package com.google.firebase.sessions;

import android.content.Context;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.sessions.settings.SessionsSettings;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: FirebaseSessionsRegistrar.kt */
@Metadata(m1394d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0001\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a0\u0012,\u0012*\u0012\u000e\b\u0001\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006 \u0007*\u0014\u0012\u000e\b\u0001\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u00050\u00050\u0004H\u0016¨\u0006\t"}, m1395d2 = {"Lcom/google/firebase/sessions/FirebaseSessionsRegistrar;", "Lcom/google/firebase/components/ComponentRegistrar;", "()V", "getComponents", "", "Lcom/google/firebase/components/Component;", "", "kotlin.jvm.PlatformType", "Companion", "com.google.firebase-firebase-sessions"}, m1396k = 1, m1397mv = {1, 7, 1}, m1399xi = 48)
/* loaded from: classes2.dex */
public final class FirebaseSessionsRegistrar implements ComponentRegistrar {

    @Deprecated
    private static final String LIBRARY_NAME = "fire-sessions";
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final Qualified<FirebaseApp> firebaseApp = Qualified.unqualified(FirebaseApp.class);

    @Deprecated
    private static final Qualified<FirebaseInstallationsApi> firebaseInstallationsApi = Qualified.unqualified(FirebaseInstallationsApi.class);

    @Deprecated
    private static final Qualified<CoroutineDispatcher> backgroundDispatcher = Qualified.qualified(Background.class, CoroutineDispatcher.class);

    @Deprecated
    private static final Qualified<CoroutineDispatcher> blockingDispatcher = Qualified.qualified(Blocking.class, CoroutineDispatcher.class);

    @Deprecated
    private static final Qualified<TransportFactory> transportFactory = Qualified.unqualified(TransportFactory.class);

    @Deprecated
    private static final Qualified<SessionsSettings> sessionsSettings = Qualified.unqualified(SessionsSettings.class);

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<? extends Object>> getComponents() {
        Component.Builder name = Component.builder(FirebaseSessions.class).name(LIBRARY_NAME);
        Qualified<FirebaseApp> qualified = firebaseApp;
        Component.Builder add = name.add(Dependency.required(qualified));
        Qualified<SessionsSettings> qualified2 = sessionsSettings;
        Component.Builder add2 = add.add(Dependency.required(qualified2));
        Qualified<CoroutineDispatcher> qualified3 = backgroundDispatcher;
        Component.Builder add3 = Component.builder(SessionFirelogPublisher.class).name("session-publisher").add(Dependency.required(qualified));
        Qualified<FirebaseInstallationsApi> qualified4 = firebaseInstallationsApi;
        return CollectionsKt.listOf((Object[]) new Component[]{add2.add(Dependency.required(qualified3)).factory(new ComponentFactory() { // from class: com.google.firebase.sessions.FirebaseSessionsRegistrar$$ExternalSyntheticLambda0
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                FirebaseSessions m1849getComponents$lambda0;
                m1849getComponents$lambda0 = FirebaseSessionsRegistrar.m1849getComponents$lambda0(componentContainer);
                return m1849getComponents$lambda0;
            }
        }).eagerInDefaultApp().build(), Component.builder(SessionGenerator.class).name("session-generator").factory(new ComponentFactory() { // from class: com.google.firebase.sessions.FirebaseSessionsRegistrar$$ExternalSyntheticLambda1
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                SessionGenerator m1850getComponents$lambda1;
                m1850getComponents$lambda1 = FirebaseSessionsRegistrar.m1850getComponents$lambda1(componentContainer);
                return m1850getComponents$lambda1;
            }
        }).build(), add3.add(Dependency.required(qualified4)).add(Dependency.required(qualified2)).add(Dependency.requiredProvider(transportFactory)).add(Dependency.required(qualified3)).factory(new ComponentFactory() { // from class: com.google.firebase.sessions.FirebaseSessionsRegistrar$$ExternalSyntheticLambda2
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                SessionFirelogPublisher m1851getComponents$lambda2;
                m1851getComponents$lambda2 = FirebaseSessionsRegistrar.m1851getComponents$lambda2(componentContainer);
                return m1851getComponents$lambda2;
            }
        }).build(), Component.builder(SessionsSettings.class).name("sessions-settings").add(Dependency.required(qualified)).add(Dependency.required(blockingDispatcher)).add(Dependency.required(qualified3)).add(Dependency.required(qualified4)).factory(new ComponentFactory() { // from class: com.google.firebase.sessions.FirebaseSessionsRegistrar$$ExternalSyntheticLambda3
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                SessionsSettings m1852getComponents$lambda3;
                m1852getComponents$lambda3 = FirebaseSessionsRegistrar.m1852getComponents$lambda3(componentContainer);
                return m1852getComponents$lambda3;
            }
        }).build(), Component.builder(SessionDatastore.class).name("sessions-datastore").add(Dependency.required(qualified)).add(Dependency.required(qualified3)).factory(new ComponentFactory() { // from class: com.google.firebase.sessions.FirebaseSessionsRegistrar$$ExternalSyntheticLambda4
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                SessionDatastore m1853getComponents$lambda4;
                m1853getComponents$lambda4 = FirebaseSessionsRegistrar.m1853getComponents$lambda4(componentContainer);
                return m1853getComponents$lambda4;
            }
        }).build(), Component.builder(SessionLifecycleServiceBinder.class).name("sessions-service-binder").add(Dependency.required(qualified)).factory(new ComponentFactory() { // from class: com.google.firebase.sessions.FirebaseSessionsRegistrar$$ExternalSyntheticLambda5
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                SessionLifecycleServiceBinder m1854getComponents$lambda5;
                m1854getComponents$lambda5 = FirebaseSessionsRegistrar.m1854getComponents$lambda5(componentContainer);
                return m1854getComponents$lambda5;
            }
        }).build(), LibraryVersionComponent.create(LIBRARY_NAME, BuildConfig.VERSION_NAME)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getComponents$lambda-0, reason: not valid java name */
    public static final FirebaseSessions m1849getComponents$lambda0(ComponentContainer componentContainer) {
        Object obj = componentContainer.get(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(obj, "container[firebaseApp]");
        Object obj2 = componentContainer.get(sessionsSettings);
        Intrinsics.checkNotNullExpressionValue(obj2, "container[sessionsSettings]");
        Object obj3 = componentContainer.get(backgroundDispatcher);
        Intrinsics.checkNotNullExpressionValue(obj3, "container[backgroundDispatcher]");
        return new FirebaseSessions((FirebaseApp) obj, (SessionsSettings) obj2, (CoroutineContext) obj3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getComponents$lambda-1, reason: not valid java name */
    public static final SessionGenerator m1850getComponents$lambda1(ComponentContainer componentContainer) {
        return new SessionGenerator(WallClock.INSTANCE, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getComponents$lambda-2, reason: not valid java name */
    public static final SessionFirelogPublisher m1851getComponents$lambda2(ComponentContainer componentContainer) {
        Object obj = componentContainer.get(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(obj, "container[firebaseApp]");
        FirebaseApp firebaseApp2 = (FirebaseApp) obj;
        Object obj2 = componentContainer.get(firebaseInstallationsApi);
        Intrinsics.checkNotNullExpressionValue(obj2, "container[firebaseInstallationsApi]");
        FirebaseInstallationsApi firebaseInstallationsApi2 = (FirebaseInstallationsApi) obj2;
        Object obj3 = componentContainer.get(sessionsSettings);
        Intrinsics.checkNotNullExpressionValue(obj3, "container[sessionsSettings]");
        SessionsSettings sessionsSettings2 = (SessionsSettings) obj3;
        Provider provider = componentContainer.getProvider(transportFactory);
        Intrinsics.checkNotNullExpressionValue(provider, "container.getProvider(transportFactory)");
        EventGDTLogger eventGDTLogger = new EventGDTLogger(provider);
        Object obj4 = componentContainer.get(backgroundDispatcher);
        Intrinsics.checkNotNullExpressionValue(obj4, "container[backgroundDispatcher]");
        return new SessionFirelogPublisherImpl(firebaseApp2, firebaseInstallationsApi2, sessionsSettings2, eventGDTLogger, (CoroutineContext) obj4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getComponents$lambda-3, reason: not valid java name */
    public static final SessionsSettings m1852getComponents$lambda3(ComponentContainer componentContainer) {
        Object obj = componentContainer.get(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(obj, "container[firebaseApp]");
        Object obj2 = componentContainer.get(blockingDispatcher);
        Intrinsics.checkNotNullExpressionValue(obj2, "container[blockingDispatcher]");
        Object obj3 = componentContainer.get(backgroundDispatcher);
        Intrinsics.checkNotNullExpressionValue(obj3, "container[backgroundDispatcher]");
        Object obj4 = componentContainer.get(firebaseInstallationsApi);
        Intrinsics.checkNotNullExpressionValue(obj4, "container[firebaseInstallationsApi]");
        return new SessionsSettings((FirebaseApp) obj, (CoroutineContext) obj2, (CoroutineContext) obj3, (FirebaseInstallationsApi) obj4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getComponents$lambda-4, reason: not valid java name */
    public static final SessionDatastore m1853getComponents$lambda4(ComponentContainer componentContainer) {
        Context applicationContext = ((FirebaseApp) componentContainer.get(firebaseApp)).getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "container[firebaseApp].applicationContext");
        Object obj = componentContainer.get(backgroundDispatcher);
        Intrinsics.checkNotNullExpressionValue(obj, "container[backgroundDispatcher]");
        return new SessionDatastoreImpl(applicationContext, (CoroutineContext) obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getComponents$lambda-5, reason: not valid java name */
    public static final SessionLifecycleServiceBinder m1854getComponents$lambda5(ComponentContainer componentContainer) {
        Object obj = componentContainer.get(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(obj, "container[firebaseApp]");
        return new SessionLifecycleServiceBinderImpl((FirebaseApp) obj);
    }

    /* compiled from: FirebaseSessionsRegistrar.kt */
    @Metadata(m1394d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R2\u0010\u0005\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\t\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\n\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u00010\u000b0\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\f\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u00010\r0\r \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u00010\r0\r\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u000e\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u00010\u000f0\u000f \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u00010\u000f0\u000f\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u0010\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00110\u0011 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00110\u0011\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1395d2 = {"Lcom/google/firebase/sessions/FirebaseSessionsRegistrar$Companion;", "", "()V", "LIBRARY_NAME", "", "backgroundDispatcher", "Lcom/google/firebase/components/Qualified;", "Lkotlinx/coroutines/CoroutineDispatcher;", "kotlin.jvm.PlatformType", "blockingDispatcher", "firebaseApp", "Lcom/google/firebase/FirebaseApp;", "firebaseInstallationsApi", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "sessionsSettings", "Lcom/google/firebase/sessions/settings/SessionsSettings;", "transportFactory", "Lcom/google/android/datatransport/TransportFactory;", "com.google.firebase-firebase-sessions"}, m1396k = 1, m1397mv = {1, 7, 1}, m1399xi = 48)
    /* loaded from: classes2.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
