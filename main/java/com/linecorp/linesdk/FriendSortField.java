package com.linecorp.linesdk;

/* loaded from: classes2.dex */
public enum FriendSortField {
    NAME("name"),
    RELATION("relation");

    private final String serverKey;

    FriendSortField(String str) {
        this.serverKey = str;
    }

    public String getServerKey() {
        return this.serverKey;
    }
}
