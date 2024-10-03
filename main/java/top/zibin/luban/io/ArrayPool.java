package top.zibin.luban.io;

/* loaded from: classes3.dex */
public interface ArrayPool {
    void clearMemory();

    <T> T get(int i, Class<T> cls);

    <T> void put(T t);

    @Deprecated
    <T> void put(T t, Class<T> cls);
}
