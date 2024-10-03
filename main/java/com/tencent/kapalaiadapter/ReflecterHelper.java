package com.tencent.kapalaiadapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.tencent.p014av.utils.QLog;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public final class ReflecterHelper {
    public static Class<?> mCurrentClass;

    public static final boolean setClass(String str) {
        Class<?> cls;
        try {
            cls = Class.forName(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            cls = null;
        }
        mCurrentClass = cls;
        return cls != null;
    }

    public static final int getStaticIntValue(String str, int i) {
        Field field = getField(str);
        if (field == null) {
            return i;
        }
        try {
            return field.getInt(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return i;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return i;
        }
    }

    public static final int getIntValue(Object obj, String str, int i) {
        setClass(obj.getClass().getName());
        Field field = getField(str);
        if (field == null) {
            return i;
        }
        try {
            return field.getInt(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return i;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return i;
        }
    }

    private static final Field getField(String str) {
        Field field = null;
        try {
            field = mCurrentClass.getDeclaredField(str);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return field;
        } catch (SecurityException e2) {
            e2.printStackTrace();
            return field;
        }
    }

    public static Object getStaticProperty(String str, String str2) {
        setClass(str);
        Field field = getField(str2);
        if (field == null) {
            return null;
        }
        try {
            return field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void setStaticProperty(String str, String str2, Object obj) {
        setClass(str);
        Field field = getField(str2);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(null, obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public static Object newInstance(String str, Object[] objArr) throws Exception {
        return Class.forName(str).getConstructor(getArgsClasses(objArr)).newInstance(objArr);
    }

    public static Object newInstance(String str) throws Exception {
        return newInstance(str, null);
    }

    public static Object invokeMethod(Object obj, String str, Object[] objArr) throws Exception {
        return invokeMethod(obj, str, getArgsClasses(objArr), objArr);
    }

    public static Object invokeMethod(Object obj, String str) throws Exception {
        return invokeMethod(obj, str, null);
    }

    public static Object invokeMethod(Object obj, String str, Class<?>[] clsArr, Object[] objArr) throws Exception {
        Method method = obj.getClass().getMethod(str, clsArr);
        method.setAccessible(true);
        return method.invoke(obj, objArr);
    }

    public static Object getProperty(Object obj, String str) throws Exception {
        return obj.getClass().getField(str).get(obj);
    }

    public static void setProperty(Object obj, String str, Object obj2) throws Exception {
        Field field = obj.getClass().getField(str);
        field.setAccessible(true);
        field.set(obj, obj2);
    }

    public static Object newInstance(String str, Object[] objArr, Class<?>[] clsArr) throws Exception {
        return Class.forName(str).getConstructor(clsArr).newInstance(objArr);
    }

    public static Object invokeStaticMethod(String str, String str2, Object[] objArr, Class<?>[] clsArr) throws Exception {
        Class<?> cls = Class.forName(str);
        return cls.getDeclaredMethod(str2, clsArr).invoke(cls, objArr);
    }

    public static Object invokeStaticMethod(String str, String str2, Object[] objArr) throws Exception {
        return invokeStaticMethod(str, str2, objArr, getArgsClasses(objArr));
    }

    public static Object invokeStaticMethod(String str, String str2) throws Exception {
        return invokeStaticMethod(str, str2, null);
    }

    private static Class<?>[] getArgsClasses(Object[] objArr) {
        Class<?>[] clsArr = null;
        if (objArr != null) {
            clsArr = new Class[objArr.length];
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                Object obj = objArr[i];
                if (obj != null) {
                    clsArr[i] = obj.getClass();
                } else {
                    clsArr[i] = String.class;
                }
                Class<?> cls = clsArr[i];
                if (cls == Integer.class) {
                    clsArr[i] = Integer.TYPE;
                } else if (cls == Boolean.class) {
                    clsArr[i] = Boolean.TYPE;
                }
            }
        }
        return clsArr;
    }

    public static void fixInputMethodManagerLeak(Context context) {
        InputMethodManager inputMethodManager;
        if (context == null || (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) == null) {
            return;
        }
        String[] strArr = {"mCurRootView", "mServedView", "mNextServedView"};
        for (int i = 0; i < 3; i++) {
            try {
                Field declaredField = inputMethodManager.getClass().getDeclaredField(strArr[i]);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                Object obj = declaredField.get(inputMethodManager);
                if (obj != null && (obj instanceof View)) {
                    View view = (View) obj;
                    if (view.getContext() != context) {
                        QLog.m598d("ReflecterHelper", "fixInputMethodManagerLeak break, context is not suitable, get_context=" + view.getContext() + " dest_context=" + context);
                        return;
                    }
                    declaredField.set(inputMethodManager, null);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void fixMesssageLeak(Dialog dialog) {
        if (dialog == null) {
            return;
        }
        String[] strArr = {"mDismissMessage", "mCancelMessage", "mShowMessage"};
        for (int i = 0; i < 3; i++) {
            try {
                Field declaredField = Dialog.class.getDeclaredField(strArr[i]);
                if (declaredField != null) {
                    if (!declaredField.isAccessible()) {
                        declaredField.setAccessible(true);
                    }
                    Object obj = declaredField.get(dialog);
                    if (obj instanceof Message) {
                        Message message = (Message) obj;
                        if (message.obj != null) {
                            message.obj = null;
                            message.what = 0;
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (NoSuchFieldException e3) {
                e3.printStackTrace();
            }
        }
    }
}
