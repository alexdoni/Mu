package com.p017xx.commom.glide.load.model;

import android.util.Log;
import com.p017xx.commom.glide.load.Encoder;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class ByteBufferEncoder implements Encoder<ByteBuffer> {
    private static final String TAG = "ByteBufferEncoder";

    @Override // com.p017xx.commom.glide.load.Encoder
    public boolean encode(ByteBuffer byteBuffer, File file, Options options) {
        try {
            ByteBufferUtil.toFile(byteBuffer, file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to write data", e);
            }
            return false;
        }
    }
}
