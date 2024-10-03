package com.p017xx.commom.glide.load.engine.bitmap_recycle;

import com.p017xx.commom.glide.load.engine.bitmap_recycle.Poolable;
import com.p017xx.commom.glide.util.Util;
import java.util.Queue;

/* loaded from: classes3.dex */
abstract class BaseKeyPool<T extends Poolable> {
    private static final int MAX_SIZE = 20;
    private final Queue<T> keyPool = Util.createQueue(20);

    abstract T create();

    /* JADX INFO: Access modifiers changed from: package-private */
    public T get() {
        T poll = this.keyPool.poll();
        return poll == null ? create() : poll;
    }

    public void offer(T t) {
        if (this.keyPool.size() < 20) {
            this.keyPool.offer(t);
        }
    }
}
