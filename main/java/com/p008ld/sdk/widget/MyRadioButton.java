package com.p008ld.sdk.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.p008ld.sdk.util.zzt;

/* loaded from: classes2.dex */
public class MyRadioButton extends FrameLayout {
    private View hot_view;
    private boolean isShowLine;
    private View select_bottom_view;
    private TextView title_name_tv;

    public MyRadioButton(Context context) {
        super(context);
        initView(context);
    }

    public MyRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public MyRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void initView(Context context) {
        View inflate = View.inflate(context, zzt.zzf(context, "ld_my_radio_button_layout"), this);
        this.title_name_tv = (TextView) zzt.zza(context, "title_name_tv", inflate);
        this.hot_view = zzt.zza(context, "hot_view", inflate);
        this.select_bottom_view = zzt.zza(context, "select_bottom_view", inflate);
    }

    public void isShowLine(boolean z) {
        this.isShowLine = z;
    }

    public void setText(String str) {
        TextView textView = this.title_name_tv;
        if (textView != null) {
            textView.setText(str);
            this.title_name_tv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ld.sdk.widget.MyRadioButton.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    MyRadioButton.this.title_name_tv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    public void isSelect(boolean z) {
        this.select_bottom_view.setVisibility(4);
        if (z) {
            this.title_name_tv.setTextColor(Color.parseColor("#000000"));
            if (this.isShowLine) {
                this.select_bottom_view.setVisibility(0);
                return;
            }
            return;
        }
        this.title_name_tv.setTextColor(Color.parseColor("#80000000"));
    }

    public void isShowHot(boolean z) {
        View view = this.hot_view;
        if (view != null) {
            view.setVisibility(z ? 0 : 4);
        }
    }

    public String getText() {
        TextView textView = this.title_name_tv;
        return textView != null ? textView.getText().toString() : "";
    }
}
