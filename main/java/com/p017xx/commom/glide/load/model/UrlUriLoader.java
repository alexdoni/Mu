package com.p017xx.commom.glide.load.model;

import android.net.Uri;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.model.ModelLoader;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public class UrlUriLoader<Data> implements ModelLoader<Uri, Data> {
    private static final Set<String> SCHEMES = Collections.unmodifiableSet(new HashSet(Arrays.asList("http", "https")));
    private final ModelLoader<GlideUrl, Data> urlLoader;

    public UrlUriLoader(ModelLoader<GlideUrl, Data> modelLoader) {
        this.urlLoader = modelLoader;
    }

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Data> buildLoadData(Uri uri, int i, int i2, Options options) {
        return this.urlLoader.buildLoadData(new GlideUrl(uri.toString()), i, i2, options);
    }

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public boolean handles(Uri uri) {
        return SCHEMES.contains(uri.getScheme());
    }

    /* loaded from: classes3.dex */
    public static class StreamFactory implements ModelLoaderFactory<Uri, InputStream> {
        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new UrlUriLoader(multiModelLoaderFactory.build(GlideUrl.class, InputStream.class));
        }
    }
}
