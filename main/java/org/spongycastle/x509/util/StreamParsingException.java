package org.spongycastle.x509.util;

/* loaded from: classes3.dex */
public class StreamParsingException extends Exception {

    /* renamed from: _e */
    Throwable f2884_e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f2884_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f2884_e;
    }
}
