package org.spongycastle.util;

/* loaded from: classes3.dex */
public class StoreException extends RuntimeException {

    /* renamed from: _e */
    private Throwable f2881_e;

    public StoreException(String str, Throwable th) {
        super(str);
        this.f2881_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f2881_e;
    }
}
