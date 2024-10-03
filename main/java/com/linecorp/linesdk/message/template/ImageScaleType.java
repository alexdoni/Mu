package com.linecorp.linesdk.message.template;

/* loaded from: classes2.dex */
enum ImageScaleType {
    COVER("cover"),
    CONTAIN("contain");

    private String serverKey;

    ImageScaleType(String str) {
        this.serverKey = str;
    }

    public String getServerKey() {
        return this.serverKey;
    }
}
