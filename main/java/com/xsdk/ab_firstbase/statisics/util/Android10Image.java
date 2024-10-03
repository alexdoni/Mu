package com.xsdk.ab_firstbase.statisics.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.luck.picture.lib.config.PictureMimeType;
import java.io.File;

/* loaded from: classes3.dex */
public class Android10Image {
    public static Bitmap displayImage(Context context, String str) {
        return getBitmapFromUri(context, getImageContentUri(context, str));
    }

    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(openFileDescriptor.getFileDescriptor());
            openFileDescriptor.close();
            return decodeFileDescriptor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Uri getImageContentUri(Context context, String str) {
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{FileDownloadModel.f718ID}, "_data=? ", new String[]{str}, null);
        if (query != null && query.moveToFirst()) {
            int i = query.getInt(query.getColumnIndex(FileDownloadModel.f718ID));
            return Uri.withAppendedPath(Uri.parse("content://media/external/images/media"), "" + i);
        }
        if (!new File(str).exists()) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("_data", str);
        return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    public static Uri getImageContentUri(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("description", "This is an image");
        contentValues.put("_display_name", "Image.png");
        contentValues.put("mime_type", PictureMimeType.PNG_Q);
        contentValues.put("title", "Image.png");
        contentValues.put("relative_path", "Pictures/test");
        return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }
}
