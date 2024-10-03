package com.p017xx.commom.glide.load.data.mediastore;

import java.io.File;

/* loaded from: classes3.dex */
class FileService {
    public boolean exists(File file) {
        return file.exists();
    }

    public long length(File file) {
        return file.length();
    }

    public File get(String str) {
        return new File(str);
    }
}
