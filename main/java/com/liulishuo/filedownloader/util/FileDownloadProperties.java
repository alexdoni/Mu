package com.liulishuo.filedownloader.util;

/* loaded from: classes2.dex */
public class FileDownloadProperties {
    private static final String FALSE_STRING = "false";
    private static final String KEY_BROADCAST_COMPLETED = "broadcast.completed";
    private static final String KEY_DOWNLOAD_MAX_NETWORK_THREAD_COUNT = "download.max-network-thread-count";
    private static final String KEY_DOWNLOAD_MIN_PROGRESS_STEP = "download.min-progress-step";
    private static final String KEY_DOWNLOAD_MIN_PROGRESS_TIME = "download.min-progress-time";
    private static final String KEY_FILE_NON_PRE_ALLOCATION = "file.non-pre-allocation";
    private static final String KEY_HTTP_LENIENT = "http.lenient";
    private static final String KEY_PROCESS_NON_SEPARATE = "process.non-separate";
    private static final String KEY_TRIAL_CONNECTION_HEAD_METHOD = "download.trial-connection-head-method";
    private static final String TRUE_STRING = "true";
    public final boolean broadcastCompleted;
    public final int downloadMaxNetworkThreadCount;
    public final int downloadMinProgressStep;
    public final long downloadMinProgressTime;
    public final boolean fileNonPreAllocation;
    public final boolean httpLenient;
    public final boolean processNonSeparate;
    public final boolean trialConnectionHeadMethod;

    /* loaded from: classes2.dex */
    public static class HolderClass {
        private static final FileDownloadProperties INSTANCE = new FileDownloadProperties();
    }

    public static FileDownloadProperties getImpl() {
        return HolderClass.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x00ad A[Catch: all -> 0x0294, TryCatch #5 {all -> 0x0294, blocks: (B:77:0x0036, B:80:0x003d, B:83:0x0041, B:86:0x0045, B:89:0x0049, B:92:0x004d, B:95:0x0051, B:98:0x0055, B:102:0x00a9, B:104:0x00ad, B:106:0x00b1, B:115:0x00bf), top: B:76:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x00c6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x00bf A[Catch: all -> 0x0294, TRY_LEAVE, TryCatch #5 {all -> 0x0294, blocks: (B:77:0x0036, B:80:0x003d, B:83:0x0041, B:86:0x0045, B:89:0x0049, B:92:0x004d, B:95:0x0051, B:98:0x0055, B:102:0x00a9, B:104:0x00ad, B:106:0x00b1, B:115:0x00bf), top: B:76:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0117  */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private FileDownloadProperties() {
        /*
            Method dump skipped, instructions count: 682
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.util.FileDownloadProperties.<init>():void");
    }

    public static int getValidNetworkThreadCount(int i) {
        if (i > 12) {
            FileDownloadLog.m596w(FileDownloadProperties.class, "require the count of network thread  is %d, what is more than the max valid count(%d), so adjust to %d auto", Integer.valueOf(i), 12, 12);
            return 12;
        }
        if (i >= 1) {
            return i;
        }
        FileDownloadLog.m596w(FileDownloadProperties.class, "require the count of network thread  is %d, what is less than the min valid count(%d), so adjust to %d auto", Integer.valueOf(i), 1, 1);
        return 1;
    }
}
