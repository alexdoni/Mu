package com.p017xx.commom.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.ResourceDecoder;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.util.Util;

/* loaded from: classes3.dex */
public final class UnitBitmapDecoder implements ResourceDecoder<Bitmap, Bitmap> {
    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public boolean handles(Bitmap bitmap, Options options) {
        return true;
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(Bitmap bitmap, int i, int i2, Options options) {
        return new NonOwnedBitmapResource(bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class NonOwnedBitmapResource implements Resource<Bitmap> {
        private final Bitmap bitmap;

        @Override // com.p017xx.commom.glide.load.engine.Resource
        public void recycle() {
        }

        NonOwnedBitmapResource(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override // com.p017xx.commom.glide.load.engine.Resource
        public Class<Bitmap> getResourceClass() {
            return Bitmap.class;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.p017xx.commom.glide.load.engine.Resource
        public Bitmap get() {
            return this.bitmap;
        }

        @Override // com.p017xx.commom.glide.load.engine.Resource
        public int getSize() {
            return Util.getBitmapByteSize(this.bitmap);
        }
    }
}
