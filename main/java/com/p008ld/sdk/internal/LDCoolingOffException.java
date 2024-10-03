package com.p008ld.sdk.internal;

import com.p008ld.sdk.bean.CoolingOffBean;
import com.p008ld.sdk.zzd.zzc;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDCoolingOffException.kt */
/* loaded from: classes2.dex */
public class LDCoolingOffException extends LDException {
    public static final Companion Companion = new Companion(null);
    public static final long serialVersionUID = 1;
    private final CoolingOffBean coolingBean;

    public final CoolingOffBean getCoolingBean() {
        return this.coolingBean;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LDCoolingOffException(CoolingOffBean coolingBean, String errorMessage) {
        super(errorMessage);
        Intrinsics.checkNotNullParameter(coolingBean, "coolingBean");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        this.coolingBean = coolingBean;
    }

    @Override // com.p008ld.sdk.internal.LDException
    public int getErrorCode() {
        return zzc.ERROR_IN_COOLING_OFF_PERIOD.zza();
    }

    @Override // com.p008ld.sdk.internal.LDException, java.lang.Throwable
    public String toString() {
        return " " + getMessage() + " : " + getErrorCode() + " , coolingBean = " + this.coolingBean;
    }

    /* compiled from: LDCoolingOffException.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
