package com.p017xx.commom.glide;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.p017xx.commom.glide.gifdecoder.GifDecoder;
import com.p017xx.commom.glide.load.DecodeFormat;
import com.p017xx.commom.glide.load.ImageHeaderParser;
import com.p017xx.commom.glide.load.ResourceDecoder;
import com.p017xx.commom.glide.load.ResourceEncoder;
import com.p017xx.commom.glide.load.data.InputStreamRewinder;
import com.p017xx.commom.glide.load.engine.Engine;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayPool;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import com.p017xx.commom.glide.load.engine.cache.MemoryCache;
import com.p017xx.commom.glide.load.engine.prefill.BitmapPreFiller;
import com.p017xx.commom.glide.load.engine.prefill.PreFillType;
import com.p017xx.commom.glide.load.model.AssetUriLoader;
import com.p017xx.commom.glide.load.model.ByteArrayLoader;
import com.p017xx.commom.glide.load.model.ByteBufferEncoder;
import com.p017xx.commom.glide.load.model.ByteBufferFileLoader;
import com.p017xx.commom.glide.load.model.DataUrlLoader;
import com.p017xx.commom.glide.load.model.FileLoader;
import com.p017xx.commom.glide.load.model.GlideUrl;
import com.p017xx.commom.glide.load.model.MediaStoreFileLoader;
import com.p017xx.commom.glide.load.model.ResourceLoader;
import com.p017xx.commom.glide.load.model.StreamEncoder;
import com.p017xx.commom.glide.load.model.StringLoader;
import com.p017xx.commom.glide.load.model.UnitModelLoader;
import com.p017xx.commom.glide.load.model.UriLoader;
import com.p017xx.commom.glide.load.model.UrlUriLoader;
import com.p017xx.commom.glide.load.model.stream.HttpGlideUrlLoader;
import com.p017xx.commom.glide.load.model.stream.HttpUriLoader;
import com.p017xx.commom.glide.load.model.stream.MediaStoreImageThumbLoader;
import com.p017xx.commom.glide.load.model.stream.MediaStoreVideoThumbLoader;
import com.p017xx.commom.glide.load.model.stream.UrlLoader;
import com.p017xx.commom.glide.load.resource.bitmap.BitmapDrawableDecoder;
import com.p017xx.commom.glide.load.resource.bitmap.BitmapDrawableEncoder;
import com.p017xx.commom.glide.load.resource.bitmap.BitmapEncoder;
import com.p017xx.commom.glide.load.resource.bitmap.ByteBufferBitmapDecoder;
import com.p017xx.commom.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.p017xx.commom.glide.load.resource.bitmap.Downsampler;
import com.p017xx.commom.glide.load.resource.bitmap.ExifInterfaceImageHeaderParser;
import com.p017xx.commom.glide.load.resource.bitmap.ResourceBitmapDecoder;
import com.p017xx.commom.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.p017xx.commom.glide.load.resource.bitmap.UnitBitmapDecoder;
import com.p017xx.commom.glide.load.resource.bitmap.VideoDecoder;
import com.p017xx.commom.glide.load.resource.bytes.ByteBufferRewinder;
import com.p017xx.commom.glide.load.resource.drawable.ResourceDrawableDecoder;
import com.p017xx.commom.glide.load.resource.drawable.UnitDrawableDecoder;
import com.p017xx.commom.glide.load.resource.file.FileDecoder;
import com.p017xx.commom.glide.load.resource.gif.ByteBufferGifDecoder;
import com.p017xx.commom.glide.load.resource.gif.GifDrawable;
import com.p017xx.commom.glide.load.resource.gif.GifDrawableEncoder;
import com.p017xx.commom.glide.load.resource.gif.GifFrameResourceDecoder;
import com.p017xx.commom.glide.load.resource.gif.StreamGifDecoder;
import com.p017xx.commom.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.p017xx.commom.glide.load.resource.transcode.BitmapDrawableTranscoder;
import com.p017xx.commom.glide.load.resource.transcode.DrawableBytesTranscoder;
import com.p017xx.commom.glide.load.resource.transcode.GifDrawableBytesTranscoder;
import com.p017xx.commom.glide.manager.ConnectivityMonitorFactory;
import com.p017xx.commom.glide.manager.RequestManagerRetriever;
import com.p017xx.commom.glide.module.GlideModule;
import com.p017xx.commom.glide.module.ManifestParser;
import com.p017xx.commom.glide.request.RequestListener;
import com.p017xx.commom.glide.request.RequestOptions;
import com.p017xx.commom.glide.request.target.ImageViewTargetFactory;
import com.p017xx.commom.glide.request.target.Target;
import com.p017xx.commom.glide.util.Preconditions;
import com.p017xx.commom.glide.util.Util;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class Glide implements ComponentCallbacks2 {
    private static final String DEFAULT_DISK_CACHE_DIR = "image_manager_disk_cache";
    private static final String TAG = "Glide";
    private static volatile Glide glide;
    private static volatile boolean isInitializing;
    private final ArrayPool arrayPool;
    private final BitmapPool bitmapPool;
    private final BitmapPreFiller bitmapPreFiller;
    private final ConnectivityMonitorFactory connectivityMonitorFactory;
    private final Engine engine;
    private final GlideContext glideContext;
    private final MemoryCache memoryCache;
    private final Registry registry;
    private final RequestManagerRetriever requestManagerRetriever;
    private final List<RequestManager> managers = new ArrayList();
    private MemoryCategory memoryCategory = MemoryCategory.NORMAL;

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    public static File getPhotoCacheDir(Context context) {
        return getPhotoCacheDir(context, "image_manager_disk_cache");
    }

    public static File getPhotoCacheDir(Context context, String str) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File file = new File(cacheDir, str);
            if (file.mkdirs() || (file.exists() && file.isDirectory())) {
                return file;
            }
            return null;
        }
        if (Log.isLoggable(TAG, 6)) {
            Log.e(TAG, "default disk cache dir is null");
        }
        return null;
    }

    public static Glide get(Context context) {
        if (glide == null) {
            synchronized (Glide.class) {
                if (glide == null) {
                    checkAndInitializeGlide(context);
                }
            }
        }
        return glide;
    }

    private static void checkAndInitializeGlide(Context context) {
        if (isInitializing) {
            throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
        }
        isInitializing = true;
        initializeGlide(context);
        isInitializing = false;
    }

    @Deprecated
    public static synchronized void init(Glide glide2) {
        synchronized (Glide.class) {
            if (glide != null) {
                tearDown();
            }
            glide = glide2;
        }
    }

    public static synchronized void init(Context context, GlideBuilder glideBuilder) {
        synchronized (Glide.class) {
            if (glide != null) {
                tearDown();
            }
            initializeGlide(context, glideBuilder);
        }
    }

    public static synchronized void tearDown() {
        synchronized (Glide.class) {
            if (glide != null) {
                glide.getContext().getApplicationContext().unregisterComponentCallbacks(glide);
                glide.engine.shutdown();
            }
            glide = null;
        }
    }

    private static void initializeGlide(Context context) {
        initializeGlide(context, new GlideBuilder());
    }

    private static void initializeGlide(Context context, GlideBuilder glideBuilder) {
        Context applicationContext = context.getApplicationContext();
        GeneratedAppGlideModule annotationGeneratedGlideModules = getAnnotationGeneratedGlideModules();
        List<GlideModule> emptyList = Collections.emptyList();
        if (annotationGeneratedGlideModules == null || annotationGeneratedGlideModules.isManifestParsingEnabled()) {
            emptyList = new ManifestParser(applicationContext).parse();
        }
        if (annotationGeneratedGlideModules != null && !annotationGeneratedGlideModules.getExcludedModuleClasses().isEmpty()) {
            Set<Class<?>> excludedModuleClasses = annotationGeneratedGlideModules.getExcludedModuleClasses();
            Iterator<GlideModule> it = emptyList.iterator();
            while (it.hasNext()) {
                GlideModule next = it.next();
                if (excludedModuleClasses.contains(next.getClass())) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "AppGlideModule excludes manifest GlideModule: " + next);
                    }
                    it.remove();
                }
            }
        }
        if (Log.isLoggable(TAG, 3)) {
            Iterator<GlideModule> it2 = emptyList.iterator();
            while (it2.hasNext()) {
                Log.d(TAG, "Discovered GlideModule from manifest: " + it2.next().getClass());
            }
        }
        glideBuilder.setRequestManagerFactory(annotationGeneratedGlideModules != null ? annotationGeneratedGlideModules.getRequestManagerFactory() : null);
        Iterator<GlideModule> it3 = emptyList.iterator();
        while (it3.hasNext()) {
            it3.next().applyOptions(applicationContext, glideBuilder);
        }
        if (annotationGeneratedGlideModules != null) {
            annotationGeneratedGlideModules.applyOptions(applicationContext, glideBuilder);
        }
        Glide build = glideBuilder.build(applicationContext);
        Iterator<GlideModule> it4 = emptyList.iterator();
        while (it4.hasNext()) {
            it4.next().registerComponents(applicationContext, build, build.registry);
        }
        if (annotationGeneratedGlideModules != null) {
            annotationGeneratedGlideModules.registerComponents(applicationContext, build, build.registry);
        }
        applicationContext.registerComponentCallbacks(build);
        glide = build;
    }

    private static GeneratedAppGlideModule getAnnotationGeneratedGlideModules() {
        try {
            return (GeneratedAppGlideModule) Class.forName("com.xx.commom.glide.GeneratedAppGlideModuleImpl").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
            return null;
        } catch (IllegalAccessException e) {
            throwIncorrectGlideModule(e);
            return null;
        } catch (InstantiationException e2) {
            throwIncorrectGlideModule(e2);
            return null;
        } catch (NoSuchMethodException e3) {
            throwIncorrectGlideModule(e3);
            return null;
        } catch (InvocationTargetException e4) {
            throwIncorrectGlideModule(e4);
            return null;
        }
    }

    private static void throwIncorrectGlideModule(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Glide(Context context, Engine engine, MemoryCache memoryCache, BitmapPool bitmapPool, ArrayPool arrayPool, RequestManagerRetriever requestManagerRetriever, ConnectivityMonitorFactory connectivityMonitorFactory, int i, RequestOptions requestOptions, Map<Class<?>, TransitionOptions<?, ?>> map, List<RequestListener<Object>> list, boolean z) {
        this.engine = engine;
        this.bitmapPool = bitmapPool;
        this.arrayPool = arrayPool;
        this.memoryCache = memoryCache;
        this.requestManagerRetriever = requestManagerRetriever;
        this.connectivityMonitorFactory = connectivityMonitorFactory;
        this.bitmapPreFiller = new BitmapPreFiller(memoryCache, bitmapPool, (DecodeFormat) requestOptions.getOptions().get(Downsampler.DECODE_FORMAT));
        Resources resources = context.getResources();
        Registry registry = new Registry();
        this.registry = registry;
        registry.register(new DefaultImageHeaderParser());
        if (Build.VERSION.SDK_INT >= 27) {
            registry.register(new ExifInterfaceImageHeaderParser());
        }
        List<ImageHeaderParser> imageHeaderParsers = registry.getImageHeaderParsers();
        Downsampler downsampler = new Downsampler(imageHeaderParsers, resources.getDisplayMetrics(), bitmapPool, arrayPool);
        ByteBufferGifDecoder byteBufferGifDecoder = new ByteBufferGifDecoder(context, imageHeaderParsers, bitmapPool, arrayPool);
        ResourceDecoder<ParcelFileDescriptor, Bitmap> parcel = VideoDecoder.parcel(bitmapPool);
        ByteBufferBitmapDecoder byteBufferBitmapDecoder = new ByteBufferBitmapDecoder(downsampler);
        StreamBitmapDecoder streamBitmapDecoder = new StreamBitmapDecoder(downsampler, arrayPool);
        ResourceDrawableDecoder resourceDrawableDecoder = new ResourceDrawableDecoder(context);
        ResourceLoader.StreamFactory streamFactory = new ResourceLoader.StreamFactory(resources);
        ResourceLoader.UriFactory uriFactory = new ResourceLoader.UriFactory(resources);
        ResourceLoader.FileDescriptorFactory fileDescriptorFactory = new ResourceLoader.FileDescriptorFactory(resources);
        ResourceLoader.AssetFileDescriptorFactory assetFileDescriptorFactory = new ResourceLoader.AssetFileDescriptorFactory(resources);
        BitmapEncoder bitmapEncoder = new BitmapEncoder(arrayPool);
        BitmapBytesTranscoder bitmapBytesTranscoder = new BitmapBytesTranscoder();
        GifDrawableBytesTranscoder gifDrawableBytesTranscoder = new GifDrawableBytesTranscoder();
        ContentResolver contentResolver = context.getContentResolver();
        registry.append(ByteBuffer.class, new ByteBufferEncoder()).append(InputStream.class, new StreamEncoder(arrayPool)).append("Bitmap", ByteBuffer.class, Bitmap.class, byteBufferBitmapDecoder).append("Bitmap", InputStream.class, Bitmap.class, streamBitmapDecoder).append("Bitmap", ParcelFileDescriptor.class, Bitmap.class, parcel).append("Bitmap", AssetFileDescriptor.class, Bitmap.class, VideoDecoder.asset(bitmapPool)).append(Bitmap.class, Bitmap.class, UnitModelLoader.Factory.getInstance()).append("Bitmap", Bitmap.class, Bitmap.class, new UnitBitmapDecoder()).append(Bitmap.class, (ResourceEncoder) bitmapEncoder).append("BitmapDrawable", ByteBuffer.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, byteBufferBitmapDecoder)).append("BitmapDrawable", InputStream.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, streamBitmapDecoder)).append("BitmapDrawable", ParcelFileDescriptor.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, parcel)).append(BitmapDrawable.class, (ResourceEncoder) new BitmapDrawableEncoder(bitmapPool, bitmapEncoder)).append(Registry.BUCKET_GIF, InputStream.class, GifDrawable.class, new StreamGifDecoder(imageHeaderParsers, byteBufferGifDecoder, arrayPool)).append(Registry.BUCKET_GIF, ByteBuffer.class, GifDrawable.class, byteBufferGifDecoder).append(GifDrawable.class, (ResourceEncoder) new GifDrawableEncoder()).append(GifDecoder.class, GifDecoder.class, UnitModelLoader.Factory.getInstance()).append("Bitmap", GifDecoder.class, Bitmap.class, new GifFrameResourceDecoder(bitmapPool)).append(Uri.class, Drawable.class, resourceDrawableDecoder).append(Uri.class, Bitmap.class, new ResourceBitmapDecoder(resourceDrawableDecoder, bitmapPool)).register(new ByteBufferRewinder.Factory()).append(File.class, ByteBuffer.class, new ByteBufferFileLoader.Factory()).append(File.class, InputStream.class, new FileLoader.StreamFactory()).append(File.class, File.class, new FileDecoder()).append(File.class, ParcelFileDescriptor.class, new FileLoader.FileDescriptorFactory()).append(File.class, File.class, UnitModelLoader.Factory.getInstance()).register(new InputStreamRewinder.Factory(arrayPool)).append(Integer.TYPE, InputStream.class, streamFactory).append(Integer.TYPE, ParcelFileDescriptor.class, fileDescriptorFactory).append(Integer.class, InputStream.class, streamFactory).append(Integer.class, ParcelFileDescriptor.class, fileDescriptorFactory).append(Integer.class, Uri.class, uriFactory).append(Integer.TYPE, AssetFileDescriptor.class, assetFileDescriptorFactory).append(Integer.class, AssetFileDescriptor.class, assetFileDescriptorFactory).append(Integer.TYPE, Uri.class, uriFactory).append(String.class, InputStream.class, new DataUrlLoader.StreamFactory()).append(Uri.class, InputStream.class, new DataUrlLoader.StreamFactory()).append(String.class, InputStream.class, new StringLoader.StreamFactory()).append(String.class, ParcelFileDescriptor.class, new StringLoader.FileDescriptorFactory()).append(String.class, AssetFileDescriptor.class, new StringLoader.AssetFileDescriptorFactory()).append(Uri.class, InputStream.class, new HttpUriLoader.Factory()).append(Uri.class, InputStream.class, new AssetUriLoader.StreamFactory(context.getAssets())).append(Uri.class, ParcelFileDescriptor.class, new AssetUriLoader.FileDescriptorFactory(context.getAssets())).append(Uri.class, InputStream.class, new MediaStoreImageThumbLoader.Factory(context)).append(Uri.class, InputStream.class, new MediaStoreVideoThumbLoader.Factory(context)).append(Uri.class, InputStream.class, new UriLoader.StreamFactory(contentResolver)).append(Uri.class, ParcelFileDescriptor.class, new UriLoader.FileDescriptorFactory(contentResolver)).append(Uri.class, AssetFileDescriptor.class, new UriLoader.AssetFileDescriptorFactory(contentResolver)).append(Uri.class, InputStream.class, new UrlUriLoader.StreamFactory()).append(URL.class, InputStream.class, new UrlLoader.StreamFactory()).append(Uri.class, File.class, new MediaStoreFileLoader.Factory(context)).append(GlideUrl.class, InputStream.class, new HttpGlideUrlLoader.Factory()).append(byte[].class, ByteBuffer.class, new ByteArrayLoader.ByteBufferFactory()).append(byte[].class, InputStream.class, new ByteArrayLoader.StreamFactory()).append(Uri.class, Uri.class, UnitModelLoader.Factory.getInstance()).append(Drawable.class, Drawable.class, UnitModelLoader.Factory.getInstance()).append(Drawable.class, Drawable.class, new UnitDrawableDecoder()).register(Bitmap.class, BitmapDrawable.class, new BitmapDrawableTranscoder(resources)).register(Bitmap.class, byte[].class, bitmapBytesTranscoder).register(Drawable.class, byte[].class, new DrawableBytesTranscoder(bitmapPool, bitmapBytesTranscoder, gifDrawableBytesTranscoder)).register(GifDrawable.class, byte[].class, gifDrawableBytesTranscoder);
        this.glideContext = new GlideContext(context, arrayPool, registry, new ImageViewTargetFactory(), requestOptions, map, list, engine, z, i);
    }

    public BitmapPool getBitmapPool() {
        return this.bitmapPool;
    }

    public ArrayPool getArrayPool() {
        return this.arrayPool;
    }

    public Context getContext() {
        return this.glideContext.getBaseContext();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectivityMonitorFactory getConnectivityMonitorFactory() {
        return this.connectivityMonitorFactory;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GlideContext getGlideContext() {
        return this.glideContext;
    }

    public void preFillBitmapPool(PreFillType.Builder... builderArr) {
        this.bitmapPreFiller.preFill(builderArr);
    }

    public void clearMemory() {
        Util.assertMainThread();
        this.memoryCache.clearMemory();
        this.bitmapPool.clearMemory();
        this.arrayPool.clearMemory();
    }

    public void trimMemory(int i) {
        Util.assertMainThread();
        this.memoryCache.trimMemory(i);
        this.bitmapPool.trimMemory(i);
        this.arrayPool.trimMemory(i);
    }

    public void clearDiskCache() {
        Util.assertBackgroundThread();
        this.engine.clearDiskCache();
    }

    public RequestManagerRetriever getRequestManagerRetriever() {
        return this.requestManagerRetriever;
    }

    public MemoryCategory setMemoryCategory(MemoryCategory memoryCategory) {
        Util.assertMainThread();
        this.memoryCache.setSizeMultiplier(memoryCategory.getMultiplier());
        this.bitmapPool.setSizeMultiplier(memoryCategory.getMultiplier());
        MemoryCategory memoryCategory2 = this.memoryCategory;
        this.memoryCategory = memoryCategory;
        return memoryCategory2;
    }

    private static RequestManagerRetriever getRetriever(Context context) {
        Preconditions.checkNotNull(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return get(context).getRequestManagerRetriever();
    }

    public static RequestManager with(Context context) {
        return getRetriever(context).get(context);
    }

    public static RequestManager with(Activity activity) {
        return getRetriever(activity).get(activity);
    }

    public static RequestManager with(FragmentActivity fragmentActivity) {
        return getRetriever(fragmentActivity).get(fragmentActivity);
    }

    public static RequestManager with(Fragment fragment) {
        return getRetriever(fragment.getActivity()).get(fragment);
    }

    @Deprecated
    public static RequestManager with(android.app.Fragment fragment) {
        return getRetriever(fragment.getActivity()).get(fragment);
    }

    public static RequestManager with(View view) {
        return getRetriever(view.getContext()).get(view);
    }

    public Registry getRegistry() {
        return this.registry;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean removeFromManagers(Target<?> target) {
        synchronized (this.managers) {
            Iterator<RequestManager> it = this.managers.iterator();
            while (it.hasNext()) {
                if (it.next().untrack(target)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerRequestManager(RequestManager requestManager) {
        synchronized (this.managers) {
            if (this.managers.contains(requestManager)) {
                throw new IllegalStateException("Cannot register already registered manager");
            }
            this.managers.add(requestManager);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unregisterRequestManager(RequestManager requestManager) {
        synchronized (this.managers) {
            if (!this.managers.contains(requestManager)) {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
            this.managers.remove(requestManager);
        }
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        trimMemory(i);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        clearMemory();
    }
}
