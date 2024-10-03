package com.p008ld.sdk.internal;

import com.p008ld.sdk.core.bean.LDLoginResult;
import com.p008ld.sdk.core.bean.LoginMode;

/* compiled from: LDThirdLoginCallback.kt */
/* loaded from: classes2.dex */
public interface LDThirdLoginCallback {
    void onError(int i, Exception exc);

    void onSuccess(LoginMode loginMode, LDLoginResult lDLoginResult);
}
