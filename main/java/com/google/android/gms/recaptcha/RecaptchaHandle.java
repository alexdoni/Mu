package com.google.android.gms.recaptcha;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public class RecaptchaHandle extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RecaptchaHandle> CREATOR = new zzf();
    private final String zza;
    private final String zzb;
    private final List<String> zzc;

    public RecaptchaHandle(String str, String str2, List<String> list) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = list;
    }

    public List<String> getAcceptableAdditionalArgs() {
        return this.zzc;
    }

    public String getClientPackageName() {
        return this.zzb;
    }

    public String getSiteKey() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getSiteKey(), false);
        SafeParcelWriter.writeString(parcel, 2, getClientPackageName(), false);
        SafeParcelWriter.writeStringList(parcel, 3, getAcceptableAdditionalArgs(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
