package com.u2020.sdk.mc;

import com.u2020.sdk.mc.C2651a;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: ApkUtil.java */
/* renamed from: com.u2020.sdk.mc.b */
/* loaded from: classes3.dex */
final class C2652b {

    /* renamed from: a */
    public static final int f1560a = -2121758584;

    /* renamed from: b */
    public static final byte[] f1561b = {119, 97, 119, 98, 121, 115, 121, 115};

    /* renamed from: c */
    public static final int f1562c = 2;

    /* renamed from: d */
    public static final long f1563d = 3617552046287187010L;

    /* renamed from: e */
    public static final long f1564e = 2334950737559900225L;

    /* renamed from: f */
    private static final int f1565f = 32;

    /* renamed from: g */
    public static final int f1566g = 1896449818;

    /* renamed from: h */
    public static final int f1567h = 1114793335;

    /* renamed from: i */
    public static final int f1568i = 4096;

    /* renamed from: j */
    public static final String f1569j = "UTF-8";

    /* renamed from: k */
    public static final int f1570k = 22;

    /* renamed from: l */
    private static final int f1571l = 101010256;

    /* renamed from: m */
    public static final int f1572m = 65535;

    /* renamed from: n */
    private static final int f1573n = 20;

    private C2652b() {
    }

    /* renamed from: a */
    public static C2655e<ByteBuffer, Long> m1188a(final FileChannel fileChannel) throws IOException, C2651a.a {
        return m1189a(fileChannel, m1197b(fileChannel));
    }

    /* renamed from: b */
    public static long m1197b(final FileChannel fileChannel) throws IOException {
        return m1198b(fileChannel, m1200c(fileChannel));
    }

    /* renamed from: c */
    public static long m1200c(final FileChannel fileChannel) throws IOException {
        long size = fileChannel.size();
        if (size >= 22) {
            long j = size - 22;
            long min = Math.min(j, WebSocketProtocol.PAYLOAD_SHORT_MAX);
            int i = 0;
            while (true) {
                long j2 = i;
                if (j2 <= min) {
                    long j3 = j - j2;
                    ByteBuffer allocate = ByteBuffer.allocate(4);
                    fileChannel.position(j3);
                    fileChannel.read(allocate);
                    ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
                    allocate.order(byteOrder);
                    if (allocate.getInt(0) == f1571l) {
                        ByteBuffer allocate2 = ByteBuffer.allocate(2);
                        fileChannel.position(j3 + 20);
                        fileChannel.read(allocate2);
                        allocate2.order(byteOrder);
                        short s = allocate2.getShort(0);
                        if (s == i) {
                            return s;
                        }
                    }
                    i++;
                } else {
                    throw new IOException("ZIP End of Central Directory (EOCD) record not found");
                }
            }
        } else {
            throw new IOException("APK too small for ZIP End of Central Directory (EOCD) record");
        }
    }

    /* renamed from: b */
    public static long m1198b(final FileChannel fileChannel, final long commentLength) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        fileChannel.position((fileChannel.size() - commentLength) - 6);
        fileChannel.read(allocate);
        return allocate.getInt(0);
    }

    /* renamed from: a */
    public static C2655e<ByteBuffer, Long> m1189a(final FileChannel fileChannel, final long centralDirOffset) throws IOException, C2651a.a {
        if (centralDirOffset >= 32) {
            fileChannel.position(centralDirOffset - 24);
            ByteBuffer allocate = ByteBuffer.allocate(24);
            fileChannel.read(allocate);
            ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
            allocate.order(byteOrder);
            if (allocate.getLong(8) == f1564e && allocate.getLong(16) == f1563d) {
                long j = allocate.getLong(0);
                if (j < allocate.capacity() || j > 2147483639) {
                    throw new C2651a.a("APK Signing Block size out of range: " + j);
                }
                int i = (int) (8 + j);
                long j2 = centralDirOffset - i;
                if (j2 >= 0) {
                    fileChannel.position(j2);
                    ByteBuffer allocate2 = ByteBuffer.allocate(i);
                    fileChannel.read(allocate2);
                    allocate2.order(byteOrder);
                    long j3 = allocate2.getLong(0);
                    if (j3 == j) {
                        return C2655e.m1207a(allocate2, Long.valueOf(j2));
                    }
                    throw new C2651a.a("APK Signing Block sizes in header and footer do not match: " + j3 + " vs " + j);
                }
                throw new C2651a.a("APK Signing Block offset out of range: " + j2);
            }
            throw new C2651a.a("No APK Signing Block before ZIP Central Directory");
        }
        throw new C2651a.a("APK too small for APK Signing Block. ZIP Central Directory offset: " + centralDirOffset);
    }

    /* renamed from: b */
    public static Map<Integer, ByteBuffer> m1199b(final ByteBuffer apkSigningBlock) throws C2651a.a {
        m1194a(apkSigningBlock);
        ByteBuffer m1191a = m1191a(apkSigningBlock, 8, apkSigningBlock.capacity() - 24);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        while (m1191a.hasRemaining()) {
            i++;
            if (m1191a.remaining() >= 8) {
                long j = m1191a.getLong();
                if (j >= 4 && j <= 2147483647L) {
                    int i2 = (int) j;
                    int position = m1191a.position() + i2;
                    if (i2 <= m1191a.remaining()) {
                        linkedHashMap.put(Integer.valueOf(m1191a.getInt()), m1190a(m1191a, i2 - 4));
                        m1191a.position(position);
                    } else {
                        throw new C2651a.a("APK Signing Block entry #" + i + " size out of range: " + i2 + ", available: " + m1191a.remaining());
                    }
                } else {
                    throw new C2651a.a("APK Signing Block entry #" + i + " size out of range: " + j);
                }
            } else {
                throw new C2651a.a("Insufficient data to read size of APK Signing Block entry #" + i);
            }
        }
        return linkedHashMap;
    }

    /* renamed from: a */
    private static ByteBuffer m1191a(final ByteBuffer source, final int start, final int end) {
        if (start < 0) {
            throw new IllegalArgumentException("start: " + start);
        }
        if (end >= start) {
            int capacity = source.capacity();
            if (end <= source.capacity()) {
                int limit = source.limit();
                int position = source.position();
                try {
                    source.position(0);
                    source.limit(end);
                    source.position(start);
                    ByteBuffer slice = source.slice();
                    slice.order(source.order());
                    return slice;
                } finally {
                    source.position(0);
                    source.limit(limit);
                    source.position(position);
                }
            }
            throw new IllegalArgumentException("end > capacity: " + end + " > " + capacity);
        }
        throw new IllegalArgumentException("end < start: " + end + " < " + start);
    }

    /* renamed from: a */
    private static ByteBuffer m1190a(final ByteBuffer source, final int size) throws BufferUnderflowException {
        if (size >= 0) {
            int limit = source.limit();
            int position = source.position();
            int i = size + position;
            if (i >= position && i <= limit) {
                source.limit(i);
                try {
                    ByteBuffer slice = source.slice();
                    slice.order(source.order());
                    source.position(i);
                    return slice;
                } finally {
                    source.limit(limit);
                }
            }
            throw new BufferUnderflowException();
        }
        throw new IllegalArgumentException("size: " + size);
    }

    /* renamed from: a */
    private static void m1194a(final ByteBuffer buffer) {
        if (buffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    /* renamed from: a */
    public static void m1193a(int i, DataOutput out) throws IOException {
        ByteBuffer order = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
        order.putShort((short) i);
        out.write(order.array());
    }

    /* renamed from: a */
    public static short m1192a(DataInput input) throws IOException {
        byte[] bArr = new byte[2];
        input.readFully(bArr);
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getShort(0);
    }

    /* renamed from: a */
    public static boolean m1195a(File file) throws IOException {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (Throwable th) {
            th = th;
            randomAccessFile = null;
        }
        try {
            long length = randomAccessFile.length();
            byte[] bArr = new byte[f1561b.length];
            randomAccessFile.seek(length - r6.length);
            randomAccessFile.readFully(bArr);
            boolean m1196a = m1196a(bArr);
            randomAccessFile.close();
            return m1196a;
        } catch (Throwable th2) {
            th = th2;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static boolean m1196a(byte[] buffer) {
        if (buffer.length != f1561b.length) {
            return false;
        }
        int i = 0;
        while (true) {
            byte[] bArr = f1561b;
            if (i >= bArr.length) {
                return true;
            }
            if (buffer[i] != bArr[i]) {
                return false;
            }
            i++;
        }
    }
}
