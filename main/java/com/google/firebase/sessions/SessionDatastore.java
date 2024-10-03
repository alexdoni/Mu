package com.google.firebase.sessions;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SessionDatastore.kt */
@Metadata(m1394d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b`\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&¨\u0006\b"}, m1395d2 = {"Lcom/google/firebase/sessions/SessionDatastore;", "", "getCurrentSessionId", "", "updateSessionId", "", "sessionId", "Companion", "com.google.firebase-firebase-sessions"}, m1396k = 1, m1397mv = {1, 7, 1}, m1399xi = 48)
/* loaded from: classes2.dex */
public interface SessionDatastore {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    String getCurrentSessionId();

    void updateSessionId(String sessionId);

    /* compiled from: SessionDatastore.kt */
    @Metadata(m1394d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m1395d2 = {"Lcom/google/firebase/sessions/SessionDatastore$Companion;", "", "()V", "instance", "Lcom/google/firebase/sessions/SessionDatastore;", "getInstance", "()Lcom/google/firebase/sessions/SessionDatastore;", "com.google.firebase-firebase-sessions"}, m1396k = 1, m1397mv = {1, 7, 1}, m1399xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final SessionDatastore getInstance() {
            Object obj = FirebaseKt.getApp(Firebase.INSTANCE).get(SessionDatastore.class);
            Intrinsics.checkNotNullExpressionValue(obj, "Firebase.app[SessionDatastore::class.java]");
            return (SessionDatastore) obj;
        }
    }
}
