package com.linecorp.linesdk.widget;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import com.linecorp.linesdk.C2028R;
import com.linecorp.linesdk.LoginDelegate;
import com.linecorp.linesdk.LoginListener;
import com.linecorp.linesdk.Scope;
import com.linecorp.linesdk.auth.LineAuthenticationParams;
import com.linecorp.linesdk.internal.FragmentWrapper;
import com.linecorp.linesdk.internal.LoginDelegateImpl;
import com.linecorp.linesdk.internal.LoginHandler;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class LoginButton extends AppCompatTextView {
    private LineAuthenticationParams authenticationParams;
    private String channelId;
    private FragmentWrapper fragmentWrapper;
    private View.OnClickListener internalListener;
    private boolean isLineAppAuthEnabled;
    private LoginDelegate loginDelegate;
    private LoginHandler loginHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-linecorp-linesdk-widget-LoginButton, reason: not valid java name */
    public /* synthetic */ void m1874lambda$new$0$comlinecorplinesdkwidgetLoginButton(View view) {
        String str = this.channelId;
        if (str == null) {
            throw new RuntimeException("Channel id should be set.");
        }
        if (str.isEmpty()) {
            throw new RuntimeException("Channel id should not be empty.");
        }
        FragmentWrapper fragmentWrapper = this.fragmentWrapper;
        if (fragmentWrapper != null) {
            performLoginWithFragment(this.channelId, fragmentWrapper);
        } else {
            performLoginWithActivity(this.channelId, getActivity());
        }
    }

    public LoginButton(Context context) {
        super(context);
        this.isLineAppAuthEnabled = true;
        this.authenticationParams = new LineAuthenticationParams.Builder().scopes(Arrays.asList(Scope.PROFILE)).build();
        this.loginHandler = new LoginHandler();
        this.internalListener = new View.OnClickListener() { // from class: com.linecorp.linesdk.widget.LoginButton$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginButton.this.m1874lambda$new$0$comlinecorplinesdkwidgetLoginButton(view);
            }
        };
        init();
    }

    public LoginButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isLineAppAuthEnabled = true;
        this.authenticationParams = new LineAuthenticationParams.Builder().scopes(Arrays.asList(Scope.PROFILE)).build();
        this.loginHandler = new LoginHandler();
        this.internalListener = new View.OnClickListener() { // from class: com.linecorp.linesdk.widget.LoginButton$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginButton.this.m1874lambda$new$0$comlinecorplinesdkwidgetLoginButton(view);
            }
        };
        init();
    }

    public LoginButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isLineAppAuthEnabled = true;
        this.authenticationParams = new LineAuthenticationParams.Builder().scopes(Arrays.asList(Scope.PROFILE)).build();
        this.loginHandler = new LoginHandler();
        this.internalListener = new View.OnClickListener() { // from class: com.linecorp.linesdk.widget.LoginButton$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginButton.this.m1874lambda$new$0$comlinecorplinesdkwidgetLoginButton(view);
            }
        };
        init();
    }

    @Override // android.view.View
    public void setOnClickListener(final View.OnClickListener onClickListener) {
        super.setOnClickListener(new View.OnClickListener() { // from class: com.linecorp.linesdk.widget.LoginButton$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginButton.this.m588x6dfe7d5d(onClickListener, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setOnClickListener$1$com-linecorp-linesdk-widget-LoginButton */
    public /* synthetic */ void m588x6dfe7d5d(View.OnClickListener onClickListener, View view) {
        this.internalListener.onClick(view);
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public void setFragment(Fragment fragment) {
        this.fragmentWrapper = new FragmentWrapper(fragment);
    }

    public void setFragment(androidx.fragment.app.Fragment fragment) {
        this.fragmentWrapper = new FragmentWrapper(fragment);
    }

    public void setLoginDelegate(LoginDelegate loginDelegate) {
        if (!(loginDelegate instanceof LoginDelegateImpl)) {
            throw new RuntimeException("Unexpected LoginDelegate, please use the provided Factory to create the instance");
        }
        ((LoginDelegateImpl) loginDelegate).setLoginHandler(this.loginHandler);
        this.loginDelegate = loginDelegate;
    }

    public void addLoginListener(LoginListener loginListener) {
        if (this.loginDelegate == null) {
            throw new RuntimeException("You must set LoginDelegate through setLoginDelegate()  first");
        }
        this.loginHandler.addLoginListener(loginListener);
    }

    public void removeLoginListener(LoginListener loginListener) {
        this.loginHandler.removeLoginListener(loginListener);
    }

    public void enableLineAppAuthentication(boolean z) {
        this.isLineAppAuthEnabled = z;
    }

    public void setChannelId(String str) {
        this.channelId = str;
    }

    public void setAuthenticationParams(LineAuthenticationParams lineAuthenticationParams) {
        this.authenticationParams = lineAuthenticationParams;
    }

    private void init() {
        setAllCaps(false);
        setGravity(17);
        setText(C2028R.string.btn_line_login);
        setTextColor(ContextCompat.getColor(getContext(), C2028R.color.text_login_btn));
        setBackgroundResource(C2028R.drawable.background_login_btn);
        super.setOnClickListener(this.internalListener);
    }

    private Activity getActivity() {
        Context context = getContext();
        while ((context instanceof ContextWrapper) && !(context instanceof Activity)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        throw new RuntimeException("Cannot find an Activity");
    }

    private void performLoginWithActivity(String str, Activity activity) {
        this.loginHandler.performLogin(activity, this.isLineAppAuthEnabled, str, this.authenticationParams);
    }

    private void performLoginWithFragment(String str, FragmentWrapper fragmentWrapper) {
        this.loginHandler.performLogin(getActivity(), fragmentWrapper, this.isLineAppAuthEnabled, str, this.authenticationParams);
    }
}
