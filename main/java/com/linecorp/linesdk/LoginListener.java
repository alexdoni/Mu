package com.linecorp.linesdk;

import com.linecorp.linesdk.auth.LineLoginResult;

/* loaded from: classes2.dex */
public interface LoginListener {
    void onLoginFailure(LineLoginResult lineLoginResult);

    void onLoginSuccess(LineLoginResult lineLoginResult);
}
