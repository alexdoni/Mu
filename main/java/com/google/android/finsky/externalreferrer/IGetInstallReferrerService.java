package com.google.android.finsky.externalreferrer;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.p007a.BinderC0896b;
import com.google.android.p007a.C0895a;
import com.google.android.p007a.C0897c;

/* loaded from: classes.dex */
public interface IGetInstallReferrerService extends IInterface {

    /* loaded from: classes.dex */
    public static abstract class Stub extends BinderC0896b implements IGetInstallReferrerService {

        /* loaded from: classes.dex */
        public static class Proxy extends C0895a implements IGetInstallReferrerService {
            Proxy(IBinder iBinder) {
                super(iBinder);
            }

            @Override // com.google.android.finsky.externalreferrer.IGetInstallReferrerService
            /* renamed from: c */
            public final Bundle mo294c(Bundle bundle) throws RemoteException {
                Parcel m254a = m254a();
                C0897c.m258b(m254a, bundle);
                Parcel m255b = m255b(m254a);
                Bundle bundle2 = (Bundle) C0897c.m257a(m255b, Bundle.CREATOR);
                m255b.recycle();
                return bundle2;
            }
        }

        /* renamed from: b */
        public static IGetInstallReferrerService m295b(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
            if (queryLocalInterface instanceof IGetInstallReferrerService) {
                return (IGetInstallReferrerService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // com.google.android.p007a.BinderC0896b
        /* renamed from: a */
        protected final boolean mo256a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            if (i != 1) {
                return false;
            }
            Bundle c = mo294c((Bundle) C0897c.m257a(parcel, Bundle.CREATOR));
            parcel2.writeNoException();
            C0897c.m259c(parcel2, c);
            return true;
        }
    }

    /* renamed from: c */
    Bundle mo294c(Bundle bundle) throws RemoteException;
}
