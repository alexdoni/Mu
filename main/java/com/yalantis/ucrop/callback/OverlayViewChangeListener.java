package com.yalantis.ucrop.callback;

import android.graphics.RectF;

/* loaded from: classes3.dex */
public interface OverlayViewChangeListener {
    void onCropRectUpdated(RectF rectF);

    void postTranslate(float f, float f2);
}
