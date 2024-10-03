package com.p017xx.commom.glide.load.resource.gif;

import android.util.Log;
import com.p017xx.commom.glide.load.EncodeStrategy;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.ResourceEncoder;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: classes3.dex */
public class GifDrawableEncoder implements ResourceEncoder<GifDrawable> {
    private static final String TAG = "GifEncoder";

    @Override // com.p017xx.commom.glide.load.ResourceEncoder
    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.SOURCE;
    }

    @Override // com.p017xx.commom.glide.load.Encoder
    public boolean encode(Resource<GifDrawable> resource, File file, Options options) {
        try {
            ByteBufferUtil.toFile(resource.get().getBuffer(), file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "Failed to encode GIF drawable data", e);
            }
            return false;
        }
    }
}
