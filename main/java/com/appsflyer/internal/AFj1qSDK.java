package com.appsflyer.internal;

import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public final class AFj1qSDK extends FilterInputStream {
    private byte[] AFInAppEventParameterName;
    private AFj1tSDK AFInAppEventType;
    private byte[] AFKeystoreWrapper;
    private int AFLogger;

    /* renamed from: d */
    private int f292d;

    /* renamed from: e */
    private int f293e;
    private int[] registerClient;
    private int unregisterClient;
    private final int valueOf;
    private byte[] values;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final boolean markSupported() {
        return false;
    }

    public AFj1qSDK(InputStream inputStream, int[] iArr, byte[] bArr, int i, boolean z, int i2) throws IOException {
        super(new BufferedInputStream(inputStream, 4096));
        this.f292d = Integer.MAX_VALUE;
        int min = Math.min(Math.max(i, 3), 16);
        this.valueOf = min;
        this.AFKeystoreWrapper = new byte[8];
        byte[] bArr2 = new byte[8];
        this.AFInAppEventParameterName = bArr2;
        this.values = new byte[8];
        this.registerClient = new int[2];
        this.unregisterClient = 8;
        this.f293e = 8;
        this.AFLogger = i2;
        if (i2 == 2) {
            System.arraycopy(bArr, 0, bArr2, 0, 8);
        }
        this.AFInAppEventType = new AFj1tSDK(iArr, min, true, false);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        AFKeystoreWrapper();
        int i = this.unregisterClient;
        if (i >= this.f293e) {
            return -1;
        }
        byte[] bArr = this.AFKeystoreWrapper;
        this.unregisterClient = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            AFKeystoreWrapper();
            int i5 = this.unregisterClient;
            if (i5 >= this.f293e) {
                if (i4 == i) {
                    return -1;
                }
                return i2 - (i3 - i4);
            }
            byte[] bArr2 = this.AFKeystoreWrapper;
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
        AFKeystoreWrapper();
        return this.f293e - this.unregisterClient;
    }

    private void AFInAppEventType() {
        if (this.AFLogger == 2) {
            byte[] bArr = this.AFKeystoreWrapper;
            System.arraycopy(bArr, 0, this.values, 0, bArr.length);
        }
        byte[] bArr2 = this.AFKeystoreWrapper;
        AFj1sSDK.valueOf(((bArr2[0] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) + ((bArr2[1] << 16) & 16711680) + ((bArr2[2] << 8) & 65280) + (bArr2[3] & 255), ((-16777216) & (bArr2[4] << Ascii.CAN)) + (16711680 & (bArr2[5] << 16)) + (65280 & (bArr2[6] << 8)) + (bArr2[7] & 255), false, this.valueOf, this.AFInAppEventType.AFInAppEventParameterName, this.AFInAppEventType.AFKeystoreWrapper, this.registerClient);
        int[] iArr = this.registerClient;
        int i = iArr[0];
        int i2 = iArr[1];
        byte[] bArr3 = this.AFKeystoreWrapper;
        bArr3[0] = (byte) (i >> 24);
        bArr3[1] = (byte) (i >> 16);
        bArr3[2] = (byte) (i >> 8);
        bArr3[3] = (byte) i;
        bArr3[4] = (byte) (i2 >> 24);
        bArr3[5] = (byte) (i2 >> 16);
        bArr3[6] = (byte) (i2 >> 8);
        bArr3[7] = (byte) i2;
        if (this.AFLogger == 2) {
            for (int i3 = 0; i3 < 8; i3++) {
                byte[] bArr4 = this.AFKeystoreWrapper;
                bArr4[i3] = (byte) (bArr4[i3] ^ this.AFInAppEventParameterName[i3]);
            }
            byte[] bArr5 = this.values;
            System.arraycopy(bArr5, 0, this.AFInAppEventParameterName, 0, bArr5.length);
        }
    }

    private int AFKeystoreWrapper() throws IOException {
        if (this.f292d == Integer.MAX_VALUE) {
            this.f292d = ((FilterInputStream) this).in.read();
        }
        if (this.unregisterClient == 8) {
            byte[] bArr = this.AFKeystoreWrapper;
            int i = this.f292d;
            bArr[0] = (byte) i;
            if (i < 0) {
                throw new IllegalStateException("unexpected block size");
            }
            int i2 = 1;
            do {
                int read = ((FilterInputStream) this).in.read(this.AFKeystoreWrapper, i2, 8 - i2);
                if (read <= 0) {
                    break;
                }
                i2 += read;
            } while (i2 < 8);
            if (i2 < 8) {
                throw new IllegalStateException("unexpected block size");
            }
            AFInAppEventType();
            int read2 = ((FilterInputStream) this).in.read();
            this.f292d = read2;
            this.unregisterClient = 0;
            this.f293e = read2 < 0 ? 8 - (this.AFKeystoreWrapper[7] & 255) : 8;
        }
        return this.f293e;
    }
}
