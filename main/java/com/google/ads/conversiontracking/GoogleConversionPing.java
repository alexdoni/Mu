package com.google.ads.conversiontracking;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Map;

@Deprecated
/* loaded from: classes.dex */
public class GoogleConversionPing {

    @Deprecated
    /* loaded from: classes.dex */
    public enum ConversionType {
        GOOGLE_CONVERSION,
        DOUBLECLICK_CONVERSION
    }

    @Deprecated
    public static void recordConversionPing(Context context, String str, String str2, String str3, boolean z) {
        AdWordsConversionReporter.reportWithConversionId(context, str, str2, str3, z);
    }

    @Deprecated
    public static void recordConversionPing(Context context, String str, ConversionType conversionType, String str2, String str3, boolean z) {
        if (conversionType == ConversionType.GOOGLE_CONVERSION) {
            AdWordsConversionReporter.reportWithConversionId(context, str, str2, str3, z);
        } else {
            DoubleClickConversionReporter.reportWithConversionId(context, str, str2, str3, z);
        }
    }

    @Deprecated
    public static void recordRemarketingPing(Context context, String str, String str2, String str3, Map<String, String> map) {
        HashMap hashMap;
        if (map == null && TextUtils.isEmpty(str3)) {
            hashMap = null;
        } else {
            hashMap = new HashMap();
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue());
                }
            }
            if (!TextUtils.isEmpty(str3)) {
                hashMap.put(FirebaseAnalytics.Param.SCREEN_NAME, str3);
            }
        }
        AdWordsRemarketingReporter.reportWithConversionId(context, str, hashMap);
    }

    @Deprecated
    public static boolean registerReferrer(Context context, Uri uri) {
        return AdWordsConversionReporter.registerReferrer(context, uri);
    }
}
