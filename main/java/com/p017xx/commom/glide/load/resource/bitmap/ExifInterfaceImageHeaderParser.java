package com.p017xx.commom.glide.load.resource.bitmap;

import android.media.ExifInterface;
import com.p017xx.commom.glide.load.ImageHeaderParser;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayPool;
import com.p017xx.commom.glide.util.ByteBufferUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class ExifInterfaceImageHeaderParser implements ImageHeaderParser {
    @Override // com.p017xx.commom.glide.load.ImageHeaderParser
    public ImageHeaderParser.ImageType getType(InputStream inputStream) throws IOException {
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    @Override // com.p017xx.commom.glide.load.ImageHeaderParser
    public ImageHeaderParser.ImageType getType(ByteBuffer byteBuffer) throws IOException {
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    @Override // com.p017xx.commom.glide.load.ImageHeaderParser
    public int getOrientation(InputStream inputStream, ArrayPool arrayPool) throws IOException {
        int attributeInt = new ExifInterface(inputStream).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
        if (attributeInt == 0) {
            return -1;
        }
        return attributeInt;
    }

    @Override // com.p017xx.commom.glide.load.ImageHeaderParser
    public int getOrientation(ByteBuffer byteBuffer, ArrayPool arrayPool) throws IOException {
        return getOrientation(ByteBufferUtil.toStream(byteBuffer), arrayPool);
    }
}
