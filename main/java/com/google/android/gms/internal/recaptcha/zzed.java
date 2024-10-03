package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.annotation.CheckReturnValue;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzed {
    private final Map<String, zzfg> zza;
    private final Map<String, zzfl> zzb;
    private final List<zzfk> zzc;

    public zzed(List<zzfg> list) {
        List<zzfl> emptyList = Collections.emptyList();
        List emptyList2 = Collections.emptyList();
        this.zza = new HashMap();
        this.zzb = new HashMap();
        this.zzc = new ArrayList();
        zzlb listIterator = ((zzkj) list).listIterator(0);
        while (listIterator.hasNext()) {
            zzfg zzfgVar = (zzfg) listIterator.next();
            if (TextUtils.isEmpty(zzfgVar.zzf())) {
                Log.w("MobStore.FileStorage", "Cannot register backend, name empty");
            } else {
                zzfg put = this.zza.put(zzfgVar.zzf(), zzfgVar);
                if (put != null) {
                    String canonicalName = put.getClass().getCanonicalName();
                    String canonicalName2 = zzfgVar.getClass().getCanonicalName();
                    StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 30 + String.valueOf(canonicalName2).length());
                    sb.append("Cannot override Backend ");
                    sb.append(canonicalName);
                    sb.append(" with ");
                    sb.append(canonicalName2);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
        }
        for (zzfl zzflVar : emptyList) {
            if (TextUtils.isEmpty(zzflVar.zza())) {
                Log.w("MobStore.FileStorage", "Cannot register transform, name empty");
            } else {
                zzfl put2 = this.zzb.put(zzflVar.zza(), zzflVar);
                if (put2 != null) {
                    String canonicalName3 = put2.getClass().getCanonicalName();
                    String canonicalName4 = zzflVar.getClass().getCanonicalName();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(canonicalName3).length() + 35 + String.valueOf(canonicalName4).length());
                    sb2.append("Cannot to override Transform ");
                    sb2.append(canonicalName3);
                    sb2.append(" with ");
                    sb2.append(canonicalName4);
                    throw new IllegalArgumentException(sb2.toString());
                }
            }
        }
        this.zzc.addAll(emptyList2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final zzeb zze(Uri uri) throws IOException {
        zzkj zzo;
        zzkf zzj = zzkj.zzj();
        zzkf zzj2 = zzkj.zzj();
        String encodedFragment = uri.getEncodedFragment();
        if (TextUtils.isEmpty(encodedFragment) || !encodedFragment.startsWith("transform=")) {
            zzo = zzkj.zzo();
        } else {
            zzo = zzkj.zzm(zzjt.zzc("+").zzb().zzd(encodedFragment.substring(10)));
        }
        int size = zzo.size();
        for (int i = 0; i < size; i++) {
            zzj2.zzc((zzkf) zzfb.zzb((String) zzo.get(i)));
        }
        zzkj zze = zzj2.zze();
        int size2 = zze.size();
        for (int i2 = 0; i2 < size2; i2++) {
            String str = (String) zze.get(i2);
            zzfl zzflVar = this.zzb.get(str);
            if (zzflVar != null) {
                zzj.zzc((zzkf) zzflVar);
            } else {
                String valueOf = String.valueOf(uri);
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(valueOf).length());
                sb.append("No such transform: ");
                sb.append(str);
                sb.append(": ");
                sb.append(valueOf);
                throw new zzew(sb.toString());
            }
        }
        zzkj zzh = zzj.zze().zzh();
        zzea zzeaVar = new zzea(null);
        zzeaVar.zzg(this);
        String scheme = uri.getScheme();
        zzfg zzfgVar = this.zza.get(scheme);
        if (zzfgVar != null) {
            zzeaVar.zzc(zzfgVar);
            zzeaVar.zze(this.zzc);
            zzeaVar.zzh(zzh);
            zzeaVar.zzf(uri);
            if (!zzh.isEmpty()) {
                ArrayList arrayList = new ArrayList(uri.getPathSegments());
                if (!arrayList.isEmpty() && !uri.getPath().endsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
                    String str2 = (String) arrayList.get(arrayList.size() - 1);
                    ListIterator<E> listIterator = zzh.listIterator(zzh.size());
                    while (listIterator.hasPrevious()) {
                        str2 = ((zzfl) listIterator.previous()).zzb();
                    }
                    arrayList.set(arrayList.size() - 1, str2);
                    uri = uri.buildUpon().path(TextUtils.join(RemoteSettings.FORWARD_SLASH_STRING, arrayList)).encodedFragment(null).build();
                }
            }
            zzeaVar.zzd(uri);
            return new zzeb(zzeaVar);
        }
        throw new zzew(String.format("Cannot open, unregistered backend: %s", scheme));
    }

    @CheckReturnValue
    public final <T> T zza(Uri uri, zzec<T> zzecVar) throws IOException {
        return zzecVar.zza(zze(uri));
    }

    public final void zzb(Uri uri) throws IOException {
        zzeb zze = zze(uri);
        zze.zzb().zzk(zze.zza());
    }

    public final void zzc(Uri uri, Uri uri2) throws IOException {
        zzeb zze = zze(uri);
        zzeb zze2 = zze(uri2);
        if (zze.zzb() != zze2.zzb()) {
            throw new zzew("Cannot rename file across backends");
        }
        zze.zzb().zzl(zze.zza(), zze2.zza());
    }

    @CheckReturnValue
    public final boolean zzd(Uri uri) throws IOException {
        zzeb zze = zze(uri);
        return zze.zzb().zzg(zze.zza());
    }
}
