package com.oversea.ab_firstarea.floatView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.net.okhttp3.CallCode;
import com.xsdk.ab_firstbase.statisics.util.DensityUtil;

/* loaded from: classes.dex */
public class FloatViewOntouch implements View.OnTouchListener {
    private static FloatViewOntouch instance;

    /* renamed from: dx */
    private int f783dx;

    /* renamed from: dy */
    private int f784dy;
    private int lastX;
    private int lastY;
    private Context mContext;
    private FloatView mFloatLayout;
    private Handler mHander;
    public int mScreenX;
    public int mScreenY;
    private boolean mIsFloatSmall = false;
    private boolean mIsPopShow = false;
    private boolean mIsStartTimer = false;
    private boolean mIsSendMsg = true;
    private String TAG = "FloatViewOntouch";
    public boolean isCollision = false;
    private int[] location = new int[2];
    int tipx = 0;
    int tipy = 0;
    int tipw = 0;
    int tiph = 0;
    int dx2 = 0;

    public static FloatViewOntouch getInstance() {
        if (instance == null) {
            instance = new FloatViewOntouch();
        }
        return instance;
    }

    public void setTwFloatViewOntouch(Context context, Handler handler, FloatView floatView) {
        this.mHander = handler;
        this.mFloatLayout = floatView;
        this.mContext = context;
    }

    public void setIsFloatSmall(boolean z) {
        this.mIsFloatSmall = z;
    }

    public void setIsPopShow(boolean z) {
        this.mIsPopShow = z;
    }

    private Message floatViewToGo(View view, MotionEvent motionEvent, Message message) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        if (iArr[0] < (DensityUtil.getWidth(this.mContext) >> 1)) {
            this.mScreenX = 0;
            this.mFloatLayout.update(0, FloatView.getInstance().changeFloatVH(this.mScreenY), -1, -1);
            message.what = ComConstants.HANDLER_POSITION_LEFT;
        } else {
            this.mFloatLayout.getContentView().measure(0, 0);
            this.mScreenX = DensityUtil.getWidth(this.mContext);
            this.mFloatLayout.update(DensityUtil.getWidth(this.mContext) + CallCode.HTTP_MULT_CHOICE, FloatView.getInstance().changeFloatVH(this.mScreenY), -1, -1);
            message.what = ComConstants.HANDLER_POSITION_RIGHT;
        }
        return message;
    }

    public void isStartTimer(boolean z) {
        this.mIsStartTimer = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000d, code lost:
    
        if (r6 != 2) goto L46;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oversea.ab_firstarea.floatView.FloatViewOntouch.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public boolean isCollisionFloatHideTip(int i) {
        if (FloatView.getInstance().isOpenFloatHideTip && Math.abs(i) > 50) {
            int width = this.mFloatLayout.getContentView().getWidth();
            int height = this.mFloatLayout.getContentView().getHeight();
            if (FloatView.getInstance().floatHideTip != null) {
                this.tipx = FloatView.getInstance().floatHideTip.floatHideTipX;
                this.tipy = FloatView.getInstance().floatHideTip.floatHideTipY;
                this.tipw = FloatView.getInstance().floatHideTip.floatHideTipW;
                int i2 = FloatView.getInstance().floatHideTip.floatHideTipH;
                this.tiph = i2;
                if (this.tipw == 0 || i2 == 0) {
                    FloatView.getInstance().floatHideTipSetxywh();
                }
            }
            if (FloatView.getInstance().getxy()[0] <= this.tipx + this.tipw && FloatView.getInstance().getxy()[0] + width >= this.tipx && FloatView.getInstance().getxy()[1] <= this.tipy + this.tiph && FloatView.getInstance().getxy()[1] + height >= this.tipy) {
                return true;
            }
        }
        return false;
    }

    public void onDestory() {
        this.lastX = 0;
        this.lastY = 0;
        this.f783dx = 0;
        this.f784dy = 0;
        this.mScreenX = 0;
        this.mScreenY = 0;
    }
}
