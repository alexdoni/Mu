package kotlinx.coroutines.sync;

import com.facebook.internal.NativeProtocol;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Semaphore.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = CipherSuite.TLS_PSK_WITH_NULL_SHA256)
@DebugMetadata(m1414c = "kotlinx.coroutines.sync.SemaphoreKt", m1415f = "Semaphore.kt", m1416i = {0, 0}, m1417l = {86}, m1418m = "withPermit", m1419n = {"$this$withPermit", NativeProtocol.WEB_DIALOG_ACTION}, m1420s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class SemaphoreKt$withPermit$1<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SemaphoreKt$withPermit$1(Continuation<? super SemaphoreKt$withPermit$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return SemaphoreKt.withPermit(null, null, this);
    }
}
