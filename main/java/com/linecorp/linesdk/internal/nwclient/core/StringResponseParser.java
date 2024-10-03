package com.linecorp.linesdk.internal.nwclient.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes2.dex */
public class StringResponseParser implements ResponseDataParser<String> {
    private static final String DEFAULT_CHARSET_NAME = "UTF-8";
    private final String charsetName;

    public StringResponseParser() {
        this("UTF-8");
    }

    public StringResponseParser(String str) {
        this.charsetName = str;
    }

    @Override // com.linecorp.linesdk.internal.nwclient.core.ResponseDataParser
    public String getResponseData(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, this.charsetName));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    } else {
                        bufferedReader2.close();
                        return sb.toString();
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
