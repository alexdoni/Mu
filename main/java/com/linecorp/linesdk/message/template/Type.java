package com.linecorp.linesdk.message.template;

/* loaded from: classes2.dex */
public enum Type {
    BUTTONS("buttons"),
    CONFIRM("confirm"),
    CAROUSEL("carousel"),
    IMAGE_CAROUSEL("image_carousel");

    private final String serverKey;

    Type(String str) {
        this.serverKey = str;
    }

    public String getServerKey() {
        return this.serverKey;
    }
}
