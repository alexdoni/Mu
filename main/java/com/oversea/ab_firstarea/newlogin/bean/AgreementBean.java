package com.oversea.ab_firstarea.newlogin.bean;

import java.util.List;

/* loaded from: classes.dex */
public class AgreementBean {
    private String agreement;
    private List<Children> children;
    private boolean isChecked;
    private boolean isNecessary;
    private String url;

    public List<Children> getChildren() {
        return this.children;
    }

    public void setChildren(List<Children> list) {
        this.children = list;
    }

    public boolean isNecessary() {
        return this.isNecessary;
    }

    public void setNecessary(boolean z) {
        this.isNecessary = z;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public AgreementBean() {
    }

    public AgreementBean(String str, String str2, boolean z, boolean z2) {
        this.agreement = str;
        this.url = str2;
        this.isChecked = z;
        this.isNecessary = z2;
    }

    public AgreementBean(String str, String str2, boolean z, boolean z2, List<Children> list) {
        this.agreement = str;
        this.url = str2;
        this.isChecked = z;
        this.isNecessary = z2;
        this.children = list;
    }

    public String getAgreement() {
        return this.agreement;
    }

    public void setAgreement(String str) {
        this.agreement = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    /* loaded from: classes.dex */
    public static class Children {
        private String agreement;
        private boolean isChecked;
        private boolean isNecessary;
        private String url;

        public Children(String str, String str2, boolean z, boolean z2) {
            this.agreement = str;
            this.url = str2;
            this.isChecked = z;
            this.isNecessary = z2;
        }

        public String getAgreement() {
            return this.agreement;
        }

        public void setAgreement(String str) {
            this.agreement = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public boolean isChecked() {
            return this.isChecked;
        }

        public void setChecked(boolean z) {
            this.isChecked = z;
        }

        public boolean isNecessary() {
            return this.isNecessary;
        }

        public void setNecessary(boolean z) {
            this.isNecessary = z;
        }
    }
}
