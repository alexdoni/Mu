package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.bm */
/* loaded from: classes3.dex */
public final class C2605bm extends AbstractC2625m implements Cloneable {

    /* renamed from: c */
    static ArrayList<String> f1322c;

    /* renamed from: a */
    public String f1323a = "";

    /* renamed from: b */
    public ArrayList<String> f1324b = null;

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo995a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo994a(C2624l c2624l) {
        c2624l.m1077a(this.f1323a, 0);
        ArrayList<String> arrayList = this.f1324b;
        if (arrayList != null) {
            c2624l.m1078a((Collection) arrayList, 1);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo993a(C2623k c2623k) {
        this.f1323a = c2623k.m1058b(0, true);
        if (f1322c == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            f1322c = arrayList;
            arrayList.add("");
        }
        this.f1324b = (ArrayList) c2623k.m1053a((C2623k) f1322c, 1, false);
    }
}
