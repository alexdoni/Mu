package com.oversea.ab_firstplatform.model;

import android.text.TextUtils;

/* loaded from: classes2.dex */
public class TSBean {
    private String extInfo;
    private boolean result = false;
    private String sourceText = "";
    private String translatedText = "";

    public String getExtInfo() {
        return this.extInfo;
    }

    public void setExtInfo(String str) {
        this.extInfo = str;
    }

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
