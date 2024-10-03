package com.google.ads.conversiontracking;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;
import com.google.ads.conversiontracking.InterfaceC0893q;
import java.io.IOException;

/* renamed from: com.google.ads.conversiontracking.i */
/* loaded from: classes.dex */
public final class C0885i {

    /* renamed from: com.google.ads.conversiontracking.i$a */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a */
        private final String f480a;

        /* renamed from: b */
        private final boolean f481b;

        public a(String str, boolean z) {
            this.f480a = str;
            this.f481b = z;
        }

        /* renamed from: a */
        public String m225a() {
            return this.f480a;
        }

        /* renamed from: b */
        public boolean m226b() {
            return this.f481b;
        }
    }

    /* renamed from: a */
    public static a m222a(Context context) throws IOException, IllegalStateException, C0886j, C0887k {
        C0892p.m244a("Calling this from your main thread can lead to deadlock");
        return m223a(context, m224b(context));
    }

    /* renamed from: a */
    static a m223a(Context context, ServiceConnectionC0890n serviceConnectionC0890n) throws IOException {
        try {
            try {
                InterfaceC0893q m249a = InterfaceC0893q.a.m249a(serviceConnectionC0890n.m238a());
                a aVar = new a(m249a.mo245a(), m249a.mo248a(true));
                try {
                    context.unbindService(serviceConnectionC0890n);
                } catch (IllegalArgumentException e) {
                    Log.i("AdvertisingIdClient", "getAdvertisingIdInfo unbindService failed.", e);
                }
                return aVar;
            } catch (RemoteException e2) {
                Log.i("AdvertisingIdClient", "GMS remote exception ", e2);
                throw new IOException("Remote exception");
            } catch (InterruptedException unused) {
                throw new IOException("Interrupted exception");
            }
        } catch (Throwable th) {
            try {
                context.unbindService(serviceConnectionC0890n);
            } catch (IllegalArgumentException e3) {
                Log.i("AdvertisingIdClient", "getAdvertisingIdInfo unbindService failed.", e3);
            }
            throw th;
        }
    }

    /* renamed from: b */
    private static ServiceConnectionC0890n m224b(Context context) throws IOException, C0886j, C0887k {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            try {
                C0888l.m235b(context);
                ServiceConnectionC0890n serviceConnectionC0890n = new ServiceConnectionC0890n();
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                if (context.bindService(intent, serviceConnectionC0890n, 1)) {
                    return serviceConnectionC0890n;
                }
                throw new IOException("Connection failure");
            } catch (C0886j e) {
                throw new IOException(e);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            throw new C0886j(9);
        }
    }
}
