package bitter.jnibridge;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes.dex */
public class JNIBridge {

    /* renamed from: bitter.jnibridge.JNIBridge$a */
    /* loaded from: classes.dex */
    private static class C0653a implements InvocationHandler {

        /* renamed from: a */
        private Object f128a = new Object[0];

        /* renamed from: b */
        private long f129b;

        /* renamed from: c */
        private Constructor f130c;

        public C0653a(long j) {
            this.f129b = j;
            try {
                Constructor declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
                this.f130c = declaredConstructor;
                declaredConstructor.setAccessible(true);
            } catch (NoClassDefFoundError unused) {
                this.f130c = null;
            } catch (NoSuchMethodException unused2) {
                this.f130c = null;
            }
        }

        /* renamed from: a */
        private Object m53a(Object obj, Method method, Object[] objArr) {
            if (objArr == null) {
                objArr = new Object[0];
            }
            Class<?> declaringClass = method.getDeclaringClass();
            return ((MethodHandles.Lookup) this.f130c.newInstance(declaringClass, 2)).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(obj).invokeWithArguments(objArr);
        }

        /* renamed from: a */
        public final void m54a() {
            synchronized (this.f128a) {
                this.f129b = 0L;
            }
        }

        public final void finalize() {
            synchronized (this.f128a) {
                long j = this.f129b;
                if (j == 0) {
                    return;
                }
                JNIBridge.delete(j);
            }
        }

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) {
            synchronized (this.f128a) {
                long j = this.f129b;
                if (j == 0) {
                    return null;
                }
                try {
                    return JNIBridge.invoke(j, method.getDeclaringClass(), method, objArr);
                } catch (NoSuchMethodError e) {
                    if (this.f130c == null) {
                        System.err.println("JNIBridge error: Java interface default methods are only supported since Android Oreo");
                        throw e;
                    }
                    if ((method.getModifiers() & 1024) == 0) {
                        return m53a(obj, method, objArr);
                    }
                    throw e;
                }
            }
        }
    }

    static native void delete(long j);

    static void disableInterfaceProxy(Object obj) {
        if (obj != null) {
            ((C0653a) Proxy.getInvocationHandler(obj)).m54a();
        }
    }

    static native Object invoke(long j, Class cls, Method method, Object[] objArr);

    static Object newInterfaceProxy(long j, Class[] clsArr) {
        return Proxy.newProxyInstance(JNIBridge.class.getClassLoader(), clsArr, new C0653a(j));
    }
}
