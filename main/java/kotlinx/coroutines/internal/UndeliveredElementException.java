package kotlinx.coroutines.internal;

import com.facebook.share.internal.ShareConstants;
import kotlin.Metadata;

/* compiled from: OnUndeliveredElement.kt */
@Metadata(m1394d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, m1395d2 = {"Lkotlinx/coroutines/internal/UndeliveredElementException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", ShareConstants.WEB_DIALOG_PARAM_MESSAGE, "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "kotlinx-coroutines-core"}, m1396k = 1, m1397mv = {1, 8, 0}, m1399xi = 48)
/* loaded from: classes3.dex */
public final class UndeliveredElementException extends RuntimeException {
    public UndeliveredElementException(String str, Throwable th) {
        super(str, th);
    }
}
