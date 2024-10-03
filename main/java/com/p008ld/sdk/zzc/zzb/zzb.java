package com.p008ld.sdk.zzc.zzb;

import com.p008ld.sdk.zzc.zza.zzd;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/* compiled from: IdValueReader.java */
/* loaded from: classes2.dex */
public class zzb {
    public static String zza(File file, int i) {
        if (file != null && file.exists() && file.isFile()) {
            try {
                byte[] zzb = zzb(file, i);
                if (zzb != null && zzb.length > 0) {
                    return new String(zzb, "UTF-8").trim();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static byte[] zzb(File file, int i) {
        if (file != null && file.exists() && file.isFile()) {
            ByteBuffer zzc = zzc(file, i);
            System.out.println("getByteValueById , id = " + i + " , value = " + zzc);
            if (zzc != null) {
                return Arrays.copyOfRange(zzc.array(), zzc.arrayOffset() + zzc.position(), zzc.arrayOffset() + zzc.limit());
            }
        }
        return null;
    }

    public static ByteBuffer zzc(File file, int i) {
        if (file != null && file.exists() && file.isFile()) {
            Map<Integer, ByteBuffer> zza = zza(file);
            System.out.println("getByteBufferValueById , destApk " + file.getAbsolutePath() + " IdValueMap = " + zza);
            if (zza != null) {
                return zza.get(Integer.valueOf(i));
            }
        }
        return null;
    }

    public static Map<Integer, ByteBuffer> zza(File file) {
        if (file != null && file.exists() && file.isFile()) {
            try {
                return zzd.zza(zzd.zza(file));
            } catch (com.p008ld.sdk.zzc.zza.zza.zzb unused) {
                System.out.println("APK : " + file.getAbsolutePath() + " not have apk signature block");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
