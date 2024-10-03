package com.liulishuo.filedownloader.event;

import com.liulishuo.filedownloader.util.FileDownloadLog;

/* loaded from: classes2.dex */
public abstract class IDownloadEvent {
    public Runnable callback = null;

    /* renamed from: id */
    protected final String f714id;

    public IDownloadEvent(String str) {
        this.f714id = str;
    }

    public IDownloadEvent(String str, boolean z) {
        this.f714id = str;
        if (z) {
            FileDownloadLog.m596w(this, "do not handle ORDER any more, %s", str);
        }
    }

    public final String getId() {
        return this.f714id;
    }
}
