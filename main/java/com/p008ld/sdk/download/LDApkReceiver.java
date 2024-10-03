package com.p008ld.sdk.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.zzj;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDApkReceiver.kt */
/* loaded from: classes2.dex */
public final class LDApkReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String schemeSpecificPart;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Uri data = intent.getData();
        if (data != null) {
            try {
                schemeSpecificPart = context.getPackageManager().getPackageInfo(data.getSchemeSpecificPart(), 0).packageName;
            } catch (Exception unused) {
                schemeSpecificPart = data.getSchemeSpecificPart();
            }
            if (TextUtils.equals(intent.getAction(), "android.intent.action.PACKAGE_ADDED")) {
                LDLog.m573e("LDApkReceiver-> ACTION_PACKAGE_ADDED: " + schemeSpecificPart);
                LDLog.m573e("LDApkReceiver-> delete state:" + zzj.zza.zzf(new File(zza.zza(context))));
            }
        }
    }
}
