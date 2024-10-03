package com.facebook.internal;

import com.facebook.FacebookException;
import com.facebook.internal.WorkQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkQueue.kt */
@Metadata(m1394d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 \u001a2\u00020\u0001:\u0003\u001a\u001b\u001cB\u001b\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007J\u0014\u0010\u0013\u001a\u00020\u00142\n\u0010\u0015\u001a\u00060\bR\u00020\u0000H\u0002J\u0016\u0010\u0016\u001a\u00020\u00142\f\u0010\u0017\u001a\b\u0018\u00010\bR\u00020\u0000H\u0002J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\u0006\u0010\u0019\u001a\u00020\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0018\u00010\bR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0018\u00010\bR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m1395d2 = {"Lcom/facebook/internal/WorkQueue;", "", "maxConcurrent", "", "executor", "Ljava/util/concurrent/Executor;", "(ILjava/util/concurrent/Executor;)V", "pendingJobs", "Lcom/facebook/internal/WorkQueue$WorkNode;", "runningCount", "runningJobs", "workLock", "Ljava/util/concurrent/locks/ReentrantLock;", "addActiveWorkItem", "Lcom/facebook/internal/WorkQueue$WorkItem;", "callback", "Ljava/lang/Runnable;", "addToFront", "", "execute", "", "node", "finishItemAndStartNew", "finished", "startItem", "validate", "Companion", "WorkItem", "WorkNode", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class WorkQueue {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int DEFAULT_MAX_CONCURRENT = 8;
    private final Executor executor;
    private final int maxConcurrent;
    private WorkNode pendingJobs;
    private int runningCount;
    private WorkNode runningJobs;
    private final ReentrantLock workLock;

    /* compiled from: WorkQueue.kt */
    @Metadata(m1394d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\b"}, m1395d2 = {"Lcom/facebook/internal/WorkQueue$WorkItem;", "", "isRunning", "", "()Z", "cancel", "moveToFront", "", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public interface WorkItem {
        boolean cancel();

        /* renamed from: isRunning */
        boolean getIsRunning();

        void moveToFront();
    }

    public WorkQueue() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    public WorkQueue(int i) {
        this(i, null, 2, 0 == true ? 1 : 0);
    }

    public final WorkItem addActiveWorkItem(Runnable callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return addActiveWorkItem$default(this, callback, false, 2, null);
    }

    public WorkQueue(int i, Executor executor) {
        Intrinsics.checkNotNullParameter(executor, "executor");
        this.maxConcurrent = i;
        this.executor = executor;
        this.workLock = new ReentrantLock();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ WorkQueue(int r1, java.util.concurrent.Executor r2, int r3, kotlin.jvm.internal.DefaultConstructorMarker r4) {
        /*
            r0 = this;
            r4 = r3 & 1
            if (r4 == 0) goto L6
            r1 = 8
        L6:
            r3 = r3 & 2
            if (r3 == 0) goto L10
            com.facebook.FacebookSdk r2 = com.facebook.FacebookSdk.INSTANCE
            java.util.concurrent.Executor r2 = com.facebook.FacebookSdk.getExecutor()
        L10:
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.WorkQueue.<init>(int, java.util.concurrent.Executor, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* compiled from: WorkQueue.kt */
    @Metadata(m1394d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, m1395d2 = {"Lcom/facebook/internal/WorkQueue$Companion;", "", "()V", "DEFAULT_MAX_CONCURRENT", "", "assert", "", "condition", "", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: assert, reason: not valid java name */
        public final void m1762assert(boolean condition) {
            if (!condition) {
                throw new FacebookException("Validation failed");
            }
        }
    }

    public static /* synthetic */ WorkItem addActiveWorkItem$default(WorkQueue workQueue, Runnable runnable, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return workQueue.addActiveWorkItem(runnable, z);
    }

    public final WorkItem addActiveWorkItem(Runnable callback, boolean addToFront) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        WorkNode workNode = new WorkNode(this, callback);
        ReentrantLock reentrantLock = this.workLock;
        reentrantLock.lock();
        try {
            this.pendingJobs = workNode.addToList(this.pendingJobs, addToFront);
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            startItem();
            return workNode;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
    
        throw new java.lang.IllegalStateException("Required value was null.".toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002b, code lost:
    
        r1 = com.facebook.internal.WorkQueue.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        if (r6.runningCount != r4) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0031, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0032, code lost:
    
        r1.m1762assert(r2);
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003a, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
    
        if (r1 != null) goto L5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
    
        if (r1 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0010, code lost:
    
        r1.verify(true);
        r4 = r4 + 1;
        r1 = r1.getNext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001a, code lost:
    
        if (r1 != r6.runningJobs) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void validate() {
        /*
            r6 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r6.workLock
            java.util.concurrent.locks.Lock r0 = (java.util.concurrent.locks.Lock) r0
            r0.lock()
            com.facebook.internal.WorkQueue$WorkNode r1 = r6.runningJobs     // Catch: java.lang.Throwable -> L3b
            r2 = 0
            r3 = 1
            r4 = r2
            if (r1 == 0) goto L2b
        Le:
            if (r1 == 0) goto L1d
            r1.verify(r3)     // Catch: java.lang.Throwable -> L3b
            int r4 = r4 + r3
            com.facebook.internal.WorkQueue$WorkNode r1 = r1.getNext()     // Catch: java.lang.Throwable -> L3b
            com.facebook.internal.WorkQueue$WorkNode r5 = r6.runningJobs     // Catch: java.lang.Throwable -> L3b
            if (r1 != r5) goto Le
            goto L2b
        L1d:
            java.lang.String r1 = "Required value was null."
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L3b
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L3b
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L3b
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch: java.lang.Throwable -> L3b
            throw r2     // Catch: java.lang.Throwable -> L3b
        L2b:
            com.facebook.internal.WorkQueue$Companion r1 = com.facebook.internal.WorkQueue.INSTANCE     // Catch: java.lang.Throwable -> L3b
            int r5 = r6.runningCount     // Catch: java.lang.Throwable -> L3b
            if (r5 != r4) goto L32
            r2 = r3
        L32:
            com.facebook.internal.WorkQueue.Companion.access$assert(r1, r2)     // Catch: java.lang.Throwable -> L3b
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L3b
            r0.unlock()
            return
        L3b:
            r1 = move-exception
            r0.unlock()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.WorkQueue.validate():void");
    }

    private final void startItem() {
        finishItemAndStartNew(null);
    }

    private final void finishItemAndStartNew(WorkNode finished) {
        WorkNode workNode;
        this.workLock.lock();
        if (finished != null) {
            this.runningJobs = finished.removeFromList(this.runningJobs);
            this.runningCount--;
        }
        if (this.runningCount < this.maxConcurrent) {
            workNode = this.pendingJobs;
            if (workNode != null) {
                this.pendingJobs = workNode.removeFromList(workNode);
                this.runningJobs = workNode.addToList(this.runningJobs, false);
                this.runningCount++;
                workNode.setRunning(true);
            }
        } else {
            workNode = null;
        }
        this.workLock.unlock();
        if (workNode != null) {
            execute(workNode);
        }
    }

    private final void execute(final WorkNode node) {
        this.executor.execute(new Runnable() { // from class: com.facebook.internal.WorkQueue$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WorkQueue.m1761execute$lambda2(WorkQueue.WorkNode.this, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: execute$lambda-2, reason: not valid java name */
    public static final void m1761execute$lambda2(WorkNode node, WorkQueue this$0) {
        Intrinsics.checkNotNullParameter(node, "$node");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            node.getCallback().run();
        } finally {
            this$0.finishItemAndStartNew(node);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WorkQueue.kt */
    @Metadata(m1394d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0012\u001a\u00060\u0000R\u00020\r2\f\u0010\u0013\u001a\b\u0018\u00010\u0000R\u00020\r2\u0006\u0010\u0014\u001a\u00020\bJ\b\u0010\u0015\u001a\u00020\bH\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u0018\u001a\b\u0018\u00010\u0000R\u00020\r2\f\u0010\u0013\u001a\b\u0018\u00010\u0000R\u00020\rJ\u000e\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR*\u0010\u000e\u001a\b\u0018\u00010\u0000R\u00020\r2\f\u0010\f\u001a\b\u0018\u00010\u0000R\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0018\u00010\u0000R\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m1395d2 = {"Lcom/facebook/internal/WorkQueue$WorkNode;", "Lcom/facebook/internal/WorkQueue$WorkItem;", "callback", "Ljava/lang/Runnable;", "(Lcom/facebook/internal/WorkQueue;Ljava/lang/Runnable;)V", "getCallback", "()Ljava/lang/Runnable;", "isRunning", "", "()Z", "setRunning", "(Z)V", "<set-?>", "Lcom/facebook/internal/WorkQueue;", "next", "getNext", "()Lcom/facebook/internal/WorkQueue$WorkNode;", "prev", "addToList", "list", "addToFront", "cancel", "moveToFront", "", "removeFromList", "verify", "shouldBeRunning", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public final class WorkNode implements WorkItem {
        private final Runnable callback;
        private boolean isRunning;
        private WorkNode next;
        private WorkNode prev;
        final /* synthetic */ WorkQueue this$0;

        public WorkNode(WorkQueue this$0, Runnable callback) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.this$0 = this$0;
            this.callback = callback;
        }

        public final Runnable getCallback() {
            return this.callback;
        }

        public final WorkNode getNext() {
            return this.next;
        }

        @Override // com.facebook.internal.WorkQueue.WorkItem
        /* renamed from: isRunning, reason: from getter */
        public boolean getIsRunning() {
            return this.isRunning;
        }

        public void setRunning(boolean z) {
            this.isRunning = z;
        }

        @Override // com.facebook.internal.WorkQueue.WorkItem
        public boolean cancel() {
            ReentrantLock reentrantLock = this.this$0.workLock;
            WorkQueue workQueue = this.this$0;
            reentrantLock.lock();
            try {
                if (!getIsRunning()) {
                    workQueue.pendingJobs = removeFromList(workQueue.pendingJobs);
                    reentrantLock.unlock();
                    return true;
                }
                Unit unit = Unit.INSTANCE;
                reentrantLock.unlock();
                return false;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }

        @Override // com.facebook.internal.WorkQueue.WorkItem
        public void moveToFront() {
            ReentrantLock reentrantLock = this.this$0.workLock;
            WorkQueue workQueue = this.this$0;
            reentrantLock.lock();
            try {
                if (!getIsRunning()) {
                    workQueue.pendingJobs = removeFromList(workQueue.pendingJobs);
                    workQueue.pendingJobs = addToList(workQueue.pendingJobs, true);
                }
                Unit unit = Unit.INSTANCE;
            } finally {
                reentrantLock.unlock();
            }
        }

        public final WorkNode addToList(WorkNode list, boolean addToFront) {
            WorkQueue.INSTANCE.m1762assert(this.next == null);
            WorkQueue.INSTANCE.m1762assert(this.prev == null);
            if (list == null) {
                this.prev = this;
                this.next = this;
                list = this;
            } else {
                this.next = list;
                WorkNode workNode = list.prev;
                this.prev = workNode;
                if (workNode != null) {
                    workNode.next = this;
                }
                WorkNode workNode2 = this.next;
                if (workNode2 != null) {
                    workNode2.prev = workNode == null ? null : workNode.next;
                }
            }
            if (list != null) {
                return addToFront ? this : list;
            }
            throw new IllegalStateException("Required value was null.".toString());
        }

        public final WorkNode removeFromList(WorkNode list) {
            WorkQueue.INSTANCE.m1762assert(this.next != null);
            WorkQueue.INSTANCE.m1762assert(this.prev != null);
            if (list == this && (list = this.next) == this) {
                list = null;
            }
            WorkNode workNode = this.next;
            if (workNode != null) {
                workNode.prev = this.prev;
            }
            WorkNode workNode2 = this.prev;
            if (workNode2 != null) {
                workNode2.next = workNode;
            }
            this.prev = null;
            this.next = null;
            return list;
        }

        public final void verify(boolean shouldBeRunning) {
            WorkNode workNode;
            WorkNode workNode2;
            Companion companion = WorkQueue.INSTANCE;
            WorkNode workNode3 = this.prev;
            if (workNode3 == null || (workNode = workNode3.next) == null) {
                workNode = this;
            }
            companion.m1762assert(workNode == this);
            Companion companion2 = WorkQueue.INSTANCE;
            WorkNode workNode4 = this.next;
            if (workNode4 == null || (workNode2 = workNode4.prev) == null) {
                workNode2 = this;
            }
            companion2.m1762assert(workNode2 == this);
            WorkQueue.INSTANCE.m1762assert(getIsRunning() == shouldBeRunning);
        }
    }
}
