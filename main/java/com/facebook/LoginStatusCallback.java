package com.facebook;

import kotlin.Metadata;

/* compiled from: LoginStatusCallback.kt */
@Metadata(m1394d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0014\u0010\u0006\u001a\u00020\u00032\n\u0010\u0007\u001a\u00060\bj\u0002`\tH&J\b\u0010\n\u001a\u00020\u0003H&¨\u0006\u000b"}, m1395d2 = {"Lcom/facebook/LoginStatusCallback;", "", "onCompleted", "", "accessToken", "Lcom/facebook/AccessToken;", "onError", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onFailure", "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public interface LoginStatusCallback {
    void onCompleted(AccessToken accessToken);

    void onError(Exception exception);

    void onFailure();
}
