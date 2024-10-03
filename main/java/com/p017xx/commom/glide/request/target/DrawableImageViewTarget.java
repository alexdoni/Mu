package com.p017xx.commom.glide.request.target;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* loaded from: classes3.dex */
public class DrawableImageViewTarget extends ImageViewTarget<Drawable> {
    public DrawableImageViewTarget(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public DrawableImageViewTarget(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p017xx.commom.glide.request.target.ImageViewTarget
    public void setResource(Drawable drawable) {
        ((ImageView) this.view).setImageDrawable(drawable);
    }
}
