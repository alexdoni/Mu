package com.p008ld.sdk.zzc.zza.zza;

import com.p008ld.sdk.zzc.zza.zzb;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
import org.spongycastle.asn1.cmc.BodyPartID;

/* compiled from: ZipUtils.java */
/* loaded from: classes2.dex */
public abstract class zzc {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzb<ByteBuffer, Long> zza(RandomAccessFile randomAccessFile) throws IOException {
        if (randomAccessFile.length() < 22) {
            return null;
        }
        zzb<ByteBuffer, Long> zza = zza(randomAccessFile, 0);
        return zza != null ? zza : zza(randomAccessFile, 65535);
    }

    private static zzb<ByteBuffer, Long> zza(RandomAccessFile randomAccessFile, int i) throws IOException {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("maxCommentSize: " + i);
        }
        long length = randomAccessFile.length();
        if (length < 22) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(((int) Math.min(i, length - 22)) + 22);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        long capacity = length - allocate.capacity();
        randomAccessFile.seek(capacity);
        randomAccessFile.readFully(allocate.array(), allocate.arrayOffset(), allocate.capacity());
        int zzd = zzd(allocate);
        if (zzd == -1) {
            return null;
        }
        allocate.position(zzd);
        ByteBuffer slice = allocate.slice();
        slice.order(ByteOrder.LITTLE_ENDIAN);
        return zzb.zza(slice, Long.valueOf(capacity + zzd));
    }

    private static int zzd(ByteBuffer byteBuffer) {
        zzc(byteBuffer);
        int capacity = byteBuffer.capacity();
        if (capacity < 22) {
            return -1;
        }
        int i = capacity - 22;
        int min = Math.min(i, 65535);
        for (int i2 = 0; i2 <= min; i2++) {
            int i3 = i - i2;
            if (byteBuffer.getInt(i3) == 101010256 && zza(byteBuffer, i3 + 20) == i2) {
                return i3;
            }
        }
        return -1;
    }

    public static final boolean zza(RandomAccessFile randomAccessFile, long j) throws IOException {
        long j2 = j - 20;
        if (j2 < 0) {
            return false;
        }
        randomAccessFile.seek(j2);
        return randomAccessFile.readInt() == 1347094023;
    }

    public static long zza(ByteBuffer byteBuffer) {
        zzc(byteBuffer);
        return zzb(byteBuffer, byteBuffer.position() + 16);
    }

    public static long zzb(ByteBuffer byteBuffer) {
        zzc(byteBuffer);
        return zzb(byteBuffer, byteBuffer.position() + 12);
    }

    public static void zzc(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    public static int zza(ByteBuffer byteBuffer, int i) {
        return byteBuffer.getShort(i) & UShort.MAX_VALUE;
    }

    public static long zzb(ByteBuffer byteBuffer, int i) {
        return byteBuffer.getInt(i) & BodyPartID.bodyIdMax;
    }
}
