package com.linecorp.linesdk.internal.nwclient.core;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.google.common.net.HttpHeaders;
import com.linecorp.android.security.TLSSocketFactory;
import com.linecorp.linesdk.LineApiError;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.LineApiResponseCode;
import com.linecorp.linesdk.utils.UriUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes2.dex */
public class ChannelServiceHttpClient {
    private static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 90000;
    private static final int DEFAULT_READ_TIMEOUT_MILLIS = 90000;
    private static final byte[] EMPTY_DATA = new byte[0];
    private static final String SERVER_SIDE_CHARSET = "UTF-8";
    private static final String TAG = "ChannelHttpClient";
    private int connectTimeoutMillis;
    private final StringResponseParser errorResponseParser;
    private int readTimeoutMillis;
    private final UserAgentGenerator userAgentGenerator;

    private static void logExceptionForDebug(LineApiResponse<?> lineApiResponse, Exception exc) {
    }

    public ChannelServiceHttpClient(Context context, String str) {
        this(new UserAgentGenerator(context, str));
    }

    protected ChannelServiceHttpClient(UserAgentGenerator userAgentGenerator) {
        this.userAgentGenerator = userAgentGenerator;
        this.errorResponseParser = new StringResponseParser("UTF-8");
        this.connectTimeoutMillis = 90000;
        this.readTimeoutMillis = 90000;
    }

    public void setConnectTimeoutMillis(int i) {
        this.connectTimeoutMillis = i;
    }

    public void setReadTimeoutMillis(int i) {
        this.readTimeoutMillis = i;
    }

    public <T> LineApiResponse<T> post(Uri uri, Map<String, String> map, Map<String, String> map2, ResponseDataParser<T> responseDataParser) {
        byte[] convertPostDataToBytes = convertPostDataToBytes(map2);
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                httpURLConnection = openPostConnection(uri, convertPostDataToBytes.length);
                setRequestHeaders(httpURLConnection, map);
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(convertPostDataToBytes);
                outputStream.flush();
                LineApiResponse<T> channelServiceResponse = getChannelServiceResponse(httpURLConnection, responseDataParser, this.errorResponseParser);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return channelServiceResponse;
            } catch (IOException e) {
                LineApiResponse<T> createAsError = LineApiResponse.createAsError(LineApiResponseCode.NETWORK_ERROR, new LineApiError(e));
                logExceptionForDebug(createAsError, e);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return createAsError;
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public <T> LineApiResponse<T> postWithJson(Uri uri, Map<String, String> map, String str, ResponseDataParser<T> responseDataParser) {
        byte[] bytes = str.getBytes();
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                httpURLConnection = openPostConnectionWithJson(uri, bytes.length);
                setRequestHeaders(httpURLConnection, map);
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bytes);
                outputStream.flush();
                LineApiResponse<T> channelServiceResponse = getChannelServiceResponse(httpURLConnection, responseDataParser, this.errorResponseParser);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return channelServiceResponse;
            } catch (IOException e) {
                LineApiResponse<T> createAsError = LineApiResponse.createAsError(LineApiResponseCode.NETWORK_ERROR, new LineApiError(e));
                logExceptionForDebug(createAsError, e);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return createAsError;
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public <T> LineApiResponse<T> get(Uri uri, Map<String, String> map, Map<String, String> map2, ResponseDataParser<T> responseDataParser) {
        Uri appendQueryParams = UriUtils.appendQueryParams(uri, map2);
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                httpURLConnection = openGetConnection(appendQueryParams);
                setRequestHeaders(httpURLConnection, map);
                httpURLConnection.connect();
                LineApiResponse<T> channelServiceResponse = getChannelServiceResponse(httpURLConnection, responseDataParser, this.errorResponseParser);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return channelServiceResponse;
            } catch (IOException e) {
                LineApiResponse<T> createAsError = LineApiResponse.createAsError(LineApiResponseCode.NETWORK_ERROR, new LineApiError(e));
                logExceptionForDebug(createAsError, e);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return createAsError;
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public <T> LineApiResponse<T> delete(Uri uri, Map<String, String> map, ResponseDataParser<T> responseDataParser) {
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                httpURLConnection = openDeleteConnection(uri);
                setRequestHeaders(httpURLConnection, map);
                httpURLConnection.connect();
                LineApiResponse<T> channelServiceResponse = getChannelServiceResponse(httpURLConnection, responseDataParser, this.errorResponseParser);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return channelServiceResponse;
            } catch (IOException e) {
                LineApiResponse<T> createAsError = LineApiResponse.createAsError(LineApiResponseCode.NETWORK_ERROR, new LineApiError(e));
                logExceptionForDebug(createAsError, e);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return createAsError;
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private HttpURLConnection openPostConnection(Uri uri, int i) throws IOException {
        HttpURLConnection openHttpConnection = openHttpConnection(uri);
        openHttpConnection.setInstanceFollowRedirects(true);
        openHttpConnection.setRequestProperty("User-Agent", this.userAgentGenerator.getUserAgent());
        openHttpConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "gzip");
        openHttpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        openHttpConnection.setRequestProperty("Content-Length", String.valueOf(i));
        openHttpConnection.setConnectTimeout(this.connectTimeoutMillis);
        openHttpConnection.setReadTimeout(this.readTimeoutMillis);
        openHttpConnection.setRequestMethod("POST");
        openHttpConnection.setDoOutput(true);
        return openHttpConnection;
    }

    private HttpURLConnection openPostConnectionWithJson(Uri uri, int i) throws IOException {
        HttpURLConnection openHttpConnection = openHttpConnection(uri);
        openHttpConnection.setInstanceFollowRedirects(true);
        openHttpConnection.setRequestProperty("User-Agent", this.userAgentGenerator.getUserAgent());
        openHttpConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "gzip");
        openHttpConnection.setRequestProperty("Content-Type", "application/json");
        openHttpConnection.setRequestProperty("Content-Length", String.valueOf(i));
        openHttpConnection.setConnectTimeout(this.connectTimeoutMillis);
        openHttpConnection.setReadTimeout(this.readTimeoutMillis);
        openHttpConnection.setRequestMethod("POST");
        openHttpConnection.setDoOutput(true);
        return openHttpConnection;
    }

    private HttpURLConnection openGetConnection(Uri uri) throws IOException {
        HttpURLConnection openHttpConnection = openHttpConnection(uri);
        openHttpConnection.setInstanceFollowRedirects(true);
        openHttpConnection.setRequestProperty("User-Agent", this.userAgentGenerator.getUserAgent());
        openHttpConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "gzip");
        openHttpConnection.setConnectTimeout(this.connectTimeoutMillis);
        openHttpConnection.setReadTimeout(this.readTimeoutMillis);
        openHttpConnection.setRequestMethod("GET");
        return openHttpConnection;
    }

    private HttpURLConnection openDeleteConnection(Uri uri) throws IOException {
        HttpURLConnection openHttpConnection = openHttpConnection(uri);
        openHttpConnection.setInstanceFollowRedirects(true);
        openHttpConnection.setRequestProperty("User-Agent", this.userAgentGenerator.getUserAgent());
        openHttpConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "gzip");
        openHttpConnection.setConnectTimeout(this.connectTimeoutMillis);
        openHttpConnection.setReadTimeout(this.readTimeoutMillis);
        openHttpConnection.setRequestMethod("DELETE");
        return openHttpConnection;
    }

    protected HttpURLConnection openHttpConnection(Uri uri) throws IOException {
        URLConnection openConnection = new URL(uri.toString()).openConnection();
        if (!(openConnection instanceof HttpsURLConnection)) {
            throw new IllegalArgumentException("The scheme of the server url must be https." + uri);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return (HttpURLConnection) openConnection;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
        httpsURLConnection.setSSLSocketFactory(new TLSSocketFactory(httpsURLConnection.getSSLSocketFactory()));
        return httpsURLConnection;
    }

    private static void setRequestHeaders(HttpURLConnection httpURLConnection, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    private static byte[] convertPostDataToBytes(Map<String, String> map) {
        if (map.isEmpty()) {
            return EMPTY_DATA;
        }
        try {
            return UriUtils.appendQueryParams("", map).getEncodedQuery().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> LineApiResponse<T> getChannelServiceResponse(HttpURLConnection httpURLConnection, ResponseDataParser<T> responseDataParser, ResponseDataParser<String> responseDataParser2) throws IOException {
        InputStream inputStreamFrom = getInputStreamFrom(httpURLConnection);
        int responseCode = httpURLConnection.getResponseCode();
        try {
            if (responseCode != 200) {
                return LineApiResponse.createAsError(LineApiResponseCode.SERVER_ERROR, new LineApiError(responseCode, responseDataParser2.getResponseData(inputStreamFrom)));
            }
            return LineApiResponse.createAsSuccess(responseDataParser.getResponseData(inputStreamFrom));
        } catch (IOException e) {
            return LineApiResponse.createAsError(LineApiResponseCode.INTERNAL_ERROR, new LineApiError(responseCode, e));
        }
    }

    private static InputStream getInputStreamFrom(HttpURLConnection httpURLConnection) throws IOException {
        InputStream errorStream;
        if (httpURLConnection.getResponseCode() < 400) {
            errorStream = httpURLConnection.getInputStream();
        } else {
            errorStream = httpURLConnection.getErrorStream();
        }
        return isGzipUsed(httpURLConnection) ? new GZIPInputStream(errorStream) : errorStream;
    }

    private static boolean isGzipUsed(HttpURLConnection httpURLConnection) {
        List<String> list = httpURLConnection.getHeaderFields().get("Content-Encoding");
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("gzip")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void logRequestForDebug(HttpURLConnection httpURLConnection, byte[] bArr) {
        Log.d(TAG, httpURLConnection.getRequestMethod() + " : " + httpURLConnection.getURL());
        for (Map.Entry<String, List<String>> entry : httpURLConnection.getRequestProperties().entrySet()) {
            Log.d(TAG, "    " + entry.getKey() + " : " + Arrays.toString(entry.getValue().toArray()));
        }
        if (bArr != null) {
            try {
                Log.d(TAG, "== Request body ==");
                Log.d(TAG, new String(bArr, "utf-8"));
            } catch (UnsupportedEncodingException unused) {
            }
        }
    }

    private static void logResponseHeadersForDebug(HttpURLConnection httpURLConnection) throws IOException {
        Log.d(TAG, httpURLConnection.getResponseCode() + " : " + httpURLConnection.getResponseMessage());
        for (Map.Entry<String, List<String>> entry : httpURLConnection.getHeaderFields().entrySet()) {
            Log.d(TAG, "    " + entry.getKey() + " : " + Arrays.toString(entry.getValue().toArray()));
        }
    }

    private static InputStream logResponseBodyForDebug(InputStream inputStream) throws IOException {
        byte[] byteArray = toByteArray(inputStream);
        Log.d(TAG, "== response body ==");
        Log.d(TAG, new StringResponseParser().getResponseData((InputStream) new ByteArrayInputStream(byteArray)));
        return new ByteArrayInputStream(byteArray);
    }

    private static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read >= 0) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
