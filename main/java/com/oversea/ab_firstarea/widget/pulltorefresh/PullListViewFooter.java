package com.oversea.ab_firstarea.widget.pulltorefresh;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/* loaded from: classes.dex */
public class PullListViewFooter extends LinearLayout {
    public static final int STATE_EMPTY = 4;
    public static final int STATE_LOADING = 2;
    public static final int STATE_NO = 3;
    public static final int STATE_READY = 1;
    private int footerHeight;
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private LinearLayout footerView;
    private Context mContext;

    public PullListViewFooter(Context context) {
        super(context);
        initView(context);
    }

    public PullListViewFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
        setState(1);
    }

    private void initView(Context context) {
        this.mContext = context;
        LinearLayout linearLayout = new LinearLayout(context);
        this.footerView = linearLayout;
        linearLayout.setOrientation(0);
        this.footerView.setGravity(17);
        TextView textView = new TextView(context);
        this.footerTextView = textView;
        textView.setGravity(16);
        setTextColor(Color.rgb(107, 107, 107));
        this.footerTextView.setTextSize(15.0f);
        this.footerTextView.setMinimumHeight(50);
        this.footerView.setPadding(0, 10, 0, 10);
        ProgressBar progressBar = new ProgressBar(context, null, R.attr.progressBarStyle);
        this.footerProgressBar = progressBar;
        progressBar.setVisibility(8);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        layoutParams.width = 50;
        layoutParams.height = 50;
        layoutParams.rightMargin = 10;
        this.footerView.addView(this.footerProgressBar, layoutParams);
        this.footerView.addView(this.footerTextView, new LinearLayout.LayoutParams(-2, -2));
        addView(this.footerView, new LinearLayout.LayoutParams(-1, -2));
        PullViewUtil.measureView(this);
        this.footerHeight = getMeasuredHeight();
    }

    public void setState(int i) {
        if (i == 1) {
            this.footerView.setVisibility(0);
            this.footerTextView.setVisibility(0);
            this.footerProgressBar.setVisibility(8);
            this.footerTextView.setText("加載更多");
            return;
        }
        if (i == 2) {
            this.footerView.setVisibility(0);
            this.footerTextView.setVisibility(0);
            this.footerProgressBar.setVisibility(0);
            this.footerTextView.setText("加載更多...");
            return;
        }
        if (i == 3) {
            this.footerView.setVisibility(8);
            this.footerTextView.setVisibility(0);
            this.footerProgressBar.setVisibility(8);
            this.footerTextView.setText("Is full load");
            return;
        }
        if (i == 4) {
            this.footerView.setVisibility(8);
            this.footerTextView.setVisibility(8);
            this.footerProgressBar.setVisibility(8);
            this.footerTextView.setText("沒有數據了！");
        }
    }

    public int getVisiableHeight() {
        return ((LinearLayout.LayoutParams) this.footerView.getLayoutParams()).height;
    }

    public void hide() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.footerView.getLayoutParams();
        layoutParams.height = 0;
        this.footerView.setLayoutParams(layoutParams);
        this.footerView.setVisibility(8);
    }

    public void show() {
        this.footerView.setVisibility(0);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.footerView.getLayoutParams();
        layoutParams.height = -2;
        this.footerView.setLayoutParams(layoutParams);
    }

    public void setTextColor(int i) {
        this.footerTextView.setTextColor(i);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.footerView.setBackgroundColor(i);
    }

    public ProgressBar getFooterProgressBar() {
        return this.footerProgressBar;
    }

    public void setFooterProgressBarDrawable(Drawable drawable) {
        this.footerProgressBar.setIndeterminateDrawable(drawable);
    }

    public int getFooterHeight() {
        return this.footerHeight;
    }

    public void setVisiableHeight(int i) {
        if (i < 0) {
            i = 0;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.footerView.getLayoutParams();
        layoutParams.height = i;
        this.footerView.setLayoutParams(layoutParams);
    }
}
