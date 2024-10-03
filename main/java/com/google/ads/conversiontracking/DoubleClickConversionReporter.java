package com.google.ads.conversiontracking;

import android.content.Context;

/* loaded from: classes.dex */
public class DoubleClickConversionReporter extends AdWordsConversionReporter {
    public DoubleClickConversionReporter(Context context, String str, String str2, String str3, boolean z) {
        super(context, str, str2, str3, z);
    }

    public static void reportWithConversionId(Context context, String str, String str2, String str3, boolean z) {
        new DoubleClickConversionReporter(context, str, str2, str3, z).report();
    }
}
