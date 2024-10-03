package com.linecorp.linesdk.utils;

import android.net.Uri;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class UriUtils {
    private UriUtils() {
    }

    public static Uri.Builder uriBuilder(String str, String... strArr) {
        return uriBuilder(Uri.parse(str), strArr);
    }

    public static Uri.Builder uriBuilder(Uri uri, String... strArr) {
        Uri.Builder buildUpon = uri.buildUpon();
        for (String str : strArr) {
            buildUpon.appendEncodedPath(str);
        }
        return buildUpon;
    }

    public static Uri.Builder appendQueryParams(Uri.Builder builder, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return builder;
    }

    public static Uri buildUri(String str, String... strArr) {
        return uriBuilder(str, strArr).build();
    }

    public static Uri buildUri(Uri uri, String... strArr) {
        return uriBuilder(uri, strArr).build();
    }

    public static Uri appendQueryParams(String str, Map<String, String> map) {
        return appendQueryParams(Uri.parse(str), map);
    }

    public static Uri appendQueryParams(Uri uri, Map<String, String> map) {
        return appendQueryParams(uriBuilder(uri, new String[0]), map).build();
    }

    public static Map<String, String> buildParams(String... strArr) {
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Odd number of key and Value");
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < strArr.length; i += 2) {
            linkedHashMap.put(strArr[i], strArr[i + 1]);
        }
        return linkedHashMap;
    }
}
