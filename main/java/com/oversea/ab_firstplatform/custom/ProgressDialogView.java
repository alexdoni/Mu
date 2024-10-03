package com.oversea.ab_firstplatform.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;

/* loaded from: classes2.dex */
public class ProgressDialogView extends LinearLayout {
    public ProgressDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ProgressDialogView(Context context, int i, int i2, String str) {
        super(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(ImageUtils.dip2px(context, 220.0f), ImageUtils.dip2px(context, 80.0f));
        layoutParams3.addRule(3, 100000001);
        layoutParams3.addRule(14, -1);
        LinearLayout linearLayout = new LinearLayout(context);
        if (i > 0) {
            linearLayout.setBackgroundResource(i);
        }
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(ImageUtils.dip2px(context, 45.0f), ImageUtils.dip2px(context, 45.0f));
        layoutParams4.gravity = 17;
        layoutParams4.leftMargin = ImageUtils.dip2px(context, 10.0f);
        ImageView imageView = new ImageView(context);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1200L);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatCount(-1);
        if (i2 > 0) {
            imageView.setImageResource(i2);
        }
        imageView.startAnimation(rotateAnimation);
        linearLayout.addView(imageView, layoutParams4);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.gravity = 17;
        layoutParams5.leftMargin = ImageUtils.dip2px(context, 20.0f);
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setTextSize(18.0f);
        textView.setTextColor(-13552591);
        linearLayout.addView(textView, layoutParams5);
        relativeLayout.addView(linearLayout, layoutParams3);
        addView(relativeLayout, layoutParams);
    }
}
