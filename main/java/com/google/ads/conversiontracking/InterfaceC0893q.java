package com.google.ads.conversiontracking;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.ads.conversiontracking.q */
/* loaded from: classes.dex */
public interface InterfaceC0893q extends IInterface {

    /* renamed from: com.google.ads.conversiontracking.q$a */
    /* loaded from: classes.dex */
    public static abstract class a extends Binder implements InterfaceC0893q {

        /* renamed from: com.google.ads.conversiontracking.q$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private static class C3237a implements InterfaceC0893q {

            /* renamed from: a */
            private IBinder f501a;

            C3237a(IBinder iBinder) {
                this.f501a = iBinder;
            }

            @Override // com.google.ads.conversiontracking.InterfaceC0893q
            /* renamed from: a */
            public String mo245a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    this.f501a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.google.ads.conversiontracking.InterfaceC0893q
            /* renamed from: a */
            public String mo246a(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    obtain.writeString(str);
                    this.f501a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.google.ads.conversiontracking.InterfaceC0893q
            /* renamed from: a */
            public void mo247a(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.f501a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.google.ads.conversiontracking.InterfaceC0893q
            /* renamed from: a */
            public boolean mo248a(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    obtain.writeInt(z ? 1 : 0);
                    this.f501a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f501a;
            }
        }

        /* renamed from: a */
        public static InterfaceC0893q m249a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof InterfaceC0893q)) ? new C3237a(iBinder) : (InterfaceC0893q) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                String a2 = mo245a();
                parcel2.writeNoException();
                parcel2.writeString(a2);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean a3 = mo248a(parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeInt(a3 ? 1 : 0);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                String a4 = mo246a(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(a4);
                return true;
            }
            if (i != 4) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                return true;
            }
            parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            mo247a(parcel.readString(), parcel.readInt() != 0);
            parcel2.writeNoException();
            return true;
        }
    }

    /* renamed from: a */
    String mo245a() throws RemoteException;

    /* renamed from: a */
    String mo246a(String str) throws RemoteException;

    /* renamed from: a */
    void mo247a(String str, boolean z) throws RemoteException;

    /* renamed from: a */
    boolean mo248a(boolean z) throws RemoteException;
}
