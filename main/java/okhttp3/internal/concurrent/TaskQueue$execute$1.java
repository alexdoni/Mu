package okhttp3.internal.concurrent;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: TaskQueue.kt */
@Metadata(m1394d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m1395d2 = {"okhttp3/internal/concurrent/TaskQueue$execute$1", "Lokhttp3/internal/concurrent/Task;", "runOnce", "", "okhttp"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = CipherSuite.TLS_PSK_WITH_NULL_SHA256)
/* loaded from: classes3.dex */
public final class TaskQueue$execute$1 extends Task {
    final /* synthetic */ Function0<Unit> $block;
    final /* synthetic */ boolean $cancelable;
    final /* synthetic */ String $name;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaskQueue$execute$1(String str, boolean z, Function0<Unit> function0) {
        super(str, z);
        this.$name = str;
        this.$cancelable = z;
        this.$block = function0;
    }

    @Override // okhttp3.internal.concurrent.Task
    public long runOnce() {
        this.$block.invoke();
        return -1L;
    }
}
