package com.yalantis.ucrop.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.WindowManager;
import com.tencent.p014av.ptt.PttError;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.task.BitmapLoadTask;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes3.dex */
public class BitmapLoadUtils {
    private static final String CONTENT_SCHEME = "content";
    private static final int MAX_BITMAP_SIZE = 104857600;
    private static final String TAG = "BitmapLoadUtils";

    public static int exifToDegrees(int i) {
        switch (i) {
            case 3:
            case 4:
                return CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public static int exifToTranslation(int i) {
        return (i == 2 || i == 7 || i == 4 || i == 5) ? -1 : 1;
    }

    public static void decodeBitmapInBackground(Context context, Uri uri, Uri uri2, int i, int i2, BitmapLoadCallback bitmapLoadCallback) {
        new BitmapLoadTask(context, uri, uri2, i, i2, bitmapLoadCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static Bitmap transformBitmap(Bitmap bitmap, Matrix matrix) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return !bitmap.sameAs(createBitmap) ? createBitmap : bitmap;
        } catch (OutOfMemoryError e) {
            Log.e(TAG, "transformBitmap: ", e);
            return bitmap;
        }
    }

    @Deprecated
    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            while (true) {
                if (i3 / i5 <= i2 && i4 / i5 <= i) {
                    break;
                }
                i5 *= 2;
            }
        }
        return i5;
    }

    public static int computeSize(int i, int i2) {
        if (i % 2 == 1) {
            i++;
        }
        if (i2 % 2 == 1) {
            i2++;
        }
        int max = Math.max(i, i2);
        float min = Math.min(i, i2) / max;
        if (min > 1.0f || min <= 0.5625d) {
            double d = min;
            if (d <= 0.5625d && d > 0.5d) {
                int i3 = max / PttError.GMESDK_UNINSTALLERROR;
                if (i3 == 0) {
                    return 1;
                }
                return i3;
            }
            return (int) Math.ceil(max / (1280.0d / d));
        }
        if (max < 1664) {
            return 1;
        }
        if (max < 4990) {
            return 2;
        }
        if (max <= 4990 || max >= 10240) {
            return max / PttError.GMESDK_UNINSTALLERROR;
        }
        return 4;
    }

    public static int[] getMaxImageSize(Context context, Uri uri) {
        InputStream openInputStream;
        if (FileUtils.isHasHttp(uri.toString())) {
            return new int[]{0, 0};
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
            options.inSampleSize = computeSize(options.outWidth, options.outHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }
        options.inJustDecodeBounds = false;
        Bitmap bitmap = null;
        boolean z = false;
        while (!z) {
            try {
                openInputStream = context.getContentResolver().openInputStream(uri);
            } catch (IOException e2) {
                Log.e(TAG, "doInBackground: ImageDecoder.createSource: ", e2);
            } catch (OutOfMemoryError e3) {
                Log.e(TAG, "doInBackground: BitmapFactory.decodeFileDescriptor: ", e3);
                options.inSampleSize *= 2;
            }
            try {
                bitmap = BitmapFactory.decodeStream(openInputStream, null, options);
                close(openInputStream);
                if (!checkSize(bitmap, options)) {
                    z = true;
                }
            } catch (Throwable th) {
                close(openInputStream);
                throw th;
                break;
            }
        }
        return bitmap == null ? new int[]{0, 0} : new int[]{bitmap.getWidth(), bitmap.getHeight()};
    }

    public static boolean checkSize(Bitmap bitmap, BitmapFactory.Options options) {
        if ((bitmap != null ? bitmap.getByteCount() : 0) <= getTotalMemory()) {
            return false;
        }
        options.inSampleSize *= 2;
        return true;
    }

    public static long getTotalMemory() {
        long j = Runtime.getRuntime().totalMemory();
        if (j > 104857600) {
            return 104857600L;
        }
        return j;
    }

    public static int getExifOrientation(Context context, Uri uri) {
        int i = 0;
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                return 0;
            }
            i = new ImageHeaderParser(openInputStream).getOrientation();
            close(openInputStream);
            return i;
        } catch (IOException e) {
            Log.e(TAG, "getExifOrientation: " + uri.toString(), e);
            return i;
        }
    }

    public static int calculateMaxBitmapSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getSize(point);
        }
        int sqrt = (int) Math.sqrt(Math.pow(point.x, 2.0d) + Math.pow(point.y, 2.0d));
        Canvas canvas = new Canvas();
        int min = Math.min(canvas.getMaximumBitmapWidth(), canvas.getMaximumBitmapHeight());
        if (min > 0) {
            sqrt = Math.min(sqrt, min);
        }
        int maxTextureSize = EglUtils.getMaxTextureSize();
        if (maxTextureSize > 0) {
            sqrt = Math.min(sqrt, maxTextureSize);
        }
        Log.d(TAG, "maxBitmapSize: " + sqrt);
        return sqrt;
    }

    public static void close(Closeable closeable) {
        if (closeable == null || !(closeable instanceof Closeable)) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    public static boolean hasContentScheme(Uri uri) {
        return uri != null && "content".equals(uri.getScheme());
    }
}
