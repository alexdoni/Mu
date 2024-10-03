package com.u2020.sdk.mc;

import java.util.Map;

/* loaded from: classes3.dex */
public class Channel {

    /* renamed from: a */
    private final long f1554a;

    /* renamed from: b */
    private final String f1555b;

    /* renamed from: c */
    private final Map<String, Object> f1556c;

    public Channel(long agentID, String siteID, Map<String, Object> extraInfo) {
        this.f1554a = agentID;
        this.f1555b = siteID;
        this.f1556c = extraInfo;
    }

    public long getAgentID() {
        return this.f1554a;
    }

    public Map<String, Object> getExtraInfo() {
        return this.f1556c;
    }

    public String getSiteID() {
        return this.f1555b;
    }

    public String toString() {
        return "Channel{agentID='" + this.f1554a + "', siteID='" + this.f1555b + "', extraInfo=" + this.f1556c + '}';
    }
}
