package com.p017xx.commom.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.ResourceDecoder;
import com.p017xx.commom.glide.load.engine.Resource;

/* loaded from: classes3.dex */
public class UnitDrawableDecoder implements ResourceDecoder<Drawable, Drawable> {
    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public boolean handles(Drawable drawable, Options options) {
        return true;
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public Resource<Drawable> decode(Drawable drawable, int i, int i2, Options options) {
        return NonOwnedDrawableResource.newInstance(drawable);
    }
}
