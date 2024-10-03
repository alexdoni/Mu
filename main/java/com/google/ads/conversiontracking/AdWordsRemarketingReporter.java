package com.google.ads.conversiontracking;

import android.content.Context;
import android.util.Log;
import com.google.ads.conversiontracking.C0883g;
import java.util.Map;

/* loaded from: classes.dex */
public final class AdWordsRemarketingReporter extends GoogleConversionReporter {

    /* renamed from: a */
    private final Context f382a;

    /* renamed from: b */
    private final String f383b;

    /* renamed from: c */
    private final Map<String, Object> f384c;

    public AdWordsRemarketingReporter(Context context, String str, Map<String, Object> map) {
        this.f382a = context;
        this.f383b = str;
        this.f384c = map;
    }

    public AdWordsRemarketingReporter(Context context, String str) {
        this.f382a = context;
        this.f383b = str;
        this.f384c = null;
    }

    @Override // com.google.ads.conversiontracking.GoogleConversionReporter
    public void report() {
        RunnableC0879c m127a = RunnableC0879c.m127a(this.f382a);
        m127a.m136c(this.f383b);
        try {
            m124a(this.f382a, new C0883g.c().m213a(this.f383b).m209a().m214a(this.f384c).m215a(m127a.m137d(this.f383b)), false, true, true);
        } catch (Exception e) {
            Log.e("GoogleConversionReporter", "Error sending ping", e);
        }
    }

    public static void reportWithConversionId(Context context, String str, Map<String, Object> map) {
        new AdWordsRemarketingReporter(context, str, map).report();
    }

    public static void reportWithConversionId(Context context, String str) {
        reportWithConversionId(context, str, null);
    }
}
