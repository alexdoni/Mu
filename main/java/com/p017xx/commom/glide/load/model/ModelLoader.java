package com.p017xx.commom.glide.load.model;

import com.p017xx.commom.glide.load.Key;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.data.DataFetcher;
import com.p017xx.commom.glide.util.Preconditions;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public interface ModelLoader<Model, Data> {
    LoadData<Data> buildLoadData(Model model, int i, int i2, Options options);

    boolean handles(Model model);

    /* loaded from: classes3.dex */
    public static class LoadData<Data> {
        public final List<Key> alternateKeys;
        public final DataFetcher<Data> fetcher;
        public final Key sourceKey;

        public LoadData(Key key, DataFetcher<Data> dataFetcher) {
            this(key, Collections.emptyList(), dataFetcher);
        }

        public LoadData(Key key, List<Key> list, DataFetcher<Data> dataFetcher) {
            this.sourceKey = (Key) Preconditions.checkNotNull(key);
            this.alternateKeys = (List) Preconditions.checkNotNull(list);
            this.fetcher = (DataFetcher) Preconditions.checkNotNull(dataFetcher);
        }
    }
}
