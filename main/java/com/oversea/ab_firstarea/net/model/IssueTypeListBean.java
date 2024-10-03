package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.List;

/* loaded from: classes.dex */
public class IssueTypeListBean extends BaseBean<Data> {
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
        private List<Issue_type_list> issue_type_list;

        public void setIssue_type_list(List<Issue_type_list> list) {
            this.issue_type_list = list;
        }

        public List<Issue_type_list> getIssue_type_list() {
            return this.issue_type_list;
        }
    }

    /* loaded from: classes.dex */
    public static class Issue_type_list {
        private int issue_type_id;
        private String issue_type_name;

        public void setIssue_type_id(int i) {
            this.issue_type_id = i;
        }

        public int getIssue_type_id() {
            return this.issue_type_id;
        }

        public void setIssue_type_name(String str) {
            this.issue_type_name = str;
        }

        public String getIssue_type_name() {
            return this.issue_type_name;
        }
    }
}
