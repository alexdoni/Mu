package top.zibin.luban;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public interface InputStreamProvider {
    void close();

    int getIndex();

    String getPath();

    InputStream open() throws IOException;
}
