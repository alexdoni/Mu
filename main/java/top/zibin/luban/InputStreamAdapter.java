package top.zibin.luban;

import java.io.IOException;
import java.io.InputStream;
import top.zibin.luban.io.ArrayPoolProvide;

/* loaded from: classes3.dex */
public abstract class InputStreamAdapter implements InputStreamProvider {
    public abstract InputStream openInternal() throws IOException;

    @Override // top.zibin.luban.InputStreamProvider
    public InputStream open() throws IOException {
        return openInternal();
    }

    @Override // top.zibin.luban.InputStreamProvider
    public void close() {
        ArrayPoolProvide.getInstance().clearMemory();
    }
}
