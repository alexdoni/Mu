package com.oversea.ab_firstarea.haiwai;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ContextHolder;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes.dex */
public class AdvertisingIdClient {
    private static String aaid = "";
    private static String gaid = "";

    public static String doGoogleAdId() {
        if (!TextUtils.isEmpty(gaid)) {
            return gaid;
        }
        String stringKeyForValue = ImageUtils.getStringKeyForValue(ContextHolder.getContext(), Constants.SDK_ADID);
        gaid = stringKeyForValue;
        if (!TextUtils.isEmpty(stringKeyForValue)) {
            return gaid;
        }
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.oversea.ab_firstarea.haiwai.AdvertisingIdClient.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String unused = AdvertisingIdClient.gaid = AdvertisingIdClient.getGoogleAdId(ContextHolder.getContext());
                    ImageUtils.setSharePreferences(ContextHolder.getContext(), Constants.SDK_ADID, AdvertisingIdClient.gaid);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
        if (TextUtils.isEmpty(gaid)) {
            gaid = "";
        }
        return gaid;
    }

    public static String getGoogleAdId(Context context) throws Exception {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            return "Cannot call in the main thread, You must call in the other thread";
        }
        context.getPackageManager().getPackageInfo("com.android.vending", 0);
        AdvertisingConnection advertisingConnection = new AdvertisingConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (!context.bindService(intent, advertisingConnection, 1)) {
            return "";
        }
        try {
            return new AdvertisingInterface(advertisingConnection.getBinder()).getId();
        } finally {
            context.unbindService(advertisingConnection);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class AdvertisingConnection implements ServiceConnection {
        private final LinkedBlockingQueue<IBinder> queue;
        boolean retrieved;

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        private AdvertisingConnection() {
            this.retrieved = false;
            this.queue = new LinkedBlockingQueue<>(1);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.queue.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public IBinder getBinder() throws InterruptedException {
            if (this.retrieved) {
                throw new IllegalStateException();
            }
            this.retrieved = true;
            return this.queue.take();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class AdvertisingInterface implements IInterface {
        private IBinder binder;

        public AdvertisingInterface(IBinder iBinder) {
            this.binder = iBinder;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.binder;
        }

        public String getId() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
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

        public boolean isLimitAdTrackingEnabled(boolean z) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(z ? 1 : 0);
                this.binder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt() != 0;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public static String doGoogleAAID() {
        if (!TextUtils.isEmpty(aaid)) {
            return aaid;
        }
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.oversea.ab_firstarea.haiwai.AdvertisingIdClient.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String unused = AdvertisingIdClient.aaid = AdvertisingIdClient.getGoogleAdId2(ContextHolder.getContext());
                    if (TextUtils.isEmpty(AdvertisingIdClient.gaid)) {
                        return;
                    }
                    ImageUtils.setSharePreferences(ContextHolder.getContext(), Constants.SDK_ADID, AdvertisingIdClient.gaid);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
        return aaid;
    }

    public static String getGoogleAdId2(final Context context) {
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.oversea.ab_firstarea.haiwai.AdvertisingIdClient.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AdvertisingIdClient.Info advertisingIdInfo = com.google.android.gms.ads.identifier.AdvertisingIdClient.getAdvertisingIdInfo(context);
                    if (advertisingIdInfo != null) {
                        String unused = AdvertisingIdClient.aaid = advertisingIdInfo.getId();
                    }
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        });
        return "";
    }
}
