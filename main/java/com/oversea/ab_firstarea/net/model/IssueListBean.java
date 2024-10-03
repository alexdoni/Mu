package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.List;

/* loaded from: classes.dex */
public class IssueListBean extends BaseBean<Data> {
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
        private List<Issue_list> issue_list;

        public void setIssue_list(List<Issue_list> list) {
            this.issue_list = list;
        }

        public List<Issue_list> getIssue_list() {
            return this.issue_list;
        }
    }

    /* loaded from: classes.dex */
    public static class Issue_list {
        private int issue_id;
        private int issue_status;
        private long issue_time;
        private String issue_title;
        private int issue_type_id;

        public void setIssue_id(int i) {
            this.issue_id = i;
        }

        public int getIssue_id() {
            return this.issue_id;
        }

        public void setIssue_type_id(int i) {
            this.issue_type_id = i;
        }

        public int getIssue_type_id() {
            return this.issue_type_id;
        }

        public void setIssue_title(String str) {
            this.issue_title = str;
        }

        public String getIssue_title() {
            return this.issue_title;
        }

        public void setIssue_time(long j) {
            this.issue_time = j;
        }

        public long getIssue_time() {
            return this.issue_time;
        }

        public void setIssue_status(int i) {
            this.issue_status = i;
        }

        public int getIssue_status() {
            return this.issue_status;
        }
    }
}
