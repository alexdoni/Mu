package com.alibaba.sdk.android.oss.network;

import com.alibaba.sdk.android.oss.common.HttpMethod;
import com.alibaba.sdk.android.oss.internal.OSSRetryHandler;
import com.alibaba.sdk.android.oss.internal.RequestMessage;
import com.alibaba.sdk.android.oss.internal.ResponseMessage;
import com.alibaba.sdk.android.oss.internal.ResponseParser;
import com.alibaba.sdk.android.oss.model.OSSResult;
import java.util.HashMap;
import java.util.concurrent.Callable;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* loaded from: classes.dex */
public class OSSRequestTask<T extends OSSResult> implements Callable<T> {
    private OkHttpClient client;
    private ExecutionContext context;
    private int currentRetryCount = 0;
    private RequestMessage message;
    private ResponseParser<T> responseParser;
    private OSSRetryHandler retryHandler;

    public OSSRequestTask(RequestMessage requestMessage, ResponseParser responseParser, ExecutionContext executionContext, int i) {
        this.responseParser = responseParser;
        this.message = requestMessage;
        this.context = executionContext;
        this.client = executionContext.getClient();
        this.retryHandler = new OSSRetryHandler(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x0197 A[Catch: Exception -> 0x02cd, TryCatch #4 {Exception -> 0x02cd, blocks: (B:3:0x0004, B:5:0x000c, B:6:0x0019, B:8:0x003a, B:10:0x0043, B:11:0x0050, B:12:0x0062, B:14:0x0068, B:16:0x007f, B:27:0x01fc, B:112:0x00c6, B:113:0x00cc, B:114:0x00d2, B:117:0x00dd, B:119:0x00ea, B:123:0x0197, B:125:0x019f, B:126:0x01aa, B:128:0x01cb, B:129:0x01e8, B:130:0x0101, B:132:0x010b, B:135:0x0128, B:136:0x012f, B:137:0x0130, B:139:0x0138, B:145:0x0168, B:150:0x0172, B:151:0x0175, B:155:0x0176, B:157:0x017e, B:158:0x018c, B:160:0x004a, B:161:0x02c5, B:162:0x02cc), top: B:2:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x031b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.util.concurrent.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public T call() throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1091
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.network.OSSRequestTask.call():com.alibaba.sdk.android.oss.model.OSSResult");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.alibaba.sdk.android.oss.network.OSSRequestTask$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C06751 {
        static final /* synthetic */ int[] $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod;

        static {
            int[] iArr = new int[HttpMethod.values().length];
            $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod = iArr;
            try {
                iArr[HttpMethod.POST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[HttpMethod.PUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[HttpMethod.GET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[HttpMethod.HEAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[HttpMethod.DELETE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private ResponseMessage buildResponseMessage(RequestMessage requestMessage, Response response) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRequest(requestMessage);
        responseMessage.setResponse(response);
        HashMap hashMap = new HashMap();
        Headers headers = response.headers();
        for (int i = 0; i < headers.size(); i++) {
            hashMap.put(headers.name(i), headers.value(i));
        }
        responseMessage.setHeaders(hashMap);
        responseMessage.setStatusCode(response.code());
        responseMessage.setContentLength(response.body().getContentLength());
        responseMessage.setContent(response.body().byteStream());
        return responseMessage;
    }
}
