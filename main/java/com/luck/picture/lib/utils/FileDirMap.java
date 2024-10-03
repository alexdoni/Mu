package com.luck.picture.lib.utils;

import android.content.Context;
import android.os.Environment;
import java.util.HashMap;

/* loaded from: classes2.dex */
public final class FileDirMap {
    private static final HashMap<Integer, String> dirMap = new HashMap<>();

    public static void init(Context context) {
        if (ActivityCompatHelper.assertValidRequest(context)) {
            HashMap<Integer, String> hashMap = dirMap;
            if (hashMap.get(1) == null) {
                hashMap.put(1, context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath());
            }
            if (hashMap.get(2) == null) {
                hashMap.put(2, context.getExternalFilesDir(Environment.DIRECTORY_MOVIES).getPath());
            }
            if (hashMap.get(3) == null) {
                hashMap.put(3, context.getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath());
            }
        }
    }

    public static String getFileDirPath(Context context, int i) {
        HashMap<Integer, String> hashMap = dirMap;
        String str = hashMap.get(Integer.valueOf(i));
        if (str != null) {
            return str;
        }
        init(context);
        return hashMap.get(Integer.valueOf(i));
    }

    public static void clear() {
        dirMap.clear();
    }
}
