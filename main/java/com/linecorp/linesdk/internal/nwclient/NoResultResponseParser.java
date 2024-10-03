package com.linecorp.linesdk.internal.nwclient;

import com.linecorp.linesdk.internal.nwclient.core.ResponseDataParser;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class NoResultResponseParser implements ResponseDataParser<Object> {
    private static final Object NO_RESULT = new Object();

    @Override // com.linecorp.linesdk.internal.nwclient.core.ResponseDataParser
    public Object getResponseData(InputStream inputStream) throws IOException {
        return NO_RESULT;
    }
}
