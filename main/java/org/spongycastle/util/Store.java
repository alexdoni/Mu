package org.spongycastle.util;

import java.util.Collection;

/* loaded from: classes3.dex */
public interface Store<T> {
    Collection<T> getMatches(Selector<T> selector) throws StoreException;
}
