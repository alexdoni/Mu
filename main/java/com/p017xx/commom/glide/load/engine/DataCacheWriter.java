package com.p017xx.commom.glide.load.engine;

import com.p017xx.commom.glide.load.Encoder;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.engine.cache.DiskCache;
import java.io.File;

/* loaded from: classes3.dex */
class DataCacheWriter<DataType> implements DiskCache.Writer {
    private final DataType data;
    private final Encoder<DataType> encoder;
    private final Options options;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataCacheWriter(Encoder<DataType> encoder, DataType datatype, Options options) {
        this.encoder = encoder;
        this.data = datatype;
        this.options = options;
    }

    @Override // com.xx.commom.glide.load.engine.cache.DiskCache.Writer
    public boolean write(File file) {
        return this.encoder.encode(this.data, file, this.options);
    }
}
