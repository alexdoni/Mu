package com.facebook.login;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.common.C0830R;
import com.facebook.login.LoginClient;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoginFragment.kt */
@Metadata(m1394d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 82\u00020\u0001:\u00018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0014J\u001c\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u00192\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001bH\u0002J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020 H\u0002J\"\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\f2\b\u0010$\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010%\u001a\u00020\u001b2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J&\u0010(\u001a\u0004\u0018\u00010\u00142\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\b\u0010-\u001a\u00020\u001bH\u0016J\u0010\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u00020\u001bH\u0016J\b\u00102\u001a\u00020\u001bH\u0016J\u0010\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u00020'H\u0016J\b\u00105\u001a\u00020\u001bH\u0014J\b\u00106\u001a\u00020\u001bH\u0014J\b\u00107\u001a\u00020\u001bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8UX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u000f@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, m1395d2 = {"Lcom/facebook/login/LoginFragment;", "Landroidx/fragment/app/Fragment;", "()V", "callingPackage", "", "<set-?>", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "launcher", "getLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "layoutResId", "", "getLayoutResId", "()I", "Lcom/facebook/login/LoginClient;", LoginFragment.SAVED_LOGIN_CLIENT, "getLoginClient", "()Lcom/facebook/login/LoginClient;", "progressBar", "Landroid/view/View;", "request", "Lcom/facebook/login/LoginClient$Request;", "createLoginClient", "getLoginMethodHandlerCallback", "Lkotlin/Function1;", "Landroidx/activity/result/ActivityResult;", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "hideSpinner", "initializeCallingPackage", "Landroid/app/Activity;", "onActivityResult", "requestCode", "resultCode", "data", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onLoginClientCompleted", "outcome", "Lcom/facebook/login/LoginClient$Result;", "onPause", "onResume", "onSaveInstanceState", "outState", "onSpinnerHidden", "onSpinnerShown", "showSpinner", "Companion", "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public class LoginFragment extends Fragment {
    public static final String EXTRA_REQUEST = "request";
    private static final String NULL_CALLING_PKG_ERROR_MSG = "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.";
    public static final String REQUEST_KEY = "com.facebook.LoginFragment:Request";
    public static final String RESULT_KEY = "com.facebook.LoginFragment:Result";
    private static final String SAVED_LOGIN_CLIENT = "loginClient";
    private static final String TAG = "LoginFragment";
    private String callingPackage;
    private ActivityResultLauncher<Intent> launcher;
    private LoginClient loginClient;
    private View progressBar;
    private LoginClient.Request request;

    protected void onSpinnerHidden() {
    }

    protected void onSpinnerShown() {
    }

    public final LoginClient getLoginClient() {
        LoginClient loginClient = this.loginClient;
        if (loginClient != null) {
            return loginClient;
        }
        Intrinsics.throwUninitializedPropertyAccessException(SAVED_LOGIN_CLIENT);
        throw null;
    }

    public final ActivityResultLauncher<Intent> getLauncher() {
        ActivityResultLauncher<Intent> activityResultLauncher = this.launcher;
        if (activityResultLauncher != null) {
            return activityResultLauncher;
        }
        Intrinsics.throwUninitializedPropertyAccessException("launcher");
        throw null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundleExtra;
        super.onCreate(savedInstanceState);
        LoginClient loginClient = savedInstanceState == null ? null : (LoginClient) savedInstanceState.getParcelable(SAVED_LOGIN_CLIENT);
        if (loginClient != null) {
            loginClient.setFragment(this);
        } else {
            loginClient = createLoginClient();
        }
        this.loginClient = loginClient;
        getLoginClient().setOnCompletedListener(new LoginClient.OnCompletedListener() { // from class: com.facebook.login.LoginFragment$$ExternalSyntheticLambda0
            @Override // com.facebook.login.LoginClient.OnCompletedListener
            public final void onCompleted(LoginClient.Result result) {
                LoginFragment.m1795onCreate$lambda0(LoginFragment.this, result);
            }
        });
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        initializeCallingPackage(activity);
        Intent intent = activity.getIntent();
        if (intent != null && (bundleExtra = intent.getBundleExtra(REQUEST_KEY)) != null) {
            this.request = (LoginClient.Request) bundleExtra.getParcelable("request");
        }
        ActivityResultContracts.StartActivityForResult startActivityForResult = new ActivityResultContracts.StartActivityForResult();
        final Function1<ActivityResult, Unit> loginMethodHandlerCallback = getLoginMethodHandlerCallback(activity);
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(startActivityForResult, new ActivityResultCallback() { // from class: com.facebook.login.LoginFragment$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                LoginFragment.m1796onCreate$lambda1(Function1.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(\n            ActivityResultContracts.StartActivityForResult(),\n            getLoginMethodHandlerCallback(activity))");
        this.launcher = registerForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m1795onCreate$lambda0(LoginFragment this$0, LoginClient.Result outcome) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(outcome, "outcome");
        this$0.onLoginClientCompleted(outcome);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1, reason: not valid java name */
    public static final void m1796onCreate$lambda1(Function1 tmp0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(activityResult);
    }

    private final Function1<ActivityResult, Unit> getLoginMethodHandlerCallback(final FragmentActivity activity) {
        return new Function1<ActivityResult, Unit>() { // from class: com.facebook.login.LoginFragment$getLoginMethodHandlerCallback$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ActivityResult activityResult) {
                invoke2(activityResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ActivityResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                if (result.getResultCode() == -1) {
                    LoginFragment.this.getLoginClient().onActivityResult(LoginClient.INSTANCE.getLoginRequestCode(), result.getResultCode(), result.getData());
                } else {
                    activity.finish();
                }
            }
        };
    }

    protected LoginClient createLoginClient() {
        return new LoginClient(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        getLoginClient().cancelCurrentHandler();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(getLayoutResId(), container, false);
        View findViewById = inflate.findViewById(C0830R.id.com_facebook_login_fragment_progress_bar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById<View>(R.id.com_facebook_login_fragment_progress_bar)");
        this.progressBar = findViewById;
        getLoginClient().setBackgroundProcessingListener(new LoginClient.BackgroundProcessingListener() { // from class: com.facebook.login.LoginFragment$onCreateView$1
            @Override // com.facebook.login.LoginClient.BackgroundProcessingListener
            public void onBackgroundProcessingStarted() {
                LoginFragment.this.showSpinner();
            }

            @Override // com.facebook.login.LoginClient.BackgroundProcessingListener
            public void onBackgroundProcessingStopped() {
                LoginFragment.this.hideSpinner();
            }
        });
        return inflate;
    }

    protected int getLayoutResId() {
        return C0830R.layout.com_facebook_login_fragment;
    }

    private final void onLoginClientCompleted(LoginClient.Result outcome) {
        this.request = null;
        int i = outcome.code == LoginClient.Result.Code.CANCEL ? 0 : -1;
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESULT_KEY, outcome);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        FragmentActivity activity = getActivity();
        if (!isAdded() || activity == null) {
            return;
        }
        activity.setResult(i, intent);
        activity.finish();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.callingPackage == null) {
            Log.e(TAG, NULL_CALLING_PKG_ERROR_MSG);
            FragmentActivity activity = getActivity();
            if (activity == null) {
                return;
            }
            activity.finish();
            return;
        }
        getLoginClient().startOrContinueAuth(this.request);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        View view = getView();
        View findViewById = view == null ? null : view.findViewById(C0830R.id.com_facebook_login_fragment_progress_bar);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getLoginClient().onActivityResult(requestCode, resultCode, data);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_LOGIN_CLIENT, getLoginClient());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSpinner() {
        View view = this.progressBar;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            throw null;
        }
        view.setVisibility(0);
        onSpinnerShown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideSpinner() {
        View view = this.progressBar;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            throw null;
        }
        view.setVisibility(8);
        onSpinnerHidden();
    }

    private final void initializeCallingPackage(Activity activity) {
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity == null) {
            return;
        }
        this.callingPackage = callingActivity.getPackageName();
    }
}
