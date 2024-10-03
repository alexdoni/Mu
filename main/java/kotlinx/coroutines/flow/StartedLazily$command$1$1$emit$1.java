package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.StartedLazily$command$1;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SharingStarted.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = 48)
@DebugMetadata(m1414c = "kotlinx.coroutines.flow.StartedLazily$command$1$1", m1415f = "SharingStarted.kt", m1416i = {}, m1417l = {CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256}, m1418m = "emit", m1419n = {}, m1420s = {})
/* loaded from: classes3.dex */
public final class StartedLazily$command$1$1$emit$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ StartedLazily$command$1.C29981<T> this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public StartedLazily$command$1$1$emit$1(StartedLazily$command$1.C29981<? super T> c29981, Continuation<? super StartedLazily$command$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = c29981;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(0, this);
    }
}
