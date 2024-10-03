package com.linecorp.linesdk.dialog.internal;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

/* loaded from: classes2.dex */
public class CircleImageView extends AppCompatImageView {
    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setImageDrawable(null);
            return;
        }
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(getContext().getResources(), ((BitmapDrawable) drawable).getBitmap());
        create.setCircular(true);
        create.setCornerRadius(Math.max(r3.getWidth(), r3.getHeight()) / 2.0f);
        super.setImageDrawable(create);
    }
}
