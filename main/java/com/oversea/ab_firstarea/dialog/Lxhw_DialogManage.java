package com.oversea.ab_firstarea.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.facebook.share.internal.ShareConstants;
import com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFChooseDialog;
import com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFDiaChatDialog;
import com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFEmailDialog;
import com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFOKDialog;
import com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFOnlineCICreateDialog;
import com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFOnlineCenterDialog;
import com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFShowBitMapDialog;
import com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFShowImageDialog;
import com.oversea.ab_firstarea.dialog.kefu.Lxhw_TInfoDialog;
import com.oversea.ab_firstarea.floatView.FloatView;
import com.oversea.ab_firstarea.haiwai.LoginGooglePlay;
import com.oversea.ab_firstarea.newlogin.dialog.Lxhw_AgreementDialog;
import com.oversea.ab_firstarea.newlogin.dialog.Lxhw_LoginSelectDialog;
import com.oversea.ab_firstarea.newlogin.impl.AgreementDialogCallback;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;
import com.xsdk.ab_firstbase.loading.ILoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_DialogManage {
    private static Lxhw_DialogManage mInstance;
    public Activity activity;
    private DialogFragment dialogFragment;
    private final String TAG = getClass().toString();
    Map<String, String> fragmentNameMap = new HashMap();
    Lxhw_LoginSuccessTip loginSuccessTip = null;

    public void downHead(Activity activity) {
    }

    public void uploadHead(Activity activity) {
    }

    public static Lxhw_DialogManage getInstance() {
        if (mInstance == null) {
            mInstance = new Lxhw_DialogManage();
        }
        return mInstance;
    }

    public synchronized void cancelDialog() {
        try {
            if (Lxhw_XSDK.getInstance().getContext() != null) {
                Lxhw_XSDK.getInstance().getContext().runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_DialogManage.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Lxhw_DialogManage.getInstance().removeFragment(Lxhw_XSDK.getInstance().getContext(), "enterLoadingDialog");
                    }
                });
            }
        } catch (Throwable unused) {
        }
    }

    public void showDialog() {
        try {
            if (Lxhw_XSDK.getInstance().getContext() != null) {
                Lxhw_XSDK.getInstance().getContext().runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_DialogManage.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Lxhw_DialogManage.getInstance().enterLoadingDialog(Lxhw_XSDK.getInstance().getContext(), "");
                    }
                });
            }
        } catch (Throwable th) {
            Log.i(this.TAG, "showDialog :Throwable" + th.toString());
        }
    }

    public void removeFragment(final Context context, final String str) {
        try {
            if (Lxhw_XSDK.getInstance().getContext() != null) {
                Lxhw_XSDK.getInstance().getContext().runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_DialogManage.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Lxhw_DialogManage.this.removeFragmentName(str);
                        FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
                        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(str);
                        if (findFragmentByTag != null) {
                            beginTransaction.remove(findFragmentByTag);
                            beginTransaction.commit();
                        }
                    }
                });
            }
        } catch (Throwable unused) {
        }
    }

    public void removeAllFragment(Context context) {
        try {
            LLog.v_noControl("removeAllFragment");
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            Iterator<Map.Entry<String, String>> it = this.fragmentNameMap.entrySet().iterator();
            while (it.hasNext()) {
                Fragment findFragmentByTag = fragmentManager.findFragmentByTag(it.next().getKey());
                if (findFragmentByTag != null) {
                    beginTransaction.remove(findFragmentByTag);
                    beginTransaction.commit();
                }
            }
            clearAllFragmentName();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void putFragmentName(String str) {
        this.fragmentNameMap.put(str, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeFragmentName(String str) {
        if (this.fragmentNameMap.containsKey(str)) {
            this.fragmentNameMap.remove(str);
        }
    }

    private void clearAllFragmentName() {
        Map<String, String> map = this.fragmentNameMap;
        if (map == null || map.size() <= 0) {
            return;
        }
        this.fragmentNameMap.clear();
    }

    public void checkUpdata(Activity activity, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        activity.startActivity(intent);
    }

    public void showBigTextCommon(Activity activity, String str) {
        try {
            FragmentManager fragmentManager = activity.getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("twbigtext");
            putFragmentName("twbigtext");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_BigTextDialog lxhw_BigTextDialog = new Lxhw_BigTextDialog();
                Bundle bundle = new Bundle();
                bundle.putString(ShareConstants.MEDIA_TYPE, str);
                lxhw_BigTextDialog.setArguments(bundle);
                beginTransaction.add(lxhw_BigTextDialog, "twbigtext");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showTipDialog(Activity activity, String str) {
        try {
            FragmentManager fragmentManager = activity.getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("twTipDialog");
            putFragmentName("twTipDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_TipDialog lxhw_TipDialog = new Lxhw_TipDialog();
                Bundle bundle = new Bundle();
                bundle.putString("tip", str);
                lxhw_TipDialog.setArguments(bundle);
                beginTransaction.add(lxhw_TipDialog, "twTipDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Exception unused) {
        }
    }

    public void showLoginV2(Activity activity) {
        if (Lxhw_AreaPlatform.getInstance().isUseNewSdkLogin) {
            getInstance().showLoginSelectDialog(activity);
        } else {
            showLogin(activity);
        }
    }

    public void showLogin(Activity activity) {
        try {
            FragmentManager fragmentManager = activity.getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("LoginDialog");
            putFragmentName("LoginDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_LoginDialog(), "LoginDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showRegister(Activity activity) {
        try {
            FragmentManager fragmentManager = activity.getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("showRegister");
            putFragmentName("showRegister");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_RegisterDialog(), "showRegister");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showUserCenter(Activity activity) {
        try {
            FragmentManager fragmentManager = activity.getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("UserCenterDialog");
            putFragmentName("UserCenterDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_UserCenterDialog(), "UserCenterDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showCustomerService(final Activity activity) {
        if (activity == null) {
            Log.e(this.TAG, "context. null");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_DialogManage.4
                @Override // java.lang.Runnable
                public void run() {
                    if (Lxhw_XUserInfo.getInstance().getSdkId() != 0) {
                        FragmentManager fragmentManager = activity.getFragmentManager();
                        Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterKFChooseCenter");
                        Lxhw_DialogManage.this.putFragmentName("enterKFChooseCenter");
                        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                        if (findFragmentByTag != null) {
                            beginTransaction.show(findFragmentByTag);
                            return;
                        }
                        Lxhw_KFChooseDialog lxhw_KFChooseDialog = new Lxhw_KFChooseDialog();
                        lxhw_KFChooseDialog.setArguments(new Bundle());
                        beginTransaction.add(lxhw_KFChooseDialog, "enterKFChooseCenter");
                        beginTransaction.commitAllowingStateLoss();
                        return;
                    }
                    Activity activity2 = activity;
                    Toast.makeText(activity, activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "tw_please_login")), 0).show();
                }
            });
        }
    }

    public void showGoogleLogin(Activity activity) {
        ComConstants.CTRL_TYPE = 11;
        if (FloatView.getInstance() != null) {
            FloatView.getInstance().onDestroyFloatView();
        }
        LoginGooglePlay.getInstent().login(activity);
    }

    public void enterForgetPwAccountCenter(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("forgetpwaccountDialog");
            putFragmentName("forgetpwaccountDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_ForgetpwAccountDialog(), "forgetpwaccountDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterForgetPwChooseCenter(Context context, String str, String str2, String str3, String str4) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("forgetpwchooseDialog");
            putFragmentName("forgetpwchooseDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_ForgetpwchooseDialog lxhw_ForgetpwchooseDialog = new Lxhw_ForgetpwchooseDialog();
                Bundle bundle = new Bundle();
                bundle.putString("phone_prefix", str3);
                bundle.putString("mobile", str4);
                bundle.putString("email", str2);
                bundle.putString("uname", str);
                lxhw_ForgetpwchooseDialog.setArguments(bundle);
                beginTransaction.add(lxhw_ForgetpwchooseDialog, "forgetpwchooseDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterForgetPwcodeCenter(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("forgetpwcodeDialog");
            putFragmentName("forgetpwcodeDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_ForgetpwcodeDialog lxhw_ForgetpwcodeDialog = new Lxhw_ForgetpwcodeDialog();
                beginTransaction.add(lxhw_ForgetpwcodeDialog, "forgetpwcodeDialog");
                lxhw_ForgetpwcodeDialog.setArguments(bundle);
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterResetPasswordCenter(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("ResetPasswordDialog");
            putFragmentName("ResetPasswordDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_ResetPasswordDialog lxhw_ResetPasswordDialog = new Lxhw_ResetPasswordDialog();
                lxhw_ResetPasswordDialog.setArguments(bundle);
                beginTransaction.add(lxhw_ResetPasswordDialog, "ResetPasswordDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterChangePsdCenter(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("changepsddialog");
            putFragmentName("changepsddialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_ChangePsdDiaDialog(), "changepsddialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterBindMobileCenter(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("bindmobile");
            putFragmentName("bindmobile");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_MobileConfirmDialog(), "bindmobile");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterBindEmailCenter(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("bindemail");
            putFragmentName("bindemail");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_EmailConfirmDialog(), "bindemail");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterAccountUpgrade(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("accountupgrade");
            putFragmentName("accountupgrade");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_AccountUpgradeDialog(), "accountupgrade");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterAppUpdateCenter(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("appupdate");
            putFragmentName("appupdate");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_AppUpdateDialog(), "appupdate");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterPErrorTip(Context context, String str, String str2) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterPErrorTip");
            putFragmentName("enterPErrorTip");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_PErrorTipDialog lxhw_PErrorTipDialog = new Lxhw_PErrorTipDialog();
                Bundle bundle = new Bundle();
                bundle.putString("popup_tips", str);
                bundle.putString("popup_url", str2);
                lxhw_PErrorTipDialog.setArguments(bundle);
                beginTransaction.add(lxhw_PErrorTipDialog, "enterPErrorTip");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterServerErrorTip(Context context, String str) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterServerErrorTip");
            putFragmentName("enterServerErrorTip");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_ServerErrorTipDialog lxhw_ServerErrorTipDialog = new Lxhw_ServerErrorTipDialog();
                Bundle bundle = new Bundle();
                bundle.putString("popup_tips", str);
                lxhw_ServerErrorTipDialog.setArguments(bundle);
                beginTransaction.add(lxhw_ServerErrorTipDialog, "enterServerErrorTip");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterSimplePwdTip(Context context, String str) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterSimplePwdTip");
            putFragmentName("enterSimplePwdTip");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_SimplePwdTipDialog lxhw_SimplePwdTipDialog = new Lxhw_SimplePwdTipDialog();
                Bundle bundle = new Bundle();
                bundle.putString("popup_tips", str);
                lxhw_SimplePwdTipDialog.setArguments(bundle);
                beginTransaction.add(lxhw_SimplePwdTipDialog, "enterSimplePwdTip");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterChooseRechargeCenter(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("chooserecharge");
            putFragmentName("chooserecharge");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_ChooseRechargeDialog(), "chooserecharge");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void openGpShop(Activity activity) {
        try {
            String packageName = Util.getPackageName(activity);
            if (!TextUtils.isEmpty(packageName) && activity != null && !activity.isFinishing()) {
                String str = "https://play.google.com/store/apps/details?id=" + packageName;
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                activity.startActivity(intent);
            }
            Log.e(this.TAG, "open name null");
        } catch (Throwable unused) {
        }
    }

    public void jumpBrowserUrl(Activity activity, String str) {
        try {
            if (!TextUtils.isEmpty(str) && activity != null && !activity.isFinishing()) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                activity.startActivity(intent);
            }
            Log.e(this.TAG, "jumpBrowserUrl  null");
        } catch (Throwable unused) {
        }
    }

    public void showWebCommon(Context context, String str, String str2) {
        try {
            Lxhw_WebDialog lxhw_WebDialog = new Lxhw_WebDialog();
            FragmentTransaction beginTransaction = ((Activity) context).getFragmentManager().beginTransaction();
            putFragmentName("twwebcommon");
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            bundle.putString(ShareConstants.MEDIA_TYPE, str2);
            lxhw_WebDialog.setArguments(bundle);
            beginTransaction.add(lxhw_WebDialog, "twwebcommon");
            beginTransaction.commitAllowingStateLoss();
        } catch (Throwable unused) {
        }
    }

    public void showWebFullScreenCommon(Context context, String str, String str2) {
        try {
            Lxhw_WebFullScreenDialog lxhw_WebFullScreenDialog = new Lxhw_WebFullScreenDialog();
            FragmentTransaction beginTransaction = ((Activity) context).getFragmentManager().beginTransaction();
            putFragmentName("twwebcommon");
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            bundle.putString(ShareConstants.MEDIA_TYPE, str2);
            lxhw_WebFullScreenDialog.setArguments(bundle);
            beginTransaction.add(lxhw_WebFullScreenDialog, "twwebcommon");
            beginTransaction.commitAllowingStateLoss();
        } catch (Throwable unused) {
        }
    }

    public void showGiftCenter(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("giftCenterDialog");
            putFragmentName("giftCenterDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_GiftCenterDialog(), "giftCenterDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterKFChooseCenter(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterKFChooseCenter");
            putFragmentName("enterKFChooseCenter");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_KFChooseDialog lxhw_KFChooseDialog = new Lxhw_KFChooseDialog();
                lxhw_KFChooseDialog.setArguments(new Bundle());
                beginTransaction.add(lxhw_KFChooseDialog, "enterKFChooseCenter");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterKFEmailDialog(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterKFEmailDialog");
            putFragmentName("enterKFEmailDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_KFEmailDialog lxhw_KFEmailDialog = new Lxhw_KFEmailDialog();
                lxhw_KFEmailDialog.setArguments(bundle);
                beginTransaction.add(lxhw_KFEmailDialog, "enterKFEmailDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterKFOnlineCenter(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterKFOnlineCenter");
            putFragmentName("enterKFOnlineCenter");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_KFOnlineCenterDialog lxhw_KFOnlineCenterDialog = new Lxhw_KFOnlineCenterDialog();
                lxhw_KFOnlineCenterDialog.setArguments(new Bundle());
                beginTransaction.add(lxhw_KFOnlineCenterDialog, "enterKFOnlineCenter");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterKFOnlineCICreate(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterKFOnlineCICreate");
            putFragmentName("enterKFOnlineCICreate");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_KFOnlineCICreateDialog lxhw_KFOnlineCICreateDialog = new Lxhw_KFOnlineCICreateDialog();
                lxhw_KFOnlineCICreateDialog.setArguments(new Bundle());
                beginTransaction.add(lxhw_KFOnlineCICreateDialog, "enterKFOnlineCICreate");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterKFShowImageDialog(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterKFShowImageDialog");
            putFragmentName("enterKFShowImageDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_KFShowImageDialog lxhw_KFShowImageDialog = new Lxhw_KFShowImageDialog();
                lxhw_KFShowImageDialog.setArguments(bundle);
                beginTransaction.add(lxhw_KFShowImageDialog, "enterKFShowImageDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterKFDiaChatDialog(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterKFDiaChatDialog");
            putFragmentName("enterKFDiaChatDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_KFDiaChatDialog lxhw_KFDiaChatDialog = new Lxhw_KFDiaChatDialog();
                lxhw_KFDiaChatDialog.setArguments(bundle);
                beginTransaction.add(lxhw_KFDiaChatDialog, "enterKFDiaChatDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterKFOKDialog(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("KFOKDialog");
            putFragmentName("KFOKDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_KFOKDialog lxhw_KFOKDialog = new Lxhw_KFOKDialog();
                lxhw_KFOKDialog.setArguments(bundle);
                beginTransaction.add(lxhw_KFOKDialog, "KFOKDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterTInfo(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterTInfo");
            putFragmentName("enterTInfo");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_TInfoDialog lxhw_TInfoDialog = new Lxhw_TInfoDialog();
                lxhw_TInfoDialog.setArguments(new Bundle());
                beginTransaction.add(lxhw_TInfoDialog, "enterTInfo");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterChangeLanguageDialog(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterChangeLanguageDialog");
            putFragmentName("enterChangeLanguageDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_ChangeLanguageDialog lxhw_ChangeLanguageDialog = new Lxhw_ChangeLanguageDialog();
                lxhw_ChangeLanguageDialog.setArguments(bundle);
                beginTransaction.add(lxhw_ChangeLanguageDialog, "enterChangeLanguageDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showDelAccountDialog(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("delAccountDialog");
            putFragmentName("delAccountDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
                return;
            }
            Lxhw_DelAccountDialog lxhw_DelAccountDialog = new Lxhw_DelAccountDialog();
            if (bundle != null) {
                lxhw_DelAccountDialog.setArguments(bundle);
            }
            beginTransaction.add(lxhw_DelAccountDialog, "delAccountDialog");
            beginTransaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showGdprDialog(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("gdprDialog");
            putFragmentName("gdprDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
                return;
            }
            lxhw_GdprDialog lxhw_gdprdialog = new lxhw_GdprDialog();
            if (bundle != null) {
                lxhw_gdprdialog.setArguments(bundle);
            }
            beginTransaction.add(lxhw_gdprdialog, "gdprDialog");
            beginTransaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showRollbackAccountDialog(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            putFragmentName("rollbackAccountDialog");
            if (this.dialogFragment == null) {
                this.dialogFragment = new Lxhw_RollbackAccountDialog();
            }
            if (bundle != null) {
                this.dialogFragment.setArguments(bundle);
            }
            DialogFragment dialogFragment = this.dialogFragment;
            if (dialogFragment == null || dialogFragment.isAdded() || this.dialogFragment.isVisible() || this.dialogFragment.isRemoving() || this.dialogFragment.getTag() != null) {
                return;
            }
            fragmentManager.beginTransaction().add(this.dialogFragment, "rollbackAccountDialog").commitAllowingStateLoss();
        } catch (Exception unused) {
        }
    }

    public void showCountDownDialog(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("countDownDialog");
            putFragmentName("countDownDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
                return;
            }
            Lxhw_CountDownDialog lxhw_CountDownDialog = new Lxhw_CountDownDialog();
            if (bundle != null) {
                lxhw_CountDownDialog.setArguments(bundle);
            }
            beginTransaction.add(lxhw_CountDownDialog, "countDownDialog");
            beginTransaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterKFShowBitMapDialog(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("KFShowBitMapDialog");
            putFragmentName("KFShowBitMapDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_KFShowBitMapDialog lxhw_KFShowBitMapDialog = new Lxhw_KFShowBitMapDialog();
                lxhw_KFShowBitMapDialog.setArguments(bundle);
                beginTransaction.add(lxhw_KFShowBitMapDialog, "KFShowBitMapDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void enterLoadingDialog(Context context, String str) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterLoadingDialog");
            putFragmentName("enterLoadingDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                ILoadingDialog iLoadingDialog = new ILoadingDialog();
                Bundle bundle = new Bundle();
                bundle.putString(NotificationCompat.CATEGORY_MESSAGE, str);
                iLoadingDialog.setArguments(bundle);
                beginTransaction.add(iLoadingDialog, "enterLoadingDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showShouquan(Activity activity, String str) {
        Lxhw_ShouquanDialog lxhw_ShouquanDialog = new Lxhw_ShouquanDialog();
        FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString(ShareConstants.MEDIA_TYPE, str);
        lxhw_ShouquanDialog.setArguments(bundle);
        beginTransaction.add(lxhw_ShouquanDialog, "ShouquanDialog");
        beginTransaction.commitAllowingStateLoss();
    }

    public void enterGmtUTCDialog(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("enterGmtUTCDialog");
            putFragmentName("enterGmtUTCDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_GmtUTCDialog(), "enterGmtUTCDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showLoginSuccessTip() {
        this.loginSuccessTip = new Lxhw_LoginSuccessTip(Lxhw_XSDK.getInstance().getContext());
    }

    public void removeLoginSuccessTip() {
        Lxhw_LoginSuccessTip lxhw_LoginSuccessTip = this.loginSuccessTip;
        if (lxhw_LoginSuccessTip != null) {
            lxhw_LoginSuccessTip.removeTip();
        }
    }

    public void setGameLanguage(Activity activity, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("languageType", i);
        enterChangeLanguageDialog(activity, bundle);
    }

    public void showAgreementDialog(Context context, AgreementDialogCallback agreementDialogCallback) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("agreementDialog");
            putFragmentName("agreementDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_AgreementDialog lxhw_AgreementDialog = new Lxhw_AgreementDialog();
                lxhw_AgreementDialog.setAgreementCallback(agreementDialogCallback);
                beginTransaction.add(lxhw_AgreementDialog, "agreementDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Exception unused) {
        }
    }

    public void showLoginSelectDialog(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("loginSelectDialog");
            putFragmentName("loginSelectDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new Lxhw_LoginSelectDialog(), "loginSelectDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Exception unused) {
        }
    }

    public void showUpDataUserInfoVI(Context context) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("showUpDataUserInfoVI");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                beginTransaction.add(new UpData_UserInfo_VI(), "showUpDataUserInfoVI");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showMyCardDialog(Context context, Bundle bundle) {
        try {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("showMyCardDialog");
            putFragmentName("showMyCardDialog");
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (findFragmentByTag != null) {
                beginTransaction.show(findFragmentByTag);
            } else {
                Lxhw_MyCardDialog lxhw_MyCardDialog = new Lxhw_MyCardDialog();
                lxhw_MyCardDialog.setArguments(bundle);
                beginTransaction.add(lxhw_MyCardDialog, "showMyCardDialog");
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Exception unused) {
        }
    }
}
