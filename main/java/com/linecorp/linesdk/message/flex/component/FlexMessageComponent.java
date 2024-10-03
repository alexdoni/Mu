package com.linecorp.linesdk.message.flex.component;

import com.facebook.share.internal.ShareConstants;
import com.linecorp.linesdk.message.Jsonable;
import com.linecorp.linesdk.message.Stringable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class FlexMessageComponent implements Jsonable {
    protected static final int FLEX_VALUE_NONE = -1;
    protected final Type type;

    /* loaded from: classes2.dex */
    public enum Alignment implements Stringable {
        START,
        END,
        CENTER
    }

    /* loaded from: classes2.dex */
    public enum AspectMode implements Stringable {
        COVER,
        FIT
    }

    /* loaded from: classes2.dex */
    public enum Gravity implements Stringable {
        TOP,
        BOTTOM,
        CENTER
    }

    /* loaded from: classes2.dex */
    public enum Height implements Stringable {
        SM,
        MD
    }

    /* loaded from: classes2.dex */
    public enum Layout implements Stringable {
        HORIZONTAL,
        VERTICAL,
        BASELINE
    }

    /* loaded from: classes2.dex */
    public enum Margin implements Stringable {
        NONE,
        XS,
        SM,
        MD,
        LG,
        XL,
        XXL
    }

    /* loaded from: classes2.dex */
    public enum Style implements Stringable {
        LINK,
        PRIMARY,
        SECONDARY
    }

    /* loaded from: classes2.dex */
    public enum Type implements Stringable {
        BOX,
        BUTTON,
        FILLER,
        ICON,
        IMAGE,
        SEPARATOR,
        SPACER,
        TEXT
    }

    /* loaded from: classes2.dex */
    public enum Weight implements Stringable {
        BOLD,
        REGULAR
    }

    /* loaded from: classes2.dex */
    public enum Size implements Stringable {
        XXS("xxs"),
        XS("xs"),
        SM("sm"),
        MD("md"),
        LG("lg"),
        XL("xl"),
        XXL("xxl"),
        XL3("3xl"),
        XL4("4xl"),
        XL5("5xl"),
        FULL("full");

        private String value;

        Size(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum AspectRatio {
        RATIO_1x1("1:1"),
        RATIO_1_51x1("1.51:1"),
        RATIO_1_91x1("1.91:1"),
        RATIO_4x3("4:3"),
        RATIO_16x9("16:9"),
        RATIO_20x13("20:13"),
        RATIO_2x1("2:1"),
        RATIO_3x1("3:1"),
        RATIO_3x4("3:4"),
        RATIO_9x16("9:16"),
        RATIO_1x2("1:2"),
        RATIO_1x3("1:3");

        private String value;

        AspectRatio(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    public FlexMessageComponent(Type type) {
        this.type = type;
    }

    @Override // com.linecorp.linesdk.message.Jsonable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ShareConstants.MEDIA_TYPE, this.type.name().toLowerCase());
        return jSONObject;
    }
}
