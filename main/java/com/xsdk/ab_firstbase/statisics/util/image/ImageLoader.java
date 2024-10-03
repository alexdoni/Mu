package com.xsdk.ab_firstbase.statisics.util.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import com.xsdk.ab_firstbase.statisics.util.image.ImageSizeUtil;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/* loaded from: classes3.dex */
public class ImageLoader {
    private static final int DEAFULT_THREAD_COUNT = 1;
    private static final String TAG = "ImageLoader";
    private static ImageLoader mInstance;
    private LruCache<String, Bitmap> mLruCache;
    private Thread mPoolThread;
    private Handler mPoolThreadHandler;
    private Semaphore mSemaphoreThreadPool;
    private LinkedList<Runnable> mTaskQueue;
    private ExecutorService mThreadPool;
    private Handler mUIHandler;
    private Type mType = Type.LIFO;
    private Semaphore mSemaphorePoolThreadHandler = new Semaphore(0);
    private boolean isDiskCacheEnable = true;

    /* loaded from: classes3.dex */
    public enum Type {
        FIFO,
        LIFO
    }

    private ImageLoader(int i, Type type) {
        init(i, type);
    }

    private void init(int i, Type type) {
        initBackThread();
        this.mLruCache = new LruCache<String, Bitmap>(((int) Runtime.getRuntime().maxMemory()) / 8) { // from class: com.xsdk.ab_firstbase.statisics.util.image.ImageLoader.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            public int sizeOf(String str, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
        this.mThreadPool = Executors.newFixedThreadPool(i);
        this.mTaskQueue = new LinkedList<>();
        this.mType = type;
        this.mSemaphoreThreadPool = new Semaphore(i);
    }

    private void initBackThread() {
        Thread thread = new Thread() { // from class: com.xsdk.ab_firstbase.statisics.util.image.ImageLoader.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Looper.prepare();
                ImageLoader.this.mPoolThreadHandler = new Handler() { // from class: com.xsdk.ab_firstbase.statisics.util.image.ImageLoader.2.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        ImageLoader.this.mThreadPool.execute(ImageLoader.this.getTask());
                        try {
                            ImageLoader.this.mSemaphoreThreadPool.acquire();
                        } catch (InterruptedException unused) {
                        }
                    }
                };
                ImageLoader.this.mSemaphorePoolThreadHandler.release();
                Looper.loop();
            }
        };
        this.mPoolThread = thread;
        thread.start();
    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(1, Type.LIFO);
                }
            }
        }
        return mInstance;
    }

    public static ImageLoader getInstance(int i, Type type) {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(i, type);
                }
            }
        }
        return mInstance;
    }

    public void loadImage(String str, ImageView imageView, boolean z) {
        imageView.setTag(str);
        if (this.mUIHandler == null) {
            this.mUIHandler = new Handler() { // from class: com.xsdk.ab_firstbase.statisics.util.image.ImageLoader.3
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    ImgBeanHolder imgBeanHolder = (ImgBeanHolder) message.obj;
                    Bitmap bitmap = imgBeanHolder.bitmap;
                    ImageView imageView2 = imgBeanHolder.imageView;
                    if (imageView2.getTag().toString().equals(imgBeanHolder.path)) {
                        imageView2.setImageBitmap(bitmap);
                    }
                }
            };
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Bitmap bitmapFromLruCache = getBitmapFromLruCache(str);
        if (bitmapFromLruCache != null) {
            refreashBitmap(str, imageView, bitmapFromLruCache);
        } else {
            addTask(buildTask(str, imageView, z));
        }
    }

    private Runnable buildTask(final String str, final ImageView imageView, final boolean z) {
        return new Runnable() { // from class: com.xsdk.ab_firstbase.statisics.util.image.ImageLoader.4
            @Override // java.lang.Runnable
            public void run() {
                Bitmap loadImageFromLocal;
                if (!z) {
                    loadImageFromLocal = ImageLoader.this.loadImageFromLocal(str, imageView);
                } else {
                    File diskCacheDir = ImageLoader.this.getDiskCacheDir(imageView.getContext(), ImageLoader.this.md5(str));
                    if (!diskCacheDir.exists()) {
                        if (ImageLoader.this.isDiskCacheEnable) {
                            if (DownloadImgUtils.downloadImgByUrl(str, diskCacheDir)) {
                                Log.e(ImageLoader.TAG, "download image :" + str + " to disk cache . path is " + diskCacheDir.getAbsolutePath());
                                loadImageFromLocal = ImageLoader.this.loadImageFromLocal(diskCacheDir.getAbsolutePath(), imageView);
                            } else {
                                loadImageFromLocal = null;
                            }
                        } else {
                            Log.e(ImageLoader.TAG, "load image :" + str + " to memory.");
                            loadImageFromLocal = DownloadImgUtils.downloadImgByUrl(str, imageView);
                        }
                    } else {
                        Log.e(ImageLoader.TAG, "find image :" + str + " in disk cache .");
                        loadImageFromLocal = ImageLoader.this.loadImageFromLocal(diskCacheDir.getAbsolutePath(), imageView);
                    }
                }
                ImageLoader.this.addBitmapToLruCache(str, loadImageFromLocal);
                ImageLoader.this.refreashBitmap(str, imageView, loadImageFromLocal);
                ImageLoader.this.mSemaphoreThreadPool.release();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap loadImageFromLocal(String str, ImageView imageView) {
        ImageSizeUtil.ImageSize imageViewSize = ImageSizeUtil.getImageViewSize(imageView);
        return decodeSampledBitmapFromPath(str, imageViewSize.width, imageViewSize.height);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Runnable getTask() {
        if (this.mType == Type.FIFO) {
            return this.mTaskQueue.removeFirst();
        }
        if (this.mType == Type.LIFO) {
            return this.mTaskQueue.removeLast();
        }
        return null;
    }

    public String md5(String str) {
        try {
            return bytes2hex02(MessageDigest.getInstance("md5").digest(str.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String bytes2hex02(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreashBitmap(String str, ImageView imageView, Bitmap bitmap) {
        Message obtain = Message.obtain();
        ImgBeanHolder imgBeanHolder = new ImgBeanHolder();
        imgBeanHolder.bitmap = bitmap;
        imgBeanHolder.path = str;
        imgBeanHolder.imageView = imageView;
        obtain.obj = imgBeanHolder;
        this.mUIHandler.sendMessage(obtain);
    }

    protected void addBitmapToLruCache(String str, Bitmap bitmap) {
        if (getBitmapFromLruCache(str) != null || bitmap == null) {
            return;
        }
        this.mLruCache.put(str, bitmap);
    }

    protected Bitmap decodeSampledBitmapFromPath(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = ImageSizeUtil.caculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    private synchronized void addTask(Runnable runnable) {
        this.mTaskQueue.add(runnable);
        try {
            if (this.mPoolThreadHandler == null) {
                this.mSemaphorePoolThreadHandler.acquire();
            }
        } catch (InterruptedException unused) {
        }
        this.mPoolThreadHandler.sendEmptyMessage(272);
    }

    public File getDiskCacheDir(Context context, String str) {
        String path;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            path = context.getExternalCacheDir().getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        return new File(path + File.separator + str);
    }

    private Bitmap getBitmapFromLruCache(String str) {
        return this.mLruCache.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class ImgBeanHolder {
        Bitmap bitmap;
        ImageView imageView;
        String path;

        private ImgBeanHolder() {
        }
    }
}
