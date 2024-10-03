package top.zibin.luban.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class BufferedInputStreamWrap extends FilterInputStream {
    public static final int DEFAULT_MARK_READ_LIMIT = 8388608;
    private volatile byte[] buf;
    private int count;
    private int markLimit;
    private int markPos;
    private int pos;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    public BufferedInputStreamWrap(InputStream inputStream) {
        this(inputStream, 65536);
    }

    BufferedInputStreamWrap(InputStream inputStream, int i) {
        super(inputStream);
        this.markPos = -1;
        this.buf = ArrayPoolProvide.getInstance().get(i);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() throws IOException {
        InputStream inputStream = this.in;
        if (this.buf != null && inputStream != null) {
            return (this.count - this.pos) + inputStream.available();
        }
        return 0;
    }

    private static IOException streamClosed() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    public synchronized void fixMarkLimit() {
        this.markLimit = this.buf.length;
    }

    public synchronized void release() {
        if (this.buf != null) {
            ArrayPoolProvide.getInstance().put(this.buf);
            this.buf = null;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.buf != null) {
            ArrayPoolProvide.getInstance().put(this.buf);
            this.buf = null;
        }
        InputStream inputStream = this.in;
        this.in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    private int fillbuf(InputStream inputStream, byte[] bArr) throws IOException {
        int i = this.markPos;
        if (i != -1) {
            int i2 = this.pos - i;
            int i3 = this.markLimit;
            if (i2 < i3) {
                if (i == 0 && i3 > bArr.length && this.count == bArr.length) {
                    int length = bArr.length * 2;
                    if (length <= i3) {
                        i3 = length;
                    }
                    byte[] bArr2 = ArrayPoolProvide.getInstance().get(i3);
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    this.buf = bArr2;
                    ArrayPoolProvide.getInstance().put(bArr);
                    bArr = bArr2;
                } else if (i > 0) {
                    System.arraycopy(bArr, i, bArr, 0, bArr.length - i);
                }
                int i4 = this.pos - this.markPos;
                this.pos = i4;
                this.markPos = 0;
                this.count = 0;
                int read = inputStream.read(bArr, i4, bArr.length - i4);
                int i5 = this.pos;
                if (read > 0) {
                    i5 += read;
                }
                this.count = i5;
                return read;
            }
        }
        int read2 = inputStream.read(bArr);
        if (read2 > 0) {
            this.markPos = -1;
            this.pos = 0;
            this.count = read2;
        }
        return read2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        this.markLimit = Math.max(this.markLimit, i);
        this.markPos = this.pos;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() throws IOException {
        byte[] bArr = this.buf;
        InputStream inputStream = this.in;
        if (bArr == null || inputStream == null) {
            throw streamClosed();
        }
        if (this.pos >= this.count && fillbuf(inputStream, bArr) == -1) {
            return -1;
        }
        if (bArr != this.buf && (bArr = this.buf) == null) {
            throw streamClosed();
        }
        int i = this.count;
        int i2 = this.pos;
        if (i - i2 <= 0) {
            return -1;
        }
        this.pos = i2 + 1;
        return bArr[i2] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int min;
        byte[] bArr2 = this.buf;
        if (bArr2 == null) {
            throw streamClosed();
        }
        if (i2 == 0) {
            return 0;
        }
        InputStream inputStream = this.in;
        if (inputStream == null) {
            throw streamClosed();
        }
        int i4 = this.pos;
        int i5 = this.count;
        if (i4 < i5) {
            int min2 = Math.min(i5 - i4, i2);
            System.arraycopy(bArr2, this.pos, bArr, i, min2);
            this.pos += min2;
            if (min2 == i2 || inputStream.available() == 0) {
                return min2;
            }
            i += min2;
            i3 = i2 - min2;
        } else {
            i3 = i2;
        }
        while (true) {
            if (this.markPos == -1 && i3 >= bArr2.length) {
                min = inputStream.read(bArr, i, i3);
                if (min == -1) {
                    return i3 != i2 ? i2 - i3 : -1;
                }
            } else {
                if (fillbuf(inputStream, bArr2) == -1) {
                    return i3 != i2 ? i2 - i3 : -1;
                }
                if (bArr2 != this.buf && (bArr2 = this.buf) == null) {
                    throw streamClosed();
                }
                min = Math.min(this.count - this.pos, i3);
                System.arraycopy(bArr2, this.pos, bArr, i, min);
                this.pos += min;
            }
            i3 -= min;
            if (i3 == 0) {
                return i2;
            }
            if (inputStream.available() == 0) {
                return i2 - i3;
            }
            i += min;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this.buf == null) {
            throw new IOException("Stream is closed");
        }
        int i = this.markPos;
        if (-1 == i) {
            throw new InvalidMarkException("Mark has been invalidated, pos: " + this.pos + " markLimit: " + this.markLimit);
        }
        this.pos = i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j) throws IOException {
        if (j < 1) {
            return 0L;
        }
        byte[] bArr = this.buf;
        if (bArr == null) {
            throw streamClosed();
        }
        InputStream inputStream = this.in;
        if (inputStream == null) {
            throw streamClosed();
        }
        int i = this.count;
        int i2 = this.pos;
        if (i - i2 >= j) {
            this.pos = (int) (i2 + j);
            return j;
        }
        long j2 = i - i2;
        this.pos = i;
        if (this.markPos != -1 && j <= this.markLimit) {
            if (fillbuf(inputStream, bArr) == -1) {
                return j2;
            }
            int i3 = this.count;
            int i4 = this.pos;
            if (i3 - i4 >= j - j2) {
                this.pos = (int) ((i4 + j) - j2);
                return j;
            }
            long j3 = (j2 + i3) - i4;
            this.pos = i3;
            return j3;
        }
        return j2 + inputStream.skip(j - j2);
    }

    /* loaded from: classes3.dex */
    static class InvalidMarkException extends IOException {
        private static final long serialVersionUID = -4338378848813561759L;

        InvalidMarkException(String str) {
            super(str);
        }
    }
}
