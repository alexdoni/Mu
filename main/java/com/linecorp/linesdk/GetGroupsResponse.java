package com.linecorp.linesdk;

import java.util.List;

/* loaded from: classes2.dex */
public class GetGroupsResponse {
    private List<LineGroup> groups;
    private String nextPageRequestToken;

    public GetGroupsResponse(List<LineGroup> list) {
        this.groups = list;
    }

    public GetGroupsResponse(List<LineGroup> list, String str) {
        this.groups = list;
        this.nextPageRequestToken = str;
    }

    public List<LineGroup> getGroups() {
        return this.groups;
    }

    public String getNextPageRequestToken() {
        return this.nextPageRequestToken;
    }

    public String toString() {
        return "GetFriendsResponse{groups=" + this.groups + ", nextPageRequestToken='" + this.nextPageRequestToken + "'}";
    }
}
