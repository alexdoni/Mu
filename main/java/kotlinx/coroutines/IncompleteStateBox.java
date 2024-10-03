package kotlinx.coroutines;

import com.facebook.internal.ServerProtocol;
import kotlin.Metadata;

/* compiled from: JobSupport.kt */
@Metadata(m1394d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m1395d2 = {"Lkotlinx/coroutines/IncompleteStateBox;", "", ServerProtocol.DIALOG_PARAM_STATE, "Lkotlinx/coroutines/Incomplete;", "(Lkotlinx/coroutines/Incomplete;)V", "kotlinx-coroutines-core"}, m1396k = 1, m1397mv = {1, 8, 0}, m1399xi = 48)
/* loaded from: classes3.dex */
final class IncompleteStateBox {
    public final Incomplete state;

    public IncompleteStateBox(Incomplete incomplete) {
        this.state = incomplete;
    }
}
