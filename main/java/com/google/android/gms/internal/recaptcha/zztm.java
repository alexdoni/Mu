package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zztm {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzpy zzpyVar) {
        StringBuilder sb = new StringBuilder(zzpyVar.zzd());
        for (int i = 0; i < zzpyVar.zzd(); i++) {
            byte zza = zzpyVar.zza(i);
            if (zza != 34) {
                if (zza != 39) {
                    if (zza == 92) {
                        sb.append("\\\\");
                    } else {
                        switch (zza) {
                            case 7:
                                sb.append("\\a");
                                break;
                            case 8:
                                sb.append("\\b");
                                break;
                            case 9:
                                sb.append("\\t");
                                break;
                            case 10:
                                sb.append("\\n");
                                break;
                            case 11:
                                sb.append("\\v");
                                break;
                            case 12:
                                sb.append("\\f");
                                break;
                            case 13:
                                sb.append("\\r");
                                break;
                            default:
                                if (zza < 32 || zza > 126) {
                                    sb.append('\\');
                                    sb.append((char) (((zza >>> 6) & 3) + 48));
                                    sb.append((char) (((zza >>> 3) & 7) + 48));
                                    sb.append((char) ((zza & 7) + 48));
                                    break;
                                } else {
                                    sb.append((char) zza);
                                    break;
                                }
                                break;
                        }
                    }
                } else {
                    sb.append("\\'");
                }
            } else {
                sb.append("\\\"");
            }
        }
        return sb.toString();
    }
}
