package com.oversea.ab_firstarea.camearAndphoto;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.share.internal.ShareInternalUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.SelectMimeType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes.dex */
public class Lxhw_PhotoUtils {
    private static final String TAG = "PhotoUtils";

    public static void takePicture(Activity activity, Uri uri, int i) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (uri != null) {
            intent.putExtra("output", uri);
        }
        activity.startActivityForResult(intent, i);
    }

    public static void openPic(Activity activity, int i) {
        Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, SelectMimeType.SYSTEM_IMAGE);
        activity.startActivityForResult(intent, i);
    }

    public static void cropImageUri(Activity activity, Uri uri, Uri uri2, int i, int i2, int i3, int i4, int i5) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(1);
        }
        intent.setDataAndType(uri, SelectMimeType.SYSTEM_IMAGE);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", i);
        intent.putExtra("aspectY", i2);
        intent.putExtra("outputX", i3);
        intent.putExtra("outputY", i4);
        intent.putExtra("scale", true);
        intent.putExtra("output", uri2);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, i5);
    }

    public static File getFile(String str) {
        try {
            return new File(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Bitmap getBitmapFromUri(Uri uri, Context context) {
        try {
            Log.v(TAG, "**getBitmapFromUri 0");
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            Log.v(TAG, "**getBitmapFromUri 1");
            return bitmap;
        } catch (FileNotFoundException e) {
            Log.v(TAG, "**getBitmapFromUri FileNotFoundException");
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            Log.v(TAG, "**getBitmapFromUri IOException");
            e2.printStackTrace();
            return null;
        }
    }

    public static String getPath(Context context, Uri uri) {
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(CertificateUtil.DELIMITER);
                if ("primary".equalsIgnoreCase(split[0])) {
                    return "" + Environment.getExternalStorageDirectory() + RemoteSettings.FORWARD_SLASH_STRING + split[1];
                }
            } else {
                if (isDownloadsDocument(uri)) {
                    return "" + getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
                }
                if (isMediaDocument(uri)) {
                    String[] split2 = DocumentsContract.getDocumentId(uri).split(CertificateUtil.DELIMITER);
                    String str = split2[0];
                    if (PictureMimeType.MIME_TYPE_PREFIX_IMAGE.equals(str)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if (PictureMimeType.MIME_TYPE_PREFIX_AUDIO.equals(str)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return "" + getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
                }
            }
        } else {
            if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
                return "" + getDataColumn(context, uri, null, null);
            }
            if (ShareInternalUtility.STAGING_PARAM.equalsIgnoreCase(uri.getScheme())) {
                return "" + uri.getPath();
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x002d, code lost:
    
        if (r8 == null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0029, code lost:
    
        if (r8 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0032, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002f, code lost:
    
        r8.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getDataColumn(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L2c
            r6 = 0
            r2 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L2c
            if (r8 == 0) goto L29
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.Throwable -> L2d
            if (r9 == 0) goto L29
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> L2d
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.Throwable -> L2d
            if (r8 == 0) goto L28
            r8.close()
        L28:
            return r9
        L29:
            if (r8 == 0) goto L32
            goto L2f
        L2c:
            r8 = r7
        L2d:
            if (r8 == 0) goto L32
        L2f:
            r8.close()
        L32:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oversea.ab_firstarea.camearAndphoto.Lxhw_PhotoUtils.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
