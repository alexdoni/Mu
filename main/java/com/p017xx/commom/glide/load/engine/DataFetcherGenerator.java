package com.p017xx.commom.glide.load.engine;

import com.p017xx.commom.glide.load.DataSource;
import com.p017xx.commom.glide.load.Key;
import com.p017xx.commom.glide.load.data.DataFetcher;

/* loaded from: classes3.dex */
interface DataFetcherGenerator {

    /* loaded from: classes3.dex */
    public interface FetcherReadyCallback {
        void onDataFetcherFailed(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource);

        void onDataFetcherReady(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2);

        void reschedule();
    }

    void cancel();

    boolean startNext();
}
