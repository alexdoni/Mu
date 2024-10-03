package com.p008ld.sdk.track;

import android.content.Context;
import android.os.Bundle;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDCallback;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDMiddleTrack.kt */
/* loaded from: classes2.dex */
public final class LDMiddleTrack implements ITrack {
    @Override // com.p008ld.sdk.track.ITrack
    public void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.p008ld.sdk.track.ITrack
    public boolean isTrackTokenName() {
        return true;
    }

    @Override // com.p008ld.sdk.track.ITrack
    public void trackPayEvent(String eventName, double d, String str) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
    }

    @Override // com.p008ld.sdk.track.ITrack
    public void trackEvent(String eventName, Bundle bundle, LDCallback<Boolean> lDCallback) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        String string = bundle != null ? bundle.getString("userId") : null;
        int i = bundle != null ? bundle.getInt("eventResult") : 1;
        String string2 = bundle != null ? bundle.getString("orderId") : null;
        String string3 = bundle != null ? bundle.getString("loginType") : null;
        zza.zza.zza().zza(eventName, i, string, bundle != null ? bundle.getString("failureReason", "") : null, string3, string2, lDCallback);
    }
}
