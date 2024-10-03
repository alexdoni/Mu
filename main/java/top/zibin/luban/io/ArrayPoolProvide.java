package top.zibin.luban.io;

import android.content.ContentResolver;
import android.net.Uri;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class ArrayPoolProvide {
    private static ArrayPoolProvide mInstance;
    private final HashSet<String> keyCache = new HashSet<>();
    private final ConcurrentHashMap<String, BufferedInputStreamWrap> bufferedLruCache = new ConcurrentHashMap<>();
    private final LruArrayPool arrayPool = new LruArrayPool(4194304);

    public byte[] get(int i) {
        return (byte[]) this.arrayPool.get(i, byte[].class);
    }

    public void put(byte[] bArr) {
        this.arrayPool.put(bArr);
    }

    public InputStream openInputStream(ContentResolver contentResolver, Uri uri) {
        try {
            try {
                BufferedInputStreamWrap bufferedInputStreamWrap = this.bufferedLruCache.get(uri.toString());
                if (bufferedInputStreamWrap != null) {
                    bufferedInputStreamWrap.reset();
                } else {
                    bufferedInputStreamWrap = wrapInputStream(contentResolver, uri);
                }
                return bufferedInputStreamWrap;
            } catch (Exception unused) {
                return contentResolver.openInputStream(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return wrapInputStream(contentResolver, uri);
        }
    }

    public InputStream openInputStream(String str) {
        try {
            BufferedInputStreamWrap bufferedInputStreamWrap = this.bufferedLruCache.get(str);
            if (bufferedInputStreamWrap != null) {
                bufferedInputStreamWrap.reset();
            } else {
                bufferedInputStreamWrap = wrapInputStream(str);
            }
            return bufferedInputStreamWrap;
        } catch (Exception unused) {
            return wrapInputStream(str);
        }
    }

    private BufferedInputStreamWrap wrapInputStream(ContentResolver contentResolver, Uri uri) {
        BufferedInputStreamWrap bufferedInputStreamWrap = null;
        try {
            BufferedInputStreamWrap bufferedInputStreamWrap2 = new BufferedInputStreamWrap(contentResolver.openInputStream(uri));
            try {
                int available = bufferedInputStreamWrap2.available();
                if (available <= 0) {
                    available = 8388608;
                }
                bufferedInputStreamWrap2.mark(available);
                this.bufferedLruCache.put(uri.toString(), bufferedInputStreamWrap2);
                this.keyCache.add(uri.toString());
                return bufferedInputStreamWrap2;
            } catch (Exception e) {
                e = e;
                bufferedInputStreamWrap = bufferedInputStreamWrap2;
                e.printStackTrace();
                return bufferedInputStreamWrap;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private BufferedInputStreamWrap wrapInputStream(String str) {
        BufferedInputStreamWrap bufferedInputStreamWrap = null;
        try {
            BufferedInputStreamWrap bufferedInputStreamWrap2 = new BufferedInputStreamWrap(new FileInputStream(str));
            try {
                int available = bufferedInputStreamWrap2.available();
                if (available <= 0) {
                    available = 8388608;
                }
                bufferedInputStreamWrap2.mark(available);
                this.bufferedLruCache.put(str, bufferedInputStreamWrap2);
                this.keyCache.add(str);
                return bufferedInputStreamWrap2;
            } catch (Exception e) {
                e = e;
                bufferedInputStreamWrap = bufferedInputStreamWrap2;
                e.printStackTrace();
                return bufferedInputStreamWrap;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void clearMemory() {
        Iterator<String> it = this.keyCache.iterator();
        while (it.hasNext()) {
            String next = it.next();
            close(this.bufferedLruCache.get(next));
            this.bufferedLruCache.remove(next);
        }
        this.keyCache.clear();
        this.arrayPool.clearMemory();
    }

    public static ArrayPoolProvide getInstance() {
        if (mInstance == null) {
            synchronized (ArrayPoolProvide.class) {
                if (mInstance == null) {
                    mInstance = new ArrayPoolProvide();
                }
            }
        }
        return mInstance;
    }

    public static void close(Closeable closeable) {
        if (closeable instanceof Closeable) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }
}
