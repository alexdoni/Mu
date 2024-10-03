package com.google.android.play.core.integrity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import java.util.Locale;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
public class IntegrityServiceException extends ApiException {
    private final Throwable zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IntegrityServiceException(int i, Throwable th) {
        super(new Status(i, String.format(Locale.ROOT, "Integrity API error (%d): %s.", Integer.valueOf(i), com.google.android.play.core.integrity.model.zza.zza(i))));
        if (i != 0) {
            this.zza = th;
            return;
        }
        throw new IllegalArgumentException("ErrorCode should not be 0.");
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable getCause() {
        return this.zza;
    }

    public int getErrorCode() {
        return super.getStatusCode();
    }
}
