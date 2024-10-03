package com.facebook.appevents;

import android.content.Context;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AccessTokenAppIdPair;
import com.facebook.appevents.AppEvent;
import com.facebook.internal.Utility;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppEventDiskStore.kt */
@Metadata(m1394d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0007J\u0017\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\bH\u0001¢\u0006\u0002\b\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m1395d2 = {"Lcom/facebook/appevents/AppEventDiskStore;", "", "()V", "PERSISTED_EVENTS_FILENAME", "", "TAG", "kotlin.jvm.PlatformType", "readAndClearStore", "Lcom/facebook/appevents/PersistedEvents;", "saveEventsToDisk", "", "eventsToPersist", "saveEventsToDisk$facebook_core_release", "MovedClassObjectInputStream", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AppEventDiskStore {
    private static final String PERSISTED_EVENTS_FILENAME = "AppEventsLogger.persistedevents";
    public static final AppEventDiskStore INSTANCE = new AppEventDiskStore();
    private static final String TAG = AppEventDiskStore.class.getName();

    private AppEventDiskStore() {
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc A[Catch: all -> 0x00c3, TRY_LEAVE, TryCatch #3 {, blocks: (B:4:0x0003, B:12:0x0030, B:14:0x0037, B:17:0x00bc, B:23:0x0042, B:36:0x0067, B:38:0x006e, B:41:0x0079, B:33:0x007f, B:44:0x0084, B:46:0x008b, B:47:0x009f, B:50:0x0096, B:27:0x00a1, B:29:0x00a8, B:32:0x00b3), top: B:3:0x0003, inners: #5, #8, #9, #10 }] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final synchronized com.facebook.appevents.PersistedEvents readAndClearStore() {
        /*
            java.lang.Class<com.facebook.appevents.AppEventDiskStore> r0 = com.facebook.appevents.AppEventDiskStore.class
            monitor-enter(r0)
            com.facebook.appevents.internal.AppEventUtility r1 = com.facebook.appevents.internal.AppEventUtility.INSTANCE     // Catch: java.lang.Throwable -> Lc3
            com.facebook.appevents.internal.AppEventUtility.assertIsNotMainThread()     // Catch: java.lang.Throwable -> Lc3
            com.facebook.FacebookSdk r1 = com.facebook.FacebookSdk.INSTANCE     // Catch: java.lang.Throwable -> Lc3
            android.content.Context r1 = com.facebook.FacebookSdk.getApplicationContext()     // Catch: java.lang.Throwable -> Lc3
            r2 = 0
            java.lang.String r3 = "AppEventsLogger.persistedevents"
            java.io.FileInputStream r3 = r1.openFileInput(r3)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5c java.io.FileNotFoundException -> La0
            java.lang.String r4 = "context.openFileInput(PERSISTED_EVENTS_FILENAME)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5c java.io.FileNotFoundException -> La0
            java.io.InputStream r3 = (java.io.InputStream) r3     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5c java.io.FileNotFoundException -> La0
            com.facebook.appevents.AppEventDiskStore$MovedClassObjectInputStream r4 = new com.facebook.appevents.AppEventDiskStore$MovedClassObjectInputStream     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5c java.io.FileNotFoundException -> La0
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5c java.io.FileNotFoundException -> La0
            r5.<init>(r3)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5c java.io.FileNotFoundException -> La0
            java.io.InputStream r5 = (java.io.InputStream) r5     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5c java.io.FileNotFoundException -> La0
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5c java.io.FileNotFoundException -> La0
            java.lang.Object r3 = r4.readObject()     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L83 java.io.FileNotFoundException -> La1
            if (r3 == 0) goto L4e
            com.facebook.appevents.PersistedEvents r3 = (com.facebook.appevents.PersistedEvents) r3     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L83 java.io.FileNotFoundException -> La1
            com.facebook.internal.Utility r2 = com.facebook.internal.Utility.INSTANCE     // Catch: java.lang.Throwable -> Lc3
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch: java.lang.Throwable -> Lc3
            com.facebook.internal.Utility.closeQuietly(r4)     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r2 = "AppEventsLogger.persistedevents"
            java.io.File r1 = r1.getFileStreamPath(r2)     // Catch: java.lang.Exception -> L41 java.lang.Throwable -> Lc3
            r1.delete()     // Catch: java.lang.Exception -> L41 java.lang.Throwable -> Lc3
            goto L4b
        L41:
            r1 = move-exception
            java.lang.String r2 = com.facebook.appevents.AppEventDiskStore.TAG     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r4 = "Got unexpected exception when removing events file: "
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch: java.lang.Throwable -> Lc3
            android.util.Log.w(r2, r4, r1)     // Catch: java.lang.Throwable -> Lc3
        L4b:
            r2 = r3
            goto Lba
        L4e:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L83 java.io.FileNotFoundException -> La1
            java.lang.String r5 = "null cannot be cast to non-null type com.facebook.appevents.PersistedEvents"
            r3.<init>(r5)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L83 java.io.FileNotFoundException -> La1
            throw r3     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L83 java.io.FileNotFoundException -> La1
        L56:
            r3 = move-exception
            goto L5e
        L58:
            r3 = move-exception
            r4 = r2
            r2 = r3
            goto L84
        L5c:
            r3 = move-exception
            r4 = r2
        L5e:
            java.lang.String r5 = com.facebook.appevents.AppEventDiskStore.TAG     // Catch: java.lang.Throwable -> L83
            java.lang.String r6 = "Got unexpected exception while reading events: "
            java.lang.Throwable r3 = (java.lang.Throwable) r3     // Catch: java.lang.Throwable -> L83
            android.util.Log.w(r5, r6, r3)     // Catch: java.lang.Throwable -> L83
            com.facebook.internal.Utility r3 = com.facebook.internal.Utility.INSTANCE     // Catch: java.lang.Throwable -> Lc3
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch: java.lang.Throwable -> Lc3
            com.facebook.internal.Utility.closeQuietly(r4)     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r3 = "AppEventsLogger.persistedevents"
            java.io.File r1 = r1.getFileStreamPath(r3)     // Catch: java.lang.Exception -> L78 java.lang.Throwable -> Lc3
            r1.delete()     // Catch: java.lang.Exception -> L78 java.lang.Throwable -> Lc3
            goto Lba
        L78:
            r1 = move-exception
            java.lang.String r3 = com.facebook.appevents.AppEventDiskStore.TAG     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r4 = "Got unexpected exception when removing events file: "
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch: java.lang.Throwable -> Lc3
        L7f:
            android.util.Log.w(r3, r4, r1)     // Catch: java.lang.Throwable -> Lc3
            goto Lba
        L83:
            r2 = move-exception
        L84:
            com.facebook.internal.Utility r3 = com.facebook.internal.Utility.INSTANCE     // Catch: java.lang.Throwable -> Lc3
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch: java.lang.Throwable -> Lc3
            com.facebook.internal.Utility.closeQuietly(r4)     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r3 = "AppEventsLogger.persistedevents"
            java.io.File r1 = r1.getFileStreamPath(r3)     // Catch: java.lang.Exception -> L95 java.lang.Throwable -> Lc3
            r1.delete()     // Catch: java.lang.Exception -> L95 java.lang.Throwable -> Lc3
            goto L9f
        L95:
            r1 = move-exception
            java.lang.String r3 = com.facebook.appevents.AppEventDiskStore.TAG     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r4 = "Got unexpected exception when removing events file: "
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch: java.lang.Throwable -> Lc3
            android.util.Log.w(r3, r4, r1)     // Catch: java.lang.Throwable -> Lc3
        L9f:
            throw r2     // Catch: java.lang.Throwable -> Lc3
        La0:
            r4 = r2
        La1:
            com.facebook.internal.Utility r3 = com.facebook.internal.Utility.INSTANCE     // Catch: java.lang.Throwable -> Lc3
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch: java.lang.Throwable -> Lc3
            com.facebook.internal.Utility.closeQuietly(r4)     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r3 = "AppEventsLogger.persistedevents"
            java.io.File r1 = r1.getFileStreamPath(r3)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Lc3
            r1.delete()     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Lc3
            goto Lba
        Lb2:
            r1 = move-exception
            java.lang.String r3 = com.facebook.appevents.AppEventDiskStore.TAG     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r4 = "Got unexpected exception when removing events file: "
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch: java.lang.Throwable -> Lc3
            goto L7f
        Lba:
            if (r2 != 0) goto Lc1
            com.facebook.appevents.PersistedEvents r2 = new com.facebook.appevents.PersistedEvents     // Catch: java.lang.Throwable -> Lc3
            r2.<init>()     // Catch: java.lang.Throwable -> Lc3
        Lc1:
            monitor-exit(r0)
            return r2
        Lc3:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.AppEventDiskStore.readAndClearStore():com.facebook.appevents.PersistedEvents");
    }

    @JvmStatic
    public static final void saveEventsToDisk$facebook_core_release(PersistedEvents eventsToPersist) {
        ObjectOutputStream objectOutputStream;
        FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
        Context applicationContext = FacebookSdk.getApplicationContext();
        ObjectOutputStream objectOutputStream2 = null;
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(applicationContext.openFileOutput(PERSISTED_EVENTS_FILENAME, 0)));
        } catch (Throwable th) {
            th = th;
        }
        try {
            objectOutputStream.writeObject(eventsToPersist);
            Utility utility = Utility.INSTANCE;
            Utility.closeQuietly(objectOutputStream);
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            try {
                Log.w(TAG, "Got unexpected exception while persisting events: ", th);
                try {
                    applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                } catch (Exception unused) {
                }
            } finally {
                Utility utility2 = Utility.INSTANCE;
                Utility.closeQuietly(objectOutputStream2);
            }
        }
    }

    /* compiled from: AppEventDiskStore.kt */
    @Metadata(m1394d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0014¨\u0006\b"}, m1395d2 = {"Lcom/facebook/appevents/AppEventDiskStore$MovedClassObjectInputStream;", "Ljava/io/ObjectInputStream;", "inputStream", "Ljava/io/InputStream;", "(Ljava/io/InputStream;)V", "readClassDescriptor", "Ljava/io/ObjectStreamClass;", "Companion", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    private static final class MovedClassObjectInputStream extends ObjectInputStream {
        private static final String ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1";
        private static final String APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV2";

        public MovedClassObjectInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.ObjectInputStream
        protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
            ObjectStreamClass resultClassDescriptor = super.readClassDescriptor();
            if (Intrinsics.areEqual(resultClassDescriptor.getName(), ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME)) {
                resultClassDescriptor = ObjectStreamClass.lookup(AccessTokenAppIdPair.SerializationProxyV1.class);
            } else if (Intrinsics.areEqual(resultClassDescriptor.getName(), APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME)) {
                resultClassDescriptor = ObjectStreamClass.lookup(AppEvent.SerializationProxyV2.class);
            }
            Intrinsics.checkNotNullExpressionValue(resultClassDescriptor, "resultClassDescriptor");
            return resultClassDescriptor;
        }
    }
}
