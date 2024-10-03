package com.p017xx.commom.glide.request.target;

import android.graphics.Bitmap;
import android.widget.ImageView;

/* loaded from: classes3.dex */
public class BitmapImageViewTarget extends ImageViewTarget<Bitmap> {
    public BitmapImageViewTarget(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public BitmapImageViewTarget(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p017xx.commom.glide.request.target.ImageViewTarget
    public void setResource(Bitmap bitmap) {
        ((ImageView) this.view).setImageBitmap(bitmap);
    }
}
