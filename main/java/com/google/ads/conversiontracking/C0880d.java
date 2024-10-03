package com.google.ads.conversiontracking;

import com.google.ads.conversiontracking.C0883g;

/* renamed from: com.google.ads.conversiontracking.d */
/* loaded from: classes.dex */
public class C0880d {

    /* renamed from: a */
    public final boolean f421a;

    /* renamed from: b */
    public final boolean f422b;

    /* renamed from: c */
    public final int f423c;

    /* renamed from: d */
    public final long f424d;

    /* renamed from: e */
    public final String f425e;

    /* renamed from: f */
    public final String f426f;

    /* renamed from: g */
    public final String f427g;

    /* renamed from: h */
    public long f428h;

    public C0880d(String str, C0883g.c cVar, boolean z, boolean z2) {
        this.f427g = str;
        this.f422b = z2;
        this.f421a = z;
        this.f428h = 0L;
        this.f424d = C0883g.m161a();
        this.f423c = 0;
        if (!z2 && z) {
            this.f426f = C0883g.m184b(cVar);
            this.f425e = C0883g.m170a(cVar);
        } else {
            this.f426f = null;
            this.f425e = null;
        }
    }

    public C0880d(long j, String str, String str2, boolean z, boolean z2, String str3, long j2, int i) {
        this.f428h = j;
        this.f427g = str;
        this.f426f = str2;
        this.f422b = z;
        this.f421a = z2;
        this.f425e = str3;
        this.f424d = j2;
        this.f423c = i;
    }
}
