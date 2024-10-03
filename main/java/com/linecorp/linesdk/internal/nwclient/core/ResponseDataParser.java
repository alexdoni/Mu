package com.linecorp.linesdk.internal.nwclient.core;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public interface ResponseDataParser<T> {
    T getResponseData(InputStream inputStream) throws IOException;
}
