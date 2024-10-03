package com.google.firebase.sessions;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SessionFirelogPublisher.kt */
@Metadata(m1396k = 3, m1397mv = {1, 7, 1}, m1399xi = 48)
@DebugMetadata(m1414c = "com.google.firebase.sessions.SessionFirelogPublisherImpl", m1415f = "SessionFirelogPublisher.kt", m1416i = {0}, m1417l = {93}, m1418m = "shouldLogSession", m1419n = {"this"}, m1420s = {"L$0"})
/* loaded from: classes2.dex */
public final class SessionFirelogPublisherImpl$shouldLogSession$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SessionFirelogPublisherImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionFirelogPublisherImpl$shouldLogSession$1(SessionFirelogPublisherImpl sessionFirelogPublisherImpl, Continuation<? super SessionFirelogPublisherImpl$shouldLogSession$1> continuation) {
        super(continuation);
        this.this$0 = sessionFirelogPublisherImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object shouldLogSession;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        shouldLogSession = this.this$0.shouldLogSession(this);
        return shouldLogSession;
    }
}
