package com.oversea.ab_firstarea.floatView;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class FloatHideTip {
    private Activity content;
    public int floatType;
    private LinearLayout floathidbackground;
    private RelativeLayout.LayoutParams mParams;
    private WindowManager wManager;
    private String TAG = "FloatHideTip";
    public View floatMenuV = null;
    private int[] location = new int[2];
    public int floatHideTipX = 0;
    public int floatHideTipY = 0;
    public int floatHideTipW = 0;
    public int floatHideTipH = 0;

    public FloatHideTip(Activity activity, int i) {
        this.content = activity;
        this.floatType = i;
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(this.content).inflate(Util.getIdByName(this.content, "layout", "floathidetip"), (ViewGroup) null);
        this.floatMenuV = inflate;
        this.floathidbackground = (LinearLayout) inflate.findViewById(Util.getIdByName(this.content, "id", "floathidbackground"));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 81;
        ((ViewGroup) this.content.getWindow().getDecorView()).addView(this.floatMenuV, layoutParams);
        showFloatmenu(false);
    }

    public void setxywh() {
        this.floatMenuV.getLocationOnScreen(this.location);
        int[] iArr = this.location;
        this.floatHideTipX = iArr[0];
        this.floatHideTipY = iArr[1];
        this.floatHideTipW = this.floatMenuV.getWidth();
        this.floatHideTipH = this.floatMenuV.getHeight();
        Log.i(this.TAG, "1initFloatMenu floatMenuV locationx" + this.location[0] + "  y=" + this.location[1] + " w=" + this.floatMenuV.getWidth() + "h=" + this.floatMenuV.getHeight());
    }

    public void showFloatmenu(boolean z) {
        View view = this.floatMenuV;
        if (view != null) {
            if (z) {
                view.setVisibility(0);
            } else {
                view.setVisibility(8);
            }
        }
    }

    public void removeFloatMenuView() {
        View view = this.floatMenuV;
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.floatMenuV);
            }
            this.floatMenuV = null;
        }
    }

    public void setfloatMenuVBackGround(final boolean z) {
        if (this.floatMenuV == null || this.floathidbackground == null) {
            return;
        }
        this.content.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.floatView.FloatHideTip.1
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    FloatHideTip.this.floathidbackground.setBackgroundColor(FloatHideTip.this.content.getResources().getColor(Util.getIdByName(FloatHideTip.this.content, TypedValues.Custom.S_COLOR, "tw_payway_background_color")));
                } else {
                    FloatHideTip.this.floathidbackground.setBackgroundColor(FloatHideTip.this.content.getResources().getColor(Util.getIdByName(FloatHideTip.this.content, TypedValues.Custom.S_COLOR, "tw_light_green")));
                }
            }
        });
    }
}
