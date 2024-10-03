package com.p017xx.commom.glide.load.resource.file;

import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.ResourceDecoder;
import com.p017xx.commom.glide.load.engine.Resource;
import java.io.File;

/* loaded from: classes3.dex */
public class FileDecoder implements ResourceDecoder<File, File> {
    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public boolean handles(File file, Options options) {
        return true;
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public Resource<File> decode(File file, int i, int i2, Options options) {
        return new FileResource(file);
    }
}
