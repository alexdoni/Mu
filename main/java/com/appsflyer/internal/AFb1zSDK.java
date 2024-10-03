package com.appsflyer.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import com.appsflyer.AFLogger;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Deprecated
/* loaded from: classes.dex */
public final class AFb1zSDK {
    public static AFa1uSDK valueOf(Context context) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        context.getPackageManager().getPackageInfo("com.android.vending", 0);
        AFa1ySDK aFa1ySDK = new AFa1ySDK((byte) 0);
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        try {
            if (!context.bindService(intent, aFa1ySDK, 1)) {
                if (context != null) {
                    context.unbindService(aFa1ySDK);
                }
                throw new IOException("Google Play connection failed");
            }
            if (aFa1ySDK.valueOf) {
                throw new IllegalStateException("Cannot call get on this connection more than once");
            }
            aFa1ySDK.valueOf = true;
            IBinder poll = aFa1ySDK.AFKeystoreWrapper.poll(10L, TimeUnit.SECONDS);
            if (poll != null) {
                AFa1vSDK aFa1vSDK = new AFa1vSDK(poll);
                return new AFa1uSDK(aFa1vSDK.AFKeystoreWrapper(), aFa1vSDK.AFInAppEventParameterName());
            }
            throw new TimeoutException("Timed out waiting for the service connection");
        } finally {
            if (context != null) {
                context.unbindService(aFa1ySDK);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class AFa1uSDK {
        private final boolean AFInAppEventParameterName;
        public final String valueOf;

        AFa1uSDK(String str, boolean z) {
            this.valueOf = str;
            this.AFInAppEventParameterName = z;
        }

        public final boolean values() {
            return this.AFInAppEventParameterName;
        }
    }

    /* loaded from: classes.dex */
    static final class AFa1ySDK implements ServiceConnection {
        final LinkedBlockingQueue<IBinder> AFKeystoreWrapper;
        boolean valueOf;

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
        }

        private AFa1ySDK() {
            this.AFKeystoreWrapper = new LinkedBlockingQueue<>(1);
            this.valueOf = false;
        }

        /* synthetic */ AFa1ySDK(byte b) {
            this();
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.AFKeystoreWrapper.put(iBinder);
            } catch (InterruptedException e) {
                AFLogger.afErrorLogForExcManagerOnly("onServiceConnected Interrupted", e);
            }
        }
    }

    /* loaded from: classes.dex */
    static final class AFa1vSDK implements IInterface {
        private final IBinder AFInAppEventParameterName;

        AFa1vSDK(IBinder iBinder) {
            this.AFInAppEventParameterName = iBinder;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.AFInAppEventParameterName;
        }

        public final String AFKeystoreWrapper() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.AFInAppEventParameterName.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        final boolean AFInAppEventParameterName() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.AFInAppEventParameterName.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt() != 0;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
