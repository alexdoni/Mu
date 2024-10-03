package com.p008ld.sdk.p009ui.floatview;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.p008ld.sdk.p009ui.floatview.FlyingBallService;
import com.p008ld.sdk.util.zzi;

/* loaded from: classes2.dex */
public class FlyingBall {
    private static FlyingBall flyingBall;
    private FlyingBallService flyingBallService = null;
    private Activity mContext = null;
    private Intent intent = null;
    private ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.ld.sdk.ui.floatview.FlyingBall.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            FlyingBall.this.flyingBallService = ((FlyingBallService.FlyingBallServiceBinder) iBinder).getService();
            FlyingBall.this.flyingBallService.init(FlyingBall.this.mContext);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            FlyingBall.this.flyingBallService = null;
        }
    };

    private FlyingBall() {
    }

    public static FlyingBall getInstance() {
        if (flyingBall == null) {
            synchronized (FlyingBall.class) {
                flyingBall = new FlyingBall();
            }
        }
        return flyingBall;
    }

    public void init(Activity activity) {
        if (zzi.zzb(activity)) {
            return;
        }
        this.mContext = activity;
        if (this.flyingBallService == null) {
            synchronized (FlyingBall.class) {
                try {
                    Intent intent = new Intent(this.mContext, (Class<?>) FlyingBallService.class);
                    this.intent = intent;
                    this.mContext.startService(intent);
                    this.mContext.bindService(this.intent, this.serviceConnection, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void displayFull(Activity activity) {
        if (this.flyingBallService == null || !isDisappear() || activity == null || activity.isFinishing() || activity.getPackageName().equals("com.android.flysilkworm")) {
            return;
        }
        this.flyingBallService.displayFull();
        hideRedDot();
    }

    public void displaySmall() {
        FlyingBallService flyingBallService = this.flyingBallService;
        if (flyingBallService != null) {
            flyingBallService.displaySmall();
        }
    }

    public void disappear() {
        if (isDisappear()) {
            return;
        }
        this.flyingBallService.disappear();
        this.flyingBallService.hideRedDot();
    }

    public void destroy() {
        FlyingBallService flyingBallService = this.flyingBallService;
        if (flyingBallService != null) {
            flyingBallService.destroy();
            this.mContext.unbindService(this.serviceConnection);
            this.mContext.stopService(this.intent);
            flyingBall = null;
        }
    }

    private boolean isDisappear() {
        FlyingBallService flyingBallService = this.flyingBallService;
        return flyingBallService == null || flyingBallService.isDisappear();
    }

    public void displayRedDot() {
        FlyingBallService flyingBallService = this.flyingBallService;
        if (flyingBallService != null) {
            flyingBallService.displayRedDot();
        }
    }

    public void hideRedDot() {
        FlyingBallService flyingBallService = this.flyingBallService;
        if (flyingBallService != null) {
            flyingBallService.hideRedDot();
        }
    }

    public void showCouponTips() {
        FlyingBallService flyingBallService = this.flyingBallService;
        if (flyingBallService != null) {
            flyingBallService.showCouponTips();
        }
    }
}
