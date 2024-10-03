package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class TranslateBean extends BaseBean<Data> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.oversea.ab_firstplatform.model.BaseBean
    public Data getData() {
        if (super.getData() == null) {
            setData(new Data());
        }
        return (Data) super.getData();
    }

    /* loaded from: classes.dex */
    public static class Data {
        private String source_lang;
        private String translated_text;

        public void setTranslated_text(String str) {
            this.translated_text = str;
        }

        public String getTranslated_text() {
            return this.translated_text;
        }

        public void setSource_lang(String str) {
            this.source_lang = str;
        }

        public String getSource_lang() {
            return this.source_lang;
        }
    }
}
