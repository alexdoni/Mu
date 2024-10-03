package com.p017xx.commom.glide.util;

import com.p017xx.commom.glide.ListPreloader;

/* loaded from: classes3.dex */
public class FixedPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T> {
    private final int[] size;

    public FixedPreloadSizeProvider(int i, int i2) {
        this.size = new int[]{i, i2};
    }

    @Override // com.xx.commom.glide.ListPreloader.PreloadSizeProvider
    public int[] getPreloadSize(T t, int i, int i2) {
        return this.size;
    }
}
