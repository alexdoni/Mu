package com.oversea.ab_firstplatform.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.RadioButton;
import androidx.core.internal.view.SupportMenu;

/* loaded from: classes2.dex */
public class CustomRadioButton extends RadioButton {

    /* renamed from: dm */
    DisplayMetrics f788dm;
    private boolean firstCreate;
    int height;
    private Paint paint;
    private int radius;
    private boolean showRedpoint;

    /* renamed from: t */
    Rect f789t;
    int textSize;
    float value;
    int width;

    public CustomRadioButton(Context context) {
        this(context, null);
    }

    public CustomRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.radius = 10;
        this.firstCreate = true;
        this.showRedpoint = false;
        this.f789t = new Rect();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f788dm = displayMetrics;
        this.value = displayMetrics.scaledDensity;
        this.textSize = 8;
        setupPaint();
    }

    private void setupPaint() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setColor(SupportMenu.CATEGORY_MASK);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setTextSize(this.textSize * this.value);
        int i = 0;
        if (this.firstCreate) {
            canvas.getClipBounds(this.f789t);
            this.firstCreate = false;
            this.height = getHeight();
            this.width = getWidth();
        }
        if (this.showRedpoint) {
            Drawable[] compoundDrawables = getCompoundDrawables();
            Rect rect = new Rect();
            while (true) {
                if (i >= compoundDrawables.length) {
                    break;
                }
                Drawable drawable = compoundDrawables[i];
                if (drawable != null) {
                    rect = drawable.getBounds();
                    break;
                }
                i++;
            }
            int width = (getWidth() - rect.right) / 2;
            int height = (getHeight() - rect.bottom) / 2;
            int i2 = this.radius;
            if (height < i2) {
                height = i2;
            }
            canvas.drawCircle(this.f789t.right - 20, height, 10.0f, this.paint);
        }
    }

    public void setShowRedPoint(boolean z) {
        this.showRedpoint = z;
        invalidate();
    }

    public boolean redPointIsShowing() {
        return this.showRedpoint;
    }
}
