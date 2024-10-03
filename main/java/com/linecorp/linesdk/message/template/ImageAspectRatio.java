package com.linecorp.linesdk.message.template;

/* loaded from: classes2.dex */
public enum ImageAspectRatio {
    RECTANGLE("rectangle"),
    SQUARE("square");

    private String serverKey;

    ImageAspectRatio(String str) {
        this.serverKey = str;
    }

    public String getServerKey() {
        return this.serverKey;
    }
}
