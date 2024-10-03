package com.p008ld.sdk.internal;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: LDSdkNotInitializedException.kt */
/* loaded from: classes2.dex */
public final class LDSdkNotInitializedException extends LDException {
    public static final Companion Companion = new Companion(null);
    public static final long serialVersionUID = 1;

    public LDSdkNotInitializedException() {
    }

    public LDSdkNotInitializedException(String str) {
        super(str);
    }

    public LDSdkNotInitializedException(String str, Throwable th) {
        super(str, th);
    }

    public LDSdkNotInitializedException(Throwable th) {
        super(th);
    }

    /* compiled from: LDSdkNotInitializedException.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
