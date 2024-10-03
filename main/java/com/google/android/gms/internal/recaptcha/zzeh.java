package com.google.android.gms.internal.recaptcha;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzeh extends zzfh {
    private final Context zza;
    private final zzfg zzb;
    private final Object zzc = new Object();

    @Nullable
    private String zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzeh(zzeg zzegVar, zzef zzefVar) {
        zzet zzetVar;
        Context context;
        zzetVar = zzegVar.zzb;
        this.zzb = new zzep(zzetVar);
        context = zzegVar.zza;
        this.zza = context;
    }

    public static zzeg zzb(Context context) {
        return new zzeg(context, null);
    }

    private final boolean zzh(Uri uri) {
        return (TextUtils.isEmpty(uri.getAuthority()) || this.zza.getPackageName().equals(uri.getAuthority())) ? false : true;
    }

    private static final void zzi() throws zzes {
        throw new zzes("Android backend cannot perform remote operations without a remote backend");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzfh
    public final Uri zza(Uri uri) throws IOException {
        if (zzh(uri)) {
            throw new zzeu("Operation across authorities is not allowed.");
        }
        File zzd = zzd(uri);
        zzen zzenVar = new zzen(null);
        zzenVar.zzb(zzd);
        return zzenVar.zza();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfh
    protected final zzfg zzc() {
        return this.zzb;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.recaptcha.zzfh, com.google.android.gms.internal.recaptcha.zzfg
    public final File zzd(Uri uri) throws IOException {
        char c;
        File filesDir;
        String str;
        if (!zzh(uri)) {
            Context context = this.zza;
            if (!uri.getScheme().equals("android")) {
                throw new zzeu("Scheme must be 'android'");
            }
            if (uri.getPathSegments().isEmpty()) {
                throw new zzeu(String.format("Path must start with a valid logical location: %s", uri));
            }
            if (TextUtils.isEmpty(uri.getQuery())) {
                ArrayList arrayList = new ArrayList(uri.getPathSegments());
                String str2 = (String) arrayList.get(0);
                switch (str2.hashCode()) {
                    case -1820761141:
                        if (str2.equals("external")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case 94416770:
                        if (str2.equals("cache")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 97434231:
                        if (str2.equals("files")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 835260319:
                        if (str2.equals("managed")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 988548496:
                        if (str2.equals("directboot-cache")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 991565957:
                        if (str2.equals("directboot-files")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                if (c != 0) {
                    if (c != 1) {
                        if (c == 2) {
                            filesDir = zzei.zza(context);
                        } else if (c == 3) {
                            filesDir = context.getCacheDir();
                        } else if (c == 4) {
                            File file = new File(zzei.zza(context), "managed");
                            if (arrayList.size() >= 3) {
                                try {
                                    if (!zzee.zza.equals(zzee.zza((String) arrayList.get(2)))) {
                                        throw new zzeu("AccountManager cannot be null");
                                    }
                                } catch (IllegalArgumentException e) {
                                    throw new zzeu(e);
                                }
                            }
                            filesDir = file;
                        } else if (c == 5) {
                            filesDir = context.getExternalFilesDir(null);
                        } else {
                            throw new zzeu(String.format("Path must start with a valid logical location: %s", uri));
                        }
                    } else if (Build.VERSION.SDK_INT >= 24) {
                        filesDir = context.createDeviceProtectedStorageContext().getCacheDir();
                    } else {
                        throw new zzeu(String.format("Direct boot only exists on N or greater: current SDK %s", Integer.valueOf(Build.VERSION.SDK_INT)));
                    }
                } else {
                    if (Build.VERSION.SDK_INT < 24) {
                        throw new zzeu(String.format("Direct boot only exists on N or greater: current SDK %s", Integer.valueOf(Build.VERSION.SDK_INT)));
                    }
                    filesDir = context.createDeviceProtectedStorageContext().getFilesDir();
                }
                File file2 = new File(filesDir, TextUtils.join(File.separator, arrayList.subList(1, arrayList.size())));
                if (!zzdr.zza(this.zza)) {
                    synchronized (this.zzc) {
                        if (this.zzd == null) {
                            this.zzd = zzei.zza(this.zza.createDeviceProtectedStorageContext()).getParentFile().getAbsolutePath();
                        }
                        str = this.zzd;
                    }
                    if (!file2.getAbsolutePath().startsWith(str)) {
                        throw new zzes("Cannot access credential-protected data from direct boot");
                    }
                }
                return file2;
            }
            throw new zzeu("Did not expect uri to have query");
        }
        throw new IOException("operation is not permitted in other authorities.");
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfh, com.google.android.gms.internal.recaptcha.zzfg
    public final InputStream zze(Uri uri) throws IOException {
        if (!zzh(uri)) {
            return zzex.zzb(zzeo.zza(zza(uri)));
        }
        zzi();
        throw null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public final String zzf() {
        return "android";
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfh, com.google.android.gms.internal.recaptcha.zzfg
    public final boolean zzg(Uri uri) throws IOException {
        if (!zzh(uri)) {
            return zzeo.zza(zza(uri)).exists();
        }
        zzi();
        throw null;
    }
}
