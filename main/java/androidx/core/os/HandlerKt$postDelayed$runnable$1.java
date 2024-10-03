package androidx.core.os;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: Handler.kt */
@Metadata(m1394d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m1395d2 = {"<anonymous>", "", "run"}, m1396k = 3, m1397mv = {1, 7, 1}, m1399xi = CipherSuite.TLS_PSK_WITH_NULL_SHA256)
/* loaded from: classes.dex */
public final class HandlerKt$postDelayed$runnable$1 implements Runnable {
    final /* synthetic */ Function0<Unit> $action;

    public HandlerKt$postDelayed$runnable$1(Function0<Unit> function0) {
        this.$action = function0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.$action.invoke();
    }
}
