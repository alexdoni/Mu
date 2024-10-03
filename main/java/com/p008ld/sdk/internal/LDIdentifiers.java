package com.p008ld.sdk.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.internal.LDIdentifiers;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzm;
import com.p008ld.sdk.zzb.zzb;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDIdentifiers.kt */
/* loaded from: classes2.dex */
public final class LDIdentifiers {
    public static String cachedGUID;
    public static LDIdentifiers cachedIdentifiers;
    private String advertiserIdField;
    private boolean isTrackingLimited;
    public static final Companion Companion = new Companion(null);
    private static final Object mLock = new Object();

    @JvmStatic
    public static final LDIdentifiers cacheAndReturnIdentifiers() {
        return Companion.cacheAndReturnIdentifiers();
    }

    @JvmStatic
    public static final void fetchAdvertId() {
        Companion.fetchAdvertId();
    }

    @JvmStatic
    public static final String getAdvertId() {
        return Companion.getAdvertId();
    }

    @JvmStatic
    public static final String getAppGUID() {
        return Companion.getAppGUID();
    }

    public final boolean isTrackingLimited() {
        return this.isTrackingLimited;
    }

    public final String getAdvertiserId() {
        return this.advertiserIdField;
    }

    /* compiled from: LDIdentifiers.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0029 A[Catch: all -> 0x0056, TryCatch #0 {, blocks: (B:6:0x0009, B:8:0x000d, B:10:0x001d, B:15:0x0029, B:17:0x0052), top: B:5:0x0009 }] */
        @kotlin.jvm.JvmStatic
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String getAppGUID() {
            /*
                r8 = this;
                java.lang.String r0 = com.p008ld.sdk.internal.LDIdentifiers.cachedGUID
                if (r0 != 0) goto L59
                java.lang.Object r0 = com.p008ld.sdk.internal.LDIdentifiers.access$getMLock$cp()
                monitor-enter(r0)
                java.lang.String r1 = com.p008ld.sdk.internal.LDIdentifiers.cachedGUID     // Catch: java.lang.Throwable -> L56
                if (r1 != 0) goto L52
                com.ld.sdk.internal.LDIdentifiers$Companion r1 = com.p008ld.sdk.internal.LDIdentifiers.Companion     // Catch: java.lang.Throwable -> L56
                java.lang.String r1 = "KEY_LD_GUID"
                java.lang.String r1 = com.p008ld.sdk.util.zzm.zzb(r1)     // Catch: java.lang.Throwable -> L56
                com.p008ld.sdk.internal.LDIdentifiers.cachedGUID = r1     // Catch: java.lang.Throwable -> L56
                java.lang.String r1 = com.p008ld.sdk.internal.LDIdentifiers.cachedGUID     // Catch: java.lang.Throwable -> L56
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch: java.lang.Throwable -> L56
                if (r1 == 0) goto L26
                int r1 = r1.length()     // Catch: java.lang.Throwable -> L56
                if (r1 != 0) goto L24
                goto L26
            L24:
                r1 = 0
                goto L27
            L26:
                r1 = 1
            L27:
                if (r1 == 0) goto L52
                com.ld.sdk.internal.LDIdentifiers$Companion r1 = com.p008ld.sdk.internal.LDIdentifiers.Companion     // Catch: java.lang.Throwable -> L56
                java.util.UUID r1 = java.util.UUID.randomUUID()     // Catch: java.lang.Throwable -> L56
                java.lang.String r2 = r1.toString()     // Catch: java.lang.Throwable -> L56
                java.lang.String r1 = "randomUUID().toString()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)     // Catch: java.lang.Throwable -> L56
                java.lang.String r3 = "-"
                java.lang.String r4 = ""
                r5 = 0
                r6 = 4
                r7 = 0
                java.lang.String r1 = kotlin.text.StringsKt.replace$default(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L56
                com.p008ld.sdk.internal.LDIdentifiers.cachedGUID = r1     // Catch: java.lang.Throwable -> L56
                com.ld.sdk.zzb.zzb$zza r1 = com.p008ld.sdk.zzb.zzb.zza     // Catch: java.lang.Throwable -> L56
                com.ld.sdk.zzb.zzb r1 = r1.zza()     // Catch: java.lang.Throwable -> L56
                java.lang.String r2 = "KEY_LD_GUID"
                java.lang.String r3 = com.p008ld.sdk.internal.LDIdentifiers.cachedGUID     // Catch: java.lang.Throwable -> L56
                r1.zza(r2, r3)     // Catch: java.lang.Throwable -> L56
            L52:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L56
                monitor-exit(r0)
                goto L59
            L56:
                r1 = move-exception
                monitor-exit(r0)
                throw r1
            L59:
                java.lang.String r0 = com.p008ld.sdk.internal.LDIdentifiers.cachedGUID
                if (r0 == 0) goto L5e
                return r0
            L5e:
                java.lang.String r0 = "Required value was null."
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ld.sdk.internal.LDIdentifiers.Companion.getAppGUID():java.lang.String");
        }

        @JvmStatic
        public final void fetchAdvertId() {
            String zzb = zzm.zzb("KEY_LD_ADVERT_ID");
            if (LDIdentifiers.cachedIdentifiers != null) {
                String str = zzb;
                if (!(str == null || str.length() == 0)) {
                    return;
                }
            }
            LDSdk.getExecutor().execute(new Runnable() { // from class: com.ld.sdk.internal.LDIdentifiers$Companion$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LDIdentifiers.Companion.fetchAdvertId$lambda$1();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void fetchAdvertId$lambda$1() {
            LDIdentifiers.Companion.cacheAndReturnIdentifiers();
        }

        @JvmStatic
        public final LDIdentifiers cacheAndReturnIdentifiers() {
            LDIdentifiers androidId = getAndroidId(LDUtil.getApp());
            String advertiserId = androidId.getAdvertiserId();
            if (!(advertiserId == null || advertiserId.length() == 0)) {
                zzb zza = zzb.zza.zza();
                String advertiserId2 = androidId.getAdvertiserId();
                Intrinsics.checkNotNull(advertiserId2);
                zza.zza("KEY_LD_ADVERT_ID", advertiserId2);
            }
            LDIdentifiers.cachedIdentifiers = androidId;
            return androidId;
        }

        @JvmStatic
        public final String getAdvertId() {
            LDIdentifiers lDIdentifiers = LDIdentifiers.cachedIdentifiers;
            if (lDIdentifiers != null) {
                String advertiserId = lDIdentifiers.getAdvertiserId();
                if (!(advertiserId == null || advertiserId.length() == 0)) {
                    String advertiserId2 = lDIdentifiers.getAdvertiserId();
                    Intrinsics.checkNotNull(advertiserId2);
                    return advertiserId2;
                }
            }
            String zzb = zzm.zzb("KEY_LD_ADVERT_ID");
            if (zzb.length() == 0) {
                zzb = LDIdentifiers.Companion.getAppGUID();
            }
            return zzb;
        }

        private final LDIdentifiers getAndroidId(Context context) {
            LDIdentifiers androidIdViaReflection = getAndroidIdViaReflection(context);
            if (androidIdViaReflection != null) {
                return androidIdViaReflection;
            }
            LDIdentifiers androidIdViaService = getAndroidIdViaService(context);
            return androidIdViaService == null ? new LDIdentifiers() : androidIdViaService;
        }

        private final LDIdentifiers getAndroidIdViaReflection(Context context) {
            Object invokeMethodQuietly;
            try {
                if (!LDUtil.isGooglePlayServicesAvailable(context)) {
                    return null;
                }
                Method methodQuietly = LDUtil.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class});
                if (methodQuietly == null || (invokeMethodQuietly = LDUtil.invokeMethodQuietly(null, methodQuietly, context)) == null) {
                    return null;
                }
                Method methodQuietly2 = LDUtil.getMethodQuietly(invokeMethodQuietly.getClass(), "getId", (Class<?>[]) new Class[0]);
                Method methodQuietly3 = LDUtil.getMethodQuietly(invokeMethodQuietly.getClass(), "isLimitAdTrackingEnabled", (Class<?>[]) new Class[0]);
                if (methodQuietly2 != null && methodQuietly3 != null) {
                    LDIdentifiers lDIdentifiers = new LDIdentifiers();
                    lDIdentifiers.advertiserIdField = (String) LDUtil.invokeMethodQuietly(invokeMethodQuietly, methodQuietly2, new Object[0]);
                    Boolean bool = (Boolean) LDUtil.invokeMethodQuietly(invokeMethodQuietly, methodQuietly3, new Object[0]);
                    lDIdentifiers.isTrackingLimited = bool != null ? bool.booleanValue() : false;
                    return lDIdentifiers;
                }
                return null;
            } catch (Exception e) {
                LDLog.m573e("getAndroidIdViaReflection error= " + e);
                return null;
            }
        }

        private final LDIdentifiers getAndroidIdViaService(Context context) {
            if (!LDUtil.isGooglePlayServicesAvailable(context)) {
                return null;
            }
            GoogleAdServiceConnection googleAdServiceConnection = new GoogleAdServiceConnection();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                if (context.bindService(intent, googleAdServiceConnection, 1)) {
                    try {
                        GoogleAdInfo googleAdInfo = new GoogleAdInfo(googleAdServiceConnection.getBinder());
                        LDIdentifiers lDIdentifiers = new LDIdentifiers();
                        lDIdentifiers.advertiserIdField = googleAdInfo.getAdvertiserId();
                        lDIdentifiers.isTrackingLimited = googleAdInfo.isTrackingLimited();
                        return lDIdentifiers;
                    } catch (Exception e) {
                        LDLog.m573e("getAndroidIdViaService error= " + e);
                    } finally {
                        context.unbindService(googleAdServiceConnection);
                    }
                }
            } catch (SecurityException unused) {
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LDIdentifiers.kt */
    /* loaded from: classes2.dex */
    public static final class GoogleAdServiceConnection implements ServiceConnection {
        private final AtomicBoolean consumed = new AtomicBoolean(false);
        private final BlockingQueue<IBinder> queue = new LinkedBlockingDeque();

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                try {
                    this.queue.put(iBinder);
                } catch (InterruptedException unused) {
                }
            }
        }

        public final IBinder getBinder() throws InterruptedException {
            if (!(!this.consumed.compareAndSet(true, true))) {
                throw new IllegalStateException("Binder already consumed".toString());
            }
            IBinder take = this.queue.take();
            Intrinsics.checkNotNullExpressionValue(take, "queue.take()");
            return take;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LDIdentifiers.kt */
    /* loaded from: classes2.dex */
    public static final class GoogleAdInfo implements IInterface {
        public static final Companion Companion = new Companion(null);
        private static final int FIRST_TRANSACTION_CODE = 1;
        private static final int SECOND_TRANSACTION_CODE = 2;
        private final IBinder binder;

        public GoogleAdInfo(IBinder binder) {
            Intrinsics.checkNotNullParameter(binder, "binder");
            this.binder = binder;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.binder;
        }

        public final String getAdvertiserId() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(obtain, "obtain()");
            Parcel obtain2 = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(obtain2, "obtain()");
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public final boolean isTrackingLimited() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(obtain, "obtain()");
            Parcel obtain2 = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(obtain2, "obtain()");
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.binder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt() != 0;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        /* compiled from: LDIdentifiers.kt */
        /* loaded from: classes2.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }
}
