package com.google.android.play.core.review;

import android.app.PendingIntent;
import com.oversea.ab_firstarea.utils.UpdateHelper;

/* compiled from: com.google.android.play:review@@2.0.1 */
/* loaded from: classes2.dex */
final class zza extends ReviewInfo {
    private final PendingIntent zza;
    private final boolean zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(PendingIntent pendingIntent, boolean z) {
        if (pendingIntent == null) {
            throw new NullPointerException("Null pendingIntent");
        }
        this.zza = pendingIntent;
        this.zzb = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReviewInfo) {
            ReviewInfo reviewInfo = (ReviewInfo) obj;
            if (this.zza.equals(reviewInfo.zza()) && this.zzb == reviewInfo.zzb()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ (true != this.zzb ? 1237 : UpdateHelper.UPDATE_REQUEST_CODE);
    }

    public final String toString() {
        return "ReviewInfo{pendingIntent=" + this.zza.toString() + ", isNoOp=" + this.zzb + "}";
    }

    @Override // com.google.android.play.core.review.ReviewInfo
    final PendingIntent zza() {
        return this.zza;
    }

    @Override // com.google.android.play.core.review.ReviewInfo
    final boolean zzb() {
        return this.zzb;
    }
}
