package com.alibaba.sdk.android.oss.model;

import android.support.v4.media.session.PlaybackStateCompat;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import java.util.Map;

/* loaded from: classes.dex */
public class ResumableDownloadRequest extends OSSRequest {
    private String bucketName;
    private String checkPointFilePath;
    private String downloadToFilePath;
    private Boolean enableCheckPoint;
    private String objectKey;
    private long partSize;
    private OSSProgressCallback progressListener;
    private Range range;
    private Map<String, String> requestHeader;

    public ResumableDownloadRequest(String str, String str2, String str3) {
        this.enableCheckPoint = false;
        this.partSize = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        this.bucketName = str;
        this.objectKey = str2;
        this.downloadToFilePath = str3;
    }

    public ResumableDownloadRequest(String str, String str2, String str3, String str4) {
        this.enableCheckPoint = false;
        this.partSize = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        this.bucketName = str;
        this.objectKey = str2;
        this.downloadToFilePath = str3;
        this.enableCheckPoint = true;
        this.checkPointFilePath = str4;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public Range getRange() {
        return this.range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public OSSProgressCallback getProgressListener() {
        return this.progressListener;
    }

    public void setProgressListener(OSSProgressCallback oSSProgressCallback) {
        this.progressListener = oSSProgressCallback;
    }

    public String getDownloadToFilePath() {
        return this.downloadToFilePath;
    }

    public void setDownloadToFilePath(String str) {
        this.downloadToFilePath = str;
    }

    public Boolean getEnableCheckPoint() {
        return this.enableCheckPoint;
    }

    public void setEnableCheckPoint(Boolean bool) {
        this.enableCheckPoint = bool;
    }

    public String getCheckPointFilePath() {
        return this.checkPointFilePath;
    }

    public void setCheckPointFilePath(String str) {
        this.checkPointFilePath = str;
    }

    public long getPartSize() {
        return this.partSize;
    }

    public void setPartSize(long j) {
        this.partSize = j;
    }

    public String getTempFilePath() {
        return this.downloadToFilePath + ".tmp";
    }

    public Map<String, String> getRequestHeader() {
        return this.requestHeader;
    }

    public void setRequestHeader(Map<String, String> map) {
        this.requestHeader = map;
    }
}
