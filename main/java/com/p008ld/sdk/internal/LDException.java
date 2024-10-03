package com.p008ld.sdk.internal;

import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.util.zzi;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LDException.kt */
/* loaded from: classes2.dex */
public class LDException extends RuntimeException {
    public static final Companion Companion = new Companion(null);
    public static final long serialVersionUID = 1;
    private int code;

    public final int getCode() {
        return this.code;
    }

    public final void setCode(int i) {
        this.code = i;
    }

    public LDException() {
        this.code = -1;
    }

    public LDException(String str) {
        super(str);
        this.code = -1;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public LDException(java.lang.String r2, java.lang.Object... r3) {
        /*
            r1 = this;
            java.lang.String r0 = "args"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            if (r2 == 0) goto L1b
            int r0 = r3.length
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r0)
            int r0 = r3.length
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r0)
            java.lang.String r2 = java.lang.String.format(r2, r3)
            java.lang.String r3 = "format(this, *args)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            goto L1c
        L1b:
            r2 = 0
        L1c:
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.internal.LDException.<init>(java.lang.String, java.lang.Object[]):void");
    }

    public LDException(String str, Throwable th) {
        super(str, th);
        this.code = -1;
    }

    public LDException(Throwable th) {
        super(th);
        this.code = -1;
    }

    public LDException(Integer num, String str) {
        super(str);
        this.code = -1;
        this.code = num != null ? num.intValue() : -1;
    }

    public int getErrorCode() {
        return this.code;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return " " + getMessage() + " : " + this.code + ' ';
    }

    public String toDesc() {
        if (getErrorCode() == -1) {
            String zza = zzi.zza(LDSdk.getApp(), "ld_network_anomaly_text");
            Intrinsics.checkNotNullExpressionValue(zza, "getResString(LDSdk.getAp…ld_network_anomaly_text\")");
            return zza;
        }
        String zza2 = zzi.zza(LDSdk.getApp(), StringsKt.replace$default("ld_fail_code_" + getErrorCode(), "-", "_", false, 4, (Object) null));
        Intrinsics.checkNotNullExpressionValue(zza2, "getResString(LDSdk.getAp…, name.replace(\"-\", \"_\"))");
        return zza2;
    }

    /* compiled from: LDException.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
