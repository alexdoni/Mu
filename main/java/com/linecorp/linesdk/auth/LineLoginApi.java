package com.linecorp.linesdk.auth;

import android.content.Context;
import android.content.Intent;
import com.linecorp.linesdk.auth.LineAuthenticationConfig;
import com.linecorp.linesdk.auth.internal.LineAuthenticationActivity;
import com.linecorp.linesdk.internal.EncryptorHolder;

/* loaded from: classes2.dex */
public class LineLoginApi {
    private LineLoginApi() {
    }

    public static Intent getLoginIntent(Context context, String str, LineAuthenticationParams lineAuthenticationParams) {
        return getLoginIntent(context, new LineAuthenticationConfig.Builder(str).build(), lineAuthenticationParams);
    }

    public static Intent getLoginIntentWithoutLineAppAuth(Context context, String str, LineAuthenticationParams lineAuthenticationParams) {
        return getLoginIntent(context, new LineAuthenticationConfig.Builder(str).disableLineAppAuthentication().build(), lineAuthenticationParams);
    }

    public static Intent getLoginIntent(Context context, LineAuthenticationConfig lineAuthenticationConfig, LineAuthenticationParams lineAuthenticationParams) {
        if (!lineAuthenticationConfig.isEncryptorPreparationDisabled()) {
            EncryptorHolder.initializeOnWorkerThread(context);
        }
        return LineAuthenticationActivity.getLoginIntent(context, lineAuthenticationConfig, lineAuthenticationParams);
    }

    public static LineLoginResult getLoginResultFromIntent(Intent intent) {
        if (intent == null) {
            return LineLoginResult.internalError("Callback intent is null");
        }
        return LineAuthenticationActivity.getResultFromIntent(intent);
    }
}
