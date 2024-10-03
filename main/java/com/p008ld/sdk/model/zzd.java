package com.p008ld.sdk.model;

import android.content.Context;
import com.luck.picture.lib.permissions.PermissionConfig;
import com.p008ld.sdk.util.zzj;
import java.io.File;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDSidDirProvider.kt */
/* loaded from: classes2.dex */
public final class zzd {
    public static final zza zza = new zza(null);
    private static volatile boolean zzc;
    private final Context zzb;

    public zzd(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        this.zzb = applicationContext;
    }

    public final File zza() {
        File zzc2 = zzc();
        if (zzj.zza(zzc2)) {
            return zzc2;
        }
        if (zzb()) {
            zzj.zza.zzb();
        }
        return null;
    }

    private final boolean zzb() {
        if (zzc) {
            return true;
        }
        zzc = this.zzb.checkSelfPermission(PermissionConfig.WRITE_EXTERNAL_STORAGE) == 0;
        return zzc;
    }

    private final File zzc() {
        return new File(zzj.zza.zza(), "ldsdk");
    }

    /* compiled from: LDSidDirProvider.kt */
    /* loaded from: classes2.dex */
    public static final class zza {
        public /* synthetic */ zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private zza() {
        }
    }
}
