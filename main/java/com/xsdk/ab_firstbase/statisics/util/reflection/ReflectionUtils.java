package com.xsdk.ab_firstbase.statisics.util.reflection;

import com.xsdk.ab_firstbase.statisics.util.DateTool;
import com.xsdk.ab_firstbase.statisics.util.json.EJSTypeConverter;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class ReflectionUtils {
    public static boolean isImplementOfInterface(Class<?> cls, Class<?> cls2) {
        if (cls == cls2) {
            return true;
        }
        Class<?>[] interfaces = cls.getInterfaces();
        if (interfaces != null) {
            for (Class<?> cls3 : interfaces) {
                if (cls3 == cls2) {
                    return true;
                }
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass == null || superclass == Object.class) {
            return false;
        }
        return isImplementOfInterface(superclass, cls2);
    }

    public static boolean isSimpleType(Class<?> cls) {
        return cls.isPrimitive() || cls == Integer.class || cls == Short.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == Byte.class || cls == Boolean.class || cls == Character.class || cls.isAssignableFrom(Date.class) || cls == String.class || cls == BigDecimal.class;
    }

    public static Object convertToSimpleType(Object obj, Class<?> cls) throws Exception {
        if (cls == Integer.TYPE) {
            if (obj == null || obj.equals("")) {
                return 0;
            }
            return Integer.valueOf(obj.toString());
        }
        if (cls == Integer.class) {
            if (obj == null || obj.equals("")) {
                return null;
            }
            return Integer.valueOf(obj.toString());
        }
        if (cls == Long.TYPE) {
            if (obj == null || obj.equals("")) {
                return Float.valueOf(0.0f);
            }
            return Long.valueOf(Long.parseLong(obj.toString()));
        }
        if (cls == Long.class) {
            if (obj == null || obj.equals("")) {
                return null;
            }
            return Long.valueOf(Long.parseLong(obj.toString()));
        }
        if (cls == Short.TYPE) {
            if (obj == null || obj.equals("")) {
                return Float.valueOf(0.0f);
            }
            return Short.valueOf(Short.parseShort(obj.toString()));
        }
        if (cls == Short.class) {
            if (obj == null || obj.equals("")) {
                return null;
            }
            return Short.valueOf(Short.parseShort(obj.toString()));
        }
        if (cls == Float.TYPE) {
            if (obj == null || obj.equals("")) {
                return Float.valueOf(0.0f);
            }
            return Float.valueOf(Float.parseFloat(obj.toString()));
        }
        if (cls == Float.class) {
            if (obj == null || obj.equals("")) {
                return null;
            }
            return Float.valueOf(Float.parseFloat(obj.toString()));
        }
        if (cls == Double.TYPE) {
            if (obj == null || obj.equals("")) {
                return 0;
            }
            return Double.valueOf(Double.parseDouble(obj.toString()));
        }
        if (cls == Double.class) {
            if (obj == null || obj.equals("")) {
                return null;
            }
            return Double.valueOf(Double.parseDouble(obj.toString()));
        }
        if (cls == Byte.TYPE) {
            if (obj == null || obj.equals("")) {
                return 0;
            }
            return Byte.valueOf(Byte.parseByte(obj.toString()));
        }
        if (cls == Byte.class) {
            if (obj == null || obj.equals("")) {
                return null;
            }
            return Byte.valueOf(Byte.parseByte(obj.toString()));
        }
        if (cls == Boolean.TYPE) {
            if (obj == null || obj.equals("")) {
                return false;
            }
            return Boolean.valueOf(Boolean.parseBoolean(obj.toString()));
        }
        if (cls == Boolean.class) {
            if (obj == null || obj.equals("")) {
                return null;
            }
            return Boolean.valueOf(Boolean.parseBoolean(obj.toString()));
        }
        if (cls == Character.TYPE) {
            if (obj == null || obj.equals("")) {
                return (char) 0;
            }
            return Character.valueOf(obj.toString().charAt(0));
        }
        if (cls == Character.class) {
            if (obj == null || obj.equals("")) {
                return (char) 0;
            }
            return Character.valueOf(obj.toString().charAt(0));
        }
        if (cls.isAssignableFrom(Date.class)) {
            if (obj == null || obj.equals("")) {
                return null;
            }
            return DateTool.getDateFormat(obj.toString(), DateTool.yyyy_MM_dd_HH_mm_ss);
        }
        if (cls == String.class) {
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }
        if (cls == BigDecimal.class) {
            return BigDecimal.valueOf(Double.parseDouble(obj.toString()));
        }
        throw new IllegalArgumentException("参数类型不是简单类型：" + cls.getName());
    }

    public static Method getMethodByNameInClass(Class<?> cls, String str) {
        for (Method method : cls.getMethods()) {
            if (method.getName().equals(str)) {
                return method;
            }
        }
        return null;
    }

    public static TypeDiscoverInfo discoverType(Type type) {
        TypeDiscoverInfo typeDiscoverInfo = new TypeDiscoverInfo();
        if (type instanceof ParameterizedType) {
            typeDiscoverInfo.isGeneric = true;
            typeDiscoverInfo.typeArgumentList = new ArrayList();
            ParameterizedType parameterizedType = (ParameterizedType) type;
            typeDiscoverInfo.parameterType = (Class) parameterizedType.getRawType();
            for (Type type2 : parameterizedType.getActualTypeArguments()) {
                if (type2 instanceof ParameterizedType) {
                    typeDiscoverInfo.typeArgumentList.add((Class) ((ParameterizedType) type2).getRawType());
                    typeDiscoverInfo.extra = discoverType(type2);
                } else {
                    typeDiscoverInfo.typeArgumentList.add((Class) type2);
                }
            }
        } else {
            typeDiscoverInfo.parameterType = (Class) type;
        }
        return typeDiscoverInfo;
    }

    public static Object buildStrongTypeObject(TypeDiscoverInfo typeDiscoverInfo, Object obj) throws Exception {
        EJSTypeConverter eJSTypeConverter = new EJSTypeConverter();
        Class<?> cls = typeDiscoverInfo.parameterType;
        if (isSimpleType(cls)) {
            return convertToSimpleType(obj, cls);
        }
        int i = 0;
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            if (isSimpleType(componentType)) {
                List list = (List) obj;
                Object newInstance = Array.newInstance(componentType, list.size());
                while (i < list.size()) {
                    Array.set(newInstance, i, convertToSimpleType(list.get(i), componentType));
                    i++;
                }
                return newInstance;
            }
            return eJSTypeConverter.createList((List) obj, componentType).toArray();
        }
        if (isImplementOfInterface(cls, Map.class)) {
            return eJSTypeConverter.createStrongTypeMap((Map) obj, typeDiscoverInfo);
        }
        if (isImplementOfInterface(cls, List.class)) {
            Class<?> cls2 = typeDiscoverInfo.typeArgumentList.get(0);
            if (isSimpleType(cls2)) {
                if (isSimpleType(obj.getClass())) {
                    return eJSTypeConverter.createSimpleList(obj, cls2);
                }
                return eJSTypeConverter.createSimpleList((List<?>) obj, cls2);
            }
            return eJSTypeConverter.createList((List) obj, cls2);
        }
        if (cls.isEnum()) {
            Object[] enumConstants = cls.getEnumConstants();
            int length = enumConstants.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                Object obj2 = enumConstants[i];
                if (obj2.toString().equals(obj.toString())) {
                    obj = obj2;
                    break;
                }
                i++;
            }
            return obj;
        }
        if (obj == null || !obj.toString().equals("")) {
            return eJSTypeConverter.createObject((Map) obj, cls);
        }
        return null;
    }

    public static List<PropertyDescriptor> getPropertyDescriptors(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        Method[] methods = cls.getMethods();
        HashMap hashMap = new HashMap();
        for (Method method : methods) {
            if (method.getName().startsWith("set") && method.getParameterTypes() != null && method.getParameterTypes().length == 1) {
                hashMap.put(method.getName(), method);
            }
        }
        for (Method method2 : methods) {
            if (method2.getName().startsWith("get") && method2.getParameterTypes().length == 0) {
                String str = Character.toLowerCase(method2.getName().charAt(3)) + method2.getName().substring(4, method2.getName().length());
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor();
                propertyDescriptor.setDisplayName(str);
                propertyDescriptor.setName(str);
                propertyDescriptor.setReadMethod(method2);
                String str2 = "set" + str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
                if (hashMap.containsKey(str2)) {
                    propertyDescriptor.setWriteMethod((Method) hashMap.get(str2));
                }
                arrayList.add(propertyDescriptor);
            } else if (method2.getName().startsWith("is") && method2.getParameterTypes().length == 0) {
                String str3 = Character.toLowerCase(method2.getName().charAt(2)) + method2.getName().substring(3, method2.getName().length());
                PropertyDescriptor propertyDescriptor2 = new PropertyDescriptor();
                propertyDescriptor2.setDisplayName(str3);
                propertyDescriptor2.setName(str3);
                propertyDescriptor2.setReadMethod(method2);
                String str4 = "set" + str3.substring(0, 1).toUpperCase() + str3.substring(1, str3.length());
                if (hashMap.containsKey(str4)) {
                    propertyDescriptor2.setWriteMethod((Method) hashMap.get(str4));
                }
                arrayList.add(propertyDescriptor2);
            }
        }
        return arrayList;
    }

    public static List<PropertyDescriptor> getPropertyDescriptors(Object obj) {
        return getPropertyDescriptors(obj.getClass());
    }
}
