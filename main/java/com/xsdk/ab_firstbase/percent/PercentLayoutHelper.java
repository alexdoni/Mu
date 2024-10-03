package com.xsdk.ab_firstbase.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.xsdk.ab_firstbase.statisics.util.Util;

@Deprecated
/* loaded from: classes3.dex */
public class PercentLayoutHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = "PercentLayout";
    private static final boolean VERBOSE = false;
    private final ViewGroup mHost;

    @Deprecated
    /* loaded from: classes3.dex */
    public interface PercentLayoutParams {
        PercentLayoutInfo getPercentLayoutInfo();
    }

    public PercentLayoutHelper(ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("host must be non-null");
        }
        this.mHost = viewGroup;
    }

    public static void fetchWidthAndHeight(ViewGroup.LayoutParams layoutParams, TypedArray typedArray, int i, int i2) {
        layoutParams.width = typedArray.getLayoutDimension(i, 0);
        layoutParams.height = typedArray.getLayoutDimension(i2, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void adjustChildren(int i, int i2) {
        PercentLayoutInfo percentLayoutInfo;
        int size = (View.MeasureSpec.getSize(i) - this.mHost.getPaddingLeft()) - this.mHost.getPaddingRight();
        int size2 = (View.MeasureSpec.getSize(i2) - this.mHost.getPaddingTop()) - this.mHost.getPaddingBottom();
        int childCount = this.mHost.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = this.mHost.getChildAt(i3);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof PercentLayoutParams) && (percentLayoutInfo = ((PercentLayoutParams) layoutParams).getPercentLayoutInfo()) != null) {
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    percentLayoutInfo.fillMarginLayoutParams(childAt, (ViewGroup.MarginLayoutParams) layoutParams, size, size2);
                } else {
                    percentLayoutInfo.fillLayoutParams(layoutParams, size, size2);
                }
            }
        }
    }

    private static int getIndex(int[] iArr, int i) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i == iArr[i2]) {
                return i2;
            }
        }
        return 0;
    }

    public static PercentLayoutInfo getPercentLayoutInfo(Context context, AttributeSet attributeSet) {
        PercentLayoutInfo percentLayoutInfo;
        int[] iArr = {Util.getIdByName(context, "attr", "layout_aspectRatio"), Util.getIdByName(context, "attr", "layout_heightPercent"), Util.getIdByName(context, "attr", "layout_marginBottomPercent"), Util.getIdByName(context, "attr", "layout_marginEndPercent"), Util.getIdByName(context, "attr", "layout_marginLeftPercent"), Util.getIdByName(context, "attr", "layout_marginPercent"), Util.getIdByName(context, "attr", "layout_marginRightPercent"), Util.getIdByName(context, "attr", "layout_marginStartPercent"), Util.getIdByName(context, "attr", "layout_marginTopPercent"), Util.getIdByName(context, "attr", "layout_widthPercent")};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        float fraction = obtainStyledAttributes.getFraction(getIndex(iArr, Util.getIdByName(context, "attr", "layout_widthPercent")), 1, 1, -1.0f);
        if (fraction != -1.0f) {
            percentLayoutInfo = new PercentLayoutInfo();
            percentLayoutInfo.widthPercent = fraction;
        } else {
            percentLayoutInfo = null;
        }
        float fraction2 = obtainStyledAttributes.getFraction(getIndex(iArr, Util.getIdByName(context, "attr", "layout_heightPercent")), 1, 1, -1.0f);
        if (fraction2 != -1.0f) {
            if (percentLayoutInfo == null) {
                percentLayoutInfo = new PercentLayoutInfo();
            }
            percentLayoutInfo.heightPercent = fraction2;
        }
        float fraction3 = obtainStyledAttributes.getFraction(getIndex(iArr, Util.getIdByName(context, "attr", "layout_marginPercent")), 1, 1, -1.0f);
        if (fraction3 != -1.0f) {
            if (percentLayoutInfo == null) {
                percentLayoutInfo = new PercentLayoutInfo();
            }
            percentLayoutInfo.leftMarginPercent = fraction3;
            percentLayoutInfo.topMarginPercent = fraction3;
            percentLayoutInfo.rightMarginPercent = fraction3;
            percentLayoutInfo.bottomMarginPercent = fraction3;
        }
        float fraction4 = obtainStyledAttributes.getFraction(getIndex(iArr, Util.getIdByName(context, "attr", "layout_marginLeftPercent")), 1, 1, -1.0f);
        if (fraction4 != -1.0f) {
            if (percentLayoutInfo == null) {
                percentLayoutInfo = new PercentLayoutInfo();
            }
            percentLayoutInfo.leftMarginPercent = fraction4;
        }
        float fraction5 = obtainStyledAttributes.getFraction(getIndex(iArr, Util.getIdByName(context, "attr", "layout_marginTopPercent")), 1, 1, -1.0f);
        if (fraction5 != -1.0f) {
            if (percentLayoutInfo == null) {
                percentLayoutInfo = new PercentLayoutInfo();
            }
            percentLayoutInfo.topMarginPercent = fraction5;
        }
        float fraction6 = obtainStyledAttributes.getFraction(getIndex(iArr, Util.getIdByName(context, "attr", "layout_marginRightPercent")), 1, 1, -1.0f);
        if (fraction6 != -1.0f) {
            if (percentLayoutInfo == null) {
                percentLayoutInfo = new PercentLayoutInfo();
            }
            percentLayoutInfo.rightMarginPercent = fraction6;
        }
        float fraction7 = obtainStyledAttributes.getFraction(getIndex(iArr, Util.getIdByName(context, "attr", "layout_marginBottomPercent")), 1, 1, -1.0f);
        if (fraction7 != -1.0f) {
            if (percentLayoutInfo == null) {
                percentLayoutInfo = new PercentLayoutInfo();
            }
            percentLayoutInfo.bottomMarginPercent = fraction7;
        }
        float fraction8 = obtainStyledAttributes.getFraction(getIndex(iArr, Util.getIdByName(context, "attr", "layout_marginStartPercent")), 1, 1, -1.0f);
        if (fraction8 != -1.0f) {
            if (percentLayoutInfo == null) {
                percentLayoutInfo = new PercentLayoutInfo();
            }
            percentLayoutInfo.startMarginPercent = fraction8;
        }
        float fraction9 = obtainStyledAttributes.getFraction(getIndex(iArr, Util.getIdByName(context, "attr", "layout_marginEndPercent")), 1, 1, -1.0f);
        if (fraction9 != -1.0f) {
            if (percentLayoutInfo == null) {
                percentLayoutInfo = new PercentLayoutInfo();
            }
            percentLayoutInfo.endMarginPercent = fraction9;
        }
        float fraction10 = obtainStyledAttributes.getFraction(getIndex(iArr, Util.getIdByName(context, "attr", "layout_aspectRatio")), 1, 1, -1.0f);
        if (fraction10 != -1.0f) {
            if (percentLayoutInfo == null) {
                percentLayoutInfo = new PercentLayoutInfo();
            }
            percentLayoutInfo.aspectRatio = fraction10;
        }
        obtainStyledAttributes.recycle();
        return percentLayoutInfo;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void restoreOriginalParams() {
        PercentLayoutInfo percentLayoutInfo;
        int childCount = this.mHost.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewGroup.LayoutParams layoutParams = this.mHost.getChildAt(i).getLayoutParams();
            if ((layoutParams instanceof PercentLayoutParams) && (percentLayoutInfo = ((PercentLayoutParams) layoutParams).getPercentLayoutInfo()) != null) {
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    percentLayoutInfo.restoreMarginLayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
                } else {
                    percentLayoutInfo.restoreLayoutParams(layoutParams);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean handleMeasuredStateTooSmall() {
        PercentLayoutInfo percentLayoutInfo;
        int childCount = this.mHost.getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mHost.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof PercentLayoutParams) && (percentLayoutInfo = ((PercentLayoutParams) layoutParams).getPercentLayoutInfo()) != null) {
                if (shouldHandleMeasuredWidthTooSmall(childAt, percentLayoutInfo)) {
                    layoutParams.width = -2;
                    z = true;
                }
                if (shouldHandleMeasuredHeightTooSmall(childAt, percentLayoutInfo)) {
                    layoutParams.height = -2;
                    z = true;
                }
            }
        }
        return z;
    }

    private static boolean shouldHandleMeasuredWidthTooSmall(View view, PercentLayoutInfo percentLayoutInfo) {
        return (view.getMeasuredWidthAndState() & ViewCompat.MEASURED_STATE_MASK) == 16777216 && percentLayoutInfo.widthPercent >= 0.0f && percentLayoutInfo.mPreservedParams.width == -2;
    }

    private static boolean shouldHandleMeasuredHeightTooSmall(View view, PercentLayoutInfo percentLayoutInfo) {
        return (view.getMeasuredHeightAndState() & ViewCompat.MEASURED_STATE_MASK) == 16777216 && percentLayoutInfo.heightPercent >= 0.0f && percentLayoutInfo.mPreservedParams.height == -2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class PercentMarginLayoutParams extends ViewGroup.MarginLayoutParams {
        private boolean mIsHeightComputedFromAspectRatio;
        private boolean mIsWidthComputedFromAspectRatio;

        public PercentMarginLayoutParams(int i, int i2) {
            super(i, i2);
        }
    }

    @Deprecated
    /* loaded from: classes3.dex */
    public static class PercentLayoutInfo {
        public float aspectRatio;
        public float widthPercent = -1.0f;
        public float heightPercent = -1.0f;
        public float leftMarginPercent = -1.0f;
        public float topMarginPercent = -1.0f;
        public float rightMarginPercent = -1.0f;
        public float bottomMarginPercent = -1.0f;
        public float startMarginPercent = -1.0f;
        public float endMarginPercent = -1.0f;
        final PercentMarginLayoutParams mPreservedParams = new PercentMarginLayoutParams(0, 0);

        public void fillLayoutParams(ViewGroup.LayoutParams layoutParams, int i, int i2) {
            this.mPreservedParams.width = layoutParams.width;
            this.mPreservedParams.height = layoutParams.height;
            boolean z = false;
            boolean z2 = (this.mPreservedParams.mIsWidthComputedFromAspectRatio || this.mPreservedParams.width == 0) && this.widthPercent < 0.0f;
            if ((this.mPreservedParams.mIsHeightComputedFromAspectRatio || this.mPreservedParams.height == 0) && this.heightPercent < 0.0f) {
                z = true;
            }
            float f = this.widthPercent;
            if (f >= 0.0f) {
                layoutParams.width = Math.round(i * f);
            }
            float f2 = this.heightPercent;
            if (f2 >= 0.0f) {
                layoutParams.height = Math.round(i2 * f2);
            }
            if (this.aspectRatio >= 0.0f) {
                if (z2) {
                    layoutParams.width = Math.round(layoutParams.height * this.aspectRatio);
                    this.mPreservedParams.mIsWidthComputedFromAspectRatio = true;
                }
                if (z) {
                    layoutParams.height = Math.round(layoutParams.width / this.aspectRatio);
                    this.mPreservedParams.mIsHeightComputedFromAspectRatio = true;
                }
            }
        }

        @Deprecated
        public void fillMarginLayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2) {
            fillMarginLayoutParams(null, marginLayoutParams, i, i2);
        }

        public void fillMarginLayoutParams(View view, ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2) {
            boolean z;
            fillLayoutParams(marginLayoutParams, i, i2);
            this.mPreservedParams.leftMargin = marginLayoutParams.leftMargin;
            this.mPreservedParams.topMargin = marginLayoutParams.topMargin;
            this.mPreservedParams.rightMargin = marginLayoutParams.rightMargin;
            this.mPreservedParams.bottomMargin = marginLayoutParams.bottomMargin;
            MarginLayoutParamsCompat.setMarginStart(this.mPreservedParams, MarginLayoutParamsCompat.getMarginStart(marginLayoutParams));
            MarginLayoutParamsCompat.setMarginEnd(this.mPreservedParams, MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams));
            float f = this.leftMarginPercent;
            if (f >= 0.0f) {
                marginLayoutParams.leftMargin = Math.round(i * f);
            }
            float f2 = this.topMarginPercent;
            if (f2 >= 0.0f) {
                marginLayoutParams.topMargin = Math.round(i2 * f2);
            }
            float f3 = this.rightMarginPercent;
            if (f3 >= 0.0f) {
                marginLayoutParams.rightMargin = Math.round(i * f3);
            }
            float f4 = this.bottomMarginPercent;
            if (f4 >= 0.0f) {
                marginLayoutParams.bottomMargin = Math.round(i2 * f4);
            }
            float f5 = this.startMarginPercent;
            boolean z2 = true;
            if (f5 >= 0.0f) {
                MarginLayoutParamsCompat.setMarginStart(marginLayoutParams, Math.round(i * f5));
                z = true;
            } else {
                z = false;
            }
            float f6 = this.endMarginPercent;
            if (f6 >= 0.0f) {
                MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, Math.round(i * f6));
            } else {
                z2 = z;
            }
            if (!z2 || view == null) {
                return;
            }
            MarginLayoutParamsCompat.resolveLayoutDirection(marginLayoutParams, ViewCompat.getLayoutDirection(view));
        }

        public String toString() {
            return String.format("PercentLayoutInformation width: %f height %f, margins (%f, %f,  %f, %f, %f, %f)", Float.valueOf(this.widthPercent), Float.valueOf(this.heightPercent), Float.valueOf(this.leftMarginPercent), Float.valueOf(this.topMarginPercent), Float.valueOf(this.rightMarginPercent), Float.valueOf(this.bottomMarginPercent), Float.valueOf(this.startMarginPercent), Float.valueOf(this.endMarginPercent));
        }

        public void restoreMarginLayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            restoreLayoutParams(marginLayoutParams);
            marginLayoutParams.leftMargin = this.mPreservedParams.leftMargin;
            marginLayoutParams.topMargin = this.mPreservedParams.topMargin;
            marginLayoutParams.rightMargin = this.mPreservedParams.rightMargin;
            marginLayoutParams.bottomMargin = this.mPreservedParams.bottomMargin;
            MarginLayoutParamsCompat.setMarginStart(marginLayoutParams, MarginLayoutParamsCompat.getMarginStart(this.mPreservedParams));
            MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, MarginLayoutParamsCompat.getMarginEnd(this.mPreservedParams));
        }

        public void restoreLayoutParams(ViewGroup.LayoutParams layoutParams) {
            if (!this.mPreservedParams.mIsWidthComputedFromAspectRatio) {
                layoutParams.width = this.mPreservedParams.width;
            }
            if (!this.mPreservedParams.mIsHeightComputedFromAspectRatio) {
                layoutParams.height = this.mPreservedParams.height;
            }
            this.mPreservedParams.mIsWidthComputedFromAspectRatio = false;
            this.mPreservedParams.mIsHeightComputedFromAspectRatio = false;
        }
    }
}
