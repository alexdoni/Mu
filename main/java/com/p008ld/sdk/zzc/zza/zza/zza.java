package com.p008ld.sdk.zzc.zza.zza;

import com.p008ld.sdk.zzc.zza.zzb;
import com.u2020.sdk.mc.C2652b;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: ApkSigningBlockUtils.java */
/* loaded from: classes2.dex */
public class zza {
    public static zzb<ByteBuffer, Long> zza(RandomAccessFile randomAccessFile) throws IOException, zzb {
        zzb<ByteBuffer, Long> zza = zzc.zza(randomAccessFile);
        if (zza != null) {
            return zza;
        }
        throw new zzb("Not an APK file: ZIP End of Central Directory record not found");
    }

    public static long zza(ByteBuffer byteBuffer, long j) throws zzb {
        long zza = zzc.zza(byteBuffer);
        if (zza <= j) {
            if (zzc.zzb(byteBuffer) + zza == j) {
                return zza;
            }
            throw new zzb("ZIP Central Directory is not immediately followed by End of Central Directory");
        }
        throw new zzb("ZIP Central Directory offset out of range: " + zza + ". ZIP End of Central Directory offset: " + j);
    }

    public static zzb<ByteBuffer, Long> zza(RandomAccessFile randomAccessFile, long j) throws IOException, zzb {
        if (j < 32) {
            throw new zzb("APK too small for APK Signing Block. ZIP Central Directory offset: " + j);
        }
        ByteBuffer allocate = ByteBuffer.allocate(24);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        randomAccessFile.seek(j - allocate.capacity());
        randomAccessFile.readFully(allocate.array(), allocate.arrayOffset(), allocate.capacity());
        if (allocate.getLong(8) != C2652b.f1564e || allocate.getLong(16) != C2652b.f1563d) {
            throw new zzb("No APK Signing Block before ZIP Central Directory");
        }
        long j2 = allocate.getLong(0);
        if (j2 < allocate.capacity() || j2 > 2147483639) {
            throw new zzb("APK Signing Block size out of range: " + j2);
        }
        int i = (int) (8 + j2);
        long j3 = j - i;
        if (j3 < 0) {
            throw new zzb("APK Signing Block offset out of range: " + j3);
        }
        ByteBuffer allocate2 = ByteBuffer.allocate(i);
        allocate2.order(ByteOrder.LITTLE_ENDIAN);
        randomAccessFile.seek(j3);
        randomAccessFile.readFully(allocate2.array(), allocate2.arrayOffset(), allocate2.capacity());
        long j4 = allocate2.getLong(0);
        if (j4 != j2) {
            throw new zzb("APK Signing Block sizes in header and footer do not match: " + j4 + " vs " + j2);
        }
        return zzb.zza(allocate2, Long.valueOf(j3));
    }

    public static void zza(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    public static ByteBuffer zza(ByteBuffer byteBuffer, int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("start: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("end < start: " + i2 + " < " + i);
        }
        int capacity = byteBuffer.capacity();
        if (i2 > byteBuffer.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + i2 + " > " + capacity);
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        try {
            byteBuffer.position(0);
            byteBuffer.limit(i2);
            byteBuffer.position(i);
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            return slice;
        } finally {
            byteBuffer.position(0);
            byteBuffer.limit(limit);
            byteBuffer.position(position);
        }
    }

    public static ByteBuffer zza(ByteBuffer byteBuffer, int i) throws BufferUnderflowException {
        if (i < 0) {
            throw new IllegalArgumentException("size: " + i);
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        int i2 = i + position;
        if (i2 < position || i2 > limit) {
            throw new BufferUnderflowException();
        }
        byteBuffer.limit(i2);
        try {
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            byteBuffer.position(i2);
            return slice;
        } finally {
            byteBuffer.limit(limit);
        }
    }
}
