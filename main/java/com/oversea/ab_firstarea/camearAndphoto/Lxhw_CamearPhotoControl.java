package com.oversea.ab_firstarea.camearAndphoto;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import com.luck.picture.lib.permissions.PermissionConfig;
import com.oversea.ab_firstarea.camearAndphoto.Lxhw_CamearCallBack;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.io.File;

/* loaded from: classes.dex */
public class Lxhw_CamearPhotoControl {
    private static final int OUTPUT_X = 480;
    private static final int OUTPUT_Y = 480;
    private static Lxhw_CamearPhotoControl mInstance;
    Bitmap camearBitmap;
    private Uri cropImageUri;
    private Uri imageUri;
    private Activity mActivity;
    private String permission;
    private String TAG = "CamearPhotoControl";
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Lxhw_CamearCallBack.CamearCallBackListener mCamearCallBackListener = null;

    public static boolean hasSdcard() {
        return true;
    }

    private void showImages(Bitmap bitmap) {
    }

    private Lxhw_CamearPhotoControl() {
    }

    public static Lxhw_CamearPhotoControl getInstance() {
        if (mInstance == null) {
            synchronized (Lxhw_CamearPhotoControl.class) {
                if (mInstance == null) {
                    mInstance = new Lxhw_CamearPhotoControl();
                }
            }
        }
        return mInstance;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 11) {
            if (i != 12) {
                return;
            }
            if (iArr.length > 0 && iArr[0] == 0) {
                ComConstants.CTRL_TYPE = 22;
                Lxhw_PhotoUtils.openPic(this.mActivity, 22);
                return;
            } else {
                Activity activity = this.mActivity;
                Lxhw_ToastUtils.showShort(this.mActivity, activity.getString(Util.getIdByName(activity, TypedValues.Custom.S_STRING, "shouquan_zx_xz_readsdcard")));
                return;
            }
        }
        if (iArr.length > 0 && iArr[0] == 0) {
            if (hasSdcard()) {
                this.imageUri = Uri.fromFile(this.fileUri);
                if (Build.VERSION.SDK_INT >= 24) {
                    this.imageUri = FileProvider.getUriForFile(this.mActivity, "com.zz.fileprovider", this.fileUri);
                }
                ComConstants.CTRL_TYPE = 21;
                Lxhw_PhotoUtils.takePicture(this.mActivity, this.imageUri, 21);
                return;
            }
            Activity activity2 = this.mActivity;
            Lxhw_ToastUtils.showShort(this.mActivity, activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "shouquan_zx_xz_nosdcard")));
            return;
        }
        Activity activity3 = this.mActivity;
        Lxhw_ToastUtils.showShort(this.mActivity, activity3.getString(Util.getIdByName(activity3, TypedValues.Custom.S_STRING, "shouquan_zx_xz_permission_turn_cammera")));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 21:
                    Log.v(this.TAG, "**CODE_CAMERA_REQUEST");
                    ComConstants.CTRL_TYPE = 23;
                    Uri fromFile = Uri.fromFile(this.fileCropUri);
                    this.cropImageUri = fromFile;
                    Lxhw_PhotoUtils.cropImageUri(this.mActivity, this.imageUri, fromFile, 1, 1, 480, 480, 23);
                    return;
                case 22:
                    if (hasSdcard()) {
                        Log.v(this.TAG, "**hasSdcard");
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.mActivity.getContentResolver(), intent.getData());
                            if (this.mCamearCallBackListener != null) {
                                Log.v(this.TAG, "**mCamebacklistener");
                                this.mCamearCallBackListener.onCCBResult(intent.getData(), bitmap);
                            } else {
                                Log.v(this.TAG, "**mCamebacklistener no");
                            }
                            return;
                        } catch (Throwable th) {
                            th.printStackTrace();
                            return;
                        }
                    }
                    Log.v(this.TAG, "**hasSdcard no");
                    Activity activity = this.mActivity;
                    Lxhw_ToastUtils.showShort(this.mActivity, activity.getString(Util.getIdByName(activity, TypedValues.Custom.S_STRING, "shouquan_zx_xz_nosdcard")));
                    return;
                case 23:
                    Log.v(this.TAG, "**CODE_RESULT_REQUEST");
                    return;
                default:
                    return;
            }
        }
    }

    private void initPerStr() {
        if (this.mActivity != null && Build.VERSION.SDK_INT >= 33 && this.mActivity.getApplicationInfo().targetSdkVersion >= 33) {
            this.permission = PermissionConfig.READ_MEDIA_IMAGES;
        } else {
            this.permission = PermissionConfig.WRITE_EXTERNAL_STORAGE;
        }
    }

    public static boolean isAppFirstRun(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("camearphotoconfig", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("camearphotofirst_run", true)) {
            edit.putBoolean("camearphotofirst_run", false);
            edit.commit();
            return true;
        }
        edit.putBoolean("camearphotofirst_run", false);
        edit.commit();
        return false;
    }

    public void autoObtainStoragePermission(Activity activity, Lxhw_CamearCallBack.CamearCallBackListener camearCallBackListener) {
        this.mActivity = activity;
        this.mCamearCallBackListener = camearCallBackListener;
        ComConstants.CTRL_TYPE = 22;
        Lxhw_PhotoUtils.openPic(this.mActivity, 22);
    }

    public void requestPermissionsReadAndWriteExt() {
        ActivityCompat.requestPermissions(this.mActivity, new String[]{this.permission}, 12);
    }
}
