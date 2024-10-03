package kotlinx.coroutines.channels;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Deprecated.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = 48)
@DebugMetadata(m1414c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m1415f = "Deprecated.kt", m1416i = {0, 0, 0}, m1417l = {38}, m1418m = "elementAt", m1419n = {"$this$consume$iv", FirebaseAnalytics.Param.INDEX, "count"}, m1420s = {"L$0", "I$0", "I$1"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$elementAt$1<E> extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$elementAt$1(Continuation<? super ChannelsKt__DeprecatedKt$elementAt$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object elementAt;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        elementAt = ChannelsKt__DeprecatedKt.elementAt(null, 0, this);
        return elementAt;
    }
}
