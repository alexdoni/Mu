package com.linecorp.linesdk.api.internal;

import com.linecorp.linesdk.LineAccessToken;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.api.LineApiClient;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class AutoRefreshLineApiClientProxy {
    private AutoRefreshLineApiClientProxy() {
    }

    public static LineApiClient newProxy(LineApiClient lineApiClient) {
        return (LineApiClient) Proxy.newProxyInstance(lineApiClient.getClass().getClassLoader(), new Class[]{LineApiClient.class}, new TokenAutoRefreshInvocationHandler(lineApiClient));
    }

    /* loaded from: classes2.dex */
    private static class TokenAutoRefreshInvocationHandler implements InvocationHandler {
        private final Map<Method, Boolean> autoRefreshStateCache;
        private final LineApiClient target;

        private TokenAutoRefreshInvocationHandler(LineApiClient lineApiClient) {
            this.target = lineApiClient;
            this.autoRefreshStateCache = new ConcurrentHashMap(0);
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                Object invoke = method.invoke(this.target, objArr);
                if (!isAutoRefreshEnabled(method) || !shouldRefreshToken(invoke)) {
                    return invoke;
                }
                LineApiResponse<LineAccessToken> refreshAccessToken = this.target.refreshAccessToken();
                if (!refreshAccessToken.isSuccess()) {
                    return refreshAccessToken.isNetworkError() ? refreshAccessToken : invoke;
                }
                try {
                    return method.invoke(this.target, objArr);
                } catch (InvocationTargetException e) {
                    throw e.getTargetException();
                }
            } catch (InvocationTargetException e2) {
                throw e2.getTargetException();
            }
        }

        private static boolean shouldRefreshToken(Object obj) {
            return (obj instanceof LineApiResponse) && ((LineApiResponse) obj).getErrorData().getHttpResponseCode() == 401;
        }

        private boolean isAutoRefreshEnabled(Method method) {
            Boolean bool = this.autoRefreshStateCache.get(method);
            if (bool != null) {
                return bool.booleanValue();
            }
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> cls = this.target.getClass(); cls != null; cls = cls.getSuperclass()) {
                if (((TokenAutoRefresh) cls.getDeclaredMethod(name, parameterTypes).getAnnotation(TokenAutoRefresh.class)) != null) {
                    this.autoRefreshStateCache.put(method, true);
                    return true;
                }
                continue;
            }
            this.autoRefreshStateCache.put(method, false);
            return false;
        }
    }
}
