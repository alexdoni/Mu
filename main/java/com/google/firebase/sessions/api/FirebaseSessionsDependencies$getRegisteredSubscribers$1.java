package com.google.firebase.sessions.api;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FirebaseSessionsDependencies.kt */
@Metadata(m1396k = 3, m1397mv = {1, 7, 1}, m1399xi = 48)
@DebugMetadata(m1414c = "com.google.firebase.sessions.api.FirebaseSessionsDependencies", m1415f = "FirebaseSessionsDependencies.kt", m1416i = {0, 0, 0}, m1417l = {124}, m1418m = "getRegisteredSubscribers$com_google_firebase_firebase_sessions", m1419n = {"destination$iv$iv", "subscriberName", "$this$withLock_u24default$iv"}, m1420s = {"L$0", "L$2", "L$3"})
/* loaded from: classes2.dex */
public final class FirebaseSessionsDependencies$getRegisteredSubscribers$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FirebaseSessionsDependencies this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirebaseSessionsDependencies$getRegisteredSubscribers$1(FirebaseSessionsDependencies firebaseSessionsDependencies, Continuation<? super FirebaseSessionsDependencies$getRegisteredSubscribers$1> continuation) {
        super(continuation);
        this.this$0 = firebaseSessionsDependencies;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getRegisteredSubscribers$com_google_firebase_firebase_sessions(this);
    }
}
