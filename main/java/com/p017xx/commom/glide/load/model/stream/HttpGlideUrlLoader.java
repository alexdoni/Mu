package com.p017xx.commom.glide.load.model.stream;

import com.p017xx.commom.glide.load.Option;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.data.HttpUrlFetcher;
import com.p017xx.commom.glide.load.model.GlideUrl;
import com.p017xx.commom.glide.load.model.ModelCache;
import com.p017xx.commom.glide.load.model.ModelLoader;
import com.p017xx.commom.glide.load.model.ModelLoaderFactory;
import com.p017xx.commom.glide.load.model.MultiModelLoaderFactory;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class HttpGlideUrlLoader implements ModelLoader<GlideUrl, InputStream> {
    public static final Option<Integer> TIMEOUT = Option.memory("com.xx.commom.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 2500);
    private final ModelCache<GlideUrl, GlideUrl> modelCache;

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public boolean handles(GlideUrl glideUrl) {
        return true;
    }

    public HttpGlideUrlLoader() {
        this(null);
    }

    public HttpGlideUrlLoader(ModelCache<GlideUrl, GlideUrl> modelCache) {
        this.modelCache = modelCache;
    }

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public ModelLoader.LoadData<InputStream> buildLoadData(GlideUrl glideUrl, int i, int i2, Options options) {
        ModelCache<GlideUrl, GlideUrl> modelCache = this.modelCache;
        if (modelCache != null) {
            GlideUrl glideUrl2 = modelCache.get(glideUrl, 0, 0);
            if (glideUrl2 == null) {
                this.modelCache.put(glideUrl, 0, 0, glideUrl);
            } else {
                glideUrl = glideUrl2;
            }
        }
        return new ModelLoader.LoadData<>(glideUrl, new HttpUrlFetcher(glideUrl, ((Integer) options.get(TIMEOUT)).intValue()));
    }

    /* loaded from: classes3.dex */
    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private final ModelCache<GlideUrl, GlideUrl> modelCache = new ModelCache<>(500);

        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public ModelLoader<GlideUrl, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new HttpGlideUrlLoader(this.modelCache);
        }
    }
}
