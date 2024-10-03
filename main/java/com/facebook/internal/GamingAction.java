package com.facebook.internal;

import java.util.Arrays;
import kotlin.Metadata;

/* compiled from: FacebookGamingAction.kt */
@Metadata(m1394d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m1395d2 = {"Lcom/facebook/internal/GamingAction;", "", "rawValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getRawValue", "()Ljava/lang/String;", "ContextChoose", "JoinTournament", "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public enum GamingAction {
    ContextChoose("context_choose"),
    JoinTournament("join_tournament");

    private final String rawValue;

    GamingAction(String str) {
        this.rawValue = str;
    }

    public final String getRawValue() {
        return this.rawValue;
    }

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static GamingAction[] valuesCustom() {
        GamingAction[] valuesCustom = values();
        return (GamingAction[]) Arrays.copyOf(valuesCustom, valuesCustom.length);
    }
}
