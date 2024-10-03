package com.appsflyer.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* loaded from: classes.dex */
public final class AFc1tSDK {
    public static final int valueOf(String str) {
        String value;
        Integer intOrNull;
        String value2;
        Integer intOrNull2;
        String value3;
        Integer intOrNull3;
        Intrinsics.checkNotNullParameter(str, "");
        MatchResult matchEntire = new Regex("(\\d+).(\\d+).(\\d+).*").matchEntire(str);
        if (matchEntire == null) {
            return -1;
        }
        MatchGroup matchGroup = matchEntire.getGroups().get(1);
        int i = 0;
        int intValue = ((matchGroup == null || (value3 = matchGroup.getValue()) == null || (intOrNull3 = StringsKt.toIntOrNull(value3)) == null) ? 0 : intOrNull3.intValue()) * 1000000;
        MatchGroup matchGroup2 = matchEntire.getGroups().get(2);
        int intValue2 = intValue + (((matchGroup2 == null || (value2 = matchGroup2.getValue()) == null || (intOrNull2 = StringsKt.toIntOrNull(value2)) == null) ? 0 : intOrNull2.intValue()) * 1000);
        MatchGroup matchGroup3 = matchEntire.getGroups().get(3);
        if (matchGroup3 != null && (value = matchGroup3.getValue()) != null && (intOrNull = StringsKt.toIntOrNull(value)) != null) {
            i = intOrNull.intValue();
        }
        return intValue2 + i;
    }
}
