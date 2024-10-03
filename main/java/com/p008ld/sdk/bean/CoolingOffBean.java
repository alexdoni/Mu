package com.p008ld.sdk.bean;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDResult.kt */
/* loaded from: classes2.dex */
public final class CoolingOffBean implements Serializable {
    private final String coolingOffPeriodEndTime;

    public static /* synthetic */ CoolingOffBean copy$default(CoolingOffBean coolingOffBean, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = coolingOffBean.coolingOffPeriodEndTime;
        }
        return coolingOffBean.copy(str);
    }

    public final String component1() {
        return this.coolingOffPeriodEndTime;
    }

    public final CoolingOffBean copy(String coolingOffPeriodEndTime) {
        Intrinsics.checkNotNullParameter(coolingOffPeriodEndTime, "coolingOffPeriodEndTime");
        return new CoolingOffBean(coolingOffPeriodEndTime);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CoolingOffBean) && Intrinsics.areEqual(this.coolingOffPeriodEndTime, ((CoolingOffBean) obj).coolingOffPeriodEndTime);
    }

    public int hashCode() {
        return this.coolingOffPeriodEndTime.hashCode();
    }

    public String toString() {
        return "CoolingOffBean(coolingOffPeriodEndTime=" + this.coolingOffPeriodEndTime + ')';
    }

    public CoolingOffBean(String coolingOffPeriodEndTime) {
        Intrinsics.checkNotNullParameter(coolingOffPeriodEndTime, "coolingOffPeriodEndTime");
        this.coolingOffPeriodEndTime = coolingOffPeriodEndTime;
    }

    public final String getCoolingOffPeriodEndTime() {
        return this.coolingOffPeriodEndTime;
    }
}
