package com.p017xx.commom.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import com.p017xx.commom.glide.load.DecodeFormat;
import com.p017xx.commom.glide.load.ImageHeaderParser;
import com.p017xx.commom.glide.load.ImageHeaderParserUtils;
import com.p017xx.commom.glide.load.Option;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayPool;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import com.p017xx.commom.glide.load.resource.bitmap.DownsampleStrategy;
import com.p017xx.commom.glide.util.LogTime;
import com.p017xx.commom.glide.util.Preconditions;
import com.p017xx.commom.glide.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/* loaded from: classes3.dex */
public final class Downsampler {
    private static final int MARK_POSITION = 10485760;
    static final String TAG = "Downsampler";
    private final BitmapPool bitmapPool;
    private final ArrayPool byteArrayPool;
    private final DisplayMetrics displayMetrics;
    private final HardwareConfigState hardwareConfigState = HardwareConfigState.getInstance();
    private final List<ImageHeaderParser> parsers;
    public static final Option<DecodeFormat> DECODE_FORMAT = Option.memory("com.xx.commom.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);

    @Deprecated
    public static final Option<DownsampleStrategy> DOWNSAMPLE_STRATEGY = DownsampleStrategy.OPTION;
    public static final Option<Boolean> FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS = Option.memory("com.xx.commom.glide.load.resource.bitmap.Downsampler.FixBitmapSize", false);
    public static final Option<Boolean> ALLOW_HARDWARE_CONFIG = Option.memory("com.xx.commom.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", false);
    private static final String WBMP_MIME_TYPE = "image/vnd.wap.wbmp";
    private static final String ICO_MIME_TYPE = "image/x-ico";
    private static final Set<String> NO_DOWNSAMPLE_PRE_N_MIME_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(WBMP_MIME_TYPE, ICO_MIME_TYPE)));
    private static final DecodeCallbacks EMPTY_CALLBACKS = new DecodeCallbacks() { // from class: com.xx.commom.glide.load.resource.bitmap.Downsampler.1
        @Override // com.xx.commom.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) {
        }

        @Override // com.xx.commom.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onObtainBounds() {
        }
    };
    private static final Set<ImageHeaderParser.ImageType> TYPES_THAT_USE_POOL_PRE_KITKAT = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
    private static final Queue<BitmapFactory.Options> OPTIONS_QUEUE = Util.createQueue(0);

    /* loaded from: classes3.dex */
    public interface DecodeCallbacks {
        void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) throws IOException;

        void onObtainBounds();
    }

    private static int round(double d) {
        return (int) (d + 0.5d);
    }

    private boolean shouldUsePool(ImageHeaderParser.ImageType imageType) {
        return true;
    }

    public boolean handles(InputStream inputStream) {
        return true;
    }

    public boolean handles(ByteBuffer byteBuffer) {
        return true;
    }

    public Downsampler(List<ImageHeaderParser> list, DisplayMetrics displayMetrics, BitmapPool bitmapPool, ArrayPool arrayPool) {
        this.parsers = list;
        this.displayMetrics = (DisplayMetrics) Preconditions.checkNotNull(displayMetrics);
        this.bitmapPool = (BitmapPool) Preconditions.checkNotNull(bitmapPool);
        this.byteArrayPool = (ArrayPool) Preconditions.checkNotNull(arrayPool);
    }

    public Resource<Bitmap> decode(InputStream inputStream, int i, int i2, Options options) throws IOException {
        return decode(inputStream, i, i2, options, EMPTY_CALLBACKS);
    }

    public Resource<Bitmap> decode(InputStream inputStream, int i, int i2, Options options, DecodeCallbacks decodeCallbacks) throws IOException {
        Preconditions.checkArgument(inputStream.markSupported(), "You must provide an InputStream that supports mark()");
        byte[] bArr = (byte[]) this.byteArrayPool.get(65536, byte[].class);
        BitmapFactory.Options defaultOptions = getDefaultOptions();
        defaultOptions.inTempStorage = bArr;
        DecodeFormat decodeFormat = (DecodeFormat) options.get(DECODE_FORMAT);
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) options.get(DownsampleStrategy.OPTION);
        boolean booleanValue = ((Boolean) options.get(FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS)).booleanValue();
        Option<Boolean> option = ALLOW_HARDWARE_CONFIG;
        try {
            return BitmapResource.obtain(decodeFromWrappedStreams(inputStream, defaultOptions, downsampleStrategy, decodeFormat, options.get(option) != null && ((Boolean) options.get(option)).booleanValue(), i, i2, booleanValue, decodeCallbacks), this.bitmapPool);
        } finally {
            releaseOptions(defaultOptions);
            this.byteArrayPool.put(bArr);
        }
    }

    private Bitmap decodeFromWrappedStreams(InputStream inputStream, BitmapFactory.Options options, DownsampleStrategy downsampleStrategy, DecodeFormat decodeFormat, boolean z, int i, int i2, boolean z2, DecodeCallbacks decodeCallbacks) throws IOException {
        int i3;
        int round;
        int round2;
        long logTime = LogTime.getLogTime();
        int[] dimensions = getDimensions(inputStream, options, decodeCallbacks, this.bitmapPool);
        int i4 = dimensions[0];
        int i5 = dimensions[1];
        String str = options.outMimeType;
        boolean z3 = (i4 == -1 || i5 == -1) ? false : z;
        int orientation = ImageHeaderParserUtils.getOrientation(this.parsers, inputStream, this.byteArrayPool);
        int exifOrientationDegrees = TransformationUtils.getExifOrientationDegrees(orientation);
        boolean isExifOrientationRequired = TransformationUtils.isExifOrientationRequired(orientation);
        int i6 = i == Integer.MIN_VALUE ? i4 : i;
        int i7 = i2 == Integer.MIN_VALUE ? i5 : i2;
        ImageHeaderParser.ImageType type = ImageHeaderParserUtils.getType(this.parsers, inputStream, this.byteArrayPool);
        calculateScaling(type, inputStream, decodeCallbacks, this.bitmapPool, downsampleStrategy, exifOrientationDegrees, i4, i5, i6, i7, options);
        calculateConfig(inputStream, decodeFormat, z3, isExifOrientationRequired, options, i6, i7);
        int i8 = options.inSampleSize;
        if (shouldUsePool(type)) {
            if (i4 < 0 || i5 < 0 || !z2) {
                float f = isScaling(options) ? options.inTargetDensity / options.inDensity : 1.0f;
                int i9 = options.inSampleSize;
                float f2 = i9;
                int ceil = (int) Math.ceil(i4 / f2);
                int ceil2 = (int) Math.ceil(i5 / f2);
                round = Math.round(ceil * f);
                round2 = Math.round(ceil2 * f);
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "Calculated target [" + round + "x" + round2 + "] for source [" + i4 + "x" + i5 + "], sampleSize: " + i9 + ", targetDensity: " + options.inTargetDensity + ", density: " + options.inDensity + ", density multiplier: " + f);
                }
            } else {
                round = i6;
                round2 = i7;
            }
            if (round > 0 && round2 > 0) {
                setInBitmap(options, this.bitmapPool, round, round2);
            }
        }
        Bitmap decodeStream = decodeStream(inputStream, options, decodeCallbacks, this.bitmapPool);
        decodeCallbacks.onDecodeComplete(this.bitmapPool, decodeStream);
        if (Log.isLoggable(TAG, 2)) {
            i3 = orientation;
            logDecode(i4, i5, str, options, decodeStream, i, i2, logTime);
        } else {
            i3 = orientation;
        }
        if (decodeStream == null) {
            return null;
        }
        decodeStream.setDensity(this.displayMetrics.densityDpi);
        Bitmap rotateImageExif = TransformationUtils.rotateImageExif(this.bitmapPool, decodeStream, i3);
        if (decodeStream.equals(rotateImageExif)) {
            return rotateImageExif;
        }
        this.bitmapPool.put(decodeStream);
        return rotateImageExif;
    }

    private static void calculateScaling(ImageHeaderParser.ImageType imageType, InputStream inputStream, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool, DownsampleStrategy downsampleStrategy, int i, int i2, int i3, int i4, int i5, BitmapFactory.Options options) throws IOException {
        float scaleFactor;
        int min;
        int max;
        int floor;
        double floor2;
        int i6;
        if (i2 <= 0 || i3 <= 0) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unable to determine dimensions for: " + imageType + " with target [" + i4 + "x" + i5 + "]");
                return;
            }
            return;
        }
        if (i == 90 || i == 270) {
            scaleFactor = downsampleStrategy.getScaleFactor(i3, i2, i4, i5);
        } else {
            scaleFactor = downsampleStrategy.getScaleFactor(i2, i3, i4, i5);
        }
        if (scaleFactor <= 0.0f) {
            throw new IllegalArgumentException("Cannot scale with factor: " + scaleFactor + " from: " + downsampleStrategy + ", source: [" + i2 + "x" + i3 + "], target: [" + i4 + "x" + i5 + "]");
        }
        DownsampleStrategy.SampleSizeRounding sampleSizeRounding = downsampleStrategy.getSampleSizeRounding(i2, i3, i4, i5);
        if (sampleSizeRounding == null) {
            throw new IllegalArgumentException("Cannot round with null rounding");
        }
        float f = i2;
        float f2 = i3;
        int round = i2 / round(scaleFactor * f);
        int round2 = i3 / round(scaleFactor * f2);
        if (sampleSizeRounding == DownsampleStrategy.SampleSizeRounding.MEMORY) {
            min = Math.max(round, round2);
        } else {
            min = Math.min(round, round2);
        }
        if (Build.VERSION.SDK_INT > 23 || !NO_DOWNSAMPLE_PRE_N_MIME_TYPES.contains(options.outMimeType)) {
            max = Math.max(1, Integer.highestOneBit(min));
            if (sampleSizeRounding == DownsampleStrategy.SampleSizeRounding.MEMORY && max < 1.0f / scaleFactor) {
                max <<= 1;
            }
        } else {
            max = 1;
        }
        options.inSampleSize = max;
        if (imageType == ImageHeaderParser.ImageType.JPEG) {
            float min2 = Math.min(max, 8);
            floor = (int) Math.ceil(f / min2);
            i6 = (int) Math.ceil(f2 / min2);
            int i7 = max / 8;
            if (i7 > 0) {
                floor /= i7;
                i6 /= i7;
            }
        } else {
            if (imageType == ImageHeaderParser.ImageType.PNG || imageType == ImageHeaderParser.ImageType.PNG_A) {
                float f3 = max;
                floor = (int) Math.floor(f / f3);
                floor2 = Math.floor(f2 / f3);
            } else if (imageType == ImageHeaderParser.ImageType.WEBP || imageType == ImageHeaderParser.ImageType.WEBP_A) {
                if (Build.VERSION.SDK_INT >= 24) {
                    float f4 = max;
                    floor = Math.round(f / f4);
                    i6 = Math.round(f2 / f4);
                } else {
                    float f5 = max;
                    floor = (int) Math.floor(f / f5);
                    floor2 = Math.floor(f2 / f5);
                }
            } else if (i2 % max != 0 || i3 % max != 0) {
                int[] dimensions = getDimensions(inputStream, options, decodeCallbacks, bitmapPool);
                int i8 = dimensions[0];
                i6 = dimensions[1];
                floor = i8;
            } else {
                floor = i2 / max;
                i6 = i3 / max;
            }
            i6 = (int) floor2;
        }
        double scaleFactor2 = downsampleStrategy.getScaleFactor(floor, i6, i4, i5);
        options.inTargetDensity = adjustTargetDensityForError(scaleFactor2);
        options.inDensity = getDensityMultiplier(scaleFactor2);
        if (isScaling(options)) {
            options.inScaled = true;
        } else {
            options.inTargetDensity = 0;
            options.inDensity = 0;
        }
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Calculate scaling, source: [" + i2 + "x" + i3 + "], target: [" + i4 + "x" + i5 + "], power of two scaled: [" + floor + "x" + i6 + "], exact scale factor: " + scaleFactor + ", power of 2 sample size: " + max + ", adjusted scale factor: " + scaleFactor2 + ", target density: " + options.inTargetDensity + ", density: " + options.inDensity);
        }
    }

    private static int adjustTargetDensityForError(double d) {
        return round((d / (r1 / r0)) * round(getDensityMultiplier(d) * d));
    }

    private static int getDensityMultiplier(double d) {
        if (d > 1.0d) {
            d = 1.0d / d;
        }
        return (int) Math.round(d * 2.147483647E9d);
    }

    private void calculateConfig(InputStream inputStream, DecodeFormat decodeFormat, boolean z, boolean z2, BitmapFactory.Options options, int i, int i2) {
        boolean z3;
        if (this.hardwareConfigState.setHardwareConfigIfAllowed(i, i2, options, decodeFormat, z, z2)) {
            return;
        }
        if (decodeFormat == DecodeFormat.PREFER_ARGB_8888) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return;
        }
        try {
            z3 = ImageHeaderParserUtils.getType(this.parsers, inputStream, this.byteArrayPool).hasAlpha();
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Cannot determine whether the image has alpha or not from header, format " + decodeFormat, e);
            }
            z3 = false;
        }
        options.inPreferredConfig = z3 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        if (options.inPreferredConfig == Bitmap.Config.RGB_565) {
            options.inDither = true;
        }
    }

    private static int[] getDimensions(InputStream inputStream, BitmapFactory.Options options, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool) throws IOException {
        options.inJustDecodeBounds = true;
        decodeStream(inputStream, options, decodeCallbacks, bitmapPool);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:?, code lost:
    
        throw r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Bitmap decodeStream(java.io.InputStream r6, android.graphics.BitmapFactory.Options r7, com.xx.commom.glide.load.resource.bitmap.Downsampler.DecodeCallbacks r8, com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool r9) throws java.io.IOException {
        /*
            java.lang.String r0 = "Downsampler"
            boolean r1 = r7.inJustDecodeBounds
            if (r1 == 0) goto Lc
            r1 = 10485760(0xa00000, float:1.469368E-38)
            r6.mark(r1)
            goto Lf
        Lc:
            r8.onObtainBounds()
        Lf:
            int r1 = r7.outWidth
            int r2 = r7.outHeight
            java.lang.String r3 = r7.outMimeType
            java.util.concurrent.locks.Lock r4 = com.p017xx.commom.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r4.lock()
            r4 = 0
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeStream(r6, r4, r7)     // Catch: java.lang.Throwable -> L30 java.lang.IllegalArgumentException -> L32
            java.util.concurrent.locks.Lock r9 = com.p017xx.commom.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r9.unlock()
            boolean r7 = r7.inJustDecodeBounds
            if (r7 == 0) goto L2f
            r6.reset()
        L2f:
            return r8
        L30:
            r6 = move-exception
            goto L5f
        L32:
            r5 = move-exception
            java.io.IOException r1 = newIoExceptionForInBitmapAssertion(r5, r1, r2, r3, r7)     // Catch: java.lang.Throwable -> L30
            r2 = 3
            boolean r2 = android.util.Log.isLoggable(r0, r2)     // Catch: java.lang.Throwable -> L30
            if (r2 == 0) goto L43
            java.lang.String r2 = "Failed to decode with inBitmap, trying again without Bitmap re-use"
            android.util.Log.d(r0, r2, r1)     // Catch: java.lang.Throwable -> L30
        L43:
            android.graphics.Bitmap r0 = r7.inBitmap     // Catch: java.lang.Throwable -> L30
            if (r0 == 0) goto L5e
            r6.reset()     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L5d
            android.graphics.Bitmap r0 = r7.inBitmap     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L5d
            r9.put(r0)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L5d
            r7.inBitmap = r4     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L5d
            android.graphics.Bitmap r6 = decodeStream(r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L5d
            java.util.concurrent.locks.Lock r7 = com.p017xx.commom.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r7.unlock()
            return r6
        L5d:
            throw r1     // Catch: java.lang.Throwable -> L30
        L5e:
            throw r1     // Catch: java.lang.Throwable -> L30
        L5f:
            java.util.concurrent.locks.Lock r7 = com.p017xx.commom.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r7.unlock()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p017xx.commom.glide.load.resource.bitmap.Downsampler.decodeStream(java.io.InputStream, android.graphics.BitmapFactory$Options, com.xx.commom.glide.load.resource.bitmap.Downsampler$DecodeCallbacks, com.xx.commom.glide.load.engine.bitmap_recycle.BitmapPool):android.graphics.Bitmap");
    }

    private static boolean isScaling(BitmapFactory.Options options) {
        return options.inTargetDensity > 0 && options.inDensity > 0 && options.inTargetDensity != options.inDensity;
    }

    private static void logDecode(int i, int i2, String str, BitmapFactory.Options options, Bitmap bitmap, int i3, int i4, long j) {
        Log.v(TAG, "Decoded " + getBitmapString(bitmap) + " from [" + i + "x" + i2 + "] " + str + " with inBitmap " + getInBitmapString(options) + " for [" + i3 + "x" + i4 + "], sample size: " + options.inSampleSize + ", density: " + options.inDensity + ", target density: " + options.inTargetDensity + ", thread: " + Thread.currentThread().getName() + ", duration: " + LogTime.getElapsedMillis(j));
    }

    private static String getInBitmapString(BitmapFactory.Options options) {
        return getBitmapString(options.inBitmap);
    }

    private static String getBitmapString(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + (" (" + bitmap.getAllocationByteCount() + ")");
    }

    private static IOException newIoExceptionForInBitmapAssertion(IllegalArgumentException illegalArgumentException, int i, int i2, String str, BitmapFactory.Options options) {
        return new IOException("Exception decoding bitmap, outWidth: " + i + ", outHeight: " + i2 + ", outMimeType: " + str + ", inBitmap: " + getInBitmapString(options), illegalArgumentException);
    }

    private static void setInBitmap(BitmapFactory.Options options, BitmapPool bitmapPool, int i, int i2) {
        Bitmap.Config config;
        if (Build.VERSION.SDK_INT < 26) {
            config = null;
        } else if (options.inPreferredConfig == Bitmap.Config.HARDWARE) {
            return;
        } else {
            config = options.outConfig;
        }
        if (config == null) {
            config = options.inPreferredConfig;
        }
        options.inBitmap = bitmapPool.getDirty(i, i2, config);
    }

    private static synchronized BitmapFactory.Options getDefaultOptions() {
        BitmapFactory.Options poll;
        synchronized (Downsampler.class) {
            Queue<BitmapFactory.Options> queue = OPTIONS_QUEUE;
            synchronized (queue) {
                poll = queue.poll();
            }
            if (poll == null) {
                poll = new BitmapFactory.Options();
                resetOptions(poll);
            }
        }
        return poll;
    }

    private static void releaseOptions(BitmapFactory.Options options) {
        resetOptions(options);
        Queue<BitmapFactory.Options> queue = OPTIONS_QUEUE;
        synchronized (queue) {
            queue.offer(options);
        }
    }

    private static void resetOptions(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }
}
