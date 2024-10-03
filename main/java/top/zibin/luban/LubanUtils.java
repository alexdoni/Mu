package top.zibin.luban;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
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
import java.util.Locale;

/* loaded from: classes3.dex */
public class LubanUtils {
    private static final String TAG = "Luban";

    public static String getPath(Context context, Uri uri) {
        Context applicationContext = context.getApplicationContext();
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(applicationContext, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(CertificateUtil.DELIMITER);
                if (!"primary".equalsIgnoreCase(split[0])) {
                    return "";
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    return applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + RemoteSettings.FORWARD_SLASH_STRING + split[1];
                }
                return Environment.getExternalStorageDirectory() + RemoteSettings.FORWARD_SLASH_STRING + split[1];
            }
            if (isDownloadsDocument(uri)) {
                return getDataColumn(applicationContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(DocumentsContract.getDocumentId(uri))), null, null);
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

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
}
