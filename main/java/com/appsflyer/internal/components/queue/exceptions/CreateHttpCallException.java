package com.appsflyer.internal.components.queue.exceptions;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006"}, m1395d2 = {"Lcom/appsflyer/internal/components/queue/exceptions/CreateHttpCallException;", "Ljava/util/NoSuchElementException;", "Lkotlin/NoSuchElementException;", "", "p0", "<init>", "(Ljava/lang/String;)V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class CreateHttpCallException extends NoSuchElementException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CreateHttpCallException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "");
    }
}
