package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFe1fSDK {
    final Timer AFInAppEventParameterName;
    final Set<AFf1zSDK> AFInAppEventType;
    public Executor AFKeystoreWrapper;
    final Set<AFe1eSDK<?>> AFLogger;

    /* renamed from: d */
    final NavigableSet<AFe1eSDK<?>> f195d;

    /* renamed from: e */
    final NavigableSet<AFe1eSDK<?>> f196e;
    final Set<AFf1zSDK> registerClient;
    final List<AFe1eSDK<?>> unregisterClient;
    public final List<AFe1bSDK> valueOf;
    final ExecutorService values;

    public AFe1fSDK(ExecutorService executorService) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(newSingleThreadExecutor, "");
        this.AFKeystoreWrapper = newSingleThreadExecutor;
        this.AFInAppEventParameterName = new Timer(true);
        this.valueOf = new CopyOnWriteArrayList();
        this.AFInAppEventType = new CopyOnWriteArraySet();
        this.registerClient = Collections.newSetFromMap(new ConcurrentHashMap());
        this.f196e = new ConcurrentSkipListSet();
        this.f195d = new ConcurrentSkipListSet();
        this.unregisterClient = new ArrayList();
        this.AFLogger = Collections.newSetFromMap(new ConcurrentHashMap());
        this.values = executorService;
    }

    /* renamed from: com.appsflyer.internal.AFe1fSDK$3 */
    /* loaded from: classes.dex */
    public class RunnableC07083 implements Runnable {
        private /* synthetic */ AFe1eSDK AFInAppEventType;

        public RunnableC07083(AFe1eSDK aFe1eSDK) {
            this.AFInAppEventType = aFe1eSDK;
        }

        @Override // java.lang.Runnable
        public final void run() {
            boolean add;
            synchronized (AFe1fSDK.this.f196e) {
                if (AFe1fSDK.this.AFLogger.contains(this.AFInAppEventType)) {
                    AFLogger aFLogger = AFLogger.INSTANCE;
                    AFg1gSDK aFg1gSDK = AFg1gSDK.QUEUE;
                    StringBuilder sb = new StringBuilder("tried to add already running task: ");
                    sb.append(this.AFInAppEventType);
                    aFLogger.m96d(aFg1gSDK, sb.toString());
                    return;
                }
                if (!AFe1fSDK.this.f196e.contains(this.AFInAppEventType) && !AFe1fSDK.this.f195d.contains(this.AFInAppEventType)) {
                    AFe1fSDK aFe1fSDK = AFe1fSDK.this;
                    AFe1eSDK aFe1eSDK = this.AFInAppEventType;
                    for (AFf1zSDK aFf1zSDK : aFe1eSDK.valueOf) {
                        if (aFe1fSDK.registerClient.contains(aFf1zSDK)) {
                            aFe1eSDK.AFKeystoreWrapper.add(aFf1zSDK);
                        }
                    }
                    if (AFe1fSDK.this.values(this.AFInAppEventType)) {
                        add = AFe1fSDK.this.f196e.add(this.AFInAppEventType);
                    } else {
                        add = AFe1fSDK.this.f195d.add(this.AFInAppEventType);
                        if (add) {
                            AFLogger aFLogger2 = AFLogger.INSTANCE;
                            AFg1gSDK aFg1gSDK2 = AFg1gSDK.QUEUE;
                            StringBuilder sb2 = new StringBuilder("new task was blocked: ");
                            sb2.append(this.AFInAppEventType);
                            aFLogger2.m96d(aFg1gSDK2, sb2.toString());
                            this.AFInAppEventType.valueOf();
                        }
                    }
                    if (add) {
                        AFe1fSDK.this.f196e.addAll(AFe1fSDK.this.unregisterClient);
                        AFe1fSDK.this.unregisterClient.clear();
                    } else {
                        AFLogger aFLogger3 = AFLogger.INSTANCE;
                        AFg1gSDK aFg1gSDK3 = AFg1gSDK.QUEUE;
                        StringBuilder sb3 = new StringBuilder("task not added, it's already in the queue: ");
                        sb3.append(this.AFInAppEventType);
                        aFLogger3.m96d(aFg1gSDK3, sb3.toString());
                    }
                    if (add) {
                        AFe1fSDK.this.registerClient.add(this.AFInAppEventType.AFInAppEventParameterName);
                        AFLogger aFLogger4 = AFLogger.INSTANCE;
                        AFg1gSDK aFg1gSDK4 = AFg1gSDK.QUEUE;
                        StringBuilder sb4 = new StringBuilder("new task added: ");
                        sb4.append(this.AFInAppEventType);
                        aFLogger4.m96d(aFg1gSDK4, sb4.toString());
                        for (AFe1bSDK aFe1bSDK : AFe1fSDK.this.valueOf) {
                        }
                        AFe1fSDK aFe1fSDK2 = AFe1fSDK.this;
                        aFe1fSDK2.values.submit(new RunnableC07094());
                        AFe1fSDK aFe1fSDK3 = AFe1fSDK.this;
                        synchronized (aFe1fSDK3.f196e) {
                            for (int size = (aFe1fSDK3.f196e.size() + aFe1fSDK3.f195d.size()) - 40; size > 0; size--) {
                                boolean z = !aFe1fSDK3.f195d.isEmpty();
                                boolean isEmpty = true ^ aFe1fSDK3.f196e.isEmpty();
                                if (isEmpty && z) {
                                    if (aFe1fSDK3.f196e.first().compareTo(aFe1fSDK3.f195d.first()) > 0) {
                                        aFe1fSDK3.valueOf(aFe1fSDK3.f196e);
                                    } else {
                                        aFe1fSDK3.valueOf(aFe1fSDK3.f195d);
                                    }
                                } else if (isEmpty) {
                                    aFe1fSDK3.valueOf(aFe1fSDK3.f196e);
                                } else if (z) {
                                    aFe1fSDK3.valueOf(aFe1fSDK3.f195d);
                                }
                            }
                        }
                        return;
                    }
                    AFLogger aFLogger5 = AFLogger.INSTANCE;
                    AFg1gSDK aFg1gSDK5 = AFg1gSDK.QUEUE;
                    StringBuilder sb5 = new StringBuilder("QUEUE: tried to add already pending task: ");
                    sb5.append(this.AFInAppEventType);
                    aFLogger5.m103w(aFg1gSDK5, sb5.toString());
                    return;
                }
                AFLogger aFLogger6 = AFLogger.INSTANCE;
                AFg1gSDK aFg1gSDK6 = AFg1gSDK.QUEUE;
                StringBuilder sb6 = new StringBuilder("tried to add already scheduled task: ");
                sb6.append(this.AFInAppEventType);
                aFLogger6.m96d(aFg1gSDK6, sb6.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.appsflyer.internal.AFe1fSDK$4 */
    /* loaded from: classes.dex */
    public final class RunnableC07094 implements Runnable {
        RunnableC07094() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (AFe1fSDK.this.f196e) {
                final AFe1eSDK<?> pollFirst = AFe1fSDK.this.f196e.pollFirst();
                if (pollFirst == null) {
                    return;
                }
                AFe1fSDK.this.AFLogger.add(pollFirst);
                long AFInAppEventType = pollFirst.AFInAppEventType();
                AFf1ySDK aFf1ySDK = new AFf1ySDK(Thread.currentThread());
                if (AFInAppEventType > 0) {
                    AFe1fSDK.this.AFInAppEventParameterName.schedule(aFf1ySDK, AFInAppEventType);
                }
                final AFe1fSDK aFe1fSDK = AFe1fSDK.this;
                aFe1fSDK.AFKeystoreWrapper.execute(new Runnable() { // from class: com.appsflyer.internal.AFe1fSDK.5
                    @Override // java.lang.Runnable
                    public final void run() {
                        Iterator<AFe1bSDK> it = AFe1fSDK.this.valueOf.iterator();
                        while (it.hasNext()) {
                            it.next().values(pollFirst);
                        }
                    }
                });
                if (!AFe1fSDK.this.f196e.isEmpty()) {
                    AFe1fSDK aFe1fSDK2 = AFe1fSDK.this;
                    aFe1fSDK2.values.submit(new RunnableC07094());
                }
                try {
                    AFLogger.INSTANCE.m96d(AFg1gSDK.QUEUE, "starting task execution: ".concat(String.valueOf(pollFirst)));
                    final AFe1dSDK call = pollFirst.call();
                    aFf1ySDK.cancel();
                    final AFe1fSDK aFe1fSDK3 = AFe1fSDK.this;
                    aFe1fSDK3.AFKeystoreWrapper.execute(new Runnable() { // from class: com.appsflyer.internal.AFe1fSDK.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            AFLogger aFLogger = AFLogger.INSTANCE;
                            AFg1gSDK aFg1gSDK = AFg1gSDK.QUEUE;
                            StringBuilder sb = new StringBuilder("execution finished for ");
                            sb.append(pollFirst);
                            sb.append(", result: ");
                            sb.append(call);
                            aFLogger.m96d(aFg1gSDK, sb.toString());
                            AFe1fSDK.this.AFLogger.remove(pollFirst);
                            Iterator<AFe1bSDK> it = AFe1fSDK.this.valueOf.iterator();
                            while (it.hasNext()) {
                                it.next().AFInAppEventParameterName(pollFirst, call);
                            }
                            if (call == AFe1dSDK.SUCCESS) {
                                AFe1fSDK.this.AFInAppEventType.add(pollFirst.AFInAppEventParameterName);
                                AFe1fSDK.AFInAppEventType(AFe1fSDK.this);
                                return;
                            }
                            if (pollFirst.AFInAppEventParameterName()) {
                                if (AFe1fSDK.valueOf((AFe1eSDK<?>) pollFirst)) {
                                    synchronized (AFe1fSDK.this.f196e) {
                                        AFe1fSDK.this.unregisterClient.add(pollFirst);
                                        for (AFe1bSDK aFe1bSDK : AFe1fSDK.this.valueOf) {
                                        }
                                    }
                                    return;
                                }
                                return;
                            }
                            AFe1fSDK.this.AFInAppEventType.add(pollFirst.AFInAppEventParameterName);
                            AFe1fSDK.AFInAppEventType(AFe1fSDK.this);
                        }
                    });
                } catch (InterruptedIOException | InterruptedException unused) {
                    AFLogger.INSTANCE.m96d(AFg1gSDK.QUEUE, "task was interrupted: ".concat(String.valueOf(pollFirst)));
                    pollFirst.AFInAppEventType = AFe1dSDK.TIMEOUT;
                    final AFe1fSDK aFe1fSDK4 = AFe1fSDK.this;
                    final AFe1dSDK aFe1dSDK = AFe1dSDK.TIMEOUT;
                    aFe1fSDK4.AFKeystoreWrapper.execute(new Runnable() { // from class: com.appsflyer.internal.AFe1fSDK.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            AFLogger aFLogger = AFLogger.INSTANCE;
                            AFg1gSDK aFg1gSDK = AFg1gSDK.QUEUE;
                            StringBuilder sb = new StringBuilder("execution finished for ");
                            sb.append(pollFirst);
                            sb.append(", result: ");
                            sb.append(aFe1dSDK);
                            aFLogger.m96d(aFg1gSDK, sb.toString());
                            AFe1fSDK.this.AFLogger.remove(pollFirst);
                            Iterator<AFe1bSDK> it = AFe1fSDK.this.valueOf.iterator();
                            while (it.hasNext()) {
                                it.next().AFInAppEventParameterName(pollFirst, aFe1dSDK);
                            }
                            if (aFe1dSDK == AFe1dSDK.SUCCESS) {
                                AFe1fSDK.this.AFInAppEventType.add(pollFirst.AFInAppEventParameterName);
                                AFe1fSDK.AFInAppEventType(AFe1fSDK.this);
                                return;
                            }
                            if (pollFirst.AFInAppEventParameterName()) {
                                if (AFe1fSDK.valueOf((AFe1eSDK<?>) pollFirst)) {
                                    synchronized (AFe1fSDK.this.f196e) {
                                        AFe1fSDK.this.unregisterClient.add(pollFirst);
                                        for (AFe1bSDK aFe1bSDK : AFe1fSDK.this.valueOf) {
                                        }
                                    }
                                    return;
                                }
                                return;
                            }
                            AFe1fSDK.this.AFInAppEventType.add(pollFirst.AFInAppEventParameterName);
                            AFe1fSDK.AFInAppEventType(AFe1fSDK.this);
                        }
                    });
                } catch (Throwable unused2) {
                    aFf1ySDK.cancel();
                    final AFe1fSDK aFe1fSDK5 = AFe1fSDK.this;
                    final AFe1dSDK aFe1dSDK2 = AFe1dSDK.FAILURE;
                    aFe1fSDK5.AFKeystoreWrapper.execute(new Runnable() { // from class: com.appsflyer.internal.AFe1fSDK.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            AFLogger aFLogger = AFLogger.INSTANCE;
                            AFg1gSDK aFg1gSDK = AFg1gSDK.QUEUE;
                            StringBuilder sb = new StringBuilder("execution finished for ");
                            sb.append(pollFirst);
                            sb.append(", result: ");
                            sb.append(aFe1dSDK2);
                            aFLogger.m96d(aFg1gSDK, sb.toString());
                            AFe1fSDK.this.AFLogger.remove(pollFirst);
                            Iterator<AFe1bSDK> it = AFe1fSDK.this.valueOf.iterator();
                            while (it.hasNext()) {
                                it.next().AFInAppEventParameterName(pollFirst, aFe1dSDK2);
                            }
                            if (aFe1dSDK2 == AFe1dSDK.SUCCESS) {
                                AFe1fSDK.this.AFInAppEventType.add(pollFirst.AFInAppEventParameterName);
                                AFe1fSDK.AFInAppEventType(AFe1fSDK.this);
                                return;
                            }
                            if (pollFirst.AFInAppEventParameterName()) {
                                if (AFe1fSDK.valueOf((AFe1eSDK<?>) pollFirst)) {
                                    synchronized (AFe1fSDK.this.f196e) {
                                        AFe1fSDK.this.unregisterClient.add(pollFirst);
                                        for (AFe1bSDK aFe1bSDK : AFe1fSDK.this.valueOf) {
                                        }
                                    }
                                    return;
                                }
                                return;
                            }
                            AFe1fSDK.this.AFInAppEventType.add(pollFirst.AFInAppEventParameterName);
                            AFe1fSDK.AFInAppEventType(AFe1fSDK.this);
                        }
                    });
                }
            }
        }
    }

    final void valueOf(NavigableSet<AFe1eSDK<?>> navigableSet) {
        AFe1eSDK<?> pollFirst = navigableSet.pollFirst();
        this.AFInAppEventType.add(pollFirst.AFInAppEventParameterName);
        Iterator<AFe1bSDK> it = this.valueOf.iterator();
        while (it.hasNext()) {
            it.next().AFKeystoreWrapper(pollFirst);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean values(AFe1eSDK<?> aFe1eSDK) {
        return this.AFInAppEventType.containsAll(aFe1eSDK.AFKeystoreWrapper);
    }

    public static boolean valueOf(AFe1eSDK<?> aFe1eSDK) {
        return ((aFe1eSDK instanceof AFf1kSDK) && aFe1eSDK.AFInAppEventParameterName == AFf1zSDK.ARS_VALIDATE) ? false : true;
    }

    static /* synthetic */ void AFInAppEventType(AFe1fSDK aFe1fSDK) {
        synchronized (aFe1fSDK.f196e) {
            Iterator<AFe1eSDK<?>> it = aFe1fSDK.f195d.iterator();
            boolean z = false;
            while (it.hasNext()) {
                AFe1eSDK<?> next = it.next();
                if (aFe1fSDK.values(next)) {
                    it.remove();
                    aFe1fSDK.f196e.add(next);
                    z = true;
                }
            }
            if (z) {
                aFe1fSDK.values.submit(new RunnableC07094());
            }
        }
    }
}
