package com.appsflyer.internal;

/* loaded from: classes.dex */
public abstract class AFh1rSDK extends AFa1pSDK {
    private final boolean force;

    /* renamed from: i */
    private final boolean f276i;

    AFh1rSDK() {
        this(null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AFh1rSDK(String str, Boolean bool, Boolean bool2) {
        super(str, null, Boolean.valueOf(bool2 != null ? bool2.booleanValue() : false));
        this.f276i = bool != null ? bool.booleanValue() : true;
        this.force = true;
    }
}
