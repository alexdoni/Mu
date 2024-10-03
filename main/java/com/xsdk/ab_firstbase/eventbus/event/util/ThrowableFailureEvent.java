package com.xsdk.ab_firstbase.eventbus.event.util;

/* loaded from: classes3.dex */
public class ThrowableFailureEvent implements HasExecutionScope {
    private Object executionContext;
    protected final boolean suppressErrorUi;
    protected final Throwable throwable;

    public ThrowableFailureEvent(Throwable th) {
        this.throwable = th;
        this.suppressErrorUi = false;
    }

    public ThrowableFailureEvent(Throwable th, boolean z) {
        this.throwable = th;
        this.suppressErrorUi = z;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public boolean isSuppressErrorUi() {
        return this.suppressErrorUi;
    }

    @Override // com.xsdk.ab_firstbase.eventbus.event.util.HasExecutionScope
    public Object getExecutionScope() {
        return this.executionContext;
    }

    @Override // com.xsdk.ab_firstbase.eventbus.event.util.HasExecutionScope
    public void setExecutionScope(Object obj) {
        this.executionContext = obj;
    }
}
