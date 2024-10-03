package com.p017xx.commom.glide.load.model;

import com.p017xx.commom.glide.load.model.LazyHeaders;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes3.dex */
public interface Headers {

    @Deprecated
    public static final Headers NONE = new Headers() { // from class: com.xx.commom.glide.load.model.Headers.1
        @Override // com.p017xx.commom.glide.load.model.Headers
        public Map<String, String> getHeaders() {
            return Collections.emptyMap();
        }
    };
    public static final Headers DEFAULT = new LazyHeaders.Builder().build();

    Map<String, String> getHeaders();
}
