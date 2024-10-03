package com.google.ads.conversiontracking;

import android.content.Context;
import android.util.Log;
import com.google.ads.conversiontracking.C0883g;

/* loaded from: classes.dex */
public abstract class GoogleConversionReporter {
    public abstract void report();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m124a(final Context context, final C0883g.c cVar, final boolean z, final boolean z2, final boolean z3) {
        new Thread(new Runnable() { // from class: com.google.ads.conversiontracking.GoogleConversionReporter.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String m167a = C0883g.m167a(context, cVar);
                    if (m167a != null) {
                        C0883g.m162a(context).m148a(m167a, cVar, z, z2, z3);
                    }
                } catch (Exception e) {
                    Log.e("GoogleConversionReporter", "Error sending ping", e);
                }
            }
        }).start();
    }
}
