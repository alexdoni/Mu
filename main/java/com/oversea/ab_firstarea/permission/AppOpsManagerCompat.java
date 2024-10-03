package com.oversea.ab_firstarea.permission;

import android.content.Context;

/* loaded from: classes.dex */
public final class AppOpsManagerCompat {
    private static final AppOpsManagerImpl IMPL = new AppOpsManager23();
    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_IGNORED = 1;

    /* loaded from: classes.dex */
    private static class AppOpsManagerImpl {
        public int noteOp(Context context, String str, int i, String str2) {
            return 1;
        }

        public int noteProxyOp(Context context, String str, String str2) {
            return 1;
        }

        public String permissionToOp(String str) {
            return null;
        }

        AppOpsManagerImpl() {
        }
    }

    /* loaded from: classes.dex */
    private static class AppOpsManager23 extends AppOpsManagerImpl {
        AppOpsManager23() {
        }

        @Override // com.oversea.ab_firstarea.permission.AppOpsManagerCompat.AppOpsManagerImpl
        public String permissionToOp(String str) {
            return AppOpsManagerCompat23.permissionToOp(str);
        }

        @Override // com.oversea.ab_firstarea.permission.AppOpsManagerCompat.AppOpsManagerImpl
        public int noteOp(Context context, String str, int i, String str2) {
            return AppOpsManagerCompat23.noteOp(context, str, i, str2);
        }

        @Override // com.oversea.ab_firstarea.permission.AppOpsManagerCompat.AppOpsManagerImpl
        public int noteProxyOp(Context context, String str, String str2) {
            return AppOpsManagerCompat23.noteProxyOp(context, str, str2);
        }
    }

    private AppOpsManagerCompat() {
    }

    public static String permissionToOp(String str) {
        return IMPL.permissionToOp(str);
    }

    public static int noteProxyOp(Context context, String str, String str2) {
        return IMPL.noteProxyOp(context, str, str2);
    }
}
