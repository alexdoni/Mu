package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzpg;
import com.google.android.gms.internal.recaptcha.zzph;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzph<MessageType extends zzph<MessageType, BuilderType>, BuilderType extends zzpg<MessageType, BuilderType>> implements zzsn {
    protected int zza = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void zzq(Iterable<T> iterable, List<? super T> list) {
        zzrp.zze(iterable);
        if (iterable instanceof zzrw) {
            List<?> zzh = ((zzrw) iterable).zzh();
            zzrw zzrwVar = (zzrw) list;
            int size = list.size();
            for (Object obj : zzh) {
                if (obj != null) {
                    if (obj instanceof zzpy) {
                        zzrwVar.zzi((zzpy) obj);
                    } else {
                        zzrwVar.add((String) obj);
                    }
                } else {
                    int size2 = zzrwVar.size();
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(size2 - size);
                    sb.append(" is null.");
                    String sb2 = sb.toString();
                    int size3 = zzrwVar.size();
                    while (true) {
                        size3--;
                        if (size3 < size) {
                            break;
                        } else {
                            zzrwVar.remove(size3);
                        }
                    }
                    throw new NullPointerException(sb2);
                }
            }
            return;
        }
        if (!(iterable instanceof zzsv)) {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(list.size() + iterable.size());
            }
            int size4 = list.size();
            for (T t : iterable) {
                if (t != null) {
                    list.add(t);
                } else {
                    int size5 = list.size();
                    StringBuilder sb3 = new StringBuilder(37);
                    sb3.append("Element at index ");
                    sb3.append(size5 - size4);
                    sb3.append(" is null.");
                    String sb4 = sb3.toString();
                    int size6 = list.size();
                    while (true) {
                        size6--;
                        if (size6 < size4) {
                            break;
                        } else {
                            list.remove(size6);
                        }
                    }
                    throw new NullPointerException(sb4);
                }
            }
            return;
        }
        list.addAll(iterable);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsn
    public final zzpy zzN() {
        try {
            int zzt = zzt();
            zzpy zzpyVar = zzpy.zzb;
            byte[] bArr = new byte[zzt];
            zzqj zzM = zzqj.zzM(bArr);
            zzM(zzM);
            return zzpu.zza(zzM, bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ByteString threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zzo() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zzr(int i) {
        throw null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzsn
    public final void zzs(OutputStream outputStream) throws IOException {
        zzqj zzN = zzqj.zzN(outputStream, zzqj.zzH(zzt()));
        zzM(zzN);
        zzN.zzT();
    }
}
