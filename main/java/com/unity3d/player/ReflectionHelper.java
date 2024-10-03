package com.unity3d.player;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import io.jsonwebtoken.JwtParser;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ReflectionHelper {
    protected static boolean LOG = false;
    protected static final boolean LOGV = false;

    /* renamed from: a */
    private static C2671a[] f1659a = new C2671a[4096];

    /* renamed from: b */
    private static long f1660b;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.unity3d.player.ReflectionHelper$a */
    /* loaded from: classes3.dex */
    public static class C2671a {

        /* renamed from: a */
        public volatile Member f1666a;

        /* renamed from: b */
        private final Class f1667b;

        /* renamed from: c */
        private final String f1668c;

        /* renamed from: d */
        private final String f1669d;

        /* renamed from: e */
        private final int f1670e;

        C2671a(Class cls, String str, String str2) {
            this.f1667b = cls;
            this.f1668c = str;
            this.f1669d = str2;
            this.f1670e = ((((cls.hashCode() + 527) * 31) + str.hashCode()) * 31) + str2.hashCode();
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof C2671a) {
                C2671a c2671a = (C2671a) obj;
                if (this.f1670e == c2671a.f1670e && this.f1669d.equals(c2671a.f1669d) && this.f1668c.equals(c2671a.f1668c) && this.f1667b.equals(c2671a.f1667b)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return this.f1670e;
        }
    }

    /* renamed from: com.unity3d.player.ReflectionHelper$b */
    /* loaded from: classes3.dex */
    protected interface InterfaceC2672b extends InvocationHandler {
        /* renamed from: a */
        void mo1271a(long j, boolean z);
    }

    ReflectionHelper() {
    }

    /* renamed from: a */
    private static float m1260a(Class cls, Class cls2) {
        if (cls.equals(cls2)) {
            return 1.0f;
        }
        if (cls.isPrimitive() || cls2.isPrimitive()) {
            return 0.0f;
        }
        try {
            if (cls.asSubclass(cls2) != null) {
                return 0.5f;
            }
        } catch (ClassCastException unused) {
        }
        try {
            return cls2.asSubclass(cls) != null ? 0.1f : 0.0f;
        } catch (ClassCastException unused2) {
            return 0.0f;
        }
    }

    /* renamed from: a */
    private static float m1261a(Class cls, Class[] clsArr, Class[] clsArr2) {
        if (clsArr2.length == 0) {
            return 0.1f;
        }
        int i = 0;
        if ((clsArr == null ? 0 : clsArr.length) + 1 != clsArr2.length) {
            return 0.0f;
        }
        float f = 1.0f;
        if (clsArr != null) {
            int length = clsArr.length;
            float f2 = 1.0f;
            int i2 = 0;
            while (i < length) {
                f2 *= m1260a(clsArr[i], clsArr2[i2]);
                i++;
                i2++;
            }
            f = f2;
        }
        return f * m1260a(cls, clsArr2[clsArr2.length - 1]);
    }

    /* renamed from: a */
    private static Class m1263a(String str, int[] iArr) {
        while (iArr[0] < str.length()) {
            int i = iArr[0];
            iArr[0] = i + 1;
            char charAt = str.charAt(i);
            if (charAt != '(' && charAt != ')') {
                if (charAt == 'L') {
                    int indexOf = str.indexOf(59, iArr[0]);
                    if (indexOf == -1) {
                        return null;
                    }
                    String substring = str.substring(iArr[0], indexOf);
                    iArr[0] = indexOf + 1;
                    try {
                        return Class.forName(substring.replace('/', JwtParser.SEPARATOR_CHAR));
                    } catch (ClassNotFoundException unused) {
                        return null;
                    }
                }
                if (charAt == 'Z') {
                    return Boolean.TYPE;
                }
                if (charAt == 'I') {
                    return Integer.TYPE;
                }
                if (charAt == 'F') {
                    return Float.TYPE;
                }
                if (charAt == 'V') {
                    return Void.TYPE;
                }
                if (charAt == 'B') {
                    return Byte.TYPE;
                }
                if (charAt == 'C') {
                    return Character.TYPE;
                }
                if (charAt == 'S') {
                    return Short.TYPE;
                }
                if (charAt == 'J') {
                    return Long.TYPE;
                }
                if (charAt == 'D') {
                    return Double.TYPE;
                }
                if (charAt == '[') {
                    return Array.newInstance((Class<?>) m1263a(str, iArr), 0).getClass();
                }
                C2711g.Log(5, "! parseType; " + charAt + " is not known!");
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static synchronized void m1266a(C2671a c2671a, Member member) {
        synchronized (ReflectionHelper.class) {
            c2671a.f1666a = member;
            f1659a[c2671a.hashCode() & (f1659a.length - 1)] = c2671a;
        }
    }

    /* renamed from: a */
    private static synchronized boolean m1267a(C2671a c2671a) {
        synchronized (ReflectionHelper.class) {
            C2671a c2671a2 = f1659a[c2671a.hashCode() & (f1659a.length - 1)];
            if (!c2671a.equals(c2671a2)) {
                return false;
            }
            c2671a.f1666a = c2671a2.f1666a;
            return true;
        }
    }

    /* renamed from: a */
    private static Class[] m1268a(String str) {
        Class m1263a;
        int i = 0;
        int[] iArr = {0};
        ArrayList arrayList = new ArrayList();
        while (iArr[0] < str.length() && (m1263a = m1263a(str, iArr)) != null) {
            arrayList.add(m1263a);
        }
        Class[] clsArr = new Class[arrayList.size()];
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            clsArr[i] = (Class) it.next();
            i++;
        }
        return clsArr;
    }

    protected static void endUnityLaunch() {
        f1660b++;
    }

    protected static Constructor getConstructorID(Class cls, String str) {
        Constructor<?> constructor;
        C2671a c2671a = new C2671a(cls, "", str);
        if (m1267a(c2671a)) {
            constructor = (Constructor) c2671a.f1666a;
        } else {
            Class[] m1268a = m1268a(str);
            Constructor<?>[] constructors = cls.getConstructors();
            int length = constructors.length;
            Constructor<?> constructor2 = null;
            float f = 0.0f;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Constructor<?> constructor3 = constructors[i];
                float m1261a = m1261a(Void.TYPE, constructor3.getParameterTypes(), m1268a);
                if (m1261a > f) {
                    if (m1261a == 1.0f) {
                        constructor2 = constructor3;
                        break;
                    }
                    constructor2 = constructor3;
                    f = m1261a;
                }
                i++;
            }
            m1266a(c2671a, constructor2);
            constructor = constructor2;
        }
        if (constructor != null) {
            return constructor;
        }
        throw new NoSuchMethodError("<init>" + str + " in class " + cls.getName());
    }

    protected static Field getFieldID(Class cls, String str, String str2, boolean z) {
        Field field;
        Class cls2 = cls;
        C2671a c2671a = new C2671a(cls2, str, str2);
        if (m1267a(c2671a)) {
            field = (Field) c2671a.f1666a;
        } else {
            Class[] m1268a = m1268a(str2);
            float f = 0.0f;
            Field field2 = null;
            while (cls2 != null) {
                Field[] declaredFields = cls2.getDeclaredFields();
                int length = declaredFields.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Field field3 = declaredFields[i];
                    if (z == Modifier.isStatic(field3.getModifiers()) && field3.getName().compareTo(str) == 0) {
                        float m1261a = m1261a(field3.getType(), (Class[]) null, m1268a);
                        if (m1261a > f) {
                            field2 = field3;
                            if (m1261a == 1.0f) {
                                f = m1261a;
                                break;
                            }
                            f = m1261a;
                        } else {
                            continue;
                        }
                    }
                    i++;
                }
                if (f == 1.0f || cls2.isPrimitive() || cls2.isInterface() || cls2.equals(Object.class) || cls2.equals(Void.TYPE)) {
                    break;
                }
                cls2 = cls2.getSuperclass();
            }
            m1266a(c2671a, field2);
            field = field2;
        }
        if (field != null) {
            return field;
        }
        Object[] objArr = new Object[4];
        objArr[0] = z ? "static" : "non-static";
        objArr[1] = str;
        objArr[2] = str2;
        objArr[3] = cls2.getName();
        throw new NoSuchFieldError(String.format("no %s field with name='%s' signature='%s' in class L%s;", objArr));
    }

    protected static String getFieldSignature(Field field) {
        Class<?> type = field.getType();
        if (type.isPrimitive()) {
            String name = type.getName();
            return TypedValues.Custom.S_BOOLEAN.equals(name) ? "Z" : "byte".equals(name) ? "B" : "char".equals(name) ? "C" : "double".equals(name) ? "D" : TypedValues.Custom.S_FLOAT.equals(name) ? "F" : "int".equals(name) ? "I" : "long".equals(name) ? "J" : "short".equals(name) ? ExifInterface.LATITUDE_SOUTH : name;
        }
        if (type.isArray()) {
            return type.getName().replace(JwtParser.SEPARATOR_CHAR, '/');
        }
        return "L" + type.getName().replace(JwtParser.SEPARATOR_CHAR, '/') + ";";
    }

    protected static Method getMethodID(Class cls, String str, String str2, boolean z) {
        Method method;
        C2671a c2671a = new C2671a(cls, str, str2);
        if (m1267a(c2671a)) {
            method = (Method) c2671a.f1666a;
        } else {
            Class[] m1268a = m1268a(str2);
            Method method2 = null;
            float f = 0.0f;
            while (cls != null) {
                Method[] declaredMethods = cls.getDeclaredMethods();
                int length = declaredMethods.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Method method3 = declaredMethods[i];
                    if (z == Modifier.isStatic(method3.getModifiers()) && method3.getName().compareTo(str) == 0) {
                        float m1261a = m1261a(method3.getReturnType(), method3.getParameterTypes(), m1268a);
                        if (m1261a > f) {
                            if (m1261a == 1.0f) {
                                method2 = method3;
                                f = m1261a;
                                break;
                            }
                            method2 = method3;
                            f = m1261a;
                        } else {
                            continue;
                        }
                    }
                    i++;
                }
                if (f == 1.0f || cls.isPrimitive() || cls.isInterface() || cls.equals(Object.class) || cls.equals(Void.TYPE)) {
                    break;
                }
                cls = cls.getSuperclass();
            }
            m1266a(c2671a, method2);
            method = method2;
        }
        if (method != null) {
            return method;
        }
        Object[] objArr = new Object[4];
        objArr[0] = z ? "static" : "non-static";
        objArr[1] = str;
        objArr[2] = str2;
        objArr[3] = cls.getName();
        throw new NoSuchMethodError(String.format("no %s method with name='%s' signature='%s' in class L%s;", objArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeProxyFinalize(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native Object nativeProxyInvoke(long j, String str, Object[] objArr);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeProxyLogJNIInvokeException(long j);

    protected static Object newProxyInstance(long j, Class cls) {
        return newProxyInstance(j, new Class[]{cls});
    }

    protected static Object newProxyInstance(final long j, final Class[] clsArr) {
        return Proxy.newProxyInstance(ReflectionHelper.class.getClassLoader(), clsArr, new InterfaceC2672b() { // from class: com.unity3d.player.ReflectionHelper.1

            /* renamed from: c */
            private long f1663c = ReflectionHelper.f1660b;

            /* renamed from: d */
            private long f1664d;

            /* renamed from: e */
            private boolean f1665e;

            /* renamed from: a */
            private Object m1270a(Object obj, Method method, Object[] objArr) {
                if (objArr == null) {
                    try {
                        objArr = new Object[0];
                    } catch (NoClassDefFoundError unused) {
                        C2711g.Log(6, String.format("Java interface default methods are only supported since Android Oreo", new Object[0]));
                        ReflectionHelper.nativeProxyLogJNIInvokeException(this.f1664d);
                        return null;
                    }
                }
                Class<?> declaringClass = method.getDeclaringClass();
                Constructor declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
                declaredConstructor.setAccessible(true);
                return ((MethodHandles.Lookup) declaredConstructor.newInstance(declaringClass, 2)).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(obj).invokeWithArguments(objArr);
            }

            @Override // com.unity3d.player.ReflectionHelper.InterfaceC2672b
            /* renamed from: a */
            public final void mo1271a(long j2, boolean z) {
                this.f1664d = j2;
                this.f1665e = z;
            }

            protected final void finalize() {
                try {
                    if (this.f1663c == ReflectionHelper.f1660b) {
                        ReflectionHelper.nativeProxyFinalize(j);
                    }
                } finally {
                    super.finalize();
                }
            }

            /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
            
                if (r6 != 0) goto L15;
             */
            @Override // java.lang.reflect.InvocationHandler
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invoke(java.lang.Object r6, java.lang.reflect.Method r7, java.lang.Object[] r8) {
                /*
                    r5 = this;
                    long r0 = r5.f1663c
                    long r2 = com.unity3d.player.ReflectionHelper.m1262a()
                    int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                    if (r0 == 0) goto L12
                    r6 = 6
                    java.lang.String r7 = "Scripting proxy object was destroyed, because Unity player was unloaded."
                    com.unity3d.player.C2711g.Log(r6, r7)
                    r6 = 0
                    return r6
                L12:
                    r0 = 0
                    r5.f1664d = r0
                    r2 = 0
                    r5.f1665e = r2
                    long r2 = r1
                    java.lang.String r4 = r7.getName()
                    java.lang.Object r2 = com.unity3d.player.ReflectionHelper.m1264a(r2, r4, r8)
                    boolean r3 = r5.f1665e
                    if (r3 == 0) goto L37
                    int r0 = r7.getModifiers()
                    r0 = r0 & 1024(0x400, float:1.435E-42)
                    if (r0 != 0) goto L34
                    java.lang.Object r6 = r5.m1270a(r6, r7, r8)
                    return r6
                L34:
                    long r6 = r5.f1664d
                    goto L3d
                L37:
                    long r6 = r5.f1664d
                    int r8 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
                    if (r8 == 0) goto L40
                L3d:
                    com.unity3d.player.ReflectionHelper.m1265a(r6)
                L40:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.ReflectionHelper.C26701.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
            }
        });
    }

    protected static void setNativeExceptionOnProxy(Object obj, long j, boolean z) {
        ((InterfaceC2672b) Proxy.getInvocationHandler(obj)).mo1271a(j, z);
    }
}
