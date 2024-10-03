package com.unity3d.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import com.luck.picture.lib.config.PictureMimeType;

/* loaded from: classes3.dex */
public class HFPStatus {

    /* renamed from: a */
    private Context f1641a;

    /* renamed from: e */
    private AudioManager f1645e;

    /* renamed from: b */
    private BroadcastReceiver f1642b = null;

    /* renamed from: c */
    private Intent f1643c = null;

    /* renamed from: d */
    private boolean f1644d = false;

    /* renamed from: f */
    private int f1646f = EnumC2668a.f1648a;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* renamed from: com.unity3d.player.HFPStatus$a */
    /* loaded from: classes3.dex */
    static final class EnumC2668a {

        /* renamed from: a */
        public static final int f1648a = 1;

        /* renamed from: b */
        public static final int f1649b = 2;

        /* renamed from: c */
        public static final int f1650c = 3;

        /* renamed from: d */
        private static final /* synthetic */ int[] f1651d = {1, 2, 3};
    }

    public HFPStatus(Context context) {
        this.f1645e = null;
        this.f1641a = context;
        this.f1645e = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        initHFPStatusJni();
    }

    private final native void deinitHFPStatusJni();

    private final native void initHFPStatusJni();

    /* renamed from: a */
    public final void m1256a() {
        deinitHFPStatusJni();
    }

    protected boolean getHFPStat() {
        return this.f1646f == EnumC2668a.f1649b;
    }

    protected void requestHFPStat() {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.unity3d.player.HFPStatus.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                if (intExtra == 0) {
                    if (HFPStatus.this.f1644d) {
                        HFPStatus.this.f1645e.setMode(0);
                    }
                    HFPStatus.this.f1644d = false;
                    return;
                }
                if (intExtra == 1) {
                    HFPStatus.this.f1646f = EnumC2668a.f1649b;
                    if (HFPStatus.this.f1644d) {
                        HFPStatus.this.f1645e.setMode(3);
                        return;
                    } else {
                        HFPStatus.this.f1645e.stopBluetoothSco();
                        return;
                    }
                }
                if (intExtra != 2) {
                    return;
                }
                if (HFPStatus.this.f1646f == EnumC2668a.f1649b) {
                    HFPStatus.this.f1644d = true;
                } else {
                    HFPStatus.this.f1646f = EnumC2668a.f1650c;
                }
            }
        };
        this.f1642b = broadcastReceiver;
        this.f1643c = this.f1641a.registerReceiver(broadcastReceiver, new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED"));
        try {
            this.f1645e.startBluetoothSco();
        } catch (NullPointerException unused) {
            C2711g.Log(5, "startBluetoothSco() failed. no bluetooth device connected.");
        }
    }
}
