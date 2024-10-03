package com.p008ld.sdk.zzc.zza;

import com.p008ld.sdk.zzc.zza.zza.zza;
import com.p008ld.sdk.zzc.zza.zza.zzb;
import com.p008ld.sdk.zzc.zza.zza.zzc;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: V2SchemeUtil.java */
/* loaded from: classes2.dex */
public class zzd {
    public static Map<Integer, ByteBuffer> zza(ByteBuffer byteBuffer) throws zzb {
        zza.zza(byteBuffer);
        ByteBuffer zza = zza.zza(byteBuffer, 8, byteBuffer.capacity() - 24);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        while (zza.hasRemaining()) {
            i++;
            if (zza.remaining() < 8) {
                throw new zzb("Insufficient data to read size of APK Signing Block entry #" + i);
            }
            long j = zza.getLong();
            if (j < 4 || j > 2147483647L) {
                throw new zzb("APK Signing Block entry #" + i + " size out of range: " + j);
            }
            int i2 = (int) j;
            int position = zza.position() + i2;
            if (i2 > zza.remaining()) {
                throw new zzb("APK Signing Block entry #" + i + " size out of range: " + i2 + ", available: " + zza.remaining());
            }
            linkedHashMap.put(Integer.valueOf(zza.getInt()), zza.zza(zza, i2 - 4));
            zza.position(position);
        }
        if (!linkedHashMap.isEmpty()) {
            return linkedHashMap;
        }
        throw new zzb("not have Id-Value Pair in APK Signing Block entry #" + i);
    }

    public static ByteBuffer zza(File file) throws zzb, IOException {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2 = null;
        if (file == null || !file.exists() || !file.isFile()) {
            return null;
        }
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (Throwable th) {
            th = th;
        }
        try {
            zzb<ByteBuffer, Long> zza = zza.zza(randomAccessFile);
            ByteBuffer zza2 = zza.zza();
            long longValue = zza.zzb().longValue();
            if (zzc.zza(randomAccessFile, longValue)) {
                throw new zzb("ZIP64 APK not supported");
            }
            ByteBuffer zza3 = zza.zza(randomAccessFile, zza.zza(zza2, longValue)).zza();
            randomAccessFile.close();
            return zza3;
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile2 = randomAccessFile;
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            throw th;
        }
    }
}
