package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GzipSink.kt */
@Metadata(m1394d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b¨\u0006\u0003"}, m1395d2 = {"gzip", "Lokio/GzipSink;", "Lokio/Sink;", "okio"}, m1396k = 2, m1397mv = {1, 5, 1}, m1399xi = 48)
/* renamed from: okio.-GzipSinkExtensions, reason: invalid class name */
/* loaded from: classes3.dex */
public final class GzipSinkExtensions {
    public static final GzipSink gzip(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        return new GzipSink(sink);
    }
}
