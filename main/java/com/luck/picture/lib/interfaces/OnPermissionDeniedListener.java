package com.luck.picture.lib.interfaces;

import androidx.fragment.app.Fragment;

/* loaded from: classes2.dex */
public interface OnPermissionDeniedListener {
    void onDenied(Fragment fragment, String[] strArr, int i, OnCallbackListener<Boolean> onCallbackListener);
}
