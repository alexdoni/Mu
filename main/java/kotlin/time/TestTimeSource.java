package kotlin.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.jsonwebtoken.JwtParser;
import kotlin.Metadata;

/* compiled from: TimeSources.kt */
@Metadata(m1394d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\nJ\b\u0010\r\u001a\u00020\u0004H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, m1395d2 = {"Lkotlin/time/TestTimeSource;", "Lkotlin/time/AbstractLongTimeSource;", "()V", "reading", "", "overflow", "", TypedValues.TransitionType.S_DURATION, "Lkotlin/time/Duration;", "overflow-LRDsOJo", "(J)V", "plusAssign", "plusAssign-LRDsOJo", "read", "kotlin-stdlib"}, m1396k = 1, m1397mv = {1, 8, 0}, m1399xi = 48)
/* loaded from: classes3.dex */
public final class TestTimeSource extends AbstractLongTimeSource {
    private long reading;

    public TestTimeSource() {
        super(DurationUnit.NANOSECONDS);
    }

    @Override // kotlin.time.AbstractLongTimeSource
    /* renamed from: read, reason: from getter */
    protected long getReading() {
        return this.reading;
    }

    /* renamed from: plusAssign-LRDsOJo, reason: not valid java name */
    public final void m3343plusAssignLRDsOJo(long duration) {
        long j;
        long m3254toLongimpl = Duration.m3254toLongimpl(duration, getUnit());
        if (m3254toLongimpl != Long.MIN_VALUE && m3254toLongimpl != Long.MAX_VALUE) {
            long j2 = this.reading;
            j = j2 + m3254toLongimpl;
            if ((m3254toLongimpl ^ j2) >= 0 && (j2 ^ j) < 0) {
                m3342overflowLRDsOJo(duration);
            }
        } else {
            double m3251toDoubleimpl = this.reading + Duration.m3251toDoubleimpl(duration, getUnit());
            if (m3251toDoubleimpl > 9.223372036854776E18d || m3251toDoubleimpl < -9.223372036854776E18d) {
                m3342overflowLRDsOJo(duration);
            }
            j = (long) m3251toDoubleimpl;
        }
        this.reading = j;
    }

    /* renamed from: overflow-LRDsOJo, reason: not valid java name */
    private final void m3342overflowLRDsOJo(long duration) {
        throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.reading + DurationUnitKt.shortName(getUnit()) + " is advanced by " + ((Object) Duration.m3257toStringimpl(duration)) + JwtParser.SEPARATOR_CHAR);
    }
}
