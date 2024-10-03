package com.p017xx.commom.glide.load.resource.bitmap;

import com.p017xx.commom.glide.load.Option;

/* loaded from: classes3.dex */
public abstract class DownsampleStrategy {
    public static final DownsampleStrategy AT_LEAST;
    public static final DownsampleStrategy AT_MOST;
    public static final DownsampleStrategy CENTER_INSIDE;
    public static final DownsampleStrategy CENTER_OUTSIDE;
    public static final DownsampleStrategy DEFAULT;
    public static final DownsampleStrategy FIT_CENTER = new FitCenter();
    public static final DownsampleStrategy NONE;
    public static final Option<DownsampleStrategy> OPTION;

    /* loaded from: classes3.dex */
    public enum SampleSizeRounding {
        MEMORY,
        QUALITY
    }

    public abstract SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4);

    public abstract float getScaleFactor(int i, int i2, int i3, int i4);

    static {
        CenterOutside centerOutside = new CenterOutside();
        CENTER_OUTSIDE = centerOutside;
        AT_LEAST = new AtLeast();
        AT_MOST = new AtMost();
        CENTER_INSIDE = new CenterInside();
        NONE = new None();
        DEFAULT = centerOutside;
        OPTION = Option.memory("com.xx.commom.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", centerOutside);
    }

    /* loaded from: classes3.dex */
    private static class FitCenter extends DownsampleStrategy {
        FitCenter() {
        }

        @Override // com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            return Math.min(i3 / i, i4 / i2);
        }

        @Override // com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    /* loaded from: classes3.dex */
    private static class CenterOutside extends DownsampleStrategy {
        CenterOutside() {
        }

        @Override // com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            return Math.max(i3 / i, i4 / i2);
        }

        @Override // com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    /* loaded from: classes3.dex */
    private static class AtLeast extends DownsampleStrategy {
        AtLeast() {
        }

        @Override // com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            if (Math.min(i2 / i4, i / i3) == 0) {
                return 1.0f;
            }
            return 1.0f / Integer.highestOneBit(r1);
        }

        @Override // com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    /* loaded from: classes3.dex */
    private static class AtMost extends DownsampleStrategy {
        AtMost() {
        }

        @Override // com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            int ceil = (int) Math.ceil(Math.max(i2 / i4, i / i3));
            return 1.0f / (r2 << (Math.max(1, Integer.highestOneBit(ceil)) >= ceil ? 0 : 1));
        }

        @Override // com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.MEMORY;
        }
    }

    /* loaded from: classes3.dex */
    private static class None extends DownsampleStrategy {
        @Override // com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            return 1.0f;
        }

        None() {
        }

        @Override // com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    /* loaded from: classes3.dex */
    private static class CenterInside extends DownsampleStrategy {
        CenterInside() {
        }

        @Override // com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            return Math.min(1.0f, FIT_CENTER.getScaleFactor(i, i2, i3, i4));
        }

        @Override // com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }
}
