package com.xsdk.ab_firstbase.eventbus.event;

/* loaded from: classes3.dex */
final class Subscription {
    volatile boolean active = true;
    final int priority;
    final Object subscriber;
    final SubscriberMethod subscriberMethod;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Subscription(Object obj, SubscriberMethod subscriberMethod, int i) {
        this.subscriber = obj;
        this.subscriberMethod = subscriberMethod;
        this.priority = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        return this.subscriber == subscription.subscriber && this.subscriberMethod.equals(subscription.subscriberMethod);
    }

    public int hashCode() {
        return this.subscriber.hashCode() + this.subscriberMethod.methodString.hashCode();
    }
}
