package com.oversea.ab_firstarea.utils;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.share.internal.ShareInternalUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.luck.picture.lib.config.PictureMimeType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;

/* loaded from: classes.dex */
public class FileUtils {
    private static final String TAG = "FileUtils";

    /* renamed from: sf */
    private static SimpleDateFormat f786sf = new SimpleDateFormat("yyyyMMdd_HHmmssSS");

    private FileUtils() {
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0051, code lost:
    
        if (r8 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x002e, code lost:
    
        if (r8 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0030, code lost:
    
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0054, code lost:
    
        return null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0059  */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getDataColumn(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L34 java.lang.IllegalArgumentException -> L36
            r6 = 0
            r8 = r6
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Throwable -> L34 java.lang.IllegalArgumentException -> L36
            r2 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L34 java.lang.IllegalArgumentException -> L36
            if (r8 == 0) goto L2e
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.IllegalArgumentException -> L2c java.lang.Throwable -> L55
            if (r9 == 0) goto L2e
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.IllegalArgumentException -> L2c java.lang.Throwable -> L55
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.IllegalArgumentException -> L2c java.lang.Throwable -> L55
            if (r8 == 0) goto L2b
            r8.close()
        L2b:
            return r9
        L2c:
            r9 = move-exception
            goto L38
        L2e:
            if (r8 == 0) goto L54
        L30:
            r8.close()
            goto L54
        L34:
            r9 = move-exception
            goto L57
        L36:
            r9 = move-exception
            r8 = r7
        L38:
            java.lang.String r10 = "FileUtils"
            java.util.Locale r11 = java.util.Locale.getDefault()     // Catch: java.lang.Throwable -> L55
            java.lang.String r0 = "getDataColumn: _data - [%s]"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L55
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L55
            r2 = 0
            r1[r2] = r9     // Catch: java.lang.Throwable -> L55
            java.lang.String r9 = java.lang.String.format(r11, r0, r1)     // Catch: java.lang.Throwable -> L55
            android.util.Log.i(r10, r9)     // Catch: java.lang.Throwable -> L55
            if (r8 == 0) goto L54
            goto L30
        L54:
            return r7
        L55:
            r9 = move-exception
            r7 = r8
        L57:
            if (r7 == 0) goto L5c
            r7.close()
        L5c:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oversea.ab_firstarea.utils.FileUtils.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static String getPath(Context context, Uri uri) {
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(CertificateUtil.DELIMITER);
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + RemoteSettings.FORWARD_SLASH_STRING + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (!TextUtils.isEmpty(documentId)) {
                    try {
                        return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), null, null);
                    } catch (NumberFormatException e) {
                        Log.i(TAG, e.getMessage());
                        return null;
                    }
                }
            } else if (isMediaDocument(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(CertificateUtil.DELIMITER);
                String str = split2[0];
                if (PictureMimeType.MIME_TYPE_PREFIX_IMAGE.equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if (PictureMimeType.MIME_TYPE_PREFIX_AUDIO.equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
            }
        } else {
            if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, null, null);
            }
            if (ShareInternalUtility.STAGING_PARAM.equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    public static boolean copyFile(FileInputStream fileInputStream, String str) throws IOException {
        FileChannel fileChannel;
        FileChannel fileChannel2;
        if (fileInputStream == null) {
            return false;
        }
        FileChannel fileChannel3 = null;
        try {
            FileChannel channel = fileInputStream.getChannel();
            try {
                fileChannel3 = new FileOutputStream(new File(str)).getChannel();
                channel.transferTo(0L, channel.size(), fileChannel3);
                channel.close();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (channel != null) {
                    channel.close();
                }
                if (fileChannel3 == null) {
                    return true;
                }
                fileChannel3.close();
                return true;
            } catch (Exception unused) {
                fileChannel2 = fileChannel3;
                fileChannel3 = channel;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileChannel3 != null) {
                    fileChannel3.close();
                }
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                return false;
            } catch (Throwable th) {
                th = th;
                fileChannel = fileChannel3;
                fileChannel3 = channel;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileChannel3 != null) {
                    fileChannel3.close();
                }
                if (fileChannel != null) {
                    fileChannel.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
            fileChannel2 = null;
        } catch (Throwable th2) {
            th = th2;
            fileChannel = null;
        }
    }

    public static boolean copyFile(FileInputStream fileInputStream, FileOutputStream fileOutputStream) throws IOException {
        FileChannel fileChannel;
        FileChannel fileChannel2;
        FileChannel channel;
        if (fileInputStream == null) {
            return false;
        }
        FileChannel fileChannel3 = null;
        try {
            channel = fileInputStream.getChannel();
        } catch (Exception unused) {
            fileChannel2 = null;
        } catch (Throwable th) {
            th = th;
            fileChannel = null;
        }
        try {
            fileChannel3 = fileOutputStream.getChannel();
            channel.transferTo(0L, channel.size(), fileChannel3);
            channel.close();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (channel != null) {
                channel.close();
            }
            if (fileChannel3 == null) {
                return true;
            }
            fileChannel3.close();
            return true;
        } catch (Exception unused2) {
            fileChannel2 = fileChannel3;
            fileChannel3 = channel;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileChannel3 != null) {
                fileChannel3.close();
            }
            if (fileChannel2 != null) {
                fileChannel2.close();
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileChannel = fileChannel3;
            fileChannel3 = channel;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileChannel3 != null) {
                fileChannel3.close();
            }
            if (fileChannel != null) {
                fileChannel.close();
            }
            throw th;
        }
    }

    public static void copyFile(String str, String str2) throws IOException {
        FileChannel fileChannel;
        if (str.equalsIgnoreCase(str2)) {
            return;
        }
        FileChannel fileChannel2 = null;
        try {
            FileChannel channel = new FileInputStream(new File(str)).getChannel();
            try {
                fileChannel2 = new FileOutputStream(new File(str2)).getChannel();
                channel.transferTo(0L, channel.size(), fileChannel2);
                channel.close();
                if (channel != null) {
                    channel.close();
                }
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
            } catch (Throwable th) {
                th = th;
                FileChannel fileChannel3 = fileChannel2;
                fileChannel2 = channel;
                fileChannel = fileChannel3;
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                if (fileChannel != null) {
                    fileChannel.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileChannel = null;
        }
    }

    public static String getCreateFileName(String str) {
        return str + f786sf.format(Long.valueOf(System.currentTimeMillis()));
    }

    public static String getCreateFileName() {
        return f786sf.format(Long.valueOf(System.currentTimeMillis()));
    }

    public static String rename(String str) {
        return str.substring(0, str.lastIndexOf(".")) + "_" + getCreateFileName() + str.substring(str.lastIndexOf("."));
    }
}
