package com.facebook.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.WorkQueue;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.share.internal.ShareConstants;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageDownloader.kt */
@Metadata(m1394d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004-./0B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\b\u0010\u0015\u001a\u00020\u0016H\u0007J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u000fH\u0002J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007J \u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u0012H\u0002J\u0018\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u000fH\u0002J(\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0014\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\"H\u0007J2\u0010#\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u000f2\u000e\u0010$\u001a\n\u0018\u00010%j\u0004\u0018\u0001`&2\b\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020\u0012H\u0002J\u0010\u0010*\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0018\u0010+\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u0012H\u0002J\u0012\u0010,\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0018\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\u0004\u0018\u00010\n8BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, m1395d2 = {"Lcom/facebook/internal/ImageDownloader;", "", "()V", "CACHE_READ_QUEUE_MAX_CONCURRENT", "", "DOWNLOAD_QUEUE_MAX_CONCURRENT", "cacheReadQueue", "Lcom/facebook/internal/WorkQueue;", "downloadQueue", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "pendingRequests", "", "Lcom/facebook/internal/ImageDownloader$RequestKey;", "Lcom/facebook/internal/ImageDownloader$DownloaderContext;", "cancelRequest", "", "request", "Lcom/facebook/internal/ImageRequest;", "clearCache", "", "download", "key", "downloadAsync", "enqueueCacheRead", "allowCachedRedirects", "enqueueDownload", "enqueueRequest", "workQueue", "workItem", "Ljava/lang/Runnable;", "getPendingRequests", "", "issueResponse", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "bitmap", "Landroid/graphics/Bitmap;", "isCachedRedirect", "prioritizeRequest", "readFromCache", "removePendingRequest", "CacheReadWorkItem", "DownloadImageWorkItem", "DownloaderContext", "RequestKey", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class ImageDownloader {
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static Handler handler;
    public static final ImageDownloader INSTANCE = new ImageDownloader();
    private static final WorkQueue downloadQueue = new WorkQueue(8, null, 2, null);
    private static final WorkQueue cacheReadQueue = new WorkQueue(2, null, 2, null);
    private static final Map<RequestKey, DownloaderContext> pendingRequests = new HashMap();

    private ImageDownloader() {
    }

    private final synchronized Handler getHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }

    @JvmStatic
    public static final void downloadAsync(ImageRequest request) {
        if (request == null) {
            return;
        }
        RequestKey requestKey = new RequestKey(request.getImageUri(), request.getCallerTag());
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            DownloaderContext downloaderContext = map.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.setRequest(request);
                downloaderContext.setCancelled(false);
                WorkQueue.WorkItem workItem = downloaderContext.getWorkItem();
                if (workItem != null) {
                    workItem.moveToFront();
                    Unit unit = Unit.INSTANCE;
                }
            } else {
                INSTANCE.enqueueCacheRead(request, requestKey, request.isCachedRedirectAllowed());
                Unit unit2 = Unit.INSTANCE;
            }
        }
    }

    @JvmStatic
    public static final boolean cancelRequest(ImageRequest request) {
        boolean z;
        Intrinsics.checkNotNullParameter(request, "request");
        RequestKey requestKey = new RequestKey(request.getImageUri(), request.getCallerTag());
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            DownloaderContext downloaderContext = map.get(requestKey);
            if (downloaderContext != null) {
                WorkQueue.WorkItem workItem = downloaderContext.getWorkItem();
                z = true;
                if (workItem != null && workItem.cancel()) {
                    map.remove(requestKey);
                } else {
                    downloaderContext.setCancelled(true);
                }
            } else {
                z = false;
            }
            Unit unit = Unit.INSTANCE;
        }
        return z;
    }

    @JvmStatic
    public static final void prioritizeRequest(ImageRequest request) {
        WorkQueue.WorkItem workItem;
        Intrinsics.checkNotNullParameter(request, "request");
        RequestKey requestKey = new RequestKey(request.getImageUri(), request.getCallerTag());
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            DownloaderContext downloaderContext = map.get(requestKey);
            if (downloaderContext != null && (workItem = downloaderContext.getWorkItem()) != null) {
                workItem.moveToFront();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @JvmStatic
    public static final void clearCache() {
        ImageResponseCache imageResponseCache = ImageResponseCache.INSTANCE;
        ImageResponseCache.clearCache();
        UrlRedirectCache urlRedirectCache = UrlRedirectCache.INSTANCE;
        UrlRedirectCache.clearCache();
    }

    public final Map<RequestKey, DownloaderContext> getPendingRequests() {
        return pendingRequests;
    }

    private final void enqueueCacheRead(ImageRequest request, RequestKey key, boolean allowCachedRedirects) {
        enqueueRequest(request, key, cacheReadQueue, new CacheReadWorkItem(key, allowCachedRedirects));
    }

    private final void enqueueDownload(ImageRequest request, RequestKey key) {
        enqueueRequest(request, key, downloadQueue, new DownloadImageWorkItem(key));
    }

    private final void enqueueRequest(ImageRequest request, RequestKey key, WorkQueue workQueue, Runnable workItem) {
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            DownloaderContext downloaderContext = new DownloaderContext(request);
            map.put(key, downloaderContext);
            downloaderContext.setWorkItem(WorkQueue.addActiveWorkItem$default(workQueue, workItem, false, 2, null));
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void issueResponse(RequestKey key, final Exception error, final Bitmap bitmap, final boolean isCachedRedirect) {
        Handler handler2;
        DownloaderContext removePendingRequest = removePendingRequest(key);
        if (removePendingRequest == null || removePendingRequest.getIsCancelled()) {
            return;
        }
        final ImageRequest request = removePendingRequest.getRequest();
        final ImageRequest.Callback callback = request == null ? null : request.getCallback();
        if (callback == null || (handler2 = getHandler()) == null) {
            return;
        }
        handler2.post(new Runnable() { // from class: com.facebook.internal.ImageDownloader$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ImageDownloader.m1747issueResponse$lambda4(ImageRequest.this, error, isCachedRedirect, bitmap, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: issueResponse$lambda-4, reason: not valid java name */
    public static final void m1747issueResponse$lambda4(ImageRequest request, Exception exc, boolean z, Bitmap bitmap, ImageRequest.Callback callback) {
        Intrinsics.checkNotNullParameter(request, "$request");
        callback.onCompleted(new ImageResponse(request, exc, z, bitmap));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void readFromCache(com.facebook.internal.ImageDownloader.RequestKey r5, boolean r6) {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
            if (r6 == 0) goto L1a
            com.facebook.internal.UrlRedirectCache r6 = com.facebook.internal.UrlRedirectCache.INSTANCE
            android.net.Uri r6 = r5.getUri()
            android.net.Uri r6 = com.facebook.internal.UrlRedirectCache.getRedirectedUri(r6)
            if (r6 == 0) goto L1a
            com.facebook.internal.ImageResponseCache r2 = com.facebook.internal.ImageResponseCache.INSTANCE
            java.io.InputStream r6 = com.facebook.internal.ImageResponseCache.getCachedImageStream(r6)
            if (r6 == 0) goto L1b
            r1 = 1
            goto L1b
        L1a:
            r6 = r0
        L1b:
            if (r1 != 0) goto L27
            com.facebook.internal.ImageResponseCache r6 = com.facebook.internal.ImageResponseCache.INSTANCE
            android.net.Uri r6 = r5.getUri()
            java.io.InputStream r6 = com.facebook.internal.ImageResponseCache.getCachedImageStream(r6)
        L27:
            if (r6 == 0) goto L38
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r6)
            com.facebook.internal.Utility r3 = com.facebook.internal.Utility.INSTANCE
            java.io.Closeable r6 = (java.io.Closeable) r6
            com.facebook.internal.Utility.closeQuietly(r6)
            r4.issueResponse(r5, r0, r2, r1)
            goto L50
        L38:
            com.facebook.internal.ImageDownloader$DownloaderContext r6 = r4.removePendingRequest(r5)
            if (r6 != 0) goto L3f
            goto L43
        L3f:
            com.facebook.internal.ImageRequest r0 = r6.getRequest()
        L43:
            if (r6 == 0) goto L50
            boolean r6 = r6.getIsCancelled()
            if (r6 != 0) goto L50
            if (r0 == 0) goto L50
            r4.enqueueDownload(r0, r5)
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.ImageDownloader.readFromCache(com.facebook.internal.ImageDownloader$RequestKey, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x00f0: MOVE (r1 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:59:0x00f0 */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void download(com.facebook.internal.ImageDownloader.RequestKey r11) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.ImageDownloader.download(com.facebook.internal.ImageDownloader$RequestKey):void");
    }

    private final DownloaderContext removePendingRequest(RequestKey key) {
        DownloaderContext remove;
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            remove = map.remove(key);
        }
        return remove;
    }

    /* compiled from: ImageDownloader.kt */
    @Metadata(m1394d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u001a\u0010\u0004\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0014"}, m1395d2 = {"Lcom/facebook/internal/ImageDownloader$RequestKey;", "", ShareConstants.MEDIA_URI, "Landroid/net/Uri;", ViewHierarchyConstants.TAG_KEY, "(Landroid/net/Uri;Ljava/lang/Object;)V", "getTag", "()Ljava/lang/Object;", "setTag", "(Ljava/lang/Object;)V", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "equals", "", "o", "hashCode", "", "Companion", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final class RequestKey {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        private Object tag;
        private Uri uri;

        public RequestKey(Uri uri, Object tag) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            Intrinsics.checkNotNullParameter(tag, "tag");
            this.uri = uri;
            this.tag = tag;
        }

        public final Object getTag() {
            return this.tag;
        }

        public final Uri getUri() {
            return this.uri;
        }

        public final void setTag(Object obj) {
            Intrinsics.checkNotNullParameter(obj, "<set-?>");
            this.tag = obj;
        }

        public final void setUri(Uri uri) {
            Intrinsics.checkNotNullParameter(uri, "<set-?>");
            this.uri = uri;
        }

        public int hashCode() {
            return ((1073 + this.uri.hashCode()) * 37) + this.tag.hashCode();
        }

        public boolean equals(Object o) {
            if (o == null || !(o instanceof RequestKey)) {
                return false;
            }
            RequestKey requestKey = (RequestKey) o;
            return requestKey.uri == this.uri && requestKey.tag == this.tag;
        }
    }

    /* compiled from: ImageDownloader.kt */
    @Metadata(m1394d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0004R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, m1395d2 = {"Lcom/facebook/internal/ImageDownloader$DownloaderContext;", "", "request", "Lcom/facebook/internal/ImageRequest;", "(Lcom/facebook/internal/ImageRequest;)V", "isCancelled", "", "()Z", "setCancelled", "(Z)V", "getRequest", "()Lcom/facebook/internal/ImageRequest;", "setRequest", "workItem", "Lcom/facebook/internal/WorkQueue$WorkItem;", "getWorkItem", "()Lcom/facebook/internal/WorkQueue$WorkItem;", "setWorkItem", "(Lcom/facebook/internal/WorkQueue$WorkItem;)V", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final class DownloaderContext {
        private boolean isCancelled;
        private ImageRequest request;
        private WorkQueue.WorkItem workItem;

        public DownloaderContext(ImageRequest request) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.request = request;
        }

        public final ImageRequest getRequest() {
            return this.request;
        }

        public final void setRequest(ImageRequest imageRequest) {
            Intrinsics.checkNotNullParameter(imageRequest, "<set-?>");
            this.request = imageRequest;
        }

        public final WorkQueue.WorkItem getWorkItem() {
            return this.workItem;
        }

        public final void setWorkItem(WorkQueue.WorkItem workItem) {
            this.workItem = workItem;
        }

        /* renamed from: isCancelled, reason: from getter */
        public final boolean getIsCancelled() {
            return this.isCancelled;
        }

        public final void setCancelled(boolean z) {
            this.isCancelled = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ImageDownloader.kt */
    @Metadata(m1394d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, m1395d2 = {"Lcom/facebook/internal/ImageDownloader$CacheReadWorkItem;", "Ljava/lang/Runnable;", "key", "Lcom/facebook/internal/ImageDownloader$RequestKey;", "allowCachedRedirects", "", "(Lcom/facebook/internal/ImageDownloader$RequestKey;Z)V", "run", "", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final class CacheReadWorkItem implements Runnable {
        private final boolean allowCachedRedirects;
        private final RequestKey key;

        public CacheReadWorkItem(RequestKey key, boolean z) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.key = key;
            this.allowCachedRedirects = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CrashShieldHandler.isObjectCrashing(this)) {
                return;
            }
            try {
                ImageDownloader.INSTANCE.readFromCache(this.key, this.allowCachedRedirects);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ImageDownloader.kt */
    @Metadata(m1394d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m1395d2 = {"Lcom/facebook/internal/ImageDownloader$DownloadImageWorkItem;", "Ljava/lang/Runnable;", "key", "Lcom/facebook/internal/ImageDownloader$RequestKey;", "(Lcom/facebook/internal/ImageDownloader$RequestKey;)V", "run", "", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final class DownloadImageWorkItem implements Runnable {
        private final RequestKey key;

        public DownloadImageWorkItem(RequestKey key) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.key = key;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CrashShieldHandler.isObjectCrashing(this)) {
                return;
            }
            try {
                ImageDownloader.INSTANCE.download(this.key);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }
}
