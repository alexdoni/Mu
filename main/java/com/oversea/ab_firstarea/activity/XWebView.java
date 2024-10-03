package com.oversea.ab_firstarea.activity;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

/* loaded from: classes.dex */
public class XWebView extends WebView {
    public XWebView(Context context) {
        super(context);
    }

    public XWebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, Resources.getSystem().getIdentifier("webViewStyle", "attr", "android"));
    }

    public XWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Log.v("XWebView", "**XWebView**i" + i);
    }
}
