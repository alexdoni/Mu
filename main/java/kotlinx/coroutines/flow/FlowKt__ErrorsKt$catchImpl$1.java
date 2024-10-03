package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Errors.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = 48)
@DebugMetadata(m1414c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt", m1415f = "Errors.kt", m1416i = {0}, m1417l = {CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256}, m1418m = "catchImpl", m1419n = {"fromDownstream"}, m1420s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__ErrorsKt$catchImpl$1<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__ErrorsKt$catchImpl$1(Continuation<? super FlowKt__ErrorsKt$catchImpl$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt.catchImpl(null, null, this);
    }
}
