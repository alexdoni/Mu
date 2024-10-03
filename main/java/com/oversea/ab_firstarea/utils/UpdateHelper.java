package com.oversea.ab_firstarea.utils;

/* loaded from: classes.dex */
public class UpdateHelper {
    public static final int UPDATE_REQUEST_CODE = 1231;
    private static volatile UpdateHelper instance;

    public static UpdateHelper getInstance() {
        if (instance == null) {
            synchronized (UpdateHelper.class) {
                if (instance == null) {
                    instance = new UpdateHelper();
                }
            }
        }
        return instance;
    }
}
