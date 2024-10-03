package com.appsflyer.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFg1bSDK extends AFg1hSDK {
    private final AFd1mSDK AFInAppEventParameterName;
    private final boolean valueOf;

    public AFg1bSDK(AFd1mSDK aFd1mSDK) {
        Intrinsics.checkNotNullParameter(aFd1mSDK, "");
        this.AFInAppEventParameterName = aFd1mSDK;
        this.valueOf = true;
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    public final boolean getShouldExtendMsg() {
        return this.valueOf;
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: d */
    public final void mo55d(AFg1gSDK aFg1gSDK, String str, boolean z) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        if (z) {
            this.AFInAppEventParameterName.mo78i().AFInAppEventType("D", AFInAppEventParameterName(str, aFg1gSDK));
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: e */
    public final void mo56e(AFg1gSDK aFg1gSDK, String str, Throwable th, boolean z, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(th, "");
        if (z4) {
            this.AFInAppEventParameterName.mo78i().AFInAppEventType(ExifInterface.LONGITUDE_EAST, AFInAppEventParameterName(str, aFg1gSDK));
        }
        if (z4) {
            this.AFInAppEventParameterName.mo78i().AFInAppEventType(th);
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: i */
    public final void mo57i(AFg1gSDK aFg1gSDK, String str, boolean z) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        if (z) {
            this.AFInAppEventParameterName.mo78i().AFInAppEventType("I", AFInAppEventParameterName(str, aFg1gSDK));
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: w */
    public final void mo59w(AFg1gSDK aFg1gSDK, String str, boolean z) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        if (z) {
            this.AFInAppEventParameterName.mo78i().AFInAppEventType(ExifInterface.LONGITUDE_WEST, AFInAppEventParameterName(str, aFg1gSDK));
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: v */
    public final void mo58v(AFg1gSDK aFg1gSDK, String str, boolean z) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        if (z) {
            this.AFInAppEventParameterName.mo78i().AFInAppEventType(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, AFInAppEventParameterName(str, aFg1gSDK));
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    public final void force(AFg1gSDK aFg1gSDK, String str) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        this.AFInAppEventParameterName.mo78i().AFInAppEventType("F", AFInAppEventParameterName(str, aFg1gSDK));
    }
}
