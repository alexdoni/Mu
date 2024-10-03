package com.google.ads.conversiontracking;

import android.content.Context;
import android.util.Log;
import com.google.ads.conversiontracking.C0883g;

/* renamed from: com.google.ads.conversiontracking.b */
/* loaded from: classes.dex */
public class C0878b extends GoogleConversionReporter {

    /* renamed from: a */
    private Context f405a;

    @Override // com.google.ads.conversiontracking.GoogleConversionReporter
    public void report() {
    }

    public C0878b(Context context) {
        this.f405a = context;
    }

    /* renamed from: a */
    public void m126a(String str, long j) {
        try {
            m124a(this.f405a, new C0883g.c().m213a(str).m215a(true).m216b().m210a(j), false, true, true);
        } catch (Exception e) {
            Log.e("GoogleConversionReporter", "Error sending ping", e);
        }
    }
}
