package com.linecorp.linesdk;

import java.util.List;

/* loaded from: classes2.dex */
public class GetFriendsResponse {
    private List<LineProfile> friends;
    private String nextPageRequestToken;

    public GetFriendsResponse(List<LineProfile> list) {
        this.friends = list;
    }

    public GetFriendsResponse(List<LineProfile> list, String str) {
        this.friends = list;
        this.nextPageRequestToken = str;
    }

    public List<LineProfile> getFriends() {
        return this.friends;
    }

    public String getNextPageRequestToken() {
        return this.nextPageRequestToken;
    }

    public String toString() {
        return "GetFriendsResponse{friends=" + this.friends + ", nextPageRequestToken='" + this.nextPageRequestToken + "'}";
    }
}
