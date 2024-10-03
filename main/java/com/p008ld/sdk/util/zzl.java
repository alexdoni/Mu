package com.p008ld.sdk.util;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.share.internal.ShareInternalUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.luck.picture.lib.config.PictureMimeType;
import com.p008ld.sdk.LDSdk;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

/* compiled from: LDUriUtils.java */
/* loaded from: classes2.dex */
public class zzl {
    public static File zza(Uri uri) {
        if (uri == null) {
            return null;
        }
        File zzb = zzb(uri);
        return zzb != null ? zzb : zzc(uri);
    }

    private static File zzb(Uri uri) {
        Uri uri2;
        File zza;
        boolean z;
        String str;
        File file;
        Log.d("UriUtils", uri.toString());
        String authority = uri.getAuthority();
        String scheme = uri.getScheme();
        String path = uri.getPath();
        if (Build.VERSION.SDK_INT >= 24 && path != null) {
            String[] strArr = {"/external/", "/external_path/"};
            for (int i = 0; i < 2; i++) {
                String str2 = strArr[i];
                if (path.startsWith(str2)) {
                    File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + path.replace(str2, RemoteSettings.FORWARD_SLASH_STRING));
                    if (file2.exists()) {
                        Log.d("UriUtils", uri.toString() + " -> " + str2);
                        return file2;
                    }
                }
            }
            if (path.startsWith("/files_path/")) {
                file = new File(LDSdk.getApp().getFilesDir().getAbsolutePath() + path.replace("/files_path/", RemoteSettings.FORWARD_SLASH_STRING));
            } else if (path.startsWith("/cache_path/")) {
                file = new File(LDSdk.getApp().getCacheDir().getAbsolutePath() + path.replace("/cache_path/", RemoteSettings.FORWARD_SLASH_STRING));
            } else if (path.startsWith("/external_files_path/")) {
                file = new File(LDSdk.getApp().getExternalFilesDir(null).getAbsolutePath() + path.replace("/external_files_path/", RemoteSettings.FORWARD_SLASH_STRING));
            } else if (path.startsWith("/external_cache_path/")) {
                file = new File(LDSdk.getApp().getExternalCacheDir().getAbsolutePath() + path.replace("/external_cache_path/", RemoteSettings.FORWARD_SLASH_STRING));
            } else {
                file = null;
            }
            if (file != null && file.exists()) {
                Log.d("UriUtils", uri.toString() + " -> " + path);
                return file;
            }
        }
        if (ShareInternalUtility.STAGING_PARAM.equals(scheme)) {
            if (path != null) {
                return new File(path);
            }
            Log.d("UriUtils", uri.toString() + " parse failed. -> 0");
            return null;
        }
        if (DocumentsContract.isDocumentUri(LDSdk.getApp(), uri)) {
            if ("com.android.externalstorage.documents".equals(authority)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(CertificateUtil.DELIMITER);
                String str3 = split[0];
                if ("primary".equalsIgnoreCase(str3)) {
                    return new File(Environment.getExternalStorageDirectory() + RemoteSettings.FORWARD_SLASH_STRING + split[1]);
                }
                StorageManager storageManager = (StorageManager) LDSdk.getApp().getSystemService("storage");
                try {
                    Class<?> cls = Class.forName("android.os.storage.StorageVolume");
                    Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
                    Method method2 = cls.getMethod("getUuid", new Class[0]);
                    Method method3 = cls.getMethod("getState", new Class[0]);
                    Method method4 = cls.getMethod("getPath", new Class[0]);
                    Method method5 = cls.getMethod("isPrimary", new Class[0]);
                    Method method6 = cls.getMethod("isEmulated", new Class[0]);
                    Object invoke = method.invoke(storageManager, new Object[0]);
                    int length = Array.getLength(invoke);
                    int i2 = 0;
                    while (i2 < length) {
                        Object obj = Array.get(invoke, i2);
                        Object obj2 = invoke;
                        if (!"mounted".equals(method3.invoke(obj, new Object[0])) && !"mounted_ro".equals(method3.invoke(obj, new Object[0]))) {
                            z = false;
                            if (z && ((!((Boolean) method5.invoke(obj, new Object[0])).booleanValue() || !((Boolean) method6.invoke(obj, new Object[0])).booleanValue()) && (str = (String) method2.invoke(obj, new Object[0])) != null && str.equals(str3))) {
                                return new File(method4.invoke(obj, new Object[0]) + RemoteSettings.FORWARD_SLASH_STRING + split[1]);
                            }
                            i2++;
                            invoke = obj2;
                        }
                        z = true;
                        if (z) {
                            return new File(method4.invoke(obj, new Object[0]) + RemoteSettings.FORWARD_SLASH_STRING + split[1]);
                        }
                        i2++;
                        invoke = obj2;
                    }
                } catch (Exception e) {
                    Log.d("UriUtils", uri.toString() + " parse failed. " + e.toString() + " -> 1_0");
                }
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_0");
                return null;
            }
            if ("com.android.providers.downloads.documents".equals(authority)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (TextUtils.isEmpty(documentId)) {
                    Log.d("UriUtils", uri.toString() + " parse failed(id is null). -> 1_1");
                    return null;
                }
                if (documentId.startsWith("raw:")) {
                    return new File(documentId.substring(4));
                }
                if (documentId.startsWith("msf:")) {
                    documentId = documentId.split(CertificateUtil.DELIMITER)[1];
                }
                try {
                    long parseLong = Long.parseLong(documentId);
                    String[] strArr2 = {"content://downloads/public_downloads", "content://downloads/all_downloads", "content://downloads/my_downloads"};
                    for (int i3 = 0; i3 < 3; i3++) {
                        try {
                            zza = zza(ContentUris.withAppendedId(Uri.parse(strArr2[i3]), parseLong), "1_1");
                        } catch (Exception unused) {
                        }
                        if (zza != null) {
                            return zza;
                        }
                    }
                    Log.d("UriUtils", uri.toString() + " parse failed. -> 1_1");
                    return null;
                } catch (Exception unused2) {
                    return null;
                }
            }
            if ("com.android.providers.media.documents".equals(authority)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(CertificateUtil.DELIMITER);
                String str4 = split2[0];
                if (PictureMimeType.MIME_TYPE_PREFIX_IMAGE.equals(str4)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str4)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else {
                    if (!PictureMimeType.MIME_TYPE_PREFIX_AUDIO.equals(str4)) {
                        Log.d("UriUtils", uri.toString() + " parse failed. -> 1_2");
                        return null;
                    }
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return zza(uri2, "_id=?", new String[]{split2[1]}, "1_2");
            }
            if (FirebaseAnalytics.Param.CONTENT.equals(scheme)) {
                return zza(uri, "1_3");
            }
            Log.d("UriUtils", uri.toString() + " parse failed. -> 1_4");
            return null;
        }
        if (FirebaseAnalytics.Param.CONTENT.equals(scheme)) {
            return zza(uri, "2");
        }
        Log.d("UriUtils", uri.toString() + " parse failed. -> 3");
        return null;
    }

    private static File zza(Uri uri, String str) {
        return zza(uri, null, null, str);
    }

    private static File zza(Uri uri, String str, String[] strArr, String str2) {
        if ("com.google.android.apps.photos.content".equals(uri.getAuthority())) {
            if (!TextUtils.isEmpty(uri.getLastPathSegment())) {
                return new File(uri.getLastPathSegment());
            }
        } else if ("com.tencent.mtt.fileprovider".equals(uri.getAuthority())) {
            String path = uri.getPath();
            if (!TextUtils.isEmpty(path)) {
                return new File(Environment.getExternalStorageDirectory(), path.substring(10, path.length()));
            }
        } else if ("com.huawei.hidisk.fileprovider".equals(uri.getAuthority())) {
            String path2 = uri.getPath();
            if (!TextUtils.isEmpty(path2)) {
                return new File(path2.replace("/root", ""));
            }
        }
        Cursor query = LDSdk.getApp().getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
        try {
            if (query == null) {
                Log.d("UriUtils", uri.toString() + " parse failed(cursor is null). -> " + str2);
                return null;
            }
            if (!query.moveToFirst()) {
                Log.d("UriUtils", uri.toString() + " parse failed(moveToFirst return false). -> " + str2);
                return null;
            }
            int columnIndex = query.getColumnIndex("_data");
            if (columnIndex > -1) {
                return new File(query.getString(columnIndex));
            }
            Log.d("UriUtils", uri.toString() + " parse failed(columnIndex: " + columnIndex + " is wrong). -> " + str2);
            return null;
        } catch (Exception unused) {
            Log.d("UriUtils", uri.toString() + " parse failed. -> " + str2);
            return null;
        } finally {
            query.close();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x005d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.io.File zzc(android.net.Uri r7) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "UriUtils"
            java.lang.String r2 = "copyUri2Cache() called"
            android.util.Log.d(r1, r2)
            r1 = 0
            android.content.Context r2 = com.p008ld.sdk.LDSdk.getApp()     // Catch: java.lang.Throwable -> L47 java.io.FileNotFoundException -> L49
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L47 java.io.FileNotFoundException -> L49
            java.io.InputStream r7 = r2.openInputStream(r7)     // Catch: java.lang.Throwable -> L47 java.io.FileNotFoundException -> L49
            java.io.File r2 = new java.io.File     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L59
            android.content.Context r3 = com.p008ld.sdk.LDSdk.getApp()     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L59
            java.io.File r3 = r3.getCacheDir()     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L59
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L59
            r4.<init>(r0)     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L59
            long r5 = java.lang.System.currentTimeMillis()     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L59
            r4.append(r5)     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L59
            java.lang.String r0 = r4.toString()     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L59
            r2.<init>(r3, r0)     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L59
            java.lang.String r0 = r2.getAbsolutePath()     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L59
            com.p008ld.sdk.util.zzg.zza(r0, r7)     // Catch: java.io.FileNotFoundException -> L45 java.lang.Throwable -> L59
            if (r7 == 0) goto L44
            r7.close()     // Catch: java.io.IOException -> L40
            goto L44
        L40:
            r7 = move-exception
            r7.printStackTrace()
        L44:
            return r2
        L45:
            r0 = move-exception
            goto L4b
        L47:
            r0 = move-exception
            goto L5b
        L49:
            r0 = move-exception
            r7 = r1
        L4b:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L59
            if (r7 == 0) goto L58
            r7.close()     // Catch: java.io.IOException -> L54
            goto L58
        L54:
            r7 = move-exception
            r7.printStackTrace()
        L58:
            return r1
        L59:
            r0 = move-exception
            r1 = r7
        L5b:
            if (r1 == 0) goto L65
            r1.close()     // Catch: java.io.IOException -> L61
            goto L65
        L61:
            r7 = move-exception
            r7.printStackTrace()
        L65:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.util.zzl.zzc(android.net.Uri):java.io.File");
    }
}
