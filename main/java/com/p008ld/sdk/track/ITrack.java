package com.p008ld.sdk.track;

import android.content.Context;
import android.os.Bundle;
import com.p008ld.sdk.internal.LDCallback;

/* compiled from: ITrack.kt */
/* loaded from: classes2.dex */
public interface ITrack {
    void init(Context context);

    boolean isTrackTokenName();

    void trackEvent(String str, Bundle bundle, LDCallback<Boolean> lDCallback);

    void trackPayEvent(String str, double d, String str2);

    /* compiled from: ITrack.kt */
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void trackEvent$default(ITrack iTrack, String str, Bundle bundle, LDCallback lDCallback, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackEvent");
            }
            if ((i & 2) != 0) {
                bundle = new Bundle();
            }
            iTrack.trackEvent(str, bundle, lDCallback);
        }
    }
}
