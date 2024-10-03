package com.p017xx.commom.glide.manager;

/* loaded from: classes3.dex */
class ApplicationLifecycle implements Lifecycle {
    @Override // com.p017xx.commom.glide.manager.Lifecycle
    public void removeListener(LifecycleListener lifecycleListener) {
    }

    @Override // com.p017xx.commom.glide.manager.Lifecycle
    public void addListener(LifecycleListener lifecycleListener) {
        lifecycleListener.onStart();
    }
}
