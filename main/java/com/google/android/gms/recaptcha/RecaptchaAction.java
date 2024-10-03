package com.google.android.gms.recaptcha;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class RecaptchaAction extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RecaptchaAction> CREATOR = new zzd();
    private final RecaptchaActionType zza;
    private final String zzb;
    private final Bundle zzc;
    private final String zzd;

    public RecaptchaAction(RecaptchaAction recaptchaAction, String str) {
        this(recaptchaAction.getAction(), recaptchaAction.getCustomAction(), recaptchaAction.getAdditionalArgs(), str);
    }

    public RecaptchaActionType getAction() {
        return this.zza;
    }

    public Bundle getAdditionalArgs() {
        return this.zzc;
    }

    public String getCustomAction() {
        return this.zzb;
    }

    public String getVerificationHistoryToken() {
        return this.zzd;
    }

    public String toString() {
        return (!"other".equals(this.zza.zza) || this.zzb.isEmpty()) ? this.zza.zza : this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getAction(), i, false);
        SafeParcelWriter.writeString(parcel, 2, getCustomAction(), false);
        SafeParcelWriter.writeBundle(parcel, 3, getAdditionalArgs(), false);
        SafeParcelWriter.writeString(parcel, 4, getVerificationHistoryToken(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public RecaptchaAction(RecaptchaActionType recaptchaActionType) {
        this(recaptchaActionType, "", new Bundle(), "");
    }

    public RecaptchaAction(RecaptchaActionType recaptchaActionType, Bundle bundle) {
        this(recaptchaActionType, "", bundle, "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecaptchaAction(RecaptchaActionType recaptchaActionType, String str, Bundle bundle, String str2) {
        this.zza = recaptchaActionType;
        this.zzb = str;
        this.zzc = bundle;
        this.zzd = str2;
    }

    public RecaptchaAction(String str) {
        this(new RecaptchaActionType("other"), str, new Bundle(), "");
    }

    public RecaptchaAction(String str, Bundle bundle) {
        this(new RecaptchaActionType("other"), str, bundle, "");
    }
}
