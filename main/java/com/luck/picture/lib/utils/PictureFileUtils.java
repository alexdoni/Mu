package com.luck.picture.lib.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.share.internal.ShareInternalUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.luck.picture.lib.config.FileSizeUnit;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.SelectMimeType;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Locale;
import java.util.Objects;

/* loaded from: classes2.dex */
public class PictureFileUtils {
    private static final int BYTE_SIZE = 1024;
    private static final String POSTFIX_AMR = ".amr";
    private static final String POSTFIX_JPG = ".jpg";
    private static final String POSTFIX_MP4 = ".mp4";
    static final String TAG = "PictureFileUtils";

    public static File createCameraFile(Context context, int i, String str, String str2, String str3) {
        return createMediaFile(context, i, str, str2, str3);
    }

    private static File createMediaFile(Context context, int i, String str, String str2, String str3) {
        return createOutFile(context, i, str, str2, str3);
    }

    private static File createOutFile(Context context, int i, String str, String str2, String str3) {
        File file;
        File rootDirFile;
        Context applicationContext = context.getApplicationContext();
        if (TextUtils.isEmpty(str3)) {
            if (TextUtils.equals("mounted", Environment.getExternalStorageState())) {
                rootDirFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                file = new File(rootDirFile.getAbsolutePath() + File.separator + PictureMimeType.CAMERA + File.separator);
            } else {
                rootDirFile = getRootDirFile(applicationContext, i);
                file = new File(rootDirFile.getAbsolutePath() + File.separator);
            }
            if (!rootDirFile.exists()) {
                rootDirFile.mkdirs();
            }
        } else {
            File file2 = new File(str3);
            if (!((File) Objects.requireNonNull(file2.getParentFile())).exists()) {
                file2.getParentFile().mkdirs();
            }
            file = file2;
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        boolean isEmpty = TextUtils.isEmpty(str);
        if (i == 2) {
            if (isEmpty) {
                str = DateUtils.getCreateFileName("VID_") + ".mp4";
            }
            return new File(file, str);
        }
        if (i == 3) {
            if (isEmpty) {
                str = DateUtils.getCreateFileName("AUD_") + ".amr";
            }
            return new File(file, str);
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = ".jpg";
        }
        if (isEmpty) {
            str = DateUtils.getCreateFileName("IMG_") + str2;
        }
        return new File(file, str);
    }

    private static File getRootDirFile(Context context, int i) {
        return new File(FileDirMap.getFileDirPath(context, i));
    }

    private PictureFileUtils() {
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

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                cursor = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            } catch (IllegalArgumentException e) {
                Log.i(TAG, String.format(Locale.getDefault(), "getDataColumn: _data - [%s]", e.getMessage()));
                if (cursor == null) {
                    return "";
                }
            }
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor == null) {
                    return "";
                }
                cursor.close();
                return "";
            }
            String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
            if (cursor != null) {
                cursor.close();
            }
            return string;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static String getPath(Context context, Uri uri) {
        Context applicationContext = context.getApplicationContext();
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(applicationContext, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(CertificateUtil.DELIMITER);
                if (!"primary".equalsIgnoreCase(split[0])) {
                    return "";
                }
                if (SdkVersionUtils.isQ()) {
                    return applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + RemoteSettings.FORWARD_SLASH_STRING + split[1];
                }
                return Environment.getExternalStorageDirectory() + RemoteSettings.FORWARD_SLASH_STRING + split[1];
            }
            if (isDownloadsDocument(uri)) {
                return getDataColumn(applicationContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), ValueOf.toLong(DocumentsContract.getDocumentId(uri))), null, null);
            }
            if (!isMediaDocument(uri)) {
                return "";
            }
            String[] split2 = DocumentsContract.getDocumentId(uri).split(CertificateUtil.DELIMITER);
            String str = split2[0];
            if (PictureMimeType.MIME_TYPE_PREFIX_IMAGE.equals(str)) {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(str)) {
                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if (PictureMimeType.MIME_TYPE_PREFIX_AUDIO.equals(str)) {
                uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return getDataColumn(applicationContext, uri2, "_id=?", new String[]{split2[1]});
        }
        if (!FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
            return ShareInternalUtility.STAGING_PARAM.equalsIgnoreCase(uri.getScheme()) ? uri.getPath() : "";
        }
        if (isGooglePhotosUri(uri)) {
            return uri.getLastPathSegment();
        }
        return getDataColumn(applicationContext, uri, null, null);
    }

    public static void copyFile(String str, String str2) {
        FileChannel fileChannel;
        if (str.equalsIgnoreCase(str2)) {
            return;
        }
        FileChannel fileChannel2 = null;
        try {
            FileChannel channel = new FileInputStream(str).getChannel();
            try {
                fileChannel2 = new FileOutputStream(str2).getChannel();
                channel.transferTo(0L, channel.size(), fileChannel2);
                close(channel);
                close(fileChannel2);
            } catch (Exception e) {
                e = e;
                FileChannel fileChannel3 = fileChannel2;
                fileChannel2 = channel;
                fileChannel = fileChannel3;
                try {
                    e.printStackTrace();
                    close(fileChannel2);
                    close(fileChannel);
                } catch (Throwable th) {
                    th = th;
                    close(fileChannel2);
                    close(fileChannel);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                FileChannel fileChannel4 = fileChannel2;
                fileChannel2 = channel;
                fileChannel = fileChannel4;
                close(fileChannel2);
                close(fileChannel);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            fileChannel = null;
        } catch (Throwable th3) {
            th = th3;
            fileChannel = null;
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
                            close(bufferedInputStream2);
                            close(bufferedOutputStream);
                            return true;
                        }
                    }
                } catch (Exception e) {
                    e = e;
                    bufferedInputStream = bufferedInputStream2;
                    try {
                        e.printStackTrace();
                        close(bufferedInputStream);
                        close(bufferedOutputStream);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        close(bufferedInputStream);
                        close(bufferedOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedInputStream = bufferedInputStream2;
                    close(bufferedInputStream);
                    close(bufferedOutputStream);
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

    public static String getVideoThumbnailDir(Context context) {
        File file = new File(context.getExternalFilesDir("").getAbsolutePath(), "VideoThumbnail");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + File.separator;
    }

    @Deprecated
    public static void deleteCacheDirFile(Context context, int i) {
        File[] listFiles;
        File externalFilesDir = context.getExternalFilesDir(i == SelectMimeType.ofImage() ? Environment.DIRECTORY_PICTURES : Environment.DIRECTORY_MOVIES);
        if (externalFilesDir == null || (listFiles = externalFilesDir.listFiles()) == null) {
            return;
        }
        for (File file : listFiles) {
            if (file.isFile()) {
                file.delete();
            }
        }
    }

    @Deprecated
    public static void deleteAllCacheDirFile(Context context) {
        File[] listFiles;
        File[] listFiles2;
        File[] listFiles3;
        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir != null && (listFiles3 = externalFilesDir.listFiles()) != null) {
            for (File file : listFiles3) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
        File externalFilesDir2 = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        if (externalFilesDir2 != null && (listFiles2 = externalFilesDir2.listFiles()) != null) {
            for (File file2 : listFiles2) {
                if (file2.isFile()) {
                    file2.delete();
                }
            }
        }
        File externalFilesDir3 = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        if (externalFilesDir3 == null || (listFiles = externalFilesDir3.listFiles()) == null) {
            return;
        }
        for (File file3 : listFiles) {
            if (file3.isFile()) {
                file3.delete();
            }
        }
    }

    public static Uri parUri(Context context, File file) {
        String str = context.getPackageName() + ".luckProvider";
        if (Build.VERSION.SDK_INT > 23) {
            return FileProvider.getUriForFile(context, str, file);
        }
        return Uri.fromFile(file);
    }

    public static String createFilePath(Context context, String str, String str2) {
        File rootDirFile;
        String str3;
        String lastSourceSuffix = PictureMimeType.getLastSourceSuffix(str);
        if (PictureMimeType.isHasVideo(str)) {
            rootDirFile = getRootDirFile(context, 2);
            str3 = "VID_";
        } else if (PictureMimeType.isHasAudio(str)) {
            rootDirFile = getRootDirFile(context, 3);
            str3 = "AUD_";
        } else {
            rootDirFile = getRootDirFile(context, 1);
            str3 = "IMG_";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(rootDirFile.getPath());
        sb.append(File.separator);
        if (TextUtils.isEmpty(str2)) {
            str2 = DateUtils.getCreateFileName(str3) + lastSourceSuffix;
        }
        sb.append(str2);
        return sb.toString();
    }

    public static boolean isImageFileExists(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 2;
        BitmapFactory.decodeFile(str, options);
        return options.outWidth > 0 && options.outHeight > 0;
    }

    public static boolean isFileExists(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }

    public static String formatFileSize(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteSize shouldn't be less than zero!");
        }
        if (j < 1024) {
            Object format = String.format("%.2f", Double.valueOf(j));
            double d = ValueOf.toDouble(format);
            long round = Math.round(d);
            StringBuilder sb = new StringBuilder();
            if (round - d == 0.0d) {
                format = Long.valueOf(round);
            }
            sb.append(format);
            sb.append("B");
            return sb.toString();
        }
        if (j < 1048576) {
            Object format2 = String.format("%.2f", Double.valueOf(j / 1024.0d));
            double d2 = ValueOf.toDouble(format2);
            long round2 = Math.round(d2);
            StringBuilder sb2 = new StringBuilder();
            if (round2 - d2 == 0.0d) {
                format2 = Long.valueOf(round2);
            }
            sb2.append(format2);
            sb2.append("KB");
            return sb2.toString();
        }
        if (j < FileSizeUnit.f727GB) {
            Object format3 = String.format("%.2f", Double.valueOf(j / 1048576.0d));
            double d3 = ValueOf.toDouble(format3);
            long round3 = Math.round(d3);
            StringBuilder sb3 = new StringBuilder();
            if (round3 - d3 == 0.0d) {
                format3 = Long.valueOf(round3);
            }
            sb3.append(format3);
            sb3.append("MB");
            return sb3.toString();
        }
        Object format4 = String.format("%.2f", Double.valueOf(j / 1.073741824E9d));
        double d4 = ValueOf.toDouble(format4);
        long round4 = Math.round(d4);
        StringBuilder sb4 = new StringBuilder();
        if (round4 - d4 == 0.0d) {
            format4 = Long.valueOf(round4);
        }
        sb4.append(format4);
        sb4.append("GB");
        return sb4.toString();
    }

    public static String formatAccurateUnitFileSize(long j) {
        double d;
        String str;
        if (j < 0) {
            throw new IllegalArgumentException("byteSize shouldn't be less than zero!");
        }
        if (j < 1000) {
            d = j;
            str = "";
        } else if (j < 1000000) {
            d = j / 1000.0d;
            str = "KB";
        } else if (j < 1000000000) {
            d = j / 1000000.0d;
            str = "MB";
        } else {
            d = j / 1.0E9d;
            str = "GB";
        }
        Object format = String.format(new Locale("zh"), "%.2f", Double.valueOf(d));
        StringBuilder sb = new StringBuilder();
        if (Math.round(ValueOf.toDouble(format)) - ValueOf.toDouble(format) == 0.0d) {
            format = Long.valueOf(Math.round(ValueOf.toDouble(format)));
        }
        sb.append(format);
        sb.append(str);
        return sb.toString();
    }

    public static void close(Closeable closeable) {
        if (closeable instanceof Closeable) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }
}
