package com.yalantis.ucrop.model;

import android.graphics.Bitmap;
import android.net.Uri;

/* loaded from: classes3.dex */
public class CropParameters {
    private Bitmap.CompressFormat mCompressFormat;
    private int mCompressQuality;
    private Uri mContentImageInputUri;
    private Uri mContentImageOutputUri;
    private ExifInfo mExifInfo;
    private String mImageInputPath;
    private String mImageOutputPath;
    private int mMaxResultImageSizeX;
    private int mMaxResultImageSizeY;

    public CropParameters(int i, int i2, Bitmap.CompressFormat compressFormat, int i3, String str, String str2, ExifInfo exifInfo) {
        this.mMaxResultImageSizeX = i;
        this.mMaxResultImageSizeY = i2;
        this.mCompressFormat = compressFormat;
        this.mCompressQuality = i3;
        this.mImageInputPath = str;
        this.mImageOutputPath = str2;
        this.mExifInfo = exifInfo;
    }

    public int getMaxResultImageSizeX() {
        return this.mMaxResultImageSizeX;
    }

    public int getMaxResultImageSizeY() {
        return this.mMaxResultImageSizeY;
    }

    public Bitmap.CompressFormat getCompressFormat() {
        return this.mCompressFormat;
    }

    public int getCompressQuality() {
        return this.mCompressQuality;
    }

    public String getImageInputPath() {
        return this.mImageInputPath;
    }

    public String getImageOutputPath() {
        return this.mImageOutputPath;
    }

    public ExifInfo getExifInfo() {
        return this.mExifInfo;
    }

    public Uri getContentImageInputUri() {
        return this.mContentImageInputUri;
    }

    public void setContentImageInputUri(Uri uri) {
        this.mContentImageInputUri = uri;
    }

    public Uri getContentImageOutputUri() {
        return this.mContentImageOutputUri;
    }

    public void setContentImageOutputUri(Uri uri) {
        this.mContentImageOutputUri = uri;
    }
}
