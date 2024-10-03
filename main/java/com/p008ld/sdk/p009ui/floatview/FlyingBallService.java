package com.p008ld.sdk.p009ui.floatview;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/* loaded from: classes2.dex */
public class FlyingBallService extends Service {
    private FlyingBallView flyingBallView = null;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new FlyingBallServiceBinder();
    }

    /* loaded from: classes2.dex */
    public class FlyingBallServiceBinder extends Binder {
        public FlyingBallServiceBinder() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public FlyingBallService getService() {
            return FlyingBallService.this;
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void init(Activity activity) {
        if (this.flyingBallView == null) {
            this.flyingBallView = new FlyingBallView(activity);
        }
    }

    public void displayFull() {
        FlyingBallView flyingBallView = this.flyingBallView;
        if (flyingBallView != null) {
            flyingBallView.displayFull();
        }
    }

    public void displaySmall() {
        FlyingBallView flyingBallView = this.flyingBallView;
        if (flyingBallView != null) {
            flyingBallView.displaySmall();
        }
    }

    public void disappear() {
        FlyingBallView flyingBallView = this.flyingBallView;
        if (flyingBallView != null) {
            flyingBallView.disappear();
        }
    }

    public void destroy() {
        FlyingBallView flyingBallView = this.flyingBallView;
        if (flyingBallView != null) {
            flyingBallView.destroy();
            this.flyingBallView = null;
        }
    }

    public boolean isDisappear() {
        FlyingBallView flyingBallView = this.flyingBallView;
        if (flyingBallView != null) {
            return flyingBallView.isDisappear();
        }
        return false;
    }

    public void displayRedDot() {
        FlyingBallView flyingBallView = this.flyingBallView;
        if (flyingBallView != null) {
            flyingBallView.displayRedDot();
        }
    }

    public void hideRedDot() {
        FlyingBallView flyingBallView = this.flyingBallView;
        if (flyingBallView != null) {
            flyingBallView.hideRedDot();
        }
    }

    public void showCouponTips() {
        FlyingBallView flyingBallView = this.flyingBallView;
        if (flyingBallView != null) {
            flyingBallView.showCouponTips();
        }
    }
}
