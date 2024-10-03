package com.p008ld.sdk.internal;

import com.p008ld.sdk.zzd.zzc;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDFreezingException.kt */
/* loaded from: classes2.dex */
public class LDFreezingException extends LDException {
    public static final Companion Companion = new Companion(null);
    public static final long serialVersionUID = 1;
    private final String url;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LDFreezingException(String url, String errorMessage) {
        super(errorMessage);
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        this.url = url;
    }

    public final String getUrl() {
        return this.url;
    }

    @Override // com.p008ld.sdk.internal.LDException
    public int getErrorCode() {
        return zzc.ERROR_FREEZING.zza();
    }

    @Override // com.p008ld.sdk.internal.LDException, java.lang.Throwable
    public String toString() {
        return " " + getMessage() + " : " + getErrorCode() + " , url = " + this.url;
    }

    /* compiled from: LDFreezingException.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
