package com.unity3d.player;

import android.view.Surface;

/* loaded from: classes3.dex */
public interface GoogleVrVideo {

    /* loaded from: classes3.dex */
    public interface GoogleVrVideoCallbacks {
        void onFrameAvailable();

        void onSurfaceAvailable(Surface surface);

        void onSurfaceUnavailable();
    }

    void deregisterGoogleVrVideoListener(GoogleVrVideoCallbacks googleVrVideoCallbacks);

    void registerGoogleVrVideoListener(GoogleVrVideoCallbacks googleVrVideoCallbacks);

    void setVideoLocationTransform(float[] fArr);
}
