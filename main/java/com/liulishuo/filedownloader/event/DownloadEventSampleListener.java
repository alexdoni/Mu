package com.liulishuo.filedownloader.event;

/* loaded from: classes2.dex */
public class DownloadEventSampleListener extends IDownloadListener {

    /* renamed from: i */
    private final IEventListener f712i;

    /* loaded from: classes2.dex */
    public interface IEventListener {
        boolean callback(IDownloadEvent iDownloadEvent);
    }

    public DownloadEventSampleListener(IEventListener iEventListener) {
        this.f712i = iEventListener;
    }

    @Override // com.liulishuo.filedownloader.event.IDownloadListener
    public boolean callback(IDownloadEvent iDownloadEvent) {
        IEventListener iEventListener = this.f712i;
        return iEventListener != null && iEventListener.callback(iDownloadEvent);
    }
}
