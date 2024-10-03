package com.linecorp.linesdk;

import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
public class LineApiResponse<R> {
    private static final LineApiResponse<?> EMPTY_RESULT_SUCCESS = new LineApiResponse<>(LineApiResponseCode.SUCCESS, null, LineApiError.DEFAULT);
    private final LineApiError errorData;
    private final LineApiResponseCode responseCode;
    private final R responseData;

    private LineApiResponse(LineApiResponseCode lineApiResponseCode, R r, LineApiError lineApiError) {
        this.responseCode = lineApiResponseCode;
        this.responseData = r;
        this.errorData = lineApiError;
    }

    public static <T> LineApiResponse<T> createAsSuccess(T t) {
        return t == null ? (LineApiResponse<T>) EMPTY_RESULT_SUCCESS : new LineApiResponse<>(LineApiResponseCode.SUCCESS, t, LineApiError.DEFAULT);
    }

    public static <T> LineApiResponse<T> createAsError(LineApiResponseCode lineApiResponseCode, LineApiError lineApiError) {
        return new LineApiResponse<>(lineApiResponseCode, null, lineApiError);
    }

    public boolean isSuccess() {
        return this.responseCode == LineApiResponseCode.SUCCESS;
    }

    public boolean isNetworkError() {
        return this.responseCode == LineApiResponseCode.NETWORK_ERROR;
    }

    public boolean isServerError() {
        return this.responseCode == LineApiResponseCode.SERVER_ERROR;
    }

    public LineApiResponseCode getResponseCode() {
        return this.responseCode;
    }

    public R getResponseData() {
        R r = this.responseData;
        if (r != null) {
            return r;
        }
        throw new NoSuchElementException("response data is null. Please check result by isSuccess before.");
    }

    public LineApiError getErrorData() {
        return this.errorData;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LineApiResponse lineApiResponse = (LineApiResponse) obj;
        if (this.responseCode != lineApiResponse.responseCode) {
            return false;
        }
        R r = this.responseData;
        if (r == null ? lineApiResponse.responseData == null : r.equals(lineApiResponse.responseData)) {
            return this.errorData.equals(lineApiResponse.errorData);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.responseCode.hashCode() * 31;
        R r = this.responseData;
        return ((hashCode + (r != null ? r.hashCode() : 0)) * 31) + this.errorData.hashCode();
    }

    public String toString() {
        return "LineApiResponse{errorData=" + this.errorData + ", responseCode=" + this.responseCode + ", responseData=" + this.responseData + '}';
    }
}
