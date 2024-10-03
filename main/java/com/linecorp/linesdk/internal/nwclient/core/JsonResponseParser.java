package com.linecorp.linesdk.internal.nwclient.core;

import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class JsonResponseParser implements ResponseDataParser<JSONObject> {
    private final StringResponseParser stringResponseParser;

    public JsonResponseParser() {
        this.stringResponseParser = new StringResponseParser();
    }

    public JsonResponseParser(String str) {
        this.stringResponseParser = new StringResponseParser(str);
    }

    @Override // com.linecorp.linesdk.internal.nwclient.core.ResponseDataParser
    public JSONObject getResponseData(InputStream inputStream) throws IOException {
        try {
            return new JSONObject(this.stringResponseParser.getResponseData(inputStream));
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }
}
