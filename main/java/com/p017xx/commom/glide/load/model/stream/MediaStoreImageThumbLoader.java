package com.p017xx.commom.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.data.mediastore.MediaStoreUtil;
import com.p017xx.commom.glide.load.data.mediastore.ThumbFetcher;
import com.p017xx.commom.glide.load.model.ModelLoader;
import com.p017xx.commom.glide.load.model.ModelLoaderFactory;
import com.p017xx.commom.glide.load.model.MultiModelLoaderFactory;
import com.p017xx.commom.glide.signature.ObjectKey;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class MediaStoreImageThumbLoader implements ModelLoader<Uri, InputStream> {
    private final Context context;

    public MediaStoreImageThumbLoader(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public ModelLoader.LoadData<InputStream> buildLoadData(Uri uri, int i, int i2, Options options) {
        if (MediaStoreUtil.isThumbnailSize(i, i2)) {
            return new ModelLoader.LoadData<>(new ObjectKey(uri), ThumbFetcher.buildImageFetcher(this.context, uri));
        }
        return null;
    }

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public boolean handles(Uri uri) {
        return MediaStoreUtil.isMediaStoreImageUri(uri);
    }

    /* loaded from: classes3.dex */
    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {
        private final Context context;

        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        public Factory(Context context) {
            this.context = context;
        }

        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new MediaStoreImageThumbLoader(this.context);
        }
    }
}
