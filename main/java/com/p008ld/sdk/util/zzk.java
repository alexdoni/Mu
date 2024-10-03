package com.p008ld.sdk.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: LDSp.java */
/* loaded from: classes2.dex */
public final class zzk {
    private static final Map<String, zzk> zza = new HashMap();
    private SharedPreferences zzb;

    public static zzk zza(Context context) {
        return zza(context, "", 0);
    }

    public static zzk zza(Context context, String str, int i) {
        if (zze(str)) {
            str = "spUtils";
        }
        Map<String, zzk> map = zza;
        zzk zzkVar = map.get(str);
        if (zzkVar == null) {
            synchronized (zzk.class) {
                zzkVar = map.get(str);
                if (zzkVar == null) {
                    zzkVar = new zzk(context, str, i);
                    map.put(str, zzkVar);
                }
            }
        }
        return zzkVar;
    }

    private zzk(Context context, String str, int i) {
        this.zzb = context.getApplicationContext().getSharedPreferences(str, i);
    }

    public void zza(String str, String str2) {
        zza(str, str2, false);
    }

    public void zza(String str, String str2, boolean z) {
        if (z) {
            this.zzb.edit().putString(str, str2).commit();
        } else {
            this.zzb.edit().putString(str, str2).apply();
        }
    }

    public String zza(String str) {
        return zzb(str, "");
    }

    public String zzb(String str, String str2) {
        return this.zzb.getString(str, str2);
    }

    public void zza(String str, int i) {
        zza(str, i, false);
    }

    public void zza(String str, int i, boolean z) {
        if (z) {
            this.zzb.edit().putInt(str, i).commit();
        } else {
            this.zzb.edit().putInt(str, i).apply();
        }
    }

    public int zzb(String str) {
        return zzb(str, -1);
    }

    public int zzb(String str, int i) {
        return this.zzb.getInt(str, i);
    }

    public void zza(String str, Set<String> set) {
        zza(str, set, false);
    }

    public void zza(String str, Set<String> set, boolean z) {
        if (z) {
            this.zzb.edit().putStringSet(str, set).commit();
        } else {
            this.zzb.edit().putStringSet(str, set).apply();
        }
    }

    public Set<String> zzc(String str) {
        return zzb(str, Collections.emptySet());
    }

    public Set<String> zzb(String str, Set<String> set) {
        return this.zzb.getStringSet(str, set);
    }

    public void zzd(String str) {
        zza(str, false);
    }

    public void zza(String str, boolean z) {
        if (z) {
            this.zzb.edit().remove(str).commit();
        } else {
            this.zzb.edit().remove(str).apply();
        }
    }

    private static boolean zze(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
