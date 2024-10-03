package com.unity3d.player;

import android.content.Context;
import com.unity3d.player.C2705a;

/* loaded from: classes3.dex */
public class AudioVolumeHandler implements C2705a.b {

    /* renamed from: a */
    private C2705a f1610a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioVolumeHandler(Context context) {
        C2705a c2705a = new C2705a(context);
        this.f1610a = c2705a;
        c2705a.m1287a(this);
    }

    /* renamed from: a */
    public final void m1226a() {
        this.f1610a.m1286a();
        this.f1610a = null;
    }

    @Override // com.unity3d.player.C2705a.b
    public final native void onAudioVolumeChanged(int i);
}
