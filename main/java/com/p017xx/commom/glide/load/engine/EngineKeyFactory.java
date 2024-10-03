package com.p017xx.commom.glide.load.engine;

import com.p017xx.commom.glide.load.Key;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.Transformation;
import java.util.Map;

/* loaded from: classes3.dex */
class EngineKeyFactory {
    /* JADX INFO: Access modifiers changed from: package-private */
    public EngineKey buildKey(Object obj, Key key, int i, int i2, Map<Class<?>, Transformation<?>> map, Class<?> cls, Class<?> cls2, Options options) {
        return new EngineKey(obj, key, i, i2, map, cls, cls2, options);
    }
}
