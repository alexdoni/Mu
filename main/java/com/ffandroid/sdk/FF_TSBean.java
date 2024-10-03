package com.ffandroid.sdk;

import android.text.TextUtils;

/* loaded from: classes.dex */
public class FF_TSBean {
    private boolean result = false;
    private String sourceText = "";
    private String translatedText = "";

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean z) {
        this.result = z;
    }

    public String getSourceText() {
        if (TextUtils.isEmpty(this.sourceText)) {
            this.sourceText = "";
        }
        return this.sourceText;
    }

    public void setSourceText(String str) {
        this.sourceText = str;
    }

    public String getTranslatedText() {
        if (TextUtils.isEmpty(this.translatedText)) {
            this.translatedText = "";
        }
        return this.translatedText;
    }

    public void setTranslatedText(String str) {
        this.translatedText = str;
    }
}
