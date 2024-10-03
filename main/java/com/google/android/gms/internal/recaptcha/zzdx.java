package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzdx extends zzez {
    private final List<zzfi> zza;

    private zzdx(InputStream inputStream, List<zzfi> list) {
        super(inputStream);
        this.zza = list;
    }

    @Nullable
    public static zzdx zza(List<zzfk> list, Uri uri, InputStream inputStream) {
        ArrayList arrayList = new ArrayList();
        Iterator<zzfk> it = list.iterator();
        while (it.hasNext()) {
            zzfi zza = it.next().zza();
            if (zza != null) {
                arrayList.add(zza);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new zzdx(inputStream, arrayList);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        Iterator<zzfi> it = this.zza.iterator();
        while (it.hasNext()) {
            try {
                it.next().close();
            } catch (Throwable unused) {
            }
        }
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        int read = this.in.read();
        if (read != -1) {
            Iterator<zzfi> it = this.zza.iterator();
            while (it.hasNext()) {
                it.next().zza();
            }
        }
        return read;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzez, java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        int read = this.in.read(bArr);
        if (read != -1) {
            Iterator<zzfi> it = this.zza.iterator();
            while (it.hasNext()) {
                it.next().zza();
            }
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.in.read(bArr, i, i2);
        if (read != -1) {
            Iterator<zzfi> it = this.zza.iterator();
            while (it.hasNext()) {
                it.next().zza();
            }
        }
        return read;
    }
}
