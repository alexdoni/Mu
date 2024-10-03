package com.p008ld.sdk.util;

import java.util.Iterator;
import java.util.Map;

/* compiled from: MapUtils.java */
/* loaded from: classes2.dex */
public class zzq {
    public static void zza(Map map) {
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            zza(map.get(it.next()), it);
        }
    }

    private static void zza(Object obj, Iterator it) {
        if (obj instanceof String) {
            if (((String) obj).equals("")) {
                it.remove();
            }
        } else if (obj == null) {
            it.remove();
        }
    }
}
