package com.facebook.login;

import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import kotlin.Metadata;

/* compiled from: LoginManager.kt */
@Metadata(m1394d1 = {"\u0000\u0019\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, m1395d2 = {"com/facebook/login/LoginManager$AndroidxActivityResultRegistryOwnerStartActivityDelegate$startActivityForResult$LauncherHolder", "", "()V", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "getLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "setLauncher", "(Landroidx/activity/result/ActivityResultLauncher;)V", "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* renamed from: com.facebook.login.LoginManager$AndroidxActivityResultRegistryOwnerStartActivityDelegate$startActivityForResult$LauncherHolder */
/* loaded from: classes.dex */
public final class C0847x7971582c {
    private ActivityResultLauncher<Intent> launcher;

    public final ActivityResultLauncher<Intent> getLauncher() {
        return this.launcher;
    }

    public final void setLauncher(ActivityResultLauncher<Intent> activityResultLauncher) {
        this.launcher = activityResultLauncher;
    }
}
