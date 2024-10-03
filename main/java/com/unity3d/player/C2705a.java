package com.unity3d.player;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.luck.picture.lib.config.PictureMimeType;

/* renamed from: com.unity3d.player.a */
/* loaded from: classes3.dex */
final class C2705a {

    /* renamed from: a */
    private final Context f1758a;

    /* renamed from: b */
    private final AudioManager f1759b;

    /* renamed from: c */
    private a f1760c;

    /* renamed from: com.unity3d.player.a$a */
    /* loaded from: classes3.dex */
    private class a extends ContentObserver {

        /* renamed from: b */
        private final b f1762b;

        /* renamed from: c */
        private final AudioManager f1763c;

        /* renamed from: d */
        private final int f1764d;

        /* renamed from: e */
        private int f1765e;

        public a(Handler handler, AudioManager audioManager, int i, b bVar) {
            super(handler);
            this.f1763c = audioManager;
            this.f1764d = 3;
            this.f1762b = bVar;
            this.f1765e = audioManager.getStreamVolume(3);
        }

        @Override // android.database.ContentObserver
        public final boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            int streamVolume;
            AudioManager audioManager = this.f1763c;
            if (audioManager == null || this.f1762b == null || (streamVolume = audioManager.getStreamVolume(this.f1764d)) == this.f1765e) {
                return;
            }
            this.f1765e = streamVolume;
            this.f1762b.onAudioVolumeChanged(streamVolume);
        }
    }

    /* renamed from: com.unity3d.player.a$b */
    /* loaded from: classes3.dex */
    public interface b {
        void onAudioVolumeChanged(int i);
    }

    public C2705a(Context context) {
        this.f1758a = context;
        this.f1759b = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
    }

    /* renamed from: a */
    public final void m1286a() {
        if (this.f1760c != null) {
            this.f1758a.getContentResolver().unregisterContentObserver(this.f1760c);
            this.f1760c = null;
        }
    }

    /* renamed from: a */
    public final void m1287a(b bVar) {
        this.f1760c = new a(new Handler(), this.f1759b, 3, bVar);
        this.f1758a.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.f1760c);
    }
}
