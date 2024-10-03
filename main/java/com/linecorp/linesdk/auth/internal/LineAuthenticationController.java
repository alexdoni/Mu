package com.linecorp.linesdk.auth.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.linecorp.linesdk.LineAccessToken;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.LineCredential;
import com.linecorp.linesdk.LineIdToken;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.Scope;
import com.linecorp.linesdk.auth.LineAuthenticationConfig;
import com.linecorp.linesdk.auth.LineAuthenticationParams;
import com.linecorp.linesdk.auth.LineLoginResult;
import com.linecorp.linesdk.auth.internal.BrowserAuthenticationApi;
import com.linecorp.linesdk.auth.internal.LineAuthenticationStatus;
import com.linecorp.linesdk.internal.AccessTokenCache;
import com.linecorp.linesdk.internal.InternalAccessToken;
import com.linecorp.linesdk.internal.IssueAccessTokenResult;
import com.linecorp.linesdk.internal.OneTimePassword;
import com.linecorp.linesdk.internal.OpenIdDiscoveryDocument;
import com.linecorp.linesdk.internal.nwclient.IdTokenValidator;
import com.linecorp.linesdk.internal.nwclient.LineAuthenticationApiClient;
import com.linecorp.linesdk.internal.nwclient.TalkApiClient;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class LineAuthenticationController {
    private static final long CANCEL_DELAY_MILLIS = 1000;
    private static final int REQUEST_CODE = 3;
    private static Intent intentResultFromLineAPP;
    private final AccessTokenCache accessTokenCache;
    private final LineAuthenticationActivity activity;
    private final LineAuthenticationApiClient authApiClient;
    private final LineAuthenticationStatus authenticationStatus;
    private final BrowserAuthenticationApi browserAuthenticationApi;
    private final LineAuthenticationConfig config;
    private final LineAuthenticationParams params;
    private final TalkApiClient talkApiClient;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LineAuthenticationController(LineAuthenticationActivity lineAuthenticationActivity, LineAuthenticationConfig lineAuthenticationConfig, LineAuthenticationStatus lineAuthenticationStatus, LineAuthenticationParams lineAuthenticationParams) {
        this(lineAuthenticationActivity, lineAuthenticationConfig, new LineAuthenticationApiClient(lineAuthenticationActivity.getApplicationContext(), lineAuthenticationConfig.getOpenidDiscoveryDocumentUrl(), lineAuthenticationConfig.getApiBaseUrl()), new TalkApiClient(lineAuthenticationActivity.getApplicationContext(), lineAuthenticationConfig.getApiBaseUrl()), new BrowserAuthenticationApi(lineAuthenticationStatus), new AccessTokenCache(lineAuthenticationActivity.getApplicationContext(), lineAuthenticationConfig.getChannelId()), lineAuthenticationStatus, lineAuthenticationParams);
    }

    LineAuthenticationController(LineAuthenticationActivity lineAuthenticationActivity, LineAuthenticationConfig lineAuthenticationConfig, LineAuthenticationApiClient lineAuthenticationApiClient, TalkApiClient talkApiClient, BrowserAuthenticationApi browserAuthenticationApi, AccessTokenCache accessTokenCache, LineAuthenticationStatus lineAuthenticationStatus, LineAuthenticationParams lineAuthenticationParams) {
        this.activity = lineAuthenticationActivity;
        this.config = lineAuthenticationConfig;
        this.authApiClient = lineAuthenticationApiClient;
        this.talkApiClient = talkApiClient;
        this.browserAuthenticationApi = browserAuthenticationApi;
        this.accessTokenCache = accessTokenCache;
        this.authenticationStatus = lineAuthenticationStatus;
        this.params = lineAuthenticationParams;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startLineAuthentication() {
        this.authenticationStatus.authenticationStarted();
        new RequestTokenRequestTask().execute(new Void[0]);
    }

    /* loaded from: classes2.dex */
    private class RequestTokenRequestTask extends AsyncTask<Void, Void, LineApiResponse<OneTimePassword>> {
        private RequestTokenRequestTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public LineApiResponse<OneTimePassword> doInBackground(Void... voidArr) {
            return LineAuthenticationController.this.authApiClient.getOneTimeIdAndPassword(LineAuthenticationController.this.config.getChannelId());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(LineApiResponse<OneTimePassword> lineApiResponse) {
            if (!lineApiResponse.isSuccess()) {
                LineAuthenticationController.this.authenticationStatus.authenticationIntentHandled();
                LineAuthenticationController.this.activity.onAuthenticationFinished(LineLoginResult.error(lineApiResponse));
                return;
            }
            OneTimePassword responseData = lineApiResponse.getResponseData();
            LineAuthenticationController.this.authenticationStatus.setOneTimePassword(responseData);
            try {
                BrowserAuthenticationApi.Request request = LineAuthenticationController.this.browserAuthenticationApi.getRequest(LineAuthenticationController.this.activity, LineAuthenticationController.this.config, responseData, LineAuthenticationController.this.params);
                if (request.isLineAppAuthentication()) {
                    LineAuthenticationController.this.activity.startActivity(request.getIntent(), request.getStartActivityOptions());
                } else {
                    LineAuthenticationController.this.activity.startActivityForResult(request.getIntent(), 3, request.getStartActivityOptions());
                }
                LineAuthenticationController.this.authenticationStatus.setSentRedirectUri(request.getRedirectUri());
            } catch (ActivityNotFoundException e) {
                LineAuthenticationController.this.authenticationStatus.authenticationIntentHandled();
                LineAuthenticationController.this.activity.onAuthenticationFinished(LineLoginResult.internalError(e));
            }
        }
    }

    public static void setIntent(Intent intent) {
        intentResultFromLineAPP = intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleIntentFromLineApp(Intent intent) {
        LineLoginResult internalError;
        this.authenticationStatus.authenticationIntentReceived();
        BrowserAuthenticationApi.Result authenticationResultFrom = this.browserAuthenticationApi.getAuthenticationResultFrom(intent);
        if (!authenticationResultFrom.isSuccess()) {
            this.authenticationStatus.authenticationIntentHandled();
            if (authenticationResultFrom.isAuthenticationAgentError()) {
                internalError = LineLoginResult.authenticationAgentError(authenticationResultFrom.getLineApiError());
            } else {
                internalError = LineLoginResult.internalError(authenticationResultFrom.getLineApiError());
            }
            this.activity.onAuthenticationFinished(internalError);
            return;
        }
        new AccessTokenRequestTask().execute(authenticationResultFrom);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 3 || this.authenticationStatus.getStatus() == LineAuthenticationStatus.Status.INTENT_RECEIVED) {
            return;
        }
        new Handler(Looper.getMainLooper()).postDelayed(new CancelAuthenticationTask(), CANCEL_DELAY_MILLIS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleCancel() {
        new Handler(Looper.getMainLooper()).postDelayed(new CancelAuthenticationTask(), CANCEL_DELAY_MILLIS);
    }

    /* loaded from: classes2.dex */
    private class CancelAuthenticationTask implements Runnable {
        private CancelAuthenticationTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (LineAuthenticationController.this.authenticationStatus.getStatus() == LineAuthenticationStatus.Status.INTENT_RECEIVED || LineAuthenticationController.this.activity.isFinishing()) {
                return;
            }
            if (LineAuthenticationController.intentResultFromLineAPP != null) {
                LineAuthenticationController.this.handleIntentFromLineApp(LineAuthenticationController.intentResultFromLineAPP);
                Intent unused = LineAuthenticationController.intentResultFromLineAPP = null;
            } else {
                LineAuthenticationController.this.activity.onAuthenticationFinished(LineLoginResult.canceledError());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class AccessTokenRequestTask extends AsyncTask<BrowserAuthenticationApi.Result, Void, LineLoginResult> {
        private AccessTokenRequestTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public LineLoginResult doInBackground(BrowserAuthenticationApi.Result... resultArr) {
            LineProfile lineProfile;
            String str;
            BrowserAuthenticationApi.Result result = resultArr[0];
            String requestToken = result.getRequestToken();
            OneTimePassword oneTimePassword = LineAuthenticationController.this.authenticationStatus.getOneTimePassword();
            String sentRedirectUri = LineAuthenticationController.this.authenticationStatus.getSentRedirectUri();
            if (TextUtils.isEmpty(requestToken) || oneTimePassword == null || TextUtils.isEmpty(sentRedirectUri)) {
                return LineLoginResult.internalError("Requested data is missing.");
            }
            LineApiResponse<IssueAccessTokenResult> issueAccessToken = LineAuthenticationController.this.authApiClient.issueAccessToken(LineAuthenticationController.this.config.getChannelId(), requestToken, oneTimePassword, sentRedirectUri);
            if (!issueAccessToken.isSuccess()) {
                return LineLoginResult.error(issueAccessToken);
            }
            IssueAccessTokenResult responseData = issueAccessToken.getResponseData();
            InternalAccessToken accessToken = responseData.getAccessToken();
            List<Scope> scopes = responseData.getScopes();
            if (scopes.contains(Scope.PROFILE)) {
                LineApiResponse<LineProfile> profile = LineAuthenticationController.this.talkApiClient.getProfile(accessToken);
                if (!profile.isSuccess()) {
                    return LineLoginResult.error(profile);
                }
                lineProfile = profile.getResponseData();
                str = lineProfile.getUserId();
            } else {
                lineProfile = null;
                str = null;
            }
            LineAuthenticationController.this.accessTokenCache.saveAccessToken(accessToken);
            LineIdToken idToken = responseData.getIdToken();
            if (idToken != null) {
                try {
                    validateIdToken(idToken, str);
                } catch (Exception e) {
                    return LineLoginResult.internalError(e.getMessage());
                }
            }
            return new LineLoginResult.Builder().nonce(LineAuthenticationController.this.authenticationStatus.getOpenIdNonce()).lineProfile(lineProfile).lineIdToken(idToken).friendshipStatusChanged(result.getFriendshipStatusChanged()).lineCredential(new LineCredential(new LineAccessToken(accessToken.getAccessToken(), accessToken.getExpiresInMillis(), accessToken.getIssuedClientTimeMillis()), scopes)).build();
        }

        private void validateIdToken(LineIdToken lineIdToken, String str) {
            LineApiResponse<OpenIdDiscoveryDocument> openIdDiscoveryDocument = LineAuthenticationController.this.authApiClient.getOpenIdDiscoveryDocument();
            if (!openIdDiscoveryDocument.isSuccess()) {
                throw new RuntimeException("Failed to get OpenId Discovery Document.  Response Code: " + openIdDiscoveryDocument.getResponseCode() + " Error Data: " + openIdDiscoveryDocument.getErrorData());
            }
            new IdTokenValidator.Builder().idToken(lineIdToken).expectedIssuer(openIdDiscoveryDocument.getResponseData().getIssuer()).expectedUserId(str).expectedChannelId(LineAuthenticationController.this.config.getChannelId()).expectedNonce(LineAuthenticationController.this.authenticationStatus.getOpenIdNonce()).build().validate();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(LineLoginResult lineLoginResult) {
            LineAuthenticationController.this.authenticationStatus.authenticationIntentHandled();
            LineAuthenticationController.this.activity.onAuthenticationFinished(lineLoginResult);
        }
    }
}
