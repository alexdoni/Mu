package com.yalantis.ucrop.util;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* loaded from: classes3.dex */
public class ImageHeaderParser {
    private static final int EXIF_MAGIC_NUMBER = 65496;
    private static final int EXIF_SEGMENT_TYPE = 225;
    private static final int INTEL_TIFF_MAGIC_NUMBER = 18761;
    private static final int MARKER_EOI = 217;
    private static final int MOTOROLA_TIFF_MAGIC_NUMBER = 19789;
    private static final int ORIENTATION_TAG_TYPE = 274;
    private static final int SEGMENT_SOS = 218;
    private static final int SEGMENT_START_ID = 255;
    private static final String TAG = "ImageHeaderParser";
    public static final int UNKNOWN_ORIENTATION = -1;
    private final Reader reader;
    private static final String JPEG_EXIF_SEGMENT_PREAMBLE = "Exif\u0000\u0000";
    private static final byte[] JPEG_EXIF_SEGMENT_PREAMBLE_BYTES = JPEG_EXIF_SEGMENT_PREAMBLE.getBytes(Charset.forName("UTF-8"));
    private static final int[] BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public interface Reader {
        int getUInt16() throws IOException;

        short getUInt8() throws IOException;

        int read(byte[] bArr, int i) throws IOException;

        long skip(long j) throws IOException;
    }

    private static int calcTagOffset(int i, int i2) {
        return i + 2 + (i2 * 12);
    }

    private static boolean handles(int i) {
        return (i & EXIF_MAGIC_NUMBER) == EXIF_MAGIC_NUMBER || i == MOTOROLA_TIFF_MAGIC_NUMBER || i == INTEL_TIFF_MAGIC_NUMBER;
    }

    public ImageHeaderParser(InputStream inputStream) {
        this.reader = new StreamReader(inputStream);
    }

    public int getOrientation() throws IOException {
        int uInt16 = this.reader.getUInt16();
        if (!handles(uInt16)) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Parser doesn't handle magic number: " + uInt16);
            }
            return -1;
        }
        int moveToExifSegmentAndGetLength = moveToExifSegmentAndGetLength();
        if (moveToExifSegmentAndGetLength == -1) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to parse exif segment length, or exif segment not found");
            }
            return -1;
        }
        return parseExifSegment(new byte[moveToExifSegmentAndGetLength], moveToExifSegmentAndGetLength);
    }

    private int parseExifSegment(byte[] bArr, int i) throws IOException {
        int read = this.reader.read(bArr, i);
        if (read != i) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unable to read exif segment data, length: " + i + ", actually read: " + read);
            }
            return -1;
        }
        if (hasJpegExifPreamble(bArr, i)) {
            return parseExifSegment(new RandomAccessReader(bArr, i));
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Missing jpeg exif preamble");
        }
        return -1;
    }

    private boolean hasJpegExifPreamble(byte[] bArr, int i) {
        boolean z = bArr != null && i > JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length;
        if (z) {
            int i2 = 0;
            while (true) {
                byte[] bArr2 = JPEG_EXIF_SEGMENT_PREAMBLE_BYTES;
                if (i2 >= bArr2.length) {
                    break;
                }
                if (bArr[i2] != bArr2[i2]) {
                    return false;
                }
                i2++;
            }
        }
        return z;
    }

    private int moveToExifSegmentAndGetLength() throws IOException {
        short uInt8;
        int uInt16;
        long j;
        long skip;
        do {
            short uInt82 = this.reader.getUInt8();
            if (uInt82 != 255) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Unknown segmentId=" + ((int) uInt82));
                }
                return -1;
            }
            uInt8 = this.reader.getUInt8();
            if (uInt8 == SEGMENT_SOS) {
                return -1;
            }
            if (uInt8 == MARKER_EOI) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            uInt16 = this.reader.getUInt16() - 2;
            if (uInt8 == EXIF_SEGMENT_TYPE) {
                return uInt16;
            }
            j = uInt16;
            skip = this.reader.skip(j);
        } while (skip == j);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Unable to skip enough data, type: " + ((int) uInt8) + ", wanted to skip: " + uInt16 + ", but actually skipped: " + skip);
        }
        return -1;
    }

    private static int parseExifSegment(RandomAccessReader randomAccessReader) {
        ByteOrder byteOrder;
        short int16 = randomAccessReader.getInt16(6);
        if (int16 == MOTOROLA_TIFF_MAGIC_NUMBER) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (int16 == INTEL_TIFF_MAGIC_NUMBER) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unknown endianness = " + ((int) int16));
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        randomAccessReader.order(byteOrder);
        int int32 = randomAccessReader.getInt32(10) + 6;
        short int162 = randomAccessReader.getInt16(int32);
        for (int i = 0; i < int162; i++) {
            int calcTagOffset = calcTagOffset(int32, i);
            short int163 = randomAccessReader.getInt16(calcTagOffset);
            if (int163 == ORIENTATION_TAG_TYPE) {
                short int164 = randomAccessReader.getInt16(calcTagOffset + 2);
                if (int164 < 1 || int164 > 12) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Got invalid format code = " + ((int) int164));
                    }
                } else {
                    int int322 = randomAccessReader.getInt32(calcTagOffset + 4);
                    if (int322 < 0) {
                        if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, "Negative tiff component count");
                        }
                    } else {
                        if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, "Got tagIndex=" + i + " tagType=" + ((int) int163) + " formatCode=" + ((int) int164) + " componentCount=" + int322);
                        }
                        int i2 = int322 + BYTES_PER_FORMAT[int164];
                        if (i2 > 4) {
                            if (Log.isLoggable(TAG, 3)) {
                                Log.d(TAG, "Got byte count > 4, not orientation, continuing, formatCode=" + ((int) int164));
                            }
                        } else {
                            int i3 = calcTagOffset + 8;
                            if (i3 < 0 || i3 > randomAccessReader.length()) {
                                if (Log.isLoggable(TAG, 3)) {
                                    Log.d(TAG, "Illegal tagValueOffset=" + i3 + " tagType=" + ((int) int163));
                                }
                            } else if (i2 < 0 || i2 + i3 > randomAccessReader.length()) {
                                if (Log.isLoggable(TAG, 3)) {
                                    Log.d(TAG, "Illegal number of bytes for TI tag data tagType=" + ((int) int163));
                                }
                            } else {
                                return randomAccessReader.getInt16(i3);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class RandomAccessReader {
        private final ByteBuffer data;

        public RandomAccessReader(byte[] bArr, int i) {
            this.data = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i);
        }

        public void order(ByteOrder byteOrder) {
            this.data.order(byteOrder);
        }

        public int length() {
            return this.data.remaining();
        }

        public int getInt32(int i) {
            return this.data.getInt(i);
        }

        public short getInt16(int i) {
            return this.data.getShort(i);
        }
    }

    /* loaded from: classes3.dex */
    private static class StreamReader implements Reader {

        /* renamed from: is */
        private final InputStream f1933is;

        public StreamReader(InputStream inputStream) {
            this.f1933is = inputStream;
        }

        @Override // com.yalantis.ucrop.util.ImageHeaderParser.Reader
        public int getUInt16() throws IOException {
            return ((this.f1933is.read() << 8) & 65280) | (this.f1933is.read() & 255);
        }

        @Override // com.yalantis.ucrop.util.ImageHeaderParser.Reader
        public short getUInt8() throws IOException {
            return (short) (this.f1933is.read() & 255);
        }

        @Override // com.yalantis.ucrop.util.ImageHeaderParser.Reader
        public long skip(long j) throws IOException {
            if (j < 0) {
                return 0L;
            }
            long j2 = j;
            while (j2 > 0) {
                long skip = this.f1933is.skip(j2);
                if (skip <= 0) {
                    if (this.f1933is.read() == -1) {
                        break;
                    }
                    skip = 1;
                }
                j2 -= skip;
            }
            return j - j2;
        }

        @Override // com.yalantis.ucrop.util.ImageHeaderParser.Reader
        public int read(byte[] bArr, int i) throws IOException {
            int i2 = i;
            while (i2 > 0) {
                int read = this.f1933is.read(bArr, i - i2, i2);
                if (read == -1) {
                    break;
                }
                i2 -= read;
            }
            return i - i2;
        }
    }

    public static void copyExif(ExifInterface exifInterface, int i, int i2, String str) {
        try {
            copyExifAttributes(exifInterface, new ExifInterface(str), i, i2);
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0037 -> B:11:0x003e). Please report as a decompilation issue!!! */
    public static void copyExif(Context context, int i, int i2, Uri uri, String str) {
        if (context == null) {
            Log.d(TAG, "context is null");
            return;
        }
        InputStream inputStream = null;
        try {
            try {
                try {
                    inputStream = context.getContentResolver().openInputStream(uri);
                    copyExifAttributes(new ExifInterface(inputStream), new ExifInterface(str), i, i2);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Throwable th) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            Log.d(TAG, e.getMessage(), e);
                        }
                    }
                    throw th;
                }
            } catch (IOException e2) {
                Log.d(TAG, e2.getMessage(), e2);
            }
        } catch (IOException e3) {
            Log.d(TAG, e3.getMessage(), e3);
            if (inputStream == null) {
            } else {
                inputStream.close();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0084 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void copyExif(android.content.Context r4, int r5, int r6, android.net.Uri r7, android.net.Uri r8) {
        /*
            java.lang.String r0 = "ImageHeaderParser"
            if (r4 != 0) goto La
            java.lang.String r4 = "context is null"
            android.util.Log.d(r0, r4)
            return
        La:
            r1 = 0
            android.content.ContentResolver r2 = r4.getContentResolver()     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
            java.io.InputStream r7 = r2.openInputStream(r7)     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
            androidx.exifinterface.media.ExifInterface r2 = new androidx.exifinterface.media.ExifInterface     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L46
            r2.<init>(r7)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L46
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L46
            java.lang.String r3 = "rw"
            android.os.ParcelFileDescriptor r1 = r4.openFileDescriptor(r8, r3)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L46
            androidx.exifinterface.media.ExifInterface r4 = new androidx.exifinterface.media.ExifInterface     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L46
            java.io.FileDescriptor r8 = r1.getFileDescriptor()     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L46
            r4.<init>(r8)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L46
            copyExifAttributes(r2, r4, r5, r6)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L46
            if (r7 == 0) goto L3c
            r7.close()     // Catch: java.io.IOException -> L34
            goto L3c
        L34:
            r4 = move-exception
            java.lang.String r5 = r4.getMessage()
            android.util.Log.d(r0, r5, r4)
        L3c:
            if (r1 == 0) goto L72
            r1.close()     // Catch: java.io.IOException -> L6a
            goto L72
        L42:
            r4 = move-exception
            r5 = r1
            r1 = r7
            goto L74
        L46:
            r4 = move-exception
            r5 = r1
            r1 = r7
            goto L4f
        L4a:
            r4 = move-exception
            r5 = r1
            goto L74
        L4d:
            r4 = move-exception
            r5 = r1
        L4f:
            java.lang.String r6 = r4.getMessage()     // Catch: java.lang.Throwable -> L73
            android.util.Log.d(r0, r6, r4)     // Catch: java.lang.Throwable -> L73
            if (r1 == 0) goto L64
            r1.close()     // Catch: java.io.IOException -> L5c
            goto L64
        L5c:
            r4 = move-exception
            java.lang.String r6 = r4.getMessage()
            android.util.Log.d(r0, r6, r4)
        L64:
            if (r5 == 0) goto L72
            r5.close()     // Catch: java.io.IOException -> L6a
            goto L72
        L6a:
            r4 = move-exception
            java.lang.String r5 = r4.getMessage()
            android.util.Log.d(r0, r5, r4)
        L72:
            return
        L73:
            r4 = move-exception
        L74:
            if (r1 == 0) goto L82
            r1.close()     // Catch: java.io.IOException -> L7a
            goto L82
        L7a:
            r6 = move-exception
            java.lang.String r7 = r6.getMessage()
            android.util.Log.d(r0, r7, r6)
        L82:
            if (r5 == 0) goto L90
            r5.close()     // Catch: java.io.IOException -> L88
            goto L90
        L88:
            r5 = move-exception
            java.lang.String r6 = r5.getMessage()
            android.util.Log.d(r0, r6, r5)
        L90:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yalantis.ucrop.util.ImageHeaderParser.copyExif(android.content.Context, int, int, android.net.Uri, android.net.Uri):void");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0038 -> B:11:0x003f). Please report as a decompilation issue!!! */
    public static void copyExif(Context context, ExifInterface exifInterface, int i, int i2, Uri uri) {
        if (context == null) {
            Log.d(TAG, "context is null");
            return;
        }
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            try {
                try {
                    parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "rw");
                    copyExifAttributes(exifInterface, new ExifInterface(parcelFileDescriptor.getFileDescriptor()), i, i2);
                    if (parcelFileDescriptor != null) {
                        parcelFileDescriptor.close();
                    }
                } catch (Throwable th) {
                    if (parcelFileDescriptor != null) {
                        try {
                            parcelFileDescriptor.close();
                        } catch (IOException e) {
                            Log.d(TAG, e.getMessage(), e);
                        }
                    }
                    throw th;
                }
            } catch (IOException e2) {
                Log.d(TAG, e2.getMessage());
                if (parcelFileDescriptor == null) {
                } else {
                    parcelFileDescriptor.close();
                }
            }
        } catch (IOException e3) {
            Log.d(TAG, e3.getMessage(), e3);
        }
    }

    private static void copyExifAttributes(ExifInterface exifInterface, ExifInterface exifInterface2, int i, int i2) throws IOException {
        String[] strArr = {ExifInterface.TAG_F_NUMBER, ExifInterface.TAG_DATETIME, ExifInterface.TAG_DATETIME_DIGITIZED, ExifInterface.TAG_EXPOSURE_TIME, ExifInterface.TAG_FLASH, ExifInterface.TAG_FOCAL_LENGTH, ExifInterface.TAG_GPS_ALTITUDE, ExifInterface.TAG_GPS_ALTITUDE_REF, ExifInterface.TAG_GPS_DATESTAMP, ExifInterface.TAG_GPS_LATITUDE, ExifInterface.TAG_GPS_LATITUDE_REF, ExifInterface.TAG_GPS_LONGITUDE, ExifInterface.TAG_GPS_LONGITUDE_REF, ExifInterface.TAG_GPS_PROCESSING_METHOD, ExifInterface.TAG_GPS_TIMESTAMP, ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY, ExifInterface.TAG_MAKE, ExifInterface.TAG_MODEL, ExifInterface.TAG_SUBSEC_TIME, ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, ExifInterface.TAG_WHITE_BALANCE};
        for (int i3 = 0; i3 < 22; i3++) {
            String str = strArr[i3];
            String attribute = exifInterface.getAttribute(str);
            if (!TextUtils.isEmpty(attribute)) {
                exifInterface2.setAttribute(str, attribute);
            }
        }
        exifInterface2.setAttribute(ExifInterface.TAG_IMAGE_WIDTH, String.valueOf(i));
        exifInterface2.setAttribute(ExifInterface.TAG_IMAGE_LENGTH, String.valueOf(i2));
        exifInterface2.setAttribute(ExifInterface.TAG_ORIENTATION, "0");
        exifInterface2.saveAttributes();
    }
}
