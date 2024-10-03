package com.xsdk.ab_firstbase.net.http;

import com.xsdk.ab_firstbase.net.okhttp3.OkHttpManger;
import java.util.Map;

/* loaded from: classes3.dex */
public class GetRequest extends HttpRequest {
    public GetRequest(String str, Object obj, Map<String, Object> map, String str2, Map<String, String> map2) {
        super(str, obj, map, str2, map2);
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequest
    public RequestCall build() {
        return new RequestCall(this, OkHttpManger.GetMethod);
    }
}
