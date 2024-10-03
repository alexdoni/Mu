package com.appsflyer.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes.dex */
public final class AFg1eSDK extends AFg1hSDK {
    private final AFd1mSDK values;

    public AFg1eSDK(AFd1mSDK aFd1mSDK) {
        Intrinsics.checkNotNullParameter(aFd1mSDK, "");
        this.values = aFd1mSDK;
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: e */
    public final void mo56e(AFg1gSDK aFg1gSDK, String str, Throwable th, boolean z, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(th, "");
        if (z3) {
            if (StringsKt.isBlank(str)) {
                str = "missing label";
            }
            this.values.onAppOpenAttributionNative().AFKeystoreWrapper(th, withTag$SDK_prodRelease(str, aFg1gSDK));
        }
    }
}
