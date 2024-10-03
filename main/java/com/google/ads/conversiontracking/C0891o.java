package com.google.ads.conversiontracking;

import android.content.Intent;
import android.net.Uri;

/* renamed from: com.google.ads.conversiontracking.o */
/* loaded from: classes.dex */
public class C0891o {

    /* renamed from: a */
    private static final Uri f499a;

    /* renamed from: b */
    private static final Uri f500b;

    static {
        Uri parse = Uri.parse("http://plus.google.com/");
        f499a = parse;
        f500b = parse.buildUpon().appendPath("circles").appendPath("find").build();
    }

    /* renamed from: a */
    public static Intent m239a() {
        return new Intent("android.settings.DATE_SETTINGS");
    }

    /* renamed from: a */
    public static Intent m240a(String str) {
        Uri fromParts = Uri.fromParts("package", str, null);
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }

    /* renamed from: b */
    public static Intent m241b(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(m243d(str));
        intent.setPackage("com.android.vending");
        intent.addFlags(524288);
        return intent;
    }

    /* renamed from: c */
    public static Intent m242c(String str) {
        Uri parse = Uri.parse("bazaar://search?q=pname:" + str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(parse);
        intent.setFlags(524288);
        return intent;
    }

    /* renamed from: d */
    private static Uri m243d(String str) {
        return Uri.parse("market://details").buildUpon().appendQueryParameter("id", str).build();
    }
}
