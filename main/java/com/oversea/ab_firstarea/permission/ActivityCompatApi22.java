package com.oversea.ab_firstarea.permission;

import android.app.Activity;
import android.net.Uri;

/* loaded from: classes.dex */
class ActivityCompatApi22 {
    ActivityCompatApi22() {
    }

    public static Uri getReferrer(Activity activity) {
        return activity.getReferrer();
    }
}
