package com.p017xx.commom.glide.load;

import android.content.Context;
import com.p017xx.commom.glide.load.engine.Resource;

/* loaded from: classes3.dex */
public interface Transformation<T> extends Key {
    Resource<T> transform(Context context, Resource<T> resource, int i, int i2);
}
