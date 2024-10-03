package com.p008ld.sdk.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes2.dex */
public class RoundCornerImageView extends ImageView {
    private final PaintFlagsDrawFilter aliasFilter;
    private int leftBottomRadius;
    private int leftTopRadius;
    private final Path path;
    private final RectF rectF;
    private int rightBottomRadius;
    private int rightTopRadius;

    public RoundCornerImageView(Context context) {
        super(context);
        this.path = new Path();
        this.rectF = new RectF();
        this.aliasFilter = new PaintFlagsDrawFilter(0, 3);
        this.leftTopRadius = 10;
        this.rightTopRadius = 10;
        this.rightBottomRadius = 10;
        this.leftBottomRadius = 10;
        init();
    }

    public RoundCornerImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.path = new Path();
        this.rectF = new RectF();
        this.aliasFilter = new PaintFlagsDrawFilter(0, 3);
        this.leftTopRadius = 10;
        this.rightTopRadius = 10;
        this.rightBottomRadius = 10;
        this.leftBottomRadius = 10;
        init();
    }

    public RoundCornerImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.path = new Path();
        this.rectF = new RectF();
        this.aliasFilter = new PaintFlagsDrawFilter(0, 3);
        this.leftTopRadius = 10;
        this.rightTopRadius = 10;
        this.rightBottomRadius = 10;
        this.leftBottomRadius = 10;
        init();
    }

    private void init() {
        setLayerType(2, null);
    }

    public void setCornerRadius(int i) {
        this.leftTopRadius = i;
        this.leftBottomRadius = i;
        this.rightTopRadius = i;
        this.rightBottomRadius = i;
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.path.reset();
        canvas.setDrawFilter(this.aliasFilter);
        this.rectF.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        int i = this.leftTopRadius;
        int i2 = this.rightTopRadius;
        int i3 = this.rightBottomRadius;
        int i4 = this.leftBottomRadius;
        this.path.addRoundRect(this.rectF, new float[]{i, i, i2, i2, i3, i3, i4, i4}, Path.Direction.CW);
        canvas.clipPath(this.path);
        super.onDraw(canvas);
    }
}
