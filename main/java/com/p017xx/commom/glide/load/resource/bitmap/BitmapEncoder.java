package com.p017xx.commom.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.p017xx.commom.glide.load.EncodeStrategy;
import com.p017xx.commom.glide.load.Option;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.ResourceEncoder;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayPool;

/* loaded from: classes3.dex */
public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    private static final String TAG = "BitmapEncoder";
    private final ArrayPool arrayPool;
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.xx.commom.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.xx.commom.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");

    public BitmapEncoder(ArrayPool arrayPool) {
        this.arrayPool = arrayPool;
    }

    @Deprecated
    public BitmapEncoder() {
        this.arrayPool = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0074 A[Catch: all -> 0x00bf, TRY_LEAVE, TryCatch #7 {all -> 0x00bf, blocks: (B:3:0x0023, B:15:0x004d, B:18:0x006e, B:20:0x0074, B:46:0x00bb, B:44:0x00be, B:38:0x0069), top: B:2:0x0023 }] */
    @Override // com.p017xx.commom.glide.load.Encoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean encode(com.p017xx.commom.glide.load.engine.Resource<android.graphics.Bitmap> r9, java.io.File r10, com.p017xx.commom.glide.load.Options r11) {
        /*
            r8 = this;
            java.lang.String r0 = "BitmapEncoder"
            java.lang.String r1 = "Compressed with type: "
            java.lang.Object r9 = r9.get()
            android.graphics.Bitmap r9 = (android.graphics.Bitmap) r9
            android.graphics.Bitmap$CompressFormat r2 = r8.getFormat(r9, r11)
            int r3 = r9.getWidth()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            int r4 = r9.getHeight()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.String r5 = "encode: [%dx%d] %s"
            com.p017xx.commom.glide.util.pool.GlideTrace.beginSectionFormat(r5, r3, r4, r2)
            long r3 = com.p017xx.commom.glide.util.LogTime.getLogTime()     // Catch: java.lang.Throwable -> Lbf
            com.xx.commom.glide.load.Option<java.lang.Integer> r5 = com.p017xx.commom.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_QUALITY     // Catch: java.lang.Throwable -> Lbf
            java.lang.Object r5 = r11.get(r5)     // Catch: java.lang.Throwable -> Lbf
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch: java.lang.Throwable -> Lbf
            int r5 = r5.intValue()     // Catch: java.lang.Throwable -> Lbf
            r6 = 0
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            r7.<init>(r10)     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            com.xx.commom.glide.load.engine.bitmap_recycle.ArrayPool r10 = r8.arrayPool     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L55
            if (r10 == 0) goto L46
            com.xx.commom.glide.load.data.BufferedOutputStream r10 = new com.xx.commom.glide.load.data.BufferedOutputStream     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L55
            com.xx.commom.glide.load.engine.bitmap_recycle.ArrayPool r6 = r8.arrayPool     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L55
            r10.<init>(r7, r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L55
            r6 = r10
            goto L47
        L46:
            r6 = r7
        L47:
            r9.compress(r2, r5, r6)     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            r6.close()     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            r6.close()     // Catch: java.io.IOException -> L50 java.lang.Throwable -> Lbf
        L50:
            r10 = 1
            goto L6d
        L52:
            r9 = move-exception
            r6 = r7
            goto Lb9
        L55:
            r10 = move-exception
            r6 = r7
            goto L5b
        L58:
            r9 = move-exception
            goto Lb9
        L5a:
            r10 = move-exception
        L5b:
            r5 = 3
            boolean r5 = android.util.Log.isLoggable(r0, r5)     // Catch: java.lang.Throwable -> L58
            if (r5 == 0) goto L67
            java.lang.String r5 = "Failed to encode Bitmap"
            android.util.Log.d(r0, r5, r10)     // Catch: java.lang.Throwable -> L58
        L67:
            if (r6 == 0) goto L6c
            r6.close()     // Catch: java.io.IOException -> L6c java.lang.Throwable -> Lbf
        L6c:
            r10 = 0
        L6d:
            r5 = 2
            boolean r5 = android.util.Log.isLoggable(r0, r5)     // Catch: java.lang.Throwable -> Lbf
            if (r5 == 0) goto Lb5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbf
            r5.<init>(r1)     // Catch: java.lang.Throwable -> Lbf
            r5.append(r2)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r1 = " of size "
            r5.append(r1)     // Catch: java.lang.Throwable -> Lbf
            int r1 = com.p017xx.commom.glide.util.Util.getBitmapByteSize(r9)     // Catch: java.lang.Throwable -> Lbf
            r5.append(r1)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r1 = " in "
            r5.append(r1)     // Catch: java.lang.Throwable -> Lbf
            double r1 = com.p017xx.commom.glide.util.LogTime.getElapsedMillis(r3)     // Catch: java.lang.Throwable -> Lbf
            r5.append(r1)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r1 = ", options format: "
            r5.append(r1)     // Catch: java.lang.Throwable -> Lbf
            com.xx.commom.glide.load.Option<android.graphics.Bitmap$CompressFormat> r1 = com.p017xx.commom.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_FORMAT     // Catch: java.lang.Throwable -> Lbf
            java.lang.Object r11 = r11.get(r1)     // Catch: java.lang.Throwable -> Lbf
            r5.append(r11)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r11 = ", hasAlpha: "
            r5.append(r11)     // Catch: java.lang.Throwable -> Lbf
            boolean r9 = r9.hasAlpha()     // Catch: java.lang.Throwable -> Lbf
            r5.append(r9)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r9 = r5.toString()     // Catch: java.lang.Throwable -> Lbf
            android.util.Log.v(r0, r9)     // Catch: java.lang.Throwable -> Lbf
        Lb5:
            com.p017xx.commom.glide.util.pool.GlideTrace.endSection()
            return r10
        Lb9:
            if (r6 == 0) goto Lbe
            r6.close()     // Catch: java.io.IOException -> Lbe java.lang.Throwable -> Lbf
        Lbe:
            throw r9     // Catch: java.lang.Throwable -> Lbf
        Lbf:
            r9 = move-exception
            com.p017xx.commom.glide.util.pool.GlideTrace.endSection()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p017xx.commom.glide.load.resource.bitmap.BitmapEncoder.encode(com.xx.commom.glide.load.engine.Resource, java.io.File, com.xx.commom.glide.load.Options):boolean");
    }

    private Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
        Bitmap.CompressFormat compressFormat = (Bitmap.CompressFormat) options.get(COMPRESSION_FORMAT);
        if (compressFormat != null) {
            return compressFormat;
        }
        if (bitmap.hasAlpha()) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    @Override // com.p017xx.commom.glide.load.ResourceEncoder
    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.TRANSFORMED;
    }
}
