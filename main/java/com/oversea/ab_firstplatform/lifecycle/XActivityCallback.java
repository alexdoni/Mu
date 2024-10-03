package com.oversea.ab_firstplatform.lifecycle;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

/* loaded from: classes2.dex */
public interface XActivityCallback {
    void onActivityResult(int i, int i2, Intent intent);

    void onBackPressed();

    void onConfigurationChanged(Configuration configuration);

    void onCreate(Bundle bundle);

    void onDestroy();

    void onNewIntent(Intent intent);

    void onPause();

    void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    void onRestart();

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    void onStop();

    void onWindowFocusChanged(boolean z);
}
