package com.tencent.sharpgme.jni;

/* loaded from: classes3.dex */
public class RingBuffer {
    private final int DEFAULT_SIZE;
    public int c_totalSize;
    public boolean m_isEmpty;
    public byte[] m_pBuf;
    public int m_read;
    public int m_write;

    public RingBuffer() {
        this.DEFAULT_SIZE = 1000;
        this.c_totalSize = 1000;
        this.m_isEmpty = true;
        this.m_read = 0;
        this.m_write = 0;
        this.m_pBuf = new byte[1000];
    }

    public RingBuffer(int i) {
        this.DEFAULT_SIZE = 1000;
        this.c_totalSize = i;
        this.m_isEmpty = true;
        this.m_read = 0;
        this.m_write = 0;
        this.m_pBuf = new byte[i];
    }

    public void Push(byte[] bArr, int i) {
        if (this.m_pBuf != null && RemainWrite() >= i) {
            int i2 = this.c_totalSize;
            int i3 = this.m_write;
            if (i2 - i3 >= i) {
                System.arraycopy(bArr, 0, this.m_pBuf, i3, i);
            } else {
                System.arraycopy(bArr, 0, this.m_pBuf, i3, i2 - i3);
                int i4 = this.c_totalSize;
                int i5 = this.m_write;
                System.arraycopy(bArr, i4 - i5, this.m_pBuf, 0, i - (i4 - i5));
            }
            this.m_write = (this.m_write + i) % this.c_totalSize;
            this.m_isEmpty = false;
        }
    }

    public int RemainRead() {
        int i = this.m_write;
        int i2 = this.m_read;
        if (i < i2) {
            return (this.c_totalSize - i2) + i;
        }
        if (i > i2) {
            return i - i2;
        }
        if (this.m_isEmpty) {
            return 0;
        }
        return this.c_totalSize;
    }

    public int RemainWrite() {
        return this.c_totalSize - RemainRead();
    }

    public void Clear() {
        this.m_write = 0;
        this.m_read = 0;
        this.m_isEmpty = true;
    }

    public boolean Pop(byte[] bArr, int i) {
        if (this.m_pBuf == null || RemainRead() < i || i <= 0) {
            return false;
        }
        int i2 = this.c_totalSize;
        int i3 = this.m_read;
        if (i2 - i3 >= i) {
            System.arraycopy(this.m_pBuf, i3, bArr, 0, i);
        } else {
            System.arraycopy(this.m_pBuf, i3, bArr, 0, i2 - i3);
            byte[] bArr2 = this.m_pBuf;
            int i4 = this.c_totalSize;
            int i5 = this.m_read;
            System.arraycopy(bArr2, 0, bArr, i4 - i5, i - (i4 - i5));
        }
        int i6 = (this.m_read + i) % this.c_totalSize;
        this.m_read = i6;
        this.m_isEmpty = i6 == this.m_write;
        return true;
    }
}
