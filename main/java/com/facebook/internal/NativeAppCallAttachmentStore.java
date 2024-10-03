package com.facebook.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import com.facebook.FacebookContentProvider;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.internal.ShareInternalUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: NativeAppCallAttachmentStore.kt */
@Metadata(m1394d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001&B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0007J\b\u0010\u000e\u001a\u00020\nH\u0007J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\n\u0010\u0017\u001a\u0004\u0018\u00010\bH\u0007J$\u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\n\u0010\u001c\u001a\u0004\u0018\u00010\bH\u0007J\u001a\u0010\u001d\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001bH\u0007J\u001e\u0010\u001f\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004H\u0007J\u0018\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\bH\u0002J \u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, m1395d2 = {"Lcom/facebook/internal/NativeAppCallAttachmentStore;", "", "()V", "ATTACHMENTS_DIR_NAME", "", "TAG", "kotlin.jvm.PlatformType", "attachmentsDirectory", "Ljava/io/File;", "addAttachments", "", "attachments", "", "Lcom/facebook/internal/NativeAppCallAttachmentStore$Attachment;", "cleanupAllAttachments", "cleanupAttachmentsForCall", "callId", "Ljava/util/UUID;", "createAttachment", "attachmentBitmap", "Landroid/graphics/Bitmap;", "attachmentUri", "Landroid/net/Uri;", "ensureAttachmentsDirectoryExists", "getAttachmentFile", "attachmentName", "createDirs", "", "getAttachmentsDirectory", "getAttachmentsDirectoryForCall", "create", "openAttachment", "processAttachmentBitmap", "bitmap", "outputFile", "processAttachmentFile", "imageUri", "isContentUri", "Attachment", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class NativeAppCallAttachmentStore {
    public static final String ATTACHMENTS_DIR_NAME = "com.facebook.NativeAppCallAttachmentStore.files";
    public static final NativeAppCallAttachmentStore INSTANCE = new NativeAppCallAttachmentStore();
    private static final String TAG = NativeAppCallAttachmentStore.class.getName();
    private static File attachmentsDirectory;

    private NativeAppCallAttachmentStore() {
    }

    @JvmStatic
    public static final Attachment createAttachment(UUID callId, Bitmap attachmentBitmap) {
        Intrinsics.checkNotNullParameter(callId, "callId");
        Intrinsics.checkNotNullParameter(attachmentBitmap, "attachmentBitmap");
        return new Attachment(callId, attachmentBitmap, null);
    }

    @JvmStatic
    public static final Attachment createAttachment(UUID callId, Uri attachmentUri) {
        Intrinsics.checkNotNullParameter(callId, "callId");
        Intrinsics.checkNotNullParameter(attachmentUri, "attachmentUri");
        return new Attachment(callId, null, attachmentUri);
    }

    private final void processAttachmentBitmap(Bitmap bitmap, File outputFile) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } finally {
            Utility utility = Utility.INSTANCE;
            Utility.closeQuietly(fileOutputStream);
        }
    }

    private final void processAttachmentFile(Uri imageUri, boolean isContentUri, File outputFile) throws IOException {
        FileInputStream openInputStream;
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        try {
            if (!isContentUri) {
                openInputStream = new FileInputStream(imageUri.getPath());
            } else {
                FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
                openInputStream = FacebookSdk.getApplicationContext().getContentResolver().openInputStream(imageUri);
            }
            Utility utility = Utility.INSTANCE;
            Utility.copyAndCloseInputStream(openInputStream, fileOutputStream);
        } finally {
            Utility utility2 = Utility.INSTANCE;
            Utility.closeQuietly(fileOutputStream);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x007d  */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void addAttachments(java.util.Collection<com.facebook.internal.NativeAppCallAttachmentStore.Attachment> r6) throws com.facebook.FacebookException {
        /*
            if (r6 == 0) goto L92
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto La
            goto L92
        La:
            java.io.File r0 = com.facebook.internal.NativeAppCallAttachmentStore.attachmentsDirectory
            if (r0 != 0) goto L11
            cleanupAllAttachments()
        L11:
            ensureAttachmentsDirectoryExists()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r0 = (java.util.List) r0
            java.util.Iterator r6 = r6.iterator()     // Catch: java.io.IOException -> L67
        L1f:
            boolean r1 = r6.hasNext()     // Catch: java.io.IOException -> L67
            if (r1 == 0) goto L66
            java.lang.Object r1 = r6.next()     // Catch: java.io.IOException -> L67
            com.facebook.internal.NativeAppCallAttachmentStore$Attachment r1 = (com.facebook.internal.NativeAppCallAttachmentStore.Attachment) r1     // Catch: java.io.IOException -> L67
            boolean r2 = r1.getShouldCreateFile()     // Catch: java.io.IOException -> L67
            if (r2 != 0) goto L32
            goto L1f
        L32:
            com.facebook.internal.NativeAppCallAttachmentStore r2 = com.facebook.internal.NativeAppCallAttachmentStore.INSTANCE     // Catch: java.io.IOException -> L67
            java.util.UUID r3 = r1.getCallId()     // Catch: java.io.IOException -> L67
            java.lang.String r4 = r1.getAttachmentName()     // Catch: java.io.IOException -> L67
            r5 = 1
            java.io.File r3 = getAttachmentFile(r3, r4, r5)     // Catch: java.io.IOException -> L67
            if (r3 == 0) goto L1f
            r0.add(r3)     // Catch: java.io.IOException -> L67
            android.graphics.Bitmap r4 = r1.getBitmap()     // Catch: java.io.IOException -> L67
            if (r4 == 0) goto L54
            android.graphics.Bitmap r1 = r1.getBitmap()     // Catch: java.io.IOException -> L67
            r2.processAttachmentBitmap(r1, r3)     // Catch: java.io.IOException -> L67
            goto L1f
        L54:
            android.net.Uri r4 = r1.getOriginalUri()     // Catch: java.io.IOException -> L67
            if (r4 == 0) goto L1f
            android.net.Uri r4 = r1.getOriginalUri()     // Catch: java.io.IOException -> L67
            boolean r1 = r1.getIsContentUri()     // Catch: java.io.IOException -> L67
            r2.processAttachmentFile(r4, r1, r3)     // Catch: java.io.IOException -> L67
            goto L1f
        L66:
            return
        L67:
            r6 = move-exception
            java.lang.String r1 = com.facebook.internal.NativeAppCallAttachmentStore.TAG
            java.lang.String r2 = "Got unexpected exception:"
            java.lang.String r2 = kotlin.jvm.internal.Intrinsics.stringPlus(r2, r6)
            android.util.Log.e(r1, r2)
            java.util.Iterator r0 = r0.iterator()
        L77:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L8a
            java.lang.Object r1 = r0.next()
            java.io.File r1 = (java.io.File) r1
            if (r1 != 0) goto L86
            goto L77
        L86:
            r1.delete()     // Catch: java.lang.Exception -> L77
            goto L77
        L8a:
            com.facebook.FacebookException r0 = new com.facebook.FacebookException
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            r0.<init>(r6)
            throw r0
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.NativeAppCallAttachmentStore.addAttachments(java.util.Collection):void");
    }

    @JvmStatic
    public static final void cleanupAttachmentsForCall(UUID callId) {
        Intrinsics.checkNotNullParameter(callId, "callId");
        File attachmentsDirectoryForCall = getAttachmentsDirectoryForCall(callId, false);
        if (attachmentsDirectoryForCall == null) {
            return;
        }
        FilesKt.deleteRecursively(attachmentsDirectoryForCall);
    }

    @JvmStatic
    public static final File openAttachment(UUID callId, String attachmentName) throws FileNotFoundException {
        Utility utility = Utility.INSTANCE;
        if (Utility.isNullOrEmpty(attachmentName) || callId == null) {
            throw new FileNotFoundException();
        }
        try {
            return getAttachmentFile(callId, attachmentName, false);
        } catch (IOException unused) {
            throw new FileNotFoundException();
        }
    }

    @JvmStatic
    public static final synchronized File getAttachmentsDirectory() {
        File file;
        synchronized (NativeAppCallAttachmentStore.class) {
            if (attachmentsDirectory == null) {
                FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
                attachmentsDirectory = new File(FacebookSdk.getApplicationContext().getCacheDir(), ATTACHMENTS_DIR_NAME);
            }
            file = attachmentsDirectory;
        }
        return file;
    }

    @JvmStatic
    public static final File ensureAttachmentsDirectoryExists() {
        File attachmentsDirectory2 = getAttachmentsDirectory();
        if (attachmentsDirectory2 != null) {
            attachmentsDirectory2.mkdirs();
        }
        return attachmentsDirectory2;
    }

    @JvmStatic
    public static final File getAttachmentsDirectoryForCall(UUID callId, boolean create) {
        Intrinsics.checkNotNullParameter(callId, "callId");
        if (attachmentsDirectory == null) {
            return null;
        }
        File file = new File(attachmentsDirectory, callId.toString());
        if (create && !file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    @JvmStatic
    public static final File getAttachmentFile(UUID callId, String attachmentName, boolean createDirs) throws IOException {
        Intrinsics.checkNotNullParameter(callId, "callId");
        File attachmentsDirectoryForCall = getAttachmentsDirectoryForCall(callId, createDirs);
        if (attachmentsDirectoryForCall == null) {
            return null;
        }
        try {
            return new File(attachmentsDirectoryForCall, URLEncoder.encode(attachmentName, "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    @JvmStatic
    public static final void cleanupAllAttachments() {
        File attachmentsDirectory2 = getAttachmentsDirectory();
        if (attachmentsDirectory2 == null) {
            return;
        }
        FilesKt.deleteRecursively(attachmentsDirectory2);
    }

    /* compiled from: NativeAppCallAttachmentStore.kt */
    @Metadata(m1394d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0015\"\u0004\b\u001c\u0010\u0017¨\u0006\u001d"}, m1395d2 = {"Lcom/facebook/internal/NativeAppCallAttachmentStore$Attachment;", "", "callId", "Ljava/util/UUID;", "bitmap", "Landroid/graphics/Bitmap;", "originalUri", "Landroid/net/Uri;", "(Ljava/util/UUID;Landroid/graphics/Bitmap;Landroid/net/Uri;)V", "attachmentName", "", "getAttachmentName", "()Ljava/lang/String;", "attachmentUrl", "getAttachmentUrl", "getBitmap", "()Landroid/graphics/Bitmap;", "getCallId", "()Ljava/util/UUID;", "isContentUri", "", "()Z", "setContentUri", "(Z)V", "getOriginalUri", "()Landroid/net/Uri;", "shouldCreateFile", "getShouldCreateFile", "setShouldCreateFile", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final class Attachment {
        private final String attachmentName;
        private final String attachmentUrl;
        private final Bitmap bitmap;
        private final UUID callId;
        private boolean isContentUri;
        private final Uri originalUri;
        private boolean shouldCreateFile;

        public Attachment(UUID callId, Bitmap bitmap, Uri uri) {
            String attachmentUrl;
            Intrinsics.checkNotNullParameter(callId, "callId");
            this.callId = callId;
            this.bitmap = bitmap;
            this.originalUri = uri;
            if (uri != null) {
                String scheme = uri.getScheme();
                if (StringsKt.equals(FirebaseAnalytics.Param.CONTENT, scheme, true)) {
                    this.isContentUri = true;
                    String authority = uri.getAuthority();
                    this.shouldCreateFile = (authority == null || StringsKt.startsWith$default(authority, ShareConstants.WEB_DIALOG_PARAM_MEDIA, false, 2, (Object) null)) ? false : true;
                } else if (StringsKt.equals(ShareInternalUtility.STAGING_PARAM, uri.getScheme(), true)) {
                    this.shouldCreateFile = true;
                } else {
                    Utility utility = Utility.INSTANCE;
                    if (!Utility.isWebUri(uri)) {
                        throw new FacebookException(Intrinsics.stringPlus("Unsupported scheme for media Uri : ", scheme));
                    }
                }
            } else if (bitmap != null) {
                this.shouldCreateFile = true;
            } else {
                throw new FacebookException("Cannot share media without a bitmap or Uri set");
            }
            String uuid = this.shouldCreateFile ? UUID.randomUUID().toString() : null;
            this.attachmentName = uuid;
            if (!this.shouldCreateFile) {
                attachmentUrl = String.valueOf(uri);
            } else {
                FacebookContentProvider.Companion companion = FacebookContentProvider.INSTANCE;
                FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
                attachmentUrl = companion.getAttachmentUrl(FacebookSdk.getApplicationId(), callId, uuid);
            }
            this.attachmentUrl = attachmentUrl;
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final UUID getCallId() {
            return this.callId;
        }

        public final Uri getOriginalUri() {
            return this.originalUri;
        }

        public final String getAttachmentUrl() {
            return this.attachmentUrl;
        }

        public final String getAttachmentName() {
            return this.attachmentName;
        }

        /* renamed from: isContentUri, reason: from getter */
        public final boolean getIsContentUri() {
            return this.isContentUri;
        }

        public final void setContentUri(boolean z) {
            this.isContentUri = z;
        }

        public final boolean getShouldCreateFile() {
            return this.shouldCreateFile;
        }

        public final void setShouldCreateFile(boolean z) {
            this.shouldCreateFile = z;
        }
    }
}
