package com.xsdk.ab_firstbase.statisics.util.json;

import java.io.IOException;
import java.io.Reader;

/* loaded from: classes3.dex */
public class StringTextReader extends Reader {
    private int length;
    private String str;
    private int next = 0;
    private int mark = 0;

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    public StringTextReader(String str) {
        this.str = str;
        this.length = str.length();
    }

    private void ensureOpen() throws IOException {
        if (this.str == null) {
            throw new IOException("Stream closed");
        }
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            int i = this.next;
            if (i >= this.length) {
                return -1;
            }
            String str = this.str;
            this.next = i + 1;
            return str.charAt(i);
        }
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        synchronized (this.lock) {
            ensureOpen();
            if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (i2 == 0) {
                return 0;
            }
            int i4 = this.next;
            int i5 = this.length;
            if (i4 >= i5) {
                return -1;
            }
            int min = Math.min(i5 - i4, i2);
            String str = this.str;
            int i6 = this.next;
            str.getChars(i6, i6 + min, cArr, i);
            this.next += min;
            return min;
        }
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            if (this.next >= this.length) {
                return 0L;
            }
            long max = Math.max(-this.next, Math.min(r2 - r1, j));
            this.next = (int) (this.next + max);
            return max;
        }
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        synchronized (this.lock) {
            ensureOpen();
        }
        return true;
    }

    @Override // java.io.Reader
    public void mark(int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("Read-ahead limit < 0");
        }
        synchronized (this.lock) {
            ensureOpen();
            this.mark = this.next;
        }
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            this.next = this.mark;
        }
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.str = null;
    }

    public int peek() throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            int i = this.next;
            if (i >= this.length) {
                return -1;
            }
            return this.str.charAt(i);
        }
    }
}
