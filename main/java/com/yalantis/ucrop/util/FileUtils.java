package com.yalantis.ucrop.util;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.share.internal.ShareInternalUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.luck.picture.lib.config.PictureMimeType;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;

/* loaded from: classes3.dex */
public class FileUtils {
    public static final String GIF = ".gif";
    public static final String JPEG = ".jpeg";
    private static final String TAG = "FileUtils";
    public static final String WEBP = ".webp";

    /* renamed from: sf */
    private static final SimpleDateFormat f1932sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private FileUtils() {
    }

    public static boolean isContent(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("content://");
    }

    public static Uri replaceOutputUri(Context context, boolean z, Uri uri, Uri uri2) {
        try {
            String postfixDefaultEmpty = getPostfixDefaultEmpty(context, z, uri);
            if (TextUtils.isEmpty(postfixDefaultEmpty)) {
                return uri2;
            }
            String uri3 = isContent(uri2.toString()) ? uri2.toString() : uri2.getPath();
            String replace = uri3.replace(uri3.substring(uri3.lastIndexOf(".")), postfixDefaultEmpty);
            return isContent(replace) ? Uri.parse(replace) : Uri.fromFile(new File(replace));
        } catch (Exception e) {
            e.printStackTrace();
            return uri2;
        }
    }

    public static String getPostfixDefaultJPEG(Context context, boolean z, Uri uri) {
        if (z) {
            String mimeTypeFromMediaContentUri = getMimeTypeFromMediaContentUri(context, uri);
            if (isGif(mimeTypeFromMediaContentUri)) {
                return ".gif";
            }
            if (isWebp(mimeTypeFromMediaContentUri)) {
                return ".webp";
            }
        }
        return ".jpeg";
    }

    public static String getPostfixDefaultEmpty(Context context, boolean z, Uri uri) {
        if (z) {
            String mimeTypeFromMediaContentUri = getMimeTypeFromMediaContentUri(context, uri);
            if (isGif(mimeTypeFromMediaContentUri)) {
                return ".gif";
            }
            if (isWebp(mimeTypeFromMediaContentUri)) {
                return ".webp";
            }
        }
        return "";
    }

    public static String getInputPath(Uri uri) {
        return (isContent(uri.toString()) || isHasHttp(uri.toString())) ? uri.toString() : uri.getPath();
    }

    public static boolean isUrlHasVideo(String str) {
        return !TextUtils.isEmpty(str) && str.toLowerCase().endsWith(PictureMimeType.MP4);
    }

    public static boolean isHasVideo(String str) {
        return str != null && str.startsWith("video");
    }

    public static boolean isHasAudio(String str) {
        return str != null && str.startsWith(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
    }

    public static boolean isHasHttp(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("http") || str.startsWith("https") || str.startsWith("/http") || str.startsWith("/https");
    }

    public static boolean isGif(String str) {
        return str != null && (str.equals("image/gif") || str.equals("image/GIF"));
    }

    public static boolean isWebp(String str) {
        return str != null && (str.equals("image/webp") || str.equals("image/WEBP"));
    }

    public static String getMimeTypeFromMediaContentUri(Context context, Uri uri) {
        if (uri.getScheme().equals(FirebaseAnalytics.Param.CONTENT)) {
            return context.getContentResolver().getType(uri);
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase());
    }

    public static String getCreateFileName(String str) {
        return str + f1932sf.format(Long.valueOf(System.currentTimeMillis()));
    }

    public static String getCreateFileName() {
        return f1932sf.format(Long.valueOf(System.currentTimeMillis()));
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

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
    
        if (r8 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x002b, code lost:
    
        if (r8 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x002d, code lost:
    
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0051, code lost:
    
        return null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0056  */
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
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L31 java.lang.IllegalArgumentException -> L33
            r6 = 0
            r2 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L31 java.lang.IllegalArgumentException -> L33
            if (r8 == 0) goto L2b
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.IllegalArgumentException -> L29 java.lang.Throwable -> L52
            if (r9 == 0) goto L2b
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.IllegalArgumentException -> L29 java.lang.Throwable -> L52
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.IllegalArgumentException -> L29 java.lang.Throwable -> L52
            if (r8 == 0) goto L28
            r8.close()
        L28:
            return r9
        L29:
            r9 = move-exception
            goto L35
        L2b:
            if (r8 == 0) goto L51
        L2d:
            r8.close()
            goto L51
        L31:
            r9 = move-exception
            goto L54
        L33:
            r9 = move-exception
            r8 = r7
        L35:
            java.lang.String r10 = "FileUtils"
            java.util.Locale r11 = java.util.Locale.getDefault()     // Catch: java.lang.Throwable -> L52
            java.lang.String r0 = "getDataColumn: _data - [%s]"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L52
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L52
            r2 = 0
            r1[r2] = r9     // Catch: java.lang.Throwable -> L52
            java.lang.String r9 = java.lang.String.format(r11, r0, r1)     // Catch: java.lang.Throwable -> L52
            android.util.Log.i(r10, r9)     // Catch: java.lang.Throwable -> L52
            if (r8 == 0) goto L51
            goto L2d
        L51:
            return r7
        L52:
            r9 = move-exception
            r7 = r8
        L54:
            if (r7 == 0) goto L59
            r7.close()
        L59:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yalantis.ucrop.util.FileUtils.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
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

    public static void copyFile(String str, String str2) throws IOException {
        FileChannel fileChannel;
        FileChannel channel;
        if (str.equalsIgnoreCase(str2)) {
            return;
        }
        FileChannel fileChannel2 = null;
        try {
            channel = new FileInputStream(new File(str)).getChannel();
        } catch (Throwable th) {
            th = th;
            fileChannel = null;
        }
        try {
            fileChannel2 = new FileOutputStream(new File(str2)).getChannel();
            channel.transferTo(0L, channel.size(), fileChannel2);
            if (channel != null) {
                channel.close();
            }
            if (fileChannel2 != null) {
                fileChannel2.close();
            }
        } catch (Throwable th2) {
            th = th2;
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
    }

    public static void copyFile(Context context, Uri uri, Uri uri2) throws IOException {
        OutputStream outputStream;
        InputStream openInputStream;
        if (uri.equals(uri2)) {
            return;
        }
        InputStream inputStream = null;
        try {
            openInputStream = context.getContentResolver().openInputStream(uri);
        } catch (Throwable th) {
            th = th;
            outputStream = null;
        }
        try {
            OutputStream openOutputStream = context.getContentResolver().openOutputStream(uri2);
            if ((openInputStream instanceof FileInputStream) && (openOutputStream instanceof FileOutputStream)) {
                FileChannel channel = ((FileInputStream) openInputStream).getChannel();
                channel.transferTo(0L, channel.size(), ((FileOutputStream) openOutputStream).getChannel());
                if (openInputStream != null) {
                    openInputStream.close();
                }
                if (openOutputStream != null) {
                    openOutputStream.close();
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("The input or output URI don't represent a file. uCrop requires then to represent files in order to work properly.");
        } catch (Throwable th2) {
            th = th2;
            inputStream = openInputStream;
            outputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            throw th;
        }
    }

    public static boolean writeFileFromIS(InputStream inputStream, OutputStream outputStream) {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
            try {
                bufferedOutputStream = new BufferedOutputStream(outputStream);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = bufferedInputStream2.read(bArr);
                        if (read != -1) {
                            outputStream.write(bArr, 0, read);
                        } else {
                            outputStream.flush();
                            BitmapLoadUtils.close(bufferedInputStream2);
                            BitmapLoadUtils.close(bufferedOutputStream);
                            return true;
                        }
                    }
                } catch (Exception e) {
                    e = e;
                    bufferedInputStream = bufferedInputStream2;
                    try {
                        e.printStackTrace();
                        BitmapLoadUtils.close(bufferedInputStream);
                        BitmapLoadUtils.close(bufferedOutputStream);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        BitmapLoadUtils.close(bufferedInputStream);
                        BitmapLoadUtils.close(bufferedOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedInputStream = bufferedInputStream2;
                    BitmapLoadUtils.close(bufferedInputStream);
                    BitmapLoadUtils.close(bufferedOutputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                bufferedOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream = null;
            }
        } catch (Exception e3) {
            e = e3;
            bufferedOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedOutputStream = null;
        }
    }
}
