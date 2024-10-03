package com.appsflyer.internal;

import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.asn1.cmc.BodyPartID;

/* loaded from: classes.dex */
public final class AFj1mSDK extends FilterInputStream {
    private static final short values = (short) ((Math.sqrt(5.0d) - 1.0d) * Math.pow(2.0d, 15.0d));
    private byte[] AFInAppEventParameterName;
    private byte[] AFInAppEventType;
    private int AFKeystoreWrapper;
    private int AFLogger;
    private int afInfoLog;

    /* renamed from: d */
    private int f289d;

    /* renamed from: e */
    private int f290e;
    private int force;

    /* renamed from: i */
    private int f291i;
    private int registerClient;
    private int unregisterClient;
    private byte[] valueOf;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final boolean markSupported() {
        return false;
    }

    public AFj1mSDK(InputStream inputStream, int[] iArr, int i, byte[] bArr, int i2, int i3) throws IOException {
        super(new BufferedInputStream(inputStream, 4096));
        this.f289d = Integer.MAX_VALUE;
        this.valueOf = new byte[8];
        this.AFInAppEventType = new byte[8];
        this.AFInAppEventParameterName = new byte[8];
        this.AFKeystoreWrapper = 8;
        this.registerClient = 8;
        this.AFLogger = Math.min(Math.max(i2, 5), 16);
        this.unregisterClient = i3;
        if (i3 == 3) {
            System.arraycopy(bArr, 0, this.AFInAppEventType, 0, 8);
        }
        long j = ((iArr[0] & BodyPartID.bodyIdMax) << 32) | (BodyPartID.bodyIdMax & iArr[1]);
        if (i != 0) {
            int i4 = (int) j;
            this.f290e = i4;
            this.f291i = i4 * i;
            this.afInfoLog = i4 ^ i;
            this.force = (int) (j >> 32);
            return;
        }
        this.f290e = (int) j;
        long j2 = j >> 3;
        short s = values;
        this.f291i = (int) ((s * j2) >> 32);
        this.afInfoLog = (int) (j >> 32);
        this.force = (int) (j2 + s);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        AFInAppEventType();
        int i = this.AFKeystoreWrapper;
        if (i >= this.registerClient) {
            return -1;
        }
        byte[] bArr = this.valueOf;
        this.AFKeystoreWrapper = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            AFInAppEventType();
            int i5 = this.AFKeystoreWrapper;
            if (i5 >= this.registerClient) {
                if (i4 == i) {
                    return -1;
                }
                return i2 - (i3 - i4);
            }
            byte[] bArr2 = this.valueOf;
            this.AFKeystoreWrapper = i5 + 1;
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
        AFInAppEventType();
        return this.registerClient - this.AFKeystoreWrapper;
    }

    private void values() {
        if (this.unregisterClient == 3) {
            byte[] bArr = this.valueOf;
            System.arraycopy(bArr, 0, this.AFInAppEventParameterName, 0, bArr.length);
        }
        byte[] bArr2 = this.valueOf;
        int i = ((bArr2[0] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) + ((bArr2[1] << 16) & 16711680) + ((bArr2[2] << 8) & 65280) + (bArr2[3] & 255);
        int i2 = ((-16777216) & (bArr2[4] << Ascii.CAN)) + (16711680 & (bArr2[5] << 16)) + (65280 & (bArr2[6] << 8)) + (bArr2[7] & 255);
        int i3 = 0;
        while (true) {
            int i4 = this.AFLogger;
            if (i3 >= i4) {
                break;
            }
            short s = values;
            i2 -= ((((i4 - i3) * s) + i) ^ ((i << 4) + this.afInfoLog)) ^ ((i >>> 5) + this.force);
            i -= (((i2 << 4) + this.f290e) ^ ((s * (i4 - i3)) + i2)) ^ ((i2 >>> 5) + this.f291i);
            i3++;
        }
        byte[] bArr3 = this.valueOf;
        bArr3[0] = (byte) (i >> 24);
        bArr3[1] = (byte) (i >> 16);
        bArr3[2] = (byte) (i >> 8);
        bArr3[3] = (byte) i;
        bArr3[4] = (byte) (i2 >> 24);
        bArr3[5] = (byte) (i2 >> 16);
        bArr3[6] = (byte) (i2 >> 8);
        bArr3[7] = (byte) i2;
        if (this.unregisterClient == 3) {
            for (int i5 = 0; i5 < 8; i5++) {
                byte[] bArr4 = this.valueOf;
                bArr4[i5] = (byte) (bArr4[i5] ^ this.AFInAppEventType[i5]);
            }
            byte[] bArr5 = this.AFInAppEventParameterName;
            System.arraycopy(bArr5, 0, this.AFInAppEventType, 0, bArr5.length);
        }
    }

    private int AFInAppEventType() throws IOException {
        if (this.f289d == Integer.MAX_VALUE) {
            this.f289d = ((FilterInputStream) this).in.read();
        }
        if (this.AFKeystoreWrapper == 8) {
            byte[] bArr = this.valueOf;
            int i = this.f289d;
            bArr[0] = (byte) i;
            if (i < 0) {
                throw new IllegalStateException("unexpected block size");
            }
            int i2 = 1;
            do {
                int read = ((FilterInputStream) this).in.read(this.valueOf, i2, 8 - i2);
                if (read <= 0) {
                    break;
                }
                i2 += read;
            } while (i2 < 8);
            if (i2 < 8) {
                throw new IllegalStateException("unexpected block size");
            }
            values();
            int read2 = ((FilterInputStream) this).in.read();
            this.f289d = read2;
            this.AFKeystoreWrapper = 0;
            this.registerClient = read2 < 0 ? 8 - (this.valueOf[7] & 255) : 8;
        }
        return this.registerClient;
    }
}
