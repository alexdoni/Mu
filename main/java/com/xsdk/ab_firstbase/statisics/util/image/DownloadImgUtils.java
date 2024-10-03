package com.xsdk.ab_firstbase.statisics.util.image;

/* loaded from: classes3.dex */
public class DownloadImgUtils {
    /* JADX WARN: Removed duplicated region for block: B:46:0x0059 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean downloadImgByUrl(java.lang.String r4, java.io.File r5) {
        /*
            r0 = 0
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L41
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L41
            java.net.URLConnection r4 = r2.openConnection()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L41
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L41
            java.io.InputStream r4 = r4.getInputStream()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L41
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L3a
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L3a
            r5 = 512(0x200, float:7.175E-43)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L34
        L1a:
            int r1 = r4.read(r5)     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L34
            r3 = -1
            if (r1 == r3) goto L25
            r2.write(r5, r0, r1)     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L34
            goto L1a
        L25:
            r2.flush()     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L34
            if (r4 == 0) goto L2d
            r4.close()     // Catch: java.io.IOException -> L2d
        L2d:
            r2.close()     // Catch: java.io.IOException -> L30
        L30:
            r4 = 1
            return r4
        L32:
            r5 = move-exception
            goto L38
        L34:
            r5 = move-exception
            goto L3c
        L36:
            r5 = move-exception
            r2 = r1
        L38:
            r1 = r4
            goto L52
        L3a:
            r5 = move-exception
            r2 = r1
        L3c:
            r1 = r4
            goto L43
        L3e:
            r5 = move-exception
            r2 = r1
            goto L52
        L41:
            r5 = move-exception
            r2 = r1
        L43:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L51
            if (r1 == 0) goto L4b
            r1.close()     // Catch: java.io.IOException -> L4b
        L4b:
            if (r2 == 0) goto L50
            r2.close()     // Catch: java.io.IOException -> L50
        L50:
            return r0
        L51:
            r5 = move-exception
        L52:
            if (r1 == 0) goto L57
            r1.close()     // Catch: java.io.IOException -> L57
        L57:
            if (r2 == 0) goto L5c
            r2.close()     // Catch: java.io.IOException -> L5c
        L5c:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xsdk.ab_firstbase.statisics.util.image.DownloadImgUtils.downloadImgByUrl(java.lang.String, java.io.File):boolean");
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0056: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:23:0x0056 */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0059 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap downloadImgByUrl(java.lang.String r4, android.widget.ImageView r5) {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            java.net.URLConnection r4 = r1.openConnection()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            java.io.InputStream r2 = r4.getInputStream()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            int r2 = r1.available()     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            r1.mark(r2)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            android.graphics.BitmapFactory$Options r2 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            r2.<init>()     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            r3 = 1
            r2.inJustDecodeBounds = r3     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            android.graphics.BitmapFactory.decodeStream(r1, r0, r2)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            com.xsdk.ab_firstbase.statisics.util.image.ImageSizeUtil$ImageSize r5 = com.xsdk.ab_firstbase.statisics.util.image.ImageSizeUtil.getImageViewSize(r5)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            int r3 = r5.width     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            int r5 = r5.height     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            int r5 = com.xsdk.ab_firstbase.statisics.util.image.ImageSizeUtil.caculateInSampleSize(r2, r3, r5)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            r2.inSampleSize = r5     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            r5 = 0
            r2.inJustDecodeBounds = r5     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            r1.reset()     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            android.graphics.Bitmap r5 = android.graphics.BitmapFactory.decodeStream(r1, r0, r2)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            r4.disconnect()     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L55
            r1.close()     // Catch: java.io.IOException -> L45
        L45:
            return r5
        L46:
            r4 = move-exception
            goto L4c
        L48:
            r4 = move-exception
            goto L57
        L4a:
            r4 = move-exception
            r1 = r0
        L4c:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L55
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.io.IOException -> L54
        L54:
            return r0
        L55:
            r4 = move-exception
            r0 = r1
        L57:
            if (r0 == 0) goto L5c
            r0.close()     // Catch: java.io.IOException -> L5c
        L5c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xsdk.ab_firstbase.statisics.util.image.DownloadImgUtils.downloadImgByUrl(java.lang.String, android.widget.ImageView):android.graphics.Bitmap");
    }
}
