package com.p017xx.commom.glide.load.resource.drawable;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.p017xx.commom.glide.load.engine.Initializable;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.resource.gif.GifDrawable;
import com.p017xx.commom.glide.util.Preconditions;

/* loaded from: classes3.dex */
public abstract class DrawableResource<T extends Drawable> implements Resource<T>, Initializable {
    protected final T drawable;

    public DrawableResource(T t) {
        this.drawable = (T) Preconditions.checkNotNull(t);
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public final T get() {
        Drawable.ConstantState constantState = this.drawable.getConstantState();
        if (constantState == null) {
            return this.drawable;
        }
        return (T) constantState.newDrawable();
    }

    public void initialize() {
        T t = this.drawable;
        if (t instanceof BitmapDrawable) {
            ((BitmapDrawable) t).getBitmap().prepareToDraw();
        } else if (t instanceof GifDrawable) {
            ((GifDrawable) t).getFirstFrame().prepareToDraw();
        }
    }
}
