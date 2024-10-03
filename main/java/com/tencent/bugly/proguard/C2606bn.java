package com.tencent.bugly.proguard;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.bn */
/* loaded from: classes3.dex */
public final class C2606bn extends AbstractC2625m implements Cloneable {

    /* renamed from: d */
    static byte[] f1325d;

    /* renamed from: a */
    public byte f1326a;

    /* renamed from: b */
    public String f1327b;

    /* renamed from: c */
    public byte[] f1328c;

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo995a(StringBuilder sb, int i) {
    }

    public C2606bn() {
        this.f1326a = (byte) 0;
        this.f1327b = "";
        this.f1328c = null;
    }

    public C2606bn(byte b, String str, byte[] bArr) {
        this.f1326a = b;
        this.f1327b = str;
        this.f1328c = bArr;
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo994a(C2624l c2624l) {
        c2624l.m1072a(this.f1326a, 0);
        c2624l.m1077a(this.f1327b, 1);
        byte[] bArr = this.f1328c;
        if (bArr != null) {
            c2624l.m1082a(bArr, 2);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo993a(C2623k c2623k) {
        this.f1326a = c2623k.m1048a(this.f1326a, 0, true);
        this.f1327b = c2623k.m1058b(1, true);
        if (f1325d == null) {
            f1325d = r0;
            byte[] bArr = {0};
        }
        this.f1328c = c2623k.m1059c(2, false);
    }
}
