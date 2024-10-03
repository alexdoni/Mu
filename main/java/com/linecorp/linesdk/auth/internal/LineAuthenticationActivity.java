package com.linecorp.linesdk.auth.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.linecorp.linesdk.C2028R;
import com.linecorp.linesdk.auth.LineAuthenticationConfig;
import com.linecorp.linesdk.auth.LineAuthenticationParams;
import com.linecorp.linesdk.auth.LineLoginResult;
import com.linecorp.linesdk.auth.internal.LineAuthenticationStatus;

/* loaded from: classes2.dex */
public class LineAuthenticationActivity extends Activity {
    private static final String INSTANCE_STATE_KEY_AUTHENTICATION_STATUS = "authentication_status";
    private static final String PARAM_KEY_AUTHENTICATION_CONFIG = "authentication_config";
    private static final String PARAM_KEY_AUTHENTICATION_PARAMS = "authentication_params";
    private static final String RESPONSE_DATA_KEY_AUTHENTICATION_RESULT = "authentication_result";
    private static final String SUPPORTED_SCHEME = "lineauth";
    private LineAuthenticationController authenticationController;
    private LineAuthenticationStatus authenticationStatus;
    private boolean isActivityStopped = false;

    public static Intent getLoginIntent(Context context, LineAuthenticationConfig lineAuthenticationConfig, LineAuthenticationParams lineAuthenticationParams) {
        Intent intent = new Intent(context, (Class<?>) LineAuthenticationActivity.class);
        intent.putExtra(PARAM_KEY_AUTHENTICATION_CONFIG, lineAuthenticationConfig);
        intent.putExtra(PARAM_KEY_AUTHENTICATION_PARAMS, lineAuthenticationParams);
        return intent;
    }

    public static LineLoginResult getResultFromIntent(Intent intent) {
        LineLoginResult lineLoginResult = (LineLoginResult) intent.getParcelableExtra(RESPONSE_DATA_KEY_AUTHENTICATION_RESULT);
        return lineLoginResult == null ? LineLoginResult.internalError("Authentication result is not found.") : lineLoginResult;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2028R.layout.linesdk_activity_lineauthentication);
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null && data.getScheme().equals(SUPPORTED_SCHEME)) {
            LineAuthenticationController.setIntent(intent);
            finish();
            return;
        }
        LineAuthenticationConfig lineAuthenticationConfig = (LineAuthenticationConfig) intent.getParcelableExtra(PARAM_KEY_AUTHENTICATION_CONFIG);
        LineAuthenticationParams lineAuthenticationParams = (LineAuthenticationParams) intent.getParcelableExtra(PARAM_KEY_AUTHENTICATION_PARAMS);
        if (lineAuthenticationConfig == null || lineAuthenticationParams == null) {
            onAuthenticationFinished(LineLoginResult.internalError("The requested parameter is illegal."));
            return;
        }
        LineAuthenticationStatus authenticationStatus = getAuthenticationStatus(bundle);
        this.authenticationStatus = authenticationStatus;
        this.authenticationController = new LineAuthenticationController(this, lineAuthenticationConfig, authenticationStatus, lineAuthenticationParams);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.authenticationStatus.getStatus() == LineAuthenticationStatus.Status.INIT) {
            this.authenticationController.startLineAuthentication();
        } else if (this.authenticationStatus.getStatus() != LineAuthenticationStatus.Status.INTENT_RECEIVED) {
            this.authenticationController.handleCancel();
        }
        this.isActivityStopped = false;
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.isActivityStopped = true;
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.authenticationStatus.getStatus() == LineAuthenticationStatus.Status.STARTED) {
            this.authenticationController.handleIntentFromLineApp(intent);
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.authenticationStatus.getStatus() == LineAuthenticationStatus.Status.STARTED) {
            this.authenticationController.onActivityResult(i, i2, intent);
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(INSTANCE_STATE_KEY_AUTHENTICATION_STATUS, this.authenticationStatus);
    }

    private LineAuthenticationStatus getAuthenticationStatus(Bundle bundle) {
        if (bundle == null) {
            return new LineAuthenticationStatus();
        }
        LineAuthenticationStatus lineAuthenticationStatus = (LineAuthenticationStatus) bundle.getParcelable(INSTANCE_STATE_KEY_AUTHENTICATION_STATUS);
        return lineAuthenticationStatus == null ? new LineAuthenticationStatus() : lineAuthenticationStatus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onAuthenticationFinished(LineLoginResult lineLoginResult) {
        LineAuthenticationStatus lineAuthenticationStatus = this.authenticationStatus;
        if (lineAuthenticationStatus == null) {
            finish();
            return;
        }
        if ((lineAuthenticationStatus.getStatus() != LineAuthenticationStatus.Status.STARTED || this.isActivityStopped) && this.authenticationStatus.getStatus() != LineAuthenticationStatus.Status.INTENT_HANDLED) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(RESPONSE_DATA_KEY_AUTHENTICATION_RESULT, lineLoginResult);
        setResult(-1, intent);
        finish();
    }
}
