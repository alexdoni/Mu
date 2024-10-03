package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzdy extends zzfa {
    private final List<zzfj> zza;

    private zzdy(OutputStream outputStream, List<zzfj> list) {
        super(outputStream);
        this.zza = list;
    }

    @Nullable
    public static zzdy zza(List<zzfk> list, Uri uri, OutputStream outputStream) {
        ArrayList arrayList = new ArrayList();
        Iterator<zzfk> it = list.iterator();
        while (it.hasNext()) {
            zzfj zzb = it.next().zzb();
            if (zzb != null) {
                arrayList.add(zzb);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new zzdy(outputStream, arrayList);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        Iterator<zzfj> it = this.zza.iterator();
        while (it.hasNext()) {
            try {
                it.next().close();
            } catch (Throwable unused) {
            }
        }
        super.close();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(int i) throws IOException {
        this.out.write(i);
        Iterator<zzfj> it = this.zza.iterator();
        while (it.hasNext()) {
            it.next().zza();
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfa, java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        for (zzfj zzfjVar : this.zza) {
            int length = bArr.length;
            zzfjVar.zza();
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfa, java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        Iterator<zzfj> it = this.zza.iterator();
        while (it.hasNext()) {
            it.next().zza();
        }
    }
}
