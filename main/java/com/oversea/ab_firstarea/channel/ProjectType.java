package com.oversea.ab_firstarea.channel;

import android.text.TextUtils;
import android.util.Log;

/* loaded from: classes.dex */
public class ProjectType {
    public static final String FIVEFIVE = "FIVEFIVE";
    public static final String FORMAL = "SPEnvironmentTypeProduction";
    public static final String TAG = "X_LOG";
    public static final String TEST = "SPEnvironmentTypeSandbox";
    public static final String ZIAN = "SPEnvironmentTypeZian";
    public static String pType = "SPEnvironmentTypeProduction";

    public static void setProjectType(String str) {
        if (!TextUtils.isEmpty(str)) {
            pType = str;
        } else {
            Log.v("X_LOG", "type= null or empty");
        }
        PTypeUrl.setProjectType();
    }

    public static boolean isFormal() {
        return FORMAL.equals(pType);
    }
}
