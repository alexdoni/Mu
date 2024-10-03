package com.p017xx.commom.glide.load.model.stream;

import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.model.GlideUrl;
import com.p017xx.commom.glide.load.model.ModelLoader;
import com.p017xx.commom.glide.load.model.ModelLoaderFactory;
import com.p017xx.commom.glide.load.model.MultiModelLoaderFactory;
import java.io.InputStream;
import java.net.URL;

/* loaded from: classes3.dex */
public class UrlLoader implements ModelLoader<URL, InputStream> {
    private final ModelLoader<GlideUrl, InputStream> glideUrlLoader;

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public boolean handles(URL url) {
        return true;
    }

    public UrlLoader(ModelLoader<GlideUrl, InputStream> modelLoader) {
        this.glideUrlLoader = modelLoader;
    }

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public ModelLoader.LoadData<InputStream> buildLoadData(URL url, int i, int i2, Options options) {
        return this.glideUrlLoader.buildLoadData(new GlideUrl(url), i, i2, options);
    }

    /* loaded from: classes3.dex */
    public static class StreamFactory implements ModelLoaderFactory<URL, InputStream> {
        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public ModelLoader<URL, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new UrlLoader(multiModelLoaderFactory.build(GlideUrl.class, InputStream.class));
        }
    }
}
