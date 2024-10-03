package com.p017xx.commom.glide;

import com.p017xx.commom.glide.manager.RequestManagerRetriever;
import com.p017xx.commom.glide.module.AppGlideModule;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class GeneratedAppGlideModule extends AppGlideModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Set<Class<?>> getExcludedModuleClasses();

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestManagerRetriever.RequestManagerFactory getRequestManagerFactory() {
        return null;
    }

    GeneratedAppGlideModule() {
    }
}
