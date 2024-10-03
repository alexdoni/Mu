package com.appsflyer.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AFi1cSDK {
    public static final Map<String, Object> AFInAppEventType(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "");
        Iterator<String> keys = jSONObject.keys();
        Intrinsics.checkNotNullExpressionValue(keys, "");
        Sequence asSequence = SequencesKt.asSequence(keys);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : asSequence) {
            Object obj2 = jSONObject.get((String) obj);
            Intrinsics.checkNotNullExpressionValue(obj2, "");
            linkedHashMap.put(obj, AFKeystoreWrapper(obj2));
        }
        return linkedHashMap;
    }

    private static final List<Object> valueOf(JSONArray jSONArray) {
        IntRange until = RangesKt.until(0, jSONArray.length());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator<Integer> it = until.iterator();
        while (it.hasNext()) {
            Object obj = jSONArray.get(((IntIterator) it).nextInt());
            Intrinsics.checkNotNullExpressionValue(obj, "");
            arrayList.add(AFKeystoreWrapper(obj));
        }
        return arrayList;
    }

    private static final Object AFKeystoreWrapper(Object obj) {
        if (obj instanceof JSONArray) {
            return valueOf((JSONArray) obj);
        }
        if (obj instanceof JSONObject) {
            return AFInAppEventType((JSONObject) obj);
        }
        if (Intrinsics.areEqual(obj, JSONObject.NULL)) {
            return null;
        }
        return obj;
    }
}
