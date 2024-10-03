package top.zibin.luban;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: classes3.dex */
public enum Checker {
    SINGLE;

    private static final String JPG = ".jpg";
    private static final String TAG = "Luban";
    private final byte[] JPEG_SIGNATURE = {-1, -40, -1};

    Checker() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isJPG(InputStream inputStream) {
        return isJPG(toByteArray(inputStream));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getOrientation(InputStream inputStream) {
        return getOrientation(toByteArray(inputStream));
    }

    private boolean isJPG(byte[] bArr) {
        if (bArr == null || bArr.length < 3) {
            return false;
        }
        return Arrays.equals(this.JPEG_SIGNATURE, new byte[]{bArr[0], bArr[1], bArr[2]});
    }

    public static boolean isContent(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("content://");
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x006a, code lost:
    
        if (r3 <= 8) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006c, code lost:
    
        r2 = pack(r12, r1, 4, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0073, code lost:
    
        if (r2 == 1229531648) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0078, code lost:
    
        if (r2 == 1296891946) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x007a, code lost:
    
        android.util.Log.e(top.zibin.luban.Checker.TAG, "Invalid byte order");
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x007f, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0080, code lost:
    
        if (r2 != 1229531648) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0082, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0085, code lost:
    
        r4 = pack(r12, r1 + 4, 4, r2) + 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x008e, code lost:
    
        if (r4 < 10) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0090, code lost:
    
        if (r4 <= r3) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0093, code lost:
    
        r1 = r1 + r4;
        r3 = r3 - r4;
        r4 = pack(r12, r1 - 2, 2, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x009b, code lost:
    
        r9 = r4 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x009d, code lost:
    
        if (r4 <= 0) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00a1, code lost:
    
        if (r3 < 12) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a9, code lost:
    
        if (pack(r12, r1, 2, r2) != 274) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00ca, code lost:
    
        r1 = r1 + 12;
        r3 = r3 - 12;
        r4 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00ab, code lost:
    
        r12 = pack(r12, r1 + 8, 2, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00b0, code lost:
    
        if (r12 == 1) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00b3, code lost:
    
        if (r12 == 3) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00b6, code lost:
    
        if (r12 == 6) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00b8, code lost:
    
        if (r12 == 8) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00ba, code lost:
    
        android.util.Log.e(top.zibin.luban.Checker.TAG, "Unsupported orientation");
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00bf, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00c0, code lost:
    
        return 270;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00c3, code lost:
    
        return 90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00c6, code lost:
    
        return org.spongycastle.crypto.tls.CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00c9, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00d0, code lost:
    
        android.util.Log.e(top.zibin.luban.Checker.TAG, "Invalid offset");
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00d5, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0084, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00d6, code lost:
    
        android.util.Log.e(top.zibin.luban.Checker.TAG, "Orientation not found");
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00db, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0066, code lost:
    
        r3 = 0;
        r1 = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int getOrientation(byte[] r12) {
        /*
            Method dump skipped, instructions count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: top.zibin.luban.Checker.getOrientation(byte[]):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String extSuffix(InputStreamProvider inputStreamProvider) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStreamProvider.open(), null, options);
            return options.outMimeType.replace("image/", ".");
        } catch (Exception unused) {
            return ".jpg";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean needCompress(int i, String str) {
        if (i <= 0) {
            return true;
        }
        File file = new File(str);
        return file.exists() && file.length() > (((long) i) << 10);
    }

    private int pack(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        if (z) {
            i += i2 - 1;
            i3 = -1;
        } else {
            i3 = 1;
        }
        int i4 = 0;
        while (true) {
            int i5 = i2 - 1;
            if (i2 <= 0) {
                return i4;
            }
            i4 = (bArr[i] & 255) | (i4 << 8);
            i += i3;
            i2 = i5;
        }
    }

    private byte[] toByteArray(InputStream inputStream) {
        if (inputStream == null) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                try {
                    int read = inputStream.read(bArr, 0, 4096);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (IOException unused) {
                        }
                    }
                } catch (Exception unused2) {
                    byte[] bArr2 = new byte[0];
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException unused3) {
                    }
                    return bArr2;
                }
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused4) {
                }
                throw th;
            }
        }
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
