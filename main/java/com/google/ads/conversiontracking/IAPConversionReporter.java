package com.google.ads.conversiontracking;

import android.content.Context;
import com.google.ads.conversiontracking.C0883g;

/* loaded from: classes.dex */
public class IAPConversionReporter extends GoogleConversionReporter {

    /* renamed from: a */
    private final Context f395a;

    /* renamed from: b */
    private final boolean f396b;

    /* renamed from: c */
    private final String f397c;

    /* renamed from: d */
    private final C0883g.d f398d = C0883g.d.IAP_CONVERSION;

    /* renamed from: e */
    private final String f399e;

    public IAPConversionReporter(Context context, String str, String str2, boolean z) {
        this.f395a = context;
        this.f397c = str;
        this.f399e = str2;
        this.f396b = z;
    }

    @Override // com.google.ads.conversiontracking.GoogleConversionReporter
    public void report() {
        C0883g.c m218c = new C0883g.c().m220e(this.f397c).m212a(this.f398d).m218c(this.f399e);
        if (C0883g.m181a(this.f395a, m218c, this.f396b)) {
            m124a(this.f395a, m218c, true, this.f396b, true);
        }
    }

    public static void reportWithProductId(Context context, String str, String str2, boolean z) {
        new IAPConversionReporter(context, str, str2, z).report();
    }
}
