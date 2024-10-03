package com.oversea.ab_firstarea.floatView;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.oversea.ab_firstplatform.statistics.FunSwitch;
import com.xsdk.ab_firstbase.statisics.util.DensityUtil;
import com.xsdk.ab_firstbase.statisics.util.Util;
import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class FloatView extends PopupWindow {
    private static FloatView mInstance;
    public FloatHideTip floatHideTip;
    private FloatMenu floatMenu;
    private int mCnt;
    private Activity mContext;
    private View mFloatLayout;
    private ImageButton mFloatView;
    private boolean mIsFloatViewSmall;
    private boolean mIsPopMenuShow;
    private View mParentView;
    private Timer mTimer;
    private View red_view;
    private String TAG = "Lhwl_FloatView";
    private final int POSITIONLEFT = ComConstants.HANDLER_POSITION_LEFT;
    private int mPosition = ComConstants.HANDLER_POSITION_LEFT;
    private boolean mIsFirLogin = true;
    private boolean isPopInRight = false;
    public boolean isOpenFloatHideTip = true;
    public boolean isHaveFloat = false;
    private int[] location = new int[2];
    public int smillFloatType = 1;
    private Handler mHandler = new Handler() { // from class: com.oversea.ab_firstarea.floatView.FloatView.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 10002) {
                FloatView.this.mFloatLayout.setVisibility(8);
            } else if (i == 10013) {
                FloatView.this.isPopInRight = false;
                FloatView.getInstance().changeNormal(false);
            } else if (i != 10017) {
                switch (i) {
                    case ComConstants.HANDLER_POP_SHOW /* 10020 */:
                        FloatView.this.mIsPopMenuShow = true;
                        FloatViewOntouch.getInstance().setIsPopShow(true);
                        FloatView.this.cancelTimer();
                        break;
                    case ComConstants.HANDLER_FLOAT_SMALL /* 10021 */:
                        FloatView.this.mContext.getResources();
                        if (FloatView.this.mContext != null) {
                            FloatView.this.mContext.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.floatView.FloatView.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (!Util.getSystemLang().equals(LanguageType.SERVER_VI) || !Lxhw_AreaPlatform.getInstance().iShowViInfo) {
                                        FloatView.this.mFloatView.setImageResource(Util.getIdByName(FloatView.this.mContext, "drawable", "tw_toolbar_normalbtn"));
                                    } else {
                                        FloatView.this.mFloatView.setImageResource(Util.getIdByName(FloatView.this.mContext, "drawable", "drhw_toolbar_normalbtn_vi"));
                                    }
                                    if (FloatView.this.isPopInRight) {
                                        FloatView.this.update(DensityUtil.getWidth(FloatView.this.mContext), FloatView.this.changeFloatVH(FloatViewOntouch.getInstance().mScreenY), -1, -1);
                                    }
                                    FloatView.this.mIsFloatViewSmall = false;
                                    FloatViewOntouch.getInstance().setIsFloatSmall(false);
                                    FloatView.this.startTimer();
                                }
                            });
                            break;
                        }
                        break;
                    case ComConstants.HANDLER_POSITION_RIGHT /* 10022 */:
                        FloatView.this.isPopInRight = true;
                        FloatView.getInstance().changeNormal(false);
                        break;
                    case ComConstants.HANDLER_FLOAT_MOVE /* 10023 */:
                        FloatView.this.cancelTimer();
                        break;
                    case ComConstants.HANDLER_POPDISMISS /* 10024 */:
                        FloatViewOntouch.getInstance().setIsPopShow(false);
                        break;
                    case ComConstants.HANDLER_POP_HINT /* 10025 */:
                        FloatView.this.mIsPopMenuShow = false;
                        FloatViewOntouch.getInstance().setIsPopShow(false);
                        FloatView.this.startTimer();
                        break;
                    case ComConstants.HANDLER_FLOAT_CLICKDOWN /* 10026 */:
                        FloatView.this.cancelTimer();
                        FloatView.this.clickDown();
                        break;
                }
            } else {
                if (!FloatView.this.mIsFloatViewSmall) {
                    FloatView.this.startTimer();
                }
                FloatView.this.mFloatLayout.setVisibility(0);
                FloatViewOntouch.getInstance().setIsPopShow(false);
            }
            if (FloatView.this.mIsPopMenuShow) {
                FloatView.this.cancelTimer();
            }
        }
    };

    static /* synthetic */ int access$908(FloatView floatView) {
        int i = floatView.mCnt;
        floatView.mCnt = i + 1;
        return i;
    }

    public static FloatView getInstance() {
        if (mInstance == null) {
            mInstance = new FloatView();
        }
        return mInstance;
    }

    public void startFloatView(Activity activity) {
        if (FunSwitch.getInstance().isOpenFloatView()) {
            this.mContext = activity;
            initView();
            startTimer();
            initFloatHideTip();
            this.isHaveFloat = true;
        }
    }

    private void initView() {
        if (Util.getSystemLang().equals(LanguageType.SERVER_VI) && Lxhw_AreaPlatform.getInstance().iShowViInfo) {
            this.mFloatLayout = LayoutInflater.from(this.mContext).inflate(Util.getIdByName(this.mContext, "layout", "drhw_xservice_floatwindow_vi"), (ViewGroup) null, false);
        } else {
            this.mFloatLayout = LayoutInflater.from(this.mContext).inflate(Util.getIdByName(this.mContext, "layout", "drhw_xservice_floatwindow"), (ViewGroup) null, false);
        }
        this.mFloatView = (ImageButton) this.mFloatLayout.findViewById(Util.getIdByName(this.mContext, "id", "img_floatwindows"));
        this.red_view = this.mFloatLayout.findViewById(Util.getIdByName(this.mContext, "id", "red_view"));
        if (ManageBean.getInstance().getKfBaseInfoBean().getData().getHas_not_read_issue()) {
            this.red_view.setVisibility(0);
        } else {
            this.red_view.setVisibility(8);
        }
        this.mParentView = ((ViewGroup) this.mContext.getWindow().getDecorView()).getChildAt(0);
        FloatViewOntouch.getInstance().setTwFloatViewOntouch(this.mContext, this.mHandler, this);
        this.mFloatView.setOnTouchListener(FloatViewOntouch.getInstance());
        setWidth(-2);
        setHeight(-2);
        setContentView(this.mFloatLayout);
        fitPopupWindowOverStatusBar(this, true);
        showPop();
    }

    public static void fitPopupWindowOverStatusBar(PopupWindow popupWindow, boolean z) {
        try {
            Field declaredField = PopupWindow.class.getDeclaredField("mLayoutInScreen");
            declaredField.setAccessible(z);
            declaredField.set(popupWindow, Boolean.valueOf(z));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    public void clickDown() {
        this.mContext.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.floatView.FloatView.2
            @Override // java.lang.Runnable
            public void run() {
                FloatView.this.initFloatMenu();
                FloatView.this.showFloatMenu(FloatViewOntouch.getInstance().mScreenX, FloatView.this.changeFloatVH(FloatViewOntouch.getInstance().mScreenY));
                FloatView.this.mFloatLayout.setVisibility(8);
            }
        });
    }

    public void showRedView() {
        Activity activity;
        try {
            if (!FunSwitch.getInstance().isOpenFloatView() || (activity = this.mContext) == null || this.mFloatLayout == null || this.red_view == null) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.floatView.FloatView.3
                @Override // java.lang.Runnable
                public void run() {
                    if (ManageBean.getInstance().getKfBaseInfoBean().getData().getHas_not_read_issue()) {
                        FloatView.this.red_view.setVisibility(0);
                    } else {
                        FloatView.this.red_view.setVisibility(8);
                    }
                }
            });
        } catch (Throwable unused) {
        }
    }

    public void changeNormal(final boolean z) {
        Activity activity = this.mContext;
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.floatView.FloatView.4
                @Override // java.lang.Runnable
                public void run() {
                    FloatView.this.cancelTimer();
                    FloatViewOntouch.getInstance().isStartTimer(true);
                    if (!z) {
                        FloatView.this.mIsFloatViewSmall = true;
                        FloatViewOntouch.getInstance().setIsFloatSmall(true);
                        if (FloatView.this.smillFloatType == 0) {
                            if (FloatView.this.isPopInRight) {
                                FloatView.this.mFloatView.setBackgroundResource(Util.getIdByName(FloatView.this.mContext, "drawable", "drhw_toolbar_normalbtn_rightsmall"));
                                return;
                            } else {
                                FloatView.this.mFloatView.setBackgroundResource(Util.getIdByName(FloatView.this.mContext, "drawable", "drhw_toolbar_normalbtn_small"));
                                return;
                            }
                        }
                        if (!Util.getSystemLang().equals(LanguageType.SERVER_VI) || !Lxhw_AreaPlatform.getInstance().iShowViInfo) {
                            FloatView.this.mFloatView.setBackgroundResource(Util.getIdByName(FloatView.this.mContext, "drawable", "smillfloat"));
                            return;
                        } else {
                            FloatView.this.mFloatView.setBackgroundResource(Util.getIdByName(FloatView.this.mContext, "drawable", "smillfloat_vi"));
                            return;
                        }
                    }
                    if (!Util.getSystemLang().equals(LanguageType.SERVER_VI) || !Lxhw_AreaPlatform.getInstance().iShowViInfo) {
                        FloatView.this.mFloatView.setBackgroundResource(Util.getIdByName(FloatView.this.mContext, "drawable", "tw_toolbar_normalbtn"));
                    } else {
                        FloatView.this.mFloatView.setBackgroundResource(Util.getIdByName(FloatView.this.mContext, "drawable", "drhw_toolbar_normalbtn_vi"));
                    }
                    FloatView.this.mIsFloatViewSmall = false;
                    FloatViewOntouch.getInstance().setIsFloatSmall(false);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFloatMenu() {
        removeFloatMenu();
        this.floatMenu = new FloatMenu(this.mContext, 0);
    }

    private void initFloatHideTip() {
        if (this.isOpenFloatHideTip) {
            removeFloatHideTip();
            this.floatHideTip = new FloatHideTip(this.mContext, 0);
        }
    }

    public void hideAll() {
        Log.v("floatView", "hideall");
        if (FunSwitch.getInstance().isOpenFloatView()) {
            onDestroyFloatView();
        }
    }

    public void startTimer() {
        if (FunSwitch.getInstance().isOpenFloatView()) {
            try {
                FloatViewOntouch.getInstance().setIsFloatSmall(false);
                this.mCnt = 0;
                Timer timer = this.mTimer;
                if (timer != null) {
                    timer.cancel();
                }
                if (!this.mIsFirLogin) {
                    FloatViewOntouch.getInstance().isStartTimer(true);
                } else {
                    this.mIsFirLogin = false;
                }
                Timer timer2 = new Timer();
                this.mTimer = timer2;
                timer2.schedule(new TimerTask() { // from class: com.oversea.ab_firstarea.floatView.FloatView.5
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        FloatView.access$908(FloatView.this);
                        if (FloatView.this.mCnt >= 18) {
                            FloatView.this.mFloatView.post(new Runnable() { // from class: com.oversea.ab_firstarea.floatView.FloatView.5.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    FloatViewOntouch.getInstance().setIsFloatSmall(true);
                                    if (FloatView.this.isPopInRight) {
                                        Log.i(FloatView.this.TAG, "startTimer 12");
                                        if (FloatView.this.smillFloatType == 0) {
                                            FloatView.this.mFloatView.setBackgroundResource(Util.getIdByName(FloatView.this.mContext, "drawable", "drhw_toolbar_normalbtn_rightsmall"));
                                        } else if (!Util.getSystemLang().equals(LanguageType.SERVER_VI) || !Lxhw_AreaPlatform.getInstance().iShowViInfo) {
                                            FloatView.this.mFloatView.setBackgroundResource(Util.getIdByName(FloatView.this.mContext, "drawable", "smillfloat"));
                                        } else {
                                            FloatView.this.mFloatView.setBackgroundResource(Util.getIdByName(FloatView.this.mContext, "drawable", "smillfloat_vi"));
                                        }
                                        FloatView.this.getContentView().measure(0, 0);
                                        FloatView.this.update(DensityUtil.getWidth(FloatView.this.mContext) + FloatView.this.getContentView().getWidth(), FloatView.this.changeFloatVH(FloatViewOntouch.getInstance().mScreenY), -1, -1);
                                        return;
                                    }
                                    if (FloatView.this.smillFloatType == 0) {
                                        FloatView.this.mFloatView.setBackgroundResource(Util.getIdByName(FloatView.this.mContext, "drawable", "drhw_toolbar_normalbtn_small"));
                                    } else if (!Util.getSystemLang().equals(LanguageType.SERVER_VI) || !Lxhw_AreaPlatform.getInstance().iShowViInfo) {
                                        FloatView.this.mFloatView.setBackgroundResource(Util.getIdByName(FloatView.this.mContext, "drawable", "smillfloat"));
                                    } else {
                                        FloatView.this.mFloatView.setBackgroundResource(Util.getIdByName(FloatView.this.mContext, "drawable", "smillfloat_vi"));
                                    }
                                    FloatView.this.update(0, FloatView.this.changeFloatVH(FloatViewOntouch.getInstance().mScreenY), -1, -1);
                                }
                            });
                            FloatView.this.cancelTimer();
                        }
                    }
                }, 0L, 100L);
            } catch (Throwable th) {
                Log.i(this.TAG, "startTimer Throwable e=" + th.toString());
            }
        }
    }

    public void setFloatHideTipBackageground(boolean z) {
        FloatHideTip floatHideTip;
        if (!this.isOpenFloatHideTip || (floatHideTip = this.floatHideTip) == null) {
            return;
        }
        floatHideTip.setfloatMenuVBackGround(z);
    }

    public int[] getxy() {
        int[] iArr;
        ImageButton imageButton = this.mFloatView;
        if (imageButton != null && (iArr = this.location) != null) {
            imageButton.getLocationOnScreen(iArr);
        }
        return this.location;
    }

    public int changeFloatVH(int i) {
        if (i == 0) {
            return 120;
        }
        if (this.mFloatView != null) {
            int height = this.mFloatLayout.getHeight();
            if (i <= height) {
                return height;
            }
            int i2 = height / 2;
            if (i + i2 > DensityUtil.getHeiht(this.mContext)) {
                return DensityUtil.getHeiht(this.mContext) - i2;
            }
        }
        Log.i(this.TAG, "floath=" + i);
        return i;
    }

    public int changeFloatVW(int i) {
        if (i <= 0) {
            return 0;
        }
        if (this.mFloatView == null) {
            return i;
        }
        this.mFloatLayout.getWidth();
        return i > DensityUtil.getWidth(this.mContext) ? DensityUtil.getWidth(this.mContext) : i;
    }

    public void viewTranslateA(float f, float f2, float f3, float f4, Animation.AnimationListener animationListener) {
        TranslateAnimation translateAnimation = new TranslateAnimation(f, f2, f3, f4);
        translateAnimation.setDuration(2000L);
        translateAnimation.setAnimationListener(animationListener);
        if (getContentView() != null) {
            getContentView().startAnimation(translateAnimation);
        }
    }

    public void setFloatClickable(boolean z) {
        ImageButton imageButton = this.mFloatView;
        if (imageButton != null) {
            imageButton.setClickable(z);
        }
    }

    public void hideFloatMenu() {
        this.floatMenu.hideFloatMenuView();
    }

    public void showFloatMenu(int i, int i2) {
        this.floatMenu.showFloatmenu(i, i2);
    }

    public void removeFloatMenu() {
        FloatMenu floatMenu = this.floatMenu;
        if (floatMenu != null) {
            floatMenu.removeFloatMenuView();
            this.floatMenu = null;
        }
    }

    public void showFloatHideTip(boolean z) {
        FloatHideTip floatHideTip;
        if (!this.isOpenFloatHideTip || (floatHideTip = this.floatHideTip) == null) {
            return;
        }
        floatHideTip.showFloatmenu(z);
        floatHideTipSetxywh();
    }

    public void floatHideTipSetxywh() {
        FloatHideTip floatHideTip;
        if (!this.isOpenFloatHideTip || (floatHideTip = this.floatHideTip) == null) {
            return;
        }
        floatHideTip.setxywh();
    }

    public void removeFloatHideTip() {
        FloatHideTip floatHideTip;
        if (!this.isOpenFloatHideTip || (floatHideTip = this.floatHideTip) == null) {
            return;
        }
        floatHideTip.removeFloatMenuView();
        this.floatHideTip = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelTimer() {
        Log.i(this.TAG, "startTimer cancelTimer 0");
        if (this.mTimer != null) {
            Log.i(this.TAG, "startTimer cancelTimer 1");
            this.mTimer.cancel();
        }
        Log.i(this.TAG, "startTimer cancelTimer 2");
        this.mCnt = 0;
    }

    public void onDestroyFloatView() {
        Log.v("floatView", "destroyf");
        if (FunSwitch.getInstance().isOpenFloatView()) {
            try {
                Activity activity = this.mContext;
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.floatView.FloatView.6
                        @Override // java.lang.Runnable
                        public void run() {
                            FloatView.this.removeFloatHideTip();
                            FloatView.this.dismiss();
                            FloatView.this.mPosition = ComConstants.HANDLER_POSITION_LEFT;
                            FloatViewOntouch.getInstance().setIsPopShow(false);
                            FloatViewOntouch.getInstance().onDestory();
                            FloatView.this.cancelTimer();
                            FloatView.this.removeFloatMenu();
                            FloatView.this.isPopInRight = false;
                        }
                    });
                }
            } catch (Throwable unused) {
                Log.i("Throwable", "***onDestroyFloatView isshowing");
            }
        }
    }

    public void showPop() {
        if (FunSwitch.getInstance().isOpenFloatView()) {
            Log.i(this.TAG, "showPop");
            if (this.mParentView == null && isShowing()) {
                return;
            }
            View view = this.mFloatLayout;
            if (view != null) {
                view.setVisibility(0);
            }
            try {
                FloatViewOntouch.getInstance().mScreenY = ((int) DensityUtil.getScreenHeight(this.mContext)) / 2;
                showAtLocation(this.mParentView, 51, 0, changeFloatVH(FloatViewOntouch.getInstance().mScreenY));
            } catch (Throwable unused) {
            }
        }
    }

    public void resumePop() {
        ImageButton imageButton = this.mFloatView;
        if (imageButton != null) {
            imageButton.setVisibility(0);
        }
    }

    public void ondismiss() {
        ImageButton imageButton = this.mFloatView;
        if (imageButton != null) {
            imageButton.setVisibility(4);
        }
    }
}
