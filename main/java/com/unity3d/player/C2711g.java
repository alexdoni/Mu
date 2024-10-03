package com.unity3d.player;

import android.util.Log;

/* renamed from: com.unity3d.player.g */
/* loaded from: classes3.dex */
final class C2711g {

    /* renamed from: a */
    protected static boolean f1813a = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public static void Log(int i, String str) {
        if (f1813a) {
            return;
        }
        if (i == 6) {
            Log.e("Unity", str);
        }
        if (i == 5) {
            Log.w("Unity", str);
        }
    }
}
