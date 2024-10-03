package com.google.ads.conversiontracking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/* loaded from: classes.dex */
public class InstallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("referrer");
        if (!"com.android.vending.INSTALL_REFERRER".equals(intent.getAction()) || stringExtra == null) {
            return;
        }
        String valueOf = String.valueOf(stringExtra);
        Log.i("GoogleConversionReporter", valueOf.length() != 0 ? "Received install referrer: ".concat(valueOf) : new String("Received install referrer: "));
        AdWordsConversionReporter.registerReferrer(context, Uri.parse("http://hostname/").buildUpon().appendQueryParameter("referrer", stringExtra).build());
    }
}
