package com.p017xx.commom.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.p017xx.commom.glide.Glide;
import com.p017xx.commom.glide.load.Transformation;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

/* loaded from: classes3.dex */
public class DrawableTransformation implements Transformation<Drawable> {
    private final boolean isRequired;
    private final Transformation<Bitmap> wrapped;

    public Transformation<BitmapDrawable> asBitmapDrawable() {
        return this;
    }

    public DrawableTransformation(Transformation<Bitmap> transformation, boolean z) {
        this.wrapped = transformation;
        this.isRequired = z;
    }

    @Override // com.p017xx.commom.glide.load.Transformation
    public Resource<Drawable> transform(Context context, Resource<Drawable> resource, int i, int i2) {
        BitmapPool bitmapPool = Glide.get(context).getBitmapPool();
        Drawable drawable = resource.get();
        Resource<Bitmap> convert = DrawableToBitmapConverter.convert(bitmapPool, drawable, i, i2);
        if (convert == null) {
            if (!this.isRequired) {
                return resource;
            }
            throw new IllegalArgumentException("Unable to convert " + drawable + " to a Bitmap");
        }
        Resource<Bitmap> transform = this.wrapped.transform(context, convert, i, i2);
        if (transform.equals(convert)) {
            transform.recycle();
            return resource;
        }
        return newDrawableResource(context, transform);
    }

    private Resource<Drawable> newDrawableResource(Context context, Resource<Bitmap> resource) {
        return LazyBitmapDrawableResource.obtain(context.getResources(), resource);
    }

    @Override // com.p017xx.commom.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof DrawableTransformation) {
            return this.wrapped.equals(((DrawableTransformation) obj).wrapped);
        }
        return false;
    }

    @Override // com.p017xx.commom.glide.load.Key
    public int hashCode() {
        return this.wrapped.hashCode();
    }

    @Override // com.p017xx.commom.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        this.wrapped.updateDiskCacheKey(messageDigest);
    }
}
