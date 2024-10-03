package com.oversea.ab_firstarea.haiwai;

import android.app.Activity;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.loading.LoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class LoginGooglePlay {
    public static final int RC_SIGN_IN = 9001;
    public static boolean googleserviceFlag = true;
    public static LoginGooglePlay instent;
    private static GoogleSignInClient mGoogleSignInClient;
    private static GoogleApiClient mgoogleApiClient;
    private GoogleApiAvailability googleApiAvailability;
    private Activity mActivity;

    public static void signIn() {
    }

    public static LoginGooglePlay getInstent() {
        if (instent == null) {
            synchronized (LoginGooglePlay.class) {
                if (instent == null) {
                    instent = new LoginGooglePlay();
                }
            }
        }
        return instent;
    }

    public void init(Activity activity) {
        int game_id;
        String string;
        GoogleSignInOptions build;
        this.mActivity = activity;
        try {
            game_id = Lxhw_AppInfoDecorator.getGame_id();
            LLog.i_Control("LoginGooglePlay-gameId: " + game_id);
            string = Util.getString(Lxhw_XSDK.getInstance().getContext(), "default_web_client_id");
        } catch (Throwable th) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_LOGIN_ERROR, "google login init throwable: " + th.toString());
            LLog.e_noControl("google login init try catch Throwable: " + th.toString());
        }
        if (game_id != 1 && game_id != 9 && game_id != 12 && !TextUtils.isEmpty(string)) {
            LLog.i_Control("LoginGooglePlay-web_client_id: " + string);
            build = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(string).requestEmail().build();
            mGoogleSignInClient = GoogleSignIn.getClient(this.mActivity, build);
            initgoogleAvailability();
        }
        build = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this.mActivity, build);
        initgoogleAvailability();
    }

    private void initgoogleAvailability() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        this.googleApiAvailability = googleApiAvailability;
        if (googleApiAvailability.isGooglePlayServicesAvailable(this.mActivity) == 0) {
            googleserviceFlag = true;
        } else {
            googleserviceFlag = false;
        }
    }

    public void login(Activity activity) {
        ComConstants.CTRL_TYPE = 11;
        if (googleserviceFlag) {
            if (activity == null && (activity = this.mActivity) == null) {
                activity = Lxhw_XSDK.getInstance().getContext();
            }
            GooglePlayControl.account = GoogleSignIn.getLastSignedInAccount(activity);
            if (GooglePlayControl.account != null && !GooglePlayControl.account.isExpired()) {
                LLog.i_noControl("google login account != null && not isExpired");
                GooglePlayControl.getInstance().updateUI();
                return;
            } else {
                LLog.i_noControl("google login oauth");
                activity.startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
                return;
            }
        }
        LoadingDialog.cancelDialogForLoading();
        Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(this.mActivity, ComConfig.PC_FF_GOOGLE_API_UNAVAILABLE, "1");
        ToastUtils.toastLongShow(this.mActivity, this.mActivity.getString(Util.getIdByName(Lxhw_XSDK.getInstance().getContext(), TypedValues.Custom.S_STRING, "tw_google_no_server")));
        initgoogleAvailability();
    }

    public void signOut() {
        GoogleSignInClient googleSignInClient;
        LLog.v_Control("Lhwl_LoginGooglePlay signOut0");
        if (!googleserviceFlag || (googleSignInClient = mGoogleSignInClient) == null) {
            return;
        }
        try {
            googleSignInClient.signOut().addOnCompleteListener(Lxhw_XSDK.getInstance().getContext(), new OnCompleteListener<Void>() { // from class: com.oversea.ab_firstarea.haiwai.LoginGooglePlay.1
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<Void> task) {
                    LLog.v_Control("Lhwl_LoginGooglePlay signOut");
                }
            });
        } catch (Throwable unused) {
        }
    }

    public void revokeAccess() {
        if (mGoogleSignInClient == null || this.mActivity == null) {
            return;
        }
        LLog.i_noControl("google account revokeAccess");
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this.mActivity, new OnCompleteListener<Void>() { // from class: com.oversea.ab_firstarea.haiwai.LoginGooglePlay.2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                LLog.i_noControl("revokeAccess:断开账户");
            }
        });
    }
}
