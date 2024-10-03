package com.xsdk.ab_firstbase.statisics.util;

/* loaded from: classes3.dex */
public class EventS {
    static EventS mInstance;
    private boolean itps = false;
    private boolean domainTestUrlOpen = false;

    public static EventS getInstance() {
        if (mInstance == null) {
            mInstance = new EventS();
        }
        return mInstance;
    }

    public boolean isItps() {
        return this.itps;
    }

    public void setItps(boolean z) {
        this.itps = z;
    }

    public boolean isDomainTestUrlOpen() {
        return this.domainTestUrlOpen;
    }

    public void setDomainTestUrlOpen(boolean z) {
        this.domainTestUrlOpen = z;
    }
}
