package com.liulishuo.filedownloader.event;

/* loaded from: classes2.dex */
public class DownloadServiceConnectChangedEvent extends IDownloadEvent {

    /* renamed from: ID */
    public static final String f713ID = "event.service.connect.changed";
    private final Class<?> serviceClass;
    private final ConnectStatus status;

    /* loaded from: classes2.dex */
    public enum ConnectStatus {
        connected,
        disconnected,
        lost
    }

    public DownloadServiceConnectChangedEvent(ConnectStatus connectStatus, Class<?> cls) {
        super(f713ID);
        this.status = connectStatus;
        this.serviceClass = cls;
    }

    public ConnectStatus getStatus() {
        return this.status;
    }

    public boolean isSuchService(Class<?> cls) {
        Class<?> cls2 = this.serviceClass;
        return cls2 != null && cls2.getName().equals(cls.getName());
    }
}
