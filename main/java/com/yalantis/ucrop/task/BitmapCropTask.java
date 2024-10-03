package com.yalantis.ucrop.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import com.yalantis.ucrop.model.CropParameters;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.model.ImageState;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import com.yalantis.ucrop.util.FileUtils;
import com.yalantis.ucrop.util.ImageHeaderParser;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class BitmapCropTask extends AsyncTask<Void, Void, Throwable> {
    private static final String CONTENT_SCHEME = "content";
    private static final int MIN_CROPPED_HEIGHT = 1;
    private static final String TAG = "BitmapCropTask";
    private int cropOffsetX;
    private int cropOffsetY;
    private final Bitmap.CompressFormat mCompressFormat;
    private final int mCompressQuality;
    private final WeakReference<Context> mContext;
    private final BitmapCropCallback mCropCallback;
    private final RectF mCropRect;
    private int mCroppedImageHeight;
    private int mCroppedImageWidth;
    private float mCurrentAngle;
    private final RectF mCurrentImageRect;
    private float mCurrentScale;
    private final ExifInfo mExifInfo;
    private final String mImageInputPath;
    private final Uri mImageInputUri;
    private final String mImageOutputPath;
    private final Uri mImageOutputUri;
    private final int mMaxResultImageSizeX;
    private final int mMaxResultImageSizeY;
    private Bitmap mViewBitmap;

    public BitmapCropTask(Context context, Bitmap bitmap, ImageState imageState, CropParameters cropParameters, BitmapCropCallback bitmapCropCallback) {
        this.mContext = new WeakReference<>(context);
        this.mViewBitmap = bitmap;
        this.mCropRect = imageState.getCropRect();
        this.mCurrentImageRect = imageState.getCurrentImageRect();
        this.mCurrentScale = imageState.getCurrentScale();
        this.mCurrentAngle = imageState.getCurrentAngle();
        this.mMaxResultImageSizeX = cropParameters.getMaxResultImageSizeX();
        this.mMaxResultImageSizeY = cropParameters.getMaxResultImageSizeY();
        this.mCompressFormat = cropParameters.getCompressFormat();
        this.mCompressQuality = cropParameters.getCompressQuality();
        this.mImageInputPath = cropParameters.getImageInputPath();
        this.mImageOutputPath = cropParameters.getImageOutputPath();
        this.mImageInputUri = cropParameters.getContentImageInputUri();
        this.mImageOutputUri = cropParameters.getContentImageOutputUri();
        this.mExifInfo = cropParameters.getExifInfo();
        this.mCropCallback = bitmapCropCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Throwable doInBackground(Void... voidArr) {
        Bitmap bitmap = this.mViewBitmap;
        if (bitmap == null) {
            return new NullPointerException("ViewBitmap is null");
        }
        if (bitmap.isRecycled()) {
            return new NullPointerException("ViewBitmap is recycled");
        }
        if (this.mCurrentImageRect.isEmpty()) {
            return new NullPointerException("CurrentImageRect is empty");
        }
        if (this.mImageOutputUri == null) {
            return new NullPointerException("ImageOutputUri is null");
        }
        try {
            crop();
            this.mViewBitmap = null;
            return null;
        } catch (Throwable th) {
            return th;
        }
    }

    private boolean crop() throws IOException {
        Context context = this.mContext.get();
        if (context == null) {
            return false;
        }
        if (this.mMaxResultImageSizeX > 0 && this.mMaxResultImageSizeY > 0) {
            float width = this.mCropRect.width() / this.mCurrentScale;
            float height = this.mCropRect.height() / this.mCurrentScale;
            int i = this.mMaxResultImageSizeX;
            if (width > i || height > this.mMaxResultImageSizeY) {
                float min = Math.min(i / width, this.mMaxResultImageSizeY / height);
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(this.mViewBitmap, Math.round(r3.getWidth() * min), Math.round(this.mViewBitmap.getHeight() * min), false);
                Bitmap bitmap = this.mViewBitmap;
                if (bitmap != createScaledBitmap) {
                    bitmap.recycle();
                }
                this.mViewBitmap = createScaledBitmap;
                this.mCurrentScale /= min;
            }
        }
        if (this.mCurrentAngle != 0.0f) {
            Matrix matrix = new Matrix();
            matrix.setRotate(this.mCurrentAngle, this.mViewBitmap.getWidth() / 2, this.mViewBitmap.getHeight() / 2);
            Bitmap bitmap2 = this.mViewBitmap;
            Bitmap createBitmap = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), this.mViewBitmap.getHeight(), matrix, true);
            Bitmap bitmap3 = this.mViewBitmap;
            if (bitmap3 != createBitmap) {
                bitmap3.recycle();
            }
            this.mViewBitmap = createBitmap;
        }
        this.cropOffsetX = Math.round((this.mCropRect.left - this.mCurrentImageRect.left) / this.mCurrentScale);
        this.cropOffsetY = Math.round((this.mCropRect.top - this.mCurrentImageRect.top) / this.mCurrentScale);
        this.mCroppedImageWidth = Math.round(this.mCropRect.width() / this.mCurrentScale);
        int round = Math.round(this.mCropRect.height() / this.mCurrentScale);
        this.mCroppedImageHeight = round;
        boolean shouldCrop = shouldCrop(this.mCroppedImageWidth, round);
        Log.i(TAG, "Should crop: " + shouldCrop);
        if (shouldCrop) {
            checkValidityCropBounds();
            saveImage(Bitmap.createBitmap(this.mViewBitmap, this.cropOffsetX, this.cropOffsetY, this.mCroppedImageWidth, this.mCroppedImageHeight));
            if (!this.mCompressFormat.equals(Bitmap.CompressFormat.JPEG)) {
                return true;
            }
            copyExifForOutputFile(context);
            return true;
        }
        if (Build.VERSION.SDK_INT >= 29 && FileUtils.isContent(this.mImageInputPath)) {
            FileUtils.writeFileFromIS(context.getContentResolver().openInputStream(Uri.parse(this.mImageInputPath)), new FileOutputStream(this.mImageOutputPath));
        } else {
            FileUtils.copyFile(this.mImageInputPath, this.mImageOutputPath);
        }
        return false;
    }

    private void checkValidityCropBounds() {
        if (this.cropOffsetX < 0) {
            this.cropOffsetX = 0;
            this.mCroppedImageWidth = this.mViewBitmap.getWidth();
        }
        if (this.cropOffsetY < 0) {
            this.cropOffsetY = 0;
            this.mCroppedImageHeight = this.mViewBitmap.getHeight();
        }
    }

    private void copyExifForOutputFile(Context context) throws IOException {
        boolean hasContentScheme = BitmapLoadUtils.hasContentScheme(this.mImageInputUri);
        boolean hasContentScheme2 = BitmapLoadUtils.hasContentScheme(this.mImageOutputUri);
        if (hasContentScheme && hasContentScheme2) {
            ImageHeaderParser.copyExif(context, this.mCroppedImageWidth, this.mCroppedImageHeight, this.mImageInputUri, this.mImageOutputUri);
            return;
        }
        if (hasContentScheme) {
            ImageHeaderParser.copyExif(context, this.mCroppedImageWidth, this.mCroppedImageHeight, this.mImageInputUri, this.mImageOutputPath);
        } else if (hasContentScheme2) {
            ImageHeaderParser.copyExif(context, new ExifInterface(this.mImageInputPath), this.mCroppedImageWidth, this.mCroppedImageHeight, this.mImageOutputUri);
        } else {
            ImageHeaderParser.copyExif(new ExifInterface(this.mImageInputPath), this.mCroppedImageWidth, this.mCroppedImageHeight, this.mImageOutputPath);
        }
    }

    private void saveImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        OutputStream openOutputStream;
        Context context = this.mContext.get();
        if (context == null) {
            return;
        }
        OutputStream outputStream = null;
        try {
            openOutputStream = context.getContentResolver().openOutputStream(this.mImageOutputUri);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (IOException e) {
                e = e;
                byteArrayOutputStream = null;
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = null;
            }
        } catch (IOException e2) {
            e = e2;
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
        try {
            bitmap.compress(this.mCompressFormat, this.mCompressQuality, byteArrayOutputStream);
            openOutputStream.write(byteArrayOutputStream.toByteArray());
            bitmap.recycle();
            BitmapLoadUtils.close(openOutputStream);
        } catch (IOException e3) {
            e = e3;
            outputStream = openOutputStream;
            try {
                Log.e(TAG, e.getLocalizedMessage());
                BitmapLoadUtils.close(outputStream);
                BitmapLoadUtils.close(byteArrayOutputStream);
            } catch (Throwable th3) {
                th = th3;
                BitmapLoadUtils.close(outputStream);
                BitmapLoadUtils.close(byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            outputStream = openOutputStream;
            BitmapLoadUtils.close(outputStream);
            BitmapLoadUtils.close(byteArrayOutputStream);
            throw th;
        }
        BitmapLoadUtils.close(byteArrayOutputStream);
    }

    private boolean shouldCrop(int i, int i2) {
        int round = Math.round(Math.max(i, i2) / 1000.0f) + 1;
        if (this.mMaxResultImageSizeX > 0 && this.mMaxResultImageSizeY > 0) {
            return true;
        }
        float f = round;
        return Math.abs(this.mCropRect.left - this.mCurrentImageRect.left) > f || Math.abs(this.mCropRect.top - this.mCurrentImageRect.top) > f || Math.abs(this.mCropRect.bottom - this.mCurrentImageRect.bottom) > f || Math.abs(this.mCropRect.right - this.mCurrentImageRect.right) > f || this.mCurrentAngle != 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Throwable th) {
        Uri fromFile;
        BitmapCropCallback bitmapCropCallback = this.mCropCallback;
        if (bitmapCropCallback != null) {
            if (th == null) {
                if (BitmapLoadUtils.hasContentScheme(this.mImageOutputUri)) {
                    fromFile = this.mImageOutputUri;
                } else {
                    fromFile = Uri.fromFile(new File(this.mImageOutputPath));
                }
                this.mCropCallback.onBitmapCropped(fromFile, this.cropOffsetX, this.cropOffsetY, this.mCroppedImageWidth, this.mCroppedImageHeight);
                return;
            }
            bitmapCropCallback.onCropFailure(th);
        }
    }
}
