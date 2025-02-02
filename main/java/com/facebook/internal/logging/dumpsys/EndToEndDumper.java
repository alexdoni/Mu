package com.facebook.internal.logging.dumpsys;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.io.PrintWriter;
import kotlin.Metadata;

/* compiled from: EndToEndDumper.kt */
@Metadata(m1394d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\bæ\u0080\u0001\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bJ-\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\tH&¢\u0006\u0002\u0010\n¨\u0006\f"}, m1395d2 = {"Lcom/facebook/internal/logging/dumpsys/EndToEndDumper;", "", "maybeDump", "", RequestParameters.PREFIX, "", "writer", "Ljava/io/PrintWriter;", "args", "", "(Ljava/lang/String;Ljava/io/PrintWriter;[Ljava/lang/String;)Z", "Companion", "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public interface EndToEndDumper {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    boolean maybeDump(String prefix, PrintWriter writer, String[] args);

    /* compiled from: EndToEndDumper.kt */
    @Metadata(m1394d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m1395d2 = {"Lcom/facebook/internal/logging/dumpsys/EndToEndDumper$Companion;", "", "()V", "instance", "Lcom/facebook/internal/logging/dumpsys/EndToEndDumper;", "getInstance", "()Lcom/facebook/internal/logging/dumpsys/EndToEndDumper;", "setInstance", "(Lcom/facebook/internal/logging/dumpsys/EndToEndDumper;)V", "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static EndToEndDumper instance;

        private Companion() {
        }

        public final EndToEndDumper getInstance() {
            return instance;
        }

        public final void setInstance(EndToEndDumper endToEndDumper) {
            instance = endToEndDumper;
        }
    }
}
