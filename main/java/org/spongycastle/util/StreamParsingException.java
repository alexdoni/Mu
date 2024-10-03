package org.spongycastle.util;

/* loaded from: classes3.dex */
public class StreamParsingException extends Exception {

    /* renamed from: _e */
    Throwable f2882_e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f2882_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f2882_e;
    }
}
