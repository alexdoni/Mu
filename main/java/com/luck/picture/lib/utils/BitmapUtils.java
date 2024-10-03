package com.luck.picture.lib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
import com.luck.picture.lib.basic.PictureContentResolver;
import com.luck.picture.lib.config.PictureMimeType;
import com.tencent.p014av.ptt.PttError;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes2.dex */
public class BitmapUtils {
    private static final int ARGB_8888_MEMORY_BYTE = 4;
    private static final int MAX_BITMAP_SIZE = 104857600;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void rotateImage(android.content.Context r6, java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 200
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.utils.BitmapUtils.rotateImage(android.content.Context, java.lang.String):void");
    }

    public static Bitmap rotatingImage(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private static void saveBitmapFile(Bitmap bitmap, FileOutputStream fileOutputStream) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
                    fileOutputStream.write(byteArrayOutputStream2.toByteArray());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    PictureFileUtils.close(fileOutputStream);
                    PictureFileUtils.close(byteArrayOutputStream2);
                } catch (Exception e) {
                    e = e;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    e.printStackTrace();
                    PictureFileUtils.close(fileOutputStream);
                    PictureFileUtils.close(byteArrayOutputStream);
                } catch (Throwable th) {
                    th = th;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    PictureFileUtils.close(fileOutputStream);
                    PictureFileUtils.close(byteArrayOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static int readPictureDegree(Context context, String str) {
        ExifInterface exifInterface;
        InputStream inputStream = null;
        try {
            if (PictureMimeType.isContent(str)) {
                inputStream = PictureContentResolver.openInputStream(context, Uri.parse(str));
                exifInterface = new ExifInterface(inputStream);
            } else {
                exifInterface = new ExifInterface(str);
            }
            int attributeInt = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                PictureFileUtils.close(inputStream);
                return CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
            }
            if (attributeInt == 6) {
                PictureFileUtils.close(inputStream);
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            PictureFileUtils.close(inputStream);
            return 270;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            PictureFileUtils.close(inputStream);
        }
    }

    public static int[] getMaxImageSize(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return new int[]{-1, -1};
        }
        int computeSize = computeSize(i, i2);
        long totalMemory = getTotalMemory();
        int i3 = -1;
        boolean z = false;
        int i4 = -1;
        while (!z) {
            i3 = i / computeSize;
            i4 = i2 / computeSize;
            if (i3 * i4 * 4 > totalMemory) {
                computeSize *= 2;
            } else {
                z = true;
            }
        }
        return new int[]{i3, i4};
    }

    public static long getTotalMemory() {
        long j = Runtime.getRuntime().totalMemory();
        if (j > 104857600) {
            return 104857600L;
        }
        return j;
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
}
