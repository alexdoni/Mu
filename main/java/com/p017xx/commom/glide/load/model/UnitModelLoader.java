package com.p017xx.commom.glide.load.model;

import com.p017xx.commom.glide.Priority;
import com.p017xx.commom.glide.load.DataSource;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.data.DataFetcher;
import com.p017xx.commom.glide.load.model.ModelLoader;
import com.p017xx.commom.glide.signature.ObjectKey;

/* loaded from: classes3.dex */
public class UnitModelLoader<Model> implements ModelLoader<Model, Model> {
    private static final UnitModelLoader<?> INSTANCE = new UnitModelLoader<>();

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public boolean handles(Model model) {
        return true;
    }

    public static <T> UnitModelLoader<T> getInstance() {
        return (UnitModelLoader<T>) INSTANCE;
    }

    @Deprecated
    public UnitModelLoader() {
    }

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Model> buildLoadData(Model model, int i, int i2, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(model), new UnitFetcher(model));
    }

    /* loaded from: classes3.dex */
    private static class UnitFetcher<Model> implements DataFetcher<Model> {
        private final Model resource;

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public void cancel() {
        }

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public void cleanup() {
        }

        UnitFetcher(Model model) {
            this.resource = model;
        }

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public void loadData(Priority priority, DataFetcher.DataCallback<? super Model> dataCallback) {
            dataCallback.onDataReady(this.resource);
        }

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public Class<Model> getDataClass() {
            return (Class<Model>) this.resource.getClass();
        }

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }

    /* loaded from: classes3.dex */
    public static class Factory<Model> implements ModelLoaderFactory<Model, Model> {
        private static final Factory<?> FACTORY = new Factory<>();

        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        public static <T> Factory<T> getInstance() {
            return (Factory<T>) FACTORY;
        }

        @Deprecated
        public Factory() {
        }

        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public ModelLoader<Model, Model> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return UnitModelLoader.getInstance();
        }
    }
}
