package kotlinx.coroutines.sync;

import com.facebook.internal.NativeProtocol;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Mutex.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = CipherSuite.TLS_PSK_WITH_NULL_SHA256)
@DebugMetadata(m1414c = "kotlinx.coroutines.sync.MutexKt", m1415f = "Mutex.kt", m1416i = {0, 0, 0}, m1417l = {125}, m1418m = "withLock", m1419n = {"$this$withLock", "owner", NativeProtocol.WEB_DIALOG_ACTION}, m1420s = {"L$0", "L$1", "L$2"})
/* loaded from: classes3.dex */
public final class MutexKt$withLock$1<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MutexKt$withLock$1(Continuation<? super MutexKt$withLock$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MutexKt.withLock(null, null, null, this);
    }
}
