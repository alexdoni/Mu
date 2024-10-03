package com.p008ld.sdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/* loaded from: classes2.dex */
public class MyRadioGroup extends LinearLayout implements View.OnClickListener {
    private boolean isShowLine;
    private Context mContext;
    private String[] mTitles;
    private RBOnClickListener rbOnClickListener;

    public MyRadioGroup(Context context) {
        super(context);
        initView(context);
    }

    public MyRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        setOrientation(0);
        setGravity(17);
    }

    public void setTitleData(String[] strArr, RBOnClickListener rBOnClickListener) {
        this.mTitles = strArr;
        this.rbOnClickListener = rBOnClickListener;
        initData();
    }

    public void showLine() {
        this.isShowLine = true;
    }

    private void initData() {
        for (int i = 0; i < this.mTitles.length; i++) {
            MyRadioButton myRadioButton = new MyRadioButton(this.mContext);
            myRadioButton.isShowLine(this.isShowLine);
            if (i == 0) {
                myRadioButton.isSelect(true);
            }
            myRadioButton.setOnClickListener(this);
            myRadioButton.setText(this.mTitles[i]);
            myRadioButton.setTag("" + i);
            addView(myRadioButton);
        }
    }

    public void updateTitles(String[] strArr) {
        MyRadioButton myRadioButton;
        for (int i = 0; i < getChildCount() && (myRadioButton = (MyRadioButton) getChildAt(i)) != null; i++) {
            myRadioButton.setText(strArr[i]);
        }
    }

    public void setSelect(int i) {
        if (getChildCount() >= i) {
            setSelected(getChildAt(i));
        }
    }

    public void setHotStatus(int i, boolean z) {
        if (getChildCount() >= i) {
            setHotStatus(getChildAt(i), z);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!(view instanceof MyRadioButton) || this.rbOnClickListener == null) {
            return;
        }
        setSelected(view);
    }

    private void setHotStatus(View view, boolean z) {
        for (int i = 0; i < getChildCount(); i++) {
            MyRadioButton myRadioButton = (MyRadioButton) getChildAt(i);
            if (view == null || myRadioButton == null) {
                return;
            }
            if (myRadioButton == view) {
                myRadioButton.isShowHot(z);
            }
        }
    }

    private void setSelected(View view) {
        for (int i = 0; i < getChildCount(); i++) {
            MyRadioButton myRadioButton = (MyRadioButton) getChildAt(i);
            if (view == null || myRadioButton == null) {
                return;
            }
            if (myRadioButton == view) {
                myRadioButton.isSelect(true);
                this.rbOnClickListener.onClick(myRadioButton.getTag().toString());
            } else {
                myRadioButton.isSelect(false);
            }
        }
    }
}
