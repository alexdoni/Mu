package com.xsdk.ab_firstbase.statisics.util.reflection;

import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class PropertyDescriptor {
    private String displayName;
    private String name;
    private Method readMethod;
    private Method writeMethod;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public Method getReadMethod() {
        return this.readMethod;
    }

    public void setReadMethod(Method method) {
        this.readMethod = method;
    }

    public Method getWriteMethod() {
        return this.writeMethod;
    }

    public void setWriteMethod(Method method) {
        this.writeMethod = method;
    }
}
