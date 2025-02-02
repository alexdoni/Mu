package com.p017xx.commom.glide.manager;

import com.p017xx.commom.glide.util.Util;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: classes3.dex */
class ActivityFragmentLifecycle implements Lifecycle {
    private boolean isDestroyed;
    private boolean isStarted;
    private final Set<LifecycleListener> lifecycleListeners = Collections.newSetFromMap(new WeakHashMap());

    @Override // com.p017xx.commom.glide.manager.Lifecycle
    public void addListener(LifecycleListener lifecycleListener) {
        this.lifecycleListeners.add(lifecycleListener);
        if (this.isDestroyed) {
            lifecycleListener.onDestroy();
        } else if (this.isStarted) {
            lifecycleListener.onStart();
        } else {
            lifecycleListener.onStop();
        }
    }

    @Override // com.p017xx.commom.glide.manager.Lifecycle
    public void removeListener(LifecycleListener lifecycleListener) {
        this.lifecycleListeners.remove(lifecycleListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onStart() {
        this.isStarted = true;
        Iterator it = Util.getSnapshot(this.lifecycleListeners).iterator();
        while (it.hasNext()) {
            ((LifecycleListener) it.next()).onStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onStop() {
        this.isStarted = false;
        Iterator it = Util.getSnapshot(this.lifecycleListeners).iterator();
        while (it.hasNext()) {
            ((LifecycleListener) it.next()).onStop();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onDestroy() {
        this.isDestroyed = true;
        Iterator it = Util.getSnapshot(this.lifecycleListeners).iterator();
        while (it.hasNext()) {
            ((LifecycleListener) it.next()).onDestroy();
        }
    }
}
