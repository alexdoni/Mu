package com.luck.picture.lib.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.luck.picture.lib.app.PictureAppMaster;
import com.luck.picture.lib.basic.PictureContentResolver;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.MediaExtraInfo;
import com.luck.picture.lib.interfaces.OnCallbackListener;
import com.luck.picture.lib.thread.PictureThreadUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

/* loaded from: classes2.dex */
public class MediaUtils {
    public static final String QUERY_ARG_SQL_LIMIT = "android:query-arg-sql-limit";

    public static boolean isLongImage(int i, int i2) {
        return i > 0 && i2 > 0 && i2 > i * 3;
    }

    public static String getRealPathUri(long j, String str) {
        Uri contentUri;
        if (PictureMimeType.isHasImage(str)) {
            contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if (PictureMimeType.isHasVideo(str)) {
            contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else if (PictureMimeType.isHasAudio(str)) {
            contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        } else {
            contentUri = MediaStore.Files.getContentUri("external");
        }
        return ContentUris.withAppendedId(contentUri, j).toString();
    }

    public static String getMimeTypeFromMediaUrl(String str) {
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str).toLowerCase());
        if (TextUtils.isEmpty(mimeTypeFromExtension)) {
            mimeTypeFromExtension = getMimeType(new File(str));
        }
        return TextUtils.isEmpty(mimeTypeFromExtension) ? "image/jpeg" : mimeTypeFromExtension;
    }

    public static String getMimeTypeFromMediaHttpUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.toLowerCase().endsWith(PictureMimeType.JPG) || str.toLowerCase().endsWith(".jpeg")) {
            return "image/jpeg";
        }
        if (str.toLowerCase().endsWith(PictureMimeType.PNG)) {
            return PictureMimeType.PNG_Q;
        }
        if (str.toLowerCase().endsWith(".gif")) {
            return "image/gif";
        }
        if (str.toLowerCase().endsWith(".webp")) {
            return "image/webp";
        }
        if (str.toLowerCase().endsWith(PictureMimeType.BMP)) {
            return "image/bmp";
        }
        if (str.toLowerCase().endsWith(PictureMimeType.MP4)) {
            return "video/mp4";
        }
        if (str.toLowerCase().endsWith(PictureMimeType.AVI)) {
            return PictureMimeType.AVI_Q;
        }
        if (str.toLowerCase().endsWith(PictureMimeType.MP3)) {
            return "audio/mpeg";
        }
        if (str.toLowerCase().endsWith(PictureMimeType.AMR)) {
            return "audio/amr";
        }
        if (str.toLowerCase().endsWith(".m4a")) {
            return "audio/mpeg";
        }
        return null;
    }

    private static String getMimeType(File file) {
        return URLConnection.getFileNameMap().getContentTypeFor(file.getName());
    }

    public static String generateCameraFolderName(String str) {
        File file = new File(str);
        return file.getParentFile() != null ? file.getParentFile().getName() : PictureMimeType.CAMERA;
    }

    @Deprecated
    public static MediaExtraInfo getImageSize(String str) {
        InputStream fileInputStream;
        MediaExtraInfo mediaExtraInfo = new MediaExtraInfo();
        InputStream inputStream = null;
        try {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                if (PictureMimeType.isContent(str)) {
                    fileInputStream = PictureContentResolver.openInputStream(PictureAppMaster.getInstance().getAppContext(), Uri.parse(str));
                } else {
                    fileInputStream = new FileInputStream(str);
                }
                try {
                    BitmapFactory.decodeStream(fileInputStream, null, options);
                    mediaExtraInfo.setWidth(options.outWidth);
                    mediaExtraInfo.setHeight(options.outHeight);
                    PictureFileUtils.close(fileInputStream);
                } catch (Exception e) {
                    inputStream = fileInputStream;
                    e = e;
                    e.printStackTrace();
                    PictureFileUtils.close(inputStream);
                    return mediaExtraInfo;
                } catch (Throwable th) {
                    inputStream = fileInputStream;
                    th = th;
                    PictureFileUtils.close(inputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            return mediaExtraInfo;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static MediaExtraInfo getImageSize(Context context, String str) {
        InputStream fileInputStream;
        MediaExtraInfo mediaExtraInfo = new MediaExtraInfo();
        if (PictureMimeType.isHasHttp(str)) {
            return mediaExtraInfo;
        }
        InputStream inputStream = null;
        try {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                if (PictureMimeType.isContent(str)) {
                    fileInputStream = PictureContentResolver.openInputStream(context, Uri.parse(str));
                } else {
                    fileInputStream = new FileInputStream(str);
                }
                try {
                    BitmapFactory.decodeStream(fileInputStream, null, options);
                    mediaExtraInfo.setWidth(options.outWidth);
                    mediaExtraInfo.setHeight(options.outHeight);
                    PictureFileUtils.close(fileInputStream);
                } catch (Exception e) {
                    inputStream = fileInputStream;
                    e = e;
                    e.printStackTrace();
                    PictureFileUtils.close(inputStream);
                    return mediaExtraInfo;
                } catch (Throwable th) {
                    inputStream = fileInputStream;
                    th = th;
                    PictureFileUtils.close(inputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            return mediaExtraInfo;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void getImageSize(final Context context, final String str, final OnCallbackListener<MediaExtraInfo> onCallbackListener) {
        PictureThreadUtils.executeByIo(new PictureThreadUtils.SimpleTask<MediaExtraInfo>() { // from class: com.luck.picture.lib.utils.MediaUtils.1
            @Override // com.luck.picture.lib.thread.PictureThreadUtils.Task
            public MediaExtraInfo doInBackground() {
                return MediaUtils.getImageSize(context, str);
            }

            @Override // com.luck.picture.lib.thread.PictureThreadUtils.Task
            public void onSuccess(MediaExtraInfo mediaExtraInfo) {
                PictureThreadUtils.cancel(this);
                OnCallbackListener onCallbackListener2 = onCallbackListener;
                if (onCallbackListener2 != null) {
                    onCallbackListener2.onCall(mediaExtraInfo);
                }
            }
        });
    }

    public static void getVideoSize(final Context context, final String str, final OnCallbackListener<MediaExtraInfo> onCallbackListener) {
        PictureThreadUtils.executeByIo(new PictureThreadUtils.SimpleTask<MediaExtraInfo>() { // from class: com.luck.picture.lib.utils.MediaUtils.2
            @Override // com.luck.picture.lib.thread.PictureThreadUtils.Task
            public MediaExtraInfo doInBackground() {
                return MediaUtils.getVideoSize(context, str);
            }

            @Override // com.luck.picture.lib.thread.PictureThreadUtils.Task
            public void onSuccess(MediaExtraInfo mediaExtraInfo) {
                PictureThreadUtils.cancel(this);
                OnCallbackListener onCallbackListener2 = onCallbackListener;
                if (onCallbackListener2 != null) {
                    onCallbackListener2.onCall(mediaExtraInfo);
                }
            }
        });
    }

    public static MediaExtraInfo getVideoSize(Context context, String str) {
        String extractMetadata;
        int i;
        int i2;
        MediaExtraInfo mediaExtraInfo = new MediaExtraInfo();
        if (PictureMimeType.isHasHttp(str)) {
            return mediaExtraInfo;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                try {
                    if (PictureMimeType.isContent(str)) {
                        mediaMetadataRetriever.setDataSource(context, Uri.parse(str));
                    } else {
                        mediaMetadataRetriever.setDataSource(str);
                    }
                    extractMetadata = mediaMetadataRetriever.extractMetadata(24);
                } catch (Throwable th) {
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                mediaMetadataRetriever.release();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (!TextUtils.equals("90", extractMetadata) && !TextUtils.equals("270", extractMetadata)) {
            i2 = ValueOf.toInt(mediaMetadataRetriever.extractMetadata(18));
            i = ValueOf.toInt(mediaMetadataRetriever.extractMetadata(19));
            mediaExtraInfo.setWidth(i2);
            mediaExtraInfo.setHeight(i);
            mediaExtraInfo.setOrientation(extractMetadata);
            mediaExtraInfo.setDuration(ValueOf.toLong(mediaMetadataRetriever.extractMetadata(9)));
            mediaMetadataRetriever.release();
            return mediaExtraInfo;
        }
        int i3 = ValueOf.toInt(mediaMetadataRetriever.extractMetadata(18));
        i = i3;
        i2 = ValueOf.toInt(mediaMetadataRetriever.extractMetadata(19));
        mediaExtraInfo.setWidth(i2);
        mediaExtraInfo.setHeight(i);
        mediaExtraInfo.setOrientation(extractMetadata);
        mediaExtraInfo.setDuration(ValueOf.toLong(mediaMetadataRetriever.extractMetadata(9)));
        mediaMetadataRetriever.release();
        return mediaExtraInfo;
    }

    public static MediaExtraInfo getAudioSize(Context context, String str) {
        MediaExtraInfo mediaExtraInfo = new MediaExtraInfo();
        if (PictureMimeType.isHasHttp(str)) {
            return mediaExtraInfo;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                try {
                    if (PictureMimeType.isContent(str)) {
                        mediaMetadataRetriever.setDataSource(context, Uri.parse(str));
                    } else {
                        mediaMetadataRetriever.setDataSource(str);
                    }
                    mediaExtraInfo.setDuration(ValueOf.toLong(mediaMetadataRetriever.extractMetadata(9)));
                    mediaMetadataRetriever.release();
                } catch (Exception e) {
                    e.printStackTrace();
                    mediaMetadataRetriever.release();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return mediaExtraInfo;
        } catch (Throwable th) {
            try {
                mediaMetadataRetriever.release();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            throw th;
        }
    }

    public static void removeMedia(Context context, int i) {
        try {
            context.getApplicationContext().getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_id=?", new String[]{Long.toString(i)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getDCIMLastImageId(Context context, String str) {
        Cursor query;
        Cursor cursor = null;
        try {
            try {
                String[] strArr = {"%" + str + "%"};
                if (SdkVersionUtils.isR()) {
                    query = context.getApplicationContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, createQueryArgsBundle("_data like ?", strArr, 1, 0, "_id DESC"), null);
                } else {
                    query = context.getApplicationContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, "_data like ?", strArr, "_id DESC limit 1 offset 0");
                }
                cursor = query;
                if (cursor == null || cursor.getCount() <= 0 || !cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return -1;
                }
                int i = DateUtils.dateDiffer(cursor.getLong(cursor.getColumnIndex("date_added"))) <= 1 ? cursor.getInt(cursor.getColumnIndex(FileDownloadModel.f718ID)) : -1;
                if (cursor != null) {
                    cursor.close();
                }
                return i;
            } catch (Exception e) {
                e.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
                return -1;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0080, code lost:
    
        if (r2 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x008e, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x008b, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0089, code lost:
    
        if (r2 == null) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Long[] getPathMediaBucketId(android.content.Context r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "%"
            r1 = 2
            java.lang.Long[] r1 = new java.lang.Long[r1]
            r2 = 0
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r3 = 0
            r1[r3] = r2
            r4 = 1
            r1[r4] = r2
            r2 = 0
            java.lang.String r8 = "_data like ?"
            java.lang.String[] r9 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r5.append(r12)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r5.append(r0)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r12 = r5.toString()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r9[r3] = r12     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            boolean r12 = com.luck.picture.lib.utils.SdkVersionUtils.isR()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r0 = "external"
            if (r12 == 0) goto L42
            java.lang.String r12 = "_id DESC"
            android.os.Bundle r12 = createQueryArgsBundle(r8, r9, r4, r3, r12)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            android.content.ContentResolver r11 = r11.getContentResolver()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            android.net.Uri r0 = android.provider.MediaStore.Files.getContentUri(r0)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            android.database.Cursor r11 = r11.query(r0, r2, r12, r2)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            goto L51
        L42:
            java.lang.String r10 = "_id DESC limit 1 offset 0"
            android.content.ContentResolver r5 = r11.getContentResolver()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            android.net.Uri r6 = android.provider.MediaStore.Files.getContentUri(r0)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r7 = 0
            android.database.Cursor r11 = r5.query(r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
        L51:
            r2 = r11
            if (r2 == 0) goto L80
            int r11 = r2.getCount()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            if (r11 <= 0) goto L80
            boolean r11 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            if (r11 == 0) goto L80
            java.lang.String r11 = "_id"
            int r11 = r2.getColumnIndex(r11)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            long r11 = r2.getLong(r11)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r1[r3] = r11     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r11 = "bucket_id"
            int r11 = r2.getColumnIndex(r11)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            long r11 = r2.getLong(r11)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r1[r4] = r11     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
        L80:
            if (r2 == 0) goto L8e
            goto L8b
        L83:
            r11 = move-exception
            goto L8f
        L85:
            r11 = move-exception
            r11.printStackTrace()     // Catch: java.lang.Throwable -> L83
            if (r2 == 0) goto L8e
        L8b:
            r2.close()
        L8e:
            return r1
        L8f:
            if (r2 == 0) goto L94
            r2.close()
        L94:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.utils.MediaUtils.getPathMediaBucketId(android.content.Context, java.lang.String):java.lang.Long[]");
    }

    public static Bundle createQueryArgsBundle(String str, String[] strArr, int i, int i2, String str2) {
        Bundle bundle = new Bundle();
        if (Build.VERSION.SDK_INT >= 26) {
            bundle.putString("android:query-arg-sql-selection", str);
            bundle.putStringArray("android:query-arg-sql-selection-args", strArr);
            bundle.putString("android:query-arg-sql-sort-order", str2);
            if (SdkVersionUtils.isR()) {
                bundle.putString(QUERY_ARG_SQL_LIMIT, i + " offset " + i2);
            }
        }
        return bundle;
    }

    public static void getAsyncVideoThumbnail(final Context context, final String str, final OnCallbackListener<MediaExtraInfo> onCallbackListener) {
        PictureThreadUtils.executeByIo(new PictureThreadUtils.SimpleTask<MediaExtraInfo>() { // from class: com.luck.picture.lib.utils.MediaUtils.3
            @Override // com.luck.picture.lib.thread.PictureThreadUtils.Task
            public MediaExtraInfo doInBackground() {
                return MediaUtils.getVideoThumbnail(context, str);
            }

            @Override // com.luck.picture.lib.thread.PictureThreadUtils.Task
            public void onSuccess(MediaExtraInfo mediaExtraInfo) {
                PictureThreadUtils.cancel(this);
                OnCallbackListener onCallbackListener2 = onCallbackListener;
                if (onCallbackListener2 != null) {
                    onCallbackListener2.onCall(mediaExtraInfo);
                }
            }
        });
    }

    public static MediaExtraInfo getVideoThumbnail(Context context, String str) {
        FileOutputStream fileOutputStream;
        Bitmap bitmap;
        Bitmap frameAtTime;
        File file;
        FileOutputStream fileOutputStream2;
        MediaExtraInfo mediaExtraInfo = new MediaExtraInfo();
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            if (PictureMimeType.isContent(str)) {
                mediaMetadataRetriever.setDataSource(context, Uri.parse(str));
            } else {
                mediaMetadataRetriever.setDataSource(str);
            }
            frameAtTime = mediaMetadataRetriever.getFrameAtTime();
        } catch (IOException e) {
            e = e;
            fileOutputStream = null;
            bitmap = null;
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
            bitmap = null;
        }
        if (frameAtTime != null) {
            try {
            } catch (IOException e2) {
                e = e2;
                bitmap = frameAtTime;
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                bitmap = frameAtTime;
                fileOutputStream = null;
            }
            if (!frameAtTime.isRecycled()) {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    frameAtTime.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream2);
                    file = new File(PictureFileUtils.getVideoThumbnailDir(context), DateUtils.getCreateFileName("vid_") + "_thumb.jpg");
                    fileOutputStream2 = new FileOutputStream(file);
                } catch (IOException e3) {
                    e = e3;
                    bitmap = frameAtTime;
                    fileOutputStream = null;
                    byteArrayOutputStream = byteArrayOutputStream2;
                } catch (Throwable th3) {
                    th = th3;
                    bitmap = frameAtTime;
                    fileOutputStream = null;
                    byteArrayOutputStream = byteArrayOutputStream2;
                }
                try {
                    fileOutputStream2.write(byteArrayOutputStream2.toByteArray());
                    fileOutputStream2.flush();
                    mediaExtraInfo.setVideoThumbnail(file.getAbsolutePath());
                    mediaExtraInfo.setWidth(frameAtTime.getWidth());
                    mediaExtraInfo.setHeight(frameAtTime.getHeight());
                    byteArrayOutputStream = byteArrayOutputStream2;
                    PictureFileUtils.close(byteArrayOutputStream);
                    PictureFileUtils.close(fileOutputStream2);
                    if (frameAtTime != null && !frameAtTime.isRecycled()) {
                        frameAtTime.recycle();
                    }
                } catch (IOException e4) {
                    fileOutputStream = fileOutputStream2;
                    e = e4;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    bitmap = frameAtTime;
                    try {
                        e.printStackTrace();
                        PictureFileUtils.close(byteArrayOutputStream);
                        PictureFileUtils.close(fileOutputStream);
                        if (bitmap != null && !bitmap.isRecycled()) {
                            bitmap.recycle();
                        }
                        return mediaExtraInfo;
                    } catch (Throwable th4) {
                        th = th4;
                        PictureFileUtils.close(byteArrayOutputStream);
                        PictureFileUtils.close(fileOutputStream);
                        if (bitmap != null && !bitmap.isRecycled()) {
                            bitmap.recycle();
                        }
                        throw th;
                    }
                } catch (Throwable th5) {
                    byteArrayOutputStream = byteArrayOutputStream2;
                    bitmap = frameAtTime;
                    fileOutputStream = fileOutputStream2;
                    th = th5;
                    PictureFileUtils.close(byteArrayOutputStream);
                    PictureFileUtils.close(fileOutputStream);
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    throw th;
                }
                return mediaExtraInfo;
            }
        }
        fileOutputStream2 = null;
        PictureFileUtils.close(byteArrayOutputStream);
        PictureFileUtils.close(fileOutputStream2);
        if (frameAtTime != null) {
            frameAtTime.recycle();
        }
        return mediaExtraInfo;
    }

    public static void deleteUri(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str) || !PictureMimeType.isContent(str)) {
                return;
            }
            context.getContentResolver().delete(Uri.parse(str), null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
