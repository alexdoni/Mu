package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class KFBaseInfoBean extends BaseBean<Data> {
    public static KFBaseInfoBean initInfo;

    public static KFBaseInfoBean getInstance() {
        if (initInfo == null) {
            initInfo = new KFBaseInfoBean();
        }
        return initInfo;
    }

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
        private String email;
        private String faq_url;
        private boolean has_not_read_issue;
        private String work_time_text;

        public String getWork_time_text() {
            return this.work_time_text;
        }

        public void setWork_time_text(String str) {
            this.work_time_text = str;
        }

        public String getFaq_url() {
            return this.faq_url;
        }

        public void setFaq_url(String str) {
            this.faq_url = str;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String str) {
            this.email = str;
        }

        public boolean getHas_not_read_issue() {
            return this.has_not_read_issue;
        }

        public void setHas_not_read_issue(boolean z) {
            this.has_not_read_issue = z;
        }
    }
}
