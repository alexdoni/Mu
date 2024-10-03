package com.luck.picture.lib.photoview;

import android.view.View;

/* loaded from: classes2.dex */
class Compat {
    Compat() {
    }

    public static void postOnAnimation(View view, Runnable runnable) {
        postOnAnimationJellyBean(view, runnable);
    }

    private static void postOnAnimationJellyBean(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }
}
