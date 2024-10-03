package com.appsflyer.internal;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public final class AFj1rSDK extends FilterInputStream {
    private byte[] AFInAppEventParameterName;
    private short AFInAppEventType;
    private long[] AFKeystoreWrapper;
    private int AFLogger;

    /* renamed from: e */
    private int f294e;
    private int unregisterClient;
    private long[] valueOf;
    private final int values;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final boolean markSupported() {
        return false;
    }

    public AFj1rSDK(InputStream inputStream, int i, int i2, short s, int i3, int i4) throws IOException {
        super(new BufferedInputStream(inputStream, 4096));
        this.AFLogger = Integer.MAX_VALUE;
        int min = Math.min(Math.max((int) s, 4), 8);
        this.values = min;
        this.AFInAppEventParameterName = new byte[min];
        this.AFKeystoreWrapper = new long[4];
        this.valueOf = new long[4];
        this.unregisterClient = min;
        this.f294e = min;
        this.AFKeystoreWrapper = AFj1uSDK.valueOf(i ^ i4, min ^ i4);
        this.valueOf = AFj1uSDK.valueOf(i2 ^ i4, i3 ^ i4);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        values();
        int i = this.unregisterClient;
        if (i >= this.f294e) {
            return -1;
        }
        byte[] bArr = this.AFInAppEventParameterName;
        this.unregisterClient = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            values();
            int i5 = this.unregisterClient;
            if (i5 >= this.f294e) {
                if (i4 == i) {
                    return -1;
                }
                return i2 - (i3 - i4);
            }
            byte[] bArr2 = this.AFInAppEventParameterName;
            this.unregisterClient = i5 + 1;
            bArr[i4] = bArr2[i5];
        }
        return i2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) throws IOException {
        long j2 = 0;
        while (j2 < j && read() != -1) {
            j2++;
        }
        return j2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int available() throws IOException {
        values();
        return this.f294e - this.unregisterClient;
    }

    private void AFInAppEventType() {
        long[] jArr = this.AFKeystoreWrapper;
        long[] jArr2 = this.valueOf;
        short s = this.AFInAppEventType;
        long j = jArr[s % 4] * 2147483085;
        long j2 = jArr2[(s + 2) % 4];
        int i = (s + 3) % 4;
        jArr2[i] = ((jArr[i] * 2147483085) + j2) / 2147483647L;
        jArr[i] = (j + j2) % 2147483647L;
        for (int i2 = 0; i2 < this.values; i2++) {
            this.AFInAppEventParameterName[i2] = (byte) (r1[i2] ^ ((this.AFKeystoreWrapper[this.AFInAppEventType] >> (i2 << 3)) & 255));
        }
        this.AFInAppEventType = (short) ((this.AFInAppEventType + 1) % 4);
    }

    private int values() throws IOException {
        int i;
        if (this.AFLogger == Integer.MAX_VALUE) {
            this.AFLogger = ((FilterInputStream) this).in.read();
        }
        if (this.unregisterClient == this.values) {
            byte[] bArr = this.AFInAppEventParameterName;
            int i2 = this.AFLogger;
            bArr[0] = (byte) i2;
            if (i2 < 0) {
                throw new IllegalStateException("unexpected block size");
            }
            int i3 = 1;
            do {
                int read = ((FilterInputStream) this).in.read(this.AFInAppEventParameterName, i3, this.values - i3);
                if (read <= 0) {
                    break;
                }
                i3 += read;
            } while (i3 < this.values);
            if (i3 < this.values) {
                throw new IllegalStateException("unexpected block size");
            }
            AFInAppEventType();
            int read2 = ((FilterInputStream) this).in.read();
            this.AFLogger = read2;
            this.unregisterClient = 0;
            if (read2 < 0) {
                int i4 = this.values;
                i = i4 - (this.AFInAppEventParameterName[i4 - 1] & 255);
            } else {
                i = this.values;
            }
            this.f294e = i;
        }
        return this.f294e;
    }
}
