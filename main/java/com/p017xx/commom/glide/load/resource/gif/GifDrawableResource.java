package com.p017xx.commom.glide.load.resource.gif;

import com.p017xx.commom.glide.load.engine.Initializable;
import com.p017xx.commom.glide.load.resource.drawable.DrawableResource;

/* loaded from: classes3.dex */
public class GifDrawableResource extends DrawableResource<GifDrawable> implements Initializable {
    public GifDrawableResource(GifDrawable gifDrawable) {
        super(gifDrawable);
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public Class<GifDrawable> getResourceClass() {
        return GifDrawable.class;
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public int getSize() {
        return ((GifDrawable) this.drawable).getSize();
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public void recycle() {
        ((GifDrawable) this.drawable).stop();
        ((GifDrawable) this.drawable).recycle();
    }

    @Override // com.p017xx.commom.glide.load.resource.drawable.DrawableResource, com.p017xx.commom.glide.load.engine.Initializable
    public void initialize() {
        ((GifDrawable) this.drawable).getFirstFrame().prepareToDraw();
    }
}
