package com.luck.picture.lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.luck.picture.lib.C2140R;

/* loaded from: classes2.dex */
public class MediumBoldTextView extends AppCompatTextView {
    private float mStrokeWidth;

    public MediumBoldTextView(Context context) {
        this(context, null);
    }

    public MediumBoldTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediumBoldTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mStrokeWidth = 0.6f;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C2140R.styleable.PictureMediumBoldTextView, i, 0);
        this.mStrokeWidth = obtainStyledAttributes.getFloat(C2140R.styleable.PictureMediumBoldTextView_stroke_Width, this.mStrokeWidth);
        obtainStyledAttributes.recycle();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        TextPaint paint = getPaint();
        float strokeWidth = paint.getStrokeWidth();
        float f = this.mStrokeWidth;
        if (strokeWidth != f) {
            paint.setStrokeWidth(f);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
        super.onDraw(canvas);
    }

    public void setStrokeWidth(float f) {
        this.mStrokeWidth = f;
        invalidate();
    }
}
