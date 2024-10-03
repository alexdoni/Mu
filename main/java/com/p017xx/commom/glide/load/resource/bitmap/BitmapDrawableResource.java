package com.p017xx.commom.glide.load.resource.bitmap;

import android.graphics.drawable.BitmapDrawable;
import com.p017xx.commom.glide.load.engine.Initializable;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import com.p017xx.commom.glide.load.resource.drawable.DrawableResource;
import com.p017xx.commom.glide.util.Util;

/* loaded from: classes3.dex */
public class BitmapDrawableResource extends DrawableResource<BitmapDrawable> implements Initializable {
    private final BitmapPool bitmapPool;

    public BitmapDrawableResource(BitmapDrawable bitmapDrawable, BitmapPool bitmapPool) {
        super(bitmapDrawable);
        this.bitmapPool = bitmapPool;
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public Class<BitmapDrawable> getResourceClass() {
        return BitmapDrawable.class;
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public int getSize() {
        return Util.getBitmapByteSize(((BitmapDrawable) this.drawable).getBitmap());
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public void recycle() {
        this.bitmapPool.put(((BitmapDrawable) this.drawable).getBitmap());
    }

    @Override // com.p017xx.commom.glide.load.resource.drawable.DrawableResource, com.p017xx.commom.glide.load.engine.Initializable
    public void initialize() {
        ((BitmapDrawable) this.drawable).getBitmap().prepareToDraw();
    }
}
