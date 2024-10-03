package kotlinx.coroutines.channels;

import com.appsflyer.AppsFlyerProperties;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TickerChannels.kt */
@Metadata(m1396k = 3, m1397mv = {1, 8, 0}, m1399xi = 48)
@DebugMetadata(m1414c = "kotlinx.coroutines.channels.TickerChannelsKt", m1415f = "TickerChannels.kt", m1416i = {0, 0, 1, 1, 2, 2}, m1417l = {106, 108, 109}, m1418m = "fixedDelayTicker", m1419n = {AppsFlyerProperties.CHANNEL, "delayMillis", AppsFlyerProperties.CHANNEL, "delayMillis", AppsFlyerProperties.CHANNEL, "delayMillis"}, m1420s = {"L$0", "J$0", "L$0", "J$0", "L$0", "J$0"})
/* loaded from: classes3.dex */
public final class TickerChannelsKt$fixedDelayTicker$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TickerChannelsKt$fixedDelayTicker$1(Continuation<? super TickerChannelsKt$fixedDelayTicker$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object fixedDelayTicker;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        fixedDelayTicker = TickerChannelsKt.fixedDelayTicker(0L, 0L, null, this);
        return fixedDelayTicker;
    }
}
