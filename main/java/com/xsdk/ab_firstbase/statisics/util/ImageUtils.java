package com.xsdk.ab_firstbase.statisics.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.share.internal.ShareInternalUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.luck.picture.lib.permissions.PermissionConfig;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

/* loaded from: classes3.dex */
public class ImageUtils {
    static String FILE_DIR = "/xhw";
    static String FILE_NAME = "";
    private static String KFFILE_NAME = "/kf.jpg";
    static final String TAG = "ImageUtils";
    private static String X_DIR = "/XFimage";
    static String imageFilePath = "";
    private static String sharedpreferencespath = "xsharep.xml";
    static Bitmap urlBitmap;

    public static String getSharedpreferencespath() {
        return sharedpreferencespath;
    }

    public static void setSharedpreferencespath(String str) {
        sharedpreferencespath = str;
    }

    public static void setfiledir(String str) {
        FILE_DIR = str;
    }

    private static void setFileName(Context context) {
        if (TextUtils.isEmpty(FILE_NAME)) {
            FILE_NAME = "/xat" + Util.getPackageName(context) + ".ini";
            StringBuilder sb = new StringBuilder("FILE_NAME=");
            sb.append(FILE_NAME);
            Log.i("FILE_NAME", sb.toString());
        }
    }

    public static void setImageDir_filename(String str, String str2) {
        X_DIR = str;
        KFFILE_NAME = str2;
    }

    public static String getRealPath(Activity activity, Uri uri) {
        String str = null;
        if (uri == null) {
            return null;
        }
        if (uri.getScheme().toString().compareTo(FirebaseAnalytics.Param.CONTENT) == 0) {
            Cursor query = activity.getContentResolver().query(uri, null, null, null, null);
            if (query == null) {
                return null;
            }
            try {
                if (!query.moveToFirst()) {
                    return null;
                }
                try {
                    String string = query.getString(query.getColumnIndexOrThrow("_data"));
                    query.close();
                    str = string;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    query.close();
                }
                return str;
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        if (uri.getScheme().compareTo(ShareInternalUtility.STAGING_PARAM) == 0) {
            return uri.getPath();
        }
        return null;
    }

    public static Uri bitmapToUri(Activity activity) {
        if (urlBitmap == null) {
            Log.v("bitmapToUri", "urlBitmap null");
            return null;
        }
        Log.v("bitmapToUri", "urlBitmap have");
        return Uri.parse(MediaStore.Images.Media.insertImage(activity.getContentResolver(), urlBitmap, "IMG" + Calendar.getInstance().getTime(), (String) null));
    }

    public static void urlToBitMap(final Handler handler, final String str) {
        new Thread(new Runnable() { // from class: com.xsdk.ab_firstbase.statisics.util.ImageUtils.1
            @Override // java.lang.Runnable
            public void run() {
                URL url;
                Log.v(ImageUtils.TAG, "urlToBitMap 0=" + str);
                try {
                    url = new URL(str);
                } catch (MalformedURLException e) {
                    Message message = new Message();
                    new Bundle().putString("isSuccess", "0");
                    handler.sendMessage(message);
                    Log.v(ImageUtils.TAG, "0urlToBitMap fail");
                    e.printStackTrace();
                    url = null;
                }
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoInput(true);
                    int contentLength = httpURLConnection.getContentLength();
                    httpURLConnection.connect();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, contentLength);
                    ImageUtils.urlBitmap = BitmapFactory.decodeStream(bufferedInputStream);
                    bufferedInputStream.close();
                    inputStream.close();
                    if (ImageUtils.urlBitmap == null) {
                        Message message2 = new Message();
                        new Bundle().putString("isSuccess", "0");
                        handler.sendMessage(message2);
                        Log.v(ImageUtils.TAG, "1urlToBitMap fail");
                        return;
                    }
                    Message message3 = new Message();
                    new Bundle().putString("isSuccess", "1");
                    handler.sendMessage(message3);
                    Log.v(ImageUtils.TAG, "2urlToBitMap success");
                } catch (Throwable th) {
                    th.printStackTrace();
                    Message message4 = new Message();
                    new Bundle().putString("isSuccess", "0");
                    handler.sendMessage(message4);
                    Log.v(ImageUtils.TAG, "3urlToBitMap fail");
                }
            }
        }).start();
    }

    public static File readTWKFImg(Context context) {
        Log.v(TAG, "**readTWKFImg");
        File file = null;
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                Log.e(TAG, "now to read kf to sdcard");
                String imageFilePath2 = getImageFilePath(context);
                File file2 = new File(imageFilePath2);
                File file3 = new File(imageFilePath2 + KFFILE_NAME);
                try {
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    file = file3;
                } catch (Exception e) {
                    e = e;
                    file = file3;
                    e.printStackTrace();
                    return file;
                }
            } else {
                Log.v(TAG, "**no getExternalStorageStart");
            }
        } catch (Exception e2) {
            e = e2;
        }
        return file;
    }

    public static void setImageFilePath(Activity activity) {
        imageFilePath = "data/data/" + Util.getPackageName(activity) + "/img";
    }

    public static String getImageFilePath(Context context) {
        try {
            if (!TextUtils.isEmpty(imageFilePath)) {
                Log.i(TAG, "**getImageFilePath");
            } else if (context != null) {
                imageFilePath = "data/data/" + Util.getPackageName(context) + "/img";
                StringBuilder sb = new StringBuilder("**imageFilePath=");
                sb.append(imageFilePath);
                Log.i(TAG, sb.toString());
            } else if (Environment.getExternalStorageState().equals("mounted")) {
                imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + X_DIR;
                StringBuilder sb2 = new StringBuilder("**imageFilePath= MEDIA_MOUNTED ");
                sb2.append(imageFilePath);
                Log.i(TAG, sb2.toString());
            } else {
                Log.i(TAG, "**imageFilePath= MEDIA_MOUNTED no");
            }
        } catch (Throwable unused) {
        }
        return imageFilePath;
    }

    public static void delTWKFImg(Context context) {
        Log.e(TAG, "***delTWKFImg ");
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                Log.e(TAG, "now to write err to sdcard");
                String imageFilePath2 = getImageFilePath(context);
                File file = new File(imageFilePath2);
                File file2 = new File(imageFilePath2 + KFFILE_NAME);
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (file2.exists()) {
                    if (file2.delete()) {
                        Log.i(TAG, "delTWKFImg del success");
                    } else {
                        Log.i(TAG, "delTWKFImg del fail");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeTWKFImg(Context context, Bitmap bitmap) {
        Log.v(TAG, "***writeTWKFImg ");
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                Log.e(TAG, "now to write err to sdcard");
                String imageFilePath2 = getImageFilePath(context);
                File file = new File(imageFilePath2);
                File file2 = new File(imageFilePath2, KFFILE_NAME);
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (file2.exists()) {
                    if (file2.delete()) {
                        Log.i(TAG, "writeTWKFImg del success");
                    } else {
                        Log.i(TAG, "writeTWKFImg del fail");
                    }
                } else {
                    Log.i(TAG, "outFile.exists() no");
                }
                file2.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                Log.i(TAG, "saveBitmap success");
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "**FileNotFoundException e");
            e.printStackTrace();
        } catch (IOException e2) {
            Log.e(TAG, "*IOException e");
            e2.printStackTrace();
        }
    }

    public static String bitmaptoString(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return Base64.encode(byteArrayOutputStream.toByteArray());
        } catch (Throwable th) {
            LLog.v_noControl("bitmaptoString e=" + th.toString());
            return null;
        }
    }

    public static InputStream bitMap2IS(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Throwable th) {
            LLog.v_noControl("Bitmap2IS e" + th.toString());
            return null;
        }
    }

    public static void setSharePreferences(Context context, String str, int i) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(getSharedpreferencespath(), 0).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public static void setSharePreferences(Context context, String str, String str2) {
        if (context == null) {
            return;
        }
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(getSharedpreferencespath(), 0).edit();
            edit.putString(str, str2);
            edit.commit();
        } catch (Throwable unused) {
        }
    }

    public static void setSharePreferences(Context context, String str, float f) {
        if (context == null) {
            return;
        }
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(getSharedpreferencespath(), 0).edit();
            edit.putFloat(str, f);
            edit.commit();
        } catch (Throwable unused) {
        }
    }

    public static String getStringKeyForValue(Context context, String str) {
        if (context == null) {
            return "";
        }
        try {
            return context.getSharedPreferences(getSharedpreferencespath(), 0).getString(str, "");
        } catch (Throwable th) {
            th.toString();
            return "";
        }
    }

    public static float getFloatKeyForValue(Context context, String str) {
        if (context == null) {
            return 0.0f;
        }
        try {
            return context.getSharedPreferences(getSharedpreferencespath(), 0).getFloat(str, 0.0f);
        } catch (Exception e) {
            e.toString();
            return 0.0f;
        }
    }

    public static int getIntKeyForValue(Context context, String str) {
        if (context == null) {
            return 0;
        }
        return context.getSharedPreferences(getSharedpreferencespath(), 0).getInt(str, 0);
    }

    public static void setSharePreferences(Context context, String str, boolean z) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(getSharedpreferencespath(), 0).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public static Boolean getStringKeyForBoolValue(Context context, String str) {
        boolean z = false;
        try {
            return Boolean.valueOf(context.getSharedPreferences(getSharedpreferencespath(), 0).getBoolean(str, false));
        } catch (Throwable th) {
            th.toString();
            return z;
        }
    }

    public static Boolean getStringKeyForBoolValue(Context context, String str, boolean z) {
        try {
            return Boolean.valueOf(context.getSharedPreferences(getSharedpreferencespath(), 0).getBoolean(str, z));
        } catch (Exception unused) {
            return Boolean.valueOf(z);
        }
    }

    public static void remove(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(getSharedpreferencespath(), 0).edit();
        edit.remove(str);
        edit.apply();
    }

    public static Boolean clearShareData(Context context) {
        try {
            context.getSharedPreferences(getSharedpreferencespath(), 0).edit().clear().commit();
        } catch (Throwable th) {
            th.toString();
        }
        return false;
    }

    public static String getMetaData(Context context, String str) {
        Object obj;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            return (bundle == null || (obj = bundle.get(str)) == null) ? "" : String.valueOf(obj);
        } catch (Exception unused) {
            return "";
        }
    }

    public static float getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static float getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static String readAccount(Context context) {
        setFileName(context);
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                Log.e(TAG, "now to write err to sdcard");
                String str = Environment.getExternalStorageDirectory().getAbsolutePath() + FILE_DIR;
                File file = new File(str);
                File file2 = new File(str + FILE_NAME);
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (file2.exists()) {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file2));
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            stringBuffer.append(readLine);
                        } else {
                            String stringBuffer2 = stringBuffer.toString();
                            bufferedReader.close();
                            return stringBuffer2;
                        }
                    }
                } else {
                    return getStringKeyForValue(context, Constants.SDK_ALLACCOUNT);
                }
            } else {
                return getStringKeyForValue(context, Constants.SDK_ALLACCOUNT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getStringKeyForValue(context, Constants.SDK_ALLACCOUNT);
        }
    }

    public static void writeAccount(Context context, String str) {
        setSharePreferences(context, Constants.SDK_ALLACCOUNT, str);
        setFileName(context);
        Log.e(TAG, "***writeAccount account " + str);
        try {
            if (!Environment.getExternalStorageState().equals("mounted") || PremissionBase.lacksPermission(context, PermissionConfig.WRITE_EXTERNAL_STORAGE)) {
                return;
            }
            Log.e(TAG, "now to write err to sdcard");
            String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + FILE_DIR;
            File file = new File(str2);
            File file2 = new File(str2 + FILE_NAME);
            if (!file.exists()) {
                file.mkdirs();
            }
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
