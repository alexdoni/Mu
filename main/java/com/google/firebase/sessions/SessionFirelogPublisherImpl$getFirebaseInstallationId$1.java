package com.google.firebase.sessions;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SessionFirelogPublisher.kt */
@Metadata(m1396k = 3, m1397mv = {1, 7, 1}, m1399xi = 48)
@DebugMetadata(m1414c = "com.google.firebase.sessions.SessionFirelogPublisherImpl", m1415f = "SessionFirelogPublisher.kt", m1416i = {}, m1417l = {111}, m1418m = "getFirebaseInstallationId", m1419n = {}, m1420s = {})
/* loaded from: classes2.dex */
public final class SessionFirelogPublisherImpl$getFirebaseInstallationId$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SessionFirelogPublisherImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionFirelogPublisherImpl$getFirebaseInstallationId$1(SessionFirelogPublisherImpl sessionFirelogPublisherImpl, Continuation<? super SessionFirelogPublisherImpl$getFirebaseInstallationId$1> continuation) {
        super(continuation);
        this.this$0 = sessionFirelogPublisherImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object firebaseInstallationId;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        firebaseInstallationId = this.this$0.getFirebaseInstallationId(this);
        return firebaseInstallationId;
    }
}
