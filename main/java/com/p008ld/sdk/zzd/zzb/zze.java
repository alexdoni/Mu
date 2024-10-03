package com.p008ld.sdk.zzd.zzb;

import android.text.TextUtils;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.p008ld.sdk.zzd.zzb.zzd;
import java.io.IOException;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.Request;
import okio.Buffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Printer.java */
/* loaded from: classes2.dex */
public class zze {
    private static final String zza;
    private static final String zzb;
    private static final String[] zzc;
    private static final String[] zzd;

    static {
        String property = System.getProperty("line.separator");
        zza = property;
        zzb = property + property;
        zzc = new String[]{property, "Omitted response body"};
        zzd = new String[]{property, "Omitted request body"};
    }

    public static boolean zza(String str) {
        return TextUtils.isEmpty(str) || "\n".equals(str) || "\t".equals(str) || TextUtils.isEmpty(str.trim());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(zzd.zza zzaVar, Request request) {
        StringBuilder sb = new StringBuilder();
        String str = zza;
        sb.append(str);
        sb.append("Body:");
        sb.append(str);
        sb.append(zza(request));
        String sb2 = sb.toString();
        String zza2 = zzaVar.zza(true);
        if (zzaVar.zzd() == null) {
            zza.zza(zzaVar.zza(), zza2, "┌────── Request ────────────────────────────────────────────────────────────────────────");
        }
        zza(zzaVar.zza(), zza2, new String[]{"URL: " + request.url()}, zzaVar.zzd(), false);
        zza(zzaVar.zza(), zza2, zza(request, zzaVar.zzb()), zzaVar.zzd(), true);
        if (request.body() instanceof FormBody) {
            StringBuilder sb3 = new StringBuilder();
            FormBody formBody = (FormBody) request.body();
            if (formBody != null && formBody.size() != 0) {
                for (int i = 0; i < formBody.size(); i++) {
                    sb3.append(formBody.encodedName(i) + "=" + formBody.encodedValue(i) + "&");
                }
                sb3.delete(sb3.length() - 1, sb3.length());
                zza(zzaVar.zza(), zza2, new String[]{sb3.toString()}, zzaVar.zzd(), true);
            }
        }
        if (zzaVar.zzb() == zzb.BASIC || zzaVar.zzb() == zzb.BODY) {
            zza(zzaVar.zza(), zza2, sb2.split(zza), zzaVar.zzd(), true);
        }
        if (zzaVar.zzd() == null) {
            zza.zza(zzaVar.zza(), zza2, "└───────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(zzd.zza zzaVar, long j, boolean z, int i, String str, String str2, List<String> list) {
        StringBuilder sb = new StringBuilder();
        String str3 = zza;
        sb.append(str3);
        sb.append("Body:");
        sb.append(str3);
        sb.append(zzc(str2));
        String sb2 = sb.toString();
        String zza2 = zzaVar.zza(false);
        if (zzaVar.zzd() == null) {
            zza.zza(zzaVar.zza(), zza2, "┌────── Response ───────────────────────────────────────────────────────────────────────");
        }
        zza(zzaVar.zza(), zza2, zza(str, j, i, z, zzaVar.zzb(), list), zzaVar.zzd(), true);
        if (zzaVar.zzb() == zzb.BASIC || zzaVar.zzb() == zzb.BODY) {
            zza(zzaVar.zza(), zza2, sb2.split(str3), zzaVar.zzd(), true);
        }
        if (zzaVar.zzd() == null) {
            zza.zza(zzaVar.zza(), zza2, "└───────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(zzd.zza zzaVar, Request request) {
        String zza2 = zzaVar.zza(true);
        if (zzaVar.zzd() == null) {
            zza.zza(zzaVar.zza(), zza2, "┌────── Request ────────────────────────────────────────────────────────────────────────");
        }
        zza(zzaVar.zza(), zza2, new String[]{"URL: " + request.url()}, zzaVar.zzd(), false);
        zza(zzaVar.zza(), zza2, zza(request, zzaVar.zzb()), zzaVar.zzd(), true);
        if (request.body() instanceof FormBody) {
            StringBuilder sb = new StringBuilder();
            FormBody formBody = (FormBody) request.body();
            if (formBody != null && formBody.size() != 0) {
                for (int i = 0; i < formBody.size(); i++) {
                    sb.append(formBody.encodedName(i) + "=" + formBody.encodedValue(i) + "&");
                }
                sb.delete(sb.length() - 1, sb.length());
                zza(zzaVar.zza(), zza2, new String[]{sb.toString()}, zzaVar.zzd(), true);
            }
        }
        if (zzaVar.zzb() == zzb.BASIC || zzaVar.zzb() == zzb.BODY) {
            zza(zzaVar.zza(), zza2, zzd, zzaVar.zzd(), true);
        }
        if (zzaVar.zzd() == null) {
            zza.zza(zzaVar.zza(), zza2, "└───────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(zzd.zza zzaVar, long j, boolean z, int i, String str, List<String> list) {
        String zza2 = zzaVar.zza(false);
        if (zzaVar.zzd() == null) {
            zza.zza(zzaVar.zza(), zza2, "┌────── Response ───────────────────────────────────────────────────────────────────────");
        }
        zza(zzaVar.zza(), zza2, zza(str, j, i, z, zzaVar.zzb(), list), zzaVar.zzd(), true);
        zza(zzaVar.zza(), zza2, zzc, zzaVar.zzd(), true);
        if (zzaVar.zzd() == null) {
            zza.zza(zzaVar.zza(), zza2, "└───────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    private static String[] zza(Request request, zzb zzbVar) {
        String headers = request.headers().toString();
        boolean z = zzbVar == zzb.HEADERS || zzbVar == zzb.BASIC;
        StringBuilder sb = new StringBuilder("Method: @");
        sb.append(request.method());
        sb.append(zzb);
        String str = "";
        if (!zza(headers) && z) {
            str = "Headers:" + zza + zzb(headers);
        }
        sb.append(str);
        return sb.toString().split(zza);
    }

    private static String[] zza(String str, long j, int i, boolean z, zzb zzbVar, List<String> list) {
        String str2;
        boolean z2 = zzbVar == zzb.HEADERS || zzbVar == zzb.BASIC;
        String zza2 = zza(list);
        StringBuilder sb = new StringBuilder();
        String str3 = "";
        if (TextUtils.isEmpty(zza2)) {
            str2 = "";
        } else {
            str2 = zza2 + " - ";
        }
        sb.append(str2);
        sb.append("is success : ");
        sb.append(z);
        sb.append(" - Received in: ");
        sb.append(j);
        sb.append("ms");
        String str4 = zzb;
        sb.append(str4);
        sb.append("Status Code: ");
        sb.append(i);
        sb.append(str4);
        if (!zza(str) && z2) {
            str3 = "Headers:" + zza + zzb(str);
        }
        sb.append(str3);
        return sb.toString().split(zza);
    }

    private static String zza(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(RemoteSettings.FORWARD_SLASH_STRING);
            sb.append(str);
        }
        return sb.toString();
    }

    public static String zzb(String str) {
        String str2;
        String[] split = str.split(zza);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (split.length > 1) {
            while (i < split.length) {
                if (i == 0) {
                    str2 = "┌ ";
                } else {
                    str2 = i == split.length - 1 ? "└ " : "├ ";
                }
                sb.append(str2);
                sb.append(split[i]);
                sb.append("\n");
                i++;
            }
        } else {
            int length = split.length;
            while (i < length) {
                String str3 = split[i];
                sb.append("─ ");
                sb.append(str3);
                sb.append("\n");
                i++;
            }
        }
        return sb.toString();
    }

    private static void zza(int i, String str, String[] strArr, zzc zzcVar, boolean z) {
        for (String str2 : strArr) {
            int length = str2.length();
            int i2 = z ? 110 : length;
            int i3 = 0;
            while (i3 <= length / i2) {
                int i4 = i3 * i2;
                i3++;
                int i5 = i3 * i2;
                if (i5 > str2.length()) {
                    i5 = str2.length();
                }
                if (zzcVar == null) {
                    zza.zza(i, str, "│ " + str2.substring(i4, i5));
                } else {
                    zzcVar.zza(i, str, str2.substring(i4, i5));
                }
            }
        }
    }

    public static String zza(Request request) {
        try {
            Request build = request.newBuilder().build();
            Buffer buffer = new Buffer();
            if (build.body() == null) {
                return "";
            }
            build.body().writeTo(buffer);
            return zzc(buffer.readUtf8());
        } catch (IOException e) {
            return "{\"err\": \"" + e.getMessage() + "\"}";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzc(String str) {
        try {
            if (str.startsWith("{")) {
                str = new JSONObject(str).toString(3);
            } else if (str.startsWith("[")) {
                str = new JSONArray(str).toString(3);
            }
        } catch (JSONException unused) {
        }
        return str;
    }
}
