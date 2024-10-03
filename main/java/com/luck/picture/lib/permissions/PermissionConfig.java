package com.luck.picture.lib.permissions;

import android.content.Context;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.utils.SdkVersionUtils;

/* loaded from: classes2.dex */
public class PermissionConfig {
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public static final String READ_MEDIA_AUDIO = "android.permission.READ_MEDIA_AUDIO";
    public static final String READ_MEDIA_IMAGES = "android.permission.READ_MEDIA_IMAGES";
    public static final String READ_MEDIA_VIDEO = "android.permission.READ_MEDIA_VIDEO";
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    public static String[] CURRENT_REQUEST_PERMISSION = new String[0];
    public static final String[] CAMERA = {"android.permission.CAMERA"};

    public static String[] getReadPermissionArray(Context context, int i) {
        if (SdkVersionUtils.isTIRAMISU()) {
            int i2 = context.getApplicationInfo().targetSdkVersion;
            if (i == SelectMimeType.ofImage()) {
                if (i2 >= 33) {
                    return new String[]{READ_MEDIA_IMAGES};
                }
                return new String[]{READ_MEDIA_IMAGES, READ_EXTERNAL_STORAGE};
            }
            if (i == SelectMimeType.ofVideo()) {
                if (i2 >= 33) {
                    return new String[]{READ_MEDIA_VIDEO};
                }
                return new String[]{READ_MEDIA_VIDEO, READ_EXTERNAL_STORAGE};
            }
            if (i == SelectMimeType.ofAudio()) {
                if (i2 >= 33) {
                    return new String[]{READ_MEDIA_AUDIO};
                }
                return new String[]{READ_MEDIA_AUDIO, READ_EXTERNAL_STORAGE};
            }
            if (i2 >= 33) {
                return new String[]{READ_MEDIA_IMAGES, READ_MEDIA_VIDEO};
            }
            return new String[]{READ_MEDIA_IMAGES, READ_MEDIA_VIDEO, READ_EXTERNAL_STORAGE};
        }
        return new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};
    }
}
