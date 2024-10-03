package com.xsdk.ab_firstbase.statisics.util.json;

import com.xsdk.ab_firstbase.statisics.util.reflection.MethodParameterDiscover;
import com.xsdk.ab_firstbase.statisics.util.reflection.ReflectionUtils;
import com.xsdk.ab_firstbase.statisics.util.reflection.TypeDiscoverInfo;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class EJSTypeConverter<E> {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v26, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r8v20, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v22, types: [float[]] */
    public E createObject(Map<Object, Object> map, Class<?> cls) throws Exception {
        Object[] objArr;
        Object[] objArr2;
        List<E> createList;
        ?? newInstance;
        if (map == null) {
            return null;
        }
        E e = (E) cls.newInstance();
        Method[] methods = cls.getMethods();
        HashMap hashMap = new HashMap();
        for (Method method : methods) {
            if (method.getName().startsWith("set") && method.getParameterTypes() != null && method.getParameterTypes().length == 1) {
                hashMap.put(method.getName(), method);
            }
        }
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            String obj = entry.getKey().toString();
            String str = "set" + obj.substring(0, 1).toUpperCase() + obj.substring(1, obj.length());
            if (hashMap.containsKey(str)) {
                Method method2 = (Method) hashMap.get(str);
                List<TypeDiscoverInfo> discoverParameter = MethodParameterDiscover.discoverParameter(method2);
                if (discoverParameter.size() == 1) {
                    TypeDiscoverInfo typeDiscoverInfo = discoverParameter.get(0);
                    Class<?> cls2 = typeDiscoverInfo.parameterType;
                    if (ReflectionUtils.isSimpleType(cls2)) {
                        objArr = new Object[]{ReflectionUtils.convertToSimpleType(entry.getValue(), cls2)};
                    } else if (cls2.isArray()) {
                        Class<?> componentType = cls2.getComponentType();
                        if (ReflectionUtils.isSimpleType(componentType)) {
                            if (ReflectionUtils.isImplementOfInterface(entry.getValue().getClass(), Map.class)) {
                                Map map2 = (Map) entry.getValue();
                                newInstance = new float[2];
                                for (int i = 0; i < 2; i++) {
                                    if (map2.containsKey(String.valueOf(i))) {
                                        newInstance[i] = ((Double) map2.get(String.valueOf(i))).floatValue();
                                    } else {
                                        newInstance[i] = 0;
                                    }
                                }
                            } else {
                                List list = (List) entry.getValue();
                                newInstance = Array.newInstance(componentType, list.size());
                                for (int i2 = 0; i2 < list.size(); i2++) {
                                    Array.set(newInstance, i2, ReflectionUtils.convertToSimpleType(list.get(i2), componentType));
                                }
                            }
                            objArr = new Object[]{newInstance};
                        } else {
                            objArr2 = new Object[]{createList((List) entry.getValue(), componentType).toArray()};
                            objArr = objArr2;
                        }
                    } else if (ReflectionUtils.isImplementOfInterface(cls2, List.class)) {
                        Class<?> cls3 = typeDiscoverInfo.typeArgumentList.get(0);
                        if (ReflectionUtils.isSimpleType(cls3)) {
                            if (ReflectionUtils.isSimpleType(entry.getValue().getClass())) {
                                createList = createSimpleList(entry.getValue(), cls3);
                            } else {
                                createList = createSimpleList((List<?>) entry.getValue(), cls3);
                            }
                        } else {
                            createList = createList((List) entry.getValue(), cls3);
                        }
                        objArr = new Object[]{createList};
                    } else if (ReflectionUtils.isImplementOfInterface(cls2, Map.class)) {
                        if (ReflectionUtils.isImplementOfInterface(entry.getValue().getClass(), List.class)) {
                            objArr = new Object[]{null};
                        } else {
                            objArr2 = new Object[]{createStrongTypeMap((Map) entry.getValue(), typeDiscoverInfo)};
                            objArr = objArr2;
                        }
                    } else {
                        objArr = (entry.getValue() == null || !entry.getValue().toString().equals("")) ? new Object[]{createObject((Map) entry.getValue(), cls2)} : new Object[]{null};
                    }
                    method2.invoke(e, objArr);
                }
            }
        }
        return e;
    }

    public Map<?, ?> createStrongTypeMap(Map<Object, Object> map, TypeDiscoverInfo typeDiscoverInfo) throws Exception {
        HashMap hashMap = new HashMap();
        TypeDiscoverInfo typeDiscoverInfo2 = new TypeDiscoverInfo();
        typeDiscoverInfo2.parameterType = typeDiscoverInfo.typeArgumentList.get(1);
        typeDiscoverInfo2.isGeneric = true;
        if (typeDiscoverInfo.extra != null) {
            typeDiscoverInfo2.typeArgumentList = typeDiscoverInfo.extra.typeArgumentList;
            typeDiscoverInfo2.extra = typeDiscoverInfo.extra.extra;
        }
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Class<?> cls = typeDiscoverInfo.typeArgumentList.get(0);
            hashMap.put(ReflectionUtils.isSimpleType(cls) ? ReflectionUtils.convertToSimpleType(entry.getKey(), cls) : null, ReflectionUtils.buildStrongTypeObject(typeDiscoverInfo2, entry.getValue()));
        }
        return hashMap;
    }

    public List<E> createList(List<Map<Object, Object>> list, Class<?> cls) throws Exception {
        ArrayList arrayList = new ArrayList();
        for (Map<Object, Object> map : list) {
            if (ReflectionUtils.isImplementOfInterface(map.getClass(), Map.class)) {
                arrayList.add(createObject(map, cls));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<E> createSimpleList(List<?> list, Class<?> cls) throws Exception {
        ArrayList arrayList = new ArrayList();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(ReflectionUtils.convertToSimpleType(it.next(), cls));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<E> createSimpleList(Object obj, Class<?> cls) throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ReflectionUtils.convertToSimpleType(obj, cls));
        return arrayList;
    }
}
