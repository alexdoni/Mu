package com.oversea.ab_firstarea.floatView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.xsdk.ab_firstbase.statisics.util.DensityUtil;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class ActivityFloatView extends PopupWindow implements View.OnTouchListener, View.OnClickListener {
    private static ActivityFloatView mInstance;

    /* renamed from: dx */
    private int f781dx;

    /* renamed from: dy */
    private int f782dy;
    private FloatHideTip floatHideTip;
    private boolean isShowContent;
    private int lastX;
    private int lastY;
    private Activity mContext;
    private View mFloatLayout;
    private ImageButton mFloatView;
    private View mParentView;
    private int mScreenY = 0;
    private int mScreenX = 0;
    private boolean isPopInRight = false;
    private boolean isCollision = false;
    int tipx = 0;
    int tipy = 0;
    int tipw = 0;
    int tiph = 0;
    private int[] location = new int[2];

    public static synchronized ActivityFloatView getInstance() {
        ActivityFloatView activityFloatView;
        synchronized (ActivityFloatView.class) {
            if (mInstance == null) {
                mInstance = new ActivityFloatView();
            }
            activityFloatView = mInstance;
        }
        return activityFloatView;
    }

    public void startFloatView(Activity activity) {
        ActivityFloatView activityFloatView = mInstance;
        if (activityFloatView == null || !activityFloatView.isShowing()) {
            this.mContext = activity;
            initView();
            initFloatHideTip();
        }
    }

    public void onDestroyFloatView() {
        if (this.mContext == null || !isShowing()) {
            return;
        }
        try {
            this.mContext.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.floatView.ActivityFloatView.1
                @Override // java.lang.Runnable
                public void run() {
                    ActivityFloatView.this.removeFloatHideTip();
                    ActivityFloatView.this.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        Activity activity = this.mContext;
        if (activity == null) {
            return;
        }
        View inflate = LayoutInflater.from(activity).inflate(Util.getIdByName(this.mContext, "layout", "drhw_xfloatview_tip"), (ViewGroup) null, false);
        this.mFloatLayout = inflate;
        this.mFloatView = (ImageButton) inflate.findViewById(Util.getIdByName(this.mContext, "id", "img_floatwindows_tip"));
        setViewSize(false);
        this.mFloatView.setBackgroundResource(Util.getIdByName(this.mContext, "drawable", "drhw_tip_vi"));
        this.mFloatView.setOnClickListener(this);
        this.mFloatView.setOnTouchListener(this);
        this.mParentView = ((ViewGroup) this.mContext.getWindow().getDecorView()).getChildAt(0);
        setWidth(-2);
        setHeight(-2);
        setContentView(this.mFloatLayout);
        fitPopupWindowOverStatusBar(this, true);
        showPop();
    }

    public void showPop() {
        if (this.mParentView == null || isShowing()) {
            return;
        }
        int screenHeight = ((int) DensityUtil.getScreenHeight(this.mContext)) / 5;
        this.mScreenY = screenHeight;
        this.f782dy = screenHeight;
        this.mScreenX = 0;
        showAtLocation(this.mParentView, 51, 0, screenHeight);
    }

    public void fitPopupWindowOverStatusBar(PopupWindow popupWindow, boolean z) {
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

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000b, code lost:
    
        if (r4 != 2) goto L25;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r4, android.view.MotionEvent r5) {
        /*
            r3 = this;
            int r4 = r5.getAction()
            r0 = 0
            if (r4 == 0) goto L7a
            r1 = 1
            if (r4 == r1) goto Lf
            r2 = 2
            if (r4 == r2) goto L16
            goto L88
        Lf:
            boolean r4 = r3.isCollision
            if (r4 == 0) goto L54
            r3.hideAll()
        L16:
            r3.showFloatHideTip(r1)
            float r4 = r5.getRawX()
            int r4 = (int) r4
            int r2 = r3.lastX
            int r4 = r4 - r2
            int r2 = r3.mScreenX
            int r4 = r4 + r2
            r3.f781dx = r4
            float r4 = r5.getRawY()
            int r4 = (int) r4
            int r2 = r3.lastY
            int r4 = r4 - r2
            int r2 = r3.mScreenY
            int r4 = r4 + r2
            r3.f782dy = r4
            int r4 = r3.lastX
            float r4 = (float) r4
            float r5 = r5.getRawX()
            float r4 = r4 - r5
            int r4 = (int) r4
            boolean r4 = r3.isCollisionFloatHideTip(r4)
            r3.isCollision = r4
            if (r4 == 0) goto L48
            r3.setFloatHideTipBackageground(r1)
            goto L4b
        L48:
            r3.setFloatHideTipBackageground(r0)
        L4b:
            int r4 = r3.f781dx
            int r5 = r3.f782dy
            r1 = -1
            r3.update(r4, r5, r1, r1)
            goto L88
        L54:
            int r4 = r3.f782dy
            r3.mScreenY = r4
            r3.showFloatHideTip(r0)
            float r4 = r5.getRawX()
            int r4 = (int) r4
            int r2 = r3.lastX
            int r4 = r4 - r2
            int r4 = java.lang.Math.abs(r4)
            r2 = 10
            if (r4 >= r2) goto L76
            float r4 = r5.getRawY()
            int r4 = (int) r4
            int r5 = r3.lastY
            int r4 = r4 - r5
            if (r4 >= r2) goto L76
            return r0
        L76:
            r3.floatViewToGo()
            return r1
        L7a:
            float r4 = r5.getRawX()
            int r4 = (int) r4
            r3.lastX = r4
            float r4 = r5.getRawY()
            int r4 = (int) r4
            r3.lastY = r4
        L88:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oversea.ab_firstarea.floatView.ActivityFloatView.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void floatViewToGo() {
        Activity activity;
        if (this.mFloatLayout == null || (activity = this.mContext) == null || activity.isFinishing() || this.mContext.isDestroyed()) {
            return;
        }
        int[] iArr = new int[2];
        getContentView().getLocationOnScreen(iArr);
        if (iArr[0] < (((int) DensityUtil.getScreenWidth(this.mContext)) >> 1)) {
            this.mScreenX = 0;
            update(0, this.mScreenY, -1, -1);
            this.isPopInRight = false;
        } else {
            getContentView().measure(0, 0);
            int screenWidth = (int) DensityUtil.getScreenWidth(this.mContext);
            this.mScreenX = screenWidth;
            update(screenWidth - getContentView().getWidth(), this.mScreenY, -1, -1);
            this.isPopInRight = true;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.isShowContent) {
            setViewSize(false);
            this.mFloatView.setBackgroundResource(Util.getIdByName(this.mContext, "drawable", "drhw_tip_vi"));
        } else {
            setViewSize(true);
            this.mFloatView.setBackgroundResource(Util.getIdByName(this.mContext, "drawable", "drhw_tip_content_vi"));
        }
        this.isShowContent = !this.isShowContent;
    }

    private void setViewSize(boolean z) {
        int dip2px;
        int dip2px2;
        if (!z) {
            dip2px = DensityUtil.dip2px(this.mContext, 35.0f);
            dip2px2 = dip2px;
        } else {
            dip2px = DensityUtil.dip2px(this.mContext, 95.0f);
            dip2px2 = DensityUtil.dip2px(this.mContext, 35.0f);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mFloatView.getLayoutParams();
        layoutParams.width = dip2px;
        layoutParams.height = dip2px2;
        this.mFloatView.setLayoutParams(layoutParams);
    }

    public void hideAll() {
        onDestroyFloatView();
    }

    private void initFloatHideTip() {
        removeFloatHideTip();
        this.floatHideTip = new FloatHideTip(this.mContext, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeFloatHideTip() {
        FloatHideTip floatHideTip = this.floatHideTip;
        if (floatHideTip != null) {
            floatHideTip.removeFloatMenuView();
            this.floatHideTip = null;
        }
    }

    public void showFloatHideTip(boolean z) {
        FloatHideTip floatHideTip = this.floatHideTip;
        if (floatHideTip != null) {
            floatHideTip.showFloatmenu(z);
            floatHideTipSetxywh();
        }
    }

    private void setFloatHideTipBackageground(boolean z) {
        FloatHideTip floatHideTip = this.floatHideTip;
        if (floatHideTip != null) {
            floatHideTip.setfloatMenuVBackGround(z);
        }
    }

    public void floatHideTipSetxywh() {
        FloatHideTip floatHideTip = this.floatHideTip;
        if (floatHideTip != null) {
            floatHideTip.setxywh();
        }
    }

    private boolean isCollisionFloatHideTip(int i) {
        if (Math.abs(i) <= 100) {
            return false;
        }
        int width = getContentView().getWidth();
        int height = getContentView().getHeight();
        FloatHideTip floatHideTip = this.floatHideTip;
        if (floatHideTip != null) {
            this.tipx = floatHideTip.floatHideTipX;
            this.tipy = this.floatHideTip.floatHideTipY;
            this.tipw = this.floatHideTip.floatHideTipW;
            int i2 = this.floatHideTip.floatHideTipH;
            this.tiph = i2;
            if (this.tipw == 0 || i2 == 0) {
                floatHideTipSetxywh();
            }
        }
        return getxy()[0] <= this.tipx + this.tipw && getxy()[0] + width >= this.tipx && getxy()[1] <= this.tipy + this.tiph && getxy()[1] + height >= this.tipy;
    }

    public int[] getxy() {
        int[] iArr;
        ImageButton imageButton = this.mFloatView;
        if (imageButton != null && (iArr = this.location) != null) {
            imageButton.getLocationOnScreen(iArr);
        }
        return this.location;
    }
}
