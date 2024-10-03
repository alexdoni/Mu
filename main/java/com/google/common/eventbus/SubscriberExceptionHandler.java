package com.google.common.eventbus;

@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
public interface SubscriberExceptionHandler {
    void handleException(Throwable th, SubscriberExceptionContext subscriberExceptionContext);
}
