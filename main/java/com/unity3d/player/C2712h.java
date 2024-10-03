package com.unity3d.player;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/* renamed from: com.unity3d.player.h */
/* loaded from: classes3.dex */
public final class C2712h implements InterfaceC2709e {
    /* renamed from: a */
    private static boolean m1331a(PackageItemInfo packageItemInfo) {
        try {
            return packageItemInfo.metaData.getBoolean("unityplayer.SkipPermissionsDialog");
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.unity3d.player.InterfaceC2709e
    /* renamed from: a */
    public final void mo1329a(Activity activity, String str) {
        if (activity == null || str == null) {
            return;
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("96489") == null) {
            FragmentC2713i fragmentC2713i = new FragmentC2713i();
            Bundle bundle = new Bundle();
            bundle.putString("PermissionNames", str);
            fragmentC2713i.setArguments(bundle);
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.add(0, fragmentC2713i, "96489");
            beginTransaction.commit();
        }
    }

    @Override // com.unity3d.player.InterfaceC2709e
    /* renamed from: a */
    public final boolean mo1330a(Activity activity) {
        try {
            PackageManager packageManager = activity.getPackageManager();
            ActivityInfo activityInfo = packageManager.getActivityInfo(activity.getComponentName(), 128);
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(activity.getPackageName(), 128);
            if (m1331a(activityInfo)) {
                return true;
            }
            return m1331a(applicationInfo);
        } catch (Exception unused) {
            return false;
        }
    }
}
