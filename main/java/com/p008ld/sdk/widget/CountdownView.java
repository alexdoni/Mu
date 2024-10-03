package com.p008ld.sdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.p008ld.sdk.util.zzt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CountdownView.kt */
/* loaded from: classes2.dex */
public final class CountdownView extends TextView implements Runnable {
    public static final Companion Companion = new Companion(null);
    private static final int TIME_DEFAULT = 60;
    private static final String TIME_UNIT = "s";
    private int mCurSecond;
    private Function0<Unit> mFinishListener;
    private int mTotalSecond;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CountdownView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CountdownView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ CountdownView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CountdownView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mTotalSecond = 60;
        setText(zzt.zza(context, "ld_get_text"));
    }

    /* compiled from: CountdownView.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void start() {
        setClickable(false);
        start(60, new Function0<Unit>() { // from class: com.ld.sdk.widget.CountdownView$start$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
    }

    public static /* synthetic */ void start$default(CountdownView countdownView, Integer num, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            num = 60;
        }
        countdownView.start(num, function0);
    }

    public final void start(Integer num, Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        int intValue = num != null ? num.intValue() : 60;
        this.mTotalSecond = intValue;
        this.mCurSecond = intValue;
        this.mFinishListener = listener;
        post(this);
    }

    public final void stop() {
        setClickable(true);
        this.mCurSecond = 0;
        setText(zzt.zza(getContext(), "ld_get_text"));
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = this.mCurSecond;
        if (i == 0) {
            stop();
            Function0<Unit> function0 = this.mFinishListener;
            if (function0 != null) {
                function0.invoke();
                return;
            }
            return;
        }
        this.mCurSecond = i - 1;
        StringBuilder sb = new StringBuilder();
        sb.append(this.mCurSecond);
        sb.append('s');
        setText(sb.toString());
        postDelayed(this, 1000L);
    }
}
