package com.appsflyer.internal;

import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* loaded from: classes.dex */
public final class AFe1zSDK {
    public static final String AFKeystoreWrapper(String str, String str2) {
        MessageDigest messageDigest = MessageDigest.getInstance(str2);
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "");
        byte[] digest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(digest, "");
        String str3 = "";
        for (byte b : digest) {
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "");
            sb.append(format);
            str3 = sb.toString();
        }
        return str3;
    }

    public static final Pair<Integer, Integer> AFInAppEventParameterName(String str) {
        String value;
        String value2;
        String value3;
        Intrinsics.checkNotNullParameter(str, "");
        MatchResult matchEntire = new Regex("^(\\d+).(\\+)$|^(\\d+).(\\d+).(\\+)$").matchEntire(str);
        if (matchEntire != null) {
            MatchGroup matchGroup = matchEntire.getGroups().get(1);
            Integer intOrNull = (matchGroup == null || (value3 = matchGroup.getValue()) == null) ? null : StringsKt.toIntOrNull(value3);
            MatchGroup matchGroup2 = matchEntire.getGroups().get(3);
            Integer intOrNull2 = (matchGroup2 == null || (value2 = matchGroup2.getValue()) == null) ? null : StringsKt.toIntOrNull(value2);
            MatchGroup matchGroup3 = matchEntire.getGroups().get(4);
            Integer intOrNull3 = (matchGroup3 == null || (value = matchGroup3.getValue()) == null) ? null : StringsKt.toIntOrNull(value);
            if (intOrNull != null) {
                return TuplesKt.m1402to(Integer.valueOf(intOrNull.intValue() * 1000000), Integer.valueOf(((intOrNull.intValue() + 1) * 1000000) - 1));
            }
            if (intOrNull2 != null && intOrNull3 != null) {
                return TuplesKt.m1402to(Integer.valueOf((intOrNull2.intValue() * 1000000) + (intOrNull3.intValue() * 1000)), Integer.valueOf(((intOrNull2.intValue() * 1000000) + ((intOrNull3.intValue() + 1) * 1000)) - 1));
            }
        }
        return null;
    }

    public static final Pair<Integer, Integer> AFInAppEventType(String str) {
        String value;
        String value2;
        String value3;
        String value4;
        String value5;
        String value6;
        Intrinsics.checkNotNullParameter(str, "");
        MatchResult matchEntire = new Regex("(\\d+).(\\d+).(\\d+)-(\\d+).(\\d+).(\\d+)").matchEntire(str);
        if (matchEntire != null) {
            MatchGroup matchGroup = matchEntire.getGroups().get(1);
            Integer intOrNull = (matchGroup == null || (value6 = matchGroup.getValue()) == null) ? null : StringsKt.toIntOrNull(value6);
            MatchGroup matchGroup2 = matchEntire.getGroups().get(2);
            Integer intOrNull2 = (matchGroup2 == null || (value5 = matchGroup2.getValue()) == null) ? null : StringsKt.toIntOrNull(value5);
            MatchGroup matchGroup3 = matchEntire.getGroups().get(3);
            Integer intOrNull3 = (matchGroup3 == null || (value4 = matchGroup3.getValue()) == null) ? null : StringsKt.toIntOrNull(value4);
            MatchGroup matchGroup4 = matchEntire.getGroups().get(4);
            Integer intOrNull4 = (matchGroup4 == null || (value3 = matchGroup4.getValue()) == null) ? null : StringsKt.toIntOrNull(value3);
            MatchGroup matchGroup5 = matchEntire.getGroups().get(5);
            Integer intOrNull5 = (matchGroup5 == null || (value2 = matchGroup5.getValue()) == null) ? null : StringsKt.toIntOrNull(value2);
            MatchGroup matchGroup6 = matchEntire.getGroups().get(6);
            Integer intOrNull6 = (matchGroup6 == null || (value = matchGroup6.getValue()) == null) ? null : StringsKt.toIntOrNull(value);
            if (AFKeystoreWrapper(intOrNull, intOrNull2, intOrNull3, intOrNull4, intOrNull5, intOrNull6)) {
                Intrinsics.checkNotNull(intOrNull);
                int intValue = intOrNull.intValue() * 1000000;
                Intrinsics.checkNotNull(intOrNull2);
                int intValue2 = intValue + (intOrNull2.intValue() * 1000);
                Intrinsics.checkNotNull(intOrNull3);
                Integer valueOf = Integer.valueOf(intValue2 + intOrNull3.intValue());
                Intrinsics.checkNotNull(intOrNull4);
                int intValue3 = intOrNull4.intValue() * 1000000;
                Intrinsics.checkNotNull(intOrNull5);
                int intValue4 = intValue3 + (intOrNull5.intValue() * 1000);
                Intrinsics.checkNotNull(intOrNull6);
                return TuplesKt.m1402to(valueOf, Integer.valueOf(intValue4 + intOrNull6.intValue()));
            }
        }
        return null;
    }

    private static boolean AFKeystoreWrapper(Object... objArr) {
        Intrinsics.checkNotNullParameter(objArr, "");
        return !ArraysKt.contains(objArr, (Object) null);
    }
}
