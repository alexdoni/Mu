package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.IThreadPoolMonitor;
import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.download.CustomComponentHolder;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
class FileDownloadManager implements IThreadPoolMonitor {
    private final FileDownloadDatabase mDatabase;
    private final FileDownloadThreadPool mThreadPool;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileDownloadManager() {
        CustomComponentHolder impl = CustomComponentHolder.getImpl();
        this.mDatabase = impl.getDatabaseInstance();
        this.mThreadPool = new FileDownloadThreadPool(impl.getMaxNetworkThreadCount());
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x0183 A[Catch: all -> 0x01ce, TryCatch #0 {, blocks: (B:4:0x0009, B:6:0x0010, B:7:0x0022, B:10:0x0034, B:12:0x0044, B:14:0x004e, B:16:0x0052, B:17:0x0065, B:19:0x0072, B:21:0x0078, B:23:0x007c, B:28:0x008d, B:29:0x0096, B:31:0x009f, B:33:0x00a3, B:38:0x00b6, B:40:0x00bf, B:41:0x00c8, B:43:0x00d7, B:45:0x00db, B:47:0x00ec, B:51:0x00fa, B:53:0x0101, B:55:0x0108, B:57:0x010e, B:59:0x0115, B:61:0x011b, B:63:0x0121, B:65:0x013b, B:66:0x013f, B:68:0x0145, B:72:0x0183, B:73:0x0188, B:76:0x0154, B:78:0x015e, B:80:0x0164, B:81:0x0169, B:82:0x00c4, B:84:0x0092), top: B:3:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void start(java.lang.String r19, java.lang.String r20, boolean r21, int r22, int r23, int r24, boolean r25, com.liulishuo.filedownloader.model.FileDownloadHeader r26, boolean r27) {
        /*
            Method dump skipped, instructions count: 465
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.services.FileDownloadManager.start(java.lang.String, java.lang.String, boolean, int, int, int, boolean, com.liulishuo.filedownloader.model.FileDownloadHeader, boolean):void");
    }

    public boolean isDownloading(String str, String str2) {
        return isDownloading(FileDownloadUtils.generateId(str, str2));
    }

    public boolean isDownloading(int i) {
        return isDownloading(this.mDatabase.find(i));
    }

    public boolean pause(int i) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.m591d(this, "request pause the task %d", Integer.valueOf(i));
        }
        FileDownloadModel find = this.mDatabase.find(i);
        if (find == null) {
            return false;
        }
        find.setStatus((byte) -2);
        this.mThreadPool.cancel(i);
        return true;
    }

    public void pauseAll() {
        List<Integer> allExactRunningDownloadIds = this.mThreadPool.getAllExactRunningDownloadIds();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.m591d(this, "pause all tasks %d", Integer.valueOf(allExactRunningDownloadIds.size()));
        }
        Iterator<Integer> it = allExactRunningDownloadIds.iterator();
        while (it.hasNext()) {
            pause(it.next().intValue());
        }
    }

    public long getSoFar(int i) {
        FileDownloadModel find = this.mDatabase.find(i);
        if (find == null) {
            return 0L;
        }
        int connectionCount = find.getConnectionCount();
        if (connectionCount <= 1) {
            return find.getSoFar();
        }
        List<ConnectionModel> findConnectionModel = this.mDatabase.findConnectionModel(i);
        if (findConnectionModel == null || findConnectionModel.size() != connectionCount) {
            return 0L;
        }
        return ConnectionModel.getTotalOffset(findConnectionModel);
    }

    public long getTotal(int i) {
        FileDownloadModel find = this.mDatabase.find(i);
        if (find == null) {
            return 0L;
        }
        return find.getTotal();
    }

    public byte getStatus(int i) {
        FileDownloadModel find = this.mDatabase.find(i);
        if (find == null) {
            return (byte) 0;
        }
        return find.getStatus();
    }

    public boolean isIdle() {
        return this.mThreadPool.exactSize() <= 0;
    }

    public synchronized boolean setMaxNetworkThreadCount(int i) {
        return this.mThreadPool.setMaxNetworkThreadCount(i);
    }

    @Override // com.liulishuo.filedownloader.IThreadPoolMonitor
    public boolean isDownloading(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            return false;
        }
        boolean isInThreadPool = this.mThreadPool.isInThreadPool(fileDownloadModel.getId());
        if (FileDownloadStatus.isOver(fileDownloadModel.getStatus())) {
            if (!isInThreadPool) {
                return false;
            }
        } else if (!isInThreadPool) {
            FileDownloadLog.m592e(this, "%d status is[%s](not finish) & but not in the pool", Integer.valueOf(fileDownloadModel.getId()), Byte.valueOf(fileDownloadModel.getStatus()));
            return false;
        }
        return true;
    }

    @Override // com.liulishuo.filedownloader.IThreadPoolMonitor
    public int findRunningTaskIdBySameTempPath(String str, int i) {
        return this.mThreadPool.findRunningTaskIdBySameTempPath(str, i);
    }

    public boolean clearTaskData(int i) {
        if (i == 0) {
            FileDownloadLog.m596w(this, "The task[%d] id is invalid, can't clear it.", Integer.valueOf(i));
            return false;
        }
        if (isDownloading(i)) {
            FileDownloadLog.m596w(this, "The task[%d] is downloading, can't clear it.", Integer.valueOf(i));
            return false;
        }
        this.mDatabase.remove(i);
        this.mDatabase.removeConnections(i);
        return true;
    }

    public void clearAllTaskData() {
        this.mDatabase.clear();
    }
}
