package com.tencent.p014av.ptt;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.tencent.p014av.utils.QLog;

/* loaded from: classes3.dex */
public class NetworkProvider {
    private static String TAG = "NetworkProvider";

    /* loaded from: classes3.dex */
    public interface NETWORK_TYPE {
        public static final int TYPE_2G = 2;
        public static final int TYPE_3G = 3;
        public static final int TYPE_4G = 4;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_WIFI = 1;
        public static final int TYPE_WIRED = 5;
    }

    public static int getNetTypeName(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return 0;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 1;
        }
        if (activeNetworkInfo.getType() == 0) {
            switch (activeNetworkInfo.getSubtype()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return 2;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return 3;
                case 13:
                    return 4;
                default:
                    String subtypeName = activeNetworkInfo.getSubtypeName();
                    QLog.m602i(TAG, "network type = " + subtypeName);
                    if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                        return 3;
                    }
                    QLog.m602i(TAG, "unknown network type = " + subtypeName);
                    return 0;
            }
        }
        return 0;
    }
}
