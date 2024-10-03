package com.google.android.p007a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: BaseStub.java */
/* renamed from: com.google.android.a.b */
/* loaded from: classes.dex */
public class BinderC0896b extends Binder implements IInterface {
    /* JADX INFO: Access modifiers changed from: protected */
    public BinderC0896b() {
        attachInterface(this, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }

    /* renamed from: a */
    protected boolean mo256a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        throw null;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i > 16777215) {
            if (super.onTransact(i, parcel, parcel2, i2)) {
                return true;
            }
        } else {
            parcel.enforceInterface(getInterfaceDescriptor());
        }
        return mo256a(i, parcel, parcel2);
    }
}
