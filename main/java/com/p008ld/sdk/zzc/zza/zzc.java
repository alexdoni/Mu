package com.p008ld.sdk.zzc.zza;

import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: V1SchemeUtil.java */
/* loaded from: classes2.dex */
public class zzc {
    public static String zza(File file) throws Exception {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2 = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (Throwable th) {
            th = th;
        }
        try {
            long length = randomAccessFile.length();
            byte[] bArr = new byte[zza.zza.length];
            long length2 = length - zza.zza.length;
            randomAccessFile.seek(length2);
            randomAccessFile.readFully(bArr);
            if (zza(bArr)) {
                long j = length2 - 2;
                randomAccessFile.seek(j);
                int zza = zza(randomAccessFile);
                if (zza > 0) {
                    randomAccessFile.seek(j - zza);
                    byte[] bArr2 = new byte[zza];
                    randomAccessFile.readFully(bArr2);
                    String trim = new String(bArr2, "UTF-8").trim();
                    randomAccessFile.close();
                    return trim;
                }
                throw new Exception("zip channel info not found");
            }
            throw new Exception("zip v1 magic not found");
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile2 = randomAccessFile;
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            throw th;
        }
    }

    private static short zza(DataInput dataInput) throws IOException {
        byte[] bArr = new byte[2];
        dataInput.readFully(bArr);
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getShort(0);
    }

    private static boolean zza(byte[] bArr) {
        if (bArr.length != zza.zza.length) {
            return false;
        }
        for (int i = 0; i < zza.zza.length; i++) {
            if (bArr[i] != zza.zza[i]) {
                return false;
            }
        }
        return true;
    }
}
