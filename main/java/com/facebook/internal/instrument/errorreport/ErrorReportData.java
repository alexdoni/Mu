package com.facebook.internal.instrument.errorreport;

import com.facebook.internal.instrument.InstrumentUtility;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.internal.ShareInternalUtility;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ErrorReportData.kt */
@Metadata(m1394d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0014\u001a\u00020\u0015J\u0011\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0000H\u0086\u0002J\u0006\u0010\u0019\u001a\u00020\u0015J\b\u0010\u001a\u001a\u00020\u0003H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006\u001c"}, m1395d2 = {"Lcom/facebook/internal/instrument/errorreport/ErrorReportData;", "", ShareConstants.WEB_DIALOG_PARAM_MESSAGE, "", "(Ljava/lang/String;)V", ShareInternalUtility.STAGING_PARAM, "Ljava/io/File;", "(Ljava/io/File;)V", "errorMessage", FileDownloadModel.FILENAME, "isValid", "", "()Z", "parameters", "Lorg/json/JSONObject;", "getParameters", "()Lorg/json/JSONObject;", ErrorReportData.PARAM_TIMESTAMP, "", "Ljava/lang/Long;", "clear", "", "compareTo", "", "data", "save", "toString", "Companion", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class ErrorReportData {
    private static final String PARAM_TIMESTAMP = "timestamp";
    private static final String PRARAM_ERROR_MESSAGE = "error_message";
    private String errorMessage;
    private String filename;
    private Long timestamp;

    public ErrorReportData(String str) {
        this.timestamp = Long.valueOf(System.currentTimeMillis() / 1000);
        this.errorMessage = str;
        StringBuffer stringBuffer = new StringBuffer(InstrumentUtility.ERROR_REPORT_PREFIX);
        Long l = this.timestamp;
        if (l == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
        }
        String stringBuffer2 = stringBuffer.append(l.longValue()).append(".json").toString();
        Intrinsics.checkNotNullExpressionValue(stringBuffer2, "StringBuffer()\n            .append(InstrumentUtility.ERROR_REPORT_PREFIX)\n            .append(timestamp as Long)\n            .append(\".json\")\n            .toString()");
        this.filename = stringBuffer2;
    }

    public ErrorReportData(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "file.name");
        this.filename = name;
        InstrumentUtility instrumentUtility = InstrumentUtility.INSTANCE;
        JSONObject readFile = InstrumentUtility.readFile(this.filename, true);
        if (readFile != null) {
            this.timestamp = Long.valueOf(readFile.optLong(PARAM_TIMESTAMP, 0L));
            this.errorMessage = readFile.optString("error_message", null);
        }
    }

    public final int compareTo(ErrorReportData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Long l = this.timestamp;
        if (l == null) {
            return -1;
        }
        long longValue = l.longValue();
        Long l2 = data.timestamp;
        if (l2 == null) {
            return 1;
        }
        return Intrinsics.compare(l2.longValue(), longValue);
    }

    public final boolean isValid() {
        return (this.errorMessage == null || this.timestamp == null) ? false : true;
    }

    public final void save() {
        if (isValid()) {
            InstrumentUtility instrumentUtility = InstrumentUtility.INSTANCE;
            InstrumentUtility.writeFile(this.filename, toString());
        }
    }

    public final void clear() {
        InstrumentUtility instrumentUtility = InstrumentUtility.INSTANCE;
        InstrumentUtility.deleteFile(this.filename);
    }

    public String toString() {
        JSONObject parameters = getParameters();
        if (parameters == null) {
            return super.toString();
        }
        String jSONObject = parameters.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "params.toString()");
        return jSONObject;
    }

    public final JSONObject getParameters() {
        JSONObject jSONObject = new JSONObject();
        try {
            Long l = this.timestamp;
            if (l != null) {
                jSONObject.put(PARAM_TIMESTAMP, l);
            }
            jSONObject.put("error_message", this.errorMessage);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
