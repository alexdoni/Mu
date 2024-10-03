package com.google.ads.conversiontracking;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.google.ads.conversiontracking.C0883g;

/* loaded from: classes.dex */
public class AdWordsConversionReporter extends GoogleConversionReporter {

    /* renamed from: a */
    private final Context f375a;

    /* renamed from: b */
    private final String f376b;

    /* renamed from: c */
    private final String f377c;

    /* renamed from: d */
    private final C0883g.d f378d;

    /* renamed from: e */
    private final String f379e;

    /* renamed from: f */
    private final String f380f;

    /* renamed from: g */
    private final boolean f381g;

    public AdWordsConversionReporter(Context context, String str, String str2, String str3, boolean z) {
        this(context, str, str2, str3, null, z);
    }

    public AdWordsConversionReporter(Context context, String str, String str2, String str3, String str4, boolean z) {
        C0883g.d dVar;
        this.f375a = context;
        this.f376b = str;
        this.f377c = str2;
        this.f379e = str3;
        this.f380f = str4;
        this.f381g = z;
        if (this instanceof DoubleClickConversionReporter) {
            dVar = C0883g.d.DOUBLECLICK_CONVERSION;
        } else {
            dVar = C0883g.d.GOOGLE_CONVERSION;
        }
        this.f378d = dVar;
    }

    @Override // com.google.ads.conversiontracking.GoogleConversionReporter
    public void report() {
        boolean z;
        C0883g.c m218c = new C0883g.c().m213a(this.f376b).m212a(this.f378d).m217b(this.f377c).m218c(this.f379e);
        String str = this.f380f;
        if (str != null) {
            m218c.m219d(str);
        }
        if (this.f378d == C0883g.d.GOOGLE_CONVERSION) {
            RunnableC0879c m127a = RunnableC0879c.m127a(this.f375a);
            m127a.m136c(this.f376b);
            m218c.m215a(m127a.m137d(this.f376b));
        }
        if (C0883g.m181a(this.f375a, m218c, this.f381g)) {
            try {
                if (this.f378d == C0883g.d.GOOGLE_CONVERSION) {
                    m218c.m211a(C0883g.m163a(this.f375a, this.f376b));
                    z = true;
                } else {
                    z = false;
                }
                m124a(this.f375a, m218c, true, this.f381g, z);
            } catch (Exception e) {
                Log.e("GoogleConversionReporter", "Error sending ping", e);
            }
        }
    }

    public static boolean registerReferrer(Context context, Uri uri) {
        if (uri == null) {
            Log.e("GoogleConversionReporter", "Failed to register referrer from a null click url");
            return false;
        }
        String valueOf = String.valueOf(uri);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
        sb.append("Registering: ");
        sb.append(valueOf);
        Log.i("GoogleConversionReporter", sb.toString());
        C0883g.b m164a = C0883g.m164a(uri);
        if (m164a == null) {
            String valueOf2 = String.valueOf(uri);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 31);
            sb2.append("Failed to parse referrer from: ");
            sb2.append(valueOf2);
            Log.w("GoogleConversionReporter", sb2.toString());
            return false;
        }
        boolean m180a = C0883g.m180a(context, m164a);
        if (m180a) {
            String valueOf3 = String.valueOf(uri);
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 25);
            sb3.append("Successfully registered: ");
            sb3.append(valueOf3);
            Log.i("GoogleConversionReporter", sb3.toString());
        } else {
            String valueOf4 = String.valueOf(uri);
            StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf4).length() + 20);
            sb4.append("Failed to register: ");
            sb4.append(valueOf4);
            Log.w("GoogleConversionReporter", sb4.toString());
        }
        return m180a;
    }

    public static void reportWithConversionId(Context context, String str, String str2, String str3, boolean z) {
        new AdWordsConversionReporter(context, str, str2, str3, z).report();
    }

    public static void reportWithConversionId(Context context, String str, String str2, String str3, String str4, boolean z) {
        new AdWordsConversionReporter(context, str, str2, str3, str4, z).report();
    }
}
