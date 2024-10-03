package com.p008ld.sdk.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.linecorp.linesdk.BuildConfig;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.p009ui.zzb.zzj;
import com.p008ld.sdk.p009ui.zzb.zzl;
import com.xsdk.ab_firstbase.statisics.util.Constants;

/* compiled from: LdDialogHelper.java */
/* loaded from: classes2.dex */
public class zzn {
    public static void zza(final Context context, final String str) {
        new zzl(context).zza(zzt.zza(context, "ld_hint_text")).zzb(String.format(zzt.zza(context, "ld_app_no_install_text"), str.equals(BuildConfig.LINE_APP_PACKAGE_NAME) ? "Line" : Constants.SDK_ACCOUNT_TYPE_FACEBOOK)).zzc(zzt.zza(context, "ld_to_to_download_text")).zza().zza(new LDCallback1<Boolean>() { // from class: com.ld.sdk.util.zzn.1
            @Override // com.p008ld.sdk.internal.LDCallback1
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(Boolean bool) {
                if (bool.booleanValue()) {
                    LDUtil.startGooglePlay(context, str);
                }
            }
        }).show();
    }

    public static Dialog zza(Context context, boolean z) {
        final zzj zzjVar = new zzj(context, false);
        zzjVar.setCancelable(z);
        zzjVar.setCanceledOnTouchOutside(false);
        zzjVar.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.ld.sdk.util.zzn.2
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                zzj zzjVar2 = zzj.this;
                if (zzjVar2 != null) {
                    zzjVar2.zzb();
                }
            }
        });
        zzjVar.show();
        zzjVar.zza();
        return zzjVar;
    }
}
