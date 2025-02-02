package com.yalantis.ucrop.model;

/* loaded from: classes3.dex */
public class ExifInfo {
    private int mExifDegrees;
    private int mExifOrientation;
    private int mExifTranslation;

    public ExifInfo(int i, int i2, int i3) {
        this.mExifOrientation = i;
        this.mExifDegrees = i2;
        this.mExifTranslation = i3;
    }

    public int getExifOrientation() {
        return this.mExifOrientation;
    }

    public int getExifDegrees() {
        return this.mExifDegrees;
    }

    public int getExifTranslation() {
        return this.mExifTranslation;
    }

    public void setExifOrientation(int i) {
        this.mExifOrientation = i;
    }

    public void setExifDegrees(int i) {
        this.mExifDegrees = i;
    }

    public void setExifTranslation(int i) {
        this.mExifTranslation = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExifInfo exifInfo = (ExifInfo) obj;
        return this.mExifOrientation == exifInfo.mExifOrientation && this.mExifDegrees == exifInfo.mExifDegrees && this.mExifTranslation == exifInfo.mExifTranslation;
    }

    public int hashCode() {
        return (((this.mExifOrientation * 31) + this.mExifDegrees) * 31) + this.mExifTranslation;
    }
}
