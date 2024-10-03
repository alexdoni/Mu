package com.linecorp.linesdk.internal;

import android.app.Fragment;
import android.content.Intent;

/* loaded from: classes2.dex */
public class FragmentWrapper {
    private Fragment fragment;
    private androidx.fragment.app.Fragment supportFragment;

    public FragmentWrapper(Fragment fragment) {
        this.fragment = fragment;
    }

    public FragmentWrapper(androidx.fragment.app.Fragment fragment) {
        this.supportFragment = fragment;
    }

    public void startActivityForResult(Intent intent, int i) {
        Fragment fragment = this.fragment;
        if (fragment != null) {
            fragment.startActivityForResult(intent, i);
            return;
        }
        androidx.fragment.app.Fragment fragment2 = this.supportFragment;
        if (fragment2 != null) {
            fragment2.startActivityForResult(intent, i);
        }
    }
}
