package com.google.android.gms.internal.recaptcha;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.recaptcha.RecaptchaAction;
import com.google.android.gms.recaptcha.RecaptchaHandle;
import com.google.firebase.remoteconfiginterop.BuildConfig;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzw implements Parcelable.Creator<zzv> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzv createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = BuildConfig.VERSION_NAME;
        RecaptchaHandle recaptchaHandle = null;
        RecaptchaAction recaptchaAction = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                recaptchaHandle = (RecaptchaHandle) SafeParcelReader.createParcelable(parcel, readHeader, RecaptchaHandle.CREATOR);
            } else if (fieldId == 2) {
                recaptchaAction = (RecaptchaAction) SafeParcelReader.createParcelable(parcel, readHeader, RecaptchaAction.CREATOR);
            } else if (fieldId == 3) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzv(recaptchaHandle, recaptchaAction, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzv[] newArray(int i) {
        return new zzv[i];
    }
}
