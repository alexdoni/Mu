package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.assetpacks.AssetPackLocation;
import com.google.android.play.core.assetpacks.AssetPackManager;
import com.google.android.play.core.assetpacks.AssetPackManagerFactory;
import com.google.android.play.core.assetpacks.AssetPackState;
import com.google.android.play.core.assetpacks.AssetPackStateUpdateListener;
import com.google.android.play.core.assetpacks.AssetPackStates;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.RuntimeExecutionException;
import com.google.android.play.core.tasks.Task;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class AssetPackManagerWrapper {

    /* renamed from: a */
    private static AssetPackManagerWrapper f1581a;

    /* renamed from: b */
    private AssetPackManager f1582b;

    /* renamed from: c */
    private HashSet f1583c;

    /* renamed from: d */
    private Object f1584d;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.unity3d.player.AssetPackManagerWrapper$a */
    /* loaded from: classes3.dex */
    public static class RunnableC2657a implements Runnable {

        /* renamed from: a */
        private Set f1585a;

        /* renamed from: b */
        private String f1586b;

        /* renamed from: c */
        private int f1587c;

        /* renamed from: d */
        private long f1588d;

        /* renamed from: e */
        private long f1589e;

        /* renamed from: f */
        private int f1590f;

        /* renamed from: g */
        private int f1591g;

        RunnableC2657a(Set set, String str, int i, long j, long j2, int i2, int i3) {
            this.f1585a = set;
            this.f1586b = str;
            this.f1587c = i;
            this.f1588d = j;
            this.f1589e = j2;
            this.f1590f = i2;
            this.f1591g = i3;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Iterator it = this.f1585a.iterator();
            while (it.hasNext()) {
                ((IAssetPackManagerDownloadStatusCallback) it.next()).onStatusUpdate(this.f1586b, this.f1587c, this.f1588d, this.f1589e, this.f1590f, this.f1591g);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.unity3d.player.AssetPackManagerWrapper$b */
    /* loaded from: classes3.dex */
    public class C2658b implements AssetPackStateUpdateListener {

        /* renamed from: b */
        private HashSet f1593b;

        /* renamed from: c */
        private Looper f1594c;

        public C2658b(AssetPackManagerWrapper assetPackManagerWrapper, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
            this(iAssetPackManagerDownloadStatusCallback, Looper.myLooper());
        }

        public C2658b(IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback, Looper looper) {
            HashSet hashSet = new HashSet();
            this.f1593b = hashSet;
            hashSet.add(iAssetPackManagerDownloadStatusCallback);
            this.f1594c = looper;
        }

        /* renamed from: a */
        private static Set m1221a(HashSet hashSet) {
            return (Set) hashSet.clone();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public synchronized void onStateUpdate(AssetPackState assetPackState) {
            if (assetPackState.status() == 4 || assetPackState.status() == 5 || assetPackState.status() == 0) {
                synchronized (AssetPackManagerWrapper.f1581a) {
                    AssetPackManagerWrapper.this.f1583c.remove(assetPackState.name());
                    if (AssetPackManagerWrapper.this.f1583c.isEmpty()) {
                        AssetPackManagerWrapper assetPackManagerWrapper = AssetPackManagerWrapper.this;
                        assetPackManagerWrapper.unregisterDownloadStatusListener(assetPackManagerWrapper.f1584d);
                        AssetPackManagerWrapper.m1220c(AssetPackManagerWrapper.this);
                    }
                }
            }
            if (this.f1593b.size() == 0) {
                return;
            }
            new Handler(this.f1594c).post(new RunnableC2657a(m1221a(this.f1593b), assetPackState.name(), assetPackState.status(), assetPackState.totalBytesToDownload(), assetPackState.bytesDownloaded(), assetPackState.transferProgressPercentage(), assetPackState.errorCode()));
        }

        /* renamed from: a */
        public final synchronized void m1223a(IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
            this.f1593b.add(iAssetPackManagerDownloadStatusCallback);
        }
    }

    /* renamed from: com.unity3d.player.AssetPackManagerWrapper$c */
    /* loaded from: classes3.dex */
    private static class C2659c implements OnSuccessListener {

        /* renamed from: a */
        private IAssetPackManagerMobileDataConfirmationCallback f1595a;

        /* renamed from: b */
        private Looper f1596b = Looper.myLooper();

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: com.unity3d.player.AssetPackManagerWrapper$c$a */
        /* loaded from: classes3.dex */
        public static class a implements Runnable {

            /* renamed from: a */
            private IAssetPackManagerMobileDataConfirmationCallback f1597a;

            /* renamed from: b */
            private boolean f1598b;

            a(IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback, boolean z) {
                this.f1597a = iAssetPackManagerMobileDataConfirmationCallback;
                this.f1598b = z;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f1597a.onMobileDataConfirmationResult(this.f1598b);
            }
        }

        public C2659c(IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback) {
            this.f1595a = iAssetPackManagerMobileDataConfirmationCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Integer num) {
            if (this.f1595a != null) {
                new Handler(this.f1596b).post(new a(this.f1595a, num.intValue() == -1));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.unity3d.player.AssetPackManagerWrapper$d */
    /* loaded from: classes3.dex */
    public static class C2660d implements OnCompleteListener {

        /* renamed from: a */
        private IAssetPackManagerDownloadStatusCallback f1599a;

        /* renamed from: b */
        private Looper f1600b = Looper.myLooper();

        /* renamed from: c */
        private String f1601c;

        public C2660d(IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback, String str) {
            this.f1599a = iAssetPackManagerDownloadStatusCallback;
            this.f1601c = str;
        }

        /* renamed from: a */
        private void m1225a(String str, int i, int i2, long j) {
            new Handler(this.f1600b).post(new RunnableC2657a(Collections.singleton(this.f1599a), str, i, j, i == 4 ? j : 0L, 0, i2));
        }

        public final void onComplete(Task task) {
            try {
                AssetPackStates assetPackStates = (AssetPackStates) task.getResult();
                Map packStates = assetPackStates.packStates();
                if (packStates.size() == 0) {
                    return;
                }
                for (AssetPackState assetPackState : packStates.values()) {
                    if (assetPackState.errorCode() != 0 || assetPackState.status() == 4 || assetPackState.status() == 5 || assetPackState.status() == 0) {
                        m1225a(assetPackState.name(), assetPackState.status(), assetPackState.errorCode(), assetPackStates.totalBytes());
                    } else {
                        AssetPackManagerWrapper.f1581a.m1217a(assetPackState.name(), this.f1599a, this.f1600b);
                    }
                }
            } catch (RuntimeExecutionException e) {
                m1225a(this.f1601c, 0, e.getErrorCode(), 0L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.unity3d.player.AssetPackManagerWrapper$e */
    /* loaded from: classes3.dex */
    public static class C2661e implements OnCompleteListener {

        /* renamed from: a */
        private IAssetPackManagerStatusQueryCallback f1602a;

        /* renamed from: b */
        private Looper f1603b = Looper.myLooper();

        /* renamed from: c */
        private String[] f1604c;

        /* renamed from: com.unity3d.player.AssetPackManagerWrapper$e$a */
        /* loaded from: classes3.dex */
        private static class a implements Runnable {

            /* renamed from: a */
            private IAssetPackManagerStatusQueryCallback f1605a;

            /* renamed from: b */
            private long f1606b;

            /* renamed from: c */
            private String[] f1607c;

            /* renamed from: d */
            private int[] f1608d;

            /* renamed from: e */
            private int[] f1609e;

            a(IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback, long j, String[] strArr, int[] iArr, int[] iArr2) {
                this.f1605a = iAssetPackManagerStatusQueryCallback;
                this.f1606b = j;
                this.f1607c = strArr;
                this.f1608d = iArr;
                this.f1609e = iArr2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f1605a.onStatusResult(this.f1606b, this.f1607c, this.f1608d, this.f1609e);
            }
        }

        public C2661e(IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback, String[] strArr) {
            this.f1602a = iAssetPackManagerStatusQueryCallback;
            this.f1604c = strArr;
        }

        public final void onComplete(Task task) {
            if (this.f1602a == null) {
                return;
            }
            int i = 0;
            try {
                AssetPackStates assetPackStates = (AssetPackStates) task.getResult();
                Map packStates = assetPackStates.packStates();
                int size = packStates.size();
                String[] strArr = new String[size];
                int[] iArr = new int[size];
                int[] iArr2 = new int[size];
                for (AssetPackState assetPackState : packStates.values()) {
                    strArr[i] = assetPackState.name();
                    iArr[i] = assetPackState.status();
                    iArr2[i] = assetPackState.errorCode();
                    i++;
                }
                new Handler(this.f1603b).post(new a(this.f1602a, assetPackStates.totalBytes(), strArr, iArr, iArr2));
            } catch (RuntimeExecutionException e) {
                String message = e.getMessage();
                for (String str : this.f1604c) {
                    if (message.contains(str)) {
                        new Handler(this.f1603b).post(new a(this.f1602a, 0L, new String[]{str}, new int[]{0}, new int[]{e.getErrorCode()}));
                        return;
                    }
                }
                String[] strArr2 = this.f1604c;
                int[] iArr3 = new int[strArr2.length];
                int[] iArr4 = new int[strArr2.length];
                for (int i2 = 0; i2 < this.f1604c.length; i2++) {
                    iArr3[i2] = 0;
                    iArr4[i2] = e.getErrorCode();
                }
                new Handler(this.f1603b).post(new a(this.f1602a, 0L, this.f1604c, iArr3, iArr4));
            }
        }
    }

    private AssetPackManagerWrapper(Context context) {
        if (f1581a != null) {
            throw new RuntimeException("AssetPackManagerWrapper should be created only once. Use getInstance() instead.");
        }
        try {
            this.f1582b = AssetPackManagerFactory.getInstance(context);
        } catch (NoClassDefFoundError unused) {
            this.f1582b = null;
        }
        this.f1583c = new HashSet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1217a(String str, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback, Looper looper) {
        synchronized (f1581a) {
            Object obj = this.f1584d;
            if (obj == null) {
                C2658b c2658b = new C2658b(iAssetPackManagerDownloadStatusCallback, looper);
                this.f1582b.registerListener(c2658b);
                this.f1584d = c2658b;
            } else {
                ((C2658b) obj).m1223a(iAssetPackManagerDownloadStatusCallback);
            }
            this.f1583c.add(str);
            this.f1582b.fetch(Collections.singletonList(str));
        }
    }

    /* renamed from: b */
    private void m1219b() {
        if (playCoreApiMissing()) {
            throw new RuntimeException("AssetPackManager API is not available! Make sure your gradle project includes \"com.google.android.play:core\" dependency.");
        }
    }

    /* renamed from: c */
    static /* synthetic */ Object m1220c(AssetPackManagerWrapper assetPackManagerWrapper) {
        assetPackManagerWrapper.f1584d = null;
        return null;
    }

    public static synchronized AssetPackManagerWrapper getInstance() {
        AssetPackManagerWrapper assetPackManagerWrapper;
        synchronized (AssetPackManagerWrapper.class) {
            while (true) {
                assetPackManagerWrapper = f1581a;
                if (assetPackManagerWrapper != null) {
                    break;
                }
                try {
                    AssetPackManagerWrapper.class.wait(3000L);
                } catch (InterruptedException e) {
                    C2711g.Log(6, e.getMessage());
                }
            }
            if (assetPackManagerWrapper == null) {
                throw new RuntimeException("AssetPackManagerWrapper is not yet initialised.");
            }
        }
        return assetPackManagerWrapper;
    }

    public static synchronized AssetPackManagerWrapper init(Context context) {
        AssetPackManagerWrapper assetPackManagerWrapper;
        synchronized (AssetPackManagerWrapper.class) {
            if (f1581a != null) {
                throw new RuntimeException("AssetPackManagerWrapper.init() should be called only once. Use getInstance() instead.");
            }
            f1581a = new AssetPackManagerWrapper(context);
            AssetPackManagerWrapper.class.notifyAll();
            assetPackManagerWrapper = f1581a;
        }
        return assetPackManagerWrapper;
    }

    public void cancelAssetPackDownload(String str) {
        cancelAssetPackDownloads(new String[]{str});
    }

    public void cancelAssetPackDownloads(String[] strArr) {
        m1219b();
        this.f1582b.cancel(Arrays.asList(strArr));
    }

    public void downloadAssetPack(String str, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        downloadAssetPacks(new String[]{str}, iAssetPackManagerDownloadStatusCallback);
    }

    public void downloadAssetPacks(String[] strArr, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        m1219b();
        for (String str : strArr) {
            this.f1582b.getPackStates(Collections.singletonList(str)).addOnCompleteListener(new C2660d(iAssetPackManagerDownloadStatusCallback, str));
        }
    }

    public String getAssetPackPath(String str) {
        m1219b();
        AssetPackLocation packLocation = this.f1582b.getPackLocation(str);
        return packLocation == null ? "" : packLocation.assetsPath();
    }

    public void getAssetPackState(String str, IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback) {
        getAssetPackStates(new String[]{str}, iAssetPackManagerStatusQueryCallback);
    }

    public void getAssetPackStates(String[] strArr, IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback) {
        m1219b();
        this.f1582b.getPackStates(Arrays.asList(strArr)).addOnCompleteListener(new C2661e(iAssetPackManagerStatusQueryCallback, strArr));
    }

    public boolean playCoreApiMissing() {
        return this.f1582b == null;
    }

    public Object registerDownloadStatusListener(IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        m1219b();
        C2658b c2658b = new C2658b(this, iAssetPackManagerDownloadStatusCallback);
        this.f1582b.registerListener(c2658b);
        return c2658b;
    }

    public void removeAssetPack(String str) {
        m1219b();
        this.f1582b.removePack(str);
    }

    public void requestToUseMobileData(Activity activity, IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback) {
        m1219b();
        this.f1582b.showCellularDataConfirmation(activity).addOnSuccessListener(new C2659c(iAssetPackManagerMobileDataConfirmationCallback));
    }

    public void unregisterDownloadStatusListener(Object obj) {
        m1219b();
        if (obj instanceof C2658b) {
            this.f1582b.unregisterListener((C2658b) obj);
        }
    }
}
