package com.linecorp.linesdk.internal;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.linecorp.linesdk.LineApiResponseCode;
import com.linecorp.linesdk.LoginListener;
import com.linecorp.linesdk.auth.LineAuthenticationParams;
import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class LoginHandler {
    private static int REQUEST_CODE_LOGIN = 1;
    private static String TAG = "LoginHandler";
    private ArrayList<LoginListener> loginListeners = new ArrayList<>();

    private boolean isLoginCanceled(int i, Intent intent) {
        return i != -1 || intent == null;
    }

    public void performLogin(Activity activity, boolean z, String str, LineAuthenticationParams lineAuthenticationParams) {
        activity.startActivityForResult(getLoginIntent(activity, z, str, lineAuthenticationParams), REQUEST_CODE_LOGIN);
    }

    public void performLogin(Activity activity, FragmentWrapper fragmentWrapper, boolean z, String str, LineAuthenticationParams lineAuthenticationParams) {
        fragmentWrapper.startActivityForResult(getLoginIntent(activity, z, str, lineAuthenticationParams), REQUEST_CODE_LOGIN);
    }

    private Intent getLoginIntent(Activity activity, boolean z, String str, LineAuthenticationParams lineAuthenticationParams) {
        if (z) {
            return LineLoginApi.getLoginIntent(activity, str, lineAuthenticationParams);
        }
        return LineLoginApi.getLoginIntentWithoutLineAppAuth(activity, str, lineAuthenticationParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (!isLoginRequestCode(i)) {
            Log.w(TAG, "Unexpected login request code");
            return false;
        }
        if (isLoginCanceled(i2, intent)) {
            Log.w(TAG, "Login failed");
            return false;
        }
        LineLoginResult loginResultFromIntent = LineLoginApi.getLoginResultFromIntent(intent);
        if (isLoginSuccess(loginResultFromIntent)) {
            onLoginSuccess(loginResultFromIntent);
            return true;
        }
        onLoginFailure(loginResultFromIntent);
        return true;
    }

    private boolean isLoginSuccess(LineLoginResult lineLoginResult) {
        return lineLoginResult != null && lineLoginResult.getResponseCode() == LineApiResponseCode.SUCCESS;
    }

    private boolean isLoginRequestCode(int i) {
        return i == REQUEST_CODE_LOGIN;
    }

    private void onLoginFailure(LineLoginResult lineLoginResult) {
        Iterator<LoginListener> it = this.loginListeners.iterator();
        while (it.hasNext()) {
            it.next().onLoginFailure(lineLoginResult);
        }
    }

    private void onLoginSuccess(LineLoginResult lineLoginResult) {
        Iterator<LoginListener> it = this.loginListeners.iterator();
        while (it.hasNext()) {
            it.next().onLoginSuccess(lineLoginResult);
        }
    }

    public void addLoginListener(LoginListener loginListener) {
        this.loginListeners.add(loginListener);
    }

    public void removeLoginListener(LoginListener loginListener) {
        this.loginListeners.remove(loginListener);
    }
}
