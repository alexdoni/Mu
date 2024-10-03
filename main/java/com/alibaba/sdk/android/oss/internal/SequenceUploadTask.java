package com.alibaba.sdk.android.oss.internal;

import android.text.TextUtils;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.OSSSharedPreferences;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class SequenceUploadTask extends BaseMultipartUploadTask<ResumableUploadRequest, ResumableUploadResult> implements Callable<ResumableUploadResult> {
    private List<Integer> mAlreadyUploadIndex;
    private File mCRC64RecordFile;
    private long mFirstPartSize;
    private File mRecordFile;
    private OSSSharedPreferences mSp;

    public SequenceUploadTask(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback, ExecutionContext executionContext, InternalRequestOperation internalRequestOperation) {
        super(internalRequestOperation, resumableUploadRequest, oSSCompletedCallback, executionContext);
        this.mAlreadyUploadIndex = new ArrayList();
        this.mSp = OSSSharedPreferences.instance(this.mContext.getApplicationContext());
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x017d A[Catch: ClientException -> 0x01e8, ServiceException -> 0x01ea, TryCatch #6 {ClientException -> 0x01e8, ServiceException -> 0x01ea, blocks: (B:35:0x0164, B:36:0x0177, B:38:0x017d, B:40:0x0199, B:42:0x019f, B:44:0x01ad, B:45:0x01c2, B:47:0x01df), top: B:34:0x0164 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0235  */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void initMultipartUploadId() throws java.io.IOException, com.alibaba.sdk.android.oss.ClientException, com.alibaba.sdk.android.oss.ServiceException {
        /*
            Method dump skipped, instructions count: 649
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.SequenceUploadTask.initMultipartUploadId():void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    public ResumableUploadResult doMultipartUpload() throws IOException, ClientException, ServiceException, InterruptedException {
        long j = this.mUploadedLength;
        checkCancel();
        int i = this.mPartAttr[0];
        int i2 = this.mPartAttr[1];
        if (this.mPartETags.size() > 0 && this.mAlreadyUploadIndex.size() > 0) {
            if (this.mUploadedLength > this.mFileLength) {
                throw new ClientException("The uploading file is inconsistent with before");
            }
            if (this.mFirstPartSize != i) {
                throw new ClientException("The part size setting is inconsistent with before");
            }
            long j2 = this.mUploadedLength;
            if (!TextUtils.isEmpty(this.mSp.getStringValue(this.mUploadId))) {
                j2 = Long.valueOf(this.mSp.getStringValue(this.mUploadId)).longValue();
            }
            long j3 = j2;
            if (this.mProgressCallback != null) {
                this.mProgressCallback.onProgress(this.mRequest, j3, this.mFileLength);
            }
            this.mSp.removeKey(this.mUploadId);
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.mAlreadyUploadIndex.size() == 0 || !this.mAlreadyUploadIndex.contains(Integer.valueOf(i3 + 1))) {
                if (i3 == i2 - 1) {
                    i = (int) (this.mFileLength - j);
                }
                OSSLog.logDebug("upload part readByte : " + i);
                j += (long) i;
                uploadPart(i3, i, i2);
                if (this.mUploadException != null) {
                    break;
                }
            }
        }
        checkException();
        CompleteMultipartUploadResult completeMultipartUploadResult = completeMultipartUploadResult();
        ResumableUploadResult resumableUploadResult = completeMultipartUploadResult != null ? new ResumableUploadResult(completeMultipartUploadResult) : null;
        File file = this.mRecordFile;
        if (file != null) {
            file.delete();
        }
        File file2 = this.mCRC64RecordFile;
        if (file2 != null) {
            file2.delete();
        }
        return resumableUploadResult;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0162 A[Catch: all -> 0x01b9, TryCatch #15 {all -> 0x01b9, blocks: (B:16:0x007a, B:18:0x0092, B:20:0x00c2, B:21:0x00cd, B:23:0x00e6, B:32:0x0101, B:33:0x0115, B:36:0x015a, B:38:0x0162, B:47:0x0166, B:49:0x0180, B:50:0x019e), top: B:15:0x007a }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01ab A[Catch: IOException -> 0x0142, TRY_ENTER, TryCatch #14 {IOException -> 0x0142, blocks: (B:25:0x00f2, B:27:0x00f7, B:29:0x00fc, B:58:0x013e, B:60:0x0146, B:62:0x014b, B:40:0x01ab, B:42:0x01b0, B:44:0x01b5), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01b0 A[Catch: IOException -> 0x0142, TryCatch #14 {IOException -> 0x0142, blocks: (B:25:0x00f2, B:27:0x00f7, B:29:0x00fc, B:58:0x013e, B:60:0x0146, B:62:0x014b, B:40:0x01ab, B:42:0x01b0, B:44:0x01b5), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01b5 A[Catch: IOException -> 0x0142, TRY_LEAVE, TryCatch #14 {IOException -> 0x0142, blocks: (B:25:0x00f2, B:27:0x00f7, B:29:0x00fc, B:58:0x013e, B:60:0x0146, B:62:0x014b, B:40:0x01ab, B:42:0x01b0, B:44:0x01b5), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0166 A[Catch: all -> 0x01b9, TryCatch #15 {all -> 0x01b9, blocks: (B:16:0x007a, B:18:0x0092, B:20:0x00c2, B:21:0x00cd, B:23:0x00e6, B:32:0x0101, B:33:0x0115, B:36:0x015a, B:38:0x0162, B:47:0x0166, B:49:0x0180, B:50:0x019e), top: B:15:0x007a }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x013e A[Catch: IOException -> 0x0142, TRY_ENTER, TryCatch #14 {IOException -> 0x0142, blocks: (B:25:0x00f2, B:27:0x00f7, B:29:0x00fc, B:58:0x013e, B:60:0x0146, B:62:0x014b, B:40:0x01ab, B:42:0x01b0, B:44:0x01b5), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0146 A[Catch: IOException -> 0x0142, TryCatch #14 {IOException -> 0x0142, blocks: (B:25:0x00f2, B:27:0x00f7, B:29:0x00fc, B:58:0x013e, B:60:0x0146, B:62:0x014b, B:40:0x01ab, B:42:0x01b0, B:44:0x01b5), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x014b A[Catch: IOException -> 0x0142, TRY_LEAVE, TryCatch #14 {IOException -> 0x0142, blocks: (B:25:0x00f2, B:27:0x00f7, B:29:0x00fc, B:58:0x013e, B:60:0x0146, B:62:0x014b, B:40:0x01ab, B:42:0x01b0, B:44:0x01b5), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01c6 A[Catch: IOException -> 0x01c2, TryCatch #13 {IOException -> 0x01c2, blocks: (B:81:0x01be, B:70:0x01c6, B:72:0x01cb), top: B:80:0x01be }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01cb A[Catch: IOException -> 0x01c2, TRY_LEAVE, TryCatch #13 {IOException -> 0x01c2, blocks: (B:81:0x01be, B:70:0x01c6, B:72:0x01cb), top: B:80:0x01be }] */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void uploadPart(int r16, int r17, int r18) {
        /*
            Method dump skipped, instructions count: 467
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.SequenceUploadTask.uploadPart(int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    public void checkException() throws IOException, ServiceException, ClientException {
        if (this.mContext.getCancellationHandler().isCancelled()) {
            if (((ResumableUploadRequest) this.mRequest).deleteUploadOnCancelling().booleanValue()) {
                abortThisUpload();
                File file = this.mRecordFile;
                if (file != null) {
                    file.delete();
                }
            } else if (this.mPartETags != null && this.mPartETags.size() > 0 && this.mCheckCRC64 && ((ResumableUploadRequest) this.mRequest).getRecordDirectory() != null) {
                HashMap hashMap = new HashMap();
                for (PartETag partETag : this.mPartETags) {
                    hashMap.put(Integer.valueOf(partETag.getPartNumber()), Long.valueOf(partETag.getCRC64()));
                }
                ObjectOutputStream objectOutputStream = null;
                try {
                    try {
                        File file2 = new File(((ResumableUploadRequest) this.mRequest).getRecordDirectory() + File.separator + this.mUploadId);
                        this.mCRC64RecordFile = file2;
                        if (!file2.exists()) {
                            this.mCRC64RecordFile.createNewFile();
                        }
                        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(this.mCRC64RecordFile));
                        try {
                            objectOutputStream2.writeObject(hashMap);
                            objectOutputStream2.close();
                        } catch (IOException e) {
                            e = e;
                            objectOutputStream = objectOutputStream2;
                            OSSLog.logThrowable2Local(e);
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            super.checkException();
                        } catch (Throwable th) {
                            th = th;
                            objectOutputStream = objectOutputStream2;
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e2) {
                    e = e2;
                }
            }
        }
        super.checkException();
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void abortThisUpload() {
        if (this.mUploadId != null) {
            this.mApiOperation.abortMultipartUpload(new AbortMultipartUploadRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId), null).waitUntilFinished();
        }
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void processException(Exception exc) {
        if (this.mUploadException == null || !exc.getMessage().equals(this.mUploadException.getMessage())) {
            this.mUploadException = exc;
        }
        OSSLog.logThrowable2Local(exc);
        if (!this.mContext.getCancellationHandler().isCancelled() || this.mIsCancel) {
            return;
        }
        this.mIsCancel = true;
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void uploadPartFinish(PartETag partETag) throws Exception {
        if (!this.mContext.getCancellationHandler().isCancelled() || this.mSp.contains(this.mUploadId)) {
            return;
        }
        this.mSp.setStringValue(this.mUploadId, String.valueOf(this.mUploadedLength));
        onProgressCallback(this.mRequest, this.mUploadedLength, this.mFileLength);
    }
}
