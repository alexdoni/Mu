package com.tencent.p014av.wrapper;

import android.content.Context;
import com.tencent.p014av.utils.QLog;

/* loaded from: classes3.dex */
public class GMEJavaInstance {
    private static OpensdkGameWrapper gameWrapper;
    private static Context mActivity;

    public static void InitJavaEnv(Context context) {
        mActivity = context;
    }

    public static Context getmActivity() {
        return mActivity;
    }

    public static int NativeInit() {
        if (mActivity == null) {
            return -1;
        }
        OpensdkGameWrapper opensdkGameWrapper = new OpensdkGameWrapper(mActivity);
        gameWrapper = opensdkGameWrapper;
        int initOpensdk = opensdkGameWrapper.initOpensdk();
        QLog.m600e("GMEJavaInstance", "initOpensdk");
        return initOpensdk;
    }
}
