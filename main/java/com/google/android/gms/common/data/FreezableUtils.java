package com.google.android.gms.common.data;

import com.linecorp.linesdk.dialog.internal.SendMessagePresenter;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes2.dex */
public final class FreezableUtils {
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        SendMessagePresenter.C20362 c20362 = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            c20362.add(arrayList.get(i).freeze());
        }
        return c20362;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        SendMessagePresenter.C20362 c20362 = (ArrayList<T>) new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            c20362.add(it.next().freeze());
        }
        return c20362;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        SendMessagePresenter.C20362 c20362 = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e : eArr) {
            c20362.add(e.freeze());
        }
        return c20362;
    }
}
