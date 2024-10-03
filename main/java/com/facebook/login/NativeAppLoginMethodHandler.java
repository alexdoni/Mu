package com.facebook.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.FacebookServiceException;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.login.CustomTabLoginMethodHandler;
import com.facebook.login.LoginClient;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NativeAppLoginMethodHandler.kt */
@Metadata(m1394d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b'\u0018\u00002\u00020\u0001B\u000f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u001a\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J0\u0010\u001a\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u001b\u001a\u0004\u0018\u00010\u00112\b\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0014J\u0018\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0019H\u0002J\"\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0018\u0010&\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010'\u001a\u00020$2\u0006\u0010\u0016\u001a\u00020\u0017H&J\u001a\u0010(\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u00192\u0006\u0010#\u001a\u00020$H\u0014R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006)"}, m1395d2 = {"Lcom/facebook/login/NativeAppLoginMethodHandler;", "Lcom/facebook/login/LoginMethodHandler;", "loginClient", "Lcom/facebook/login/LoginClient;", "(Lcom/facebook/login/LoginClient;)V", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "tokenSource", "Lcom/facebook/AccessTokenSource;", "getTokenSource", "()Lcom/facebook/AccessTokenSource;", "completeLogin", "", "outcome", "Lcom/facebook/login/LoginClient$Result;", "getError", "", "extras", "Landroid/os/Bundle;", "getErrorMessage", "handleResultCancel", "request", "Lcom/facebook/login/LoginClient$Request;", "data", "Landroid/content/Intent;", "handleResultError", "error", "errorMessage", "errorCode", "handleResultOk", "isCallable", "", "intent", "onActivityResult", "requestCode", "", "resultCode", "processSuccessResponse", "tryAuthorize", "tryIntent", "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public abstract class NativeAppLoginMethodHandler extends LoginMethodHandler {
    private final AccessTokenSource tokenSource;

    @Override // com.facebook.login.LoginMethodHandler
    public abstract int tryAuthorize(LoginClient.Request request);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeAppLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
        Intrinsics.checkNotNullParameter(loginClient, "loginClient");
        this.tokenSource = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeAppLoginMethodHandler(Parcel source) {
        super(source);
        Intrinsics.checkNotNullParameter(source, "source");
        this.tokenSource = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
    }

    private final void processSuccessResponse(final LoginClient.Request request, final Bundle extras) {
        if (extras.containsKey("code")) {
            Utility utility = Utility.INSTANCE;
            if (!Utility.isNullOrEmpty(extras.getString("code"))) {
                FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
                FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.login.NativeAppLoginMethodHandler$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        NativeAppLoginMethodHandler.m1806processSuccessResponse$lambda0(NativeAppLoginMethodHandler.this, request, extras);
                    }
                });
                return;
            }
        }
        handleResultOk(request, extras);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: processSuccessResponse$lambda-0, reason: not valid java name */
    public static final void m1806processSuccessResponse$lambda0(NativeAppLoginMethodHandler this$0, LoginClient.Request request, Bundle extras) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        Intrinsics.checkNotNullParameter(extras, "$extras");
        try {
            this$0.handleResultOk(request, this$0.processCodeExchange(request, extras));
        } catch (FacebookServiceException e) {
            FacebookRequestError requestError = e.getRequestError();
            this$0.handleResultError(request, requestError.getErrorType(), requestError.getErrorMessage(), String.valueOf(requestError.getErrorCode()));
        } catch (FacebookException e2) {
            this$0.handleResultError(request, null, e2.getMessage(), null);
        }
    }

    public AccessTokenSource getTokenSource() {
        return this.tokenSource;
    }

    @Override // com.facebook.login.LoginMethodHandler
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        LoginClient.Request pendingRequest = getLoginClient().getPendingRequest();
        if (data == null) {
            completeLogin(LoginClient.Result.INSTANCE.createCancelResult(pendingRequest, "Operation canceled"));
        } else if (resultCode == 0) {
            handleResultCancel(pendingRequest, data);
        } else if (resultCode != -1) {
            completeLogin(LoginClient.Result.Companion.createErrorResult$default(LoginClient.Result.INSTANCE, pendingRequest, "Unexpected resultCode from authorization.", null, null, 8, null));
        } else {
            Bundle extras = data.getExtras();
            if (extras == null) {
                completeLogin(LoginClient.Result.Companion.createErrorResult$default(LoginClient.Result.INSTANCE, pendingRequest, "Unexpected null from returned authorization data.", null, null, 8, null));
                return true;
            }
            String error = getError(extras);
            Object obj = extras.get(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
            String obj2 = obj == null ? null : obj.toString();
            String errorMessage = getErrorMessage(extras);
            String string = extras.getString("e2e");
            Utility utility = Utility.INSTANCE;
            if (!Utility.isNullOrEmpty(string)) {
                logWebLoginCompleted(string);
            }
            if (error == null && obj2 == null && errorMessage == null && pendingRequest != null) {
                processSuccessResponse(pendingRequest, extras);
            } else {
                handleResultError(pendingRequest, error, errorMessage, obj2);
            }
        }
        return true;
    }

    private final void completeLogin(LoginClient.Result outcome) {
        if (outcome != null) {
            getLoginClient().completeAndValidate(outcome);
        } else {
            getLoginClient().tryNextHandler();
        }
    }

    protected void handleResultError(LoginClient.Request request, String error, String errorMessage, String errorCode) {
        if (error != null && Intrinsics.areEqual(error, "logged_out")) {
            CustomTabLoginMethodHandler.Companion companion = CustomTabLoginMethodHandler.INSTANCE;
            CustomTabLoginMethodHandler.calledThroughLoggedOutAppSwitch = true;
            completeLogin(null);
            return;
        }
        ServerProtocol serverProtocol = ServerProtocol.INSTANCE;
        if (CollectionsKt.contains(ServerProtocol.getErrorsProxyAuthDisabled(), error)) {
            completeLogin(null);
            return;
        }
        ServerProtocol serverProtocol2 = ServerProtocol.INSTANCE;
        if (CollectionsKt.contains(ServerProtocol.getErrorsUserCanceled(), error)) {
            completeLogin(LoginClient.Result.INSTANCE.createCancelResult(request, null));
        } else {
            completeLogin(LoginClient.Result.INSTANCE.createErrorResult(request, error, errorMessage, errorCode));
        }
    }

    protected void handleResultOk(LoginClient.Request request, Bundle extras) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(extras, "extras");
        try {
            completeLogin(LoginClient.Result.INSTANCE.createCompositeTokenResult(request, LoginMethodHandler.INSTANCE.createAccessTokenFromWebBundle(request.getPermissions(), extras, getTokenSource(), request.getApplicationId()), LoginMethodHandler.INSTANCE.createAuthenticationTokenFromWebBundle(extras, request.getNonce())));
        } catch (FacebookException e) {
            completeLogin(LoginClient.Result.Companion.createErrorResult$default(LoginClient.Result.INSTANCE, request, null, e.getMessage(), null, 8, null));
        }
    }

    protected void handleResultCancel(LoginClient.Request request, Intent data) {
        Object obj;
        Intrinsics.checkNotNullParameter(data, "data");
        Bundle extras = data.getExtras();
        String error = getError(extras);
        String str = null;
        if (extras != null && (obj = extras.get(NativeProtocol.BRIDGE_ARG_ERROR_CODE)) != null) {
            str = obj.toString();
        }
        ServerProtocol serverProtocol = ServerProtocol.INSTANCE;
        if (Intrinsics.areEqual(ServerProtocol.getErrorConnectionFailure(), str)) {
            completeLogin(LoginClient.Result.INSTANCE.createErrorResult(request, error, getErrorMessage(extras), str));
        } else {
            completeLogin(LoginClient.Result.INSTANCE.createCancelResult(request, error));
        }
    }

    protected String getError(Bundle extras) {
        String string = extras == null ? null : extras.getString("error");
        if (string != null) {
            return string;
        }
        if (extras == null) {
            return null;
        }
        return extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_TYPE);
    }

    protected String getErrorMessage(Bundle extras) {
        String string = extras == null ? null : extras.getString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE);
        if (string != null) {
            return string;
        }
        if (extras == null) {
            return null;
        }
        return extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean tryIntent(Intent intent, int requestCode) {
        ActivityResultLauncher<Intent> launcher;
        if (intent == null || !isCallable(intent)) {
            return false;
        }
        Fragment fragment = getLoginClient().getFragment();
        Unit unit = null;
        LoginFragment loginFragment = fragment instanceof LoginFragment ? (LoginFragment) fragment : null;
        if (loginFragment != null && (launcher = loginFragment.getLauncher()) != null) {
            launcher.launch(intent);
            unit = Unit.INSTANCE;
        }
        return unit != null;
    }

    private final boolean isCallable(Intent intent) {
        FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(FacebookSdk.getApplicationContext().getPackageManager().queryIntentActivities(intent, 65536), "FacebookSdk.getApplicationContext()\n            .packageManager\n            .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)");
        return !r3.isEmpty();
    }
}
