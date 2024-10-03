package com.luck.picture.lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.luck.picture.lib.C2140R;

/* loaded from: classes2.dex */
public class RoundCornerRelativeLayout extends RelativeLayout {
    private final float cornerSize;
    private final boolean isBottomNormal;
    private final boolean isTopNormal;
    private final RectF mRect;
    private final Path path;

    public RoundCornerRelativeLayout(Context context) {
        this(context, null);
    }

    public RoundCornerRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundCornerRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRect = new RectF();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C2140R.styleable.PictureRoundCornerRelativeLayout, i, 0);
        this.cornerSize = obtainStyledAttributes.getDimension(C2140R.styleable.PictureRoundCornerRelativeLayout_psCorners, 0.0f);
        this.isTopNormal = obtainStyledAttributes.getBoolean(C2140R.styleable.PictureRoundCornerRelativeLayout_psTopNormal, false);
        this.isBottomNormal = obtainStyledAttributes.getBoolean(C2140R.styleable.PictureRoundCornerRelativeLayout_psBottomNormal, false);
        obtainStyledAttributes.recycle();
        this.path = new Path();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.path.reset();
        this.mRect.right = i;
        this.mRect.bottom = i2;
        boolean z = this.isTopNormal;
        if (!z && !this.isBottomNormal) {
            Path path = this.path;
            RectF rectF = this.mRect;
            float f = this.cornerSize;
            path.addRoundRect(rectF, f, f, Path.Direction.CW);
            return;
        }
        if (z) {
            float f2 = this.cornerSize;
            this.path.addRoundRect(this.mRect, new float[]{0.0f, 0.0f, 0.0f, 0.0f, f2, f2, f2, f2}, Path.Direction.CW);
        }
        if (this.isBottomNormal) {
            float f3 = this.cornerSize;
            this.path.addRoundRect(this.mRect, new float[]{f3, f3, f3, f3, 0.0f, 0.0f, 0.0f, 0.0f}, Path.Direction.CW);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.clipPath(this.path);
        super.dispatchDraw(canvas);
        canvas.restore();
    }
}
