package com.linecorp.linesdk.internal;

import android.content.Intent;
import com.linecorp.linesdk.LoginDelegate;

/* loaded from: classes2.dex */
public class LoginDelegateImpl implements LoginDelegate {
    private LoginHandler loginHandler;

    @Override // com.linecorp.linesdk.LoginDelegate
    public boolean onActivityResult(int i, int i2, Intent intent) {
        LoginHandler loginHandler = this.loginHandler;
        return loginHandler != null && loginHandler.onActivityResult(i, i2, intent);
    }

    public void setLoginHandler(LoginHandler loginHandler) {
        this.loginHandler = loginHandler;
    }
}
