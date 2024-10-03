package com.u2020.sdk.mc;

/* compiled from: Pair.java */
/* renamed from: com.u2020.sdk.mc.e */
/* loaded from: classes3.dex */
final class C2655e<A, B> {

    /* renamed from: a */
    private final A f1579a;

    /* renamed from: b */
    private final B f1580b;

    private C2655e(final A first, final B second) {
        this.f1579a = first;
        this.f1580b = second;
    }

    /* renamed from: a */
    public static <A, B> C2655e<A, B> m1207a(final A first, final B second) {
        return new C2655e<>(first, second);
    }

    /* renamed from: b */
    public B m1209b() {
        return this.f1580b;
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2655e.class != obj.getClass()) {
            return false;
        }
        C2655e c2655e = (C2655e) obj;
        A a2 = this.f1579a;
        if (a2 == null) {
            if (c2655e.f1579a != null) {
                return false;
            }
        } else if (!a2.equals(c2655e.f1579a)) {
            return false;
        }
        B b = this.f1580b;
        if (b == null) {
            if (c2655e.f1580b != null) {
                return false;
            }
        } else if (!b.equals(c2655e.f1580b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        A a2 = this.f1579a;
        int hashCode = ((a2 == null ? 0 : a2.hashCode()) + 31) * 31;
        B b = this.f1580b;
        return hashCode + (b != null ? b.hashCode() : 0);
    }

    /* renamed from: a */
    public A m1208a() {
        return this.f1579a;
    }
}
