package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.bp */
/* loaded from: classes3.dex */
public final class C2608bp extends AbstractC2625m implements Cloneable {

    /* renamed from: b */
    static ArrayList<C2607bo> f1358b;

    /* renamed from: a */
    public ArrayList<C2607bo> f1359a = null;

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo995a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo994a(C2624l c2624l) {
        c2624l.m1078a((Collection) this.f1359a, 0);
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo993a(C2623k c2623k) {
        if (f1358b == null) {
            f1358b = new ArrayList<>();
            f1358b.add(new C2607bo());
        }
        this.f1359a = (ArrayList) c2623k.m1053a((C2623k) f1358b, 0, true);
    }
}
