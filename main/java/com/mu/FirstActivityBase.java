package com.mu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.Window;
import android.view.WindowManager;
import com.mu.update.HotUpdateMgr;
import com.mu.utility.MuDebug;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class FirstActivityBase extends Activity {
    private AlertDialog dialog;
    protected String[] PrivacyPermissions = new String[0];
    private List<String> permissionList = null;

    public abstract void EnterGame();

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            SetFullScreen();
        }
    }

    public void SetFullScreen() {
        try {
            getWindow().getDecorView().setSystemUiVisibility(5894);
            Window window = getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.systemUiVisibility = 2050;
            window.setAttributes(attributes);
        } catch (Exception unused) {
        }
    }

    public void CheckPermissions() {
        EnterGame();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 321) {
            EnterGame();
        }
    }

    private void showDialogTipUserGoToAppSettting(String str, String str2) {
        this.dialog = new AlertDialog.Builder(this).setTitle(str).setMessage(str2).setPositiveButton("立即开启", new DialogInterface.OnClickListener() { // from class: com.mu.FirstActivityBase.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                FirstActivityBase.this.goToAppSetting();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.mu.FirstActivityBase.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                FirstActivityBase.this.finish();
            }
        }).setCancelable(false).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivityForResult(intent, 123);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        MuDebug.Log("onActivityResult ...................... " + i);
        if (i == 123) {
            EnterGame();
        } else if (i == 100) {
            HotUpdateMgr.getInstance().mUpdateManager.installApk();
        }
    }
}
