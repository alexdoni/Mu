package com.p017xx.commom.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.p017xx.commom.glide.Glide;
import com.p017xx.commom.glide.gifdecoder.GifDecoder;
import com.p017xx.commom.glide.gifdecoder.GifHeader;
import com.p017xx.commom.glide.gifdecoder.GifHeaderParser;
import com.p017xx.commom.glide.gifdecoder.StandardGifDecoder;
import com.p017xx.commom.glide.load.DecodeFormat;
import com.p017xx.commom.glide.load.ImageHeaderParser;
import com.p017xx.commom.glide.load.ImageHeaderParserUtils;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.ResourceDecoder;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayPool;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import com.p017xx.commom.glide.load.resource.UnitTransformation;
import com.p017xx.commom.glide.util.LogTime;
import com.p017xx.commom.glide.util.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;

/* loaded from: classes3.dex */
public class ByteBufferGifDecoder implements ResourceDecoder<ByteBuffer, GifDrawable> {
    private static final GifDecoderFactory GIF_DECODER_FACTORY = new GifDecoderFactory();
    private static final GifHeaderParserPool PARSER_POOL = new GifHeaderParserPool();
    private static final String TAG = "BufferGifDecoder";
    private final Context context;
    private final GifDecoderFactory gifDecoderFactory;
    private final GifHeaderParserPool parserPool;
    private final List<ImageHeaderParser> parsers;
    private final GifBitmapProvider provider;

    public ByteBufferGifDecoder(Context context) {
        this(context, Glide.get(context).getRegistry().getImageHeaderParsers(), Glide.get(context).getBitmapPool(), Glide.get(context).getArrayPool());
    }

    public ByteBufferGifDecoder(Context context, List<ImageHeaderParser> list, BitmapPool bitmapPool, ArrayPool arrayPool) {
        this(context, list, bitmapPool, arrayPool, PARSER_POOL, GIF_DECODER_FACTORY);
    }

    ByteBufferGifDecoder(Context context, List<ImageHeaderParser> list, BitmapPool bitmapPool, ArrayPool arrayPool, GifHeaderParserPool gifHeaderParserPool, GifDecoderFactory gifDecoderFactory) {
        this.context = context.getApplicationContext();
        this.parsers = list;
        this.gifDecoderFactory = gifDecoderFactory;
        this.provider = new GifBitmapProvider(bitmapPool, arrayPool);
        this.parserPool = gifHeaderParserPool;
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public boolean handles(ByteBuffer byteBuffer, Options options) throws IOException {
        return !((Boolean) options.get(GifOptions.DISABLE_ANIMATION)).booleanValue() && ImageHeaderParserUtils.getType(this.parsers, byteBuffer) == ImageHeaderParser.ImageType.GIF;
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public GifDrawableResource decode(ByteBuffer byteBuffer, int i, int i2, Options options) {
        GifHeaderParser obtain = this.parserPool.obtain(byteBuffer);
        try {
            return decode(byteBuffer, i, i2, obtain, options);
        } finally {
            this.parserPool.release(obtain);
        }
    }

    private GifDrawableResource decode(ByteBuffer byteBuffer, int i, int i2, GifHeaderParser gifHeaderParser, Options options) {
        long logTime = LogTime.getLogTime();
        try {
            GifHeader parseHeader = gifHeaderParser.parseHeader();
            if (parseHeader.getNumFrames() > 0 && parseHeader.getStatus() == 0) {
                Bitmap.Config config = options.get(GifOptions.DECODE_FORMAT) == DecodeFormat.PREFER_RGB_565 ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
                GifDecoder build = this.gifDecoderFactory.build(this.provider, parseHeader, byteBuffer, getSampleSize(parseHeader, i, i2));
                build.setDefaultBitmapConfig(config);
                build.advance();
                Bitmap nextFrame = build.getNextFrame();
                if (nextFrame == null) {
                    return null;
                }
                GifDrawableResource gifDrawableResource = new GifDrawableResource(new GifDrawable(this.context, build, UnitTransformation.get(), i, i2, nextFrame));
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "Decoded GIF from stream in " + LogTime.getElapsedMillis(logTime));
                }
                return gifDrawableResource;
            }
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Decoded GIF from stream in " + LogTime.getElapsedMillis(logTime));
            }
            return null;
        } finally {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Decoded GIF from stream in " + LogTime.getElapsedMillis(logTime));
            }
        }
    }

    private static int getSampleSize(GifHeader gifHeader, int i, int i2) {
        int min = Math.min(gifHeader.getHeight() / i2, gifHeader.getWidth() / i);
        int max = Math.max(1, min == 0 ? 0 : Integer.highestOneBit(min));
        if (Log.isLoggable(TAG, 2) && max > 1) {
            Log.v(TAG, "Downsampling GIF, sampleSize: " + max + ", target dimens: [" + i + "x" + i2 + "], actual dimens: [" + gifHeader.getWidth() + "x" + gifHeader.getHeight() + "]");
        }
        return max;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GifDecoderFactory {
        GifDecoderFactory() {
        }

        GifDecoder build(GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
            return new StandardGifDecoder(bitmapProvider, gifHeader, byteBuffer, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GifHeaderParserPool {
        private final Queue<GifHeaderParser> pool = Util.createQueue(0);

        GifHeaderParserPool() {
        }

        synchronized GifHeaderParser obtain(ByteBuffer byteBuffer) {
            GifHeaderParser poll;
            poll = this.pool.poll();
            if (poll == null) {
                poll = new GifHeaderParser();
            }
            return poll.setData(byteBuffer);
        }

        synchronized void release(GifHeaderParser gifHeaderParser) {
            gifHeaderParser.clear();
            this.pool.offer(gifHeaderParser);
        }
    }
}
