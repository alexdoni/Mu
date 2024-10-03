package com.linecorp.linesdk.internal.nwclient;

import com.linecorp.linesdk.internal.nwclient.core.JsonResponseParser;
import com.linecorp.linesdk.internal.nwclient.core.ResponseDataParser;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
abstract class JsonToObjectBaseResponseParser<T> implements ResponseDataParser<T> {
    private final JsonResponseParser jsonResponseParser;

    abstract T parseJsonToObject(JSONObject jSONObject) throws JSONException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JsonToObjectBaseResponseParser() {
        this(new JsonResponseParser());
    }

    JsonToObjectBaseResponseParser(String str) {
        this(new JsonResponseParser(str));
    }

    JsonToObjectBaseResponseParser(JsonResponseParser jsonResponseParser) {
        this.jsonResponseParser = jsonResponseParser;
    }

    @Override // com.linecorp.linesdk.internal.nwclient.core.ResponseDataParser
    public T getResponseData(InputStream inputStream) throws IOException {
        try {
            return parseJsonToObject(this.jsonResponseParser.getResponseData(inputStream));
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }
}
