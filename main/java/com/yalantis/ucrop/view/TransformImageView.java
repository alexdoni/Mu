package com.yalantis.ucrop.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.yalantis.ucrop.UCropDevelopConfig;
import com.yalantis.ucrop.UCropImageEngine;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import com.yalantis.ucrop.util.FastBitmapDrawable;
import com.yalantis.ucrop.util.FileUtils;
import com.yalantis.ucrop.util.RectUtils;

/* loaded from: classes3.dex */
public class TransformImageView extends AppCompatImageView {
    private static final int MATRIX_VALUES_COUNT = 9;
    private static final int RECT_CENTER_POINT_COORDS = 2;
    private static final int RECT_CORNER_POINTS_COORDS = 8;
    private static final String TAG = "TransformImageView";
    protected boolean mBitmapDecoded;
    protected boolean mBitmapLaidOut;
    protected final float[] mCurrentImageCenter;
    protected final float[] mCurrentImageCorners;
    protected Matrix mCurrentImageMatrix;
    private ExifInfo mExifInfo;
    private String mImageInputPath;
    private Uri mImageInputUri;
    private String mImageOutputPath;
    private Uri mImageOutputUri;
    private float[] mInitialImageCenter;
    private float[] mInitialImageCorners;
    private final float[] mMatrixValues;
    private int mMaxBitmapSize;
    protected int mThisHeight;
    protected int mThisWidth;
    protected TransformImageListener mTransformImageListener;

    /* loaded from: classes3.dex */
    public interface TransformImageListener {
        void onLoadComplete();

        void onLoadFailure(Exception exc);

        void onRotate(float f);

        void onScale(float f);
    }

    public TransformImageView(Context context) {
        this(context, null);
    }

    public TransformImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TransformImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentImageCorners = new float[8];
        this.mCurrentImageCenter = new float[2];
        this.mMatrixValues = new float[9];
        this.mCurrentImageMatrix = new Matrix();
        this.mBitmapDecoded = false;
        this.mBitmapLaidOut = false;
        this.mMaxBitmapSize = 0;
        init();
    }

    public void setTransformImageListener(TransformImageListener transformImageListener) {
        this.mTransformImageListener = transformImageListener;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == ImageView.ScaleType.MATRIX) {
            super.setScaleType(scaleType);
        } else {
            Log.w(TAG, "Invalid ScaleType. Only ScaleType.MATRIX can be used");
        }
    }

    public void setMaxBitmapSize(int i) {
        this.mMaxBitmapSize = i;
    }

    public int getMaxBitmapSize() {
        if (this.mMaxBitmapSize <= 0) {
            this.mMaxBitmapSize = BitmapLoadUtils.calculateMaxBitmapSize(getContext());
        }
        return this.mMaxBitmapSize;
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        setImageDrawable(new FastBitmapDrawable(bitmap));
    }

    public String getImageInputPath() {
        return this.mImageInputPath;
    }

    public String getImageOutputPath() {
        return this.mImageOutputPath;
    }

    public Uri getImageInputUri() {
        return this.mImageInputUri;
    }

    public Uri getImageOutputUri() {
        return this.mImageOutputUri;
    }

    public ExifInfo getExifInfo() {
        return this.mExifInfo;
    }

    public void setImageUri(Uri uri, Uri uri2, boolean z) {
        if (UCropDevelopConfig.imageEngine != null && z) {
            useCustomLoaderCrop(uri, uri2);
        } else {
            useDefaultLoaderCrop(uri, uri2);
        }
    }

    private void useCustomLoaderCrop(final Uri uri, final Uri uri2) {
        int[] maxImageSize = BitmapLoadUtils.getMaxImageSize(getContext(), uri);
        if (maxImageSize[0] > 0 && maxImageSize[1] > 0) {
            UCropDevelopConfig.imageEngine.loadImage(getContext(), uri, maxImageSize[0], maxImageSize[1], new UCropImageEngine.OnCallbackListener<Bitmap>() { // from class: com.yalantis.ucrop.view.TransformImageView.1
                @Override // com.yalantis.ucrop.UCropImageEngine.OnCallbackListener
                public void onCall(Bitmap bitmap) {
                    if (bitmap == null) {
                        TransformImageView.this.useDefaultLoaderCrop(uri, uri2);
                    } else {
                        TransformImageView.this.setBitmapLoadedResult(bitmap.copy(bitmap.getConfig(), true), new ExifInfo(0, 0, 0), uri, uri2);
                    }
                }
            });
        } else {
            useDefaultLoaderCrop(uri, uri2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void useDefaultLoaderCrop(Uri uri, Uri uri2) {
        int maxBitmapSize = getMaxBitmapSize();
        BitmapLoadUtils.decodeBitmapInBackground(getContext(), uri, uri2, maxBitmapSize, maxBitmapSize, new BitmapLoadCallback() { // from class: com.yalantis.ucrop.view.TransformImageView.2
            @Override // com.yalantis.ucrop.callback.BitmapLoadCallback
            public void onBitmapLoaded(Bitmap bitmap, ExifInfo exifInfo, Uri uri3, Uri uri4) {
                TransformImageView.this.setBitmapLoadedResult(bitmap, exifInfo, uri3, uri4);
            }

            @Override // com.yalantis.ucrop.callback.BitmapLoadCallback
            public void onFailure(Exception exc) {
                Log.e(TransformImageView.TAG, "onFailure: setImageUri", exc);
                if (TransformImageView.this.mTransformImageListener != null) {
                    TransformImageView.this.mTransformImageListener.onLoadFailure(exc);
                }
            }
        });
    }

    public void setBitmapLoadedResult(Bitmap bitmap, ExifInfo exifInfo, Uri uri, Uri uri2) {
        String str;
        this.mImageInputUri = uri;
        this.mImageOutputUri = uri2;
        this.mImageInputPath = FileUtils.isContent(uri.toString()) ? uri.toString() : uri.getPath();
        if (uri2 != null) {
            str = FileUtils.isContent(uri2.toString()) ? uri2.toString() : uri2.getPath();
        } else {
            str = null;
        }
        this.mImageOutputPath = str;
        this.mExifInfo = exifInfo;
        this.mBitmapDecoded = true;
        setImageBitmap(bitmap);
    }

    public float getCurrentScale() {
        return getMatrixScale(this.mCurrentImageMatrix);
    }

    public float getMatrixScale(Matrix matrix) {
        return (float) Math.sqrt(Math.pow(getMatrixValue(matrix, 0), 2.0d) + Math.pow(getMatrixValue(matrix, 3), 2.0d));
    }

    public float getCurrentAngle() {
        return getMatrixAngle(this.mCurrentImageMatrix);
    }

    public float getMatrixAngle(Matrix matrix) {
        return (float) (-(Math.atan2(getMatrixValue(matrix, 1), getMatrixValue(matrix, 0)) * 57.29577951308232d));
    }

    @Override // android.widget.ImageView
    public void setImageMatrix(Matrix matrix) {
        super.setImageMatrix(matrix);
        this.mCurrentImageMatrix.set(matrix);
        updateCurrentImagePoints();
    }

    public Bitmap getViewBitmap() {
        if (getDrawable() == null || !(getDrawable() instanceof FastBitmapDrawable)) {
            return null;
        }
        return ((FastBitmapDrawable) getDrawable()).getBitmap();
    }

    public void postTranslate(float f, float f2) {
        if (f == 0.0f && f2 == 0.0f) {
            return;
        }
        this.mCurrentImageMatrix.postTranslate(f, f2);
        setImageMatrix(this.mCurrentImageMatrix);
    }

    public void postScale(float f, float f2, float f3) {
        if (f != 0.0f) {
            this.mCurrentImageMatrix.postScale(f, f, f2, f3);
            setImageMatrix(this.mCurrentImageMatrix);
            TransformImageListener transformImageListener = this.mTransformImageListener;
            if (transformImageListener != null) {
                transformImageListener.onScale(getMatrixScale(this.mCurrentImageMatrix));
            }
        }
    }

    public void postRotate(float f, float f2, float f3) {
        if (f != 0.0f) {
            this.mCurrentImageMatrix.postRotate(f, f2, f3);
            setImageMatrix(this.mCurrentImageMatrix);
            TransformImageListener transformImageListener = this.mTransformImageListener;
            if (transformImageListener != null) {
                transformImageListener.onRotate(getMatrixAngle(this.mCurrentImageMatrix));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void init() {
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z || (this.mBitmapDecoded && !this.mBitmapLaidOut)) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            this.mThisWidth = width - paddingLeft;
            this.mThisHeight = height - paddingTop;
            onImageLaidOut();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onImageLaidOut() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        Log.d(TAG, String.format("Image size: [%d:%d]", Integer.valueOf((int) intrinsicWidth), Integer.valueOf((int) intrinsicHeight)));
        RectF rectF = new RectF(0.0f, 0.0f, intrinsicWidth, intrinsicHeight);
        this.mInitialImageCorners = RectUtils.getCornersFromRect(rectF);
        this.mInitialImageCenter = RectUtils.getCenterFromRect(rectF);
        this.mBitmapLaidOut = true;
        TransformImageListener transformImageListener = this.mTransformImageListener;
        if (transformImageListener != null) {
            transformImageListener.onLoadComplete();
        }
    }

    protected float getMatrixValue(Matrix matrix, int i) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i];
    }

    protected void printMatrix(String str, Matrix matrix) {
        Log.d(TAG, str + ": matrix: { x: " + getMatrixValue(matrix, 2) + ", y: " + getMatrixValue(matrix, 5) + ", scale: " + getMatrixScale(matrix) + ", angle: " + getMatrixAngle(matrix) + " }");
    }

    private void updateCurrentImagePoints() {
        this.mCurrentImageMatrix.mapPoints(this.mCurrentImageCorners, this.mInitialImageCorners);
        this.mCurrentImageMatrix.mapPoints(this.mCurrentImageCenter, this.mInitialImageCenter);
    }
}
