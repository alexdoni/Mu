package com.xsdk.ab_firstbase.statisics.util.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class MethodParameterDiscover {
    public static List<TypeDiscoverInfo> discoverParameter(Method method) {
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        ArrayList arrayList = new ArrayList();
        for (Type type : genericParameterTypes) {
            arrayList.add(ReflectionUtils.discoverType(type));
        }
        return arrayList;
    }

    public static TypeDiscoverInfo discoverReturnType(Method method) {
        return ReflectionUtils.discoverType(method.getGenericReturnType());
    }
}
