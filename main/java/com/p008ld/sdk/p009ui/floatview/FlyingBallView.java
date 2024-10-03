package com.p008ld.sdk.p009ui.floatview;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.view.GravityCompat;
import com.p008ld.sdk.core.UserAccountMgr;
import com.p008ld.sdk.p009ui.zzb.zzd;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzk;
import com.p008ld.sdk.util.zzt;
import com.tencent.p014av.ptt.PttError;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class FlyingBallView extends FrameLayout implements View.OnTouchListener {
    private static int BALL_FULL_HEIGHT = 0;
    private static int BALL_FULL_SIZE = 0;
    private static int BALL_FULL_WIDTH = 0;
    private static int BALL_HIDE_HEIGHT = 0;
    private static int BALL_HIDE_WIDTH = 0;
    private static final float DP_FLOAT_FULL_HEIGHT = 42.0f;
    private static final float DP_FLOAT_FULL_WIDTH = 42.0f;
    private static final float DP_FLOAT_HIDE_HEIGHT = 42.0f;
    private static final float DP_FLOAT_HIDE_WIDTH = 17.0f;
    private static final float DP_RED_DOT_HEIGHT = 8.0f;
    private static final float DP_RED_DOT_WIDTH = 8.0f;
    private static final float FULL_ALPHA = 1.0f;
    private static final float HALF_ALPHA = 0.7f;
    private static int RED_DOT_HEIGHT = 0;
    private static int RED_DOT_WIDTH = 0;
    private static final int TO_HALF_FLYING_BALL = 1;
    private static final int TO_SMALL_FLYING_BALL = 2;
    private ImageView ballIv;
    private FrameLayout frame;
    private Bitmap fullBitmap;
    private Bitmap halfBitmap;
    private TimerTask halfTimerTask;
    private TimerTask hideTimerTask;
    private int iconHeight;
    private int iconWidth;
    boolean isMnq;
    private boolean isMoved;
    private boolean isOnlyFullBall;
    private boolean isOnlySmall;
    private boolean isRightSide;
    private boolean isTopSide;
    boolean isVisibleHot;
    private Bitmap leftBitmap;
    private Activity mActivity;
    private zzd mCouponTipsDialog;
    private int mPadding;
    private Runnable mRunnable;
    private final Handler mTimerHandler;
    private ImageView redDot;
    private Bitmap redDotBitmap;
    private Bitmap rightBitmap;
    private RootLinearLayout rootView;
    private int screenHeight;
    private int screenWidth;
    private Timer timer;
    private Bitmap topBitmap;
    private Bitmap touchBitmap;
    private float touchStartX;
    private float touchStartY;
    private Bitmap transRedDotBitmap;
    private WindowManager.LayoutParams wlp;

    /* renamed from: wm */
    private WindowManager f689wm;

    public FlyingBallView(Activity activity) {
        super(activity);
        this.isRightSide = false;
        this.isTopSide = false;
        this.isOnlyFullBall = false;
        this.isOnlySmall = false;
        this.isMoved = false;
        this.mTimerHandler = new Handler(Looper.getMainLooper()) { // from class: com.ld.sdk.ui.floatview.FlyingBallView.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 2) {
                    if (FlyingBallView.this.isOnlyFullBall) {
                        FlyingBallView.this.resetRootViewStatus(true, false);
                        FlyingBallView.this.refreshBall();
                    }
                } else if (message.what == 1 && FlyingBallView.this.isOnlyFullBall) {
                    FlyingBallView.this.redDot.setImageBitmap(FlyingBallView.this.transRedDotBitmap);
                    FlyingBallView.this.ballIv.setImageBitmap(FlyingBallView.this.halfBitmap);
                    FlyingBallView.this.wlp.alpha = 1.0f;
                    WindowManager windowManager = FlyingBallView.this.f689wm;
                    FlyingBallView flyingBallView = FlyingBallView.this;
                    windowManager.updateViewLayout(flyingBallView, flyingBallView.wlp);
                }
                super.handleMessage(message);
            }
        };
        this.mRunnable = new Runnable() { // from class: com.ld.sdk.ui.floatview.FlyingBallView.4
            @Override // java.lang.Runnable
            public void run() {
                FlyingBallView.this.showFullView();
            }
        };
        this.mActivity = activity;
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        boolean zza = zzi.zza();
        this.isMnq = zza;
        if (zza) {
            this.mPadding = dip2Px(activity, 1.0f);
        } else {
            this.mPadding = dip2Px(activity, 6.0f);
        }
        BALL_FULL_SIZE = dip2Px(activity, 42.0f) + this.mPadding;
        BALL_FULL_WIDTH = dip2Px(activity, 42.0f) + (this.mPadding * 2);
        BALL_FULL_HEIGHT = dip2Px(activity, 42.0f) + (this.mPadding * 2);
        BALL_HIDE_HEIGHT = dip2Px(activity, 42.0f) + (this.mPadding * 2);
        BALL_HIDE_WIDTH = dip2Px(activity, DP_FLOAT_HIDE_WIDTH) + this.mPadding;
        RED_DOT_WIDTH = dip2Px(activity, 8.0f);
        RED_DOT_HEIGHT = dip2Px(activity, 8.0f);
        Activity activity2 = this.mActivity;
        if (activity2 == null || activity2.isFinishing() || this.mActivity.isDestroyed()) {
            return;
        }
        resToBitmap(this.mActivity);
        createWM();
        createViews();
        addView(this.rootView);
        this.f689wm.addView(this, this.wlp);
        this.timer = new Timer();
    }

    private void createWM() {
        this.f689wm = this.mActivity.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.f689wm.getDefaultDisplay().getMetrics(displayMetrics);
        this.screenWidth = displayMetrics.widthPixels;
        this.screenHeight = displayMetrics.heightPixels;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.wlp = layoutParams;
        layoutParams.type = 99;
        this.wlp.format = 1;
        this.wlp.flags = 40;
        this.wlp.gravity = 51;
        int zzb = zzk.zza(this.mActivity).zzb("flying_ball_index_x");
        int zzb2 = zzk.zza(this.mActivity).zzb("flying_ball_index_y");
        if (zzb != -1 && zzb2 != -1) {
            this.wlp.x = zzb;
            this.wlp.y = zzb2;
        } else if (zzi.zza(this.mActivity)) {
            this.wlp.x = 0;
            WindowManager.LayoutParams layoutParams2 = this.wlp;
            int i = this.screenHeight;
            layoutParams2.y = (i / 2) - ((((i / 2) / 2) / 2) / 2);
        } else {
            this.wlp.x = 0;
            WindowManager.LayoutParams layoutParams3 = this.wlp;
            int i2 = this.screenHeight;
            layoutParams3.y = (int) ((i2 / 2) - ((i2 / 2) / 2.5d));
        }
        if (zzb2 == 0) {
            this.isTopSide = true;
        } else if (this.wlp.x >= this.screenWidth / 2) {
            this.isRightSide = true;
        }
        this.wlp.width = -2;
        this.wlp.height = -2;
        this.wlp.systemUiVisibility = PttError.RECORDER_MIC_PERMISSION_ERROR;
    }

    private void createViews() {
        if (this.rootView == null) {
            RootLinearLayout rootLinearLayout = new RootLinearLayout(this.mActivity);
            this.rootView = rootLinearLayout;
            rootLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(BALL_FULL_WIDTH, BALL_FULL_HEIGHT));
        }
        if (this.frame == null) {
            FrameLayout frameLayout = new FrameLayout(this.mActivity);
            this.frame = frameLayout;
            frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        }
        if (this.ballIv == null) {
            ImageView imageView = new ImageView(this.mActivity);
            this.ballIv = imageView;
            imageView.setLayoutParams(new FrameLayout.LayoutParams(BALL_FULL_WIDTH, BALL_FULL_HEIGHT));
            this.ballIv.setScaleType(ImageView.ScaleType.FIT_XY);
            this.ballIv.setClickable(true);
            this.ballIv.setOnTouchListener(this);
        }
        this.frame.addView(this.ballIv);
        if (this.redDot == null) {
            this.redDot = new ImageView(this.mActivity);
            refreshFullStateRedDot();
            this.redDot.setVisibility(4);
            this.redDot.setScaleType(ImageView.ScaleType.FIT_XY);
            this.redDot.setClickable(false);
        }
        this.frame.addView(this.redDot);
        refreshRootViewGravity();
        this.rootView.addView(this.frame);
        setVisibility(8);
    }

    public void displayFull() {
        if (UserAccountMgr.zza().zzf()) {
            return;
        }
        setVisibility(0);
        resetRootViewStatus(false, true);
        refreshBall();
        startTimerTask();
    }

    public void displaySmall() {
        Message obtainMessage = this.mTimerHandler.obtainMessage();
        obtainMessage.what = 2;
        this.mTimerHandler.sendMessage(obtainMessage);
        stopTimerTask();
    }

    public void disappear() {
        setVisibility(8);
        stopTimerTask();
    }

    public boolean isDisappear() {
        return getVisibility() == 8;
    }

    public void destroy() {
        removeRootView();
        stopTimerTask();
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            this.timer = null;
        }
    }

    private void startTimerTask() {
        TimerTask timerTask = this.halfTimerTask;
        if (timerTask != null) {
            try {
                timerTask.cancel();
                this.halfTimerTask = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        TimerTask timerTask2 = this.hideTimerTask;
        if (timerTask2 != null) {
            try {
                timerTask2.cancel();
                this.hideTimerTask = null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.halfTimerTask = new TimerTask() { // from class: com.ld.sdk.ui.floatview.FlyingBallView.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Message obtainMessage = FlyingBallView.this.mTimerHandler.obtainMessage();
                obtainMessage.what = 1;
                FlyingBallView.this.mTimerHandler.sendMessage(obtainMessage);
            }
        };
        this.hideTimerTask = new TimerTask() { // from class: com.ld.sdk.ui.floatview.FlyingBallView.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Message obtainMessage = FlyingBallView.this.mTimerHandler.obtainMessage();
                obtainMessage.what = 2;
                FlyingBallView.this.mTimerHandler.sendMessage(obtainMessage);
            }
        };
        if (this.isOnlyFullBall) {
            this.timer.schedule(this.halfTimerTask, 1500L, 1500L);
            this.timer.schedule(this.hideTimerTask, 3000L, 1500L);
        }
    }

    private void stopTimerTask() {
        TimerTask timerTask = this.halfTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.halfTimerTask = null;
        }
        TimerTask timerTask2 = this.hideTimerTask;
        if (timerTask2 != null) {
            timerTask2.cancel();
            this.hideTimerTask = null;
        }
    }

    private void removeRootView() {
        try {
            this.f689wm.removeView(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        stopTimerTask();
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.touchStartX = motionEvent.getX();
            this.touchStartY = motionEvent.getY();
            this.isMoved = false;
            this.frame.postDelayed(this.mRunnable, 50L);
            zzd zzdVar = this.mCouponTipsDialog;
            if (zzdVar != null && zzdVar.isShowing()) {
                this.mCouponTipsDialog.dismiss();
            }
        } else if (action == 1) {
            this.frame.removeCallbacks(this.mRunnable);
            this.isTopSide = false;
            this.isRightSide = false;
            if (rawY < this.frame.getHeight()) {
                this.wlp.y = 0;
                this.isTopSide = true;
            } else {
                int i = this.wlp.x;
                int i2 = this.screenWidth;
                if (i >= i2 / 2) {
                    this.wlp.x = i2;
                    this.isRightSide = true;
                } else if (this.wlp.x < this.screenWidth / 2) {
                    this.wlp.x = 0;
                }
            }
            this.wlp.width = -2;
            this.wlp.height = -2;
            this.f689wm.updateViewLayout(this, this.wlp);
            zzk.zza(this.mActivity).zza("flying_ball_index_x", this.wlp.x);
            zzk.zza(this.mActivity).zza("flying_ball_index_y", this.wlp.y);
            if (this.isOnlyFullBall && !this.isMoved) {
                popupAccountShowOrDismiss(0);
            }
            if (!this.isMoved && this.isOnlySmall) {
                resetRootViewStatus(false, true);
                popupAccountShowOrDismiss(0);
            }
            if (this.isOnlyFullBall && this.isMoved) {
                refreshRootViewGravity();
            }
            refreshBall();
            if (this.isOnlyFullBall) {
                startTimerTask();
            }
            this.touchStartY = 0.0f;
            this.touchStartX = 0.0f;
            if (this.isVisibleHot) {
                this.redDot.setVisibility(0);
            }
            this.isVisibleHot = false;
        } else if (action == 2) {
            if (!this.isOnlyFullBall) {
                this.isOnlyFullBall = true;
                this.isOnlySmall = false;
                this.wlp.alpha = 1.0f;
                this.ballIv.setImageBitmap(this.touchBitmap);
            }
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (Math.abs(this.touchStartX - x) > 2.0f || Math.abs(this.touchStartY - y) > 2.0f) {
                this.isMoved = true;
                this.wlp.x = (int) (rawX - this.touchStartX);
                this.wlp.y = (int) (rawY - this.touchStartY);
                this.f689wm.updateViewLayout(this, this.wlp);
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFullView() {
        this.redDot.setImageBitmap(this.redDotBitmap);
        refreshFullStateRedDot();
        if (this.redDot.getVisibility() == 0) {
            this.redDot.setVisibility(8);
            this.isVisibleHot = true;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.ballIv.getLayoutParams();
        this.ballIv.setPadding(0, 0, 0, 0);
        layoutParams.width = BALL_FULL_SIZE;
        layoutParams.height = BALL_FULL_SIZE;
        this.ballIv.setLayoutParams(layoutParams);
        this.ballIv.setImageBitmap(this.touchBitmap);
        this.wlp.alpha = HALF_ALPHA;
        if (!this.isRightSide) {
            this.wlp.width = BALL_FULL_SIZE;
            this.wlp.height = BALL_FULL_SIZE;
        }
        this.f689wm.updateViewLayout(this, this.wlp);
    }

    private void popupAccountShowOrDismiss(int i) {
        Activity activity = this.mActivity;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        UserAccountMgr.zza().zza(i, true, null);
        disappear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetRootViewStatus(boolean z, boolean z2) {
        this.isOnlySmall = z;
        this.isOnlyFullBall = z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBall() {
        ViewGroup.LayoutParams layoutParams = this.ballIv.getLayoutParams();
        if (this.isOnlySmall) {
            this.redDot.setImageBitmap(this.transRedDotBitmap);
            refreshHideStateRedDot();
            if (this.isTopSide) {
                layoutParams.height = BALL_HIDE_WIDTH + (this.isMnq ? this.mPadding : this.mPadding / 2);
                layoutParams.width = BALL_HIDE_HEIGHT;
            } else {
                layoutParams.height = BALL_HIDE_HEIGHT;
                layoutParams.width = BALL_HIDE_WIDTH + (this.isMnq ? this.mPadding : this.mPadding / 2);
            }
            setPadding();
            this.ballIv.setLayoutParams(layoutParams);
            this.wlp.alpha = 1.0f;
            if (this.isTopSide) {
                this.wlp.y = 0;
            } else if (!this.isRightSide) {
                this.wlp.x = 0;
            }
            this.f689wm.updateViewLayout(this, this.wlp);
            zzd zzdVar = this.mCouponTipsDialog;
            if (zzdVar == null || !zzdVar.isShowing()) {
                return;
            }
            this.mCouponTipsDialog.dismiss();
            showCouponTips();
            return;
        }
        if (this.isOnlyFullBall) {
            this.redDot.setImageBitmap(this.redDotBitmap);
            refreshFullStateRedDot();
            layoutParams.width = BALL_FULL_WIDTH;
            layoutParams.height = BALL_FULL_HEIGHT;
            setPadding();
            this.ballIv.setLayoutParams(layoutParams);
            this.ballIv.setImageBitmap(this.fullBitmap);
            this.wlp.alpha = HALF_ALPHA;
            if (this.isTopSide) {
                this.wlp.y = dip2Px(this.mActivity, 8.0f);
            } else if (!this.isRightSide) {
                this.wlp.x = dip2Px(this.mActivity, 8.0f);
            }
            this.f689wm.updateViewLayout(this, this.wlp);
            this.ballIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ld.sdk.ui.floatview.FlyingBallView.5
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (FlyingBallView.this.iconWidth == 0) {
                        FlyingBallView flyingBallView = FlyingBallView.this;
                        flyingBallView.iconWidth = flyingBallView.ballIv.getWidth();
                        FlyingBallView flyingBallView2 = FlyingBallView.this;
                        flyingBallView2.iconHeight = flyingBallView2.ballIv.getHeight();
                    }
                    FlyingBallView.this.ballIv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    private void setPadding() {
        this.ballIv.setImageBitmap(this.leftBitmap);
        this.ballIv.setPadding(0, 0, 0, 0);
        refreshHideStateRedDot();
    }

    private void refreshFullStateRedDot() {
        int i;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(RED_DOT_WIDTH, RED_DOT_HEIGHT);
        if (this.isTopSide) {
            i = 85;
        } else if (this.isRightSide) {
            layoutParams.leftMargin = 0;
            layoutParams.bottomMargin = 0;
            i = GravityCompat.START;
        } else {
            i = GravityCompat.END;
        }
        layoutParams.gravity = i;
        layoutParams.topMargin = dip2Px(this.mActivity, 3.0f);
        layoutParams.rightMargin = dip2Px(this.mActivity, 2.0f);
        this.redDot.setLayoutParams(layoutParams);
    }

    private void refreshHideStateRedDot() {
        int i;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(RED_DOT_WIDTH, RED_DOT_HEIGHT);
        if (this.isTopSide) {
            this.ballIv.setImageBitmap(this.topBitmap);
            int i2 = this.mPadding;
            layoutParams.rightMargin = i2 + (i2 / 4);
            int i3 = this.mPadding;
            layoutParams.bottomMargin = i3 + (i3 / 4);
            i = 85;
        } else if (this.isRightSide) {
            this.ballIv.setImageBitmap(this.rightBitmap);
            layoutParams.topMargin = dip2Px(this.mActivity, 10.0f);
            layoutParams.leftMargin = this.mPadding;
            i = GravityCompat.START;
        } else {
            this.ballIv.setImageBitmap(this.leftBitmap);
            layoutParams.topMargin = dip2Px(this.mActivity, 10.0f);
            layoutParams.rightMargin = this.mPadding;
            i = GravityCompat.END;
        }
        layoutParams.gravity = i;
        this.redDot.setLayoutParams(layoutParams);
    }

    private void refreshRootViewGravity() {
        if (this.isTopSide) {
            this.rootView.setGravity(48);
        } else if (this.isRightSide) {
            this.rootView.setGravity(5);
        } else {
            this.rootView.setGravity(3);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.f689wm.getDefaultDisplay().getMetrics(displayMetrics);
        this.screenWidth = displayMetrics.widthPixels;
        this.screenHeight = displayMetrics.heightPixels;
        int i = this.wlp.x;
        int i2 = this.wlp.y;
        int i3 = configuration.orientation;
        if (i3 != 1) {
            if (i3 == 2) {
                if (this.isRightSide) {
                    this.wlp.x = this.screenWidth;
                    this.wlp.y = i2;
                } else {
                    this.wlp.x = i;
                    this.wlp.y = i2;
                }
            }
        } else if (this.isRightSide) {
            this.wlp.x = this.screenWidth;
            this.wlp.y = i2;
        } else {
            this.wlp.x = i;
            this.wlp.y = i2;
        }
        this.f689wm.updateViewLayout(this, this.wlp);
    }

    private void resToBitmap(Context context) {
        this.fullBitmap = BitmapFactory.decodeResource(this.mActivity.getResources(), zzt.zzd(context, "ld_float_view_icon_default"));
        this.halfBitmap = BitmapFactory.decodeResource(this.mActivity.getResources(), zzt.zzd(context, "ld_float_view_icon_default_half"));
        this.touchBitmap = BitmapFactory.decodeResource(this.mActivity.getResources(), zzt.zzd(context, "ld_float_view_icon_touch"));
        this.leftBitmap = BitmapFactory.decodeResource(this.mActivity.getResources(), zzt.zzd(context, "ld_float_view_icon_hide_left"));
        this.rightBitmap = BitmapFactory.decodeResource(this.mActivity.getResources(), zzt.zzd(context, "ld_float_view_icon_hide_right"));
        this.topBitmap = BitmapFactory.decodeResource(this.mActivity.getResources(), zzt.zzd(context, "ld_float_view_icon_hide_top"));
        this.redDotBitmap = BitmapFactory.decodeResource(this.mActivity.getResources(), zzt.zzd(context, "ld_dot_red"));
        this.transRedDotBitmap = BitmapFactory.decodeResource(this.mActivity.getResources(), zzt.zzd(context, "ld_dot_red_trans"));
    }

    private int dip2Px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        zzd zzdVar;
        super.onVisibilityChanged(view, i);
        if (i == 8 && (zzdVar = this.mCouponTipsDialog) != null && zzdVar.isShowing()) {
            this.mCouponTipsDialog.dismiss();
        }
    }

    public void showCouponTips() {
        if (getVisibility() == 8) {
            return;
        }
        int i = 2;
        int i2 = this.mPadding / 2;
        if (!this.isTopSide) {
            i = this.isRightSide ? 4 : 3;
        } else if (this.screenWidth / 2 > this.wlp.x) {
            i = 1;
        }
        int i3 = i;
        zzd zzdVar = this.mCouponTipsDialog;
        if (zzdVar == null || !zzdVar.isShowing()) {
            zzd zzdVar2 = new zzd(this.mActivity);
            this.mCouponTipsDialog = zzdVar2;
            zzdVar2.zza(i3, this.isOnlySmall, this.wlp.x, this.wlp.y, this.iconWidth, this.iconHeight, i2);
            this.rootView.postDelayed(new Runnable() { // from class: com.ld.sdk.ui.floatview.FlyingBallView.6
                @Override // java.lang.Runnable
                public void run() {
                    if (FlyingBallView.this.mCouponTipsDialog == null || !FlyingBallView.this.mCouponTipsDialog.isShowing()) {
                        return;
                    }
                    FlyingBallView.this.mCouponTipsDialog.dismiss();
                }
            }, 60000L);
            return;
        }
        this.mCouponTipsDialog.zzb(i3, this.isOnlySmall, this.wlp.x, this.wlp.y, this.iconWidth, this.iconHeight, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class RootLinearLayout extends LinearLayout {
        public RootLinearLayout(Context context) {
            super(context);
        }
    }

    public void displayRedDot() {
        this.redDot.setVisibility(0);
    }

    public void hideRedDot() {
        this.redDot.setVisibility(4);
    }
}
