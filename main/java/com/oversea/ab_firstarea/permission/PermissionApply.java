package com.oversea.ab_firstarea.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.luck.picture.lib.permissions.PermissionConfig;
import com.oversea.ab_firstarea.haiwai.GooglePlayControl;
import com.oversea.ab_firstarea.permission.PermissionCallBack;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PermissionApply {
    public static final int CAMERA_PERMISSIONS_REQUEST_CODE = 11;
    private static final int MY_PERMISSIONS_REQUEST_CALL_CAMERA = 2;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private static final int REQUEST_PERMISSION_SETTING = 2;
    private static final int REQUEST_STATUS_CODE = 1;
    public static final int STORAGE_PERMISSIONS_REQUEST_CODE = 12;
    public static PermissionApply mInstance;
    private Activity mActivity;
    private String TAG = "PermissionApply";
    private boolean ishaveAllAllow = true;
    public String[] permissions = {PermissionConfig.WRITE_EXTERNAL_STORAGE, PermissionConfig.READ_EXTERNAL_STORAGE};
    private List<String> mPermissionList = new ArrayList();
    public String[] permissionsTemp = new String[0];
    public boolean isNeedExit = true;

    public static PermissionApply getInstance() {
        if (mInstance == null) {
            mInstance = new PermissionApply();
        }
        return mInstance;
    }

    public void initPermiss(Activity activity, PermissionCallBack.PermissionCallBackListener permissionCallBackListener) {
        this.mActivity = activity;
        PermissionCallBack.getInstance().setOversCallback(activity, permissionCallBackListener);
        if (checkSDkMustP(activity)) {
            PermissionCallBack.getInstance().callBackOnPCBResult(true);
        } else {
            checkAll(activity);
        }
    }

    public void setPermissions(String[] strArr) {
        this.permissions = strArr;
    }

    public boolean checkSDkMustP(Activity activity) {
        this.mActivity = activity;
        int i = 0;
        while (true) {
            String[] strArr = this.permissions;
            if (i >= strArr.length) {
                return true;
            }
            if (ContextCompat.checkSelfPermission(activity, strArr[i]) != 0) {
                LLog.v_noControl("checkSDkMustP=" + this.permissions[i]);
                return false;
            }
            i++;
        }
    }

    private void checkOne(Activity activity, String str) {
        this.mActivity = activity;
        if (ContextCompat.checkSelfPermission(activity, str) != 0) {
            ActivityCompat.requestPermissions(activity, new String[]{str}, 1);
        }
    }

    public boolean checkHaveOne(Activity activity, String str) {
        this.mActivity = activity;
        for (int i = 0; i < this.permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, str) != 0) {
                Log.v(this.TAG, "checkSDkMustP=" + str);
                return false;
            }
        }
        return true;
    }

    public void initPermission() {
        getInstance().setPermissions(new String[]{PermissionConfig.WRITE_EXTERNAL_STORAGE, PermissionConfig.READ_EXTERNAL_STORAGE});
    }

    public void checkStartMustPermission(Activity activity, PermissionCallBack.PermissionCallBackListener permissionCallBackListener) {
        this.mActivity = activity;
        PermissionCallBack.getInstance().setOversCallback(activity, permissionCallBackListener);
        if (!checkSDkMustP(activity)) {
            GooglePlayControl.getInstance().getGAid();
        } else {
            PermissionCallBack.getInstance().callBackOnPCBResult(true);
        }
    }

    public boolean checkAll(Activity activity) {
        this.mActivity = activity;
        LLog.v_noControl("申请权限总数 permissions.length=" + this.permissions.length);
        this.mPermissionList.clear();
        int i = 0;
        while (true) {
            String[] strArr = this.permissions;
            if (i >= strArr.length) {
                break;
            }
            if (ContextCompat.checkSelfPermission(activity, strArr[i]) != 0) {
                this.mPermissionList.add(this.permissions[i]);
            }
            i++;
        }
        Log.v(this.TAG, "需要申请权限个数 mPermissionList.size=" + this.mPermissionList.size());
        if (this.mPermissionList.isEmpty()) {
            LLog.v_noControl("已经授权");
            return true;
        }
        List<String> list = this.mPermissionList;
        ActivityCompat.requestPermissions(activity, (String[]) list.toArray(new String[list.size()]), 2);
        return false;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1) {
            Activity activity = this.mActivity;
            if (activity != null) {
                String string = activity.getString(Util.getIdByName(activity, TypedValues.Custom.S_STRING, "tw_permission_wushenqing"));
                if (iArr[0] == 0) {
                    showToast(string);
                    return;
                } else {
                    showToast(string);
                    return;
                }
            }
            return;
        }
        if (i == 2) {
            if (!this.isNeedExit) {
                PermissionCallBack.getInstance().callBackOnPCBResult(true);
                return;
            }
            if (checkSDkMustP(this.mActivity)) {
                PermissionCallBack.getInstance().callBackOnPCBResult(true);
                return;
            }
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (iArr[i2] == -1) {
                    if (checkSDkMustP(this.mActivity)) {
                        PermissionCallBack.getInstance().callBackOnPCBResult(true);
                        return;
                    }
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this.mActivity, strArr[i2])) {
                        Activity activity2 = this.mActivity;
                        String string2 = activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "tw_permission_tip"));
                        Activity activity3 = this.mActivity;
                        showMessage(this.mActivity, string2, activity3.getString(Util.getIdByName(activity3, TypedValues.Custom.S_STRING, "tw_permission_content1")));
                        return;
                    }
                    if (!isAppFirstRun(this.mActivity)) {
                        Activity activity4 = this.mActivity;
                        String string3 = activity4.getString(Util.getIdByName(activity4, TypedValues.Custom.S_STRING, "tw_permission_tip"));
                        Activity activity5 = this.mActivity;
                        showMessage2(this.mActivity, string3, activity5.getString(Util.getIdByName(activity5, TypedValues.Custom.S_STRING, "tw_permission_content2")));
                        return;
                    }
                    Activity activity6 = this.mActivity;
                    String string4 = activity6.getString(Util.getIdByName(activity6, TypedValues.Custom.S_STRING, "tw_permission_tip"));
                    Activity activity7 = this.mActivity;
                    showMessage(this.mActivity, string4, activity7.getString(Util.getIdByName(activity7, TypedValues.Custom.S_STRING, "tw_permission_content1")));
                    return;
                }
            }
        }
    }

    private void showToast(String str) {
        Toast.makeText(this.mActivity, str, 1).show();
    }

    public void showMessage(Activity activity, String str, String str2) {
        new AlertDialog.Builder(activity).setTitle(str).setMessage(str2).setPositiveButton(activity.getString(Util.getIdByName(activity, TypedValues.Custom.S_STRING, "tw_show_ensure")), new DialogInterface.OnClickListener() { // from class: com.oversea.ab_firstarea.permission.PermissionApply.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                PermissionApply.this.closeApp();
            }
        }).show();
    }

    private void showMessage2(Activity activity, String str, String str2) {
        new AlertDialog.Builder(activity).setTitle(str).setMessage(str2).setPositiveButton(activity.getString(Util.getIdByName(activity, TypedValues.Custom.S_STRING, "tw_show_ensure")), new DialogInterface.OnClickListener() { // from class: com.oversea.ab_firstarea.permission.PermissionApply.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", PermissionApply.this.mActivity.getPackageName(), null));
                PermissionApply.this.mActivity.startActivityForResult(intent, 2);
                PermissionApply.this.closeApp();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeApp() {
        new Handler().postDelayed(new Runnable() { // from class: com.oversea.ab_firstarea.permission.PermissionApply.3
            @Override // java.lang.Runnable
            public void run() {
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        }, 500L);
    }

    public static boolean isAppFirstRun(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("config", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("first_run", true)) {
            edit.putBoolean("first_run", false);
            edit.commit();
            return true;
        }
        edit.putBoolean("first_run", false);
        edit.commit();
        return false;
    }
}
