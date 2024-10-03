package com.p008ld.sdk.zzd;

import java.util.ArrayList;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: LDApiCode.kt */
/* loaded from: classes2.dex */
public enum zzc {
    ERROR_CAPTCHA(1001),
    ERROR_FREEZING(1002),
    ERROR_IN_COOLING_OFF_PERIOD(1003),
    ERROR_TOKEN(401);

    private final int zzf;
    public static final zza zza = new zza(null);
    private static final zzc[] zzg = values();

    zzc(int i) {
        this.zzf = i;
    }

    public final int zza() {
        return this.zzf;
    }

    /* compiled from: LDApiCode.kt */
    /* loaded from: classes2.dex */
    public static final class zza {
        public /* synthetic */ zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private zza() {
        }

        private final Integer[] zza() {
            zzc[] zzcVarArr = zzc.zzg;
            ArrayList arrayList = new ArrayList(zzcVarArr.length);
            for (zzc zzcVar : zzcVarArr) {
                arrayList.add(Integer.valueOf(zzcVar.zza()));
            }
            return (Integer[]) arrayList.toArray(new Integer[0]);
        }

        public final boolean zza(int i) {
            return ArraysKt.contains(zza(), Integer.valueOf(i));
        }
    }
}
