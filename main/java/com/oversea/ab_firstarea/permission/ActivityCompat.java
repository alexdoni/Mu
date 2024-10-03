package com.oversea.ab_firstarea.permission;

import android.app.Activity;
import android.content.pm.PackageManager;

/* loaded from: classes.dex */
public class ActivityCompat {

    /* loaded from: classes.dex */
    public interface OnRequestPermissionsResultCallback {
        void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);
    }

    public static void requestPermissions(Activity activity, String[] strArr, int i) {
        ActivityCompatApi23.requestPermissions(activity, strArr, i);
    }

    /* renamed from: com.oversea.ab_firstarea.permission.ActivityCompat$1 */
    /* loaded from: classes.dex */
    class RunnableC24771 implements Runnable {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ String[] val$permissions;
        final /* synthetic */ int val$requestCode;

        RunnableC24771(String[] strArr, Activity activity, int i) {
            this.val$permissions = strArr;
            this.val$activity = activity;
            this.val$requestCode = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int[] iArr = new int[this.val$permissions.length];
            PackageManager packageManager = this.val$activity.getPackageManager();
            String packageName = this.val$activity.getPackageName();
            int length = this.val$permissions.length;
            for (int i = 0; i < length; i++) {
                iArr[i] = packageManager.checkPermission(this.val$permissions[i], packageName);
            }
            ((OnRequestPermissionsResultCallback) this.val$activity).onRequestPermissionsResult(this.val$requestCode, this.val$permissions, iArr);
        }
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
        return ActivityCompatApi23.shouldShowRequestPermissionRationale(activity, str);
    }
}
