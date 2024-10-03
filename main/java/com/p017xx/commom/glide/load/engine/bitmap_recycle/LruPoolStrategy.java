package com.p017xx.commom.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;

/* loaded from: classes3.dex */
interface LruPoolStrategy {
    Bitmap get(int i, int i2, Bitmap.Config config);

    int getSize(Bitmap bitmap);

    String logBitmap(int i, int i2, Bitmap.Config config);

    String logBitmap(Bitmap bitmap);

    void put(Bitmap bitmap);

    Bitmap removeLast();
}
