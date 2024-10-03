package androidx.activity.contextaware;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: ContextAware.kt */
@Metadata(m1394d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m1395d2 = {"androidx/activity/contextaware/ContextAwareKt$withContextAvailable$2$listener$1", "Landroidx/activity/contextaware/OnContextAvailableListener;", "onContextAvailable", "", "context", "Landroid/content/Context;", "activity-ktx_release"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = CipherSuite.TLS_PSK_WITH_NULL_SHA256)
/* loaded from: classes.dex */
public final class ContextAwareKt$withContextAvailable$2$listener$1 implements OnContextAvailableListener {
    final /* synthetic */ CancellableContinuation<R> $co;
    final /* synthetic */ Function1<Context, R> $onContextAvailable;

    /* JADX WARN: Multi-variable type inference failed */
    public ContextAwareKt$withContextAvailable$2$listener$1(CancellableContinuation<? super R> cancellableContinuation, Function1<? super Context, ? extends R> function1) {
        this.$co = cancellableContinuation;
        this.$onContextAvailable = function1;
    }

    @Override // androidx.activity.contextaware.OnContextAvailableListener
    public void onContextAvailable(Context context) {
        Object m1882constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Continuation continuation = this.$co;
        Function1<Context, R> function1 = this.$onContextAvailable;
        try {
            Result.Companion companion = Result.INSTANCE;
            ContextAwareKt$withContextAvailable$2$listener$1 contextAwareKt$withContextAvailable$2$listener$1 = this;
            m1882constructorimpl = Result.m1882constructorimpl(function1.invoke(context));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
        }
        continuation.resumeWith(m1882constructorimpl);
    }
}
