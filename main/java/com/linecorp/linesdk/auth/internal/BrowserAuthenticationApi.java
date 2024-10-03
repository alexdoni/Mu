package com.linecorp.linesdk.auth.internal;

import android.R;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.linecorp.android.security.SecurityUtils;
import com.linecorp.linesdk.BuildConfig;
import com.linecorp.linesdk.LineApiError;
import com.linecorp.linesdk.Scope;
import com.linecorp.linesdk.auth.LineAuthenticationConfig;
import com.linecorp.linesdk.auth.LineAuthenticationParams;
import com.linecorp.linesdk.internal.OneTimePassword;
import com.linecorp.linesdk.utils.UriUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class BrowserAuthenticationApi {
    private static final LineAppVersion AUTO_LOGIN_FOR_LINE_SDK_ENABLED_VERSION = new LineAppVersion(6, 9, 0);
    private final LineAuthenticationStatus authenticationStatus;

    boolean isChromeCustomTabSupported() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Request {
        private final Intent intent;
        private final boolean isLineAppAuthentication;
        private final String redirectUri;
        private final Bundle startActivityOptions;

        Request(Intent intent, Bundle bundle, String str, boolean z) {
            this.intent = intent;
            this.startActivityOptions = bundle;
            this.redirectUri = str;
            this.isLineAppAuthentication = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Intent getIntent() {
            return this.intent;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Bundle getStartActivityOptions() {
            return this.startActivityOptions;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getRedirectUri() {
            return this.redirectUri;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isLineAppAuthentication() {
            return this.isLineAppAuthentication;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BrowserAuthenticationApi(LineAuthenticationStatus lineAuthenticationStatus) {
        this.authenticationStatus = lineAuthenticationStatus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request getRequest(Context context, LineAuthenticationConfig lineAuthenticationConfig, OneTimePassword oneTimePassword, LineAuthenticationParams lineAuthenticationParams) throws ActivityNotFoundException {
        String str;
        String createRandomString = SecurityUtils.createRandomString(8);
        this.authenticationStatus.setOAuthState(createRandomString);
        if (!lineAuthenticationParams.getScopes().contains(Scope.OPENID_CONNECT)) {
            str = null;
        } else if (!TextUtils.isEmpty(lineAuthenticationParams.getNonce())) {
            str = lineAuthenticationParams.getNonce();
        } else {
            str = SecurityUtils.createRandomString(8);
        }
        String str2 = str;
        this.authenticationStatus.setOpenIdNonce(str2);
        String createRedirectUri = createRedirectUri(context);
        AuthenticationIntentHolder authenticationIntentHolder = getAuthenticationIntentHolder(context, createLoginUrl(lineAuthenticationConfig, oneTimePassword, lineAuthenticationParams, createRandomString, str2, createRedirectUri), lineAuthenticationConfig.isLineAppAuthenticationDisabled());
        return new Request(authenticationIntentHolder.getIntent(), authenticationIntentHolder.getStartActivityOptions(), createRedirectUri, authenticationIntentHolder.isLineAppAuthentication);
    }

    Uri createLoginUrl(LineAuthenticationConfig lineAuthenticationConfig, OneTimePassword oneTimePassword, LineAuthenticationParams lineAuthenticationParams, String str, String str2, String str3) {
        Map<String, String> buildParams = UriUtils.buildParams(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, "code", "client_id", lineAuthenticationConfig.getChannelId(), ServerProtocol.DIALOG_PARAM_STATE, str, "otpId", oneTimePassword.getId(), ServerProtocol.DIALOG_PARAM_REDIRECT_URI, str3, "sdk_ver", BuildConfig.VERSION_NAME, "scope", Scope.join(lineAuthenticationParams.getScopes()));
        if (!TextUtils.isEmpty(str2)) {
            buildParams.put("nonce", str2);
        }
        if (lineAuthenticationParams.getBotPrompt() != null) {
            buildParams.put("bot_prompt", lineAuthenticationParams.getBotPrompt().name().toLowerCase());
        }
        Map<String, String> buildParams2 = UriUtils.buildParams("returnUri", UriUtils.appendQueryParams("/oauth2/v2.1/authorize/consent", buildParams).toString(), "loginChannelId", lineAuthenticationConfig.getChannelId());
        if (lineAuthenticationParams.getUILocale() != null) {
            buildParams2.put("ui_locales", lineAuthenticationParams.getUILocale().toString());
        }
        return UriUtils.appendQueryParams(lineAuthenticationConfig.getWebLoginPageUrl(), buildParams2);
    }

    String createRedirectUri(Context context) {
        return "intent://result#Intent;package=" + context.getPackageName() + ";scheme=lineauth;end";
    }

    AuthenticationIntentHolder getAuthenticationIntentHolder(Context context, Uri uri, boolean z) throws ActivityNotFoundException {
        Intent data;
        Bundle bundle;
        if (isChromeCustomTabSupported()) {
            CustomTabsIntent build = new CustomTabsIntent.Builder().setToolbarColor(ContextCompat.getColor(context, R.color.white)).build();
            data = build.intent.setData(uri);
            bundle = build.startAnimationBundle;
        } else {
            data = new Intent("android.intent.action.VIEW").setData(uri);
            bundle = null;
        }
        LineAppVersion lineAppVersion = LineAppVersion.getLineAppVersion(context);
        if (lineAppVersion == null) {
            return new AuthenticationIntentHolder(data, bundle, false);
        }
        if (!z && lineAppVersion.isEqualOrGreaterThan(AUTO_LOGIN_FOR_LINE_SDK_ENABLED_VERSION)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(uri);
            intent.setPackage(BuildConfig.LINE_APP_PACKAGE_NAME);
            return new AuthenticationIntentHolder(intent, bundle, true);
        }
        List<Intent> convertToIntents = convertToIntents(uri, context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("http://")), 0), data.getExtras());
        int size = convertToIntents.size();
        if (size == 0) {
            throw new ActivityNotFoundException("Activity for LINE log-in is not found. uri=" + uri);
        }
        if (size == 1) {
            return new AuthenticationIntentHolder(convertToIntents.get(0), bundle, false);
        }
        Intent createChooser = Intent.createChooser(convertToIntents.remove(0), null);
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) convertToIntents.toArray(new Parcelable[convertToIntents.size()]));
        return new AuthenticationIntentHolder(createChooser, bundle, false);
    }

    private static List<Intent> convertToIntents(Uri uri, Collection<ResolveInfo> collection, Bundle bundle) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (ResolveInfo resolveInfo : collection) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(uri);
            intent.setPackage(resolveInfo.activityInfo.packageName);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            arrayList.add(intent);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Result getAuthenticationResultFrom(Intent intent) {
        Uri data = intent.getData();
        if (data == null) {
            return Result.createAsInternalError("Illegal redirection from external application.");
        }
        String oAuthState = this.authenticationStatus.getOAuthState();
        String queryParameter = data.getQueryParameter(ServerProtocol.DIALOG_PARAM_STATE);
        if (oAuthState == null || !oAuthState.equals(queryParameter)) {
            return Result.createAsInternalError("Illegal parameter value of 'state'.");
        }
        String queryParameter2 = data.getQueryParameter("code");
        String queryParameter3 = data.getQueryParameter("friendship_status_changed");
        Boolean valueOf = !TextUtils.isEmpty(queryParameter3) ? Boolean.valueOf(Boolean.parseBoolean(queryParameter3)) : null;
        if (!TextUtils.isEmpty(queryParameter2)) {
            return Result.createAsSuccess(queryParameter2, valueOf);
        }
        return Result.createAsAuthenticationAgentError(data.getQueryParameter("error"), data.getQueryParameter(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Result {
        private final Boolean friendshipStatusChanged;
        private final String internalErrorMessage;
        private final String requestToken;
        private final String serverErrorCode;
        private final String serverErrorDescription;

        private Result(String str, Boolean bool, String str2, String str3, String str4) {
            this.requestToken = str;
            this.friendshipStatusChanged = bool;
            this.serverErrorCode = str2;
            this.serverErrorDescription = str3;
            this.internalErrorMessage = str4;
        }

        static Result createAsSuccess(String str, Boolean bool) {
            return new Result(str, bool, null, null, null);
        }

        static Result createAsAuthenticationAgentError(String str, String str2) {
            return new Result(null, null, str, str2, null);
        }

        static Result createAsInternalError(String str) {
            return new Result(null, null, null, null, str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isSuccess() {
            return !TextUtils.isEmpty(this.requestToken);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isAuthenticationAgentError() {
            return TextUtils.isEmpty(this.internalErrorMessage) && !isSuccess();
        }

        private void checkRequestToken() {
            if (TextUtils.isEmpty(this.requestToken)) {
                throw new UnsupportedOperationException("requestToken is null. Please check result by isSuccess before.");
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getRequestToken() {
            checkRequestToken();
            return this.requestToken;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Boolean getFriendshipStatusChanged() {
            checkRequestToken();
            return this.friendshipStatusChanged;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public LineApiError getLineApiError() {
            if (isAuthenticationAgentError()) {
                try {
                    return new LineApiError(new JSONObject().putOpt("error", this.serverErrorCode).putOpt(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION, this.serverErrorDescription).toString());
                } catch (JSONException e) {
                    return new LineApiError(e);
                }
            }
            return new LineApiError(this.internalErrorMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class AuthenticationIntentHolder {
        private final Intent intent;
        private final boolean isLineAppAuthentication;
        private final Bundle startActivityOptions;

        AuthenticationIntentHolder(Intent intent, Bundle bundle, boolean z) {
            this.intent = intent;
            this.startActivityOptions = bundle;
            this.isLineAppAuthentication = z;
        }

        public Intent getIntent() {
            return this.intent;
        }

        public Bundle getStartActivityOptions() {
            return this.startActivityOptions;
        }

        public boolean isLineAppAuthentication() {
            return this.isLineAppAuthentication;
        }
    }
}
