package com.linecorp.linesdk.auth.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* loaded from: classes2.dex */
public class LineAuthenticationCallbackActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent(this, (Class<?>) LineAuthenticationActivity.class);
        intent.setData(getIntent().getData());
        intent.setFlags(335544320);
        startActivity(intent);
        finish();
    }
}
