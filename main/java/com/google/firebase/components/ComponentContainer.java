package com.google.firebase.components;

import com.android.tools.r8.annotations.SynthesizedClassV2;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.Set;

/* loaded from: classes2.dex */
public interface ComponentContainer {
    <T> T get(Qualified<T> qualified);

    <T> T get(Class<T> cls);

    <T> Deferred<T> getDeferred(Qualified<T> qualified);

    <T> Deferred<T> getDeferred(Class<T> cls);

    <T> Provider<T> getProvider(Qualified<T> qualified);

    <T> Provider<T> getProvider(Class<T> cls);

    <T> Set<T> setOf(Qualified<T> qualified);

    <T> Set<T> setOf(Class<T> cls);

    <T> Provider<Set<T>> setOfProvider(Qualified<T> qualified);

    <T> Provider<Set<T>> setOfProvider(Class<T> cls);

    @SynthesizedClassV2(kind = 8, versionHash = "7a5b85d3ee2e0991ca3502602e9389a98f55c0576b887125894a7ec03823f8d3")
    /* renamed from: com.google.firebase.components.ComponentContainer$-CC, reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        public static Object $default$get(ComponentContainer _this, Qualified qualified) {
            Provider provider = _this.getProvider(qualified);
            if (provider == null) {
                return null;
            }
            return provider.get();
        }

        public static Set $default$setOf(ComponentContainer _this, Qualified qualified) {
            return (Set) _this.setOfProvider(qualified).get();
        }
    }
}
