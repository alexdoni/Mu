package com.google.ads.conversiontracking;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.ads.conversiontracking.C0883g;
import java.util.Map;

/* loaded from: classes.dex */
public class DoubleClickAudienceReporter extends GoogleConversionReporter {

    /* renamed from: a */
    private final Context f385a;

    /* renamed from: b */
    private String f386b;

    /* renamed from: c */
    private Map<String, String> f387c;

    private DoubleClickAudienceReporter(Context context, String str, Map<String, String> map) {
        this.f385a = context;
        this.f386b = str;
        this.f387c = map;
    }

    @Override // com.google.ads.conversiontracking.GoogleConversionReporter
    public void report() {
        if (TextUtils.isEmpty(this.f386b)) {
            Log.e("GoogleConversionReporter", "Error sending activity ping with empty ad unit id.");
            return;
        }
        try {
            m124a(this.f385a, new C0883g.c().m212a(C0883g.d.DOUBLECLICK_AUDIENCE).m214a(this.f387c).m218c(this.f386b), false, true, false);
        } catch (Exception e) {
            Log.e("GoogleConversionReporter", "Error sending ping", e);
        }
    }

    public static void reportActivity(Context context, String str, Map<String, String> map) {
        new DoubleClickAudienceReporter((Context) C0883g.m165a(context), C0883g.m174a(str), map).report();
    }
}
