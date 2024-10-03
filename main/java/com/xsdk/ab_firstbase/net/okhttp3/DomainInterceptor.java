package com.xsdk.ab_firstbase.net.okhttp3;

import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes3.dex */
public class DomainInterceptor implements Interceptor {
    private int RetryCount = 0;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Response doRequest = doRequest(chain, request);
        this.RetryCount = DomainUtils.getInstance().getUrlList(request.url().getUrl()).size();
        for (int i = 0; doRequest == null && i < this.RetryCount; i++) {
            LLog.e_Control("DomainInterceptor:Request is not successful - " + i);
            try {
                doRequest = doRequest(chain, request.newBuilder().url(switchServer(request, i)).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doRequest == null ? chain.proceed(request) : doRequest;
    }

    private Response doRequest(Interceptor.Chain chain, Request request) {
        try {
            return chain.proceed(request);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private HttpUrl switchServer(Request request, int i) {
        HttpUrl url = request.url();
        LLog.e_Control("DomainInterceptor:域名失效的链接为 - " + request.url().getUrl());
        try {
            String str = DomainUtils.getInstance().getUrlList(request.url().getUrl()).get(i);
            DomainUtils.getInstance().updateUrl(str);
            URI uri = new URL(str).toURI();
            HttpUrl build = url.newBuilder().scheme(uri.getScheme()).host(uri.getHost()).build();
            LLog.e_Control("DomainInterceptor:域名切换的链接为 - " + build.url().toString());
            return build;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return url;
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            return url;
        } catch (URISyntaxException e3) {
            e3.printStackTrace();
            return url;
        }
    }
}
