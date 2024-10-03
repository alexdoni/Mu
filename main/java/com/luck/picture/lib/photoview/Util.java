package com.luck.picture.lib.photoview;

import android.widget.ImageView;

/* loaded from: classes2.dex */
class Util {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getPointerIndex(int i) {
        return (i & 65280) >> 8;
    }

    Util() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkZoomLevels(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
        }
        if (f2 >= f3) {
            throw new IllegalArgumentException("Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasDrawable(ImageView imageView) {
        return imageView.getDrawable() != null;
    }

    /* renamed from: com.luck.picture.lib.photoview.Util$1 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class C22141 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            $SwitchMap$android$widget$ImageView$ScaleType = iArr;
            try {
                iArr[ImageView.ScaleType.MATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSupportedScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        if (C22141.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()] != 1) {
            return true;
        }
        throw new IllegalStateException("Matrix scale type is not supported");
    }
}
