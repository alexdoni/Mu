package com.oversea.ab_firstarea.widget.pulltorefresh;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xsdk.ab_firstbase.statisics.util.DateUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes.dex */
public class PullListViewHeader extends LinearLayout {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_READY = 1;
    public static final int STATE_REFRESHING = 2;
    private final int ROTATE_ANIM_DURATION;
    private Bitmap arrowImage;
    private ImageView arrowImageView;
    private int headerHeight;
    private ProgressBar headerProgressBar;
    private TextView headerTimeView;
    private LinearLayout headerView;
    private String lastRefreshTime;
    private Context mContext;
    private Animation mRotateDownAnim;
    private Animation mRotateUpAnim;
    private int mState;
    private TextView tipsTextview;

    public PullListViewHeader(Context context) {
        super(context);
        this.arrowImage = null;
        this.mState = -1;
        this.ROTATE_ANIM_DURATION = CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
        this.lastRefreshTime = null;
        initView(context);
    }

    public PullListViewHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.arrowImage = null;
        this.mState = -1;
        this.ROTATE_ANIM_DURATION = CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
        this.lastRefreshTime = null;
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LinearLayout linearLayout = new LinearLayout(context);
        this.headerView = linearLayout;
        linearLayout.setOrientation(0);
        this.headerView.setGravity(17);
        this.headerView.setPadding(0, 5, 0, 5);
        FrameLayout frameLayout = new FrameLayout(context);
        ImageView imageView = new ImageView(context);
        this.arrowImageView = imageView;
        imageView.setImageResource(Util.getIdByName(this.mContext, "drawable", "tw_dia_chat_fragment"));
        ProgressBar progressBar = new ProgressBar(context, null, R.attr.progressBarStyle);
        this.headerProgressBar = progressBar;
        progressBar.setVisibility(8);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        layoutParams.width = 50;
        layoutParams.height = 50;
        frameLayout.addView(this.arrowImageView, layoutParams);
        frameLayout.addView(this.headerProgressBar, layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(context);
        this.tipsTextview = new TextView(context);
        this.headerTimeView = new TextView(context);
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(12, 0, 0, 0);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        linearLayout2.addView(this.tipsTextview, layoutParams2);
        linearLayout2.addView(this.headerTimeView, layoutParams2);
        this.tipsTextview.setTextColor(Color.rgb(107, 107, 107));
        this.headerTimeView.setTextColor(Color.rgb(107, 107, 107));
        this.tipsTextview.setTextSize(15.0f);
        this.headerTimeView.setTextSize(14.0f);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 17;
        layoutParams3.bottomMargin = 5;
        layoutParams3.topMargin = 5;
        LinearLayout linearLayout3 = new LinearLayout(context);
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(17);
        linearLayout3.addView(frameLayout, layoutParams3);
        linearLayout3.addView(linearLayout2, layoutParams3);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams4.gravity = 80;
        this.headerView.addView(linearLayout3, layoutParams4);
        addView(this.headerView, layoutParams4);
        PullViewUtil.measureView(this);
        int measuredHeight = getMeasuredHeight();
        this.headerHeight = measuredHeight;
        this.headerView.setPadding(0, measuredHeight * (-1), 0, 0);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.mRotateUpAnim = rotateAnimation;
        rotateAnimation.setDuration(180L);
        this.mRotateUpAnim.setFillAfter(true);
        RotateAnimation rotateAnimation2 = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mRotateDownAnim = rotateAnimation2;
        rotateAnimation2.setDuration(180L);
        this.mRotateDownAnim.setFillAfter(true);
        setState(0);
    }

    public void setState(int i) {
        if (i == this.mState) {
            return;
        }
        if (i == 2) {
            this.arrowImageView.clearAnimation();
            this.arrowImageView.setVisibility(4);
            this.headerProgressBar.setVisibility(0);
        } else {
            this.arrowImageView.setVisibility(0);
            this.headerProgressBar.setVisibility(4);
        }
        if (i == 0) {
            if (this.mState == 1) {
                this.arrowImageView.startAnimation(this.mRotateDownAnim);
            }
            if (this.mState == 2) {
                this.arrowImageView.clearAnimation();
            }
            this.tipsTextview.setText("下拉刷新");
            if (this.lastRefreshTime == null) {
                this.lastRefreshTime = DateUtils.getStrTime_hms(DateUtils.getTime());
                this.headerTimeView.setText("刷新時間：" + this.lastRefreshTime);
            } else {
                this.headerTimeView.setText("上次刷新時間：" + this.lastRefreshTime);
            }
        } else if (i != 1) {
            if (i == 2) {
                this.tipsTextview.setText("正在刷新...");
                this.headerTimeView.setText("刷新時間：" + this.lastRefreshTime);
            }
        } else if (this.mState != 1) {
            this.arrowImageView.clearAnimation();
            this.arrowImageView.startAnimation(this.mRotateUpAnim);
            this.tipsTextview.setText("鬆手刷新");
            this.headerTimeView.setText("上次刷新時間：" + this.lastRefreshTime);
            this.lastRefreshTime = DateUtils.getStrTime_hms(DateUtils.getTime());
        }
        this.mState = i;
    }

    public void setVisiableHeight(int i) {
        if (i < 0) {
            i = 0;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.headerView.getLayoutParams();
        layoutParams.height = i;
        this.headerView.setLayoutParams(layoutParams);
    }

    public int getVisiableHeight() {
        return ((LinearLayout.LayoutParams) this.headerView.getLayoutParams()).height;
    }

    public LinearLayout getHeaderView() {
        return this.headerView;
    }

    public void setRefreshTime(String str) {
        this.headerTimeView.setText(str);
    }

    public int getHeaderHeight() {
        return this.headerHeight;
    }

    public void setTextColor(int i) {
        this.tipsTextview.setTextColor(i);
        this.headerTimeView.setTextColor(i);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.headerView.setBackgroundColor(i);
    }

    public ProgressBar getHeaderProgressBar() {
        return this.headerProgressBar;
    }

    public void setHeaderProgressBarDrawable(Drawable drawable) {
        this.headerProgressBar.setIndeterminateDrawable(drawable);
    }
}
