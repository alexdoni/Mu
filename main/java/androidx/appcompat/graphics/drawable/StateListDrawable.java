package androidx.appcompat.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.appcompat.graphics.drawable.DrawableContainer;
import androidx.appcompat.resources.C0084R;
import androidx.core.content.res.TypedArrayUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class StateListDrawable extends DrawableContainer {
    private static final boolean DEBUG = false;
    private static final String TAG = "StateListDrawable";
    private boolean mMutated;
    private StateListState mStateListState;

    @Override // androidx.appcompat.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    StateListDrawable() {
        this(null, null);
    }

    public void addState(int[] iArr, Drawable drawable) {
        if (drawable != null) {
            this.mStateListState.addStateSet(iArr, drawable);
            onStateChange(getState());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        int indexOfStateSet = this.mStateListState.indexOfStateSet(iArr);
        if (indexOfStateSet < 0) {
            indexOfStateSet = this.mStateListState.indexOfStateSet(StateSet.WILD_CARD);
        }
        return selectDrawable(indexOfStateSet) || onStateChange;
    }

    public void inflate(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, C0084R.styleable.StateListDrawable);
        setVisible(obtainAttributes.getBoolean(C0084R.styleable.StateListDrawable_android_visible, true), true);
        updateStateFromTypedArray(obtainAttributes);
        updateDensity(resources);
        obtainAttributes.recycle();
        inflateChildElements(context, resources, xmlPullParser, attributeSet, theme);
        onStateChange(getState());
    }

    private void updateStateFromTypedArray(TypedArray typedArray) {
        StateListState stateListState = this.mStateListState;
        stateListState.mChangingConfigurations |= typedArray.getChangingConfigurations();
        stateListState.mVariablePadding = typedArray.getBoolean(C0084R.styleable.StateListDrawable_android_variablePadding, stateListState.mVariablePadding);
        stateListState.mConstantSize = typedArray.getBoolean(C0084R.styleable.StateListDrawable_android_constantSize, stateListState.mConstantSize);
        stateListState.mEnterFadeDuration = typedArray.getInt(C0084R.styleable.StateListDrawable_android_enterFadeDuration, stateListState.mEnterFadeDuration);
        stateListState.mExitFadeDuration = typedArray.getInt(C0084R.styleable.StateListDrawable_android_exitFadeDuration, stateListState.mExitFadeDuration);
        stateListState.mDither = typedArray.getBoolean(C0084R.styleable.StateListDrawable_android_dither, stateListState.mDither);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004a, code lost:
    
        if (r4 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004c, code lost:
    
        r4 = r10.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0051, code lost:
    
        if (r4 != 4) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0054, code lost:
    
        if (r4 != 2) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0056, code lost:
    
        r4 = android.graphics.drawable.Drawable.createFromXmlInner(r9, r10, r11, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0075, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException(r10.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0076, code lost:
    
        r0.addStateSet(r3, r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void inflateChildElements(android.content.Context r8, android.content.res.Resources r9, org.xmlpull.v1.XmlPullParser r10, android.util.AttributeSet r11, android.content.res.Resources.Theme r12) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r7 = this;
            androidx.appcompat.graphics.drawable.StateListDrawable$StateListState r0 = r7.mStateListState
            int r1 = r10.getDepth()
            r2 = 1
            int r1 = r1 + r2
        L8:
            int r3 = r10.next()
            if (r3 == r2) goto L7a
            int r4 = r10.getDepth()
            if (r4 >= r1) goto L17
            r5 = 3
            if (r3 == r5) goto L7a
        L17:
            r5 = 2
            if (r3 == r5) goto L1b
            goto L8
        L1b:
            if (r4 > r1) goto L8
            java.lang.String r3 = r10.getName()
            java.lang.String r4 = "item"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L2a
            goto L8
        L2a:
            int[] r3 = androidx.appcompat.resources.C0084R.styleable.StateListDrawableItem
            android.content.res.TypedArray r3 = androidx.core.content.res.TypedArrayUtils.obtainAttributes(r9, r12, r11, r3)
            int r4 = androidx.appcompat.resources.C0084R.styleable.StateListDrawableItem_android_drawable
            r6 = -1
            int r4 = r3.getResourceId(r4, r6)
            if (r4 <= 0) goto L42
            androidx.appcompat.widget.ResourceManagerInternal r6 = androidx.appcompat.widget.ResourceManagerInternal.get()
            android.graphics.drawable.Drawable r4 = r6.getDrawable(r8, r4)
            goto L43
        L42:
            r4 = 0
        L43:
            r3.recycle()
            int[] r3 = r7.extractStateSet(r11)
            if (r4 != 0) goto L76
        L4c:
            int r4 = r10.next()
            r6 = 4
            if (r4 != r6) goto L54
            goto L4c
        L54:
            if (r4 != r5) goto L5b
            android.graphics.drawable.Drawable r4 = android.graphics.drawable.Drawable.createFromXmlInner(r9, r10, r11, r12)
            goto L76
        L5b:
            org.xmlpull.v1.XmlPullParserException r8 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = r10.getPositionDescription()
            r9.append(r10)
            java.lang.String r10 = ": <item> tag requires a 'drawable' attribute or child tag defining a drawable"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L76:
            r0.addStateSet(r3, r4)
            goto L8
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.graphics.drawable.StateListDrawable.inflateChildElements(android.content.Context, android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] extractStateSet(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        int[] iArr = new int[attributeCount];
        int i = 0;
        for (int i2 = 0; i2 < attributeCount; i2++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i2);
            if (attributeNameResource != 0 && attributeNameResource != 16842960 && attributeNameResource != 16843161) {
                int i3 = i + 1;
                if (!attributeSet.getAttributeBooleanValue(i2, false)) {
                    attributeNameResource = -attributeNameResource;
                }
                iArr[i] = attributeNameResource;
                i = i3;
            }
        }
        return StateSet.trimStateSet(iArr, i);
    }

    StateListState getStateListState() {
        return this.mStateListState;
    }

    int getStateCount() {
        return this.mStateListState.getChildCount();
    }

    int[] getStateSet(int i) {
        return this.mStateListState.mStateSets[i];
    }

    Drawable getStateDrawable(int i) {
        return this.mStateListState.getChild(i);
    }

    int getStateDrawableIndex(int[] iArr) {
        return this.mStateListState.indexOfStateSet(iArr);
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mStateListState.mutate();
            this.mMutated = true;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.graphics.drawable.DrawableContainer
    public StateListState cloneConstantState() {
        return new StateListState(this.mStateListState, this, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.graphics.drawable.DrawableContainer
    public void clearMutated() {
        super.clearMutated();
        this.mMutated = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class StateListState extends DrawableContainer.DrawableContainerState {
        int[][] mStateSets;

        /* JADX INFO: Access modifiers changed from: package-private */
        public StateListState(StateListState stateListState, StateListDrawable stateListDrawable, Resources resources) {
            super(stateListState, stateListDrawable, resources);
            if (stateListState != null) {
                this.mStateSets = stateListState.mStateSets;
            } else {
                this.mStateSets = new int[getCapacity()];
            }
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableContainer.DrawableContainerState
        void mutate() {
            int[][] iArr = this.mStateSets;
            int[][] iArr2 = new int[iArr.length];
            for (int length = iArr.length - 1; length >= 0; length--) {
                int[] iArr3 = this.mStateSets[length];
                iArr2[length] = iArr3 != null ? (int[]) iArr3.clone() : null;
            }
            this.mStateSets = iArr2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int addStateSet(int[] iArr, Drawable drawable) {
            int addChild = addChild(drawable);
            this.mStateSets[addChild] = iArr;
            return addChild;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int indexOfStateSet(int[] iArr) {
            int[][] iArr2 = this.mStateSets;
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (StateSet.stateSetMatches(iArr2[i], iArr)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new StateListDrawable(this, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new StateListDrawable(this, resources);
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableContainer.DrawableContainerState
        public void growArray(int i, int i2) {
            super.growArray(i, i2);
            int[][] iArr = new int[i2];
            System.arraycopy(this.mStateSets, 0, iArr, 0, i);
            this.mStateSets = iArr;
        }
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.graphics.drawable.DrawableContainer
    public void setConstantState(DrawableContainer.DrawableContainerState drawableContainerState) {
        super.setConstantState(drawableContainerState);
        if (drawableContainerState instanceof StateListState) {
            this.mStateListState = (StateListState) drawableContainerState;
        }
    }

    StateListDrawable(StateListState stateListState, Resources resources) {
        setConstantState(new StateListState(stateListState, this, resources));
        onStateChange(getState());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StateListDrawable(StateListState stateListState) {
        if (stateListState != null) {
            setConstantState(stateListState);
        }
    }
}
