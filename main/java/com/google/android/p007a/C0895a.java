package com.google.android.p007a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: BaseProxy.java */
/* renamed from: com.google.android.a.a */
/* loaded from: classes.dex */
public class C0895a implements IInterface {

    /* renamed from: a */
    private final IBinder f506a;

    /* renamed from: b */
    private final String f507b = "com.google.android.finsky.externalreferrer.IGetInstallReferrerService";

    /* JADX INFO: Access modifiers changed from: protected */
    public C0895a(IBinder iBinder) {
        this.f506a = iBinder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final Parcel m254a() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f507b);
        return obtain;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f506a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public final Parcel m255b(Parcel parcel) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        try {
            try {
                this.f506a.transact(1, parcel, obtain, 0);
                obtain.readException();
                return obtain;
            } catch (RuntimeException e) {
                obtain.recycle();
                throw e;
            }
        } finally {
            parcel.recycle();
        }
    }
}
