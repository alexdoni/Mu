package com.linecorp.linesdk;

import android.content.Intent;
import com.linecorp.linesdk.internal.LoginDelegateImpl;

/* loaded from: classes2.dex */
public interface LoginDelegate {
    boolean onActivityResult(int i, int i2, Intent intent);

    /* loaded from: classes2.dex */
    public static class Factory {
        public static LoginDelegate create() {
            return new LoginDelegateImpl();
        }
    }
}
