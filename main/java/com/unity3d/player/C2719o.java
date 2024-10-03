package com.unity3d.player;

/* renamed from: com.unity3d.player.o */
/* loaded from: classes3.dex */
final class C2719o {

    /* renamed from: a */
    private static boolean f1844a = false;

    /* renamed from: b */
    private boolean f1845b = false;

    /* renamed from: c */
    private boolean f1846c = false;

    /* renamed from: d */
    private boolean f1847d = true;

    /* renamed from: e */
    private boolean f1848e = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m1352a() {
        f1844a = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m1353b() {
        f1844a = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static boolean m1354c() {
        return f1844a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m1355a(boolean z) {
        this.f1845b = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m1356b(boolean z) {
        this.f1847d = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final void m1357c(boolean z) {
        this.f1848e = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final void m1358d(boolean z) {
        this.f1846c = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final boolean m1359d() {
        return this.f1847d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public final boolean m1360e() {
        return this.f1848e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public final boolean m1361e(boolean z) {
        if (f1844a) {
            return ((!z && !this.f1845b) || this.f1847d || this.f1846c) ? false : true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public final boolean m1362f() {
        return this.f1846c;
    }

    public final String toString() {
        return super.toString();
    }
}
