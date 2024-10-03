package com.p017xx.commom.glide.load.model;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.p017xx.commom.glide.Priority;
import com.p017xx.commom.glide.load.DataSource;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.data.DataFetcher;
import com.p017xx.commom.glide.load.model.ModelLoader;
import com.p017xx.commom.glide.signature.ObjectKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class FileLoader<Data> implements ModelLoader<File, Data> {
    private static final String TAG = "FileLoader";
    private final FileOpener<Data> fileOpener;

    /* loaded from: classes3.dex */
    public interface FileOpener<Data> {
        void close(Data data) throws IOException;

        Class<Data> getDataClass();

        Data open(File file) throws FileNotFoundException;
    }

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public boolean handles(File file) {
        return true;
    }

    public FileLoader(FileOpener<Data> fileOpener) {
        this.fileOpener = fileOpener;
    }

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Data> buildLoadData(File file, int i, int i2, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(file), new FileFetcher(file, this.fileOpener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class FileFetcher<Data> implements DataFetcher<Data> {
        private Data data;
        private final File file;
        private final FileOpener<Data> opener;

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public void cancel() {
        }

        FileFetcher(File file, FileOpener<Data> fileOpener) {
            this.file = file;
            this.opener = fileOpener;
        }

        /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Object, Data] */
        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public void loadData(Priority priority, DataFetcher.DataCallback<? super Data> dataCallback) {
            try {
                Data open = this.opener.open(this.file);
                this.data = open;
                dataCallback.onDataReady(open);
            } catch (FileNotFoundException e) {
                if (Log.isLoggable(FileLoader.TAG, 3)) {
                    Log.d(FileLoader.TAG, "Failed to open file", e);
                }
                dataCallback.onLoadFailed(e);
            }
        }

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public void cleanup() {
            Data data = this.data;
            if (data != null) {
                try {
                    this.opener.close(data);
                } catch (IOException unused) {
                }
            }
        }

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public Class<Data> getDataClass() {
            return this.opener.getDataClass();
        }

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }

    /* loaded from: classes3.dex */
    public static class Factory<Data> implements ModelLoaderFactory<File, Data> {
        private final FileOpener<Data> opener;

        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public final void teardown() {
        }

        public Factory(FileOpener<Data> fileOpener) {
            this.opener = fileOpener;
        }

        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public final ModelLoader<File, Data> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new FileLoader(this.opener);
        }
    }

    /* loaded from: classes3.dex */
    public static class StreamFactory extends Factory<InputStream> {
        public StreamFactory() {
            super(new FileOpener<InputStream>() { // from class: com.xx.commom.glide.load.model.FileLoader.StreamFactory.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.xx.commom.glide.load.model.FileLoader.FileOpener
                public InputStream open(File file) throws FileNotFoundException {
                    return new FileInputStream(file);
                }

                @Override // com.xx.commom.glide.load.model.FileLoader.FileOpener
                public void close(InputStream inputStream) throws IOException {
                    inputStream.close();
                }

                @Override // com.xx.commom.glide.load.model.FileLoader.FileOpener
                public Class<InputStream> getDataClass() {
                    return InputStream.class;
                }
            });
        }
    }

    /* loaded from: classes3.dex */
    public static class FileDescriptorFactory extends Factory<ParcelFileDescriptor> {
        public FileDescriptorFactory() {
            super(new FileOpener<ParcelFileDescriptor>() { // from class: com.xx.commom.glide.load.model.FileLoader.FileDescriptorFactory.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.xx.commom.glide.load.model.FileLoader.FileOpener
                public ParcelFileDescriptor open(File file) throws FileNotFoundException {
                    return ParcelFileDescriptor.open(file, 268435456);
                }

                @Override // com.xx.commom.glide.load.model.FileLoader.FileOpener
                public void close(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
                    parcelFileDescriptor.close();
                }

                @Override // com.xx.commom.glide.load.model.FileLoader.FileOpener
                public Class<ParcelFileDescriptor> getDataClass() {
                    return ParcelFileDescriptor.class;
                }
            });
        }
    }
}
